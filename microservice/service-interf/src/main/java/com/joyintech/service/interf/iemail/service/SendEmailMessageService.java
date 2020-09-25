/*
 * 文件名：SendEmailMessageService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨静敏
 * 修改时间：2018年12月4日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.iemail.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;

import com.dc.eai.data.CompositeData;
import com.dc.eai.data.Field;
import com.dc.eai.data.FieldAttr;
import com.dc.eai.data.FieldType;
import com.joyintech.tams.common.base.dao.InterfEmailmessageMapper;
import com.joyintech.tams.common.base.entity.InterfEmailconfig;
import com.joyintech.tams.common.base.entity.InterfEmailmessage;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.cache.InterfCacheUtil;
import com.joyintech.service.interf.core.constant.InterfConstant;
import com.joyintech.service.interf.core.constant.InterfDictCodeList;
import com.joyintech.service.interf.core.service.InterfBaseService;
import com.joyintech.service.interf.core.util.InterfUtil;
import com.joyintech.service.interf.iemail.dao.SendEmailMessageMapper;
import com.joyintech.service.interf.iemail.dto.SendEmailDto;
import com.joyintech.service.interf.iemail.entity.InterfEmailInEntity;
import com.joyintech.tams.invest.common.component.base.core.impl.dao.BaseGetBussinessIdMapper;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.pingan.isc.ISCMessageBroker;

/**
 * 发送邮件信息 处理详细
 * 
 * @author 杨静敏
 * @version 1.0 2018年12月4日
 * @see SendEmailMessageService
 * @since 1.0
 */
@Service
public class SendEmailMessageService extends InterfBaseService {

    /**
     * 业务编号生成规则mapper
     */
    @Autowired
    private BaseGetBussinessIdMapper bussinessCodeMapper;

    /**
     * 发送邮件查询mapper
     */
    @Autowired
    private SendEmailMessageMapper mapper;

    /**
     * 发送邮件查询mapper
     */
    @Autowired
    private InterfEmailmessageMapper interfmapper;

    /**
     * 系统报文头
     */
    private static final String ISC_SYS_HEAD = "ISC_SYS_HEAD";

    /**
     * 公共报文头
     */
    private static final String ISC_PUB_HEAD = "ISC_PUB_HEAD";

    /**
     * 服务数据返回体
     */
    private static final String ISC_REP_BODY = "ISC_REP_BODY";

    /**
     * 主题
     */
    private static final String MAIN_TOPIC = "MAIN_TOPIC";

    /**
     * 子主题
     */
    private static final String SUB_TOPIC = "SUB_TOPIC";

    /**
     * 处理流水号
     */
    private static final String APP_MSG_SEQ_NO = "APP_MSG_SEQ_NO";

    /**
     * 业务系统ID
     */
    private static final String MSG_PRODUCER_ID = "MSG_PRODUCER_ID";

    /**
     * 业务场景
     */
    private static final String SERVICE_CODE = "SERVICE_CODE";

    /**
     * 时间格式：yyyyMMddhhmmss
     */
    private static final String FORMAT_YMDHMS = "yyyyMMddhhmmss";

    /**
     * 处理流水号
     */
    private String subTopicNo;

    /**
     * 初始化变量
     * 
     * @param uuid 处理识别号
     * @see
     * @since 1.0
     */
    public void init(String uuid) {
        setUuid(uuid);
        setHandleOK(true);
        setErrMsg("");
    }

    /**
     * 发送邮件
     * 
     * @param serialNo 邮件序号
     * @param user 更新人
     * @return 发送结果
     * @see
     * @since 1.0
     */
    public SendEmailDto sendEmail(String serialNo, String user) {
        InterfEmailInEntity email = selectEmail(serialNo);
        if (email == null) {
            doException(null, "邮件序号对应的内容不存在");
        }
        else {
            email.setLstmntuser(user);
            sendEmail(email);
        }
        // 日志记录
        addInterfLog("email", getSubTopicNo(), DictCodeList.是否_否.getValue(),
            StringUtil.EMPTY, StringUtil.EMPTY);
        return getReturn(serialNo);
    }

    /**
     * 构建返回结果
     * 
     * @param serialNo 邮件序号
     * @return 发送结果
     * @see
     * @since 1.0
     */
    public SendEmailDto getReturn(String serialNo) {
        SendEmailDto dto = new SendEmailDto();
        dto.setSerialNo(serialNo);
        dto.setHandleOK(isHandleOK());
        dto.setErrMsg(getErrMsg());
        return dto;
    }

    /**
     * 取得要发送的邮件内容
     * 
     * @param serialNo 邮件序号
     * @return 邮件内容
     * @see
     * @since 1.0
     */
    public InterfEmailInEntity selectEmail(String serialNo) {
        return mapper.selectEmailMessage(serialNo);
    }

