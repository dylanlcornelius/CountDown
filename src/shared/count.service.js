import { Observable } from 'rxjs';
import FirestoreService from './firestore.service.js';

const ref = FirestoreService.getRef('counts');

export default {
    get(uid) {
        if (!uid) {
            return new Observable(observable => observable.next([]));
        }
        return FirestoreService.get(ref, uid, 'uids');
    },
    upsert(data) {
        FirestoreService.upsert(ref, data);
    },
    delete(id) {
        FirestoreService.delete(ref, id);
    }
}
