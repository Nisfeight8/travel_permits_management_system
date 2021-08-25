<template>
  <div class="homeSupervisor">
    <br />
    <br />
    <v-jumbotron>
      <v-container fill-height>
        <v-layout align-center>
          <v-flex>
            <h3 class="display-3">Welcome Supervisor</h3>

            <span class="subheading"
              >Welcome to Travel Permits Management System ! Supervisor can approve department's travel requests!
            </span>

            <v-divider class="my-3"></v-divider>

            <div class="title mb-3">View Travel Requests</div>
            <v-btn color="teal">
              <router-link
                to="/supervisor/travelRequests"
                class="white--text"
                large
              >
                go to travel Requests
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
  name: "HomeSupervisor",
};
</script>