<template>
  <div>
    <nav class="navbar navbar-default navbar-fixed-top">
      <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                  data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#" style="color: dodgerblue;">扯乎</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
          <ul class="nav navbar-nav">
            <li>
              <a href="#">首页 <span class="sr-only">(current)</span></a>
            </li>
            <li>
              <a href="#">发现</a>
            </li>
            <li>
              <a href="#">话题</a>
            </li>
          </ul>
          <form class="navbar-form navbar-left">
            <div class="form-group">
              <input type="text" class="form-control" placeholder="搜索你感兴趣的内容...">
            </div>
            <button type="submit" class="btn btn-default">
              <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
            </button>
          </form>

          <ul class="nav navbar-nav navbar-right" v-show="$store.state.login">
            <li>
              <a href="#"><span class="glyphicon glyphicon-bell"></span></a>
            </li>
            <li>
              <a href="#"><span class="glyphicon glyphicon-comment"></span></a>
            </li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle user-pic" data-toggle="dropdown" role="button" aria-haspopup="true"
                 aria-expanded="false">
                <img src="/static/img/portrait.jpg" width="20px"
                     height="20px"></img>
              </a>
              <ul class="dropdown-menu">
                <li role="separator" class="divider"></li>
                <li>
                  <router-link :to="'/people/' + $store.state.username"><span class="glyphicon glyphicon-user">&nbsp;我的主页</span></router-link>
                </li>
                <li>
                  <a href="#"><span class="glyphicon glyphicon-cog">&nbsp;设置</span></a>
                </li>
                <li role="separator" class="divider"></li>
                <li>
                  <a @click="quit"><span class="glyphicon glyphicon-off">&nbsp;退出</span></a>
                </li>
              </ul>
            </li>
          </ul>

          <div class="pull-right" v-show="!$store.state.login">
            <a class="btn btn-default navbar-btn" v-on:click="toggle">登录</a>
            <a class="btn btn-primary navbar-btn" v-on:click="showRegister = true">加入知乎</a>
          </div>

        </div>
        <!-- /.navbar-collapse -->
      </div>
      <!-- /.container-fluid -->
    </nav>

    <vodal :show="show" animation="rotate" @hide="show = false" :height="220">
      <div>
        请填入用户名和密码
      </div>
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
    <vodal :show="showRegister" animation="rotate" @hide="showRegister = false" :height="220">
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
        <button type="button" class="btn btn-primary" v-on:click="requestRegister">注册</button>
      </div>
    </vodal>
  </div>
</template>
<script>
import config from '../config.js'
import Vodal from 'vodal'
import 'vodal/common.css'
import 'vodal/rotate.css'

export default {
  name: 'navbar',
  data () {
    return {
      username: '',
      password: '',
      show: false,
      showRegister: false
    }
  },
  methods: {
    toggle: function () {
      this.show = !this.show
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
    },
    hideModal: function () {
      document.getElementById('loginModal').modal('hide')
    },
    quit: function () {
      document.cookie = 'JSESSIONID=;-1'
      this.$store.state.login = false
    },
    requestRegister: function () {
      this.$http.post(config.baseUrl + '/register', {username: this.username, password: this.password})
        .then(function (rep) {
          if (parseInt(rep.data.status) === 200) {
            this.showRegister = !this.showRegister
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
