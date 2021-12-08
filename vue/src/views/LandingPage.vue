<template>
    <div class="container">
        <h1>Welcome to the Stock Market App</h1>
        <img src="../../public/stonks.jpg" alt="stonks">
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
    
    img {
        display: block;
        margin-left: auto;
        margin-right: auto;
        width: 75%;
        border-radius: 25%;

        margin-bottom: 30px;
        
    }
    .register {
        background: #34495e;
        color: white;
        margin-right: 20px;
        border-radius: 25px;
        width: 100px;
    }

    .login {
        background: #34495e;
        color: white;
        border-radius: 25px;
        width: 100px;
    }
</style>