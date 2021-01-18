package com.joyintech.tams.batch.svreport.chb.sdassetdealsub.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.svreport.chbsd.assetdealsub.service.SdAssetDealRegBatchService;

/**
 * 功能:资产交易信息登记直连报送批处理调度入口
 * @since 1.0.0
 * @version 1.0.0
 * @作者：lingxq
 * @创建时间：2020年09月27日
 * @修改记录：
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SdAssetDealCBMainService extends AutoSendCBBaseService{

    @Autowired
    private SdAssetDealRegBatchService sdAssetDealRegBatchService;
    
	public void sdAssetDealRegData(Date currentTime,List<String> prodList)  throws Exception {
		sdAssetDealRegBatchService.sdAssetDealRegData(currentTime,prodList);
	}
	
}