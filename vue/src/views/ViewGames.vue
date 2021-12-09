<template>
  <div class="container">
      <div class="nav">
          <nav-bar/>
      </div>
      <div v-for="userGame in games" :key="userGame.id">
          <h3>{{userGame.gameName}}</h3>
          <h3>{{userGame.startingAmount}}</h3>
      </div>
  </div>
</template>

<script>
import NavBar from '../components/NavBar';
import gameService from "../services/GameService";

export default {
    components: { NavBar },
    data() {
        return {
            games: [],
            game: {
                gameName: '',
                startingAmount: '',
                endDate: ''
            }
        }
    },
    
    created() {
        gameService.viewGames().then(response=>{
            this.games=response;})
       
    }

    
}
</script>

<style>

</style>