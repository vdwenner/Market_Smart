import axios from 'axios'

export default {
    createGame(game) {
        return axios.post('/createGame', game)
    },

    viewGames() {
        return axios.get('/user/games').then((response)=>{
            return response.data;
    })}
}