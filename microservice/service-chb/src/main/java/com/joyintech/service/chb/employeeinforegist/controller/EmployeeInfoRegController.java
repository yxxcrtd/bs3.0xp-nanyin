package com.joyintech.service.chb.employeeinforegist.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.svreport.chb.employeeinforegist.dto.EmployeeInfoReg001Dto;
import com.joyintech.tams.svreport.chb.employeeinforegist.service.EmployeeInfoReg001Service;


/**
 * 从业人员登记服务
 * 〈功能详细描述〉
 * @author lingxq
 * @version 1.0.0 2020年11月3日
 * @see EmployeeInfoRegController
 * @since 1.0
 */
@RestController("backEmployeeInfoRegController")
@RequestMapping("/service/EmployeeInfoReg")
public class EmployeeInfoRegController {
   
	@Autowired
	private EmployeeInfoReg001Service employeeInfoReg001Service;

    
    /**
    *
    * 业务画面查询事件
    * <功能详细描述>
    * @return
    * @see
    * @since 1.0
    */
   @PostMapping("findPage/{page}/{length}")
   public Page<EmployeeInfoReg001Dto> selectForPage(@RequestBody Map<String, Object> param) {
       Page<EmployeeInfoReg001Dto> page = employeeInfoReg001Service.selectForPage(param);
       return page;
   }
   
    /**
	 * 手工处理功能
	 *
	 * @return 提示信息
	 * @param map
	 *            条件map
	 * @seea
	 * @since 1.0
	 */
	@PostMapping("updateSumStaus")
	public void updateSumStaus(@RequestBody Map<String, Object> map) {
		employeeInfoReg001Service.updateSumStaus(map);
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
		return employeeInfoReg001Service.cancelMannul(map);
	}
	
	
	/**
    * 业务画面导出事件
    * @return
    * @since 1.0
    */
	@PostMapping("selectForExport")
	public List<EmployeeInfoReg001Dto> selectForExport(@RequestBody Map<String, Object> param) {
	    return employeeInfoReg001Service.selectForExport(param);
	}
	
	/**
    * 报送检查
    * @param map 查询条件
    * @return 检查结果
    * @see
    * @since 1.0
    */
   @PostMapping("checkData")
   public Map<String,Object> checkData(@RequestBody Map<String,Object> map) {
       return employeeInfoReg001Service.checkData(map);
   }

}
