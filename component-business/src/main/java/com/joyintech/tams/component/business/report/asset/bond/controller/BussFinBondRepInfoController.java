/*

 * 文件名：BussFinBondRepInfoController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.report.asset.bond.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.annotation.SubPageId;
import com.joyintech.tams.constant.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.report.asset.bond.BussFinBondRepInfoService;
import com.joyintech.tams.component.business.report.asset.bond.dto.BussFinBondRepInfoDto;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 债券类--中债报送
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月23日
 * @see BussFinBondRepInfoController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/report/asset/bond/BussFinBondRepInfo")
@SubPageId("BussFinBondRepInfo")
public class BussFinBondRepInfoController extends BaseController{

    @Autowired
    private BussFinBondRepInfoService bussFinBondRepInfoService;
    
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;
    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, bussFinBondRepInfoService.getInitDictCode());
        setSelectOptionToModel(model, DictCode.债券性质);
        setSelectOptionToModel(model, DictCode.是否_中债);
        // 设置画面初始值
        setEntity(model);
        // 画面参数设置
        model.addAttribute("entityName", StringUtil.defaultString(this.getParam("entityName"), BussFinBondRepInfoService.DEFAULT_ENTITY_NAME));
        model.addAttribute("INT_ASSET_TYPE", this.getParam("int_asset_type"));
        model.addAttribute("issuerElement", this.getParam("issuerElement"));
        model.addAttribute("PARTY_TYPE2_01",DictCodeListExt2.发行机构类型_按经济类型划分_国有企业.getValue());
        model.addAttribute("PARTY_TYPE2_02",DictCodeListExt2.发行机构类型_按经济类型划分_集体企业.getValue());
        model.addAttribute("PARTY_TYPE2_03", DictCodeListExt2.发行机构类型_按经济类型划分_私营企业.getValue());
        model.addAttribute("PARTY_TYPE2_99",DictCodeListExt2.发行机构类型_按经济类型划分_其他.getValue());
        //项目类资产中债报送专用
        if(StringUtil.equals(this.getParam("int_asset_type"), DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue())){
            model.addAttribute("SVR_ASSET_TYPE_SECOND_LEVEL", createChbAssetType());
        }
        model.addAttribute("FINPROD_TYPE_TG_F18",DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
        model.addAttribute("YES", DictCodeList.是否_中债_是.getValue());
        model.addAttribute("SVR_PARTY_TYPE1_01", DictCodeListExt3.发行机构类型_按规模划分_中小微企业.getValue());
        return "component/report/asset/bond/BussFinBondRepInfo";
    }
    
    private void setEntity(Model model){
        String finprodId = this.getParam("finprodId");
        String workFlowId = this.getParam("workflowId");
        String entityName = StringUtil.defaultString(this.getParam("entityName"), BussFinBondRepInfoService.DEFAULT_ENTITY_NAME);
      //不来自于代办
        if(WorkFlowUtil.isNotFromToDoList()&& StringUtil.isNotEmpty(finprodId)) {
            //根据主键获取业务实体-中债报送基本信息
            BussFinBondRepInfoDto bussFinBondRepInfoDto = bussFinBondRepInfoService.getBussFinBondRepInfoByProductId(finprodId);
            if(null!=bussFinBondRepInfoDto){
                model.addAttribute("AssetTypeInit", bussFinBondRepInfoDto.getRepAssetType()); 
            }
            model.addAttribute(GlobalConstant.ENTITY, bussFinBondRepInfoDto);
        }//来自于代办
        else if(!WorkFlowUtil.isNotFromToDoList()){
            //根据工作流与留痕处理提供的共通方法获取业务实体
            BussFinBondRepInfoDto bussFinBondRepInfoDto = mstWorkflowCommonService.getBusinessEntityByWorkFlowId(workFlowId, entityName, BussFinBondRepInfoDto.class);
            if(null!=bussFinBondRepInfoDto){
                model.addAttribute("AssetTypeInit", bussFinBondRepInfoDto.getRepAssetType()); 
            }
            model.addAttribute(GlobalConstant.ENTITY, bussFinBondRepInfoDto);
        }
    }
    
    private String createChbAssetType(){
        SelectDto selectDto = this.getSelectDataFromDataDic(DictCode.资产_负债二级类别, null);
        if (selectDto != null && !StringUtil.isEmptyList(selectDto.getOptions())) {
            List<SelectOptionDto> options = selectDto.getOptions();
            for (int index = options.size() - 1; index >= 0; index-- ) {
                SelectOptionDto option = options.get(index);
                if(!StringUtil.equals(option.getId(), DictCodeListExt2.资产_负债二级类别_理财直接融资工具.getValue())){
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
    @ResponseBody
    @RequestMapping("getInfoByIssuer")
    public BussFinBondRepInfoDto getInfoByIssuer(@RequestBody Map<String, Object> param){
        return bussFinBondRepInfoService.getInfoByIssuer(param);
    }
}
