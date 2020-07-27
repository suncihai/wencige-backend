package dev.local.todo.model;

import javax.persistence.*;

@Entity
@Table(name = "problem")
public class Problem {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

   @Column(nullable = false)
    private int number;

   @Column(nullable = false)
    private String name;

   @Column(nullable = false)
    private String category;

   @Column(nullable = false)
    private String difficulty;

   @Column(nullable = false)
    private String tag;

   public Problem(){}

    public Problem(int number, String name, String category, String difficulty, String tag) {
        this.number = number;
        this.name = name;
        this.category = category;
        this.difficulty = difficulty;
        this.tag = tag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
       this.difficulty = difficulty;
    }

    public String getTag() {
       return tag;
    }

    public void setTag(String tag) {
       this.tag = tag;
    }
}
