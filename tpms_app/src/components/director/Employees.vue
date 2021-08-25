<template>
  <div>
  <v-card>
        <v-card-title>
          <v-btn
            class="mx-2 text--white"
            color="teal"
            dark
            @click="addEmployee"
            ><v-icon left>
              mdi-account-plus
            </v-icon>
            Add Employee
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
          :items="employees"
          :headers="headers"
          :items-per-page="3"
          :search="search"
        >
          <template v-slot:[`item.actions`]="{ item }">
            <v-btn
              class="ma-2"
              outlined
              small
              color="teal"
              @click="deleteEmployee(item.id)"
            >
              Remove
            </v-btn>
            <v-btn
              class="ma-2"
              outlined
              small
              color="teal"
              @click="getEmployee(item)"
            >
              Change Department
            </v-btn>
          </template>
        </v-data-table>
      </v-card>
       <v-dialog v-model="dialog" hide-overlay persistent max-width="600px">
        <v-card
          ><v-card-title>
            <span class="headline"> New Employee </span>
          </v-card-title>
          <v-form
            ref="form"
            lazy-validation
            @submit.prevent="newEmployee"
            v-model="valid"
          >
            <v-card-text>
              <v-container>
                <v-select
              return-object
              v-model="newemployee.user"
              item-text="username"
              :items="employeeUsers"
              :rules="[(v) => !!v || 'Item is required']"
              label="Select Employee"
              required
            ></v-select>
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
            <span class="headline"> Change Department </span>
          </v-card-title>
         <v-form ref="form2" lazy-validation v-model="valid2" @submit.prevent="changeDepartment">
          <v-card-text>
            <v-container>
                <v-select
                return-object
                v-model="editemployee.department"
                item-text="name"
                :items="departments"
                :rules="[(v) => !!v || 'Item is required']"
                label="Select Department"
                required
            ></v-select>
            </v-container>
          </v-card-text>
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
  </div>
</template>

<script>
import EmployeeService from "@/services/employee.service";
import UserService from "@/services/user.service";

export default {
  name: "Employees",
  props: {
    department: {
      type: Object,
    },
    departments: {
      type: Array,
    },
    employees: {
      type: Array,
    },
    message: {
      type: String,
    },
  },
  data() {
    return {
      dialog: false,
      dialog2: false,
      valid: true,
      valid2: true,
      search: "",
      depEmployees:this.employees,
      newemployee: {},
      editemployee:{},
      employeeUsers: [],
      headers: [
        { text: "Username", value: "user.username" },
        { text: "Fullname", value: "user.fullName" },
        { text: "Actions", value: "actions", sortable: false },
      ],
    };
  },
  methods: {
    getEmployee(employee){
      this.editemployee=employee;
      this.dialog2=true;
    },
    addEmployee() {
      UserService.getEmployees().then(
        (res) => {
          this.employeeUsers = res;
          this.dialog=true;
        },
        (error) => {
          this.$emit("messagechange", error.response.data.message);
        }
      );
    },
    newEmployee() {
      if (!this.$refs.form.validate()) return false;
      this.newemployee.department = this.department;
      EmployeeService.newEmployee(this.newemployee).then(
        (res) => {
          this.depEmployees.push(res);
          this.$emit("employeeschange", this.depEmployees);
          this.dialog=false;
        },
        (error) => {
          this.$emit("messagechange", error.response.data.message);
        }
      );
    },
    changeDepartment() {
      if (!this.$refs.form2.validate()) return false;
      EmployeeService.editEmployee(this.editemployee.id,this.editemployee).then(
        (res) => {
          if (res.department.name === this.department.name) {
            let index = this.depEmployees.findIndex((r) => r.id == res.id);
            this.$set(this.depEmployees, index, res);
            this.$emit("employeeschange", this.depEmployees);
          } else {
            this.depEmployees = this.depEmployees.filter((employee) => employee.id != this.editemployee.id);
            this.$emit("employeeschange", this.depEmployees);
          }
          this.dialog2=false;
        },
        (error) => {
          this.$emit("messagechange", error.response.data.message);
        }
      );
    },
    deleteEmployee(id) {
      EmployeeService.deleteEmployee(id).then(
        (res) => {
          this.depEmployees = this.depEmployees.filter((employee) => employee.id != res);
          this.$emit("employeeschange", this.depEmployees);
        },
        (error) => {
          this.$emit("messagechange", error.response.data.message);
        }
      );
    },
  },
};
</script>

