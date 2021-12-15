import axios from 'axios'

export default {
    getStockBySymbol(symbol) {
        return axios.get(`/stock/${symbol}`).then((response) => {
            return response.data;
        })
    },

    updateLeaderboard(gameId) {
        return axios.put(`/update/leaderboard/${gameId}`).then((response)=> {
            return response.data;
        })
    },

    listTrendingStocks(){
        return axios.get("/trending").then((response) => {
            return response.data;
        })
    },

    endGame(gameId) {
        return axios.put(`/${gameId}/endgame`).then((response) => {
            return response.data;
        })
    }

    
}