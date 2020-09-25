/*
 * 文件名：InterSMS004Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年10月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.isms004.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.dc.eai.data.CompositeData;
import com.joyintech.tams.common.base.entity.InterfFileLog;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.core.annotation.EsbSyshead;
import com.joyintech.service.interf.core.constant.EsbServiceInfoList;
import com.joyintech.service.interf.core.constant.InterfConstant;
import com.joyintech.service.interf.core.constant.InterfDictCodeList;
import com.joyintech.service.interf.core.constant.SqlldrColsEnum;
import com.joyintech.service.interf.core.service.ReceivedEsbService;
import com.joyintech.service.interf.core.util.InterfException;
import com.joyintech.service.interf.core.util.InterfUtil;
import com.joyintech.service.interf.cstptrade.service.CstpTradeService;
import com.joyintech.service.interf.isms003.entity.InterfSMS003Entity;
import com.joyintech.service.interf.isms004.entity.InterfSMS004Entity;
import com.joyintech.service.interf.ods.service.OdsFileReadService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tools.util.FileUtil;
import com.pingan.bfts.bftscore.client.FileAccessConfig;
import com.pingan.bfts.bftscore.client.FileClient;
import com.pingan.bfts.bftscore.client.FileObject;
import com.pingan.bfts.bftscore.client.IAccess;
import com.pingan.bfts.bftscore.client.Reply;

/**
 * 接口SMS004详细处理
 * 
 * @author zhangrui1
 * @version 1.0 2018年10月22日
 * @see InterfSMS004Service
 * @since 1.0
 */
@Service
@EsbSyshead(value = EsbServiceInfoList.INF_SMS004)
public class InterfSMS004Service extends ReceivedEsbService<InterfSMS003Entity, InterfSMS004Entity> {

    /**
     * 远端服务连接配置
     */
    private FileAccessConfig config;

    /**
     * 线程池
     */
    @Autowired(required = false)
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    /**
     * ODS文件读取服务
     */
    @Autowired
    private OdsFileReadService odsService;

    /**
     * cstp交易入库服务
     */
    @Autowired
    private CstpTradeService cstpTradeSvc;

    /**
     * 文件扩展名xml
     */
    private static final String XML = "xml";

    /**
     * 文件路径
     */
    private static final String SMS004_FILE_PATH = "SMS004_FILE_PATH";

    /**
     * 服务启动
     * 
     * @param fileList 文件列表
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    private void executeService(List<File> fileList)
        throws Exception {
        CountDownLatch countDown = new CountDownLatch(fileList.size());
        for (File file : fileList) {
            String fileName = file.getName();
            String ex = FileUtil.getExtensionName(fileName);
            if (!XML.equals(ex)) {
                odsService.init(getUuid(), getInterfId(), getBizSeqNo());
            }
            threadPoolTaskExecutor.execute(new Runnable() {

                @Override
                public void run() {
                    if (XML.equals(ex)) {
                        cstpTradeSvc.insertCstpTrade(file.getAbsolutePath());
                    }
                    else {
                        // 取得不带扩展的文件名
                        String table = StringUtil.replaceAll(FileUtil.getFileNameNoEx(fileName),
                            InterfConstant.UNDERLINE + getSysDate(), StringUtil.EMPTY);
                        // 执行文件录入
                        odsService.loadFileIntoTable(SqlldrColsEnum.getTableByDataFile(table),
                            file.getAbsolutePath());
                    }
                    countDown.countDown();
                }
            });
        }
    }

    /**
     * 初始化变量
     * 
     * @param entity 连接参数
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    private void initService(InterfFileLog entity)
        throws Exception {
        entity.setBftsFlg(DictCodeList.是否_是.getValue());
        config = InterfUtil.getFileAccessConfig(entity);
        if (config == null) {
            doException(new Exception("无法实例化连接配置"), "连接配置异常");
        }
    }

    /**
     * 文件下载
     * 
     * @param filePath 文件路径
     * @param localpath 下载本地路径
     * @return 文件名列表
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    private synchronized List<File> fileDownload(String filePath, String localpath)
        throws Exception {
        IAccess acc = null; // 连接
        String file = null; // 文件全名
        List<File> fileList = new ArrayList<>();
        localpath = InterfUtil.pathEndAsSeparator(localpath);
        // 创建文件夹
        if (!InterfUtil.createFolder(localpath)) {
            throw new InterfException("创建本地文件夹异常:" + localpath);
        }
        acc = FileClient.createFileClient(config); // 建立连接
        acc.open(); // 打开连接
        List<?> l = acc.list(filePath); // 列出所有文件
        if (StringUtil.isEmptyList(l)) {
            file = filePath;
            String fName = StringUtil.substring(filePath,
                StringUtil.lastIndexOf(filePath, "/") + 1);
            String localFile = localpath + fName;
            // 文件下载
            Object ob = acc.copy(file, localFile);
            if (Reply.isOK(ob)) {
                getLogger().info("文件下载成功:{}", localFile);
                fileList.add(new File(localpath + fName));
            }
            else {
                getLogger().info("文件下载失败:{}", localFile);
            }
        }
        else {
            for (Object obj : l) {
                String fName = ((FileObject)obj).getName();
                if (StringUtil.contains(filePath, fName)) {
                    file = filePath;
                }
                else {
                    file = filePath + fName;
                }
                String localFile = localpath + fName;
                // 文件下载
                Object ob = acc.copy(file, localFile);
                if (Reply.isOK(ob)) {
                    getLogger().info("文件下载成功:{}", localFile);
                    fileList.add(new File(localpath + fName));
                }
                else {
                    getLogger().info("文件下载失败:{}", localFile);
                }
            }
        }
        addFileLog(config, file, DictCodeList.是否_是.getValue(), filePath,
            FileUtil.getExtensionName(file), InterfDictCodeList.文件操作类型_下载.getValue(),
            StringUtil.EMPTY, StringUtil.EMPTY);
        return fileList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveInterfData(InterfSMS004Entity entity)
        throws Exception {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveBusinessData(InterfSMS004Entity entity)
        throws Exception {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CompositeData doReceivedData(CompositeData reqData, String uuid) {
        String path = reqData.getStruct(InterfConstant.SYS_HEAD).getField(
            InterfConstant.KEY_FILE_PATH).strValue();
        setServiceInfo(SMS004_FILE_PATH, path);
        return super.doReceivedData(reqData, uuid);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InterfSMS003Entity selectResponseData(InterfSMS004Entity entity)
        throws Exception {
        InterfSMS003Entity resEntity = new InterfSMS003Entity();
        Map<String, Object> interfCahe = getSysParamMap();
        // 连接参数取得
        InterfFileLog logEntity = InterfUtil.getInterfFileLog(interfCahe, InterfConstant.INF_BFTS);
        // 初始化变量
        initService(logEntity);
        // 从系统参数取得本地路径
        String localpath = (String)interfCahe.get(
            InterfConstant.INF_BFTS + InterfConstant.UNDERLINE + InterfConstant.KEY_LOCALPATH);
        // 文件下载
        String path = (String)getServiceInfo(SMS004_FILE_PATH);
        List<File> fileList = fileDownload(path, localpath);
        // 文件处理
        executeService(fileList);
        resEntity.setConsumerId(entity.getCosumerId());
        resEntity.setFilePath(entity.getFilePath());
        resEntity.setResult(
            isHandleOK() ? InterfConstant.ESB_RET_STATUS_S : InterfConstant.ESB_RET_STATUS_F);
        resEntity.setSeqId(entity.getSeqId());
        resEntity.setArr(entity.getArr());
        return resEntity;
    }
}
