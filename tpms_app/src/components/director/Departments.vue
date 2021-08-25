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
    <v-container fluid>
      <br />
      <v-row>
        <v-col cols="4">
          <v-card class="mx-auto" max-width="300" tile>
            <v-card-title>
              <v-btn
                class="mx-2 text--white"
                color="teal"
                dark
                @click="dialog = true"
                ><v-icon left>
                  mdi-plus
                </v-icon>
                New Department
              </v-btn>
            </v-card-title>
            <v-list flat>
              <v-subheader>Departments</v-subheader>
              <v-list-item-group color="teal">
                <v-list-item
                  v-for="department in departments"
                  :key="department.name"
                  @click="showDepartment(department)"
                >
                  <v-list-item-content>
                    <v-list-item-title
                      v-text="department.name"
                    ></v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
              </v-list-item-group>
            </v-list>
          </v-card>
        </v-col>
        <v-col cols="12" sm="12" md="8" v-show="showModal">
          <v-card color="basil">
            <v-card-title class="text-center justify-center py-6">
              <h1 class="font-weight-bold display-2 teal--text">
                {{ selectedDepartment.name }}
              </h1>
            </v-card-title>
            <v-tabs
              v-model="tab"
              background-color="transparent"
              color="teal"
              grow
            >
              <v-tab>
                Informations
              </v-tab>
              <v-tab>
                Supervisor
              </v-tab>
              <v-tab>
                Employees
              </v-tab>
            </v-tabs>

            <v-tabs-items v-model="tab">
              <v-tab-item>
                <v-form
                  ref="form2"
                  lazy-validation
                  @submit.prevent="editDepartment"
                  v-model="valid2"
                >
                  <v-row>
                    <v-col cols="6" sm="5">
                      <v-text-field
                        label="Name"
                        v-model="selectedDepartment.name"
                        :rules="nameRules"
                        type="text"
                      ></v-text-field>
                    </v-col>
                    <v-col cols="6" sm="5">
                      <v-text-field
                        label="Place"
                        v-model="selectedDepartment.place"
                        :rules="placeRules"
                        type="text"
                      ></v-text-field>
                    </v-col>
                    <v-col cols="2" sm="2">
                      <v-btn
                        class="ma-2"
                        outlined
                        small
                        color="teal"
                        type="submit"
                      >
                        Save
                      </v-btn>
                    </v-col>
                  </v-row>
                </v-form>
                <v-container>
                  <v-row>
                    <v-spacer> </v-spacer>
                    <v-btn
                      class="ma-2"
                      text
                      color="teal accent-4"
                      @click="deleteDepartment(selectedDepartment.id)"
                    >
                      delete
                    </v-btn>
                  </v-row>
                </v-container>
              </v-tab-item>
              <v-tab-item>
                <Supervisor
                  v-bind:supervisor="supervisor"
                  v-bind:departments="departments"
                  v-bind:department="selectedDepartment"
                  @supervisorchange="updateSupervisor"
                  @messagechange="updateMessage"
                ></Supervisor>
              </v-tab-item>
              <v-tab-item>
                <Employees
                  v-bind:employees="employees"
                  v-bind:departments="departments"
                  v-bind:department="selectedDepartment"
                  @employeeschange="updateEmployees"
                  @messagechange="updateMessage"
                ></Employees>
              </v-tab-item>
            </v-tabs-items>
          </v-card>
        </v-col>
      </v-row>
      <br />
      <br />
      <v-dialog v-model="dialog" hide-overlay persistent max-width="600px">
        <v-card
          ><v-card-title>
            <span class="headline"> New Department </span>
          </v-card-title>
          <v-form
            ref="form"
            lazy-validation
            @submit.prevent="newDepartment"
            v-model="valid"
          >
            <v-card-text>
              <v-container>
                <v-text-field
                  label="Name*"
                  v-model="department.name"
                  type="text"
                  :rules="nameRules"
                  required
                ></v-text-field>
                <v-text-field
                  label="Place*"
                  v-model="department.place"
                  :rules="placeRules"
                  type="text"
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
    </v-container>
  </div>
</template>
<script>
import DepartmentService from "@/services/department.service";
import SupervisorService from "@/services/supervisor.service";
import EmployeeService from "@/services/employee.service";
import Supervisor from "./Supervisor.vue";
import Employees from "./Employees.vue";

export default {
  components: { Supervisor, Employees },
  name: "Departments",
  data() {
    return {
      alert: true,
      showModal: false,
      selectedDepartment: {},
      department: {},
      supervisor: {},
      tab: null,
      employees: [],
      valid: true,
      valid2: true,
      message: "",
      departments: [],
      dialog: false,
      nameRules: [
        (v) => !!v || "Name is required",
        (v) => (v && v.length >= 3) || "minimum 3 characters",
        (v) => (v && v.length <= 20) || "maximum 20 characters",
      ],
      placeRules: [
        (v) => !!v || "Place is required",
        (v) => (v && v.length >= 3) || "minimum 3 characters",
        (v) => (v && v.length <= 20) || "maximum 20 characters",
        (v) => /^[a-zA-Z\s]+$/.test(v) || "only letters",
      ],
    };
  },

  mounted: function() {
    DepartmentService.getDepartments().then(
      (res) => {
        this.departments = res;
      },
      (error) => {
        this.message = error.response.data.message;
      }
    );
  },
  methods: {
    updateMessage(variable) {
      this.message = variable;
    },
    updateSupervisor(variable) {
      this.supervisor = variable;
    },
    updateEmployees(variable) {
      this.employees = variable;
    },
    showDepartment(department) {
      this.selectedDepartment = department;
      SupervisorService.getSupervisorByDepartment(department.name).then(
        (res) => {
          if (res === "") {
            this.supervisor = {};
          } else {
            this.supervisor = res;
          }
        },
        (error) => {
          this.message = error.response.data.message;
          this.supervisor = {};
        }
      );
      EmployeeService.getEmployeesByDepartment(department.name).then(
        (res) => {
          this.employees = res;
        },
        (error) => {
          this.message = error.response.data.message;
        }
      );
      this.showModal = true;
    },
    newDepartment() {
      if (!this.$refs.form.validate()) return false;
      this.message = "";
      DepartmentService.newDepartment(this.department).then(
        (res) => {
          this.departments.push(res);
          this.department = {};
          this.dialog = false;
        },
        (error) => {
          this.message = error.response.data.message;
        }
      );
    },
    editDepartment() {
      if (!this.$refs.form2.validate()) return false;
      DepartmentService.editDepartment(
        this.selectedDepartment.id,
        this.selectedDepartment
      ).then(
        (res) => {
          this.selectedDepartment = res;
        },
        (error) => {
          this.message = error.response.data.message;
        }
      );
    },
    deleteDepartment(id) {
      if (!this.$refs.form2.validate()) return false;
      DepartmentService.deleteDepartment(id).then(
        (res) => {
          this.departments = this.departments.filter((dep) => dep.id != res);
          this.showModal = false;
        },
        (error) => {
          this.message = error.response.data.message;
        }
      );
    },
  },
};
</script>
