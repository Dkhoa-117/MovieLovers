<script>
	export let tv;
</script>

<div class="tv-card">
	<a sveltekit:prefetch sveltekit:noscroll href={'/tv/' + tv.id}>
		<img src={'https://image.tmdb.org/t/p/w500' + tv.poster_path} alt={tv.name} />
	</a>
	<div class="description">
		<h2>{tv.name}</h2>
		<p>{tv.first_air_date}</p>
	</div>
</div>

<style>
	img {
		width: 100%;
		height: 40vh;
		object-fit: cover;
		border-radius: 1rem;
		margin-bottom: 1rem;
	}
	h2 {
		font-size: 0.9rem;
	}
	.description {
		height: 5vh;
	}
	p {
		font-size: 0.7rem;
	}
	.tv-card {
		display: flex;
		flex-direction: column;
		justify-content: space-around;
		padding: 1rem;
	}
</style>
