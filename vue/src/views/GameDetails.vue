<template>
<div class="game-details-container">
      
      <div class="nav">
          <nav-bar/>
      </div>
      
    <div class="game-container">
        <game-detail-guts/>
    </div>

    <table class = "portfolioLeaderboard" >
        <thead>
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

    <div class="buy-stock-container">
        <button class="buy-btn" v-on:click="showBuy = !showBuy">Buy Stock</button>

        <form v-show="showBuy == true" v-on:submit.prevent>
            <label for="symbol">Stock Symbol: </label>
            <input type="text" id="symbol" name="symbol" v-model="symbol" placeholder="Enter Stock Symbol">
            <label for="quantity" >Quantity: </label>
            <input type="number"
                min="1" id="quantity" name="quantity" placeholder="Enter Quantity"
                v-model="transaction.quantity">
            <input type="submit" name="submit" v-on:click="buyStock"> 
        </form>
    </div>
    <div class="sell-stock-container">
        <button class="sell-btn" v-on:click="showSell = !showSell">Sell Stock</button>

        <form v-show="showSell == true">
            <label for="symbol">Stock Symbol: </label>
            <input type="text" id="symbol" name="symbol" v-model="symbol" placeholder="Enter Stock Symbol">
            <label for="quantity" >Quantity: </label>
            <input type="number"
                min="1" id="quantity" name="quantity" placeholder="Enter Quantity"
                v-model="transaction.quantity">
            <input type="submit" name="submit">
        </form>
    </div>

    </div>
</template>

<script>
import GameDetailGuts from '../components/GameDetailGuts';
import NavBar from '../components/NavBar';
import Leaderboard from '../components/Leaderboard';
import gameService from '../services/GameService';
import yahooAPIService from '../services/YahooAPIService';
// import authService from '../services/AuthService';
export default {
components: { NavBar, GameDetailGuts, Leaderboard },
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
            showBuy: false,
            showSell: false
        // game: {
        //         gameId: '',
        //         gameName: '',
        //         startingAmount: '',
        //         endDate: ''
        // },
       
        
    } 
},
methods: {
    mapUserToPortfolio() {
        // this.portfolios.filter(portfolio=>{
        //     portfolio.id == this.$store.state.user.userId
        // })


        this.portfolios.forEach( (portfolio) => {
            if(portfolio.userId == this.$store.state.user.userId) {
                this.portfolioId = portfolio.id;
            
            }
        })
    },
        buyStock() {
            this.mapUserToPortfolio();
            yahooAPIService.getStockBySymbol(this.symbol).then( response => {
                this.stock = response;
                this.errorMessage = '';
                this.transaction.transactionType = 1;
                this.transaction.portfolioId = this.portfolioId;
                this.transaction.price = response.quote.price;
                this.transaction.stockSymbol = response.symbol;
                gameService.buyStock(this.transaction, this.$route.params.id, this.symbol).then( response => {
                    if(response.status == 200) {
                        this.resetForm();
                        this.errorMessage = '';
                    }
                })
            })
            
        },

        sellStock() {
            yahooAPIService.getStockBySymbol(this.symbol).then( response => {
                this.stock = response;
                this.errorMessage = '';
                this.transaction.transactionType = 2;
                this.transaction.portfolioId = this.portfolios.
                this.transaction.price = response.quote.price;
                this.transaction.stockSymbol = response.symbol;
                gameService.sellStock(this.transaction, this.$route.params.id, this.symbol).then( response => {
                    if(response.status == 200) {
                        this.resetForm();
                        this.errorMessage = '';
                    }
                })
            })
            
        },

        resetForm() {
            this.symbol = '';
            this.stock = {};
            this.showform = false;
        }
    },

created(){
        gameService.viewLeaderboard(this.$route.params.id).then( response => {
            this.portfolios = response;

            response.forEach( portfolio => {
            if(portfolio.userId == this.$store.state.user.userId) {
                this.portfolioId = portfolio.id;
            
            }
        })
        })

        
}


}
</script>

<style>
    .game-details-container {
        display: grid;
        grid-template-areas:
        "nav . title title title ."
        "nav . details details details ."
        "nav . leaderboard leaderboard leaderboard ."
        "nav . buy . sell ."
        ;
    }
    .game-container {
        grid-area: details;
    }


    table.portfolioLeaderboard {
    background: #012B39;
    border-radius: 0.25em;
    border-collapse: collapse;
    margin: 1em;
    grid-area: leaderboard;
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
    color: #fff;
    font-weight: 400;
    padding: 0.65em 1em;
    width: 100px;
    }

    .disabled td {
    color: #4F5F64;
    }

    tbody.leaderboard-details tr {
    transition: background 0.25s ease;
    }
    
    tbody.leaderboard-details tr:hover {
    background: #014055;
    }

    .buy-stock-container {
        grid-area: buy;
    }

    .sell-stock-container {
        grid-area: sell;
    }
</style>