package cn.possible2dream.menjin_at.Dao;

import cn.possible2dream.menjin_at.entity.Department;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer scDepartmentid);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer scDepartmentid);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKeyWithBLOBs(Department record);

    int updateByPrimaryKey(Department record);
}