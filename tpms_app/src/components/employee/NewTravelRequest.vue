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
      <v-card
        ><v-card-title class="teal lighten-1  white--text">
          <span class="headline"> New Travel Request </span>
        </v-card-title>
        <v-container>
            <v-form ref="form" lazy-validation v-model="valid" @submit.prevent="newTravelRequest">
        <v-card-text>
              <v-row>
                <v-col>
                  <v-menu
                    v-model="menu2"
                    :close-on-content-click="false"
                    :nudge-right="40"
                    transition="scale-transition"
                    offset-y
                    min-width="290px"
                  >
                    <template v-slot:activator="{ on, attrs }">
                      <v-text-field
                        v-model="travelRequest.permitDate"
                        label="Date"
                        prepend-icon="mdi-calendar"
                        readonly
                        v-bind="attrs"
                        v-on="on"
                      ></v-text-field>
                    </template>
                    <v-date-picker color="teal"
                      v-model="travelRequest.permitDate"
                      @input="menu2 = false"
                    ></v-date-picker>
                  </v-menu>
                </v-col>
                <v-col>
                  <v-select
                    v-model="travelRequest.permitDuration"
                    :items="['DAILY', 'WEEKLY', 'EXTRAORDINARY']"
                    :rules="[(v) => !!v || 'Item is required']"
                    label="Duration"
                    required
                  ></v-select>
                </v-col>
              </v-row>
              <v-row>
                <v-col>
                  <v-text-field
                    label="Phone"
                    :rules="phoneRules"
                    v-model="travelRequest.phone"
                    type="text"
                    required
                  ></v-text-field>
                </v-col>
                <v-col >
                  <v-text-field
                    label="Address"
                    v-model="travelRequest.address"
                    :rules="addressRules"
                    type="text"
                    required
                  ></v-text-field>
                </v-col>
              </v-row>
              <v-row>
                <v-col>
                  <v-checkbox
                    v-model="travelRequest.fromWorkPlace"
                    label="Is from workplace"
                  ></v-checkbox>
                </v-col>
              </v-row>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="teal darken-1" text type="submit">
            create
          </v-btn>
        </v-card-actions>
        </v-form>
        </v-container>
      </v-card>
    </v-container>
  </div>
</template>
<script>
import TravelRequestService from "@/services/travelRequest.service";

export default {
  name: "NewTravelRequest",
  data() {
    return {
      valid: true,
      phoneRules: [
        (v) => /^\d+$/.test(v)||'Phone must have only numbers',
        (v) => (v && v.length == 10) || 'Phone must have 10 numbers'
      ],
      addressRules: [(v) => !!v || "Address is required"],
      message: "",
      message1: "",
      travelRequest: {
        permitDate:new Date().toISOString().substr(0, 10)
      },
      menu2: false,
    };
  },
  methods: {
    newTravelRequest() {
      if (!this.$refs.form.validate()) return false;
      const authUser = JSON.parse(localStorage.getItem("user"));
      this.travelRequest.id=authUser.id;
      TravelRequestService.newTravelRequest(this.travelRequest).then(
        () => {
          this.message1 = "Travel request created !";
        },
        (error) => {
          this.message = error.response.data.message;
        }
      );
    },
  },
};
</script>
