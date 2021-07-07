package com.joyintech.tams.report.system.chbfeedbackfilemanagement.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.n3r.idworker.Sid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.joyintech.background.component.common.pack.common.util.DateUtil;
import com.joyintech.background.component.common.pack.common.util.JsonUtils;
import com.joyintech.component.svrreport.chb.contant.DictCodeList;
import com.joyintech.component.svrreport.chb.dto.AsynReportEntity;
import com.joyintech.component.svrreport.chb.dto.ChbInfoReportDto;
import com.joyintech.component.svrreport.chb.dto.ChbSubmitStatusDto;
import com.joyintech.component.svrreport.chb.dto.CommonNodeEntity;
import com.joyintech.component.svrreport.chb.dto.InformationEntity;
import com.joyintech.component.svrreport.chb.dto.XbrliDownLoadXml;
import com.joyintech.component.svrreport.chb.feedbackmsg.dto.ChbFeedbackFile;
import com.joyintech.component.svrreport.chb.feedbackmsg.service.ChbFeedbackMsgService;
import com.joyintech.component.svrreport.chb.msgsend.service.CnBondSendService;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.datasource.DynamicDataSourceHolder;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.report.system.chbfeedbackfilemanagement.dao.ChbFeedbackFileManagementMapper;
import com.joyintech.tams.report.system.chbfeedbackfilemanagement.dto.ChbFeedbackFileManagementDto;
import com.joyintech.tams.report.system.chbfeedbackfilemanagement.dto.ChbFeedbackFileMatchDetailDto;
import com.joyintech.tams.report.system.chbfeedbackfilemanagement.dto.ChbFeedbackFileMatchInfoDto;

import cn.hutool.core.util.StrUtil;

/**
 * @program: tams-svreport
 * @description:
 * @author: wanghai
 * @create: 2021-05-10 11:28
 **/
@Service("chbFeedbackFileManagementService")
public class ChbFeedbackFileManagementService extends BaseService<ChbFeedbackFileManagementDto> {

    @Autowired
    private ChbFeedbackFileManagementMapper chbFeedbackFileManagementMapper;

    @Autowired
    private ChbFeedbackFileAnalysisUtil chbFeedbackFileAnalysisUtil;

    /**
     * 中债反馈文件下载获取方式(01：DI获取   02：本地获取)
     */
    @Value("${application.chb.diDirectAccessFlg:01}")
    private String diDirectAccessFlg;
    
    @Value("${application.chb.download-file-path-movepath:}")
    private String downloadFilePathMovePath;

    /**
     * {@linkplain}
     */
    @Autowired
    private ChbFeedbackMsgService chbFeedbackMsgService;

    /**
     * {@linkplain}
     */
    @Autowired
    private CnBondSendService cnBondSendService;

    private static final Logger logger = LoggerFactory.getLogger(ChbFeedbackFileManagementService.class);

    /**
     * 页面查询列表
     * @param param
     * @return
     */
    public Page<ChbFeedbackFileManagementDto> findPage(int pagenum, int length, Map<String, Object> param) {
        String repSeqNo = (String) param.get("repSeqNo");
        if(StringUtil.isNotEmpty(repSeqNo)){
            //因为需要查询全部内容再过滤，所以不采用分页模式
            param.put("handleStatus", DictCodeListExt3.中债一二三期文件处理状态_未处理.getValue());
            List<ChbFeedbackFileManagementDto> list = null;
            if (!StringUtil.equals(diDirectAccessFlg, "01")){
                list = queryFeedbackListOthSrevice(param);
            }else{
                list = chbFeedbackFileManagementMapper.queryChbFeedbackFileList(param);
            }
            return analysisQueryResult(repSeqNo, pagenum, length, list);
        }else{
            //使用分页查询
            if (!StringUtil.equals(diDirectAccessFlg, "01")){
                return queryFeedbackPageOthSrevice(pagenum, length, param);
            }else{
                return queryForPage(pagenum, length, ()->chbFeedbackFileManagementMapper.queryChbFeedbackFileList(param));
            }
        }
    }

