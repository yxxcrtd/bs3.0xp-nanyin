package com.joyintech.service.chb.prodterminationregist.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.chb.prodterminationregist.dto.ProdTerminationRegist001Dto;
import com.joyintech.tams.svreport.chb.prodterminationregist.service.ProdTerminationRegist001Service;


/**
 * @Description 产品终止登记服务
 * @author zhangzhongliang
 * @version 1.6.0 2019年04月24日
 * @since 3.0
 */
@RestController
@RequestMapping("/ProdTerminationRegist")
public class ProdTerminationRegistController{

	@Autowired
	private ProdTerminationRegist001Service prodTerminationRegist001Service;

    /**
     * @Description 产品终止登记页面查询
     * @return Page<ProdTerminationRegist001Dto>
     * @version 3.0 2019年1月23日
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<ProdTerminationRegist001Dto> selectForPage(@RequestBody Map<String, Object> param) {
        Page<ProdTerminationRegist001Dto> page =
        		prodTerminationRegist001Service.selectForPage(param);
        return page;
    }
    
    /**
     * 业务画面导出事件
     * @return
     * @since 3.0
     */
    @PostMapping("selectForExport")
    public List<ProdTerminationRegist001Dto> selectForExport(@RequestBody Map<String, Object> param) {
          return prodTerminationRegist001Service.selectForExport(param);
   
    }
    /**
     *手工处理功能
     * @return 提示信息
     * @param map 条件map
     */
    @PostMapping("updateSumStaus")
    public void doUpdate(@RequestBody Map<String,Object> map) {
    	prodTerminationRegist001Service.updateSumStaus(map);
    }
    
    /**
     * @Description 取消手工处理
     * @param map
     * @return
     * @return AjaxResult
     * @version 3.0 2019年1月24日
     */
	@PostMapping("cancelMannul")
	public String cancelMannul(@RequestBody Map<String, Object> map) {
    	return prodTerminationRegist001Service.cancelMannul(map);
	}
	
	@PostMapping("selectAllData")
	public List<ProdTerminationRegist001Dto> selectAllData(@RequestBody Map<String, Object> pageParam){
		return prodTerminationRegist001Service.selectAllData(pageParam);
	}
	
	/**   
	* @Function: ProdTerminationRegistController.java
	* @Description: 产品终止登记数据检查
	*
	* @param: 待检查数据
	* @return：产品终止登记数据检查结果
	*
	* @author: 王冠/wangguan
	* @date: 2019年6月25日 上午10:32:37 
	*/
	@PostMapping("reportCheck")
	public Map<String,Object> reportCheck(@RequestBody Map<String,Object> map) {
		List<String> keyList = new ArrayList<String>();
        Map<String, Object> param = map;
        if(!param.containsKey("checkKeys")){
        	keyList=prodTerminationRegist001Service.getCheckEndList(param);
        }
        else{
        	keyList=StringUtil.splitToList(GlobalConstant.COMMA, (String)param.get("checkKeys"));
        }
        if (CollectionUtils.isEmpty(keyList)) {
            List<ExcelValidDto> checkResult = new ArrayList<ExcelValidDto>();
            checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(0), "无报送数据",null));
			Map<String,Object> returnMap = new HashMap<>();
			returnMap.put("excelValidDtoList",checkResult);
			return returnMap;
        }
        return prodTerminationRegist001Service.checkData(keyList);
	}
}
