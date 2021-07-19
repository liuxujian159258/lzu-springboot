package boot.vo.admin;

public class TeamsScoresVO {

    private Integer teamId;
    private String teamName;
    private String teamCaptainNumber;
    private String teamCaptain;
    private String teamCollege;
    private String competitionId;
    private String competitionName;
    private Float teamScore;
    private Integer teamTime;

    @Override
    public String toString() {
        return "TeamsScoresVO{" +
                "teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
                ", teamCaptainNumber='" + teamCaptainNumber + '\'' +
                ", teamCaptain='" + teamCaptain + '\'' +
                ", teamCollege='" + teamCollege + '\'' +
                ", competitionId='" + competitionId + '\'' +
                ", competitionName='" + competitionName + '\'' +
                ", teamScore=" + teamScore +
                ", teamTime=" + teamTime +
                '}';
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamCaptainNumber() {
        return teamCaptainNumber;
    }

    public void setTeamCaptainNumber(String teamCaptainNumber) {
        this.teamCaptainNumber = teamCaptainNumber;
    }

    public String getTeamCaptain() {
        return teamCaptain;
    }

    public void setTeamCaptain(String teamCaptain) {
        this.teamCaptain = teamCaptain;
    }

    public String getTeamCollege() {
        return teamCollege;
    }

    public void setTeamCollege(String teamCollege) {
        this.teamCollege = teamCollege;
    }

    public String getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(String competitionId) {
        this.competitionId = competitionId;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public Float getTeamScore() {
        return teamScore;
    }

    public void setTeamScore(Float teamScore) {
        this.teamScore = teamScore;
    }

    public Integer getTeamTime() {
        return teamTime;
    }

    public void setTeamTime(Integer teamTime) {
        this.teamTime = teamTime;
    }

    public TeamsScoresVO() {
    }

    public TeamsScoresVO(Integer teamId, String teamName, String teamCaptainNumber, String teamCaptain, String teamCollege, String competitionId, String competitionName, Float teamScore, Integer teamTime) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.teamCaptainNumber = teamCaptainNumber;
        this.teamCaptain = teamCaptain;
        this.teamCollege = teamCollege;
        this.competitionId = competitionId;
        this.competitionName = competitionName;
        this.teamScore = teamScore;
        this.teamTime = teamTime;
    }
}
