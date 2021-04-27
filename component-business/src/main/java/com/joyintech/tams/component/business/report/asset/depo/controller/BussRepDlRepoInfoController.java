/*
 * 文件名：BussRepDlRepoInfoController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年11月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.report.asset.depo.controller;

import com.joyintech.tams.constant.DictCodeListExt2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.report.asset.depo.BussRepDepositInfoService;
import com.joyintech.tams.component.business.report.asset.depo.BussRepDlRepoInfoService;
import com.joyintech.tams.component.business.report.asset.depo.dto.BussRepDlRepoInfoDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 拆放同业及买入返售报送信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月6日
 * @see BussRepDlRepoInfoController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/report/asset/depo/BussRepDlRepoInfo")
public class BussRepDlRepoInfoController extends BaseController{

    @Autowired
    private BussRepDlRepoInfoService bussRepDlRepoInfoService;
    
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;
    
    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, bussRepDlRepoInfoService.getInitDictCode());
        model.addAttribute("type6_01",DictCodeList.存款类资产类型_同业借款.getValue());
        model.addAttribute("type6_02",DictCodeList.存款类资产类型_存放同业.getValue());
        model.addAttribute("YES", DictCodeList.是否_中债_是.getValue());
        //中债报送关联上级页面数据字典
       /* if (StringUtil.isNotEmpty(this.getParam("svr_asset_type_second_level"))){
            model.addAttribute("SVR_ASSET_TYPE_SECOND_LEVEL",this.getParam("svr_asset_type_second_level"));
        }*/
       // 存款类标的拆放同业报送页面资产负债类别应只有1107:拆放同业
        model.addAttribute("SVR_ASSET_TYPE_SECOND_LEVEL",createChbAssetType());
        
        model.addAttribute("ASSET_TYPE_SECOND_LEVEL_INIT", DictCodeListExt2.资产_负债二级类别_拆放同业.getValue());
        // 设置画面初始值
        setEntity(model);
        // 画面参数设置
        model.addAttribute("type6Element", this.getParam("type6Element"));
        model.addAttribute("entityName", StringUtil.defaultString(this.getParam("entityName"), BussRepDepositInfoService.DEFAULT_ENTITY_NAME));
        return "component/report/asset/depo/BussRepDlRepoInfo";
    }
    
    private void setEntity(Model model){
        String finprodId = this.getParam("finprodId");
        String workFlowId = this.getParam("workflowId");
        String entityName = StringUtil.defaultString(this.getParam("entityName"), BussRepDepositInfoService.DEFAULT_ENTITY_NAME);
      //不来自于代办
        if(WorkFlowUtil.isNotFromToDoList()&& StringUtil.isNotEmpty(finprodId)) {
            //根据主键获取业务实体-中债报送基本信息
            BussRepDlRepoInfoDto bussRepDlRepoInfoDto = bussRepDlRepoInfoService.getBussRepDlRepoInfoByProductId(finprodId);
            model.addAttribute(GlobalConstant.ENTITY, bussRepDlRepoInfoDto);
        }//来自于代办
        else if(!WorkFlowUtil.isNotFromToDoList()){
            //根据工作流与留痕处理提供的共通方法获取业务实体
            BussRepDlRepoInfoDto bussRepDlRepoInfoDto = mstWorkflowCommonService.getBusinessEntityByWorkFlowId(workFlowId, entityName, BussRepDlRepoInfoDto.class);
            model.addAttribute(GlobalConstant.ENTITY, bussRepDlRepoInfoDto);
        }
    }
    /**
     * 存款类标的拆放同业报送页面资产负债类别应只有1107:拆放同业
     * @return
     */
    private String createChbAssetType() {
        SelectDto selectDto = this.getSelectDataFromDataDic(DictCode.资产_负债二级类别, null);
        if (selectDto != null && !StringUtil.isEmptyList(selectDto.getOptions())) {
            List<SelectOptionDto> options = selectDto.getOptions();
            for (int index = options.size() - 1; index >= 0; index--) {
                SelectOptionDto option = options.get(index);
                if (!StringUtil.equals(option.getId(), DictCodeListExt2.资产_负债二级类别_拆放同业.getValue())) {
                    options.remove(option);
                }
            }
            return JsonUtils.objectArray2Json(options.toArray());
        }
        return null;
    }
    /**
     * 重写pageId {@inheritDoc}
     */
    @Override
    public String getPageId() {
        return this.getParam(GlobalConstant.PAGE_ID);
    }
}
