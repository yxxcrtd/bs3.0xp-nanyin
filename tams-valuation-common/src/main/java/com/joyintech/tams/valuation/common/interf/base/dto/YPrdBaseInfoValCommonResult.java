/*
 * 文件名：YPrdBaseInfoValCommonResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：dongjun
 * 修改时间：2018年10月8日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.interf.base.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.BokAcctLayeringInfo;
import com.joyintech.tams.common.base.entity.BokAcctSubjectInfo;
import com.joyintech.tams.common.base.entity.BokBooksetPlan;
import com.joyintech.tams.common.base.entity.BokDetailId;
import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.FinFloatAccrualSche;
import com.joyintech.tams.common.base.entity.FinFloatCalcRule;
import com.joyintech.tams.common.base.entity.FinFloatCalcRuleDetail;
import com.joyintech.tams.common.base.entity.FinFloatCountingRule;
import com.joyintech.tams.common.base.entity.FinOpenPlan;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductAdd;
import com.joyintech.tams.common.base.entity.FinTradeProduct;
import com.joyintech.tams.common.base.entity.ValInterflog;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdBaseInfo;

/**
 *  预期型产品接口共通DTO
 * 
 * @author dongjun
 * @version 1.0 2018年10月8日
 * @see YPrdBaseInfoValCommonResult
 * @since 1.0
 */
public class YPrdBaseInfoValCommonResult extends BaseEntity {

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
     * 账套信息初始化Result
     */
    private List<BooksetInitResult> booksetInitResultList = new ArrayList<BooksetInitResult>();

    /**
     * 现金流信息表
     */
    private List<FinCashInfo> listValCashInfo = new ArrayList<FinCashInfo>();

    /**
     * 现金流计算规则表
     */
    private List<FinCashCalcRule> listValCashCalcRule = new ArrayList<FinCashCalcRule>();
    
    /**
 	 * 现金流计划表
 	 */
 	private List<FinCashPlan> listValCashPlan = new ArrayList<FinCashPlan>();

	/**
	 * 开放计划表
	 */
	private List<FinOpenPlan> listValOpenPlan = new ArrayList<FinOpenPlan>();
	
    /**
     * 现金流支付规则表
     */
    private List<FinCashPayRule> listValCashPayRule = new ArrayList<FinCashPayRule>();
    
    /**
     * 业绩报酬计提计划
     */
    private List<FinFloatAccrualSche> listFinFloatAccrualSche = new ArrayList<FinFloatAccrualSche>();

    /**
     * 账务明细表
     */
    private List<BokDetailId> listValBokDetailId = new ArrayList<BokDetailId>();

    /**
     * 税务方案数据
     */
    private List<BokBooksetPlan> listBokBooksetPlan = new ArrayList<BokBooksetPlan>();
    //huwei 2018.10.09
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
	//huwei 2018.10.09
    /**
     * 日志
     */
    private List<ValInterflog> valInterflog = new ArrayList<ValInterflog>();

    private Map<String, Object> paramMap = new HashMap<String, Object>();
    
    /**modify by dongjun on 20181015 start**/
    /**
     * 核算主体集合
     */
    private List<BokAcctSubjectInfo> listAcctSubjectInfo = new ArrayList<BokAcctSubjectInfo>();            

    /**
     * 核算分层集合
     */
    private List<BokAcctLayeringInfo> listAcctLayeringInfo = new ArrayList<BokAcctLayeringInfo>();
    
    /**
     * 交易类金融产品集合
     */
    List<FinTradeProduct> listFinTradeProduct = new ArrayList<FinTradeProduct>();//add by dongjun on 20181019


    /**
     * 几许传数
     */
    private List<ValPrdBaseInfo> listValPrdInfoY = new ArrayList<ValPrdBaseInfo>();


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
    /**modify by dongjun on 20181015 end**/

    /**
     * 获取 valInterflog
     * 
     * @return valInterflog.
     */
    public List<ValInterflog> getValInterflog() {
        return valInterflog;
    }

    /**
     * 设置 valInterflog
     * 
     * @param valInterflog valInterflog
     */
    public void setValInterflog(List<ValInterflog> valInterflog) {
        this.valInterflog = valInterflog;
    }

