package com.joyintech.tams.common.basicdata.marketdata.fundmarketinfo.validator;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.basicdata.marketdata.fundmarketinfo.dto.ComFundMarketInfo001dto;
import com.joyintech.tams.common.basicdata.marketdata.fundmarketinfo.service.ComFundMarketInfo001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author mefiaxin
 *
 */
@Component
public class ComFundMarketInfoValidator implements BeanValidator {

    @Autowired
    private ComFundMarketInfo001Service comFundMarketInfo001Service;
    
    @Override
    public List<ValidMessage> valid(Object value) {
        ComFundMarketInfo001dto dto = (ComFundMarketInfo001dto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        if (StringUtil.equals(dto.getOperateType(), "delete")) {
            // 删除check
            List<ComFundMarketInfo001dto> list = dto.getSelectedItem();
            for (ComFundMarketInfo001dto mst : list) {
                if(StringUtil.isNotEmpty(mst.getProcessState())){
                    resultList.add(new ValidMessage("流程中的数据不能删除!"));
                }
            }

        }
        else {
            if (StringUtil.equals(dto.getOperateType(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {
                if(StringUtil.isNotEmpty(dto.getProcessState())){
                    resultList.add(new ValidMessage("流程中的数据不能修改!"));
                }
            }
            else {
            }

        }
        return resultList;
    }

}
