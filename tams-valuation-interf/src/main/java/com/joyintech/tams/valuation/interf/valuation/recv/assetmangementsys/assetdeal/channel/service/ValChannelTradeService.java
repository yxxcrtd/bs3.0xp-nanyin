/*
 * 文件名：ValChannelTradeService.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：zhangwei 修改时间：2019年3月12日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.channel.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.constant.TempConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.dto.ValChannelTradeValCommonResult;
import com.joyintech.tams.valuation.common.interf.base.service.assetdeal.channel.ValChannelProdDealTemplateRecService;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.channel.dto.ValChannelTradeProcessorResult;

/**
 * 通道产品-交易数据接口 Processor Service
 * 
 * @author zhangwei
 * @version 1.0 2019年3月12日
 * @see ValChannelTradeService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ValChannelTradeService {

    @Autowired
    private ValChannelProdDealTemplateRecService valChannelProdDealTemplateRecService;

    @SuppressWarnings("unchecked")
    public ValChannelTradeProcessorResult execute(TemplateOrderInfoReaderResult acctEntity) {
        // 初始化 模板解析对象
        ValChannelTradeValCommonResult result = new ValChannelTradeValCommonResult();

        // 设置启动源属性
        TemplateSourceFlgDTO templateSourceFlgDTO = new TemplateSourceFlgDTO();
        templateSourceFlgDTO.setCallSourceType(DictCodeInterfList.文件解析功能启动源_TASK.getValue());

        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<String> bookSetIds = (List<String>)acctEntity.getParams().get(InterfCommonConstant.接口_运行接口账套ID);
        paramMap.put(InterfCommonConstant.接口_运行接口账套ID, bookSetIds);
        paramMap.put(InterfCommonConstant.接口模式, (String)acctEntity.getParams().get(InterfCommonConstant.接口模式));
        paramMap.put("TMP_E_NAME", acctEntity.getTmpEName());
        /** add by wujie on 20190321 start **/
        List<String> ps = new ArrayList<String>();
        /** add by wujie on 20190321 end **/

        if (TempConstant.理财产品借通道投资交易.equals(acctEntity.getTmpEName())) {
            result = valChannelProdDealTemplateRecService.execute(templateSourceFlgDTO, acctEntity);

            /** add by wujie on 20190321 start **/
            paramMap.put("busiType", DictCodeList.业务类型_货币基金交易_货基_类货基资产交易.getValue());

            paramMap.put("finprodType", DictCodeListExt1.金融产品类型_计划类资产.getValue());

            paramMap.put("assetProfitType", DictCodeList.收益类型_货币型.getValue());

            paramMap.put("IS_CHL", DictCodeList.是否_是.getValue());

            ps.add(DictCodeList.交易方向_申购确认.getValue());
            ps.add(DictCodeList.交易方向_赎回确认.getValue());
            ps.add(DictCodeList.交易方向_买入.getValue());
            ps.add(DictCodeList.交易方向_卖出.getValue());
            paramMap.put("pslist", ps);
            /** add by wujie on 20190321 end **/
        }
        else {
            result.setDatanum(0);
        }
        result.setParamMap(paramMap);

        return new ValChannelTradeProcessorResult(result);
    }

}
