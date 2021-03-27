<script>
    import Button, { Icon } from '@smui/button';
    import moment from 'moment';
    import GoogleAuth from './google-auth.svelte';
    import CountService from '../services/count.service.js';
    import { selectedTab } from '../stores/selected-tab.store.js';
    import { user } from '../services/user.service.js';
    import { loading, LOADING_TYPES } from '../stores/loading.store.js';
    import { STATUS_TYPES } from '../stores/status.store.js';

    function handleAdd() {
        user.subscribe(user => {
            CountService.upsert({
                title: 'Title Goes Here',
                type: $selectedTab,
                startDate: moment().format(),
                endDate: moment().format(),
                value: 0,
                uids: [user.uid],
                status: STATUS_TYPES.ACTIVE,
                order: -1
            });
        });
    }
</script>

<div class="header">
    <h1>Count Down</h1>
    <div>
        {#if $loading === LOADING_TYPES.LOADED} 
            <Button on:click={handleAdd} variant="unelevated"><Icon class="material-icons">create</Icon></Button>
        {/if}
        <GoogleAuth/>
    </div>
</div>

<style>
    .header {
        display: flex;
        justify-content: space-between;
        padding: 5px 15px 2px;
    }
</style>
