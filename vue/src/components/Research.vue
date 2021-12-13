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
    <table class="table-config">
      <th>
        <td class="research-data">Company Name:     {{stock.name}}</td>
        <td class="research-data">Current Price:   ${{stock.quote.price}}</td>
        <td class="research-data">Today Open:      ${{stock.quote.open}}</td>
        <td class="research-data">Previous Close:  ${{stock.quote.previousClose}}</td>
        <td class="research-data">Percent Change:   {{stock.quote.changeInPercent}}%</td>
      </th>
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

/* table.researchData {
  background: #012B39;
  border-radius: 0.25em;
  border-collapse: collapse;
  margin: 1em;
  grid-area: table;
  height: 500px;
  overflow-y: scroll;
}

thead {
    display: flex;
    justify-content: space-between;
    width: 100%;
}

th {
  border-bottom: 1px solid #364043;
  color: #E2B842;
  font-size: 0.85em;
  font-weight: 600;
  padding: 0.5em 1em;
  text-align: center;
  width: 100px;
}
td.research-data {
  border-bottom: 1px solid #364043;
  color: #E2B842;
  font-size: 0.85em;
  font-weight: 600;
  padding: 0.5em 1em;
  text-align: center;
  width: 100px;
}
.disabled td {
  color: #4F5F64;
}
tbody.pending-invites-data tr {
  transition: background 0.25s ease;
}
tbody.pending-invites-data tr:hover {
  background: #014055;
} */
</style>