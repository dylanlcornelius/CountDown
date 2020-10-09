<script>
    import { onMount, onDestroy } from 'svelte';
    import DateCount from '../count/date-count.svelte';
    import NumberCount from '../count/number-count.svelte';
    import { counts } from '../shared/count.store.js';
    
    export let type;

    $: countsByType = $counts.filter(count => count.type === type);

    onMount(() => {
        
    });

    onDestroy(() => {
        countSubscription.unsubscribe();
    });
</script>

{#each $counts as count}
    {#if type === 'date'}
        <DateCount/>
    {:else}
        <NumberCount/>
    {/if}
{/each}
<button>Add</button>

<style>

</style>
