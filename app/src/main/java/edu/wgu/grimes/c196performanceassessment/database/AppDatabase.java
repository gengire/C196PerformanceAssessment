package edu.wgu.grimes.c196performanceassessment.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import edu.wgu.grimes.c196performanceassessment.database.daos.CourseDao;
import edu.wgu.grimes.c196performanceassessment.database.daos.TermDao;
import edu.wgu.grimes.c196performanceassessment.database.entities.CourseEntity;
import edu.wgu.grimes.c196performanceassessment.database.entities.TermEntity;

@Database(entities = {TermEntity.class, CourseEntity.class}, version = 1)
@TypeConverters({DateConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "c196pa.db";
    private static volatile AppDatabase instance;
    private static final Object LOCK = new Object();

    public abstract TermDao termDao();
    public abstract CourseDao courseDao();
//    public abstract AssessmentDao assessmentDao();

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }

}
