package com.joyintech.service.pbc.monthreport.controller;



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
import com.joyintech.tams.svreport.pbc.report.dto.PbcMonthReport;
import com.joyintech.tams.svreport.pbc.report.service.PbcMonthReport001Service;


/**
 * 人行报表 服务
 * 〈功能详细描述〉
 * @author zhangzhongliang
 * @version 1.0 2019年4月26日
 * @see PbcMonthReportController
 * @since 1.0
 */
@RestController
@RequestMapping("/PbcMonthReport")
public class PbcMonthReportController{
    /**
     * service
     */
    @Autowired
    private PbcMonthReport001Service pbcMonthReport001Service;
 
	/**
	 *
	 * 业务画面查询事件
	 *
	 * @return
	 * @see
	 * @since 1.0
	 */
	@PostMapping("findPage/{page}/{length}")
	public Page<PbcMonthReport> searchPage(@RequestBody Map<String, Object> param) {
		Page<PbcMonthReport> page = pbcMonthReport001Service.searchPage(param);
		return page;
	}


    /**
     *
     * 业务画面查询事件
     *
     * @return
     * @see
     * @since 1.0
     */
	@PostMapping("searchPageBysubmitDate")
    public PbcMonthReport searchPageBysubmitDate(@RequestBody Map<String, Object> param) {
        return pbcMonthReport001Service.searchPageBysubmitDate(param);
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
		pbcMonthReport001Service.updateSumStaus(map);
	}
	
	@PostMapping("recalReport")
	public String recalReport(@RequestBody Map<String, Object> map){
		String message = pbcMonthReport001Service.recalReport(map);
		return message;
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
	public boolean cancelMannul(@RequestBody Map<String, Object> map) {
		return pbcMonthReport001Service.cancelMannul(map);
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
            keyList=pbcMonthReport001Service.getCheckRepList(param);
        }
        else{
            keyList=StringUtil.splitToList(GlobalConstant.COMMA, (String)param.get("checkKeys"));
        }
        if (CollectionUtils.isEmpty(keyList)) {
            List<ExcelValidDto> checkResult = new ArrayList<ExcelValidDto>();
            checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(0), "无报送数据",null));
            return checkResult;
        }
        return pbcMonthReport001Service.checkData(keyList);
    }
	
}
