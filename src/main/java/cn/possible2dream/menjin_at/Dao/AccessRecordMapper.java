package cn.possible2dream.menjin_at.Dao;

import cn.possible2dream.menjin_at.entity.AccessRecord;

public interface AccessRecordMapper {
    int deleteByPrimaryKey(Long scSerierno);

    int insert(AccessRecord record);

    int insertSelective(AccessRecord record);

    AccessRecord selectByPrimaryKey(Long scSerierno);

    int updateByPrimaryKeySelective(AccessRecord record);

    int updateByPrimaryKeyWithBLOBs(AccessRecord record);

    int updateByPrimaryKey(AccessRecord record);
}