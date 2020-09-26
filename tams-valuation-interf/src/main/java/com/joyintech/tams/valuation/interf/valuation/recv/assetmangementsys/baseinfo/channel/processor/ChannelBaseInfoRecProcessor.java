/*
 * 文件名：ChannelBaseInfoRecProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2019年3月12日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.channel.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.dto.InterfNeedOperateEntity;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.interf.core.InterfBaseProcessor;
import com.joyintech.tams.valuation.interf.core.entity.InterfExecuteInfo;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.channel.dto.ChannelBaseInfoProcessorResult;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.channel.service.ChannelBaseInfoRecService;


/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author HuangJian
 * @version 1.0 2019年3月12日
 * @see ChannelBaseInfoRecProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ChannelBaseInfoRecProcessor extends InterfBaseProcessor<InterfNeedOperateEntity, ChannelBaseInfoProcessorResult>{

    @Autowired
    private ChannelBaseInfoRecService channelBaseInfoRecService;
    
    
    @Override
    public ChannelBaseInfoProcessorResult process(InterfNeedOperateEntity item)
        throws Exception {
                
        // 获取reader方法的返回值(processor并发数据结构)
        TemplateOrderInfoReaderResult acctEntity = (TemplateOrderInfoReaderResult)item;
        /**add by wujie on 21090325 start**/
        InterfExecuteInfo interfExecuteInfo = (InterfExecuteInfo)this.getParams().get("executeInfo");
        acctEntity.setResovleDate(interfExecuteInfo.getInterfDate());
        /**add by wujie on 21090325 end**/
        //调用信息处理公用service
        ChannelBaseInfoProcessorResult  result = channelBaseInfoRecService.execute(acctEntity); 
        
        return result;
    }
}
