<template>
  <div class="homeAdmin">
    <br />
    <br />
    <v-jumbotron>
      <v-container fill-height>
        <v-layout align-center>
          <v-flex>
            <h3 class="display-3">Welcome Director</h3>

            <span class="subheading"
              >Welcome to Travel Permits Management System ! Director can
              add add Departments ,supervisors and employees.You can also
              find Travel Requests by date.
            </span>

            <v-divider class="my-3"></v-divider>

            <div class="title mb-3">View Departments</div>
            <v-btn color="teal">
              <router-link to="/director/departments" class="white--text" large>
                go to Departments
              </router-link>
            </v-btn>
          </v-flex>
        </v-layout>
      </v-container>
    </v-jumbotron>
  </div>
</template>

<script>
export default {
  name: "HomeDirector",
};
</script>
