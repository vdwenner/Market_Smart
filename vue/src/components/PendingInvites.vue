<template>
  <div class = "pendingInvites">
    <div id="pending-invites" >

        <tr class = "game-info" >
            <td class="game-data td-left">{{invite.gameName}}</td>
            <td class="game-data td-right">{{invite.startingAmount}}</td> 
            <td class="game-data td-right">{{invite.endDate}}</td> 
            <td><button v-on:click="acceptInvite(invite)">Accept</button></td>
            <td><button v-on:click="rejectInvite(invite)">Decline</button></td>
            
        </tr>

    </div>  
          
  </div>     
</template>

<script>

import gameService from "../services/GameService";

export default {
    name: 'pending-invite',
    props: ['pendingInvite'],

    created() {
        this.invite = this.pendingInvite;
    },
    
    data() {
        return {
          componentKey: 0,
          invite: {}
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
                // invite.gameInviteTypeId = 1;
                // this.$emit('acceptInvite');
        
                this.$store.commit("SET_USER_GAMES");
                this.$store.commit("SET_PENDING_INVITES");
                return response;
            }) 
        },
        
        rejectInvite(invite){
            gameService.rejectPendingInvite(invite).then( response =>{
                    this.$store.commit("SET_USER_GAMES");
                    this.$store.commit("SET_PENDING_INVITES");
                    return response;
                
            })
        },

        // forceRerender() {
        //     this.componentKey += 1;  
        // },
        
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
