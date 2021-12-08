<template>
  <div class="container">
      <div class="nav">
          <nav-bar/>
      </div>
      <div class="create-game-container">
          <form action="">
              <label for="game-name">Name: </label>
              <input type="text" class="text-input" name="game-name">
              <input type="number" >
          </form>
      </div>
  </div>
</template>

<script>
import NavBar from './NavBar.vue'
export default {
    components: { NavBar }
}
</script>

<style>

</style>