    /**
     * 根据流水号从反馈文件中找到匹配的文件列表
     * @param repSeqNo 流水号
     * @param pageNum
     * @param length
     * @param list
     * @return
     */
    private Page<ChbFeedbackFileManagementDto> analysisQueryResult(String repSeqNo, int pageNum, int length,
                List<ChbFeedbackFileManagementDto> list){
        if(list == null || list.size() == 0){
            return new Page<>(list);
        }
        List<ChbFeedbackFileManagementDto> resultList = new ArrayList<>();
        //根据流水号找到对应的记录
        for(ChbFeedbackFileManagementDto dto:list){
            File file = new File(dto.getFilePath()+dto.getFileName());
            if(file.exists() && StringUtil.lowerCase(dto.getFileName()).endsWith(".zip")){
                //解析文件
                List<XbrliDownLoadXml> xmlList = chbFeedbackFileAnalysisUtil.analysisXmlFileList(dto.getFilePath(), dto.getFileName());
                if(!StringUtil.isEmptyList(xmlList)){
                    for(XbrliDownLoadXml xml:xmlList){
                        List<InformationEntity> errorInfo = xml.getErrorInformationTuple();
                        List<InformationEntity> CorrectInfo = xml.getCorrectInformationTuple();
                        boolean matched = false;
                        if(!StringUtil.isEmptyList(errorInfo)){
                            matched = errorInfo.stream().anyMatch(a->repSeqNo.equals(a.getRegistrationSerialNumber().getValue()));
                        }
                        if(!StringUtil.isEmptyList(CorrectInfo)){
                            matched = matched||CorrectInfo.stream().anyMatch(a->repSeqNo.equals(a.getRegistrationSerialNumber().getValue()));
                        }
                        if(matched){
                            resultList.add(dto);
                            //跳出内循环，解析下一个文件
                            break;
                        }
                    }
                }
            }
        }
        Page<ChbFeedbackFileManagementDto> result = new Page<>();
        result.setRoot(resultList);
        result.setPage(pageNum);
        result.setRecords(resultList.size());
        return result;
    }

