/*
 * 文件名：TrdQueryDuration001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2018年9月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.period.trdqueryduration.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.period.trdbondduration.dto.TrdBondDuration002ProcessDto;
import com.joyintech.tams.invest.period.trdqueryduration.dto.TrdQueryDuration001Dto;
import com.joyintech.tams.invest.period.trdqueryduration.dto.TrdQueryDurationDto;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


/**
 * 交易查询service
 * 〈功能详细描述〉
 * @author  wangyang1
 * @version 1.0 2021年2月1日
 * @see TrdQueryDuration001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class TrdQueryDuration001Service extends CommonExportService<TrdQueryDuration001Dto> {
    
    
    private Logger logger = LoggerFactory.getLogger(TrdQueryDuration001Service.class);
    
    /**
	 * 根据有效状态的不同的查询
	 * @param pageParam
	 * @return
	 * @throws Exception
	 */
	public Page<TrdQueryDuration001Dto> selectForPage(Map<String, Object> pageParam){
        if (pageParam.get("statusValid") != null) {
            pageParam.put("statusValid",
                    StringUtil.splitToList(",", (String)pageParam.get("statusValid")));
        }
        else {
            pageParam.put("statusValid", new ArrayList<>());
        }
        if (pageParam.get("portfolioId") != null) {
            pageParam.put("portfolioId",
                    StringUtil.splitToList(",", (String)pageParam.get("portfolioId")));
        }
        else {
            pageParam.put("portfolioId", new ArrayList<>());
        }
        pageParam.put("userName", LoginUserUtil.getUserName());
        return restTemplate.postForPage(
                ServiceApplication.PERIOD_SERVICE_NAME + "/TrdQueryDuration001/findPage", pageParam,
                TrdQueryDuration001Dto.class);
	}

    /**
     * 组合代码的联想文本框
     * @param portfolioId
     * @return
     */
	public Page<SelectOptionDto> getPortfolioId(Map<String,Object> map) {
		return restTemplate.postForPage(
				ServiceApplication.PERIOD_SERVICE_NAME + "/TrdQueryDuration001/getPortfolioIds",
				map, SelectOptionDto.class);
	}

	/**
	 * 证券账户合作资产的下拉框
	 * @return
	 */
	public Page<SelectOptionDto> getSecAcctIdAndChlIds(Map<String,Object> map) {
		return restTemplate.postForPage(
				ServiceApplication.PERIOD_SERVICE_NAME + "/TrdQueryDuration001/getSecAcctIdAndChlIds",
				map, SelectOptionDto.class);
	}

	/**
	 * 资产代码的联想文本框
	 * @param finprodMarketId
	 * @return
	 */
	public List<SuggestDto> getFinprodMarketIds(String finprodMarketId) {
		return restTemplate.getForList(ServiceApplication.PERIOD_SERVICE_NAME
    			+ "/TrdQueryDuration001/getFinprodMarketIds?finprodMarketId=" +finprodMarketId,SuggestDto.class);				
	}
	/**
    * 导出 
    */
	@Override
	protected void buildExportParam(ExportParam exportParam) {
		// TODO Auto-generated method stub
		exportParam.config(
				"/com/joyintech/tams/invest/period/trdqueryduration/conf/export_conf.xml").dataSource(DataSourceType.MASERT);
	}
	/**
	 * 
	 */
	@Override
	public Page<TrdQueryDuration001Dto> selectPageData(Map<String, Object> selectParam) {
		// TODO Auto-generated method stub
		return selectForPage(selectParam);
	} 

}
