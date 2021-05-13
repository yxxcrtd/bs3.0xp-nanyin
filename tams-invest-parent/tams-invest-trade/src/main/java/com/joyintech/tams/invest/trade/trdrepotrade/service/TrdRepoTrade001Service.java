/*
 * 文件名：TrdRepoTrade001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年12月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.trade.trdrepotrade.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.trade.trdrepotrade.dto.TrdRepoTrade001Dto;
import com.joyintech.tams.invest.trade.trdrepotrade.dto.TrdRepoTradeExportMail001Dto;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TrdRepoTrade001Service extends CommonExportService<TrdRepoTrade001Dto> {
    
    /**
    * 针对weblogic部署无法获取模版问题
    */
   private static final String TMP_DIR = "/tpl/";
    /**
     * 检索回购资产交易信息
     *
     * @param pageParam 查询参数
     * @return
     * @see
     * @since 1.0
     */
    public Page<TrdRepoTrade001Dto> selectForPage(Map<String, Object> pageParam) {
        // 多选下拉框值处理
        // 组合代码
        Object portfolioIdObj = pageParam.get("portfolioId");
        if (portfolioIdObj != null) {
            pageParam.put("portfolioIdList",
                    StringUtil.splitToList(GlobalConstant.COMMA, (String) portfolioIdObj));
        } else {
            pageParam.put("portfolioIdList", new ArrayList<>());
        }

        //多选下拉框处理（抵押品代码）
		Object finprodIdObj = pageParam.get("finprodId");
        if (finprodIdObj != null){
        	pageParam.put("finprodIdList",
					StringUtil.splitToList(GlobalConstant.COMMA,(String) finprodIdObj));
		}else {
        	pageParam.put("finprodIdList",new ArrayList<>());
		}

		//多选下拉框处理（合作资产）
		Object chlFinprodIdObj = pageParam.get("chlFinprodId");
		if (chlFinprodIdObj != null){
			pageParam.put("chlFinprodIdList",
					StringUtil.splitToList(GlobalConstant.COMMA,(String) chlFinprodIdObj));
		}else {
			pageParam.put("chlFinprodIdList",new ArrayList<>());
		}

		pageParam.put("BRANCH_0", CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE));
        pageParam.put("loginUserName", LoginUserUtil.getUserName());

        return restTemplate.postForPage(ServiceApplication.TRADE_SERVICE_NAME + "/TrdRepoTrade/selectForPage",
                pageParam, TrdRepoTrade001Dto.class);
    }

    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/invest/trade/trdrepotrade/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<TrdRepoTrade001Dto> selectPageData(Map<String, Object> selectParam) {
        return this.selectForPage(selectParam);
    }

    /**
     * 根据交易编号查询流程中数据是否存在
     *
     * @param tradeId 交易编号
     * @return 流程中数据是否存在
     * @see
     * @since 1.0
     */
    public Boolean isInFlowDataExisted(String tradeId) {
        return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME
                        + "/TrdRepoTrade/isInFlowDataExisted/" + tradeId,
                null, Boolean.class);
    }

    /**
     * 根据交易编号查询该交易是否已被撤销
     *
     * @param tradeId 交易编号
     * @return 该交易是否已被撤销
     * @see
     * @since 1.0
     */
    public Boolean isRevokeDataExisted(String tradeId) {
        return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME
                        + "/TrdRepoTrade/isAlreadyRevoked/" + tradeId,
                null, Boolean.class);
    }

    /**
     * 导出正回购邮件模版excel
     *
     * @param selectParam selectParam
     * @return 正回购邮件模版excel
     * @see
     * @since 1.0
     */
    public ResponseEntity<byte[]> exportMailModel(Map<String, Object> selectParam) throws IOException {
    	Workbook wb =null;
	    InputStream stream = null;
		FileOutputStream out = null;
        try {
			selectParam.put("branchNo", Long.valueOf(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
			List<TrdRepoTradeExportMail001Dto> finDealPledgeList = restTemplate.postForList(ServiceApplication.TRADE_SERVICE_NAME
			                + "/TrdRepoTrade/selectFinDealPledge",
			        selectParam, TrdRepoTradeExportMail001Dto.class);

			if (!StringUtil.isEmptyList(finDealPledgeList)) {
			    int maxPledgeCount = finDealPledgeList.get(0).getMaxPledgeCount();
			    String fileName = "TrdRepoTrade001MailModel.xlsx";
			    File tempFile = new File(this.buildTempleteFilePath(fileName));
			    // 模板输入流
			    if (tempFile.exists()) {
			        stream = new FileInputStream(tempFile);
			    }
			    else {
			        stream = this.getClass().getResourceAsStream(
			           TMP_DIR + fileName);
			    }
			    wb = new XSSFWorkbook(stream);

			    Sheet sheet = wb.getSheetAt(0);
			    for (int i = 0; i < maxPledgeCount; i++) {
			        sheet.getRow(0).createCell(i + 6).setCellValue("质押债券" + (i + 1));
			        sheet.getRow(0).getCell(i + 6).
			                setCellStyle(sheet.getRow(0).getCell(0).getCellStyle());
			    }
			    for (int i = 0; i < finDealPledgeList.size(); i++) {
			        TrdRepoTradeExportMail001Dto finDealPledge = finDealPledgeList.get(i);
			        if (i > 0) {
			            sheet.createRow(i + 1);
			        }
			        Row row = sheet.getRow(i + 1);
			        if (row.getCell(0) == null) {
			            row.createCell(0).setCellStyle(sheet.getRow(i).getCell(0).getCellStyle());
			        }
			        row.getCell(0).setCellValue(finDealPledge.getCounter());

			        if (row.getCell(1) == null) {
			            row.createCell(1).setCellStyle(sheet.getRow(i).getCell(1).getCellStyle());
			        }
			        row.getCell(1).setCellValue(finDealPledge.getTermDay());

			        if (row.getCell(2) == null) {
			            row.createCell(2).setCellStyle(sheet.getRow(i).getCell(2).getCellStyle());
			        }
			        row.getCell(2).setCellValue(finDealPledge.getRebackPrice());

			        if (row.getCell(3) == null) {
			            row.createCell(3).setCellStyle(sheet.getRow(i).getCell(3).getCellStyle());
			        }
			        row.getCell(3).setCellValue(finDealPledge.getPrinAmt());

			        if (row.getCell(4) == null) {
			            row.createCell(4).setCellStyle(sheet.getRow(i).getCell(4).getCellStyle());
			        }
			        row.getCell(4).setCellValue(finDealPledge.getVdate());

			        if (row.getCell(5) == null) {
			            row.createCell(5).setCellStyle(sheet.getRow(i).getCell(5).getCellStyle());
			        }
			        row.getCell(5).setCellValue(finDealPledge.getMdate());

			        List<String> lstFinDealPledgeResult = finDealPledge.getLstFinDealPledgeResult();
			        if (!StringUtil.isEmptyList(lstFinDealPledgeResult)) {
			            for (int j = 0; j < lstFinDealPledgeResult.size(); j++) {
			                row.createCell(j + 6).setCellValue(lstFinDealPledgeResult.get(j));
			                row.getCell(j + 6).setCellStyle(row.getCell(0).getCellStyle());
			            }
			        }
			        if (i > 0) {
			            row.setRowStyle(sheet.getRow(i).getRowStyle());
			        }
			    }
			    String exportFilePath = CacheUtil.getSysParam("access_exportfilepath");
			    File file = new File(exportFilePath + File.separator + "xlsx");
			    if (!file.getParentFile().exists()) {
			        file.getParentFile().mkdirs();
			    }
				out = new FileOutputStream(file);
			    wb.write(out);
			    String exportFileName =  "正回购交易查询" 
			        + GlobalConstant.OPEN_PARENTHESIS + DateUtil.getCurrentDate()
			        + GlobalConstant.CLOSE_PARENTHESIS + GlobalConstant.POINT
			        + FileNameUtil.getFileExtName(file);
			    return ExportUtil.export(exportFileName, file, true);
			}
		} finally {
            if (stream != null) {
            	stream.close();
            }
            if (wb != null) {
            	wb.close();
            }
			if (out != null) {
				out.close();
			}
		}
        return null;
    }
    /**
     * 根据交易编号查询该交易是否由到期续做交易产生
     *
     * @param tradeId 交易编号
     * @return 该交易是否为到期限续做建的交易
     * @see
     * @since 1.0
     */
    public Boolean isDqTrdProductInfo(String tradeId) {
        return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME
                        + "/TrdRepoTrade/isDqTrdProductInfo/" + tradeId,
                null, Boolean.class);
    }

	/**
	 * 获取交易代码
	 * @param map 请求参数
	 * @return 联想文本框实体类
	 * @see
	 * @since 1.0
	 */
	public List<SuggestDto> getEntrustIdByType(Map<String,Object> map){
		return restTemplate.postForList(
				ServiceApplication.COMMON_SERVICE_NAME + "/CommonSelect/getEntrustId", map,
				SuggestDto.class);
	}
}
