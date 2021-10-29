package in.purabtech.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Comment {

    @Column(name = "comment_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "body",columnDefinition = "TEXT")
    @NotEmpty(message = "*Please write something")
    private String body;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_date",nullable = false,updatable = false)
    @CreationTimestamp
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "post_id", nullable = false)
    @NotNull
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    @NotNull
    private User user;
}
