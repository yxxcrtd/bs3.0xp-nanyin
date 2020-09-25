/*
 * 文件名：FinProjectTargetAsset003Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年11月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.asset.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.listener.FileAttachmentProcess;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.asset.project.dto.FinProjectTargetAssetCmsDto;
import com.joyintech.tams.invest.asset.project.service.FinProjectTargetAssetService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 *  项目类资产CMS信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月21日
 * @see FinProjectTargetAsset003Controller
 * @since 1.0
 */
@Controller
@PageId("FinProjectTargetAsset003")
@RequestMapping("/invest/asset/project/FinProjectTargetAsset003")
public class FinProjectTargetAsset003Controller extends BaseController{

    @Autowired
    private FinProjectTargetAssetService finProjectTargetAssetService;
    
    /**
     * FileAttachmentProcess
     */
    @Autowired(required = false)
    private FileAttachmentProcess fileAttachmentProcess;
    
    @Override
    public String init(Model model){
        /*if(StringUtil.isNotEmpty(finprodId)){
            List<PaFinProductAssetAttach>  list = finProjectTargetAssetService.getBusinessIdByFinprodId(finprodId);
            String assetAttachNo1 = StringUtil.EMPTY;String assetAttachNo2 = StringUtil.EMPTY;
            if(null!=list&&!list.isEmpty()){
                for(PaFinProductAssetAttach entity:list){
                    if(StringUtil.equals(entity.getAssetAttachType(), DictCodeList.资产附件类型_批复文件.getValue())){
                        assetAttachNo1 = entity.getAssetAttachNo();
                    }
                    if(StringUtil.equals(entity.getAssetAttachType(), DictCodeList.资产附件类型_CMS批复文件.getValue())){
                        assetAttachNo2 = entity.getAssetAttachNo();
                    }
                }
            }*/
        String assetAttachNo1 = getParam("assetAttachNo1");String assetAttachNo2 = getParam("assetAttachNo2");
        if(null != fileAttachmentProcess && StringUtil.isNotEmpty(assetAttachNo1)) {
            List<UploadFile> files = fileAttachmentProcess.fetchByBusinessCode(assetAttachNo1);
            model.addAttribute("attachmentFiles1", JsonUtils.object2Json(files));
        }
        if(null != fileAttachmentProcess && StringUtil.isNotEmpty(assetAttachNo2)) {
            List<UploadFile> files = fileAttachmentProcess.fetchByBusinessCode(assetAttachNo2);
            model.addAttribute("attachmentFiles2", JsonUtils.object2Json(files));
        }
        model.addAttribute("YES", DictCodeList.是否_是.getValue());
        model.addAttribute("cashOutNo", this.getParam("cashOutNo"));
        return super.init(model);
    }
    
    
    /**
     * 根据出账编号查出所有CMS信息
     * <功能详细描述>
     * @param cashOutNo
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getCmsInfoBycashOutNo")
    public AjaxResult getCmsInfoBycashOutNo(String cashOutNo) {
        AjaxResult ar = new AjaxResult();
        if(!StringUtil.isEmpty(cashOutNo)){
            FinProjectTargetAssetCmsDto finProjectTargetAssetCmsDto = finProjectTargetAssetService.getCmsInfoBycashOutNo(cashOutNo);
            if (finProjectTargetAssetCmsDto != null) {
                ar.setData(finProjectTargetAssetCmsDto);
            }
        }
        return ar;
    } 
}
