package com.joyintech.service.chb.prodraisetotalregist.controller;

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
import com.joyintech.tams.svreport.chb.prodraisetotalregist.dto.RaiseTotalRegist001Dto;
import com.joyintech.tams.svreport.chb.prodraisetotalregist.service.RaiseTotalRegist001Service;

/**
 * 募集总量登记服务
 * 〈功能详细描述〉
 * @author zhangzhongliang
 * @version 1.6.0 2019年04月24日
 * @see RaiseTotalRegistController
 * @since 3.0
 */
@RestController
@RequestMapping("/RaiseTotalRegist")
public class RaiseTotalRegistController{
	@Autowired
	private RaiseTotalRegist001Service raiseTotalRegist001Service;

    
    /**
     * @Description 产品募集总量登记分页查询
     * @return Page<InvestorsHoldInfo001Dto>
     * @version 3.0 2019年1月22日
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<RaiseTotalRegist001Dto> selectForPage(@RequestBody Map<String, Object> param) {
        Page<RaiseTotalRegist001Dto> page = raiseTotalRegist001Service.selectForPage(param);
        return page;
    }
    
    /**
    * 业务画面导出事件
    * @return
    * @since 1.0
    */
   @PostMapping("selectForExport")
   public List<RaiseTotalRegist001Dto> selectForExport(@RequestBody Map<String, Object> param) {
         return raiseTotalRegist001Service.selectForExport(param);
   }
   
   /**
    *手工处理功能
    * @return 提示信息
    * @param map 条件map
    */
   @PostMapping("updateSumStaus")
   public void doUpdate(@RequestBody Map<String,Object> map) {
	   raiseTotalRegist001Service.updateSumStaus(map);
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
	   return raiseTotalRegist001Service.cancelMannul(map);
	}
	
	@PostMapping("selectAllData")
	public List<RaiseTotalRegist001Dto> selectAllData(@RequestBody Map<String, Object> pageParam){
		return raiseTotalRegist001Service.selectAllData(pageParam);
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
        	keyList=raiseTotalRegist001Service.getCheckRepList(param);
        }
        else{
        	keyList=StringUtil.splitToList(GlobalConstant.COMMA, (String)param.get("checkKeys"));
        }
        if (CollectionUtils.isEmpty(keyList)) {
            List<ExcelValidDto> checkResult = new ArrayList<ExcelValidDto>();
            checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(0), "无报送数据",null));
            return checkResult;
        }
        return raiseTotalRegist001Service.checkData(keyList);
    }
}
