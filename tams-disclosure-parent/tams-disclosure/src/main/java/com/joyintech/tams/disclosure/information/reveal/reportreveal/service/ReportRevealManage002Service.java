/*
 * 文件名：ValFinprodTradeQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.disclosure.information.reveal.reportreveal.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.file.SafelyFile;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.listener.FileAttachmentProcess;
import com.joyintech.framework.progressbar.ProgressBarService;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.oss.service.FileRemoteService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.ftpDetail.dto.FtpDetailDto;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.ftpDetail.service.FtpDetailService;
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.common.common.util.ServiceApplication;
import com.joyintech.tams.disclosure.information.reveal.reportconfirm.controller.UploadattAchment001Controller;
import com.joyintech.tams.disclosure.information.reveal.reportconfirm.dto.ReportConfirmManage001Dto;
import com.joyintech.tams.disclosure.information.reveal.reportconfirm.dto.ReportConfirmManageParamDto;
import com.joyintech.tams.disclosure.information.reveal.reportreveal.dao.ReportRevealManageEX001Mapper;
import com.joyintech.tams.disclosure.information.reveal.reportreveal.util.SendFileUtil;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.aspectj.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 金融产品交易查询Service
 * 
 * @author zhouqiang
 * @version 1.0 2022年08月11日
 * @see ReportRevealManage002Service
 * @since 1.0
 */
@Service
public class ReportRevealManage002Service  implements BaseProcessService<ReportConfirmManage001Dto> {

    /**
     * 报表下载临时路径
     */
    public static final String KEY_REPORT_DOWNLOAD_PATH = "REPORT_DOWNLOAD_PATH";

    /**
     * 日志
     */
    @SuppressWarnings("unused")
    private static Logger log = LoggerFactory.getLogger(ReportRevealManage002Service.class);

    @Autowired
    private ReportRevealManageEX001Mapper mapper;

    @Autowired
    private FileAttachmentProcess fileAttachmentProcess;

    @Autowired
    private FileRemoteService minioFileRemoteService;

    @Autowired
    private FtpDetailService ftpDetailService;

    /**
     * 进度条接口
     */
    @Autowired
    private ProgressBarService progressBarService;

    /**
     * 进度条ID
     */
    private static final String PROCESS_KEY = "exportProcessBar";

	private Logger logger = LoggerFactory.getLogger(UploadattAchment001Controller.class);
	
    /**
     * 手动披露
     * @param listdto
     */
    public boolean updateRevealStatusEX(List<ReportConfirmManage001Dto> listdto) throws IOException{
        boolean result=true;
        //1.先将单条数据披露，发送文件至指定服务器
        result=this.updateRevealStatusByRevealId(listdto);
        //有披露失败的，默认全部失败，直接结束
        if(!result){
            return false;
        }
        List<String> prodIds = listdto.stream().distinct().map(ReportConfirmManage001Dto::getProdid).collect(Collectors.toList());
        result=genFile(prodIds);

        return result;
    }

