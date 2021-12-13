<template>
<div>
    <th>Game Name: {{game.gameName}}</th>
    <th>Starting Amount: {{game.startingAmount}}</th>
    <th>End Date: {{game.endDate}}</th>
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