    /**
     * 邮件保存
     * 
     * @param email 邮件内容
     * @return 邮件序号
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public String insertEmail(InterfEmailmessage email) {
        // 获取事务控制管理器
        DataSourceTransactionManager transactionManager = getTransactionManager();
        // 获取事务控制管理器
        TransactionStatus status = getTransactionStatus();
        // 生成邮件序号
        String serialno = getSysDate() + bussinessCodeMapper.getNextValBySequenceName(
            InterfConstant.INTERF_EMAIL_SEQNO);
        // 设置邮件序号
        email.setSerialno(serialno);
        // 设置创建人
        email.setCreator(email.getCreateUser());
        // 设置更新人
        email.setLstmntuser(email.getUpdateUser());
        // 设置发送时间 平台日期
        email.setSendtime(DateUtil.parse(getSysDate()));
        // 获取邮件接收人和发送人信息
        InterfEmailconfig emailconfig = mapper.selectEmailConfig(email.getBusitype());
        // 设置发件人地址
        email.setSendemail(emailconfig.getSendemail());
        // 设置发件人名称
        email.setSendname(emailconfig.getSendname());
        // 设置收件人地址
        email.setRevemail(emailconfig.getRevemail());
        // 设置抄送人地址
        email.setRevemail2(emailconfig.getRevemail2());
        // 设置密送人地址
        email.setRevemail3(emailconfig.getRevemail3());
        // 设置发送次数0
        email.setSendnum(0);
        // 登录
        int ret = interfmapper.insert(email);
        if (ret == 0) {
            doException(null, "邮件保存失败！");
            transactionManager.rollback(status);
        }
        else {
            transactionManager.commit(status);
        }
        return serialno;
    }

    /**
     * 更新邮件发送状态
     * 
     * @param email 邮件内容
     * @see
     * @since 1.0
     */
    private void updateEmailStatus(InterfEmailInEntity email) {
        // 发送次数 + 1
        int num = email.getSendnum() + 1;
        email.setSendnum(num);
        email.setSendstate(
            isHandleOK() ? InterfDictCodeList.邮件发送状态_成功.getValue() : InterfDictCodeList.邮件发送状态_失败.getValue());
        // 更新邮件状态
        mapper.updateMsgStatus(email);
    }

    /**
     * 发送EMAIl
     * 
     * @param email 邮件内容
     * @see
     * @since 1.0
     */
    private void sendEmail(InterfEmailInEntity email) {
        // 构建邮件报文
        CompositeData body = createEmailBody(email);
        CompositeData data = createEmail(body);
        if (data != null) {
            // 发送报文
            send(data);
        }
        // 更新发送状态
        updateEmailStatus(email);
    }

    /**
     * 调用邮件发送接口发送邮件
     * 
     * @param data 邮件报文
     * @see
     * @since 1.0
     */
    private void send(CompositeData data) {
        try {
            // 发送信息
            ISCMessageBroker.send(data);
            ISCMessageBroker.destroyed();
        }
        catch (Exception e) {
            doException(e, "发送邮件异常");
        }
    }

    /**
     * 构建邮件报文体
     * 
     * @param msgEntity 邮件内容
     * @return 邮件报文体
     * @see
     * @since 1.0
     */
    private CompositeData createEmailBody(InterfEmailInEntity msgEntity) {
        CompositeData bodyData = null;
        try {
            msgEntity.setBusiseqno(getSubTopicNo());
            bodyData = InterfUtil.changeEntityToESBData(msgEntity);
        }
        catch (Exception e2) {
            bodyData = null;
            doException(e2, "构建邮件报文失败");
        }
        return bodyData;
    }

    /**
     * 构建邮件报文
     * 
     * @param body 邮件报文体
     * @return 邮件报文
     * @see
     * @since 1.0
     */
    private CompositeData createEmail(CompositeData body) {
        if (body == null) {
            return null;
        }
        CompositeData msgData = new CompositeData();
        CompositeData iscSysData = new CompositeData();
        CompositeData iscPubData = new CompositeData();
        CompositeData iscRepBody = new CompositeData();
        // 系统报文处理
        msgData.addStruct(ISC_SYS_HEAD, iscSysData);
        // 公共报文处理
        msgData.addStruct(ISC_PUB_HEAD, iscPubData);
        // 服务数据返回体
        msgData.addStruct(ISC_REP_BODY, iscRepBody);
        // 主题
        addField(iscSysData, MAIN_TOPIC, "80", 10);
        // 子主题
        addField(iscSysData, SUB_TOPIC, "80012", 48);
        // 处理流水号
        addField(iscSysData, APP_MSG_SEQ_NO, getSubTopicNo(), 42);

        // 系统编号
        addField(iscPubData, MSG_PRODUCER_ID,
            InterfCacheUtil.getSysParam(InterfConstant.INF_CONSUMER_ID), 42);
        // 业务场景
        addField(iscPubData, SERVICE_CODE, "000000", 42);

        msgData.addStruct(InterfConstant.BODY, body);
        getLogger().info("邮件报文：{}", msgData);
        return msgData;
    }

    /**
     * 取得处理流水号
     * 
     * @return 处理流水号
     * @see
     * @since 1.0
     */
    private String getSubTopicNo() {
        if (StringUtil.isEmpty(subTopicNo)) {
            subTopicNo = "TCMS_EMAIL_MSG_NO_" + DateUtil.format(new Date(), FORMAT_YMDHMS);
        }
        return subTopicNo;
    }

    /**
     * 设置Filed
     * 
     * @param cData 数据
     * @param fieldStr 字段定义
     * @param value 值
     * @param len 长度
     * @see
     * @since 1.0
     */
    private void addField(CompositeData cData, String fieldStr, String value, int len) {
        Field field = new Field(new FieldAttr(FieldType.FIELD_STRING, len));
        field.setValue(value);
        cData.addField(fieldStr, field);
    }
}
