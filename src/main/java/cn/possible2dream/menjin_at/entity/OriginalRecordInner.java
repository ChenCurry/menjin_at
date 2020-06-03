package cn.possible2dream.menjin_at.entity;

public class OriginalRecordInner {

    private String zuizaojinru;
    private String zuihouchuqu;
    private Integer times;//次数
    private String innerTime;//室内时长

    //SC_AccessRecord 表
    private Long scCardguidno;//'%'+SC_CardGuidNO   卡号 10忆  不行的话换成Long
    //SC_Employee 表
    private String scWorkerno;//工号
    private String scName;//姓名
    private String scMobileno;//完整卡号
    private Integer scIdtypeid;//类型
    private Integer scDepartmentid;//部门
    //SC_Department 表
    private String scDepartmentname;

    public String getZuizaojinru() {
        return zuizaojinru;
    }

    public void setZuizaojinru(String zuizaojinru) {
        this.zuizaojinru = zuizaojinru;
    }

    public String getZuihouchuqu() {
        return zuihouchuqu;
    }

    public void setZuihouchuqu(String zuihouchuqu) {
        this.zuihouchuqu = zuihouchuqu;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public String getInnerTime() {
        return innerTime;
    }

    public void setInnerTime(String innerTime) {
        this.innerTime = innerTime;
    }


    public Long getScCardguidno() {
        return scCardguidno;
    }


    public void setScCardguidno(Long scCardguidno) {
        this.scCardguidno = scCardguidno;
    }


    public String getScWorkerno() {
        return scWorkerno;
    }


    public void setScWorkerno(String scWorkerno) {
        this.scWorkerno = scWorkerno;
    }


    public String getScName() {
        return scName;
    }


    public void setScName(String scName) {
        this.scName = scName;
    }


    public String getScMobileno() {
        return scMobileno;
    }


    public void setScMobileno(String scMobileno) {
        this.scMobileno = scMobileno;
    }


    public Integer getScIdtypeid() {
        return scIdtypeid;
    }


    public void setScIdtypeid(Integer scIdtypeid) {
        this.scIdtypeid = scIdtypeid;
    }


    public Integer getScDepartmentid() {
        return scDepartmentid;
    }


    public void setScDepartmentid(Integer scDepartmentid) {
        this.scDepartmentid = scDepartmentid;
    }


    public String getScDepartmentname() {
        return scDepartmentname;
    }


    public void setScDepartmentname(String scDepartmentname) {
        this.scDepartmentname = scDepartmentname;
    }


    @Override
    public String toString() {
        return "OriginalRecordInner{" +
                "zuizaojinru='" + zuizaojinru + '\'' +
                ", zuihouchuqu='" + zuihouchuqu + '\'' +
                ", times=" + times +
                ", innerTime='" + innerTime + '\'' +
                ", scCardguidno=" + scCardguidno +
                ", scWorkerno='" + scWorkerno + '\'' +
                ", scName='" + scName + '\'' +
                ", scMobileno='" + scMobileno + '\'' +
                ", scIdtypeid=" + scIdtypeid +
                ", scDepartmentid=" + scDepartmentid +
                ", scDepartmentname='" + scDepartmentname + '\'' +
                '}';
    }
}
