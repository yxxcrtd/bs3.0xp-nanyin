/*
 * 文件名：BussRepFundInfoController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.report.asset.fund.controller;

import java.util.List;

import com.joyintech.tams.constant.*;
import com.joyintech.tams.constant.DictCodeListExt2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.report.asset.fund.BussRepFundInfoService;
import com.joyintech.tams.component.business.report.asset.fund.dto.BussRepFundInfoDto;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 基金资产中债报送信息组件
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年9月18日
 * @see BussRepFundInfoController
 * @since 1.0
 */
@Controller
@PageId("BussRepFundInfo")
@RequestMapping("/component/report/asset/fund/BussRepFundInfo")
public class BussRepFundInfoController extends BaseController{
    /**
     * bussRepFundInfoService
     */
    @Autowired(required = false)
    private BussRepFundInfoService bussRepFundInfoService;
    
    /**
     * bussRepFundInfoService
     */
    @Autowired(required = false)
    private MstWorkflowCommonService mstWorkflowCommonService;
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        // 设置画面初始值
        setEntity(model);
        // 初始化开放计划组件的画面元素
        setSelectOptionToModel(model, new DictCode[] {DictCode.政府投资基金投向,
            DictCode.是否_中债, DictCode.登记备案机构, DictCode.资产_负债二级类别,
            DictCode.营业日准则,DictCode.机构所属行业一级分类,DictCode.机构所属行业二级分类
            ,DictCode.发行机构类型_按规模划分,DictCode.发行机构类型_按经济类型划分,DictCode.发行机构类型_按技术领域划分
            ,DictCode.投资阶段,DictCode.交易流通场所,DictCode.人行机构规模,DictCode.受让形式});
        //设置页面参数
        model.addAttribute("fundTypeElement", this.getParam("fundTypeElement"));
        model.addAttribute("secondTypeElement", this.getParam("secondTypeElement"));
        this.getParam("entityName");
        model.addAttribute("entityName", StringUtil.defaultString(this.getParam("entityName"), BussRepFundInfoService.DEFAULT_ENTITY_NAME));
       // model.addAttribute("Y", DictCodeList.是否_中债_是.getValue());
        model.addAttribute("SVR_YES_OR_NO_default", DictCodeList.是否_中债_是.getValue());
        model.addAttribute("z_02", DictCodeListExt2.资产_负债二级类别_货币市场基金.getValue());
        model.addAttribute("h_01", DictCodeList.基金类资产类型_货币基金.getValue());
        model.addAttribute("CHB_FUND_ASSET_SECOND_TYPE_02", DictCodeListExt2.资产_负债二级类别_货币市场基金.getValue());
        model.addAttribute("CHB_FUND_ASSET_SECOND_TYPE_01", DictCodeListExt2.资产_负债二级类别_债券基金.getValue());
        model.addAttribute("CHB_FUND_ASSET_SECOND_TYPE_03", DictCodeListExt2.资产_负债二级类别_股票基金.getValue());
        model.addAttribute("CHB_FUND_ASSET_SECOND_TYPE_07", DictCodeListExt2.资产_负债二级类别_基金中基金.getValue());
//        model.addAttribute("CHB_FUND_ASSET_SECOND_TYPE_05", DictCodeListExt2.资产_负债二级类别_保本基金.getValue());
        model.addAttribute("CHB_FUND_ASSET_SECOND_TYPE_04", DictCodeListExt2.资产_负债二级类别_混合基金.getValue());
        model.addAttribute("CHB_FUND_ASSET_SECOND_TYPE_06", DictCodeListExt2.资产_负债二级类别_其他公募基金.getValue());
        model.addAttribute("FUND_ASSET_SECOND_TYPE_01", DictCodeList.基金类资产二级类型_债券基金.getValue());
        model.addAttribute("FUND_ASSET_SECOND_TYPE_02", DictCodeList.基金类资产二级类型_货币市场基金.getValue());
        model.addAttribute("FUND_ASSET_SECOND_TYPE_03", DictCodeList.基金类资产二级类型_股票基金.getValue());
        model.addAttribute("FUND_ASSET_SECOND_TYPE_05", DictCodeList.基金类资产二级类型_保本基金.getValue());
        model.addAttribute("FUND_ASSET_SECOND_TYPE_04", DictCodeList.基金类资产二级类型_混合基金.getValue());
        model.addAttribute("FUND_ASSET_SECOND_TYPE_06", DictCodeList.基金类资产二级类型_其他公募基金.getValue());
        model.addAttribute("FUND_ASSET_SECOND_TYPE_07", DictCodeList.基金类资产二级类型_基金中基金.getValue());
        model.addAttribute("TRADE_MARKET_99", DictCodeList.交易流通场所_其他交易流通场所.getValue());
        //项目类资产中债报送专用
        if(StringUtil.equals(this.getParam("int_asset_type"), DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue())){
            model.addAttribute("CHB_FUND_ASSET_SECOND_TYPE", createChbAssetType());
            model.addAttribute("ASSET_SECOND_TYPE", "CHB_ASSET_TYPE_PROJECT");
        }
        //中债报送关联上级页面数据字典
        if (StringUtil.isNotEmpty(this.getParam("svr_asset_type_second_level"))){
            model.addAttribute("SVR_ASSET_TYPE_SECOND_LEVEL",this.getParam("svr_asset_type_second_level"));
        }
        model.addAttribute("INT_ASSET_TYPE", this.getParam("int_asset_type"));
        model.addAttribute("FINPROD_TYPE_TG_F18",DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
        model.addAttribute("SVR_PARTY_TYPE1_01", DictCodeListExt3.发行机构类型_按规模划分_中小微企业.getValue());
        return "component/report/asset/fund/BussRepFundInfo";
    }
    
