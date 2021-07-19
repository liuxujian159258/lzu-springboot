package boot.vo.student;

import java.util.List;

public class StudentsScoreVO {

    private String studentNumber;
    private String studentName;
    private String studentCollege;
    private String studentClass;
    private Integer studentTime;
    private List<CompetitionsScoreVO> competitions;

    @Override
    public String toString() {
        return "StudentsScoreVO{" +
                "studentNumber='" + studentNumber + '\'' +
                ", studentName='" + studentName + '\'' +
                ", studentCollege='" + studentCollege + '\'' +
                ", studentClass='" + studentClass + '\'' +
                ", studentTime=" + studentTime +
                ", competitions=" + competitions +
                '}';
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentCollege() {
        return studentCollege;
    }

    public void setStudentCollege(String studentCollege) {
        this.studentCollege = studentCollege;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public Integer getStudentTime() {
        return studentTime;
    }

    public void setStudentTime(Integer studentTime) {
        this.studentTime = studentTime;
    }

    public List<CompetitionsScoreVO> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(List<CompetitionsScoreVO> competitions) {
        this.competitions = competitions;
    }

    public StudentsScoreVO() {
    }

    public StudentsScoreVO(String studentNumber, String studentName, String studentCollege, String studentClass, Integer studentTime, List<CompetitionsScoreVO> competitions) {
        this.studentNumber = studentNumber;
        this.studentName = studentName;
        this.studentCollege = studentCollege;
        this.studentClass = studentClass;
        this.studentTime = studentTime;
        this.competitions = competitions;
    }
}
