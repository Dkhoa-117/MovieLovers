<script context="module">
	export async function load({ fetch, params }) {
		const res = await fetch(
			`https://api.themoviedb.org/3/trending/movie/week?api_key=${
				import.meta.env.VITE_API
			}&language=en-US&page=1&include_adult=false`
		);
		const data = await res.json();
		if (res.ok) {
			return {
				props: {
					trending: data.results
				}
			};
		}
	}
</script>

<script>
	import Moviecard from '../../components/Moviecard.svelte';
	import { fly } from 'svelte/transition';
	export let trending;
</script>

<h2>Hot Movies This Week</h2>

<div class="trending" in:fly={{ y: 50, duration: 500, delay: 500 }} out:fly={{ duration: 500 }}>
	{#each trending as movie}
		<Moviecard {movie} />
	{/each}
</div>

<style>
	h2 {
		padding: 0 1rem;
	}
	.trending {
		display: grid;
		grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
		grid-column-gap: 1rem;
		grid-row-gap: 2rem;
		height: 20vh;
	}
</style>
