package com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixmeninfo.dto;

import com.joyintech.framework.base.BaseEntity;

import java.util.Date;

/**
 * 描述:FX_IMIX_MEM_INFO表的实体类
 * @version
 * @author:  zcf
 * @创建时间: 2020-01-07
 */
public class FxImixMenInfoDto extends BaseEntity {
    /**
     * 流水号
     */
    private String seqno;

    /**
     * 交易成员ID(CSTPID)
     */
    private String jycyid;

    /**
     * 交易账户21位码
     */
    private String jgdm;

    /**
     * 交易成员全称
     */
    private String jycyqc;

    /**
     * 中文简称
     */
    private String zwjc;

    /**
     * 英文全称
     */
    private String ywqc;

    /**
     * 英文简称
     */
    private String ywjc;

    /**
     * 创建人
     */
    private String creatorid;

    /**
     * 创建时间
     */
    private Date createdtime;

    /**
     * 更新时间
     */
    private Date lastmodifiedtime;

    /**
     * 更新人
     */
    private String lastmodifierid;

    /**
     * 中文简称首字母
     */
    private String zwjcszm;

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
     * 交易账户21位码
     * @return JGDM 交易账户21位码
     */
    public String getJgdm() {
        return jgdm;
    }

    /**
     * 交易账户21位码
     * @param jgdm 交易账户21位码
     */
    public void setJgdm(String jgdm) {
        this.jgdm = jgdm == null ? null : jgdm.trim();
    }

    /**
     * 交易成员全称
     * @return JYCYQC 交易成员全称
     */
    public String getJycyqc() {
        return jycyqc;
    }

    /**
     * 交易成员全称
     * @param jycyqc 交易成员全称
     */
    public void setJycyqc(String jycyqc) {
        this.jycyqc = jycyqc == null ? null : jycyqc.trim();
    }

    /**
     * 中文简称
     * @return ZWJC 中文简称
     */
    public String getZwjc() {
        return zwjc;
    }

    /**
     * 中文简称
     * @param zwjc 中文简称
     */
    public void setZwjc(String zwjc) {
        this.zwjc = zwjc == null ? null : zwjc.trim();
    }

    /**
     * 英文全称
     * @return YWQC 英文全称
     */
    public String getYwqc() {
        return ywqc;
    }

    /**
     * 英文全称
     * @param ywqc 英文全称
     */
    public void setYwqc(String ywqc) {
        this.ywqc = ywqc == null ? null : ywqc.trim();
    }

    /**
     * 英文简称
     * @return YWJC 英文简称
     */
    public String getYwjc() {
        return ywjc;
    }

    /**
     * 英文简称
     * @param ywjc 英文简称
     */
    public void setYwjc(String ywjc) {
        this.ywjc = ywjc == null ? null : ywjc.trim();
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
     * 中文简称首字母
     * @return ZWJCSZM 中文简称首字母
     */
    public String getZwjcszm() {
        return zwjcszm;
    }

    /**
     * 中文简称首字母
     * @param zwjcszm 中文简称首字母
     */
    public void setZwjcszm(String zwjcszm) {
        this.zwjcszm = zwjcszm == null ? null : zwjcszm.trim();
    }
}