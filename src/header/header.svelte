<script>
    import Button, { Label } from '@smui/button';
    import moment from 'moment';
    import GoogleAuth from './google-auth.svelte';
    import CountService from '../shared/count.service.js';
    import { selectedTab } from '../shared/selected-tab.store.js';
    import { user } from '../shared/user.service.js';
    import { loading} from '../shared/loading.store.js';

    function handleAdd() {
        user.subscribe(user => {
            CountService.upsert({title: 'Title Goes Here', type: $selectedTab, startDate: moment().format(), endDate: moment().format(), value: 0, uids: [user.uid]});
        });
    }
</script>

<div class="header">
    <h1>Count Down</h1>
    <div>
        {#if !$loading} 
            <Button on:click={handleAdd} variant="unelevated"><Label>Add</Label></Button>
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
