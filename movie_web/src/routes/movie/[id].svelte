<script context="module">
	let trailer;
	export async function load({ fetch, params }) {
		const res2 = await fetch(
			`https://api.themoviedb.org/3/movie/${params.id}/videos?api_key=${
				import.meta.env.VITE_API
			}&language=en-US`
		);
		const videos = await res2.json();
		if (res2.ok) {
			trailer = videos.results.find((element) => element.type === 'Trailer');
		}

		const res = await fetch(
			`https://api.themoviedb.org/3/movie/${params.id}?api_key=${
				import.meta.env.VITE_API
			}&language=en-US`
		);
		const movieDetail = await res.json();
		if (res.ok) {
			return {
				props: {
					movieDetail
				}
			};
		}
	}
</script>

<script>
	import { fly } from 'svelte/transition';
	export let movieDetail;
	console.log(movieDetail);
	import Youtube from 'svelte-youtube-embed';
</script>

<div
	class="movie-details"
	in:fly={{ y: 50, duration: 500, delay: 500 }}
	out:fly={{ duration: 400 }}
>
	<div class="img-container">
		<img
			src={'https://image.tmdb.org/t/p/original' + movieDetail.backdrop_path}
			alt={movieDetail.title}
		/>
	</div>
	<div class="txt-container">
		<h1>{movieDetail.title}</h1>
		<p class="overview">{movieDetail.overview}</p>
		<p>
			<span>Release Date:</span>
			{movieDetail.release_date} <br />
			<span>Budget:</span> ${movieDetail.budget} <br />
			<span>Rating:</span>
			{movieDetail.vote_average} <br />
			<span>Runtime:</span>
			{movieDetail.runtime} mins
		</p>
	</div>
	<h2>Official Trailer</h2>
	<Youtube id={trailer.key} />
</div>

<style>
	h2 {
		padding: 1rem 0rem 1rem;
	}
	h1 {
		padding: 1rem 0rem 2rem;
	}
	p {
		padding: 1rem 0rem;
	}
	img {
		width: 100%;
		border-radius: 1rem;
	}
	.movie-details {
		margin: 2rem 20%;
	}
	span {
		font-weight: bold;
	}
</style>
