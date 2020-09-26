/*
 * 文件名：SubjectBalanceCollectProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.batch.repassetindex.processor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.repassetindex.dto.BokBookset;
import com.joyintech.tams.valuation.batch.repassetindex.dto.RepAssetIndexDtoResult;
import com.joyintech.tams.valuation.batch.repassetindex.service.RepAssetIndexDtoReaderService;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 资产指标计算step2Process
 * 〈功能详细描述〉
 * @author dongxiangyang
 * @version 1.0 2018年12月21日
 * @see RepAssetIndexProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class RepAssetIndexProcessor extends BaseProcessor<NeedOperateEntity, RepAssetIndexDtoResult> {
    
     
	@Autowired
	private  RepAssetIndexDtoReaderService repAssetIndexDtoReaderService;
    /**
     * {@inheritDoc}
     */
    @Override
    public RepAssetIndexDtoResult process(NeedOperateEntity items) throws Exception {
        // 获取reader方法的返回值
    	BokBookset acctEntity = (BokBookset)items;
        
        Map<String, Object> thisParams = new HashMap<>();
        thisParams.putAll(this.getParams());
        // 将返回值塞入参数中
        thisParams.put(GlobalConstant.READER_RESULT, acctEntity);
        // 调用service方法进行逻辑处理
        RepAssetIndexDtoResult result=repAssetIndexDtoReaderService.execute(thisParams);        
       
        //当为null时候，给予初始化
        if(result == null){
            result = new RepAssetIndexDtoResult();
        }

        result.setProdId(
            acctEntity.getBooksetId());

        return result;
    }

}
