package in.purabtech.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id")
    private Long id;

    @Column(name = "title",nullable = false)
    @Length(min = 5,message = "*Your title must have at least 5 characters")
    @NotEmpty(message = "*Please provide title")
    private String title;

    @Column(name = "body",columnDefinition = "TEXT")
    private String body;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_date",nullable = false, updatable = false)
    @CreationTimestamp
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    @NotNull
    private User user;

    @OneToMany(mappedBy = "post",cascade = CascadeType.REMOVE)
    private Collection<Comment> comments;



}
