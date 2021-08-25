import axios from "axios";

const API_URL = process.env.VUE_APP_URL+"auth/";

class AuthService {
  login(user) {
    return axios
      .post(API_URL + "signin", {
        username: user.username,
        password: user.password,
      })
      .then((response) => {
        localStorage.setItem('user', JSON.stringify(response.data));
        return response.data;
      });
  }

  logout() {
    localStorage.removeItem("user");
  }
}

export default new AuthService();
