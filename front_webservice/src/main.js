import Vue from 'vue'
import App from './App.vue'
import router from './router'
import BootstrapVue from 'bootstrap-vue'
import Vuex from 'vuex'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.use(BootstrapVue)
Vue.use(Vuex)
window.axios = require('axios');

Vue.config.productionTip = false

Vue.prototype.$userForm = {
  user_id: '',
  firstName: '',
  lastName: '',
  email: '',
  password: '',
  role: '',
  teamNumber:0,
  connected:false
}

Vue.prototype.$managerSelectedUser = {
  user_id: '',
  firstName: '',
  lastName: '',
  email: '',
  password: '',
  role: '',
  teamNumber:''
}

Vue.prototype.$userList = []
Vue.prototype.$userListFetched = 0

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
