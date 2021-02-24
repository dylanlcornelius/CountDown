<script>
    import moment from 'moment';
    import LinearProgress from '@smui/linear-progress';
    import Count from './count.svelte';
    import TitleModal from '../count-modals/title-modal.svelte';
    import DateModal from '../count-modals/date-modal.svelte';

    export let count;

    const today = moment().format('YYYY-MM-DD');
    let progressBar;
    $: value = moment(today).diff(moment(count.endDate), 'days');
    // today - start / end - start
    $: progress = moment(today).diff(moment(count.startDate), 'days') / moment(count.endDate).diff(moment(count.startDate), 'days');
    // 0..280 | red -> purple
    $: progressBar && progressBar.style.setProperty('--mdc-theme-primary', `hsl(${Math.min(progress * 120, 280).toString(10)}, 60%, 40%)`);
</script>

<Count {count}>
    <TitleModal {count} on:submit><h3>{count.title}</h3></TitleModal>
    <DateModal {count} on:submit><h4>{Math.abs(value)}</h4></DateModal>
    {#if value <= 0}
        <span>Days Left</span>
    {:else}
        <span>Days Since</span>
    {/if}
    <div bind:this={progressBar}>
        <LinearProgress {progress}/>
    </div>
</Count>

<style>
    span {
        margin-bottom: 5px;
    }
</style>
