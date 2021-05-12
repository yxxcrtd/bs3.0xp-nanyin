package com.joyintech.tams.common.basicdata.marketdata.fundmarketinfo.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.basicdata.constant.ServiceApplication;
import com.joyintech.tams.common.basicdata.marketdata.fundmarketinfo.dto.ComFundMarketInfo001dto;
import com.joyintech.tams.common.basicdata.util.ComBasicDataExcelUtil;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.systemtools.util.ExcelUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 每日基金行情业务层
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangqidong
 * @version 1.0 2018年7月23日
 * @see ComFundMarketInfo001Service
 * @since 1.0
 */
@Service
public class ComFundMarketInfo001Service extends CommonExportService<ComFundMarketInfo001dto> implements BaseProcessService<ComFundMarketInfo001dto> {

	/**
	 * 加载表格
	 * @param params
	 * @return  List<ComFundMarketInfo001dto>
	 */
    public List<ComFundMarketInfo001dto> tableLoad(Map<String, Object> params) {
//        return valFundMarketInfo001mapper.tableLoad(dto);
    	return restTemplate.postForList(
                ServiceApplication.CommonBasicDataService + "/ComFundMarketInfo001/tableLoad",
                params, ComFundMarketInfo001dto.class);
    }
    public Page<ComFundMarketInfo001dto> findPage(Map<String, Object> params) {
//      return valFundMarketInfo001mapper.tableLoad(dto);
  	return restTemplate.postForPage(
              ServiceApplication.CommonBasicDataService + "/ComFundMarketInfo001/findPage",
              params, ComFundMarketInfo001dto.class);
  }
	/**
	 * 
	 * 赋值条件类型转换
	 * <功能详细描述>
	 * @param map map
	 * @return ComFundMarketInfo001dto
	 */
//    public ComFundMarketInfo001dto dto(Map<String, Object> map){
//        ComFundMarketInfo001dto dto = new ComFundMarketInfo001dto();
//        dto.setFundName((String)map.get("fundName"));
//        dto.setFundCode((String)map.get("fundCode"));
//        dto.setValueSource((String)map.get("valueSource"));
//        dto.setFundType((String)map.get("fundType"));
//        dto.setStartDate((String)map.get("startDate"));
//        dto.setEndDate((String)map.get("endDate"));
//        dto.setFinprodType(DictCodeListExt1.金融产品类型_基金.getValue());
//        return dto;
//    }

	@Override
	public void doComplete(ComFundMarketInfo001dto valFundMarketInfo001dto, OperationType type) {
		String action = StringUtil.EMPTY;
		if (OperationType.DELETE.equals(type)) {
			action = "delete";
		}
		else if (OperationType.NEW.equals(type)) {
			action = "insert";
		}
		else {
			action = "update";
		}
		restTemplate.postForObject(
				ServiceApplication.CommonBasicDataService + "/ComFundMarketInfo001/" + action, valFundMarketInfo001dto,
				Integer.class);
	}

	/**
	 * 查询基金代码
	 */
	public Page<SelectOptionDto> getFinProductList(Map<String, Object> params) throws Exception{
		return restTemplate.postForPage(ServiceApplication.CommonBasicDataService + "/ComFundMarketInfo001/getFinProductList",
				params, SelectOptionDto.class);
	}

