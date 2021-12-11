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
        created() {
            gameService.getGameByGameId(this.$route.params.id).then(response=>{
                gameService.viewLeaderboard(response).then(response => {
                    this.portfolios.push(response);
                })
            })
            
        }
    }
}
    
}
</script>

<style>

</style>