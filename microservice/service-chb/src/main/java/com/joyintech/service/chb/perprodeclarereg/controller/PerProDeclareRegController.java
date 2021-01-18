package com.joyintech.service.chb.perprodeclarereg.controller;


import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.svreport.chb.perprodeclarereg.dto.PerProDeclareRegDto;
import com.joyintech.tams.svreport.chb.perprodeclarereg.service.PerProDeclareReg001Service;

/**
 * 产品个人申报登记登服务
 * 〈功能详细描述〉
 * @author zhangzhongliang
 * @version 1.6.0 2019年04月24日
 * @see PerProDeclareRegController
 * @since 1.0
 */
@RestController
@RequestMapping("/PerProDeclareReg")
public class PerProDeclareRegController{
	@Autowired
	private PerProDeclareReg001Service perProDeclareReg001Service;

    /**
    *
    * 业务画面查询事件
    * <功能详细描述>
    * @return
    * @see
    * @since 1.0
    */
   @PostMapping("findPage/{page}/{length}")
   public Page<PerProDeclareRegDto> selectForPage(@RequestBody Map<String, Object> param) {
       Page<PerProDeclareRegDto> page = perProDeclareReg001Service.selectForPage(param);
       return page;
   }
   
   @PostMapping("selectAllList")
   public List<PerProDeclareRegDto> selectAllList(@RequestBody Map<String, Object> param) {
         return perProDeclareReg001Service.selectAllList(param);
   }
   
   
   
	@PostMapping("updateSumStaus")
	public void updateSumStaus(@RequestBody Map<String, Object> map) {
	   perProDeclareReg001Service.updateSumStaus(map);
	}
   
   
   /**
	 * 取消手工处理功能
	 *
	 * @return 提示信息
	 * @param map
	 *            条件map
	 * @seea
	 * @since 1.0
	 */
	@PostMapping("cancelMannul")
	public String cancelMannul(@RequestBody Map<String, Object> map) {
		return perProDeclareReg001Service.cancelMannul(map);
	}
	
	@PostMapping("updateFileNo")
	public void updateFileNo(@RequestBody PerProDeclareRegDto perProDeclareRegDto) {
//		perProDeclareReg001Service.updateFileNo(perProDeclareRegDto);
		
	}
	
	@PostMapping("selectAllData")
	public List<PerProDeclareRegDto> selectAllData(@RequestBody Map<String, Object> map){
		return perProDeclareReg001Service.selectAllData(map);
	}
	/**
	 *  查询行内标识码
	 * @param map 入参
	 * @return 行内标识码列表
	 */
	@PostMapping("queryProdInsIdList")
	public List<SelectOptionDto> queryProdInsIdList(@RequestBody Map<String,Object> map){
		return perProDeclareReg001Service.queryProdInsIdList(map);
	}
}
