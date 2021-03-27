var cacheName = 'count-down-CACHE_NAME';
var filesToCache = [
  "/",
  "/index.html",
  "/build/bundle.css",
  "/build/bundle.js",
  "/global.css",
  "/manifest.json",
  "/build/assets/icons/icon-16x16.png",
  "/build/assets/icons/icon-32x32.png",
  "/build/assets/icons/icon-72x72.png",
  "/build/assets/icons/icon-96x96.png",
  "/build/assets/icons/icon-128x128.png",
  "/build/assets/icons/icon-144x144.png",
  "/build/assets/icons/icon-152x152.png",
  "/build/assets/icons/icon-192x192.png",
  "/build/assets/icons/icon-384x384.png",
  "/build/assets/icons/icon-512x512.png"
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
