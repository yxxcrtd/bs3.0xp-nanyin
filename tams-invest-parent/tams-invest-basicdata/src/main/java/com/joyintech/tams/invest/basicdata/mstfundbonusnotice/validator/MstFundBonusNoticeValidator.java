package com.joyintech.tams.invest.basicdata.mstfundbonusnotice.validator;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.basicdata.mstfundbonusnotice.dto.MstFundBonusNotice001Dto;
import com.joyintech.tams.invest.basicdata.mstfundbonusnotice.service.MstFundBonusNotice001Service;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname MstFundBonusNoticeValidator
 * @Description TODO
 * @author zhuhanlu 
 * @date 2020/8/11 17:27 
 * @version 1.0 
 */
@Component
public class MstFundBonusNoticeValidator implements BeanValidator {
    @Autowired
    private MstFundBonusNotice001Service mstFundBonusNotice001Service;
    @Override
    public List<ValidMessage> valid(Object value) {
        MstFundBonusNotice001Dto dto = (MstFundBonusNotice001Dto) value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        List<MstFundBonusNotice001Dto> list = new ArrayList<MstFundBonusNotice001Dto>();
        if (WorkFlowUtil.isNotFromToDoList()) {
            if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
                    Map<String, Object> pageParam = new HashMap<>();
                if (StringUtil.isEmpty(dto.getFundId())) {
                    return resultList;
                }
                else{
                    pageParam.put("fundId", dto.getFundId());
                }
                if (StringUtil.isEmpty(DateUtil.format(dto.getRegDate()))) {
                        return resultList;
                } else {
                        pageParam.put("regDate", DateUtil.format(dto.getRegDate()));
                    }
                    list.addAll(mstFundBonusNotice001Service.selectAllList(pageParam));
                    if (list.size()>0){
                        resultList.add(new ValidMessage("fundId", getMessage(
                                "MST_FUND_BONUS_NOTICE001", getMessage("MstFundBonusNotice001.fundId")
                                , getMessage("MstFundBonusNotice001.regDate"))));
                    }
            }



        }
        return resultList;
    }
}
