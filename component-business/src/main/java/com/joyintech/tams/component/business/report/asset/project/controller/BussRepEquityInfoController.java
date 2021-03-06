/*
 * 文件名：BussRepEquityInfoController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.report.asset.project.controller;

import com.joyintech.tams.constant.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.report.asset.bond.BussFinBondRepInfoService;
import com.joyintech.tams.component.business.report.asset.project.BussRepEquityInfoService;
import com.joyintech.tams.component.business.report.asset.project.dto.BussRepEquityInfoDto;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;

/**
 * 权益类--中债报送
 * 〈功能详细描述〉
 *
 * @author zhangnian
 * @version 1.0 2018年10月31日
 * @see BussRepEquityInfoController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/report/asset/project/BussRepEquityInfo")
public class BussRepEquityInfoController extends BaseController{

    @Autowired
    private BussRepEquityInfoService bussRepEquityInfoService;

    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;
    /**
     * 业务共同
     */
    @Autowired
    private CommonBusinessService commonBusinessService;

    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, bussRepEquityInfoService.getInitDictCode());
        setSelectOptionToModel(model, new DictCode[]{DictCode.机构所属行业一级分类, DictCode.机构所属行业二级分类,DictCode.是否_中债});
        model.addAttribute("YES", DictCodeList.是否_中债_是.getValue());
        //中债报送关联上级页面数据字典
        if (StringUtil.isNotEmpty(this.getParam("svr_asset_type_second_level"))){
            model.addAttribute("SVR_ASSET_TYPE_SECOND_LEVEL",this.getParam("svr_asset_type_second_level"));
        }

        // 设置画面初始值
        setEntity(model);
        model.addAttribute("gp", getParam("gp"));
        model.addAttribute("entityName", StringUtil.defaultString(this.getParam("entityName"), BussRepEquityInfoService.DEFAULT_ENTITY_NAME));
        model.addAttribute("INT_ASSET_TYPE", this.getParam("int_asset_type"));
        model.addAttribute("FINPROD_TYPE_TG_F18",DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
        model.addAttribute("FINPROD_TYPE_TG_F01",DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        model.addAttribute("SVR_PARTY_TYPE1_01", DictCodeListExt3.发行机构类型_按规模划分_中小微企业.getValue());
        return "component/report/asset/project/BussRepEquityInfo";
    }

    private void setEntity(Model model){
        String finprodId = this.getParam("finprodId");
        String finprodMarketId = this.getParam("finprodMarketId");
        String workFlowId = this.getParam("workflowId");
        String gp = this.getParam("gp");//从股票标的来
        //StringUtil.defaultString防止报空指针异常的错误
        String entityName = StringUtil.defaultString(this.getParam("entityName"), BussFinBondRepInfoService.DEFAULT_ENTITY_NAME);
      //不来自于代办
        if(WorkFlowUtil.isNotFromToDoList()&& StringUtil.isNotEmpty(finprodId)) {
            //根据主键获取业务实体-中债报送基本信息

            if(DictCodeListExt1.金融产品类型_投管用_股票.getValue().equals(gp)){
                BussRepEquityInfoDto dto = new BussRepEquityInfoDto();
                dto.setType8(DictCodeListExt1.金融产品类型_投管用_股票.getValue());
                dto.setRepInvestStage(DictCodeListExt2.投资阶段_已上市.getValue());
                dto.setRepSharesType(DictCodeListExt2.股票类型_普通股.getValue());
                model.addAttribute(GlobalConstant.ENTITY, dto);
            }
          else{
            BussRepEquityInfoDto bussRepEquityInfoDto = bussRepEquityInfoService.getBussRepEquityInfoByProductId(finprodId);
            model.addAttribute(GlobalConstant.ENTITY, bussRepEquityInfoDto);
           }
        }//来自于代办
        else if(!WorkFlowUtil.isNotFromToDoList()){
            //根据工作流与留痕处理提供的共通方法获取业务实体
            BussRepEquityInfoDto bussRepEquityInfoDto = mstWorkflowCommonService.getBusinessEntityByWorkFlowId(workFlowId, entityName, BussRepEquityInfoDto.class);
            model.addAttribute(GlobalConstant.ENTITY, bussRepEquityInfoDto);
    }
        else if(WorkFlowUtil.isNotFromToDoList()&& StringUtil.isNotEmpty(finprodMarketId)&& DictCodeListExt1.金融产品类型_投管用_股票.getValue().equals(gp)){ BussRepEquityInfoDto bussRepEquityInfoDto = bussRepEquityInfoService.getBussRepEquityInfoByProductId(finprodId);
            model.addAttribute(GlobalConstant.ENTITY, bussRepEquityInfoDto);}
}
}
