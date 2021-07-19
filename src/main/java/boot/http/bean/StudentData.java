package boot.http.bean;

public class StudentData {
    private String xykh;
    private String xm;
    private String xbm;
    private String xb;
    private String dzxx;
    private String dwmc;
    private String zymc;
    private String jxrq;

    @Override
    public String toString() {
        return "StudentDate{" +
                "xykh='" + xykh + '\'' +
                ", xm='" + xm + '\'' +
                ", xbm='" + xbm + '\'' +
                ", xb='" + xb + '\'' +
                ", dzxx='" + dzxx + '\'' +
                ", dwmc='" + dwmc + '\'' +
                ", zymc='" + zymc + '\'' +
                ", jxrq='" + jxrq + '\'' +
                '}';
    }

    public StudentData(String xykh, String xm, String xbm, String xb, String dzxx, String dwmc, String zymc, String jxrq) {
        this.xykh = xykh;
        this.xm = xm;
        this.xbm = xbm;
        this.xb = xb;
        this.dzxx = dzxx;
        this.dwmc = dwmc;
        this.zymc = zymc;
        this.jxrq = jxrq;
    }

    public String getXykh() {
        return xykh;
    }

    public void setXykh(String xykh) {
        this.xykh = xykh;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getXbm() {
        return xbm;
    }

    public void setXbm(String xbm) {
        this.xbm = xbm;
    }

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }

    public String getDzxx() {
        return dzxx;
    }

    public void setDzxx(String dzxx) {
        this.dzxx = dzxx;
    }

    public String getDwmc() {
        return dwmc;
    }

    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
    }

    public String getZymc() {
        return zymc;
    }

    public void setZymc(String zymc) {
        this.zymc = zymc;
    }

    public String getJxrq() {
        return jxrq;
    }

    public void setJxrq(String jxrq) {
        this.jxrq = jxrq;
    }
}
