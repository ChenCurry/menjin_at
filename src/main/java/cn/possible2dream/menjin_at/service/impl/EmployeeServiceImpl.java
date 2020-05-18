package cn.possible2dream.menjin_at.service.impl;

import cn.possible2dream.menjin_at.dao.EmployeeMapper;
import cn.possible2dream.menjin_at.entity.EmployeeWithBLOBs;
import cn.possible2dream.menjin_at.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    private static final EmployeeServiceImpl single = new EmployeeServiceImpl();
    //静态工厂方法
    public static EmployeeServiceImpl getInstance() {
        return single;
    }

    @Override
    public EmployeeWithBLOBs getEmployee(String scWorkerno) {
        return employeeMapper.selectByScWorkerno(scWorkerno);
    }
}
