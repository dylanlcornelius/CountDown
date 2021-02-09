<script>
    import TabBar from '@smui/tab-bar';
    import Tab, { Label } from '@smui/tab';
    import { selectedTab } from '../shared/selected-tab.store.js';

    const types = {
        'Count Down': 'date',
        'Count Up': 'number'
    };

    let active = Object.keys(types)[0];

    $: selectedTab.set(types[active]);
</script>

<div>
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
        height: 50px;
        margin-top: -50px;
    }
</style>
