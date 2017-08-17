package cn.omsfuk.zhihu.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
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
public class Answer {

    @Id
    @GeneratedValue
    private int id;

    @Column(length = 10000)
    private String text;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date modifyDate;

    @ManyToOne
    private Question question;

    @ManyToOne
    private User answerer;

    @ManyToMany
    @JsonIgnore
    @Fetch(FetchMode.SUBSELECT)
    private List<User> liker;

    private Integer likerCount = 0;

    @OneToMany(mappedBy = "answer")
    @JsonIgnore
    @Fetch(FetchMode.SUBSELECT)
    private List<Comment> comments;

    private Integer commentCount = 0;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Answer answer = (Answer) o;

        return id == answer.id;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id;
        return result;
    }
}
