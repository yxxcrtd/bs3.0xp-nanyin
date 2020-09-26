/*
 * 文件名：PlannedAssetBaseInfoTemplateRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年6月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.mngasset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductClass;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.PlannedAssetBaseInfoRecCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValMngAssetInfo;

/**
 * 计划类资产（利率型）基础信息模板处理
 * 〈功能详细描述〉
 * @author ruanhongming
 * @version 1.0 2018年6月25日
 * @see PlannedAssetBaseInfoTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class PlannedAssetBaseInfoTemplateRecService {
    
    /**
     * 基本信息模板处理service
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;
    
    @Autowired
    private InterfCommonService interfCommonService;
    
    @SuppressWarnings({"unchecked", "rawtypes"})
    public PlannedAssetBaseInfoRecCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                                           TemplateOrderInfoReaderResult acctEntity) {
        //定义返回变量
        PlannedAssetBaseInfoRecCommonResult result = new PlannedAssetBaseInfoRecCommonResult();
        List<ValMngAssetInfo> listValMngAssetInfo = new ArrayList<ValMngAssetInfo>();
        //解析模板后的list以及日志list
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        // 1、文件读取解析
        returnResult= templateAnalysisForXMLService.execute(acctEntity);
                
        //2、进行数据有效性的check,并生出进入日志表的数据结构
        listValMngAssetInfo = returnResult.getT(ValMngAssetInfo.class);
        
        //3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        Map<String, Object> resultMap = handleListValMngAssetInfo(listValMngAssetInfo);
        // 判断返回结果不为空
        if (resultMap != null) {
            List<FinProduct> listValFinProduct = (List<FinProduct>)resultMap.get("ValFinProduct");
            List<FinCashInfo> listValCashInfo = (List<FinCashInfo>)resultMap.get("ValCashInfo");
            List<FinCashCalcRule> listValCashCalcRule = (List<FinCashCalcRule>)resultMap.get("ValCashCalcRule");

            result.setListValFinProduct(listValFinProduct);
            result.setListValCashInfo(listValCashInfo);
            result.setListValCashCalcRule(listValCashCalcRule);
            
//            result.setListBenchmarkInfo((List<MstBenchmarkInfo>)resultMap.get("listBenchmarkInfo"));
//            result.setListBenchmarkCompo((List<MstBenchmarkCompo>)resultMap.get("listBenchmarkCompo"));        
        
            /**add by wujie on 20180822 start**/
            List<FinProductType> ValFinProductType = (List<FinProductType>)resultMap.get("ValFinProductType"); 
            result.setListValFinProductType(ValFinProductType); 
            /**add by wujie on 20180822 end**/
        }
        // 添加日志
        result.setValInterflog(returnResult.getValInterflog());
        return result;
    }
    
    /**
     * 01-基础信息
     * 
     * @param handleListValMngAssetInfo 计划类资产（利率型）基础信息表
     * @return 标的类金融产品 + 现金流信息+现金流计算 规则
     * @see
     * @since 1.0
     */
    private Map<String, Object> handleListValMngAssetInfo(List<ValMngAssetInfo> listValMngAssetInfo) {
        Map<String, Object> resultMap = new HashMap<>();
        List<FinProduct> valFinProductList = new ArrayList<FinProduct>();
        /**add by wujie on 20180822 start**/
        List<FinProductType> valFinProductTypeList = new ArrayList<FinProductType>();
        /**add by wujie on 20180822 end**/
        List<FinCashInfo> valCashInfoList = new ArrayList<FinCashInfo>();
        List<FinCashCalcRule> valCashCalcRuleList = new ArrayList<FinCashCalcRule>();
//        List<MstBenchmarkInfo> listBenchmarkInfo = new ArrayList<MstBenchmarkInfo>();
//        List<MstBenchmarkCompo> listBenchmarkCompo = new ArrayList<MstBenchmarkCompo>();
        // 获取基础信息
        for (ValMngAssetInfo dto : listValMngAssetInfo) {
        	String benchmarkNo = null;
            // 标的类金融产品 处理
            FinProduct valFinProduct = getValFinProduct(dto);
            valFinProductList.add(valFinProduct);

            /**add by wujie on 20180822 start**/
            // 金融产品分类 
            FinProductType valFinProductType = getValFinProductType(dto);
            valFinProductTypeList.add(valFinProductType);
            /**add by wujie on 20180822 end**/
            
//            //调用共通设计->【详细设计-接口共通方法设计.xlsx】->浮动利率初始化SHEET 
//            if(DictCodeList.利率类型_浮动利率.getValue().equals(dto.getRateType())){
//                FloatingInterestRateCommonResult floatingInterestRateCommonResult = this.getFloatingInterestRateCommonResult(dto);
//            	listBenchmarkInfo.add(floatingInterestRateCommonResult.getBenchmarkInfoModel());
//            	listBenchmarkCompo.add(floatingInterestRateCommonResult.getBenchmarkCompoModel());
//            	benchmarkNo = floatingInterestRateCommonResult.getBenchmarkInfoModel().getBenchmarkNo();
//            }
            // 现金流计算规则信息  处理
            FinCashCalcRule valCashCalcRule = getValCashCalcRule(dto,benchmarkNo);
            valCashCalcRuleList.add(valCashCalcRule);
        }
        resultMap.put("ValFinProduct", valFinProductList);
        resultMap.put("ValCashInfo", valCashInfoList);
        resultMap.put("ValCashCalcRule", valCashCalcRuleList);
//        resultMap.put("listBenchmarkInfo", listBenchmarkInfo);
//        resultMap.put("listBenchmarkCompo", listBenchmarkCompo);
        /**add by wujie on 20180822 start**/
        resultMap.put("ValFinProductType", valFinProductTypeList);
        /**add by wujie on 20180822 end**/

        return resultMap;
    }
    
    /**
     * 标的类金融产品表 接收
     * 
     * @param dto 01-基础信息
     * @return 标的类金融产品
     * @see
     * @since 1.0
     */
    private FinProduct getValFinProduct(ValMngAssetInfo dto) {

        FinProduct valFinProduct = new FinProduct();
        // 金融产品代码 = A.资产代码
        valFinProduct.setFinprodId(dto.getAssetId());
        // 金融产品类型 = 固定值(F17：债权类资产)
        valFinProduct.setFinprodType(DictCodeListExt1.金融产品类型_计划类资产.getValue());
        // 金融产品简称= A.资产名称
        valFinProduct.setFinprodAbbr(dto.getAssetName());
        // 金融产品全称= A.资产名称
        valFinProduct.setFinprodName(dto.getAssetName());
        // 收益类型= 设置固定值
        valFinProduct.setProfitType(DictCodeList.收益类型_收益型.getValue());
        // 通道代码 = A.通道代码
        valFinProduct.setChlFinprodId(dto.getChlId());
        // 发行规模= A.发行规模
        valFinProduct.setIssueAmt(dto.getIssueAmt());
        // 市场代码= A.资产代码
        valFinProduct.setFinprodMarketId(dto.getAssetId());
        // 币种= A.币种
        valFinProduct.setCcy(dto.getCcy());
        // 境内外= A.境内外
        valFinProduct.setBlnArea(dto.getBlnArea());
        //交易日历 = 调用共通设计->【详细设计-接口共通方法设计.xlsx】->交易日历映射关系SHEET
        valFinProduct.setCalendarId(interfCommonService.getCalendarId(dto.getTradeMarket()));
        // 交易场所= A.交易场所
        valFinProduct.setTradeMarket(dto.getTradeMarket());
        // 发行方式 = A.发行方式 模板上提示字段不用
        //valFinProduct.setIdate(dto.getVdate());
        // 发行日= A.起息日
        valFinProduct.setIdate(dto.getVdate());
        // 起息日= A.起息日
        valFinProduct.setVdate(dto.getVdate());
        // 到期日= A.到期日
        valFinProduct.setMdate(dto.getMdate());
        // 实际到期日= A.到期日
        valFinProduct.setActmdate(dto.getMdate());
        // 是否通道 = A.是否通道
        valFinProduct.setIsChl(dto.getIsChl());
        // 母金融产品代码 = A.资产代码
        valFinProduct.setpFinprodId(dto.getAssetId());
        // 原金融产品代码 = A.资产代码
        valFinProduct.setoFinprodId(dto.getAssetId());
        // 原金融产品类型= F17:债权类资产
//        valFinProduct.setoFinprodType(DictCodeListExt1.金融产品类型_计划类资产.getValue());
        //本金收益特征 = A.本金收益特征
        valFinProduct.setCapiIncomeFeature(dto.getCapiProfFeature());
        //add by liupengfei for 多级法人改造 S
        valFinProduct.setBelongInstitutionCode(dto.getBelongInstitutionCode());
        //add by liupengfei for 多级法人改造 E
        return valFinProduct;
    }
    
    /**
     * 金融产品分类表 接收
     * 
     * @param dto 01-金融产品分类
     * @return 金融产品分类
     * @see
     * @since 1.0
     * add by wujie on 20180822
     */
    private FinProductType getValFinProductType(ValMngAssetInfo dto) {

        FinProductType valFinProductType = new FinProductType();
        //金融产品代码= A.资产代码
        valFinProductType.setFinprodId(dto.getAssetId());
        // 金融产品类型 = 固定值(数据字典：金融产品类型.计划类资产)
//        valFinProductType.setFinprodType(DictCodeListExt1.金融产品类型_计划类资产.getValue());
        //分类1 = A.资产分类
        valFinProductType.setType1(dto.getAssetClass());
        
        /**add by wujie on 20180822 start**/
        // 分类2 = 调用共通方法查询核算分类
        FinProductClass fdto = new FinProductClass();
        //金融产品类型
        fdto.setFinprodType(DictCodeListExt1.金融产品类型_计划类资产.getValue());
        //境内外
        fdto.setBlnArea(dto.getBlnArea());
        //分类1
        fdto.setAssetType(dto.getAssetClass());
        
        valFinProductType.setType2(interfCommonService.getFinanceProductClass(fdto));
        
        // 分类3 = 调用共通方法查询税务分类
        valFinProductType.setType3(interfCommonService.getFinanceProductTaxClass(fdto));
        /**add by wujie on 20180822 end**/
        valFinProductType.setBranch(InterfCommonConstant.分支序号_默认);
        return valFinProductType;
        
    }
    
