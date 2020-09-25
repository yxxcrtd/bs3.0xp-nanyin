package com.jixu.mvc.control.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.type.ResultContainer;
import com.jixu.mvc.control.model.PortfolioSummary;
import com.orm.obj.PerformanceFeeCfg;
import com.orm.obj.PortExpenseCfg;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.PortfolioInterfaceMap;
import com.orm.obj.TranBasic;

/**
 * 
 * @author Pan Wei
 *
 */
public interface PortfolioCfgService {
	
	/**
	 * 获取投资组合列表
	 * @param clientCode	客户id
	 * @param portType		类型id，0为全部
	 * @param portName		投资组合名称
	 * @return
	 */
	public List<PortfolioSummary> getPortfolioSummary(int userid, int clientCode, int portType, String portName);
	
	public List<PortfolioSummary> getPortfolioSummary(int clientCode, int portType, String portName);

	/**
	 * 获取投资组合的开支列表
	 * @param portnum
	 * @return
	 */
	public List<PortExpenseCfg> loadExpenseListByPortnum(String portnum);
	
	/**
	 * 创建投资组合
	 * @param paramsList 创建投资组合的配置参数
	 * @return	返回是否创建成功，如不成功，返回错误信息
	 */
	public ResultContainer createPortfolio(List<Object> paramsList);
	
	/**
	 * 更新投资组合信息
	 * @param portfolio
	 */
	public void updatePortfolio(PortfolioHdr portfolio);
	
	/**
	 * 创建投资组合的税费配置
	 * @param portnum	投资组合编号
	 * @param param_list	税费配置信息
	 * @return
	 */
	public ResultContainer savePortExpense(String portnum, List<Object> param_list);
	
	/**
	 * 删除该费用记录
	 * @param expenseID
	 */
	public void deletePortExpense(int expenseID);
	
	public PortExpenseCfg loadPortExpenseCfgByID(int expenseID);
	
	/**
	 * 更新投资组合税费配置
	 * @param portID	投资组合编号
	 * @param expID		税费编号
	 * @param param_list	税费配置
	 */
	public void updatePortExpense(String portID, int expID, List<Object> param_list);
	
	public List<PortfolioInterfaceMap> loadPortInterfaceMapImport(String portID);
	
	public List<PortfolioInterfaceMap> loadPortInterfaceMapExport(String portID);
	
	public void deletePortfolioInterfaceMap(int sourceID);
	
	public HashMap<Integer,String> loadManagersByPortnum(String portnum);
	
	public List<String> loadPortfolioPosition(String portnum, String endDate);
	
	public List<PortfolioHdr> loadPortfolios(List<Object> ports);
	
	public PortfolioHdr loadPortfolio(String portnum);
	
	public String loadCountryIDByProvinceID(int provinceID);
	
	public PortfolioInterfaceMap createPortInterfaceMap(String portnum, List<Object> paramsList);
	
	public PortfolioInterfaceMap createPortInterfaceExpMap(String portnum, List<Object> paramsList);
	
	public void updatePortInterfaceExpMap(int mapID, List<Object> paramsList);
	
	public HashMap<Integer, String> loadInterfaceSourceByPortnum(String portnum);

	public List<InterfaceSource> loadInterfaceSource(String[] ports, int sourceType);
	
	/**
	 * 
	 * @param mapID 映射ID
	 * @param portnum	投资组合
	 * @param sourceType	价格,汇率等类型
	 * @param rpSecType		资产类型，期货,普通股等
	 * @param sourceID		接口ID
	 * @param importType	导入类型,自动还是手动
	 */
	public void updatePortInterfaceMap(int mapID, String portnum, int sourceType, int rpSecType, int sourceID, int importType);
	
	//创建投资组合和子投资组合的map关系
	public void createPortfolioClassIDMap(String classID,List<String> portIDList);
	
	//查询该投资组合客户下的所有投资组合
	public List<PortfolioHdr> loadPortfolios(String portID);
	
	public HashMap<Integer, String> loadTranFeeDef();
	
	/**
	 * 查询产品批次的信息
	 * @param portnum
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public HashMap<String, Object> getBankZiGuanInfo(String portnum, Date startDate, Date endDate); 
	
	/**
	 * 获取绩效费的配置
	 * @param portnum
	 * @param cusip
	 * @param lotnum
	 * @return
	 */
	public PerformanceFeeCfg loadPerformanceFeeCfg(String portnum, String cusip, int lotnum);
	
	// 递归返回该投资组合下的所有子组合    
	public List<String> loadPortfolioClassIDRecursion(@Param("classid")String classid);
	
	public void loadPortfolioCIDRecursion(String classid,List<String> totalList);
	
	//获取该投资组合下所有股票现金分红的数量和金额
	public Object[] getPortfolioStockCashDivident(TranBasic tranAdvice);
}