    private void setEntity(Model model) {
        String workFlowId = StringUtil.defaultString(this.getParam("workflowId"));
        String finprodId = this.getParam("finprodId");
        if(!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workFlowId)){
            BussRepFundInfoDto param = mstWorkflowCommonService.getBusinessEntityByWorkFlowId(workFlowId, "bussRepFundInfoDto", BussRepFundInfoDto.class);
            model.addAttribute(GlobalConstant.ENTITY, param);
        }else if(WorkFlowUtil.isNotFromToDoList()&& StringUtil.isNotEmpty(finprodId)) {
                BussRepFundInfoDto param = bussRepFundInfoService.getByFinprodId(finprodId);
                if(null!=param){
                    param.setRepPartyType1(DictCodeListExt2.发行机构类型_按规模划分_大型企业.getValue());
                }
                model.addAttribute(GlobalConstant.ENTITY, param);
            }else{
                BussRepFundInfoDto fundDto = new BussRepFundInfoDto();
                fundDto.setRepPartyType1(DictCodeListExt2.发行机构类型_按规模划分_大型企业.getValue());
                model.addAttribute("entity", fundDto);
            }
            /*if(!StringUtil.equals(getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
                String finprodId = StringUtil.defaultString(this.getParam("finprodId"));
                if(StringUtil.isNotEmpty(finprodId) && null != bussRepFundInfoService) {
                    BussRepFundInfoDto param = bussRepFundInfoService.getByFinprodId(finprodId);
                    model.addAttribute(GlobalConstant.ENTITY, param);
                }
            }
            else{
                BussRepFundInfoDto fundDto = new BussRepFundInfoDto();
                fundDto.setRepPartyType1(DictCodeListExt2.发行机构类型_按规模划分_大型企业.getValue());
                model.addAttribute("entity", fundDto);
            }*/
     }
    
    private String createChbAssetType(){
        SelectDto selectDto = this.getSelectDataFromDataDic(DictCode.资产_负债二级类别, null);
        if (selectDto != null && !StringUtil.isEmptyList(selectDto.getOptions())) {
            List<SelectOptionDto> options = selectDto.getOptions();
            for (int index = options.size() - 1; index >= 0; index-- ) {
                SelectOptionDto option = options.get(index);
                if(!StringUtil.equals(option.getId(), DictCodeListExt2.资产_负债二级类别_基础设施基金.getValue())){
                    options.remove(option);
                }
            }
            return JsonUtils.objectArray2Json(options.toArray());
        }
        return null;
    }
     
     @Override
     public String getPageId(){
         return this.getParam(GlobalConstant.PAGE_ID);
     }
     
}
