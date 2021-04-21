<script>
    import { createEventDispatcher } from 'svelte';
    import Button, { Icon } from '@smui/button';
    import Count from './count.svelte';
    import TitleModal from '../count-modals/title-modal.svelte';
    import NumberModal from '../count-modals/number-modal.svelte';
    import CountService from '../services/count.service.js';
    import HistoryModal from '../count-modals/history-modal.svelte';

    export let count;

    const dispatch = createEventDispatcher();

    function increment() {
        count.value++;
        count = CountService.trackHistory(count);
        dispatch('submit', {count});
    }

    function decrement() {
        count.value--;
        count = CountService.trackHistory(count);
        dispatch('submit', {count});
    }
</script>

<Count {count} on:reorder>
    <NumberModal {count} on:submit><h4>{count.value}</h4></NumberModal>
    <TitleModal {count} on:submit><h3>{count.title}</h3></TitleModal>

    <div class="actions" slot="actions">
        <Button on:click={decrement} variant="unelevated"><Icon class="material-icons">remove</Icon></Button>
        <Button on:click={increment} variant="unelevated"><Icon class="material-icons">add</Icon></Button>

        <span>
          <HistoryModal {count}/>
        </span>
    </div>
</Count>

<style>
    span {
        margin-left: auto;
    }
</style>
