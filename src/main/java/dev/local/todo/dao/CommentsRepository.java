package dev.local.todo.dao;

import dev.local.todo.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long>{

    @Query("from Record u where u.username=:username")
    Comments findRecord(@Param("username") String username);

}
