/*
 * 文件名：DataFileUpload001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangwei
 * 修改时间：2018年11月14日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.fileupload.service;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.JSchException;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.progressbar.ProgressBarService;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.tams.common.base.dao.BokFileInfoMapper;
import com.joyintech.tams.common.base.dao.BokValbalTableFileMapper;
import com.joyintech.tams.common.base.entity.BokFileInfo;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.common.service.BaseCommonService;
import com.joyintech.tams.valuation.common.utils.SftpTools;
import com.joyintech.tams.valuation.fileupload.dao.DataFileUpload002Mapper;

/**
 * 文件上传
 * 
 * @author zhagnwei
 * @version 1.0 2018年11月14日
 * @see DataFileUpload002Service
 * @since 1.0
 */
@Service
public class DataFileUpload002Service extends BaseService<BokFileInfo> {

    @Autowired
    DataFileUpload002Mapper dataFileUpload002Mapper;
    
    /**
     * 文件信息表mapper
     */
    @Autowired
    BokFileInfoMapper bokFileInfoMapper;
    
    /**
     * 估值表余额表上传文件信息mapper
     */
    @Autowired
    BokValbalTableFileMapper bokValbalTableFileMapper;

    @Autowired
    private ProgressBarService progressBarService;

    @Autowired
    private BaseCommonService baseCommonService;
    
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(DataFileUpload002Service.class);


    /**
     * 读取文件并上传到文件服务器
     * 
     * @param request
     * @throws Exception
     */
    public void readFile(HttpServletRequest request) throws Exception {
        // 文件本地路径（应用服务器路径，同名添加）
        String filePath = request.getParameter("fileName1");
        // 文件名称
        String fileName = FileNameUtil.formatFileName(filePath);
        try {
            File file = new File(filePath);
            boolean rec = file.renameTo(new File(fileName));
            if (rec) {
                logger.info("处理成功");
            }

            String Sysdate = baseCommonService.getSysDateString();
            if (Sysdate == null || "".equals(Sysdate)) {
                Sysdate = DateUtil.format(new Date());
            }

            String ip = CacheUtil.getSysParam("FILEUPLOAD_HOST");// 主机IP
            Integer port = Integer.parseInt(CacheUtil.getSysParam("FILEUPLOAD_PORT"));// 主机端口
            String username = CacheUtil.getSysParam("FILEUPLOAD_USERNAME");// 主机用户名
            String password = CacheUtil.getSysParam("FILEUPLOAD_PASSWORD");// 主机密码或秘钥地址
            String sftpLoginType = CacheUtil.getSysParam("SFTP_LOGIN_TYPE");// SFTP登录方式

            // File.separator 会根据应用平台选择，可能和文件服务器平台不同。
            // FILEUPLOAD_ACCOUNT_CHECK_PATH : 对账文件上传功能目录
            String remotePath = CacheUtil.getSysParam("FILEUPLOAD_ACCOUNT_CHECK_PATH") + "/"
                                + DateUtil.format(DateUtil.parse(Sysdate), DateUtil.DEFAULT_OTHER_FORMAT);// 主机远程路径

            SftpTools sftpTools = new SftpTools();
            sftpTools.login(username, password, ip, port, sftpLoginType);// sftp登录

            sftpTools.putFile(fileName, remotePath); // 上传至文件服务器
            sftpTools.close();// 关闭登录

            progressBarService.updatePercent("DocumentManagement002", ProgressBarService.FILE_UPLOAD_ID, 1D, false);
        } catch (JSchException e) {
            logger.error(e.getMessage(), e);
            throw e;
        } finally {
            File file = new File(fileName);
            if(file !=  null && file.isFile()) {
                if (file.delete()) {
                    logger.info("应用服务器文件删除成功！");
                }
            }
        }

    }

    /**
     * 上传并把数据插入数据库 
     * 
     * @param request
     * @return
     * @throws Exception
     */
    public int insertFileTransferInfo(HttpServletRequest request) throws Exception {
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("fileType", request.getParameter("fileType"));
    	params.put("filePath", request.getParameter("fileName1"));
    	params.put("fileNameOnly", request.getParameter("fileNameOnly"));
    	return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/DataFileUpload002/insertFileTransferInfo",
                params, Integer.class);
    }

    /**
     * 根据条件查询
     * @param map
     * @see
     * @since 1.0
     */
    public int countByCondition(Map<String, Object> map) {
        return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/DataFileUpload002/countByCondition",
                map, Integer.class);
    }

}