//    /**
//     * 现金流信息表 接收
//     * 
//     * @param dto 01-基础信息
//     * @return 现金流信息
//     * @see
//     * @since 1.0
//     */
//    private FinCashInfo getValCashInfo(ValMngAssetInfo dto) {
//
//        FinCashInfo valCashInfo = new FinCashInfo();
//        // 现金流代码 = A.资产代码+现金流一级类型(02 利息)
//        valCashInfo.setCashId(dto.getAssetId()+"02");
//        // 现金流一级类型= 02:利息
//        valCashInfo.setCashType1("02");
//        // 现金流二级类型= C02：利息
//        valCashInfo.setCashType2("C02");
//        // 金融产品代码= A.资产名称
//        valCashInfo.setFinprodId(dto.getAssetId());
//        // 金融产品类型= F17:债权类资产
//        valCashInfo.setFinprodType("F17");
//        // 分支序号= 设置全局变量(默认为0）
//        valCashInfo.setBranch((long)0);
//        return valCashInfo;
//    }
    
    
//    /**
//     * 浮动利率初始化
//     * 
//     * @param dto ValSecBaseInfo
//     * @return ValCashCalcRule
//     * @see
//     * @since 1.0
//     */
//    private FloatingInterestRateCommonResult getFloatingInterestRateCommonResult(ValMngAssetInfo dto) {
//    	FloatingInterestRateDto floatDto = new FloatingInterestRateDto();
//    	floatDto.setFloatingInterestRateNo(dto.getIntRateId());
//    	floatDto.setInitRate(dto.getIntRate());//初始利率INIT_RATE,输入.资产标的信息.初始利率                                              
//    	floatDto.setCoefficient(dto.getRatio());//系数COEFFICIENT,输入.资产标的信息.系数                                                
//    	floatDto.setSpreadRate(dto.getSpreadRate());//利差 SPREAD_RATE,输入.资产标的信息.利差                                                
//    	floatDto.setSpreadMax(dto.getRateMax());//利率上限SPREAD_MAX,输入.资产标的信息.利率上限                                              
//    	floatDto.setSpreadMin(dto.getRateMin());//利率下限SPREAD_MIN,输入.资产标的信息.利率下限                                      
//    	
//    	FloatingInterestRateCommonResult result = interfCommonService.floatingInterestRateInitialization(floatDto);
//        return result;
//    }
    
    /**
     * 现金流计算规则表 接收
     * 
     * @param dto 01-基础信息
     * @return 现金流计算规则
     * @see
     * @since 1.0
     */
    private FinCashCalcRule getValCashCalcRule(ValMngAssetInfo dto,String benchmarkNo) {

        FinCashCalcRule valCashCalcRule = new FinCashCalcRule();
        // 现金流代码 = A.资产代码+数据字典：金融产品类型.计划类资产+数据字典：现金流二级类型.利息
        String cashId = InterfHelper.getMd5CashId_(dto.getAssetId(),DictCodeListExt1.金融产品类型_计划类资产.getValue()
        		, DictCodeListExt1.现金流二级类型_利息.getValue());
        valCashCalcRule.setCashId(cashId);
        // 生效日期= A.起息日
        valCashCalcRule.setEffDate(dto.getVdate());
        // 计算类型= A.利率类型
        valCashCalcRule.setCalcType(dto.getRateType());
        // 生效利率= A.初始利率
        valCashCalcRule.setYield(dto.getIntRate());
        // 计算基础= A.计息基础
        valCashCalcRule.setBasis(dto.getBasis());
        // 金融产品代码= A.资产代码
        valCashCalcRule.setFinprodId(dto.getAssetId());
        // 金融产品类型 = 固定值(F17：债权类资产)
        valCashCalcRule.setFinprodType(DictCodeListExt1.金融产品类型_计划类资产.getValue());
        // 分支序号= 设置全局变量(默认为0）
        valCashCalcRule.setBranch((long)0);
        /**add by wujie on 20180724 remark:设置默认值**/
        valCashCalcRule.setBaseType(DictCodeList.基数类型_实收资本.getValue());
        valCashCalcRule.setBaseDateType(DictCodeList.基数日期类型_当日.getValue());
        /**add by wujie on 20180724 end**/
        return valCashCalcRule;
    }
}
