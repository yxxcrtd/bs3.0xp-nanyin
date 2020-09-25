package com.joyintech.service.chb.prodpersonissue.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.chb.proddurationreg.dto.ProdDurationReg001Dto;
import com.joyintech.tams.svreport.chb.prodpersonissue.dto.ProdPersonIssue001Dto;
import com.joyintech.tams.svreport.chb.prodpersonissue.service.ProdPersonIssue001Service;


/**
 * 个人发行登记
 *
 * @author zhangzhongliang
 * @version 1.6.0 2019年04月24日
 * @see ProdPersonIssueController
 * @since 1.0
 */
@RestController
@RequestMapping("/ProdPersonIssue")
public class ProdPersonIssueController {
	/**
     * 001Service
     */
    @Autowired
    private ProdPersonIssue001Service prodPersonIssue001Service;

    /**
    *
    * 查询功能
    * <功能详细描述>
    * @return 存储了查询结果的list
    * @see
    * @since 1.0
    */
   @PostMapping("findPage/{page}/{length}")
   public Page<ProdPersonIssue001Dto> search(@RequestBody Map<String, Object> param){
       return prodPersonIssue001Service.searchForPage(param);
   }
   /**
    * 业务画面导出事件
    * @return
    * @since 1.0
    */
   @PostMapping("selectForExport")
   public List<ProdPersonIssue001Dto> selectForExport(@RequestBody Map<String, Object> param) {
         return prodPersonIssue001Service.selectForExport(param);
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
	public void doUpdate(@RequestBody Map<String, Object> map) {
		prodPersonIssue001Service.updateSumStaus(map);
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
		return prodPersonIssue001Service.cancelMannul(map);
	}
	 
	@PostMapping("queryForProdPersonIssuePage")
    public List<ProdPersonIssue001Dto> queryForProdPersonIssuePage(@RequestBody Map<String, Object> param){
    	return prodPersonIssue001Service.queryForProdPersonIssuePage(param);
    }
    
	@PostMapping("queryExportListByKeys")
    public List<ProdPersonIssue001Dto> queryExportListByKeys(@RequestBody List<String> keys){
    	return prodPersonIssue001Service.queryExportListByKeys(keys);
    }
	
	/**
     * 报送检查
     * @param map 查询条件
     * @return 检查结果
     * @see
     * @since 1.0
     */
	@PostMapping("reportCheck")
    public List<ExcelValidDto> reportCheck(@RequestBody Map<String,Object> map) {
    	List<String> keyList = new ArrayList<String>();
        Map<String, Object> param = map;
        if(!param.containsKey("checkKeys")){
        	keyList=prodPersonIssue001Service.getCheckRepList(param);
        }
        else{
        	keyList=StringUtil.splitToList(GlobalConstant.COMMA, (String)param.get("checkKeys"));
        }
        if (CollectionUtils.isEmpty(keyList)) {
            List<ExcelValidDto> checkResult = new ArrayList<ExcelValidDto>();
            checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(0), "无报送数据",null));
            return checkResult;
        }
        return prodPersonIssue001Service.checkData(keyList);
    }
}
