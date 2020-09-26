/*
 * 文件名：FundDealInfoRecProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：HuangJian
 * 修改时间：2018年6月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.lend.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.dto.InterfNeedOperateEntity;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.interf.core.InterfBaseProcessor;
import com.joyintech.tams.valuation.interf.core.entity.InterfExecuteInfo;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.lend.dto.LendDealRecProcessorResult;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.lend.service.LendDealRecComForInterfService;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author HuangJian
 * @version 1.0 2018年6月27日
 * @see LendDealRecProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class LendDealRecProcessor extends InterfBaseProcessor<InterfNeedOperateEntity, LendDealRecProcessorResult> {
    /**
     * FundDealInfoComForInterfService
     */
    @Autowired
    private LendDealRecComForInterfService lendDealRecComForInterfService;


    /**
     * {@inheritDoc}
     */
    @Override
    public LendDealRecProcessorResult process(InterfNeedOperateEntity item)
        throws Exception {
        // 获取reader方法的返回值(processor并发数据结构)
        TemplateOrderInfoReaderResult acctEntity = (TemplateOrderInfoReaderResult)item;

        /**add by wujie on 20180731 start remark:用于去重数据**/
        InterfExecuteInfo interfExecuteInfo = (InterfExecuteInfo)this.getParams().get("executeInfo");
        acctEntity.setResovleDate(interfExecuteInfo.getInterfDate());
        acctEntity.setParams(this.getParams());
        /**add by wujie on 20180731 end**/
        // 调用净值型基础信息处理公用service
        LendDealRecProcessorResult result = lendDealRecComForInterfService.execute(
            acctEntity);
        return result;
    }

}
