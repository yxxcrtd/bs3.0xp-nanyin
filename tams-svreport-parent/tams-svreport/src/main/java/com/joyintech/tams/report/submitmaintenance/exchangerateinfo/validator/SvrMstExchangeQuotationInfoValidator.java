package com.joyintech.tams.report.submitmaintenance.exchangerateinfo.validator;

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
import com.joyintech.tams.report.submitmaintenance.exchangerateinfo.dto.SvrMstExchangeQuotationInfoDto;
import com.joyintech.tams.report.submitmaintenance.exchangerateinfo.service.SvrMstExchangeQuotationInfoService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 汇率行情信息验证 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author wangzhao
 * @version 1.0 2018年8月9日
 * @see SvrMstExchangeQuotationInfoValidator
 * @since 1.0
 */
@Transactional
@Component
public class SvrMstExchangeQuotationInfoValidator implements BeanValidator {

    /**
     * 汇率行情service
     */
    @Autowired
    private SvrMstExchangeQuotationInfoService mstExchangeQuotationInfoService;

    /**
     * 增删改之前校验
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        SvrMstExchangeQuotationInfoDto dto = (SvrMstExchangeQuotationInfoDto)value;
        ArrayList<ValidMessage> errorList = new ArrayList<>();
        Date sysDate = DateUtil.platDateFromCache();
        if (WorkFlowUtil.isNotFromToDoList()) {
            if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
                // 新建，生效日小于平台日期时的判断
                Date tradeDate = dto.getTradeDate();
                if (tradeDate != null) {
//                    if (DateUtil.compareDate(tradeDate, sysDate) < 0) {
//                        errorList.add(new ValidMessage("tradeDate",
//                            getMessage("BUSS_COM_E_0004",
//                                getMessage("MstExchangeQuotationInfo001.tradeDate"),
//                                getMessage("SysCommon.platDate"))));
//                    }
                    String exchargeCode = dto.getExchargeCode();
                    Map<String, Object> pageParam = new HashMap<>();
                    pageParam.put("exchargeCode", exchargeCode);
                    pageParam.put("tradeDate", DateUtil.format(tradeDate));
                    List<SvrMstExchangeQuotationInfoDto> list = mstExchangeQuotationInfoService.selectAllList(
                        pageParam);
                    if (list != null && !list.isEmpty()) {
                        errorList.add(new ValidMessage("exchargeCode", getMessage(
                            "BUSS_COM_E_0006",
                            getMessage("MstExchangeQuotationInfo001.exchargeCode") + ":"
                                               + getMessage(
                                                   "MstExchangeQuotationInfo001.tradeDate"))));
                    }
                }
            }
            if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {
                // 编辑时生效日小于平台日期时
                Date tradeDate = dto.getTradeDate();
//                if (DateUtil.compareDate(tradeDate, sysDate) < 0) {
//                    errorList.add(new ValidMessage("tradeDate",
//                        getMessage("BUSS_COM_E_0018",
//                            getMessage("MstExchangeQuotationInfo001.tradeDate"),
//                            getMessage("SysCommon.platDate"))));
//                }
            }

            if (StringUtil.equals(dto.getOperateType(), "delete")) {
                if (dto.getSelectedItem() == null || dto.getSelectedItem().size() == 0) {
                    errorList.add(new ValidMessage("table", getMessage("COMMON_E_0002")));
                }
                // 判断生效日小于平台日期时，不能删除
                List<SvrMstExchangeQuotationInfoDto> items = dto.getSelectedItem();
                for (SvrMstExchangeQuotationInfoDto mstExchangeQuotationInfoDto : items) {
                    Date tradeDate = mstExchangeQuotationInfoDto.getTradeDate();
//                    if (DateUtil.compareDate(tradeDate, sysDate) < 0) {
//                        errorList.add(new ValidMessage("tradeDate",
//                            getMessage("BUSS_COM_E_0015",
//                                getMessage("MstExchangeQuotationInfo001.tradeDate"),
//                                getMessage("SysCommon.platDate"))));
//                    }
                }
            }
        }
        return errorList;
    }
}
