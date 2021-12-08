<template>
    <div class="container">
        <h1>Welcome to the Stock Market App</h1>
        <img src="../../public/stocks.jpg" alt="stonks">
        <router-link :to="{name: 'register'}" tag="button" class="register">Register</router-link>
        <router-link :to="{name: 'login'}" tag="button" class="login">Login</router-link>
    </div>
</template>

<script>
export default {
    name: "landing-page",
    showRegisterForm: false,
    showLoginForm: false
}
</script>

<style>
    .container {
        text-align: center;
    }

    h1 {
        margin-top: 100px;
        color: rgba(4, 42, 61);
    }
    
    img {
        display: block;
        margin-left: auto;
        margin-right: auto;
        height: 300px;
        width: 300px;
        border-radius: 100%;

        margin-bottom: 50px;
        margin-top: 50px;
        
    }
    .register {
        background: rgba(4, 42, 61) 0%;
        color: #dab679;
        margin-right: 20px;
        border-radius: 25px;
        width: 100px;
        height: 40px;

        cursor: pointer;
    }

    .login {
        background: rgba(4, 42, 61) 0%;
        color:  #dab679;
        border-radius: 25px;
        width: 100px;
        height: 40px;

        cursor: pointer;
    }

    button:hover {
        background: white;
        color: #34495e;
    }
</style>