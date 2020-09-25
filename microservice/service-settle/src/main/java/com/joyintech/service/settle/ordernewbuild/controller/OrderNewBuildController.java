package com.joyintech.service.settle.ordernewbuild.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.settle.ordernewbuild.dto.OrderNewBuildDto;
import com.joyintech.service.settle.ordernewbuild.service.OrderNewBuildService;

/**
* @author yaozhenguo
* @version 1.0 2019年10月29日 下午5:14:08
* @ClassName OrderNewBuildController
* @Description 指令新建
*/
@RestController("backOrderNewBuildController_dtx")
@RequestMapping("/orderNewBuild")
public class OrderNewBuildController extends BaseController{
	
	@Autowired
	OrderNewBuildService orderNewBuildService;
	
	/**
	 * 查询所有清算账户
	 * @param map
	 * @return
	 */
	@PostMapping("/queryCapacctInfo")
	public List<SelectOptionDto> queryCapacctInfo(@RequestBody Map<String , Object> map){
		List<SelectOptionDto> capacctInfos = orderNewBuildService.queryCapacctInfo(map);
		return capacctInfos;
	}
	
	/**
	 * 页面查询
	 * @param param
	 * @param page
	 * @param length
	 * @return
	 */
	@PostMapping("/selectForPage/{page}/{length}")
	public Page<OrderNewBuildDto> selectForPage(@RequestBody Map<String, Object> param,@PathVariable("page") int page,@PathVariable("length") int length){
		Page<OrderNewBuildDto> orders =  orderNewBuildService.selectForPage(param, page, length);
		return orders;
	}
	
	/**
	 * 新建插入
	 * @param orderNewBuildDto
	 */
	@DTXTransaction
	@Transactional
	@PostMapping("insertOrder")
	public void insertOrder(@RequestBody OrderNewBuildDto orderNewBuildDto) {
		orderNewBuildService.insertOrder(orderNewBuildDto);
	}
	
	/**
	 * 详情页查看
	 * @param orderId
	 * @return
	 */
	@GetMapping("selectByOrderId")
	public OrderNewBuildDto selectByOrderId(String orderId) {
		return orderNewBuildService.selectByOrderId(orderId);
	}
	
	/**
	 * 指令撤销
	 * @param orderNewBuildDto
	 */
	@DTXTransaction
	@Transactional
	@PostMapping("orderRevoke")
	public void orderRevoke(@RequestBody OrderNewBuildDto orderNewBuildDto ) {
		orderNewBuildService.orderRevoke(orderNewBuildDto);
	}
}
