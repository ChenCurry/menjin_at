package cn.possible2dream.menjin_at.entity;

import java.util.Date;

public class OriginalRecordInner extends OriginalRecord {

    private Date zuizaojinru;
    private Date zuihouchuqu;
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

    public Date getZuizaojinru() {
        return zuizaojinru;
    }

    public void setZuizaojinru(Date zuizaojinru) {
        this.zuizaojinru = zuizaojinru;
    }

    public Date getZuihouchuqu() {
        return zuihouchuqu;
    }

    public void setZuihouchuqu(Date zuihouchuqu) {
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

    @Override
    public Long getScCardguidno() {
        return scCardguidno;
    }

    @Override
    public void setScCardguidno(Long scCardguidno) {
        this.scCardguidno = scCardguidno;
    }

    @Override
    public String getScWorkerno() {
        return scWorkerno;
    }

    @Override
    public void setScWorkerno(String scWorkerno) {
        this.scWorkerno = scWorkerno;
    }

    @Override
    public String getScName() {
        return scName;
    }

    @Override
    public void setScName(String scName) {
        this.scName = scName;
    }

    @Override
    public String getScMobileno() {
        return scMobileno;
    }

    @Override
    public void setScMobileno(String scMobileno) {
        this.scMobileno = scMobileno;
    }

    @Override
    public Integer getScIdtypeid() {
        return scIdtypeid;
    }

    @Override
    public void setScIdtypeid(Integer scIdtypeid) {
        this.scIdtypeid = scIdtypeid;
    }

    @Override
    public Integer getScDepartmentid() {
        return scDepartmentid;
    }

    @Override
    public void setScDepartmentid(Integer scDepartmentid) {
        this.scDepartmentid = scDepartmentid;
    }

    @Override
    public String getScDepartmentname() {
        return scDepartmentname;
    }

    @Override
    public void setScDepartmentname(String scDepartmentname) {
        this.scDepartmentname = scDepartmentname;
    }

    @Override
    public String toString() {
        return "OriginalRecordInner{" +
                "zuizaojinru=" + zuizaojinru +
                ", zuihouchuqu=" + zuihouchuqu +
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
