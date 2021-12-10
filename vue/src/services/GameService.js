import axios from 'axios'

export default {
    createGame(game) {
        return axios.post('/createGame', game)
    },

    viewGames() {
        return axios.get('/user/games').then((response)=>{
            return response.data;
    })},

    inviteToGame(inviteType){
    return axios.post('/games/invite/request',inviteType)
    },

    viewPendingGameInvites(){
        return axios.get('/games/invite/pending').then((response)=>{
            return response.data;
        })
    }

}