package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class InterfEmailmessage extends BaseEntity {
    /**
     * 序号
     */
    private String serialno;

    /**
     * 发件人地址
     */
    private String sendemail;

    /**
     * 发件人名称
     */
    private String sendname;

    /**
     * 收件人地址
     */
    private String revemail;

    /**
     * 抄送对象
     */
    private String revemail2;

    /**
     * 密送对象
     */
    private String revemail3;

    /**
     * 标题
     */
    private String title;

    /**
     * 业务类型
     */
    private String busitype;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 发送时间
     */
    private Date sendtime;

    /**
     * 发送状态：0成功，1失败
     */
    private String sendstate;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 录入日期
     */
    private Date createdate;

    /**
     * 更新人员
     */
    private String lstmntuser;

    /**
     * 更新时间
     */
    private Date lstmntdate;

    /**
     * 发送次数
     */
    private Integer sendnum;

    /**
     * 模板号
     */
    private String busicode;

    /**
     * 邮件发送内容
     */
    private String subcontent;

    /**
     * 取得 序号
     * @return 序号
     */
    public String getSerialno() {
        return serialno;
    }

    /**
     * 设置 序号
     * @param serialno 序号
     */
    public void setSerialno(String serialno) {
        this.serialno = serialno == null ? null : serialno.trim();
    }

    /**
     * 取得 发件人地址
     * @return 发件人地址
     */
    public String getSendemail() {
        return sendemail;
    }

    /**
     * 设置 发件人地址
     * @param sendemail 发件人地址
     */
    public void setSendemail(String sendemail) {
        this.sendemail = sendemail == null ? null : sendemail.trim();
    }

    /**
     * 取得 发件人名称
     * @return 发件人名称
     */
    public String getSendname() {
        return sendname;
    }

    /**
     * 设置 发件人名称
     * @param sendname 发件人名称
     */
    public void setSendname(String sendname) {
        this.sendname = sendname == null ? null : sendname.trim();
    }

    /**
     * 取得 收件人地址
     * @return 收件人地址
     */
    public String getRevemail() {
        return revemail;
    }

    /**
     * 设置 收件人地址
     * @param revemail 收件人地址
     */
    public void setRevemail(String revemail) {
        this.revemail = revemail == null ? null : revemail.trim();
    }

    /**
     * 取得 抄送对象
     * @return 抄送对象
     */
    public String getRevemail2() {
        return revemail2;
    }

    /**
     * 设置 抄送对象
     * @param revemail2 抄送对象
     */
    public void setRevemail2(String revemail2) {
        this.revemail2 = revemail2 == null ? null : revemail2.trim();
    }

    /**
     * 取得 密送对象
     * @return 密送对象
     */
    public String getRevemail3() {
        return revemail3;
    }

    /**
     * 设置 密送对象
     * @param revemail3 密送对象
     */
    public void setRevemail3(String revemail3) {
        this.revemail3 = revemail3 == null ? null : revemail3.trim();
    }

    /**
     * 取得 标题
     * @return 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置 标题
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 取得 业务类型
     * @return 业务类型
     */
    public String getBusitype() {
        return busitype;
    }

    /**
     * 设置 业务类型
     * @param busitype 业务类型
     */
    public void setBusitype(String busitype) {
        this.busitype = busitype == null ? null : busitype.trim();
    }

    /**
     * 取得 消息内容
     * @return 消息内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置 消息内容
     * @param content 消息内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 取得 发送时间
     * @return 发送时间
     */
    public Date getSendtime() {
        return sendtime;
    }

    /**
     * 设置 发送时间
     * @param sendtime 发送时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    /**
     * 取得 发送状态：0成功，1失败
     * @return 发送状态：0成功，1失败
     */
    public String getSendstate() {
        return sendstate;
    }

    /**
     * 设置 发送状态：0成功，1失败
     * @param sendstate 发送状态：0成功，1失败
     */
    public void setSendstate(String sendstate) {
        this.sendstate = sendstate == null ? null : sendstate.trim();
    }

    /**
     * 取得 创建人
     * @return 创建人
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置 创建人
     * @param creator 创建人
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 取得 录入日期
     * @return 录入日期
     */
    public Date getCreatedate() {
        return createdate;
    }

    /**
     * 设置 录入日期
     * @param createdate 录入日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    /**
     * 取得 更新人员
     * @return 更新人员
     */
    public String getLstmntuser() {
        return lstmntuser;
    }

    /**
     * 设置 更新人员
     * @param lstmntuser 更新人员
     */
    public void setLstmntuser(String lstmntuser) {
        this.lstmntuser = lstmntuser == null ? null : lstmntuser.trim();
    }

    /**
     * 取得 更新时间
     * @return 更新时间
     */
    public Date getLstmntdate() {
        return lstmntdate;
    }

    /**
     * 设置 更新时间
     * @param lstmntdate 更新时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setLstmntdate(Date lstmntdate) {
        this.lstmntdate = lstmntdate;
    }

    /**
     * 取得 发送次数
     * @return 发送次数
     */
    public Integer getSendnum() {
        return sendnum;
    }

    /**
     * 设置 发送次数
     * @param sendnum 发送次数
     */
    public void setSendnum(Integer sendnum) {
        this.sendnum = sendnum;
    }

    /**
     * 取得 模板号
     * @return 模板号
     */
    public String getBusicode() {
        return busicode;
    }

    /**
     * 设置 模板号
     * @param busicode 模板号
     */
    public void setBusicode(String busicode) {
        this.busicode = busicode == null ? null : busicode.trim();
    }

    /**
     * 取得 邮件发送内容
     * @return 邮件发送内容
     */
    public String getSubcontent() {
        return subcontent;
    }

    /**
     * 设置 邮件发送内容
     * @param subcontent 邮件发送内容
     */
    public void setSubcontent(String subcontent) {
        this.subcontent = subcontent == null ? null : subcontent.trim();
    }
}