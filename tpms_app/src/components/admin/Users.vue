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
      <v-card>
        <v-card-title>
          <v-btn
            class="mx-2 text--white"
            color="teal"
            dark
            @click="dialog = true"
            ><v-icon left>
              mdi-account-plus
            </v-icon>
            New User
          </v-btn>
          <v-text-field
            v-model="search"
            append-icon="mdi-magnify"
            label="Search"
            single-line
            hide-details
          ></v-text-field>
        </v-card-title>
        <v-data-table
          :items="users"
          :headers="headers"
          :items-per-page="5"
          :search="search"
        >
          <template v-slot:[`item.actions`]="{ item }">
            <v-btn
              class="ma-2"
              fab
              outlined
              small
              color="teal"
              @click="deleteUser(item.id)"
            >
              <v-icon>
                mdi-account-minus
              </v-icon>
            </v-btn>
            <v-btn
              class="ma-2"
              fab
              outlined
              small
              color="teal"
              @click="getUser(item.id)"
            >
              <v-icon>
                mdi-account-edit
              </v-icon>
            </v-btn>
          </template>
        </v-data-table>
      </v-card>
      <br />
      <br />
      <v-dialog v-model="dialog" hide-overlay persistent max-width="600px">
        <v-card
          ><v-card-title>
            <span class="headline"> New User </span>
          </v-card-title>
          <v-form
            ref="form"
            lazy-validation
            @submit.prevent="newUser"
            v-model="valid"
          >
            <v-card-text>
              <v-container>
                <v-row>
                  <v-col cols="12" sm="6">
                    <v-text-field
                      label="Username*"
                      v-model="user.username"
                      type="text"
                      :rules="usernameRules"
                      required
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6">
                    <v-text-field
                      label="Password*"
                      v-model="user.password"
                      :rules="passwordRules"
                      type="password"
                      required
                    ></v-text-field>
                  </v-col>
                </v-row>
                <v-col cols="12">
                  <v-select
                    return-object
                    v-model="user.authority"
                    :items="authorities"
                    item-text="authority"
                    :rules="[(v) => !!v || 'Item is required']"
                    label="Authority Name"
                    required
                  ></v-select>
                </v-col>
                <v-text-field
                  label="FullName*"
                  type="text"
                  v-model="user.fullName"
                  :rules="fullnameRules"
                  required
                ></v-text-field>
              </v-container>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue darken-1" text @click="dialog = false">
                Close
              </v-btn>
              <v-btn color="blue darken-1" text type="submit">
                Save
              </v-btn>
            </v-card-actions>
          </v-form>
        </v-card>
      </v-dialog>
      <v-dialog v-model="dialog2" hide-overlay persistent max-width="600px">
        <v-card
          ><v-card-title>
            <span class="headline"> Edit User </span>
          </v-card-title>
          <v-form ref="form2" lazy-validation v-model="valid2" @submit.prevent="editUser">
            <v-container>
          <v-card-text>
                <v-col cols="12">
                  <v-text-field
                    label="Username*"
                    v-model="modifyUser.username"
                    :rules="usernameRules"
                    type="text"
                    required
                  ></v-text-field>
                </v-col>
                <v-col cols="12">
                  <v-text-field
                    label="FullName*"
                    type="text"
                    :rules="fullnameRules"
                    v-model="modifyUser.fullName"
                    required
                  ></v-text-field>
                </v-col>
          </v-card-text>
          </v-container>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken-1" text @click="dialog2 = false">
              Close
            </v-btn>
            <v-btn color="blue darken-1" text type="submit">
              Save
            </v-btn>
            </v-card-actions>
            </v-form>
        </v-card>
      </v-dialog>
    </v-container>
  </div>
</template>
<script>
import UserService from "@/services/user.service";
import AuthorityService from "@/services/authority.service";

export default {
  name: "Users",
  data() {
    return {
      search: "",
      valid: true,
      valid2: true,
      message: "",
      message1: "",
      user: {},
      users: [],
      authorities: [],
      dialog: false,
      dialog2: false,
      modifyUser: "",
      usernameRules: [(v) => !!v || "Username is required",
      (v) => (v && v.length >= 5) || "minimum 5 characters",
      (v) => (v && v.length <= 20) || "maximum 20 characters",
      ],
      passwordRules: [(b) => !!b || "Password is required",
      (v) => (v && v.length >= 5) || "minimum 5 characters",
      ],
      fullnameRules: [(v) => !!v || "Fullname is required",
      (v) => (v && v.length >= 5) || "minimum 5 characters",
      (v) => (v && v.length <= 36) || "maximum 36 characters",
      (v)=>((/^[a-zA-Z\s]+$/.test(v))) || "only letters"
      ],
      headers: [
        { text: "Username", value: "username" },
        { text: "Fullname", value: "fullName" },
        { text: "Authority", value: "authority.authority" },
        { text: "Actions", value: "actions", sortable: false },
      ],
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
    UserService.getUsers().then(
      (res) => {
        this.users = res;
      },
      (error) => {
        this.message = error.response.data.message;
      }
    );
  },
  methods: {
    deleteUser(id) {
      UserService.deleteUser(id).then(
        () => {
          this.users = this.users.filter((user) => user.id != id);
          this.message1="User deleted successfully !"
        },
        (error) => {
          this.message = error.response.data.message;
        }
      );
    },
    getUser(id) {
      UserService.getUser(id).then(
        (res) => {
          this.modifyUser = res;
          this.dialog2 = true;
        },
        (error) => {
          this.message = error.response.data.message;
        }
      );
    },
    editUser() {
      if (!this.$refs.form2.validate()) return false;
      UserService.editUser(this.modifyUser.id, this.modifyUser).then(
        (res) => {
          let index = this.users.findIndex((r) => r.id == res.id);
          this.$set(this.users, index, res);
          this.dialog2 = false;
        },
        (error) => {
          this.message = error.response.data.message;
        }
      );
    },
    newUser() {
      if (!this.$refs.form.validate()) return false;
      UserService.newUser(this.user).then(
        (res) => {
          this.users.push(res);
          this.user = {};
          this.dialog = false;
          this.message1="User saved successfully !"
        },
        (error) => {
          this.message = error.response.data.message;
        }
      );
    },
  },
};
</script>
