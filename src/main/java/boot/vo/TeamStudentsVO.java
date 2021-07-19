package boot.vo;

public class TeamStudentsVO {
    private String snu;
    private String sname;
    private int role;

    public TeamStudentsVO() {
    }

    @Override
    public String toString() {
        return "TeamUser{" +
                "snu='" + snu + '\'' +
                ", sname='" + sname + '\'' +
                ", role=" + role +
                '}';
    }

    public String getSnu() {
        return snu;
    }

    public void setSnu(String snu) {
        this.snu = snu;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public TeamStudentsVO(String snu, String sname, int role) {
        this.snu = snu;
        this.sname = sname;
        this.role = role;
    }
}
