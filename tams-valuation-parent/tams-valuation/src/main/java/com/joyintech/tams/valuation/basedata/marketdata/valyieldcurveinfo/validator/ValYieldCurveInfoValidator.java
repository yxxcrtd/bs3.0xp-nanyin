package com.joyintech.tams.valuation.basedata.marketdata.valyieldcurveinfo.validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.valuation.basedata.marketdata.valyieldcurveinfo.dto.ValYieldCurveInfoDto;
import com.joyintech.tams.valuation.basedata.marketdata.valyieldcurveinfo.service.ValYieldCurveInfoService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 收益率曲线信息验证 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author wangzhao
 * @version 1.0 2018年8月9日
 * @see ValYieldCurveInfoValidator
 * @since 1.0
 */
@Transactional
@Component
public class ValYieldCurveInfoValidator implements BeanValidator {
    /**
     * 
     */
    @Autowired
    private ValYieldCurveInfoService mstYieldCurveInfoService;

    @Override
    public List<ValidMessage> valid(Object value) {
        ValYieldCurveInfoDto dto = (ValYieldCurveInfoDto)value;
        ArrayList<ValidMessage> errorList = new ArrayList<>();
        Date sysDate = DateUtil.platDateFromCache();
        if (WorkFlowUtil.isNotFromToDoList()) {
            if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
                // 新建时曲线日期小于平台日期时
                Date curveDate = dto.getCurveDate();
                if (curveDate != null) {
                    if (DateUtil.compareDate(curveDate, sysDate) < 0) {
                        errorList.add(new ValidMessage("curveDate",
                            getMessage("BUSS_COM_E_0004",
                                getMessage("ValYieldCurveInfo001.curveDate"),
                                getMessage("ValYieldCurveInfo001.sysDate"))));
                    }
                    String curveCode = dto.getCurveCode();
                    Map<String, Object> pageParam = new HashMap<>();
                    pageParam.put("curveCode", curveCode);
                    pageParam.put("curveDate", DateUtil.format(curveDate));
                    pageParam.put("curveType", dto.getCurveType());
                    List<ValYieldCurveInfoDto> list = mstYieldCurveInfoService.selectAllList(
                        pageParam);
                    if (list != null && !list.isEmpty()) {
                        errorList.add(new ValidMessage("curveCode", getMessage(
                            "BUSS_COM_E_0006", getMessage("ValYieldCurveInfo001.curveName") + ":"
                                               + getMessage("ValYieldCurveInfo001.curveDate")+":"+getMessage("ValYieldCurveInfo001.curveType"))));

                    }
                }
            }
            if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {
                // 点击编辑按钮，判断曲线日期小于平台日期
                Date curveDate = dto.getCurveDate();
                if (DateUtil.compareDate(curveDate, sysDate) < 0) {
                    errorList.add(new ValidMessage("curveDate",
                        getMessage("BUSS_COM_E_0018", getMessage("ValYieldCurveInfo001.curveDate"),
                            getMessage("ValYieldCurveInfo001.sysDate"))));
                }
            }

            if (StringUtil.equals(dto.getOperateType(), "delete")) {
                // 点击删除操作时的判断
                if (dto.getSelectedItem() == null || dto.getSelectedItem().size() == 0) {
                    errorList.add(new ValidMessage("table", getMessage("COMMON_E_0002")));
                }
                // 判断曲线日期小于平台日期时，不能删除
                List<ValYieldCurveInfoDto> items = dto.getSelectedItem();
                for (ValYieldCurveInfoDto mstYieldCurveInfoDto : items) {
                    Date curveDate = mstYieldCurveInfoDto.getCurveDate();
                    if (DateUtil.compareDate(curveDate, sysDate) < 0) {
                        errorList.add(new ValidMessage("curveDate",
                            getMessage("BUSS_COM_E_0015",
                                getMessage("ValYieldCurveInfo001.curveDate"),
                                getMessage("ValYieldCurveInfo001.sysDate"))));
                    }
                }
            }
        }
        return errorList;
    }
}
