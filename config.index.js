const fs = require('fs');

const targetPath = './src/environment.js';

const envConfigFile = `export const environment = {
    firebaseConfig: {
        apiKey: '${process.env.FIREBASE_TOKEN}',
        authDomain: '${process.env.FIREBASE_AUTH_DOMAIN}',
        databaseURL: '${process.env.FIREBASE_DB_URL}',
        projectId: '${process.env.FIREBASE_PROJECT_ID}',
        storageBucket: '${process.env.FIREBASE_STORAGE}'
    }
};
`;

fs.writeFile(targetPath, envConfigFile, 'utf8', (error) => {
    if (error) {
        return console.log(error);
    }
});
