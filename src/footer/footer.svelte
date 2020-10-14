<script>
    import TabBar from '@smui/tab-bar';
    import Tab, { Label } from '@smui/tab';
    import { selectedTab } from '../shared/selected-tab.store.js';

    const types = {
        'Count Down': 'date',
        'Count Up': 'number'
    };

    // use search param if set
    let active = 'URLSearchParams' in window && new URLSearchParams(window.location.search).get('tab')
        ? Object.keys(types)[new URLSearchParams(window.location.search).get('tab')]
        : Object.keys(types)[0];

    $: {
        selectedTab.set(types[active]);

        // set search param
        if ('URLSearchParams' in window) {
            const params = new URLSearchParams(window.location.search);
            if (!params.get('tab') || params.get('tab') && Object.keys(types)[params.get('tab')] != active) {
                params.set('tab', Object.keys(types).indexOf(active));
                history.replaceState(null, '', window.location.pathname + '?' + params.toString());
            }
        }
    };
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
