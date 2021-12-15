<template>
  <div class="home">
    <nav-bar/>
    <h1>Welcome to the Dashboard</h1>
    <h4>This is just the start! Investing Awaits!</h4>
    <img class="hero" src="../../public/stocks.jpg" alt="">
  </div>
</template>

<script>
import NavBar from '../components/NavBar.vue';
export default {
  name: "home",
  components: { NavBar }
  };
</script>

<style>
  div.home {
    display: grid;
    grid-template-areas: 
      "nav . title . ."
      "nav . description . ."
      "nav . img . .";
    grid-template-columns: 1fr 1fr 2fr 1fr 1fr;
  }
  

  h1 {
    text-align: center;
    grid-area: title;
  }

  h4 {
    text-align: center;
    grid-area: description;
  }

  img.hero {
    grid-area: img;
    width: 350px;
    height: 350px;
  }
</style>
