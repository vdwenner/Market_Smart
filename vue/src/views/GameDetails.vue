<template>
<div class="game-details-container">
      
      <div class="nav">
          <nav-bar/>
      </div>
      
    <div class="game-container">
        <game-detail-guts/>
    </div>

    <div class="leaderboard">
        <leaderboard/>
        <button v-on:click="getLeaderboard">Show Leaderboard</button>
    </div>
    </div>
</template>

<script>
import GameDetailGuts from '../components/GameDetailGuts';
import NavBar from '../components/NavBar';
import Leaderboard from '../components/Leaderboard';
import gameService from '../services/GameService';
export default {
components: { NavBar, GameDetailGuts, Leaderboard },
data() {
    return {
        portfolios: [],
        game: {
                gameId: '',
                gameName: '',
                startingAmount: '',
                endDate: ''
        },
        methods: {
            getLeaderboard() {
                gameService.viewLeaderboard(this.game).then( response => {
                    this.portfolios = response;
                    return this.portfolios;
                })
            }
            
        }
        
    } 
},
created() {
    gameService.getGameByGameId(this.$route.params.id).then(response=>{
                this.game = response;
            })
  },

}
</script>

<style>
    .leaderboard{
        text-align: center;
    }
</style>