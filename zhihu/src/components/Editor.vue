<template>
  <div>
    <mavon-editor id="editor" style="height: 100%" default_open="editor" v-model="value"></mavon-editor>
    <button class="btn btn-primary pull-right" @click="upload">提交回答</button>
  </div>
</template>
<script>
  // Local Registration
  import { mavonEditor } from 'mavon-editor'
  import 'mavon-editor/dist/css/index.css'
  import config from '../config.js'
  export default {
    name: 'Editor',
    props: ['question'],
    data () {
      return {
        value: ''
      }
    },
    methods: {
      upload: function () {
        this.$http({
          url: config.baseUrl + '/answer',
          method: 'post',
          body: {
            text: this.value,
            question: {
              id: this.question.id
            }
          }
        })
        console.log(this.value)
      }
    },
    components: {
      mavonEditor
      // or 'mavon-editor': mavonEditor
    }
  }
</script>
<style>
  #editor {
    padding-bottom: 10px;
    height: 200px
  }
  .portrait {
    margin: 10px;
    -webkit-border-radius: 3px;
    -moz-border-radius: 3px;
    border-radius: 3px;
  }
</style>
