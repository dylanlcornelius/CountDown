<script>
    import { createEventDispatcher } from 'svelte';
    import Textfield from '@smui/textfield';
    import Button, { Label } from '@smui/button';
    import Modal from './modal.svelte';

    export let count;

    const dispatch = createEventDispatcher();
</script>

<Modal>
    <span slot="name"><slot></slot></span>

    <h3>Choose Title</h3>
    <Textfield bind:value={count.title} Label="Title"/>

    <div slot="actions">
        <Button on:click={dispatch('submit', {count})} variant="unelevated"><Label>Submit</Label></Button>
    </div>
</Modal>

<style>
    span {
        display: block;
        width: 75%;
        margin: 0 auto;
    }
</style>
