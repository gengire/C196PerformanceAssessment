package edu.wgu.grimes.c196performanceassessment.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TermDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveTerm(TermEntity term);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAll(List<TermEntity> terms);

    @Delete
    void deleteTerm(TermEntity term);

    @Query("select * from terms where termId = :termId")
    TermEntity selectTermById(int termId);

    @Query("select * from terms order by startDate asc")
    LiveData<List<TermEntity>> selectAll();

    @Query("delete from terms")
    int deleteAll();

    @Query("select count(*) from terms")
    int selectCount();
}
