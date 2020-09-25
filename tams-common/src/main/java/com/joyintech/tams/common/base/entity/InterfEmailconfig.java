package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class InterfEmailconfig extends BaseEntity {
    /**
     * 序号
     */
    private String serialno;

    /**
     * 业务类型
     */
    private String busitype;

    /**
     * 发件人EMAIL
     */
    private String sendemail;

    /**
     * 发件人名称
     */
    private String sendname;

    /**
     * 发件人部门
     */
    private String senddepart;

    /**
     * 收件人EMAIL
     */
    private String revemail;

    /**
     * 抄送人
     */
    private String revemail2;

    /**
     * 密送人
     */
    private String revemail3;

    /**
     * 收件人部门
     */
    private String revdepart;

    /**
     * 邮件模版号
     */
    private String modelcode;

    /**
     * 有效标识：D 删除  A 新建  E 有效
     */
    private String effectflag;

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
     * 取得 发件人EMAIL
     * @return 发件人EMAIL
     */
    public String getSendemail() {
        return sendemail;
    }

    /**
     * 设置 发件人EMAIL
     * @param sendemail 发件人EMAIL
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
     * 取得 发件人部门
     * @return 发件人部门
     */
    public String getSenddepart() {
        return senddepart;
    }

    /**
     * 设置 发件人部门
     * @param senddepart 发件人部门
     */
    public void setSenddepart(String senddepart) {
        this.senddepart = senddepart == null ? null : senddepart.trim();
    }

    /**
     * 取得 收件人EMAIL
     * @return 收件人EMAIL
     */
    public String getRevemail() {
        return revemail;
    }

    /**
     * 设置 收件人EMAIL
     * @param revemail 收件人EMAIL
     */
    public void setRevemail(String revemail) {
        this.revemail = revemail == null ? null : revemail.trim();
    }

    /**
     * 取得 抄送人
     * @return 抄送人
     */
    public String getRevemail2() {
        return revemail2;
    }

    /**
     * 设置 抄送人
     * @param revemail2 抄送人
     */
    public void setRevemail2(String revemail2) {
        this.revemail2 = revemail2 == null ? null : revemail2.trim();
    }

    /**
     * 取得 密送人
     * @return 密送人
     */
    public String getRevemail3() {
        return revemail3;
    }

    /**
     * 设置 密送人
     * @param revemail3 密送人
     */
    public void setRevemail3(String revemail3) {
        this.revemail3 = revemail3 == null ? null : revemail3.trim();
    }

    /**
     * 取得 收件人部门
     * @return 收件人部门
     */
    public String getRevdepart() {
        return revdepart;
    }

    /**
     * 设置 收件人部门
     * @param revdepart 收件人部门
     */
    public void setRevdepart(String revdepart) {
        this.revdepart = revdepart == null ? null : revdepart.trim();
    }

    /**
     * 取得 邮件模版号
     * @return 邮件模版号
     */
    public String getModelcode() {
        return modelcode;
    }

    /**
     * 设置 邮件模版号
     * @param modelcode 邮件模版号
     */
    public void setModelcode(String modelcode) {
        this.modelcode = modelcode == null ? null : modelcode.trim();
    }

    /**
     * 取得 有效标识：D 删除  A 新建  E 有效
     * @return 有效标识：D 删除  A 新建  E 有效
     */
    public String getEffectflag() {
        return effectflag;
    }

    /**
     * 设置 有效标识：D 删除  A 新建  E 有效
     * @param effectflag 有效标识：D 删除  A 新建  E 有效
     */
    public void setEffectflag(String effectflag) {
        this.effectflag = effectflag == null ? null : effectflag.trim();
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
}