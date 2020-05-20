package cn.possible2dream.menjin_at.dao;

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

    int updateByPrimaryKeySelective(AccessRecord record);

    int updateByPrimaryKeyWithBLOBs(AccessRecord record);

    int updateByPrimaryKey(AccessRecord record);
}