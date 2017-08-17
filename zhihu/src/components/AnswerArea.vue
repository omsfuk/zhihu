<template>
  <div>
    <div class="row">
      <div class="col-md-8">
        <div class="m-title">
          <b style="font-size: 18px;">他的回答</b>
          <div class="pull-right">按时间顺序排序&nbsp;<span class="glyphicon glyphicon-menu-hamburger"></span></div>
        </div>
        <hr/>
        <div v-for="answer in answer.content">
          <Answer v-bind:answer="answer"></Answer>
        </div>
      </div>

      <div class="col-md-4">
        <PersonPageSideNavbar></PersonPageSideNavbar>
      </div>
    </div>
  </div>
</template>

<script>
  import Answer from './Answer.vue'
  import config from '../config.js'
  import PersonPageSideNavbar from './PersonalPageSideNavbar.vue'

  export default {
    created: function () {
      this.$http.get(config.baseUrl + '/answer/1').then(function (rep) {
        this.answer = rep.data.data
      })
    },
    name: 'question-area',
    data () {
      return {
        answer: []
      }
    },
    components: {
      Answer, PersonPageSideNavbar
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

  .m-title {
    margin-top: 20px;
  }
  hr {
    margin:5px 0px 20px;
  }
</style>
