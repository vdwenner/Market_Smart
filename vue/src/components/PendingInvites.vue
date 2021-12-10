<template>
  <div class = "pendingInvites"><a href="#pending-invites" 
  v-on:click.prevent="showPending = !showPending" v-show="showPending==false">View Pending Invites</a>
          
    <div id="pending-invites" v-on:submit.prevent v-show="showPending == true">

        <tr class = "game-info" >
            <!-- <router-link :to="" -->
            <!-- <td class="game-data td-left">{{userGame.gameName}}</td>
            <td class="game-data td-right">{{userGame.startingAmount}}</td> 
            <td class="game-data td-right">{{userGame.endDate}}</td> -->
            <input type="button" value="Cancel" v-on:click="resetForm">
        </tr>

    </div>  
          
  </div>     
</template>

<script>

// import gameService from "../services/GameService";
// import authService from '../services/AuthService';

export default {
    data() {
        return {
          showPending: false,  
        }
    },
    game: {
        gameId: '',
        gameName: '',
        startingAmount: '',
        endDate: ''
        },
    methods: {
        resetForm() {
            this.showPending = false;
        }
    } 

}
</script>

<style>
.pendingInvites{
    display: flex;
    justify-content: center;
}
</style>