import axios from "axios";
import authHeader from "./auth-header";
const API_URL = process.env.VUE_APP_URL+"employees/";

class EmployeeService {
  getEmployees() {
    return axios
      .get(API_URL , { headers: authHeader() })
      .then((response) => {
        return response.data;
      });
  }
  getEmployeesByDepartment(name) {
    return axios
      .get(process.env.VUE_APP_URL+"employees?department="+name , { headers: authHeader() })
      .then((response) => {
        return response.data;
      });
  }

  newEmployee(employee) {
    console.log(employee);
    return axios
      .post(
        API_URL ,employee,
        { headers: authHeader() }
      )
      .then((response) => {
        return response.data;
      });
  }
  deleteEmployee(id) {
    return axios
      .delete(API_URL + id, { headers: authHeader() })
      .then((response) => {
        return response.data;
      });
  }
  getEmployee(id) {
    return axios
      .get(API_URL + id, { headers: authHeader() })
      .then((response) => {
        return response.data;
      });
  }
  editEmployee(id,employee) {
    console.log(id);
    return axios
      .put(API_URL+id , employee, { headers: authHeader() })
      .then((response) => {
        return response.data;
      });
  }
}
export default new EmployeeService();
