package com.joyintech.service.chb.proddurationreg.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.chb.proddurationreg.dto.ProdDurationReg001Dto;
import com.joyintech.tams.svreport.chb.proddurationreg.service.ProdDurationReg001Service;
import com.joyintech.tams.svreport.chb.prodraisetotalregist.dto.RaiseTotalRegist001Dto;

/**
 * 存续期交易登记服务
 * 〈功能详细描述〉
 * @author zhangzhongliang
 * @version 1.6.0 2019年04月24日
 * @see ProdDurationRegController
 * @since 1.0
 */
@RestController
@RequestMapping("/ProdDurationReg")
public class ProdDurationRegController {
   
	@Autowired
	private ProdDurationReg001Service prodDurationReg001Service;

    
    /**
    *
    * 业务画面查询事件
    * <功能详细描述>
    * @return
    * @see
    * @since 1.0
    */
   @PostMapping("findPage/{page}/{length}")
   public Page<ProdDurationReg001Dto> selectForPage(@RequestBody Map<String, Object> param) {
       Page<ProdDurationReg001Dto> page = prodDurationReg001Service.selectForPage(param);
       return page;
   }
   /**
    * 业务画面导出事件
    * @return
    * @since 1.0
    */
   @PostMapping("selectForExport")
   public List<ProdDurationReg001Dto> selectForExport(@RequestBody Map<String, Object> param) {
         return prodDurationReg001Service.selectForExport(param);
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
		prodDurationReg001Service.updateSumStaus(map);
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
		return prodDurationReg001Service.cancelMannul(map);
	}
	
	@PostMapping("selectAllData")
	List<ProdDurationReg001Dto>  selectAllData(@RequestBody Map<String, Object> pageParam){
		return prodDurationReg001Service.selectAllData(pageParam);
	}

	@PostMapping("queryExportListByKeys")
	List<ProdDurationReg001Dto> queryExportListByKeys(@RequestBody List<ProdDurationReg001Dto> prodDurationRegistList){
		return prodDurationReg001Service.queryExportListByKeys(prodDurationRegistList);
	}
	 /**
	    * 报送检查
	    * @param map 查询条件
	    * @return 检查结果
	    * @see
	    * @since 1.0
	    */
	   @PostMapping("reportCheck")
	   public Map<String,Object> reportCheck(@RequestBody Map<String,Object> map) {
	       return prodDurationReg001Service.checkData(map);
	   }
	
}
