package com.joyintech.tams.invest.period.trdbondduration.dto;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.period.trdbondduration.SecPositionExerciseInfoDto;

/**
 * @program: 2.1.2
 * @description:
 * @author: 强成西
 * @create: 2020-01-19 14:44
 **/
public class SecPositionExerciseInfo003Dto extends SecPositionExerciseInfoDto {

    public String getInvAimString() {
        if (StringUtil.isNotEmpty(super.getInvAim())) {
            return SysDataUtil.getNameFromDataDict(DictCode.投资目的, super.getInvAim());
        }
        return super.getInvAim();
    }
}
