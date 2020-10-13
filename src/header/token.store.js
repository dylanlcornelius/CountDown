import { writable } from 'svelte/store';

function createToken() {
    const { subscribe, set } = writable();

    return {
        subscribe,
        set: (token) => set(token),
        remove: () => set()
    }
}

export const token = createToken();
