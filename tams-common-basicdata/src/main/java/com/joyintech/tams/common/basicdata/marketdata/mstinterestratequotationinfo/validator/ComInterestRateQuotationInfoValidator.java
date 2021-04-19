package com.joyintech.tams.common.basicdata.marketdata.mstinterestratequotationinfo.validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.basicdata.marketdata.mstinterestratequotationinfo.dto.ComInterestRateQuotationInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.basicdata.marketdata.mstinterestratequotationinfo.service.ComInterestRateQuotationInfoService;

/**
 * 利率行情的check方法
 * 
 * @author qiangchengxi
 * @version 1.0 2018年8月20日
 * @see ComInterestRateQuotationInfoValidator
 * @since 1.0
 */
@Component
public class ComInterestRateQuotationInfoValidator implements BeanValidator {

    /**
     * 注入Mapper
     */
    @Autowired
    private ComInterestRateQuotationInfoService comInterestRateQuotationInfoService;

//    @Autowired(required = false)
//    private IPlatformDate iPlatformDate;

    /**
     * 重写valid方法 {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        ComInterestRateQuotationInfoDto dto = (ComInterestRateQuotationInfoDto)value;
        ArrayList<ValidMessage> errorList = new ArrayList<>();// 生成错误集合
        Date sysDate = DateUtil.platDateFromCache();// 获取平台日期
        // if (WorkFlowUtil.isNotFromToDoList()) {
        if (StringUtil.equals(dto.getOperateType(), "delete")) {
            // 判断是否获取到删除的项目
            if (dto.getSelectedItem() != null || dto.getSelectedItem().size() > 0) {
                // 遍历选择的项目
                for (ComInterestRateQuotationInfoDto cur : dto.getSelectedItem()) {
                    // 判断发布日是否小于平台时间
                    if (cur.getEffDate().compareTo(sysDate) < 0) {
                        // 添加错误信息
                        errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0015",
                            getMessage("ComInterestRateQuotationInfo002.effDate"),
                            getMessage("ComInterestRateQuotationInfo002.date"))));
                    }
                }
            }
        }
        else if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
            // 判断数据库是否有此数据
            Map<String, Object> map = new HashMap<>();
            map.put("intRateId", dto.getIntRateId());
            map.put("effDate", DateUtil.format(dto.getEffDate()));
            map.put("ccy", dto.getCcy());
            if (dto.getEffDate() != null && dto.getRelaeseDate() != null
                && dto.getEffDate().compareTo(dto.getRelaeseDate()) < 0) {
                errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0004",
                    getMessage("ComInterestRateQuotationInfo002.effDate"),
                    getMessage("ComInterestRateQuotationInfo002.relaeseDate"))));
            }
            if (!dto.getIntRateId().isEmpty() && dto.getEffDate() != null) {
                if (comInterestRateQuotationInfoService.selectByPrimaryKey(map) != null) {
                    errorList.add(new ValidMessage(getMessage(
                        "BUSS_COM_E_0006",
                        getMessage("ComInterestRateQuotationInfo002.intRateId") + ":"
                                           + dto.getIntRateId() + ","
                                           + getMessage("ComInterestRateQuotationInfo002.effDate")
                                           + ":" + DateUtil.format(dto.getEffDate()) + ","
                                           + getMessage("ComInterestRateQuotationInfo001.ccy")
                                           + ":" + dto.getCcyName()

                    )));
                }
                else if (dto.getEffDate().compareTo(sysDate) < 0) {
                    // 添加错误信息
                    errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0004",
                        getMessage("ComInterestRateQuotationInfo002.effDate"),
                        getMessage("ComInterestRateQuotationInfo002.date"))));
                }
            }
        }
        else {
            if (dto.getEffDate().compareTo(sysDate) < 0) {
                errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0018",
                    getMessage("ComInterestRateQuotationInfo002.effDate"),
                    getMessage("ComInterestRateQuotationInfo002.date"))));
            }
            if (dto.getEffDate() != null && dto.getRelaeseDate() != null
                && dto.getEffDate().compareTo(dto.getRelaeseDate()) < 0) {
                errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0004",
                    getMessage("ComInterestRateQuotationInfo002.effDate"),
                    getMessage("ComInterestRateQuotationInfo002.relaeseDate"))));
            }
        }
        
        if(StringUtil.equals(dto.getOperateType(), "save") || StringUtil.equals(dto.getOperateType(), "submit")){
            if (dto.getRelaeseDate() != null
                && DateUtil.compareDate(dto.getRelaeseDate(), sysDate) < 0) {
                errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0004",
                    getMessage("ComInterestRateQuotationInfo002.relaeseDate"),
                    getMessage("ComInterestRateQuotationInfo002.date"))));
            }
        }
        // }
        return errorList;
    }

}
