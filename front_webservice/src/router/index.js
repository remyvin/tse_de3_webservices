import Vue from 'vue'
import VueRouter from 'vue-router'
import Homepage from '../components/Homepage.vue'
import Connexion from '../components/Connexion.vue'
import Edition from '../components/Edition.vue'
import Equipe from '../components/Equipe.vue'
import BootstrapVue from 'bootstrap-vue'
import 'xmldom-ts';
import { VueHtmlToPaper } from "vue-html-to-paper";

Vue.use(VueRouter);
Vue.use(BootstrapVue);
window.axios = require('axios');
Vue.use(VueHtmlToPaper.default);

const routes = [
  {
    path: '/',
    name: 'Homepage',
    component: Homepage
  },
  {
    path: '/Connexion',
    name: 'Connexion',
    component: Connexion
  },
  {
    path: '/Equipe',
    name: 'Equipe',
    component: Equipe
  },
  {
    path: '/Edition',
    name: 'Edition',
    component: Edition
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
