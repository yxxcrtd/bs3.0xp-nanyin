/*
 * 文件名：ValFinprodTradeQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.disclosure.information.reveal.reportconfirm.service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.file.SafelyFile;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ZipFileUtil;
import com.joyintech.tams.common.oss.service.FileRemoteService;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.aspectj.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.listener.FileAttachmentProcess;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.common.common.util.ServiceApplication;
import com.joyintech.tams.disclosure.information.reveal.reportconfirm.dao.ReportConfirmManage001Mapper;
import com.joyintech.tams.disclosure.information.reveal.reportconfirm.dto.ReportConfirmManage001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 金融产品交易查询Service
 *
 * @author weipeng
 * @version 1.0 2018年5月16日
 * @see ValFinprodTradeQuery001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ReportConfirmManage001Service extends CommonExportService<ReportConfirmManage001Dto> implements BaseProcessService<ReportConfirmManage001Dto> {

    /**
     * ValProductInfoQuery001Mapper
     */
    @Autowired
    private ReportConfirmManage001Mapper reportConfirmManage001Mapper;

    @Autowired
    private FileAttachmentProcess fileAttachmentProcess;
    @Autowired
    private FileRemoteService minioFileRemoteService;

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ReportConfirmManage001Service.class);

    /**
     * 报表下载临时路径
     */
    public static final String KEY_REPORT_DOWNLOAD_PATH = "REPORT_DOWNLOAD_PATH";

    /**
     * 日志
     */
    private static Logger log = LoggerFactory.getLogger(ReportConfirmManage001Service.class);

    /**
     * 根据条件获取表格信息
     *
     * @param params 筛选条件
     * @return 表格信息
     * @throws Exception
     */
    public Page<ReportConfirmManage001Dto> findPage(Map<String, Object> params){
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME + "/ReportConfirmManage001/findPage",
                params, ReportConfirmManage001Dto.class);
    }

    public Page<SelectOptionDto> getPrdInfoSelect(Map<String, Object> paraMap){
        Page<SelectOptionDto> page =  queryForPageForSelectReturn(()->reportConfirmManage001Mapper.getPrdInfoSelect(paraMap));
        return page;
    }

    /**
     * <一句话功能简述> <功能详细描述>
     *
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    public List<ReportConfirmManage001Dto> queryPageList(Map<String, Object> params) {
        return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ReportConfirmManage001/queryPageList", params,
                ReportConfirmManage001Dto.class);
    }

    /**
     * 估值表余额表状态表 - 确认状态和下发状态更新
     */
    @Override
    public void doComplete(ReportConfirmManage001Dto entity, OperationType type) {
        restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ReportConfirmManage001/doComplete/" + type.name(), entity,
                AjaxResult.class);
    }

    /**
     * 删除service <一句话功能简述> <功能详细描述>
     *
     * @param jobId
     * @return
     */
    public int deleteComfirmByPrimaryKey(ReportConfirmManage001Dto dto) {
        return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ReportConfirmManage001/deleteComfirmByPrimaryKey", dto,
                Integer.class);
    }

    // add by chendong in 2018-11-18 start
    /**
     * 下载报表信息
     *
     * @param paraMap
     * @return 报表结果集
     * @throws IOException
     */
    public ResponseEntity<byte[]> downLoadReportInfo(Map<String, Object> paraMap)
            throws IOException {
        List<RepReveal> reportList = reportConfirmManage001Mapper.queryReportInfo(paraMap);
        // 时间戳
        String dateTime = DateUtil.format(new Date(), DateUtil.DEFAULT_OTHER_FORMAT);
        String exportFilePath = CacheUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
        String zipFileName = FileNameUtil.createFileName("zip");
        File zipFile = new SafelyFile(exportFilePath + File.separator + zipFileName);
        ZipOutputStream zipOut = null;
        OutputStream toClient=null;
        String tempDir = CacheUtil.getSysParam(GlobalConstant.COM_PROP_UPLOAD_PATH);
        if (StringUtil.isNotEmpty(reportList)) {
            String basePath = CacheUtil.getSysParam(KEY_REPORT_DOWNLOAD_PATH);
            // 需要层级。01：是，02：否
            FileOutputStream fos = null;
            if (StringUtil.equals(CacheUtil.getSysParam(ReportGlobalConstant.IS_NEED_SECOND), "01")) {
                File firstFile = null;
                File secondFile = null;
                try {
                    fos = new FileOutputStream(zipFile);
                    zipOut = new ZipOutputStream(fos);
                    toClient = new BufferedOutputStream(fos);

                    // 设置UTF8编码
                    zipOut.setEncoding(StandardCharsets.UTF_8.displayName());
                    for (RepReveal report : reportList) {
                        String fileType = StringUtil.substring(report.getSysFilename(),
                                report.getSysFilename().lastIndexOf("."));
                        String reportName = report.getReportName() + fileType;
                        if (report.getEndDate() != null && StringUtil.isNotEmpty(report.getEndDate())) {
                            reportName = DateUtil.format(report.getEndDate(), DateUtil.DEFAULT_DATE_FORMAT_EN)
                                    + GlobalConstant.UNDER_LINE + reportName;
                        }
                        if (StringUtil.isNotEmpty(report.getProdid())) {
                            reportName = report.getProdid() + GlobalConstant.UNDER_LINE + reportName;
                        }

                        // 报表类别名称
                        String reportCategoryName = reportConfirmManage001Mapper.getReportCategoryName(
                                report.getReportCategory());
                        String firstPath = basePath + report.getProdid();
                        // 存在人工上传的报表
                        String downloadPathPeople = null;
                        File downloadFilePeople = null;

                        if (StringUtil.isNotEmpty(report.getFilepath())) {
                            downloadPathPeople = this.getReportPath(report.getFilepath());
                            downloadFilePeople = fileCopy(tempDir,downloadPathPeople,downloadFilePeople,report.getFilename());
                        }
                        // 获取报表下载路径
                        String downloadPath = null;
                        File downloadFile = null;
                        if (StringUtil.isNotEmpty(report.getSysFilepath())) {
                            downloadPath = this.getReportPath(report.getSysFilepath());
                            downloadFile = fileCopy(tempDir,downloadPath,downloadFile,report.getSysFilename());
//                            File downloadFileTmp = new SafelyFile(downloadPath);
//                            downloadFile = new SafelyFile(
//                                downloadFileTmp.getParent() + File.separator + report.getSysFilename());
//                            FileUtil.copyFile(downloadFileTmp, downloadFile);
                        }

                        // 第一层目录
                        firstFile = new SafelyFile(firstPath);
                        // 判断第一层文件夹是否存在，否则创建
                        if (!firstFile.exists()) {
                            firstFile.mkdir();
                        }
                        String secondPath = firstPath + File.separator + report.getReportCategory();
                        // 第二层目录
                        secondFile = new SafelyFile(secondPath);
                        // 判断第二层文件夹是否存在，否则创建
                        if (!secondFile.exists()) {
                            secondFile.mkdir();
                            // 是否需要下载系统生成报表。01：是，02：否
                            if (StringUtil.equals(CacheUtil.getSysParam(ReportGlobalConstant.IS_NEED_SYSFILE), "01")) {
                                // 存在人工上传报表
                                if (downloadFilePeople != null && StringUtil.isNotEmpty(downloadFilePeople)) {
                                    String filename = downloadFilePeople.getName();
                                    List<String> splitToList = StringUtil.splitToList(".", reportName);
                                    // 将下载的报表文件重命名
                                    String newFileName = "";
                                    if (StringUtil.isNotEmpty(splitToList)) {
                                        // 获得报表文件类型
                                        fileType = splitToList.get(splitToList.size() - 1);
                                        splitToList.remove(splitToList.size() - 1);
                                        // 命名规则：产品名称+报表类别名称+时间戳+（手工上传）
                                        newFileName = splitToList.get(splitToList.size() - 1) + "_(手动上传)." + fileType;
                                    }
                                    File secondPeopleFile = null;
                                    if (StringUtil.isNotEmpty(newFileName)) {
                                        String secondPeoplePath = secondPath + File.separator + newFileName;
                                        secondPeopleFile = new SafelyFile(getEncodeFileName(secondPeoplePath));
                                    }

                                    if (StringUtil.isNotEmpty(downloadFilePeople)) {

                                        if (StringUtil.isNotEmpty(secondPeopleFile)) {
                                            FileUtil.copyFile(downloadFilePeople, secondPeopleFile);
                                        }
                                        else {
                                            FileUtil.copyFile(downloadFilePeople, secondFile);
                                        }
                                    }
                                }
                                // 存在系统生成文件
                                if (StringUtil.isNotEmpty(downloadFile)) {
                                    String sysFileName = downloadFile.getName();
                                    // 报表类型
                                    String sysFileType = sysFileName.substring(sysFileName.lastIndexOf("."),
                                            sysFileName.length());
                                    // 重命名。命名规则：产品名称+报表类型+时间戳
                                    String newSysFileName = reportName;
                                    File secondSysFile = null;
                                    if (StringUtil.isNotEmpty(newSysFileName)) {
                                        String secondSysPath = secondPath + File.separator + newSysFileName;
                                        secondSysFile = new SafelyFile(getEncodeFileName(secondSysPath));
                                    }

                                    if (StringUtil.isNotEmpty(secondSysFile)) {
                                        FileUtil.copyFile(downloadFile, secondSysFile);
                                    }
                                    else {
                                        FileUtil.copyFile(downloadFile, secondFile);
                                    }
                                }
                            }
                            // 当存在手动上传和系统生成文件时不需要下载系统生成文件
                            else {
                                // 存在人工上传文件，下载手动上传报表
                                if (StringUtil.isNotEmpty(downloadFilePeople)) {
                                    List<String> splitToList = StringUtil.splitToList(".", reportName);
                                    String newFileName = "";
                                    if (StringUtil.isNotEmpty(splitToList)) {
                                        fileType = splitToList.get(splitToList.size() - 1);
                                        splitToList.remove(splitToList.size() - 1);
                                        // 重命名。命名规则：产品名称+报表类型
                                        newFileName = splitToList.get(splitToList.size() - 1) + "_(手动上传)." + fileType;
                                    }
                                    File secondPeopleFile = null;
                                    if (StringUtil.isNotEmpty(newFileName)) {
                                        String secondPeoplePath = secondPath + File.separator + newFileName;
                                        secondPeopleFile = new SafelyFile(getEncodeFileName(secondPeoplePath));
                                    }
                                    if (StringUtil.isNotEmpty(secondPeopleFile)) {
                                        FileUtil.copyFile(downloadFilePeople, secondPeopleFile);
                                    }
                                    else {
                                        FileUtil.copyFile(downloadFilePeople, secondFile);
                                    }
                                }
                                // 不存在手动上传文件时下载系统生成文件
                                else {
                                    if (StringUtil.isNotEmpty(downloadFile)) {
                                        String newSysFileName = reportName;
                                        File secondSysFile = null;
                                        if (StringUtil.isNotEmpty(newSysFileName)) {
                                            String secondSysPath = secondPath + File.separator + newSysFileName;
                                            secondSysFile = new SafelyFile(getEncodeFileName(secondSysPath));
                                        }
                                        if (StringUtil.isNotEmpty(secondSysFile)) {
                                            FileUtil.copyFile(downloadFile, secondSysFile);
                                        }
                                        else {
                                            FileUtil.copyFile(downloadFile, secondFile);
                                        }

                                    }
                                }
                            }
                        }
                        compressFile(zipOut, firstFile, firstFile.getName());
                        //zipOut.setEncoding("gbk");
                        //zipOut.setEncoding(System.getProperty("file.encoding"));
                        deleteFile(firstFile);
                        if (downloadFilePeople != null) {
                            downloadFilePeople.delete();
                        }
                        if (downloadFile != null) {
                            downloadFile.delete();
                        }
                    }
                    zipOut.close();
                }
                catch (Exception e) {
                    log.error("", e);
                }finally {
                    if (fos != null){
                        fos.close();
                    }
                }
            }
            // 不需要层级
            else {
                try {
                    fos = new FileOutputStream(zipFile);
                    zipOut = new ZipOutputStream(fos);
                    toClient = new BufferedOutputStream(fos);
                    // 设置UTF8编码
                    zipOut.setEncoding(StandardCharsets.UTF_8.displayName());
                    for (RepReveal report : reportList) {
                        // TODO
                        String reportName = report.getReportName();
                        if (report.getEndDate() != null && StringUtil.isNotEmpty(report.getEndDate())) {
                            reportName = DateUtil.format(report.getEndDate(), DateUtil.DEFAULT_DATE_FORMAT_EN)
                                    + GlobalConstant.UNDER_LINE + reportName;
                        }
                        if (StringUtil.isNotEmpty(report.getProdid())) {
                            reportName = report.getProdid() + GlobalConstant.UNDER_LINE + reportName;
                        }
                        String reportCategoryName = reportConfirmManage001Mapper.getReportCategoryName(
                                report.getReportCategory());
                        File baseFile = new SafelyFile(basePath);
                        if (!baseFile.exists()) {
                            baseFile.mkdir();
                        }
                        // 存在人工上传的报表
                        String downloadPathPeople = null;
                        File downloadFilePeople = null;
                        if (StringUtil.isNotEmpty(report.getFilepath())) {
                            downloadPathPeople = this.getReportPath(report.getFilepath());
//                            File downloadFilePeopleTmp = new SafelyFile(downloadPathPeople);
//                            downloadFilePeople = new SafelyFile(getEncodeFileName(
//                                downloadFilePeopleTmp.getParent() + File.separator + report.getFilename()));
//                            FileUtil.copyFile(downloadFilePeopleTmp, downloadFilePeople);
                            downloadFilePeople = fileCopy(tempDir,downloadPathPeople,downloadFilePeople,report.getFilename());
                        }
                        // 获取报表下载路径（系统生成）
                        String downloadPath = null;
                        File downloadFile = null;
                        if (StringUtil.isNotEmpty(report.getSysFilepath())) {
                            downloadPath = this.getReportPath(report.getSysFilepath());
                            downloadFile = fileCopy(tempDir,downloadPath,downloadFile,report.getSysFilename());
//                            File downloadFileTmp = new SafelyFile(downloadPath);
//
//                            downloadFile = new SafelyFile(getEncodeFileName(downloadFileTmp.getParent() + File.separator + report.getProdName()
//                                                    + report.getSysFilename()));
//                            FileUtil.copyFile(downloadFileTmp, downloadFile);
                        }
                        // 需要系统文件
                        if (StringUtil.equals(CacheUtil.getSysParam(ReportGlobalConstant.IS_NEED_SYSFILE), "01")) {
                            if (StringUtil.isNotEmpty(downloadFilePeople)) {
                                String filename = downloadFilePeople.getName();
                                List<String> splitToList = StringUtil.splitToList(".", reportName);
                                String newFileName = "";
                                if (StringUtil.isNotEmpty(splitToList)) {
                                    String fileType = splitToList.get(splitToList.size() - 1);
                                    splitToList.remove(splitToList.size() - 1);
                                    // 命名规则：产品名称+报表类型名称+时间戳
                                    newFileName = splitToList.get(splitToList.size() - 1) + "_(手动上传)." + fileType;
                                }
                                File peopleFile = null;
                                if (StringUtil.isNotEmpty(newFileName)) {
                                    String peoplePath = basePath + File.separator + newFileName;
                                    peopleFile = new SafelyFile(getEncodeFileName(peoplePath));
                                }
                                if (StringUtil.isNotEmpty(downloadFilePeople)) {

                                    if (StringUtil.isNotEmpty(peopleFile)) {
                                        FileUtil.copyFile(downloadFilePeople, peopleFile);
                                    }
                                    else {
                                        FileUtil.copyFile(downloadFilePeople, baseFile);
                                    }
                                }
                            }
                            if (StringUtil.isNotEmpty(downloadFile)) {
                                String sysFileName = downloadFile.getName();
                                // 文件类型
                                String sysFileType = sysFileName.substring(sysFileName.lastIndexOf("."),
                                        sysFileName.length());
                                // 命名规则：产品名称+报表类型+时间戳
                                String newSysFileName = reportName;
                                File sysFile = null;
                                if (StringUtil.isNotEmpty(newSysFileName)) {
                                    String sysPath = basePath + File.separator + newSysFileName;
                                    sysFile = new SafelyFile(getEncodeFileName(sysPath));
                                }
                                if (StringUtil.isNotEmpty(sysFile)) {
                                    FileUtil.copyFile(downloadFile, sysFile);
                                }
                                else {
                                    FileUtil.copyFile(downloadFile, baseFile);
                                }
                            }
                        }
                        // 不需要系统文件
                        else {
                            // 如果存在人工上传，下载人工上传
                            if (StringUtil.isNotEmpty(downloadFilePeople)) {
                                String filename = downloadFilePeople.getName();// 上传的文件

                                // modifed by zhangyf 2019-04-22 start
                                // List<String> splitToList = StringUtil.splitToList(".",
                                // reportName);
                                List<String> splitToList = StringUtil.splitToList(".", filename);
                                // modifed by zhangyf 2019-04-22 end

                                String newFileName = filename;
//                                if (StringUtil.isNotEmpty(splitToList)) {
//                                    String fileType = splitToList.get(splitToList.size() - 1);
//                                    splitToList.remove(splitToList.size() - 1);
//
//                                    // modifed by zhangyf 2019-04-22 start
//                                    // newFileName = reportName+"_(手动上传)."+fileType;
//                                    newFileName = report.getSysFilename() + "." + fileType;
//                                    // modifed by zhangyf 2019-04-22 end
//                                }
                                File peopleFile = null;
                                if (StringUtil.isNotEmpty(newFileName)) {
                                    String peoplePath = basePath + File.separator + newFileName;
                                    peopleFile = new SafelyFile(getEncodeFileName(peoplePath));
                                }

                                if (StringUtil.isNotEmpty(downloadFilePeople)) {
                                    if (StringUtil.isNotEmpty(peopleFile)) {
                                        FileUtil.copyFile(downloadFilePeople, peopleFile);
                                    }
                                    else {
                                        FileUtil.copyFile(downloadFilePeople, baseFile);
                                    }
                                }
                            }
                            // 没有人工上传，下载系统文件
                            else {
                                if (StringUtil.isNotEmpty(downloadFile)) {
                                    String sysFileName = downloadFile.getName();
                                    // 文件类型
                                    String sysFileType = sysFileName.substring(sysFileName.lastIndexOf("."),
                                            sysFileName.length());
                                    // 命名规则：产品名称+报表类型+时间戳

                                    // modifed by zhangyf 2019-04-22 start
                                    // String newSysFileName =reportName;
                                    String newSysFileName = report.getSysFilename();
                                    // modifed by zhangyf 2019-04-22 end

                                    File sysFile = null;
                                    if (StringUtil.isNotEmpty(newSysFileName)) {
                                        String sysPath = basePath + File.separator + newSysFileName;
                                        sysFile = new SafelyFile(getEncodeFileName(sysPath));
                                    }
                                    if (StringUtil.isNotEmpty(sysFile)) {
                                        FileUtil.copyFile(downloadFile, sysFile);
                                    }
                                    else {
                                        FileUtil.copyFile(downloadFile, baseFile);
                                    }

                                }
                            }
                        }
                        compressFile2(zipOut, baseFile, baseFile.getName());
                        // zipOut.setEncoding("gbk");
                        // zipOut.setEncoding(System.getProperty("file.encoding"));
                        deleteFile(baseFile);
                        if (downloadFilePeople != null) {
                            downloadFilePeople.delete();
                        }
                        if (downloadFile != null) {
                            downloadFile.delete();
                        }
                    }
                }
                catch (Exception e) {
                    log.error("", e);
                }finally {
                    if(zipOut != null){
                        zipOut.close();
                    }
                    if (fos != null){
                        fos.close();
                    }
                }
            }
        }
        return ExportUtil.export(zipFileName, zipFile, true);
    }

    /**
     * 中文文件名编码转换
     * @param fileName
     * @return
     */
    private String getEncodeFileName(String fileName) {
        try {
            return StringUtil.isNotEmpty(fileName)
                    ? (new String(fileName.getBytes(System.getProperty("file.encoding")==null?"":System.getProperty("file.encoding")), "UTF-8")) : fileName;
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(),e);
        }
        return fileName;
    }

    /**
     * 删除多余文件
     *
     * @param
     */
    private void deleteFile(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null && files.length > 0) {
                for (int i = 0; i < files.length; i++ ) {
                    deleteFile(files[i]);
                }
            }

        }
        file.delete();
    }

    /**
     * 获取报表路径
     *
     * @param Seqno
     * @return 报表路径
     */
    @DataSource(DataSourceType.OTHER)
    public String getReportPath(String Seqno) {

        UploadFile file = fileAttachmentProcess.fetch(Seqno);

        return file.getTargetFile().getPath();
    }

    /**
     * 取消确认
     *
     * @param
     * @see
     * @since 1.0
     */
    public int confirmCancle(Map<String, Object> params) {
        return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ReportConfirmManage001/confirmCancle", params, Integer.class);
    }

    /**
     * 删除文件
     *
     * @param
     * @return
     */
    public int deleteFile(Map<String, Object> params) {
        return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ReportConfirmManage001/deleteFile", params, Integer.class);
    }

    /**
     * 代办中修改数据时同步更新
     *
     * @param wflList
     * @return
     */
    public ReportConfirmManage001Dto findNewData(ReportConfirmManage001Dto wflList) {
        return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ReportConfirmManage001/findNewData", wflList,
                ReportConfirmManage001Dto.class);
    }

    public String getFileName(String sysFilepath) {
        return restTemplate.getForObject(ServiceApplication.VALUATION_SERVICE_NAME
                        + "/ReportConfirmManage001/getFileName?sysFilepath=" + sysFilepath,
                String.class);
    }

    public String getFormate(String sysFilepath) {
        return restTemplate.getForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ReportConfirmManage001/getFormate?sysFilepath=" + sysFilepath,
                String.class);
    }



    /**
     * 压缩报表信息
     *
     * @param
     * @param
     * @param
     * @throws IOException
     */
    private void compressFile(ZipOutputStream out, File sourcefile, String basedir)
            throws IOException {
        if (sourcefile.isDirectory()) {
            File[] files = sourcefile.listFiles();

            if (files == null || files.length == 0) {
                out.putNextEntry(new ZipEntry(basedir + File.separator));
            }
            else {
                for (int i = 0; i < files.length; i++ ) {
                    compressFile(out, files[i], basedir + File.separator + files[i].getName());
                }
            }
        }
        else {
            out.putNextEntry(new ZipEntry(basedir));

            FileInputStream fos =null;
            BufferedInputStream bis =null;
            try {
                fos = new FileInputStream(sourcefile);
                bis = new BufferedInputStream(fos);
                int len;
                byte[] buf = new byte[1024];
                while ((len = bis.read(buf, 0, 1024)) != -1) {
                    out.write(buf, 0, len);
                }
            }catch (Exception e)
            {
                logger.error(e.getMessage(),e);
            }finally {
                if(bis!=null)
                {
                    bis.close();

                }

                if(fos!=null)
                {
                    fos.close();

                }
            }



        }
    }

    /**
     * 压缩报表信息
     *
     * @param
     * @param
     * @param
     * @throws IOException
     */
    private void compressFile2(ZipOutputStream out, File sourcefile, String basedir)
            throws IOException {
        if (sourcefile.isDirectory()) {
            File[] files = sourcefile.listFiles();

            if (files == null || files.length == 0) {
                out.putNextEntry(new ZipEntry(basedir + File.separator));
            }
            else {
                for (int i = 0; i < files.length; i++ ) {
                    compressFile2(out, files[i], files[i].getName());
                }
            }
        }
        else {
            out.putNextEntry(new ZipEntry(basedir));

            FileInputStream fos = null;
            BufferedInputStream bis = null;
            try {
                fos = new FileInputStream(sourcefile);
                bis = new BufferedInputStream(fos);
                int len;
                byte[] buf = new byte[1024];
                while ((len = bis.read(buf, 0, 1024)) != -1) {
                    out.write(buf, 0, len);
                }
            }catch (Exception e)
            {
                logger.error(e.getMessage(),e);
            }finally {
                if(bis!=null)
                {
                    bis.close();

                }
                if(fos!=null)
                {
                    fos.close();

                }
            }



        }
    }

    private File fileCopy(String basePath,String filePath,File newFile,String filename) throws IOException {
        if(filePath.startsWith("http:")) {
            byte[] bytes = minioFileRemoteService.downloadFile(filePath);
            if(bytes !=null) {
                if(newFile == null) {
                    File downloadFilePeopleTmp = new SafelyFile(minioFileRemoteService.getSimpleFilePathName(filePath));
                    newFile = new SafelyFile(getEncodeFileName(basePath+File.separator+
                            downloadFilePeopleTmp.getParent() + File.separator + filename));
                    File parentFile = new File(newFile.getParent());
                    if(!parentFile.exists()){
                        parentFile.mkdirs();
                    }
                }
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(newFile);
                    fos.write(bytes);
                    fos.flush();
                }catch(Exception e){
                    logger.error("远程文件写入本地文件异常",e);
                }finally{
                    if(fos != null){
                        try{
                            fos.close();
                        }catch(IOException e){
                            logger.error("关闭流异常",e);
                        }
                    }
                }
            }
        }else{
            File downloadFilePeopleTmp = new SafelyFile(filePath);
            if(newFile == null) {
                newFile = new SafelyFile(getEncodeFileName(
                        downloadFilePeopleTmp.getParent() + File.separator + filename));
            }
            try {
                FileUtil.copyFile(downloadFilePeopleTmp, newFile);
            }catch(Exception e){
                logger.error("本地文件Copy异常",e);
            }
        }
        return newFile;


    }

    public String getProcessInstanceId(String workflowId){
        return reportConfirmManage001Mapper.getProcessInstanceId(workflowId);
    }
}
