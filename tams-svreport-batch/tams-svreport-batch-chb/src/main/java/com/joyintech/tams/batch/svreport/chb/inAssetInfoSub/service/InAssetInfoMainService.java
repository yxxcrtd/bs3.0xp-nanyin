package com.joyintech.tams.batch.svreport.chb.inAssetInfoSub.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.tams.svreport.chbhz.inAssetInfoSub.service.InAssetInfoSubBatchService;

/**
 * 功能:资产负债登记生成批处理入口
 * @since 
 * @version 
 * @作者：dongxiangyang
 * @创建时间：
 * @修改记录：
 */
@Service(value="inAssetInfoMainService")
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class InAssetInfoMainService {

    @Autowired
	private InAssetInfoSubBatchService inAssetInfoSubBatchService;
	
    /**
     * @param bookDate 
     * @see
     * @since 1.0
     */
    public void generateDurationRegData(Date batchDate,List<String> prodIdList) throws BatchException {
    	inAssetInfoSubBatchService.generateDeclareInfoData(batchDate, prodIdList);
    }

}