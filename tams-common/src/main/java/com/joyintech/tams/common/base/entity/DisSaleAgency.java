package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class DisSaleAgency extends BaseEntity {
    /**
     * 代销机构uuid
     */
    private String sequenceNo;

    /**
     * 代销机构名称
     */
    private String agencyName;

    /**
     * 协议起始时间
     */
    private Date agreementVdate;

    /**
     * 协议终止时间
     */
    private Date agreementMdate;

    /**
     * 取得 代销机构uuid
     * @return 代销机构uuid
     */
    public String getSequenceNo() {
        return sequenceNo;
    }

    /**
     * 设置 代销机构uuid
     * @param sequenceNo 代销机构uuid
     */
    public void setSequenceNo(String sequenceNo) {
        this.sequenceNo = sequenceNo == null ? null : sequenceNo.trim();
    }

    /**
     * 取得 代销机构名称
     * @return 代销机构名称
     */
    public String getAgencyName() {
        return agencyName;
    }

    /**
     * 设置 代销机构名称
     * @param agencyName 代销机构名称
     */
    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName == null ? null : agencyName.trim();
    }

    /**
     * 取得 协议起始时间
     * @return 协议起始时间
     */
    public Date getAgreementVdate() {
        return agreementVdate;
    }

    /**
     * 设置 协议起始时间
     * @param agreementVdate 协议起始时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setAgreementVdate(Date agreementVdate) {
        this.agreementVdate = agreementVdate;
    }

    /**
     * 取得 协议终止时间
     * @return 协议终止时间
     */
    public Date getAgreementMdate() {
        return agreementMdate;
    }

    /**
     * 设置 协议终止时间
     * @param agreementMdate 协议终止时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setAgreementMdate(Date agreementMdate) {
        this.agreementMdate = agreementMdate;
    }
}