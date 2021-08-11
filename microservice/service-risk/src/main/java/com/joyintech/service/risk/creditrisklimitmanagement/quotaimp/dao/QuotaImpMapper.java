package com.joyintech.service.risk.creditrisklimitmanagement.quotaimp.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.risk.creditrisklimitmanagement.quotaimp.dto.QuotaImpDto;

@Repository
public interface QuotaImpMapper {
	
	List<QuotaImpDto> queryForPage(Map<String, Object> pageParam);
	
	/*根据授信类型名称查询授信标签Id*/
	String queryTagIdByCreditTypeName(@Param("creditTypeName")String creditTypeName);
	
	/*根据标签维度查询授信分项的Id*/
	String queryConfIdByTypeAndTerm(@Param("tagId")String tagId,@Param("term")BigDecimal term,@Param("termUnit")String termUnit);
	
	/*根据信用代码查询机构代码*/
	List<String> queryCustomerIdByCode(@Param("insitituteCode")String insitituteCode);
	
	/*根据导入类型获取批次号*/
	String queryPflshByImpType(@Param("impType")String impType);
}
