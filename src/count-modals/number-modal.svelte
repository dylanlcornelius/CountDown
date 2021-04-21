<script>
    import { createEventDispatcher } from 'svelte';
    import Textfield from '@smui/textfield';
    import Button, { Label } from '@smui/button';
    import Modal from './modal.svelte';
    import CountService from '../services/count.service.js';

    export let count;

    const dispatch = createEventDispatcher();

    function handleUpdate() {
        count = CountService.trackHistory(count);
        dispatch('submit', {count})
    }
</script>

<Modal>
    <span slot="name"><slot></slot></span>

    <h3>Choose Value</h3>
    <Textfield bind:value={count.value} Label="Value"/>

    <div slot="actions">
        <Button on:click={handleUpdate} variant="unelevated"><Label>Submit</Label></Button>
    </div>
</Modal>
