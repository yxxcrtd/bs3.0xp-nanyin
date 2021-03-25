package com.joyintech.tams.batch.svreport.chb.assetinfocb.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.svreport.chb.assetinforeg.service.AssetInfoRegistBatchService;

/**
 * 功能:资产要素登记调度批处理入口
 * @since 1.7.0
 * @version 1.7.0
 * @作者：weiyuqiang
 * @创建时间：2019年5月30日
 * @修改记录：
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AssetInfoCBMainService extends AutoSendCBBaseService{
	
    @Autowired
    private AssetInfoRegistBatchService assetInfoRegistBatchService;
    /**
     * 功能:自动生成资产要素登记信息
     * @since 1.6.0
     * @version 1.6.0
     * @author：weiyuqiang 2019年2月21日
     * @param executeDate 跑批日
     * @param prodList 产品代码
     * @param assetCodeList 资产代码
     * void
     * @throws BatchException 
     */
	public void autoRegistAssetInfo(Date executeDate,List<String> prodList,List<String> assetCodeList,String InstitutionCode){
	    assetInfoRegistBatchService.autoRegistAssetInfo(executeDate, prodList, assetCodeList,InstitutionCode);
	}
	

}
