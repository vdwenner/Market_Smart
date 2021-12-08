<template>
  <div class="nav-bar">
  <!-- This section gets pushed to the top-->
  <div class="section">
    <router-link v-bind:to="{ name: 'home' }" tag="div" class="item">Home</router-link>
    <div class="item">Create Game</div>
    <div class="item">My Games</div>
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

.item:hover {
    background: white;
    color: #34495e;
}

.item {
    padding: 10px 20px;
    color: white;
    font-size: 20px;
    cursor: pointer;
}

.section {
  padding: 10px;
}
</style>