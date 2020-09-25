package com.jixu.dao.intf;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.PortfolioHdr;

/**
 * @ClassName 		PortfolioHdrDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface PortfolioHdrDao extends CommFuncDAO{
	
	@Cacheable(value = "portDataCache", key = "'PortCode_'+#portCode")
	public PortfolioHdr loadPortfolioByPortCode(@Param("portCode")String portCode);
	
	/**
	 * 根据证券账户获取投资组合
	 * @param AcctID
	 * @return
	 */
	public PortfolioHdr loadPortByAcctID(@Param("AcctID")int AcctID);
	
	/**
	 * 根据公司编号获取下面的投资组合
	 * @param clientnum	公司编号
	 * @return	投资组合集
	 */
	public List<PortfolioHdr> getPortfoliosByClientNum(@Param("clientnum")int clientnum);
	
	public List<PortfolioHdr> getPortfoliosByClientAndUser(@Param("clientID")int clientID, @Param("userID") int userID);
	
	@CacheEvict(value="portDataCache", key="'PortNum_'+#portfolio.getId()")
	public void updatePortflio(@Param("portfolio")PortfolioHdr portfolio);
	
	public List<PortfolioHdr> getPortfoliosByUserID(@Param("userid")int userid, @Param("clientCode") int clientCode, @Param("portType") int portType, @Param("portName") String portName);
	
	/**
	 * 获取投资组合
	 * @param clientCode
	 * @param portType
	 * @param portName
	 * @return
	 */
	public List<PortfolioHdr> getPortfolios(@Param("clientCode")int clientCode, @Param("portType") int portType, @Param("portName") String portName);
	
	public List<PortfolioHdr> loadPortfoliosByIds(@Param("portnums")String[] portnums);
	
	public List<PortfolioHdr> loadPortfolios(@Param("portID")String portID);
	
	/**
	 * 获取投资组合实例
	 * @param portnum	投资组合编号
	 * @return	投资组合实例
	 */
	@Cacheable(value="portDataCache", key="'PortNum_'+#portfolio")
	public PortfolioHdr loadPortfolio(@Param("portnum")String portnum);
	
	/**
	 * 更新投资组合
	 * @param portfolio	投资组合实例
	 */
	@CacheEvict(value="portDataCache", key="'PortNum_'+#portfolio.getId()")
	public void updatePortfolio(@Param("portfolio")PortfolioHdr portfolio);
	
	/**
	 * 如果投资组合已存在,返回true。不存在,返回false
	 * @param portname
	 * @return
	 */
	public boolean checkSamePortNameExist(@Param("portname")String portname);
	
	// 查询该投资组合下子组合(包括该投资组合)
	@Cacheable(value="portDataCache", key="'classID'+#classid")
	public List<String> loadPortfolioClassIDMap(@Param("classid")String classid);
	
	
	@Cacheable(value="portDataCache", key="'classID'+#classid")
	public List<String> loadPortfolioCIDRecursion(@Param("classid")String classid);
	
	// 查询该投资组合下子组合(不包括该投资组合,主要用于subPorts.do方法的子组合查询)
	public List<PortfolioHdr> loadPortfolioByClassID(@Param("classid")String classid);
	
	//兆尹导入产品前   先删除有关该投资组合的表中的数据
	public void deletePortfolioMap(String portnum);
}
