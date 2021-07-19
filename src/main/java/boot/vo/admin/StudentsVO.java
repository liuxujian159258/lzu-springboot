package boot.vo.admin;

import java.util.List;

public class StudentsVO {

    private String studentNumber;
    private String studentName;
    private Integer studentSex;
    private String studentCollege;
    private String studentClass;
    private Integer studentGrade;
    private String studentEmail;
    private String studentPhone;
    private List<CompetitionsVO> competitions;

    @Override
    public String toString() {
        return "StudentsVO{" +
                "studentNumber='" + studentNumber + '\'' +
                ", studentName='" + studentName + '\'' +
                ", studentSex=" + studentSex +
                ", studentCollege='" + studentCollege + '\'' +
                ", studentClass='" + studentClass + '\'' +
                ", studentGrade=" + studentGrade +
                ", studentEmail='" + studentEmail + '\'' +
                ", studentPhone='" + studentPhone + '\'' +
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

    public Integer getStudentSex() {
        return studentSex;
    }

    public void setStudentSex(Integer studentSex) {
        this.studentSex = studentSex;
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

    public Integer getStudentGrade() {
        return studentGrade;
    }

    public void setStudentGrade(Integer studentGrade) {
        this.studentGrade = studentGrade;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    public List<CompetitionsVO> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(List<CompetitionsVO> competitions) {
        this.competitions = competitions;
    }

    public StudentsVO() {
    }

    public StudentsVO(String studentNumber, String studentName, Integer studentSex, String studentCollege, String studentClass, Integer studentGrade, String studentEmail, String studentPhone, List<CompetitionsVO> competitions) {
        this.studentNumber = studentNumber;
        this.studentName = studentName;
        this.studentSex = studentSex;
        this.studentCollege = studentCollege;
        this.studentClass = studentClass;
        this.studentGrade = studentGrade;
        this.studentEmail = studentEmail;
        this.studentPhone = studentPhone;
        this.competitions = competitions;
    }
}
