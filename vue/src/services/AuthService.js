import axios from 'axios';

export default {

  login(user) {
    return axios.post('/login', user)
  },

  register(user) {
    return axios.post('/register', user)
  },
  getGamerByUsername(username){
    return axios.get(`/gamer/${username}`).then( response =>{
      return response.data;
    })
  },

  getUsernameById(id) {
    return axios.get(`/user/username/${id}`).then( response => {
      return response.data;
    })
  },

  getPortfolioByUserIdAndGameId(userId, gameId) {
      return axios.get(`/user/games/${userId}/${gameId}/portfolio`).then(response => {
        return response.data;
      })
  }

}
