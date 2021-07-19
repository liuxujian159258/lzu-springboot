package boot.vo.student;

public class StudentsMyTeamsStudentsVO {

    private String studentNumber;
    private String studentName;
    private String studentCollege;
    private String studentClass;
    private String studentEmail;
    private String studentPhone;

    @Override
    public String toString() {
        return "StudentsMyTeamsStudentsVO{" +
                "studentNumber='" + studentNumber + '\'' +
                ", studentName='" + studentName + '\'' +
                ", studentCollege='" + studentCollege + '\'' +
                ", studentClass='" + studentClass + '\'' +
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

    public StudentsMyTeamsStudentsVO() {
    }

    public StudentsMyTeamsStudentsVO(String studentNumber, String studentName, String studentCollege, String studentClass, String studentEmail, String studentPhone) {
        this.studentNumber = studentNumber;
        this.studentName = studentName;
        this.studentCollege = studentCollege;
        this.studentClass = studentClass;
        this.studentEmail = studentEmail;
        this.studentPhone = studentPhone;
    }
}
