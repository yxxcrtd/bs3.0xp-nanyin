/*
 * 文件名：RepFinProductExpirationService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年9月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.report.informationdisclosureform.repfinproductexpiration.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.util.ZipFileUtil;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.report.informationdisclosureform.repfinproductestablish.dto.RepFinProductEstablishDto;
import com.joyintech.tams.invest.report.informationdisclosureform.repfinproductexpiration.dto.RepFinProductExpirationDto;
import com.joyintech.tools.util.ExcelTools;

/**
 * 产品到期公告service
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年9月14日
 * @see RepFinProductExpirationService
 * @since 1.0
 */
@Service
public class RepFinProductExpirationService extends CommonExportService<RepFinProductExpirationDto>{
    
    /**
     * 
     * 页面组合代码下拉联想框
     * <功能详细描述>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getSelectOption(){
    	Map<String,Object> param = new HashMap<String,Object>();
    	User user = SecurityUtil.getLoginUser();
        param.put("userId", user.getUserName());
        return restTemplate.postForList(ServiceApplication.REPORT_SERVICE_NAME
            + "/RepFinProductExpiration/getPortfolioId",param,
            SelectOptionDto.class);
    }
    
    /**
     * 
     * 页面查询
     * <功能详细描述>
     * @param pageParam Map<String, Object>
     * @return Page<RepFinProductExpirationDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    public Page<RepFinProductExpirationDto> select(Map<String, Object> pageParam){
        pageParam.put("noBranchNoValue", Long.parseLong(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
        return restTemplate.postForPage(
            ServiceApplication.REPORT_SERVICE_NAME + "/RepFinProductExpiration/findPage",
            pageParam, RepFinProductExpirationDto.class);
    }
    
    /**
     * 
     * 生成公告
     * <功能详细描述>
     * @param dto RepFinProductExpirationDto
     * @return int
     * @throws IOException 
     * @see
     * @since 1.0
     */
    public ResponseEntity<byte[]> generateBulletin(RepFinProductExpirationDto dto) throws IOException{
        List<RepFinProductExpirationDto> list =restTemplate.postForList(
            ServiceApplication.REPORT_SERVICE_NAME + "/RepFinProductExpiration/generateBulletin",
            dto, RepFinProductExpirationDto.class);
        List<File> arrFile = new ArrayList<File>();
        String strFile = null;
        int i = 0;
        for(RepFinProductExpirationDto repFinProductExpirationDto:list){
            //循环List<金融产品成立公告生成信息DTO>，取出文件模板类型为【01：产品成立公告（横表）】的数据，放入横版List<金融产品成立公告生成信息DTO>                                                                                                                                                              
            if(repFinProductExpirationDto.getFileType().equals(DictCodeList.文件类型_投管_产品成立公告_横表.getValue())){
                
                List<SysUploadFileInfo> sysUploadFileInfoList = restTemplate.getForList(
                    ServiceApplication.REPORT_SERVICE_NAME + "/RepFinProductExpiration/getSysUploadFileInfo?bussinessId="+
                        repFinProductExpirationDto.getRevealFileId(),
                    SysUploadFileInfo.class);
                 //将DTO转成Map
                //Map<String,Object> excleMap = JsonUtils.object2Map(repFinProductExpirationDto);
                Map<String,Object> excleMap = new HashMap<String,Object>();
                excleMap.put("prodName", repFinProductExpirationDto.getProdName());
                excleMap.put("prodId", repFinProductExpirationDto.getProdId());
                excleMap.put("ccy", repFinProductExpirationDto.getCcyStr());
                excleMap.put("prodRiskLevel", repFinProductExpirationDto.getProdRiskLevel());
                excleMap.put("profitType", repFinProductExpirationDto.getProfitType());
                excleMap.put("vDate", repFinProductExpirationDto.getVdateStr());
                excleMap.put("mDate", repFinProductExpirationDto.getMdateStr());
                excleMap.put("yield", repFinProductExpirationDto.getYieldStr());
                excleMap.put("trustFee", repFinProductExpirationDto.getTrustFeeStr());
                excleMap.put("saleFee", repFinProductExpirationDto.getSaleFeeStr());
                excleMap.put("investTargetRatio", repFinProductExpirationDto.getInvestTargetRatio());
                excleMap.put("payDay", repFinProductExpirationDto.getPayDayStr());
                strFile = SysDataUtil.getNameFromDataDict(DictCode.文件类型, repFinProductExpirationDto.getFileType());
                String fileName = repFinProductExpirationDto.getFinprodMarketId()+strFile+".xlsx";
                ExcelTools excelTools = new ExcelTools();
                String exportPath = CacheUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
                File file = new File(sysUploadFileInfoList.get(0).getPath());
                FileInputStream inputStream = null;
                try {
                    inputStream = new FileInputStream(file);
                    excelTools.writeExcel(inputStream, sysUploadFileInfoList.get(0).getFileName(), excleMap, exportPath+"/"+fileName);
                    arrFile.add(new File(exportPath+"/"+fileName));
                    i++;
                }finally {
                    if (inputStream != null){
                        inputStream.close();
                    }
                }
            }
            //循环List<金融产品成立公告生成信息DTO>，取出文件模板类型为【02：产品成立公告（竖表）】的数据，放入竖版List<金融产品成立公告生成信息DTO> 
            else{
                
                List<SysUploadFileInfo> sysUploadFileInfoList = restTemplate.getForList(
                    ServiceApplication.REPORT_SERVICE_NAME + "/RepFinProductExpiration/getSysUploadFileInfo?bussinessId="+
                        repFinProductExpirationDto.getRevealFileId(),
                        SysUploadFileInfo.class);
                //将DTO转成Map
                //Map<String,Object> excleMap = JsonUtils.object2Map(repFinProductExpirationDto);
                Map<String,Object> excleMap = new HashMap<String,Object>();
                excleMap.put("prodName", repFinProductExpirationDto.getProdName());
                excleMap.put("prodId", repFinProductExpirationDto.getProdId());
                excleMap.put("ccy", repFinProductExpirationDto.getCcyStr());
                excleMap.put("prodRiskLevel", repFinProductExpirationDto.getProdRiskLevel());
                excleMap.put("profitType", repFinProductExpirationDto.getProfitType());
                excleMap.put("vDate", repFinProductExpirationDto.getVdateStr());
                excleMap.put("mDate", repFinProductExpirationDto.getMdateStr());
                excleMap.put("yield", repFinProductExpirationDto.getYieldStr());
                excleMap.put("trustFee", repFinProductExpirationDto.getTrustFeeStr());
                excleMap.put("saleFee", repFinProductExpirationDto.getSaleFeeStr());
                excleMap.put("payDay", repFinProductExpirationDto.getPayDayStr());
                strFile = SysDataUtil.getNameFromDataDict(DictCode.文件类型, repFinProductExpirationDto.getFileType());
                String fileName = repFinProductExpirationDto.getFinprodMarketId()+strFile+".xlsx";
                ExcelTools excelTools = new ExcelTools();
                String exportPath = CacheUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
                File file = new File(sysUploadFileInfoList.get(0).getPath());
                FileInputStream inputStream = null;
                try {
                    inputStream = new FileInputStream(file);
                    excelTools.writeExcel(inputStream, sysUploadFileInfoList.get(0).getFileName(), excleMap, exportPath+"/"+fileName);
                }finally {
                    if (inputStream != null){
                        inputStream.close();
                    }
                }
                arrFile.add(new File(exportPath+"/"+fileName));
                i++;
            }
        }
        FileInputStream zipStream = null;
        try {
            zipStream = new FileInputStream(CacheUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH)+"/"+strFile+".zip");
            ZipFileUtil.compressZip(new File(CacheUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH)+"/"+strFile+".zip"),
                    false, arrFile.toArray(new File[arrFile.size()]));
            return ExportUtil.export(strFile+".zip",
                    new FileInputStream(CacheUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH)+"/"+strFile+".zip"));
        }finally {
            if (zipStream != null){
                zipStream.close();
            }
        }
    }
    
    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/report/informationdisclosureform/repfinproductexpiration/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    
    @Override
    public Page<RepFinProductExpirationDto> selectPageData(Map<String, Object> selectParam) {
        return select(selectParam);
    }

}
