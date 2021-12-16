<template>
  <div class="new-game-container">
      <div class="nav">
          <nav-bar/>
        
      </div>

      
        <h1 class="create-title">Create a New Game</h1>
      

      <div class="create-game-container">
          <form @submit.prevent="createGame">
              <label for="game-name">Name: </label>
              <input type="text" class="text-input" name="game-name" placeholder="Name" v-model="game.gameName">
              <label for="starting-amount" class="starting-cash">Starting Cash:
                <select name="starting-amount" id="starting-amount" v-model.number="game.startingAmount">
                    <option value="10000" class="amount">10,000</option>
                    <option value="25000" class="amount">25,000</option>
                    <option value="50000" class="amount">50,000</option>
                    <option value="100000" class="amount">100,000</option>
                </select>
              </label>
              <label for="end-date">Ending Date: </label>
              <input type="date" name="end-date" min="date" id="date" v-model="game.endDate">
              <button class="btn btn-lg btn-primary btn-block" type="submit">Create Game</button>
          </form>
      </div>
  </div>
</template>

<script>
import NavBar from "../components/NavBar"
import gameService from "../services/GameService";

export default {
  components: { NavBar },
    data() {
        return {
            date: new Date().toISOString().slice(0, 10),
        game: {
            gameName: '',
            startingAmount: '',
            endDate: ''
          }
        }
    },
  methods: {
    createGame() {
      gameService.createGame(this.game).then((response) => {
        if(response.status == 200) {
          this.$router.push({
            path: '/user/games',
          })
        }
      }).catch()
    }
  }

}
</script>

<style>
  @import url('https://fonts.googleapis.com/css2?family=Inter&family=Lexend+Deca&display=swap');

  .new-game-container {
        display: flex;
        font-family: 'Inter', sans-serif;
    }

    .create-title{
      display: inline-block;
      text-align: center;
      width: 100%;
      margin-top: 100px;
    }
    
    .create-game-container {
        position: fixed;
        display: inline-block;
        width: 100vw;
        margin-top: 250px;
        border-radius: 20px;
    }

    label {
      display: inline-block;
      padding: 12px 12px 12px 0;
      color: #012B39;
    }

    input[type=text] {
      font-family: 'Inter';
      width: 100%;
      padding: 12px 20px;
      margin: 8px 0;
      display: inline-block;
      border: 1px solid #ccc;
      border-radius: 4px;
      box-sizing: border-box;
    }

    select {
      margin: 8px 0;
      width: 100%;
      font-family: 'Inter';
    }

    input[type=number] {
      font-family: 'Inter';
      width: 100%;
      padding: 12px 20px;
      margin: 8px 0;
      display: inline-block;
      border: 1px solid #ccc;
      border-radius: 4px;
      box-sizing: border-box;
    }

    input[type=date] {
      font-family: 'Inter';
      width: 100%;
      padding: 12px 20px;
      margin: 8px 0;
      display: inline-block;
      border: 1px solid #ccc;
      border-radius: 4px;
      box-sizing: border-box;
    }

    button.btn {
      margin-top: 20px;
      font-family: 'Inter', sans-serif;
      background: #012B39;
      color:  #ccd9dd;
    }

    button.btn:hover {
      color: #012B39;
      background: #efefef;
    }

</style>