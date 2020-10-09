import { writable } from 'svelte/store';

function createSelectedTab() {
    const { subscribe, set } = writable(undefined);

    return {
        subscribe,
        set: (selectedTab) => set(selectedTab)
    }
}

export const selectedTab = createSelectedTab();
