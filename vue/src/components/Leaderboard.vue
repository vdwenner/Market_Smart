<template>
<div>
    <tr>
      <td>{{this.username.username}}</td>
      <td>{{this.portfolio.cashBalance}}</td>
      <td>{{this.portfolio.portfolioValue}}</td> 
    </tr>
</div>
  
</template>

<script>
import authService from '../services/AuthService'
export default {
    props: ['portfolio'],
    data() {
    return {
        portfolios: [],
        portfolio1: {
          id: '',
          gameId: '',
          userId: '',
          cashBalance: '',
          portfolioValue: ''
        },
        game: {
                gameId: '',
                gameName: '',
                startingAmount: '',
                endDate: ''
        },
        username: ''
    }
  },
  created() {
    authService.getUsernameById(this.portfolio.userId).then( response => {
      this.username = response;
    })
  }
}
</script>

<style>

</style>