package cn.possible2dream.menjin_at.service;

import cn.possible2dream.menjin_at.entity.*;

import java.util.List;

public interface OriginalRecordService {
    public AccessRecord getAccessRecordByScSerierno(Long scSerierno);
    Long selectMaxScSerierno();
    public List<OriginalRecord> getOriginalRecordListByMaxId(Long scSerierno);
    public List<OriginalRecord> getTop25();

    /**
     * 查询所有室内人员
     * @return
     */
    public List<OriginalRecord> getMaxAddTime8h();
    /**
     *  根据条件 分页查询进出记录
     */
    public TableSplitResult<List<OriginalRecord>> getInOutRecordByConditions(Conditions conditions);

    /**
     * 不分页导出进出记录
     * @param conditions
     * @return
     */
    public List<OriginalRecord> getInOutRecordByConditionsWithoutPages(Conditions conditions);


    /**
     * 不分页 导出 室内时长
     * @param conditions
     * @return
     */
    public List<OriginalRecordInner> getInnerTimeByConditionsWithoutPages(Conditions conditions);

    /**
     * 获取所有的部门信息
     * @return
     */
    public List<Department> getAllDepartment();

    /**
     *  根据条件 分页查询室内时长记录
     */
    public TableSplitResult<List<OriginalRecordInner>> getInnerTimeByConditions(Conditions conditions);
}
