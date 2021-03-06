package cn.possible2dream.menjin_at.mapper;

import cn.possible2dream.menjin_at.entity.AccessRecord;
import cn.possible2dream.menjin_at.entity.Conditions;
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

    /**
     * 根据条件分页查询 进出记录   top 20
     * @return
     */
    List<OriginalRecord> selectGetInOutRecordByConditions(Conditions conditions);

    /**
     * 导出所有 进出记录
     * @param conditions
     * @return
     */
    List<OriginalRecord> selectGetInOutRecordByConditionsConditionsWithoutPages(Conditions conditions);

    /**
     *  查询总条数
     * @param conditions
     * @return
     */
    Integer selectGetInOutRecordByConditionsTotal(Conditions conditions);

    /**
     * 查询室内时长
     * @param conditions
     * @return
     */
    List<OriginalRecord> selectInnerTime(Conditions conditions);


    int updateByPrimaryKeySelective(AccessRecord record);

    int updateByPrimaryKeyWithBLOBs(AccessRecord record);

    int updateByPrimaryKey(AccessRecord record);
}