<script>
    import { createEventDispatcher } from 'svelte';
    import Textfield from '@smui/textfield';
    import Button, { Label } from '@smui/button';
    import Modal from '../util/modal.svelte';

    export let count;

    const dispatch = createEventDispatcher();
</script>

<Modal>
    <span slot="name"><slot></slot></span>

    <h3>Choose Start Date</h3>
    <Textfield bind:value={count.startDate} Label="Start Date" type="date"/>
    <br/><br/>

    <h3>Choose End Date</h3>
    <Textfield bind:value={count.endDate} Label="End Date" type="date"/>

    <div slot="actions">
        <Button on:click={dispatch('submit', {count})} variant="unelevated"><Label>Submit</Label></Button>
    </div>
</Modal>
