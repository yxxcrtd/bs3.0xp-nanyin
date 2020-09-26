/*
 * 文件名：CashFlowsStatement001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：QuJianZhong
 * 修改时间：2018年10月22日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.cashflowsstatement.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.accounadjustmanage.cashflowsstatement.dao.CashFlowsStatement001Mapper;
import com.joyintech.tams.valuation.accounadjustmanage.cashflowsstatement.dto.CashFlowsDto;
import com.joyintech.tams.valuation.accounadjustmanage.cashflowsstatement.dto.CashFlowsStatement001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.valuationtablegeneration.dto.ExportFileDto;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.common.dao.BooksetComMapper;
import com.joyintech.tools.util.ExcelCellValueFunction;
import com.joyintech.tools.util.ExcelTools;

/**
 * @author QuJianZhong
 * @version 1.0 2018年9月18日
 * @see CashFlowsStatement001Service
 * @since 1.0
 */
@Service
public class CashFlowsStatement001Service extends CommonExportService<CashFlowsStatement001Dto> {

    @Autowired
    private CashFlowsStatement001Mapper cashFlowsStatement001Mapper;
    @Autowired
    private BooksetComMapper  booksetComMapper;
    /**
    * 针对weblogic部署无法获取模版问题
    */
   private static final String TMP_DIR = "/tpl/";
    
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(CashFlowsStatement001Service.class);
    
