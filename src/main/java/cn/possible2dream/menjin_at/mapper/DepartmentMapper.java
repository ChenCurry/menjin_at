package cn.possible2dream.menjin_at.mapper;

import cn.possible2dream.menjin_at.entity.Department;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer scDepartmentid);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer scDepartmentid);

    /**
     * 加载所有部门到内存中
     * @return
     */
    List<Department> selectAll();

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKeyWithBLOBs(Department record);

    int updateByPrimaryKey(Department record);
}