package com.joyintech.tams.report.system.bonddiscountrate.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.ChbMstCounterParty;
import com.joyintech.tams.common.base.entity.ChbSecDiscountRate;
import com.joyintech.tams.report.common.base.annotate.TableName;
import com.joyintech.tams.report.system.bonddiscountrate.validator.ChbSecDiscountRateValidator;
import com.joyintech.tams.report.system.chbmst.validator.ChbMstCounterPartyValidator;
import com.joyintech.tams.report.system.prorate.validator.DisPrdFeeRateValidator;
import com.joyintech.workflow.engine.dto.ProcessEntity;

@TableName("chb_sec_discount_rate")
@PageId("ChbSecDiscountRate002")
@ValidLogic(targetClass = ChbSecDiscountRateValidator.class, groups = { ValidateGroupEdit.class,
        ValidateGroupDelete.class })
public class ChbSecDiscountRateDto extends ChbSecDiscountRate implements ITableRow, ProcessEntity {

    private String secTypeStr;

    private String issueTypeStr;

    private String gradeTypeStr;

    public String getSecTypeStr() {
        return secTypeStr;
    }

    public void setSecTypeStr(String secTypeStr) {
        this.secTypeStr = secTypeStr;
    }

    public String getIssueTypeStr() {
        return issueTypeStr;
    }

    public void setIssueTypeStr(String issueTypeStr) {
        this.issueTypeStr = issueTypeStr;
    }

    public String getGradeTypeStr() {
        return gradeTypeStr;
    }

    public void setGradeTypeStr(String gradeTypeStr) {
        this.gradeTypeStr = gradeTypeStr;
    }
}
