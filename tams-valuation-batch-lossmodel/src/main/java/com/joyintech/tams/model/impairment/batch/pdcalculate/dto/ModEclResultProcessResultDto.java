package com.joyintech.tams.model.impairment.batch.pdcalculate.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.tams.common.base.entity.ModEclResultProcess;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description:处理过的交易信息Dto
 * @auther: yuzj
 * @date: 2021/7/15
 * @param:
 * @return:
 */
public class ModEclResultProcessResultDto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 减值结果过程表
     */
    private ModEclResultProcess modEclResultProcess;
}
