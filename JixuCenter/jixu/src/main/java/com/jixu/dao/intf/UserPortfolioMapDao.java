package com.jixu.dao.intf;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.UserPortfolioMap;

/**
 * @ClassName 		UserPortfolioMapDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface UserPortfolioMapDao extends CommFuncDAO{
	
	@CacheEvict(value="userPortfolioMapCache", key="#userID")
	public void deleteUserPortMap(@Param("userID")int userID);
	
	@CacheEvict(value="userPortfolioMapCache", key="#userPortfolioMap.getId()")
	public void createUserPortMap(@Param("userPortfolioMap")UserPortfolioMap userPortfolioMap);
	
	@Cacheable(value="userPortfolioMapCache", key="#userID")
	public List<UserPortfolioMap> loadUserPortMapListByUserID(@Param("userID")int userID);

}
