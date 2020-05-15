package cn.possible2dream.menjin_at.entity;

import java.util.Date;

public class Employee {
    private String scWorkerno;

    private Long scEmpno;

    private Long scSerierno;

    private Long scAccountno;

    private String scName;

    private Byte scSexid;

    private String scMobileno;

    private String scOfficetelno;

    private String scActualaddr;

    private String scRegisteraddr;

    private Date scBirthday;

    private Integer scIdtypeid;

    private String scIdno;

    private String scNativeid;

    private Integer scPoliticsstatusid;

    private Integer scWorkertypeid;

    private Integer scJobtypeid;

    private Integer scDepartmentid;

    private Integer scWorkerstatusid;

    private Integer scCardstatusid;

    private Date scWorkbegintime;

    private Date scWorkendtime;

    private Integer scPower;

    public String getScWorkerno() {
        return scWorkerno;
    }

    public void setScWorkerno(String scWorkerno) {
        this.scWorkerno = scWorkerno == null ? null : scWorkerno.trim();
    }

    public Long getScEmpno() {
        return scEmpno;
    }

    public void setScEmpno(Long scEmpno) {
        this.scEmpno = scEmpno;
    }

    public Long getScSerierno() {
        return scSerierno;
    }

    public void setScSerierno(Long scSerierno) {
        this.scSerierno = scSerierno;
    }

    public Long getScAccountno() {
        return scAccountno;
    }

    public void setScAccountno(Long scAccountno) {
        this.scAccountno = scAccountno;
    }

    public String getScName() {
        return scName;
    }

    public void setScName(String scName) {
        this.scName = scName == null ? null : scName.trim();
    }

    public Byte getScSexid() {
        return scSexid;
    }

    public void setScSexid(Byte scSexid) {
        this.scSexid = scSexid;
    }

    public String getScMobileno() {
        return scMobileno;
    }

    public void setScMobileno(String scMobileno) {
        this.scMobileno = scMobileno == null ? null : scMobileno.trim();
    }

    public String getScOfficetelno() {
        return scOfficetelno;
    }

    public void setScOfficetelno(String scOfficetelno) {
        this.scOfficetelno = scOfficetelno == null ? null : scOfficetelno.trim();
    }

    public String getScActualaddr() {
        return scActualaddr;
    }

    public void setScActualaddr(String scActualaddr) {
        this.scActualaddr = scActualaddr == null ? null : scActualaddr.trim();
    }

    public String getScRegisteraddr() {
        return scRegisteraddr;
    }

    public void setScRegisteraddr(String scRegisteraddr) {
        this.scRegisteraddr = scRegisteraddr == null ? null : scRegisteraddr.trim();
    }

    public Date getScBirthday() {
        return scBirthday;
    }

    public void setScBirthday(Date scBirthday) {
        this.scBirthday = scBirthday;
    }

    public Integer getScIdtypeid() {
        return scIdtypeid;
    }

    public void setScIdtypeid(Integer scIdtypeid) {
        this.scIdtypeid = scIdtypeid;
    }

    public String getScIdno() {
        return scIdno;
    }

    public void setScIdno(String scIdno) {
        this.scIdno = scIdno == null ? null : scIdno.trim();
    }

    public String getScNativeid() {
        return scNativeid;
    }

    public void setScNativeid(String scNativeid) {
        this.scNativeid = scNativeid == null ? null : scNativeid.trim();
    }

    public Integer getScPoliticsstatusid() {
        return scPoliticsstatusid;
    }

    public void setScPoliticsstatusid(Integer scPoliticsstatusid) {
        this.scPoliticsstatusid = scPoliticsstatusid;
    }

    public Integer getScWorkertypeid() {
        return scWorkertypeid;
    }

    public void setScWorkertypeid(Integer scWorkertypeid) {
        this.scWorkertypeid = scWorkertypeid;
    }

    public Integer getScJobtypeid() {
        return scJobtypeid;
    }

    public void setScJobtypeid(Integer scJobtypeid) {
        this.scJobtypeid = scJobtypeid;
    }

    public Integer getScDepartmentid() {
        return scDepartmentid;
    }

    public void setScDepartmentid(Integer scDepartmentid) {
        this.scDepartmentid = scDepartmentid;
    }

    public Integer getScWorkerstatusid() {
        return scWorkerstatusid;
    }

    public void setScWorkerstatusid(Integer scWorkerstatusid) {
        this.scWorkerstatusid = scWorkerstatusid;
    }

    public Integer getScCardstatusid() {
        return scCardstatusid;
    }

    public void setScCardstatusid(Integer scCardstatusid) {
        this.scCardstatusid = scCardstatusid;
    }

    public Date getScWorkbegintime() {
        return scWorkbegintime;
    }

    public void setScWorkbegintime(Date scWorkbegintime) {
        this.scWorkbegintime = scWorkbegintime;
    }

    public Date getScWorkendtime() {
        return scWorkendtime;
    }

    public void setScWorkendtime(Date scWorkendtime) {
        this.scWorkendtime = scWorkendtime;
    }

    public Integer getScPower() {
        return scPower;
    }

    public void setScPower(Integer scPower) {
        this.scPower = scPower;
    }
}