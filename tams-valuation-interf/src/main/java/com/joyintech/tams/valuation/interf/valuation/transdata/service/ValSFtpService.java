/*
 * 文件名：ValFtpService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年6月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.interf.valuation.transdata.service;

import java.io.File;
import java.util.Date;
import java.util.List;

import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.systemtools.service.TamsBaseCommonService;
import com.joyintech.tams.valuation.common.service.BaseCommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.util.SftpUtil;
import com.joyintech.tams.valuation.interf.core.exception.InterfException;
import com.joyintech.tams.valuation.interf.valuation.transdata.entity.ValRecvFileEntity;
import com.joyintech.tams.valuation.interf.valuation.transdata.entity.ValRecvServerInfoEntity;
import com.joyintech.tams.common.base.entity.ValInterfTemplate;
import com.joyintech.tams.common.base.entity.ValInterflog;

@Service
@Scope("prototype")
public class ValSFtpService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ValSFtpService.class);
    //add by liupnegfei for 多级法人改造 S
    @Autowired
    private TamsBaseCommonService tamsBaseCommonService;
    //add by liupnegfei for 多级法人改造 E

    /**
     * 接收文件
     * 
     * @param entity
     * @param fileNamePart
     * @throws Exception
     * @see
     * @since 1.0
     */
    public ValRecvFileEntity doRecv(ValRecvServerInfoEntity entity,
                                    List<ValInterfTemplate> tempList)
        throws Exception {
        ValRecvFileEntity retEntity = new ValRecvFileEntity();
        // 本地路径
        String localDirTemp = entity.getLocalPath() + File.separator + entity.getSystemID()
                          + File.separator
                          + DateUtil.format(entity.getOccurTime(), DateUtil.DEFAULT_OTHER_FORMAT);
        // 远程路径
        String remotePathTemp = entity.getRemotePath() + "/" + DateUtil.format(entity.getOccurTime(),
            DateUtil.DEFAULT_OTHER_FORMAT);
        //add by liupengfei for 多级法人改造 S
        if(MultilevelDataAuthorityUtil.isEnabled()){
            if(StringUtil.isNotEmpty(entity.getInstitutionCode())){
                //省联社机构读取所有
                if(!StringUtil.equals(tamsBaseCommonService.getInstitutionType(entity.getInstitutionCode()),
                        DictCodeListExt3.联社机构类型_省级机构.getValue())){
                    localDirTemp = localDirTemp+File.separator+entity.getInstitutionCode();
                    remotePathTemp = remotePathTemp+"/"+entity.getInstitutionCode();
                }
            }
        }
        String localDir = localDirTemp;
        String remotePath = remotePathTemp;
        File localDirFile = new File(localDir);
        //add by liupengfei for 多级法人改造 E
        // 删除本地路径的原始文件
        if (localDirFile.exists()) {
            delFolder(localDir);
        }
        localDirFile.mkdirs();
        SftpUtil ftpUtil = new SftpUtil();
        try {
            ftpUtil.login(entity.getUserName(), entity.getPassword(), entity.getServerIp(),
                entity.getServerPort());
        }
        catch (Exception e) {
            throw new InterfException("登录远程服务器失败！");
        }

        // 如果是空的列表，全部下载
        if (StringUtil.isEmptyList(tempList)) {
            List<String> fileNames = ftpUtil.getSFtpFile(remotePath);
            //add by liupengfei for 多级法人改造 S
            if(MultilevelDataAuthorityUtil.isEnabled()){
                fileNames = ftpUtil.listFileName(remotePath, "");
                if (fileNames.isEmpty()) {
                    return retEntity;
                }
                fileNames.forEach(fileName -> {
                    String remoteFile = remotePath + "/" + fileName;
                    ValInterflog log = new ValInterflog();
                    log.setFileName((fileName.split("/").length>1?File.separator+fileName.split("/")[1]:""));
                    // 操作日期
                    log.setExDate(new Date());
                    log.setIsCheck("Y");
                    try {
                        ftpUtil.getFile(remoteFile, localDir+(fileName.split("/").length>1?File.separator+fileName.split("/")[0]:""));
                    }
                    catch (Exception e) {
                        log.setIsCheck("N");
                        logger.error("下载文件失败：" + (fileName.split("/").length>1?File.separator+fileName.split("/")[1]:""), e);
                    }

                    log.setNum(1L);
                    log.setParentSeqno(entity.getExecInfoId());
                    log.setTmpName("sftp-download");
                    retEntity.getInterfLogs().add(log);
                });
            }
            //add by liupengfei for 多级法人改造 E
            else{
                if (fileNames.isEmpty()) {
                    return retEntity;
                }
                fileNames.forEach(fileName -> {
                    String remoteFile = remotePath + "/" + fileName;
                    ValInterflog log = new ValInterflog();
                    log.setFileName(fileName);
                    // 操作日期
                    log.setExDate(new Date());
                    log.setIsCheck("Y");
                    try {
                        ftpUtil.getFile(remoteFile, localDir);
                    }
                    catch (Exception e) {
                        log.setIsCheck("N");
                        logger.error("下载文件失败：" + fileName, e);
                    }

                    log.setNum(1L);
                    log.setParentSeqno(entity.getExecInfoId());
                    log.setTmpName("sftp-download");
                    retEntity.getInterfLogs().add(log);
                });
            }
        }
        else {
            for (ValInterfTemplate fileNamePart : tempList) {
                List<String> fileNames;
                if ("ZG".equals(fileNamePart.getSystemid())) {
                    /**modify by dongjun on 20181031 start **/
                    String fileName = null;
                    if(StringUtil.equals(fileNamePart.getTmpType(), DictCodeInterfList.接口文件类型_STTXT.getValue())){
                        fileName = fileNamePart.getTmpEName() + DateUtil.format(entity.getOccurTime(),
                             DateUtil.DEFAULT_OTHER_FORMAT);
                     } else {
                         fileName = fileNamePart.getTmpEName() + "_" + DateUtil.format(entity.getOccurTime(),
                             DateUtil.DEFAULT_OTHER_FORMAT);
                     }
                     fileNames = ftpUtil.listFileName(remotePath,fileName);
                    /**modify by dongjun on 20181031 end **/

                }
                else {
                    fileNames = ftpUtil.listFileName(remotePath, fileNamePart.getTmpEName());
                }
                if (StringUtil.isEmptyList(fileNames)) {
                    logger.info("没有模板：" + fileNamePart.getTmpEName());
                    ValInterflog log = new ValInterflog();
                    log.setFileName(fileNamePart.getTmpEName());
                    // 操作日期
                    log.setExDate(new Date());
                    log.setIsCheck("N");
                    log.setNum(1L);
                    log.setParentSeqno(entity.getExecInfoId());
                    log.setTmpName("sftp-download");
                    retEntity.getInterfLogs().add(log);
                    continue;
                }
                fileNames.forEach(fileName->{
                    String remoteFile = remotePath + "/" + fileName;
                    ValInterflog log = new ValInterflog();
                    log.setFileName(fileName);
                    // 操作日期
                    log.setExDate(new Date());
                    log.setIsCheck("Y");
                    try {
                        ftpUtil.getFile(remoteFile, localDir+(fileName.split("/").length>1?File.separator+fileName.split("/")[0]:""));
                    }
                    catch (Exception e) {
                        log.setIsCheck("N");
                        logger.error("下载文件失败：" + fileName, e);
                    }

                    log.setNum(1L);
                    log.setParentSeqno(entity.getExecInfoId());
                    log.setTmpName("sftp-download");
                    retEntity.getInterfLogs().add(log);
                });
            }

        }

        ftpUtil.close();
        return retEntity;
    }

    /**
     * 接收文件
     * 
     * @param entity
     * @param fileNamePart
     * @throws Exception
     * @see
     * @since 1.0
     */
    public ValRecvFileEntity doRecvId(ValRecvServerInfoEntity entity,
                                    List<ValInterfTemplate> tempList)
        throws Exception {
        ValRecvFileEntity retEntity = new ValRecvFileEntity();
        // 本地路径
        String localDirTemp = entity.getLocalPath() + File.separator + entity.getSystemID()
                          + File.separator
                          + DateUtil.format(entity.getOccurTime(), DateUtil.DEFAULT_OTHER_FORMAT);
        // 远程路径
        String remotePathTemp = entity.getRemotePath() + "/" + DateUtil.format(entity.getOccurTime(),
            DateUtil.DEFAULT_OTHER_FORMAT);
        //add by liupengfei for 多级法人改造 S
        if(MultilevelDataAuthorityUtil.isEnabled()){
            if(StringUtil.isNotEmpty(entity.getInstitutionCode())){
                //省联社机构读取所有
                if(!StringUtil.equals(tamsBaseCommonService.getInstitutionType(entity.getInstitutionCode()),
                        DictCodeListExt3.联社机构类型_省级机构.getValue())){
                    localDirTemp = localDirTemp+File.separator+entity.getInstitutionCode();
                    remotePathTemp = remotePathTemp+"/"+entity.getInstitutionCode();
                }
            }
        }
        String localDir = localDirTemp;
        String remotePath = remotePathTemp;
        File localDirFile = new File(localDir);
        //add by liupengfei for 多级法人改造 E
        // 删除本地路径的原始文件
        if (localDirFile.exists()) {
            delFolder(localDir);
        }
        localDirFile.mkdirs();
        SftpUtil ftpUtil = new SftpUtil();
        try {
            ftpUtil.loginById(entity.getUserName(), entity.getPassword(), entity.getServerIp(),
                entity.getServerPort());
        }
        catch (Exception e) {
            throw new InterfException("登录远程服务器失败！");
        }

        // 如果是空的列表，全部下载
        if (StringUtil.isEmptyList(tempList)) {
            List<String> fileNames = ftpUtil.getSFtpFile(remotePath);
            //add by liupengfei for 多级法人改造 S
            if(MultilevelDataAuthorityUtil.isEnabled()){
                fileNames = ftpUtil.listFileName(remotePath, "");
                if (fileNames.isEmpty()) {
                    return retEntity;
                }
                fileNames.forEach(fileName -> {
                    String remoteFile = remotePath + "/" + fileName;
                    ValInterflog log = new ValInterflog();
                    log.setFileName((fileName.split("/").length>1?File.separator+fileName.split("/")[1]:""));
                    // 操作日期
                    log.setExDate(new Date());
                    log.setIsCheck("Y");
                    try {
                        ftpUtil.getFile(remoteFile, localDir+(fileName.split("/").length>1?File.separator+fileName.split("/")[0]:""));
                    }
                    catch (Exception e) {
                        log.setIsCheck("N");
                        logger.error("下载文件失败：" + (fileName.split("/").length>1?File.separator+fileName.split("/")[1]:""), e);
                    }

                    log.setNum(1L);
                    log.setParentSeqno(entity.getExecInfoId());
                    log.setTmpName("sftp-download");
                    retEntity.getInterfLogs().add(log);
                });
            }
            if (fileNames.isEmpty()) {
                return retEntity;
            }
            fileNames.forEach(fileName -> {
                String remoteFile = remotePath + "/" + fileName;
                ValInterflog log = new ValInterflog();
                log.setFileName(fileName);
                // 操作日期
                log.setExDate(new Date());
                log.setIsCheck("Y");
                try {
                    ftpUtil.getFile(remoteFile, localDir);
                }
                catch (Exception e) {
                    log.setIsCheck("N");
                    logger.error("下载文件失败：" + fileName, e);
                }
                
                log.setNum(0L);
                log.setParentSeqno(entity.getExecInfoId());
                log.setTmpName("sftp-download");
                retEntity.getInterfLogs().add(log);
            });
        }
        else {
            for (ValInterfTemplate fileNamePart : tempList) {
                List<String> fileNames;
                if ("ZG".equals(fileNamePart.getSystemid())) {
                    fileNames = ftpUtil.listFileName(remotePath,
                        fileNamePart.getTmpEName() + "_" + DateUtil.format(entity.getOccurTime(),
                            DateUtil.DEFAULT_OTHER_FORMAT));
                }
                else {
                    fileNames = ftpUtil.listFileName(remotePath, fileNamePart.getTmpEName());
                }
                if (StringUtil.isEmptyList(fileNames)) {
                    logger.info("没有模板：" + fileNamePart.getTmpEName());
                    ValInterflog log = new ValInterflog();
                    log.setFileName(fileNamePart.getTmpEName());
                    // 操作日期
                    log.setExDate(new Date());
                    log.setIsCheck("N");
                    log.setNum(1L);
                    log.setParentSeqno(entity.getExecInfoId());
                    log.setTmpName("sftp-download");
                    retEntity.getInterfLogs().add(log);
                    continue;
                }
                fileNames.forEach(fileName->{
                    String remoteFile = remotePath + "/" + fileName;
                    ValInterflog log = new ValInterflog();
                    log.setFileName(fileName);
                    // 操作日期
                    log.setExDate(new Date());
                    log.setIsCheck("Y");
                    try {
                        ftpUtil.getFile(remoteFile, localDir+(fileName.split("/").length>1?File.separator+fileName.split("/")[0]:""));
                    }
                    catch (Exception e) {
                        log.setIsCheck("N");
                        logger.error("下载文件失败：" + fileName, e);
                    }
                    
                    log.setNum(0L);
                    log.setParentSeqno(entity.getExecInfoId());
                    log.setTmpName("sftp-download");
                    retEntity.getInterfLogs().add(log);
                });
            }

        }

        ftpUtil.close();
        return retEntity;
    }


    private void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); // 删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            boolean rec = myFilePath.delete(); // 删除空文件夹
            if(rec){
                logger.info("空文件夹删除成功");
            }
        }
        catch (Exception e) {
            logger.error("", e);
        }
    }

    // 删除指定文件夹下所有文件
    // param path 文件夹完整绝对路径
    private boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        if (tempList != null) {
            for (int i = 0; i < tempList.length; i++ ) {
                if (path.endsWith(File.separator)) {
                    temp = new File(path + tempList[i]);
                }
                else {
                    temp = new File(path + File.separator + tempList[i]);
                }
                if (temp.isFile()) {
                  boolean rec =  temp.delete();
                  if(rec){
                      logger.info("空文件夹删除成功");
                  }
                }
                if (temp.isDirectory()) {
                    delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
                    delFolder(path + "/" + tempList[i]);// 再删除空文件夹
                    flag = true;
                }
            }
        }
        return flag;
    }
}
