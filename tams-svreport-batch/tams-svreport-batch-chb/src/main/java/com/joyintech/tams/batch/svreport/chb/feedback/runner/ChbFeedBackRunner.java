/*
 * 文件名：ChbFeedBackRunner.java
 * 版权：Copyright 2020-2022 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：王波
 * 修改时间：2020年10月20日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */

package com.joyintech.tams.batch.svreport.chb.feedback.runner;

import cn.hutool.core.collection.CollectionUtil;
import com.joyintech.background.component.common.pack.common.util.DateUtil;
import com.joyintech.background.component.common.pack.common.util.JsonUtils;
import com.joyintech.background.component.common.pack.common.util.StringUtil;
import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.component.svrreport.chb.contant.ChbConstant;
import com.joyintech.component.svrreport.chb.contant.DictCodeList;
import com.joyintech.component.svrreport.chb.dto.AsynReport40Entity;
import com.joyintech.component.svrreport.chb.dto.AsynReportEntity;
import com.joyintech.component.svrreport.chb.dto.ChbInfoReportDto;
import com.joyintech.component.svrreport.chb.feedbackmsg.dto.ChbFeedbackFile;
import com.joyintech.component.svrreport.chb.feedbackmsg.service.ChbFeedbackMsgService;
import com.joyintech.component.svrreport.chb.msgsend.dto.ExtFprDClient;
import com.joyintech.framework.jdbc.datasource.DynamicDataSourceHolder;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 王波
 * @date 2020年10月20日
 * @see ChbFeedBackRunner
 * @since 1.0
 */
@Service(value = "chbFeedBackRunner")
@Scope("prototype")
public class ChbFeedBackRunner extends BatchBaseService {

    @Autowired
    private ChbFeedbackMsgService chbFeedbackMsgService;

    /**
     * 中债一二三期部署方式(01:独立一二期  03：独立三期  00：一起部署)
     */
    @Value("${application.chb.chbPerNo:00}")
    private String chbPerNo;

    /**
     * 中债反馈文件下载获取方式(01：DI获取   02：本地获取)
     */
    @Value("${application.chb.diDirectAccessFlg:01}")
    private String diDirectAccessFlg;

    private static final Logger logger = LoggerFactory.getLogger(ChbFeedBackRunner.class);

