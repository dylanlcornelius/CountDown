<script>
    import { createEventDispatcher } from 'svelte';
    import Button, { Label } from '@smui/button';
    import Count from '../count-list/count.svelte';
    import TitleModal from './title-modal.svelte';
    import CountService from '../shared/count.service.js';    

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

<Count id={count.id}>
    <h3>{count.title}</h3>
    <h4>{count.value}</h4>

    <div slot="actions">
        <TitleModal {count} on:submit/>
        <Button on:click={decrement} variant="unelevated"><Label>-</Label></Button>
        <Button on:click={increment} variant="unelevated"><Label>+</Label></Button>
    </div>
</Count>

<style>
    div {
        display: block;
    }
</style>
