package com.jixu.mvc.control.service;

import java.util.Date;
import java.util.List;

import com.github.pagehelper.Page;
import com.jixu.common.type.CashPositionModel;
import com.jixu.common.types.NavAssetObj;
import com.jixu.common.util.Pages;
import com.jixu.mvc.control.model.SecPositionModel;
import com.orm.obj.BkCashFlow;
import com.orm.obj.BkCashRcpy;
import com.orm.obj.Nav;

public interface SecCashService {
	public Pages<BkCashFlow> getCashFlow(Pages<BkCashFlow> pages, String portNum, Date startDate, Date endDate);
	
	public Pages<BkCashRcpy> getCashRcpy(Pages<BkCashRcpy> pages,String portNum, Date startDate, Date endDate);
	
	//PageHelper查询分页
	public Page<BkCashRcpy> getPageHelperCashRcpy(Page<BkCashRcpy> page,String portNum, Date startDate, Date endDate);
	
	public List<CashPositionModel> getCashPosition(String portNum, Date endDate);
	
	/**
	 * 获取当天可以使用的银行现金，包括当天即将结转的应收应付
	 * @param portNum
	 * @param asOfDate
	 * @return
	 */
	public List<CashPositionModel> getUseageCashPosition(String portNum, java.sql.Date asOfDate);
	
	/**
	 * 
	 * @param portNum
	 * @param endDate	yyyy-mm-dd 格式
	 * @return
	 */
	public Pages<SecPositionModel> getSecPositions(Pages<SecPositionModel> pages,String portNum, Date endDate);
	
	public Pages<NavAssetObj> getNavObjList(Pages<NavAssetObj> pages,String portnum, Date endDate);
	
	public Page<NavAssetObj> getPageHelperNavList(Page<NavAssetObj> page,String portnum, Date endDate);
	
	public BkCashRcpy loadCashRcpy(int rcpyNo);
	
	public String loadFxCurrCode(String fxCurNum);
	
	public List<Nav> getNavDetail(String portnum, Date navDate);
	
	public List<List<String>> loadTranHisSummary(String portnum, String secID);
	
	/**
	 * 获取现金流交易的基本信息
	 * @param portnum
	 * @param cashacctnum
	 * @return
	 */
	public List<List<String>> loadCashflowHisSummary(String portnum, int cashacctnum);
	
	public List<SecPositionModel> getSecPosition(String portNum, String secid, Date endDate);
	
	/**
	 * 根据cloid或者当天和前一天的头寸
	 * @param tranCloID
	 * @return
	 */
	public List<SecPositionModel> getSecPositionByCloID(String tranCloID);
	
	/**
	 * 获取证券头寸
	 * @param portnum
	 * @param endDate
	 * @return
	 */
	public List<SecPositionModel> getSecPositions(String portnum, Date endDate);
	
	
	/**
	 * 债券摊销计划
	 * @param secID
	 * @param portID
	 * @param endDate
	 * @return
	 */
	public List<Object> positionAmortDetail(String secID,String portID,Date endDate);
}
