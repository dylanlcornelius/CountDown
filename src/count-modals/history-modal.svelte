<script>
    import Button, { Icon } from '@smui/button';
    import Modal from './modal.svelte';

    export let count;
</script>

<Modal>
    <span slot="name"><Button variant="unelevated"><Icon class="material-icons">timeline</Icon></Button></span>

    <h4>{count.title} History</h4>
    <div>
        {#if !count.history}
            <span>No history</span>
        {/if}
        {#each count.history || [] as history}
            <span>{history.date} {history.value}</span>
        {/each}
    </div>
</Modal>

<style>
    h4, span {
        color: black;
    }
    div {
        display: flex;
        flex-direction: column;
        max-height: 200px;
        overflow-y: auto;
        width: fit-content;
        margin: 0 auto;
        padding: 10px;
    }
    span {
        text-align: start;
    }
</style>
