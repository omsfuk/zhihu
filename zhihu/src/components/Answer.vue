<template>
  <div>
    <div class="panel panel-default">
      <div class="panel-body">
        <router-link :to="'/people/' + answer.answerer.username" class="m-header">
          <img src="http://s.amazeui.org/media/i/demos/bw-2014-06-19.jpg?imageView/1/w/1000/h/1000/q/80" width="35" height="35" class="portrait"/>
          &nbsp;<b style="font-size: 130%">{{ this.answer.answerer.username }}</b>， {{ this.answer.answerer.signature }}
        </router-link>
        <router-link v-show="showQuestion" :to="'/question/' + answer.question.id">
          <div style="font-size: 24px; color: black;padding:10px 0px 5px;font-weight: bold;">{{ answer.question.title }}</div>
        </router-link>
        <div class="m-body">
          <p v-show="!isExpand" class="s-unexpand" v-on:click="expandText">
            {{ answer.text | text }}
            &nbsp;&nbsp;<span style="color: darkgray">阅读全文&nbsp;<span class="glyphicon glyphicon-menu-down"></span></span>
          </p>
          <div v-show="isExpand">
            <p v-html="markdown(answer.text)">
            </p>
            <p style="font-size: 80%;color: gray;text-align: right">
              最后编辑于&nbsp;&nbsp;{{ answer.modifyDate | format }}<br/>
              创建于&nbsp;&nbsp;{{ answer.createDate | format }}
            </p>
          </div>
        </div>
        <div class="m-footer">
          <Like :like="true" :answer="answer" :support="support"></Like>
          <div class="other-btns">
            <IconBtn icon-name="comment" btn-title="评论"></IconBtn>
            <IconBtn icon-name="share" btn-title="分享"></IconBtn>
            <IconBtn icon-name="star" btn-title="收藏"></IconBtn>
            <IconBtn icon-name="heart" btn-title="感谢"></IconBtn>
            <div class="pull-right" v-on:click="expandText" v-show="isExpand">
              <IconBtn icon-name="menu-up" btn-title="收起"></IconBtn>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Like from './Like.vue'
import Vue from 'vue'
import IconBtn from './IconBtn.vue'
import LoginBtn from './LoginBtn.vue'
import MarkdownIt from 'markdown-it'
export default {
  name: 'answer',
  props: {
    answer: Object,
    showQuestion: {
      default: false
    }
  },
  data () {
    return {
      isExpand: false,
      support: false
    }
  },
  methods: {
    expandText: function () {
      this.isExpand = !this.isExpand
    },
    markdown: function (str) {
      let md = new MarkdownIt()
      return md.render(str)
    }
  },
  components: {
    Like, IconBtn, LoginBtn
  }
}
Vue.filter('text', function (html) {
  var re = new RegExp('<.+?>', 'g')
  var msg = html.replace(re, '')
  return msg.substr(0, 200) + '... ...'
})
</script>
<style scoped>
  .other-btns {
    width:85%;
    display: inline-block;
    padding:0px 0px 0px 20px;
  }
  .m-header {
    width:100%;
    padding: 0px 0px 15px;
    color: black;
  }
  .m-header:hover {
    color: black;
    text-decoration: none;
  }
  .m-body {
    width:100%;
    padding: 5px 0px 5px;
  }
  .m-footer {
    width:100%;
    padding:5px 0px 0px;
  }
  .s-unexpand:hover {
    color: gray;
    cursor: pointer;
  }
  p {
    line-height: 30px;
  }
  a:hover {
    text-decoration: none;
  }
  .portrait {
    margin: 0px;
    border-radius: 3px;
  }
</style>
