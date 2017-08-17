<template>
  <div>
    <div class="panel panel-default">
      <div class="panel-body">
        <div class="row">
          <div class="col-md-8">
            <p class="m-title">{{ question.title }}</p>
            <p class="m-body">{{ question.desc }}</p>
            <IconBtn icon-name="comment" btn-title="评论"></IconBtn>
            <IconBtn icon-name="share" btn-title="分享"></IconBtn>
            <IconBtn icon-name="star" btn-title="邀请"></IconBtn>
            <IconBtn icon-name="comment" btn-title="举报"></IconBtn>
          </div>
          <div class="col-md-4">
            <div class="row">
              <div class="col-md-3"></div>
              <div class="col-md-3">
                <div style="font-size: 18px;text-align: center;color: darkgray">关注者</div>
                <div style="text-align: center">{{ question.followerCount }}</div>
              </div>
              <div class="col-md-3">
                <div style="font-size: 18px;text-align: center;color: darkgray">被浏览</div>
                <div style="text-align: center">{{ question.pv }}</div>
              </div>
              <div class="col-md-3"></div>
            </div>
            <div>&nbsp;</div>
            <div class="row">
              <div class="col-md-3"></div>
              <div class="col-md-9">
                <LoginBtn v-show="!isfollow" class="btn btn-primary" :fun="follow">关注问题</LoginBtn>
                <LoginBtn v-show="isfollow" :fun="follow" style="display: inline-block;">
                  <button class="btn btn-default" @mouseenter="enter" @mouseleave="leave">{{ sign }}</button>
                </LoginBtn>
                <button @click="write" class="btn btn-default"><span class="glyphicon glyphicon-pencil">写回答</span></button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="row">
      <div class="col-md-8">
        <div v-for="answer in answers">
          <Answer :answer="answer"></Answer>
        </div>
        <Editor id="editor" v-show="$store.state.login" :ishljs = "false" :question="question"></Editor>
      </div>
      <div class="col-md-4">
        <div class="panel panel-default">
          <div class="panel-heading">
            优质推荐
          </div>
          <div class="panel-body">
            <ul class="m-question-list">
              <div v-for="answer in brilliantAnswers">
                <li><router-link :to="'/question/' + answer.question.id">{{ answer.question.title }}&nbsp;&nbsp;&nbsp;{{ answer.likerCount }} 个赞同</router-link></li>
              </div>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import config from '../config.js'
  import IconBtn from './IconBtn.vue'
  import Answer from './Answer.vue'
  import LoginBtn from './LoginBtn.vue'
  import Editor from './Editor.vue'
  import 'mavon-editor/dist/css/index.css'
  export default {
    name: '',
    created: function () {
      this.$http.get(config.baseUrl + '/question/id/' + this.$route.params.questionId)
        .then(function (rep) {
          this.question = rep.data.data
        })
      this.$http.get(config.baseUrl + '/answer/questionId/' + this.$route.params.questionId)
        .then(function (rep) {
          this.answers = rep.data.data
        })
      this.$http.get(config.baseUrl + '/question/id/' + this.$route.params.questionId + '/isFollow')
        .then(function (rep) {
          this.isfollow = rep.data.data
        })
      this.$http.get(config.baseUrl + '/answer/brilliant')
        .then(function (rep) {
          this.brilliantAnswers = rep.data.data
        })
    },
    data () {
      return {
        question: {
          title: '',
          desc: '',
          date: 0
        },
        isfollow: false,
        answers: [],
        sign: '正在关注',
        brilliantAnswers: []
      }
    },
    methods: {
      follow: function () {
        let requestUrl = config.baseUrl + '/question/id/' + this.question.id + (this.isfollow ? '/unfollow' : '/follow')
        this.$http.get(requestUrl)
          .then(function (rep) {
            if (rep.data.data === true) {
              console.log('success')
              this.isfollow = !this.isfollow
            }
          })
      },
      write: function () {
        document.getElementById('editor').scrollIntoView()
      },
      enter: function () {
        this.sign = '取消关注'
      },
      leave: function () {
        this.sign = '正在关注'
      }
    },
    components: {
      IconBtn, Answer, LoginBtn, Editor
    }
  }
</script>
<style scoped>
  .m-title {
    font-weight: bold;
    font-size: 24px;
    padding-bottom: 10px;
  }
  .m-body {
    padding-bottom: 10px;
  }
  .m-question-list {
    padding-left: 20px;
  }
  .m-question-list li {
    padding: 5px;
  }
  p {
    line-height: 30px;
  }
</style>
