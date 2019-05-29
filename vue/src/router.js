import Vue from 'vue';
import Router from 'vue-router';
import Login from "./views/Login";
import About from "./views/About";
import 'iview/dist/styles/iview.css'
import axios from 'axios'
import VueAxios from 'vue-axios'

Vue.use(VueAxios, axios);
Vue.use(Router)

export default new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [

        {
            path: '/',
            name: 'login',
            component: Login
        },
        {
            path: '/about',
            name: 'about',
            component: About,
            meta: {
                requireAuth: true
            }
        },
        {
            path: '/login',
            name: 'login',
            component: Login
        },
    ]
})