    public boolean genFile(List<String> prodIds) throws IOException {
        HashMap<String, Object> param = new HashMap<>();
        String date = DateUtil.getCurrentDate();
        param.put("date", date);
        //查询要处理的信披单据，没有关联代销机构的产品对应的单据，不进行披露
         List<ReportConfirmManage001Dto> list = mapper.getRepRevealList(param);
        if (list != null && list.size() > 0) {
            //list=list.stream().filter(dto->prodIds.contains(dto.getProdid())).collect(Collectors.toList());
            Map<String, List<ReportConfirmManage001Dto>> map = list.stream().collect(Collectors.groupingBy(ReportConfirmManage001Dto::getAgencyName));
            for (String agencyName : map.keySet()) {
                List<ReportConfirmManage001Dto> reportConfirmManage001DtoList = map.get(agencyName);
                if (reportConfirmManage001DtoList != null && reportConfirmManage001DtoList.size() > 0) {
                    boolean generateResult = generatePackageByAgency(agencyName, reportConfirmManage001DtoList);
                    if (!generateResult) {
                        //有生成压缩包失败的，默认全部披露失败，直接结束
                        return false;
                    }
                }
            }

            //所有的代销机构都成功生成压缩包，再上传压缩包至指定服务器
//            int i=1;
            for (String agencyName : map.keySet()) {
                String exportFilePath = CacheUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
                exportFilePath = exportFilePath + DateUtil.formatDate(DateUtil.getCurrentDate());
                String zipFileName = this.createFileName("zip");
                zipFileName = agencyName + zipFileName;
                Boolean isSucess = SendFileUtil.sendToFinanceByFtp(zipFileName, exportFilePath);
                if (!isSucess) {
                    //有上传压缩包失败的，默认全部披露失败，直接结束
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 披露
     * @param listdto
     * @return
     */
    private boolean updateRevealStatusByRevealId(List<ReportConfirmManage001Dto> listdto) {
        boolean flag=true;
        if(listdto!=null && listdto.size()>0){
            for (ReportConfirmManage001Dto entity : listdto) {
                if(entity!=null){
                    //披露操作用户
                    entity.setRevealUser(LoginUserUtil.getUserName());
                    entity.setRevealStatus(DictCodeListExt2.披露状态_已披露.getValue());
                    entity.setRevealDate(new Date());
                    // add by guozhongbao 披露时上传文件 20181210 start
                    FtpDetailDto ftpDetailDto = new FtpDetailDto();
                    ftpDetailDto.setSysFilepath(entity.getSysFilepath());
                    ftpDetailDto.setProdId(entity.getProdid());
                    ftpDetailDto.setReportId(entity.getRepId());
                    ftpDetailDto.setReportCategory(entity.getReportCategory()); // 获取ftp路径用
                    ftpDetailDto.setOption(DictCodeListExt2.披露方式_手动.getValue()); //手工操作
                    if(StringUtil.isNotEmpty(entity.getStartDate())) {
                        ftpDetailDto.setStatrDate(entity.getStartDate());
                    }
                    if(StringUtil.isNotEmpty(entity.getEndDate())) {
                        ftpDetailDto.setEndDate(entity.getEndDate());
                    }
                    ftpDetailDto.setRevealChannel(entity.getRevealChannel());
                    ftpDetailDto.setRevealId(entity.getId());

                    Boolean checkAndCreate = ftpDetailService.doReveal(ftpDetailDto);
                    if(checkAndCreate) {
                        entity.setUpdateUser(LoginUserUtil.getUserName());
                        mapper.updateRevealStatusByIdEX(entity);
                    }else{
                        //有一条数据披露失败，默认全部失败，直接返回false
                        flag=false;
                        break;
                    }
                    // add by guozhongbao 披露时上传文件 20181210 end
                }
            }
        }
        return flag;
    }

    private boolean generatePackageByAgency(String agencyName, List<ReportConfirmManage001Dto> infoDiscAutoRevealList) throws IOException {
        String exportFilePath = CacheUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
        exportFilePath=exportFilePath+ DateUtil.formatDate(DateUtil.getCurrentDate());
        File dateFilePath=new SafelyFile(exportFilePath);
        if ( !dateFilePath.exists()){//若此目录不存在，则创建之
            boolean ret = dateFilePath.mkdir();
            if ( ret==false ) {
            	logger.error("创建目录错误："+dateFilePath.getName());
            }
        }
        //生成的压缩包的名称:zipFileName
        String zipFileName = this.createFileName("zip");
        zipFileName=agencyName+zipFileName;
        File zipFile = new SafelyFile(exportFilePath + File.separator + zipFileName);
        ZipOutputStream zipOut = null;
        OutputStream toClient=null;
        String tempDir = CacheUtil.getSysParam(GlobalConstant.COM_PROP_UPLOAD_PATH);
        if(!StringUtil.isEmptyList(infoDiscAutoRevealList)){
            //报表生成临时路径
            String basePath = CacheUtil.getSysParam(KEY_REPORT_DOWNLOAD_PATH);
            FileOutputStream fos = null;
            File firstFile = null;
            try {
                fos = new FileOutputStream(zipFile);
                zipOut = new ZipOutputStream(fos);
                toClient = new BufferedOutputStream(fos);
                // 设置UTF8编码
                zipOut.setEncoding(StandardCharsets.UTF_8.displayName());
                for (ReportConfirmManage001Dto report : infoDiscAutoRevealList) {
                    //获取系统生成的文件名
                    String reportName=report.getSysFilename();//系统生成的文件名
                    String firstPath = basePath + report.getProdName();
                    // 存在人工上传的报表
                    String downloadPathPeople = null;
                    File downloadFilePeople = null;

                    if (StringUtil.isNotEmpty(report.getFilepath())) {
                        //获取人工上传文件的路径
                        downloadPathPeople = this.getReportPath(report.getFilepath());
                        downloadFilePeople = fileCopy(tempDir,downloadPathPeople,downloadFilePeople,report.getFilename());
                    }
                    // 获取系统生成报表下载路径
                    String downloadPath = null;
                    File downloadFile = null;
                    if (StringUtil.isNotEmpty(report.getSysFilepath())) {
                        downloadPath = this.getReportPath(report.getSysFilepath());
                            downloadFile = fileCopy(tempDir,downloadPath,downloadFile,report.getSysFilename());
                    }

                    // 第一层目录：产品名称
                    firstFile = new SafelyFile(firstPath);
                    // 判断第一层文件夹是否存在，否则创建
                    if (!firstFile.exists()) {
                        boolean ret = firstFile.mkdir();
                        if ( ret==false ) {
                        	logger.error("创建目录错误："+firstFile.getName());
                        }
                    }
                    // 是否需要打包系统生成报表。01：是，02：否
                    if (StringUtil.equals(CacheUtil.getSysParam(ReportGlobalConstant.IS_NEED_SYSFILE), "01")) {
                        // 存在人工上传报表
                        if (downloadFilePeople != null && StringUtil.isNotEmpty(downloadFilePeople)) {
                            String filename = downloadFilePeople.getName();
                            File secondPeopleFile = null;
                            if (StringUtil.isNotEmpty(filename)) {
                                String secondPeoplePath = firstPath + File.separator + filename;
                                secondPeopleFile = new SafelyFile(getEncodeFileName(secondPeoplePath));
                            }

                            if (StringUtil.isNotEmpty(downloadFilePeople)) {

                                if (StringUtil.isNotEmpty(secondPeopleFile)) {
                                    FileUtil.copyFile(downloadFilePeople, secondPeopleFile);
                                }
								/*else {
									FileUtil.copyFile(downloadFilePeople, firstFile);
								}*/
                            }
                        }
                        // 存在系统生成文件
                        if (StringUtil.isNotEmpty(downloadFile)) {
                            String sysFileName = downloadFile.getName();
                            File secondSysFile = null;
                            if (StringUtil.isNotEmpty(sysFileName)) {
                                String secondSysPath = firstPath + File.separator + sysFileName;
                                secondSysFile = new SafelyFile(getEncodeFileName(secondSysPath));
                            }

                            if (StringUtil.isNotEmpty(secondSysFile)) {
                                FileUtil.copyFile(downloadFile, secondSysFile);
                            }
							/*else {
								FileUtil.copyFile(downloadFile, secondFile);
							}*/
                        }
                    }else {// 当存在手动上传和系统生成文件时不需要下载系统生成文件
                        // 存在人工上传报表
                        if (downloadFilePeople != null && StringUtil.isNotEmpty(downloadFilePeople)) {
                            String filename = downloadFilePeople.getName();
                            File secondPeopleFile = null;
                            if (StringUtil.isNotEmpty(filename)) {
                                String secondPeoplePath = firstPath + File.separator + filename;
                                secondPeopleFile = new SafelyFile(getEncodeFileName(secondPeoplePath));
                            }

                            if (StringUtil.isNotEmpty(downloadFilePeople)) {

                                if (StringUtil.isNotEmpty(secondPeopleFile)) {
                                    FileUtil.copyFile(downloadFilePeople, secondPeopleFile);
                                }
								/*else {
									FileUtil.copyFile(downloadFilePeople, firstFile);
								}*/
                            }
                        }
                        // 不存在手动上传文件时下载系统生成文件
                        else {
                            if (StringUtil.isNotEmpty(downloadFile)) {
                                String filename = downloadFile.getName();
                                File secondSysFile = null;
                                if (StringUtil.isNotEmpty(filename)) {
                                    String secondSysPath = firstPath + File.separator + filename;
                                    secondSysFile = new SafelyFile(getEncodeFileName(secondSysPath));
                                }
                                if (StringUtil.isNotEmpty(secondSysFile)) {
                                    FileUtil.copyFile(downloadFile, secondSysFile);
                                }
                                else {
                                    FileUtil.copyFile(downloadFile, firstFile);
                                }

                            }
                        }
                    }
                    compressFile(zipOut, firstFile, firstFile.getName());
                    //zipOut.setEncoding("gbk");
                    //zipOut.setEncoding(System.getProperty("file.encoding"));
                    deleteFile(firstFile);
                    if (downloadFilePeople != null) {
                        boolean ret = downloadFilePeople.delete();
                        if ( ret==false ) {
                        	logger.error("删除文件错误："+downloadFilePeople.getName());
                        }
                    }
                    if (downloadFile != null) {
                        boolean ret = downloadFile.delete();
                        if ( ret==false ) {
                        	logger.error("删除文件错误："+downloadFilePeople.getName());
                        }
                    }
                }
//                toClient.close();
//                toClient = null;
//                zipOut.close();
//                zipOut=null;
            }
            catch (Exception e) {
                log.error("", e);
                return false;
            }finally {
            	try {
                    if ( zipOut!=null ) {
                        zipOut.flush();
                        zipOut.close();
                    }
	                if (fos != null){
	                	fos.flush();
	                    fos.close();
	                }
	                if ( toClient!=null ) {
	                	toClient.flush();
	                	toClient.close();
	                }
            	} catch (Exception e) {
            		logger.error("资源关闭错误。",e);
            	}
            }
        }
        return true;
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
        boolean ret = file.delete();
        if ( ret==false ) {
        	logger.error("删除文件错误："+file.getName());
        }
    }

    /**
     * 生成文件名-yyyyMMdd.zip
     * @param extName
     * @return
     */
    private String createFileName(String extName) {
        String fileName = DateUtil.format(new Date(), DateUtil.DEFAULT_OTHER_FORMAT);
        if(StringUtil.isNotEmpty(extName)) {
            if(!extName.startsWith(GlobalConstant.POINT)) {
                extName = GlobalConstant.POINT + extName;
            }
            fileName = GlobalConstant.SPLIT+fileName + extName;
        }
        return fileName;
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
                        boolean ret = parentFile.mkdirs();
                        if ( ret==false ) {
                        	logger.error("创建目录错误："+parentFile.getName());
                        }
                    }
                }
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(newFile);
                    fos.write(bytes);
                    fos.flush();
                }catch(Exception e){
                    log.error("远程文件写入本地文件异常",e);
                }finally{
                    if(fos != null){
                        try{
                            fos.close();
                        }catch(IOException e){
                            log.error("关闭流异常",e);
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
                log.error("本地文件Copy异常",e);
            }
        }
        return newFile;
    }

    /**
     * 中文文件名编码转换
     * @param fileName
     * @return
     */
    private String getEncodeFileName(String fileName) {
        try {
            return StringUtil.isNotEmpty(fileName) ? (new String(fileName.getBytes(System.getProperty("file.encoding")==null?"":System.getProperty("file.encoding")), "UTF-8")) : fileName;
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(),e);
        }
        return fileName;
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
                log.error(e.getMessage(),e);
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
     * 批量更新披露状态--生成失败
     * @param listdto
     */
    public void updateRevealStatusBatch(List<ReportConfirmManage001Dto> listdto) {
        //获取所有勾选单据的id
        for (ReportConfirmManage001Dto dto : listdto) {
            dto.setRevealStatus(DictCodeListExt2.披露状态_生成失败.getValue());
            dto.setRevealUser(CommonUtil.getUserName());
            dto.setRevealDate(DateUtil.now());
            mapper.updateRevealStatusById(dto);
        }
    }

    @Override
    public void doComplete(ReportConfirmManage001Dto entity, OperationType type) {

    }
}
