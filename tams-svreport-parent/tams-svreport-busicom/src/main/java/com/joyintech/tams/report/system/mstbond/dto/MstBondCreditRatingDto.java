package com.joyintech.tams.report.system.mstbond.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.ChbMstCounterParty;
import com.joyintech.tams.common.base.entity.MstBondCreditRating;
import com.joyintech.tams.report.common.base.annotate.TableName;
import com.joyintech.tams.report.system.prorate.validator.DisPrdFeeRateValidator;
import com.joyintech.workflow.engine.dto.ProcessEntity;

@TableName("MST_BOND_CREDIT_RATING")
@PageId("MstBondCreditRating002")
@ValidLogic(targetClass = DisPrdFeeRateValidator.class, groups = { ValidateGroupEdit.class,
        ValidateGroupDelete.class })
public class MstBondCreditRatingDto extends MstBondCreditRating implements ITableRow, ProcessEntity {

}
