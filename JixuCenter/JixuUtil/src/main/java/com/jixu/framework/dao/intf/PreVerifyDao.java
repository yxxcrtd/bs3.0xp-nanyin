package com.jixu.framework.dao.intf;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;

import com.jixu.common.pojo.PreVerify;

/**
 * @ClassName 		PreVerifyDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface PreVerifyDao extends CommFuncDAO{
	
	public List<PreVerify> loadPreVerifyBySubmitterID(@Param("portfolios")String[] portfolios, @Param("submitterID")int submitterID, @Param("start")Date start, @Param("end")Date end);
	
	public List<PreVerify> loadPreVerifyByAll(@Param("portfolios")String[] portfolios, @Param("userid")int userid, @Param("start")Date start, @Param("end")Date end);
	
	public PreVerify loadPreVerify(@Param("id")int id);
	
	@Cacheable(value="verifyDataCache", key="'verifyconfigID'+#id")
	public List<PreVerify> loadPreVerifyByVConfigID(@Param("id")int id);

}
