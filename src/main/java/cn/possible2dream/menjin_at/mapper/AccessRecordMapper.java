package cn.possible2dream.menjin_at.mapper;

import cn.possible2dream.menjin_at.entity.AccessRecord;
import cn.possible2dream.menjin_at.entity.OriginalRecord;

import java.util.List;

public interface AccessRecordMapper {
    int deleteByPrimaryKey(Long scSerierno);

    int insert(AccessRecord record);

    int insertSelective(AccessRecord record);

    AccessRecord selectByPrimaryKey(Long scSerierno);

    /**
     * 根据最大的id进行查询  所有字段
     * @param scSerierno
     * @return
     */
    List<OriginalRecord> selectAllByMaxId(Long scSerierno);

    /**
     * 查询当前最大的 scSerierno
     * @return
     */
    Long selectMaxScSerierno();

    /**
     * 固定查询25条  定时任务的形式
     * @return
     */
    List<OriginalRecord> selectTop25();

    /**
     * 最近8小时 所有人的最后一条记录  方式1
     * @return
     */
    List<OriginalRecord> selectRealTimeInner1();

    /**
     * 最近8小时 所有人的最后一条记录  方式2
     * @return
     */
    List<OriginalRecord> selectRealTimeInner2();


    int updateByPrimaryKeySelective(AccessRecord record);

    int updateByPrimaryKeyWithBLOBs(AccessRecord record);

    int updateByPrimaryKey(AccessRecord record);
}