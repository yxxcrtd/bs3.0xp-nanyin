/*
 * 文件名：FundDealInfoRecProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：HuangJian
 * 修改时间：2018年6月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.fund.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.constant.TempConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.InterfNeedOperateEntity;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.interf.core.InterfBaseProcessor;
import com.joyintech.tams.valuation.interf.core.entity.InterfExecuteInfo;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.fund.dto.FundDealInfoRecProcessorResult;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.fund.service.FundDealInfoComForInterfService;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author HuangJian
 * @version 1.0 2018年6月27日
 * @see FundDealInfoRec1Processor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class FundDealInfoRec1Processor extends InterfBaseProcessor<InterfNeedOperateEntity, FundDealInfoRecProcessorResult> {
    /**
     * FundDealInfoComForInterfService
     */
    @Autowired
    private FundDealInfoComForInterfService fundDealInfoComForInterfService;


    /**
     * {@inheritDoc}
     */
    @Override
    public FundDealInfoRecProcessorResult process(InterfNeedOperateEntity item)
        throws Exception {
        // 获取reader方法的返回值(processor并发数据结构)
        TemplateOrderInfoReaderResult acctEntity = (TemplateOrderInfoReaderResult)item;
        InterfExecuteInfo interfExecuteInfo = (InterfExecuteInfo)this.getParams().get("executeInfo");
        acctEntity.setResovleDate(interfExecuteInfo.getInterfDate());
        acctEntity.setParams(this.getParams());
        FundDealInfoRecProcessorResult result = null;
        if(TempConstant.申购申请交易.equals(acctEntity.getTmpEName())){//ValApplyPurTade
        	 result = fundDealInfoComForInterfService.execute(acctEntity);
        }
        return result;
    }
}
