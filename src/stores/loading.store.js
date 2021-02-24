import { writable } from 'svelte/store';

export const LOADING_TYPES = {
    INIT: 0,
    LOGGED_OUT: 1,
    LOGGED_IN: 2,
    LOADED: 3,
};

export const loading = writable(LOADING_TYPES.INIT);
