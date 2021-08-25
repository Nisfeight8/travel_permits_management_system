<template>
  <v-container>
    <v-card color="teal darken-2" max-width="500" class="mx-auto my-14">
      <v-toolbar flat color="teal ">
        <v-icon>mdi-login</v-icon>
        <v-toolbar-title class="font-weight-light">
          Login
        </v-toolbar-title>
      </v-toolbar>
      <v-form @submit.prevent="handleLogin">
        <v-card-text>
          <v-text-field
            v-model="user.username"
            text-color="white"
            :rules="usernameRules"
            label="Username"
            type="text"
            name="username"
            required
            color="white"
          ></v-text-field>
          <v-text-field
            v-model="user.password"
            :rules="passwordRules"
            label="Password"
            type="password"
            name="password"
            required
            text-color="white"
          ></v-text-field>
        </v-card-text>
        <v-divider></v-divider>
        <v-card-actions>
          <p class="red--text" absolute bottom left v-if="message">
            {{ message }}
          </p>
          <v-spacer></v-spacer>
          <v-btn class="white  black--text" :disabled="loading" type="submit">
            Login
          </v-btn>
        </v-card-actions>
      </v-form>
    </v-card>
  </v-container>
</template>

<script>

export default {
  name: "Login",
  data() {
    return {
      user: {},
      loading: false,
      message: "",
      usernameRules: [(v) => !!v || "Username is required"],
      passwordRules: [(b) => !!b || "Password is required"],
      loggedUser: " ",
    };
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
  },
  created() {
    if (this.loggedIn) {
      this.loggedUser = this.$store.state.auth.user;
      if (this.loggedUser.role === "ROLE_ADMIN") {
        this.$router.push("/admin/home");
      }
      if (this.loggedUser.role === "ROLE_STAFF") {
        this.$router.push("/staff/home");
      }
      if (this.loggedUser.role === "ROLE_DIRECTOR") {
        this.$router.push("/director/home");
      }
      if (this.loggedUser.role === "ROLE_SUPERVISOR") {
        this.$router.push("/supervisor/home");
      }
      if (this.loggedUser.role === "ROLE_EMPLOYEE") {
        this.$router.push("/employee/home");
      }
    }
  },
  methods: {
    handleLogin() {
      this.loading = true;
      if (this.user.username && this.user.password) {
        this.$store.dispatch("auth/login", this.user).then(
          () => {
            this.loggedUser = this.$store.state.auth.user;
            if (this.loggedUser.role === "ROLE_ADMIN") {
              this.$router.push("/admin/home");
            }
            if (this.loggedUser.role === "ROLE_DIRECTOR") {
              this.$router.push("/director/home");
            }
            if (this.loggedUser.role === "ROLE_SUPERVISOR") {
              this.$router.push("/supervisor/home");
            }
            if (this.loggedUser.role === "ROLE_EMPLOYEE") {
              this.$router.push("/employee/home");
            }
          },
          (error) => {
            this.loading = false;
            this.message = error.response.data.message;
          }
        );
      } else {
        this.loading = false;
        this.message = "Username and password must not be blank !";
      }
    },
  },
};
</script>

<style scoped></style>
