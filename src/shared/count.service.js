import { Observable } from 'rxjs';
import FirestoreService from './firestore.service.js';
import { STATUS_TYPES } from './status.store.js';

const ref = FirestoreService.getRef('counts');

export default {
    get(uid) {
        if (!uid) {
            return new Observable(observable => observable.next([]));
        }
        return FirestoreService.get(ref.where('uids', 'array-contains', uid));
    },
    upsert(data) {
        FirestoreService.upsert(ref, data);
    },
    delete(data) {
        if (data.status === STATUS_TYPES.ACTIVE) {
            data.status = 'archived';
            FirestoreService.upsert(ref, data);
        } else {
            FirestoreService.delete(ref, data.id);
        }
    }
}
