package dev.local.todo.dao;

import dev.local.todo.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.sql.Timestamp;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long>{

    @Query("from Record u where u.username=:username")
    Record findRecord(@Param("username") String username);

}
