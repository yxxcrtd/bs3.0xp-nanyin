/*
 * 文件名：TrdBondInvestTradeDtoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年11月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.portfolio.ptlfuturecashposi.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.portfolio.ptlfuturecashposi.dto.PtlFutureCashPosiDto;

/**
 * 组合未来头寸分析头寸查询check
 *
 * @author renping
 * @version 1.0 2019年11月09日
 * @see PtlFutureCashPosiDtoValidator
 * @since 1.0
 */
@Component
public class PtlFutureCashPosiDtoValidator implements BeanValidator {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        PtlFutureCashPosiDto inputDto = (PtlFutureCashPosiDto)value;
        List<ValidMessage> errorList = new ArrayList<>();
        // 展示维度_短期以外，日期必填
        if (StringUtil.isNotEmpty(inputDto.getShowDimension())
            && !StringUtil.equals(DictCodeListExt3.展示维度_短期.getValue(),
                inputDto.getShowDimension())) {
            if (inputDto.getCdate() == null) {
                ValidMessage validMessage = new ValidMessage("cdate",
                    getMessage("FW_E_0006", getMessage("PtlFutureCashPosi001.cdate")));
                errorList.add(validMessage);
            }
        }
        // 日期必须大于平台日期
        if (inputDto.getCdate() != null
            && inputDto.getCdate().compareTo(DateUtil.platDateFromCache()) <= 0) {
            ValidMessage validMessage = new ValidMessage("cdate",
                getMessage("BUSS_COM_E_0036", getMessage("PtlFutureCashPosi001.cdate"),
                    getMessage("PtlFutureCashPosi001.platDate")));
            errorList.add(validMessage);
        }
        return errorList;
    }

}
