package boot.bean;

public class ScoreTeam {
    private Integer teamId;
    private Integer competitionId;
    private String competitionName;
    private Double teamScore;
    private Integer teamTime;

    public ScoreTeam() {
    }

    public ScoreTeam(Integer teamId, Integer competitionId, String competitionName, Double teamScore, Integer teamTime) {
        this.teamId = teamId;
        this.competitionId = competitionId;
        this.competitionName = competitionName;
        this.teamScore = teamScore;
        this.teamTime = teamTime;
    }

    @Override
    public String toString() {
        return "ScoreTeam{" +
                "teamId=" + teamId +
                ", competitionId=" + competitionId +
                ", competitionName='" + competitionName + '\'' +
                ", teamScore=" + teamScore +
                ", teamTime=" + teamTime +
                '}';
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(Integer competitionId) {
        this.competitionId = competitionId;
    }

    public Double getTeamScore() {
        return teamScore;
    }

    public void setTeamScore(Double teamScore) {
        this.teamScore = teamScore;
    }

    public Integer getTeamTime() {
        return teamTime;
    }

    public void setTeamTime(Integer teamTime) {
        this.teamTime = teamTime;
    }

}
