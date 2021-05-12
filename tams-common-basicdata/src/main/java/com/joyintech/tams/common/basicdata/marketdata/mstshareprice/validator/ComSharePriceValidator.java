/*
 * 文件名：MstSharePriceValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangpeng
 * 修改时间：2019年6月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.common.basicdata.marketdata.mstshareprice.validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.basicdata.marketdata.mstshareprice.dto.ComSharePriceDto;
import com.joyintech.tams.systemtools.service.CommonPlatformDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.basicdata.marketdata.mstshareprice.service.ComSharePriceService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 股票行情管理校验
 * 〈功能详细描述〉
 * @author wangpeng
 * @version 1.0 2019年6月28日
 * @see ComSharePriceValidator
 * @since 1.0
 */
@Component
public class ComSharePriceValidator implements BeanValidator{
    /**
     * 股票行情Service
     */
    @Autowired
    private ComSharePriceService comSharePriceService;

    /**
     * 校验
     */
    @Override
    public List<ValidMessage> valid(Object value) {
    	ComSharePriceDto dto = (ComSharePriceDto)value;
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
        	List<ComSharePriceDto> list = comSharePriceService.selectAllList(pageParam);
        	if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
            	//新建
            	if (list != null && !list.isEmpty()) {
            		resultList.add(new ValidMessage("sharesId", getMessage(
                        "MST_SHAREPRICE_0002", getMessage("MstSharePrice001.sharesId") 
                                           , getMessage("MstSharePrice001.dealDate"))));

                }
            }
            if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {
            	//编辑
                if (list == null || list.isEmpty()) {
                	resultList.add(new ValidMessage("sharesId",
                        getMessage("MST_SHAREPRICE_0002", getMessage("MstSharePrice001.sharesId"),
                            getMessage("MstSharePrice001.dealDate"))));
                }
            }
        }
        return resultList;
    }

}