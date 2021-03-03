package com.joyintech.tams.batch.svreport.chb.sdprdissuesub.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.svreport.chbsd.prdissuesub.service.SdProdIssueBatchService;

/**
 * 功能:产品发行信息报送批处理入口
 * @since 
 * @version 
 * @作者：zhaoyongsheng
 * @创建时间：
 * @修改记录：
 */
@Service(value="sdProdIssueMainService")
@Scope("prototype")
public class SdProdIssueMainService {

    @Autowired
	private SdProdIssueBatchService sdProdIssueBatchService;
	
    /**
     * @param bookDate 
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public void SendDurationRegMsg(Date batchDate,List<String> prodIdList) throws Exception {
    	sdProdIssueBatchService.sendIssueInfoMsg(batchDate, prodIdList);
    }

}
