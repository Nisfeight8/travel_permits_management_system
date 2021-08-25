import axios from "axios";
import authHeader from "./auth-header";
const API_URL = process.env.VUE_APP_URL+"authorities/";

class AuthorityService {
  getAuthorities() {
    return axios
      .get(API_URL , { headers: authHeader() })
      .then((response) => {
        return response.data;
      });
  }

  newAuthority(authority) {
    return axios
      .post(
        API_URL ,
        {
          name: authority,
        },
        { headers: authHeader() }
      )
      .then((response) => {
        return response.data;
      });
  }
  deleteAuthority(id) {
    return axios
      .delete(API_URL + id, { headers: authHeader() })
      .then((response) => {
        return response.data;
      });
  }
  getAuthority(id) {
    return axios
      .get(API_URL + id, { headers: authHeader() })
      .then((response) => {
        return response.data;
      });
  }
}

export default new AuthorityService();