    /**
     * 查询反馈文件匹配信息
     * @param repSeqNo 流水号
     * @param filePath 文件路径
     * @param fileName 文件名
     * @param reportEtrType 报文指令类型
     * @return
     */
    public ChbFeedbackFileMatchInfoDto queryMatchInfo(String repSeqNo, String rtnRepMesNo, String filePath, String fileName, String reportEtrType){
        ChbFeedbackFileMatchInfoDto resDto = new ChbFeedbackFileMatchInfoDto();
        resDto.setRtnRepMesNo(rtnRepMesNo);
        Map<String, ChbFeedbackFileMatchDetailDto> resMap = new HashMap<>();
        List<String> sumitSeqList = new ArrayList<>();
        int regErrorCount = 0;          //登记表异常明细数
        int regSendingCount = 0;        //登记表发送中明细数
        int regOthersCount = 0;         //登记表其他状态明细数
        int regMatchNum = 0;            //登记表匹配明细数

        // 1.开始解析反馈文件
        List<XbrliDownLoadXml> xmlList = chbFeedbackFileAnalysisUtil.analysisXmlFileList(filePath, fileName);
        if(!StringUtil.isEmptyList(xmlList)){
            for(XbrliDownLoadXml xml:xmlList){
                //解析错误信息列表
                List<InformationEntity> errorInfo = xml.getErrorInformationTuple();
                if(!StringUtil.isEmptyList(errorInfo)){
                    for(InformationEntity errDto:errorInfo){
                        ChbFeedbackFileMatchDetailDto handleDto = new ChbFeedbackFileMatchDetailDto();
                        handleDto.setFileRegSeqno(errDto.getRegistrationSerialNumber().getValue());
                        handleDto.setFileRepType(reportEtrType);
                        handleDto.setFileFeedbackResult(DictCodeListExt2.报送反馈状态_中债退回.getValue());
                        handleDto.setFeedbackMsg(errDto.getErrorMessage().getValue());
                        resMap.put(handleDto.getFileRegSeqno(), handleDto);
                        sumitSeqList.add(handleDto.getFileRegSeqno());
                    }
                    //记录中债退回数
                    resDto.setChbErrorNum(resDto.getChbErrorNum() + errorInfo.size());
                }

                //解析正确信息列表
                List<InformationEntity> CorrectInfo = xml.getCorrectInformationTuple();
                if(!StringUtil.isEmptyList(CorrectInfo)) {
                    for (InformationEntity corDto : CorrectInfo) {
                        ChbFeedbackFileMatchDetailDto handleDto = new ChbFeedbackFileMatchDetailDto();
                        handleDto.setFileRegSeqno(corDto.getRegistrationSerialNumber().getValue());
                        handleDto.setFileRepType(reportEtrType);
                        handleDto.setFileFeedbackResult(DictCodeListExt2.报送反馈状态_中债通过.getValue());
                        resMap.put(handleDto.getFileRegSeqno(), handleDto);
                        sumitSeqList.add(handleDto.getFileRegSeqno());
                    }
                    //记录中债通过数
                    resDto.setChbCorrectNum(resDto.getChbCorrectNum() + CorrectInfo.size());
                }
            }
            //记录反馈文件明细数
            resDto.setFeedbackFileNum(resDto.getChbErrorNum() + resDto.getChbCorrectNum());
        }

        // 2.查询登记表进行匹配
        List<ChbFeedbackFileMatchDetailDto> chbSubStatusList = queryRegSubTable(reportEtrType, sumitSeqList);
        if(!StringUtil.isEmptyList(chbSubStatusList)){
            for(ChbFeedbackFileMatchDetailDto dto:chbSubStatusList){
                if(StringUtil.equals(dto.getRegFeedbackStatus(), DictCodeListExt2.报送反馈状态_发送异常.getValue())){
                    regErrorCount += 1;
                }else if(StringUtil.equals(dto.getRegFeedbackStatus(), DictCodeListExt2.报送反馈状态_发送中.getValue())){
                    regSendingCount += 1;
                }else{
                    regOthersCount += 1;
                }
                ChbFeedbackFileMatchDetailDto handleDto = resMap.get(dto.getRegSeqno());
                if(handleDto != null){
                    handleDto.setRegSeqno(dto.getRegSeqno());
                    handleDto.setKeyCode(dto.getKeyCode());
                    handleDto.setRegRepStatus(dto.getRegRepStatus());
                    handleDto.setRegFeedbackStatus(dto.getRegFeedbackStatus());
                    handleDto.setRegDataDate(dto.getRegDataDate());
                    handleDto.setRegDataStatus(dto.getRegDataStatus());
                    regMatchNum += 1;
                }
            }
        }

        // 3.设置匹配结果
        resDto.setRegErrorNum(regErrorCount);
        resDto.setRegSendingNum(regSendingCount);
        resDto.setRegOthersNum(regOthersCount);
        resDto.setRegMatchNum(regMatchNum);
        resDto.setMatchedFlag(DictCodeListExt3.中债_匹配结果_匹配失败.getValue());
        resDto.setMatchResultMsg("报送文件和登记信息最新情况存在冲突，请业务确认后，手动处理！");
        if(!StringUtil.isEmptyList(chbSubStatusList) && resMap.size() == chbSubStatusList.size()){
            if(resMap.size() == regErrorCount || resMap.size() == regSendingCount){
                //如果反馈文件明细和登记表匹配结果明细数相等，且和发送中或发送异常数相等则置为匹配成功
                resDto.setMatchedFlag(DictCodeListExt3.中债_匹配结果_匹配成功.getValue());
                resDto.setMatchResultMsg("文件匹配成功，可以进行文件匹配操作。");
            }
        }
        resDto.setDetailList(new ArrayList<>(resMap.values()));
        return resDto;
    }

