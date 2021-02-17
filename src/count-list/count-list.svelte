<script>
    import { onMount, onDestroy } from 'svelte';
    import { fade } from 'svelte/transition';
    import DateCount from '../count/date-count.svelte';
    import NumberCount from '../count/number-count.svelte';
    import Spinner from '../util/spinner.svelte';
    import CountService from '../shared/count.service.js';
    import { user } from '../shared/user.service.js';
    import { loading, LOADING_TYPES } from '../shared/loading.store.js';
    import { status, STATUS_TYPES } from '../shared/status.store.js';
    
    export let type;

    let counts = [];
    let countSubscription;

    $: filteredCounts = counts.filter(count => count.type === type && count.status === $status);
    
    function handleUpdate(event) {
        CountService.upsert(event.detail.count);
    }

    onMount(() => {
        user.subscribe(user => {
            countSubscription = CountService.get(user.uid).subscribe(c => {
                // use alphabetical until adding display order
                counts = c.sort((a, b) => a.title.toLowerCase().localeCompare(b.title.toLowerCase()));
                loading.set(LOADING_TYPES.LOADED);
            });
        });
    });

    onDestroy(() => {
        countSubscription.unsubscribe();
    });
</script>


{#if $loading === LOADING_TYPES.INIT}
    <Spinner/>
{:else if $loading === LOADING_TYPES.LOGGED_OUT}
    <h3>Please log in</h3>
{:else}
    <div class="list" transition:fade>
        {#if filteredCounts.length === 0}
            {#if $status === STATUS_TYPES.ACTIVE}
                <h3>Add a count!</h3>
            {:else if $status === STATUS_TYPES.ARCHIVED}
                <h3>No counts archived</h3>
            {/if}
        {/if}
        {#each filteredCounts as count}
            {#if type === 'date'}
                <DateCount {count} on:submit={handleUpdate}/>
            {:else}
                <NumberCount {count} on:submit={handleUpdate}/>
            {/if}
        {/each}
    </div>
{/if}

<style>
    .list {
        display: flex;
        flex-direction: column;
        max-height: calc(100vh - 140px); /* header(50px) + footer(90px) */
        overflow-y: auto;
    }
</style>
