<script>
    import { onMount, onDestroy } from 'svelte';
    import { fade } from 'svelte/transition';
    import { flip } from 'svelte/animate';
    import DateCount from '../counts/date-count.svelte';
    import NumberCount from '../counts/number-count.svelte';
    import Spinner from './spinner.svelte';
    import CountService from '../services/count.service.js';
    import { user } from '../services/user.service.js';
    import { loading, LOADING_TYPES } from '../stores/loading.store.js';
    import { status, STATUS_TYPES } from '../stores/status.store.js';
    
    export let type;

    let counts = [];
    let countSubscription;

    $: filteredCounts = counts.filter(count => count.type === type && count.status === $status);

    const handleUpdate = event => CountService.upsert(event.detail.count);
    const handleReorder = ({detail: {isUp, countId}}) => CountService.reorder(isUp, countId, filteredCounts);

    onMount(() => {
        user.subscribe(user => {
            countSubscription = CountService.get(user.uid).subscribe(c => {
                counts = c.sort(CountService.sort);
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
        {#each filteredCounts as count (count.id)}
            <span animate:flip="{{duration: 500}}">
                {#if type === 'date'}
                    <DateCount {count} on:submit={handleUpdate} on:reorder={handleReorder}/>
                {:else}
                    <NumberCount {count} on:submit={handleUpdate} on:reorder={handleReorder}/>
                {/if}
            </span>
        {/each}
    </div>
{/if}

<style>
    .list {
        display: flex;
        flex-direction: column;
        height: calc(100vh - 140px); /* header(50px) + footer(90px) */
        overflow-y: auto;
    }
</style>
