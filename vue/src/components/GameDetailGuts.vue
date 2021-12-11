<template>
<div>
    <h1>{{game.creatorId}}</h1>
    <h1>{{game.endDate}}</h1>
    <h1>{{game.gameName}}</h1>
    <h1>{{game.id}}</h1>
    <h1>{{game.startingAmount}}</h1>
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