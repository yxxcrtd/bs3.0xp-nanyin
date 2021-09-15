/*
 * 文件名：ChbFeedbackFileManagement002Service.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：徽银理财资管系统
 * 修改人：wanghai
 * 修改时间：2021年5月10日
 * 修改内容：新建
 * 系统名称：徽银理财资管系统
 */
 
package com.joyintech.tams.report.system.chbfeedbackfilemanagement.service;

import com.joyintech.background.component.common.pack.common.util.StringUtil;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.system.chbfeedbackfilemanagement.dto.ChbFeedbackFileManagement002Dto;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 中债系统报送反馈文件信息
 * 〈功能详细描述〉
  * @author wanghai
 * @version 1.0 2021年5月10日
 * @see ChbFeedbackFileManagement002Service
 * @since 1.0
 */
@Service
public class ChbFeedbackFileManagement002Service extends BaseService{
    
    /**
    * 取得匹配信息数据
    * @return
    */
    public ChbFeedbackFileManagement002Dto queryMatchInfo(Map<String, Object> param) {
        return simpleRestTemplate.postForObject(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/ChbFeedbackFileManagement/queryMatchInfo", param,
                ChbFeedbackFileManagement002Dto.class);
    }

    public AjaxResult fileHandle(ChbFeedbackFileManagement002Dto dto){
        AjaxResult ajaxResult = new AjaxResult();
        String result = simpleRestTemplate.postForObject(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/ChbFeedbackFileManagement/fileHandle", dto,
                String.class);
        if (StringUtil.isEmpty(result)){
            ajaxResult.setData("处理成功！");
        }
        else{
            ajaxResult.setMessage(result, MessageType.错误消息);
        }
        return ajaxResult;
    }

}
