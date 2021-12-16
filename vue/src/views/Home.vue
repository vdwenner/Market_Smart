<template>
<div class="home-outer-div">
  <nav-bar class="home-nav-bar"></nav-bar>
  <div class="home">
    
    <h1 class="page-title">Market Smart</h1>

    <h3 class="page-phrase">Test Your Skills, Without the Bills</h3>

    <img class="hero" src="../../public/stocks.jpg" alt="">
    
    <h4 class="about-container">"Building wealth is a marathon, not a sprint. Discipline is the key ingredient." </h4>
    
    
  </div>
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
  .home {
    
    display: grid;
    grid-template-areas: 
      ". . title . ."
      ". . description . ."
      ". . img . ."
      "about about about about about";
    grid-template-columns: 1fr 1fr 2fr 1fr 1fr;
    grid-area: home;
  }
  .home-outer-div{
    display: grid;
    grid-template-areas:
    "nav . home home .";
    grid-template-columns: 1fr .5fr .5fr 1fr 1fr;
  }
  

  .home-nav-bar{
    grid-area: nav;
  }
  

  h1 {
    text-align: center;
    grid-area: title;
    
  }
  
  h1.page-title{
    font-size: 60px;
  }

  h4, h3{
    text-align: center;
    grid-area: description;
  }

  img.hero {
    grid-area: img;
    width: 400px;
    height: 400px;
  }

  .about-container {
        color: rgba(4, 42, 61) 0%;
        grid-area: about;
        margin-top: 50px;
        text-align: center;
        background: #efefef;
        /* width: 40vw; */
        padding: 20px;
        border-radius: 30px;
        box-shadow: 10px 5px 20px  grey;
    }
</style>
