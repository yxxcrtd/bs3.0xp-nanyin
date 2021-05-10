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
import java.text.ParseException;
import java.util.*;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.accounadjustmanage.assetbook.service.ValAssetBook001Service;

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
import com.joyintech.tams.valuation.accounadjustmanage.cashflowsstatement.dao.CashFlowsStatement001Mapper;
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
	@Autowired
    private ValAssetBook001Service valAssetBook001Service;
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

        List<CashFlowsStatement001Dto> list = restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+"/CashFlowsStatement001/findPage",
                pageParam, CashFlowsStatement001Dto.class);
        dealStyle(list, String.valueOf(pageParam.get("STYLE_TYPE")), String.valueOf(pageParam.get("reportDimen")));
        return list;
    }

    /** modify by ynaya on 20201221 指标抽象 start**/
    private final static String 汇总指标后缀 = "活动产生的现金流量";
    private final static String 汇总指标1 = "一、经营活动产生的现金流量";
    private final static String 汇总指标2 = "二、投资活动产生的现金流量";
    private final static String 汇总指标3 = "三、筹资活动产生的现金流量";
    private final static String 汇总指标4 = "四、汇率变动对现金及现金等价物的影响";
    private final static String 汇总指标5 = "五、现金及现金等价物净变动额";
    private final static String 汇总指标6 = "加:期初现金及现金等价物余额";
    private final static String 汇总指标7 = "六、期末现金及现金等价物余额";
    private final static String 小计指标1_1 = "经营活动现金流入小计";
    private final static String 小计指标1_2 = "经营活动现金流出小计";
    private final static String 小计指标1_3 = "经营活动产生的现金流量净额";
    private final static String 小计指标2_1 = "投资活动现金流入小计";
    private final static String 小计指标2_2 = "投资活动现金流出小计";
    private final static String 小计指标2_3 = "投资活动产生的现金流量净额";
    private final static String 小计指标3_1 = "筹资活动现金流入小计";
    private final static String 小计指标3_2 = "筹资活动现金流出小计";
    private final static String 小计指标3_3 = "筹资活动产生的现金流量净额";
    /** modify by ynaya on 20201221 指标抽象 end**/

    /**
     * 处理查询样式
     * @param list
     * @param type
     * @param reportDimen
     */
    private void dealStyle(List<CashFlowsStatement001Dto> list, String type, String reportDimen) {
        if(!StringUtil.isEmptyList(list)) {
            list.stream().forEach(item -> {
                if(StringUtil.isNotEmpty(item.getProject())) {
                    //EXCEL 导出，不处理样式
                    if (StringUtil.equals(type, "EXCEL")) {

                    }
                    //页面查询，处理样式
                    else {
                        if(judgeStrong(item.getProject())) {
                            item.setProject("<strong>" + item.getProject() + "</strong>");
                        }
                        if (StringUtil.equals(reportDimen, DictCodeListExt2.批处理账套类型_账套组.getValue())) {
                            item.setProject( "<label style='color:blue;'>" + item.getProject() + "</label>");
                        }
                    }
                }
            });
        }
    }

    /**
     * 判断是否需要加粗
     * @param project
     * @return
     */
    private boolean judgeStrong(Object project) {
        if(StringUtil.isNotEmpty(project)) {
            String objectStr = project.toString();
            String 一级项目前缀 = "[一二三四五六].*"; // 一级项目前缀
            String 特殊字符 = 汇总指标6;
            if (objectStr.matches(一级项目前缀) || objectStr.indexOf(特殊字符) >= 0) {
                return true;
            }
        }
        return false;
     }

    /**
     * 分析余额日期
     * 
     * @param pageParam 查询条件
     * @param ‘balanceType’ 期末、年初
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
        //modify by yanya on 20201221 处理账套组导出报错问题 start
        String fileName = null;
        /** modify by yanya on 20210114 增加产品代码**/
        String prefix = null;
        if (DictCodeListExt2.批处理账套类型_账套.getValue().equals(String.valueOf(params.get("reportDimen")))) {
            fileName = params.get("booksetId").toString() + "_" + reportDate + "_现金流量表" + ".xls";
            prefix = params.get("booksetId").toString();
        }
        else {
            Map<String, Object> pageParam = new HashMap<>();
            pageParam.put("booksetGroup",params.get("booksetGroup").toString());
            Page<SelectOptionDto> booksetGroup = valAssetBook001Service.findBooksetGroup(pageParam);
            SelectOptionDto optionDto = new SelectOptionDto();
            if (StringUtil.isNotEmpty(booksetGroup) && !StringUtil.isEmptyList(booksetGroup.getRoot())){
                optionDto = booksetGroup.getRoot().get(0);
            }
            fileName = optionDto.getText() + "_" + reportDate + "_现金流量表" + ".xls";
            prefix = optionDto.getText();
        }
        //modify by yanya on 20201221 处理账套组导出报错问题 end
        String tempName = "CashFlowsStatement.xls";
        String tplFlodPath = "accounadjustmanage/cashflowsstatement/CashFlowsStatement.xls";
        String tplPath = FileNameUtil.getTplFile(tplFlodPath);
        String outPath = exportFilePath + File.separator + fileName;

        ExportFileDto exportFileDto = new ExportFileDto();
        InputStream stream = null;
        Map<String, Object> map = new HashMap<>();
        /** modify by yanya on 20210114 增加产品代码**/
        map.put("prefix", prefix);
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
                            //判断加粗方法条件改变，modify by yanya on 20201221
                        && judgeStrong(value)) {
                        font.setBold(true);
                        /** modify by yanya on 20210113 统一字体格式及大小**/
                        font.setFontName("宋体");
                        font.setFontHeightInPoints((short) 11);
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
    //public List<CashFlowsStatement001Dto> selectListp(Map<String, Object> pageParam, CashFlowsStatement001Dto dto) {
    public List<CashFlowsStatement001Dto> selectListp(Map<String, Object> pageParam) {

//        pageParam.put("CashFlowsStatement001Dto",dto);

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
