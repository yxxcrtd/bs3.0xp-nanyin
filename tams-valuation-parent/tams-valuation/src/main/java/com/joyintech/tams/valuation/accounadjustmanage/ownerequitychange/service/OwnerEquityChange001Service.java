/*
 * 文件名：OwnerEquityChange001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangzhao
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.ownerequitychange.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.*;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.valuation.accounadjustmanage.ownerequitychange.dto.OwnerEquityChange001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.valuationtablegeneration.dto.ExportFileDto;
import com.joyintech.tams.valuation.common.batch.finproduct.service.FinProductCommonBatchService;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.common.dao.BooksetComMapper;
import com.joyintech.tools.util.ExcelTools;

/**
 * @author zhangzhao
 * @version 1.0 2018年9月18日
 * @see OwnerEquityChange001Service
 * @since 1.0
 */
@Service
public class OwnerEquityChange001Service extends CommonExportService<OwnerEquityChange001Dto> {

    /**
     * 针对weblogic部署无法获取模版问题
     */
    private static final String TMP_DIR = "/tpl/";

    /**
     * 标的类共通方法
     */
    @Autowired
    private FinProductCommonBatchService finProductCommonBatchService;
    @Autowired
    private BooksetComMapper  booksetComMapper;
    /**
     * 分页查询
     * 
     * @param pageParam
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public List<OwnerEquityChange001Dto> selectList(Map<String, Object> pageParam) {
        // 调用共通方法【详细设计-标的类共通方法设计】-核算主体查询
        FinProduct finProduct = finProductCommonBatchService.queryAcctSubject(
            (String)pageParam.get("booksetId"));
        if(finProduct != null&& finProduct.getVdate() != null) {
            pageParam.put("vdateStr", DateFormatUtils.ISO_DATE_FORMAT.format(finProduct.getVdate()));
        }

        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+"/OwnerEquityChange001/selectList", pageParam, OwnerEquityChange001Dto.class);
    }

    /**
     * 设置页面传回参数
     * 
     * @param pageParam
     * @throws Exception
     * @see
     * @since 1.0
     */
    public void changeMapData(Map<String, Object> pageParam)
        throws Exception {
        String reportTermType = (String)pageParam.get("reportTermType");
        String valDate = DateUtil.format(analyseDate(pageParam), DateUtil.DEFAULT_DATE_FORMAT_EN);
        int thisYear = DateUtil.getYear(valDate);
        int lastYear = thisYear - 1;
        int yearBeforeLast = thisYear - 2;
        String reportTerm = (String)pageParam.get("reportTerm");
        String lastDimen = (String)pageParam.get("lastDimen");
        if (StringUtil.equals(reportTermType, DictCodeListExt1.报表期限类型_日报.getValue())) {

            pageParam.put("thisTermMdate", valDate); // 本期
            pageParam.put("thisTermVdate", DateUtil.addDay(valDate, -1));// 期初
            if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_环比.getValue())) {
                pageParam.put("lastTermMdate", DateUtil.addDay(valDate, -1)); // 上期本期
                pageParam.put("lastTermVdate", DateUtil.addDay(valDate, -2)); // 上期期初
            }
            else if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_同比.getValue())) {
                pageParam.put("lastTermMdate",
                    DateUtil.format(DateUtil.addYear(DateUtil.parse(valDate), -1),
                        DateUtil.DEFAULT_DATE_FORMAT_EN)); // 上期本期
                pageParam.put("lastTermVdate",
                    DateUtil.format(DateUtil.addDate(DateUtil.parse(valDate), -1, 0, -1),
                        DateUtil.DEFAULT_DATE_FORMAT_EN)); // 上期期初
            }
        }
        else if (StringUtil.equals(reportTermType, DictCodeListExt1.报表期限类型_月报.getValue())) {
            pageParam.put("thisTermMdate", DateUtil.format(
                DateUtil.getLastDay(DateUtil.parse(valDate)), DateUtil.DEFAULT_DATE_FORMAT_EN)); // 本期
            pageParam.put("thisTermVdate",
                DateUtil.format(
                    DateUtil.getLastDay(DateUtil.addMonthDate(DateUtil.parse(valDate), -1)),
                    DateUtil.DEFAULT_DATE_FORMAT_EN)); // 期初
            if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_环比.getValue())) {
                pageParam.put("lastTermMdate",
                    DateUtil.format(
                        DateUtil.getLastDay(DateUtil.addMonthDate(DateUtil.parse(valDate), -1)),
                        DateUtil.DEFAULT_DATE_FORMAT_EN)); // 上期本期
                pageParam.put("lastTermVdate",
                    DateUtil.format(
                        DateUtil.getLastDay(DateUtil.addMonthDate(DateUtil.parse(valDate), -2)),
                        DateUtil.DEFAULT_DATE_FORMAT_EN)); // 上期期初
            }
            else if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_同比.getValue())) {
                pageParam.put("lastTermMdate",
                    DateUtil.format(
                        DateUtil.getLastDay(DateUtil.addYear(DateUtil.parse(valDate), -1)),
                        DateUtil.DEFAULT_DATE_FORMAT_EN)); // 上期本期
                pageParam.put("lastTermVdate",
                    DateUtil.format(
                        DateUtil.getLastDay(DateUtil.addDate(DateUtil.parse(valDate), -1, -1, 0)),
                        DateUtil.DEFAULT_DATE_FORMAT_EN)); // 上期期初
            }
        }
        else if (StringUtil.equals(reportTermType, DictCodeListExt1.报表期限类型_季报.getValue())) {
            if (StringUtil.equals(reportTerm, DictCodeListExt1.季度_第一季度.getValue())) {
                pageParam.put("thisTermVdate", lastYear + "-12-31"); // 期初
                pageParam.put("thisTermMdate", thisYear + "-03-31"); // 本期
                if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_环比.getValue())) {
                    pageParam.put("lastTermVdate", lastYear + "-09-30"); // 期初
                    pageParam.put("lastTermMdate", lastYear + "-12-31"); // 本期
                }
                else if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_同比.getValue())) {
                    pageParam.put("lastTermVdate", yearBeforeLast + "-12-31"); // 期初
                    pageParam.put("lastTermMdate", lastYear + "-03-31"); // 本期
                }
            }
            else if (StringUtil.equals(reportTerm, DictCodeListExt1.季度_第二季度.getValue())) {
                pageParam.put("thisTermVdate", thisYear + "-03-31"); // 期初
                pageParam.put("thisTermMdate", thisYear + "-06-30"); // 本期
                if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_环比.getValue())) {
                    pageParam.put("lastTermVdate", lastYear + "-12-31"); // 期初
                    pageParam.put("lastTermMdate", thisYear + "-03-31"); // 本期
                }
                else if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_同比.getValue())) {
                    pageParam.put("lastTermVdate", lastYear + "-03-31"); // 期初
                    pageParam.put("lastTermMdate", lastYear + "-06-30"); // 本期
                }
            }
            else if (StringUtil.equals(reportTerm, DictCodeListExt1.季度_第三季度.getValue())) {
                pageParam.put("thisTermVdate", thisYear + "-06-30");
                pageParam.put("thisTermMdate", thisYear + "-09-30");
                if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_环比.getValue())) {
                    pageParam.put("lastTermVdate", thisYear + "-03-31");
                    pageParam.put("lastTermMdate", thisYear + "-06-30");
                }
                else if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_同比.getValue())) {
                    pageParam.put("lastTermVdate", lastYear + "-06-30");
                    pageParam.put("lastTermMdate", lastYear + "-09-30");
                }
            }
            else {
                pageParam.put("thisTermVdate", thisYear + "-09-30");
                pageParam.put("thisTermMdate", thisYear + "-12-31");
                if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_环比.getValue())) {
                    pageParam.put("lastTermVdate", thisYear + "-06-30");
                    pageParam.put("lastTermMdate", thisYear + "-09-30");
                }
                else if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_同比.getValue())) {
                    pageParam.put("lastTermVdate", lastYear + "-09-30");
                    pageParam.put("lastTermMdate", lastYear + "-12-31");
                }
            }
        }
        else if (StringUtil.equals(reportTermType, DictCodeListExt1.报表期限类型_半年报.getValue())) {
            if (StringUtil.equals(reportTerm, DictCodeListExt1.半年_上半年.getValue())) {
                pageParam.put("thisTermVdate", lastYear + "-12-31");
                pageParam.put("thisTermMdate", thisYear + "-06-30");
                if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_环比.getValue())) {
                    pageParam.put("lastTermVdate", lastYear + "-06-30");
                    pageParam.put("lastTermMdate", lastYear + "-12-31");
                }
                else if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_同比.getValue())) {
                    pageParam.put("lastTermVdate", yearBeforeLast + "-12-31");
                    pageParam.put("lastTermMdate", lastYear + "-06-30");
                }
            }
            else {
                pageParam.put("thisTermVdate", thisYear + "-06-30");
                pageParam.put("thisTermMdate", thisYear + "-12-31");
                if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_环比.getValue())) {
                    pageParam.put("lastTermVdate", lastYear + "-12-31");
                    pageParam.put("lastTermMdate", thisYear + "-06-30");
                }
                else if (StringUtil.equals(lastDimen, DictCodeListExt1.上期维度_同比.getValue())) {
                    pageParam.put("lastTermVdate", lastYear + "-06-30");
                    pageParam.put("lastTermMdate", lastYear + "-12-31");
                }
            }
        }
        else {
            pageParam.put("thisTermVdate", lastYear + "-12-31");
            pageParam.put("thisTermMdate", thisYear + "-12-31");
            pageParam.put("lastTermVdate", yearBeforeLast + "-12-31");
            pageParam.put("lastTermMdate", lastYear + "-12-31");
        }
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
        String reportDate = (String)pageParam.get("valDate");
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
     * <一句话功能简述> <功能详细描述>
     * 
     * @param params
     * @return
     * @throws IOException
     * @see
     * @since 1.0
     */
    public ResponseEntity<byte[]> exportExcel(Map<String, Object> params)
        throws Exception {
        ExportFileDto exportFileDto = createExcel(params);
        File file = new File(exportFileDto.getFilePath());
        return ExportUtil.export(exportFileDto.getFileName(), file, true);
    }

    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @param params
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    private ExportFileDto createExcel(Map<String, Object> params) {
        // 报表日期
        String reportDate = DateUtil.format(analyseDate(params), DateUtil.DEFAULT_DATE_FORMAT_EN);
        // 导出数据集
        List<OwnerEquityChange001Dto> dtoList = selectList(params);
        String exportFilePath = CacheUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
        String prefix;
        if (DictCodeListExt2.批处理账套类型_账套.getValue().equals(params.get("reportDimen"))) {
        	// 单账套
        	prefix = params.get("booksetId").toString();
        } else {
        	// 全账套
        	prefix = "账套组";
        }
        String fileName = prefix+ "_" + reportDate + "_所有者权益变动表" + ".xls";

        
        
       // String fileName = params.get("booksetId").toString() + "_" + reportDate + "_所有者权益变动表" + ".xls";
                         
        String tempName = "OwnerEquityChange001.xls";
        String tplFlodPath = "accounadjustmanage/ownerequitychange/OwnerEquityChange001.xls";
        String tplPath = FileNameUtil.getTplFile(tplFlodPath);
        String outPath = exportFilePath + File.separator + fileName;

        ExportFileDto exportFileDto = new ExportFileDto();
        InputStream stream = null;
        Map<String, Object> map = new HashMap<String, Object>();
       // map.put("org", CacheUtil.getSysParam(GlobalConstant.财务报表_编制单位));
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
        map.put("reportTermType", SysDataUtil.getNameFromDataDict(DictCode.报表期限类型,
            params.get("reportTermType").toString()));
        exportFileDto.setFileName(fileName);
        exportFileDto.setFilePath(outPath);
        try {

            // 针对weblogic环境部署，FileNameUtil.getTplFile无法获取tpl文件夹下模版。
            File templeteFile = new File(tplPath);
            // 文件存在情况,直接该路径获取文件流
            if (templeteFile.exists()) {
                stream = new FileInputStream(templeteFile);
            }
            // 文件不存在情况，即weblogic部署情况，需要换方式获取文件流。
            else {
                stream = this.getClass().getResourceAsStream(TMP_DIR + tplFlodPath);
            }

            ExcelTools.writeExcel(stream, tempName, map, outPath);
        }
        catch (IOException e) {
        }
        finally {
            if (stream != null) {
                try {
                    stream.close();
                }
                catch (IOException e) {
                }
            }
        }
        return exportFileDto;
    }
    
    /**
     * 获取默认日期信息
     * 
     * @param pageParam
     * @return OwnerEquityChange001Dto
     */
    public OwnerEquityChange001Dto getDateInfo(Map<String, Object> pageParam) {
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME
                                          + "/OwnerEquityChange001/getDateInfo",
            pageParam, OwnerEquityChange001Dto.class);
    }

}
