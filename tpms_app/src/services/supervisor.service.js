import axios from "axios";
import authHeader from "./auth-header";
const API_URL = process.env.VUE_APP_URL+"supervisors/";

class SupervisorService {
  getSupervisors() {
    return axios
      .get(API_URL , { headers: authHeader() })
      .then((response) => {
        return response.data;
      });
  }

  newSupervisor(supervisor) {
    console.log(supervisor);
    return axios
      .post(
        API_URL ,supervisor,
        { headers: authHeader() }
      )
      .then((response) => {
        return response.data;
      });
  }
  deleteSupervisor(id) {
    return axios
      .delete(API_URL + id, { headers: authHeader() })
      .then((response) => {
        return response.data;
      });
  }
  getSupervisor(id) {
    return axios
      .get(API_URL + id, { headers: authHeader() })
      .then((response) => {
        return response.data;
      });
  }
  getSupervisorByDepartment(name) {
    return axios
      .get(API_URL +"department/"+ name, { headers: authHeader() })
      .then((response) => {
        return response.data;
      });
  }
  editSupervisor(id,supervisor) {
    return axios
      .put(API_URL+id , supervisor, { headers: authHeader() })
      .then((response) => {
        return response.data;
      });
  }
}
export default new SupervisorService();
