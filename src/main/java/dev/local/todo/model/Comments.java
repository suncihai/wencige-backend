package dev.local.todo.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "comments")
public class Comments {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

   @Column(nullable = false)
    private String username;

   @Column(nullable = false)
    private String text;

   @Column(nullable = false)
    private Timestamp createTime;

   public Comments(){}

    public Comments(String username, String text, Timestamp createTime) {
        this.username = username;
        this.text = text;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
       this.createTime = createTime;
    }
}
