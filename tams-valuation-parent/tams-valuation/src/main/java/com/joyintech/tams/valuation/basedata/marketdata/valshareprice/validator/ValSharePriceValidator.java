/*
 * 文件名：ValSharePriceValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangpeng
 * 修改时间：2019年6月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.basedata.marketdata.valshareprice.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.valuation.basedata.marketdata.valshareprice.dto.ValSharePriceDto;
import com.joyintech.tams.valuation.basedata.marketdata.valshareprice.service.ValSharePriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 股票行情管理校验
 * 〈功能详细描述〉
 * @author wangpeng
 * @version 1.0 2019年6月28日
 * @see ValSharePriceValidator
 * @since 1.0
 */
@Component
public class ValSharePriceValidator implements BeanValidator{
    /**
     * 股票行情Service
     */
    @Autowired
    private ValSharePriceService ValSharePriceService;

    /**
     * 校验
     */
    @Override
    public List<ValidMessage> valid(Object value) {
    	ValSharePriceDto dto = (ValSharePriceDto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        if (WorkFlowUtil.isNotFromToDoList()) {
        	Map<String, Object> pageParam =new HashMap<>();
        	pageParam.put("sharesId",dto.getSharesId());
        	if(StringUtil.isEmpty(DateUtil.format(dto.getDealDate()))){
        		//日期为空  不需要提示主键问题
        		return resultList;
        	}else{
        		pageParam.put("dealDateString",DateUtil.format(dto.getDealDate()));
        	}
        	List<ValSharePriceDto> list = ValSharePriceService.selectAllList(pageParam);
        	if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
            	//新建
            	if (list != null && !list.isEmpty()) {
            		resultList.add(new ValidMessage("sharesId", getMessage(
                        "Val_SHAREPRICE_0002", getMessage("ValSharePrice001.sharesId") 
                                           , getMessage("ValSharePrice001.dealDate"))));

                }
            }
            if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {
            	//编辑
                if (list == null || list.isEmpty()) {
                	resultList.add(new ValidMessage("sharesId",
                        getMessage("Val_SHAREPRICE_0002", getMessage("ValSharePrice001.sharesId"),
                            getMessage("ValSharePrice001.dealDate"))));
                }
            }
        }
        return resultList;
    }

}