package com.joyintech.tams.batch.svreport.chb.sdemployeeinfosub.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.tams.svreport.chbsd.employeeinfo.service.SdEmployInfoBatchService;

/**
 * 功能:从业人员信息报送批处理入口
 * @since 
 * @version 
 * @作者：zhaoyongsheng
 * @创建时间：
 * @修改记录：
 */
@Service(value="sdEmployeeInfoMainService")
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class SdEmployeeInfoMainService {

    @Autowired
	private SdEmployInfoBatchService sdEmployInfoBatchService;
	
    /**
     * @param bookDate 
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public void SendEmployeeInfoRegMsg(Date batchDate) throws Exception {
    	sdEmployInfoBatchService.sendEmployeeInfoMsg(batchDate);
    }

}
