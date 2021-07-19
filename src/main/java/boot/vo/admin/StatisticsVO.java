package boot.vo.admin;

public class StatisticsVO {

    private String competitionName;
    private Integer competitionTime;
    private Integer number;

    @Override
    public String toString() {
        return "StatisticsVO{" +
                "competitionName='" + competitionName + '\'' +
                ", competitionTime=" + competitionTime +
                ", number=" + number +
                '}';
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public Integer getCompetitionTime() {
        return competitionTime;
    }

    public void setCompetitionTime(Integer competitionTime) {
        this.competitionTime = competitionTime;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public StatisticsVO() {
    }

    public StatisticsVO(String competitionName, Integer competitionTime, Integer number) {
        this.competitionName = competitionName;
        this.competitionTime = competitionTime;
        this.number = number;
    }
}
