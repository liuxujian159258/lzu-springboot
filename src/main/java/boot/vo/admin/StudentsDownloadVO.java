package boot.vo.admin;

public class StudentsDownloadVO {
    private String studentNumber;
    private String studentName;
    private Integer studentSex;
    private String studentCollege;
    private String studentClass;
    private Integer studentGrade;
    private String studentEmail;
    private String studentPhone;
    private Integer competitionId;
    private String competitionName;
    private Double studentScore;
    private Integer studentTime;

    @Override
    public String toString() {
        return "StudentsDownloadVO{" +
                "学号='" + studentNumber + '\'' +
                ", 姓名='" + studentName + '\'' +
                ", 性别=" + studentSex +
                ", 学院='" + studentCollege + '\'' +
                ", 班级='" + studentClass + '\'' +
                ", 年级=" + studentGrade +
                ", 邮箱='" + studentEmail + '\'' +
                ", 手机号码='" + studentPhone + '\'' +
                ", 竞赛编号=" + competitionId +
                ", 竞赛名称='" + competitionName + '\'' +
                ", 竞赛成绩=" + studentScore +
                ", 参赛时间=" + studentTime +
                '}';
    }

    public StudentsDownloadVO() {
    }

    public StudentsDownloadVO(String studentNumber, String studentName, Integer studentSex, String studentCollege, String studentClass, Integer studentGrade, String studentEmail, String studentPhone, Integer competitionId, String competitionName, Double studentScore, Integer studentTime) {
        this.studentNumber = studentNumber;
        this.studentName = studentName;
        this.studentSex = studentSex;
        this.studentCollege = studentCollege;
        this.studentClass = studentClass;
        this.studentGrade = studentGrade;
        this.studentEmail = studentEmail;
        this.studentPhone = studentPhone;
        this.competitionId = competitionId;
        this.competitionName = competitionName;
        this.studentScore = studentScore;
        this.studentTime = studentTime;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getStudentSex() {
        return studentSex;
    }

    public void setStudentSex(Integer studentSex) {
        this.studentSex = studentSex;
    }

    public String getStudentCollege() {
        return studentCollege;
    }

    public void setStudentCollege(String studentCollege) {
        this.studentCollege = studentCollege;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public Integer getStudentGrade() {
        return studentGrade;
    }

    public void setStudentGrade(Integer studentGrade) {
        this.studentGrade = studentGrade;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
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

    public Double getStudentScore() {
        return studentScore;
    }

    public void setStudentScore(Double studentScore) {
        this.studentScore = studentScore;
    }

    public Integer getStudentTime() {
        return studentTime;
    }

    public void setStudentTime(Integer studentTime) {
        this.studentTime = studentTime;
    }
}
