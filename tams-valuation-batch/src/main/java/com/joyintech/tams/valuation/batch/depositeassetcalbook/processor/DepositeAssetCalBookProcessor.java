/*
 * 文件名：DepositeAssetCalBookProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月24日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */
 
package com.joyintech.tams.valuation.batch.depositeassetcalbook.processor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResultWithBokSummary;
import com.joyintech.tams.valuation.batch.common.drawbaseinfoselectcom.dto.DrawBaseInfoSelectComResult;
import com.joyintech.tams.valuation.batch.depositeassetcalbook.service.DepositeAssetCalBookService;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 存款类资产计提账务处理 - processor
 * @author weipeng
 * @version 1.0 2018年5月17日
 * @see DepositeAssetCalBookProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class DepositeAssetCalBookProcessor extends BaseProcessor<NeedOperateEntity, BookGenerateResult>  {
	
    /**
     * 传入参数
     */
    private Map<String, Object> param = new HashMap<String, Object>();

    /**
     * 设置 param
     * 
     * @param param param
     */
    public void setParam(Map<String, Object> param) {
        this.param = param;
    }

    /**
     * processor方法的 Service
     */
    @Autowired
    private DepositeAssetCalBookService depositeAssetCalBookService;

    @Override
	public BookGenerateResultWithBokSummary process(NeedOperateEntity items) throws Exception {
		//获取reader方法的返回值
        DrawBaseInfoSelectComResult acctEntity = (DrawBaseInfoSelectComResult) items;
		
        Map<String, Object> thisParams = new HashMap<>();
        thisParams.putAll(this.getParams());
        // 将返回值塞入参数中
        thisParams.put(GlobalConstant.READER_RESULT, acctEntity);

		//调用service方法进行逻辑逻辑
        BookGenerateResultWithBokSummary result = depositeAssetCalBookService.execute(thisParams);
        
        //当为null时候，给予初始化
        if(result == null){
            result = new BookGenerateResultWithBokSummary();
        }
		
        result.setProdId(
            acctEntity.getBooksetId() + GlobalConstant.SPLIT + acctEntity.getBokDetailId());

        return result;
    }
}
