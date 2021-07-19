package boot.vo;

import java.util.List;

public class TeamVO {
    private int teamId;
    private String teamName;
    private String teamCompetition;
    private double teamScore;
    private int teamTime;
    private List<TeamStudentsVO> teamStudentsVOS;

    public TeamVO() {
    }

    @Override
    public String toString() {
        return "TeamVO{" +
                "teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
                ", teamCompetition='" + teamCompetition + '\'' +
                ", teamScore=" + teamScore +
                ", teamTime=" + teamTime +
                ", teamStudents=" + teamStudentsVOS +
                '}';
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamCompetition() {
        return teamCompetition;
    }

    public void setTeamCompetition(String teamCompetition) {
        this.teamCompetition = teamCompetition;
    }

    public double getTeamScore() {
        return teamScore;
    }

    public void setTeamScore(double teamScore) {
        this.teamScore = teamScore;
    }

    public int getTeamTime() {
        return teamTime;
    }

    public void setTeamTime(int teamTime) {
        this.teamTime = teamTime;
    }

    public List<TeamStudentsVO> getTeamStudentsVOS() {
        return teamStudentsVOS;
    }

    public void setTeamStudentsVOS(List<TeamStudentsVO> teamStudentsVOS) {
        this.teamStudentsVOS = teamStudentsVOS;
    }

    public TeamVO(int teamId, String teamName, String teamCompetition, double teamScore, int teamTime, List<TeamStudentsVO> teamStudentsVOS) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.teamCompetition = teamCompetition;
        this.teamScore = teamScore;
        this.teamTime = teamTime;
        this.teamStudentsVOS = teamStudentsVOS;
    }
}
