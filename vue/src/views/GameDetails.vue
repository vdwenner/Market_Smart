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
        gameId: ''
        // game: {
        //         gameId: '',
        //         gameName: '',
        //         startingAmount: '',
        //         endDate: ''
        // },
       
        
    } 
},
//  methods: {
//             getLeaderboard() {
//                 gameService.viewLeaderboard(this.$route.params.id).then( response => {
//                     this.portfolios = response;
//                 })
        //     }
            
        // },
        created(){
             gameService.viewLeaderboard(this.$route.params.id).then( response => {
                    this.portfolios = response;
                })
        }


}
</script>

<style>
    .leaderboard{
        text-align: center;
    }
</style>