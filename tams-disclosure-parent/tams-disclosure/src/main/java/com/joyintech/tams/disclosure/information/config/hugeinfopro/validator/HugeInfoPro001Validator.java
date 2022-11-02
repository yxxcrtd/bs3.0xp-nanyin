package com.joyintech.tams.disclosure.information.config.hugeinfopro.validator;


import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.disclosure.information.config.hugeinfopro.dto.HugeInfoPro001Dto;
import com.joyintech.tams.disclosure.information.config.hugeinfopro.service.HugeInfoPro001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 产品巨额赎回信息校验
 * @author zhoubin
 */
@Component
public class HugeInfoPro001Validator implements BeanValidator {
    @Autowired
    private HugeInfoPro001Service hugeInfoPro001Service;


    @Override
    public List<ValidMessage> valid(Object value) {
        HugeInfoPro001Dto dto = (HugeInfoPro001Dto) value;
        String triggerRemark = dto.getTriggerRemark();
        String rejectRemark = dto.getRejectRemark();
        List<ValidMessage> errorList = new ArrayList<>();
        //触发的情形说明和拒绝赎回说明是否超长
        if (triggerRemark!=null && triggerRemark.length() > 500) {
            errorList.add(new ValidMessage("triggerRemark", "触发的情形说明文字不能查过500字符"));
        }
        if (rejectRemark!=null && rejectRemark.length() > 500) {
            errorList.add(new ValidMessage("rejectRemark", "拒绝赎回说明不能查过500字符"));
        }
        if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
            if (StringUtil.isNotEmpty(dto.getProdId()) && dto.getProdId().length() != 0) {
                Map<String, Object> map = new HashMap<>();
                map.put("prodId", dto.getProdId());
                if (StringUtil.isNotEmpty(dto.getRptIssueDate())) {
                    map.put("rptIssueDateStr", DateUtil.format(dto.getRptIssueDate()));
                } else {
                    map.put("rptIssueDateStr", "");
                }

                List<HugeInfoPro001Dto> HugeInfoPro001Dtos = hugeInfoPro001Service.selectHugeInfoByProdId(map);
                if (HugeInfoPro001Dtos.size() > 0) {
                    for (HugeInfoPro001Dto hugeInfoPro001Dto : HugeInfoPro001Dtos) {
                        if (DateUtil.compareDate(dto.getRptIssueDate(), hugeInfoPro001Dto.getRptIssueDate()) == 0) {
                            errorList.add(new ValidMessage("子产品代码+报告发布日期数据已重复，请重新输入！"));
                            break;
                        }
                    }
                }
            }
        }

        return errorList;
    }

}