    /**
     * 根据报送类型和流水号查询登记sub表，获取状态信息
     * @param repType 报文指令类型
     * @param registSeqs 流水号
     * @return
     */
    private List<ChbFeedbackFileMatchDetailDto> queryRegSubTable(String repType, List<String> registSeqs){
        if(StringUtil.isEmptyList(registSeqs)){
            return new ArrayList<>();
        }
        if (StrUtil.equals(DictCodeList.报表名称_资产估值信息登记.getValue(), repType)) {
            return handleByBatch(registSeqs, subList->chbFeedbackFileManagementMapper.queryChbAssetValSubStatus(subList));

        }else if(StrUtil.equals(DictCodeList.报表名称_产品申报登记.getValue(), repType)){
            return handleByBatch(registSeqs, subList->chbFeedbackFileManagementMapper.queryChbPrdDeclareSubStatus(subList));

        }else if(StrUtil.equals(DictCodeList.报表名称_产品发行登记.getValue(), repType)){
            return handleByBatch(registSeqs, subList->chbFeedbackFileManagementMapper.queryChbPrdIssueSubStatus(subList));

        }else if(StrUtil.equals(DictCodeList.报表名称_募集总量登记.getValue(), repType)){
            return handleByBatch(registSeqs, subList->chbFeedbackFileManagementMapper.queryChbPrdRaisetotSubStatus(subList));

        }else if(StrUtil.equals(DictCodeList.报表名称_产品存续期登记.getValue(), repType)){
            return handleByBatch(registSeqs, subList->chbFeedbackFileManagementMapper.queryChbPrdDealSubStatus(subList));

        }else if(StrUtil.equals(DictCodeList.报表名称_产品终止登记.getValue(), repType)){
            return handleByBatch(registSeqs, subList->chbFeedbackFileManagementMapper.queryChbPrdEndSubStatus(subList));

        }else if(StrUtil.equals(DictCodeList.报表名称_资管计划底层持仓信息登记.getValue(), repType)){
            return handleByBatch(registSeqs, subList->chbFeedbackFileManagementMapper.queryChbBottomAstPosSubStatus(subList));

        }else if(StrUtil.equals(DictCodeList.报表名称_资产交易信息登记.getValue(), repType)){
            return handleByBatch(registSeqs, subList->chbFeedbackFileManagementMapper.queryChbInvestTradeSubStatus(subList));

        }else if(StrUtil.equals(DictCodeList.报表名称_资产要素信息登记.getValue(), repType)){
            return handleByBatch(registSeqs, subList->chbFeedbackFileManagementMapper.queryChbAssetInfoSubStatus(subList));

        }else if(StrUtil.equals(DictCodeList.报表名称_从业人员登记.getValue(), repType)){
            return handleByBatch(registSeqs, subList->chbFeedbackFileManagementMapper.queryChbEmployeeInfoSubStatus(subList));
        }else{
            return new ArrayList<>();
        }
    }

    /**
     * 将大数据量分批操作，并将最终结果合并返回
     * @param paramList
     * @param func
     * @param <T>
     * @return
     */
    private <T> List<T> handleByBatch(List<String> paramList, Function<List<String>, List<T>> func){
        int batchSize = 900;        //批量大小
        List<T> result = new ArrayList<>();
        if(StringUtil.isEmptyList(paramList)){
            return result;
        }
        if(batchSize <= 0){
            return func.apply(paramList);
        }
        int groupNum = paramList.size()/batchSize;
        for(int i=0; i<groupNum; i++){
            result.addAll(func.apply(paramList.subList(i*batchSize, (i+1)*batchSize)));
        }
        if(paramList.size() > groupNum*batchSize){
            result.addAll(func.apply(paramList.subList(groupNum*batchSize, paramList.size())));
        }
        return result;
    }

    /**
     * 文件迁移
     * @param dto
     * @return
     */
    public String backupFile(ChbFeedbackFileManagementDto dto){
        ChbFeedbackFile backfile = new ChbFeedbackFile();
        String filePathBak = getDownloadFilePathMovePath();
        
        backfile.setFilePath(dto.getFilePath());
        backfile.setFileName(dto.getFileName());
        backfile.setSendRepMesNo(dto.getSendRepMesNo());
        backfile.setRtnRepMesNo(dto.getRtnRepMesNo());
        backfile.setReportEtrType(dto.getReportEtrType());
        backfile.setHandleStatus(DictCodeList.中债一二三期文件处理状态_已处理.getValue());
        backfile.setUpdateTime(DateUtil.now());
        backfile.setMoveFlag(DictCodeList.是否_中债_是.getValue());
        backfile.setMovePath(filePathBak);// 迁移后的放置目录
        
        chbFeedbackMsgService.updateChbFeedbackFileByNo(backfile);
        
        try {
            FileUtils.moveFileToDirectory(new File(dto.getFilePath() + dto.getFileName()), new File(filePathBak), true);
            FileUtils.moveFileToDirectory(new File(dto.getFilePath() + dto.getFileName().replace(".zip",".xml")), new File(filePathBak), true);
        }catch (Exception e){
            logger.error("反馈文件处理结束文件迁移异常，异常内容：" + e.getMessage());
            return "反馈文件处理结束文件迁移异常！";
        }

        return "文件迁移成功！";
    }

    /**
     * 获取下载文件解释后的移植路径
     * @return 下载文件解释后的移植路径
     */
    public String getDownloadFilePathMovePath() {
        if (StrUtil.isEmpty(downloadFilePathMovePath)) {
            return  System.getProperty("user.home") + File.separator + "chb" +  File.separator + "download" + File.separator + "bak" + File.separator;
        }
        return downloadFilePathMovePath;
    }

