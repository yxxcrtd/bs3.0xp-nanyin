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
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.PbcSendZipDatFileMapper;
import com.joyintech.tams.common.base.entity.PbcSendZipDatFile;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.submitmanagement.checkreport.dao.CheckReport001Mapper;
import com.joyintech.tams.svreport.pbc.assetdebt.service.*;
import com.joyintech.tams.svreport.pbc.assetposition.service.PbcMonthAssetPositionService;
import com.joyintech.tams.svreport.pbc.assetposition.service.PbcMonthAssetReportService;
import com.joyintech.tams.svreport.pbc.datacheck.beforesub.service.PbcBeforeSubCheck;
import com.joyintech.tams.svreport.pbc.prod.service.*;
import com.joyintech.tams.svreport.pbc.report.dto.PbcMonthReport;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
	private  PbcPrdBaseInfoSubDatService pbcPrdBaseInfoSubDatService;
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

	public String priview(String pageid,Map<String, Object> selectParam) {
		//获取报表类型  根据不同的报表类型 查询不同的明细和对应的 模板数据
		String reporttype=selectParam.get("reporttype").toString();
		String message="";
		if(reporttype.equals(DictCodeListExt2.人行报送明细类型_产品基本信息.getValue())){
			// 设置table预览大小
			selectParam.put("tableWidth", 200);
			message=pbcPrdBaseInfoSubDatService.priview(selectParam);
		}else if(reporttype.equals(DictCodeListExt2.人行报送明细类型_产品终止信息)){
			// 设置table预览大小
			selectParam.put("tableWidth", 200);
			message=pbcReportProd001Sub002Service.priview(selectParam);
		}else if(reporttype.equals(DictCodeListExt2.人行报送明细类型_募集余额报表)){
			// 设置table预览大小
			selectParam.put("tableWidth", 120);
			message=pbcReportProd001Sub001Service.priview(selectParam);
		}else if(reporttype.equals(DictCodeListExt2.人行报送明细类型_募集兑付报表)){
			// 设置table预览大小
			selectParam.put("tableWidth", 200);
			message=pbcReportProd001Sub002Service.priview(selectParam);
		}else if(reporttype.equals(DictCodeListExt2.人行报送明细类型_只数情况报表)){
			message=pbcReportProd001Sub003Service.priview(selectParam);
		}else if(reporttype.equals(DictCodeListExt2.人行报送明细类型_募集余额报表分地区报表)){
			// 设置table预览大小
			selectParam.put("tableWidth", 120);
			message=pbcReportProd001Sub004Service.priview(selectParam);
		}else if(reporttype.equals(DictCodeListExt2.人行报送明细类型_提前及延期兑付报表)){
			// 设置table预览大小
			selectParam.put("tableWidth", 160);
			message=pbcReportProd001Sub005Service.priview(selectParam);
		}else if(reporttype.equals(DictCodeListExt2.人行报送明细类型_到期未兑付报表)){
			message=pbcReportProd001Sub006Service.priview(selectParam);
		}else if(reporttype.equals(DictCodeListExt2.人行报送明细类型_资管产品资产负债统计)){
			// 设置table预览大小
			selectParam.put("tableWidth", 200);
			message=pbcReportAssetDebtService.priview(selectParam);
		}else if(reporttype.equals(DictCodeListExt2.人行报送明细类型_除回购和拆借外贷款分行业企业规模统计)){
			message=pbcReportCompanyService.priview(selectParam);
		}else if(reporttype.equals(DictCodeListExt2.人行报送明细类型_除回购和拆借外贷款分地区统计表)){
			message=pbcReportAreaService.priview(selectParam);
		}else if(reporttype.equals(DictCodeListExt2.人行报送明细类型_资产收益权按基础资产投向分类统计报表)){
			// 设置table预览大小
			selectParam.put("tableWidth", 120);
			message=pbcReportAssetIncomeDetailService.priview(selectParam);
		}else if(reporttype.equals(DictCodeListExt2.人行报送明细类型_企业债券分行业企业规模统计)){
			message=pbcReportCompanyBondService.priview(selectParam);
		}else if(reporttype.equals(DictCodeListExt2.人行报送明细类型_存续产品分期限募集余额报表)){
			message=pbcReportProd003Sub001Service.priview(selectParam);
		}else if(reporttype.equals(DictCodeListExt2.人行报送明细类型_新发产品分期限募集金额报表)){
			message=pbcReportProd003Sub002Service.priview(selectParam);
		}else if(reporttype.equals(DictCodeListExt2.人行报送明细类型_资管产品资产负债剩余期限统计)){
			message=pbcReportDebtTermDetailService.priview(selectParam);
		}

//		Map<Object,Object> data = createData(selectParam);
//
//		data.put("termname",data.get("termname").toString());
//	    File depositfile = createFile(data,exceltemplate);
//		return this.priView(depositfile, true);
		return message;
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
