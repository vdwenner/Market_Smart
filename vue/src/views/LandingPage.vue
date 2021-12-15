<template>
    <div class="lp-container">
        <h1>Welcome to Market Smart!</h1>
        <img src="../../public/LogoMakr.png" alt="stonks">
        <div class="register-container">
            <router-link :to="{name: 'register'}" tag="button" class="btn btn-lg btn-primary btn-block">Register</router-link>
        </div>
        <div class="login-container">
            <router-link :to="{name: 'login'}" tag="button" class="btn btn-lg btn-primary btn-block">Login</router-link>
        </div>
        <div class="description-container">
            <h2 class="description">Welcome to Market Smart, a virtual stock market experience from the developers that brought you TEnmo!</h2>
            <h2 class="description">Challenge your friends to see who the best investor is!</h2>
            
        </div>    
    </div>
</template>

<script>
export default {
    name: "landing-page",
}
</script>

<style>
    /* body {
        background-image: linear-gradient(to bottom, grey, white);
    } */
    .lp-container {
        display: grid;
        grid-template-areas:
        ". title title ."
        ". image image ."
        ". register login ."
        ". description description .";
        grid-template-columns: .5fr 1fr 1fr .5fr;
    }

    h1 {
        margin-left: auto;
        margin-right: auto;
        margin-top: 20px;
        color: rgba(4, 42, 61);
        grid-area: title;
    }
    
    img {
        margin-top: 50px;
        margin-bottom: 20px;
        margin-left: auto;
        margin-right: auto;
        height: 300px;
        width: 300px;
        border-radius: 100%;
        grid-area: image;     
    }

    div.register-container {
        grid-area: register;
        text-align: right;
        padding-right: 20px;
    }

    div.login-container {
        grid-area: login;
        text-align: left;
        padding-left: 20px;
    }

    .btn {
        background: rgba(4, 42, 61) 0%;
        color: #eeaf03;
        align-content: center;
        border-radius: 25px;
        width: 100px;
        height: 40px;
        cursor: pointer;
    }

    .btn {
        background: rgba(4, 42, 61) 0%;
        color:  #eeaf03;
        border-radius: 25px;
        width: 100px;
        height: 40px;
        cursor: pointer;
    }

    button:hover {
        background: white;
        color: rgba(4, 42, 61) 0%;
    }

    .description-container {
        color: rgba(4, 42, 61) 0%;
        grid-area: description;
        margin-top: 50px;
        text-align: center;
        background: #efefef;
        width: 75vw;
        padding: 20px;
        border-radius: 30px;
        box-shadow: 10px 5px 20px  grey;
    }

    h2.description {
        margin: 20px;
        color: black;
    }
</style>