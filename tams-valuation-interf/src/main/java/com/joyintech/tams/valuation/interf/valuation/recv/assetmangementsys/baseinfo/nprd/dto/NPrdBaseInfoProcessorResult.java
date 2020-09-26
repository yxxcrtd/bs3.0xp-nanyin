/*
 * 文件名：AssetMangementRecResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年6月22日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.nprd.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.common.base.entity.BokAcctLayeringInfo;
import com.joyintech.tams.common.base.entity.BokAcctSubjectInfo;
import com.joyintech.tams.common.base.entity.BokBooksetPlan;
import com.joyintech.tams.common.base.entity.BokDetailId;
import com.joyintech.tams.common.base.entity.FinBaseRuleInfo;
import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.FinClawbackCalcRule;
import com.joyintech.tams.common.base.entity.FinFloatAccrualSche;
import com.joyintech.tams.common.base.entity.FinFloatCalcRule;
import com.joyintech.tams.common.base.entity.FinFloatCalcRuleDetail;
import com.joyintech.tams.common.base.entity.FinFloatCountingRule;
import com.joyintech.tams.common.base.entity.FinOpenPlan;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductAdd;
import com.joyintech.tams.common.base.entity.FinTradeProduct;
import com.joyintech.tams.common.base.entity.ValInterflog;
import com.joyintech.tams.valuation.common.interf.base.dto.BooksetInitResult;
import com.joyintech.tams.valuation.common.interf.base.dto.NPrdBaseInfoValCommonResult;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdInfoN;

/**
 * 净值型产品基本信息Processor返回共通结果
 * 
 * @author weipeng
 * @version 1.0 2018年6月22日
 * @see NPrdBaseInfoProcessorResult
 * @since 1.0
 */
