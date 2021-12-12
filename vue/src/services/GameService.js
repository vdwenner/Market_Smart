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
    },

    getGameByGameId(Id) {
        return axios.get(`/games/Id/${Id}`).then((response) => {
            return response.data;
        })
    },
    approvePendingInvite(invite){
        return axios.put('/games/invite/accept', invite).then((response)=>{
            return response.data;
        })
    },
    rejectPendingInvite(invite){
        return axios.put('/games/invite/reject', invite).then((response) =>{
            return response.data;
        })

    },

    viewLeaderboard(gameId) {
        return axios.get(`/user/games/${gameId}/leaderboard`, gameId).then((response) => {
            return response.data;
        })
    }

}