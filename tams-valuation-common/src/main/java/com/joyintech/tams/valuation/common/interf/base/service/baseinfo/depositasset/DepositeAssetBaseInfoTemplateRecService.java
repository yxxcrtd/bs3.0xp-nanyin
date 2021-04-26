/*
 * 文件名：DepositeAssetBaseInfoTemplateRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年6月27日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.depositasset;

import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.ValInterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.DepositeAssetBaseInfoValCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValBotAstInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 存款类资产-基础信息模板处理Service(VAL_BOT_AST_INFO)
 * 
 * @author weipeng
 * @version 1.0 2018年6月27日
 * @see DepositeAssetBaseInfoTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class DepositeAssetBaseInfoTemplateRecService {

    /**
     * XMl文件解析Service
     */
    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;

    @Autowired
    private InterfCommonService interfCommonService;

    /**
     * 存款类资产-基础信息 接口模板处理
     * 
     * @param templateSourceFlgDTO 文件来源和类型
     * @param acctEntity 计提基础信息DTO
     * @return 净值型产品交易 接口模板处理返回共通结果
     * @see
     * @since 1.0
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public DepositeAssetBaseInfoValCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                                        TemplateOrderInfoReaderResult acctEntity) {
        // 定义返回变量
        DepositeAssetBaseInfoValCommonResult result = new DepositeAssetBaseInfoValCommonResult();
        List<ValBotAstInfo> listValBotAstInfo = new ArrayList<ValBotAstInfo>();
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);

        // 2、进行数据有效性的check,并生出进入日志表的数据结构
        listValBotAstInfo = returnResult.getT(ValBotAstInfo.class);

        // 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        Map<String, Object> map = handleValBotAstInfo(listValBotAstInfo);
        // 标的类金融产品List
        List<FinProduct> listValFinProduct = (List<FinProduct>)map.get("ValFinProduct");
        result.setListValFinProduct(listValFinProduct);
        // 定义 金融产品分类List
        List<FinProductType> listValFinProductType = (List<FinProductType>)map.get(
            "ValFinProductType");
        result.setListValFinProductType(listValFinProductType);
        // 现金流计算规则List
        List<FinCashCalcRule> listValCashCalcRule = (List<FinCashCalcRule>)map.get(
            "ValCashCalcRule");
        result.setListValCashCalcRule(listValCashCalcRule);
        //modify by yanya on 20210318 增加标的类金融产品扩展表
        result.setListValFinProductExt((List<FinProductAdd>)map.get("listValFinProductAdd"));

        result.setListValCashInfo((List<FinCashInfo>)map.get("ValCashInfo"));
        //modify by sunshuaishuai on 20210320 现金流利率信息list
        result.setListValCashIntInfo((List<FinRateInfo>)map.get("ValFinCashIntInfo"));
        // 添加日志
        result.setValInterflog(returnResult.getValInterflog());
        // 返回处理结果数据
        return result;
    }

    private Map<String, Object> handleValBotAstInfo(List<ValBotAstInfo> listValBotAstInfo) {
        Map<String, Object> result = new HashMap<>();
        // 定义 交易类金融产品List
        // List<FinTradeProduct> listValTradeFinProduct = new ArrayList<FinTradeProduct>();
        // 定义 标的类金融产品List
        List<FinProduct> listValFinProduct = new ArrayList<FinProduct>();
        // 定义 金融产品分类List
        List<FinProductType> listValFinProductType = new ArrayList<FinProductType>();
        // 定义 现金流信息List
         List<FinCashInfo> listValCashInfo = new ArrayList<FinCashInfo>();
        // 定义 现金流计算规则List
        List<FinCashCalcRule> listValCashCalcRule = new ArrayList<FinCashCalcRule>();
        // 定义 现金流支付规则List
         List<FinCashPayRule> listValCashPayRule = new ArrayList<FinCashPayRule>();
        //modify by sunshuaishuai on 20210319 增加现金流利率信息list
        List<FinRateInfo> listFinCashIntInfo = new ArrayList<FinRateInfo>();
        // //浮动利率初始化List
        // List<FloatingInterestRateCommonResult> listFloatingInterestRateCommonResult = new
        // ArrayList<FloatingInterestRateCommonResult>();
        //
        //modify by yanya on 20210318 增加标的类金融产品扩展表
        List<FinProductAdd> listValFinProductAdd = new ArrayList<FinProductAdd>();

        for (ValBotAstInfo dto : listValBotAstInfo) {
            // String benchmarkNo = null;
            // if(dto.getRateType().equals(DictCodeList.利率类型_浮动利率.getValue())){
            // //浮动利率初始化
            // FloatingInterestRateCommonResult floatingResult =getFloatingResult(dto);
            // listFloatingInterestRateCommonResult.add(floatingResult);
            // benchmarkNo = floatingResult.getBenchmarkInfoModel().getBenchmarkNo();
            // }

            // // 交易类金融产品
            // FinTradeProduct valTradeFinProduct = getValTradeFinProduct(dto,benchmarkNo);
            // listValTradeFinProduct.add(valTradeFinProduct);
            // 标的类金融产品
            FinProduct valFinProduct = getValFinProduct(dto);
            listValFinProduct.add(valFinProduct);
            // 金融产品分类
            FinProductType valFinProductType = getValFinProductType(dto);
            listValFinProductType.add(valFinProductType);
            // 现金流信息
             FinCashInfo valCashInfo = getValCashInfo(dto);
             listValCashInfo.add(valCashInfo);
            // 现金流计算规则
            /*
             * FinCashCalcRule valCashCalcRule = getValCashCalcRule(dto, valTradeFinProduct,
             * valCashInfo); listValCashCalcRule.add(valCashCalcRule);
             */
            // 现金流计算规则
            /** add by wangqidong start **/
            FinCashCalcRule valCashCalcRule = getValCashCalcRule(dto);
            listValCashCalcRule.add(valCashCalcRule);
            /** add by wangqidong end **/
            /*
             * // 现金流支付规则 FinCashPayRule valCashPayRule = getValCashPayRule(dto, valCashInfo);
             * listValCashPayRule.add(valCashPayRule);
             */
            //modify by yanya on 20210318 增加标的类金融产品扩展表
            listValFinProductAdd.add(this.getValFinProductAdd(dto));
            //modify by sunshuaishuai on 20210319 增加现金流利率信息list
            listFinCashIntInfo.add(this.getFinCashIntInfo(dto, valCashCalcRule));
        }
        // result.put("ValTradeFinProduct", listValTradeFinProduct);
        result.put("ValFinProduct", listValFinProduct);
        result.put("ValFinProductType", listValFinProductType);
         result.put("ValCashInfo", listValCashInfo);
        result.put("ValCashCalcRule", listValCashCalcRule);
        // result.put("ValCashPayRule", listValCashPayRule);
        // result.put("listFloatingInterestRateCommonResult",
        // listFloatingInterestRateCommonResult);
        //modify by yanya on 20210318 增加标的类金融产品扩展表
        result.put("listValFinProductAdd", listValFinProductAdd);
        //modify by sunshuaishuai on 20210319 增加现金流利率信息list
        result.put("ValFinCashIntInfo", listFinCashIntInfo);
        return result;
    }

    // /**
    // * <一句话功能简述>
    // * <功能详细描述>
    // * @param dto
    // * @return
    // * @see
    // * @since 1.0
    // */
    // private FloatingInterestRateCommonResult getFloatingResult(ValBotAstInfo dto) {
    // FloatingInterestRateDto floatingInterestRateDto=new FloatingInterestRateDto();
    // floatingInterestRateDto.setInitRate(dto.getInitRate().isEmpty()? null : new
    // BigDecimal(dto.getInitRate()));//初始利率
    // floatingInterestRateDto.setLinkedInterestRate(dto.getLinkRate());//挂钩利率
    // floatingInterestRateDto.setCoefficient(dto.getRatio());//系数
    // floatingInterestRateDto.setSpreadMax(dto.getRateMax());//利率上限
    // floatingInterestRateDto.setSpreadMin(dto.getRateMin());//利率下限
    // floatingInterestRateDto.setFloatingInterestRateNo(dto.getIntRateId()); //浮动利率编号
    // floatingInterestRateDto.setSpreadRate(dto.getSpreadRate());//利差
    // FloatingInterestRateCommonResult floatingResult =
    // interfCommonService.floatingInterestRateInitialization(floatingInterestRateDto);
    // return floatingResult;
    // }

    /**
     * T1 标的类金融产品
     * 
     * @param dto 基础信息
     * @return
     * @see
     * @since 1.0
     */
    private FinProduct getValFinProduct(ValBotAstInfo dto) {
        // 定义标的类金融产品
        FinProduct valFinProduct = new FinProduct();
        // 金融产品代码=A.资产代码
        valFinProduct.setFinprodId(dto.getAssetId());
        // 金融产品类型=A.存款类型
        valFinProduct.setFinprodType(dto.getPrinType());
        // 金融产品简称=A.资产名称
        valFinProduct.setFinprodAbbr(dto.getAssetName());
        // 金融产品全称=A.资产名称
        valFinProduct.setFinprodName(dto.getAssetName());
        // 收益类型=数据字典：收益类型.预期收益
        valFinProduct.setProfitType((DictCodeList.收益类型_收益型.getValue()));
        // 市场代码=A.资产代码
        valFinProduct.setFinprodMarketId(dto.getAssetId());
        // 发行规模=A.本金金额
        valFinProduct.setIssueAmt(dto.getCapital());
        // 币种=A.币种
        valFinProduct.setCcy(dto.getCcy());
        // 境内外=A.境内外
        valFinProduct.setBlnArea(dto.getInOut());
        // 交易场所=A.交易场所
        valFinProduct.setTradeMarket(dto.getTradeMarket());
        
        /**add by wujie on 20190115 start**/
        // 交易日历 = 共通
        valFinProduct.setCalendarId(interfCommonService.getCalendarId(dto.getTradeMarket()));
        /**add by wujie on 20190115 end**/
        
        // 发行人=A.存款机构
        valFinProduct.setIssuer(dto.getPrinAgent());
        // 发行日=A.起息日
        valFinProduct.setIdate(dto.getVdate());
        // 起息日=A.起息日
        valFinProduct.setVdate(dto.getVdate());
        // 到期日=A.到期日
        valFinProduct.setMdate(dto.getMdate());
        // 实际到期日=A.到期日
        valFinProduct.setActmdate(dto.getMdate());
        // 母金融产品代码=A.资产代码
        valFinProduct.setpFinprodId(dto.getAssetId());
        // 原金融产品代码=A.资产代码
        valFinProduct.setoFinprodId(dto.getAssetId());
        // 原金融产品类型=A.存款类型
//        valFinProduct.setoFinprodType(dto.getPrinType());
        // 本金收益特征=A.本金收益特征
        valFinProduct.setCapiIncomeFeature(dto.getCapiProfFeature());
        // 所属机构 = A.所属机构   add by tanxuan on 20210310 多级法人权限控制改造
		valFinProduct.setBelongInstitutionCode(dto.getBelongInstitutionCode());
        return valFinProduct;

    }

    /**
     * 构建标的类金融产品扩展表
     * @param dto
     * @return
     */
    private FinProductAdd getValFinProductAdd(ValBotAstInfo dto) {
        FinProductAdd result = new FinProductAdd();
        // 金融产品代码 = A.资产代码
        result.setFinprodId(dto.getAssetId());
        //数据来源 = 常量.估值数据来源
        result.setDataSource(ValInterfCommonConstant.getValDataSource());
        return result;
    }

    /**
     * T1 交易类金融产品
     * 
     * @param dto 01-基础信息
     * @param floatingResult
     * @return 交易类金融产品
     * @see
     * @since 1.0
     */
    /*
     * private FinTradeProduct getValTradeFinProduct(ValBotAstInfo dto, String benchmarkNo) {
     * FinTradeProduct valTradeFinProduct = new FinTradeProduct(); // 金融产品代码 = A.资产代码
     * valTradeFinProduct.setFinprodId(dto.getAssetId()); // 金融产品类型 = A.存款类型
     * valTradeFinProduct.setFinprodType(dto.getPrinType()); // 分支序号 = 设置全局变量(默认为0）
     * valTradeFinProduct.setBranch(InterfCommonConstant.分支序号_默认); // 分支类型 = 设置全局变量(默认为空)
     * valTradeFinProduct.setBranchType(null); // 名义本金 = A.本金金额
     * valTradeFinProduct.setPrin(dto.getCapital()); // 本金币种 = A.币种
     * valTradeFinProduct.setCcy(dto.getCcy()); // 起息日 = A.起息日
     * valTradeFinProduct.setVdate(dto.getVdate()); // 到期日 = A.到期日
     * valTradeFinProduct.setMdate(dto.getMdate()); // 期限(天) = A.到期日-A.起息日
     * valTradeFinProduct.setTerm(DateUtil.getBetweenDayNumber(dto.getMdate(), dto.getVdate())); //
     * 利率类型 = A.利率类型 valTradeFinProduct.setIntType(dto.getRateType()); // 固定利率 = A.初始利率
     *//**
       * modify by wujie on 20180929 start valTradeFinProduct.setIntRate(new
       * BigDecimal(dto.getInitRate()));
       **/
    /*
     * valTradeFinProduct.setIntRate(dto.getInitRate());
     *//** modify by wujie on 20180929 end **/
    /*
     * // 利率编号 = 调用挂钩浮动利率生成方法 valTradeFinProduct.setIntRateId(benchmarkNo); // 计息基础 = A.计息基础
     * valTradeFinProduct.setBasis(dto.getBasis()); // 原金融产品代码 = A.资产代码
     * valTradeFinProduct.setoFinprodId(dto.getAssetId()); // 原金融产品类型 = A.存款类型
     * valTradeFinProduct.setoFinprodType(dto.getPrinType());
     *//** add by wujie on 20180823 start **/
    /*
     * //本金收益特征 = A.本金收益特征 valTradeFinProduct.setCapiIncomeFeature(dto.getCapiProfFeature());
     *//** add by wujie on 20180823 end **//*
                                           * return valTradeFinProduct; }
                                           */

    /**
     * T2 金融产品分类
     * 
     * @param dto 01-基础信息
     * @return 金融产品分类
     * @see
     * @since 1.0
     */
    private FinProductType getValFinProductType(ValBotAstInfo dto) {
        FinProductType valFinProductType = new FinProductType();
        // 金融产品代码 = A.资产代码
        valFinProductType.setFinprodId(dto.getAssetId());
        // 金融产品类型 = A.存款类型
//        valFinProductType.setFinprodType(dto.getPrinType());
        // 分类1 = A.资产类型
        valFinProductType.setType1(dto.getAssetClass());
        // // 分类2 = A.核算分类
        // valFinProductType.setType2(dto.getValClas());

        /** add by wujie on 20180822 start **/
        // 分类2 = 调用共通方法查询核算分类
        FinProductClass fdto = new FinProductClass();
        // 金融产品类型
        fdto.setFinprodType(dto.getPrinType());
        // 资产类型
        fdto.setAssetType(dto.getAssetClass());
        // 机构核算分类
        fdto.setOrgClass(dto.getValClas());

        valFinProductType.setType2(interfCommonService.getFinanceProductClass(fdto));

        // 分类3 = 调用共通方法查询税务分类
        valFinProductType.setType3(interfCommonService.getFinanceProductTaxClass(fdto));
        /** add by wujie on 20180822 end **/
        valFinProductType.setBranch(InterfCommonConstant.分支序号_默认);
        return valFinProductType;
    }

    /**
     * T3 现金流信息
     * 
     * @param dto 01-基础信息
     * @return 现金流信息
     * @see
     * @since 1.0
     */

      private FinCashInfo getValCashInfo(ValBotAstInfo dto) {
          FinCashInfo valCashInfo = new FinCashInfo();
          // 现金流代码 = A.资产代码+A.存款类型+现金流二级类型(C02 利息)
          valCashInfo.setCashId(InterfHelper.getMd5CashId_(dto.getAssetId(),dto.getPrinType(),
          DictCodeListExt1.现金流二级类型_利息.getValue()));
          // 现金流一级类型 = 02 利息
          valCashInfo.setCashType1(DictCodeListExt1.现金流一级类型_利息.getValue());
          // 现金流二级类型 = C02 利息
          valCashInfo.setCashType2(DictCodeListExt1.现金流二级类型_利息.getValue());
          // 金融产品代码 = A.资产代码
          valCashInfo.setFinprodId(dto.getAssetId());
          // 金融产品类型 = A.存款类型
          valCashInfo.setFinprodType(dto.getPrinType());
          // 分支序号 = 设置全局变量(默认为0）
          valCashInfo.setBranch(InterfCommonConstant.分支序号_默认);
          //币种
          valCashInfo.setCcy(dto.getCcy());

          return valCashInfo;
      }


    /**
     * 现金流计算规则
     * 
     * @param dto 01-基础信息
     * @return 现金流计算规则
     * @see
     * @since 1.0
     */
    private FinCashCalcRule getValCashCalcRule(ValBotAstInfo dto) {
        FinCashCalcRule valCashCalcRule = new FinCashCalcRule();

        // 现金流代码 = InterfHelper.getMd5CashId_（A.资产代码,数据字典：金融产品类型.存款,数据字典：现金流二级类型.利息
        valCashCalcRule.setCashId(InterfHelper.getMd5CashId_(dto.getAssetId(),
            DictCodeListExt1.金融产品类型_存款.getValue(), DictCodeListExt1.现金流二级类型_利息.getValue()));
        // 生效日期 = A.起息日
        valCashCalcRule.setEffDate(dto.getVdate());
        // 计算类型 = A.利率类型
        valCashCalcRule.setCalcType(dto.getRateType());
        // 浮动利率编号 = T1.利率编号
        // valCashCalcRule.setIntRateId(T1.getIntRateId());
        // 生效利率 = A.初始利率
        /**
         * modify by wujie on 20180929 start valCashCalcRule.setYield(new
         * BigDecimal(dto.getInitRate()));
         **/
        valCashCalcRule.setYield(dto.getInitRate());
        /** modify by wujie on 20180929 end **/
        // 计算基础 = A.计息基础
        valCashCalcRule.setBasis(dto.getBasis());
        // 金融产品代码 = A.资产代码
        valCashCalcRule.setFinprodId(dto.getAssetId());
        // 金融产品类型 = A.存款类型
        valCashCalcRule.setFinprodType(dto.getPrinType());
        // 分支序号 = 设置全局变量(默认为0）
        valCashCalcRule.setBranch(InterfCommonConstant.分支序号_默认);
        /** add by wujie 20180823 start **/
        // 基数类型 = 数据字典：基数类型.实收资本
        valCashCalcRule.setBaseType(DictCodeList.基数类型_实收资本.getValue());
        // 基数日期类型 = 数据字典：基数日期类型.当日
        valCashCalcRule.setBaseDateType(DictCodeList.基数日期类型_当日.getValue());
        /** add by wujie 20180823 end **/
        return valCashCalcRule;
    }

    /**
     * 现金流信息表构建
     * @param dto 接口数据dto
     * @param calcDto 已经构建的现金流计算规则
     * @return
     */
    private FinRateInfo getFinCashIntInfo(ValBotAstInfo dto, FinCashCalcRule calcDto) {
        //现金流利率信息
        FinRateInfo valFinCashIntInfo = new FinRateInfo();
        //现金流代码=MD5(A.资金账户账号+数据字典：金融产品类型.资金账户+数据字典：现金流二级类型.利息)
        valFinCashIntInfo.setCashId(calcDto.getCashId());
        //生效日=A.生效日
        valFinCashIntInfo.setEffDate(calcDto.getEffDate());
        //利率类型=A.利率类型
        valFinCashIntInfo.setIntType(calcDto.getCalcType());
        //初始利率=A.初始利率
        valFinCashIntInfo.setRate(calcDto.getYield());
        //计算基础=A.计息基础
        valFinCashIntInfo.setBasis(dto.getBasis());
        //系数=A.系数
        valFinCashIntInfo.setCoefficient(dto.getRatio());
        //利差=A.利差
        valFinCashIntInfo.setSpreadRate(dto.getSpreadRate());
        //利率上限=A.利率上限
        valFinCashIntInfo.setHighestRate(dto.getRateMax());
        //利率下限=A.利率下限
        valFinCashIntInfo.setLowestRate(dto.getRateMin());
        //基准编号=A.基准利率编号
        valFinCashIntInfo.setBenchmarkId(dto.getIntRateId());
        //金融产品代码=A.资金账户账号
        valFinCashIntInfo.setFinprodId(calcDto.getFinprodId());
        //金融产品类型=数据字典：金融产品类型.资金账户
        valFinCashIntInfo.setFinprodType(calcDto.getFinprodType());
        valFinCashIntInfo.setFinprodType2(calcDto.getFinprodType2());
        //基准类型
        valFinCashIntInfo.setBenchmarkType(calcDto.getBaseType());
        //分支序号 = 设置全局变量(默认为0）
        valFinCashIntInfo.setBranch(InterfCommonConstant.分支序号_默认);
        return valFinCashIntInfo;
    }

    /**
     * 现金流支付规则
     * 
     * @param dto 01-基础信息
     * @return 现金流支付规则
     * @see
     * @since 1.0
     */
    /*
     * private FinCashPayRule getValCashPayRule(ValBotAstInfo dto, FinCashInfo T3) { FinCashPayRule
     * valCashPayRule = new FinCashPayRule(); // 现金流代码 = T3.现金流代码
     * valCashPayRule.setCashId(T3.getCashId()); // 生效日期 = A.起息日
     * valCashPayRule.setEffDate(dto.getVdate()); // 支付频率 = A.付息频率
     * valCashPayRule.setPayCycle(dto.getPayCycle()); // 营业日准则 = A.营业日准则
     * valCashPayRule.setTdayRule(dto.getTdayRule()); // 推算方式 = A.推算方式
     * valCashPayRule.setCalcRule(dto.getCalcRule()); // 首次支付日 = A.首次付息日
     * valCashPayRule.setFirstPayDate(dto.getFirstPayDate()); // 现金流单位 = 02：总额
     * valCashPayRule.setCashUnitType(DictCodeList.现金流单位_总额.getValue()); // 金融产品代码 = A.资产代码
     * valCashPayRule.setFinprodId(dto.getAssetId()); // 金融产品类型 = A.存款类型
     * valCashPayRule.setFinprodType(dto.getPrinType()); // 支付方式 = 02:现金
     * valCashPayRule.setPayType(DictCodeList.支付方式_现金.getValue()); // 分支序号 = 设置全局变量(默认为0）
     * valCashPayRule.setBranch(InterfCommonConstant.分支序号_默认); return valCashPayRule; }
     */

}
