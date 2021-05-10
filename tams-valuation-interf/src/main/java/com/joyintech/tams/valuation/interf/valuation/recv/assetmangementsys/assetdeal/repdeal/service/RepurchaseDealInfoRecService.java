/*
 * 文件名：NPrdBaseInfoComForInterfService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wudebao
 * 修改时间：2018年6月22日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.repdeal.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.constant.TempConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.RepurchaseDealInfoValCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.assetdeal.repdeal.RepurchaseDealTemplateRecService;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.repdeal.dto.RepurchaseDealInfoProcessorResult;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 批处理启动文件接收批处理 对回购交易信息接口文件系列机型处理，供给Writer使用
 * 
 * @author chufengjie
 * @version 1.0 2018年8月7日
 * @see RepurchaseDealInfoRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class RepurchaseDealInfoRecService {

    /**
     * 交易信息-回购交易 模板处理Service
     */
    @Autowired
    private RepurchaseDealTemplateRecService repurchaseDealTemplateRecService;

//    /**
//     * 交易信息-买断式回购派息交易 模板处理Service
//     */
//    @Autowired
//    private DividendDealTemplateRecService dividendDealTemplateRecService;

    /**
     * 交易信息文件处理
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @SuppressWarnings({"unchecked"})
    public RepurchaseDealInfoProcessorResult execute(TemplateOrderInfoReaderResult acctEntity) {

        // 定义返回变量
        RepurchaseDealInfoValCommonResult rtnResult = new RepurchaseDealInfoValCommonResult();

        // 设置启动源属性
        TemplateSourceFlgDTO templateSourceFlgDTO = new TemplateSourceFlgDTO();
        templateSourceFlgDTO.setCallSourceType(DictCodeInterfList.文件解析功能启动源_TASK.getValue());
        Map<String,Object> paramMap = new HashMap<String,Object>();
        List<String> bookSetIds = (List<String>)acctEntity.getParams().get(InterfCommonConstant.接口_运行接口账套ID);
        paramMap.put(InterfCommonConstant.接口_运行接口账套ID,bookSetIds);
        paramMap.put(InterfCommonConstant.接口模式,(String)acctEntity.getParams().get(InterfCommonConstant.接口模式));
        List<String> ps = new ArrayList<String>();
        List<String> busiTypes = new ArrayList<String>();
        // 01-回购交易
        if (TempConstant.回购交易.equals(acctEntity.getTmpEName())) {
            rtnResult = repurchaseDealTemplateRecService.execute(templateSourceFlgDTO,
                acctEntity);
            busiTypes.add(DictCodeList.业务类型_债券买断式回购交易.getValue());
            busiTypes.add(DictCodeList.业务类型_债券质押式回购交易.getValue());
            paramMap.put("busiTypelist", busiTypes);
            
            ps.add(DictCodeList.交易方向_买入.getValue());
            ps.add(DictCodeList.交易方向_卖出.getValue());
            ps.add(DictCodeList.交易方向_到期.getValue());
            /** modify by yanya on 20191111 增加提前终止、逾期返售 start**/
            ps.add(DictCodeListExt3.交易方向_投资端_提前终止.getValue());
            ps.add(DictCodeListExt3.交易方向_投资端_逾期返售.getValue());
            /** modify by yanya on 20191111 增加提前终止、逾期返售 end**/
            paramMap.put("pslist", ps);

            /**add by wujie on 20210506 start**/
            paramMap.put(InterfCommonConstant.读取文件状态, rtnResult.getParamMap()==null?null:rtnResult.getParamMap().get(InterfCommonConstant.读取文件状态));
            /**add by wujie on 20210506 end**/
            
        }
//        // 02-买断式回购派息交易
//        else if (TempConstant.买断式回购派息交易.equals(acctEntity.getTmpEName())) {
//            rtnResult = dividendDealTemplateRecService.execute(templateSourceFlgDTO,
//                acctEntity);
//            paramMap.put("busiType", DictCodeList.业务类型_债券买断式回购交易.getValue());
//            ps.add(DictCodeList.交易方向_债券派息.getValue());
//            paramMap.put("pslist", ps);//删除除这些交易方向的数据
//        }else{
//        	paramMap.put("busiType", "");
//        	paramMap.put("pslist", null);
//        }
        rtnResult.setParamMap(paramMap);
        return new RepurchaseDealInfoProcessorResult(rtnResult);
    }

}
