<template>

<div class="game-details-container">
      <div class="navbar">
          <nav-bar/>
      </div>
      
    <div class="game-container">
        <game-detail-guts/>
    </div>
    
    <table class = "myStonks" >
        <thead>
            <h2>My Stocks</h2>
            <tr>
                <th>Stock Symbol</th>
                <th>Quantity</th>
                <th>Current Price</th>
                <th>Average Cost</th>
                <th>Total Cost</th>
            </tr>
        </thead>
    <tbody>
        <portfolio v-for="stock in portfolioStocks" :key="stock.id"
        v-bind:portfolioStocks="stock"/>
    </tbody>
    </table>

    <table class = "portfolioLeaderboard" >
        <thead>
            <h2>Leaderboard</h2>
            <tr>
                <th>User</th>
                <th>Cash Amount</th>
                <th>Portfolio Value</th>
            </tr>
        </thead>
        <tbody id="win" class="leaderboard-details">
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
import Portfolio from '../components/Portfolio.vue';
import authService from '../services/AuthService';
import YahooAPIService from '../services/YahooAPIService';

export default {
components: { NavBar, GameDetailGuts, Leaderboard, BuyStock, SellStock, Portfolio },
data() {
    return {
        highlight: false,
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
        //Moved to $store
       // portfolioStocks: {},
        portfolioValue: 0,
        psQuantity: '',
        psPrice: '',
        portfolio:  {
            id: '',
            userId: '',
            gameId: '',
            cashBalance: '',
            portfolioValue: ''
        },
    } 
},
computed: {

    portfolioStocks(){
        return this.$store.state.portfolioStocks;
    },

    portfolios(){
        return this.$store.state.portfolios;
    }

},
methods: {
            updatePortfolioValue(portfolio){
                gameService.setPortfolioValue(portfolio).then(response=>{
                this.portfolioValue =0;
                return response;
                })
                
            },

            updateLeaderboard() {
                 YahooAPIService.updateLeaderboard(this.$route.params.id).then(response => {
                 return response;
            })
            },

            highlightWinner() {
                let winnerRow = document.getElementById('win').firstElementChild;
                winnerRow.classList.add('winner');
            }

            


},

created(){
        let today = new Date();
        let dd = String(today.getDate()).padStart(2, '0');
        let mm = String(today.getMonth() + 1).padStart(2, '0');
        let yyyy = today.getFullYear();

        today = `${yyyy}-${mm}-${dd}`

        if(this.$store.state.game.endDate > today) {
            this.updateLeaderboard();
        }
        

        
        gameService.viewLeaderboard(this.$route.params.id).then( response => {
            //MOVE TO STORE
            //this.portfolios = response;
            
            this.$store.commit("SET_PORTFOLIOS", response);
        })

        this.$store.commit("SET_GAME_ID", this.$route.params.id);

        authService.getPortfolioByUserIdAndGameId(this.$store.state.user.id, this.$store.state.gameId).then(response=>{
            gameService.getPortfolioStocksByPortfolioId(response.id).then(response =>{
                //Moved to $store Mutation
                //this.portfolioStocks = response;
                this.$store.commit("SET_PORTFOLIO_STOCKS", response);
            })
        })
        
            
            
        this.$store.commit("SET_HIDE_BUTTON", false);
        this.$store.commit("SET_GAME_END_DATE", this.$route.params.id)
        if(today >= this.$store.state.game.endDate) {
            YahooAPIService.endGame(this.$route.params.id).then(response => {
                this.highlightWinner();
                return response;
            })
            // this.$store.commit('SET_HIDE_BUTTON', true);
            this.highlightWinner();
        }
    }
}

</script>

<style>

    .winner{
        background-color: #E2B842;
        color:  #012B39;
    }

    .game-details-container {
        display: grid;
        grid-template-areas:
        "nav . details details details details ."
        "nav . stocks stocks stocks stocks ."
        "nav . . buy sell . ."
        "nav . leaderboard leaderboard leaderboard leaderboard .";
        
        grid-template-columns: 1fr 1fr 1fr 1fr 1fr 1fr 1fr;
    }
    .game-container {
        grid-area: details;
        margin-top: 50px;
    }
    .myStonks{
        grid-area: stocks;
        margin-bottom: 20px;
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
    margin-top: 100px;
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