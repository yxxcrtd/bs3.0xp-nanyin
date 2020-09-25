package com.joyintech.tams.disclosure.information.config.dispoiquery.validator;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.disclosure.information.config.dispoiquery.dao.DisPoiQuery001Mapper;
import com.joyintech.tams.disclosure.information.config.dispoiquery.dto.DisPoiQuery001Dto;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * poi查询sql校验
 */
@Component
public class DisPoiQuery001Validator implements BeanValidator {

    @Autowired
    private DisPoiQuery001Mapper disPoiQuery001Mapper;

    @Override
    public List<ValidMessage> valid(Object value) {
        DisPoiQuery001Dto dto = (DisPoiQuery001Dto) value;
        List<ValidMessage> errorList = new ArrayList<>();
        if (StringUtil.equals(dto.getOperateType(), "submit") || StringUtil.equals(dto.getOperateType(), "edit")) {
            //必填验证
            dataCheck(dto, errorList);
            lengthCheck(dto, errorList);
        }
        return errorList;
    }


    /**
     * 保存时必填项判断
     *
     * @param dto
     */
    private void dataCheck(DisPoiQuery001Dto dto, List<ValidMessage> errorList) {
        if (StringUtils.isBlank(dto.getPoiType())) {
            errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
                    this.getMessage("DisPoiQuery002.poiType"))));
        }
        if (StringUtil.isBlank(dto.getPoiId())) {
            errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
                    this.getMessage("DisPoiQuery002.poiId"))));
        }
        if (StringUtil.isBlank(dto.getPoiOrder())) {
            errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
                    this.getMessage("DisPoiQuery002.poiOrder"))));
        }
        if (StringUtil.isBlank(dto.getPoiSql())) {
            errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
                    this.getMessage("DisPoiQuery002.poiSql"))));
        }
    }

    /**
     * 字段长度判断
     *
     * @param dto
     */
    private void lengthCheck(DisPoiQuery001Dto dto, List<ValidMessage> errorList) {
        if (StringUtil.isNotBlank(dto.getPoiOrder())) {
            if (dto.getPoiOrder().length() > 200) {
                errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0002",
                        this.getMessage("DisPoiQuery002.poiOrder"), String.valueOf(200))));
            }
        }
        if (StringUtil.isNotBlank(dto.getPoiSql())) {
            if (dto.getPoiSql().length() > 4000) {
                errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0002",
                        this.getMessage("DisPoiQuery002.poiSql"), String.valueOf(4000))));
            }
        }
    }
}
