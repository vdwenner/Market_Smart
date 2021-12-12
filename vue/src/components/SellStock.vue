<template>
  <div>1</div>
</template>

<script>
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
    }
}
</script>

<style>

</style>