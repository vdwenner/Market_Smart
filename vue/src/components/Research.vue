<template>
<div class="research-container">

  <div class="stock">
    <h1>Search For Stocks</h1>
      <form v-on:submit.prevent>
      <input type="text" name="Stock" id="stock" v-model="symbol">
      <input type="submit" value="Search" v-on:click="showStockInfo(symbol)">
      <div class="errorMessage" v-if="errorMessage!=''">{{errorMessage}}</div>
    </form>
  </div>

  <div class="stock-info-table" v-if="symbol != ''">
    <table>
      <thead>
        <tr>
          <th>Company Name</th>
          <th>Current Price</th>
          <th>Today Open</th>
          <th>Previous Close</th>
          <th>Percent Change</th>
        </tr>
      </thead>
        <tbody>
          <tr>
            <td>{{stock.name}}</td>
            <td>${{stock.quote.price}}</td>
            <td>${{stock.quote.open}}</td>
            <td>${{stock.quote.previousClose}}</td>
            <td>{{stock.quote.changeInPercent}}%</td>
          </tr>
      </tbody>
    </table>
    
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

.research-container{
    display: grid;
    grid-template-columns:1fr 1fr 1fr;
    grid-template-rows: 1fr 2fr 1fr;
    grid-template-areas: 
    "stock stock stock"
    "table table table"
    ". . .";
}
.stock{
  grid-area: stock;
}

.stock-info-table {
  grid-area: table;
  text-align: center;
  align-content: center;
  justify-items: center;
}
</style>