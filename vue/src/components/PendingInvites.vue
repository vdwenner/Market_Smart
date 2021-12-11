<template>
  <div class = "pendingInvites">
    <div id="pending-invites" >

        <tr class = "game-info" >
            <td class="game-data td-left">{{pendingInvite.gameName}}</td>
            <td class="game-data td-right">{{pendingInvite.startingAmount}}</td> 
            <td class="game-data td-right">{{pendingInvite.endDate}}</td> 
            <td><button v-on:click="acceptInvite(pendingInvite)">Accept</button></td>
            <td><button v-on:click="rejectInvite(pendingInvite)">Decline</button></td>
            
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
          componentKey: 0
        }
    },
    
    inviteToSend: {
        senderId: '',
        receiverId: '',
        gameId: '',
        gameInviteTypeId: ''
    },
    
    game: {
        gameId: '',
        gameName: '',
        startingAmount: '',
        endDate: ''
    },
    
    methods: {
        acceptInvite(invite){
            gameService.approvePendingInvite(invite).then( response =>{
                invite.gameInviteTypeId = 1;
                this.$emit('acceptInvite');
                return response;
            }) 
        },
        
        rejectInvite(invite){
            gameService.rejectPendingInvite(invite).then( response =>{
                if(response.status == 200){
                    this.$router.push('/');
                }
            })
        },

        forceRerender() {
            this.componentKey += 1;  
        },
        
        resetForm() {
            this.game = {
                gameId: '',
                gameName: '',
                startingAmount: '',
                endDate: ''
            }
        }

    } 

}
</script>

<style>

</style>
