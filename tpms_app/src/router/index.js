import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import Admin from "../views/Admin.vue";
import Supervisor from "../views/Supervisor.vue";
import Director from "../views/Director.vue";
import Employee from "../views/Employee.vue";
import Error from "../views/Error.vue";
import UserDetails from "../views/UserDetails.vue";
import Users from "@/components/admin/Users.vue";
import Authorities from "@/components/admin/Authorities.vue";
import HomeAdmin from "@/components/admin/HomeAdmin.vue";
import Departments from "@/components/director/Departments.vue";
import TravelRequests from "@/components/director/TravelRequests.vue";
import HomeDirector from "@/components/director/HomeDirector.vue";
import TravelRequestsByDepartment from "@/components/supervisor/TravelRequestsByDepartment.vue";
import HomeSupervisor from "@/components/supervisor/HomeSupervisor.vue";
import HomeEmployee from "@/components/employee/HomeEmployee.vue";
import TravelRequest from "@/components/employee/TravelRequest.vue";
import NewTravelRequest from "@/components/employee/NewTravelRequest.vue";




Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    components: { a: Home },
  },
  {
    path: "/error",
    name: "Error",
    components: { a: Error },
  },
  {
    path: "/admin/home",
    name: "Admin",
    components: { a: Admin },
    meta: {
      requiresAuth: true,
      supervisorAuth: false,
      adminAuth: true,
      employeeAuth: false,
      directorAuth: false,
    },
    children: [
      {
        path: "/admin/users",
        components: {
          admin: Users,
        },
        meta: {
          requiresAuth: true,
          supervisorAuth: false,
          adminAuth: true,
          employeeAuth: false,
          directorAuth: false,
        },
      },
      {
        path: "/admin/changeDetails",
        name: "UserDetails",
        components: { admin: UserDetails},
        meta: {
          requiresAuth: true,
          supervisorAuth: false,
          adminAuth: true,
          employeeAuth: false,
          directorAuth: false,
        },
      },
      {

        path: "/admin/authorities",
        components: {
          admin: Authorities,
        },
        meta: {
          requiresAuth: true,
          supervisorAuth: false,
          adminAuth: true,
          employeeAuth: false,
          directorAuth: false,
        },
      },
      {
        path: "/admin/home",
        components: {
          admin: HomeAdmin,
        },
        meta: {
          requiresAuth: true,
          supervisorAuth: false,
          adminAuth: true,
          employeeAuth: false,
          directorAuth: false,
        },
      },
    ],
  },
  {
    path: "/supervisor/home",
    name: "Supervisor",
    components: { a: Supervisor },
    meta: {
      requiresAuth: true,
      supervisorAuth: true,
      adminAuth: false,
      employeeAuth: false,
      directorAuth: false,
    },
    children: [
      {
        path: "/supervisor/travelRequests",
        components: {
          supervisor: TravelRequestsByDepartment,
        },
        meta: {
          requiresAuth: true,
          supervisorAuth: true,
          adminAuth: false,
          employeeAuth: false,
          directorAuth: false,
        },
      },
      {
        path: "/supervisor/changeDetails",
        name: "UserDetails",
        components: { supervisor: UserDetails},
        meta: {
          requiresAuth: true,
          supervisorAuth: true,
          adminAuth: false,
          employeeAuth: false,
          directorAuth: false,
        },
      },
      {
        path: "/supervisor/home",
        components: {
          supervisor: HomeSupervisor,
        },
        meta: {
          requiresAuth: true,
          supervisorAuth: true,
          adminAuth: false,
          employeeAuth: false,
          directorAuth: false,
        },
      },
    ],
  },
  {
    path: "/director/home",
    name: "Director",
    components: { a: Director },
    meta: {
      requiresAuth: true,
      supervisorAuth: false,
      adminAuth: false,
      employeeAuth: false,
      directorAuth: true,
    },
    children: [
      {
        path: "/director/travelRequests",
        components: {
          director: TravelRequests,
        },
        meta: {
          requiresAuth: true,
          supervisorAuth: false,
          adminAuth: false,
          employeeAuth: false,
          directorAuth: true,
        },
      },
      {
        path: "/director/changeDetails",
        name: "UserDetails",
        components: { director: UserDetails},
        meta: {
          requiresAuth: true,
          supervisorAuth: false,
          adminAuth: false,
          employeeAuth: false,
          directorAuth: true,
        },
      },
      {
        path: "/director/departments",
        components: {
          director: Departments,
        },
        meta: {
          requiresAuth: true,
          supervisorAuth: false,
          adminAuth: false,
          employeeAuth: false,
          directorAuth: true,
        },
      },
      {
        path: "/director/home",
        components: {
          director: HomeDirector,
        },
        meta: {
          requiresAuth: true,
          supervisorAuth: false,
          adminAuth: false,
          employeeAuth: false,
          directorAuth: true,
        },
      },
    ],
  },
  {
    path: "/employee/home",
    name: "Employee",
    components: { a: Employee },
    meta: {
      requiresAuth: true,
      supervisorAuth: false,
      adminAuth: false,
      employeeAuth: true,
      directorAuth: false,
    },
    children: [
      {
        path: "/employee/newTravelRequest",
        components: {
          employee:NewTravelRequest ,
        },
        meta: {
          requiresAuth: true,
          supervisorAuth: false,
          adminAuth: false,
          employeeAuth: true,
          directorAuth: false,
        },
      },
      {
        path: "/employee/changeDetails",
        name: "UserDetails",
        components: { employee: UserDetails},
        meta: {
          requiresAuth: true,
          supervisorAuth: false,
          adminAuth: false,
          employeeAuth: true,
          directorAuth: false,
        },
      },
      {
        path: "/employee/travelRequest",
        components: {
          employee: TravelRequest,
        },
        meta: {
          requiresAuth: true,
          supervisorAuth: false,
          adminAuth: false,
          employeeAuth: true,
          directorAuth: false,
        },
      },
      {
        path: "/employee/home",
        components: {
          employee: HomeEmployee,
        },
        meta: {
          requiresAuth: true,
          supervisorAuth: false,
          adminAuth: false,
          employeeAuth: true,
          directorAuth: false,
        },
      },
    ],
  },
  { path: "*", components: { a: Error } },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});
router.beforeEach((to, from, next) => {
  if (to.meta.requiresAuth) {
    const authUser = JSON.parse(localStorage.getItem("user"));
    if (!authUser || !authUser.token) {
      next({ name: "Home" });
    } else if (to.meta.adminAuth) {
      const authUser = JSON.parse(localStorage.getItem("user"));
      if (authUser.role === "ROLE_ADMIN") {
        next();
      } else {
        next({ name: "Error" });
      }
    } else if (to.meta.supervisorAuth) {
      const authUser = JSON.parse(localStorage.getItem("user"));
      if (authUser.role === "ROLE_SUPERVISOR") {
        next();
      } else {
        next({ name: "Error" });
      }
    } else if (to.meta.employeeAuth) {
      const authUser = JSON.parse(localStorage.getItem("user"));
      if (authUser.role === "ROLE_EMPLOYEE") {
        next();
      } else {
        next({ name: "Error" });
      }
    } else if (to.meta.directorAuth) {
      const authUser = JSON.parse(localStorage.getItem("user"));
      if (authUser.role === "ROLE_DIRECTOR") {
        next();
      } else {
        next({ name: "Error" });
      }
    }
  } else {
    next();
  }
});
export default router;
