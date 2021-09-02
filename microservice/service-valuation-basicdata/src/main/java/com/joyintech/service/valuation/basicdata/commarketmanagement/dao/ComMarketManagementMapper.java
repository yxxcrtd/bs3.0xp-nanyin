package com.joyintech.service.valuation.basicdata.commarketmanagement.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.valuation.basicdata.commarketmanagement.dto.ComMarketManageDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository 
public interface ComMarketManagementMapper {

    List<ComMarketManageDto> queryForPage(Map<String, Object> pageParam);

 
	List<SuggestDto> selectFinprodName(Map<String, Object> param);

 
    List<SelectOptionDto> getGradeResult(Map<String, Object> param);

    /**
     * 根据资产代码获取债券
     * @param pageParam
     * @return
     */
    List<ComMarketManageDto> getFinprodLists(Map<String, Object> pageParam);

    /**
     * 根据评级结构获取机构
     * @param pageParam
     * @return
     */
    List<ComMarketManageDto> getCustomerLists(Map<String, Object> pageParam);

    /**
     * 根据list删除数据
     * @param list
     * @return
     */
    int deleteBatch(@Param("list")List<ComMarketManageDto> list);

    /**
     * 批量插入数据
     * @param list
     * @return
     */
    int insertBatch(@Param("list")List<ComMarketManageDto> list);
	 

	 


	 


	 

}
