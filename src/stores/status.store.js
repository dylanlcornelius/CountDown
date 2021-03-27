import { writable } from 'svelte/store';

export const STATUS_TYPES = {
    ACTIVE: 'active',
    ARCHIVED: 'archived',
};

export const status = writable(STATUS_TYPES.ACTIVE);
