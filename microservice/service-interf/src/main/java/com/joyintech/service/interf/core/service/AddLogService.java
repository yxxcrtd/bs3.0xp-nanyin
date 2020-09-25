/*
 * 文件名：AddLogService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年10月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc.eai.data.CompositeData;
import com.dcfs.esb.client.config.Config;
import com.dcfs.esb.client.converter.PackUtil;
import com.github.pagehelper.util.StringUtil;
import com.joyintech.tams.common.base.dao.InterfEsbLogMapper;
import com.joyintech.tams.common.base.dao.InterfFileLogMapper;
import com.joyintech.tams.common.base.dao.InterfHttpLogMapper;
import com.joyintech.tams.common.base.dao.InterfLogMapper;
import com.joyintech.tams.common.base.entity.InterfEsbLogWithBLOBs;
import com.joyintech.tams.common.base.entity.InterfFileLog;
import com.joyintech.tams.common.base.entity.InterfHttpLogWithBLOBs;
import com.joyintech.tams.common.base.entity.InterfLog;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.interf.core.constant.InterfConstant;
import com.joyintech.service.interf.core.constant.InterfDictCodeList;
import com.joyintech.tams.invest.common.component.base.core.impl.dao.BaseGetBussinessIdMapper;

/**
 * 日志登录
 * 
 * @author zhangrui1
 * @version 1.0 2018年10月25日
 * @see AddLogService
 * @since 1.0
 */
@Service
public class AddLogService {

    /**
     * ESB日志Mapper
     */
    @Autowired
    private InterfEsbLogMapper esbLogMapper;

    /**
     * 文件日志Mapper
     */
    @Autowired
    private InterfFileLogMapper fileLogMapper;

    /**
     * http日志Mapper
     */
    @Autowired
    private InterfHttpLogMapper httpLogMapper;

    /**
     * 接口日志Mapper
     */
    @Autowired
    private InterfLogMapper interfLogMapper;

    /**
     * 业务编号生成规则mapper
     */
    @Autowired
    private BaseGetBussinessIdMapper bussinessCodeMapper;

    /**
     * Esb通信日志登录
     * 
     * @param interfId 接口ID
     * @param reqData 请求数据
     * @param resData 应答数据
     * @param sendStatus 发送状态
     * @param msg 错误信息
     * @param uuid 处理识别号
     * @param sendReceive 出入方向
     * @return 登录行数
     * @see
     * @since 1.0
     */
    public int addEsbLog(String interfId, String reqData, CompositeData resData, String sendStatus,
                         String msg, String uuid, String sendReceive) {
        InterfEsbLogWithBLOBs record = new InterfEsbLogWithBLOBs();

        String resXml = null;
        if (resData != null) {
            CompositeData sysHead = resData.getStruct(InterfConstant.SYS_HEAD);
            String bizseqno = sysHead.getField(InterfConstant.CONSUMER_SEQ_NO).strValue();
            if (StringUtil.isEmpty(bizseqno)) {
                bizseqno = sysHead.getField(InterfConstant.ESB_SEQ_NO).strValue();
            }
            String serviceCode = sysHead.getField(InterfConstant.SERVICE_CODE).strValue();
            String serviceScene = sysHead.getField(InterfConstant.SERVICE_SCENE).strValue();
            String retCode = sysHead.getArray(InterfConstant.RET).getStruct(0).getField(
                InterfConstant.RET_CODE).strValue();
            String retMsg = sysHead.getArray(InterfConstant.RET).getStruct(0).getField(
                InterfConstant.RET_MSG).strValue();
            String retStatus = sysHead.getField(InterfConstant.RET_STATUS).strValue();
            record.setBizSeqNo(bizseqno);
            record.setServiceCode(serviceCode);
            record.setServiceScene(serviceScene);
            record.setSendStatus(retStatus);
            record.setRetCode(retCode);
            record.setRetMsg(retMsg);
            resXml = PackUtil.packXmlStr(resData);
        }
        else {
            record.setSendStatus(sendStatus);
            record.setRetMsg(msg);
        }
        if (InterfDictCodeList.出入方向_发送ESB.getValue().equals(sendReceive)) {
            record.setSendData(reqData);
            record.setReceiveData(resXml);
        }
        else {
            record.setSendData(resXml);
            record.setReceiveData(reqData);
        }
        record.setInterfId(interfId);
        record.setUuid(uuid);
        record.setSendReceive(sendReceive);

        return addEsbLog(record);
    }

