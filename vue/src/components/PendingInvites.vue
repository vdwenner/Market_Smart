<template>
  <div class = "pendingInvites">
    <div id="pending-invites" >

        <tr class = "game-info" >
            <td class="game-data td-left">{{pendingInvite.gameName}}</td>
            <td class="game-data td-right">{{pendingInvite.startingAmount}}</td> 
            <td class="game-data td-right">{{pendingInvite.endDate}}</td> 
            <td><input type="submit" value="Accept" v-on:click="acceptInvite(pendingInvite.id)"></td>
            <td> <button>No</button>   </td>
            
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
        gameService.approvePendingInvite(invite).then(response =>{
             if(response.status == 200) {
                 this.$router.push("/user/games");    }
        }) 
        }

    } 

}
</script>

<style>

</style>
