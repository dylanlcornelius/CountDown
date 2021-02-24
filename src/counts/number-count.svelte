<script>
    import { createEventDispatcher } from 'svelte';
    import Button, { Icon } from '@smui/button';
    import Count from './count.svelte';
    import TitleModal from '../count-modals/title-modal.svelte';
    import NumberModal from '../count-modals/number-modal.svelte';

    export let count;

    const dispatch = createEventDispatcher();

    function increment() {
        count.value++;
        dispatch('submit', {count});
    }

    function decrement() {
        count.value--;
        dispatch('submit', {count});
    }
</script>

<Count {count}>
    <NumberModal {count} on:submit><h4>{count.value}</h4></NumberModal>
    <TitleModal {count} on:submit><h3>{count.title}</h3></TitleModal>

    <div slot="actions">
        <Button on:click={decrement} variant="unelevated"><Icon class="material-icons">remove</Icon></Button>
        <Button on:click={increment} variant="unelevated"><Icon class="material-icons">add</Icon></Button>
    </div>
</Count>

<style>
    div {
        display: flex;
        gap: 10px;
    }
</style>
