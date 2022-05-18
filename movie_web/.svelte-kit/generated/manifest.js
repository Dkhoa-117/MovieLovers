const c = [
	() => import("../../src/routes/__layout.svelte"),
	() => import("../runtime/components/error.svelte"),
	() => import("../../src/routes/index.svelte"),
	() => import("../../src/routes/trending/movie.svelte"),
	() => import("../../src/routes/trending/tv.svelte"),
	() => import("../../src/routes/search/[id].svelte"),
	() => import("../../src/routes/movie/[id].svelte"),
	() => import("../../src/routes/tv/[id].svelte")
];

const d = decodeURIComponent;

export const routes = [
	// src/routes/index.svelte
	[/^\/$/, [c[0], c[2]], [c[1]]],

	// src/routes/trending/movie.svelte
	[/^\/trending\/movie\/?$/, [c[0], c[3]], [c[1]]],

	// src/routes/trending/tv.svelte
	[/^\/trending\/tv\/?$/, [c[0], c[4]], [c[1]]],

	// src/routes/search/[id].svelte
	[/^\/search\/([^/]+?)\/?$/, [c[0], c[5]], [c[1]], (m) => ({ id: d(m[1])})],

	// src/routes/movie/[id].svelte
	[/^\/movie\/([^/]+?)\/?$/, [c[0], c[6]], [c[1]], (m) => ({ id: d(m[1])})],

	// src/routes/tv/[id].svelte
	[/^\/tv\/([^/]+?)\/?$/, [c[0], c[7]], [c[1]], (m) => ({ id: d(m[1])})]
];

// we import the root layout/error components eagerly, so that
// connectivity errors after initialisation don't nuke the app
export const fallback = [c[0](), c[1]()];