// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Vuex from 'vuex'
import router from './router'
import App from './App.vue'
import VueResource from 'vue-resource'
import config from './config.js'
import MarkdownIt from 'markdown-it'

Vue.config.productionTip = false
Vue.use(Vuex)
Vue.use(VueResource)

const store = new Vuex.Store({
  state: {
    login: false,
    username: '',
    user: {
      username: '',
      signature: ''
    }
  },
  mutations: {
    setUnlogin (state) {
      state.login = false
    },
    setLogin (state) {
      state.login = true
    }
  }
})

new Vue().$http.get(config.baseUrl + '/isLogin')
  .then(function (rep) {
    if (!(rep.data.data === false)) {
      store.state.login = true
      store.state.username = rep.data.data.username
      store.state.user = rep.data.data
    }
  })

Vue.filter('format', function (dateStr) {
  let date = new Date(dateStr)
  let fmt = 'yyyy-MM-dd'
  let padLeftZero = function (str) {
    return ('00' + str).substr(str.length)
  }
  if (/(y+)/.test(fmt)) {
    fmt = fmt.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length))
  }
  let o = {
    'M+': date.getMonth() + 1,
    'd+': date.getDate(),
    'h+': date.getHours(),
    'm+': date.getMinutes(),
    's+': date.getSeconds()
  }
  for (let k in o) {
    if (new RegExp(`(${k})`).test(fmt)) {
      let str = o[k] + ''
      fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? str : padLeftZero(str))
    }
  }
  return fmt
})

let md = new MarkdownIt()

Vue.filter('markdown', function (str) {
  return md.render(str)
})

Vue.http.options.credentials = true

/* eslint-disable no-new */
new Vue({
  el: '#app',
  store,
  router,
  template: '<App />',
  components: { App }
})
