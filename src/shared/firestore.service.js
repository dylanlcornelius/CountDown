import { firebase } from '@firebase/app';
import { environment } from '../environment.js';
import 'firebase/firestore';
import { Observable } from 'rxjs';

firebase.initializeApp(environment.firebaseConfig);
firebase.firestore().enablePersistence();
const firestore = firebase.firestore();

export default {
    getRef(collection) {
        return firestore.collection(collection);
    },
    getAll(ref) {
        return new Observable(observer => {
            ref.onSnapshot(querySnapshot => {
                observer.next(querySnapshot.docs.map(doc => ({ id: doc.id, ...doc.data()})));
            });
        });
    },
    get(ref) {
        return this.getAll(ref);
    },
    insert(ref, data) {
        ref.add(data).catch(error => {
            console.error(error);
        });
    },
    update(ref, data) {
        ref.doc(data.id).set(data).catch(error => {
            console.error(error);
        });
    },
    upsert(ref, data) {
        if (data.id) {
            this.update(ref, data);
        } else {
            this.insert(ref, data)
        }
    },
    delete(ref, id) {
        ref.doc(id).delete().catch(error => {
            console.error(error);
        });
    }
}
