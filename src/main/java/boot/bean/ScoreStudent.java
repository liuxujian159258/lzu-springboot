package boot.bean;

public class ScoreStudent {
    private String studentNumber;
    private Integer competitionId;
    private String competitionName;
    private Double studentScore;
    private Integer studentTime;

    public ScoreStudent() {
    }

    public String getCompetitionName() {
        return competitionName;
    }

    @Override
    public String toString() {
        return "ScoreStudent{" +
                "studentNumber='" + studentNumber + '\'' +
                ", competitionId=" + competitionId +
                ", competitionName='" + competitionName + '\'' +
                ", studentScore=" + studentScore +
                ", studentTime=" + studentTime +
                '}';
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public ScoreStudent(String studentNumber, Integer competitionId, String competitionName, Double studentScore, Integer studentTime) {
        this.studentNumber = studentNumber;
        this.competitionId = competitionId;
        this.competitionName = competitionName;
        this.studentScore = studentScore;
        this.studentTime = studentTime;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public Integer getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(Integer competitionId) {
        this.competitionId = competitionId;
    }

    public Double getStudentScore() {
        return studentScore;
    }

    public void setStudentScore(Double studentScore) {
        this.studentScore = studentScore;
    }

    public Integer getStudentTime() {
        return studentTime;
    }

    public void setStudentTime(Integer studentTime) {
        this.studentTime = studentTime;
    }
}
