/*
 * 文件名：NPrdBaseInfoComForInterfService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wudebao
 * 修改时间：2018年6月22日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.nprod.service;


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
import com.joyintech.tams.valuation.common.interf.base.dto.NProdDealInfoValCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.assetdeal.nprddeal.NPrdDealBonusDealTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.assetdeal.nprddeal.NPrdDealFeeDealTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.assetdeal.nprddeal.NPrdDealPeriodDealTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.assetdeal.nprddeal.NPrdDelDealService;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.nprod.dto.NProdDealInfoProcessorResult;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 批处理启动文件接收批处理 对净值型产品-产品交易 文件系列机型处理，供给Writer使用
 * 
 * @author weipeng
 * @version 1.0 2018年6月25日
 * @see NProdDealInfoRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class NProdDealInfoRecService {

    /**
     * 存续期交易 模板处理Service
     */
    @Autowired
    private NPrdDealPeriodDealTemplateRecService nPrdDealPeriodDealTemplateRecService;

    /**
     * 分红交易 模板处理Service
     */
    @Autowired
    private NPrdDealBonusDealTemplateRecService nPrdDealBonusDealTemplateRecService;

    /**
     * 费用交易 模板处理Service
     */
    @Autowired
    private NPrdDealFeeDealTemplateRecService nPrdDealFeeDealTemplateRecService;
    
    @Autowired
    private NPrdDelDealService nPrdDelDealService;

    /**
     * 批处理启动文件接收批处理
     * 
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @SuppressWarnings({"unchecked"})
    public NProdDealInfoProcessorResult execute(TemplateOrderInfoReaderResult acctEntity) {
        //定义模板解析返回结果变量
        NProdDealInfoValCommonResult rtnResult = new NProdDealInfoValCommonResult();
        // 设置启动源属性
        TemplateSourceFlgDTO templateSourceFlgDTO = new TemplateSourceFlgDTO();
        templateSourceFlgDTO.setCallSourceType(DictCodeInterfList.文件解析功能启动源_TASK.getValue());
        
        Map<String,Object> paramMap = new HashMap<String,Object>();
        List<String> bookSetIds = (List<String>)acctEntity.getParams().get(InterfCommonConstant.接口_运行接口账套ID);
        paramMap.put(InterfCommonConstant.接口_运行接口账套ID,bookSetIds);
        paramMap.put(InterfCommonConstant.接口模式,(String)acctEntity.getParams().get(InterfCommonConstant.接口模式));
        paramMap.put("TMP_E_NAME", acctEntity.getTmpEName());
        List<String> ps = new ArrayList<String>();
        
        if (TempConstant.净值型产品存续期交易表.equals(acctEntity.getTmpEName())) {
            rtnResult = nPrdDealPeriodDealTemplateRecService.execute(templateSourceFlgDTO,acctEntity);
            
            paramMap.put("busiType", DictCodeList.业务类型_净值型TA交易.getValue());
            ps.add(DictCodeList.交易方向_产品现金分红.getValue());
            ps.add(DictCodeList.交易方向_产品红利转投.getValue());
            
            /*modify by wujie on 20190213
            ps.add(DictCodeList.交易方向_产品付费.getValue());
            */
            
            ps.add(DictCodeList.交易方向_产品除权登记.getValue());
            paramMap.put("pslistNot", ps);

            /**add by wujie on 20210506 start**/
            paramMap.put(InterfCommonConstant.读取文件状态, rtnResult.getParamMap()==null?null:rtnResult.getParamMap().get(InterfCommonConstant.读取文件状态));
            /**add by wujie on 20210506 end**/
        }
        else if (TempConstant.净值型产品分红交易表.equals(acctEntity.getTmpEName())) {
            rtnResult = nPrdDealBonusDealTemplateRecService.execute(templateSourceFlgDTO,acctEntity);
            
            paramMap.put("busiType", DictCodeList.业务类型_净值型TA交易.getValue());
            ps.add(DictCodeList.交易方向_产品现金分红.getValue());
            ps.add(DictCodeList.交易方向_产品红利转投.getValue());
            paramMap.put("pslist", ps);

            /**add by wujie on 20210506 start**/
            paramMap.put(InterfCommonConstant.读取文件状态, rtnResult.getParamMap()==null?null:rtnResult.getParamMap().get(InterfCommonConstant.读取文件状态));
            /**add by wujie on 20210506 end**/
        }
        else if (TempConstant.净值型产品费用交易表.equals(acctEntity.getTmpEName())) {
            rtnResult = nPrdDealFeeDealTemplateRecService.execute(templateSourceFlgDTO,acctEntity);
            paramMap.put("busiType", DictCodeList.业务类型_产品费用交易.getValue());
            ps.add(DictCodeList.交易方向_产品付费.getValue());
            paramMap.put("pslist", ps);
            
            /**modify by wujie on 20190122 start remark:重复导入删除逻辑调整
            if(rtnResult.getParamMap()!=null){
            	 paramMap.put("cqFtradeIds", rtnResult.getParamMap().get("cqFtradeIds"));
            }
            int i = rtnResult.getListValFinProductDeal()==null?0:(rtnResult.getListValFinProductDeal().size());
        	paramMap.put("resultNum", i);
        	**/
            
            paramMap.put("profitType", DictCodeList.收益类型_净值型.getValue());
            
            /**modify by wujie on 20190122 end**/

            /**add by wujie on 20210506 start**/
            paramMap.put(InterfCommonConstant.读取文件状态, rtnResult.getParamMap()==null?null:rtnResult.getParamMap().get(InterfCommonConstant.读取文件状态));
            /**add by wujie on 20210506 end**/
            
        } else if (TempConstant.净值型产品分红除权表.equals(acctEntity.getTmpEName())) {
            rtnResult = nPrdDelDealService.execute(templateSourceFlgDTO,acctEntity);
            paramMap.put("busiType", DictCodeList.业务类型_净值型TA交易.getValue());
            ps.add(DictCodeList.交易方向_产品除权登记.getValue());
            paramMap.put("pslist", ps);

            /**add by wujie on 20210506 start**/
            paramMap.put(InterfCommonConstant.读取文件状态, rtnResult.getParamMap()==null?null:rtnResult.getParamMap().get(InterfCommonConstant.读取文件状态));
            /**add by wujie on 20210506 end**/

        }else{
        	rtnResult.setDatanum(0);
        	paramMap.put("busiType", "");
        	paramMap.put("pslist", null);
        }
        rtnResult.setParamMap(paramMap);
        return new NProdDealInfoProcessorResult(rtnResult);
    }

}
