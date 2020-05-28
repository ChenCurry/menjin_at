package cn.possible2dream.menjin_at.service;

import cn.possible2dream.menjin_at.entity.*;

import java.util.List;

public interface OriginalRecordService {
    public AccessRecord getAccessRecordByScSerierno(Long scSerierno);
    Long selectMaxScSerierno();
    public List<OriginalRecord> getOriginalRecordListByMaxId(Long scSerierno);
    public List<OriginalRecord> getTop25();
    public List<OriginalRecord> getMaxAddTime8h();
    /**
     *  根据条件 分页查询进出记录
     */
    public TableSplitResult<List<OriginalRecord>> getInOutRecordByConditions(Conditions conditions);

    /**
     * 获取所有的部门信息
     * @return
     */
    public List<Department> getAllDepartment();
}
