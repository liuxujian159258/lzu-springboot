package boot.vo.admin;

import java.util.List;

public class TeamsNotSignUpVO {

    private Integer teamId;
    private String teamName;
    private String teamCollege;
    private List<TeamStudentsVO> teamStudents;

    @Override
    public String toString() {
        return "TeamsNotSignUpVO{" +
                "teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
                ", teamCollege='" + teamCollege + '\'' +
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

    public List<TeamStudentsVO> getTeamStudents() {
        return teamStudents;
    }

    public void setTeamStudents(List<TeamStudentsVO> teamStudents) {
        this.teamStudents = teamStudents;
    }

    public TeamsNotSignUpVO() {
    }

    public TeamsNotSignUpVO(Integer teamId, String teamName, String teamCollege, List<TeamStudentsVO> teamStudents) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.teamCollege = teamCollege;
        this.teamStudents = teamStudents;
    }
}
