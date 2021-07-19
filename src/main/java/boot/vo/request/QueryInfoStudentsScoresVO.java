package boot.vo.request;

public class QueryInfoStudentsScoresVO {

    private String studentNumberOrName;
    private Integer studentTime;
    private Integer competitionId;
    private Integer pageNum;
    private Integer pageSize;

    @Override
    public String toString() {
        return "QueryInfoStudentsScoresVO{" +
                "studentNumberOrName='" + studentNumberOrName + '\'' +
                ", studentTime=" + studentTime +
                ", competitionId=" + competitionId +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }

    public String getStudentNumberOrName() {
        return studentNumberOrName;
    }

    public void setStudentNumberOrName(String studentNumberOrName) {
        this.studentNumberOrName = studentNumberOrName;
    }

    public Integer getStudentTime() {
        return studentTime;
    }

    public void setStudentTime(Integer studentTime) {
        this.studentTime = studentTime;
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

    public QueryInfoStudentsScoresVO() {
    }

    public QueryInfoStudentsScoresVO(String studentNumberOrName, Integer studentTime, Integer competitionId, Integer pageNum, Integer pageSize) {
        this.studentNumberOrName = studentNumberOrName;
        this.studentTime = studentTime;
        this.competitionId = competitionId;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
}
