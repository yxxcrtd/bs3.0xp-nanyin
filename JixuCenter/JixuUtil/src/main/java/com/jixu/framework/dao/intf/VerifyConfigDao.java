package com.jixu.framework.dao.intf;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.jixu.common.pojo.VerifyConfig;

/**
 * @ClassName 		VerifyConfigDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface VerifyConfigDao extends CommFuncDAO{
	
	@CacheEvict(value="verifyDataCache", key="'groupID'+#groupID")
	public void deleteVerifyConfig(@Param("groupID")int groupID);
	
	public VerifyConfig loadVerifyConfigByHeader(@Param("msgheader")String msgheader);
	
	@Cacheable(value="verifyDataCache", key="'id'+#id")
	public VerifyConfig loadVerifyConfigDetail(@Param("id")int id);
	
	@Cacheable(value="verifyDataCache", key="'groupID'+#groupid")
	public List<VerifyConfig> loadVerifyConfig(@Param("groupid")int groupid);

}