    @Override
    public JobExecution execute() throws BatchException {

        JobExecution result = new JobExecution(1l);
        // 调用组件处理反馈信息
        try {
//            chbFeedbackMsgService.dealFeedBack(new Date(), chbFeedbackFileList);



            // add by zhangliang 20210527 关于多数据源的配置写在组件里面切换不合理，应该是在应用层面去决定 start
            Date dealDate = new Date();

            //查询中债对应的发行机构代码
            String registBankCode = "";
            List<String> registBankCodeList = chbFeedbackMsgService.queryChbOrgRegistBankCodeList();
            if (!StringUtil.isEmptyList(registBankCodeList)){
                registBankCode = registBankCodeList.get(0);
            }

            // 查询待处理反馈文件列表
            List<ChbFeedbackFile> chbFeedbackFileList = this.queryChbFeedbackFileList();
            // add by zhangliang 20210511 处理待处理反馈文件 start
            if (!StringUtil.isEmptyList(chbFeedbackFileList)){
                logger.debug("处理中债报送反馈结果待处理文件开始");
                for (ChbFeedbackFile chbFeedbackFile : chbFeedbackFileList){
                    FileOutputStream fos = null;
                    FileChannel channel = null;
                    FileLock lock = null;
                    File lockFile = null;
                    try{
                        String fileLockPath = chbFeedbackMsgService.getDownloadFilePath() + "lock" + File.separator;
                        // 查看锁文件是否存在
                        File lockPath = new File(fileLockPath);
                        if (!lockPath.exists()) {
                            lockPath.mkdirs();
                        }
                        lockFile = new File(fileLockPath + File.separator + chbFeedbackFile.getFileName().replace(".zip","") + "up.lockfile");
                        if (!lockFile.exists()) {
                            lockFile.createNewFile();
                        }
                        fos = new FileOutputStream(lockFile);
                        channel = fos.getChannel();
                        // 获得锁方法一：lock()，阻塞的方法，当文件锁不可用时，当前进程会被挂起
                        lock = channel.tryLock();// 无参lock()为独占锁
                        if (lock == null) {
                            logger.error("当前中债反馈文件" + chbFeedbackFile.getFileName() + "已被锁！");
                            continue;
                        }

                        if (StringUtil.isNotEmpty(chbFeedbackFile.getFeedbackMsg())){
                            AsynReportEntity asynReportEntity = JsonUtils.json2Object(chbFeedbackFile.getFeedbackMsg(), AsynReportEntity.class);
                            analysisData(asynReportEntity, dealDate, chbFeedbackFile, registBankCode);
                        }
                    }catch (FileNotFoundException e) {
                        logger.error("当前中债反馈文件" + chbFeedbackFile.getFileName() + e.getMessage(), e);
                        continue;
                    }
                    catch (IOException e) {
                        logger.error("当前中债反馈文件" + chbFeedbackFile.getFileName() + e.getMessage(), e);
                        continue;
                    }catch (Exception e) {
                        //保证本地文件处理异常的情况下，可以继续走DI机，取得最新的中债处理完成的文件进行处理
                        logger.error("本地未处理文件处理异常：" + chbFeedbackFile.getFileName() + e.getMessage(), e);
                        continue;
                    }
                    finally {
                        if (lock != null) {
                            try {
                                lock.release();
                            }
                            catch (IOException e) {
                                logger.error("当前中债反馈文件" + chbFeedbackFile.getFileName() + e.getMessage(),e);
                            }
                        }
                        if (channel != null) {
                            try {
                                channel.close();
                            }
                            catch (IOException e) {
                                logger.error("当前中债反馈文件" + chbFeedbackFile.getFileName() + e.getMessage(),e);
                            }
                        }
                        if (fos != null) {
                            try {
                                fos.close();
                            }
                            catch (IOException e) {
                                logger.error("当前中债反馈文件" + chbFeedbackFile.getFileName() + e.getMessage(),e);
                            }
                        }
                        if(lockFile != null){
                            lockFile.delete();
                        }
                    }
                }
                logger.debug("处理中债报送反馈结果待处理文件结束");
            }
            // add by zhangliang 20210511 处理待处理反馈文件 end

            logger.debug("开始处理读取中债报送反馈结果文件");
            if (!StringUtil.equals(diDirectAccessFlg, "02")){
                Map<String, String> serverMap = ExtFprDClient.getDimServer();
                if (CollectionUtil.isNotEmpty(serverMap)) {
                    String finalRegistBankCode = registBankCode;
                    serverMap.forEach((regBankCode, serverUrl) -> {
                        ExtFprDClient.setRegBankCode(regBankCode);
                        this.getMessageSigle(dealDate, finalRegistBankCode);
                    });
                } else {
                    this.getMessageSigle(dealDate, registBankCode);
                }
            }
            logger.debug("开始处理读取中债报送反馈结果文件");
            // add by zhangliang 20210527 关于多数据源的配置写在组件里面切换不合理，应该是在应用层面去决定 end
        } catch (Exception e) {
            result.setStatus(BatchStatus.FAILED);
        }

        result.setStatus(BatchStatus.COMPLETED);
        return result;
    }

    /**
     * 轮询中债报送结果(单个直连机)
     *
     * @param dealDate 业务日期
     * @see
     * @since 1.0
     */
    public void getMessageSigle(Date dealDate, String registBankCode) {
        List<AsynReportEntity> messages = chbFeedbackMsgService.getMessage(ChbConstant.MSG_KIND_20);

        AsynReportEntity msg = new AsynReportEntity();
        if (CollectionUtil.isNotEmpty(messages)) {
            for (AsynReportEntity message : messages) {
                try {
                    analysisData(message, dealDate, null, registBankCode);
                }
                catch (Exception e) {
                    logger.error("analysisData出现异常，异常内容：" + e.getMessage(),e);
                    continue; // 当前报错跳过执行下一条
                }
            }
        }

        List<AsynReport40Entity> messages1 = chbFeedbackMsgService.getMessageFor40(ChbConstant.MSG_KIND_40);
        if (CollectionUtil.isNotEmpty(messages1)) {
            for (AsynReport40Entity message : messages1) {
                try {
                    logger.info("getProdRecode开始");
                    chbFeedbackMsgService.getProdRecode(message, dealDate);
                    logger.info("getProdRecode结束");
                }
                catch (Exception e) {
                    logger.error("getProdRecode出现异常，异常内容：" + e.getMessage(),e);
                    continue; // 当前报错跳过执行下一条
                }
            }
        }
    }

