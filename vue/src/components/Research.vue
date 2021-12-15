<template>
<div class="research-container">

  <div class="stock">
    
      <form class="search-form" v-on:submit.prevent>
      <input type="text" name="Stock" id="stock" v-model="symbol">
      <button class="btn" v-on:click="showStockInfo(symbol)">Search</button>
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
            <td>${{stock.quote.price.toFixed(2)}}</td>
            <td>${{stock.quote.open.toFixed(2)}}</td>
            <td>${{stock.quote.previousClose.toFixed(2)}}</td>
            <td>{{stock.quote.changeInPercent.toFixed(2)}}%</td>
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
      stock : {},
      trendingResponse: {}
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

  created() {
      yahooAPIService.listTrendingStocks().then(response => {
      this.trendingResponse = response;
    })
  
  },

  
  resetSymbol() {
    this.symbol = '';
  }

}
</script>

<style>

/* input[type=text] {
      font-family: 'Inter';
      width: 100%;
      padding: 12px 20px;
      margin: 8px 0;
      display: inline-block;
      border: 1px solid #ccc;
      border-radius: 4px;
      box-sizing: border-box;
    } */

.errorMessage{
  color: red;
  text-align: center;
}

.research-container{
    display: grid;
    grid-template-columns:1fr;
    /* grid-template-rows: 1fr 2fr 1fr; */
    grid-template-areas: 
    "stock"
    "table";
}

.search-form{
  display: inline-block;
  text-align: center;
}


input#stock{
  /* width: 100%; */
  display: inline-block;
  /* justify-content: flex-start; */
}
.stock{
  grid-area: stock;
  display: flex;
  justify-content: center;
  
}

.stock-info-table {
  grid-area: table;
  display: flex;
  justify-content: center;
  
}
</style>