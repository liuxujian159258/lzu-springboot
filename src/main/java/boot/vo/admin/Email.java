package boot.vo.admin;

public class Email {

    private String from;
    private String fromPassword;
    private String to;
    private String subject;
    private String text;
    private String competitionName;
    private Integer competitionTime;
    private Integer competitionType;

    @Override
    public String toString() {
        return "Email{" +
                "from='" + from + '\'' +
                ", fromPassword='" + fromPassword + '\'' +
                ", to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", text='" + text + '\'' +
                ", competitionName='" + competitionName + '\'' +
                ", competitionTime=" + competitionTime +
                ", competitionType=" + competitionType +
                '}';
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFromPassword() {
        return fromPassword;
    }

    public void setFromPassword(String fromPassword) {
        this.fromPassword = fromPassword;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
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

    public Integer getCompetitionType() {
        return competitionType;
    }

    public void setCompetitionType(Integer competitionType) {
        this.competitionType = competitionType;
    }

    public Email() {
    }

    public Email(String from, String fromPassword, String to, String subject, String text, String competitionName, Integer competitionTime, Integer competitionType) {
        this.from = from;
        this.fromPassword = fromPassword;
        this.to = to;
        this.subject = subject;
        this.text = text;
        this.competitionName = competitionName;
        this.competitionTime = competitionTime;
        this.competitionType = competitionType;
    }
}
