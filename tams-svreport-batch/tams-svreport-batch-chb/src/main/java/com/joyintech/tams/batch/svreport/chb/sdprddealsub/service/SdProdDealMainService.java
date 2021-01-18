package com.joyintech.tams.batch.svreport.chb.sdprddealsub.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.tams.svreport.chbsd.proddealsub.service.SdProdDealBatchService;

/**
 * 功能:产品存续期信息报送批处理入口
 * @since 
 * @version 
 * @作者：zhaoyongsheng
 * @创建时间：
 * @修改记录：
 */
@Service(value="sdProdDealMainService")
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class SdProdDealMainService {

    @Autowired
	private SdProdDealBatchService sdProdDealBatchService;
	
    /**
     * @param bookDate 
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public void SendDurationRegMsg(Date batchDate,List<String> prodIdList) throws Exception {
    	sdProdDealBatchService.sendDealInfoMsg(batchDate, prodIdList);
    }

}
