import { firebase } from '@firebase/app';
import { environment } from '../environment.js';
import 'firebase/firestore';

firebase.initializeApp(environment.firebaseConfig);
firebase.firestore().enablePersistence();
const firestore = firebase.firestore();

export default {
    getRef(collection) {
        return firestore.collection(collection);
    },
    async get(ref) {
        return new Observable(observer => {
            ref.onSnapshot(async querySnapshot => {
                observer.next(querySnapshot.map(doc => {
                    return { id: doc.id, ...doc.data()};
                }));
            });
        });
    },
    async insert(ref, data) {
        ref.add(data).catch(error => {
            console.error(error);
        });
    },
    async update(ref, data) {
        ref.doc(data.id).set(data).catch(error => {
            console.error(error);
        });
    },
    async upsert(ref, data) {
        if (data.id) {
            this.update(ref, data);
        } else {
            this.insert(ref, data)
        }
    },
    async delete(ref, id) {
        ref.doc(id).delete().catch(error => {
            console.error(error);
        });
    }
}
