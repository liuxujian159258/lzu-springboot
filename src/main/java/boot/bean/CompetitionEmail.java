package boot.bean;

public class CompetitionEmail {

    private Integer competitionId;
    private String competitionName;
    private String subject;
    private String text;

    @Override
    public String toString() {
        return "CompetitionEmail{" +
                "competitionId=" + competitionId +
                ", competitionName='" + competitionName + '\'' +
                ", subject='" + subject + '\'' +
                ", text='" + text + '\'' +
                '}';
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public CompetitionEmail() {
    }

    public CompetitionEmail(Integer competitionId, String competitionName, String subject, String text) {
        this.competitionId = competitionId;
        this.competitionName = competitionName;
        this.subject = subject;
        this.text = text;
    }
}
