<script>
    import Paper from '@smui/paper';
    import Button, { Label } from '@smui/button';
    import CountService from '../shared/count.service.js';

    export let id;

    function handleDelete() {
        CountService.delete(id);
    }
</script>

<div class="count">
    <Paper elevation={6}>
        <slot></slot>
        <div class="actions">
            <Button color="secondary" on:click={handleDelete} variant="unelevated"><Label>Delete</Label></Button>
            <slot name="actions"></slot>
        </div>
    </Paper>
</div>

<style>
    .count {
        width: 330px;
        margin: 5px auto;
    }
    .actions {
        margin-top: 10px;
        display: flex;
        justify-content: space-between;
    }
</style>
