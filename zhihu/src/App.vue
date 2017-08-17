<template>
  <div id="app">
    <Navbar></Navbar>
    <router-view class="m-content container"></router-view>
  </div>
</template>

<script>
import Navbar from './components/Navbar.vue'
import AnswerArea from './components/AnswerArea.vue'

export default {
  name: 'app',
  data: function () {
    return {
      login: false
    }
  },
  components: {
    Navbar, AnswerArea
  }
}
</script>
<style scoped>
  .m-content {
    margin-top: 70px;
    margin-bottom: 20px;
  }
</style>
