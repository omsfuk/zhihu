package cn.omsfuk.zhihu.controller;

import cn.omsfuk.zhihu.base.Result;
import cn.omsfuk.zhihu.base.ResultCache;
import cn.omsfuk.zhihu.dao.AnswerDAO;
import cn.omsfuk.zhihu.dao.QuestionDAO;
import cn.omsfuk.zhihu.dao.UserDAO;
import cn.omsfuk.zhihu.entity.Question;
import cn.omsfuk.zhihu.entity.User;
import cn.omsfuk.zhihu.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

/**
 * Talk is cheap. Show me the code
 * 多说无益，代码上见真章
 * -------  by omsfuk  2017/8/13
 */

@RestController
@RequestMapping("api")
public class MainController {

    @Autowired
    private UserDAO userDAO;


    @RequestMapping(value = "user/{page}", method = RequestMethod.GET)
    public Result getUser(@PathVariable Integer page) {
        return ResultCache.getOk(userDAO.findAll(new PageRequest(page - 1, 10)).getContent());
    }

    @RequestMapping(value = "user/name/{username}", method = RequestMethod.GET)
    public Result getUserByName(@PathVariable String username) {
        return ResultCache.getOk(userDAO.findUserByName(username));
    }

    @RequestMapping(value = "user", method = RequestMethod.POST)
    public Result insertUser(User user) {
        return ResultCache.getOk(userDAO.save(user));
    }

    @RequestMapping(value = "user/name/{username}/follow", method = RequestMethod.POST)
    public Result follow(@PathVariable String username) {
        User user = userDAO.findUserByName(username);
        User currentUser = userDAO.findUserByName(SessionUtil.user().getName());
        user.getFollower().add(currentUser);
        currentUser.getFollowed().add(user);
        userDAO.save(user);
        userDAO.save(currentUser);
        return ResultCache.OK;
    }

    @RequestMapping(value = "user/name/{username}/unfollow", method = RequestMethod.POST)
    public Result unfollow (@PathVariable String username) {
        User user = userDAO.findUserByName(username);
        User currentUser = userDAO.findUserByName(SessionUtil.user().getName());
        user.getFollower().remove(currentUser);
        currentUser.getFollowed().remove(user);
        userDAO.save(user);
        userDAO.save(currentUser);
        return ResultCache.OK;
    }

    @RequestMapping(value = "user/name/{username}/follower", method = RequestMethod.GET)
    public Result getfollower(@PathVariable String username) {
        User user = userDAO.findUserByName(username);
        return ResultCache.getOk(user.getFollower());
    }

    @RequestMapping(value = "user/name/{username}/followed", method = RequestMethod.GET)
    public Result getfollowed(@PathVariable String username) {
        User user = userDAO.findUserByName(username);
        return ResultCache.getOk(user.getFollowed());
    }

    @RequestMapping(value = "user/name/{username}/isfollow", method = RequestMethod.GET)
    public Result isfollow(@PathVariable String username) {
        User user = userDAO.findUserByName(username);
        return ResultCache.getOk(user.getFollower().contains(SessionUtil.user()));
    }

}
