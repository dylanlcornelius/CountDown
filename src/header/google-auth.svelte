<script>
    import { onMount } from 'svelte';
    import Button, { Icon } from '@smui/button';
    import { fade } from 'svelte/transition';
    import { firebase } from '@firebase/app';
    import '@firebase/auth';
    import { token } from './token.store.js';
    import { user } from '../shared/user.service.js';
    import { loading, LOADING_TYPES } from '../shared/loading.store.js';

    function initToken() {
        firebase.auth().onAuthStateChanged(currentUser => {
            if (!currentUser) {
                loading.set(LOADING_TYPES.LOGGED_OUT);
                return;
            }

            currentUser.getIdToken(true).then((accessToken) => {
                token.set(accessToken);
                user.next(currentUser);
                loading.set(LOADING_TYPES.LOGGED_IN);
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
            loading.set(LOADING_TYPES.LOGGED_OUT);
        }, error => {
            console.error(error);
        });
    }

    onMount(async () => {
        initToken();
    });
</script>

{#if $loading === LOADING_TYPES.INIT}
    <span></span>
{:else if $token}
    <Button color="secondary" on:click={handleLogout} variant="unelevated"><Icon class="material-icons">logout</Icon></Button>
{:else}
    <Button on:click={handleLogin} variant="unelevated"><Icon class="material-icons">login</Icon></Button>
{/if}
