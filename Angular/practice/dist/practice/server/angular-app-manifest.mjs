
export default {
  bootstrap: () => import('./main.server.mjs').then(m => m.default),
  inlineCriticalCss: true,
  baseHref: '/',
  locale: undefined,
  routes: [
  {
    "renderMode": 2,
    "route": "/election"
  },
  {
    "renderMode": 2,
    "route": "/voter"
  },
  {
    "renderMode": 2,
    "route": "/candidate"
  },
  {
    "renderMode": 2,
    "route": "/candidate-election"
  },
  {
    "renderMode": 2,
    "route": "/voter-election-candidate"
  },
  {
    "renderMode": 2,
    "route": "/stats"
  },
  {
    "renderMode": 2,
    "route": "/home"
  },
  {
    "renderMode": 2,
    "redirectTo": "/home",
    "route": "/"
  },
  {
    "renderMode": 2,
    "redirectTo": "/home",
    "route": "/**"
  }
],
  assets: {
    'index.csr.html': {size: 4971, hash: 'ff22fe7c3bdb7230b81724825f350293127a74f0dfd3ae5c01de5e525ecfb6cb', text: () => import('./assets-chunks/index_csr_html.mjs').then(m => m.default)},
    'index.server.html': {size: 1070, hash: '228254ca4bcfb5199fe7653ce2088be5b74562abd0f7a079cc35c304ad13af61', text: () => import('./assets-chunks/index_server_html.mjs').then(m => m.default)},
    'voter/index.html': {size: 13847, hash: '3c09953e987304d02c1b44dec6d60f9c54a9debf24fab37698ed0abc40fef75b', text: () => import('./assets-chunks/voter_index_html.mjs').then(m => m.default)},
    'election/index.html': {size: 13747, hash: '6d0543284b55dbd9260ecc30bfc64bda5b297345ce584523831934dbc27e6c45', text: () => import('./assets-chunks/election_index_html.mjs').then(m => m.default)},
    'candidate-election/index.html': {size: 14362, hash: '0c2f83bf040d40753757ca88bc3d5d1e7e508f1272ee2192a27e239025d6a416', text: () => import('./assets-chunks/candidate-election_index_html.mjs').then(m => m.default)},
    'candidate/index.html': {size: 13747, hash: '6e3d777f27fbb5a48f7d0a614a9f388b64eb150552e4e30efe1b39bcf344d6d3', text: () => import('./assets-chunks/candidate_index_html.mjs').then(m => m.default)},
    'stats/index.html': {size: 14944, hash: 'c091612fc7acefb99301941eb346c647aa3c146603396da5ee11da90d8fff383', text: () => import('./assets-chunks/stats_index_html.mjs').then(m => m.default)},
    'voter-election-candidate/index.html': {size: 14411, hash: '72ad9c405f0d75adc560b754e6949baadc5fbb4072fcf43a69ea9f4be0559e2b', text: () => import('./assets-chunks/voter-election-candidate_index_html.mjs').then(m => m.default)},
    'home/index.html': {size: 17601, hash: '995c5d82e48345ee2374241fd73f88dcd3dba01b5cea9bed783297fc79257226', text: () => import('./assets-chunks/home_index_html.mjs').then(m => m.default)},
    'styles-VAMJ6QOM.css': {size: 265145, hash: 'U7loKWkF0r4', text: () => import('./assets-chunks/styles-VAMJ6QOM_css.mjs').then(m => m.default)}
  },
};
