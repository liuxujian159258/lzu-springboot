package boot.bean;

public class TeamStudent {
    private Integer teamId;
    private String studentNumber;
    private String studentName;
    private Integer isCaptain;

    @Override
    public String toString() {
        return "TeamStudent{" +
                "teamId=" + teamId +
                ", studentNumber='" + studentNumber + '\'' +
                ", studentName='" + studentName + '\'' +
                ", isCaptain=" + isCaptain +
                '}';
    }

    public TeamStudent(String studentNumber, String studentName, Integer isCaptain) {
        this.studentNumber = studentNumber;
        this.studentName = studentName;
        this.isCaptain = isCaptain;
    }

    public TeamStudent(Integer teamId, String studentNumber, String studentName, Integer isCaptain) {
        this.teamId = teamId;
        this.studentNumber = studentNumber;
        this.studentName = studentName;
        this.isCaptain = isCaptain;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public TeamStudent() {
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public Integer getIsCaptain() {
        return isCaptain;
    }

    public void setIsCaptain(Integer isCaptain) {
        this.isCaptain = isCaptain;
    }
}
