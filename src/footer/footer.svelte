<script>
    import FormField from '@smui/form-field';
    import Checkbox from '@smui/checkbox';
    import TabBar from '@smui/tab-bar';
    import Tab, { Label } from '@smui/tab';
    import { selectedTab } from '../stores/selected-tab.store.js';
    import { status, STATUS_TYPES } from '../stores/status.store.js';
    import { sorting } from '../stores/sorting.store.js';

    const types = {
        'Count Down': 'date',
        'Count Up': 'number'
    };

    let isArchived = false;
    let isSorting = false;
    let active = Object.keys(types)[0];

    $: status.set(isArchived ? STATUS_TYPES.ARCHIVED : STATUS_TYPES.ACTIVE);
    $: sorting.set(isSorting);
    $: selectedTab.set(types[active]);
</script>

<div>
    <FormField>
        <Checkbox bind:checked={isArchived}/>
        <span slot="label">Archived</span>
    </FormField>
    <FormField>
        <Checkbox bind:checked={isSorting}/>
        <span slot="label">Reorder</span>
    </FormField>
    <TabBar tabs={Object.keys(types)} let:tab bind:active>
        <Tab {tab}>
            <Label>{tab}</Label>
        </Tab>
    </TabBar>
</div>

<style>
    div {
        clear: both;
        position: relative;
        height: 90px;
        margin-top: -90px;
    }
</style>
