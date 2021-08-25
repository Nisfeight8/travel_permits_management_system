<template>
  <div>
    <v-card>
      <v-card-text v-if="Object.keys(supervisor).length != 0">
        <v-simple-table>
          <template v-slot:default>
            <thead>
              <tr>
                <th class="text-left">
                  Username
                </th>
                <th class="text-left">
                  Fullname
                </th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td class="text-left">{{ supervisor.user.username }}</td>
                <td class="text-left">{{ supervisor.user.fullName }}</td>
              </tr>
            </tbody>
          </template>
        </v-simple-table>
      </v-card-text>
      <v-card-text v-if="Object.keys(supervisor).length === 0">
        <div>
          Supervisor not found !
        </div>
        <p class="display-1 text--primary">
          Add a new one by clicking ADD!
        </p>
      </v-card-text>
      <v-card-actions>
        <v-btn
          v-if="Object.keys(supervisor).length === 0"
          text
          color="teal accent-4"
          @click="addSupervisor"
        >
          Add
        </v-btn>
        <v-spacer> </v-spacer>
        <v-btn
          v-if="Object.keys(supervisor).length != 0"
          text
          color="teal accent-4"
          @click="deleteSupervisor"
        >
          Remove
        </v-btn>
        <v-btn
          v-if="Object.keys(supervisor).length != 0"
          text
          color="teal accent-4"
          @click="edit = true"
        >
          change Department
        </v-btn>
      </v-card-actions>

      <v-expand-transition>
        <v-card
          v-if="add & (Object.keys(supervisor).length === 0)"
          class="transition-fast-in-fast-out v-card--reveal"
        >
          <v-form ref="form" lazy-validation v-model="valid" @submit.prevent="newSupervisor">
          <v-card-text class="pb-0">
            <p class=" text--primary">
              Select Supervisor
            </p>
            <v-select
              return-object
              v-model="newsupervisor.user"
              item-text="username"
              :items="supervisors"
              :rules="[(v) => !!v || 'Item is required']"
              label="Select Supervisor"
              required
            ></v-select>
          </v-card-text>
          <v-card-actions class="pt-0">
            <v-btn text color="teal accent-4" type="submit">
              Save
            </v-btn>
            <v-spacer> </v-spacer>
            <v-btn text color="teal accent-4" @click="add = false">
              Close
            </v-btn>
          </v-card-actions>
          </v-form>
        </v-card>
      </v-expand-transition>
      <v-expand-transition>
        <v-card
          v-if="edit & (Object.keys(supervisor).length != 0)"
          class="transition-fast-in-fast-out v-card--reveal"
        >
        <v-form ref="form2" lazy-validation v-model="valid2" @submit.prevent="changeDepartment">
          <v-card-text class="pb-0">
            <p class=" text--primary">
              Select Department
            </p>
            <v-select
              return-object
              v-model="supervisor.department"
              item-text="name"
              :items="departments"
              :rules="[(v) => !!v || 'Item is required']"
              label="Select Department"
              required
            ></v-select>
          </v-card-text>
          <v-card-actions class="pt-0">
            <v-btn text color="teal accent-4" type="submit">
              Save
            </v-btn>
            <v-spacer> </v-spacer>
            <v-btn text color="teal accent-4" @click="edit = false">
              Close
            </v-btn>
          </v-card-actions>
          </v-form>
        </v-card>
      </v-expand-transition>
    </v-card>
  </div>
</template>

<script>
import SupervisorService from "@/services/supervisor.service";
import UserService from "@/services/user.service";

export default {
  name: "Supervisor",
  props: {
    department: {
      type: Object,
    },
    departments: {
      type: Array,
    },
    supervisor: {
      type: Object,
    },
    message: {
      type: String,
    },
  },
  data() {
    return {
      valid: true,
      valid2: true,
      edit: false,
      add: false,
      newsupervisor: {},
      supervisors: [],
    };
  },
  methods: {
    addSupervisor() {
      UserService.getSupervisors().then(
        (res) => {
          this.supervisors = res;
          this.add = true;
        },
        (error) => {
          this.$emit("messagechange", error.response.data.message);
        }
      );
    },
    newSupervisor() {
      if (!this.$refs.form.validate()) return false;
      this.newsupervisor.department = this.department;
      SupervisorService.newSupervisor(this.newsupervisor).then(
        (res) => {
          this.$emit("supervisorchange", res);
          this.add = false;
        },
        (error) => {
          this.$emit("messagechange", error.response.data.message);
        }
      );
    },
    changeDepartment() {
      if (!this.$refs.form2.validate()) return false;
      SupervisorService.editSupervisor(
        this.supervisor.id,
        this.supervisor
      ).then(
        (res) => {
          if (res.department.name === this.department.name) {
          this.$emit("supervisorchange", res);
          } else {
          this.$emit("supervisorchange", {});
          }
          this.edit = false;
        },
        (error) => {
          this.$emit("messagechange", error.response.data.message);
        }
      );
    },
    deleteSupervisor() {
      SupervisorService.deleteSupervisor(this.supervisor.id).then(
        () => {
          this.$emit("supervisorchange", {});
        },
        (error) => {
          this.$emit("messagechange", error.response.data.message);
        }
      );
    },
  },
};
</script>

<style>
.v-card--reveal {
  bottom: 0;
  opacity: 1 !important;
  position: absolute;
  width: 100%;
}
</style>
