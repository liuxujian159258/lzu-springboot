package boot.vo.student;

public class CompetitionsScoreVO {

    private Integer competitionId;
    private String competitionName;
    private Float competitionScore;

    @Override
    public String toString() {
        return "CompetitionsScoreVO{" +
                "competitionId=" + competitionId +
                ", competitionName='" + competitionName + '\'' +
                ", competitionScore=" + competitionScore +
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

    public Float getCompetitionScore() {
        return competitionScore;
    }

    public void setCompetitionScore(Float competitionScore) {
        this.competitionScore = competitionScore;
    }

    public CompetitionsScoreVO() {
    }

    public CompetitionsScoreVO(Integer competitionId, String competitionName, Float competitionScore) {
        this.competitionId = competitionId;
        this.competitionName = competitionName;
        this.competitionScore = competitionScore;
    }
}
