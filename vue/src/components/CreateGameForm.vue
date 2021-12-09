<template>
  <div class="container">
      <div class="nav">
          <nav-bar/>
      </div>
      <div class="create-game-container">
          <form @submit.prevent="createGame">
              <label for="game-name">Name: </label>
              <input type="text" class="text-input" name="game-name" v-model="game.name">
              <label for="starting-amount">Starting Cash: </label>
              <select name="starting-amount" id="starting-amount" v-model="game.amount">
                  <option value="10000" class="amount">10,000</option>
                  <option value="25000" class="amount">25,000</option>
                  <option value="50000" class="amount">50,000</option>
                  <option value="100000" class="amount">100,000</option>
              </select>
              <label for="end-date">Ending Date: </label>
              <input type="date" name="end-date" min="date" id="date" v-model="game.endDate">
              <button class="btn btn-lg btn-primary btn-block" type="submit">Create Game</button>
          </form>
      </div>
  </div>
</template>

<script>
import NavBar from './NavBar.vue'
export default {
    name: 'create-game-form',
    components: { NavBar },
    data() {
        return {
            date: new Date().toISOString().slice(0, 10),
        game: {
            name: '',
            amount: '',
            endDate: ''
          }
        }
    },
    methods: {
        getToday() {
            let today = new Date();
            const dd = today.getDate();
            const mm = today.getMonth() + 1;
            const yyyy = today.getFullYear();
            today = `${yyyy}-${mm}-${dd}`;
            document.getElementById("date").setAttribute("min", today);
        }
    }
}
</script>

<style>
    .container {
        display: flex;
    }
    
    .create-game-container {
        display: flex;

    }
</style>