package boot.vo;

import java.util.List;

public class UserVO {
    private String snu;
    private String sname;
    private String scollege;
    private String sclass;
    private String stime;
    private String semail;
    private String sphone;
    private List<UserCompetitionVO> userCompetitionVOS;

    public UserVO(String snu, String sname, String scollege, String sclass, String stime, String semail, String sphone, List<UserCompetitionVO> userCompetitionVOS) {
        this.snu = snu;
        this.sname = sname;
        this.scollege = scollege;
        this.sclass = sclass;
        this.stime = stime;
        this.semail = semail;
        this.sphone = sphone;
        this.userCompetitionVOS = userCompetitionVOS;
    }

    public String getSclass() {
        return sclass;
    }

    public void setSclass(String sclass) {
        this.sclass = sclass;
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

    public String getScollege() {
        return scollege;
    }

    public void setScollege(String scollege) {
        this.scollege = scollege;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getSemail() {
        return semail;
    }

    public void setSemail(String semail) {
        this.semail = semail;
    }

    public String getSphone() {
        return sphone;
    }

    public void setSphone(String sphone) {
        this.sphone = sphone;
    }

    public List<UserCompetitionVO> getUserCompetitionVOS() {
        return userCompetitionVOS;
    }

    public void setUserCompetitionVOS(List<UserCompetitionVO> userCompetitionVOS) {
        this.userCompetitionVOS = userCompetitionVOS;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "snu='" + snu + '\'' +
                ", sname='" + sname + '\'' +
                ", scollege='" + scollege + '\'' +
                ", stime='" + stime + '\'' +
                ", semail='" + semail + '\'' +
                ", sphone='" + sphone + '\'' +
                ", competitions=" + userCompetitionVOS +
                '}';
    }
}
