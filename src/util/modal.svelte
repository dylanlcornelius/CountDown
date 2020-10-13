<script>
    import Button, { Label } from '@smui/button';

    let isOpen = false;

    function open() {
        isOpen = true;
    }

    function close() {
        isOpen = false;
    }
</script>

<Button on:click={open} variant="unelevated"><Label>
    <slot name="name"></slot>
</Label></Button>
{#if isOpen} 
    <div class="overlay" on:click={close}></div>
    <div class="modal">
        <slot></slot>
        <div class="actions" on:click={close}>
            <Button color="secondary" variant="unelevated"><Label>Close</Label></Button>
            <slot name="actions"></slot>
        </div>
    </div>
{/if}

<style>
    .overlay {
        z-index: 1;
        position: fixed;
        top: 0;
        bottom: 0;
        left: 0;
        right: 0;
        background: #0000001a;
        transform: translateZ(0);
    }
    .modal {
        z-index: 2;
        position: fixed;
        width: 300px;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        background-color: #F4F0EB;
        box-shadow: 10px 8px 8px 0px rgba(0,0,0,0.3);
        padding: 15px;
        border-radius: 4px;
        box-sizing: border-box;
    }
    .actions {
        display: flex;
        justify-content: flex-end;
        gap: 10px;
        margin-top: 10px;
    }
</style>
