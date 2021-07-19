package boot.vo.student;

import java.util.List;

public class TeamsScoreVO {

    private Integer teamId;
    private String teamName;
    private String teamCollege;
    private Integer teamTime;
    private Integer isCaptain;
    private List<CompetitionsScoreVO> competitions;

    @Override
    public String toString() {
        return "TeamsScoreVO{" +
                "teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
                ", teamCollege='" + teamCollege + '\'' +
                ", teamTime=" + teamTime +
                ", isCaptain=" + isCaptain +
                ", competitions=" + competitions +
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

    public Integer getTeamTime() {
        return teamTime;
    }

    public void setTeamTime(Integer teamTime) {
        this.teamTime = teamTime;
    }

    public Integer getIsCaptain() {
        return isCaptain;
    }

    public void setIsCaptain(Integer isCaptain) {
        this.isCaptain = isCaptain;
    }

    public List<CompetitionsScoreVO> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(List<CompetitionsScoreVO> competitions) {
        this.competitions = competitions;
    }

    public TeamsScoreVO() {
    }

    public TeamsScoreVO(Integer teamId, String teamName, String teamCollege, Integer teamTime, Integer isCaptain, List<CompetitionsScoreVO> competitions) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.teamCollege = teamCollege;
        this.teamTime = teamTime;
        this.isCaptain = isCaptain;
        this.competitions = competitions;
    }
}
