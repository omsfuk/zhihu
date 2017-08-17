package cn.omsfuk.zhihu.dao;

import cn.omsfuk.zhihu.entity.Answer;
import cn.omsfuk.zhihu.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Talk is cheap. Show me the code
 * 多说无益，代码上见真章
 * -------  by omsfuk  2017/8/13
 */
public interface QuestionDAO extends JpaRepository<Question, Integer> {
}
