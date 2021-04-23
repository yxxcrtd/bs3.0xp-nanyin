/*
 * 文件名：ManualVoucherReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：chenggaoling
 * 修改时间：2018年8月16日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */
 
package com.joyintech.tams.valuation.batch.manualvoucher.reader;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.batch.manualvoucher.dao.ManualVoucherMapper;
import com.joyintech.tams.valuation.batch.manualvoucher.dto.ManualVoucherReaderResult;

/**
 * 手工凭证交易批处理Reader
 * @author chenggaoling
 * @version 1.0 2018年8月16日
 * @see ManualVoucherReader
 * @since 1.0
 */
@Service
public class ManualVoucherReader extends BaseReader<ManualVoucherReaderResult>{
    
    /**
     * 手工凭证批处理DAO
     */
    @Autowired
    private ManualVoucherMapper manualVoucherMapper;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ManualVoucherReaderResult> createList() {
    	Map<String, Object> params = this.getParams();
    	String taskId =  (String) params.get("taskId");
    	if(taskId.equals("201")){
    		params.put("accountOder", DictCodeListExt3.记账顺序_交易前.getValue());
    	}else{
    		params.put("accountOder", DictCodeListExt3.记账顺序_计提后.getValue());
    	}
        List<ManualVoucherReaderResult> list = manualVoucherMapper.getTradVouchDealList(params);
    	for (ManualVoucherReaderResult result:list){
    	    result.setProdId(result.getBooksetId());
        }
    	return list;
    }

}
