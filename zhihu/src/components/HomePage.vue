<template>
  <div class="container">
    <div class="panel panel-default">
      <div class="panel-body">
        <img src="/static/img/bg.jpg" width="100%"/>
        <div class="row">
          <div class="col-md-3">
            <img src="/static/img/portrait.jpg" width="200px" class="portrait"/>
          </div>
          <div class="col-md-6">
            <div class="m-user-info">
              <div><b style="font-size: 38px">{{ user.username }}</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size:20px">计算机/大二狗/Java</span></div>
              <div><span class="glyphicon glyphicon-user">&nbsp;互联网</span></div>
              <div><span class="glyphicon glyphicon-envelope">&nbsp;{{ user.email }}</span></div>
              <div><span class="glyphicon glyphicon-pencil">&nbsp;{{ user.signature }}</span></div>
            </div>
          </div>
          <div class="col-md-3">
            <div>&nbsp;</div>
            <div>&nbsp;</div>
            <div>&nbsp;</div>
            <div>&nbsp;</div>
            <div>&nbsp;</div>
            <div>&nbsp;</div>
            <div>&nbsp;</div>

            <div v-show="$store.state.username === $route.params.username">
              <router-link to="edit/people/" class="btn btn-default btn-sm" style="font-size:14px">编辑个人主页</router-link>
            </div>
            <div v-show="!($store.state.username === $route.params.username)">
              <div class="btn btn-primary btn-sm" @click="follow" v-show="!isfollow">
                <span class="glyphicon glyphicon-plus" style="font-size:14px">&nbsp;关注他</span>
              </div>
              <div v-show="isfollow" class="btn btn-default btn-sm" @mouseenter="enter" @mouseleave="leave" @click="follow">
                <span v-html="sign"></span>
              </div>
              <router-link to="inbox/people" class="btn btn-default btn-sm">
                <span class="glyphicon glyphicon-comment" style="font-size:14px">&nbsp;发私信</span>
              </router-link>
            </div>
          </div>
        </div>
      </div>
    </div>

    <ul class="nav nav-tabs">
      <li role="presentation" :class="[$route.path.endsWith('activities') ? 'active' : '']">
        <router-link :to="'/people/' + $route.params.username + '/activities'">动态</router-link>
      </li>
      <li role="presentation" :class="[$route.path.endsWith('questions') ? 'active' : '']">
        <router-link :to="'/people/' + $route.params.username + '/questions'">提问</router-link>
      </li>
      <li role="presentation" :class="[!$route.path.endsWith('activities') && !$route.path.endsWith('questions') ? 'active' : '']">
        <router-link :to="'/people/' + $route.params.username + '/'">回答</router-link>
      </li>
    </ul>

    <router-view></router-view>

  </div>
</template>

<script>
import AnswerArea from './AnswerArea.vue'
import config from '../config.js'

export default {
  name: 'homePage',
  created: function () {
    this.$http.get(config.baseUrl + '/user/name/' + this.$route.params.username)
      .then(function (rep) {
        this.user = rep.data.data
      })
    this.$http.get(config.baseUrl + '/user/name/' + this.$route.params.username + '/isfollow')
      .then(function (rep) {
        this.isfollow = rep.data.data
      })
  },
  data () {
    return {
      user: {
        username: '',
        email: '',
        career: '',
        signature: ''
      },
      sign: '&nbsp;&nbsp;已关注&nbsp;&nbsp;',
      isfollow: false
    }
  },
  methods: {
    follow: function () {
      let requestUrl = config.baseUrl + '/user/name/' + this.$route.params.username + (this.isfollow ? '/unfollow' : '/follow')
      this.$http({
        url: requestUrl,
        method: 'post'
      }).then(function (rep) {
        if (rep.data.status === 200) {
          this.isfollow = !this.isfollow
        }
      })
    },
    enter: function () {
      this.sign = '取消关注'
    },
    leave: function () {
      this.sign = '&nbsp;&nbsp;已关注&nbsp;&nbsp;'
    }
  },
  components: {
    AnswerArea
  }
}
</script>
<style scoped>
  .portrait {
    border: 8px solid white;
    border-radius: 10px;
    margin-left: 20px;
    position: relative;
    top: -30px;
  }
  .m-user-info div {
    padding: 8px;
  }
</style>
