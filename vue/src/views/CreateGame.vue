<template>
  <div class="container">
      <create-game-form/>
  </div>
</template>

<script>
import CreateGameForm from "../components/CreateGameForm.vue";
import gameService from "../services/GameService";

export default {
  components: { CreateGameForm },
  methods: {
    createGame() {
      gameService.createGame(this.game).then((response) => {
        if(response.status == 201) {
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

</style>