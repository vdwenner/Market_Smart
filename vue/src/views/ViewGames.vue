<template>
  <div class="container">
      
      <div class="nav">
          <nav-bar/>
      </div>
      <div class="title">
          <h1>Select the Game You Would Like to Play</h1>
      </div>
      <!-- Special Game -->
      <table class = "userGames" >
          <tr class = "game-header">
              <th>Game Name</th>
              <th>Starting Amount</th>
              <th>End Date</th>
              <th>Invite to Game</th>
          </tr>
          <view-game-component v-for="userGame in games" :key="userGame.id" 
          v-bind:userGame="userGame"/>

          <!-- <pending-invites/> -->

      </table>
      <div class="pending-invites">
        <pending-invites/>
      </div>
  </div>
  
</template>

<script>
import NavBar from '../components/NavBar';
import gameService from "../services/GameService";
import authService from '../services/AuthService';
import ViewGameComponent from '../components/ViewGameComponent.vue';
import PendingInvites from '../components/PendingInvites.vue';


export default {
    components: { NavBar, ViewGameComponent, PendingInvites },
    data() {
        
        return{
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
            invites: [],            
            inviteType: {
                senderId: '',
                receiverId: '',
                gameId: '',
                gameInviteTypeId: 3

            },
            showForm: false,
            showPending: false
        }}
    ,
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

        gameService.viewPendingGameInvites().then(response =>{
            this.invites=response;
        })
       
    }
    
}
</script>

<style>
.container{
    display: grid;
    grid-template-columns:1fr 1fr 1fr 1fr 1fr 1fr;
    grid-template-rows: 1fr 2fr 1fr;
    grid-template-areas: 
    "nav . title title title ."
    "nav . table table table ."
    "nav . pending pending pending .";

}
 .pending-invites{
    grid-area: pending;
} 

.nav{
    grid-area: nav;
}

.title {
    grid-area: title;
    text-align: center;
    margin-bottom: 20px;
    height: 100px;
}
.userGames {
    display: flex;
    flex-direction: column;
    border:1px solid black;
    grid-area: table;
}
.game-header{
    display:flex;
	background: rgba(4, 42, 61) 0%;
    color: #c99200;
    /* justify-content: space-evenly;*/
} 

/* .game-info{
    display:flex;
    justify-content: space-around;
} */



th,td{
    padding: 20px;   
}

tr.game-info:hover {
    background: rgba(4, 42, 61) 0%;
    color: #c99200;
}

#invite-form:hover {
    text-decoration: #c99200;
}
</style>