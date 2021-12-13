<template>
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
</template>

<script>
import gameService from '../services/GameService';
import yahooAPIService from '../services/YahooAPIService';

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
                portfolioId: 3,
                stockSymbol: '',
                quantity: ''
            },
            errorMessage: '',
            showSell: false
        }
    },
    methods: {
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
    }
}
</script>

<style>

</style>