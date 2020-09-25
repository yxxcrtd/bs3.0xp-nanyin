/*
 * 文件名：RepFinProductEstablishService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年9月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.report.informationdisclosureform.repfinproductestablish.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
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
import com.joyintech.tools.util.ExcelTools;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年9月7日
 * @see RepFinProductEstablishService
 * @since 1.0
 */
@Service
public class RepFinProductEstablishService extends CommonExportService<RepFinProductEstablishDto> {
    
    
    /**
     * 
     * 页面下拉框初始化
     * <功能详细描述>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getSelectOption(Map<String,Object> param){
    	//Map<String,Object> param = new HashMap<String,Object>();
    	User user = SecurityUtil.getLoginUser();
        param.put("userId", user.getUserName());
        return restTemplate.postForList(ServiceApplication.REPORT_SERVICE_NAME
            + "/RepFinProductEstablish/getPortfolioId",param,
            SelectOptionDto.class);
    }
    
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param pageParam Map<String, Object>
     * @return Page<RepFinProductEstablishDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    public Page<RepFinProductEstablishDto> select(Map<String, Object> pageParam){
        pageParam.put("noBranchNoValue", Long.parseLong(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
        return restTemplate.postForPage(
            ServiceApplication.REPORT_SERVICE_NAME + "/RepFinProductEstablish/findPage",
            pageParam, RepFinProductEstablishDto.class);
    }
    
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto RepFinProductEstablishDto
     * @return int
     * @throws IOException 
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public ResponseEntity<byte[]> generateBulletin(RepFinProductEstablishDto dto) throws IOException{
        List<RepFinProductEstablishDto> list = restTemplate.postForList(
            ServiceApplication.REPORT_SERVICE_NAME + "/RepFinProductEstablish/generateBulletin",
            dto,RepFinProductEstablishDto.class);
        List<File> arrFile = new ArrayList<File>();
        String strFile = null;
        int i = 0;
        for(RepFinProductEstablishDto repFinProductEstablishDto:list){
            //循环List<金融产品成立公告生成信息DTO>，取出文件模板类型为【01：产品成立公告（横表）】的数据，放入横版List<金融产品成立公告生成信息DTO>                                                                                                                                                              
            if(repFinProductEstablishDto.getFileType().equals(DictCodeList.文件类型_投管_产品成立公告_横表.getValue())){
                
                List<SysUploadFileInfo> sysUploadFileInfoList = restTemplate.getForList(
                    ServiceApplication.REPORT_SERVICE_NAME + "/RepFinProductEstablish/getSysUploadFileInfo?bussinessId="+
                        repFinProductEstablishDto.getRevealFileId(),
                    SysUploadFileInfo.class);
                 //将DTO转成Map
                //Map<String,Object> excleMap = JsonUtils.object2Map(repFinProductEstablishDto);
                Map<String,Object> excleMap = new HashMap<String,Object>();
                excleMap.put("prodName", repFinProductEstablishDto.getProdName());
                excleMap.put("prodId", repFinProductEstablishDto.getProdId());
                excleMap.put("ccy", repFinProductEstablishDto.getCcyStr());
                excleMap.put("prodRiskLevel", repFinProductEstablishDto.getProdRiskLevel());
                excleMap.put("profitType", repFinProductEstablishDto.getProfitType());
                excleMap.put("vDate", repFinProductEstablishDto.getVdateStr());
                excleMap.put("mDate", repFinProductEstablishDto.getMdateStr());
                excleMap.put("yield", repFinProductEstablishDto.getYieldStr());
                excleMap.put("trustFee", repFinProductEstablishDto.getTrustFeeStr());
                excleMap.put("saleFee", repFinProductEstablishDto.getSaleFeeStr());
                excleMap.put("investTargetRatio", repFinProductEstablishDto.getInvestTargetRatio());
                strFile = SysDataUtil.getNameFromDataDict(DictCode.文件类型, repFinProductEstablishDto.getFileType());
                String fileName = repFinProductEstablishDto.getFinprodMarketId()+strFile+".xls";
                ExcelTools excelTools = new ExcelTools();
                String exportPath = CacheUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
                
                File file = new File(sysUploadFileInfoList.get(0).getPath());
                FileInputStream inputStream = new FileInputStream(file);
                excelTools.writeExcel(inputStream, sysUploadFileInfoList.get(0).getFileName(), excleMap, exportPath+"/"+fileName);
                arrFile.add(new File(exportPath+"/"+fileName));
                i++;
            }
            //循环List<金融产品成立公告生成信息DTO>，取出文件模板类型为【02：产品成立公告（竖表）】的数据，放入竖版List<金融产品成立公告生成信息DTO> 
            else{
                
                List<SysUploadFileInfo> sysUploadFileInfoList = restTemplate.getForList(
                    ServiceApplication.REPORT_SERVICE_NAME + "/RepFinProductEstablish/getSysUploadFileInfo?bussinessId="+
                        repFinProductEstablishDto.getRevealFileId(),
                        SysUploadFileInfo.class);
                //将DTO转成Map
                //Map<String,Object> excleMap = JsonUtils.object2Map(repFinProductEstablishDto);
                Map<String,Object> excleMap = new HashMap<String,Object>();
                excleMap.put("prodName", repFinProductEstablishDto.getProdName());
                excleMap.put("prodId", repFinProductEstablishDto.getProdId());
                excleMap.put("ccy", repFinProductEstablishDto.getCcyStr());
                excleMap.put("prodRiskLevel", repFinProductEstablishDto.getProdRiskLevel());
                excleMap.put("profitType", repFinProductEstablishDto.getProfitType());
                excleMap.put("vDate", repFinProductEstablishDto.getVdateStr());
                excleMap.put("mDate", repFinProductEstablishDto.getMdateStr());
                excleMap.put("yield", repFinProductEstablishDto.getYieldStr());
                excleMap.put("trustFee", repFinProductEstablishDto.getTrustFeeStr());
                excleMap.put("saleFee", repFinProductEstablishDto.getSaleFeeStr());
                excleMap.put("investTargetRatio", repFinProductEstablishDto.getInvestTargetRatio());
                strFile = SysDataUtil.getNameFromDataDict(DictCode.文件类型, repFinProductEstablishDto.getFileType());
                String fileName = repFinProductEstablishDto.getFinprodMarketId()+strFile+".xls";
                ExcelTools excelTools = new ExcelTools();
                String exportPath = CacheUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
                File file = new File(sysUploadFileInfoList.get(0).getPath());
                FileInputStream inputStream = new FileInputStream(file);
                excelTools.writeExcel(inputStream, sysUploadFileInfoList.get(0).getFileName(), excleMap, exportPath+"/"+fileName);
                arrFile.add(new File(exportPath+"/"+fileName));
                i++;
            }
        }
        ZipFileUtil.compressZip(new File(CacheUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH)+"/"+strFile+".zip"), 
            false, arrFile.toArray(new File[arrFile.size()]));
        return ExportUtil.export(strFile+".zip", 
            new FileInputStream(CacheUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH)+"/"+strFile+".zip"));
    }
    
    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/report/informationdisclosureform/repfinproductestablish/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    
    @Override
    public Page<RepFinProductEstablishDto> selectPageData(Map<String, Object> selectParam) {
        return select(selectParam);
    }
    
        
}
