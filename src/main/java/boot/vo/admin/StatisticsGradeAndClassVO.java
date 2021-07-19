package boot.vo.admin;

public class StatisticsGradeAndClassVO {

    private String studentClass;
    private Integer studentGrade;
    private Integer number;

    @Override
    public String toString() {
        return "StatisticsGradeAndClassVO{" +
                "studentClass='" + studentClass + '\'' +
                ", studentGrade=" + studentGrade +
                ", number=" + number +
                '}';
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public StatisticsGradeAndClassVO() {
    }

    public StatisticsGradeAndClassVO(String studentClass, Integer studentGrade, Integer number) {
        this.studentClass = studentClass;
        this.studentGrade = studentGrade;
        this.number = number;
    }
}
