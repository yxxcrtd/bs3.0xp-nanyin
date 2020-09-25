/*
 * 文件名：ValNetValueConfirmParamValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年9月28日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.disclosure.information.reveal.reportconfirm.validator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.disclosure.information.reveal.reportconfirm.dto.ReportConfirmManage001Dto;
import com.joyintech.service.disclosure.information.reveal.reportconfirm.dto.ReportConfirmManageParamDto;
import com.joyintech.service.disclosure.information.reveal.reportconfirm.service.ReportConfirmManage001Service;
import com.joyintech.tams.disclosure.common.common.dto.WflValidInputParameter;
import com.joyintech.tams.disclosure.common.common.service.MstWorkflowCommonService;

/**
 * 净值下发确认 - 确认和下发操作流程验证
 * 
 * @author yangjian
 * @version 1.0 2018年9月28日
 * @see ValNetValueConfirmParamValidator
 * @since 1.0
 */
@Component(value="serviceDisValNetValueConfirmParamValidator")
public class ValNetValueConfirmParamValidator implements BeanValidator {
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ValNetValueConfirmParamValidator.class);
    
    @Autowired
    private ReportConfirmManage001Service valReportConfirmManage001Service;

    /**
     * 工作流CommonServic
     */
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;
    
    @Override
    public List<ValidMessage> valid(Object value) {
        
        //获取传递值
        ReportConfirmManageParamDto paDto = (ReportConfirmManageParamDto)value;
        
        //获取页面勾选的数据
        String data=paDto.getData();
        
        //转换为实体类
        List<ReportConfirmManage001Dto> listdto = new ArrayList<>();
        try {
            listdto = JsonUtils.json2List(data, ReportConfirmManage001Dto.class);
        }
        catch (IOException e) {
        	logger.error("",e);
        }
        
        
        //定义返回值：验证消息
        List<ValidMessage> validMessageList = new ArrayList<ValidMessage>();
        
        //数据验证判断
        if(listdto!=null){
            WflValidInputParameter wflValidInputParameter;
            for(ReportConfirmManage001Dto nowDto : listdto){
                //判断当前流程是否存在
//                if(valReportConfirmManage001Service.checkBusinessDataInFlow(nowDto)){
                
                //判断是否在流程中 zhangyf
                wflValidInputParameter  = new WflValidInputParameter();
                wflValidInputParameter.setPageId(paDto.getPageId());
                wflValidInputParameter.setBusinessDataPk1(nowDto.getSeqno());
                if(StringUtil.isNotEmpty(nowDto.getWorkflowId())){
                    wflValidInputParameter.setWorkflowId(nowDto.getWorkflowId());
                }
                if(mstWorkflowCommonService.isInWflProcess(wflValidInputParameter)){
                    //流程存在情况
                    validMessageList.add(new ValidMessage(getMessage("COMMON_E_0021","标识",nowDto.getProdid())));
                }
            }
        }
             
        return validMessageList;
    }
   
}
