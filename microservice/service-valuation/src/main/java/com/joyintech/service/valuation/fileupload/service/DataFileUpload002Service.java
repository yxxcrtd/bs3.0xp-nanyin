/*
 * 文件名：DataFileUpload001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangwei
 * 修改时间：2018年11月14日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.fileupload.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.joyintech.framework.util.*;
import org.apache.commons.lang3.StringUtils;
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
import com.joyintech.service.valuation.fileupload.dao.DataFileUpload002Mapper;
import com.joyintech.tams.common.base.dao.BokFileInfoMapper;
import com.joyintech.tams.common.base.dao.BokValbalTableFileMapper;
import com.joyintech.tams.common.base.entity.BokFileInfo;
import com.joyintech.tams.common.base.entity.BokValbalTableFile;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.service.BaseCommonService;
import com.joyintech.tams.valuation.common.utils.SftpTools;

/**
 * 文件上传
 * 
 * @author zhagnwei
 * @version 1.0 2018年11月14日
 * @see DataFileUpload002Service
 * @since 1.0
 */
@Service(value="serviceDataFileUpload002Service")
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


//    /**
//     * 读取文件并上传到文件服务器
//     *
//     * @param params
//     * @throws Exception
//     */
//    public void readFile(Map<String, Object> params)
//        throws Exception {
//        // 文件本地路径（应用服务器路径，同名添加）
//        String filePath = (String)params.get("filePath");
//        // 文件名称
//        String fileName = FileNameUtil.formatFileName(filePath);
//        try {
//            File file = new File(filePath);
//            boolean rec = file.renameTo(new File(fileName));
//            if (rec) {
//                logger.info("处理成功");
//            }
//
//            String Sysdate = baseCommonService.getSysDateString();
//            if (Sysdate == null || "".equals(Sysdate)) {
//                Sysdate = DateUtil.format(new Date());
//            }
//
//            String ip = CacheUtil.getSysParam("FILEUPLOAD_HOST");// 主机IP
//            Integer port = Integer.parseInt(CacheUtil.getSysParam("FILEUPLOAD_PORT"));// 主机端口
//            String username = CacheUtil.getSysParam("FILEUPLOAD_USERNAME");// 主机用户名
//            String password = CacheUtil.getSysParam("FILEUPLOAD_PASSWORD");// 主机密码或秘钥地址
//            String sftpLoginType = CacheUtil.getSysParam("SFTP_LOGIN_TYPE");// SFTP登录方式
//
//            // File.separator 会根据应用平台选择，可能和文件服务器平台不同。
//            // FILEUPLOAD_ACCOUNT_CHECK_PATH : 对账文件上传功能目录
//            String remotePath = CacheUtil.getSysParam("FILEUPLOAD_ACCOUNT_CHECK_PATH") + "/"
//                                + DateUtil.format(DateUtil.parse(Sysdate), DateUtil.DEFAULT_OTHER_FORMAT);// 主机远程路径
//
//            SftpTools sftpTools = new SftpTools();
//            sftpTools.login(username, password, ip, port, sftpLoginType);// sftp登录
//
//            sftpTools.putFile(fileName, remotePath); // 上传至文件服务器
//            sftpTools.close();// 关闭登录
//
//            progressBarService.updatePercent("DocumentManagement002", ProgressBarService.FILE_UPLOAD_ID, 1D, false);
//        } catch (JSchException e) {
//            logger.error(e.getMessage(), e);
//            throw e;
//        } finally {
//            File file = new File(fileName);
//            if(file !=  null && file.isFile()) {
//                if (file.delete()) {
//                    logger.info("应用服务器文件删除成功！");
//                }
//            }
//        }
//
//    }

    /**
     * 上传并把数据插入数据库 
     * 
     * @param (String)params
     * @return
     * @throws Exception
     */
    public int insertFileTransferInfo(Map<String, Object> params)
        throws Exception {
        BokFileInfo bokFileInfo = new BokFileInfo(); // 文件信息表记录表
        BokValbalTableFile bokValbalTableFile = new BokValbalTableFile(); // 估值表余额表上传文件信息表
        
        // 系统时间
        String Sysdate = baseCommonService.getSysDateString();
        if (Sysdate == null || "".equals(Sysdate)) {
            Sysdate = DateUtil.format(new Date());
        }
        
        // 命名示例 ： 估值表_A130F0003_20170706.xls
        String fileNameOnly = (String)params.get("fileNameOnly"); // 文件名
        String fileName = fileNameOnly.substring(0, fileNameOnly.lastIndexOf(".")); // 去后缀文件名
        String fileType = (String)params.get("fileType");
        
        String valBalFlag = StringUtils.EMPTY; // 01 估值，02 余额
        if(DictCodeList.文件类型_估值表.getValue().equals(fileType) 
            || DictCodeList.文件类型_估值表对账文件.getValue().equals(fileType) ) {
            valBalFlag = "01";
        }
        if(DictCodeList.文件类型_余额表.getValue().equals(fileType) 
            || DictCodeList.文件类型_余额表对账文件.getValue().equals(fileType) ) {
            valBalFlag = "02";
        }
        
//        String finprodId = fileName.split("_")[1]; //  产品代码
//        String valDate = fileName.split("_")[2]; // 估值日期
       
        String finprodId = StringUtils.EMPTY; //  产品代码
        String valDate = null; // 估值日期
        // 根据【文件标识_产品代码_估值日期】格式：将文件名用“_”转化为数组
        String[] nameArr = fileName.split("_");
        // 根据【文件标识_产品代码_估值日期】格式：估值日期 = 数组最后一个元素
        valDate = nameArr[nameArr.length - 1];
        // 根据【文件标识_产品代码_估值日期】格式：产品代码 = “首元素”和“最后一个元素”之间的元素组合
        for(int index = 0 ; index < nameArr.length; index ++){
            if(index==0 || index == nameArr.length - 1){
                continue;
            }

            //组建产品代码
            if(index == 1){
                finprodId = nameArr[index];
            }else{
                finprodId = finprodId + "_" + nameArr[index];
            }

        }        
        
        String fileId = IDGenerator.generate();// 生成流水号
        
        bokFileInfo.setFileId(fileId);// 文件主键
        bokFileInfo.setFileName(fileNameOnly);// 设置文件名称
        bokFileInfo.setFileType(fileType);// 设置文件类型
        
        // File.separator 会根据应用平台选择，可能和文件服务器平台不同。
        // FILEUPLOAD_ACCOUNT_CHECK_PATH : 对账文件上传功能目录
        // 文件存放目录
        String filePath = CacheUtil.getSysParam("FILEUPLOAD_ACCOUNT_CHECK_PATH") + "/"
                          + DateUtil.format(DateUtil.parse(Sysdate), DateUtil.DEFAULT_OTHER_FORMAT) + "/" + fileNameOnly;
        bokFileInfo.setFilePath(filePath);

        // 记录： 文件信息表记录表
        int row1 = bokFileInfoMapper.insertSelective(bokFileInfo); 
        
        bokValbalTableFile.setFinprodId(finprodId);// 产品代码
        bokValbalTableFile.setValDate(DateUtil.parse(valDate));//  估值时间
        bokValbalTableFile.setValBalFlag(valBalFlag);// 设置估值表余额表标志
        bokValbalTableFile.setFileId(fileId);// 文件主键
        bokValbalTableFile.setFileType(fileType);// 设置文件类型
        
        // 记录：估值表余额表上传文件信息
        int row2 = bokValbalTableFileMapper.insertSelective(bokValbalTableFile);
        
        return row1>0 && row2>0 ? 1 : -1 ;
    }

    /**
     * 根据条件查询
     * @param map
     * @see
     * @since 1.0
     */
    public int countByCondition(Map map) {

        // 日期转换，由于微服务调用无法识别map<String,Object>中的Date类型，故在此处理
        String valDateString = map.get("valDateString").toString();
        if(StringUtil.isNotEmpty(valDateString)){
            Date valDate = DateUtil.parse(valDateString); // 估值日期
            map.put("valDate",valDate);
        }

        return dataFileUpload002Mapper.countByCondition(map);
    }

}
