package com.joyintech.tams.invest.basicdata.mstsharebonusnotice.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.basicdata.mstsharebonusnotice.dto.MstShareBonusNotice001Dto;
import com.joyintech.tams.invest.basicdata.mstsharebonusnotice.service.MstShareBonusNotice001Service;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * @Classname MstShareBonusNoticeValidator
 * @Description TODO
 * @author zhuhanlu 
 * @date 2020/8/11 11:01 
 * @version 1.0 
 */
@Component
public class MstShareBonusNoticeValidator implements BeanValidator {

    @Autowired
    private  MstShareBonusNotice001Service mstShareBonusNotice001Service;
    @Override
    public List<ValidMessage> valid(Object value) {
        MstShareBonusNotice001Dto dto = (MstShareBonusNotice001Dto) value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        List<MstShareBonusNotice001Dto> list = new ArrayList<MstShareBonusNotice001Dto>();
        if (WorkFlowUtil.isNotFromToDoList()) {
            if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW) ) {
                Map<String, Object> pageParam = new HashMap<>();
                if (StringUtil.isEmpty(dto.getSharesId())) {
                    return resultList;
                }
                else{
                    pageParam.put("sharesId", dto.getSharesId());
                }
                if (StringUtil.isEmpty(DateUtil.format(dto.getRegDate()))) {
                    return resultList;
                } else {
                    pageParam.put("regDate", DateUtil.format(dto.getRegDate()));
                }
                list.addAll(mstShareBonusNotice001Service.selectAllList(pageParam));
                if (list.size()>0){
                    resultList.add(new ValidMessage("sharesId", getMessage(
                            "MST_SHARE_BONUS_NOTICE001", getMessage("MstShareBonusNotice001.sharesId")
                            , getMessage("MstShareBonusNotice001.regDate"))));
                }

            }

        }
        return resultList;
    }
}
