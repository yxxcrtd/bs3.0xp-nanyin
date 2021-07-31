/*
 * 文件名：InvestorsHoldInfoRegist001Service.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：zuoyinyi
 * 修改时间：2018年5月8日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.svreport.pbc.common.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.interf.common.util.TisUtil;
import com.joyintech.tams.report.system.instregbankcode.dao.SysInstRegBankCode001Mapper;
import com.joyintech.tams.report.system.instregbankcode.dto.SysInstRegBankCode001Dto;
import com.joyintech.tams.svreport.pbc.common.annotation.TemplateName;
import com.joyintech.tams.svreport.pbc.common.util.PbcExcelUtil;
import com.joyintech.tams.svreport.pbc.common.util.XlsxToHtml;
import com.joyintech.tams.svreport.pbc.report.dto.PbcMonthReport;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 新人行报表预览共通service
 *
 * @author meifaxin
 * @version 1.0 2021年7月30日
 * @since 1.0
 */
@Service
public class PbcDatPriviewService extends BaseService<ITableRow>{
	private String templateName;

    /**
     * 设置公共参数
     *
     * @see
     * @since 1.0
     */
	@PostConstruct
	protected void initCommonParams() {
		if (StringUtils.isEmpty(templateName)) {
			TemplateName anno = this.getClass().getAnnotation(TemplateName.class);
			if (null != anno) {
				// 从注解中获取pageId
				templateName = anno.value();
			}
		}
	}


	public String priview(Map<String,Object> param) {
		String template = templateName;
		if(param.get("templateChange")!=null){
			template = (String)param.get("templateChange");
		}
		// 生成报表数据map
		Map<Object, Object> data = createDataMS(param);
		// 查询不到数据
		if (data.get("report") == null){
		    return "fail";
		}
		Map<String, Integer> redMap=(Map)data.get("redMap");
		// 表格宽度默认100%
		int tableWidth = 100;
		if (param.get("tableWidth") != null){
		    tableWidth = (int) param.get("tableWidth");
		}
        // sheet页数默认1
        int sheetNum = 1;
        if (param.get("sheetNum") != null){
            sheetNum = (int) param.get("sheetNum");
        }
		// 生成临时文件
		String tempFileName = template + new Date().getTime();
//		File depositfile = PbcExcelUtil.createFile(data, template, tempFileName);
		File depositfile = TisUtil.createFileIfCellRed(PbcExcelUtil.convertMapKey2String(data), "pbc/"+template, tempFileName,redMap);
		
		return XlsxToHtml.readExcelToHtml(depositfile, true, true,sheetNum,tableWidth);
	}

	public Map<Object, Object> createData(Map<String,Object> param) {
		return new HashMap<Object, Object>();
	}
	
	public Map<Object, Object> createDataMS(Map<String,Object> param) {
		return new HashMap<Object, Object>();
	}

}
