<template>
  <div class="nav-bar">
  <!-- This section gets pushed to the top-->
  <div class="section">
    <router-link v-bind:to="{ name: 'home' }" class="home"><img src="../../public/stocks.jpg" alt="Stock Market" class="image"></router-link>
    <div class="user">Current User</div>
    <div class="item"><router-link :to="{ name: 'create-game' }" class="item">Create Game</router-link></div>
    <div class="item"><router-link :to="{ name: 'view-games' }" class="item">My Games</router-link></div>
    <div class="item">Research</div>
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
.nav-bar {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    flex-wrap: wrap;
    height: 100vh;
    width: 30vw;
    max-width: 300px;
    border-right: 1px solid #D7DBDD;
    font-family: raleway;
    background: #34495e
}

.image {
  width: 25vw;
  height: 25vw;
  max-width: 250px;
  max-height: 250px;
  border-radius: 100%;
}

.user {
  color: white;
  font-size: 15px;
  text-align: center;
  margin-bottom: 100px;
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
}

.section {
  padding: 10px;
}
</style>