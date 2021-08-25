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
      <v-card>
        <v-card-title>
          <v-btn
            class="mx-2 text--white"
            color="teal"
            dark
            @click="dialog = true"
            ><v-icon left>
              mdi-plus
            </v-icon>
            New Authority
          </v-btn>
        </v-card-title>
        <v-simple-table>
          <thead class="grey lighten-4">
            <tr>
              <th class="text-center">
                ID
              </th>
              <th class="text-center">
                Name
              </th>
              <th class="text-center">
                Action
              </th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="authority in authorities" :key="authority.id">
              <td>{{ authority.id }}</td>
              <td>{{ authority.authority }}</td>
              <td>
                <v-btn
                  class="mx-2 text--white"
                  color="teal"
                  @click="deleteAuthority(authority.id)"
                >
                  <v-icon left>
                    mdi-delete-outline
                  </v-icon>
                  Delete
                </v-btn>
              </td>
            </tr>
          </tbody>
        </v-simple-table>
      </v-card>
      <v-dialog v-model="dialog" hide-overlay persistent width="300">
        <v-card
          ><v-card-title>
            <span class="headline"> New Authority </span>
          </v-card-title>
          <v-card-text>
            <v-container>
              <v-form ref="form" lazy-validation v-model="valid">
                <v-select
                  v-model="authority"
                  :items="['ROLE_SUPERVISOR', 'ROLE_DIRECTOR', 'ROLE_EMPLOYEE']"
                  :rules="[(v) => !!v || 'Item is required']"
                  label="Authority Name"
                  required
                ></v-select>
              </v-form>
            </v-container>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken-1" text @click="dialog = false">
              Close
            </v-btn>
            <v-btn color="blue darken-1" text @click="newAuthority">
              Save
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-container>
  </div>
</template>
<script>
import AuthorityService from "@/services/authority.service";

export default {
  name: "Authorities",
  data() {
    return {
      valid: true,
      message: "",
      authority: "",
      authorities: [],
      dialog: false,
    };
  },

  mounted: function() {
    AuthorityService.getAuthorities().then(
      (res) => {
        this.authorities = res;
      },
      (error) => {
        this.message = error.response.data.message;
      }
    );
  },
  methods: {
    deleteAuthority(id) {
      AuthorityService.deleteAuthority(id).then(
        () => {
          this.authorities = this.authorities.filter(
            (authority) => authority.id != id
          );
          this.message1="Authority deleted successfully !"
        },
        (error) => {
          this.message = error.response.data.message;
        }
      );
    },
    newAuthority() {
      if (!this.$refs.form.validate()) return false;
      this.dialog = false;
      AuthorityService.newAuthority(this.authority).then(
        (res) => {
          this.authorities.push(res);
        },
        (error) => {
          this.message = error.response.data.message;
        }
      );
    },
  },
};
</script>