public class NPrdBaseInfoProcessorResult extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 标的类金融产品表
     */
    private List<FinProduct> listValFinProduct = new ArrayList<FinProduct>();

    /**
     * 标的类金融产品扩展表
     */
    private List<FinProductAdd> listValFinProductExt = new ArrayList<FinProductAdd>();

    /**
     * 开放计划表
     */
    private List<FinOpenPlan> listValOpenPlan = new ArrayList<FinOpenPlan>();

    /**
     * 现金流信息表
     */
    private List<FinCashInfo> listValCashInfo = new ArrayList<FinCashInfo>();

    /**
     * 现金流计算规则表
     */
    private List<FinCashCalcRule> listValCashCalcRule = new ArrayList<FinCashCalcRule>();

    /**
     * 现金流支付规则表
     */
    private List<FinCashPayRule> listValCashPayRule = new ArrayList<FinCashPayRule>();

    /**
     * 业绩报酬计算规则表
     */
    private List<FinFloatCalcRule> listValFloatCalcRule = new ArrayList<FinFloatCalcRule>();

    /**
     * 业绩报酬计提规则表
     */
    private List<FinFloatCountingRule> listValFloatCountingRule = new ArrayList<FinFloatCountingRule>();

    /**
     * 业绩报酬计算规则明细表
     */
    private List<FinFloatCalcRuleDetail> listValFloatCalcRuleDetail = new ArrayList<FinFloatCalcRuleDetail>();

    /**
     * 现金流计划表
     */
    private List<FinCashPlan> listValCashPlan = new ArrayList<FinCashPlan>();

    /**
     * 业绩报酬计提计划表
     */
    private List<FinFloatAccrualSche> listValPerfRewardAccrsche = new ArrayList<FinFloatAccrualSche>();
    
    /**
     * 账务明细表
     */
    private List<BokDetailId> listValBokDetailId = new ArrayList<BokDetailId>();
    /**
     * 账套信息初始化Result
     */
    private List<BooksetInitResult> booksetInitResultList=new ArrayList<BooksetInitResult>();
    /**
     * 税务方案数据
     */
    private List<BokBooksetPlan> listBokBooksetPlan = new ArrayList<BokBooksetPlan>();
    /**
     * 日志
     */
    private List<ValInterflog> valInterflog = new ArrayList<ValInterflog>();
    
    /**
     * 业绩基础信息
     */
    private List<FinBaseRuleInfo> finBenchmarkDetailList = new ArrayList<FinBaseRuleInfo>();


    public NPrdBaseInfoProcessorResult() {

    }
    /**
     * 交易类金融产品集合
     */
    List<FinTradeProduct> listFinTradeProduct = new ArrayList<FinTradeProduct>();//add by dongjun on 20181019
    
    /**
     * 几许传数
     */
    private List<ValPrdInfoN> listValPrdInfoN = new ArrayList<ValPrdInfoN>();//add by chufengjie on 20190522
    /**
     * 重载构造方法 从valuation-common中返回的result重新赋值
     * 
     * @param rtnResult 共通结果
     */
    public NPrdBaseInfoProcessorResult(NPrdBaseInfoValCommonResult result) {
        this.setListValCashCalcRule(result.getListValCashCalcRule());
        this.setListValCashInfo(result.getListValCashInfo());
        this.setListValFinProduct(result.getListValFinProduct());
        this.setListValFinProductExt(result.getListValFinProductExt());
        this.setListValOpenPlan(result.getListValOpenPlan());
        this.setListValCashInfo(result.getListValCashInfo());
        this.setListValCashCalcRule(result.getListValCashCalcRule());
        this.setListValCashPayRule(result.getListValCashPayRule());
        this.setListValFloatCalcRule(result.getListValFloatCalcRule());
        this.setListValFloatCountingRule(result.getListValFloatCountingRule());
        this.setListValFloatCalcRuleDetail(result.getListValFloatCalcRuleDetail());
        this.setListValCashPlan(result.getListValCashPlan());
        this.setListValPerfRewardAccrsche(result.getListValPerfRewardAccrsche());
        this.setValInterflog(result.getValInterflog());
        this.setListValBokDetailId(result.getListValBokDetailId());
        this.setBooksetInitResultList(result.getBooksetInitResultList());
        this.setListBokBooksetPlan(result.getListBokBooksetPlan());
        this.setParamMap(result.getParamMap());//add by wujie on 20180909
        this.setFinBenchmarkDetailList(result.getListFinBenchmarkDetail());//add by wangqidong on 20190215
        /**modify by dongjun on 20181015 start**/
        this.listAcctSubjectInfo = result.getListAcctSubjectInfo();
        this.listAcctLayeringInfo = result.getListAcctLayeringInfo();
        this.listFinTradeProduct = result.getListFinTradeProduct();
        this.listFinCawlbackCalcRule = result.getListFinCawlbackCalcRule();//add by dongjun on 20190219
        this.listValPrdInfoN = result.getListValPrdInfoN();
    }
    /**
     * 核算主体集合
     */
    private List<BokAcctSubjectInfo> listAcctSubjectInfo = new ArrayList<BokAcctSubjectInfo>();            

    /**
     * 核算分层集合
     */
    private List<BokAcctLayeringInfo> listAcctLayeringInfo = new ArrayList<BokAcctLayeringInfo>();
    
    /**add by dongjun on 20190218 Remark:净值型产品业绩回补规则 start**/
    /**
     * 业绩回补计算规则
     */
    private List<FinClawbackCalcRule> listFinCawlbackCalcRule = new ArrayList<FinClawbackCalcRule>();
    
    /**
     * 
     * @return listFinCawlbackCalcRule
     */
    public List<FinClawbackCalcRule> getListFinCawlbackCalcRule() {
		return listFinCawlbackCalcRule;
	}

    /**
     * 
     * @param listFinCawlbackCalcRule listFinCawlbackCalcRule
     */
	public void setListFinCawlbackCalcRule(List<FinClawbackCalcRule> listFinCawlbackCalcRule) {
		this.listFinCawlbackCalcRule = listFinCawlbackCalcRule;
	}
	/**add by dongjun on 20190218 Remark:净值型产品业绩回补规则 end**/    
    
    /**
     * 获取 listAcctSubjectInfo
     * @return listAcctSubjectInfo.
     */
    public List<BokAcctSubjectInfo> getListAcctSubjectInfo() {
        return listAcctSubjectInfo;
    }

    
    /**
     * 设置 listAcctSubjectInfo
     * @param listAcctSubjectInfo listAcctSubjectInfo
     */
    public void setListAcctSubjectInfo(List<BokAcctSubjectInfo> listAcctSubjectInfo) {
        this.listAcctSubjectInfo = listAcctSubjectInfo;
    }

    
    /**
     * 获取 listAcctLayeringInfo
     * @return listAcctLayeringInfo.
     */
    public List<BokAcctLayeringInfo> getListAcctLayeringInfo() {
        return listAcctLayeringInfo;
    }

    
    /**
     * 设置 listAcctLayeringInfo
     * @param listAcctLayeringInfo listAcctLayeringInfo
     */
    public void setListAcctLayeringInfo(List<BokAcctLayeringInfo> listAcctLayeringInfo) {
        this.listAcctLayeringInfo = listAcctLayeringInfo;
    }

    
    /**
     * 获取 serialversionuid
     * @return serialversionuid.
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * 取得 标的类金融产品表
     * 
     * @return 标的类金融产品表
     */
    public List<FinProduct> getListValFinProduct() {
        return listValFinProduct;
    }

    /**
     * 设置 标的类金融产品表
     * 
     * @param listValFinProduct 标的类金融产品表
     */
    public void setListValFinProduct(List<FinProduct> listValFinProduct) {
        this.listValFinProduct = listValFinProduct;
    }

    /**
     * 取得 标的类金融产品扩展表
     * 
     * @return 标的类金融产品扩展表
     */
    public List<FinProductAdd> getListValFinProductExt() {
        return listValFinProductExt;
    }

    /**
     * 设置 标的类金融产品扩展表
     * 
     * @param listValFinProductExt 标的类金融产品扩展表
     */
    public void setListValFinProductExt(List<FinProductAdd> listValFinProductExt) {
        this.listValFinProductExt = listValFinProductExt;
    }

    /**
     * 取得 开放计划表
     * 
     * @return 开放计划表
     */
    public List<FinOpenPlan> getListValOpenPlan() {
        return listValOpenPlan;
    }

    /**
     * 设置 开放计划表
     * 
     * @param listValOpenPlan 开放计划表
     */
    public void setListValOpenPlan(List<FinOpenPlan> listValOpenPlan) {
        this.listValOpenPlan = listValOpenPlan;
    }

    /**
     * 取得 现金流信息表
     * 
     * @return 现金流信息表
     */
    public List<FinCashInfo> getListValCashInfo() {
        return listValCashInfo;
    }

    /**
     * 设置 现金流信息表
     * 
     * @param listValCashInfo 现金流信息表
     */
    public void setListValCashInfo(List<FinCashInfo> listValCashInfo) {
        this.listValCashInfo = listValCashInfo;
    }

    /**
     * 取得 现金流计算规则表
     * 
     * @return 现金流计算规则表
     */
    public List<FinCashCalcRule> getListValCashCalcRule() {
        return listValCashCalcRule;
    }

    /**
     * 设置 现金流计算规则表
     * 
     * @param listValCashCalcRule 现金流计算规则表
     */
    public void setListValCashCalcRule(List<FinCashCalcRule> listValCashCalcRule) {
        this.listValCashCalcRule = listValCashCalcRule;
    }

    /**
     * 取得 现金流支付规则表
     * 
     * @return 现金流支付规则表
     */
    public List<FinCashPayRule> getListValCashPayRule() {
        return listValCashPayRule;
    }

    /**
     * 设置 现金流支付规则表
     * 
     * @param listValCashPayRule 现金流支付规则表
     */
    public void setListValCashPayRule(List<FinCashPayRule> listValCashPayRule) {
        this.listValCashPayRule = listValCashPayRule;
    }

    /**
     * 取得 业绩报酬计算规则表
     * 
     * @return 业绩报酬计算规则表
     */
    public List<FinFloatCalcRule> getListValFloatCalcRule() {
        return listValFloatCalcRule;
    }

    /**
     * 设置 业绩报酬计算规则表
     * 
     * @param listValFloatCalcRule 业绩报酬计算规则表
     */
    public void setListValFloatCalcRule(List<FinFloatCalcRule> listValFloatCalcRule) {
        this.listValFloatCalcRule = listValFloatCalcRule;
    }

    /**
     * 取得 业绩报酬计提规则表
     * 
     * @return 业绩报酬计提规则表
     */
    public List<FinFloatCountingRule> getListValFloatCountingRule() {
        return listValFloatCountingRule;
    }

    /**
     * 设置 业绩报酬计提规则表
     * 
     * @param listValFloatCountingRule 业绩报酬计提规则表
     */
    public void setListValFloatCountingRule(List<FinFloatCountingRule> listValFloatCountingRule) {
        this.listValFloatCountingRule = listValFloatCountingRule;
    }

    /**
     * 取得 业绩报酬计算规则明细表
     * 
     * @return 业绩报酬计算规则明细表
     */
    public List<FinFloatCalcRuleDetail> getListValFloatCalcRuleDetail() {
        return listValFloatCalcRuleDetail;
    }

    /**
     * 设置 业绩报酬计算规则明细表
     * 
     * @param listValFloatCalcRuleDetail 业绩报酬计算规则明细表
     */
    public void setListValFloatCalcRuleDetail(List<FinFloatCalcRuleDetail> listValFloatCalcRuleDetail) {
        this.listValFloatCalcRuleDetail = listValFloatCalcRuleDetail;
    }

    /**
     * 取得 现金流计划表
     * 
     * @return 现金流计划表
     */
    public List<FinCashPlan> getListValCashPlan() {
        return listValCashPlan;
    }

    /**
     * 设置 现金流计划表
     * 
     * @param listValCashPlan 现金流计划表
     */
    public void setListValCashPlan(List<FinCashPlan> listValCashPlan) {
        this.listValCashPlan = listValCashPlan;
    }

    /**
     * 取得 业绩报酬计提计划表
     * 
     * @return 业绩报酬计提计划表
     */
    public List<FinFloatAccrualSche> getListValPerfRewardAccrsche() {
        return listValPerfRewardAccrsche;
    }

    /**
     * 设置 业绩报酬计提计划表
     * 
     * @param listValPerfRewardAccrsche 业绩报酬计提计划表
     */
    public void setListValPerfRewardAccrsche(List<FinFloatAccrualSche> listValPerfRewardAccrsche) {
        this.listValPerfRewardAccrsche = listValPerfRewardAccrsche;
    }
    
    /**
     * 获取 账务明细表
     * 
     * @return 账务明细表.
     */
    public List<BokDetailId> getListValBokDetailId() {
        return listValBokDetailId;
    }

    /**
     * 设置 账务明细表
     * 
     * @param listValBokDetailId 账务明细表
     */
    public void setListValBokDetailId(List<BokDetailId> listValBokDetailId) {
        this.listValBokDetailId = listValBokDetailId;
    }

    /**
     * 获取 日志
     * 
     * @return 日志.
     */
    public List<ValInterflog> getValInterflog() {
        return valInterflog;
    }

    /**
     * 设置 日志
     * 
     * @param valInterflog 日志
     */
    public void setValInterflog(List<ValInterflog> valInterflog) {
        this.valInterflog = valInterflog;
    }

    
    /**
     * 获取 booksetInitResultList
     * @return booksetInitResultList.
     */
    public List<BooksetInitResult> getBooksetInitResultList() {
        return booksetInitResultList;
    }

    
    /**
     * 设置 booksetInitResultList
     * @param booksetInitResultList booksetInitResultList
     */
    public void setBooksetInitResultList(List<BooksetInitResult> booksetInitResultList) {
        this.booksetInitResultList = booksetInitResultList;
    }

	public List<BokBooksetPlan> getListBokBooksetPlan() {
		return listBokBooksetPlan;
	}

	public void setListBokBooksetPlan(List<BokBooksetPlan> listBokBooksetPlan) {
		this.listBokBooksetPlan = listBokBooksetPlan;
	}

    /**add by wujie on 20180909 start**/
    private Map<String,Object> paramMap = new HashMap<String,Object>();


	/**
	 * @return the paramMap
	 */
	public Map<String, Object> getParamMap() {
		return paramMap;
	}


	/**
	 * @param paramMap the paramMap to set
	 */
	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}


    
    /**
     * 获取 listFinTradeProduct
     * @return listFinTradeProduct.
     */
    public List<FinTradeProduct> getListFinTradeProduct() {
        return listFinTradeProduct;
    }


    
    /**
     * 设置 listFinTradeProduct
     * @param listFinTradeProduct listFinTradeProduct
     */
    public void setListFinTradeProduct(List<FinTradeProduct> listFinTradeProduct) {
        this.listFinTradeProduct = listFinTradeProduct;
    }


    /**
     * 获取 finBenchmarkDetailList
     * @return finBenchmarkDetailList.
     */
    public List<FinBaseRuleInfo> getFinBenchmarkDetailList() {
        return finBenchmarkDetailList;
    }


    /**
     * 设置 finBenchmarkDetailList
     * @param finBenchmarkDetailList finBenchmarkDetailList
     */
    public void setFinBenchmarkDetailList(List<FinBaseRuleInfo> finBenchmarkDetailList) {
        this.finBenchmarkDetailList = finBenchmarkDetailList;
    }
	/**
	 * @return the listValPrdInfoN
	 */
	public List<ValPrdInfoN> getListValPrdInfoN() {
		return listValPrdInfoN;
	}


	/**
	 * @param listValPrdInfoN the listValPrdInfoN to set
	 */
	public void setListValPrdInfoN(List<ValPrdInfoN> listValPrdInfoN) {
		this.listValPrdInfoN = listValPrdInfoN;
	}
	
	/**add by wujie on 20180909 end**/
	
}
