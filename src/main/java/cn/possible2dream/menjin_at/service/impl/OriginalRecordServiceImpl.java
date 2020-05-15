package cn.possible2dream.menjin_at.service.impl;

import cn.possible2dream.menjin_at.dao.AccessRecordMapper;
import cn.possible2dream.menjin_at.entity.AccessRecord;
import cn.possible2dream.menjin_at.service.OriginalRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("qriginalRecordService")
public class OriginalRecordServiceImpl implements OriginalRecordService {

    @Resource
    private AccessRecordMapper accessRecordMapper;

    @Override
    public AccessRecord getAccessRecordByScSerierno(Long scSerierno) {
        return accessRecordMapper.selectByPrimaryKey(scSerierno);
    }
}
