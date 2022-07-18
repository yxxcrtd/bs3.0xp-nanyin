/*
 * 文件名：ValTableGenerate001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.disclosure.information.config.prodbonusplanmanage.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.util.*;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.information.config.prodbonusplanmanage.dto.ProdBonusplanDto;
import com.joyintech.tams.systemtools.util.ExcelUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.exception.WorkFlowException;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.disclosure.busicommon.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.util.JasparConnectUtilService;
import com.joyintech.tams.disclosure.common.common.util.ServiceApplication;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

/**
 * 记账凭证查询service
 * 
 * @author ruanhongming
 * @version 2.0 2018年5月3日
 * @see ProdBonusplanManager001Service
 * @since 2.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProdBonusplanManager001Service  extends CommonExportService<ProdBonusplanDto> /*implements BaseProcessService<ProdBonusplanDto>*/ {

    @Autowired
    private JasparConnectUtilService jasparConnectUtilService;
        /**
         * 日志
         */
    private Logger logger = LoggerFactory.getLogger(ProdBonusplanManager001Service.class);

    private Map<String,List<String>> propertiesMap = new HashMap<>();
    {
        propertiesMap.put("ProdBonusplanManager001_1",Lists.newArrayList("产品计划排期导入"));
        propertiesMap.put("ProdBonusplanManager_2",Lists.newArrayList("产品名称","产品主代码","开放起始日","开放起始日","产品冷静期","运作起始日","自动赎回日","实际持有天数"));
    }


        
        public List<Map<String,String>> searchReports(Map<String,Object> params) throws IOException, SAXException, ParserConfigurationException {
        	//后台批处理报表地址单独啦出来
    		String serverUrl = CacheUtil.getSysParam(ReportGlobalConstant.REPORT_SERVER_URL);
    		//用户名
    		String serverUser = CacheUtil.getSysParam(ReportGlobalConstant.REPORT_USERNAME);
    		//密码 
    		String serverPwd = CacheUtil.getSysParam(ReportGlobalConstant.REPORT_PASSWORD);
    		//路径 
    		String path = params.get("folderUri").toString();
    		
    		String url = jasparConnectUtilService.getReportsUrl(serverUrl, serverUser, serverPwd, path);
    		//
    		List<Map<String,String>> result = jasparConnectUtilService.connectJasparAndGenXML(url,false);
    		return result;
        }
        
        public List<ProdBonusplanDto> searchReportsNew(Map<String,Object> params) {

        	List<ProdBonusplanDto> result =restTemplate.postForList(
                    ServiceApplication.VALUATION_SERVICE_NAME + "/ProdBonusplanManager001/findAll",
                    params,ProdBonusplanDto.class);
    		
    		
    		return result;
        }
        /**
    	 * 指标库查询service <一句话功能简述> <功能详细描述>
    	 * 
    	 * @param pageParam
    	 * @return
    	 */
    	public Page<ProdBonusplanDto> findByPage(Map<String, Object> pageParam) {
//    		return super.queryForPage(() -> reportModelManage001Mapper.searchReports(pageParam));
    		
    		return restTemplate.postForPage(

                    ServiceApplication.VALUATION_SERVICE_NAME + "/ProdBonusplanManager001/findPage",
                    pageParam, ProdBonusplanDto.class);
    		
    		
    	}

	/**
	 *  todo
	 * @param
	 * @return
	 */
	public void doComplete(ProdBonusplanDto dto, OperationType operationType) {
		dto.setOperationType(operationType.toString());
		restTemplate.postForObject(
				ServiceApplication.VALUATION_SERVICE_NAME + "/ProdBonusplanManager001/doComplete",
				dto, Integer.class);

	}

	/**
	 *  todo
	 * @param prodId
	 * @return
	 */
	public String selectByProdId(String prodId) {
		Map<String,Object>map=new HashMap<>();
		map.put("prodId", prodId);
		return restTemplate.postForObject(
				ServiceApplication.VALUATION_SERVICE_NAME + "/ProdBonusplanManager001/selectByProdId",
				map, String.class);
	}


	/**
	 * 导入check
	 * @param fileName
	 * @return
	 */
	public AjaxResult checkImportData(String fileName) {
//        List<String> findList =  this.queryFinprod(Maps.newHashMap()).stream().map(item->item.getId()).collect(Collectors.toList());
		List<List<String>> objects = ExcelUtil.getTempList(fileName);
		if (objects.size() - 1 > 50000) {
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("errorMsg", "单次最多导入50000条。");
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
			if (null == objects.get(0) || objects.get(0).size() != 8) {
				Map<String, Object> resultMap = new HashMap<>();
				resultMap.put("errorMsg", "数据格式错误，数据格式需要8列。");
				AjaxResult wr = new AjaxResult();
				wr.setData(resultMap);
				return wr;
			}else {
				StringBuilder returnerrorMsg = new StringBuilder("");
				for (int i = 1; i < objects.size(); i++) {
					StringBuilder errorMsg = new StringBuilder("");
					List<String> rowData = objects.get(i);

					if (!StringUtil.isNotEmpty(rowData.get(0))){
						errorMsg.append("子产品代码 必填！");
					}
					if (!StringUtil.isNotEmpty(rowData.get(1))){
						errorMsg.append("分红方式 必填！");
					}else if(!StringUtils.equals(DictCodeListExt3.信披_分红方式_份额分红后净值为1.getCodeName(),rowData.get(1))
					 		&& !StringUtils.equals(DictCodeListExt3.信披_分红方式_到期兑付.getCodeName(),rowData.get(1))
							&& !StringUtils.equals(DictCodeListExt3.信披_分红方式_份额分红后净值为1.getValue(),rowData.get(1))
							&& !StringUtils.equals(DictCodeListExt3.信披_分红方式_到期兑付.getValue(),rowData.get(1))){
						errorMsg.append("分红方式值 不正确！");
					}
					if (!StringUtil.isNotEmpty(rowData.get(2))){
						errorMsg.append("收益分配基准日 必填！");
					}else if(!this.isDateStr(rowData.get(2))){
						errorMsg.append("收益分配基准日 格式不正确！");
					}
					if (!StringUtil.isNotEmpty(rowData.get(3))){
						errorMsg.append("现金红利发放日 必填！");
					}else if(!this.isDateStr(rowData.get(3))){
						errorMsg.append("现金红利发放日 格式不正确！");
					}
					if (!StringUtil.isNotEmpty(rowData.get(4))){
						errorMsg.append("业绩报酬 必填！");
					}
					if (!StringUtil.isNotEmpty(rowData.get(5))){
						errorMsg.append("超额业绩报酬收取比例 必填！");
					}
					if (!StringUtil.isNotEmpty(rowData.get(6))){
						errorMsg.append("业绩比较基准 必填！");
					}
					if (!StringUtil.isNotEmpty(rowData.get(7))){
						errorMsg.append("业绩比较基准比例 必填！");
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
				//第一行【i=0】为表头，所以不操作。
				for (int i = objects.size() - 1; i >= 1; i--) {
					List<String> list1 = objects.get(i);
					Boolean isRepeat = false;
					for (int k = i - 1; k >= 1; k--) {
						List<String> list2 = objects.get(k);
						//子产品代码，相同时为重复数据
						if ((StringUtil.equals(list1.get(0), list2.get(0)))) {
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

	public static boolean isDateStr(String str) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			format.setLenient(false);
			format.parse(str);
			return true;
		} catch (Exception var3) {
			return false;
		}
	}

/*	public static void main(String[] args) {
		String str = "2022-06-30";
		if(isDateStr(str)){
			System.out.println("正确");
		}else{
			System.out.println("no正确");
		}

	}*/


	public AjaxResult importFile(Map<String, Object> param) {
		AjaxResult ajaxResult = new AjaxResult();
		String tempList = (String) param.get("tempList");
		ProdBonusplanDto dto = getValNetAssetQuotationInfoListData(tempList);
		List<ProdBonusplanDto> selectedItem = dto.getSelectedItem();
		List<ProdBonusplanDto>  existData = new ArrayList<>();
		List<ProdBonusplanDto>  newData = new ArrayList<>();
		for (ProdBonusplanDto temp:selectedItem) {
			//数据已存在 【产品编码 相同】，做更新操作。
			if (StringUtil.isNotEmpty(this.selectByProdId(temp.getProdId()))){
				existData.add(temp);
			}else {
				temp.setDataStatus(DictCodeListExt3.信披_数据状态_有效.getValue());
				temp.setDataSource(DictCodeListExt3.信披_数据来源_EXCEL导入.getValue());
				newData.add(temp);
			}
		}
		//更新操作
		for (ProdBonusplanDto existDataDto : existData) {
			this.doComplete(existDataDto, OperationType.MODIFY);
		}
		//插入操作
		for (ProdBonusplanDto newDataDto : newData
		) {
			this.doComplete(newDataDto,OperationType.NEW);
		}
		ajaxResult.setStatuCode(HttpStatus.OK.value());
		return ajaxResult;
	}



	public ProdBonusplanDto getValNetAssetQuotationInfoListData(String tempList) {
		ProdBonusplanDto dto = new ProdBonusplanDto();
		// 行情信息列表
		List<ProdBonusplanDto> prodBonusplanDtoList = new ArrayList<>();
		dto.setSelectedItem(prodBonusplanDtoList);
		JSONArray objects = JSONArray.parseArray(tempList);

		for (int i = 0; i < objects.size(); i++) {
			JSONArray rowData = JSONArray.parseArray(((JSONArray) objects.get(i)).toJSONString());
			ProdBonusplanDto prodBonusplanDto = new ProdBonusplanDto();
			/*if (StringUtil.isNotEmpty(rowData.get(0))) {
				prodBonusplanDto.setDataStatus(rowData.get(0).toString());
			}*/
			if (StringUtil.isNotEmpty(rowData.get(0))) {
				prodBonusplanDto.setProdId(rowData.get(0).toString());
			}
			if (StringUtil.isNotEmpty(rowData.get(1))) {
				if(StringUtils.equals(DictCodeListExt3.信披_分红方式_份额分红后净值为1.getCodeName(),rowData.get(1).toString())
					||  StringUtils.equals(DictCodeListExt3.信披_分红方式_份额分红后净值为1.getValue(),rowData.get(1).toString())){
					prodBonusplanDto.setBonusMode(DictCodeListExt3.信披_分红方式_份额分红后净值为1.getValue());
				}else if(StringUtils.equals(DictCodeListExt3.信披_分红方式_到期兑付.getCodeName(),rowData.get(1).toString())
						||  StringUtils.equals(DictCodeListExt3.信披_分红方式_到期兑付.getValue(),rowData.get(1).toString())){
					prodBonusplanDto.setBonusMode(DictCodeListExt3.信披_分红方式_到期兑付.getValue());
				}
			}
			if (StringUtil.isNotEmpty(rowData.get(2))) {
				prodBonusplanDto.setSettDate(DateUtil.parse(rowData.get(2).toString()));
			}
			if (StringUtil.isNotEmpty(rowData.get(3))) {
				prodBonusplanDto.setPaymentDate(DateUtil.parse(rowData.get(3).toString()));
			}
			if (StringUtil.isNotEmpty(rowData.get(4))) {
				prodBonusplanDto.setAchievement(rowData.get(4).toString());
			}
			if (StringUtil.isNotEmpty(rowData.get(5))) {
				prodBonusplanDto.setExcessAchievementProp( new BigDecimal(rowData.get(5).toString()));
			}
			if (StringUtil.isNotEmpty(rowData.get(6))) {
				prodBonusplanDto.setAchievementBasis(rowData.get(6).toString());
			}
			if (StringUtil.isNotEmpty(rowData.get(7))) {
				prodBonusplanDto.setAchievementBasisProp(new BigDecimal(rowData.get(7).toString()));
			}

			prodBonusplanDtoList.add(prodBonusplanDto);
		}
		dto.setSelectedItem(prodBonusplanDtoList);
		return dto;
	}


	/*@Override
	public String start(WorkFlowParameter<ProdBonusplanDto> params, ProdBonusplanDto... entities) throws WorkFlowException {
		return BaseProcessService.super.start(params, entities);
	}*/
}
