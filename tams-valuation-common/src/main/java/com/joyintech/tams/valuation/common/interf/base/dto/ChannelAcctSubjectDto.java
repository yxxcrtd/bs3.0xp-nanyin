package com.joyintech.tams.valuation.common.interf.base.dto;

import java.util.List;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.common.base.entity.BokAcctLayeringInfo;
import com.joyintech.tams.common.base.entity.BokAcctSubjectInfo;
import com.joyintech.tams.common.base.entity.BokBookset;
import com.joyintech.tams.common.base.entity.BokBooksetCapacctInfo;
import com.joyintech.tams.common.base.entity.BokBooksetDate;
import com.joyintech.tams.common.base.entity.BokBooksetExt;
import com.joyintech.tams.common.base.entity.BokBooksetPlan;
import com.joyintech.tams.common.base.entity.BokBooksetRe;
import com.joyintech.tams.common.base.entity.BokDetailId;
import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductAdd;
import com.joyintech.tams.common.base.entity.FinProductValuationPara;
import com.joyintech.tams.common.base.entity.JJBkCashAcct;
import com.joyintech.tams.common.base.entity.JJPortfolio;
import com.joyintech.tams.common.base.entity.StlFinProductAcct;
import com.joyintech.tams.common.base.entity.FinProductType;

/**
 * 通道核算信息DTO
 */
public class ChannelAcctSubjectDto extends BaseEntity{


	private static final long serialVersionUID = 1L;

	/**
	 * 	标的金融产品
	 */
	private  FinProduct finProduct;
	/**
	 * 	标的金融产品扩展
	 */
	private  FinProductAdd finProductExt;
	/**
	 * 	标的类金融产品类型
	 */
	private  FinProductType FinProductType;
	
	/**
	 * 	母子账套关系
	 */
	private  BokBooksetRe bokBooksetRe;
	/**
	 * 	账套日期
	 */
	private  BokBooksetDate bokBooksetDate;
	
	/**
	 * 账套配置List
	 */
	private List<BokBooksetExt> listBokBooksetExt;
	
	/**
	 * 	核算主体信息
	 */
	private  BokAcctSubjectInfo bokAcctSubjectInfo;
	/**
	 * 	核算分层信息
	 */

	private  BokAcctLayeringInfo bokAcctLayeringInfo;
	/**
	 * 	账套估值方案List
	 */
	private  List<BokBooksetPlan> bokBooksetPlanList;
	/**
	 * 	账务明细代码List
	 */
	private  List<BokDetailId> bokDetailIdList;
	/**
	 * 	子账套通道现金流信息List
	 */
	private  List<FinCashInfo> finCashInfoList;
	/**
	 * 	子账套通道现金流计算规则List
	 */
	private  List<FinCashCalcRule> finCashCalcRuleList;
	/**
	 * 	金融产品账户List
	 */
	private  List<StlFinProductAcct> stlFinProductAcctList;
	/**
	 * 	账套清算账户表List
	 */
	private  List<BokBooksetCapacctInfo> bokBooksetCapacctInfoList;
	
	/**
	 * 几许产品信息
	 * add by zhangquan 20200103
	 */
	private JJPortfolio jJPortfolio;
	
	/**
	 * 几许产品资金账户信息List
	 * add by zhangquan 20200103
	 */
	private List<JJBkCashAcct> jJBkCashAcctList;
	
	
	/**
	 * 处理结果
	 */
	private  String resultCode;
	/**
	 * 处理结果描述
	 */
	private  String resultMsg;
	
	private FinProductValuationPara finProductValuationPara;
	
	private BokBookset bokBookset;

	public FinProduct getFinProduct() {
		return finProduct;
	}

	public void setFinProduct(FinProduct finProduct) {
		this.finProduct = finProduct;
	}

	public FinProductAdd getFinProductAdd() {
		return finProductExt;
	}

	public void setFinProductAdd(FinProductAdd finProductExt) {
		this.finProductExt = finProductExt;
	}

	public FinProductType getFinProductType() {
		return FinProductType;
	}

	public void setFinProductType(FinProductType finProductType) {
		FinProductType = finProductType;
	}

	public BokBooksetRe getBokBooksetRe() {
		return bokBooksetRe;
	}