    /**
     * 文件处理
     * @param dto
     */
    public String fileHandle(ChbFeedbackFileMatchInfoDto dto) {
        String resMsg = "处理异常，请重试！";
        if (!StringUtil.isNotEmpty(dto) || !StringUtil.isNotEmpty(dto.getRtnRepMesNo())){
            logger.error("ChbFeedbackFileManagementService.fileHandle传入参数有误！");
            return resMsg;
        }
        List<ChbFeedbackFile> chbFeedbackFileList = chbFeedbackMsgService.queryChbFeedbackFileList(dto.getRtnRepMesNo());
        if(StringUtil.isEmptyList(chbFeedbackFileList)){
            logger.error("ChbFeedbackFileManagementService.fileHandle反馈报文编码" + dto.getRtnRepMesNo() + "不存在！");
            return resMsg;
        }

        ChbFeedbackFile chbFeedbackFile =chbFeedbackFileList.get(0);

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
                return "文件正在被处理，请重新查询！";
            }

            logger.info("当前中债反馈文件" + chbFeedbackFile.getFileName() + "处理开始");
            // 获取报文指令类型
            String submitType = chbFeedbackFile.getReportEtrType();
            // 获取发送-报文编码
            String sendRepMesNo = chbFeedbackFile.getSendRepMesNo();
            // 异步文件返回报文Entity
            AsynReportEntity asynReportEntity = JsonUtils.json2Object(chbFeedbackFile.getFeedbackMsg(), AsynReportEntity.class);
            // 获取文件名称
            String fileName = asynReportEntity.getDocument().getFileName();
            // 获取文件路径
            String filePath = chbFeedbackMsgService.getDownloadFilePath();

            // 1、插入CHB_INFO_REPORT表
            ChbInfoReportDto chbInfoReportDto = new ChbInfoReportDto();
            chbInfoReportDto.setReportNo(Sid.nextShort(20));
            chbInfoReportDto.setRepMesNo(getVaule(asynReportEntity.getMsgHeader().getBizInfo().getOrigMsgId()));
            chbInfoReportDto.setUploadResult(asynReportEntity.getDocument().getErrorCode());
            //chbInfoReportDto.setUploadResultMsg();
            chbInfoReportDto.setUploadFileId(asynReportEntity.getDocument().getFileId());
            //chbInfoReportDto.setCheckFileId();
            chbInfoReportDto.setCheckResult(asynReportEntity.getDocument().getErrorCode());
            chbInfoReportDto.setCheckResultMsg(asynReportEntity.getDocument().getErrorText());
            chbInfoReportDto.setCheckFileName(asynReportEntity.getDocument().getFileName());
            chbInfoReportDto.setCheckTotalCount(Integer.valueOf(asynReportEntity.getDocument().getTotalCount()));
            chbInfoReportDto.setCheckSuccessCount(Integer.valueOf(asynReportEntity.getDocument().getSuccessCount()));
            chbInfoReportDto.setCheckFailedCount(Integer.valueOf(asynReportEntity.getDocument().getFailedCount()));
            chbInfoReportDto.setSendTime(DateUtil.now());
            chbInfoReportDto.setReplyTime(DateUtil.now());
            chbInfoReportDto.setSubmitType(submitType);

            // 先删再插，防止重复
            if (StringUtil.isNotEmpty(chbInfoReportDto.getRepMesNo())){
                chbFeedbackFileManagementMapper.delChbInfoReportByMesNo(chbInfoReportDto.getRepMesNo());
            }
            chbFeedbackFileManagementMapper.mergeInfoChbInfoReport(Arrays.asList(chbInfoReportDto));
            // 2、插入CHB_SUBMIT_STATUS_TWO表
            List<ChbSubmitStatusDto> chbSubmitStatusDtos = new ArrayList<>();

