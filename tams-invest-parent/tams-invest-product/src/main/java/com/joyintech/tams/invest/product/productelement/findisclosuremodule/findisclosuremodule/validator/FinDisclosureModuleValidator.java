/*
 * 文件名：FinDisclosureModuleValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productelement.findisclosuremodule.findisclosuremodule.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.framework.listener.FileAttachmentProcess;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstFileCommonService;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.invest.common.dto.MstFileOutputParameter;
import com.joyintech.tams.invest.product.productelement.findisclosuremodule.findisclosuremodule.dto.FinDisclosureModuleDto;
import com.joyintech.tams.invest.product.productelement.findisclosuremodule.findisclosuremodule.service.FinDisclosureModuleService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author wangzehui
 * @version 1.0 2018年8月24日
 * @see FinDisclosureModuleValidator
 * @since 1.0
 */
@Component
public class FinDisclosureModuleValidator implements BeanValidator {

    /**
     * 定义mapper对象
     */
    @Autowired(required = false)
    private FinDisclosureModuleService finDisclosureModuleService;

    /**
     * 定义service对象
     */
    @Autowired
    private MstFileCommonService mstFileCommonService;
    /**
     * 文件附件处理接口
     */
    @Autowired
    private FileAttachmentProcess fileAttachmentProcess;
    /**
     * mstWorkflowCommonService工作流组件
     */
    @Autowired(required=false)
    private MstWorkflowCommonService mstWorkflowCommonService;
    /**
     * 校验
     * 
     * @param value 页面数据
     * @return 校验结果
     */
    public List<ValidMessage> valid(Object value) {
        FinDisclosureModuleDto dto = (FinDisclosureModuleDto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        if (StringUtil.equals(dto.getOperateType(), "delete")) {
            List<FinDisclosureModuleDto> list = dto.getSelectedItem();
            // 判断是否选中数据
            for (FinDisclosureModuleDto eneity : list) {
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("revealFileId", eneity.getRevealFileId());
                FinDisclosureModuleDto param = finDisclosureModuleService.selectByRevealFileId(map);
                //判断数据是否被删除
                if(param == null){
                    resultList.add(new ValidMessage(
                        this.getMessage("BUSS_COM_E_0022")));
                }
                //判断数据是否被使用
                if (finDisclosureModuleService.isUsed(eneity.getRevealFileId()) != null) {
                    resultList.add(new ValidMessage(
                        this.getMessage("FIN_PROD_E_0004", eneity.getRevealFileId(),
                            finDisclosureModuleService.isUsed(eneity.getRevealFileId()))));
                }
            }

        }
        else {
            MstFileOutputParameter param = mstFileCommonService.getFileInfoByFileName(dto.getFileName());
            if(dto.getFileName() != null && param != null ){
                List<SysUploadFileInfo> uploadFileList = param.getUploadFileList();
                if(uploadFileList != null && uploadFileList.size()>0){
                    for(SysUploadFileInfo e:uploadFileList){
                        Map<String,Object> map = new HashMap<String,Object>();
                        map.put("revealFileId", e.getBusinessId());
                        FinDisclosureModuleDto d = finDisclosureModuleService.selectByRevealFileId(map);
                        if(d != null){
                            fileAttachmentProcess.remove(dto.getFileId());
                            resultList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0003")));
                        }
                    }
                }
                
            }
            if(dto.getFileName() == null){
                resultList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0062")));
            }
            if (!WorkFlowUtil.isNotFromToDoList()) {
                if(dto != null && dto.getWorkflowId() != null){
                  //判断流程中是否存在相同的数据
                    WflValidInputParameter parameter = new WflValidInputParameter();
                    parameter.setWorkflowId(dto.getWorkflowId());
                    parameter.setBusinessDataPk1(dto.getFileName());
                    parameter.setPageId("FinDisclosureModule002");
                    if(null != mstWorkflowCommonService && 
                        mstWorkflowCommonService.isInWflProcess(parameter)){
                        resultList.add(
                            new ValidMessage("已存在流程,不能操作:key:"+dto.getFileName()));
                    }
                }
                
            }
        }
        return resultList;
    }
}
