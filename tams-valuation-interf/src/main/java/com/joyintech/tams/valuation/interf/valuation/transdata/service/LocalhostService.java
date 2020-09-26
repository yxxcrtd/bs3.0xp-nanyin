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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.util.SearchFilePath;
import com.joyintech.tams.valuation.interf.valuation.transdata.entity.ValRecvFileEntity;
import com.joyintech.tams.valuation.interf.valuation.transdata.entity.ValRecvServerInfoEntity;
import com.joyintech.tams.common.base.entity.ValInterfTemplate;
import com.joyintech.tams.common.base.entity.ValInterflog;

@Service
public class LocalhostService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(LocalhostService.class);

    /**
     * 文件接收
     * 
     * @param entity
     * @throws Exception
     */
    public ValRecvFileEntity doRecv(ValRecvServerInfoEntity entity,
                                    List<ValInterfTemplate> tempList)
        throws Exception {
        ValRecvFileEntity retEntity = new ValRecvFileEntity();
        String localDir = entity.getLocalPath() + File.separator + entity.getSystemID()
                          + File.separator
                          + DateUtil.format(entity.getOccurTime(), DateUtil.DEFAULT_OTHER_FORMAT);
        File localDirFile = new File(localDir);
        if (localDirFile.exists()) {
            delFolder(localDir);
        }
        localDirFile.mkdirs();
        // 远程路径
        String remotePath = entity.getRemotePath() + File.separator + DateUtil.format(entity.getOccurTime(),
            DateUtil.DEFAULT_OTHER_FORMAT);
        // 如果是空的列表，全部下载
        if (StringUtil.isEmptyList(tempList)) {
        	List<File> files = SearchFilePath.searchAllFile(remotePath,false);
        	files.forEach(file->{
                String remoteFile = remotePath + File.separator + file.getName();
                SearchFilePath.copyFile(remoteFile, localDir);
                ValInterflog log = new ValInterflog();
                log.setFileName(file.getName());
                // 操作日期
                log.setExDate(new Date());
                log.setIsCheck("Y");
                log.setNum(1L);
                log.setParentSeqno(entity.getExecInfoId());
                log.setTmpName("ftp-download");
                retEntity.getInterfLogs().add(log);
            });
        }
        else {
            for (ValInterfTemplate fileNamePart : tempList) {
            	/**modify by wujie on 20181225 start**/
            	List<File> files = new ArrayList<File>();
            	if ("ZG".equals(fileNamePart.getSystemid())) {
            		String fileName = null;
                    if(StringUtil.equals(fileNamePart.getTmpType(), DictCodeInterfList.接口文件类型_STTXT.getValue())){                   
                        fileName = fileNamePart.getTmpEName() + DateUtil.format(entity.getOccurTime(),
                             DateUtil.DEFAULT_OTHER_FORMAT);
                        
                        files = SearchFilePath.searchFileByName(remotePath,fileName);
                        
                     } else {
                         fileName = fileNamePart.getTmpEName() + "_" + DateUtil.format(entity.getOccurTime(),
                             DateUtil.DEFAULT_OTHER_FORMAT);
                         
                         files = SearchFilePath.searchFileByName(remotePath,fileName);
                     }
            	}
//              List<File> files = SearchFilePath.searchFileByName(remotePath,fileNamePart.getTmpEName());
            	/**modify by wujie on 20181225 end**/
                if (StringUtil.isEmptyList(files)) {
                    logger.info("没有模板：" + fileNamePart.getTmpEName());
                    ValInterflog log = new ValInterflog();
                    log.setFileName(fileNamePart.getTmpEName());
                    // 操作日期
                    log.setExDate(new Date());
                    log.setIsCheck("N");
                    log.setNum(1L);
                    log.setParentSeqno(entity.getExecInfoId());
                    log.setTmpName("ftp-download");
                    retEntity.getInterfLogs().add(log);
                    continue;
                }
                files.forEach(file->{
                    String remoteFile = remotePath + "/" + file.getName();
                    SearchFilePath.copyFile(remoteFile, localDir);
                    ValInterflog log = new ValInterflog();
                    log.setFileName(file.getName());
                    // 操作日期
                    log.setExDate(new Date());
                    log.setIsCheck("Y");
                    log.setNum(1L);
                    log.setParentSeqno(entity.getExecInfoId());
                    log.setTmpName("ftp-download");
                    retEntity.getInterfLogs().add(log);
                });
            }

        }
        return retEntity;
    }

    private void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); // 删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            boolean rec= myFilePath.delete(); // 删除空文件夹
            if(rec){
                logger.info(" 删除空文件夹成功");
            }
        }
        catch (Exception e) {
           logger.error("删除文件夹失败",e);
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
	                boolean rec = temp.delete();
	                if(rec){
	                	 logger.info(" 删除空文件夹成功");
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
