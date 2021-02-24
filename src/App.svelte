<script>
	import Header from './header/header.svelte';
	import CountList from './count-list/count-list.svelte';
    import { selectedTab } from './stores/selected-tab.store.js';
	import Footer from './footer/footer.svelte';

	if ('serviceWorker' in navigator) {
		navigator.serviceWorker.register('/service-worker.js');
	}
</script>

<main>
	<Header/>
	<CountList type={$selectedTab}/>
</main>
<Footer/>

<style>
	main {
		text-align: center;
		margin: 0 auto 90px;
		min-height: calc(100vh - 90px);
	}
</style>
