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

}
