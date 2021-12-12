<template>
    <div class="buy-stock-container">
        <button class="buy-btn" v-on:click="showForm = !showForm">Buy Stock</button>

        <form v-show="showForm == true">
            <label for="symbol">Stock Symbol: </label>
            <input type="text" id="symbol" name="symbol" v-model="symbol" placeholder="Enter Stock Symbol">
            <label for="quantity" >Quantity: </label>
            <input type="number"
                min="1" id="quantity" name="quantity" placeholder="Enter Quantity"
                v-model="transaction.quantity">
            <input type="submit" name="submit" v-on:click="buyStock">
        </form>
    </div>
</template>

<script>
import gameService from '../services/GameService';
import yahooAPIService from '../services/YahooAPIService';
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
                portfolioId: 4,
                stockSymbol: '',
                quantity: ''
            },
            errorMessage: '',
            showForm: false
        }
    },

    methods: {
        buyStock() {
            yahooAPIService.getStockBySymbol(this.symbol).then(response => {
                this.stock = response;
                this.errorMessage = '';
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
        resetForm() {
            this.symbol = '';
            this.stock = {};
            this.showform = false;
        }
    }
}


</script>

<style>
    #symbol, #quantity {
        width: 150px;
        padding: 0px;
    }
</style>