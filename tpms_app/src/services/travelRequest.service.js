import axios from "axios";
import authHeader from "./auth-header";
const API_URL = process.env.VUE_APP_URL+"travelRequests/";

class TravelRequestService {
  getTravelRequests() {
    return axios
      .get(API_URL , { headers: authHeader() })
      .then((response) => {
        return response.data;
      });
  }
  getTravelRequestsByDepartment(name) {
    return axios
      .get(process.env.VUE_APP_URL+"travelRequests?department="+name , { headers: authHeader() })
      .then((response) => {
        return response.data;
      });
  }

  getTravelRequestsByDate(date) {
    return axios
      .get(process.env.VUE_APP_URL+"travelRequests?date="+date , { headers: authHeader() })
      .then((response) => {
        return response.data;
      });
  }

  newTravelRequest(travelRequest) {
    return axios
      .post(
        API_URL ,travelRequest,
        { headers: authHeader() }
      )
      .then((response) => {
        return response.data;
      });
  }
  deleteTravelRequest(id) {
    return axios
      .delete(API_URL + id, { headers: authHeader() })
      .then((response) => {
        return response.data;
      });
  }
  getTravelRequest(id) {
    return axios
      .get(API_URL + id, { headers: authHeader() })
      .then((response) => {
        return response.data;
      });
  }
  editTravelRequest(id,travelRequest) {
    return axios
      .put(API_URL+id , travelRequest, { headers: authHeader() })
      .then((response) => {
        return response.data;
      });
  }
}
export default new TravelRequestService();