    /**
     * Esb通信日志登录
     * 
     * @param esbLogInfo 登录日志信息
     * @return 登录行数
     * @see
     * @since 1.0
     */
    public int addEsbLog(InterfEsbLogWithBLOBs esbLogInfo) {
        if (esbLogInfo == null) {
            return 0;
        }
        String uid = Config.getLogProperty("uid");
        String password = Config.getLogProperty("password");
        String masterkey = Config.getLogProperty("masterKey");
        String url = Config.getLogProperty("url");
        esbLogInfo.setUrl(url);
        esbLogInfo.setMasterKey(StringUtil.isEmpty(masterkey) ? password : masterkey);
        esbLogInfo.setUsid(uid);
        if (StringUtil.isEmpty(esbLogInfo.getSeqNo())) {
            String seqNo = bussinessCodeMapper.getNextValBySequenceName(
                InterfConstant.INTERF_LOG_SEQNO);
            esbLogInfo.setSeqNo(seqNo);
        }
        esbLogInfo.setCreateTime(DateUtil.parse(DateUtil.getCurrentDate()));
        return esbLogMapper.insertSelective(esbLogInfo);
    }

    /**
     * 文件日志登录
     * 
     * @param fileLogInfo 文件日志信息
     * @return 登录行数
     * @see
     * @since 1.0
     */
    public int addFileLog(InterfFileLog fileLogInfo) {
        if (fileLogInfo == null) {
            return 0;
        }
        if (StringUtil.isEmpty(fileLogInfo.getSeqNo())) {
            String seqNo = bussinessCodeMapper.getNextValBySequenceName(
                InterfConstant.INTERF_LOG_SEQNO);
            fileLogInfo.setSeqNo(seqNo);
        }
        fileLogInfo.setCreateTime(DateUtil.parse(DateUtil.getCurrentDate()));
        return fileLogMapper.insertSelective(fileLogInfo);
    }

    /**
     * HTTP日志登录
     * 
     * @param httpLogInfo HTTp日志信息
     * @return 登录行数
     * @see
     * @since 1.0
     */
    public int addHttpLog(InterfHttpLogWithBLOBs httpLogInfo) {
        if (httpLogInfo == null) {
            return 0;
        }
        if (StringUtil.isEmpty(httpLogInfo.getSeqNo())) {
            String seqNo = bussinessCodeMapper.getNextValBySequenceName(
                InterfConstant.INTERF_LOG_SEQNO);
            httpLogInfo.setSeqNo(seqNo);
        }
        httpLogInfo.setCreateTime(DateUtil.parse(DateUtil.getCurrentDate()));
        return httpLogMapper.insertSelective(httpLogInfo);
    }

    /**
     * 接口日志登录
     * 
     * @param interfLogInfo 接口日志信息
     * @return 登录行数
     * @see
     * @since 1.0
     */
    public int addInterfLog(InterfLog interfLogInfo) {
        if (interfLogInfo == null) {
            return 0;
        }
        if (StringUtil.isEmpty(interfLogInfo.getSeqNo())) {
            String seqNo = bussinessCodeMapper.getNextValBySequenceName(
                InterfConstant.INTERF_LOG_SEQNO);
            interfLogInfo.setSeqNo(seqNo);
        }
        interfLogInfo.setCreateTime(DateUtil.parse(DateUtil.getCurrentDate()));
        return interfLogMapper.insertSelective(interfLogInfo);
    }

    /**
     * 接口日志登录
     * 
     * @param interfId 接口ID
     * @param taskstatus 执行状态
     * @param errMsg 异常信息
     * @param flg 警告标示：是否
     * @param remark1 备用字段1
     * @param remark2 备用字段2
     * @param uuid 处理识别号
     * @param bizSeqNo 报文流水号
     * @param bizDate 业务日期
     * @return 登录行数
     * @see
     * @since 1.0
     */
    public int addInterfLog(String interfId, String taskstatus, String errMsg, String flg,
                            String remark1, String remark2, String uuid, String bizSeqNo,
                            String bizDate) {
        InterfLog interfLogInfo = new InterfLog();
        interfLogInfo.setInterfId(interfId);
        interfLogInfo.setErrMessage(errMsg);
        interfLogInfo.setTaskStatus(taskstatus);
        interfLogInfo.setIsneedwarning(flg);
        interfLogInfo.setRemark1(remark1);
        interfLogInfo.setRemark1(remark2);
        interfLogInfo.setUuid(uuid);
        interfLogInfo.setBizSeqNo(bizSeqNo);
        interfLogInfo.setBizDate(bizDate);
        return addInterfLog(interfLogInfo);
    }
}
