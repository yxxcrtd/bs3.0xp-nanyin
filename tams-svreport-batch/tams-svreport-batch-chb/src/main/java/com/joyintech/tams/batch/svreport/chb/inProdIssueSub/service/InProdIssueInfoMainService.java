package com.joyintech.tams.batch.svreport.chb.inProdIssueSub.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.tams.svreport.chbhz.inPrdIssueSub.service.InProdIssueInfoBatchService;

/**
 * 功能:产品发行登记接口生成批处理入口
 * @since 
 * @version 
 * @作者：dongxiangyang
 * @创建时间：
 * @修改记录：
 */
@Service(value="inProdIssueInfoMainService")
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class InProdIssueInfoMainService {

    @Autowired
	private InProdIssueInfoBatchService inProdIssueInfoBatchService;
	
    /**
     * @param bookDate 
     * @see
     * @since 1.0
     */
    public void generateDurationRegData(Date batchDate,List<String> prodIdList) throws BatchException {
    	inProdIssueInfoBatchService.generateDeclareInfoData(batchDate, prodIdList);
    }

}