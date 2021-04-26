/*
 * 文件名：SecBaseInfoRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：HuangJian
 * 修改时间：2018年6月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.sec;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.ValInterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.SecBaseInfoRecProcessorRecCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValCurrentDepositRate;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValSecBaseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 债券基础信息
 * 
 * @author HuangJian
 * @version 1.0 2018年6月28日
 * @see SecBaseInfoRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class SecBaseInfoRecService {

    /**
     * TemplateAnalysisForXMLService
     */
    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;
    @Autowired
    private InterfCommonService interfCommonService;
    

    @SuppressWarnings({"unchecked", "rawtypes"})
    public SecBaseInfoRecProcessorRecCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                                          TemplateOrderInfoReaderResult acctEntity) {
        SecBaseInfoRecProcessorRecCommonResult result = new SecBaseInfoRecProcessorRecCommonResult();
        List<ValSecBaseInfo> listValSecBaseInfo = new ArrayList<ValSecBaseInfo>();
        // 解析模板后的list以及日志list
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);
        
        // 2、进行数据有效性的check,并生出进入日志表的数据结构
        listValSecBaseInfo = returnResult.getT(ValSecBaseInfo.class);
        // 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        Map<String, Object> map = this.handleValSecBaseInfo(listValSecBaseInfo);
        if (!map.isEmpty()) {
            result.setListValCashInfo((List<FinCashInfo>)map.get("listValCashInfo"));
            result.setListValFinProduct((List<FinProduct>)map.get("listValFinProduct"));
            result.setListValFinProductType(
                (List<FinProductType>)map.get("listValFinProductType"));
            result.setListValCashCalcRule((List<FinCashCalcRule>)map.get("listValCashCalcRule"));
            //modify by yanya on 20210318 增加标的类金融产品扩展表
            result.setListValFinProductExt((List<FinProductAdd>)map.get("listValFinProductAdd"));
            //modify by yanya on 20210318 现金流利率信息list
            result.setListValCashIntInfo((List<FinRateInfo>)map.get("ValFinCashIntInfo"));
        }
        // 添加日志
        result.setListValInterflog(returnResult.getValInterflog());
        return result;
    }

    /**
     * 更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
     * 
     * @param listValSecBaseInfo List<ValSecBaseInfo>
     * @return Map<String,Object>
     * @see
     * @since 1.0
     */
    private Map<String, Object> handleValSecBaseInfo(List<ValSecBaseInfo> listValSecBaseInfo) {
        Map<String, Object> mapResult = new HashMap<>();
        List<FinProduct> listValFinProduct = new ArrayList<FinProduct>();
        List<FinProductType> listValFinProductType = new ArrayList<FinProductType>();
        List<FinCashCalcRule> listValCashCalcRule = new ArrayList<FinCashCalcRule>();
        //modify by yanya on 20210318 增加标的类金融产品扩展表
        List<FinProductAdd> listValFinProductAdd = new ArrayList<FinProductAdd>();
        //modify by yanya on 20210318 增加现金流利率信息list
        List<FinRateInfo> listFinCashIntInfo = new ArrayList<FinRateInfo>();
        for (ValSecBaseInfo item : listValSecBaseInfo) {
        	String benchmarkNo = null;
        	//接收 标的类金融产品 T1
            FinProduct ValFinProduct = this.getValFinProduct(item);
            listValFinProduct.add(ValFinProduct);
            //接收 金融产品分类 T2
            FinProductType valFinProductType = this.getValFinProductType(item);
            listValFinProductType.add(valFinProductType);
            //接收 现金流计算规则
            FinCashCalcRule valCashCalcRule = this.getValCashCalcRule(item,benchmarkNo);
            listValCashCalcRule.add(valCashCalcRule);
            //modify by yanya on 20210318 增加标的类金融产品扩展表
            listValFinProductAdd.add(this.getValFinProductAdd(item));
            //modify by yanya on 20210318 增加现金流利率信息list
            listFinCashIntInfo.add(this.getFinCashIntInfo(item, valCashCalcRule));
        }
        mapResult.put("listValFinProduct", listValFinProduct);
        mapResult.put("listValFinProductType", listValFinProductType);
        mapResult.put("listValCashCalcRule", listValCashCalcRule);
        //modify by yanya on 20210318 增加标的类金融产品扩展表
        mapResult.put("listValFinProductAdd", listValFinProductAdd);
        //modify by yanya on 20210318 增加现金流利率信息list
        mapResult.put("ValFinCashIntInfo", listFinCashIntInfo);

        return mapResult;
    }

    /**
     * 接收 标的类金融产品 T1
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    private FinProduct getValFinProduct(ValSecBaseInfo dto) {
        FinProduct result = new FinProduct();
        // 金融产品代码 = A.资产代码
        result.setFinprodId(dto.getAssetId());
        // 金融产品类型 = F01 债券
        result.setFinprodType(DictCodeListExt1.金融产品类型_债券.getValue());
        // 金融产品简称 = A.债券简称
        result.setFinprodAbbr(dto.getSecAbbr());
        // 金融产品全称 = A.债券名称
        result.setFinprodName(dto.getSecName());
        // 息票品种 = A.息票品种
        result.setCouponSpecies(dto.getCouponSpecies());
        // 通道代码 = A.通道代码
        result.setChlFinprodId(dto.getChlId());
        // 市场代码 = A.债券代码
        result.setFinprodMarketId(dto.getSecId());
        // 发行价 = A.发行价格
        result.setIssuePrice(dto.getPublishPrice());
        // 发行规模 = A.发行总额
        result.setIssueAmt(dto.getPublishTotal());
        // 币种 = A.币种
        result.setCcy(dto.getCcy());
        // 境内外 = A.境内外
        result.setBlnArea(dto.getInOut());
        //交易日历 = 调用共通设计->【详细设计-接口共通方法设计.xlsx】->交易日历映射关系SHEET
        result.setCalendarId(interfCommonService.getCalendarId(dto.getTradePlace()));
        // 交易场所 = A.交易场所
        result.setTradeMarket(dto.getTradePlace());
        // 发行人 = A.发行人
//        result.setIssuer(dto.getPublisher()); //暂时不解析，后续再确认
        // 发行日 = A.起息日
        result.setIdate(dto.getVdate());
        // 起息日 = A.起息日
        result.setVdate(dto.getVdate());
        // 到期日= A.到期日
        result.setMdate(dto.getMdate());
        // 实际到期日 = A.到期日
        result.setActmdate(dto.getMdate());
        // 是否通道 = A.是否通道
        result.setIsChl(dto.getIsChl());
        // 是否含权 = A.是否含权债
        result.setIsRight(dto.getIsInright());
        // 原金融产品代码 = A.资产代码
        result.setoFinprodId(dto.getAssetId());
        // 原金融产品类型 =F01 债券
//        result.setoFinprodType(DictCodeListExt1.金融产品类型_债券.getValue());
        result.setProfitType(DictCodeList.收益类型_收益型.getValue());
        //本金收益特征 = A.本金收益特征
        result.setCapiIncomeFeature(dto.getCapiProfFeature());
        //托管登记机构 = A.托管登记机构
        result.setRegistOrg(dto.getRegistOrg());
        /** modify by dongjun on 20181017 含权债摊销日 = 系统参数.行权债摊销目标日是否为行权日 start**/
        result.setExeDate(CacheUtil.getSysParam("ISSECTOEXEDATE"));//modify by dongjun on 20181023
        /** modify by dongjun on 20181017 含权债摊销日 = 系统参数.行权债摊销目标日是否为行权日 end**/
        /** modify by dongjun on 20181017 end**/
        
        /**modify by wujie on 20181126 start
         * remark:
         * 含权债时:取【系统参数.含权债摊销摊销日】
         * 非含权债：数据字典：含权债摊销日.到期日**/
        if(DictCodeList.是否_否.getValue().equals(dto.getIsInright())){
        	result.setExeDate(DictCodeList.含权债摊销日_到期日.getValue());
        }
        /**modify by wujie on 20181126 end**/

        return result;
    }

    /**
     * 构建标的类金融产品扩展表
     * @param dto
     * @return
     */
    private FinProductAdd getValFinProductAdd(ValSecBaseInfo dto) {
        FinProductAdd result = new FinProductAdd();
        // 金融产品代码 = A.资产代码
        result.setFinprodId(dto.getAssetId());
        //数据来源 = 常量.估值数据来源
        result.setDataSource(ValInterfCommonConstant.getValDataSource());
        return result;
    }

    /**
     * 现金流信息表构建
     * @param dto 接口数据dto
     * @param calcDto 已经构建的现金流计算规则
     * @return
     */
    private FinRateInfo getFinCashIntInfo(ValSecBaseInfo dto, FinCashCalcRule calcDto) {
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
        valFinCashIntInfo.setSpreadRate(dto.getProfitSub());
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
     * 接收 金融产品分类 T2
     * 
     * @param dto ValSecBaseInfo
     * @return ValFinProductType
     * @see
     * @since 1.0
     */
    private FinProductType getValFinProductType(ValSecBaseInfo dto) {
        FinProductType result = new FinProductType();
        // 金融产品代码 = A.资产代码
        result.setFinprodId(dto.getAssetId());
        // 金融产品类型 =F01 债券
//        result.setFinprodType(DictCodeListExt1.金融产品类型_债券.getValue());
        // 分类1，金融产品类型为债券时，设为债券二级类型 =债券类型
        result.setType1(dto.getBondType());
        
        /**add by wujie on 20180822 start**/
        // 分类2 = 调用共通方法查询核算分类
        FinProductClass fdto = new FinProductClass();
        //金融产品类型
        fdto.setFinprodType(DictCodeListExt1.金融产品类型_债券.getValue());
        //资产类型
        fdto.setAssetType(dto.getBondType());
        //交易场所
        fdto.setTradeMarket(dto.getTradePlace());
        //境内外
        fdto.setBlnArea(dto.getInOut());
        result.setType2(interfCommonService.getFinanceProductClass(fdto));
        
        // 分类3 = 调用共通方法查询税务分类
        result.setType3(interfCommonService.getFinanceProductTaxClass(fdto));
        /**add by wujie on 20180822 end**/
        result.setBranch(InterfCommonConstant.分支序号_默认);
        return result;
    }

//    /**
//     * 接收 现金流信息 T3
//     * 
//     * @param dto ValSecBaseInfo
//     * @return ValCashInfo
//     * @see
//     * @since 1.0
//     * add by wujie on 20180815
//     */
//    private FinCashInfo getFinCashInfo(ValSecBaseInfo dto) {
//        FinCashInfo result = new FinCashInfo();
//        /**modify by wujie on 20190121 start
//        // 现金流代码 = A.资产代码+数据字典：金融产品类型.债券+数据字典：现金流二级类型.行权
//        String cashId = InterfHelper.getMd5CashId_(dto.getAssetId(),DictCodeListExt1.金融产品类型_债券.getValue()
//        		,DictCodeListExt1.现金流二级类型_行权.getValue());
//        		**/
//        // 现金流代码 = A.资产代码+数据字典：金融产品类型.债券+数据字典：现金流二级类型.本金
//        String cashId = InterfHelper.getMd5CashId_(dto.getAssetId(),DictCodeListExt1.金融产品类型_债券.getValue()
//        		,DictCodeListExt1.现金流二级类型_本金.getValue());
//        /**modify by wujie on 20190121 end**/
//        
//        result.setCashId(cashId);
//        // 现金流一级类型 = 数据字典：现金流一级类型.行权
//        result.setCashType1(DictCodeListExt1.现金流一级类型_行权.getValue());
//        /**modify by wujie on 20190121 start
//        // 现金流二级类型 = 默认为空
//        result.setCashType2(DictCodeListExt1.现金流二级类型_行权.getValue());
//        **/
//        result.setCashType2(DictCodeListExt1.现金流二级类型_本金.getValue());
//        /**modify by wujie on 20190121 end**/
//        // 金融产品代码 =A.资产代码
//        result.setFinprodId(dto.getAssetId());
//        // 金融产品类型 =F01 债券
//        result.setFinprodType(DictCodeListExt1.金融产品类型_债券.getValue());
//        // 分支序号 = 设置全局变量(默认为0）
//        result.setBranch(InterfCommonConstant.分支序号_默认);
//        return result;
//    }
    
//    /**
//     * 浮动利率初始化
//     * 
//     * @param dto ValSecBaseInfo
//     * @return ValCashCalcRule
//     * @see
//     * @since 1.0
//     */
//    private FloatingInterestRateCommonResult getFloatingInterestRateCommonResult(ValSecBaseInfo dto) {
//    	FloatingInterestRateDto floatDto = new FloatingInterestRateDto();
//		floatDto.setFloatingInterestRateNo(dto.getIntRateId());
//    	floatDto.setInitRate(dto.getInitRate());//初始利率INIT_RATE,输入.资产标的信息.初始利率                                              
//    	floatDto.setCoefficient(dto.getRatio());//系数COEFFICIENT,输入.资产标的信息.系数                                                
//    	floatDto.setSpreadRate(dto.getProfitSub());//利差 SPREAD_RATE,输入.资产标的信息.利差                                                
//    	floatDto.setSpreadMax(dto.getRateMax());//利率上限SPREAD_MAX,输入.资产标的信息.利率上限                                              
//    	floatDto.setSpreadMin(dto.getRateMin());//利率下限SPREAD_MIN,输入.资产标的信息.利率下限                                      
//    	FloatingInterestRateCommonResult result = interfCommonService.floatingInterestRateInitialization(floatDto);
//        return result;
//    }

    /**
     * 接收 现金流计算规则
     * 
     * @param dto ValSecBaseInfo
     * @return ValCashCalcRule
     * @see
     * @since 1.0
     */
    private FinCashCalcRule getValCashCalcRule(ValSecBaseInfo dto,String benchmarkNo) {
        FinCashCalcRule result = new FinCashCalcRule();
        // 现金流代码 = A.资产代码+数据字典：金融产品类型.债券+数据字典：现金流二级类型.利息
        String cashId = InterfHelper.getMd5CashId_(dto.getAssetId(),DictCodeListExt1.金融产品类型_债券.getValue()
        		,DictCodeListExt1.现金流二级类型_利息.getValue());
        result.setCashId(cashId);
        // 生效日期 = A.起息日
        result.setEffDate(dto.getVdate());
        // 计算类型 =A.利率类型
        result.setCalcType(dto.getIntType());
        //调用浮动利率初始化方法
//        result.setIntRateId(benchmarkNo);
        // 生效利率 = 初始利率
         result.setYield(dto.getInitRate());
        // 计算基础 = A.计息基础
        result.setBasis(dto.getBasis());
        // 金融产品代码 =A.资产代码
        result.setFinprodId(dto.getAssetId());
        // 金融产品类型 =F01 债券
        result.setFinprodType(DictCodeListExt1.金融产品类型_债券.getValue());
        // 分支序号 =设置全局变量(默认为0）
        result.setBranch(InterfCommonConstant.分支序号_默认);
        /**add by wujie on 20180724 remark:设置默认值**/
        result.setBaseType(DictCodeList.基数类型_实收资本.getValue());
        result.setBaseDateType(DictCodeList.基数日期类型_当日.getValue());
        /**add by wujie on 20180724 end**/
//        /** modify by yanya on 20210319 增加是否初始利率字段：基础信息接入时始终为是**/
//        result.setIsInitial(DictCodeList.是否_是.getValue());
        return result;
    }
}
