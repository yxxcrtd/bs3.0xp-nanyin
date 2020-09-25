/*
 * 文件名：UpLoadFileService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.loadfile.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.InterfFileLog;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.core.constant.InterfConstant;
import com.joyintech.service.interf.core.constant.InterfDictCodeList;
import com.joyintech.service.interf.core.service.InterfBaseService;
import com.joyintech.service.interf.core.util.InterfException;
import com.joyintech.service.interf.core.util.InterfUtil;
import com.joyintech.service.interf.isms003.entity.InterfSMS003Entity;
import com.joyintech.service.interf.isms004.entity.InterfSMS004Entity;
import com.joyintech.service.interf.isms004.service.InterfSMS004SendService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.pingan.bfts.bftscore.client.FileAccessConfig;
import com.pingan.bfts.bftscore.client.FileClient;
import com.pingan.bfts.bftscore.client.IAccess;
import com.pingan.bfts.bftscore.client.Reply;

/**
 * 文件上传Service类
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月21日
 * @see UpLoadFileService
 * @since 1.0
 */
@Service
public class UpLoadFileService extends InterfBaseService {

    /**
     * 文件通知接口
     */
    @Autowired
    private InterfSMS004SendService sms004Service;

    /**
     * 远端服务连接配置
     */
    private FileAccessConfig config;

    /**
     * 发送通知EntityKEY
     */
    private static final String NOTICE_ENTITY = "NOTICE_ENTITY";

    /**
     * 初始化变量
     * 
     * @param entity 连接参数
     * @see
     * @since 1.0
     */
    private void initService(InterfFileLog entity) {
        try {
            config = InterfUtil.getFileAccessConfig(entity);
        }
        catch (Exception e) {
            config = null;
            getLogger().error("初始化异常：{}", e.getMessage());
        }
    }

    /**
     * 文件上传入口
     * 
     * @param id 接口ID
     * @param uuid 处理识别码
     * @param seqNo 报文流水号
     * @param type 处理类别，INF_BFTS/INF_ODS
     * @param file 上传的本地文件
     * @return 上传路径
     * @see
     * @since 1.0
     */
    public String upLoadFile(String id, String uuid, String seqNo, String type, String file) {
        // 设置处理识别码
        setUuid(uuid);
        // 取得系统参数
        Map<String, Object> interfCahe = getSysParamMap();
        // 构建连接参数
        boolean isBFTS = StringUtil.startsWith(type, InterfConstant.INF_BFTS);
        InterfFileLog entity = InterfUtil.getInterfFileLog(interfCahe, type);
        entity.setBftsFlg(
            isBFTS ? DictCodeList.是否_是.getValue() : DictCodeList.是否_否.getValue());

        String name = StringUtil.substringAfterLast(file, String.valueOf(File.separatorChar));
        entity.setFtpName(name);
        // 构建上传路径
        String path = createFtpsFile(entity);
        Thread runner = new Thread() {

            /**
             * {@inheritDoc}
             */
            @Override
            public void run() {
                // 初始化连接
                initService(entity);

                boolean result = false;
                try {
                    // 上传
                    result = loadFile(file, path);
                    // 设置上传结果
                    setHandleOK(result);
                    if (!result) {
                        setErrMsg("文件上传失败");
                    }
                }
                catch (Exception e) {
                    getLogger().error("文件上传异常：{}", e.getMessage());
                    doException(e, "文件上传异常");
                }
                // 文件日志登录
                addLog(entity);
                // 发送文件通知
                if (result && isBFTS) {
                    getNoticeEntity().setFilePath(path);
                    sendNotice();
                }
                // 接口日志登录
                else {
                    addInterfLog(id, seqNo, DictCodeList.是否_否.getValue(),
                        StringUtil.EMPTY, StringUtil.EMPTY);
                }
                super.run();
            }

        };
        runner.start();
        return path;
    }

    /**
     * 日志登录
     * 
     * @param entity 日志内容
     * @see
     * @since 1.0
     */
    private void addLog(InterfFileLog entity) {
        // 处理识别号
        entity.setUuid(getUuid());
        // 操作类型 上传
        entity.setExcuteType(InterfDictCodeList.文件操作类型_上传.getValue());
        // 错误信息
        entity.setErrMessage(getErrMsg());
        // 执行结果
        entity.setTaskStatus(
            isHandleOK() ? InterfDictCodeList.执行状态_成功.getValue() : InterfDictCodeList.执行状态_失败.getValue());
        getAddLogService().addFileLog(entity);
    }

    /**
     * 发送文件通知
     * 
     * @return 结果
     * @see
     * @since 1.0
     */
    private InterfSMS003Entity sendNotice() {
        Map<String, Object> param = new HashMap<>();
        param.put(InterfConstant.SYS_DATE, getSysDate());
        param.put(InterfConstant.UUID, getUuid());
        param.put(InterfConstant.PROD_INFO, getNoticeEntity());
        return sms004Service.sendRequest(param);
    }

    /**
     * 构建ftps上传路径</br>
     * ftps://server:port/
     * 
     * @param entity 构建参数
     * @return ftps上传路径
     * @see
     * @since 1.0
     */
    private String createFtpsFile(InterfFileLog entity) {
        StringBuilder builder = new StringBuilder("ftps://");
        builder.append(entity.getIp()).append(":").append(entity.getPort());
        String bftsFilePath = InterfUtil.pathEndAsSeparator(entity.getFtpPath());
        builder.append(bftsFilePath);
        builder.append(getSysDate());
        builder.append("/").append(entity.getFtpName());
        return builder.toString();
    }

    /**
     * {@inheritDoc}
     */
    public synchronized boolean loadFile(String srcFile, String dstFile)
        throws Exception {
        if (config == null) {
            throw new InterfException("连接初始化失败");
        }
        // 建立连接
        IAccess acc = FileClient.createFileClient(config);
        // 打开连接
        acc.open();
        // 文件上传
        Object ob = acc.copy(srcFile, dstFile);

        boolean result = Reply.isOK(ob);
        getLogger().info("文件上传成功:{}; 本地文件:{}; 上传路径:{}", result, srcFile, dstFile);
        return result;
    }

    /**
     * 获取 noticeEntity
     * 
     * @return noticeEntity.
     */
    public InterfSMS004Entity getNoticeEntity() {
        return (InterfSMS004Entity)getServiceInfo(NOTICE_ENTITY);
    }

    /**
     * 设置 noticeEntity
     * 
     * @param noticeEntity noticeEntity
     */
    public void setNoticeEntity(InterfSMS004Entity noticeEntity) {
        setServiceInfo(NOTICE_ENTITY, noticeEntity);
    }
}
