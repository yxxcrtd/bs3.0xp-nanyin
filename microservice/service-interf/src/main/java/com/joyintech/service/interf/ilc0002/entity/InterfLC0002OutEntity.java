package com.joyintech.service.interf.ilc0002.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Required;

import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;
import com.joyintech.service.interf.core.annotation.EsbNumber;
import com.joyintech.service.interf.ibillinfo.entity.InterfBillOutEntity;

/**
 * 接口LC0002输出
 * 
 * @author 杨静敏
 * @version 1.0 2018年11月21日
 * @see InterfLC0002OutEntity
 * @since 1.0
 */
public class InterfLC0002OutEntity extends InterfBillOutEntity {

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

    @EsbName(value = "CCY")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 3)
    @Required
    @Override
    public String getSubscriptionCcy() {
        return super.getSubscriptionCcy();
    }

    @EsbName(value = "CCYNAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 30)
    @Required
    @Override
    public String getSubscriptionCcyName() {
        return super.getSubscriptionCcyName();
    }

    @EsbName(value = "BASIS")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 6)
    @Required
    @Override
    public String getInterestMode() {
        return super.getInterestMode();
    }

    @EsbName(value = "ISSUETYPE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 1)
    @Required
    @Override
    public String getIssueMode() {
        return super.getIssueMode();
    }

    @EsbName(value = "ISSUETYPENAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 40)
    @Required
    @Override
    public String getIssueModeName() {
        return super.getIssueModeName();
    }

    @EsbName(value = "BRANCH")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
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

    @EsbName(value = "VDATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    @Override
    public Date getValueDate() {
        return super.getValueDate();
    }

    @EsbName(value = "MDATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    @Override
    public Date getExpiryDate() {
        return super.getExpiryDate();
    }

    /**
     * 获取 现金流发生日
     * 
     * @return
     */
    @EsbName(value = "CDATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    @Override
    public Date getCashdate() {
        return super.getCashdate();
    }

    @EsbName(value = "TERM")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 10)
    @Required
    @Override
    public String getTerm() {
        return super.getTerm();
    }

    @EsbName(value = "ACTTERM")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 10)
    @Required
    @Override
    public String getActterm() {
        return super.getActterm();
    }

    @EsbName(value = "PRINOUTAMT")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 20, scale = 4)
    @Override
    public BigDecimal getPrinoutamt() {
        return super.getPrinoutamt();
    }

    @EsbName(value = "NXTPRINAMT")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 20, scale = 4)
    @Override
    public BigDecimal getNxtprinamt() {
        return super.getNxtprinamt();
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
    @Override
    public String getAmt1() {
        return super.getAmt1();
    }

    @EsbName(value = "YIELD")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 20, scale = 4)
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

    @EsbName(value = "ACTINTAMT")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 20, scale = 4)
    @Required
    @Override
    public BigDecimal getActintamt() {
        return super.getActintamt();
    }

    @EsbName(value = "LEVELACTINTAMT")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 200)
    @Override
    public String getLevelactintamt() {
        return super.getLevelactintamt();
    }

    @EsbName(value = "ACTPRININTAMT")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 20, scale = 4)
    @Required
    @Override
    public BigDecimal getActprinintamt() {
        return super.getActprinintamt();
    }

    @EsbName(value = "LEVELPRININTAMT")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 200)
    @Override
    public String getLevelprinintamt() {
        return super.getLevelactintamt();
    }

    @EsbName(value = "SYIELD")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 20, scale = 9)
    @Override
    public BigDecimal getSaleFeeRate() {
        return super.getSaleFeeRate();
    }

    @EsbName(value = "PAYSAMT")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 20, scale = 4)
    @Override
    public BigDecimal getPaysamt() {
        return super.getPaysamt();
    }

    @EsbName(value = "HYIELD")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 20, scale = 9)
    @Override
    public BigDecimal getConsignFeeRate() {
        return super.getConsignFeeRate();
    }

    @EsbName(value = "PAYHOSTAMT")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 20, scale = 4)
    @Override
    public BigDecimal getPayhostamt() {
        return super.getPayhostamt();
    }

    @EsbName(value = "AMYIELD")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 20, scale = 9)
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
    @EsbNumber(length = 2)
    @Required
    @Override
    public String getAffixFileNum() {
        return super.getAffixFileNum();
    }

    @EsbName(value = "FTP")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 20, scale = 9)
    @Override
    public BigDecimal getFtp() {
        return super.getFtp();
    }

    @EsbName(value = "FTPAMT")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 20, scale = 9)
    @Override
    public BigDecimal getFtpamt() {
        return super.getFtpamt();
    }

    @EsbName(value = "RECAMT")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 20, scale = 4)
    @Override
    public BigDecimal getRecamt() {
        return super.getRecamt();
    }

}
