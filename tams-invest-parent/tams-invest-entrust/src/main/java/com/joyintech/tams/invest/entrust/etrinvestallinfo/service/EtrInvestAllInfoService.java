/*
 * 文件名：EtrInvestAllInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年9月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.entrust.etrinvestallinfo.service;

import java.util.List;
import java.util.Map;

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
import com.joyintech.tams.invest.entrust.etrrepo.dto.EtrRepoDto;

/**
 * 所有投资委托画面段service 〈功能详细描述〉
 * 
 * @author xiexinting
 * @version 1.0 2018年9月7日
 * @see EtrInvestAllInfoService
 * @since 1.0
 */
@Service
public class EtrInvestAllInfoService extends CommonExportService<EtrInvestAllInfoDto> {

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
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrInvestAllInfo/getentrustId?entrustId="+param,
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
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrInvestAllInfo/getfinprodId?finprodId="+param,
            SuggestDto.class);

    }

    /**
     * 所有投资委托信息查询 <功能详细描述>
     * 
     * @param param
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<EtrInvestAllInfoDto> selectForPage(Map<String, Object> param){
        if(StringUtil.isNotEmpty((String)param.get("entrustDate"))){
            List<String> dateList =StringUtil.splitToList(" ", (String)param.get("entrustDate"));
            param.put("startDate",dateList.get(0));
            param.put("endDate",dateList.get(2));
        }
        if(StringUtil.isNotEmpty((String)param.get("finprodType2"))){
            List<String> dateList =StringUtil.splitToList(",", (String)param.get("finprodType2"));
            param.put("finprodType2List",dateList);
        }

        if(StringUtil.isNotEmpty(param.get("entrustStatus"))){
            List<String> entrustStatusList = StringUtil.splitToList(",",(String)param.get("entrustStatus"));
            param.put("entrustStatusList", entrustStatusList);
        }
        //交易方向为利率互换 转换
        if(StringUtil.isNotEmpty((String)param.get("ps"))){
        	if( StringUtil.equals((String)param.get("ps") , DictCode.互换方式.getValue()+DictCodeListExt2.互换方式_收取固定.getValue()) ){
        		param.put("ps", DictCodeListExt2.互换方式_收取固定.getValue());
        		param.put("IRSps", DictCodeListExt2.互换方式_收取固定.getValue());
            }
            if( StringUtil.equals((String)param.get("ps") , DictCode.互换方式.getValue()+DictCodeListExt2.互换方式_收取浮动.getValue()) ){
            	param.put("ps", DictCodeListExt2.互换方式_收取浮动.getValue());
            	param.put("IRSps", DictCodeListExt2.互换方式_收取浮动.getValue());
            }
        }
        param.put("IRSType_01", DictCodeListExt2.互换方式_收取固定.getValue());
        param.put("IRSType_02", DictCodeListExt2.互换方式_收取浮动.getValue());
        param.put("userId", SecurityUtil.getLoginUser().getUserName());
        return restTemplate.postForPage(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrInvestAllInfo/findPage", param,
            EtrInvestAllInfoDto.class);
    }
    
    /**
     * 导出
     * 
     * @param exportParam 导出信息
     * @see
     * @since 1.0
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/invest/entrust/etrinvestallinfo/conf/export_conf.xml")
            .dataSource(DataSourceType.MASERT);
    }
    @Override
    public Page<EtrInvestAllInfoDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
    /**
     * PB文件导出前数据检查 add by caoxue 2019-12-31
     * 
     * @return 返回检查结果
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    public EtrRepoDto checkPBRepoType(Map<String, Object> params) {
    	if(StringUtil.isNotEmpty((String)params.get("entrustId"))){
        	EtrRepoDto etrRepoDto = restTemplate.getForObject(
    				ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrRepo/selectByKey?entrustId=" + params.get("entrustId"),
    				EtrRepoDto.class);

        	return etrRepoDto;
    	}
    	return null;
    }
    /**
     * 查询PB导出信息 add by caoxue 2019-12-31
     * 
     * @param param
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
	public List<EtrInvestAllInfoDto> selectExportPB(Map<String, Object> param) {
		return restTemplate.postForList(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrInvestAllInfo/selectExportPB", param,
				EtrInvestAllInfoDto.class);
	}
}
