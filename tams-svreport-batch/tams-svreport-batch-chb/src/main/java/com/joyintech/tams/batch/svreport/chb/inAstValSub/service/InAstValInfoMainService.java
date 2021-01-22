package com.joyintech.tams.batch.svreport.chb.inAstValSub.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.tams.svreport.chbhz.inAstValSub.service.InAstValInfoBatchService;

/**
 * 功能:资产估值信息登记生成批处理入口
 * @since 
 * @version 
 * @作者：zhaoyongsheng
 * @创建时间：
 * @修改记录：
 */
@Service(value="inAstValInfoMainService")
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class InAstValInfoMainService {

    @Autowired
	private InAstValInfoBatchService inAstValInfoBatchService;
	
    /**
     * @param bookDate 
     * @see
     * @since 1.0
     */
    public void generateDurationRegData(Date batchDate) throws BatchException {
    	inAstValInfoBatchService.generateAstValInfoData(batchDate);
    }
}
