package cn.possible2dream.menjin_at.mapper;

import cn.possible2dream.menjin_at.entity.Employee;
import cn.possible2dream.menjin_at.entity.EmployeeWithBLOBs;

public interface EmployeeMapper {
    int deleteByPrimaryKey(String scWorkerno);

    int insert(EmployeeWithBLOBs record);

    int insertSelective(EmployeeWithBLOBs record);

    EmployeeWithBLOBs selectByPrimaryKey(String scWorkerno);

    //员工对象需要放到 WebSocketServer 中
    //根据 scEmpno 数字查询
    EmployeeWithBLOBs selectByScEmpno(Long scEmpno);

    //根据工号查询 EmployeeWithBLOBs
    EmployeeWithBLOBs selectByScWorkerno(String scWorkerno);

    //根据工号查询 Employee   忘记注解了
    Employee selectByScWorkerno2(String scWorkerno);

    int updateByPrimaryKeySelective(EmployeeWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(EmployeeWithBLOBs record);

    int updateByPrimaryKey(Employee record);
}