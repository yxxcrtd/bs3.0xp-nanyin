package com.joyintech.tams.valuation.basedata.marketdata.fundmarketinfo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.invest.common.dto.InvAjaxResult;
import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;
import com.joyintech.tams.invest.common.util.ExcelUtil;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.valuation.basedata.marketdata.fundmarketinfo.dto.ValFundMarketInfo001dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
/**
 * 每日基金行情业务层
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangqidong
 * @version 1.0 2018年7月23日
 * @see ValFundMarketInfo001Service
 * @since 1.0
 */
@Service
public class ValFundMarketInfo001Service extends CommonExportService<ValFundMarketInfo001dto>{

	/**
	 * 加载表格
	 * @param dto ValFundMarketInfo001dto
	 * @return  List<ValFundMarketInfo001dto>
	 */
    public List<ValFundMarketInfo001dto> tableLoad(Map<String, Object> params) {
//        return valFundMarketInfo001mapper.tableLoad(dto);
    	return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValFundMarketInfo001/tableLoad",
                params, ValFundMarketInfo001dto.class);
    }
    public Page<ValFundMarketInfo001dto> findPage(Map<String, Object> params) {
//      return valFundMarketInfo001mapper.tableLoad(dto);
  	return restTemplate.postForPage(
              ServiceApplication.VALUATION_SERVICE_NAME + "/ValFundMarketInfo001/findPage",
              params, ValFundMarketInfo001dto.class);
  }
	/**
	 * 
	 * 赋值条件类型转换
	 * <功能详细描述>
	 * @param map map
	 * @return ValFundMarketInfo001dto
	 */
//    public ValFundMarketInfo001dto dto(Map<String, Object> map){
//        ValFundMarketInfo001dto dto = new ValFundMarketInfo001dto();
//        dto.setFundName((String)map.get("fundName"));
//        dto.setFundCode((String)map.get("fundCode"));
//        dto.setValueSource((String)map.get("valueSource"));
//        dto.setFundType((String)map.get("fundType"));
//        dto.setStartDate((String)map.get("startDate"));
//        dto.setEndDate((String)map.get("endDate"));
//        dto.setFinprodType(DictCodeListExt1.金融产品类型_基金.getValue());
//        return dto;
//    }
    
    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    public AjaxResult checkConfirmMsgForImportData(String fileName)  {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("dataList", ExcelUtil.getDataList1(fileName));
        param.put("orgCode", CommonUtil.getOrgCode());
        param.put("deptCode", CommonUtil.getDeptCode());
        return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValFundMarketInfo001/checkConfirmMsgForImportData",
                param,
                InvAjaxResult.class);
    }



    /**
     * 导入数据文件
     *
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    public List<ExcelValidDto> importData(String fileName) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("dataList", ExcelUtil.getDataList1(fileName));
        param.put("tempList", ExcelUtil.getTempList1(fileName));
        param.put("userName", CommonUtil.getUserName());
        param.put("orgCode", CommonUtil.getOrgCode());
        param.put("deptCode", CommonUtil.getDeptCode());
        return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValFundMarketInfo001/importData",
                param,
                ExcelValidDto.class);
    }
    

}