    /**
     * 查询现金流量表数据list
     * 
     * @param pageParam
     * @return
     * @throws ParseException
     * @see
     * @since 1.0
     */
    public List<CashFlowsStatement001Dto> selectList(Map<String, Object> pageParam) {
        
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+"/CashFlowsStatement001/findPage", pageParam, CashFlowsStatement001Dto.class);
    }





    /**
     * 分析余额日期
     * 
     * @param pageParam 查询条件
     * @param balanceType 期末、年初
     * @return
     * @throws ParseException
     */
    public Date analyseDate(Map<String, Object> pageParam) {

        String reportTermType = (String)pageParam.get("reportTermType");
        String reportDate = (String)pageParam.get("reportDate");
        String reportTerm = (String)pageParam.get("reportTerm");

        int year = 0;
        int month = -1;
        int day = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);// 时
        calendar.set(Calendar.MINUTE, 0);// 分
        calendar.set(Calendar.SECOND, 0);// 秒
        calendar.set(Calendar.MILLISECOND, 0);// 毫秒

        String[] strArr = reportDate.split(",")[0].split("-");
        if (DictCodeListExt1.报表期限类型_年报.getValue().equals(reportTermType)) {// 年底
            year = Integer.valueOf(strArr[0]);
            month += 12;
            day = 31;
        }
        if (DictCodeListExt1.报表期限类型_半年报.getValue().equals(reportTermType)) {
            year = Integer.valueOf(strArr[0]);
            if (DictCodeListExt1.半年_上半年.getValue().equals(reportTerm)) {
                month += 6;
                day = 30;
            }
            else {
                month += 12;
                day = 31;
            }
        }
        if (DictCodeListExt1.报表期限类型_季报.getValue().equals(reportTermType)) {
            year = Integer.valueOf(strArr[0]);
            if (DictCodeListExt1.季度_第一季度.getValue().equals(reportTerm)) {
                month += 3;
                day = 31;
            }
            else if (DictCodeListExt1.季度_第二季度.getValue().equals(reportTerm)) {
                month += 6;
                day = 30;
            }
            else if (DictCodeListExt1.季度_第三季度.getValue().equals(reportTerm)) {
                month += 9;
                day = 30;
            }
            else {
                month += 12;
                day = 31;
            }
        }
        if (DictCodeListExt1.报表期限类型_月报.getValue().equals(reportTermType)) {// 月底：当前月的下个月的第0天
            year = Integer.valueOf(strArr[0]);
            month += Integer.valueOf(strArr[1]) + 1;
            day = 0;
        }
        if (DictCodeListExt1.报表期限类型_日报.getValue().equals(reportTermType)) {
            year = Integer.valueOf(strArr[0]);
            month += Integer.valueOf(strArr[1]);
            day = Integer.valueOf(strArr[2]);
        }

        calendar.set(year, month, day);
        return calendar.getTime();

    }
    
    /**
     * 联动：报表期限类型 - 季度 半年
     * 
     * @return
     * @throws Exception
     */
    public List<SelectOptionDto> getTradeMarket(Map<String, Object> param) {
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+"/CashFlowsStatement001/getTradeMarket", param, SelectOptionDto.class);
    }

    /**
     * 通用联动查询
     * 
     * @param param : dict_code_parent,dict_code_child,item_code_parent
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.OTHER)
    public List<SelectOptionDto> getLinkSelectOption(Map<String, Object> param) {
        return cashFlowsStatement001Mapper.getLinkSelectOption(param);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param params 
     * @return 
     * @throws IOException 
     * @throws ParseException 
     * @see
     * @since 1.0
     */
    public ResponseEntity<byte[]> exportExcel(Map<String, Object> params) throws IOException, ParseException {
        ExportFileDto exportFileDto = createExcel(params);
        File file = new File(exportFileDto.getFilePath());
        return ExportUtil.export(exportFileDto.getFileName(), file, true);
        
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param params 
     * @return
     * @throws ParseException 
     * @see
     * @since 1.0
     */
    private ExportFileDto createExcel(Map<String, Object> params) throws ParseException {
        // 报表日期
        String reportDate = DateUtil.format(analyseDate(params), DateUtil.DEFAULT_DATE_FORMAT_EN);
        // 导出数据集
        List<CashFlowsStatement001Dto> dtoList = selectList(params);
        String exportFilePath = CacheUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
        String prefix;
        if (DictCodeListExt2.批处理账套类型_账套.getValue().equals(params.get("reportDimen"))) {
        	// 单账套
        	prefix = params.get("booksetId").toString();
        } else {
        	// 全账套
        	prefix = "账套组";
        }
        
        String fileName = prefix + "_" + reportDate + "_现金流量表" + ".xls";
       // String fileName = params.get("booksetId").toString() + "_" + reportDate + "_现金流量表" + ".xls";
        String tempName = "CashFlowsStatement.xls";
        String tplFlodPath = "accounadjustmanage/cashflowsstatement/CashFlowsStatement.xls";
        String tplPath = FileNameUtil.getTplFile(tplFlodPath);
        String outPath = exportFilePath + File.separator + fileName;

        ExportFileDto exportFileDto = new ExportFileDto();
        InputStream stream = null;
        Map<String, Object> map = new HashMap<>();
        //map.put("org",CacheUtil.getSysParam(GlobalConstant.财务报表_编制单位));
        //多级法人改造start add by hxy 20200612
        if(MultilevelDataAuthorityUtil.isEnabled()) {
        	 //联社模式
        	 //根据选择的账套组或者账套，查询出它们的所属机构
        	String booksetOrg;
        	if (DictCodeListExt2.批处理账套类型_账套组.getValue().equals((String)params.get("reportDimen"))) {
        		booksetOrg=(String)params.get("booksetGroup");
        		Map<String, Object> orgmap=new HashMap<String, Object>();
        		orgmap.put("booksetOrg", booksetOrg);
        		orgmap.put("dataType", "V08");
        		String orgName=booksetComMapper.selectAssociationModeOrgGrpName(orgmap);
            	map.put("org", orgName);
        	}else{
        		booksetOrg=(String)params.get("booksetId");
        		Map<String, Object> orgmap=new HashMap<String, Object>();
        		orgmap.put("booksetOrg", booksetOrg);
        		orgmap.put("dataType", "V08");
        		String orgName=booksetComMapper.selectAssociationModeOrgName(orgmap);
            	map.put("org", orgName);
        	}
        	}else{
        	//银行模式
        	String orgName=booksetComMapper.selectBankModeOrgName();
        	map.put("org", orgName);
        	
        }
        //多级法人改造end add by hxy 20200612
        map.put("dtoList", dtoList);
        map.put("reportDate", reportDate);
        map.put("reportTermType", SysDataUtil.getNameFromDataDict(DictCode.报表期限类型, params.get("reportTermType").toString()));
        exportFileDto.setFileName(fileName);
        exportFileDto.setFilePath(outPath);
        try {
            
            //针对weblogic环境部署，FileNameUtil.getTplFile无法获取tpl文件夹下模版。
            File templeteFile = new File(tplPath);
            //文件存在情况,直接该路径获取文件流
            if (templeteFile.exists()) {
                stream = new FileInputStream(templeteFile);
            }
            //文件不存在情况，即weblogic部署情况，需要换方式获取文件流。
            else {
                stream = this.getClass().getResourceAsStream(TMP_DIR + tplFlodPath);
            }
            
            ExcelTools.writeExcel(stream, tempName, map, outPath, new ExcelCellValueFunction() {
                
                @Override
                public void execute(String tplKey, Object value, Cell cell, Row row, Sheet sheet, Workbook workbook) {
                    
                    CellStyle newStyle = workbook.createCellStyle();
                    newStyle.cloneStyleFrom(cell.getCellStyle());
                    Font font = workbook.createFont();
                    // 部分标题加粗
                    if (StringUtil.startsWith(tplKey, "dtoList")
                        && tplKey.contains("project")
                        && String.valueOf(value).endsWith("活动产生的现金流量")) {
                        font.setBold(true);
                        newStyle.setFont(font);
                        cell.setCellStyle(newStyle);
                    }
                }
            });
        } catch (IOException e) {
        	logger.error("",e);
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                	logger.error("",e);
                }
            }
        }
        return exportFileDto;
    }





    /**
     * 查询现金流量表数据list
     *
     * @param pageParam
     * @return
     * @throws ParseException
     * @see
     * @since 1.0
     */
    public List<CashFlowsStatement001Dto> selectListp(Map<String, Object> pageParam, CashFlowsStatement001Dto dto) {

        pageParam.put("CashFlowsStatement001Dto",dto);

        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+"/CashFlowsStatement001/selectListp",
                pageParam, CashFlowsStatement001Dto.class);
    }

    /**
     * 获取默认日期信息
     * 
     * @param pageParam
     * @return CashFlowsStatement001Dto
     */
    public CashFlowsStatement001Dto getDateInfo(Map<String, Object> pageParam) {
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME
                                          + "/CashFlowsStatement001/getDateInfo",
            pageParam, CashFlowsStatement001Dto.class);
    }
    
}
