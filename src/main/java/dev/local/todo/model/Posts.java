package dev.local.todo.model;

import javax.persistence.*;
import org.joda.time.DateTime;
import java.sql.Timestamp;

@Entity
@Table(name = "posts")
public class Posts {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

   @Column(nullable = false)
    private String username;

   @Column(nullable = false)
    private String title;

   @Column(nullable = false)
    private String text;

   @Column(nullable = false)
    private String imageUrl;

   @Column(nullable = false)
    private Timestamp createTime;

   public Posts(){}

    public Posts(String username, String title, String text, String imageUrl, Timestamp createTime) {
        this.username = username;
        this.title = title;
        this.text = text;
        this.imageUrl = imageUrl;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
       this.createTime = createTime;
    }
}
