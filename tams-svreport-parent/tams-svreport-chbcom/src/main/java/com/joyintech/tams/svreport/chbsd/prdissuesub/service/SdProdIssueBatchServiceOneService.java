package com.joyintech.tams.svreport.chbsd.prdissuesub.service;

import com.joyintech.component.svrreport.chb.dto.ChbPrdIssueSubDto;
import com.joyintech.component.svrreport.chb.service.MsgSendService;
import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 产品发行登记单批次报送 业务service - 按批次
 * @since 1.0.0
 * @version 1.0.0
 * @作者：zhaoyongsheng
 * @see SdProdIssueBatchServiceOneService
 */
@Service
public class SdProdIssueBatchServiceOneService extends AutoSendCBBaseService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
	
//	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	private MsgSendService msgSend;


   /**
    * 单批次处理数据
    * @param handleIndex
    * @param dataCountPerTime
    * @param msgRstList
    * @param sendResult
    * @return
    * @throws Exception 
    */
    public boolean handleDataPerTime(int handleIndex, 
    		                         int dataCountPerTime,List<ChbPrdIssueSubDto> msgRstList,
    		                         List<ChbPrdIssueSubDto> sendResult)  throws Exception{
        logger.info("【产品发行登记】直连报送处理开始，批次：" + handleIndex + "  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        try {
            	 msgSend.sendChbPrdIssueSubMsgs(msgRstList);
            	 sendResult.addAll(msgRstList);
        } catch (Exception e) {
	        logger.error("【产品发行登记】直连报送处理异常，批次：" + handleIndex + e.getMessage());
	        throw e;
		}
        logger.info("【产品发行登记】直连报送处理结束，批次：" + handleIndex + "  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        return true;
    }
}
