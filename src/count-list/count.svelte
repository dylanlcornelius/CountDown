<script>
    import Paper from '@smui/paper';
    import IconButton from '@smui/icon-button';
    import CountService from '../shared/count.service.js';

    export let count;

    function handleDelete() {
        CountService.delete(count);
    }
</script>

<div class="count">
    <Paper elevation={6}>
        <div class="delete">
            <IconButton class="material-icons" on:click={handleDelete}>close</IconButton>
        </div>
        <slot></slot>
        <slot name="actions"></slot>
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
</style>
