package boot.vo.admin;

public class TeamStudentsVO {

    private String studentNumber;
    private String studentName;
    private Integer isCaptain;

    @Override
    public String toString() {
        return "TeamStudents{" +
                "studentNumber='" + studentNumber + '\'' +
                ", studentName='" + studentName + '\'' +
                ", isCaptain=" + isCaptain +
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

    public Integer getIsCaptain() {
        return isCaptain;
    }

    public void setIsCaptain(Integer isCaptain) {
        this.isCaptain = isCaptain;
    }

    public TeamStudentsVO() {
    }

    public TeamStudentsVO(String studentNumber, String studentName, Integer isCaptain) {
        this.studentNumber = studentNumber;
        this.studentName = studentName;
        this.isCaptain = isCaptain;
    }
}
