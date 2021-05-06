/*
 * 文件名：PlanAssetCurrencyDealRecService.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：估值核算系统V1.0 修改人：HuangJian
 * 修改时间：2019年1月23日 修改内容：新建 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.pacd.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.constant.TempConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.PlanAssetCurrencyDealCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.assetdeal.pacd.CPlannedAffirmPurDealTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.assetdeal.pacd.CPlannedApplyPurDealTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.assetdeal.pacd.CPlannedBuyAndSellDealTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.assetdeal.pacd.CPlannedCashBonusDealTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.assetdeal.pacd.CPlannedRedAccoutDealTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.assetdeal.pacd.CPlannedRedAffirmDealTemplateRecService;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.pacd.dto.PlanAssetCurrencyDealRecProcessorResult;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 计划类资产货币型交易
 * 
 * @author HuangJian
 * @version 1.0 2019年1月23日
 * @see PlanAssetCurrencyDealRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class PlanAssetCurrencyDealRecService {

    @Autowired
    private CPlannedApplyPurDealTemplateRecService pacdApplyPurDealTemplateRecService;

    @Autowired
    private CPlannedAffirmPurDealTemplateRecService pacdAffirmPurDealTemplateRecService;

    @Autowired
    private CPlannedRedAffirmDealTemplateRecService pacdRedAffirmDealTemplateRecService;

    @Autowired
    private CPlannedRedAccoutDealTemplateRecService pacdRedAccoutDealTemplateRecService;

    @Autowired
    private CPlannedCashBonusDealTemplateRecService pacdCashBonusDealTemplateRecService;

    @Autowired
    private CPlannedBuyAndSellDealTemplateRecService pacdBuyAndSellDealTemplateRecService;

    @SuppressWarnings({"unchecked"})
    public PlanAssetCurrencyDealRecProcessorResult execute(TemplateOrderInfoReaderResult acctEntity) {
        // 设置启动源属性
        TemplateSourceFlgDTO templateSourceFlgDTO = new TemplateSourceFlgDTO();
        templateSourceFlgDTO.setCallSourceType(DictCodeInterfList.文件解析功能启动源_TASK.getValue());
        PlanAssetCurrencyDealCommonResult resultcommom = new PlanAssetCurrencyDealCommonResult();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<String> bookSetIds = (List<String>)acctEntity.getParams().get(InterfCommonConstant.接口_运行接口账套ID);
        paramMap.put(InterfCommonConstant.接口_运行接口账套ID, bookSetIds);
        paramMap.put(InterfCommonConstant.接口模式, (String)acctEntity.getParams().get(InterfCommonConstant.接口模式));
        paramMap.put("TMP_E_NAME", acctEntity.getTmpEName());
        List<String> ps = new ArrayList<String>();

        /** add by wujie on 20190225 start **/
        paramMap.put("finprodType", DictCodeListExt1.金融产品类型_计划类资产.getValue());
        paramMap.put("assetProfitType", DictCodeList.收益类型_货币型.getValue());
        /** add by wujie on 20190225 end **/

        /** add by wujie on 20190321 start **/
        paramMap.put("IS_CHL", DictCodeList.是否_否.getValue());
        /** add by wujie on 20190321 end **/

        if (TempConstant.计划类资产货币型申购申请交易.equals(acctEntity.getTmpEName())) {
            paramMap.put("busiType", DictCodeList.业务类型_货币基金交易_货基_类货基资产交易.getValue());
            ps.add(DictCodeList.交易方向_申购.getValue());
            paramMap.put("pslist", ps);
            resultcommom = pacdApplyPurDealTemplateRecService.execute(templateSourceFlgDTO, acctEntity);

            /**add by wujie on 20210506 start**/
            paramMap.put(InterfCommonConstant.读取文件状态, resultcommom.getParamMap()==null?null:resultcommom.getParamMap().get(InterfCommonConstant.读取文件状态));
            /**add by wujie on 20210506 end**/

        }
        else if (TempConstant.计划类资产货币型申购确认交易.equals(acctEntity.getTmpEName())) {
            paramMap.put("busiType", DictCodeList.业务类型_货币基金交易_货基_类货基资产交易.getValue());
            ps.add(DictCodeList.交易方向_申购确认.getValue());
            // ON:开 OFF:关
            if ("ON".equals(CacheUtil.getSysParam("FUND_APPLY_KG"))) {
                ps.add(DictCodeList.交易方向_申购.getValue());
            }
            paramMap.put("pslist", ps);

            resultcommom = pacdAffirmPurDealTemplateRecService.execute(templateSourceFlgDTO, acctEntity);

            /**add by wujie on 20210506 start**/
            paramMap.put(InterfCommonConstant.读取文件状态, resultcommom.getParamMap()==null?null:resultcommom.getParamMap().get(InterfCommonConstant.读取文件状态));
            /**add by wujie on 20210506 end**/
        }
        else if (TempConstant.计划类资产货币型赎回确认交易.equals(acctEntity.getTmpEName())) {
            paramMap.put("busiType", DictCodeList.业务类型_货币基金交易_货基_类货基资产交易.getValue());
            ps.add(DictCodeList.交易方向_赎回确认.getValue());
            paramMap.put("pslist", ps);
            resultcommom = pacdRedAffirmDealTemplateRecService.execute(templateSourceFlgDTO, acctEntity);

            /**add by wujie on 20210506 start**/
            paramMap.put(InterfCommonConstant.读取文件状态, resultcommom.getParamMap()==null?null:resultcommom.getParamMap().get(InterfCommonConstant.读取文件状态));
            /**add by wujie on 20210506 end**/
        }
        else if (TempConstant.计划类资产货币型赎回到账交易.equals(acctEntity.getTmpEName())) {
            paramMap.put("busiType", DictCodeList.业务类型_货币基金交易_货基_类货基资产交易.getValue());
            ps.add(DictCodeList.交易方向_赎回到账.getValue());
            paramMap.put("pslist", ps);
            resultcommom = pacdRedAccoutDealTemplateRecService.execute(templateSourceFlgDTO, acctEntity);

            /**add by wujie on 20210506 start**/
            paramMap.put(InterfCommonConstant.读取文件状态, resultcommom.getParamMap()==null?null:resultcommom.getParamMap().get(InterfCommonConstant.读取文件状态));
            /**add by wujie on 20210506 end**/
        }
        else if (TempConstant.计划类资产货币型存续期交易.equals(acctEntity.getTmpEName())) {
            paramMap.put("busiType", DictCodeList.业务类型_货币基金交易_货基_类货基资产交易.getValue());
            ps.add(DictCodeList.交易方向_现金分红.getValue());
            ps.add(DictCodeList.交易方向_红利再投.getValue());
            ps.add(DictCodeList.交易方向_到期.getValue());
            paramMap.put("pslist", ps);
            resultcommom = pacdCashBonusDealTemplateRecService.execute(templateSourceFlgDTO, acctEntity);

            /**add by wujie on 20210506 start**/
            paramMap.put(InterfCommonConstant.读取文件状态, resultcommom.getParamMap()==null?null:resultcommom.getParamMap().get(InterfCommonConstant.读取文件状态));
            /**add by wujie on 20210506 end**/
        }
        else if (TempConstant.计划类资产货币型买入卖出交易.equals(acctEntity.getTmpEName())) {

            paramMap.put("busiType", DictCodeList.业务类型_货币基金交易_货基_类货基资产交易.getValue());

            ps.add(DictCodeList.交易方向_买入.getValue());
            ps.add(DictCodeList.交易方向_卖出.getValue());
            paramMap.put("pslist", ps);
            resultcommom = pacdBuyAndSellDealTemplateRecService.execute(templateSourceFlgDTO, acctEntity);

            /**add by wujie on 20210506 start**/
            paramMap.put(InterfCommonConstant.读取文件状态, resultcommom.getParamMap()==null?null:resultcommom.getParamMap().get(InterfCommonConstant.读取文件状态));
            /**add by wujie on 20210506 end**/
        }

        resultcommom.setParamMap(paramMap);
        return new PlanAssetCurrencyDealRecProcessorResult(resultcommom);
    }
}
