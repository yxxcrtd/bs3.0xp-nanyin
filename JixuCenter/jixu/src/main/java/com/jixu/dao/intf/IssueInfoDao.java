package com.jixu.dao.intf;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import com.github.pagehelper.Page;
import com.jixu.common.util.Pages;
import com.jixu.framework.dao.intf.CommFuncDAO;
import com.jixu.joyin2jixu.dto.SecurityInfoDto;
import com.jixu.mvc.control.model.MarketStockModel;
import com.orm.obj.FinProductType;
import com.orm.obj.IssueInfo;

/**
 * @ClassName 		IssueInfoDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface IssueInfoDao extends CommFuncDAO{
	
	@Caching(evict={@CacheEvict(value="secDataCache", key="'cusip'+#sec.getId()"),@CacheEvict(value="secDataCache", key="'cusip'+#sec.getSecurityid()")})
	public void saveSecurity(IssueInfo sec);
	
	@CacheEvict(value="secDataCache", key="'cusip'+#issue.getId()")
	public void updateIssue(@Param("issue")IssueInfo issue);
	
	@CacheEvict(value="secDataCache", key="'cusip'+#issue.getId()")
	public void deleteIssue(@Param("issue")IssueInfo issue);
	
	@Cacheable(value = "issuerInfoCache", key = "#countryCode")
	public HashMap<String, IssueInfo> loadIssueInfo(@Param("countryCode")int countryCode);
	
	/**
	 * 获取swap的证券
	 * @param secid
	 * @return
	 */
	public IssueInfo loadSwapSecurity(@Param("secid")String secid);
	
	/**
	 * 获取证券实例
	 * @param secid	证券ID,类似600030
	 * @return	返回证券实例
	 */
	@CacheEvict(value="secDataCache", key="'secid'+#secid")
	public IssueInfo loadSecurity(@Param("secid")String secid);
	
	public List<String> loadSecIDNameList(@Param("sourceid")int sourceid, @Param("secType") String secType);
	
	public Pages<MarketStockModel> loadStockList(@Param("sourceids")int[] sourceids, @Param("secType")String secType, @Param("secID")String secID, @Param("pages") Pages<MarketStockModel> pages);
	
	public Page<MarketStockModel> loadPageHelperStockList(@Param("sourceids")int[] sourceids, @Param("secType")String secType, @Param("secID")String secID, @Param("page") Page<MarketStockModel> page);

	@Cacheable(value="secDataCache", key="'cusip'+#cusip")
	public IssueInfo loadIssueByCusip(@Param("cusip")String cusip);

	/**
	 * 获取基准指数证券
	 * @return
	 */
	public List<IssueInfo> loadBasePotinSec();
	
	//根据多空方货币查询即期smf
	public IssueInfo loadIssueInfoBuyCur(String assetType,String longCur,String shortCur);
	
	//根据证券代码查询FinProductType表
	public FinProductType loadFinProductType(String secid);
	
	/**
	 * 根据证券名称获取代码
	 * @param secname
	 * @return
	 */
	public IssueInfo loadIssueBySecurityname(String secname);
	
	/**
	 * 根据资产类型获取证券
	 * @param assetType
	 * @return
	 */
	public List<SecurityInfoDto> loadSecurityByAssetType(String assetType);
	
	/**
	 * 根据资产类型数组获取证券
	 * @param assetTypes
	 * @return
	 */
	public List<SecurityInfoDto> loadSecurityByAssetType(String[] assetTypes);
	
	public String getCusipBySecid(String secid);
}
