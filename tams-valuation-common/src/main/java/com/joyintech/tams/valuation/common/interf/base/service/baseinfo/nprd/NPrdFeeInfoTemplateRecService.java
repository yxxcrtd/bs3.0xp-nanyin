/*
 * 文件名：NPrdBaseinfoTemplateRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年6月22日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.nprd;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.BokDetailId;
import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.NPrdBaseInfoValCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdFeeInfoN;

/**
 * 净值型产品费用基础信息表(Val_PRD_FEE_INFO_N) 〈功能详细描述〉
 * 
 * @author 吴德宝
 * @version 1.0 2018年6月22日
 * @see NPrdFeeInfoTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class NPrdFeeInfoTemplateRecService {

    /**
     * 净值型产品基本信息模板处理service <功能详细描述>
     * 
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;
    
    @SuppressWarnings({"unchecked", "rawtypes"})
    public NPrdBaseInfoValCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                               TemplateOrderInfoReaderResult acctEntity) {
        // 定义返回变量
        NPrdBaseInfoValCommonResult result = new NPrdBaseInfoValCommonResult();
        List<ValPrdFeeInfoN> listValPrdFeeInfoN = new ArrayList<ValPrdFeeInfoN>();
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);

        // 2、List数据接收转换
        listValPrdFeeInfoN = returnResult.getT(ValPrdFeeInfoN.class);

        // 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        Map<String, Object> map = handleValPrdFeeInfoN(listValPrdFeeInfoN);
        // 现金流信息List
        List<FinCashInfo> listValCashInfo = (List<FinCashInfo>)map.get("ValCashInfo");
        result.setListValCashInfo(listValCashInfo);

        // 现金流计算规则List
        List<FinCashCalcRule> listValCashCalcRule = (List<FinCashCalcRule>)map.get(
            "ValCashCalcRule");
        result.setListValCashCalcRule(listValCashCalcRule);

        // 现金流支付规则List
        List<FinCashPayRule> listValCashPayRule = (List<FinCashPayRule>)map.get("ValCashPayRule");
        result.setListValCashPayRule(listValCashPayRule);

        // 账务明细代码List
        List<BokDetailId> listValBokDetailId = (List<BokDetailId>)map.get("ValBokDetailId");
        result.setListValBokDetailId(listValBokDetailId);
        // 添加日志
        result.setValInterflog(returnResult.getValInterflog());
        // 返回处理结果数据
        return result;
    }

    /**
     * 04-费用基础信息
     * 
     * @param listValPrdFeeInfoN 净值型产品费用基础信息
     * @return 现金流信息List + 现金流计算规则List + 现金流支付规则List
     * @see
     * @since 1.0
     */
    private Map<String, Object> handleValPrdFeeInfoN(List<ValPrdFeeInfoN> listValPrdFeeInfoN) {
        Map<String, Object> resultMap = new HashMap<>();
        // 现金流信息List
        List<FinCashInfo> listValCashInfo = new ArrayList<FinCashInfo>();
        // 现金流计算规则List
        List<FinCashCalcRule> listValCashCalcRule = new ArrayList<FinCashCalcRule>();
        // 现金流支付规则List
        List<FinCashPayRule> listValCashPayRule = new ArrayList<FinCashPayRule>();
        // 账务明细表List
//        List<BokDetailId> listValBokDetailId = new ArrayList<BokDetailId>();
        for (ValPrdFeeInfoN dto : listValPrdFeeInfoN) {
            // 现金流信息表
            FinCashInfo valCashInfo = this.getValCashInfo(dto);
            listValCashInfo.add(valCashInfo);
            // 现金流计算规则表
            FinCashCalcRule valCashCalcRule = this.getValCashCalcRule(dto,valCashInfo);
            listValCashCalcRule.add(valCashCalcRule);
            // 现金流支付规则表
            FinCashPayRule valCashPayRule = this.getValCashPayRule(dto,valCashInfo);
            listValCashPayRule.add(valCashPayRule);
            // 生成账务明细表
//            BokDetailId valBokDetailId = this.getBokDetailId(dto, valCashInfo);
//            listValBokDetailId.add(valBokDetailId);
        }
        resultMap.put("ValCashInfo", listValCashInfo);
        resultMap.put("ValCashCalcRule", listValCashCalcRule);
        resultMap.put("ValCashPayRule", listValCashPayRule);
//        resultMap.put("ValBokDetailId",listValBokDetailId);
        return resultMap;
    }

    /**
     * T4 现金流信息表 接收
     * 
     * @param dto 04-费用基础信息
     * @return 现金流信息表
     * @see
     * @since 1.0
     */
    private FinCashInfo getValCashInfo(ValPrdFeeInfoN dto) {
        FinCashInfo valCashInfo = new FinCashInfo();
        // 现金流代码 = D.产品代码+D.费用ID
//        valCashInfo.setCashId(InterfHelper.getMd5CashId_(dto.getProdId(),dto.getFeeId()));
        String cashId = null;
        if(StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_理财子产品.getValue())){
            cashId = InterfHelper.getMd5CashId_(dto.getProdId() + new SimpleDateFormat("yyyyMMdd").format(dto.getProdVdate()),
                dto.getFeeId());
        } else {
            cashId = InterfHelper.getMd5CashId_(dto.getProdId() , dto.getFeeId());
        }  
        valCashInfo.setCashId(cashId);
        // 现金流一级类型 = 03:费用
        valCashInfo.setCashType1(DictCodeListExt1.现金流一级类型_费用.getValue());
        // 现金流二级类型 = D.费用类型
        valCashInfo.setCashType2(dto.getFeeType());
        // 金融产品代码 = D.产品代码
        valCashInfo.setFinprodId(dto.getProdId());
        // 金融产品类型 = F16：理财产品
        valCashInfo.setFinprodType(dto.getFinprodType());
        // 分支序号 = 设置全局变量(默认为0）
        Long branch = null;
        if(StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_理财子产品.getValue())){
            branch = Long.valueOf(new SimpleDateFormat("yyyyMMdd").format(dto.getProdVdate()));
        } else {
            branch = InterfCommonConstant.分支序号_默认;
        }
        valCashInfo.setBranch(branch);
        return valCashInfo;
    }

    /**
     * 现金流计算规则表 接收
     * 
     * @param dto 04-费用基础信息
     * @return 现金流计算规则
     * @see
     * @since 1.0
     */
    private FinCashCalcRule getValCashCalcRule(ValPrdFeeInfoN dto,FinCashInfo T4) {
        FinCashCalcRule valCashCalcRule = new FinCashCalcRule();
        // 现金流代码 = D.产品代码+D.费用ID
        valCashCalcRule.setCashId(T4.getCashId());
        // 生效日期 = D.生效日
        valCashCalcRule.setEffDate(dto.getEffDate());
        // 计算类型 = 01：固定利率
        valCashCalcRule.setCalcType(DictCodeListExt1.计算类型_固定利率.getValue());
        // 生效利率 = D.初始费率
        valCashCalcRule.setYield(dto.getInitRate());
        // 计算基础 = D.计费基础
        valCashCalcRule.setBasis(dto.getChargeBase());
        // 基数类型 = D.计费基数
        valCashCalcRule.setBaseType(dto.getChargeBasis());
        // 基数日期类型 = D.计费基数日期类型
        valCashCalcRule.setBaseDateType(dto.getBasisDate());
        // 金融产品代码 = D.产品代码
        valCashCalcRule.setFinprodId(T4.getFinprodId());
        // 金融产品类型 = F16：理财产品
        valCashCalcRule.setFinprodType(T4.getFinprodType());
        // 分支序号 = 设置全局变量(默认为0）
        valCashCalcRule.setBranch(T4.getBranch());
        return valCashCalcRule;
    }

    /**
     * 现金流支付规则表 接收
     * 
     * @param dto 04-费用基础信息
     * @return 现金流支付规则
     * @see
     * @since 1.0
     */
    private FinCashPayRule getValCashPayRule(ValPrdFeeInfoN dto,FinCashInfo T4) {
        FinCashPayRule valCashPayRule = new FinCashPayRule();
        // 现金流代码 = D.产品代码+D.费用ID
        valCashPayRule.setCashId(T4.getCashId());
        // 生效日期 = D.生效日
        valCashPayRule.setEffDate(dto.getEffDate());
        // 支付频率 = D.付费规则
        valCashPayRule.setPayCycle(dto.getPayRule());
        // 营业日准则 = D.营业准则
//        valCashPayRule.setTdayRule(dto.getTdayRule());
        // 现金流单位 = 02：总额
        valCashPayRule.setCashUnitType(DictCodeList.现金流单位_总额.getValue());
        // 金融产品代码 = D.产品代码
        valCashPayRule.setFinprodId(T4.getFinprodId());
        // 金融产品类型 = F16：理财产品
        valCashPayRule.setFinprodType(T4.getFinprodType());
        // 支付方式 = 02:现金
        valCashPayRule.setPayType(DictCodeList.支付方式_现金.getValue());
        // 分支序号 = 设置全局变量(默认为0）
        valCashPayRule.setBranch(T4.getBranch());
        return valCashPayRule;
    }

    /**
     * 账务明细表 生成
     * 
     * @param dto 04-费用基础信息
     * @param T4 现金流信息表
     * @return 账务明细
     * @see
     * @since 1.0
     */
