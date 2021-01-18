package com.joyintech.tams.batch.svreport.chb.sdbottomastsub.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.svreport.chbsd.bottomastsub.service.SdBotAssetPosRegBatchService;

/**
 * 功能:底层持仓登记直连报送批处理调度入口
 * @since 1.0.0
 * @version 1.0.0
 * @作者：lingxq
 * @创建时间：2020年09月27日
 * @修改记录：
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SdBotAstPosCBMainService extends AutoSendCBBaseService{

    @Autowired
    private SdBotAssetPosRegBatchService sdAssetPosRegBatchService;
    
	public void sdBottomAstPositionRegData(Date currentTime)  throws Exception {
		sdAssetPosRegBatchService.sdBottomAstPositionRegData(currentTime);
	}
	
}