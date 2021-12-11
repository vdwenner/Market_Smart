<template>
<div>
    <h1>Creator Id: {{game.creatorId}}</h1>
    <h1>End Date: {{game.endDate}}</h1>
    <h1>Game Name: {{game.gameName}}</h1>
    <h1>Game Id: {{game.id}}</h1>
    <h1>Starting Amount: {{game.startingAmount}}</h1>
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