/*
 * 文件名：BussRepDepositInfoController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.report.asset.depo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.report.asset.depo.BussRepDepositInfoService;
import com.joyintech.tams.component.business.report.asset.depo.dto.BussRepDepositInfoDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 存款类--中债报送
 * @author zhangnian
 * @version 1.0 2018年9月18日
 * @see BussRepDepositInfoController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/report/asset/depo/BussRepDepositInfo")
public class BussRepDepositInfoController extends BaseController{

    @Autowired
    private BussRepDepositInfoService bussRepDepositInfoService;
    
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;
    
    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, bussRepDepositInfoService.getInitDictCode());
        model.addAttribute("type6_01",DictCodeList.存款类资产类型_同业借款.getValue());
        model.addAttribute("type6_02",DictCodeList.存款类资产类型_存放同业.getValue());
        model.addAttribute("YES", DictCodeList.是否_中债_是.getValue());
        //中债报送关联上级页面数据字典
        // 注释掉判断语句使下拉框显示 赵东东 2019.7.26 start
		/*
		 * if (StringUtil.isNotEmpty(this.getParam("svr_asset_type_second_level"))){
		 * model.addAttribute("SVR_ASSET_TYPE_SECOND_LEVEL",this.getParam(
		 * "svr_asset_type_second_level")); }
		 */
        // 注释掉判断语句使下拉框显示 赵东东 2019.7.26 end
       /* model.addAttribute("ASSET_TYPE_SECOND_LEVEL", createAssetTypeSecondLevel());*/
        // 设置画面初始值
        setEntity(model);
        // 画面参数设置
        //存款类型为结构性存款 update by lwj 20200402
        model.addAttribute("SVR_DEPO_TYPE_06", DictCodeListExt1.存款类型_结构性存款.getValue());
        model.addAttribute("SVR_ASSET_TYPE_SECOND_LEVEL_1002", DictCodeListExt2.资产_负债二级类别_本行存款.getValue());
        model.addAttribute("SVR_ASSET_TYPE_SECOND_LEVEL_1003", DictCodeListExt2.资产_负债二级类别_他行存款.getValue());
        setSelectOptionToModel(model, DictCode.存款类型);
        
        model.addAttribute("type6Element", this.getParam("type6Element"));
        model.addAttribute("entityName", StringUtil.defaultString(this.getParam("entityName"), BussRepDepositInfoService.DEFAULT_ENTITY_NAME));
        return "component/report/asset/depo/BussRepDepositInfo";
    }
    
    private void setEntity(Model model){
        String finprodId = this.getParam("finprodId");
        String workFlowId = this.getParam("workflowId");
        String entityName = StringUtil.defaultString(this.getParam("entityName"), BussRepDepositInfoService.DEFAULT_ENTITY_NAME);
      //不来自于代办
        if(WorkFlowUtil.isNotFromToDoList()&& StringUtil.isNotEmpty(finprodId)) {
            //根据主键获取业务实体-中债报送基本信息
            BussRepDepositInfoDto bussRepDepositInfoDto = bussRepDepositInfoService.getBussRepDepositInfoByProductId(finprodId);
            model.addAttribute(GlobalConstant.ENTITY, bussRepDepositInfoDto);
        }//来自于代办
        else if(!WorkFlowUtil.isNotFromToDoList()){
            //根据工作流与留痕处理提供的共通方法获取业务实体
            BussRepDepositInfoDto bussRepDepositInfoDto = mstWorkflowCommonService.getBusinessEntityByWorkFlowId(workFlowId, entityName, BussRepDepositInfoDto.class);
            model.addAttribute(GlobalConstant.ENTITY, bussRepDepositInfoDto);
        }
    }
    
    /*private String createAssetTypeSecondLevel(){
        SelectDto selectDto = this.getSelectDataFromDataDic(DictCode.存款类一级分类_中债, null);
        if (selectDto != null && !StringUtil.isEmptyList(selectDto.getOptions())) {
            List<SelectOptionDto> options = selectDto.getOptions();
            for (int index = options.size() - 1; index >= 0; index-- ) {
                SelectOptionDto option = options.get(index);
                if(!StringUtil.equals(option.getId(), DictCodeList.二级资产类别_本行存款.getValue())
                    &&!StringUtil.equals(option.getId(), DictCodeList.二级资产类别_他行存款.getValue())
                    &&!StringUtil.equals(option.getId(), DictCodeList.二级资产类别_本行发行的大额存单.getValue())
                    &&!StringUtil.equals(option.getId(), DictCodeList.二级资产类别_他行发行的大额存单.getValue())){
                    options.remove(option);
                }
            }
            return JsonUtils.objectArray2Json(options.toArray());
        }
        return null;
    }*/

    /**
     * 重写pageId {@inheritDoc}
     */
    @Override
    public String getPageId() {
        return this.getParam(GlobalConstant.PAGE_ID);
    }
}
