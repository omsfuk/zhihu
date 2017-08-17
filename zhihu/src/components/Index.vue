<template>
  <div>
    <div class="m-content">
      <div class="row">
        <div class="col-md-8">
          <div class="navbar navbar-default" v-show="$store.state.login">
            <div class="container">
              <ul class="nav navbar-nav">
                <li><a href="#"><span class="glyphicon glyphicon-question-sign">&nbsp;提问</span></a></li>
                <li><a href="#"><span class="glyphicon glyphicon-list">&nbsp;回答</span></a></li>
                <li><a href="#"><span class="glyphicon glyphicon-pencil">&nbsp;写文章</span></a></li>
              </ul>
            </div>
          </div>
          <div v-for="answer in answer.content">
            <Answer :showQuestion="true" v-bind:answer="answer"></Answer>
          </div>
        </div>

        <div class="col-md-4">
          <div class="panel panel-default">
            <div class="panel-body m-activities">
              <div class="row">
                <div class="col-md-4">
                  <div class="m-activity">
                    <div class="glyphicon glyphicon-music" style="font-size:30px; color: yellow"></div>
                    <div>Live</div>
                  </div>
                </div>
                <div class="col-md-4">
                  <div class="m-activity">
                    <div class="glyphicon glyphicon-book" style="font-size:30px; color: greenyellow"></div>
                    <div>书店</div>
                  </div>
                </div>
                <div class="col-md-4">
                  <div class="m-activity">
                    <div class="glyphicon glyphicon-asterisk" style="font-size:30px; color:dodgerblue"></div>
                    <div>圆桌</div>
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="col-md-4">
                  <div class="m-activity">
                    <div class="glyphicon glyphicon-pencil" style="font-size:30px; color:dodgerblue"></div>
                    <div>专栏</div>
                  </div>
                </div>
                <div class="col-md-4">
                  <div class="m-activity">
                    <div class="glyphicon glyphicon-yen" style="font-size:30px; color: purple"></div>
                    <div>付费咨询</div>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div class="panel panel-default">
            <div class="panel-body">
              <ul class="m-side-navbar">
                <li>
                  <div class="glyphicon glyphicon-star">&nbsp;我的收藏</div>
                </li>
                <li>
                  <div class="glyphicon glyphicon-question-sign">&nbsp;我关注的问题</div>
                </li>
                <li>
                  <div class="glyphicon glyphicon-user">&nbsp;我的邀请</div>
                </li>
                <li>
                  <div class="glyphicon glyphicon-home">&nbsp;社区服务中心</div>
                </li>
                <li>
                  <div class="glyphicon glyphicon-thumbs-up">&nbsp;版权服务中心</div>
                </li>
                <li>
                  <div class="glyphicon glyphicon-pencil">&nbsp;公共编辑动态</div>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import Answer from './Answer.vue'
  import config from '../config.js'

  export default {
    created: function () {
      this.$http.get(config.baseUrl + '/answer/1').then(function (rep) {
        this.answer = rep.data.data
      })
    },
    name: 'question-area',
    data () {
      return {
        msg: 'Welcome to Your Vue.js App',
        answer: []
      }
    },
    components: {
      Answer
    }
  }
</script>
<style scoped>
  .m-side-r {
    position: fixed;
  }

  .m-activities {
    text-align: center;
  }

  .m-activity {
    padding: 15px 0px 15px;
    cursor: pointer;
  }

  .m-side-navbar {
    list-style: none;
  }

  ul.m-side-navbar {
    padding: 0px;
  }

  .m-side-navbar li {
    padding: 10px;
  }

  .m-side-navbar li:hover {
    background-color: ghostwhite;
    cursor: pointer;
  }
</style>
