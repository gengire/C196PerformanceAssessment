package edu.wgu.grimes.c196performanceassessment.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class CourseEntity {

    @PrimaryKey(autoGenerate = true)
    private int courseId;
    private int termId;
    private int competencyUnits;
    private String code;
    private String title;
    private Date startDate;
    private Date endDate;
    private String status;
    // list mentors
    // list assessments
    // list notes

    @Ignore
    public CourseEntity() {
    }

    public CourseEntity(int courseId, int termId, int competencyUnits, String code, String title, Date startDate, Date endDate, String status) {
        this.courseId = courseId;
        this.termId = termId;
        this.competencyUnits = competencyUnits;
        this.code = code;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    @Ignore
    public CourseEntity(int termId, int competencyUnits, String code, String title, Date startDate, Date endDate, String status) {
        this.termId = termId;
        this.competencyUnits = competencyUnits;
        this.code = code;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    public int getCompetencyUnits() {
        return competencyUnits;
    }

    public void setCompetencyUnits(int competencyUnits) {
        this.competencyUnits = competencyUnits;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CourseEntity{" +
                "courseId=" + courseId +
                ", termId=" + termId +
                ", competencyUnits=" + competencyUnits +
                ", code='" + code + '\'' +
                ", title='" + title + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status='" + status + '\'' +
                '}';
    }
}
