package cn.omsfuk.zhihu.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Talk is cheap. Show me the code
 * 多说无益，代码上见真章
 * -------  by omsfuk  2017/8/11
 */
@Entity
@Data
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Question {

    @Id
    @GeneratedValue
    private Integer id;

    private String title;

    @Column(name = "txt", length = 20000)
    private String desc;

    @OneToOne
    private User questioner;

    @ManyToMany
    @JsonIgnore
    private List<User> followers;

    private Integer pv = 0;

    private Integer followerCount = 0;

    @OneToMany(mappedBy = "question")
    @JsonIgnore
    private List<Answer> answers;

    private Integer answerCount = 0;

    @CreationTimestamp
    private Date date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Question question = (Question) o;

        return id.equals(question.id);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id.hashCode();
        return result;
    }
}
