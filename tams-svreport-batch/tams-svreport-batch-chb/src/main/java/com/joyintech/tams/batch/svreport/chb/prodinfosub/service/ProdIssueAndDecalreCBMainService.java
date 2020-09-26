package com.joyintech.tams.batch.svreport.chb.prodinfosub.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.svreport.chb.prodpersonissue.service.ProdIssueAndDecalreRegBatchService;

/**
 * 功能:产品发行申报登记批处理入口O
 * @since 1.7.0
 * @version 1.7.0
 * @作者：weiyuqiang
 * @创建时间：2019年5月30日
 * @修改记录：
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProdIssueAndDecalreCBMainService  extends AutoSendCBBaseService{
	
    @Autowired
    private ProdIssueAndDecalreRegBatchService prodIssueAndDecalreRegBatchService;
    
    public void generateIssueData(Date batchTime,List<String> prodIds,boolean isSynchronizeTask) {
        prodIssueAndDecalreRegBatchService.generateIssueData(batchTime, prodIds, isSynchronizeTask);
	}
    

}
