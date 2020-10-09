<script>
    import Count from '../count-list/count.svelte';
    import LinearProgress from '@smui/linear-progress';
    import moment from 'moment';

    export let count;

    // |start - today|
    $: value = Math.abs(moment(count.startDate).diff(moment()));
    // start - today / today - end
    $: progress = moment(count.startDate).diff(moment()) / moment().diff(count.endDate);
</script>

<Count>
    <h4>{count.name}</h4>
    <h5>{value}</h5>
    {#if value > 0} 
        Days Left
    {:else}
        Days Since
    {/if}

    <LinearProgress {progress}/>

    <div slot="actions">
        <button>Choose Title</button>
        <button>Choose Date</button>
    </div>
</Count>

<style>

</style>
