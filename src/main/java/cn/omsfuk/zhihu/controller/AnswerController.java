package cn.omsfuk.zhihu.controller;

import cn.omsfuk.zhihu.base.Result;
import cn.omsfuk.zhihu.base.ResultCache;
import cn.omsfuk.zhihu.dao.AnswerDAO;
import cn.omsfuk.zhihu.dao.UserDAO;
import cn.omsfuk.zhihu.entity.Answer;
import cn.omsfuk.zhihu.entity.User;
import cn.omsfuk.zhihu.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Talk is cheap. Show me the code
 * 多说无益，代码上见真章
 * -------  by omsfuk  2017/8/16
 */

@RestController
@Transactional
@RequestMapping("api")
public class AnswerController {

    @Autowired
    private AnswerDAO answerDAO;

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(value = "answer/{page}", method = RequestMethod.GET)
    public Result answer(@PathVariable Integer page, Integer userId) {
        return ResultCache.getOk(answerDAO.findAll(new PageRequest(page - 1, 10)));
    }

    @RequestMapping(value = "answer/questionId/{questionId}", method = RequestMethod.GET)
    public Result getAnswerByQuestionId(@PathVariable Integer questionId) {
        return ResultCache.getOk(answerDAO.findAnswersByQuestionId(questionId));
    }

    @RequestMapping(value = "answer/user/{username}", method = RequestMethod.GET)
    public Result getAnswerByUser(@PathVariable String username) {
        User user = userDAO.findUserByName(username);
        return ResultCache.getOk(user.getAnswers());
    }

    @RequestMapping(value = "answer/id/{answerId}/like", method = RequestMethod.GET)
    public Result upvote(@PathVariable Integer answerId) {
        User user = SessionUtil.user();
        Answer answer = answerDAO.getOne(answerId);
        answer.getLiker().add(user);
        answer.setLikerCount(answer.getLikerCount() + 1);
        answerDAO.save(answer);
        return ResultCache.OK;
    }

    @RequestMapping(value = "answer/id/{answerId}/unlike", method = RequestMethod.GET)
    public Result downvote(@PathVariable Integer answerId) {
        User user = SessionUtil.user();
        Answer answer = answerDAO.getOne(answerId);
        answer.getLiker().remove(user);
        answer.setLikerCount(answer.getLikerCount() - 1);
        answerDAO.save(answer);
        return ResultCache.OK;
    }

    @RequestMapping(value = "getComment", method = RequestMethod.GET)
    public Result getComment(@PathVariable Integer answerId) {
        Answer answer = answerDAO.getOne(answerId);
        return ResultCache.getOk(answer.getComments());
    }

    @RequestMapping(value = "answer/id/{answerId}/isSupport", method = RequestMethod.GET)
    public Result isSupport(@PathVariable Integer answerId) {
        User user = SessionUtil.user();
        User aUser = new User();
        aUser.setId(1);
        List<User> users = answerDAO.getOne(answerId).getLiker();
        return ResultCache.getOk(answerDAO.getOne(answerId).getLiker().contains(SessionUtil.user()));
    }

    @RequestMapping(value = "answer", method = RequestMethod.POST)
    public Result answer(@RequestBody Answer answer) {
        answer.setAnswerer(SessionUtil.user());
        return ResultCache.getOk(answerDAO.save(answer));
    }
    
    @RequestMapping(value = "answer/brilliant", method = RequestMethod.GET)
    public Result brilliant() {
        return ResultCache.getOk(answerDAO.findFirst5ByLikerCount(new PageRequest(0, 5)));
    }
}