//    private BokDetailId getBokDetailId(ValPrdFeeInfoN dto, FinCashInfo T4) {
//        BokDetailId valBokDetailId = new BokDetailId();
//        // 账套代码 = T4.金融产品代码 根据产品代码查询账套代码
//        // 调用共通查询账套（根据产品代码）
//        BokBookset valBokBookset = valInterfCommonService.getBooksetByProdId(T4.getFinprodId());
//        if(valBokBookset != null){
//            valBokDetailId.setBooksetId(valBokBookset.getBooksetId());
//        }else{
//            valBokDetailId.setBooksetId(T4.getFinprodId());
//        }
//        // 账务明细代码 = T4.现金流代码+费用类型
//        /**modify by dongjun 20181012 start**/
//        // 业务明细代码 = D.产品代码
//        valBokDetailId.setBusiId(dto.getProdId());
//        // 业务明细类型 = 数据字典：金融产品类型.理财产品
//        valBokDetailId.setBusiDetailType(DictCodeListExt1.金融产品类型_理财产品.getValue());
//        // 投资目的 = 默认为空
//        valBokDetailId.setInvAim(null);
//        // 证券管理户代码 = 默认为空
//        valBokDetailId.setSecManageAcctId(null);
//        // 交易编号 = 默认为空
//        valBokDetailId.setTradeId(null);
//        // 四级科目尾号 = D.产品代码
//        /**modify by wujie on 20190103 start
//         valBokDetailId.setDetailSubjectNoTail(dto.getProdId());
//         **/
//        valBokDetailId.setDetailSubjectNoTail(dto.getProdId()+
//        		FinalString.LAYERING_SPLIT+
//        		DictCodeListExt1.金融产品类型_理财产品.getValue());
//        /**modify by wujie on 20190103 end**/
//        // 四级科目尾号名称 = D.产品简称
//        valBokDetailId.setDetailSubnameTail(dto.getProdAbbr());
//        /**modify by dongjun 20181012 end**/
//        valBokDetailId.setBokDetailId(interfCommonService.genMd5BokDetailId(valBokDetailId));//MD5加密
//        
//        return valBokDetailId;
//    }

}
