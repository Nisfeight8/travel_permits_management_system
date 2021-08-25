import axios from "axios";
import authHeader from "./auth-header";
const API_URL = process.env.VUE_APP_URL+"users/";

class UserService {
  getUsers() {
    return axios
      .get(API_URL , { headers: authHeader() })
      .then((response) => {
        return response.data;
      });
  }
  getSupervisors() {
    return axios
      .get( process.env.VUE_APP_URL+"users?authority=ROLE_SUPERVISOR" , { headers: authHeader() })
      .then((response) => {
        return response.data;
      });
  }

  getEmployees() {
    return axios
      .get( process.env.VUE_APP_URL+"users?authority=ROLE_EMPLOYEE" , { headers: authHeader() })
      .then((response) => {
        return response.data;
      });
  }

  newUser(user) {
    return axios
      .post(
        API_URL ,user,
        { headers: authHeader() }
      )
      .then((response) => {
        return response.data;
      });
  }
  deleteUser(id) {
    return axios
      .delete(API_URL + id, { headers: authHeader() })
      .then((response) => {
        return response.data;
      });
  }
  getUser(id) {
    return axios
      .get(API_URL + id, { headers: authHeader() })
      .then((response) => {
        return response.data;
      });
  }
  editUser(id,user) {
    return axios
      .put(API_URL+id , user, { headers: authHeader() })
      .then((response) => {
        return response.data;
      });
  }
  getCurrentUser() {
    return axios
      .get(API_URL + "whoami", { headers: authHeader() })
      .then((response) => {
        return response.data;
      });
  }
  changeDetails(user) {
    return axios
      .put(API_URL + "changeDetails",user ,{ headers: authHeader() })
      .then((response) => {
        return response.data;
      });
  }
  changePassword(passwordChanger) {
    return axios
      .post(API_URL + "changePassword",passwordChanger, { headers: authHeader() })
      .then((response) => {
        return response.data;
      });
  }
}

export default new UserService();
