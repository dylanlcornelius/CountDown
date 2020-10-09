<script>
    import CountService from '../shared/count.service.js';

    export let id;

    function handleDelete() {
        CountService.delete(id);
    }
</script>

<div>
    <slot></slot>
    <div>
        <button onclick="handleDelete">Delete</button>
        <slot name="actions"></slot>
    </div>
</div>

<style>

</style>
