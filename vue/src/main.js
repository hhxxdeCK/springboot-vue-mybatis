import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
import {Message} from 'iview'
Vue.prototype.$Message = Message
import 'iview/dist/styles/iview.css'
import axios from 'axios'
import VueAxios from 'vue-axios'

Vue.prototype.$axios = axios
Vue.use(VueAxios,axios);
Vue.use(ElementUI)
Vue.config.productionTip = false
axios.defaults.baseURL = 'http://localhost:8080/api'
router.beforeEach((to,from,next) => {
  if(to.meta.requireAuth) {
    if(store.state.user.username) {
      next()
    } else {
      next({
        path: 'login',
        query: {redirect: to.fullPath}
      })
    }
  } else {
    next()
  }
})
new Vue({
  el: '#app',
  render: h => h(App),
  router,
  store,
  components: { App },
  template: '<App/>'
})
