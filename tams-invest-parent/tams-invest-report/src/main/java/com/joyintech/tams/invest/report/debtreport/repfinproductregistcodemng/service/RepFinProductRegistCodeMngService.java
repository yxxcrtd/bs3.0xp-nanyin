/*
 * 文件名：RepFinProductRegistCodeMngService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年11月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.report.debtreport.repfinproductregistcodemng.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.tams.common.base.entity.PaPrdIssueSchedule;
import com.joyintech.tams.common.base.entity.RepProdDeclare;
import com.joyintech.tams.common.base.entity.RepProdIssue;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExcelUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;
import com.joyintech.tams.invest.report.debtreport.repfinproductregistcodemng.dto.RepFinProductRegistCodeMngDto;

/**
 * 产品登记编码管理
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年11月19日
 * @see RepFinProductRegistCodeMngService
 * @since 1.0
 */
@Service
public class RepFinProductRegistCodeMngService extends CommonExportService<RepFinProductRegistCodeMngDto>{
    
    /**
     * 
     * 产品名称下拉框
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getFinprodMarketId(Map<String, Object> param){
        return restTemplate.postForList(ServiceApplication.REPORT_SERVICE_NAME
            + "/RepFinProductRegistCodeMng/getFinprodMarketId",param,
            SelectOptionDto.class);
    }
    
    /**
     * 
     * 产品登记编码下拉框
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getPordRegistCode(Map<String, Object> param){
        return restTemplate.postForList(ServiceApplication.REPORT_SERVICE_NAME
            + "/RepFinProductRegistCodeMng/getPordRegistCode",param,
            SelectOptionDto.class);
    }
    
    /**
     * 
     * 页面查询
     * <功能详细描述>
     * @param pageParam Map<String, Object>
     * @return Page<RepFinProductRegistCodeMngDto>
     * @see
     * @since 1.0
     */
    public Page<RepFinProductRegistCodeMngDto> select(Map<String, Object> pageParam){
        return restTemplate.postForPage(
            ServiceApplication.REPORT_SERVICE_NAME + "/RepFinProductRegistCodeMng/findPage",
            pageParam, RepFinProductRegistCodeMngDto.class);
    }
    
    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/report/debtreport/repfinproductregistcodemng/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    
    @Override
    public Page<RepFinProductRegistCodeMngDto> selectPageData(Map<String, Object> selectParam) {
        return select(selectParam);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if(StringUtil.equals("vdate", propName)||StringUtil.equals("mdate", propName)) {
            cell.setCellValue(DateUtil.format((Date)value));
        }
    }
    
    /**
     * 
     * 产品代码在理财产品排期表里面不存在
     * <功能详细描述>
     * @param finprodMarketId String
     * @return int
     * @see
     * @since 1.0
     */
    public int check1(String finprodMarketId){
        return restTemplate.getForObject(
            ServiceApplication.REPORT_SERVICE_NAME + "/RepFinProductRegistCodeMng/check1?finprodMarketId="+finprodMarketId,
            Integer.class);
    }
    
    /**
     * 
     * 产品登记编码在理财产品排期表里面已存在
     * <功能详细描述>
     * @param prodRegistCode String
     * @return int
     * @see
     * @since 1.0
     */
    public int check2(String prodRegistCode){
        return restTemplate.getForObject(
            ServiceApplication.REPORT_SERVICE_NAME + "/RepFinProductRegistCodeMng/check2?prodRegistCode="+prodRegistCode,
            Integer.class);
    }
    
    /**
     * 
     * 产品代码在理财产品排期表的产品登记编码和该行的产品登记编码不一样 
     * <功能详细描述>
     * @param finprodMarketId String
     * @param prodRegistCode String
     * @return int
     * @see
     * @since 1.0
     */
    public int check3(String finprodMarketId, String prodRegistCode){
        return restTemplate.getForObject(
            ServiceApplication.REPORT_SERVICE_NAME + 
            "/RepFinProductRegistCodeMng/check3?finprodMarketId="+finprodMarketId+"&prodRegistCode="+prodRegistCode,
            Integer.class);
    }
    
    /**
     * 
     * 更新理财产品排期
     * <功能详细描述>
     * @param paPrdIssueSchedule PaPrdIssueSchedule
     * @return int
     * @see
     * @since 1.0
     */
    public int updatePaPrdIssueSchedule(List<PaPrdIssueSchedule> paPrdIssueSchedule){
        return restTemplate.postForObject(
            ServiceApplication.REPORT_SERVICE_NAME + 
            "/RepFinProductRegistCodeMng/updatePaPrdIssueSchedule",paPrdIssueSchedule,
            Integer.class);
    }
    
    /**
     * 
     * 更新产品申报报送信息
     * <功能详细描述>
     * @param repProdDeclare RepProdDeclare
     * @return int
     * @see
     * @since 1.0
     */
    public int updateRepProdDeclare(List<RepProdDeclare> repProdDeclare){
        return restTemplate.postForObject(
            ServiceApplication.REPORT_SERVICE_NAME + 
            "/RepFinProductRegistCodeMng/updateRepProdDeclare",repProdDeclare,
            Integer.class);
    }
    
    /**
     * 
     * 更新产品发行报送信息
     * <功能详细描述>
     * @param repProdIssue RepProdIssue
     * @return int
     * @see
     * @since 1.0
     */
    public int updateRepProdIssue(List<RepProdIssue> repProdIssue){
        return restTemplate.postForObject(
            ServiceApplication.REPORT_SERVICE_NAME + 
            "/RepFinProductRegistCodeMng/updateRepProdIssue",repProdIssue,
            Integer.class);
    }
    
