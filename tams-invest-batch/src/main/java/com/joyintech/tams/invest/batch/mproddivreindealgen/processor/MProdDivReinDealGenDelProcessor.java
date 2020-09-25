/*
 * 文件名：ResetBaseRuleInfoDelProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年2月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.mproddivreindealgen.processor;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.invest.batch.mproddivreindealgen.dto.MProdDivReinDealGenReaderResult;
import com.joyintech.tams.invest.batch.mproddivreindealgen.dto.MProdDivReinDealGenResult;

/**
 * DelProcessor
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年2月16日
 * @see MProdDivReinDealGenDelProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class MProdDivReinDealGenDelProcessor extends BaseProcessor<NeedOperateEntity, MProdDivReinDealGenResult>{

    /**
     * {@inheritDoc}
     */
    @Override
    public MProdDivReinDealGenResult process(NeedOperateEntity item)
        throws Exception {
    	MProdDivReinDealGenResult reault = new MProdDivReinDealGenResult();
    	 // 获取reader方法的返回值
    	MProdDivReinDealGenReaderResult readerResult = (MProdDivReinDealGenReaderResult) item;
    	reault.setDelDtos(readerResult.getDelDtos());
        return reault;
    }
    
}
