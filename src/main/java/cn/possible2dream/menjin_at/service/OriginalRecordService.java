package cn.possible2dream.menjin_at.service;

import cn.possible2dream.menjin_at.entity.AccessRecord;
import cn.possible2dream.menjin_at.entity.OriginalRecord;

import java.util.List;

public interface OriginalRecordService {
    public AccessRecord getAccessRecordByScSerierno(Long scSerierno);
    Long selectMaxScSerierno();
    public List<OriginalRecord> getOriginalRecordListByMaxId(Long scSerierno);
    public List<OriginalRecord> getTop25();
    public List<OriginalRecord> getMaxAddTime8h();
    /**
     *  根据条件 分页查询进出记录
     * @param time1
     * @param time2
     * @param floorx
     * @param departmentx
     * @param nameX
     * @param jobX
     * @param offset
     * @param pageSize
     * @param pageNumber
     * @return
     */
    public List<OriginalRecord> getInOutRecordByConditions(String time1
            ,String time2,String floorx,String departmentx,String nameX
            ,String jobX,String offset,String pageSize,String pageNumber);
}