	/**
	 * 点击编辑时查询数据
	 */
	public List<ComFundMarketInfo001dto> queryDtoByKey(Map<String, Object> params){
		return restTemplate.postForList(ServiceApplication.CommonBasicDataService + "/ComFundMarketInfo001/queryDtoByKey",
				params, ComFundMarketInfo001dto.class);
	}
	/**
	 * 导入check
	 * @param fileName
	 * @return
	 */
	public AjaxResult checkImportData(String fileName) {
		List<List<String>> objects = ExcelUtil.getTempList(fileName);
		if (objects.size() - 1 > 1000) {
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("errorMsg", "行情单次最多导入1000条。");
			AjaxResult wr = new AjaxResult();
			wr.setData(resultMap);
			return wr;
		}
		String totalMsg = "";
		List<List<String>> jsonArrays = new ArrayList<>();
		// 重复数据
		int repeatNum = 0;
		if (objects.size() <= 1) {
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("errorMsg", "数据格式错误，内容需要大于1行，模板是有表头的。");
			AjaxResult wr = new AjaxResult();
			wr.setData(resultMap);
			return wr;
		} else {
			if (null == objects.get(0) || objects.get(0).size() != 10) {
				Map<String, Object> resultMap = new HashMap<>();
				resultMap.put("errorMsg", "数据格式错误，数据格式需要10列。");
				AjaxResult wr = new AjaxResult();
				wr.setData(resultMap);
				return wr;
			}else {
				StringBuilder returnerrorMsg = new StringBuilder("");
				for (int i = 1; i < objects.size(); i++) {
					StringBuilder errorMsg = new StringBuilder("");
					List<String> rowData = objects.get(i);
					//基金代码 必填
					if (!StringUtil.isNotEmpty(rowData.get(0))){
						errorMsg.append("基金代码 必填！");
					}
					//基金名称 必填
					if (!StringUtil.isNotEmpty(rowData.get(1))){
						errorMsg.append("基金名称 必填！");
					}
					//基金类型 必填
					if (!StringUtil.isNotEmpty(rowData.get(2))){
						errorMsg.append("基金类型 必填！");
					}
					//估值日期 必填
					if (!StringUtil.isNotEmpty(rowData.get(3))){
						errorMsg.append("估值日期 必填！");
					}
					//收盘价 必填
					if (!StringUtil.isNotEmpty(rowData.get(4))){
						errorMsg.append("收盘价 必填！");
					}
					//平均价 必填
					if (!StringUtil.isNotEmpty(rowData.get(5))){
						errorMsg.append("平均价 必填！");
					}
					//最高价 必填
					if (!StringUtil.isNotEmpty(rowData.get(6))){
						errorMsg.append("最高价 必填！");
					}
					//最低价 必填
					if (!StringUtil.isNotEmpty(rowData.get(7))){
						errorMsg.append("最低价 必填！");
					}
					//买一价 必填
					if (!StringUtil.isNotEmpty(rowData.get(8))){
						errorMsg.append("买一价 必填！");
					}
					//卖一价 必填
					if (!StringUtil.isNotEmpty(rowData.get(9))){
						errorMsg.append("卖一价 必填！");
					}
					if (StringUtil.isNotEmpty(errorMsg.toString())){
						errorMsg.append("<br />");
						returnerrorMsg.append("第"+(i+1)+"行数据："+errorMsg.toString());
					}
				}
				if (StringUtil.isNotEmpty(returnerrorMsg.toString())){
					Map<String, Object> resultMap = new HashMap<>();
					resultMap.put("errorMsg", returnerrorMsg.toString());
					AjaxResult wr = new AjaxResult();
					wr.setData(resultMap);
					return wr;
				}
				for (int i = objects.size() - 1; i >= 1; i--) {
					List<String> list1 = objects.get(i);
					Boolean isRepeat = false;
					for (int k = i - 1; k >= 1; k--) {
						List<String> list2 = objects.get(k);
						//基金代码、估值日期，相同时为重复数据
						if ((StringUtil.equals(list1.get(0), list2.get(0)) && StringUtil.equals(list1.get(3), list2.get(3)))) {
							isRepeat = true;
							break;
						}
					}
					if (isRepeat){
						repeatNum++;
					}else {
						jsonArrays.add(list1);
					}
				}
			}
		}
		if (repeatNum > 0){
			totalMsg += "导入文件存在"+String.valueOf(repeatNum)+"条数据重复，重复数据不导入，是否确认？";
		}
		Map<String, Object> resultMap = new HashMap<>();
		if (StringUtil.isNotBlank(totalMsg)) {
			resultMap.put("confirmMsg", totalMsg);
		}
		resultMap.put("correctData", JsonUtils.object2Json(jsonArrays));
		AjaxResult wr = new AjaxResult();
		wr.setData(resultMap);
		return wr;
	}

