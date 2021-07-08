package com.joyintech.tams.invest.batch.stockonlinevoting.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class XmlSZVoteDto {
    /**
     * 投票议案编号
     */
    @XmlElement(name = "ProposalID")
    private String proposalID;

    /**
     * 投票议案名称
     */
    @XmlElement(name = "ProposalName")
    private String proposalName;

    /**
     * 投票议案类型
     */
    @XmlElement(name = "ProposalType")
    private String proposalType;

    /**
     *
     */
    @XmlElement(name = "ProposalRelation")
    private String proposalRelation;

    /**
     * 累计投票应选数
     */
    @XmlElement(name = "ElectionNum")
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal electionNum;

    /**
     *
     */
    @XmlElement(name = "vrolelist")
    private List<XmlSZVroleDto> vrolelist;

    public String getProposalID() {
        return proposalID;
    }

    public void setProposalID(String proposalID) {
        this.proposalID = proposalID;
    }

    public String getProposalName() {
        return proposalName;
    }

    public void setProposalName(String proposalName) {
        this.proposalName = proposalName;
    }

    public String getProposalType() {
        return proposalType;
    }

    public void setProposalType(String proposalType) {
        this.proposalType = proposalType;
    }

    public String getProposalRelation() {
        return proposalRelation;
    }

    public void setProposalRelation(String proposalRelation) {
        this.proposalRelation = proposalRelation;
    }

    public BigDecimal getElectionNum() {
        return electionNum;
    }

    public void setElectionNum(BigDecimal electionNum) {
        this.electionNum = electionNum;
    }

    public List<XmlSZVroleDto> getVrolelist() {
        return vrolelist;
    }

    public void setVrolelist(List<XmlSZVroleDto> vrolelist) {
        this.vrolelist = vrolelist;
    }
}
