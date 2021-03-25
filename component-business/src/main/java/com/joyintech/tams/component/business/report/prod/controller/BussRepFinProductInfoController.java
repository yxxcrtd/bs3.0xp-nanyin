/*
 * 文件名：BussRepFinProductInfoController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年9月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.report.prod.controller;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.report.prod.BussRepFinProductInfoService;
import com.joyintech.tams.component.business.report.prod.dto.BussRepFinProductInfoDto;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * @author 张亮
 * @version 1.0 2018年9月21日
 * @see BussRepFinProductInfoController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/report/prod/BussRepFinProductInfo")
public class BussRepFinProductInfoController extends BaseController {

    /**
     * {@linkplain}
     */
    @Autowired
    private BussRepFinProductInfoService bussRepFinProductInfoService;

    /**
     * {@linkplain}
     */
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    @Override
    public String init(Model model) {
        setSelectOptionToModel(model,
            new DictCode[] {DictCode.产品期限, DictCode.所属国家或地区,
                DictCode.理财业务服务模式, DictCode.会计核算方式_中债, DictCode.产品资产配置方式_中债,
                DictCode.管理模式, DictCode.定价方式, DictCode.资金实际投向,
                DictCode.行政区划_省, DictCode.币种, DictCode.投资者风险偏好,
                DictCode.产品风险等级, DictCode.合作模式, DictCode.投资本金_收益_到账日,
                DictCode.有无, DictCode.是否, DictCode.产品增信机构类型, DictCode.产品增信形式,
                DictCode.开放期业务, DictCode.规律开放周期, DictCode.资金投向地区,
                DictCode.产品运作模式, DictCode.目标客户类型});
        model.addAttribute("SALETARGET_DECLARE_01",
            DictCodeList.目标客户类型_私人银行客户专属.getValue());
        model.addAttribute("SALETARGET_DECLARE_02",
            DictCodeList.目标客户类型_机构客户专属.getValue());
        model.addAttribute("YES_OR_NO_Y", DictCodeList.是否_是.getValue());
        model.addAttribute("HAS", DictCodeListExt1.有无_有.getValue());
        //多级法人改造.新建时.产品取机构登记银行代码管理 中的登记编码
        model.addAttribute("ISSUE_PARTY_ID", bussRepFinProductInfoService.getIssuePartyId());
        model.addAttribute("defalutRepRiskLevel", DictCodeListExt1.产品风险等级_一级_低.getValue());
        model.addAttribute("saleAreaDeclareIn", DictCodeListExt2.资金投向地区_境内.getValue());
        model.addAttribute("saleAreaDeclareOut", DictCodeListExt2.资金投向地区_境外.getValue());
        model.addAttribute("saleAreaDeclareInOut", DictCodeListExt2.资金投向地区_境内和境外.getValue());
        model.addAttribute("prodManageModeActM", DictCodeListExt1.产品管理模式_资产管理公司为实际管理人.getValue());
        model.addAttribute("prodManageModeOthO", DictCodeListExt1.产品管理模式_其他机构为实际管理人.getValue());
        model.addAttribute("copModeSigRun", DictCodeListExt2.合作模式_独立运作.getValue());
        model.addAttribute("prodRunModeCloN", DictCodeListExt2.产品运作模式_封闭式净值型.getValue());
        model.addAttribute("prodRunModeCloNN", DictCodeListExt2.产品运作模式_封闭式非净值型.getValue());
        // 期限品种相关
        model.addAttribute("REPTERM_02", DictCodeListExt2.产品期限_7天_含_以内.getValue());
        model.addAttribute("REPTERM_03", DictCodeListExt2.产品期限_7天_1个月_含.getValue());
        model.addAttribute("REPTERM_04", DictCodeListExt2.产品期限_1_3个月_含.getValue());
        model.addAttribute("REPTERM_05", DictCodeListExt2.产品期限_3_6个月_含.getValue());
        model.addAttribute("REPTERM_06", DictCodeListExt2.产品期限_6_12个月_含.getValue());
        model.addAttribute("REPTERM_07", DictCodeListExt2.产品期限_1_3年_含.getValue());

        model.addAttribute("entityName", StringUtil.defaultString(this.getParam("entityName"),
            BussRepFinProductInfoService.DEFAULT_ENTITY_NAME));
        model.addAttribute("pageId", this.getParam("pageId"));
        model.addAttribute("isLayer", this.getParam("isLayer"));
        model.addAttribute("vdateElement", this.getParam("vdateElement"));
        model.addAttribute("mdateElement", this.getParam("mdateElement"));
        model.addAttribute("operationType", this.getParam("operationType"));
        model.addAttribute("openMode", this.getParam("openMode"));
        model.addAttribute("openCycle", this.getParam("openCycle"));
        model.addAttribute("openCycleUnit", this.getParam("openCycleUnit"));

        if (StringUtil.equals(RequestUtil.getParam("isTodoList"), "true")) {
            BussRepFinProductInfoDto dto = mstWorkflowCommonService.getBusinessEntityByWorkFlowId(
                this.getParam(GlobalConstant.WORKFLOW_ID),
                StringUtil.defaultString(this.getParam("entityName"),
                    BussRepFinProductInfoService.DEFAULT_ENTITY_NAME),
                BussRepFinProductInfoDto.class);
            model.addAttribute(GlobalConstant.ENTITY, dto);
        }
        else if (StringUtil.isNotEmpty(this.getParam("finprodId"))){
            model.addAttribute(GlobalConstant.ENTITY,
                bussRepFinProductInfoService.select(this.getParam("finprodId")));
        }

        return "component/report/prod/BussRepFinProductInfo";
    }
    
    /**
     * 根据金融产品代码查询中债信息
     * 
     * @param finprodId 金融产品代码
     * @return 中债信息
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryRepInfoByFinprodId")
    public BussRepFinProductInfoDto queryRepInfoByFinprodId(String finprodId){
        return bussRepFinProductInfoService.select(finprodId);
    }
    /**
     * 根据行内标识码查询中债信息
     * 
     * @param repinprodId 行内标识码
     * @return 中债信息
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("queryRepInfoByRepFinprodId")
    public BussRepFinProductInfoDto queryRepInfoByRepFinprodId(String repFinprodInsId){
        return bussRepFinProductInfoService.selectRepFinprodId(repFinprodInsId);
    }
    
    /**
     * 计算[产品期限(天)]以及[期限品种]设定值
     *
     * @param dto 画面上的参数dto
     * @return 结果dto
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("calcProductTermInfo")
    public AjaxResult calcProductTermInfo(@RequestBody BussRepFinProductInfoDto dto){
        AjaxResult ar = new AjaxResult();
        ar.setData(bussRepFinProductInfoService.calcProductTermInfo(dto));
        return ar;
    }
}