	public void setBokBooksetRe(BokBooksetRe bokBooksetRe) {
		this.bokBooksetRe = bokBooksetRe;
	}

	public BokBooksetDate getBokBooksetDate() {
		return bokBooksetDate;
	}

	public void setBokBooksetDate(BokBooksetDate bokBooksetDate) {
		this.bokBooksetDate = bokBooksetDate;
	}

	public BokAcctSubjectInfo getBokAcctSubjectInfo() {
		return bokAcctSubjectInfo;
	}

	public void setBokAcctSubjectInfo(BokAcctSubjectInfo bokAcctSubjectInfo) {
		this.bokAcctSubjectInfo = bokAcctSubjectInfo;
	}

	public BokAcctLayeringInfo getBokAcctLayeringInfo() {
		return bokAcctLayeringInfo;
	}

	public void setBokAcctLayeringInfo(BokAcctLayeringInfo bokAcctLayeringInfo) {
		this.bokAcctLayeringInfo = bokAcctLayeringInfo;
	}

	public List<BokBooksetPlan> getBokBooksetPlanList() {
		return bokBooksetPlanList;
	}

	public void setBokBooksetPlanList(List<BokBooksetPlan> bokBooksetPlanList) {
		this.bokBooksetPlanList = bokBooksetPlanList;
	}

	public List<BokDetailId> getBokDetailIdList() {
		return bokDetailIdList;
	}

	public void setBokDetailIdList(List<BokDetailId> bokDetailIdList) {
		this.bokDetailIdList = bokDetailIdList;
	}

	public List<FinCashInfo> getFinCashInfoList() {
		return finCashInfoList;
	}

	public void setFinCashInfoList(List<FinCashInfo> finCashInfoList) {
		this.finCashInfoList = finCashInfoList;
	}

	public List<FinCashCalcRule> getFinCashCalcRuleList() {
		return finCashCalcRuleList;
	}

	public void setFinCashCalcRuleList(List<FinCashCalcRule> finCashCalcRuleList) {
		this.finCashCalcRuleList = finCashCalcRuleList;
	}

	public List<StlFinProductAcct> getStlFinProductAcctList() {
		return stlFinProductAcctList;
	}

	public void setStlFinProductAcctList(
			List<StlFinProductAcct> stlFinProductAcctList) {
		this.stlFinProductAcctList = stlFinProductAcctList;
	}

	public List<BokBooksetCapacctInfo> getBokBooksetCapacctInfoList() {
		return bokBooksetCapacctInfoList;
	}

	public void setBokBooksetCapacctInfoList(
			List<BokBooksetCapacctInfo> bokBooksetCapacctInfoList) {
		this.bokBooksetCapacctInfoList = bokBooksetCapacctInfoList;
	}

	
	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public FinProductAdd getFinProductExt() {
		return finProductExt;
	}

	public void setFinProductExt(FinProductAdd finProductExt) {
		this.finProductExt = finProductExt;
	}

	public List<BokBooksetExt> getListBokBooksetExt() {
		return listBokBooksetExt;
	}

	public void setListBokBooksetExt(List<BokBooksetExt> listBokBooksetExt) {
		this.listBokBooksetExt = listBokBooksetExt;
	}

	public FinProductValuationPara getFinProductValuationPara() {
		return finProductValuationPara;
	}

	public void setFinProductValuationPara(FinProductValuationPara finProductValuationPara) {
		this.finProductValuationPara = finProductValuationPara;
	}

	public JJPortfolio getjJPortfolio() {
		return jJPortfolio;
	}

	public void setjJPortfolio(JJPortfolio jJPortfolio) {
		this.jJPortfolio = jJPortfolio;
	}

	public List<JJBkCashAcct> getjJBkCashAcctList() {
		return jJBkCashAcctList;
	}

	public void setjJBkCashAcctList(List<JJBkCashAcct> jJBkCashAcctList) {
		this.jJBkCashAcctList = jJBkCashAcctList;
	}

	public BokBookset getBokBookset() {
		return bokBookset;
	}

	public void setBokBookset(BokBookset bokBookset) {
		this.bokBookset = bokBookset;
	}
	
	
	
}
