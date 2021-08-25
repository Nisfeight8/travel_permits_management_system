import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import * as VeeValidate from 'vee-validate';
import Vuex from 'vuex';
import vuetify from './plugins/vuetify';

Vue.config.productionTip = false
Vue.use(VeeValidate);
Vue.use(Vuex);
new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
