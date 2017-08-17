<template>
  <div style="display:inline-block">
    <LoginBtn :fun="handleClick">
      <button v-bind:class="['btn', !support ? 'btn-default' : 'btn-primary']">
        <span v-bind:class="['glyphicon', like == true ? 'glyphicon-triangle-top' : 'glyphicon-triangle-bottom']"></span> {{count}}
      </button>
    </LoginBtn>
  </div>
</template>

<script>
import LoginBtn from './LoginBtn.vue'
import config from '../config.js'
export default {
  name: 'like',
  props: ['like', 'answer'],
  created: function () {
    this.$http.get(config.baseUrl + '/answer/id/' + this.answer.id + '/isSupport')
      .then(function (rep) {
        this.support = rep.data.data
      })
  },
  data () {
    return {
      count: 1000,
      support: false
    }
  },
  methods: {
    handleClick: function () {
      let requestUrl = config.baseUrl + '/answer/id/' + this.answer.id + (this.support ? '/unlike' : '/like')
      this.$http.get(requestUrl)
        .then(function (rep) {
          if (parseInt(rep.data.status) === 200) {
            this.support = !this.support
          }
        })
    }
  },
  components: {
    LoginBtn
  }
}
</script>
