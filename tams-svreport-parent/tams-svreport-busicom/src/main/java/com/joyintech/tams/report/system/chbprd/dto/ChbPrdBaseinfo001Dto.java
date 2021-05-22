package com.joyintech.tams.report.system.chbprd.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.ChbPrdBaseinfo;
import com.joyintech.tams.report.common.base.annotate.TableName;
import com.joyintech.tams.report.system.chbprd.validator.ChbPrdBaseinfoValidator;
import com.joyintech.workflow.engine.dto.ProcessEntity;


@TableName("chb_prd_baseinfo")
@PageId("ChbPrdBaseinfo002")
@ValidLogic(targetClass = ChbPrdBaseinfoValidator.class, groups = { ValidateGroupEdit.class,
        ValidateGroupDelete.class })
public class ChbPrdBaseinfo001Dto extends ChbPrdBaseinfo implements ITableRow, ProcessEntity {

    private static final long serialVersionUID = -4563164887691600259L;
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
