<template>
  <div>
    <v-alert
      dense
      border="left"
      type="warning"
      color="red lighten-2"
      dark
      v-if="message"
    >
      <v-row align="center">
        <v-col class="grow">
          {{ message }}
        </v-col>
        <v-col class="shrink">
          <v-btn @click="message = ''" class="mx-2" x-small fab color="white"
            ><v-icon color="red">
              mdi-close-outline
            </v-icon>
          </v-btn>
        </v-col>
      </v-row>
    </v-alert>
    <v-alert
      dense
      border="left"
      type="success"
      color="green lighten-2"
      dark
      v-if="message1"
    >
      <v-row align="center">
        <v-col class="grow">
          {{ message1 }}
        </v-col>
        <v-col class="shrink">
          <v-btn @click="message1 = ''" class="mx-2" x-small fab color="white"
            ><v-icon color="green">
              mdi-close-outline
            </v-icon>
          </v-btn>
        </v-col>
      </v-row>
    </v-alert>
    <v-container>
      <br />
      <br />
      <v-tabs v-model="tab" background-color="transparent" color="teal" grow>
        <v-tab>
          Waiting
        </v-tab>
        <v-tab>
          Approved
        </v-tab>
      </v-tabs>
      <v-tabs-items v-model="tab">
        <v-tab-item>
          <v-card>
            <v-card-title>
              <v-text-field
                v-model="search"
                append-icon="mdi-magnify"
                label="Search"
                single-line
                hide-details
              ></v-text-field>
            </v-card-title>
            <v-data-table
              :items="nonApproved"
              :headers="headers"
              :items-per-page="5"
              :search="search"
            >
              <template v-slot:[`item.actions`]="{ item }">
                <v-btn
                  class="ma-2"
                  outlined
                  small
                  color="teal"
                  @click="approveTravelRequest(item)"
                >
                  Approve
                </v-btn>
              </template>
            </v-data-table>
          </v-card>
        </v-tab-item>
        <v-tab-item>
          <v-card>
            <v-card-title>
              <v-text-field
                v-model="search"
                append-icon="mdi-magnify"
                label="Search"
                single-line
                hide-details
              ></v-text-field>
            </v-card-title>
            <v-data-table
              :items="approved"
              :headers="headers"
              :items-per-page="5"
              :search="search"
            >
              <template v-slot:[`item.actions`]="{ item }">
                <v-btn
                  class="ma-2"
                  outlined
                  small
                  color="teal"
                  @click="deleteTravelRequest(item.id)"
                >
                  Delete
                </v-btn>
              </template>
            </v-data-table>
          </v-card>
        </v-tab-item>
      </v-tabs-items>
    </v-container>
  </div>
</template>
<script>
import SupervisorService from "@/services/supervisor.service";
import TravelRequestService from "@/services/travelRequest.service";

export default {
  name: "TravelRequestsByDepartment",
  data() {
    return {
      search: "",
      message: "",
      message1: "",
      tab: null,
      authUser: JSON.parse(localStorage.getItem("user")),
      travelRequests: [],
      headers: [
        { text: "Employee Username", value: "employee.user.username" },
        { text: "Employee Fullname", value: "employee.user.fullName" },
        { text: "Address", value: "address" },
        { text: "Phone", value: "phone" },
        { text: "Duration", value: "permitDuration" },
        { text: "Date", value: "permitDate" },
        { text: "From work place", value: "fromWorkPlace" },
        { text: "Actions", value: "actions", sortable: false },
      ],
    };
  },
  computed: {
    nonApproved: function() {
      return this.travelRequests.filter((tr) => tr.approved == false);
    },
    approved: function() {
      return this.travelRequests.filter((tr) => tr.approved == true);
    },
  },
  mounted: function() {
    SupervisorService.getSupervisor(this.authUser.id).then(
      (res) => {
        TravelRequestService.getTravelRequestsByDepartment(
          res.department.name
        ).then(
          (res) => {
            this.travelRequests = res;
          },
          (error) => {
            this.message = error.response.data.message;
          }
        );
      },
      (error) => {
        this.message = error.response.data.message;
      }
    );
  },
  methods: {
    approveTravelRequest(travelRequest) {
      console.log(travelRequest);
      travelRequest.approved = true;
      TravelRequestService.editTravelRequest(
        travelRequest.id,
        travelRequest
      ).then(
        (res) => {
          this.message1 = "Travel request approved !";
          this.nonApproved = this.nonApproved.filter((tr) => tr.id != res.id);
        },
        (error) => {
          this.message = error.response.data.message;
        }
      );
    },
    deleteTravelRequest(id) {
      TravelRequestService.deleteTravelRequest(id).then(
        (res) => {
          this.nonApproved = this.nonApproved.filter((tr) => tr.id != res);
          this.message1 = "Travel request deleted !";
        },
        (error) => {
          this.message = error.response.data.message;
        }
      );
    },
  },
};
</script>
