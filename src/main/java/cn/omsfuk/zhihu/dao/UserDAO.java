package cn.omsfuk.zhihu.dao;

import cn.omsfuk.zhihu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Talk is cheap. Show me the code
 * 多说无益，代码上见真章
 * -------  by omsfuk  2017/8/11
 */

public interface UserDAO extends JpaRepository<User, Integer> {

    User findUserByNameAndPassword(String name, String password);

    User findUserByName(String name);
}
