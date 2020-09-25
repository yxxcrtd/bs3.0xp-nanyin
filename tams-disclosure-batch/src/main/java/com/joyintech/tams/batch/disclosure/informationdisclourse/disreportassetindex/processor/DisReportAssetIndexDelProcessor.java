/*
 * 文件名：AssetIntdateCarryoverProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chenghzong
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.batch.disclosure.informationdisclourse.disreportassetindex.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.framework.base.entity.SysBatchgrpExecInfo;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportassetindex.dto.DisReportAssetIndexIntoResult;
import com.joyintech.tams.batch.disclosure.informationdisclourse.disreportassetindex.dto.DisReportAssetIndexResult;

/**
 * 资产理论结息日结转- processor
 * 
 * @author chengzhong
 * @version 1.0 2018年6月7日
 * @see DisReportAssetIndexDelProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class DisReportAssetIndexDelProcessor extends BaseProcessor<NeedOperateEntity, DisReportAssetIndexIntoResult> {

    @Override
    public DisReportAssetIndexIntoResult process(NeedOperateEntity items)
        throws Exception {
    	SysBatchgrpExecInfo execinfo = this.getBatchExecuteDetail();
        // 调用service方法进行逻辑逻辑
    	DisReportAssetIndexIntoResult result = new DisReportAssetIndexIntoResult();
    	//result.setRepProdIndexList(new ArrayList<>(acctEntity));
        
    	Map<String,Object> map = new HashMap<>();
		map.put("startDate", execinfo.getStartDate());
		map.put("endDate", execinfo.getEndDate());
		map.put("prodid", execinfo.getOtherParam1());
		result.setParam(map);
        return result;
    }
}
