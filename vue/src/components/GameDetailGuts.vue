<template>
<div class="game-details-small">
    <h1>Game Name: {{game.gameName}}</h1>
    <h1>Starting Amount: {{game.startingAmount}}</h1>
    <h1>End Date: {{game.endDate}}</h1>
</div>





</template>


<script>

import gameService from "../services/GameService";
// import authService from '../services/AuthService';

export default {
    data(){
       
        return{
         game:[],
        
    }
    },
    methods: {
         showGameInfo() {
             gameService.getGameByGameId(this.$route.params.id).then(response=>{
                this.game = response;
             })
         }
    },
    created(){
        this.showGameInfo();
    }
}
</script>

<style>

</style>