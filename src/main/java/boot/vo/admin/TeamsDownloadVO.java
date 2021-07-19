package boot.vo.admin;

public class TeamsDownloadVO {
    private Integer teamId;
    private String teamName;
    private String teamCollege;
    private String CompetitionName;
    private Float teamScore;
    private Integer teamTime;
    private String studentNumber;
    private String studentName;
    private Integer isCaptain;

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

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getIsCaptain() {
        return isCaptain;
    }

    public void setIsCaptain(Integer isCaptain) {
        this.isCaptain = isCaptain;
    }

    @Override
    public String toString() {
        return "TeamsDownloadVO{" +
                "团队编号=" + teamId +
                ", 团队名称='" + teamName + '\'' +
                ", 团队注册学院='" + teamCollege + '\'' +
                ", 团队参与竞赛='" + CompetitionName + '\'' +
                ", 竞赛成绩=" + teamScore +
                ", 参赛时间=" + teamTime +
                ", 团队成员编号='" + studentNumber + '\'' +
                ", 团队成员姓名='" + studentName + '\'' +
                ", 团队成员角色=" + isCaptain +
                '}';
    }

    public String getTeamCollege() {
        return teamCollege;
    }

    public void setTeamCollege(String teamCollege) {
        this.teamCollege = teamCollege;
    }

    public TeamsDownloadVO() {
    }

    public TeamsDownloadVO(Integer teamId, String teamName, String teamCollege, String competitionName, Float teamScore, Integer teamTime, String studentNumber, String studentName, Integer isCaptain) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.teamCollege = teamCollege;
        CompetitionName = competitionName;
        this.teamScore = teamScore;
        this.teamTime = teamTime;
        this.studentNumber = studentNumber;
        this.studentName = studentName;
        this.isCaptain = isCaptain;
    }
}
