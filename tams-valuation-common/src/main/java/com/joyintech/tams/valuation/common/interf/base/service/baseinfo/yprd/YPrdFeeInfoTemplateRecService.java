/*
 * 文件名：YPrdFeeInfoTemplateRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：dongjun
 * 修改时间：2018年10月9日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.yprd;

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
import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.dto.YPrdBaseInfoValCommonResult;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdFeeBaseinfo;

/**
 * 预期收益型产品费用基础信息处理
 * 
 * @author dongjun
 * @version 1.0 2018年10月9日
 * @see YPrdFeeInfoTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class YPrdFeeInfoTemplateRecService {

    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public YPrdBaseInfoValCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
            TemplateOrderInfoReaderResult acctEntity) {
        // 定义返回变量
        YPrdBaseInfoValCommonResult result = new YPrdBaseInfoValCommonResult();
        List<ValPrdFeeBaseinfo> listValPrdFeeInfoY = new ArrayList<ValPrdFeeBaseinfo>();
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);
        
        // 2、List数据接收转换
        listValPrdFeeInfoY = returnResult.getT(ValPrdFeeBaseinfo.class);

        // 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        Map<String, Object> map = handleValPrdFeeInfoC(listValPrdFeeInfoY);
        // 现金流信息List
        List<FinCashInfo> listValCashInfo = (List<FinCashInfo>) map.get("ValCashInfo");
        result.setListValCashInfo(listValCashInfo);

        // 现金流计算规则List
        List<FinCashCalcRule> listValCashCalcRule = (List<FinCashCalcRule>) map.get("ValCashCalcRule");
        result.setListValCashCalcRule(listValCashCalcRule);

        // 现金流支付规则List
        List<FinCashPayRule> listValCashPayRule = (List<FinCashPayRule>) map.get("ValCashPayRule");
        result.setListValCashPayRule(listValCashPayRule);

        /**modify by wujie on 20181109 remark:费用的账务明细代码采用产品的账务明细代码，在初始化产品基本信息时就已经初始化了
        // 账务明细代码List
        List<BokDetailId> listValBokDetailId = (List<BokDetailId>) map.get("ValBokDetailId");
        result.setListValBokDetailId(listValBokDetailId);
        **/
        // 添加日志
        result.setValInterflog(returnResult.getValInterflog());
        // 返回处理结果数据
        return result;
    }

    /**
     * 04-费用基础信息
     * 
     * @param listValPrdFeeInfoN
     *            货币型产品费用基础信息
     * @return 现金流信息List + 现金流计算规则List + 现金流支付规则List+账务明细代码List
     * @see
     * @since 1.0
     */
    private Map<String, Object> handleValPrdFeeInfoC(List<ValPrdFeeBaseinfo> listValPrdFeeInfoY) {
        Map<String, Object> resultMap = new HashMap<>();
        // 现金流信息List
        List<FinCashInfo> listValCashInfo = new ArrayList<FinCashInfo>();
        // 现金流计算规则List
        List<FinCashCalcRule> listValCashCalcRule = new ArrayList<FinCashCalcRule>();
        // 现金流支付规则List
        List<FinCashPayRule> listValCashPayRule = new ArrayList<FinCashPayRule>();
        /**modify by wujie on 20181109 remark:费用的账务明细代码采用产品的账务明细代码，在初始化产品基本信息时就已经初始化了
        // 账务明细表List
        List<BokDetailId> listValBokDetailId = new ArrayList<BokDetailId>();
        **/
        for (ValPrdFeeBaseinfo dto : listValPrdFeeInfoY) {
            // 现金流信息表
            FinCashInfo valCashInfo = this.getValCashInfo(dto);
            listValCashInfo.add(valCashInfo);
            // 现金流计算规则表
            FinCashCalcRule valCashCalcRule = this.getValCashCalcRule(dto,valCashInfo);
            listValCashCalcRule.add(valCashCalcRule);
            // 现金流支付规则表
            FinCashPayRule valCashPayRule = this.getValCashPayRule(dto,valCashInfo);
            listValCashPayRule.add(valCashPayRule);
            /** modify by wujie on 20181109 remark:费用的账务明细代码采用产品的账务明细代码，在初始化产品基本信息时就已经初始化了
            // 生成账务明细表
            BokDetailId valBokDetailId = this.getBokDetailId(dto, valCashInfo);
            listValBokDetailId.add(valBokDetailId);
            **/
        }
        resultMap.put("ValCashInfo", listValCashInfo);
        resultMap.put("ValCashCalcRule", listValCashCalcRule);
        resultMap.put("ValCashPayRule", listValCashPayRule);
        /**modify by wujie on 20181109 remark:费用的账务明细代码采用产品的账务明细代码，在初始化产品基本信息时就已经初始化了
        resultMap.put("ValBokDetailId", listValBokDetailId);
        **/
        return resultMap;
    }

