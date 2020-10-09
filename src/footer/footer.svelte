<script>
    import TabBar from '@smui/tab-bar';
    import Tab, {Label } from '@smui/tab';
    import { selectedTab } from '../shared/selected-tab.store.js';

    const types = [
        'Count Down',
        'Count It'
    ];

    let active = types[0];

    $: () => {
        console.log(active);
        selectedTab.set(active);
    }
</script>

<TabBar tabs={types} let:tab bind:active>
    <Tab {tab}>
        <Label>{tab}</Label>
    </Tab>
</TabBar>

<style>

</style>
