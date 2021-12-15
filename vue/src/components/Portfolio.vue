<template>
  <div>
    <tr>
      <td>{{this.portfolioStocks.stockSymbol}}</td>
      <td>{{this.portfolioStocks.quantity}}</td>
      <td>${{this.currentPrice.toFixed(2)}}</td>
      <td>${{this.portfolioStocks.averagePrice.toFixed(2)}}</td>
      <td>${{(this.portfolioStocks.averagePrice * this.portfolioStocks.quantity).toFixed(2)}}</td>
    </tr>
</div>
</template>



<script>
import yahooAPIService from '../services/YahooAPIService';

export default {
props:["portfolioStocks"],
data() {
  return {
    currentPrice: ''
  }

},
created() {
    yahooAPIService.getStockBySymbol(this.portfolioStocks.stockSymbol).then(response => {
      this.currentPrice = response.quote.price;
    })
  }
}


</script>

<style>

</style>