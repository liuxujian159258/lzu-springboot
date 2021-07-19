package boot.bean;

public class Student {

    private String studentNumber;
    private String studentPassword;
    private String studentName;
    private Integer studentSex;
    private String studentCollege;
    private String studentClass;
    private Integer studentGrade;
    private String studentEmail;
    private String studentPhone;

    public Student(String studentNumber, String studentPassword, String studentName, Integer studentSex, String studentCollege, String studentClass, Integer studentGrade, String studentEmail, String studentPhone) {
        this.studentNumber = studentNumber;
        this.studentPassword = studentPassword;
        this.studentName = studentName;
        this.studentSex = studentSex;
        this.studentCollege = studentCollege;
        this.studentClass = studentClass;
        this.studentGrade = studentGrade;
        this.studentEmail = studentEmail;
        this.studentPhone = studentPhone;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentNumber='" + studentNumber + '\'' +
                ", studentPassword='" + studentPassword + '\'' +
                ", studentName='" + studentName + '\'' +
                ", studentSex=" + studentSex +
                ", studentCollege='" + studentCollege + '\'' +
                ", studentClass='" + studentClass + '\'' +
                ", studentTime=" + studentGrade +
                ", studentEmail='" + studentEmail + '\'' +
                ", studentPhone='" + studentPhone + '\'' +
                '}';
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
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
}
