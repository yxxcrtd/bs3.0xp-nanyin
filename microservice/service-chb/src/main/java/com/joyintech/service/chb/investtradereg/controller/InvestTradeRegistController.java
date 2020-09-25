package com.joyintech.service.chb.investtradereg.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.chb.investtradereg.dto.InvestTradeRegist001Dto;
import com.joyintech.tams.svreport.chb.investtradereg.service.InvestTradeRegist001Service;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 交易登记服务
 * 〈功能详细描述〉
 * @author zhangzhongliang
 * @version 1.6.0 2019年04月24日
 * @see InvestTradeRegistController
 * @since 1.0
 */
@RestController("InvestTradeRegistCtr_dtx")
@RequestMapping("/InvestTradeRegist")
public class InvestTradeRegistController{

	@Autowired
	private InvestTradeRegist001Service investTradeRegist001Service;
	
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(getClass());
	
    
    /**
     * @Description 交易登记分页查询
     * @return Page<RaiseTotalRegist001Dto>
     * @version 3.0 2019年1月25日
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<InvestTradeRegist001Dto> selectForPage(@RequestBody Map<String, Object> param) {
        Page<InvestTradeRegist001Dto> page =
        		investTradeRegist001Service.selectForPage(param);
        return page;
    }
    
    /**
     * 业务画面导出事件
     * @return
     * @since 1.0
     */
    @PostMapping("selectForExport")
    public List<InvestTradeRegist001Dto> selectForExport(@RequestBody Map<String, Object> param){
          return investTradeRegist001Service.selectForExport(param);
    }
    
    /**
     *手工处理功能
     * @return 提示信息
     * @param map 条件map
     */
    @Transactional
    @DTXTransaction
    @PostMapping("updateSumStaus")
    public void doUpdate(@RequestBody Map<String,Object> map) {
    	investTradeRegist001Service.updateSumStaus(map);
    }
    
    /**
     * @Description 取消手工处理
     * @param map
     * @return
     * @return AjaxResult
     * @version 3.0 2019年1月25日
     */
    @Transactional
    @DTXTransaction
 	@PostMapping("cancelMannul")
 	public boolean cancelMannul(@RequestBody Map<String, Object> map) {
    	 return investTradeRegist001Service.cancelMannul(map);
 	}
     
     /**
      * 行内交易编码下拉框
      * @return 产品名称列表
      */
     @PostMapping("queryTradeNoCodeList")
     public List<SelectOptionDto> queryTradeNoCodeList(@RequestBody Map<String, Object> map) {
         return investTradeRegist001Service.queryTradeNoCodeList(map);
     } 
     
     @PostMapping("selectAllData")
     public List<InvestTradeRegist001Dto> selectAllData(@RequestBody Map<String, Object> pageParam){
 		return investTradeRegist001Service.selectAllData(pageParam);
 	}

    /**
     * 数据检查
     * 
     * @param map
     *            查询条件
     * @return 检查结果
     * @see
     * @since 1.0
     */
    @PostMapping("reportCheck")
    public List<ExcelValidDto> reportCheck(@RequestBody Map<String, Object> map) {
        List<InvestTradeRegist001Dto> keyList = new ArrayList<InvestTradeRegist001Dto>();
        Map<String, Object> param = map;
        if (!param.containsKey("keyList")) {
            keyList = investTradeRegist001Service.getCheckRepList(param);
        } else {
            String dataList = map.get("keyList").toString();
            try {
                keyList = JsonUtils.json2List(dataList,
                        InvestTradeRegist001Dto.class);
            } catch (IOException e) {
                logger.error(e.getMessage(),e);
            }
        }
        if (CollectionUtils.isEmpty(keyList)) {
            List<ExcelValidDto> checkResult = new ArrayList<ExcelValidDto>();
            checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(0), "无报送数据", null));
            return checkResult;
        }
        return investTradeRegist001Service.checkData(keyList);
    }
}
