package com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixjgzcgx.dto;

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 描述:机构主从关系表的实体类
 * @version
 * @author:  zcf
 * @创建时间: 2020-01-07
 */
public class FxImixJgzcgxDto extends BaseEntity {
    /**
     * 流水号
     */
    private BigDecimal seqno;

    /**
     * 主机构名称
     */
    private String zjgmc;

    /**
     * 主机构六位码
     */
    private String zjglwm;

    /**
     * 从机构名称
     */
    private String cjgmc;

    /**
     * 从机构6位码
     */
    private String cjglwm;

    /**
     * 主关系类型名称
     */
    private String zgxlxmc;

    /**
     * 从关系类型名称
     */
    private String cgxlxmc;

    /**
     * 有效标识
     */
    private String effectflag;

    /**
     * 创建人
     */
    private BigDecimal creatorid;

    /**
     * 创建时间
     */
    private Date createdtime;

    /**
     * 修改人
     */
    private BigDecimal lastmodifierid;

    /**
     * 修改时间
     */
    private Date lastmodifietime;

    /**
     * 流水号
     * @return SEQNO 流水号
     */
    public BigDecimal getSeqno() {
        return seqno;
    }

    /**
     * 流水号
     * @param seqno 流水号
     */
    public void setSeqno(BigDecimal seqno) {
        this.seqno = seqno;
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

    /**
     * 主机构六位码
     * @return ZJGLWM 主机构六位码
     */
    public String getZjglwm() {
        return zjglwm;
    }

    /**
     * 主机构六位码
     * @param zjglwm 主机构六位码
     */
    public void setZjglwm(String zjglwm) {
        this.zjglwm = zjglwm == null ? null : zjglwm.trim();
    }

    /**
     * 从机构名称
     * @return CJGMC 从机构名称
     */
    public String getCjgmc() {
        return cjgmc;
    }

    /**
     * 从机构名称
     * @param cjgmc 从机构名称
     */
    public void setCjgmc(String cjgmc) {
        this.cjgmc = cjgmc == null ? null : cjgmc.trim();
    }

    /**
     * 从机构6位码
     * @return CJGLWM 从机构6位码
     */
    public String getCjglwm() {
        return cjglwm;
    }

    /**
     * 从机构6位码
     * @param cjglwm 从机构6位码
     */
    public void setCjglwm(String cjglwm) {
        this.cjglwm = cjglwm == null ? null : cjglwm.trim();
    }

    /**
     * 主关系类型名称
     * @return ZGXLXMC 主关系类型名称
     */
    public String getZgxlxmc() {
        return zgxlxmc;
    }

    /**
     * 主关系类型名称
     * @param zgxlxmc 主关系类型名称
     */
    public void setZgxlxmc(String zgxlxmc) {
        this.zgxlxmc = zgxlxmc == null ? null : zgxlxmc.trim();
    }

    /**
     * 从关系类型名称
     * @return CGXLXMC 从关系类型名称
     */
    public String getCgxlxmc() {
        return cgxlxmc;
    }

    /**
     * 从关系类型名称
     * @param cgxlxmc 从关系类型名称
     */
    public void setCgxlxmc(String cgxlxmc) {
        this.cgxlxmc = cgxlxmc == null ? null : cgxlxmc.trim();
    }

    /**
     * 有效标识
     * @return EFFECTFLAG 有效标识
     */
    public String getEffectflag() {
        return effectflag;
    }

    /**
     * 有效标识
     * @param effectflag 有效标识
     */
    public void setEffectflag(String effectflag) {
        this.effectflag = effectflag == null ? null : effectflag.trim();
    }

    /**
     * 创建人
     * @return CREATORID 创建人
     */
    public BigDecimal getCreatorid() {
        return creatorid;
    }

    /**
     * 创建人
     * @param creatorid 创建人
     */
    public void setCreatorid(BigDecimal creatorid) {
        this.creatorid = creatorid;
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
     * 修改人
     * @return LASTMODIFIERID 修改人
     */
    public BigDecimal getLastmodifierid() {
        return lastmodifierid;
    }

    /**
     * 修改人
     * @param lastmodifierid 修改人
     */
    public void setLastmodifierid(BigDecimal lastmodifierid) {
        this.lastmodifierid = lastmodifierid;
    }

    /**
     * 修改时间
     * @return LASTMODIFIETIME 修改时间
     */
    public Date getLastmodifietime() {
        return lastmodifietime;
    }

    /**
     * 修改时间
     * @param lastmodifietime 修改时间
     */
    public void setLastmodifietime(Date lastmodifietime) {
        this.lastmodifietime = lastmodifietime;
    }
}