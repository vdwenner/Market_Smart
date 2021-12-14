<template>
  <div>
    <tr>
      <td>{{this.portfolioStocks.stockSymbol}}</td>
      <td>{{this.portfolioStocks.quantity}}</td>
      <td >{{this.portfolioStocks.averagePrice.toFixed(2)}}</td>
      <td>{{(this.portfolioStocks.averagePrice * this.portfolioStocks.quantity).toFixed(2)}}</td>
    </tr>
</div>
</template>



<script>


export default {
props:["portfolioStocks"],
}
</script>

<style>

</style>