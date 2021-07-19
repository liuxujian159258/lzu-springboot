package boot.vo.admin;

import java.util.List;

public class TeamsVO {

    private Integer teamId;
    private String teamName;
    private String teamCollege;
    private Integer competitionId;
    private String CompetitionName;
    private Float teamScore;
    private Integer teamTime;
    private List<TeamStudentsVO> teamStudents;

    @Override
    public String toString() {
        return "TeamsVO{" +
                "teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
                ", teamCollege='" + teamCollege + '\'' +
                ", competitionId=" + competitionId +
                ", CompetitionName='" + CompetitionName + '\'' +
                ", teamScore=" + teamScore +
                ", teamTime=" + teamTime +
                ", teamStudents=" + teamStudents +
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

    public String getTeamCollege() {
        return teamCollege;
    }

    public void setTeamCollege(String teamCollege) {
        this.teamCollege = teamCollege;
    }

    public String getCompetitionName() {
        return CompetitionName;
    }

    public void setCompetitionName(String competitionName) {
        CompetitionName = competitionName;
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

    public List<TeamStudentsVO> getTeamStudents() {
        return teamStudents;
    }

    public void setTeamStudents(List<TeamStudentsVO> teamStudents) {
        this.teamStudents = teamStudents;
    }

    public Integer getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(Integer competitionId) {
        this.competitionId = competitionId;
    }

    public TeamsVO() {
    }

    public TeamsVO(Integer teamId, String teamName, String teamCollege, Integer competitionId, String competitionName, Float teamScore, Integer teamTime, List<TeamStudentsVO> teamStudents) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.teamCollege = teamCollege;
        this.competitionId = competitionId;
        CompetitionName = competitionName;
        this.teamScore = teamScore;
        this.teamTime = teamTime;
        this.teamStudents = teamStudents;
    }
}
