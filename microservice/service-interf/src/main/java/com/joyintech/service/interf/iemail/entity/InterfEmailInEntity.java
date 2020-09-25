/*
 * 文件名：InterfEmailInEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨静敏
 * 修改时间：2018年12月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.iemail.entity;

import com.github.pagehelper.util.StringUtil;
import com.joyintech.tams.common.base.entity.InterfEmailmessage;
import com.joyintech.service.interf.cache.InterfCacheUtil;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;
import com.joyintech.service.interf.core.constant.InterfConstant;

/**
 * 邮件接口入参
 * 
 * @author 杨静敏
 * @version 1.0 2018年12月5日
 * @see InterfEmailInEntity
 * @since 1.0
 */
public class InterfEmailInEntity extends InterfEmailmessage {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 1323547527257036829L;

    /**
     * 特殊目的载体
     */
    private static final String SPECIALLEND = "specialLend";

    /**
     * 系统ID
     */
    private String senderid;

    /**
     * 消息流水号
     */
    private String busiseqno;

    /**
     * 机构号
     */
    private String senddeptid;

    /**
     * 开始日期
     */
    private String startdate;

    /**
     * 终止日期
     */
    private String enddate;

    /**
     * 获取 消息流水号
     * 
     * @return 消息流水号
     */
    @EsbName(value = "BUSISEQNO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 100)
    public String getBusiseqno() {
        return busiseqno;
    }

    /**
     * 设置 消息流水号
     * 
     * @param busiseqno 消息流水号
     */
    public void setBusiseqno(String busiseqno) {
        this.busiseqno = busiseqno;
    }

    /**
     * 获取 系统ID
     * 
     * @return 系统ID
     */
    @EsbName(value = "SENDERID")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 42)
    public String getSenderid() {
        if (StringUtil.isEmpty(senderid)) {
            senderid = InterfCacheUtil.getSysParam(InterfConstant.INF_CONSUMER_ID);
        }
        return senderid;
    }

    /**
     * 设置 系统ID
     * 
     * @param senderid 系统ID
     */
    public void setSenderid(String senderid) {
        this.senderid = senderid;
    }

    /**
     * 获取 机构号
     * 
     * @return 机构号
     */
    @EsbName(value = "SENDDEPTID")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 100)
    public String getSenddeptid() {
        if (StringUtil.isEmpty(senddeptid)) {
            senddeptid = InterfCacheUtil.getSysParam(InterfConstant.INF_BRANCH_ID);
        }
        return senddeptid;
    }

    /**
     * 设置 机构号
     * 
     * @param senddeptid 机构号
     */
    public void setSenddeptid(String senddeptid) {
        this.senddeptid = senddeptid;
    }

    @EsbName(value = "BUSICODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 100)
    @Override
    public String getBusicode() {
        return super.getBusicode();
    }

    @EsbName(value = "TITLE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 100)
    @Override
    public String getTitle() {
        return super.getTitle();
    }

    @EsbName(value = "EMAIL")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 2000)
    @Override
    public String getRevemail() {
        return super.getRevemail();
    }

    @EsbName(value = "EMAIL2")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 2000)
    @Override
    public String getRevemail2() {
        return super.getRevemail2();
    }

    @EsbName(value = "EMAIL3")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 2000)
    @Override
    public String getRevemail3() {
        return super.getRevemail3();
    }

    /**
     * 获取 邮件发送方式
     * 
     * @return 邮件发送方式
     */
    @EsbName(value = "CONTENTTYPE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 2)
    public String getContenttype() {
        if (StringUtil.isNotEmpty(super.getBusicode())) {
            // 模板发送
            return "01";
        }
        else {
            // 无格式文本发送
            return "02";
        }
    }

    /**
     * 取得发送邮件的消息内容
     * 
     * @return 发送邮件的消息内容
     */
    @EsbName(value = "CONTENT")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 5000)
    public String getEmailContent() {
        if (SPECIALLEND.equals(super.getBusitype())) {
            return super.getSubcontent();
        }
        return super.getContent();
    }

    @EsbName(value = "FROMEMAIL")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 48)
    @Override
    public String getSendemail() {
        return super.getSendemail();
    }

    @EsbName(value = "FROMNAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 32)
    @Override
    public String getSendname() {
        return super.getSendname();
    }

    /**
     * 获取 开始日期
     * 
     * @return 开始日期
     */
    @EsbName(value = "STARTDATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 14)
    public String getStartdate() {
        return startdate;
    }

    /**
     * 设置 开始日期
     * 
     * @param startdate 开始日期
     */
    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    /**
     * 获取 终止日期
     * 
     * @return 终止日期
     */
    @EsbName(value = "ENDDATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 14)
    public String getEnddate() {
        return enddate;
    }

    /**
     * 设置 终止日期
     * 
     * @param enddate 终止日期
     */
    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }
}
