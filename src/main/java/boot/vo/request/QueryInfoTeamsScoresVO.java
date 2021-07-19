package boot.vo.request;

public class QueryInfoTeamsScoresVO {

    private String teamIdOrName;
    private Integer teamTime;
    private Integer competitionId;
    private Integer pageNum;
    private Integer pageSize;

    @Override
    public String toString() {
        return "QueryInfoTeamsScoresVO{" +
                "teamIdOrName='" + teamIdOrName + '\'' +
                ", teamTime=" + teamTime +
                ", competitionId=" + competitionId +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }

    public String getTeamIdOrName() {
        return teamIdOrName;
    }

    public void setTeamIdOrName(String teamIdOrName) {
        this.teamIdOrName = teamIdOrName;
    }

    public Integer getTeamTime() {
        return teamTime;
    }

    public void setTeamTime(Integer teamTime) {
        this.teamTime = teamTime;
    }

    public Integer getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(Integer competitionId) {
        this.competitionId = competitionId;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public QueryInfoTeamsScoresVO() {
    }

    public QueryInfoTeamsScoresVO(String teamIdOrName, Integer teamTime, Integer competitionId, Integer pageNum, Integer pageSize) {
        this.teamIdOrName = teamIdOrName;
        this.teamTime = teamTime;
        this.competitionId = competitionId;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
}
