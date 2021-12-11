<template>
<div>
    <h1>Leaderboard</h1>

  <tr >
    <td class="leaderboard td-left">{{userGame.gameName}}</td> 
    <td class="leaderboard td-right">{{userGame.startingAmount}}</td> 
    <td class="leaderboard td-right">{{userGame.endDate}}</td>
  </tr>
</div>
  
</template>

<script>
export default {
    props: ['user'],
}
</script>

<style>

</style>