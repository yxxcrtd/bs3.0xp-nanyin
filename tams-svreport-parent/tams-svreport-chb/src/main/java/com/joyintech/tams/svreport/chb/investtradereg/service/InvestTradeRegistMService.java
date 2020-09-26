package com.joyintech.tams.svreport.chb.investtradereg.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.chb.investtradereg.dto.InvestTradeRegist001Dto;

/**
 * 交易登记服务
 * 〈功能详细描述〉
 * @author zhangzhongliang
 * @version 1.6.0 2019年04月24日
 * @see InvestTradeRegistMService
 * @since 1.0
 */
@Service
public class InvestTradeRegistMService extends BaseService<InvestTradeRegist001Dto>{
    
    /**
     * @Description 交易登记分页查询
     * @return Page<RaiseTotalRegist001Dto>
     * @version 3.0 2019年1月25日
     */
    public Page<InvestTradeRegist001Dto> selectForPage(Map<String, Object> param) {
        Page<InvestTradeRegist001Dto> page = restTemplate.postForPage(
        		SvrServiceApplication.CHB_SERVICE_NAME + "/InvestTradeRegist/findPage",param,
        		InvestTradeRegist001Dto.class);
//        for (InvestTradeRegist001Dto dto : page.getRoot()) {
//            dto.setEndRate(dto.getEndRate()==null ? BigDecimal.ZERO:dto.getEndRate());
//        }
        return page;
    }
    
    /**
     * 业务画面导出事件
     * @return
     * @since 1.0
     */
    public List<InvestTradeRegist001Dto> selectForExport(Map<String, Object> param){
          return restTemplate.postForList(
          		SvrServiceApplication.CHB_SERVICE_NAME + "/InvestTradeRegist/selectForExport",param,
          		InvestTradeRegist001Dto.class);
    }
    
    /**
     *手工处理功能
     * @return 提示信息
     * @param map 条件map
     */
    public void updateSumStaus(Map<String,Object> map) {
    	restTemplate.postForObject(
          		SvrServiceApplication.CHB_SERVICE_NAME + "/InvestTradeRegist/updateSumStaus",map,
          		Integer.class);
    }
    
    /**
     * @Description 取消手工处理
     * @param map
     * @return
     * @return AjaxResult
     * @version 3.0 2019年1月25日
     */
 	public boolean cancelMannul(Map<String, Object> map) {
 		return restTemplate.postForObject(
          		SvrServiceApplication.CHB_SERVICE_NAME + "/InvestTradeRegist/cancelMannul",map,
          		Boolean.class);
 	}
     
     /**
      * 行内交易编码下拉框
      * @return 产品名称列表
      */
     public List<SelectOptionDto> queryTradeNoCodeList(Map<String, Object> map) {
    	 return restTemplate.postForList(
           		SvrServiceApplication.CHB_SERVICE_NAME + "/InvestTradeRegist/queryTradeNoCodeList",map,
           		SelectOptionDto.class);
     }

    public List<ExcelValidDto> reportCheck(Map<String, Object> pageParam) {
        return restTemplate.postForList(
                SvrServiceApplication.CHB_SERVICE_NAME + "/InvestTradeRegist/reportCheck", pageParam,
                ExcelValidDto.class);
    } 

}