    private void analysisData(AsynReportEntity message, Date dealDate, ChbFeedbackFile chbFeedbackFile, String registBankCode){
        logger.info("analysisData开始");
        // 报送报文编码
        String repMesNo = message.getMsgHeader().getBizInfo().getOrigMsgId().getValue();
        // 查询 报送编码
        List<ChbInfoReportDto> reports = this.selectReportNoByRepMesNo(repMesNo);
        // 匹配更新
        ChbFeedbackFile chbFeedbackFileUp = chbFeedbackMsgService.analysisData(message, dealDate, chbFeedbackFile, registBankCode, reports);
        logger.info("analysisData结束");

        if (chbFeedbackFileUp != null){
            // 更新反馈文件管理表
            chbFeedbackFileUp.setMovePath(moveFileToDirectory(chbFeedbackMsgService.getDownloadFilePath(), message.getDocument().getFileName()));// 迁移后的放置目录
            updateChbFeedbackFileByNo(chbFeedbackFileUp);
        }
    }

    /**
     * 查询待处理反馈文件列表
     * @return
     */
    private List<ChbFeedbackFile> queryChbFeedbackFileList(){
        List<ChbFeedbackFile> chbFeedbackFileList = chbFeedbackMsgService.queryChbFeedbackFileList(null);
        if (!StringUtil.equals(chbPerNo, "00")){
            try{
                DynamicDataSourceHolder.putDataSource("chbSendDataSource");
                chbFeedbackFileList.addAll(chbFeedbackMsgService.queryChbFeedbackFileList(null));
                DynamicDataSourceHolder.clearDataSource();
            }catch (Exception e){
                logger.error("查询从数据源待处理反馈文件列表异常，异常内容：" + e.getMessage());
            }
        }

        return chbFeedbackFileList;
    }

    /**
     * 查询CHB_INFO_REPORT对象
     * @return
     */
    public List<ChbInfoReportDto> selectReportNoByRepMesNo(String repMesNo){
        List<ChbInfoReportDto> reports = chbFeedbackMsgService.selectReportNoByRepMesNo(repMesNo);
        if (!StringUtil.equals(chbPerNo, "00")){
            try{
                DynamicDataSourceHolder.putDataSource("chbSendDataSource");
                reports.addAll(chbFeedbackMsgService.selectReportNoByRepMesNo(repMesNo));
                DynamicDataSourceHolder.clearDataSource();
            }catch (Exception e){
                logger.error("查询从数据源中债直连报送表异常，异常内容：" + e.getMessage());
            }
        }

        return reports;
    }

    /**
     * 记录反馈文件管理表
     * @param chbFeedbackFile 文件管理对象
     */
    public void updateChbFeedbackFileByNo(ChbFeedbackFile chbFeedbackFile){
        logger.info("更新反馈文件管理表开始");
        if (!StringUtil.equals(diDirectAccessFlg, "01")){
            DynamicDataSourceHolder.putDataSource("chbSendDataSource");
            chbFeedbackMsgService.updateChbFeedbackFileByNo(chbFeedbackFile);
            DynamicDataSourceHolder.clearDataSource();
        }else{
            chbFeedbackMsgService.updateChbFeedbackFileByNo(chbFeedbackFile);
        }
        logger.info("更新反馈文件管理表结束");
    }

    /**
     * 反馈文件处理结束文件迁移
     * @param filePath 文件路径
     * @param fileName 文件名称
     * @return
     */
    public String moveFileToDirectory(String filePath, String fileName){
        // 获取文件路径
        String filePathBak = chbFeedbackMsgService.getDownloadFilePathMovePath();
        try {
            FileUtils.moveFileToDirectory(new File(filePath + fileName), new File(filePathBak), true);
            FileUtils.moveFileToDirectory(new File(filePath + fileName.replace(".zip",".xml")), new File(filePathBak), true);
        }catch (Exception e){
            logger.error("反馈文件处理结束文件迁移异常，异常内容：" + e.getMessage());
        }
        return filePathBak;
    }

}
