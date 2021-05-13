/*
 * 文件名：ValProfitQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.valprofitquery.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.*;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.valuation.accounadjustmanage.valprofitquery.dto.ValProfitQuery001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.valuationtablegeneration.dto.ExportFileDto;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.common.dao.BooksetComMapper;
import com.joyintech.tams.valuation.common.service.BooksetCommonService;
import com.joyintech.tams.valuation.valuationparametermanage.withholdingamortize.dto.ValWithholdingAmortizeSet001Dto;
import com.joyintech.tools.util.ExcelCellValueFunction;
import com.joyintech.tools.util.ExcelTools;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.*;

/**
 * 利润表service〉
 * 
 * @author wanghb
 * @version 1.0 2018年9月18日
 * @see ValProfitQuery001Service
 * @since 1.0
 */
@Service
public class ValProfitQuery001Service extends CommonExportService<ValProfitQuery001Dto> {
    
    /**
    * 针对weblogic部署无法获取模版问题
    */
   private static final String TMP_DIR = "/tpl/";
   @Autowired
   private BooksetComMapper  booksetComMapper;
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ValProfitQuery001Service.class);

    /**
     *  html 样式
     */
    private static final String HTML_STYLE = "HTML_STYLE";
    
    /**
     * excel 样式
     */
    private static final String EXCEL_STYLE = "EXCEL_STYLE";
    
    /**
     * 通用联动查询
     * 
     * @param param : dict_code_parent,dict_code_child,item_code_parent
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.OTHER)
    public List<SelectOptionDto> getLinkSelectOption(Map<String, Object> param) {
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+"/ValProfitQuery001/getLinkSelectOption", param, SelectOptionDto.class);
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
        	//update yanya 20190912 年报原控件类型yyyy更为到天yyyy-MM-dd
        	//year = Integer.valueOf(strArr[0]);
            //month += 12;
            //day = 31;
            year = Integer.valueOf(strArr[0]);
            month += Integer.valueOf(strArr[1]);
            day = Integer.valueOf(strArr[2]);
           
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
     * 查询利率表数据list
     * 
     * @param pageParam
     * @return
     * @throws ParseException
     * @see
     * @since 1.0
     */
    public List<ValProfitQuery001Dto> selectList(Map<String, Object> pageParam) {
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+"/ValProfitQuery001/selectList", pageParam, ValProfitQuery001Dto.class);
    }
    
    /**
     * 分页查询 并 添加样式
     * @param parameters
     * @return
     * @throws ParseException 
     * @see
     * @since 1.0
     */
    public List<ValProfitQuery001Dto> findPage(Map<String, Object> parameters) throws ParseException {
        List<ValProfitQuery001Dto> dtoList = this.selectList(parameters);
        this.dealStyle(dtoList, HTML_STYLE, String.valueOf(parameters.get("reportDimen"))); // 添加样式
        return dtoList;
    }
    
    /**
     * 处理样式
     * @param list
     * @param style
     * @param reportDimen 报表维度
     * @see
     * @since 1.0
     */
    public void dealStyle(List<ValProfitQuery001Dto> list,  String style, String reportDimen) {
        String prefix1 = "[一二三].*";  // 一级项目前缀 
        String prefix2 = "[\\d].*";   // 二级项目前缀
        if(HTML_STYLE.equals(style)) {// html 样式
            for (ValProfitQuery001Dto dto : list) {
                String project = dto.getProject();
                if(project.matches(prefix1)) {
                    dto.setProject("<strong>" + project + "</strong>");
                }else if(!project.matches(prefix2)) {
                    dto.setProject("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + project);
                }
                
                if (DictCodeListExt2.批处理账套类型_账套组.getValue().equals(reportDimen)) {
                	dto.setProject("<label style='color:blue;'>" + dto.getProject() + "</label>");
                }
            }
        }else if(EXCEL_STYLE.equals(style)){ // excel 样式
            for (ValProfitQuery001Dto dto : list) {
                String project = dto.getProject();
                if( !project.matches(prefix1) && !project.matches(prefix2)) {
                    // \t 在 windows 平台 不起作用。
                    dto.setProject("    " + project);
                }
            }
        }
    }
    
    
    /**
     * 导出 利润表 excel
     * 
     * @param dto
     * @return
     * @throws ParseException
     * @throws IOException
     */
    public ResponseEntity<byte[]> exportExcel(Map<String, Object> params)
            throws ParseException, IOException {
        ExportFileDto exportFileDto = createExcel(params);
        File file = new File(exportFileDto.getFilePath());
        return ExportUtil.export(exportFileDto.getFileName(), file, true);
    }

    /**
     * 利润表 导出逻辑
     * 
     * @param dto
     * @return
     * @throws ParseException
     * @throws IOException
     */
    public ExportFileDto createExcel(Map<String, Object> params) throws ParseException, IOException {

        // 报表日期
        String reportDate = DateUtil.format(analyseDate(params), DateUtil.DEFAULT_DATE_FORMAT_EN);
        List<ValProfitQuery001Dto> dtoList = new ArrayList<ValProfitQuery001Dto>();
        String prefix;
        if (DictCodeListExt2.批处理账套类型_账套.getValue().equals(params.get("reportDimen"))) {
        	// 单账套
        	dtoList = this.selectList(params);
        	prefix = params.get("booksetId").toString();
        } else {
        	// 全账套
        	dtoList = this.selectListAll(params);
        	prefix = "账套组";
        }
        
        this.dealStyle(dtoList, EXCEL_STYLE, String.valueOf(params.get("reportDimen"))); // 添加样式
        
        String exportFilePath = CacheUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
        String fileName = prefix + "_" + reportDate + "_利润表" + ".xls";
        String tempName = "ValProfitQuery.xls";
        String tplFlodPath = "accounadjustmanage" + File.separator + "valprofitquery" + File.separator + "ValProfitQuery.xls";
        String tplPath = FileNameUtil.getTplFile(tplFlodPath);
        String outPath = exportFilePath + File.separator + fileName;

        ExportFileDto exportFileDto = new ExportFileDto();
        InputStream stream = null;
        Map<String, Object> map = new HashMap<>();
       // map.put("org",CacheUtil.getSysParam(GlobalConstant.财务报表_编制单位));
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
                    // 加粗一级项目
                    if (StringUtil.startsWith(tplKey, "dtoList")
                        && tplKey.contains("project")
                        && String.valueOf(value).matches("[一二三].*")) {
                        font.setBold(true);
                        newStyle.setFont(font);
                        cell.setCellStyle(newStyle);
                    }
                }
            });
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
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
     * 分页查询 并 添加样式
     * @param parameters
     * @return
     * @throws ParseException 
     * @see
     * @since 1.0
     */
    public List<ValProfitQuery001Dto> findPageAll(Map<String, Object> parameters){
        List<ValProfitQuery001Dto> dtoList = this.selectListAll(parameters);
        this.dealStyle(dtoList, HTML_STYLE, String.valueOf(parameters.get("reportDimen"))); // 添加样式
        return dtoList;
    }
    
    /**
     * 查询利率表数据list
     * 
     * @param pageParam
     * @return
     * @throws ParseException
     * @see
     * @since 1.0
     */
    public List<ValProfitQuery001Dto> selectListAll(Map<String, Object> pageParam) {
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+"/ValProfitQuery001/selectListAll", pageParam, ValProfitQuery001Dto.class);
    }
    
    /**
     * 获取默认日期信息
     * 
     * @param pageParam
     * @return ValProfitQuery001Dto
     */
    public ValProfitQuery001Dto getDateInfo(Map<String, Object> pageParam) {
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME
                                          + "/ValProfitQuery001/getDateInfo",
            pageParam, ValProfitQuery001Dto.class);
    }

}
