<script>
    import moment from 'moment';
    import LinearProgress from '@smui/linear-progress';
    import Count from '../count-list/count.svelte';
    import TitleModal from './title-modal.svelte';
    import DateModal from './date-modal.svelte';

    export let count;

    const today = moment().format('YYYY-MM-DD');
    $: value = moment(today).diff(moment(count.endDate), 'days');
    $: progress = moment(today).diff(moment(count.startDate), 'days') / moment(count.endDate).diff(moment(count.startDate), 'days');
</script>

<Count {count}>
    <TitleModal {count} on:submit><h3>{count.title}</h3></TitleModal>
    <DateModal {count} on:submit><h4>{Math.abs(value)}</h4></DateModal>
    {#if value <= 0}
        <span>Days Left</span>
    {:else}
        <span>Days Since</span>
    {/if}
    <LinearProgress {progress}/>
</Count>

<style>
    span {
        margin-bottom: 5px;
    }
</style>
