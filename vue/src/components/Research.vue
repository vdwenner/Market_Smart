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

  <div class="stock-info-data">
    <div class="stock-info-data-table" v-if="symbol != ''">
    <table>
      <tbody>
        <tr>
          <td class="research-td"><span class="research-span">Day Low</span><span class="research-span">${{stock.quote.dayLow.toFixed(2)}}</span></td>
          <td class="research-td"><span class="research-span">Day High</span><span class="research-span">${{stock.quote.dayHigh.toFixed(2)}}</span></td>
        </tr>
        <tr>
          <td class="research-td"><span class="research-span">Year Low</span><span class="research-span">${{stock.quote.yearLow.toFixed(2)}}</span></td>
          <td class="research-td"><span class="research-span">Year High</span><span class="research-span">${{stock.quote.yearHigh.toFixed(2)}}</span></td>
        </tr>
        <tr>
         <td class="research-td"><span class="research-span">Volume</span><span class="research-span">{{stock.quote.volume.toFixed(2)}}</span></td>
          <td class="research-td"><span class="research-span">Average Volume</span><span class="research-span">{{stock.quote.avgVolume.toFixed(2)}}</span></td>
        </tr>
        <tr>
          <td class="research-td"><span class="research-span">Change From Year High in Percent</span><span class="research-span">{{stock.quote.changeFromYearLowInPercent.toFixed(2)}}%</span></td>
          <td class="research-td"><span class="research-span">Change From Year Low in Percent</span><span class="research-span">{{stock.quote.changeFromYearHighInPercent.toFixed(2)}}%</span></td>
        </tr>
        <tr>
          <td class="research-td"><span class="research-span">Change From Average 200</span><span class="research-span">{{stock.quote.changeFromAvg200.toFixed(2)}}</span></td>
          <td class="research-td"><span class="research-span">Change From Average 200 in Percent</span><span class="research-span">{{stock.quote.changeFromAvg200InPercent.toFixed(2)}}%</span></td>
        </tr>
        <tr>
          <td class="research-td"><span class="research-span">Change From Average 50</span><span class="research-span">{{stock.quote.changeFromAvg50.toFixed(2)}}</span></td>
          <td class="research-td"><span class="research-span">Change Form Average 50 in Percent</span><span class="research-span">{{stock.quote.changeFromAvg50InPercent.toFixed(2)}}%</span></td>
        </tr>
        <tr>
          <td class="research-td"><span class="research-span">Market Cap</span><span class="research-span">{{stock.stats.marketCap.toFixed(2)}}</span></td>
          <td class="research-td"><span class="research-span">Shares Outstanding</span><span class="research-span">{{stock.stats.sharesOutstanding.toFixed(2)}}</span></td>
        </tr>
        <tr>
          <td class="research-td"><span class="research-span">Price Book</span><span class="research-span">${{stock.stats.priceBook.toFixed(2)}}</span></td>
          <td class="research-td"><span class="research-span">Book Values Per Share</span><span class="research-span">${{stock.stats.bookValuePerShare.toFixed(2)}}</span></td>
        </tr>
      </tbody>
    </table>
    </div>
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

.research-td{

  display: flex;
  justify-content: space-between;

  width: 100%;

  text-align: left;
  color: #fff;
  font-weight: 400;
  padding: 0.65em 1em;
  align-content: center;
  
}

.stock-info-data{
  display: flex;
  justify-content: center;

  
}
.stock-info-data-table{
  display: flex;
}

.research-span{
  width: 175px;
  margin-right: 20px;
}



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