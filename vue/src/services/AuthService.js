import axios from 'axios';

export default {

  login(user) {
    return axios.post('/login', user)
  },

  register(user) {
    return axios.post('/register', user)
  },
  getIdByUsername(username){
    return axios.get(`/get/userid/by/${username}`).then((response) =>{
      return response.data;
    })
  }

}
