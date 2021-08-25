<template>
  <div>
    <br />
     <br />
      <br />
      <v-container>
    <v-card  max-width="500" class="mx-auto my-14" >
      <v-toolbar flat >
        <v-icon>mdi-account</v-icon>
        <v-toolbar-title class="font-weight-light">
          User Profile
        </v-toolbar-title>
        <v-spacer></v-spacer>
        <v-btn  fab small @click="isEditing = !isEditing">
          <v-icon v-if="isEditing">
            mdi-close
          </v-icon>
          <v-icon v-else>
            mdi-pencil
          </v-icon>
        </v-btn>
      </v-toolbar>
      <v-form
        ref="form"
        lazy-validation
        @submit.prevent="changeDetails"
        v-model="valid"
      >
        <v-card-text>
          <v-text-field
            :disabled="!isEditing"
            color="teal"
            label="Username"
            :rules="usernameRules"
            v-model="authUser.username"
          ></v-text-field>
          <v-text-field
            :disabled="!isEditing"
            color="teal"
            label="Fullname"
            :rules="fullnameRules"
            v-model="authUser.fullName"
          ></v-text-field>
        </v-card-text>
        <v-divider></v-divider>
        <v-card-actions>
          <v-btn :disabled="!isEditing" color="teal darken-1" text @click="dialog=true">
            change password
          </v-btn>
          <v-spacer></v-spacer>
          <v-btn :disabled="!isEditing" color="teal" text type="submit">
            Save
          </v-btn>
        </v-card-actions>
      </v-form>
      <v-snackbar v-model="hasSaved" :timeout="2000" absolute bottom left>
        {{ message }}
      </v-snackbar>
    </v-card>
      </v-container>
    <v-dialog v-model="dialog" hide-overlay persistent max-width="600px">
      <v-card
        ><v-card-title>
          <span class="headline"> Change Password </span>
        </v-card-title>
        <v-form
          ref="form2"
          lazy-validation
          @submit.prevent="changePassword"
          v-model="valid2"
        >
          <v-card-text>
            <v-text-field
              label="Old Password*"
              v-model="passwordChanger.oldPassword"
              type="password"
              :rules="passwordRules"
              required
            ></v-text-field>
            <v-text-field
              label="New Password*"
              v-model="passwordChanger.newPassword"
              :rules="passwordRules"
              type="password"
              required
            ></v-text-field>
            <v-text-field
              label="Confirm Password*"
              v-model="confirmPassword"
              :rules="confirmPasswordRules"
              type="password"
              required
            ></v-text-field>
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
  </div>
</template>

<script>
import UserService from "../services/user.service";

export default {
  name: "UserDetails",
  data() {
    return {
      message: "",
      hasSaved: false,
      isEditing: null,
      authUser: {},
      confirmPassword: "",
      dialog: false,
      valid: true,
      valid2: true,
      confirmPasswordRules: [
        (v) => !!v || "type confirm password",
        (v) =>
          v === this.passwordChanger.newPassword ||
          "The password confirmation does not match.",
      ],
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
      passwordChanger: {
        oldPassword:"",
        newPassword:""
      },
    };
  },
  mounted: function() {
    UserService.getCurrentUser().then(
      (res) => {
        this.authUser = res;
      },
      (error) => {
        this.message = error.response.data.message;
      }
    );
  },
  methods: {
    changeDetails() {
      if (!this.$refs.form.validate()) return false;
      UserService.changeDetails(this.authUser).then(
        (res) => {
          this.authUser = res;
          this.hasSaved = true;
          this.isEditing = !this.isEditing;
          this.message = "Details changed successfully !";
        },
        (error) => {
          this.hasSaved = true;
          this.message = error.response.data.message;
        }
      );
    },
    changePassword() {
      if (!this.$refs.form2.validate()) return false;
      UserService.changePassword(this.passwordChanger).then(
        (res) => {
          this.dialog=false;
          this.hasSaved = true;
          this.message = res.result;
        },
        (error) => {
          this.message = error.response.data.message;
        }
      );
    },
  },
};
</script>
