<template>
  <div class="nav-bar">
  <!-- This section gets pushed to the top-->
  <div class="section">
    <router-link v-bind:to="{ name: 'home' }" class="home"><img src="../../public/stocks.jpg" alt="Stock Market" class="image"></router-link>
    <div class="user">{{this.$store.state.user.username}}</div>
    <router-link :to="{ name: 'create-game' }" class="item" tag="div">Create Game</router-link>
    <router-link :to="{ name: 'view-games' }" class="item" tag="div">My Games</router-link>
    <router-link :to="{ name: 'research' }" class="item" tag="div">Research</router-link>
  </div>
  <!-- This section gets pushed to the bottom-->
  <div class="section">
    <div class="item">About</div>
    <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''" tag="div" class="item">Logout</router-link>
  </div>
</div>
</template>

<script>
export default {

}
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Inter&family=Lexend+Deca&display=swap');

.nav-bar {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    flex-wrap: none;
    min-height: 100vh;
    width: 30vw;
    max-width: 300px;
    border-right: 1px solid #D7DBDD;
    font-family: 'Inter', sans-serif;
    background: rgba(4, 42, 61) 0%;
    position: absolute;
    z-index: 3;
}

.image {
  width: 25vw;
  height: 25vw;
  max-width: 250px;
  max-height: 250px;
  display: flex;
}

.user {
  color: #E2B842;
  font-size: 25px;
  text-align: center;
  margin-bottom: 50px;

}

.item:hover {
    background: white;
    color: #34495e;
}

.item {
    padding: 10px 20px;
    text-decoration: none;
    color: white;
    font-size: 20px;
    cursor: pointer;
    text-align: center;
}

.section {
  padding: 10px;
}
</style>