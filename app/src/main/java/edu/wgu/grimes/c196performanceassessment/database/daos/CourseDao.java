package edu.wgu.grimes.c196performanceassessment.database.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import edu.wgu.grimes.c196performanceassessment.database.entities.CourseEntity;

@Dao
public interface CourseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveCourse(CourseEntity term);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAll(List<CourseEntity> terms);

    @Delete
    void deleteCourse(CourseEntity term);

    @Query("select * from courses where courseId = :courseId")
    CourseEntity selectCourseById(int courseId);

    @Query("select * from courses order by startDate asc")
    LiveData<List<CourseEntity>> selectAll();

    @Query("delete from courses")
    int deleteAll();

    @Query("select count(*) from courses")
    int selectCount();

    @Query("select * from courses where courseId = :courseId")
    LiveData<List<CourseEntity>> selectAllByTerm(int courseId);
}
