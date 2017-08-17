<template>
  <div>
    <div @click="invoke"><slot></slot></div>
    <vodal :show="show" animation="rotate" @hide="show = false" :height="220">
      <div style="color:black">输入用户名和密码</div>
      <div class="body">
        <div class="form-group">
          <input type="text" name="username" class="form-control" placeholder="在此输入用户名" v-model="username">
        </div>
        <div class="form-group">
          <input type="password" name="password" class="form-control" placeholder="在此输入密码" v-model="password">
        </div>
      </div>
      <div class="footer">
        <button type="button" class="btn btn-primary" v-on:click="requestLogin">登录</button>
      </div>
    </vodal>
  </div>
</template>

<script>
  import Vodal from 'vodal'
  import 'vodal/common.css'
  import 'vodal/rotate.css'
  import config from '../config.js'
  export default {
    name: 'LoginBtn',
    props: ['fun'],
    data () {
      return {
        show: false,
        username: '',
        password: ''
      }
    },
    methods: {
      invoke: function () {
        if (this.$store.state.login === true) {
          this.fun()
        } else {
          this.show = true
        }
      },
      requestLogin: function () {
        this.$http.post(config.baseUrl + '/login', {username: this.username, password: this.password})
          .then(function (rep) {
            if (parseInt(rep.data.status) === 200) {
              this.show = !this.show
              this.$store.state.login = true
              this.$store.state.username = this.username
            }
          })
      }
    },
    components: {
      Vodal
    }
  }
</script>
<style scoped>
  .body {
    padding: 20px 0px 0px;
  }
</style>
