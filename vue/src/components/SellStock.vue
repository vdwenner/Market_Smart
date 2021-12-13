<template>
   <div class="sell-stock-container">
        <button class="sell-btn" v-on:click="showSell = !showSell">Sell Stock</button>

        <form v-show="showSell == true" v-on:submit.prevent>
            <label for="symbol">Stock Symbol: </label>
            <input type="text" id="symbol" name="symbol" v-model="symbol" placeholder="Enter Stock Symbol">
            <label for="quantity" >Quantity: </label>
            <input type="number"
                min="1" id="quantity" name="quantity" placeholder="Enter Quantity"
                v-model="transaction.quantity">
            <input type="submit" name="submit" v-on:click="sellStock">
        </form>
    </div>
</template>

<script>
import gameService from '../services/GameService';
import yahooAPIService from '../services/YahooAPIService';
import authService from '../services/AuthService';

export default {


    name: 'sell-stock',
    props: ['sellForm'],
    data() {
        return {
            stock: {},
            symbol: '',
            transaction: {
                transactionId: '',
                transactionType: 2,
                price: '',
                portfolioId: '',
                stockSymbol: '',
                quantity: ''
            },
            errorMessage: '',
            showSell: false
        }
    },
    methods: {
        sellStock() {
            this.getPortfolio();
            yahooAPIService.getStockBySymbol(this.symbol).then( response => {
                this.stock = response;
                this.errorMessage = '';
                this.transaction.transactionType = 2;
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
    }
}
</script>

<style>

</style>