//    /**
//     * 财务明细 接收 <一句话功能简述>
//     * 
//     * @param dto
//     *            04-费用基础信息
//     * @return 财务明细
//     * @see
//     * @since 1.0
//     */
//    private BokDetailId getBokDetailId(ValPrdFeeBaseinfo dto, FinCashInfo T4) {
//        BokDetailId valBokDetailId = new BokDetailId();
//        // 账套代码 = T4.金融产品代码 根据产品代码查询账套代码
//        // 调用共通查询账套（根据产品代码）
//        BokBookset valBokBookset = valInterfCommonService.getBooksetByProdId(T4.getFinprodId());
//        if (valBokBookset != null) {
//            valBokDetailId.setBooksetId(valBokBookset.getBooksetId());
//        } else {
//            valBokDetailId.setBooksetId(T4.getFinprodId());
//        }
//        /**modify by dongjun 20181012 start**/
//        // 业务明细代码 = T4.金融产品代码
//        valBokDetailId.setBusiId(T4.getFinprodId());//modify by dongjun on 20181029
//        // 业务明细类型 = T4.理财产品
//        valBokDetailId.setBusiDetailType(T4.getFinprodType());//modify by dongjun on 20181029
//        // 投资目的 = 默认为空
//        valBokDetailId.setInvAim(null);
//        // 证券管理户代码 = 默认为空
//        valBokDetailId.setSecManageAcctId(null);
//        // 交易编号 = 默认为空
//        valBokDetailId.setTradeId(null);
//        // 四级科目尾号 = E.产品代码
//        /** modify by dongjun on 20181029 start**/
//        String detailSubjectNoTail = null;
//        String detailSubnameTail = null;
//        Long branch = null;
//        if(StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_理财子产品.getValue())){
//        	branch = Long.valueOf(new SimpleDateFormat("yyyyMMdd").format(dto.getProdVdate()));
//        	detailSubjectNoTail = T4.getFinprodId() + branch;
//            detailSubnameTail = dto.getProdAbbr()+branch;
//        } else {
//            detailSubjectNoTail = T4.getFinprodId();
//            detailSubnameTail = dto.getProdAbbr();
//        }
//        valBokDetailId.setDetailSubjectNoTail(detailSubjectNoTail);
//        /** modify by dongjun on 20181029 start**/
//        // 四级科目尾号名称 = E.产品简称
//        valBokDetailId.setDetailSubnameTail(detailSubnameTail);
//        /**modify by dongjun 20181012 end**/
//        //分支序号
//        valBokDetailId.setBranch(branch);
//        //账务明细代码
//        valBokDetailId.setBokDetailId(InterfHelper.genMd5BokDetailId(valBokDetailId));// MD5加密
//
//        return valBokDetailId;
//    }

    /**
     * 现金流支付规则 接收 
     * 
     * @param dto
     *            04-费用基础信息
     * @return 现金流支付规则
     * @see
     * @since 1.0
     */
    private FinCashPayRule getValCashPayRule(ValPrdFeeBaseinfo dto,FinCashInfo T4) {
        FinCashPayRule valCashPayRule = new FinCashPayRule();
        // 现金流代码 = T4.现金流代码
        valCashPayRule.setCashId(T4.getCashId());
        // 生效日期 = D.生效日
        valCashPayRule.setEffDate(dto.getVdate());
        // 支付频率 = D.付费规则
        valCashPayRule.setPayCycle(dto.getPayRule());
        // 营业日准则 = D.营业准则
//        valCashPayRule.setTdayRule(dto.getTdayRule());
        // 现金流单位 = 02：总额
        valCashPayRule.setCashUnitType(DictCodeList.现金流单位_总额.getValue());
        // 金融产品代码 = T4.金融产品代码
        valCashPayRule.setFinprodId(T4.getFinprodId());
        // 金融产品类型 = T4.金融产品类型
        valCashPayRule.setFinprodType(T4.getFinprodType());//modify by dongjun 20181029
        // 支付方式 = 02:现金
        valCashPayRule.setPayType(DictCodeList.支付方式_现金.getValue());
        // 分支序号 = T4.分支序号
        valCashPayRule.setBranch(T4.getBranch());
        return valCashPayRule;
    }

    /**
     * 现金流计算规则 接收 
     * 
     * @param dto
     *            04-费用基础信息
     * @return 现金流计算规则
     * @see
     * @since 1.0
     */
    private FinCashCalcRule getValCashCalcRule(ValPrdFeeBaseinfo dto,FinCashInfo T4) {
        FinCashCalcRule valCashCalcRule = new FinCashCalcRule();
        // 现金流代码 = T4.现金流代码
        valCashCalcRule.setCashId(T4.getCashId());
        // 生效日期 = D.生效日
        valCashCalcRule.setEffDate(dto.getVdate());
        // 计算类型 = 01：固定利率
        valCashCalcRule.setCalcType(DictCodeListExt1.计算类型_固定利率.getValue());
        // 生效利率 = D.初始费率
        valCashCalcRule.setYield(dto.getInitRate());
        // 计算基础 = D.计费基础
        valCashCalcRule.setBasis(dto.getChargeBase());
        // 基数类型 = D.计费基数
        valCashCalcRule.setBaseType(dto.getChargeBasic());
        // 基数日期类型 = D.计费基数日期类型
        valCashCalcRule.setBaseDateType(dto.getBasisDate());
        // 金融产品代码 = T4.金融产品代码
        valCashCalcRule.setFinprodId(T4.getFinprodId());
        // 金融产品类型 = T4.金融产品类型
        valCashCalcRule.setFinprodType(T4.getFinprodType());//modify by dongjun 20181029
        // 分支序号 = T4.分支序号
        valCashCalcRule.setBranch(T4.getBranch());
        return valCashCalcRule;
    }

    /**
     * 现金流信息 接收
     * 
     * @param dto
     *            04-费用基础信息
     * @return 现金流信息
     * @see
     * @since 1.0
     */
    private FinCashInfo getValCashInfo(ValPrdFeeBaseinfo dto) {
        FinCashInfo valCashInfo = new FinCashInfo();
        // 现金流代码 = MD5加密(D.产品代码+D.费用ID)
        /** modify  by dongjun on 20181029 start**/
        String cashId = null;
        if(StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_理财子产品.getValue())){
            cashId = InterfHelper.getMd5CashId_(dto.getProdId() + new SimpleDateFormat("yyyyMMdd").format(dto.getProdVdate()),
                dto.getFeeId());
        } else {
            cashId = InterfHelper.getMd5CashId_(dto.getProdId() , dto.getFeeId());
        }     
        valCashInfo.setCashId(cashId);
        /** modify  by dongjun on 20181029 end**/ 
        // 现金流一级类型 = 03:费用
        valCashInfo.setCashType1(DictCodeListExt1.现金流一级类型_费用.getValue());
        // 现金流二级类型 = D.费用类型
        valCashInfo.setCashType2(dto.getFeeType());
        // 金融产品代码 = D.产品代码
        valCashInfo.setFinprodId(dto.getProdId());
        // 金融产品类型 = D.理财产品
        valCashInfo.setFinprodType(dto.getFinprodType());//modify by dongjun 20181023
        // 分支序号 = 设置全局变量(默认为0）
        /** modify  by dongjun on 20181029 start**/
        Long branch = null;
        if(StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_理财子产品.getValue())){
            branch = Long.valueOf(new SimpleDateFormat("yyyyMMdd").format(dto.getProdVdate()));
        } else {
            branch = InterfCommonConstant.分支序号_默认;
        }
        valCashInfo.setBranch(branch);
        /** modify  by dongjun on 20181029 start**/
        return valCashInfo;
    }
}
