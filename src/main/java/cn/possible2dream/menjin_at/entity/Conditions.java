package cn.possible2dream.menjin_at.entity;

import java.util.Date;

public class Conditions {
    private Date time1;
    private Date time2;
    private Integer floorx;
    private Integer departmentx;
    private String nameX;
    private String jobX;

    private Integer pageSize;
    private Integer pageNumber;
    private Integer total;

    public Date getTime1() {
        return time1;
    }

    public void setTime1(Date time1) {
        this.time1 = time1;
    }

    public Date getTime2() {
        return time2;
    }

    public void setTime2(Date time2) {
        this.time2 = time2;
    }

    public Integer getFloorx() {
        return floorx;
    }

    public void setFloorx(Integer floorx) {
        this.floorx = floorx;
    }

    public Integer getDepartmentx() {
        return departmentx;
    }

    public void setDepartmentx(Integer departmentx) {
        this.departmentx = departmentx;
    }

    public String getNameX() {
        return nameX;
    }

    public void setNameX(String nameX) {
        this.nameX = nameX;
    }

    public String getJobX() {
        return jobX;
    }

    public void setJobX(String jobX) {
        this.jobX = jobX;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Conditions{" +
                "time1=" + time1 +
                ", time2=" + time2 +
                ", floorx=" + floorx +
                ", departmentx=" + departmentx +
                ", nameX='" + nameX + '\'' +
                ", jobX='" + jobX + '\'' +
                ", pageSize=" + pageSize +
                ", pageNumber=" + pageNumber +
                ", total=" + total +
                '}';
    }
}
