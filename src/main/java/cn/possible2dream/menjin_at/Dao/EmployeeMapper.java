package cn.possible2dream.menjin_at.Dao;

import cn.possible2dream.menjin_at.entity.Employee;
import cn.possible2dream.menjin_at.entity.EmployeeWithBLOBs;

public interface EmployeeMapper {
    int deleteByPrimaryKey(String scWorkerno);

    int insert(EmployeeWithBLOBs record);

    int insertSelective(EmployeeWithBLOBs record);

    EmployeeWithBLOBs selectByPrimaryKey(String scWorkerno);

    int updateByPrimaryKeySelective(EmployeeWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(EmployeeWithBLOBs record);

    int updateByPrimaryKey(Employee record);
}