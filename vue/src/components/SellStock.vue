<template>
  <div class="sell-stock-container">
        <button class="sell-btn" v-on:click="showForm = !showForm">Sell Stock</button>

        <form v-show="showForm == true">
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
                portfolioId: 4,
                stockSymbol: '',
                quantity: ''
            },
            errorMessage: '',
            showForm: false
        }
    },

    methods: {
        sellStock() {
            yahooAPIService.getStockBySymbol(this.symbol).then(response => {
                this.stock = response;
                this.errorMessage = '';
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