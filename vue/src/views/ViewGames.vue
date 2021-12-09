<template>
  <div class="container">
      <div class="nav">
          <nav-bar/>
      </div>
      <div v-for="game in games" :key="game" :v-model="games.game">
          <h3>{{this.gameName}}</h3>
          <h3>{{this.games.game.startingAmount}}</h3>
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
    methods: {
        created() {
            gameService.viewGames().forEach( (game) => {
                this.games.push(game);
            });
        }
    }
    
}
</script>

<style>

</style>