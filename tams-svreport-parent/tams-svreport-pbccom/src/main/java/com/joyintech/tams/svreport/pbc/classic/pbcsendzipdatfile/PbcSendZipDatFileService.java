/*
 * 文件名：PbcSendZipDatFileService.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：meifaxin
 * 修改时间：2021年07月28日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.svreport.pbc.classic.pbcsendzipdatfile;


import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.file.SafelyFile;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.dao.PbcSendZipDatFileMapper;
import com.joyintech.tams.common.base.entity.PbcSendZipDatFile;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.interf.common.util.TisUtil;
import com.joyintech.tams.report.submitmanagement.checkreport.dao.CheckReport001Mapper;
import com.joyintech.tams.report.submitmanagement.checkreport.dto.CheckReport001Dto;
import com.joyintech.tams.svreport.pbc.assetdebt.service.*;
import com.joyintech.tams.svreport.pbc.assetposition.service.PbcMonthAssetPositionService;
import com.joyintech.tams.svreport.pbc.assetposition.service.PbcMonthAssetReportService;
import com.joyintech.tams.svreport.pbc.common.constants.PbcReportType;
import com.joyintech.tams.svreport.pbc.common.constants.PbcTemplateName;
import com.joyintech.tams.svreport.pbc.common.util.PbcExcelUtil;
import com.joyintech.tams.svreport.pbc.common.util.XlsxToHtml;
import com.joyintech.tams.svreport.pbc.datacheck.beforesub.service.PbcBeforeSubCheck;
import com.joyintech.tams.svreport.pbc.prod.service.*;
import com.joyintech.tams.svreport.pbc.report.dao.PbcMonthReportMapper;
import com.joyintech.tams.svreport.pbc.report.dto.PbcMonthReport;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 
 * 功能:〈银行月度报表登记service
 * @since 1.7.0
 * @version 1.7.0
 * @作者：weiyuqiang
 * @创建时间：2019年5月30日
 * @修改记录：
 */
@Service
public class PbcSendZipDatFileService extends CommonExportService<PbcSendZipDatFile> {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private PbcReportProd001Sub001Service pbcReportProd001Sub001Service;
	@Autowired
	private PbcReportProd001Sub002Service pbcReportProd001Sub002Service;
	@Autowired
	private PbcReportProd001Sub003Service pbcReportProd001Sub003Service;
	@Autowired
	private PbcReportProd001Sub004Service pbcReportProd001Sub004Service;
	@Autowired
	private PbcReportProd001Sub005Service pbcReportProd001Sub005Service;
	@Autowired
	private PbcReportProd001Sub006Service pbcReportProd001Sub006Service;
	@Autowired
	private PbcReportProd003Sub001Service pbcReportProd003Sub001Service;
	@Autowired
	private PbcReportProd003Sub002Service pbcReportProd003Sub002Service;
	@Autowired
	private PbcReportAssetDebtService pbcReportAssetDebtService;
	@Autowired
	private PbcReportCompanyService pbcReportCompanyService;
	@Autowired
	private PbcReportAreaService pbcReportAreaService;
	@Autowired
	private PbcReportCompanyBondService pbcReportCompanyBondService;
	@Autowired
	private PbcReportAssetIncomeDetailService pbcReportAssetIncomeDetailService;
	@Autowired
	private PbcReportDebtTermDetailService pbcReportDebtTermDetailService;
    @Autowired
    private PbcMonthProdPositionService pbcMonthProdPositionService;
    @Autowired
    private PbcMonthAssetPositionService pbcAssetPositionInterf;
    @Autowired
    private PbcMonthAssetReportService pbcMonthAssetPositionService;
    @Autowired
	private CheckReport001Mapper checkReport001Mapper;
    @Autowired
	private PbcBeforeSubCheck pbcBeforeSubCheck;

	@Autowired
	private PbcSendZipDatFileMapper pbcSendZipDatFileMapper;

    /**
     * 短sql最大一次最大插入条数
     */
    @Value("${application.reportSelectOrDeleteCountForShenFen}")
    public  int reportSelectOrDeleteCountForShenFen;
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * 人行导出模版sheet页名称
     */
    private final static String SHEET_1_1 = "表1-1资管产品募集余额";
    
