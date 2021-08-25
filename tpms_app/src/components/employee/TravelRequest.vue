<template>
  <div>
    <v-alert
      dense
      border="left"
      type="warning"
      color="red lighten-2"
      dark
      v-if="message"
      dismissible
    >
      {{ message }}
    </v-alert>
    <v-container>
      <br />
      <br />
      <v-card class="mx-auto">
        <v-card-title class="teal lighten-1 white--text text-right">
          <v-icon class="white--text">mdi-ticket-percent</v-icon> My Travel
          Request
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col>
                <v-text-field
                  readonly
                  label="Date"
                  v-model="travelRequest.permitDate"
                ></v-text-field>
              </v-col>
              <v-col>
                <v-text-field
                  readonly
                  label="Duration"
                  v-model="travelRequest.permitDuration"
                ></v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col>
                <v-text-field
                  readonly
                  label="Phone"
                  v-model="travelRequest.phone"
                ></v-text-field>
              </v-col>
              <v-col>
                <v-text-field
                  readonly
                  label="Address"
                  v-model="travelRequest.address"
                ></v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col>
                <v-text-field
                  readonly
                  label="Is from work place"
                  v-model="travelRequest.fromWorkPlace"
                ></v-text-field>
              </v-col>
              <v-col>
                <v-text-field
                  readonly
                  label="Approved"
                  v-model="travelRequest.approved"
                ></v-text-field>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
      </v-card>
    </v-container>
  </div>
</template>
<script>
import TravelRequestService from "@/services/travelRequest.service";

export default {
  name: "TravelRequest",
  data() {
    return {
      travelRequest: {},
      message: "",
      authUser : JSON.parse(localStorage.getItem("user")),
    };
  },
  mounted: function() {

    TravelRequestService.getTravelRequest(this.authUser.id).then(
      (res) => {
        this.travelRequest = res;
      },
      (error) => {
        this.message = error.response.data.message;
      }
    );
  },
};
</script>
