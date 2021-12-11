<template>
  <div class = "pendingInvites">
    <div id="pending-invites" >

        <tr class = "game-info" >
            <td class="game-data td-left">{{pendingInvite.gameName}}</td>
            <td class="game-data td-right">{{pendingInvite.startingAmount}}</td> 
            <td class="game-data td-right">{{pendingInvite.endDate}}</td> 
            <td><input type="submit" value="Accept" v-on:click="acceptInvite(pendingInvite)"></td>
            <td> <input type="submit" value="Decline" v-on:click="rejectInvite(pendingInvite)"></td>
            
        </tr>

    </div>  
          
  </div>     
</template>

<script>

import gameService from "../services/GameService";
// import authService from '../services/AuthService';

export default {
    name: 'pending-invite',
    props: ['pendingInvite'],
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
        acceptInvite(invite){
            // this.game.gameId = invite.id;
            // this.game.gameName = invite.gameName;
            // this.game.startingAmount = invite.startingAmount;
            // this.game.endDate = invite.endDate;
            gameService.approvePendingInvite(invite).then((response) =>{
             if(response.status == 200 || response.status == 201) {
                 this.$router.push("/user/games"); 
                 this.resetForm();   }
        }) 
        }, rejectInvite(invite){
            gameService.rejectPendingInvite(invite).then((response) =>{
                if(response.status==200){
                    this.$router.push("user/games");
                    this.resetForm();
                }
            })
        }
        
        
        ,resetForm() {
            this.game ={
        gameId: '',
        gameName: '',
        startingAmount: '',
        endDate: ''
        }
        },

    } 

}
</script>

<style>

</style>
