import axios from 'axios'

export default {
    getStockBySymbol(symbol) {
        return axios.get(`/stock/${symbol}`).then((response) => {
            return response.data;
        })
    },

    
}