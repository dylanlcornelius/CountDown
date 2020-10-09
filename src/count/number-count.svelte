<script>
    import Count from '../count-list/count.svelte';

    export let count;
</script>

<Count>
    <h4>{count.name}</h4>
    <h5>{count.value}</h5>

    <div slot="actions">
        <button>Choose Title</button>
        <button>+</button>
        <button>-</button>
    </div>
</Count>

<style>

</style>
