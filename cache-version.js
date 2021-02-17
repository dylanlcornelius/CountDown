const fs = require('fs');

const targetPath = './public/service-worker.js';
const serviceWorker = fs.readFileSync(targetPath, 'utf-8')
    .replace('CACHE_NAME', process.env.GITHUB_SHA);

fs.writeFileSync(targetPath, serviceWorker, 'utf8', (error) => {
    if (error) {
        return console.log(error);
    }
});
