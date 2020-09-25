package com.joyintech.service.chb.astvalueregist.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.svreport.chb.astvalueregist.dto.ChbAstValueRegist001Dto;
import com.joyintech.tams.svreport.chb.astvalueregist.service.ChbAstValueRegist001Service;


/**
 * 资产估值信息登记服务
 * 〈功能详细描述〉
 * @author zhangzhongliang
 * @version 1.6.0 2019年04月24日
 * @see ChbAstValueRegistController
 * @since 1.0
 */
@RestController
@RequestMapping("/ChbAstValueRegist")
public class ChbAstValueRegistController{
    /**
     * service
     */
    @Autowired
    private ChbAstValueRegist001Service chbAstValueRegist001Service;
    

	/**
	 *
	 * 业务画面查询事件
	 *
	 * @return
	 * @see
	 * @since 1.0
	 */
	@PostMapping("findPage/{page}/{length}")
	public Page<ChbAstValueRegist001Dto> searchPage(@RequestBody Map<String, Object> param) {
		Page<ChbAstValueRegist001Dto> page = chbAstValueRegist001Service
				.searchPage(param);
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
		chbAstValueRegist001Service.updateSumStaus(map);
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
		return chbAstValueRegist001Service.cancelMannul(map);
	}
	
	@PostMapping("searchPageExport")
    public List<ChbAstValueRegist001Dto> searchPageExport(@RequestBody Map<String,Object> map){
		return chbAstValueRegist001Service.searchPageExport(map);
    }
   
	@PostMapping("queryExportListByKeys")
    public List<ChbAstValueRegist001Dto> queryExportListByKeys(@RequestBody List<ChbAstValueRegist001Dto> registList){
		return chbAstValueRegist001Service.queryExportListByKeys(registList);
    }
}
