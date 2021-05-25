package com.joyintech.tams.report.system.chbmst.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.ChbMstCounterParty;
import com.joyintech.tams.report.common.base.annotate.TableName;
import com.joyintech.tams.report.system.chbmst.validator.ChbMstCounterPartyValidator;
import com.joyintech.tams.report.system.prorate.validator.DisPrdFeeRateValidator;
import com.joyintech.workflow.engine.dto.ProcessEntity;

@TableName("chb_mst_counter_party")
@PageId("ChbMstCounterParty002")
@ValidLogic(targetClass = ChbMstCounterPartyValidator.class, groups = { ValidateGroupEdit.class,
        ValidateGroupDelete.class })
public class ChbMstCounterPartyDto extends ChbMstCounterParty implements ITableRow, ProcessEntity {

    private static final long serialVersionUID = 3438383148770226070L;
    /**
     * 中债数据状态
     * 原status框架上面会做权限控制，所以这里换了name
     */
    private String svrStatus;

    private String svrStatusName;


    public String getSvrStatus() {
        return svrStatus;
    }

    public void setSvrStatus(String svrStatus) {
        this.svrStatus = svrStatus;
    }

    public String getSvrStatusName() {
        return svrStatusName;
    }

    public void setSvrStatusName(String svrStatusName) {
        this.svrStatusName = svrStatusName;
    }
}
