<template>
  <div class="homeAdmin">
    <br />
    <br />
    <v-jumbotron>
      <v-container fill-height>
        <v-layout align-center>
          <v-flex>
            <h3 class="display-3">Welcome Employee</h3>

            <span class="subheading"
              >Welcome to Travel Permits Management System !
              Employee can create new Travel request.You can also see your Travel Request.
              </span
            >

            <v-divider class="my-3"></v-divider>

            <div class="title mb-3">New Travel Request</div>
            <v-btn color="teal">
            <router-link to="/employee/newTravelRequest"  class="white--text" large>
              go to form
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
  name: "HomeEmployee",
};
</script>