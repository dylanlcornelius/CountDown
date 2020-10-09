import { writable } from 'svelte/store';

function createCounts() {
	const { subscribe, set, update } = writable([]);

	return {
        subscribe,
        get: () => set(CountService.get())
	};
}

export const counts = createCounts();
