package com.joyintech.tams.invest.batch.stockonlinevoting.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class XmlSHVoteDto {
    /**
     * 投票议案编号
     */
    @XmlElement(name = "vid")
    private String vid;

    /**
     * 投票议案名称
     */
    @XmlElement(name = "vinfo")
    private String vinfo;

    /**
     * 投票议案类型
     */
    @XmlElement(name = "vtype")
    private String vtype;

    /**
     *
     */
    @XmlElement(name = "vrelation")
    private String vrelation;

    /**
     * 累计投票应选数
     */
    @XmlElement(name = "vleijino")
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal vleijino;

    /**
     *
     */
    @XmlElement(name = "vrolelist")
    private List<XmlSHVroleDto> vrolelist;

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getVinfo() {
        return vinfo;
    }

    public void setVinfo(String vinfo) {
        this.vinfo = vinfo;
    }

    public String getVtype() {
        return vtype;
    }

    public void setVtype(String vtype) {
        this.vtype = vtype;
    }

    public String getVrelation() {
        return vrelation;
    }

    public void setVrelation(String vrelation) {
        this.vrelation = vrelation;
    }

    public BigDecimal getVleijino() {
        return vleijino;
    }

    public void setVleijino(BigDecimal vleijino) {
        this.vleijino = vleijino;
    }

    public List<XmlSHVroleDto> getVrolelist() {
        return vrolelist;
    }

    public void setVrolelist(List<XmlSHVroleDto> vrolelist) {
        this.vrolelist = vrolelist;
    }
}
