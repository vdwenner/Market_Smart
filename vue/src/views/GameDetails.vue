<template>

<div class="game-details-container">
      <div class="navbar">
          <nav-bar/>
      </div>
      
    <div class="game-container">
        <game-detail-guts/>
    </div>
    
    <table class = "portfolioLeaderboard" >
        <thead>
            <h2>Leaderboard</h2>
            <tr>
                <th>User</th>
                <th>Cash Amount</th>
                <th>Portfolio Value</th>
            </tr>
        </thead>
        <tbody class="leaderboard-details">
            <leaderboard v-for="portfolio in portfolios" :key="portfolio.id" 
                v-bind:portfolio="portfolio"/>
        </tbody>

    </table>

    <buy-stock class="buy-stock-container"/>
    <sell-stock class="sell-stock-container"/>
    </div>
</template>

<script>
import GameDetailGuts from '../components/GameDetailGuts';
import NavBar from '../components/NavBar';
import Leaderboard from '../components/Leaderboard';
import gameService from '../services/GameService';
import BuyStock from '../components/BuyStock.vue';
import SellStock from '../components/SellStock.vue';

export default {
components: { NavBar, GameDetailGuts, Leaderboard, BuyStock, SellStock },
data() {
    return {
        portfolios: [],
        gameId: '',
        portfolioId:'',
        stock: {},
            symbol: '',
            transaction: {
                transactionId: '',
                transactionType: '',
                price: '',
                portfolioId: '',
                stockSymbol: '',
                quantity: ''
            },
            errorMessage: '',
            
      
       
        
    } 
},
methods: {},

created(){
        gameService.viewLeaderboard(this.$route.params.id).then( response => {
            this.portfolios = response;
        })

        
}


}
</script>

<style>
    .game-details-container {
        display: grid;
        grid-template-areas:
        "nav . details details details details ."
        "nav . leaderboard leaderboard leaderboard leaderboard ."
        "nav . . . . . ."
        "nav . . buy sell . .";
        grid-template-columns: 1fr 1fr 1fr 1fr 1fr 1fr 1fr;
    }
    .game-container {
        grid-area: details;
        margin-top: 50px;
    }

    h2 {
        color: #E2B842;
        font-weight: 700;
        text-align: center;
    }

    .portfolioLeaderboard {
    background: #012B39;
    border-radius: 0.25em;
    border-collapse: collapse;
    margin: 1em;
    grid-area: leaderboard;
    }

    .buy-stock-container {
        grid-area: buy;
        display: flex;
        justify-content: center;
        width: 100%;
    }

    .sell-stock-container {
        grid-area: sell;
        display: flex;
        justify-content: center;
        width: 100%;
    }
    .navbar{
        grid-area: nav;
    }

    /* th {
    border-bottom: 1px solid #364043;
    color: #E2B842;
    font-size: 0.85em;
    font-weight: 600;
    padding: 0.5em 1em;
    text-align: center;
    width: 100px;
    }

    td {
    color: #fff;
    font-weight: 400;
    padding: 0.65em 1em;
    width: 100px;
    } */

    .disabled td {
    color: #4F5F64;
    }

    tbody.leaderboard-details tr {
    transition: background 0.25s ease;
    }
    
    tbody.leaderboard-details tr:hover {
    background: #014055;
    }

    
</style>