<template>
  <div>
    <tr>
      <td>{{this.portfolioStocks.stockSymbol}}</td>
      <td>{{this.portfolioStocks.quantity}}</td>
      <td >{{this.portfolioStocks.averagePrice}}</td>
      <td>{{this.portfolioStocks.averagePrice * this.portfolioStocks.quantity}}</td>
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