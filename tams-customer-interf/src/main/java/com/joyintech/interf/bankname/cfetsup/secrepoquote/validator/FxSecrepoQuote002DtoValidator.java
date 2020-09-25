/*
 * 文件名：FinNetProductDurationDtoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zqz
 * 修改时间：2019年1月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.interf.bankname.cfetsup.secrepoquote.validator;

import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.interf.bankname.cfetsup.secrepoquote.dao.FxSecrepoQuoteMapper;
import com.joyintech.interf.bankname.cfetsup.secrepoquote.dto.FxSecrepoQuote002Dto;
import com.joyintech.interf.bankname.cfetsup.secrepoquote.entity.FxSecrepoQuote;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 质押式回购报价校验
 * @author zcf
 * @version 1.0
 * @date 2020-01-09
 */
@Component
public class FxSecrepoQuote002DtoValidator implements BeanValidator{

    @Autowired
    private FxSecrepoQuoteMapper fxSecrepoQuoteMapper;
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        FxSecrepoQuote002Dto  dto = (FxSecrepoQuote002Dto ) value;
        ArrayList<ValidMessage> errorList = new ArrayList<>();
        //委托编号
        if (StringUtils.isEmpty(dto.getTrustid())) {
            errorList.add(new ValidMessage("委托编号不能为空！"));
            return errorList;
        }
        //组合代码
        if (StringUtils.isEmpty(dto.getPortfolioId())) {
            errorList.add(new ValidMessage("组合代码不能为空！"));
            return errorList;
        }
        if(!StringUtils.isEmpty(dto.getSeqno())){
             FxSecrepoQuote fx = fxSecrepoQuoteMapper.getObjectBySeqno(dto.getSeqno());
            if(null == fx ){
                errorList.add(new ValidMessage("该数据状态发生变化，不能修改报价，请重新查询！"));
                return errorList;
            }
        }
        if(dto.getOperateType2().equalsIgnoreCase("3")){
            //回复报价
            if(StringUtils.isEmpty(dto.getReplyType())){
                errorList.add(new ValidMessage("回复类别不能为空！"));
                return errorList;
            }
        }

        return errorList;
    }

}
