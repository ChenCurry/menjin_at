package cn.possible2dream.menjin_at.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 父类 可能业务需要继承
 */
@Data
public class DemoParentDto {

    @ExcelProperty(index = 0,value = {"序号"})
    private Integer num;

}