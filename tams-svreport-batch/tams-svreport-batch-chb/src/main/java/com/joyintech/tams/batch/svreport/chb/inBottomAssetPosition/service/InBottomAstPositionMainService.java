package com.joyintech.tams.batch.svreport.chb.inBottomAssetPosition.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.tams.svreport.chbhz.inBottomAssetPosition.service.InBottomAstPositionBatchService;

/**
 * 功能:底层资产持仓信息生成批处理入口
 * @since 
 * @version 
 * @作者：lingxq
 * @创建时间：
 * @修改记录：
 */
@Service(value="inBottomAstPositionMainService")
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class InBottomAstPositionMainService {

    @Autowired
	private InBottomAstPositionBatchService inBottomAstPositionBatchService;
	
    /**
     * @param bookDate 
     * @see
     * @since 1.0
     */
    public void generateBottomAstPositionRegData(Date batchDate) throws BatchException {
    	inBottomAstPositionBatchService.generateBottomAstPositionData(batchDate);
    }

}