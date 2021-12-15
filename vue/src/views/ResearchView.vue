<template>
  <div class="research-view-container">
    <div class="nav">
        <nav-bar/>
    </div>
    <h1 class="search-title">Search For Stocks</h1>
    <div class="research">
      <research />
    </div>
  </div>
</template>

<script>
import Research from '../components/Research.vue'
import NavBar from '../components/NavBar';
export default {
    components: {Research, NavBar}
}
</script>

<style>
.research-view-container{
    display: grid;
    grid-template-columns:1fr 1fr 1fr 1fr 1fr 1fr;
    /* grid-template-rows: 1fr 2fr 1fr; */
    grid-template-areas: 
    "nav . title title title ."
    "nav . research research research ."
    "nav . . . . .";
}
.research {
  grid-area: research;
  justify-content: center;
  
}
.nav {
  grid-area: nav;
}

.search-title{
  grid-area: title;
}



</style>