package com.joyintech.tams.valuation.component.asset.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinRateInfo;
import com.joyintech.tams.common.base.entity.MstBondCreditRating;
import com.joyintech.tams.common.base.entity.PaFinCashCalcRule;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.component.asset.dto.FinInterestFactorDto;
import org.springframework.stereotype.Service;

/**
 * @ClassName FinInterestFactorService
 * @Description: 计息要素 Service
 * @Author yanya
 * @Date 2021/3/4
 * @Version V1.0
 **/
@Service
public class FinInterestFactorService extends CommonExportService<FinInterestFactorDto> {

    /**
     * 共同画面返回值
     * @param dto
     * @return
     */
    public FinInterestFactorDto getFinInterestFactorDto(FinInterestFactorDto dto){
        //FinRateInfo--现金流利率信息   FinCashCalcRule--现金流计算规则  PaFinCashCalcRule--现金流计算规则扩展
        FinRateInfo finRateInfo = new FinRateInfo();
        FinCashCalcRule finCashCalcRule = new FinCashCalcRule();
        PaFinCashCalcRule paFinCashCalcRule = new PaFinCashCalcRule();
        BeanUtil.copyPropertiesIgnoreNull(finRateInfo, dto);
        BeanUtil.copyPropertiesIgnoreNull(finCashCalcRule, dto);
        BeanUtil.copyPropertiesIgnoreNull(paFinCashCalcRule,dto);
        dto.setFinRateInfo(finRateInfo);dto.setFinCashCalcRule(finCashCalcRule);
        dto.setPaFinCashCalcRule(paFinCashCalcRule);
        return dto;
    }

    /**
     * 业务组件画面初始化
     * @param finProductId
     * @param vdate
     * @return
     */
    public FinInterestFactorDto getFinInterestFactorByProductId(String finprodId, String vdate) {
        return restTemplate.getForObject(ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME
                        + "/FinInterestFactor/getFinInterestFactorByProductId?finprodId="
                        + finprodId+"&vdate="+vdate+"&cashType="+ DictCodeListExt1.现金流二级类型_利息.getValue(),
                FinInterestFactorDto.class);
    }


}
