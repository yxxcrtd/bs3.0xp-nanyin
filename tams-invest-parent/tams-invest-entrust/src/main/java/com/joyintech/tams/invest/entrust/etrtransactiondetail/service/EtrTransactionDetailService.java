package com.joyintech.tams.invest.entrust.etrtransactiondetail.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.entrust.etrinvestallinfo.dto.EtrInvestAllInfoDto;
import com.joyintech.tams.invest.entrust.etrtransactiondetail.dto.EtrTransactionDetailDto;
import com.joyintech.tams.invest.entrust.etrtransactiondetail.dto.TrdProductDealDetailDto;

@Service
public class EtrTransactionDetailService extends CommonExportService<TrdProductDealDetailDto> {
	/**
	 * 获取组合代码的联想文本框 <功能详细描述>
	 * 
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */

	public List<SuggestDto> getentrustId(String param) {
		return restTemplate.getForList(
				ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrTransactionDetail/getentrustId?entrustId=" + param,
				SuggestDto.class);
	}

	/**
	 * 获取资产编号的联想文本框 <功能详细描述>
	 * 
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */
	public List<SuggestDto> getfinprodId(String param) {
		return restTemplate.getForList(
				ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrTransactionDetail/getfinprodId?finprodId=" + param,
				SuggestDto.class);

	}

	/**
	 * 所有交易所指令信息查询 <功能详细描述>
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	public Page<EtrTransactionDetailDto> selectForPage(Map<String, Object> param) {
		if (StringUtil.isNotEmpty((String) param.get("entrustDate"))) {
			List<String> dateList = StringUtil.splitToList(" ", (String) param.get("entrustDate"));
			param.put("startDate", dateList.get(0));
			param.put("endDate", dateList.get(2));
		}
		if(StringUtil.isNotEmpty((String)param.get("finprodType2"))){
            List<String> dateList =StringUtil.splitToList(",", (String)param.get("finprodType2"));
            param.put("finprodType2List",dateList);
        }
        param.put("userId", SecurityUtil.getLoginUser().getUserName());
		if(StringUtil.isNotEmpty(param.get("entrustStatus"))){
			List<String> entrustStatusList = StringUtil.splitToList(",",(String)param.get("entrustStatus"));
			param.put("entrustStatusList", entrustStatusList);
		}

		if(StringUtil.isNotEmpty(param.get("portfolioId"))){
			param.put("portfolioIdList", StringUtil.splitToList(GlobalConstant.COMMA,(String)param.get("portfolioId")));
		}

		param.put("userId", SecurityUtil.getLoginUser().getUserName());
		return restTemplate.postForPage(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrTransactionDetail/findPage",
				param, EtrTransactionDetailDto.class);
	}
	
	
	/**
	 * 交易所指令成交明细查询 <功能详细描述>
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	public Page<TrdProductDealDetailDto> selectTransDetailPage(Map<String, Object> param) {
		if (StringUtil.isNotEmpty((String) param.get("entrustDate"))) {
			List<String> dateList = StringUtil.splitToList(" ", (String) param.get("entrustDate"));
			param.put("startDate", dateList.get(0));
			param.put("endDate", dateList.get(2));
		}
		if (StringUtil.isNotEmpty((String) param.get("finprodType2"))) {
			List<String> dateList = StringUtil.splitToList(",", (String) param.get("finprodType2"));
			param.put("finprodType2List", dateList);
		}
		if(StringUtil.isNotEmpty(param.get("entrustStatus"))){
			List<String> entrustStatusList = StringUtil.splitToList(",",(String)param.get("entrustStatus"));
			param.put("entrustStatusList", entrustStatusList);
		}


		return restTemplate.postForPage(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrTransactionDetail/selectTransDetailPage", param,
				TrdProductDealDetailDto.class);
	}
	
	/**
	 * 交易所指令成交明细查询 <功能详细描述>
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	public Page<TrdProductDealDetailDto> selectTransDetail(Map<String, Object> param) {
		return restTemplate.postForPage(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrTransactionDetail/selectTransDetailPage", param,
				TrdProductDealDetailDto.class);
	}

	/**
	 * 导出
	 * 
	 * @param exportParam
	 *            导出信息
	 * @see
	 * @since 1.0
	 */
	@Override
	protected void buildExportParam(ExportParam exportParam) {
		exportParam.config("/com/joyintech/tams/invest/entrust/etrtransactiondetail/conf/export_conf.xml").dataSource(DataSourceType.MASERT);
	}

	@Override
	public Page<TrdProductDealDetailDto> selectPageData(Map<String, Object> selectParam) {
		Page<EtrTransactionDetailDto> EtrTransactionDetailDtoResult = selectForPage(selectParam);
		if(StringUtil.isNotEmpty(EtrTransactionDetailDtoResult) && StringUtil.isNotEmpty(EtrTransactionDetailDtoResult.getRoot())) {
			List<String> entrustIdList = new ArrayList<>();
			EtrTransactionDetailDtoResult.getRoot().forEach(item ->{
				entrustIdList.add(item.getEntrustId());
			});

			if(StringUtil.isNotEmpty((String) selectParam.get("entrustId"))) {
				selectParam.put("entrustId",null);
			}
			selectParam.put("entrustIdList",entrustIdList);
		}
		return selectTransDetailPage(selectParam);
	}

	/**
	 * 设置导出单元格格式
	 */
	@Override
	public void setCellValue(String beanName, Cell cell, String propName, Object value) {
		if(StringUtil.equals(propName,"vPayDate") || StringUtil.equals(propName,"mPayDate")){
			if(value instanceof Date){
				cell.setCellValue(DateUtil.format((Date)value));
			}else if (value instanceof Long){
				cell.setCellValue(DateUtil.format(new Date((Long) value)));
			}
		}
	}
}
