package cn.possible2dream.menjin_at.dto;

import cn.possible2dream.menjin_at.entity.OriginalRecordInner;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 子类 一般业务一个子类即可
 */
@Data
public class DemoUserDto2 {

    @ExcelProperty(value = {"部门"})
    private String scDepartmentname;
    @ExcelProperty(value = {"卡号"})
    private String scMobileno;
    @ExcelProperty(value = {"工号"})
    private String scWorkerno;
    @ExcelProperty(value = {"姓名"})
    private String scName;
    @ExcelProperty(value = {"最早进入"})
    private String zuizaojinru;
    @ExcelProperty(value = {"最后出去"})
    private String zuihouchuqu;
    @ExcelProperty(value = {"进入次数"})
    private Integer times;
    @ExcelProperty(value = {"室内时长"})
    private String innerTime;

    public DemoUserDto2() {
    }

    public DemoUserDto2(String scDepartmentname, String scMobileno, String scWorkerno, String scName
            , String zuizaojinru, String zuihouchuqu, Integer times, String innerTime) {
        this.scDepartmentname = scDepartmentname;
        this.scMobileno = scMobileno;
        this.scWorkerno = scWorkerno;
        this.scName = scName;
        this.zuizaojinru = zuizaojinru;
        this.zuihouchuqu = zuihouchuqu;
        this.times = times;
        this.innerTime = innerTime;
    }

    /**
     * 获取6个测试数据
     * @return 6个
     */
    public static List<DemoUserDto2> getUserDtoTest6(List<OriginalRecordInner> originalRecordInners){
        List<DemoUserDto2> list = new ArrayList<>();
        if(null != originalRecordInners && 0 != originalRecordInners.size()){
            for (OriginalRecordInner eachO:originalRecordInners) {
                list.add(new DemoUserDto2(eachO.getScDepartmentname(),eachO.getScMobileno(),eachO.getScWorkerno(),eachO.getScName()
                        ,eachO.getZuizaojinru(),eachO.getZuihouchuqu(),eachO.getTimes(),eachO.getInnerTime()));
            }
        }

        return list;
    }
}