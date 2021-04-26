/*
 * 文件名：FundBaseInfoMoneyFund.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：HuangJian
 * 修改时间：2018年6月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.lendstandard;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.ValInterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.LendStandardCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValDlTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 拆借标的 ValDlTarget
 * 
 * @author HuangJian
 * @version 1.0 2018年7月23日
 * @see LendStandardRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class LendStandardRecService {

    /**
     * TemplateAnalysisForXMLService
     */
    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;
    
    @Autowired
    private InterfCommonService interfCommonService;

    /**
     * LendStandardCommonResult
     * 
     * @param templateSourceFlgDTO TemplateSourceFlgDTO
     * @param acctEntity TemplateOrderInfoReaderResult
     * @return LendStandardCommonResult
     * @see
     * @since 1.0
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public LendStandardCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                            TemplateOrderInfoReaderResult acctEntity) {
        LendStandardCommonResult result = new LendStandardCommonResult();
        List<ValDlTarget> listValDlTarget = new ArrayList<ValDlTarget>();
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);
        
        // 2、进行数据有效性的check,并生出进入日志表的数据结构
        listValDlTarget = returnResult.getT(ValDlTarget.class);

        // 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        Map<String, Object> map = this.handleValDlTarget(listValDlTarget);
        if (map != null) {
            //标的类金融产品list
            result.setListValFinProduct((List<FinProduct>)map.get("ValFinProduct"));
            //交易类金融产品list
            result.setListValTradeFinProduct(
                (List<FinTradeProduct>)map.get("valTradeFinProduct"));
            //金融产品分类list
            result.setListValFinProductType((List<FinProductType>)map.get("valFinProductType"));
            
            /**add by wujie on 20180823 start**/
            // 现金流信息List
            List<FinCashInfo> listValCashInfo = (List<FinCashInfo>)map.get("ValCashInfo");
            result.setListValCashInfo(listValCashInfo);
            // 现金流计算规则List
            List<FinCashCalcRule> listValCashCalcRule = (List<FinCashCalcRule>)map.get(
                "ValCashCalcRule");
            result.setListValCashCalcRule(listValCashCalcRule);
            //现金流支付规则List
            List<FinCashPayRule>  listFinCashPayRule =  (List<FinCashPayRule>)map.get(
                    "FinCashPayRule");
            result.setListValCashPayRule(listFinCashPayRule);
            //现金流计划List
            List<FinCashPlan>  listFinCashPlan =  (List<FinCashPlan>)map.get(
                    "FinCashPlan");
            result.setListValCashPlan(listFinCashPlan);

            //add by sunshuaishuai on 20210322  增加标的类金融产品扩展表 现金流利率信息list start
            result.setListValFinProductExt((List<FinProductAdd>)map.get("listValFinProductAdd"));
            result.setListValCashIntInfo((List<FinRateInfo>)map.get("ValFinCashIntInfo"));
            //add by sunshuaishuai on 20210322 end

            /**add by wujie on 20180823 end**/
            
        }

        // 添加日志
        result.setValInterflog(returnResult.getValInterflog());
        return result;

    }

    /**
     * 更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
     * 
     * @param listValDlTarget List<ValDlTarget>
     * @return Map<String,Object>
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
	private Map<String, Object> handleValDlTarget(List<ValDlTarget> listValDlTarget) {
        Map<String, Object> resultMap = new HashMap<>();
        List<FinTradeProduct> listValTradeFinProduct = new ArrayList<FinTradeProduct>();
        List<FinProduct> listValFinProduct = new ArrayList<FinProduct>();
        List<FinProductType> listValFinProductType = new ArrayList<FinProductType>();
        
        /**add by wujie on 20180823 start**/
        List<FinCashInfo> listValCashInfo = new ArrayList<FinCashInfo>();
        List<FinCashCalcRule> listValCashCalcRule = new ArrayList<FinCashCalcRule>();
        List<FinCashPayRule> listFinCashPayRule = new ArrayList<FinCashPayRule>();
        List<FinCashPlan> listFinCashPlan = new ArrayList<FinCashPlan>();
        /**add by wujie on 20180823 end**/

        //modify by sunshuaishuai on 20210322 增加标的类金融产品扩展表 增加现金流利率信息list start
        List<FinProductAdd> listValFinProductAdd = new ArrayList<FinProductAdd>();
        List<FinRateInfo> listFinCashIntInfo = new ArrayList<FinRateInfo>();
        //modify by sunshuaishuai on 20210322 end

        for (ValDlTarget item : listValDlTarget) {
            /**
             * modify by chendijun 2019/04/29 start
             * IF 接口.业务类型 != 数据字典：金融产品类型.同业借款
             拆借标的信息存放到交易类金融产品表中
             IF 接口.业务类型 eq 数据字典：金融产品类型.同业借款
             拆借标的信息存放到标的类金融产品表中
             */

            if(DictCodeListExt1.金融产品类型_同业借款.getValue().equals(item.getBusiType())) {
                //标的类金融产品
                FinProduct valTradeFinProduct = this.getValFinProduct(item);
                listValFinProduct.add(valTradeFinProduct);
            } else {
                //交易类金融产品
                FinTradeProduct valTradeFinProduct = this.getValTradeFinProduct(item);
                listValTradeFinProduct.add(valTradeFinProduct);
            }

            /**
             * modify by chendijun 2019/04/29 end
             */
            //金融产品分类
            FinProductType valFinProductType = this.getValFinProductType(item);
            listValFinProductType.add(valFinProductType);
            
            /**add by wujie on 20180823 start**/
            // 现金流信息-收息
            FinCashInfo valCashInfo = getValCashInfo(item,DictCodeList.交易方向_收息.getValue());
            listValCashInfo.add(valCashInfo);

            // 现金流计算规则-收息计算规则
            FinCashCalcRule valCashCalcRule = getValCashCalcRule(item,valCashInfo);
            listValCashCalcRule.add(valCashCalcRule);
            
            // 现金流信息-本金
            valCashInfo = getValCashInfo(item,DictCodeList.交易方向_还本.getValue());
            listValCashInfo.add(valCashInfo);
            
            /**add by wujie on 20180823 end**/

            //modify by sunshuaishuai on 20210322 增加标的类金融产品扩展表 增加现金流利率信息list start
            listValFinProductAdd.add(this.getValFinProductAdd(item));
            listFinCashIntInfo.add(this.getFinCashIntInfo(item, valCashCalcRule));
            //modify by sunshuaishuai on 20210322 end

            // 拆出 初始化还本支付规则、付息支付规则、还本计划、付息计划信息
            if(!DictCodeListExt1.金融产品类型_同业借款.getValue().equals(item.getBusiType())){
            	Map<String,Object> map = interfCommonService.getFinTradProduct(item);
            	listFinCashPayRule.addAll((List<FinCashPayRule>)map.get("FIN_CASH_PAY_RULE"));
            	listFinCashPlan.addAll((List<FinCashPlan>)map.get("FIN_CASH_PLAN"));
            }
        }
        resultMap.put("valTradeFinProduct", listValTradeFinProduct);
        resultMap.put("ValFinProduct", listValFinProduct);
        resultMap.put("valFinProductType", listValFinProductType);
        /**add by wujie on 20180823 start**/
        resultMap.put("ValCashInfo", listValCashInfo);
        resultMap.put("ValCashCalcRule", listValCashCalcRule);
        resultMap.put("FinCashPayRule", listFinCashPayRule);
        resultMap.put("FinCashPlan", listFinCashPlan);
        /**add by wujie on 20180823 end**/
        //modify by sunshuaishuai on 20210322 增加标的类金融产品扩展表
        resultMap.put("listValFinProductAdd", listValFinProductAdd);
        //modify by sunshuaishuai on 20210319 增加现金流利率信息list
        resultMap.put("ValFinCashIntInfo", listFinCashIntInfo);

        return resultMap;
    }

    /**
     * 交易类金融产品 T1
     * 
     * @param dto ValDlTarget
     * @return ValTradeFinProduct
     * @see
     * @since 1.0
     */
    private FinTradeProduct getValTradeFinProduct(ValDlTarget dto) {
        FinTradeProduct result = new FinTradeProduct();
        // 金融产品代码 = A.拆借标的代码
        result.setFinprodId(dto.getDlId());
        // 金融产品类型 = A.业务类型
        result.setFinprodType(dto.getBusiType());
        // 分支序号 = 设置全局变量(默认为0）
        result.setBranch(InterfCommonConstant.分支序号_默认);
        // 分支类型 = 设置全局变量(默认为空)
        result.setBranchType(null);
        // 通道代码 = A.通道代码
        result.setChlAgrtId(dto.getChlId());
        // 名义本金 = A.名义本金
        result.setPrin(dto.getCapital());
        // 本金币种 = A.币种
        result.setCcy(dto.getCcy());
        // 起息日 = A.起息日
        result.setVdate(dto.getVdate());
        // 到期日 = A.到期日
        result.setMdate(dto.getMdate());
        // 期限(天) = A.到期日-A.起息日
        result.setTerm(DateUtil.getBetweenDayNumber(dto.getVdate(), dto.getMdate()));
        // 利率类型 = A.利率类型
        result.setIntType(dto.getRateType());
        // 固定利率 = A.拆借利率
        result.setIntRate(dto.getDlRate());
        // 计息基础 = A.计息基础
        result.setBasis(dto.getBasis());
        // 到期本金 = A.到期本金
        result.setmPrinAmt(dto.getmPrinAmt());
        // 到期利息 = A.到期利息
        result.setmIntAmt(dto.getmIntAmt());
        // 到期金额 = A.到期金额
        result.setmTradeAmt(dto.getmTradeAmt());
        // 原金融产品代码 = A.拆借标的代码
        result.setoFinprodId(dto.getDlId());
        // 原金融产品类型 = A.业务类型
//        result.setoFinprodType(dto.getBusiType());
        //期限品种 = A.期限品种
        result.setTermType(dto.getTermKind());
        //本金收益特征 = A.本金收益特征
        result.setCapiIncomeFeature(dto.getCapiProfFeature());
        // 所属机构 = A.所属机构   add by tanxuan on 20210310 多级法人权限控制改造
        result.setBelongInstitutionCode(dto.getBelongInstitutionCode());
        /**add by wujie on 20190115 start**/
        //收益类型 = 数据字典：收益类型.预期收益
        result.setProfitType(DictCodeList.收益类型_收益型.getValue());
        /**add by wujie on 20190115 end**/
        
        return result;

    }

    /**
     *
     * 标的类金融产品
     *
     * @param dto
     * @return FinProduct
     * @see
     * @since 1.0
     */
    private FinProduct getValFinProduct(ValDlTarget dto) {
        FinProduct finProduct = new FinProduct();
        //金融产品代码 = A.拆借标的代码
        finProduct.setFinprodId(dto.getDlId());
        //金融产品类型 = A.业务类型
        finProduct.setFinprodType(dto.getBusiType());
        //金融产品简称 = A.拆借标的名称
        finProduct.setFinprodAbbr(dto.getDlName());
        //金融产品全称 = A.拆借标的名称
        finProduct.setFinprodName(dto.getDlName());
        //收益类型 = 数据字典：收益类型.收益型
        finProduct.setProfitType(DictCodeList.收益类型_收益型.getValue());
        //市场代码 = A.拆借标的代码
        finProduct.setFinprodMarketId(dto.getDlId());
        //币种 = A.币种
        finProduct.setCcy(dto.getCcy());
        //起息日  = A.起息日
        finProduct.setVdate(dto.getVdate());
        //到期日 = A.到期日
        finProduct.setMdate(dto.getMdate());
        //实际到期日  = A.到期日
        finProduct.setActmdate(dto.getMdate());
        //原金融产品代码 = A.拆借标的代码
        finProduct.setoFinprodId(dto.getDlId());
        //原金融产品类型 = A.业务类型
        //finProduct.setoFinprodType(dto.getBusiType());
        //本金收益特征 = A.本金收益特征
        finProduct.setCapiIncomeFeature(dto.getCapiProfFeature());
        //发现规模 = A.名义本金
        finProduct.setIssueAmt(dto.getCapital());
        // 所属机构 = A.所属机构   add by tanxuan on 20210310 多级法人权限控制改造
        finProduct.setBelongInstitutionCode(dto.getBelongInstitutionCode());
        return finProduct;
    }

    /**
     * 接收 金融产品分类表 T2
     * 
     * @param dto ValDlTarget
     * @return ValFinProductType
     * @see
     * @since 1.0
     */
    private FinProductType getValFinProductType(ValDlTarget dto) {
        FinProductType result = new FinProductType();
        // 金融产品代码 = A.拆借标的代码
        result.setFinprodId(dto.getDlId());
        // 金融产品类型 = A.业务类型
//        result.setFinprodType(dto.getBusiType());
        /**add by wujie 20180822 start**/
        //分类1 = A.资产类型
        result.setType1(dto.getAssetClass());
        // 分类2 = 调用共通方法查询核算分类
        FinProductClass fdto = new FinProductClass();
        //金融产品类型
        fdto.setFinprodType(dto.getBusiType());
        //资产类型
        fdto.setAssetType(dto.getAssetClass());
        //机构核算分类
        fdto.setOrgClass(dto.getValClas());
        result.setType2(interfCommonService.getFinanceProductClass(fdto));
        // 分类3 = 调用共通方法查询税务分类
        result.setType3(interfCommonService.getFinanceProductTaxClass(fdto));
        /**add by wujie on 20180822 end**/
        result.setBranch(InterfCommonConstant.分支序号_默认);
        return result;
    }


    /**
     * T3 现金流信息
     * 
     * @param dto 01-基础信息
     * @return 现金流信息
     * @see
     * @since 1.0
     */
    private FinCashInfo getValCashInfo(ValDlTarget dto,String ps) {
        FinCashInfo valCashInfo = new FinCashInfo();
        if(ps.equals(DictCodeList.交易方向_收息.getValue())){
        	// 现金流代码 = A.拆借标的代码+A.业务类型+数据字典：现金流二级类型.利息
            valCashInfo.setCashId(InterfHelper.getMd5CashId_(dto.getDlId(),dto.getBusiType(),
            		DictCodeListExt1.现金流二级类型_利息.getValue()));
            // 现金流一级类型 = 02 利息
            valCashInfo.setCashType1(DictCodeListExt1.现金流一级类型_利息.getValue());
            // 现金流二级类型 = C02 利息
            valCashInfo.setCashType2(DictCodeListExt1.现金流二级类型_利息.getValue());
        }else{
        	// 现金流代码 = A.拆借标的代码+A.业务类型+数据字典：现金流二级类型.本金
            valCashInfo.setCashId(InterfHelper.getMd5CashId_(dto.getDlId(),dto.getBusiType(),
            		DictCodeListExt1.现金流二级类型_本金.getValue()));
            // 现金流一级类型 = 02 利息
            valCashInfo.setCashType1(DictCodeListExt1.现金流一级类型_本金.getValue());
            // 现金流二级类型 = C02 利息
            valCashInfo.setCashType2(DictCodeListExt1.现金流二级类型_本金.getValue());
        }
        // 金融产品代码 = A.资产代码
        valCashInfo.setFinprodId(dto.getDlId());
        // 金融产品类型 = A.业务类型
        valCashInfo.setFinprodType(dto.getBusiType());
        // 分支序号 = 设置全局变量(默认为0）
        valCashInfo.setBranch(InterfCommonConstant.分支序号_默认);
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
    private FinCashCalcRule getValCashCalcRule(ValDlTarget dto,FinCashInfo T3) {
        FinCashCalcRule valCashCalcRule = new FinCashCalcRule();
        // 现金流代码 = T3.现金流代码
        valCashCalcRule.setCashId(T3.getCashId());
        // 生效日期 = A.起息日
        valCashCalcRule.setEffDate(dto.getVdate());
        // 计算类型 = A.利率类型
        valCashCalcRule.setCalcType(dto.getRateType());
        // 浮动利率编号 = T1.利率编号
//        valCashCalcRule.setIntRateId(T1.getIntRateId());
        // 生效利率 = A.拆借利率
        valCashCalcRule.setYield(dto.getDlRate());
        // 计算基础 = A.计息基础
        valCashCalcRule.setBasis(dto.getBasis());
        // 金融产品代码 = A.资产代码
        valCashCalcRule.setFinprodId(dto.getDlId());
        // 金融产品类型 = A.业务类型
        valCashCalcRule.setFinprodType(dto.getBusiType());
        //基数类型 = 数据字典：基数类型.实收资本
        valCashCalcRule.setBaseType(DictCodeList.基数类型_实收资本.getValue());
        //基数日期类型 = 数据字典：基数日期类型.当日
        valCashCalcRule.setBaseDateType(DictCodeList.基数日期类型_当日.getValue());
        // 分支序号 = 设置全局变量(默认为0）
        valCashCalcRule.setBranch(InterfCommonConstant.分支序号_默认);
        return valCashCalcRule;
    }

    /**
     * 构建标的类金融产品扩展表
     * @param dto
     * @return
     */
    private FinProductAdd getValFinProductAdd(ValDlTarget dto) {
        FinProductAdd result = new FinProductAdd();
        // 金融产品代码 = A.资产代码
        result.setFinprodId(dto.getDlId());
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
    private FinRateInfo getFinCashIntInfo(ValDlTarget dto, FinCashCalcRule calcDto) {
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
//        //系数=A.系数
//        valFinCashIntInfo.setCoefficient(dto.getRatio());
//        //利差=A.利差
//        valFinCashIntInfo.setSpreadRate(dto.getSpreadRate());
//        //利率上限=A.利率上限
//        valFinCashIntInfo.setHighestRate(dto.getRateMax());
//        //利率下限=A.利率下限
//        valFinCashIntInfo.setLowestRate(dto.getRateMin());
//        //基准编号=A.基准利率编号
//        valFinCashIntInfo.setBenchmarkId(dto.getIntRateId());
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
}
