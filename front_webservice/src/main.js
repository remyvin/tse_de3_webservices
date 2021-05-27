import Vue from 'vue'
import App from './App.vue'
import router from './router'
import BootstrapVue from 'bootstrap-vue'
import Vuex from 'vuex'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import VueHtmlToPaper from 'vue-html-to-paper';

Vue.use(BootstrapVue)
Vue.use(Vuex)
Vue.use(VueHtmlToPaper);
window.axios = require('axios');

//VARIABLES A CHANGER POUR CONNEXION API AVEC IP DU SERVEUR OU LOCALHOST SI API EN LOCAL: 
Vue.prototype.$urlSOAP_Users = 'http://176.190.50.162:8081/ws';
Vue.prototype.$urlSOAP_Projects = 'http://176.190.50.162:8082/ws';
Vue.prototype.$urlREST_fetchTimes = 'http://176.190.50.162:8083/timebyuwy';
Vue.prototype.$urlREST_deleteTime = 'http://176.190.50.162:8083/time/';
Vue.prototype.$urlREST_AddTime = 'http://176.190.50.162:8083/time/';
Vue.prototype.$urlREST_fetchMonthly = 'http://176.190.50.162:8083/timebyumy';





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

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
