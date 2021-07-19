package boot.bean;

public class Competition {

    private Integer competitionId;
    private String competitionName;
    private Integer competitionType;
    private Integer competitionPeople;
    private String competitionDepartment;
    private Integer isDelete;

    @Override
    public String toString() {
        return "Competition{" +
                "competitionId=" + competitionId +
                ", competitionName='" + competitionName + '\'' +
                ", competitionType=" + competitionType +
                ", competitionPeople=" + competitionPeople +
                ", competitionDepartment='" + competitionDepartment + '\'' +
                ", isDelete=" + isDelete +
                '}';
    }

    public Competition() {
    }

    public Integer getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(Integer competitionId) {
        this.competitionId = competitionId;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public Integer getCompetitionType() {
        return competitionType;
    }

    public void setCompetitionType(Integer competitionType) {
        this.competitionType = competitionType;
    }

    public Integer getCompetitionPeople() {
        return competitionPeople;
    }

    public void setCompetitionPeople(Integer competitionPeople) {
        this.competitionPeople = competitionPeople;
    }

    public String getCompetitionDepartment() {
        return competitionDepartment;
    }

    public void setCompetitionDepartment(String competitionDepartment) {
        this.competitionDepartment = competitionDepartment;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Competition(Integer competitionId, String competitionName, Integer competitionType, Integer competitionPeople, String competitionDepartment, Integer isDelete) {
        this.competitionId = competitionId;
        this.competitionName = competitionName;
        this.competitionType = competitionType;
        this.competitionPeople = competitionPeople;
        this.competitionDepartment = competitionDepartment;
        this.isDelete = isDelete;
    }
}
