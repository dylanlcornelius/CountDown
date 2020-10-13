<script>
    import { onMount } from 'svelte';
    import Button, { Label } from '@smui/button';
    import { fade } from 'svelte/transition';
    import { firebase } from '@firebase/app';
    import '@firebase/auth';
    import { token } from './token.store.js';
    import { user } from '../shared/user.service.js';
    import { loading } from '../shared/loading.store.js';

    let loadingAuth = true;

    function initToken() {
        firebase.auth().onAuthStateChanged(currentUser => {
            if (!currentUser) {
                loadingAuth = false;
                return;
            }

            currentUser.getIdToken(true).then((accessToken) => {
                token.set(accessToken);
                user.next(currentUser);
                loadingAuth = false;
            }, error => {
                console.error(error);
            });
        })
    }

    function handleLogin() {
        let provider = new firebase.auth.GoogleAuthProvider();
        firebase.auth().signInWithRedirect(provider);
    }

    function handleLogout() {
        firebase.auth().signOut().then(() => {
            token.remove();
            user.next({});
            loading.set(true);
        }, error => {
            console.error(error);
        });
    }

    onMount(async () => {
        initToken();
    });
</script>

{#if loadingAuth}
    <span></span>
{:else if $token}
    <span transition:fade>
        <Button color="secondary" on:click={handleLogout} variant="unelevated"><Label>Logout</Label></Button>
    </span>
{:else}
    <span transition:fade>
        <Button on:click={handleLogin} variant="unelevated"><Label>Login</Label></Button>
    </span>
{/if}
