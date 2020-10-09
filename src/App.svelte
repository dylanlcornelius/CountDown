<script>
	import Header from './header/header.svelte';
	import CountList from './count-list/count-list.svelte';
    import { selectedTab } from './shared/selected-tab.store.js';
	import Footer from './footer/footer.svelte';

	const counts = [
		{ name: 'Count Down' },
		{ name: 'Count It' }
	];
</script>

<main>
	<Header/>
	<CountList type={$selectedTab}/>
	<Footer/>
</main>

<style>
	main {
		text-align: center;
		padding: 1em;
		max-width: 240px;
		margin: 0 auto;
	}
	@media (min-width: 640px) {
		main {
			max-width: none;
		}
	}
</style>
