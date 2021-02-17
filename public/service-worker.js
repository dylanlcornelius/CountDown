var cacheName = 'count-down-CACHE_NAME';
var filesToCache = [
  "/",
  "/index.html",
  "/build/bundle.css",
  "/build/bundle.js",
  "/global.css",
  "/manifest.json",
  "/favicon-16x16.png",
  "/favicon-32x32.png",
  "/favicon-96x96.png"
];

self.addEventListener("install", function(e) {
  e.waitUntil(
    caches.open(cacheName).then(function(cache) {
      return cache.addAll(filesToCache);
    }).then(function () {
      return self.skipWaiting();
    })
  );
});

self.addEventListener("activate", e => {
  e.waitUntil(
    caches.keys().then(function(cacheNames) {
      return Promise.all(
        cacheNames.map(function(thisCacheName) {
          if (thisCacheName !== cacheName) {
            return caches.delete(thisCacheName);
          }
        })
      );
    }).then(function () {
      return self.clients.claim();
    })
  );
});

self.addEventListener("fetch", e => {
  e.respondWith(
    caches.match(e.request).then(r => {
      return r || fetch(e.request).then(response => {
        if (e.request.method == 'POST') {
          return response;
        }
        return caches.open(cacheName).then(cache => {
          cache.put(e.request.url, response.clone());
          return response;
        });
      });
    })
  );
});
