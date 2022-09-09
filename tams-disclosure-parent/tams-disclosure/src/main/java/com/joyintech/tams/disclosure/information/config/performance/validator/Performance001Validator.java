package com.joyintech.tams.disclosure.information.config.performance.validator;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.disclosure.information.config.performance.dto.Performance001Dto;
import com.joyintech.tams.disclosure.information.config.performance.service.Performance001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Performance001Validator implements BeanValidator {
    @Autowired
    private Performance001Service performance001Service;

    @Override
    public List<ValidMessage> valid(Object value) {
        Performance001Dto dto = (Performance001Dto) value;
        Date platDate = DateUtil.platDateFromCache();
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();


        if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW) ||StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {
            //生效日截止日期要大于等于生效日
            if (DateUtil.compareDate(dto.getEffDate(), dto.getExpDate()) > 0) {
                resultList.add(new ValidMessage("业绩比较基准生效截止日期必须大于等于业绩比较基准生效日！"));
            }
        }

        if (StringUtil.isNotEmpty(dto.getProdId()) && dto.getProdId().length() != 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("prodId", dto.getProdId());
            if(StringUtil.isNotEmpty(dto.getEffDateO())){
                map.put("effDateO", DateUtil.format(dto.getEffDateO()));
            }else{
                map.put("effDateO", "");
            }
            if(StringUtil.isNotEmpty(dto.getExpDateO())){
                map.put("expDateO", DateUtil.format(dto.getExpDateO()));
            }else{
                map.put("expDateO", "");
            }
//        if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
//            param.put("excludeId",dto.getProdAssetId());
//        }
            List<Performance001Dto> performance001Dtos = performance001Service.selectPerformanceByProdId(map);
            List<Performance001Dto> worklowList = performance001Service.getWorklowData(dto.getProdId());
            if (performance001Dtos.size() > 0) {
                for (Performance001Dto Performance001Dto : performance001Dtos) {
                    if (!((DateUtil.compareDate(dto.getEffDate(), Performance001Dto.getEffDate()) < 0 && DateUtil.compareDate(dto.getExpDate(), Performance001Dto.getEffDate()) < 0)
                            || (DateUtil.compareDate(dto.getEffDate(), Performance001Dto.getExpDate()) > 0 && DateUtil.compareDate(dto.getExpDate(), Performance001Dto.getExpDate()) > 0))) {
                        resultList.add(new ValidMessage("子产品业绩比较基准区间生效时间不能重叠！" + "{" + DateUtil.format(Performance001Dto.getEffDate(), "yyyyMMdd")
                                + "-" + DateUtil.format(Performance001Dto.getExpDate(), "yyyyMMdd") + "}"));
                        break;
                    }
                }
            }
            if (worklowList.size() > 0) {
                for (Performance001Dto performance001Dto : worklowList) {
                    if (!((DateUtil.compareDate(dto.getEffDate(), performance001Dto.getEffDate()) < 0 && DateUtil.compareDate(dto.getExpDate(), performance001Dto.getEffDate()) < 0)
                            || (DateUtil.compareDate(dto.getEffDate(), performance001Dto.getExpDate()) > 0 && DateUtil.compareDate(dto.getExpDate(), performance001Dto.getExpDate()) > 0)
                    )) {
                        if (DateUtil.compareDate(dto.getEffDate(), performance001Dto.getEffDate()) != 0 && DateUtil.compareDate(dto.getExpDate(), performance001Dto.getExpDate()) != 0){
                            resultList.add(new ValidMessage("子产品业绩比较基准区间生效时间与代办数据有重叠！" + "{" + DateUtil.format(performance001Dto.getEffDate(), "yyyyMMdd")
                                    + "-" + DateUtil.format(performance001Dto.getExpDate(), "yyyyMMdd") + "}"));
                            break;
                        }
                    }
                }
            }

        }
        return resultList;
    }
}
