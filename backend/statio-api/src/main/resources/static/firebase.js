import { initializeApp } from "https://www.gstatic.com/firebasejs/10.12.2/firebase-app.js";

import {
    getAuth,
    GoogleAuthProvider,
    signInWithPopup,
    signOut
} from "https://www.gstatic.com/firebasejs/10.12.2/firebase-auth.js";

const firebaseConfig = {
    apiKey: "AIzaSyCA9LnzzIxKFg2AyrffXlaWpy1dtbnU7Lc",
    authDomain: "statio-6945a.firebaseapp.com",
    projectId: "statio-6945a",
    storageBucket: "statio-6945a.firebasestorage.app",
    messagingSenderId: "807131263718",
    appId: "1:807131263718:web:7a0a91ab27169a4d594127"
};

const app = initializeApp(firebaseConfig);

const auth = getAuth(app);

const provider = new GoogleAuthProvider();

export {
    auth,
    provider,
    signInWithPopup,
    signOut
};