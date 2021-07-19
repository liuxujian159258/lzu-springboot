package boot.bean;

public class CompetitionHtml {

    private Integer competitionId;
    private String competitionHtml;

    @Override
    public String toString() {
        return "CompetitionHtml{" +
                "competitionId=" + competitionId +
                ", competitionHtml='" + competitionHtml + '\'' +
                '}';
    }

    public Integer getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(Integer competitionId) {
        this.competitionId = competitionId;
    }

    public String getCompetitionHtml() {
        return competitionHtml;
    }

    public void setCompetitionHtml(String competitionHtml) {
        this.competitionHtml = competitionHtml;
    }

    public CompetitionHtml() {
    }

    public CompetitionHtml(Integer competitionId, String competitionHtml) {
        this.competitionId = competitionId;
        this.competitionHtml = competitionHtml;
    }
}