    /**
     * 获取 listValFinProduct
     * 
     * @return listValFinProduct.
     */
    public List<FinProduct> getListValFinProduct() {
        return listValFinProduct;
    }

    /**
     * 设置 listValFinProduct
     * 
     * @param listValFinProduct listValFinProduct
     */
    public void setListValFinProduct(List<FinProduct> listValFinProduct) {
        this.listValFinProduct = listValFinProduct;
    }

    /**
     * 获取 serialversionuid
     * 
     * @return serialversionuid.
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * 获取 listValFinProductExt
     * 
     * @return listValFinProductExt.
     */
    public List<FinProductAdd> getListValFinProductExt() {
        return listValFinProductExt;
    }

    /**
     * 设置 listValFinProductExt
     * 
     * @param listValFinProductExt listValFinProductExt
     */
    public void setListValFinProductExt(List<FinProductAdd> listValFinProductExt) {
        this.listValFinProductExt = listValFinProductExt;
    }

    /**
     * 获取 booksetInitResultList
     * 
     * @return booksetInitResultList.
     */
    public List<BooksetInitResult> getBooksetInitResultList() {
        return booksetInitResultList;
    }

    /**
     * 设置 booksetInitResultList
     * 
     * @param booksetInitResultList booksetInitResultList
     */
    public void setBooksetInitResultList(List<BooksetInitResult> booksetInitResultList) {
        this.booksetInitResultList = booksetInitResultList;
    }

    /**
     * 获取 listValCashInfo
     * 
     * @return listValCashInfo.
     */
    public List<FinCashInfo> getListValCashInfo() {
        return listValCashInfo;
    }

    /**
     * 设置 listValCashInfo
     * 
     * @param listValCashInfo listValCashInfo
     */
    public void setListValCashInfo(List<FinCashInfo> listValCashInfo) {
        this.listValCashInfo = listValCashInfo;
    }

    /**
     * 获取 listValBokDetailId
     * 
     * @return listValBokDetailId.
     */
    public List<BokDetailId> getListValBokDetailId() {
        return listValBokDetailId;
    }

    /**
     * 设置 listValBokDetailId
     * 
     * @param listValBokDetailId listValBokDetailId
     */
    public void setListValBokDetailId(List<BokDetailId> listValBokDetailId) {
        this.listValBokDetailId = listValBokDetailId;
    }

    /**
     * 获取 listBokBooksetPlan
     * 
     * @return listBokBooksetPlan.
     */
    public List<BokBooksetPlan> getListBokBooksetPlan() {
        return listBokBooksetPlan;
    }

    /**
     * 设置 listBokBooksetPlan
     * 
     * @param listBokBooksetPlan listBokBooksetPlan
     */
    public void setListBokBooksetPlan(List<BokBooksetPlan> listBokBooksetPlan) {
        this.listBokBooksetPlan = listBokBooksetPlan;
    }

    /**
     * 获取 paramMap
     * 
     * @return paramMap.
     */
    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    /**
     * 设置 paramMap
     * 
     * @param paramMap paramMap
     */
    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    /**
     * 获取 listValCashCalcRule
     * 
     * @return listValCashCalcRule.
     */
    public List<FinCashCalcRule> getListValCashCalcRule() {
        return listValCashCalcRule;
    }

    /**
     * 设置 listValCashCalcRule
     * 
     * @param listValCashCalcRule listValCashCalcRule
     */
    public void setListValCashCalcRule(List<FinCashCalcRule> listValCashCalcRule) {
        this.listValCashCalcRule = listValCashCalcRule;
    }

    /**
     * 获取 listValCashPayRule
     * 
     * @return listValCashPayRule.
     */
    public List<FinCashPayRule> getListValCashPayRule() {
        return listValCashPayRule;
    }

    /**
     * 设置 listValCashPayRule
     * 
     * @param listValCashPayRule listValCashPayRule
     */
    public void setListValCashPayRule(List<FinCashPayRule> listValCashPayRule) {
        this.listValCashPayRule = listValCashPayRule;
    }

	/**
	 * 获取 listValCashPlan
	 * @return listValCashPlan.
	 */
	public List<FinCashPlan> getListValCashPlan() {
		return listValCashPlan;
	}