	/**
	 * 每日基金行情
	 * @param tempList
	 * @return
	 * @throws IOException 
	 */
	public ComFundMarketInfo001dto getValNetAssetQuotationInfoListData(String tempList) throws IOException {
		ComFundMarketInfo001dto dto = new ComFundMarketInfo001dto();
		// 行情信息列表
		List<ComFundMarketInfo001dto> valFundMarketInfo001dtoList = new ArrayList<>();
		dto.setSelectedItem(valFundMarketInfo001dtoList);
		List<String> objects = JsonUtils.json2List(tempList, String.class);

		for (int i = 0; i < objects.size(); i++) {
//			JSONArray rowData = JSONArray.parseArray(((JSONArray) objects.get(i)).toJSONString());
			List<String> rowData = JsonUtils.json2List(objects.get(i), String.class);
			ComFundMarketInfo001dto valFundMarketInfo001dto = new ComFundMarketInfo001dto();
			// 基金代码【0】
			if (StringUtil.isNotEmpty(rowData.get(0))) {
				valFundMarketInfo001dto.setFinprodId(rowData.get(0).toString());
			}
			// 基金名称【1】
			if (StringUtil.isNotEmpty(rowData.get(1))) {
				valFundMarketInfo001dto.setFundName(rowData.get(1).toString());
			}
			// 基金类型【2】
			if (StringUtil.isNotEmpty(rowData.get(2))) {
				valFundMarketInfo001dto.setFundType(this.getCode(rowData.get(2).toString()));
			}
			// 估值日期【3】
			if (StringUtil.isNotEmpty(rowData.get(3))) {
				valFundMarketInfo001dto.setValueDate(DateUtil.format(DateUtil.parse(rowData.get(3).toString())));
			}
			// 收盘价【4】
			if (StringUtil.isNotEmpty(rowData.get(4))) {
				valFundMarketInfo001dto.setClosePrice(new BigDecimal(rowData.get(4).toString()));
			}
			// 平均价【5】
			if (StringUtil.isNotEmpty(rowData.get(5))) {
				valFundMarketInfo001dto.setAveragePrice(new BigDecimal(rowData.get(5).toString()));
			}
			// 最高价【6】
			if (StringUtil.isNotEmpty(rowData.get(6))) {
				valFundMarketInfo001dto.setMaxPrice(new BigDecimal(rowData.get(6).toString()));
			}
			// 最低价【7】
			if (StringUtil.isNotEmpty(rowData.get(7))) {
				valFundMarketInfo001dto.setMinPrice(new BigDecimal(rowData.get(7).toString()));
			}
			// 买一价【8】
			if (StringUtil.isNotEmpty(rowData.get(8))) {
				valFundMarketInfo001dto.setBuyOnePrice(new BigDecimal(rowData.get(8).toString()));
			}
			// 卖一价【9】
			if (StringUtil.isNotEmpty(rowData.get(9))) {
				valFundMarketInfo001dto.setSellOnePrice(new BigDecimal(rowData.get(9).toString()));
			}
			valFundMarketInfo001dtoList.add(valFundMarketInfo001dto);
		}
		dto.setSelectedItem(valFundMarketInfo001dtoList);
		return dto;
	}

	/**
	 * 取空格前的数据
	 * @param str
	 * @return
	 */
	private String getCode(String str) {
		return str.split(" ")[0];
	}
	/**
	 * 查询市场代码
	 */
	public List<SelectOptionDto> getFinprodMarketId(Map<String, Object> params) {
		return restTemplate.postForList(ServiceApplication.CommonBasicDataService + "/ComFundMarketInfo001/getFinprodMarketId",
				params, SelectOptionDto.class);
	}
	/**
	 *
	 * @param param
	 * @return
	 * @throws IOException 
	 */
	public AjaxResult importFile(Map<String, Object> param) throws IOException {
		AjaxResult ajaxResult = new AjaxResult();
		String tempList = (String) param.get("tempList");
		ComFundMarketInfo001dto dto = getValNetAssetQuotationInfoListData(tempList);
		List<ComFundMarketInfo001dto> selectedItem = dto.getSelectedItem();
		List<ComFundMarketInfo001dto>  existData = new ArrayList<>();
		List<ComFundMarketInfo001dto>  newData = new ArrayList<>();
		for (ComFundMarketInfo001dto temp:selectedItem
		) {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("finprodId",temp.getFinprodId());
			List<SelectOptionDto> finprodMarketIdDto = this.getFinprodMarketId(paramMap);

			Map<String, Object> map = new HashMap<>();
			if (!StringUtil.isEmptyList(finprodMarketIdDto)){
				map.put("fundCode",finprodMarketIdDto.get(0).getId());
				temp.setFundCode(finprodMarketIdDto.get(0).getId());
			}else {
				/** modify by yanya on 20210329 去除截取前四位字符的逻辑**/
				//String fundCode = temp.getFinprodId().substring(4);
				String fundCode = temp.getFinprodId();
				map.put("fundCode",fundCode);
			}
			map.put("valueDate",temp.getValueDate());
			List<ComFundMarketInfo001dto> comFundMarketInfo001dto = this.queryDtoByKey(map);
			//数据已存在 【债券代码、估值日 相同】，做更新操作。
			if (!StringUtil.isEmptyList(comFundMarketInfo001dto)){
				existData.add(temp);
			}else {
				newData.add(temp);
			}
		}
		//更新操作
		for (ComFundMarketInfo001dto existDataDto : existData
		) {
			this.doComplete(existDataDto,OperationType.MODIFY);
		}
		//插入操作
		for (ComFundMarketInfo001dto newDataDto : newData
		) {
			this.doComplete(newDataDto,OperationType.NEW);
		}
		ajaxResult.setStatuCode(HttpStatus.OK.value());
		return ajaxResult;
	}

	public ComFundMarketInfo001dto getAssetInfo(Map<String, Object> param) {
		List<ComFundMarketInfo001dto> comFundMarketInfo001dto = restTemplate.postForList(ServiceApplication.CommonBasicDataService + "/ComFundMarketInfo001/getAssetInfo",
				param, ComFundMarketInfo001dto.class);
		if (!StringUtil.isEmptyList(comFundMarketInfo001dto)){
			return comFundMarketInfo001dto.get(0);
		}
		return null;
	}
}



