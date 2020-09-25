package com.joyintech.service.chb.botastpositionregist.controller;

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
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.chb.botastpositionregist.dto.ChbBotAstPositionRegist001Dto;
import com.joyintech.tams.svreport.chb.botastpositionregist.service.ChbBotAstPositionRegist001Service;


/**
 * 底层资产持仓信息登记服务
 * 〈功能详细描述〉
 * @author zhangzhongliang
 * @version 1.6.0 2019年04月24日
 * @see ChbBotAstPositionRegistController
 * @since 1.0
 */
@RestController
@RequestMapping("/ChbBotAstPositionRegist")
public class ChbBotAstPositionRegistController{
    /**
     * service
     */
    @Autowired
    private ChbBotAstPositionRegist001Service chbBotAstPositionRegist001Service;

	/**
	 *
	 * 业务画面查询事件
	 *
	 * @return
	 * @see
	 * @since 1.0
	 */
	@PostMapping("findPage/{page}/{length}")
	public Page<ChbBotAstPositionRegist001Dto> searchPage(@RequestBody Map<String, Object> param) {
		Page<ChbBotAstPositionRegist001Dto> page = chbBotAstPositionRegist001Service
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
		chbBotAstPositionRegist001Service.updateSumStaus(map);
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
		return chbBotAstPositionRegist001Service.cancelMannul(map);
	}
	
	@PostMapping("searchPageExport")
    public List<ChbBotAstPositionRegist001Dto> searchPageExport(@RequestBody Map<String,Object> map){
      	 return chbBotAstPositionRegist001Service.searchPageExport(map);
    }
      
	@PostMapping("queryExportListByKeys")
    public List<ChbBotAstPositionRegist001Dto> queryExportListByKeys(@RequestBody List<ChbBotAstPositionRegist001Dto> registList){
      	return chbBotAstPositionRegist001Service.queryExportListByKeys(registList);
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
        	keyList=chbBotAstPositionRegist001Service.getCheckRepList(param);
        }
        else{
        	keyList=StringUtil.splitToList(GlobalConstant.COMMA, (String)param.get("checkKeys"));
        }
        if (CollectionUtils.isEmpty(keyList)) {
            List<ExcelValidDto> checkResult = new ArrayList<ExcelValidDto>();
            checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(0), "无报送数据",null));
            return checkResult;
        }
        return chbBotAstPositionRegist001Service.checkData(keyList);
    }
}
