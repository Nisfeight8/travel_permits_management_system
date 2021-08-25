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
    <br />
    <br />
    <v-container fluid>
      <v-row>
        <v-col cols="4">
          <h4>Select the date you want to see travel requests:</h4>
          <v-date-picker
            v-model="date"
            width="290"
            color="teal"
            class="mt-4"
            @click:date="getTravelRequests"
          ></v-date-picker>
        </v-col>
        <v-col cols="12" sm="12" md="8">
          <v-card>
            <v-overlay :absolute="absolute" :value="overlay">
              <v-progress-circular
                indeterminate
                size="64"
              ></v-progress-circular>
            </v-overlay>

            <v-simple-table height="400px">
              <thead class="grey lighten-4">
                <tr>
                  <th class="text-center">
                    Employee Fullname
                  </th>
                  <th class="text-center">
                    Department
                  </th>
                  <th class="text-center">
                    Employee Phone
                  </th>
                  <th class="text-center">
                    Employee Address
                  </th>
                  <th class="text-center">
                    Request Duration
                  </th>
                  <th class="text-center">
                    Is from work place
                  </th>
                  <th class="text-center">
                    Request Date
                  </th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="tr in travelRequests" :key="tr.id">
                  <td>{{ tr.employee.user.fullName }}</td>
                  <td>{{ tr.employee.department.name }}</td>
                  <td>{{ tr.phone }}</td>
                  <td>{{ tr.address }}</td>
                  <td>{{ tr.permitDuration }}</td>
                  <td>{{ tr.fromWorkPlace }}</td>
                  <td>{{ tr.permitDate }}</td>
                </tr>
              </tbody>
            </v-simple-table>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>
<script>
import TravelRequestService from "@/services/travelRequest.service";

export default {
  name: "TravelRequests",
  data() {
    return {
      absolute: true,
      overlay: false,
      message: "",
      travelRequests: [],
      date: new Date().toISOString().substr(0, 10),
    };
  },
  methods: {
    getTravelRequests() {
      this.overlay = true;
      TravelRequestService.getTravelRequestsByDate(this.date).then(
        (res) => {
          this.travelRequests = res;
          this.overlay = false;
        },
        (error) => {
          this.message = error.response.data.message;
          this.overlay = false;
        }
      );
    },
  },
};
</script>
