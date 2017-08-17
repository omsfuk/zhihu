package cn.omsfuk.zhihu.dao;

import cn.omsfuk.zhihu.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Talk is cheap. Show me the code
 * 多说无益，代码上见真章
 * -------  by omsfuk  2017/8/13
 */
public interface CommentDAO extends JpaRepository<Comment, Integer> {
}