	/**
	 * 设置 listValCashPlan
	 * @param listValCashPlan listValCashPlan
	 */
	public void setListValCashPlan(List<FinCashPlan> listValCashPlan) {
		this.listValCashPlan = listValCashPlan;
	}

	/**
	 * 获取 listFinFloatAccrualSche
	 * @return listFinFloatAccrualSche.
	 */
	public List<FinFloatAccrualSche> getListFinFloatAccrualSche() {
		return listFinFloatAccrualSche;
	}

	/**
	 * 设置 listFinFloatAccrualSche
	 * @param listFinFloatAccrualSche listFinFloatAccrualSche
	 */
	public void setListFinFloatAccrualSche(List<FinFloatAccrualSche> listFinFloatAccrualSche) {
		this.listFinFloatAccrualSche = listFinFloatAccrualSche;
	}

	/**
	 * 获取 listValOpenPlan
	 * @return listValOpenPlan.
	 */
	public List<FinOpenPlan> getListValOpenPlan() {
		return listValOpenPlan;
	}

	/**
	 * 设置 listValOpenPlan
	 * @param listValOpenPlan listValOpenPlan
	 */
	public void setListValOpenPlan(List<FinOpenPlan> listValOpenPlan) {
		this.listValOpenPlan = listValOpenPlan;
	}
	//huwei 2018.10.09
		/**
		 * 获取 listValFloatCalcRule
		 * 
		 * @return listValFloatCalcRule.
		 */
		public List<FinFloatCalcRule> getListValFloatCalcRule() {
			return listValFloatCalcRule;
		}

		/**
		 * 设置 listValFloatCalcRule
		 * 
		 * @param listValFloatCalcRule
		 *            listValFloatCalcRule
		 */
		public void setListValFloatCalcRule(List<FinFloatCalcRule> listValFloatCalcRule) {
			this.listValFloatCalcRule = listValFloatCalcRule;
		}

		/**
		 * 获取 listValFloatCountingRule
		 * 
		 * @return listValFloatCountingRule.
		 */
		public List<FinFloatCountingRule> getListValFloatCountingRule() {
			return listValFloatCountingRule;
		}

		/**
		 * 设置 listValFloatCountingRule
		 * 
		 * @param listValFloatCountingRule
		 *            listValFloatCountingRule
		 */
		public void setListValFloatCountingRule(List<FinFloatCountingRule> listValFloatCountingRule) {
			this.listValFloatCountingRule = listValFloatCountingRule;
		}
		/**
		 * 获取 listValFloatCalcRuleDetail
		 * 
		 * @return listValFloatCalcRuleDetail.
		 */
		public List<FinFloatCalcRuleDetail> getListValFloatCalcRuleDetail() {
			return listValFloatCalcRuleDetail;
		}

		/**
		 * 设置 listValFloatCalcRuleDetail
		 * 
		 * @param listValFloatCalcRuleDetail
		 *            listValFloatCalcRuleDetail
		 */
		public void setListValFloatCalcRuleDetail(List<FinFloatCalcRuleDetail> listValFloatCalcRuleDetail) {
			this.listValFloatCalcRuleDetail = listValFloatCalcRuleDetail;
		}
		//huwei 2018.10.09
		
		  /**
	     * 获取 listFinTradeProduct
	     * @return listFinTradeProduct.
	     */
	    public List<FinTradeProduct> getListFinTradeProduct() {//add by dongjun on 20181019 start
	        return listFinTradeProduct;
	    }

	    
	    /**
	     * 设置 listFinTradeProduct
	     * @param listFinTradeProduct listFinTradeProduct
	     */
	    public void setListFinTradeProduct(List<FinTradeProduct> listFinTradeProduct) {
	        this.listFinTradeProduct = listFinTradeProduct;
	    }//add by dongjun on 20181019 end


        /**
         * @return the listValPrdInfoY
         */
        public List<ValPrdBaseInfo> getListValPrdInfoY() {
            return listValPrdInfoY;
        }

        /**
         * @param listValPrdInfoY the listValPrdInfoY to set
         */
        public void setListValPrdInfoY(List<ValPrdBaseInfo> listValPrdInfoY) {
            this.listValPrdInfoY = listValPrdInfoY;
        }
}
