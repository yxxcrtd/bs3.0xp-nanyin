package com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixmenmailinfo.dto;

import com.joyintech.framework.base.BaseEntity;

import java.util.Date;

/**
 * 描述:FX_IMIX_MEM_MAIL_INFO表的实体类
 * @version
 * @author:  zcf
 * @创建时间: 2020-01-15
 */
public class FxImixMenMailInfoDto extends BaseEntity {
    /**
     * 流水号
     */
    private String seqno;

    /**
     * 交易成员ID(CSTPID)
     */
    private String jycyid;

    /**
     * 交易成员
     */
    private String jycy;

    /**
     * 主机构交易员ID
     */
    private String jyyid;

    /**
     * 主机构交易员姓名
     */
    private String jyyxm;

    /**
     * 联系电话
     */
    private String lxdh;

    /**
     * 传真
     */
    private String cz;

    /**
     * EMail
     */
    private String email;

    /**
     * MSN
     */
    private String msn;

    /**
     * 通讯地址
     */
    private String txdz;

    /**
     * 邮政编码
     */
    private String yzbm;

    /**
     * 创建人
     */
    private String creatorid;

    /**
     * 创建时间
     */
    private Date createdtime;

    /**
     * 更新人
     */
    private String lastmodifierid;

    /**
     * 更新时间
     */
    private Date lastmodifiedtime;

    /**
     * 交易员姓名首字母
     */
    private String jyyxmszm;

    /**
     * 是否主机构
     */
    private String sfzjg;

    /**
     * 主机构代码
     */
    private String zjgdm;

    /**
     * 主机构名称
     */
    private String zjgmc;

    /**
     * 流水号
     * @return SEQNO 流水号
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 流水号
     * @param seqno 流水号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 交易成员ID(CSTPID)
     * @return JYCYID 交易成员ID(CSTPID)
     */
    public String getJycyid() {
        return jycyid;
    }

    /**
     * 交易成员ID(CSTPID)
     * @param jycyid 交易成员ID(CSTPID)
     */
    public void setJycyid(String jycyid) {
        this.jycyid = jycyid == null ? null : jycyid.trim();
    }

    /**
     * 交易成员
     * @return JYCY 交易成员
     */
    public String getJycy() {
        return jycy;
    }

    /**
     * 交易成员
     * @param jycy 交易成员
     */
    public void setJycy(String jycy) {
        this.jycy = jycy == null ? null : jycy.trim();
    }

    /**
     * 主机构交易员ID
     * @return JYYID 主机构交易员ID
     */
    public String getJyyid() {
        return jyyid;
    }

    /**
     * 主机构交易员ID
     * @param jyyid 主机构交易员ID
     */
    public void setJyyid(String jyyid) {
        this.jyyid = jyyid == null ? null : jyyid.trim();
    }

    /**
     * 主机构交易员姓名
     * @return JYYXM 主机构交易员姓名
     */
    public String getJyyxm() {
        return jyyxm;
    }

    /**
     * 主机构交易员姓名
     * @param jyyxm 主机构交易员姓名
     */
    public void setJyyxm(String jyyxm) {
        this.jyyxm = jyyxm == null ? null : jyyxm.trim();
    }

    /**
     * 联系电话
     * @return LXDH 联系电话
     */
    public String getLxdh() {
        return lxdh;
    }

    /**
     * 联系电话
     * @param lxdh 联系电话
     */
    public void setLxdh(String lxdh) {
        this.lxdh = lxdh == null ? null : lxdh.trim();
    }

    /**
     * 传真
     * @return CZ 传真
     */
    public String getCz() {
        return cz;
    }

    /**
     * 传真
     * @param cz 传真
     */
    public void setCz(String cz) {
        this.cz = cz == null ? null : cz.trim();
    }

    /**
     * EMail
     * @return EMAIL EMail
     */
    public String getEmail() {
        return email;
    }

    /**
     * EMail
     * @param email EMail
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * MSN
     * @return MSN MSN
     */
    public String getMsn() {
        return msn;
    }

    /**
     * MSN
     * @param msn MSN
     */
    public void setMsn(String msn) {
        this.msn = msn == null ? null : msn.trim();
    }

    /**
     * 通讯地址
     * @return TXDZ 通讯地址
     */
    public String getTxdz() {
        return txdz;
    }

    /**
     * 通讯地址
     * @param txdz 通讯地址
     */
    public void setTxdz(String txdz) {
        this.txdz = txdz == null ? null : txdz.trim();
    }

    /**
     * 邮政编码
     * @return YZBM 邮政编码
     */
    public String getYzbm() {
        return yzbm;
    }

    /**
     * 邮政编码
     * @param yzbm 邮政编码
     */
    public void setYzbm(String yzbm) {
        this.yzbm = yzbm == null ? null : yzbm.trim();
    }

    /**
     * 创建人
     * @return CREATORID 创建人
     */
    public String getCreatorid() {
        return creatorid;
    }

    /**
     * 创建人
     * @param creatorid 创建人
     */
    public void setCreatorid(String creatorid) {
        this.creatorid = creatorid == null ? null : creatorid.trim();
    }

    /**
     * 创建时间
     * @return CREATEDTIME 创建时间
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * 创建时间
     * @param createdtime 创建时间
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * 更新人
     * @return LASTMODIFIERID 更新人
     */
    public String getLastmodifierid() {
        return lastmodifierid;
    }

    /**
     * 更新人
     * @param lastmodifierid 更新人
     */
    public void setLastmodifierid(String lastmodifierid) {
        this.lastmodifierid = lastmodifierid == null ? null : lastmodifierid.trim();
    }

    /**
     * 更新时间
     * @return LASTMODIFIEDTIME 更新时间
     */
    public Date getLastmodifiedtime() {
        return lastmodifiedtime;
    }

    /**
     * 更新时间
     * @param lastmodifiedtime 更新时间
     */
    public void setLastmodifiedtime(Date lastmodifiedtime) {
        this.lastmodifiedtime = lastmodifiedtime;
    }

    /**
     * 交易员姓名首字母
     * @return JYYXMSZM 交易员姓名首字母
     */
    public String getJyyxmszm() {
        return jyyxmszm;
    }

    /**
     * 交易员姓名首字母
     * @param jyyxmszm 交易员姓名首字母
     */
    public void setJyyxmszm(String jyyxmszm) {
        this.jyyxmszm = jyyxmszm == null ? null : jyyxmszm.trim();
    }

    /**
     * 是否主机构
     * @return SFZJG 是否主机构
     */
    public String getSfzjg() {
        return sfzjg;
    }

    /**
     * 是否主机构
     * @param sfzjg 是否主机构
     */
    public void setSfzjg(String sfzjg) {
        this.sfzjg = sfzjg == null ? null : sfzjg.trim();
    }

    /**
     * 主机构代码
     * @return ZJGDM 主机构代码
     */
    public String getZjgdm() {
        return zjgdm;
    }

    /**
     * 主机构代码
     * @param zjgdm 主机构代码
     */
    public void setZjgdm(String zjgdm) {
        this.zjgdm = zjgdm == null ? null : zjgdm.trim();
    }

    /**
     * 主机构名称
     * @return ZJGMC 主机构名称
     */
    public String getZjgmc() {
        return zjgmc;
    }

    /**
     * 主机构名称
     * @param zjgmc 主机构名称
     */
    public void setZjgmc(String zjgmc) {
        this.zjgmc = zjgmc == null ? null : zjgmc.trim();
    }
}