package boot.vo.student;

import java.util.List;

public class StudentsMyTeamsVO {

    private Integer teamId;
    private String teamName;
    private String teamCollege;
    private Integer teamDismiss;
    private List<StudentsMyTeamsStudentsVO> teamsStudent;

    @Override
    public String toString() {
        return "StudentsMyTeamsVO{" +
                "teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
                ", teamCollege='" + teamCollege + '\'' +
                ", teamDismiss=" + teamDismiss +
                ", teamsStudent=" + teamsStudent +
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

    public Integer getTeamDismiss() {
        return teamDismiss;
    }

    public void setTeamDismiss(Integer teamDismiss) {
        this.teamDismiss = teamDismiss;
    }

    public List<StudentsMyTeamsStudentsVO> getTeamsStudent() {
        return teamsStudent;
    }

    public void setTeamsStudent(List<StudentsMyTeamsStudentsVO> teamsStudent) {
        this.teamsStudent = teamsStudent;
    }

    public StudentsMyTeamsVO() {
    }

    public StudentsMyTeamsVO(Integer teamId, String teamName, String teamCollege, Integer teamDismiss, List<StudentsMyTeamsStudentsVO> teamsStudent) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.teamCollege = teamCollege;
        this.teamDismiss = teamDismiss;
        this.teamsStudent = teamsStudent;
    }
}
