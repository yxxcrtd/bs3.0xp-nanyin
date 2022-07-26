package com.joyintech.tams.disclosure.information.config.repprodinfo.dto;

import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.tams.common.base.entity.RepProdBasicChange;
import com.joyintech.workflow.engine.dto.ProcessEntity;

// @PageId("ProdInfo001")
/*@ValidLogic(targetClass = ProdInfoValidator.class, groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class})*/
public class ProdBasicChangeDto extends RepProdBasicChange implements ITableRow, ProcessEntity {

}