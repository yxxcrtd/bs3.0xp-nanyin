package com.joyintech.tams.batch.svreport.chb.sdprdendsub.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.svreport.chbsd.prdendsub.service.SdProdTerminationBatchService;

/**
 * 功能:产品终止信息报送批处理入口
 * @since 
 * @version 
 * @作者：zhaoyongsheng
 * @创建时间：
 * @修改记录：
 */
@Service(value="sdProdTerminationMainService")
@Scope("prototype")
public class SdProdTerminationMainService {

    @Autowired
	private SdProdTerminationBatchService sdProdTerminationBatchService;
	
    /**
     * @param bookDate 
     * @see
     * @since 1.0
     */
    public void SendDurationRegMsg(Date batchDate,List<String> prodIdList) throws Exception {
    	sdProdTerminationBatchService.sendTerminationInfoMsg(batchDate, prodIdList);
    }

}
