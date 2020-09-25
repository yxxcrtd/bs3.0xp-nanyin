package com.joyintech.service.settle.ordernewbuild.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.settle.ordernewbuild.dao.OrderNewBuildMapper;
import com.joyintech.service.settle.ordernewbuild.dto.OrderNewBuildDto;

/**
* @author yaozhenguo
* @version 1.0 2019年10月29日 下午5:15:19
* @ClassName OrderNewBuildService
* @Description 指令新建 
*/
@Service
public class OrderNewBuildService extends BaseService<OrderNewBuildDto>{

	@Autowired
	private OrderNewBuildMapper orderNewBuildMapper;
	
	/**
	 * 查询所有清算账户
	 * @param map
	 * @return
	 */
	public List<SelectOptionDto> queryCapacctInfo(Map<String, Object> map){
		return orderNewBuildMapper.queryCapacctInfo(map);
	}
	
	/**
	 * 页面查询
	 * @param param
	 * @param page
	 * @param length
	 * @return
	 */
	public Page<OrderNewBuildDto> selectForPage(Map<String, Object> param,int page,int length){
		return queryForPage(page, length, () -> orderNewBuildMapper.selectForPage(param));
	}
	
	/**
	 * 新建插入
	 * @param orderNewBuildDto
	 */
	public void insertOrder(OrderNewBuildDto orderNewBuildDto) {
		orderNewBuildMapper.insertSelective(orderNewBuildDto);
	}
	
	/**
	 * 根据指令Id查询
	 * @param orderId
	 * @return
	 */
	public OrderNewBuildDto selectByOrderId(String orderId) {
		return orderNewBuildMapper.selectByPrimaryKey(orderId);
	}
	
	/**
	 * 撤销
	 * @param orderNewBuildDto
	 */
	public void orderRevoke(OrderNewBuildDto orderNewBuildDto) {
		orderNewBuildMapper.orderRevoke(orderNewBuildDto);
	}
	
}
