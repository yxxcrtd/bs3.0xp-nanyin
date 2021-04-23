/*
 * 文件名：AssetIntdateCarryoverReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chengzhong
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.assetimpairmentprovision.reader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.valuation.batch.assetimpairmentprovision.dto.AssetImpairmentProvisionReaderResult;
import com.joyintech.tams.valuation.batch.assetimpairmentprovision.service.AssetImpairmentProvisionReaderService;
import com.joyintech.tams.valuation.batch.assetintdatecarryover.dto.AssetIntdateCarryoverReaderResult;
import com.joyintech.tams.valuation.batch.assetintdatecarryover.service.AssetIntdateCarryoverReaderService;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookCommonLogic;
import com.joyintech.tams.valuation.common.constants.BookConstant;

/**
 * 资产减值计提批处理 - 读入
 * 
 * @author chufengjie
 * @version 1.0 2020年10月13日
 * @see AssetImpairmentProvisionTaskReader
 * @since 1.0
 */
@Service
@Scope("prototype")
public class AssetImpairmentProvisionTaskReader extends BaseReader<AssetImpairmentProvisionReaderResult> {

    /**
     * reader方法的 Service
     */
    @Autowired
    private AssetImpairmentProvisionReaderService assetImpairmentProvisionReaderService;
    
    @Autowired
    private BookCommonLogic bookCommonLogic;

    /**
     * 读取数据 {@inheritDoc}
     */
    @Override
    public List<AssetImpairmentProvisionReaderResult> createList() {
    	
        Map<String, Object> serviceParamMap = new HashMap<String, Object>();
        serviceParamMap.put("BATCHINFO", this.getBatchExecuteInfo());
        serviceParamMap.put("BATCHDEL", this.getBatchExecuteDetail());
        serviceParamMap.put("BATCHTASK", this.getBatchTask());
        Map<String, String> dimValueMap = bookCommonLogic.getDimensionValue(this.getBatchExecuteInfo());
        serviceParamMap.put(BookConstant.BOOKSET_DATE, DateUtil.parse(dimValueMap.get(BookConstant.BOOKSET_DATE)));
        serviceParamMap.put(BookConstant.BOOK_DATE, DateUtil.parse(dimValueMap.get(BookConstant.BOOK_DATE)));
        serviceParamMap.put(BookConstant.HAPPEN_DATE, DateUtil.parse(dimValueMap.get(BookConstant.HAPPEN_DATE)));
        serviceParamMap.put("seqNo", this.getBatchExecuteDetail().getSeqNo());
        serviceParamMap.put("taskId", this.getBatchExecuteInfo().getTaskId());
        serviceParamMap.put(BookConstant.IS_SUB_BOOKSET, dimValueMap.get(BookConstant.IS_SUB_BOOKSET));
        if (this.getParams() != null) {
            this.getParams().putAll(serviceParamMap);
        }

        // 获取reader返回值
        List<AssetImpairmentProvisionReaderResult> resultList = assetImpairmentProvisionReaderService.execute(serviceParamMap);

        return resultList;
    }

}
