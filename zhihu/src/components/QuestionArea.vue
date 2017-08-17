<template>
  <div>
    <div class="row">
      <div class="col-md-8">
        <div class="m-title">
          <b style="font-size: 18px;">他的提问</b>
          <div class="pull-right">按时间顺序排序&nbsp;<span class="glyphicon glyphicon-menu-hamburger"></span></div>
        </div>
        <hr/>
        <div v-for="question in questions">
          <div class="title"><router-link :to="'/question/' + question.id">{{ question.title }}</router-link></div>
          <span class="footer">{{ question.date | format }}&nbsp;&nbsp;{{ question.answerCount }}个回答&nbsp;&nbsp;{{ question.followerCount }}个关注</span>
          <hr>
        </div>
      </div>

      <div class="col-md-4">
        <PersonPageSideNavbar></PersonPageSideNavbar>
      </div>
    </div>
  </div>
</template>

<script>
import config from '../config.js'
import PersonPageSideNavbar from './PersonalPageSideNavbar.vue'
export default {
  name: 'like',
  created: function () {
    this.$http.get(config.baseUrl + '/question/user/' + this.$route.params.username)
      .then(function (rep) {
        this.questions = rep.data.data
      })
  },
  data () {
    return {
      questions: []
    }
  },
  methods: {
  },
  components: {
    PersonPageSideNavbar
  }
}
</script>
<style scoped="">
  .m-title {
    margin-top: 20px;
  }
  hr {
    margin:5px 0px 20px;
  }
  .title {
    font-size: 20px;
    font-weight: bold;
    padding-bottom: 5px;
  }
</style>
