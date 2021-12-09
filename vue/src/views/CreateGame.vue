<template>
  <div class="container">
      <div class="nav">
          <nav-bar/>
      </div>
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
            path: '/',
          })
        }
      }).catch()
    }
  }

}
</script>

<style>
  @import url('https://fonts.googleapis.com/css2?family=Inter&family=Lexend+Deca&display=swap');

  .container {
        display: flex;
        font-family: 'Inter', sans-serif;
    }
    
    .create-game-container {
        position: fixed;
        display: inline-block;
        width: 100vw;
        margin-top: 250px;
    }

    label {
      display: inline-block;
      padding: 12px 12px 12px 0;
    }

    input[type=text] {
      width: 100%;
      
    }

    select {
      width: 150px;
    }

    input[type=number] {
      width: 100%;
      padding-left: 100px;
    }

    button {
      margin-top: 20px;
      font-family: 'Inter', sans-serif;
    }

</style>