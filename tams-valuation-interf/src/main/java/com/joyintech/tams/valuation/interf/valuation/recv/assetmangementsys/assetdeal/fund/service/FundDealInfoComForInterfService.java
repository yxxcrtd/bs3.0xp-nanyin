/*
 * 文件名：FundDealInfoComForInterfService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：HuangJian
 * 修改时间：2018年6月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.fund.service;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.constant.TempConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.FundDealInfoForInterfRecCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.assetdeal.fund.*;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.fund.dto.FundDealInfoRecProcessorResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author HuangJian
 * @version 1.0 2018年6月27日
 * @see FundDealInfoComForInterfService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class FundDealInfoComForInterfService {

    /**
     * 红利再投交易
     */
    @Autowired
    private FundBonusReinvestmentDealRecService fundBonusReinvestmentDealRecService;

    /**
     * 现金分红交易
     */
    @Autowired
    private FundCashDividendDealRecService fundCashDividendDealRecService;

    /**
     * 货币基金赎回确认交易
     */
    @Autowired
    private FundMoneyFundRedeemConfirmDealRecService fundMoneyFundRedeemConfirmDealRecService;

    /**
     * 赎回到账交易
     */
    @Autowired
    private FundNMoneyFundRedeemConfirmDealRecService fundNMoneyFundRedeemConfirmDealRecService;

    /**
     * 赎回到账交易
     */
    @Autowired
    private FundRedeemArrivalAccountDealRecService fundRedeemArrivalAccountDealRecService;

    /**
     * 申购申请交易
     */
    @Autowired
    private FundSubscribeApplyDealRecService fundSubscribeApplyDealRecService;
    
    /**
     * 买入卖出交易
     */
    @Autowired
    private FundBuyAndSellDealRecService fundBuyAndSellDealRecService;

    /**
     * 申购确认交易
     */
    @Autowired
    private FundSubscribeConfirmDealRecService fundSubscribeConfirmDealRecService;

    /**
     * 基金份额折算交易
     */
    @Autowired
    private FundShareConvertDealRecService fundShareConvertDealRecService;
    
    /**
     * 基金转换交易
     */
    @Autowired
    private FundChangeDealRecService fundChangeDealRecService;

    @SuppressWarnings({"unchecked"})
    public FundDealInfoRecProcessorResult execute(TemplateOrderInfoReaderResult acctEntity) {
        FundDealInfoForInterfRecCommonResult result = new FundDealInfoForInterfRecCommonResult();
        // 设置启动源属性
        TemplateSourceFlgDTO templateSourceFlgDTO = new TemplateSourceFlgDTO();
        templateSourceFlgDTO.setCallSourceType(DictCodeInterfList.文件解析功能启动源_TASK.getValue());
        Map<String,Object> paramMap = new HashMap<String,Object>();
        List<String> bookSetIds = (List<String>)acctEntity.getParams().get(InterfCommonConstant.接口_运行接口账套ID);
        paramMap.put(InterfCommonConstant.接口_运行接口账套ID,bookSetIds);
        paramMap.put(InterfCommonConstant.接口模式,(String)acctEntity.getParams().get(InterfCommonConstant.接口模式));
        List<String> ps = new ArrayList<String>();
        List<String> busiTypes = new ArrayList<String>();
        
        /**add by wujie on 20190225 start**/
        paramMap.put("finprodType", DictCodeListExt1.金融产品类型_基金.getValue());
        /**add by wujie on 20190225 end**/
        
        if (TempConstant.买入卖出交易.equals(acctEntity.getTmpEName())) {
            busiTypes.add(DictCodeList.业务类型_货币基金交易_货基_类货基资产交易.getValue());
            busiTypes.add(DictCodeList.业务类型_非货币基金交易_非货基_非类货基资产交易.getValue());
            paramMap.put("busiTypelist", busiTypes);
            
            ps.add(DictCodeList.交易方向_买入.getValue());
            ps.add(DictCodeList.交易方向_卖出.getValue());
            paramMap.put("pslist", ps);
            result = fundBuyAndSellDealRecService.execute(templateSourceFlgDTO, acctEntity);
        }
        else if (TempConstant.申购申请交易.equals(acctEntity.getTmpEName())) {
            busiTypes.add(DictCodeList.业务类型_货币基金交易_货基_类货基资产交易.getValue());
            busiTypes.add(DictCodeList.业务类型_非货币基金交易_非货基_非类货基资产交易.getValue());
            paramMap.put("busiTypelist", busiTypes);
            
            ps.add(DictCodeList.交易方向_申购.getValue());
            ps.add(DictCodeListExt2.交易方向_认购.getValue());
            paramMap.put("pslist", ps);
            
            result = fundSubscribeApplyDealRecService.execute(templateSourceFlgDTO, acctEntity);
        }
        else if (TempConstant.申购确认交易.equals(acctEntity.getTmpEName())) {
            busiTypes.add(DictCodeList.业务类型_货币基金交易_货基_类货基资产交易.getValue());
            busiTypes.add(DictCodeList.业务类型_非货币基金交易_非货基_非类货基资产交易.getValue());
            paramMap.put("busiTypelist", busiTypes);
            
            ps.add(DictCodeList.交易方向_申购确认.getValue());
            ps.add(DictCodeList.交易方向_认购确认.getValue());
            
            //ON:开 OFF:关
            if("ON".equals(CacheUtil.getSysParam("FUND_APPLY_KG"))){
                ps.add(DictCodeList.交易方向_申购.getValue());
                ps.add(DictCodeListExt2.交易方向_认购.getValue());
            }
            paramMap.put("pslist", ps);
            
            result = fundSubscribeConfirmDealRecService.execute(templateSourceFlgDTO, acctEntity);
        }
        else if (TempConstant.货币基金赎回确认交易.equals(acctEntity.getTmpEName())) {
            paramMap.put("busiType", DictCodeList.业务类型_货币基金交易_货基_类货基资产交易.getValue());
            ps.add(DictCodeList.交易方向_赎回确认.getValue());
            paramMap.put("pslist", ps);
            result = fundMoneyFundRedeemConfirmDealRecService.execute(templateSourceFlgDTO,
                acctEntity);
        }
        else if (TempConstant.净值型基金赎回确认交易.equals(acctEntity.getTmpEName())) {
            paramMap.put("busiType", DictCodeList.业务类型_非货币基金交易_非货基_非类货基资产交易.getValue());
            ps.add(DictCodeList.交易方向_赎回确认.getValue());
            paramMap.put("pslist", ps);
            result = fundNMoneyFundRedeemConfirmDealRecService.execute(templateSourceFlgDTO,
                acctEntity);
        }
        else if (TempConstant.赎回到账交易.equals(acctEntity.getTmpEName())) {
            busiTypes.add(DictCodeList.业务类型_货币基金交易_货基_类货基资产交易.getValue());
            busiTypes.add(DictCodeList.业务类型_非货币基金交易_非货基_非类货基资产交易.getValue());
            paramMap.put("busiTypelist", busiTypes);
            
            ps.add(DictCodeList.交易方向_赎回到账.getValue());
            paramMap.put("pslist", ps);
            result = fundRedeemArrivalAccountDealRecService.execute(templateSourceFlgDTO,
                acctEntity);
        }
        else if (TempConstant.现金分红交易.equals(acctEntity.getTmpEName())) {
            busiTypes.add(DictCodeList.业务类型_货币基金交易_货基_类货基资产交易.getValue());
            busiTypes.add(DictCodeList.业务类型_非货币基金交易_非货基_非类货基资产交易.getValue());
            paramMap.put("busiTypelist", busiTypes);
            
            ps.add(DictCodeList.交易方向_现金分红.getValue());
            paramMap.put("pslist", ps);
            result = fundCashDividendDealRecService.execute(templateSourceFlgDTO, acctEntity);
            
            List<String> fpsList = new ArrayList<String>();
            fpsList.add(DictCodeList.交易方向_除权登记.getValue());
            paramMap.put("fpsList", fpsList);
        }
        else if (TempConstant.红利再投交易.equals(acctEntity.getTmpEName())) {
            busiTypes.add(DictCodeList.业务类型_货币基金交易_货基_类货基资产交易.getValue());
            busiTypes.add(DictCodeList.业务类型_非货币基金交易_非货基_非类货基资产交易.getValue());
            paramMap.put("busiTypelist", busiTypes);
            
            ps.add(DictCodeList.交易方向_红利再投.getValue());
            paramMap.put("pslist", ps);
            result = fundBonusReinvestmentDealRecService.execute(templateSourceFlgDTO, acctEntity);
            
            List<String> fpsList = new ArrayList<String>();
            fpsList.add(DictCodeList.交易方向_除权登记.getValue());
            paramMap.put("fpsList", fpsList);
            
        }
        /** add by yanya on 20191116 增加基金份额折算交易处理 start**/
        else if (TempConstant.基金份额折算交易.equals(acctEntity.getTmpEName())) {
            busiTypes.add(DictCodeList.业务类型_货币基金交易_货基_类货基资产交易.getValue());
            busiTypes.add(DictCodeList.业务类型_非货币基金交易_非货基_非类货基资产交易.getValue());
            paramMap.put("busiTypelist", busiTypes);

            ps.add(DictCodeList.交易方向_份额强增.getValue());
            ps.add(DictCodeList.交易方向_份额强减.getValue());
            paramMap.put("pslist", ps);
            result = fundShareConvertDealRecService.execute(templateSourceFlgDTO, acctEntity);
        }
        /** add by yanya on 20191116 增加基金份额折算交易处理 end**/
        
        /** add by tanxuan on 20200110  start**/
        else if (TempConstant.基金转换交易.equals(acctEntity.getTmpEName())) {
            busiTypes.add(DictCodeList.业务类型_货币基金交易_货基_类货基资产交易.getValue());
            busiTypes.add(DictCodeList.业务类型_非货币基金交易_非货基_非类货基资产交易.getValue());
            paramMap.put("busiTypelist", busiTypes);

            ps.add(DictCodeListExt3.交易方向_转换申购确认.getValue());
            ps.add(DictCodeListExt3.交易方向_转换赎回确认.getValue());
            paramMap.put("pslist", ps);
            result = fundChangeDealRecService.execute(templateSourceFlgDTO, acctEntity);
        }
        /** add by tanxuan on 20200110 end**/
        
        else{
        	result.setDataNum(0);
        }
        result.setParamMap(paramMap);
        return new FundDealInfoRecProcessorResult(result);
    }
}
