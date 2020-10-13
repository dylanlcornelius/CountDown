<script>
	import Header from './header/header.svelte';
	import CountList from './count-list/count-list.svelte';
    import { selectedTab } from './shared/selected-tab.store.js';
	import Footer from './footer/footer.svelte';
</script>

<main>
	<Header/>
	<CountList type={$selectedTab}/>
</main>
<Footer/>

<style>
	main {
		text-align: center;
		margin: 0 auto 50px;
		min-height: calc(100vh - 50px);
	}
</style>
