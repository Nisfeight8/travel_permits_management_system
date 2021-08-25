import axios from "axios";
import authHeader from "./auth-header";
const API_URL = process.env.VUE_APP_URL+"departments/";

class DepartmentService {
  getDepartments() {
    return axios
      .get(API_URL , { headers: authHeader() })
      .then((response) => {
        return response.data;
      });
  }

  newDepartment(department) {
    console.log(department);
    return axios
      .post(
        API_URL ,department,
        { headers: authHeader() }
      )
      .then((response) => {
        return response.data;
      });
  }
  deleteDepartment(id) {
    return axios
      .delete(API_URL + id, { headers: authHeader() })
      .then((response) => {
        return response.data;
      });
  }
  getDepartment(id) {
    return axios
      .get(API_URL + id, { headers: authHeader() })
      .then((response) => {
        return response.data;
      });
  }
  editDepartment(id,department) {
    return axios
      .put(API_URL+id , department, { headers: authHeader() })
      .then((response) => {
        return response.data;
      });
  }
}

export default new DepartmentService();