<template>
  <div class="container1">
    <div class="nav">
        <nav-bar/>
    </div>
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
.container1{
    display: grid;
    grid-template-columns:1fr 1fr 1fr 1fr 1fr 1fr;
    grid-template-rows: 1fr 2fr 1fr;
    grid-template-areas: 
    "nav . research research research ."
    "nav . . . . ."
    "nav . . . . .";
}
.research {
  grid-area: research;
}
.nav {
  grid-row: nav;
}
</style>