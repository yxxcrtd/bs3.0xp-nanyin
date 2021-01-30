package com.joyintech.tams.batch.svreport.employee.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.svreport.chb.employee.service.SvrChbEmployeeDBInterf;



@Service(value = "employeeImpMainService")
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class EmployeeImpMainService extends AutoSendCBBaseService{
    
    @Autowired
    private SvrChbEmployeeDBInterf SvrChbEmployeeDBInterf;
    
    /**
     * 功能:将维护表中的数据导入到登记表中
     * @param batchDate 跑批日
     * void
     */
    public void generateEmployeeSubInfo(Date batchDate){
    	try{
    		SvrChbEmployeeDBInterf.generateData(batchDate);
    	}catch(Exception e){
    		throw e;
    	}
    }
}
