<script>
    import { createEventDispatcher } from 'svelte';
    import Textfield from '@smui/textfield';
    import Button, { Label } from '@smui/button';
    import Modal from '../util/modal.svelte';
    import CountService from '../shared/count.service.js';

    export let count;

    const dispatch = createEventDispatcher();
</script>

<Modal>
    <span slot="name">Choose Value</span>

    <h3>Choose Value</h3>
    <Textfield bind:value={count.value} Label="Value"/>

    <div slot="actions">
        <Button on:click={dispatch('submit', {count})} variant="unelevated"><Label>Submit</Label></Button>
    </div>
</Modal>
