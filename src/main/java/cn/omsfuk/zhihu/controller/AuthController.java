package cn.omsfuk.zhihu.controller;

import cn.omsfuk.zhihu.base.Result;
import cn.omsfuk.zhihu.base.ResultCache;
import cn.omsfuk.zhihu.dao.UserDAO;
import cn.omsfuk.zhihu.entity.User;
import cn.omsfuk.zhihu.util.SessionUtil;
import org.hibernate.annotations.ResultCheckStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Talk is cheap. Show me the code
 * 多说无益，代码上见真章
 * -------  by omsfuk  2017/8/15
 */

@RestController
@RequestMapping("api")
public class AuthController {

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Result login(@RequestBody User user) {
        if ((user = userDAO.findUserByNameAndPassword(user.getName(), user.getPassword())) == null) {
            return ResultCache.FAILURE;
        }
        SessionUtil.setAttribute("user", user);
        return ResultCache.OK;
    }

    @RequestMapping(value = "isLogin", method = RequestMethod.GET)
    public Result isLogin() {
        User user = SessionUtil.user();
        if (user == null) {
            return new Result(300, null, false);
        }
        return ResultCache.getOk(user);
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public Result register(@RequestBody User user) {
        userDAO.save(user);
        return ResultCache.getOk(user);
    }
}
