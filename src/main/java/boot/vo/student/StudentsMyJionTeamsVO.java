package boot.vo.student;

public class StudentsMyJionTeamsVO {

    private Integer teamId;
    private String teamName;
    private String teamCaptain;
    private String teamCollege;
    private Integer teamDismiss;

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

    @Override
    public String toString() {
        return "StudentsMyJionTeamsVO{" +
                "teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
                ", teamCaptain='" + teamCaptain + '\'' +
                ", teamCollege='" + teamCollege + '\'' +
                ", teamDismiss=" + teamDismiss +
                '}';
    }

    public Integer getTeamDismiss() {
        return teamDismiss;
    }

    public void setTeamDismiss(Integer teamDismiss) {
        this.teamDismiss = teamDismiss;
    }

    public StudentsMyJionTeamsVO() {
    }

    public StudentsMyJionTeamsVO(Integer teamId, String teamName, String teamCaptain, String teamCollege, Integer teamDismiss) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.teamCaptain = teamCaptain;
        this.teamCollege = teamCollege;
        this.teamDismiss = teamDismiss;
    }
}