    private final static String SHEET_1_2 = "表1-2募集及兑付发生额";
    
    private final static String SHEET_1_3 = "表1-3只数情况";
    
    private final static String SHEET_1_4 = "表1-4境内募集余额分地区";
    
    private final static String SHEET_1_5 = "表1-5提前及延期兑付";
    
    private final static String SHEET_1_6 = "表1-6资管产品到期未兑付";
    
    private final static String SHEET_2_1 = "表2-1资产负债";
    
    private final static String SHEET_2_2 = "表2-2除回购和拆借外贷款按行业、企业";
    
    private final static String SHEET_2_3 = "表2-3除回购和拆借外贷款分地区";
    
    private final static String SHEET_2_4 = "表2-4资产收益权按基础资产投向";
    
    private final static String SHEET_2_5 = "表2-5企业债券按行业、企业";
    
    private final static String SHEET_3_1 = "表3-1存续产品分期限募集余额";
    
    private final static String SHEET_3_2 = "表3-2新发产品分期限募集金额";
    
    private final static String SHEET_3_3 = "表3-3资产负债剩余期限";
    
	/**
	 * 查询页面数据
	 *
	 * @param pageParam
	 *            查询条件
	 * @return 结果列表
	 * @see
	 * @since 1.0
	 */
	public Page<PbcSendZipDatFile> searchPage(Map<String, Object> pageParam) {
		dealSearchParam(pageParam);
		//将发送状态改为空
		pageParam.put("sendStatus", "");
        if (pageParam.get(GlobalConstant.PARAM_PAGE) != null
                && pageParam.get(GlobalConstant.PARAM_LENGTH) != null){
            int pagenum = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_PAGE).toString());
            int legth = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_LENGTH).toString());
            Page<PbcSendZipDatFile> page = queryForPage(
                    pagenum,legth,()->pbcSendZipDatFileMapper.searchPage(pageParam));
            return page;
        } else {
        	Page<PbcSendZipDatFile> page = queryForPage(PbcSendZipDatFileMapper.class, "searchPage",
    				pageParam);
            return page;
        }
	}



	/**
	 * 处理查询前的参数 <功能详细描述>
	 *
	 * @param pageParam
	 * @see
	 * @since 1.0
	 */
	public void dealSearchParam(Map<String, Object> pageParam) {
		// 选择行数据时
		if (pageParam.containsKey("keyList")) {
			String keyList = pageParam.get("keyList").toString();
			try {
				List<PbcMonthReport> astValueList = JsonUtils.json2List(keyList,
						PbcMonthReport.class);
				pageParam.put("reportList", astValueList);
				if (!CollectionUtils.isEmpty(astValueList)){
					pageParam.put("reportuuid", astValueList.get(0).getReportuuid());
				}
			} catch (IOException e) {
//				e.printStackTrace();
	            logger.error(e.getMessage(),e);
			}
		} else {
			// 报送类型
			String datZipType = (String) pageParam.get("datZipType");
			// 报送状态
			String zipSendStatus = (String) pageParam.get("zipSendStatus");
			// 反馈状态
			String returnZipSendStatus = (String) pageParam.get("returnZipSendStatus");


			// 将数据状态当做多选下拉框处理
			if (StringUtil.isNotBlank(datZipType)) {
				pageParam.put("datZipTypeList", StringUtil.splitToList(GlobalConstant.COMMA, datZipType));
			}
			// 将报送状态当做多选下拉框处理
			if (StringUtil.isNotBlank(zipSendStatus)) {
				pageParam.put("zipSendStatusList", StringUtil.splitToList(GlobalConstant.COMMA, zipSendStatus));
			}
			// 将反馈状态当做多选下拉框处理
			if (StringUtil.isNotBlank(returnZipSendStatus)) {
				pageParam.put("returnZipSendStatusList",
						StringUtil.splitToList(GlobalConstant.COMMA, returnZipSendStatus));
			}

		}
	}


	
	

}
