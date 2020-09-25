package com.joyintech.service.interf.ilc0001.entity;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Required;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;
import com.joyintech.service.interf.core.annotation.EsbNumber;
import com.joyintech.service.interf.ibillinfo.entity.InterfBillOutEntity;

/**
 * 接口LC0001输出
 * 
 * @author zhangrui1
 * @version 1.0 2018年10月12日
 * @see InterfBillOutEntity
 * @since 1.0
 */
public class InterfLC0001OutEntity extends InterfBillOutEntity {

    @EsbName(value = "PRODID")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    @Override
    public String getProdId() {
        return super.getProdId();
    }

    @EsbName(value = "SHORTNAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 100)
    @Required
    @Override
    public String getProductShortname() {
        return super.getProductShortname();
    }

    @EsbName(value = "PRODNAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 100)
    @Required
    @Override
    public String getProductName() {
        return super.getProductName();
    }

    @EsbName(value = "ASSETPOOL")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    @Override
    public String getAssetsPool() {
        return super.getAssetsPool();
    }

    @EsbName(value = "ASSETPOOLNAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 30)
    @Required
    @Override
    public String getAssetsPoolName() {
        return super.getAssetsPoolName();
    }

    @EsbName(value = "PRODTYPE1")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 10)
    @Required
    @Override
    public String getType() {
        return super.getType();
    }

    @EsbName(value = "PRODTYPE1NAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 100)
    @Required
    @Override
    public String getProductTypeName() {
        return super.getProductTypeName();
    }

    @EsbName(value = "MKTDEPTNO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    @Override
    public String getSaleDept() {
        return super.getSaleDept();
    }

    @EsbName(value = "MKTDEPTNONAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 50)
    @Required
    @Override
    public String getDeptName() {
        return super.getDeptName();
    }

    @EsbName(value = "SUBCCY")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 3)
    @Required
    @Override
    public String getSubscriptionCcy() {
        return super.getSubscriptionCcy();
    }

    @EsbName(value = "SUBCCYNAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 30)
    @Required
    @Override
    public String getSubscriptionCcyName() {
        return super.getSubscriptionCcyName();
    }

    @EsbName(value = "ROLLAMT")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 10)
    @Required
    @Override
    public String getRollTerm() {
        return super.getRollTerm();
    }

    @EsbName(value = "BASIS")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 6)
    @Required
    @Override
    public String getInterestMode() {
        return super.getInterestMode();
    }

    @EsbName(value = "VDATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    public String getVDate() {
        return DateUtil.format(super.getValueDate(), DateUtil.DEFAULT_OTHER_FORMAT);
    }

    @EsbName(value = "MDATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    public String getMDate() {
        return DateUtil.format(super.getExpiryDate(), DateUtil.DEFAULT_OTHER_FORMAT);
    }

    @EsbName(value = "TERM")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 10)
    @Required
    @Override
    public String getTerm() {
        return super.getTerm();
    }

    @EsbName(value = "PREPRININAMT")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 20, scale = 4)
    @Required
    @Override
    public BigDecimal getAmt() {
        return super.getAmt();
    }

    @EsbName(value = "PRININAMT")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 20, scale = 4)
    @Required
    @Override
    public BigDecimal getSubscriptionAmt() {
        return super.getSubscriptionAmt();
    }

    @EsbName(value = "PRINAMT")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 20, scale = 4)
    @Required
    @Override
    public BigDecimal getCorpus() {
        return super.getCorpus();
    }

    @EsbName(value = "LEVELPRINAMT")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 200)
    @Required
    @Override
    public String getAmt1() {
        return super.getAmt1();
    }

    @EsbName(value = "ISSUETYPE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 1)
    @Override
    public String getIssueMode() {
        return super.getIssueMode();
    }

    @EsbName(value = "ISSUETYPENAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 40)
    @Override
    public String getIssueModeName() {
        return super.getIssueModeName();
    }

    @EsbName(value = "BRANCH")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    @Override
    public String getAcctBranchId() {
        return super.getAcctBranchId();
    }

    @EsbName(value = "BRANCHNAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 100)
    @Override
    public String getBranchName() {
        return super.getBranchName();
    }

    @EsbName(value = "YIELD")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 20, scale = 7)
    @Required
    @Override
    public BigDecimal getYield() {
        return super.getYield();
    }

    @EsbName(value = "LEVELYIELD")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 200)
    @Override
    public String getBedYieldChar() {
        return super.getBedYieldChar();
    }

    @EsbName(value = "SYIELD")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 20, scale = 9)
    @Required
    @Override
    public BigDecimal getSaleFeeRate() {
        return super.getSaleFeeRate();
    }

    @EsbName(value = "HYIELD")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 20, scale = 9)
    @Override
    public BigDecimal getConsignFeeRate() {
        return super.getConsignFeeRate();
    }

    @EsbName(value = "AMYIELD")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 20, scale = 9)
    @Required
    @Override
    public BigDecimal getAssetsManagementFeeRate() {
        return super.getAssetsManagementFeeRate();
    }

    @EsbName(value = "OYIELD")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 20, scale = 9)
    @Override
    public BigDecimal getOtherFeeRate() {
        return super.getOtherFeeRate();
    }

    @EsbName(value = "SUBMITUSER")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 36)
    @Override
    public String getAgencyClientId() {
        return super.getAgencyClientId();
    }

    @EsbName(value = "SUBMITUSERNAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 50)
    @Override
    public String getAgencyClientName() {
        return super.getAgencyClientName();
    }

    /**
     * 获取 复核柜员
     * 
     * @return
     */
    @EsbName(value = "REVIEWUSER")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 36)
    @Override
    public String getApprUserId() {
        return super.getApprUserId();
    }

    @EsbName(value = "REVIEWUSERNAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 50)
    @Override
    public String getApprTellerName() {
        return super.getApprTellerName();
    }

    @EsbName(value = "GRANTUSER")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 36)
    @Override
    public String getGrantTellerNo() {
        return super.getGrantTellerNo();
    }

    @EsbName(value = "GRANTUSERNAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 50)
    @Override
    public String getOwnerName() {
        return super.getOwnerName();
    }

    @EsbName(value = "OWNMKTDEPTNO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 32)
    @Required
    @Override
    public String getBelongDeptCode() {
        return super.getBelongDeptCode();
    }

    @EsbName(value = "OWNMKTDEPTNONAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 50)
    @Required
    @Override
    public String getBelongDeptName() {
        return super.getBelongDeptName();
    }

    @EsbName(value = "ACCESSORY_COUNT")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 2)
    @Required
    @Override
    public String getAffixFileNum() {
        return super.getAffixFileNum();
    }

}
