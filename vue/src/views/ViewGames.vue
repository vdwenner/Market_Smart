<template>
  <div class="container">
      <div class="nav">
          <nav-bar/>
      </div>
      <table class = "userGames" >
          <tr class = "game-header">
              <th>Game Name</th>
              <th>Starting Amount</th>
              <th>End Date</th>
          </tr>
          <tr class = "game-info" v-for="userGame in games" :key="userGame.id">
            <!-- <router-link :to="" -->
            <td class="game-data">{{userGame.gameName}}</td>
            <td class="game-data">{{userGame.startingAmount}}</td> 
            <td class="game-data">{{userGame.endDate}}</td>
            <td class="game-data">{{userGame.id}}</td>

            <td class="game-data">
                <a href="#invite-form" v-on:click.prevent="showForm = !showForm" v-show="showForm==false">Invite Player</a>
                 <form id="invite-form" v-on:submit.prevent v-if="showForm == true">
                <label for="receiver-name">Enter Player's Username</label>
                <input id="receiver-name" type="text" v-model="receiver.receiverName" />
                 <input type="submit" value="Save" v-on:click="inviteToGame(userGame.id)">
                 <input type="button" value="Cancel" v-on:click="resetForm">

          </form>        
            </td>
           
          </tr>
              
          <!-- Form should go here -->
            
      </table>
      
  </div>
</template>

<script>
import NavBar from '../components/NavBar';
import gameService from "../services/GameService";
import authService from '../services/AuthService';

export default {
    components: { NavBar },
    data() {
        return {
            games: [],
            game: {
                gameId: '',
                gameName: '',
                startingAmount: '',
                endDate: ''
            },
            receiver: {
                receiverName: ''
            },
            inviteType: {
                senderId: '',
                receiverId: '',
                gameId: '',
                gameInviteTypeId: 3

            },
            showForm: false
        }
    },
    methods: {
        inviteToGame(gameId) {
            authService.getGamerByUsername(this.receiver.receiverName).then( response =>{
               this.inviteType.receiverId = response.userId;
               this.inviteType.senderId = this.$store.state.user.userId;
               this.inviteType.gameId = gameId;
               
                    gameService.inviteToGame(this.inviteType).then( response => {
                            if(response.status == 200) {
                                this.resetForm();
                            }
                        }).catch()
            })
        },
        resetForm() {
            this.receiver.receiverName = '';
            this.showForm = false;
        },
        setForm(){
            authService.getGamerByUsername(this.receiver.receiverName).then( response =>{
               this.inviteType.receiverId = response.userId;
               return this.inviteType;
           }

           );
        }
    },
    created() {
        gameService.viewGames().then(response=>{
            this.games=response;})
       
    }
}
</script>

<style>
.container{
    display: grid;
    grid-template-columns:1fr 1fr 1fr 1fr 1fr 1fr;
    grid-template-rows: 1fr 2fr 1fr;
    grid-template-areas: 
    "nav . . . . ."
    "nav . table table table ."
    "nav . . . . .";

}
.nav{
    grid-area: nav;
}
.userGames {
    display: flex;
    flex-direction: column;
    
border:1px solid black;
grid-area: table;

  
}
.game-header{
  display:flex;

	background: red;
    justify-content: space-evenly;
}

.game-info{
    display:flex;
	
    justify-content: space-around;

}


.game-data{
    display: flex;
    justify-content: space-evenly;
}

th,td{
    padding: 20px;
    
}
</style>