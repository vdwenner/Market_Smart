<template>
<div>
  <div class="stock">
    <h1>Search For Stocks</h1>
      <form v-on:submit.prevent>
      <input type="text" name="Stock" id="stock" v-model="symbol">
      <input type="submit" value="Search" v-on:click="showStockInfo(symbol)">
      <div class="errorMessage" v-if="errorMessage!=''">{{errorMessage}}</div>
    </form>
    
  </div>
  <div class = "stock-info" v-if="symbol != ''">
    <table>
      <tr>
      <th >
        <td>Name</td>
        <td>Currency</td>
        <td>Current Price</td>
        <td>Daily Open</td>
        <td>Previous Close</td>
        <td>Change in Percent</td>
      </th>
      </tr>
      <tr class="research-data">
        <td class="stock-name">{{stock.name}}</td>
        <td>{{stock.currency}}</td>
        <td>${{stock.quote.price}}</td>
        <td>${{stock.quote.open}}</td>
        <td>${{stock.quote.previousClose}}</td>
        <td>{{stock.quote.changeInPercent}}%</td>
      </tr>
    </table>
    <!-- <h2>{{stock.name}}</h2>
    <h3>{{stock.currency}}</h3>
    <h3>{{stock.quote.price}}</h3>
    <h3>{{stock.quote.open}}</h3>
    <h3>{{stock.quote.previousClose}}</h3>
    <h3>{{stock.quote.changeInPercent}}</h3> -->
  </div>
  </div>
</template>

<script>
import yahooAPIService from "../services/YahooAPIService";
export default {
  data() {
    return{
      errorMessage: '',
      symbol: '',
      stock : {}
    }
  },
  methods: {
    showStockInfo(symbol) {
      yahooAPIService.getStockBySymbol(symbol).then(response => {
        this.stock = response;
        this.resetSymbol;
        this.errorMessage = '';
      }).catch((error) => {
        
         if (error.response.status != 200) {
        this.errorMessage = "Stock Does Not Exist";
        this.resetSymbol;
         }})
      
      }
  },
  resetSymbol() {
    this.symbol = '';
  }

}
</script>

<style>
.errorMessage{
  color: red;
  text-align: center;
}
/* .stock-name{
  padding: 1px;
}
.research-data{
  padding: 1px;
  display: flex;
} */
</style>