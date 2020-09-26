package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.secbl.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.BokDealAccounting;
import com.joyintech.tams.common.base.entity.BokDealAccountingDetail;
import com.joyintech.tams.common.base.entity.BokDetailId;
import com.joyintech.tams.common.base.entity.FinDealTarget;
import com.joyintech.tams.common.base.entity.StlOrders;
import com.joyintech.tams.common.base.entity.StlOrdersExt;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;
import com.joyintech.tams.common.base.entity.ValInterflog;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.valuation.common.interf.base.dto.AssetDealInfoValCommonResult;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.sec.dto.SecDealInfoProcessorResult;

/**
 * 债券借贷交易信息Processor返回共通结果
 * 
 * @author dongxiangyang
 * @version 1.0 2018年10月26日
 * @see SecDealInfoProcessorResult
 * @since 1.0
 */
public class SecblDealInfoProcessorResult extends BaseEntity{

	  private static final long serialVersionUID = 1L;
	  /**
	     * 金融产品交易
	     */
	    private List<TrdProductDeal> listValFinProductDeal = new ArrayList<TrdProductDeal>();
	    
	    /**
	     * 估值核算交易扩展表
	     */
	    private List<TrdProductDealAdd> listValFinProductDealExt = new ArrayList<TrdProductDealAdd>();
	    
	    /**
	     * 金融产品标的信息
	     */
	    private List<FinDealTarget> listFinDealTarget=new ArrayList<FinDealTarget>();
	    /**
	     * 交易费用明细表
	     */
	    private List<TrdTradeFeeDetail> listValTradeFeeDetail = new ArrayList<TrdTradeFeeDetail>();
	    /**
	     * 入账信息表
	     */
	    private List<BokDealAccounting> listValDealAccounting = new ArrayList<BokDealAccounting>();
	    /**
	     * 账务明细表
	     */
	    private List<BokDealAccountingDetail> listValDealAccountingDetail = new ArrayList<BokDealAccountingDetail>();
	    /**
	     * 账务明细代码表 add by wujie on 20180712
	     */
	    private List<BokDetailId> listValBokDetailId = new ArrayList<BokDetailId>();
	    /**
	     * 日志
	     */
	    private List<ValInterflog> valInterflog = new ArrayList<ValInterflog>();
	    /**
	     * 结算指令表
	     */
	   private List<StlOrders> valOrdersList = new ArrayList<StlOrders>();
	    /**
	     * 结算指令拓展表
	     */
	   private List<StlOrdersExt> valOrdersExtList = new ArrayList<StlOrdersExt>();
	    /**
	     * 读取数据数量
	     */
	    private int dataNum;
	    /**
	     * 用于存放去重数据筛选条件
	     */
	    private Map<String,Object> paramMap;
	    
	    
	    public Map<String, Object> getParamMap() {
			return paramMap;
		}

		public void setParamMap(Map<String, Object> paramMap) {
			this.paramMap = paramMap;
		}

		public SecblDealInfoProcessorResult(){
	    	
	    }
	    
	    /**
	     * 重载构造方法 从valuation-common中返回的result重新赋值
	     * 
	     * @param rtnResult 共通结果
	     */
       public SecblDealInfoProcessorResult(AssetDealInfoValCommonResult result){
    	   this.setListValDealAccounting(result.getListValDealAccounting());
           this.setListValDealAccountingDetail(result.getListValDealAccountingDetail());
           this.setListValFinProductDeal(result.getListValFinProductDeal());
           this.setListValFinProductDealExt(result.getListValFinProductDealExt());
           this.setValInterflog(result.getValInterflog());
           /***add by wujie on 20180712 start*/
           this.setListValBokDetailId(result.getListValBokDetailId());
           /***add by wujie on 20180712 end*/
           this.setDataNum(result.getDataNum());
           this.setValOrdersList(result.getListValOrders());
           this.setValOrdersExtList(result.getListValOrdersExt());
           this.setListValTradeFeeDetail(result.getListValTradeFeeDetail());
           this.paramMap = result.getParamMap();
	    }

		public List<TrdProductDeal> getListValFinProductDeal() {
			return listValFinProductDeal;
		}

		public void setListValFinProductDeal(List<TrdProductDeal> listValFinProductDeal) {
			this.listValFinProductDeal = listValFinProductDeal;
		}

		public List<TrdProductDealAdd> getListValFinProductDealExt() {
			return listValFinProductDealExt;
		}

		public void setListValFinProductDealExt(List<TrdProductDealAdd> listValFinProductDealExt) {
			this.listValFinProductDealExt = listValFinProductDealExt;
		}

		public List<FinDealTarget> getListFinDealTarget() {
			return listFinDealTarget;
		}

		public void setListFinDealTarget(List<FinDealTarget> listFinDealTarget) {
			this.listFinDealTarget = listFinDealTarget;
		}

		public List<BokDealAccounting> getListValDealAccounting() {
			return listValDealAccounting;
		}

		public void setListValDealAccounting(List<BokDealAccounting> listValDealAccounting) {
			this.listValDealAccounting = listValDealAccounting;
		}

		public List<BokDealAccountingDetail> getListValDealAccountingDetail() {
			return listValDealAccountingDetail;
		}

		public void setListValDealAccountingDetail(List<BokDealAccountingDetail> listValDealAccountingDetail) {
			this.listValDealAccountingDetail = listValDealAccountingDetail;
		}

		public List<BokDetailId> getListValBokDetailId() {
			return listValBokDetailId;
		}

		public void setListValBokDetailId(List<BokDetailId> listValBokDetailId) {
			this.listValBokDetailId = listValBokDetailId;
		}

		public List<ValInterflog> getValInterflog() {
			return valInterflog;
		}

		public void setValInterflog(List<ValInterflog> valInterflog) {
			this.valInterflog = valInterflog;
		}

		public int getDataNum() {
			return dataNum;
		}

		public void setDataNum(int dataNum) {
			this.dataNum = dataNum;
		}

		public List<StlOrders> getValOrdersList() {
			return valOrdersList;
		}

		public void setValOrdersList(List<StlOrders> valOrdersList) {
			this.valOrdersList = valOrdersList;
		}

		public List<StlOrdersExt> getValOrdersExtList() {
			return valOrdersExtList;
		}

		public void setValOrdersExtList(List<StlOrdersExt> valOrdersExtList) {
			this.valOrdersExtList = valOrdersExtList;
		}

		public List<TrdTradeFeeDetail> getListValTradeFeeDetail() {
			return listValTradeFeeDetail;
		}

		public void setListValTradeFeeDetail(List<TrdTradeFeeDetail> listValTradeFeeDetail) {
			this.listValTradeFeeDetail = listValTradeFeeDetail;
		} 
       
       
       
}
