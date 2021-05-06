/*
 * 文件名：CProdDealInfoRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chendijun
 * 修改时间：2018年8月13日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.cprod.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.constant.TempConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.CProdDealInfoValCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.assetdeal.cprddeal.CPrdDealBonusDealTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.assetdeal.cprddeal.CPrdDealFeeDealTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.assetdeal.cprddeal.CPrdDealPeriodDealTemplateRecService;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.cprod.dto.CProdDealInfoProcessorResult;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 批处理启动文件接收批处理 对货币型产品-产品交易 文件系列机型处理，供给Writer使用
 * 
 * @author chendijun
 * @version 1.0 2018年8月13日
 * @see CProdDealInfoRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class CProdDealInfoRecService {

    /**
     * 货币型产品存续期交易表 模板处理Service
     */
    @Autowired
    private CPrdDealPeriodDealTemplateRecService cPrdDealPeriodDealTemplateRecService;

    /**
     * 货币型产品分红交易 模板处理Service
     */
    @Autowired
    private CPrdDealBonusDealTemplateRecService cPrdDealBonusDealTemplateRecService;

    /**
     * 货币型产品费用交易 模板处理Service
     */
    @Autowired
    private CPrdDealFeeDealTemplateRecService cPrdDealFeeDealTemplateRecService;

    /**
     * 批处理启动文件接收批处理
     * 
     * @param acctEntity
     * @return
     * @see
     * @since 1.0
     */
    @SuppressWarnings({"unchecked"})
    public CProdDealInfoProcessorResult execute(TemplateOrderInfoReaderResult acctEntity)
        throws Exception {
        //初始化 模板解析对象
        CProdDealInfoValCommonResult result = new CProdDealInfoValCommonResult();
        
        //设置启动源属性
        TemplateSourceFlgDTO templateSourceFlgDTO = new TemplateSourceFlgDTO();
        templateSourceFlgDTO.setCallSourceType(DictCodeInterfList.文件解析功能启动源_TASK.getValue());
        
        Map<String,Object> paramMap = new HashMap<String,Object>();
        List<String> bookSetIds = (List<String>)acctEntity.getParams().get(InterfCommonConstant.接口_运行接口账套ID);
        paramMap.put(InterfCommonConstant.接口_运行接口账套ID,bookSetIds);
        paramMap.put(InterfCommonConstant.接口模式,(String)acctEntity.getParams().get(InterfCommonConstant.接口模式));
        paramMap.put("TMP_E_NAME", acctEntity.getTmpEName());
        List<String> ps = new ArrayList<String>();

        paramMap.put("profitType", DictCodeList.收益类型_货币型.getValue());

        if(TempConstant.货币型产品存续期交易表.equals(acctEntity.getTmpEName())) {
            result = cPrdDealPeriodDealTemplateRecService.execute(templateSourceFlgDTO, acctEntity);
            paramMap.put("busiType",DictCodeList.业务类型_货币型TA交易.getValue());
            ps.add(DictCodeList.交易方向_产品申购.getValue());
            ps.add(DictCodeList.交易方向_产品认购.getValue());
            ps.add(DictCodeList.交易方向_产品赎回.getValue());
            ps.add(DictCodeList.交易方向_产品到期.getValue());
            ps.add(DictCodeList.交易方向_产品到期分红.getValue());
            ps.add(DictCodeList.交易方向_赎回收益确认.getValue());

            /**add by wujie on 20210428 start**/
            ps.add(DictCodeList.交易方向_产品提前赎回.getValue());
            ps.add(DictCodeList.交易方向_产品违约赎回.getValue());
            ps.add(DictCodeList.交易方向_份额强增.getValue());
            ps.add(DictCodeList.交易方向_份额强减.getValue());
            ps.add(DictCodeList.交易方向_提前终止.getValue());
            /**add by wujie on 20210428 start**/
            
            paramMap.put("pslist", ps);

            /**add by wujie on 20210506 start**/
            paramMap.put(InterfCommonConstant.读取文件状态, result.getParamMap()==null?null:result.getParamMap().get(InterfCommonConstant.读取文件状态));
            /**add by wujie on 20210506 end**/
            
        } 
        else if(TempConstant.货币型产品分红交易表.equals(acctEntity.getTmpEName())) {
            result = cPrdDealBonusDealTemplateRecService.execute(templateSourceFlgDTO, acctEntity);
            paramMap.put("busiType", DictCodeList.业务类型_货币型TA交易.getValue());
            ps.add(DictCodeList.交易方向_产品现金分红.getValue());
            ps.add(DictCodeList.交易方向_产品红利转投.getValue());
            paramMap.put("pslist", ps);

            /**add by wujie on 20210506 start**/
            paramMap.put(InterfCommonConstant.读取文件状态, result.getParamMap()==null?null:result.getParamMap().get(InterfCommonConstant.读取文件状态));
            /**add by wujie on 20210506 end**/
            
        } 
        else if (TempConstant.货币型产品费用交易表.equals(acctEntity.getTmpEName())) {
            result = cPrdDealFeeDealTemplateRecService.execute(templateSourceFlgDTO, acctEntity);
            paramMap.put("busiType", DictCodeList.业务类型_产品费用交易.getValue());
            ps.add(DictCodeList.交易方向_产品付费.getValue());
            paramMap.put("pslist", ps);

            /**add by wujie on 20210506 start**/
            paramMap.put(InterfCommonConstant.读取文件状态, result.getParamMap()==null?null:result.getParamMap().get(InterfCommonConstant.读取文件状态));
            /**add by wujie on 20210506 end**/
        } else {
        	result.setDatanum(0);
        }
        result.setParamMap(paramMap);
        return new CProdDealInfoProcessorResult(result);
    }

}
