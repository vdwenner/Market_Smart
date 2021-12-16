<template>
    <div class="view-game-container">
      
        <div class="nav">
            <nav-bar/>
        </div>
        <div class="title">
            <h1>Select the Game You Would Like to Play</h1>
        </div>
        
        <!-- <table class = "userGames" > -->
        <table class="game-table">
            <thead>
            
                <!-- <tr class="game-info"> -->
                <tr>
                    <th>Game Name</th>
                    <th>Starting Amount</th>
                    <th>End Date</th>
                    <th>Invite to Game</th>
                </tr>
            </thead>
            <tbody>
                <game-overview v-for="userGame in games" :key="userGame.id" 
                    v-bind:userGame="userGame"/>
            </tbody>

        </table>
        
        
        <!-- <div class="pending-invites" > -->
            <div class="the-button"> 
        <button class="btn pend-btn" href="#pending-invites" v-on:click="showPending = !showPending"
            v-show="showPending==false">Show Pending Invites</button>  
        

        <button class="btn pend-btn"
        v-on:click="resetPendingForm" v-show="showPending == true">Hide</button>
        </div>
            <table class="pending-invites-table">
                 
                <thead>
                    <tr v-show="showPending == true">
                        <th>Game Name</th>
                        <th>Starting Amount</th>
                        <th>End Date</th>
                        <th>Accept Invite</th>
                        <th>Decline Invite</th>
                    </tr>
                </thead>
                <tbody>
                    
                    <pending-invites v-for="pendingInvite in invites" 

                    :key="pendingInvite.gameId"
                    v-bind:pendingInvite="pendingInvite"
                    v-show="showPending == true"
                    v-on:acceptInvite="acceptInvite"/>
                    
                </tbody>
            
            
            </table>
        </div>
      
  <!-- </div> -->
  
</template>

<script>
import NavBar from '../components/NavBar';
import gameService from "../services/GameService";
import authService from '../services/AuthService';
import GameOverview from '../components/GameOverview.vue';
import PendingInvites from '../components/PendingInvites.vue';

export default {
    components: { NavBar, GameOverview, PendingInvites },
    data() {
        
        return{
            // games: [],
            game: {
                gameId: '',
                gameName: '',
                startingAmount: '',
                endDate: ''
            },
            receiver: {
                receiverName: ''
            },
            // invites: [],            
            inviteType: {
                senderId: '',
                receiverId: '',
                gameId: '',
                gameInviteTypeId: 3

            },
            showForm: false,
            showPending: false,
            rerender: true,
            pendingGameInvites: []
        }},


        computed: {
            invites() {
                return this.$store.state.pendingInvites;
            },
            games() {
                return this.$store.state.games;
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

        acceptInvite() {
            this.rerender = false;
            this.$nextTick(() => {
          // Add the component back in
            this.rerender = true;
        });

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
        },
        resetPendingForm() {
            this.showPending = false;
        }
    },
    created() {

        this.$store.commit("SET_USER_GAMES");
        // gameService.viewGames().then(response=>{
        //     this.games=response;})

        // gameService.viewPendingGameInvites().then(response =>{
        //     this.pendingGameInvites = response;
        //     response.forEach((game) =>{
        //     gameService.getGameByGameId(game.gameId).then((response)=>{
        //         this.invites.push(response);
        //     })
        //     })
        // })
       
       //CALL STORE HERE
       this.$store.commit("SET_PENDING_INVITES");

    }
    
}
</script>

<style>
 .view-game-container{
    display: grid;
    grid-template-columns:1fr 1fr 1fr 1fr 1fr 1fr;
    grid-template-areas: 
    "nav . title title title ."
    "nav . table table table ."
    "nav . . button . ."
    "nav . pending pending pending .";
    
}
.pend-btn{
    width: 150px;
}

 .pending-invites-table{
    /* display: flex; */
    grid-area: pending;
    margin-top: 50px;
} 
.nav{
    grid-area: nav;
}
.title {
    grid-area: title;
    text-align: center;
    margin-bottom: 50px;
    height: 100px;
}
table.game-table{
    grid-area: table;
}
.the-button{
    grid-area: button;
    text-align: center;
}

::-webkit-scrollbar {
    width: 10px;
}

::-webkit-scrollbar-track {
    background: #f1f1f1;
}

::-webkit-scrollbar-thumb {
  background: #888; 
}

::-webkit-scrollbar-thumb:hover {
  background: #555; 
}
/*
.game-header{
    display:flex;
    border-radius: 10px;
	background: rgba(4, 42, 61) 0%;
    color: #c99200;
    justify-content: space-evenly;
} 


.pending-invites-table >thead{
    background: rgba(4, 42, 61) 0%;
    color: #c99200;
}
.pending-invites-table {
    width: 100%;
}
tr.game-info:hover {
    background: rgba(4, 42, 61) 0%;
    color: #c99200;
}

#invite-form:hover {
    text-decoration: #c99200;
}
div.pending-invites{
    width: 100%;
    justify-content: center;
}

table.userGames {
  background: #012B39;
  border-radius: 0.25em;
  border-collapse: collapse;
  margin: 1em;
  grid-area: table;
  height: 500px;
  overflow-y: scroll;
  box-shadow: 10px 10px 10px #999;
}

thead {
    display: flex;
    justify-content: space-between;
    width: 80%;
}

th {
  border-bottom: 1px solid #364043;
  color: #E2B842;
  font-size: 0.85em;
  font-weight: 600;
  padding: 0.5em 1em;
  text-align: center;
  width: 100px;
}
td {
  background: #012B39;
  color: #fff;
  font-weight: 400;
  padding: 0.65em 1em;
  width: 100px;
}
.disabled td {
  color: #4F5F64;
}
tbody.pending-invites-data tr {
  transition: background 0.25s ease;
}
tbody.pending-invites-data tr:hover {
  background: #fff;
}

::-webkit-scrollbar {
    width: 10px;
}

::-webkit-scrollbar-track {
    background: #f1f1f1;
}

::-webkit-scrollbar-thumb {
  background: #888; 
}

::-webkit-scrollbar-thumb:hover {
  background: #555; 
} */
</style>