<template>
  <div class="homeAdmin">
    <br />
    <br />
    <v-jumbotron>
      <v-container fill-height>
        <v-layout align-center>
          <v-flex>
            <h3 class="display-3">Welcome Admin</h3>

            <span class="subheading"
              >Welcome to Travel Permits Management System ! Admin can
              create new roles and users.You can also delete roles.You can edit
              users and delete them.
            </span>

            <v-divider class="my-3"></v-divider>

            <div class="title mb-3">View Users</div>
            <v-btn color="teal">
              <router-link to="/admin/users" class="white--text" large>
                go to Users
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
  name: "HomeAdmin",
};
</script>
