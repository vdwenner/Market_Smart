<template>
    <div class="buy-stock-container">
        <button class="btn btn-lg btn-primary btn-block" v-on:click="showBuy = !showBuy" v-show="showBuy == false" v-if="this.$store.state.hideButtons == false">Buy Stock</button>

        <form v-show="showBuy == true" v-on:submit.prevent>
            <label for="symbol">Stock Symbol: </label>
            <input type="text" id="symbol" name="symbol" v-model="symbol" placeholder="Enter Stock Symbol">
            <label for="quantity" >Quantity: </label>
            <input type="number"
                min="1" id="quantity" name="quantity" placeholder="Enter Quantity"
                v-model="transaction.quantity">
            <button class="btn btn-lg btn-primary btn-block" v-on:click="buyStock">Buy Stock</button>
            <button class="btn btn-lg btn-primary btn-block" v-on:click="showBuy = !showBuy">Cancel</button>
        </form>
    </div>
</template>

<script>
import gameService from '../services/GameService';
import yahooAPIService from '../services/YahooAPIService';
import authService from '../services/AuthService';


export default {
    name: 'buy-stock',
    props: ['buyForm'],
    data() {
        return {
            stock: {},
            symbol: '',
            transaction: {
                transactionId: '',
                transactionType: 1,
                price: '',
                portfolioId: '',
                stockSymbol: '',
                quantity: ''
            },
            errorMessage: '',
            showBuy: false
        }
    },
    methods: {
        buyStock() {
            this.getPortfolio();
            yahooAPIService.getStockBySymbol(this.symbol.toUpperCase()).then( response => {
                this.stock = response;
                this.errorMessage = '';
                this.transaction.transactionType = 1;
                this.transaction.price = response.quote.price;
                this.transaction.stockSymbol = response.symbol;
                gameService.buyStock(this.transaction, this.$route.params.id, this.symbol.toUpperCase()).then( response => {
                    if(response.status == 200) {
                        
                         authService.getPortfolioByUserIdAndGameId(this.$store.state.user.id, this.$store.state.gameId).then(response=>{
                            gameService.getPortfolioStocksByPortfolioId(response.id).then(response =>{
                                this.$store.commit("SET_PORTFOLIO_STOCKS", response);

                                gameService.viewLeaderboard(this.$store.state.gameId).then( response => {
                                    this.$store.commit("SET_PORTFOLIOS", response);
                                })
                            })
                        })

                        this.resetForm();
                        this.errorMessage = '';
                    }
                })
            })
            
            
                 yahooAPIService.updateLeaderboard(this.$store.state.gameId).then(response => {
                 return response;
            })


           

        },

        getPortfolio() {
            authService.getPortfolioByUserIdAndGameId(this.$store.state.user.id, this.$route.params.id).then( response => {
            this.transaction.userId = this.$store.state.user.id;
            this.transaction.portfolioId = response.id;
        })
        },

         resetForm() {
            this.symbol = '';
            this.transaction.quantity = '';
            this.stock = {};
            this.showform = false;
        }
    },
    created() {
        
    }

    
}


</script>

<style>
    button.buy-btn {
        max-height: 19px
    }

    #symbol, #quantity {
        width: 150px;
        padding: 0px;
    }

    #submit-buy {
        margin-bottom: 5px;
    }
</style>