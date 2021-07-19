package boot.vo;

public class CompetitionVO {
    private int competitionId;
    private String competitionName;
    private String competitionBackground;
    private String competitionBackgroundImage;
    private String competitionRequired;
    private String competitionRequiredImage;
    private String competitionReviewRules;
    private String competitionReviewRulesImage;

    public CompetitionVO() {
    }

    @Override
    public String toString() {
        return "CompetitionVO{" +
                "competitionId=" + competitionId +
                ", competitionName='" + competitionName + '\'' +
                ", competitionBackground='" + competitionBackground + '\'' +
                ", competitionBackgroundImage='" + competitionBackgroundImage + '\'' +
                ", competitionRequired='" + competitionRequired + '\'' +
                ", competitionRequiredImage='" + competitionRequiredImage + '\'' +
                ", competitionReviewRules='" + competitionReviewRules + '\'' +
                ", competitionReviewRulesImage='" + competitionReviewRulesImage + '\'' +
                '}';
    }

    public int getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(int competitionId) {
        this.competitionId = competitionId;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public String getCompetitionBackground() {
        return competitionBackground;
    }

    public void setCompetitionBackground(String competitionBackground) {
        this.competitionBackground = competitionBackground;
    }

    public String getCompetitionBackgroundImage() {
        return competitionBackgroundImage;
    }

    public void setCompetitionBackgroundImage(String competitionBackgroundImage) {
        this.competitionBackgroundImage = competitionBackgroundImage;
    }

    public String getCompetitionRequired() {
        return competitionRequired;
    }

    public void setCompetitionRequired(String competitionRequired) {
        this.competitionRequired = competitionRequired;
    }

    public String getCompetitionRequiredImage() {
        return competitionRequiredImage;
    }

    public void setCompetitionRequiredImage(String competitionRequiredImage) {
        this.competitionRequiredImage = competitionRequiredImage;
    }

    public String getCompetitionReviewRules() {
        return competitionReviewRules;
    }

    public void setCompetitionReviewRules(String competitionReviewRules) {
        this.competitionReviewRules = competitionReviewRules;
    }

    public String getCompetitionReviewRulesImage() {
        return competitionReviewRulesImage;
    }

    public void setCompetitionReviewRulesImage(String competitionReviewRulesImage) {
        this.competitionReviewRulesImage = competitionReviewRulesImage;
    }

    public CompetitionVO(int competitionId, String competitionName, String competitionBackground, String competitionBackgroundImage, String competitionRequired, String competitionRequiredImage, String competitionReviewRules, String competitionReviewRulesImage) {
        this.competitionId = competitionId;
        this.competitionName = competitionName;
        this.competitionBackground = competitionBackground;
        this.competitionBackgroundImage = competitionBackgroundImage;
        this.competitionRequired = competitionRequired;
        this.competitionRequiredImage = competitionRequiredImage;
        this.competitionReviewRules = competitionReviewRules;
        this.competitionReviewRulesImage = competitionReviewRulesImage;
    }
}
