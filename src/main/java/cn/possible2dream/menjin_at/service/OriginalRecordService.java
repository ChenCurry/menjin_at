package cn.possible2dream.menjin_at.service;

import cn.possible2dream.menjin_at.entity.AccessRecord;
import cn.possible2dream.menjin_at.entity.OriginalRecord;

import java.util.List;

public interface OriginalRecordService {
    public AccessRecord getAccessRecordByScSerierno(Long scSerierno);
    public List<OriginalRecord> getOriginalRecordListByMaxId(Long scSerierno);
    public List<OriginalRecord> getTop25();
}
