<template>
<div class="container">
      
      <div class="nav">
          <nav-bar/>
      </div>
      
    <div class="game-container">
        <game-detail-guts/>
    </div>
    </div>
</template>

<script>
import GameDetailGuts from '../components/GameDetailGuts';
import NavBar from '../components/NavBar';
export default {
    components: { NavBar, GameDetailGuts},
    
    /* Took this directly from the 3-15 HW */
    
}
</script>

<style>

</style>