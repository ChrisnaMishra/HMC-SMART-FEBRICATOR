/* Hulas Smart Fabricator — service worker (network first, so updates always show) */
const CACHE = 'hulas-v13';
const ASSETS = ['./', './index.html', './manifest.json', './icon-192.svg', './icon-512.svg'];
self.addEventListener('install', e => { e.waitUntil(caches.open(CACHE).then(c => c.addAll(ASSETS))); self.skipWaiting(); });
self.addEventListener('activate', e => {
  e.waitUntil(caches.keys().then(ks => Promise.all(ks.filter(k => k !== CACHE).map(k => caches.delete(k)))));
  self.clients.claim();
});
self.addEventListener('fetch', e => {
  if (e.request.method !== 'GET') return;
  const url = new URL(e.request.url);
  if (e.request.mode === 'navigate' || url.pathname.endsWith('/index.html')) {
    e.respondWith(fetch(e.request, {cache:'no-store'}).catch(() => caches.match('./index.html')));
    return;
  }
  e.respondWith(fetch(e.request).then(r => {
    const c = r.clone();
    caches.open(CACHE).then(x => x.put(e.request, c));
    return r;
  }).catch(() => caches.match(e.request)));
});
