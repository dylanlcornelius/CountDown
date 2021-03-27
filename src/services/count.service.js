import { Observable } from 'rxjs';
import FirestoreService from './firestore.service.js';
import { STATUS_TYPES } from '../stores/status.store.js';

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
            data.order = -1;
            FirestoreService.upsert(ref, data);
        } else {
            FirestoreService.delete(ref, data.id);
        }
    },
    reorder(isUp, countId, counts) {
        // always reindex ordering to account for new, deleted, and archived counts
        const newCounts = counts.map((count, i) => ({...count, order: i}));
        const order = newCounts.find(({id}) => countId === id).order;

        const reorderedCounts = newCounts.map((count) => {
            if (countId === count.id) {
                if (isUp && count.order !== 0) {
                    count.order--;
                } else if (!isUp && count.order !== newCounts.length - 1) {
                    count.order++;
                }
            } else if (isUp && order - 1 === count.order) {
                count.order++;
            } else if (!isUp && order + 1 === count.order) {
                count.order--;
            }
            return count;
        });

        this.upsert(reorderedCounts);
    },
    sort(a, b) {
        if (a.order === b.order) {
            return 0;
        } else if (a.order === undefined) {
            return 1;
        } else if (b.order === undefined) {
            return -1;
        } else {
            return a.order < b.order ? -1 : 1;
        }
    }
}
