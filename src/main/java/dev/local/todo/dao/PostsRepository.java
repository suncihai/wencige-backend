package dev.local.todo.dao;

import dev.local.todo.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.sql.Timestamp;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.List;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long>{

}