            if (StringUtil.isNotEmpty(dto) && !StringUtil.isEmptyList(dto.getDetailList())){
                for (ChbFeedbackFileMatchDetailDto chbFeedbackFileMatchDetailDto : dto.getDetailList()){
                    // 中债报送状态
                    ChbSubmitStatusDto submitStatus = new ChbSubmitStatusDto();
                    // 报送序列号
                    submitStatus.setSeqno(chbFeedbackFileMatchDetailDto.getFileRegSeqno());
                    // 报送流水号
                    submitStatus.setSubmitNum(chbFeedbackFileMatchDetailDto.getFileRegSeqno());
                    // 业务类型
                    submitStatus.setSubmitType(submitType);
                    // 报送反馈状态
                    submitStatus.setSubmitFeedbackStatus(chbFeedbackFileMatchDetailDto.getFileFeedbackResult());
                    // 报送编码
                    submitStatus.setReportNo(chbInfoReportDto.getReportNo());
                    // 报送反馈消息
                    submitStatus.setSubmitFeedbackMsg(chbFeedbackFileMatchDetailDto.getFeedbackMsg());
                    // 识别标识  通过流水号获取
                    submitStatus.setUniqueIdenti(chbFeedbackFileMatchDetailDto.getKeyCode());

                    chbSubmitStatusDtos.add(submitStatus);
                }
            }
            chbFeedbackFileManagementMapper.mergeInfoChbSubmitStatusTwo(chbSubmitStatusDtos);
            // 3、更新报送登记信息表
            chbFeedbackMsgService.updateSubStatus(filePath, fileName, sendRepMesNo, submitType, false);
            // 4、移动文件、并更新 反馈文件管理表
            ChbFeedbackFileManagementDto chbFeedbackFileManagementDto = new ChbFeedbackFileManagementDto();
            chbFeedbackFileManagementDto.setFilePath(filePath);
            chbFeedbackFileManagementDto.setFileName(fileName);
            chbFeedbackFileManagementDto.setRtnRepMesNo(chbFeedbackFile.getRtnRepMesNo());
            this.backupFile(chbFeedbackFileManagementDto);
            logger.info("当前中债反馈文件" + chbFeedbackFile.getFileName() + "处理结束");
            return null;
        }catch (FileNotFoundException e) {
            logger.error("当前中债反馈文件" + chbFeedbackFile.getFileName() + e.getMessage(), e);
            throw new BusinessException("MSG:文件处理异常，请稍后重试！");
        }
        catch (IOException e) {
            logger.error("当前中债反馈文件" + chbFeedbackFile.getFileName() + e.getMessage(), e);
            throw new BusinessException("MSG:文件处理异常，请稍后重试！");
        }finally {
            if (lock != null) {
                try {
                    lock.release();
                }
                catch (IOException e) {
                    logger.error("当前中债反馈文件" + chbFeedbackFile.getFileName() + e.getMessage(),e);
                    return resMsg;
                }
            }
            if (channel != null) {
                try {
                    channel.close();
                }
                catch (IOException e) {
                    logger.error("当前中债反馈文件" + chbFeedbackFile.getFileName() + e.getMessage(),e);
                    return resMsg;
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                }
                catch (IOException e) {
                    logger.error("当前中债反馈文件" + chbFeedbackFile.getFileName() + e.getMessage(),e);
                    return resMsg;
                }
            }
            if(lockFile != null){
                lockFile.delete();
            }
        }
    }

    /**
     * 更新异地反馈文件管理列表
     * @return
     */
    @DataSource("chbSendDataSource")
    private List<ChbFeedbackFileManagementDto> queryFeedbackListOthSrevice(Map<String, Object> param) {
        DynamicDataSourceHolder.putDataSource("chbSendDataSource");
        List<ChbFeedbackFileManagementDto> list = chbFeedbackFileManagementMapper.queryChbFeedbackFileList(param);
        DynamicDataSourceHolder.clearDataSource();
        return list;
    }

    /**
     * 更新异地反馈文件管理列表
     * @return
     */
    @DataSource("chbSendDataSource")
    private Page<ChbFeedbackFileManagementDto> queryFeedbackPageOthSrevice(int pagenum, int length, Map<String, Object> param) {
        DynamicDataSourceHolder.putDataSource("chbSendDataSource");
        Page<ChbFeedbackFileManagementDto> page = queryForPage(pagenum, length,
                ()->chbFeedbackFileManagementMapper.queryChbFeedbackFileList(param));
        DynamicDataSourceHolder.clearDataSource();
        return page;
    }

    /**
     * @param entity entity
     * @return 值
     */
    private String getVaule(CommonNodeEntity entity) {
        if (entity == null) {
            return null;
        }
        return entity.getValue();
    }

}
