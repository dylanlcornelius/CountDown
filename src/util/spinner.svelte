<div class="container">
    <div class="spinner"></div>
</div>

<style>
    .container {
        position: fixed;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
    }
    .spinner {
        border: 0px solid #9E2020;
        border-top: 3px solid #9E2020;
        border-radius: 50%;
        width: 75px;
        height: 75px;
        animation: spin 0.7s linear infinite;
    }
    @keyframes spin {
        0% { transform: rotate(0deg); }
        25% { transform: rotate(45deg); }
        75% { transform: rotate(325deg); }
        100% { transform: rotate(360deg); }
    }
</style>