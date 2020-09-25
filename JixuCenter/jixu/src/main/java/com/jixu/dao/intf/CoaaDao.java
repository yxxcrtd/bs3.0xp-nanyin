package com.jixu.dao.intf;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.Coaa;

/**
 * @ClassName 		CoaaDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface CoaaDao extends CommFuncDAO{
	public List<Coaa> getLevel1Coaa(@Param("portnum")String portnum);
	
	@CacheEvict(value="coaDataCache", key="#coa.getChartnum().getId()+'_'+#coa.getAccntnum()")
	public void saveCOA(@Param("coa")Coaa coa);
	
	@CacheEvict(value="coaDataCache", key="#coa.getChartnum().getId()+'_'+#coa.getAccntnum()")
	public void updateCOA(@Param("coa")Coaa coa);
	
	public List<Coaa> loadCoaParent(@Param("coaahdr")String coaahdr, @Param("acctNum") String acctNum);
	
	/**
	 * 获取会计科目表明细最末级
	 * @param chartnum	会计科目表编号
	 * @return
	 */
	public List<Coaa> loadCoaaDetailsByChartNum(@Param("chartnum")String chartnum);
	
	public List<Coaa> loadCoaaDetailsAllByChartNum(@Param("chartnum")String chartnum);
	
	public List<Coaa> loadCoaaDetailsByPortnum(@Param("portnum")String portnum);
	
	@Cacheable(value="coaDataCache", key="#chartnum+'_'+#accntnum")
	public Coaa loadCoa(@Param("chartnum")String chartnum, @Param("accntnum") String accntnum);
	
	public Coaa loadCoaByChartAndAcctname(String chartnum, String accntname);
}
