import FirestoreService from './firestore.service.js';

export default {
    ref: FirestoreService.getRef('counts'),
    async get() {
        return FirestoreService.get(ref);
    },
    async upsert(data) {
        FirestoreService.upsert(ref, data);
    },
    async delete(id) {
        FirestoreService.delete(ref, id);
    }
}
