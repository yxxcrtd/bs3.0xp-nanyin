package com.joyintech.service.settle.ordernewbuild.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.settle.ordernewbuild.dto.OrderNewBuildDto;


/**
 * @author yaozhenguo
 * @version 1.0 2019年10月29日 下午5:14:33
 * @ClassName OrderNewBuildMapper
 * @Description
 */
@Repository
public interface OrderNewBuildMapper {
	int deleteByPrimaryKey(String actOrderId);

	int insert(OrderNewBuildDto record);

	int insertSelective(OrderNewBuildDto record);

	int insertBasicByBasicInfo(OrderNewBuildDto record);

	OrderNewBuildDto selectByPrimaryKey(String actOrderId);

	int updateByPrimaryKeySelective(OrderNewBuildDto record);

	int updateByPrimaryKey(OrderNewBuildDto record);
	
	List<SelectOptionDto> queryCapacctInfo(Map<String, Object> map);
	
	List<OrderNewBuildDto> selectForPage(Map<String, Object> param);
	
	void orderRevoke(OrderNewBuildDto orderNewBuildDto);
	
}