    /**
     * excel导入功能
     *
     * @param flieName 文件名称
     * @return 检验list
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @Transactional(rollbackFor = Exception.class)
    public List<ExcelValidDto> importData(String flieName)
        throws Exception {
        //记录错误信息
        List<ExcelValidDto> errorMessage = new ArrayList<ExcelValidDto>();
        // 检查文件名是否为空
        if (StringUtil.isNotEmpty(flieName)) {
            ExcelUtil excelUtil = ExcelUtil.build(FileNameUtil.getUploadFilePath(flieName),
                null);
            List<List<String>> dataList = excelUtil.readToList(0, 0, 0);
            //记录正确数据
            List<List<String>> correctList = new ArrayList<List<String>>();
            //记录错误数据
            List<List<String>> errortList = new ArrayList<List<String>>();
            //检查数据重复性
            for(int m=1; m<dataList.size(); m++){
            	ExcelValidDto excelValidDto = new ExcelValidDto();
            	for(int n=m+1; n<dataList.size(); n++ ){
            		if(dataList.get(m).get(1).equals(dataList.get(n).get(1))){
            			String errorStr = getMessage("REP_E_0003", dataList.get(m).get(1));
            			excelValidDto.setErrorInfo(errorStr);
                        excelValidDto.setRowNum(m+"");
                        excelValidDto.setValidKey(dataList.get(m).get(0));
                        errortList.add(dataList.get(m));
                        errorMessage.add(excelValidDto);
                        return errorMessage;
            		}
            	}
            }
            // 检查读出信息是否为空
            if (null != dataList) {
                for(int i=1; i<dataList.size(); i++){
                    ExcelValidDto excelValidDto = new ExcelValidDto();
                    String errorStr = "";
                    //产品代码在理财产品排期表里面不存在
                    if(this.check1(dataList.get(i).get(0))!=1){
                        errorStr = getMessage("REP_W_0002", dataList.get(i).get(0)+",");
                    }
                    //产品登记编码在理财产品排期表里面已存在
                    if(this.check2(dataList.get(i).get(1))==1){
                        if (StringUtil.isNotEmpty(errorStr)){
                            errorStr = errorStr+";";
                        }
                        errorStr = errorStr + getMessage("REP_E_0003", dataList.get(i).get(1)+",");
                    }
                    //产品代码在理财产品排期表的产品登记编码和该行的产品登记编码不一样 
                    if(this.check3(dataList.get(i).get(0),dataList.get(i).get(1))==1){
                        if (StringUtil.isNotEmpty(errorStr)){
                            errorStr = errorStr+";";
                        }
                        errorStr = errorStr + getMessage("REP_E_0004", dataList.get(i).get(0)+","); 
                    }
                    //产品登记编码长度不是14位或15位报错
                    if(dataList.get(i).get(1).length() !=14 && dataList.get(i).get(1).length() !=15){
                        if (StringUtil.isNotEmpty(errorStr)){
                            errorStr = errorStr+";";
                        }
                        errorStr = errorStr + getMessage("REP_E_0005", dataList.get(i).get(1));
                    }
                    if(StringUtil.isNotEmpty(errorStr)){
                        excelValidDto.setErrorInfo(errorStr);
                        excelValidDto.setRowNum(i+"");
                        excelValidDto.setValidKey(dataList.get(i).get(0));
                        errortList.add(dataList.get(i));
                    }
                    else{
                        correctList.add(dataList.get(i));
                    }
                    if(StringUtil.isNotEmpty(excelValidDto.getErrorInfo())){
                        errorMessage.add(excelValidDto);
                    }
                }
                if(errorMessage.size()>0){
                	return errorMessage;
                }
            }
            //数据入库
            if(correctList.size()>0){
                List<PaPrdIssueSchedule> paPrdIssueScheduleList = new ArrayList<>();
                List<RepProdDeclare> repProdDeclareList = new ArrayList<>();
                List<RepProdIssue> repProdIssueList = new ArrayList<>();
                for(int i=0; i<correctList.size(); i++){
                    //理财产品排期
                    PaPrdIssueSchedule paPrdIssueSchedule = new PaPrdIssueSchedule();
                    paPrdIssueSchedule.setProdRegistCode(correctList.get(i).get(1));
                    paPrdIssueSchedule.setFinprodMarketId(correctList.get(i).get(0));
                    paPrdIssueScheduleList.add(paPrdIssueSchedule);
                    //产品申报报送信息
                    RepProdDeclare repProdDeclare = new RepProdDeclare();
                    repProdDeclare.setRepFinprodRegId(correctList.get(i).get(1));
                    repProdDeclare.setRepFinprodInsId(correctList.get(i).get(0));
                    repProdDeclareList.add(repProdDeclare);
                    //产品发行报送信息
                    RepProdIssue repProdIssue = new RepProdIssue();
                    repProdIssue.setRepFinprodRegId(correctList.get(i).get(1));
                    repProdIssue.setRepBankProdCode(correctList.get(i).get(0));
                    repProdIssueList.add(repProdIssue);
                }
                this.updatePaPrdIssueSchedule(paPrdIssueScheduleList);
                this.updateRepProdDeclare(repProdDeclareList);
                this.updateRepProdIssue(repProdIssueList);
            }
        }
        return null;
    }
    

}
