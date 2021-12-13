<template>
<div class="comp-game-details">
    <h3>Game Name: {{game.gameName}}</h3>
    <h3>Starting Amount: ${{game.startingAmount}}</h3>
    <h3>End Date: {{game.endDate}}</h3>
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
    div.comp-game-details {
        display: flex;
        flex-direction: column;
        align-items: flex-start;
    }
</style>