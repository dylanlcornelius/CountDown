<script>
    import { onMount, onDestroy } from 'svelte';
    import { fade } from 'svelte/transition';
    import Button, { Label } from '@smui/button';
    import DateCount from '../count/date-count.svelte';
    import NumberCount from '../count/number-count.svelte';
    import Spinner from '../util/spinner.svelte';
    import CountService from '../shared/count.service.js';
    import { user } from '../shared/user.service.js';
    import { loading } from '../shared/loading.store.js';
    
    export let type;

    let counts = [];
    let countSubscription;

    $: countsByType = counts.filter(count => count.type === type);
    
    function handleUpdate(event) {
        CountService.upsert(event.detail.count);
    }

    onMount(() => {
        user.subscribe(user => {
            countSubscription = CountService.get(user.uid).subscribe(c => {
                counts = c;
                loading.set(false);
            });
        });
    });

    onDestroy(() => {
        countSubscription.unsubscribe();
    });
</script>

{#if $loading}
    <Spinner/>
{:else}
    <div class="list" transition:fade>
        {#if countsByType.length === 0}
            <h3>Add a count!</h3>
        {/if}
        {#each countsByType as count}
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
        max-height: calc(100vh - 100px); /* header(50px) + footer(50px) */
        overflow-y: auto;
    }
</style>
