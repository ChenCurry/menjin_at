package cn.possible2dream.menjin_at.service;

import cn.possible2dream.menjin_at.entity.EmployeeWithBLOBs;

public interface EmployeeService {

    EmployeeWithBLOBs getEmployee(String scWorkerno);

    /**
     * 借用一下这个service 免得去WebSocketServer中注入新的service
     * @return
     */
    Long selectMaxScSerierno();

}
