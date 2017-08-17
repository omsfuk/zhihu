package cn.omsfuk.zhihu.controller;

import cn.omsfuk.zhihu.base.Result;
import cn.omsfuk.zhihu.base.ResultCache;
import cn.omsfuk.zhihu.dao.QuestionDAO;
import cn.omsfuk.zhihu.dao.UserDAO;
import cn.omsfuk.zhihu.entity.Answer;
import cn.omsfuk.zhihu.entity.Question;
import cn.omsfuk.zhihu.entity.User;
import cn.omsfuk.zhihu.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Talk is cheap. Show me the code
 * 多说无益，代码上见真章
 * -------  by omsfuk  2017/8/16
 */

@RestController
@Transactional
@RequestMapping("api")
public class QuestionController {

    @Autowired
    private QuestionDAO questionDAO;

    @Autowired
    private UserDAO userDAO;

    private static Object lock = new Object();

    @RequestMapping(value = "question/{page}", method = RequestMethod.GET)
    public Result getQuestion(@PathVariable Integer page) {
        return ResultCache.getOk(questionDAO.findAll(new PageRequest(page - 1, 100)));
    }

    @RequestMapping(value = "question/id/{questionId}", method = RequestMethod.GET)
    public Result getQuestionById(@PathVariable Integer questionId) {
        Question question = null;
        synchronized (lock) {
            question = questionDAO.getOne(questionId);
            question.setPv(question.getPv() + 1);
            questionDAO.save(question);
        }
        return ResultCache.getOk(question);
    }

    @RequestMapping(value = "question/user/{username}", method = RequestMethod.GET)
    public Result getQuestionByUser(@PathVariable String username) {
        User user = userDAO.findUserByName(username);
        return ResultCache.getOk(user.getQuestions());
    }

    @RequestMapping(value = "question", method = RequestMethod.POST)
    public Result insertQuestion(Question question) {
        return ResultCache.getOk(questionDAO.save(question));
    }

    @RequestMapping(value = "question/id/{questionId}/follow", method = RequestMethod.GET)
    public Result follow(@PathVariable Integer questionId) {
        User user = SessionUtil.user();
        Question question = questionDAO.getOne(questionId);
        question.getFollowers().add(user);
        question.setFollowerCount(question.getFollowerCount() + 1);
        questionDAO.save(question);
        return ResultCache.getOk(true);
    }

    @RequestMapping(value = "question/id/{questionId}/unfollow", method = RequestMethod.GET)
    public Result unfollow(@PathVariable Integer questionId) {
        User user = SessionUtil.user();
        Question question = questionDAO.getOne(questionId);
        question.getFollowers().remove(user);
        question.setFollowerCount(question.getFollowerCount() - 1);
        questionDAO.save(question);
        return ResultCache.getOk(true);
    }

    @RequestMapping(value = "question/id/{questionId}/isFollow", method = RequestMethod.GET)
    public Result isFollow(@PathVariable Integer questionId) {
        return ResultCache.getOk(questionDAO.getOne(questionId).getFollowers().contains(SessionUtil.user()));
    }

}
