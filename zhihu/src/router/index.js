import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/Index.vue'
import AnswerArea from '@/components/AnswerArea.vue'
import QuestionArea from '@/components/QuestionArea.vue'
import HomePage from '@/components/HomePage.vue'
import QuestionPage from '@/components/QuestionPage.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'index',
      component: Index
    },
    {
      path: '/people/:username',
      component: HomePage,
      children: [
        {
          path: '/',
          component: AnswerArea
        },
        {
          path: 'questions',
          component: QuestionArea
        }
      ]
    },
    {
      path: '/question/:questionId',
      name: 'questionPage',
      component: QuestionPage
    }
  ]
})
