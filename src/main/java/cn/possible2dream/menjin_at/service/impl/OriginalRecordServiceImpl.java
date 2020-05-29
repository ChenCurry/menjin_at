package cn.possible2dream.menjin_at.service.impl;

import cn.possible2dream.menjin_at.entity.*;
import cn.possible2dream.menjin_at.mapper.AccessRecordMapper;
import cn.possible2dream.menjin_at.mapper.DepartmentMapper;
import cn.possible2dream.menjin_at.service.OriginalRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("originalRecordService")
public class OriginalRecordServiceImpl implements OriginalRecordService {

    @Resource
    private AccessRecordMapper accessRecordMapper;

    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public Long selectMaxScSerierno() {
        return accessRecordMapper.selectMaxScSerierno();
    }

    @Override
    public AccessRecord getAccessRecordByScSerierno(Long scSerierno) {
        return accessRecordMapper.selectByPrimaryKey(scSerierno);
    }

    @Override
    public List<OriginalRecord> getOriginalRecordListByMaxId(Long scSerierno) {
        return accessRecordMapper.selectAllByMaxId(scSerierno);
    }

    @Override
    public List<OriginalRecord> getTop25() {
        return accessRecordMapper.selectTop25();
    }

    @Override
    public List<OriginalRecord> getMaxAddTime8h() {
        List<OriginalRecord> list = accessRecordMapper.selectRealTimeInner1();//可以试试 selectRealTimeInner2
        for(int i=list.size()-1;i>=0;i--){
            if(1!=list.get(i).getScInoutstatus()){
                list.remove(i);
            }
        }
        return list;
    }

    @Override
    public TableSplitResult<List<OriginalRecord>> getInOutRecordByConditions(Conditions conditions) {

        Integer total = 0;
        total = accessRecordMapper.selectGetInOutRecordByConditionsTotal(conditions);
        conditions.setTotal(total);
        int page = conditions.getPageNumber();
        if(page==1){
            conditions.setMinRow(1);
            conditions.setMaxRow(conditions.getPageSize());
        }else{
            conditions.setMinRow((conditions.getPageNumber()-1)*conditions.getPageSize()+1);
            conditions.setMaxRow(conditions.getPageNumber()*conditions.getPageSize());
        }
        List<OriginalRecord> list = new ArrayList<>();
        if(null!=total&&total!=0){
            list = accessRecordMapper.selectGetInOutRecordByConditions(conditions);
        }
        TableSplitResult<List<OriginalRecord>> fanhui = new TableSplitResult<List<OriginalRecord>>(page,total,list);

        return fanhui;
    }

    @Override
    public List<OriginalRecord> getInOutRecordByConditionsWithoutPages(Conditions conditions) {
        List<OriginalRecord> list = new ArrayList<>();
        list = accessRecordMapper.selectGetInOutRecordByConditionsConditionsWithoutPages(conditions);
        return list;
    }

    @Override
    public List<Department> getAllDepartment() {
        return departmentMapper.selectAll();
    }

}
