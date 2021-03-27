<script>
    import { createEventDispatcher } from 'svelte';
    import Button, { Icon } from '@smui/button';
    import Paper from '@smui/paper';
    import IconButton from '@smui/icon-button';
    import CountService from '../services/count.service.js';
    import { sorting } from '../stores/sorting.store.js';

    export let count;

    const dispatch = createEventDispatcher();

    const handleDelete = () => CountService.delete(count);
    const handleReorder = (isUp) => dispatch('reorder', {isUp, countId: count.id});
</script>

<div class="count">
    <Paper elevation={6}>
        <div class="delete">
            <IconButton class="material-icons" on:click={handleDelete}>close</IconButton>
        </div>
        <slot></slot>

        {#if $sorting}
            <div class="actions">
                <Button color="primary" on:click={() => handleReorder(true)} variant="unelevated">
                    <Icon class="material-icons">arrow_upward</Icon>
                </Button>
                <Button color="primary" on:click={() => handleReorder(false)} variant="unelevated">
                    <Icon class="material-icons">arrow_downward</Icon>
                </Button>
            </div>
        {:else}
            <slot name="actions"></slot>
        {/if}
    </Paper>
</div>

<style>
    .count {
        position: relative;
        width: 330px;
        margin: 5px auto;
    }
    .delete {
        position: absolute;
        top: 2px;
        right: 2px;
    }
    :global(.actions) {
        display: flex;
        gap: 10px;
        margin-top: 5px;
    }
</style>
