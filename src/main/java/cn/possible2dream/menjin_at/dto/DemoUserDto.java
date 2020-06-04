package cn.possible2dream.menjin_at.dto;

import cn.possible2dream.menjin_at.entity.OriginalRecord;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 子类 一般业务一个子类即可
 */
//@EqualsAndHashCode(callSuper = true)
//@AllArgsConstructor
//@NoArgsConstructor
@Data
public class DemoUserDto {//extends DemoParentDto

    @ExcelProperty(value = {"流水"})
    private Long scSerierno;//记录号自增 百万级
    @ExcelProperty(value = {"门号"})
    private Integer scDoorno;//设备号1-16
    @ExcelProperty(value = {"进出"})
    private Short scInoutstatus;//进还是出

    @ExcelProperty(value = {"时间"})
    //@DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private Date scAddtime;
    //private Date scAddtime;//记录时间  LocalDate  Date
    @ExcelProperty(value = {"工号"})
    private String scWorkerno;//工号
    @ExcelProperty(value = {"姓名"})
    private String scName;//姓名
    @ExcelProperty(value = {"卡号"})
    private String scMobileno;//完整卡号
    @ExcelProperty(value = {"部门"})
    private String scDepartmentname;
    @ExcelProperty(value = {"事件类型"})
    private String scCheckResultName;
    @ExcelProperty(value = {"区域"})
    private String scDeviceAreaName;

    public DemoUserDto() {
    }

    public DemoUserDto(Long scSerierno, Integer scDoorno, Short scInoutstatus, Date scAddtime, String scWorkerno
            , String scName, String scMobileno, String scDepartmentname, String scCheckResultName, String scDeviceAreaName) {
        this.scSerierno = scSerierno;
        this.scDoorno = scDoorno;
        this.scInoutstatus = scInoutstatus;
        this.scAddtime = scAddtime;
        this.scWorkerno = scWorkerno;
        this.scName = scName;
        this.scMobileno = scMobileno;
        this.scDepartmentname = scDepartmentname;
        this.scCheckResultName = scCheckResultName;
        this.scDeviceAreaName = scDeviceAreaName;
    }

    /**
     * 获取6个测试数据
     * @return 6个
     */
    public static List<DemoUserDto> getUserDtoTest6(List<OriginalRecord> originalRecords){
        List<DemoUserDto> list = new ArrayList<>();
        if(null != originalRecords && 0 != originalRecords.size()){
            for (OriginalRecord eachO:originalRecords) {
                list.add(new DemoUserDto(eachO.getScSerierno(),eachO.getScDoorno(),eachO.getScInoutstatus(),eachO.getScAddtime()
                        ,eachO.getScWorkerno(),eachO.getScName(),eachO.getScMobileno(),eachO.getScDepartmentname()
                        ,eachO.getScCheckResultName(),eachO.getScDeviceAreaName()));
            }
        }

        return list;
    }
}