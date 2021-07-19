package boot.bean;

public class Team {
    private Integer teamId;
    private String teamName;
    private String teamCollege;
    private Integer teamDismiss;

    @Override
    public String toString() {
        return "Team{" +
                "teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
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

    public Team(Integer teamId, String teamName, String teamCollege, Integer teamDismiss) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.teamCollege = teamCollege;
        this.teamDismiss = teamDismiss;
    }

    public Team(Integer teamId, String teamName, String teamCollege) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.teamCollege = teamCollege;
    }

    public String getTeamCollege() {
        return teamCollege;
    }

    public void setTeamCollege(String teamCollege) {
        this.teamCollege = teamCollege;
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

    public Team() {
    }

    public Team(Integer teamId, String teamName) {
        this.teamId = teamId;
        this.teamName = teamName;
    }
}
