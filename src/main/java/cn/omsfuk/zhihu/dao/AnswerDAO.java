package cn.omsfuk.zhihu.dao;

import cn.omsfuk.zhihu.entity.Answer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Talk is cheap. Show me the code
 * 多说无益，代码上见真章
 * -------  by omsfuk  2017/8/13
 */
public interface AnswerDAO extends JpaRepository<Answer, Integer> {

    List<Answer> findAnswersByQuestionId(int questionId);

    @Query("select a from Answer a order by likerCount desc")
    List<Answer> findFirst5ByLikerCount(Pageable pageable);

}
