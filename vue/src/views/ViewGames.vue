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
            <td class="game-data">{{userGame.gameName}}</td>
            <td class="game-data">{{userGame.startingAmount}}</td> 
            <td class="game-data">{{userGame.endDate}}</td>
            
          </tr>
      </table>
  </div>
</template>

<script>
import NavBar from '../components/NavBar';
import gameService from "../services/GameService";


export default {
    components: { NavBar },
    data() {
        return {
            games: [],
            game: {
                gameName: '',
                startingAmount: '',
                endDate: ''
            }
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