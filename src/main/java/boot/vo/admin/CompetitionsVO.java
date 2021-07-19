package boot.vo.admin;

public class CompetitionsVO {

    private Integer competitionId;
    private String competitionName;
    private Double studentScore;
    private Integer studentTime;

    @Override
    public String toString() {
        return "competitions{" +
                "competitionId=" + competitionId +
                ", competitionName='" + competitionName + '\'' +
                ", studentScore=" + studentScore +
                ", studentTime=" + studentTime +
                '}';
    }

    public Integer getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(Integer competitionId) {
        this.competitionId = competitionId;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
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

    public CompetitionsVO() {
    }

    public CompetitionsVO(Integer competitionId, String competitionName, Double studentScore, Integer studentTime) {
        this.competitionId = competitionId;
        this.competitionName = competitionName;
        this.studentScore = studentScore;
        this.studentTime = studentTime;
    }
}
