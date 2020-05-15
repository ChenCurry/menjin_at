package cn.possible2dream.menjin_at.mapper;

import cn.possible2dream.menjin_at.entity.OriginalRecord;

import java.util.List;

public interface AppMessageMapper {
    OriginalRecord selectByPrimaryKey(String id);

    List<OriginalRecord> selectAll();
    List<OriginalRecord> getMessById(String id);
}