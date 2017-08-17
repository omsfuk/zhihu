package cn.omsfuk.zhihu.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
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
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    private String email;

    private String password;

    private String signature;

    @JsonProperty(value = "username")
    private String name;

    private Integer gender;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date joinTime;

    @OneToMany(mappedBy = "answerer")
    @JsonIgnore
    private List<Answer> answers;

    @ManyToMany(mappedBy = "liker")
    @JsonIgnore
    private List<Answer> likedAnswers;

    @OneToMany(mappedBy = "questioner")
    @JsonIgnore
    private List<Question> questions;

    @ManyToMany(mappedBy = "follower")
    @JsonIgnore
    private List<User> followed;

    @ManyToMany
    @JsonIgnore
    private List<User> follower;

    @ManyToMany(mappedBy = "followers")
    @JsonIgnore
    private List<Question> followedQuestions;

    private Integer followerCount = 0;

    private Integer followedCount = 0;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id.hashCode();
        return result;
    }
}
