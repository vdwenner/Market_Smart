<template>
    <div>1</div>
</template>

<script>

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
                portfolioId: '',
                stockSymbol: '',
                quantity: ''
            },
            errorMessage: '',
            showForm: false
        }
    },

    
}


</script>

<style>
    #symbol, #quantity {
        width: 150px;
        padding: 0px;
    }
</style>