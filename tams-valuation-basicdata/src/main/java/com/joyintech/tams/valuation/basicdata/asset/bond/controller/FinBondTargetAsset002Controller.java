/*
 * 文件名：FinBondTargetAsset002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.basicdata.asset.bond.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.listener.FileAttachmentProcess;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductClass;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.valuation.basicdata.asset.bond.dto.FinBondTargetAssetDto;
import com.joyintech.tams.valuation.basicdata.asset.bond.service.FinBondTargetAssetService;
import com.joyintech.tams.valuation.basicdata.asset.common.service.ValAssetCommonService;
import com.joyintech.tams.valuation.component.business.service.CommonBusinessService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 债券类标的信息新建
 * @author zhangnian
 * @version 1.0 2018年9月20日
 * @see FinBondTargetAsset002Controller
 * @since 1.0
 */
@Controller
@PageId("FinBondTargetAsset002")
@RequestMapping("/valuation/basicdata/asset/bond/FinBondTargetAsset002")
public class FinBondTargetAsset002Controller extends BaseController{

    @Autowired
    private FinBondTargetAssetService finBondTargetAssetService;



    @Autowired
    private FileAttachmentProcess fileAttachmentProcess;

    @Autowired
    private CommonBusinessService commonBusinessService;


    @Autowired
    private ValAssetCommonService assetCommonService;

    /**
     * 初始化数据字典下拉框
     */
    private static final IDictCode[] DICT_CODES = new DictCode[] {
            DictCode.交易场所,
            DictCode.境内外,
            DictCode.发行方式,
            DictCode.是否,
            DictCode.币种,
            DictCode.登记托管机构,
            DictCode.本金收益特征,
            DictCode.债券核算分类,
            DictCode.增值税资产分类,
            DictCode.债券类型
    };
    @Override
    public String init(Model model){
        //初始化基本信息画面数据字典
        setSelectOptionToModel(model, DICT_CODES);
        String finprodId = this.getParam("finprodId");
        //String status = getParam("status");
        String workflowId = getParam("workflowId");
        model.addAttribute("YES",DictCodeList.是否_是.getValue());
        model.addAttribute("isTradeCalendar",DictCodeList.是否_是.getValue());
        model.addAttribute("FINPROD_TYPE_F01",DictCodeListExt1.金融产品类型_债券.getValue());
        model.addAttribute("FINPROD_TYPE_F07", DictCodeListExt1.金融产品类型_存款.getValue());
        model.addAttribute("FINPROD_TYPE_F17",DictCodeListExt1.金融产品类型_计划类资产.getValue());
        model.addAttribute("FINPROD_TYPE_F10",DictCodeListExt1.金融产品类型_股票.getValue());
        model.addAttribute("COUPON_SPECIES_02", DictCodeList.息票品种_贴现.getValue());
        model.addAttribute("COUPON_SPECIES_03", DictCodeList.息票品种_零息.getValue());
        model.addAttribute("CCY_INIT", DictCodeList.币种_人民币_CNY.getValue());
        model.addAttribute("COPY_FLAG", this.getParam("copyFlag"));//是否复制
        //平台日
        model.addAttribute("iPlatformDate", DateUtil.parse(DateUtil.getSysDate()));
        model.addAttribute("YES_OR_NO_Y", DictCodeList.是否_是.getValue());
        //不来自于代办
        if(WorkFlowUtil.isNotFromToDoList()&& StringUtil.isNotEmpty(finprodId)) {
            model.addAttribute("isTransInAsset", commonBusinessService.isTransInAsset(finprodId)!=0?true:false);
            model.addAttribute("isHqTrade", finBondTargetAssetService.isHqTrade(finprodId)>0?true:false);
            //根据主键获取业务实体--标的金融产品基本信息
            FinBondTargetAssetDto finBondTargetAssetDto = finBondTargetAssetService.getBasicInfo(
                    finprodId);
            model.addAttribute("entity", finBondTargetAssetDto);
        }//来自于代办
        else if(!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)){
            //根据工作流与留痕处理提供的共通方法获取业务实体
            FinBondTargetAssetDto finBondTargetAssetDto = finBondTargetAssetService.getBussinessEntityByWorkflowId(workflowId);
            //commonBusinessService.setTradeMarketId(finBondTargetAssetDto,model);
            //设置到model中
            model.addAttribute("entity", finBondTargetAssetDto);
        }else{
            FinBondTargetAssetDto finBondTargetAssetDto = new FinBondTargetAssetDto();
            finBondTargetAssetDto.setFinprodId(IDGenerator.generate());
            finBondTargetAssetDto.setIsAutoUpdate(DictCodeList.是否_是.getValue());
            finBondTargetAssetDto.setBlnArea(DictCodeList.境内外_境内.getValue());
            //commonBusinessService.setTradeMarketId(finBondTargetAssetDto,model);
            model.addAttribute("entity", finBondTargetAssetDto);
        }
        //资产类型
        model.addAttribute("INT_ASSET_TYPE", DictCodeListExt1.金融产品类型_债券.getValue());
        model.addAttribute("workflowId",workflowId);
        model.addAttribute("pageModel",this.getPageModel());
        model.addAttribute("SVR_SHARES_TYPE_02",DictCodeListExt2.股票类型_优先股.getValue());
        model.addAttribute("BOND_TYPE_028",DictCodeList.债券类型_可转债.getValue());
        model.addAttribute("BOND_TYPE_029",DictCodeList.债券类型_可交换债.getValue());
        return super.init(model);
    }


    /**
     * check--保存
     * @param dto
     * @param result
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) FinBondTargetAssetDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     *
     * 保存
     * @param dto
     * @param br
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(PAGE_SAVE)
    public AjaxResult save(@RequestBody @Validated({
            ValidateGroupEdit.class}) FinBondTargetAssetDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        //生成金融产品代码
        if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
            dto = finBondTargetAssetService.getFinprodId(dto);
        }
        dto.setFinprodType(DictCodeListExt1.金融产品类型_债券.getValue());
        dto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        WorkFlowParameter<FinBondTargetAssetDto> params = new WorkFlowParameter<FinBondTargetAssetDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getFinprodMarketId());
        params.setBusinessType(DictCodeList.共通业务类型_债券类资产信息.getValue());
        // 设置流程业务说明
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment(getMessage("FinBondTargetAsset002.finprodId")+
                    GlobalConstant.OPEN_PARENTHESIS+entity.getFinprodMarketId(), GlobalConstant.CLOSE_PARENTHESIS+
                    getMessage("FinBondTargetAsset002.finprodName")+GlobalConstant.OPEN_PARENTHESIS+
                    entity.getFinprodAbbr()+GlobalConstant.CLOSE_PARENTHESIS);
        });
        // 给参数变成ProcessParameter而非WorkFlowParameter
        ProcessParameter<FinBondTargetAssetDto> parameter = new ProcessParameter<FinBondTargetAssetDto>();
        parameter.setWorkFlowParameter(params);
        // 系统页面表IS_NEED_WORKFLOW 是否走流程，01-是，02-否
        //封装标的类金融产品基础组件需要的DTO
        String msg = finBondTargetAssetService.start(parameter, dto);
        return new AjaxResult(msg);
    }

    /**
     * 新建时根据市场代码查询wind接口债券信息
     *
     * @param finprodMarketId 市场代码
     * @return
     * @throws ParseException
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getInterfBondDto")
    public AjaxResult getInterfBondDto(@RequestParam("finprodMarketId") String finprodMarketId,
                                       @RequestParam("tradeMarketId") String tradeMarketId)
            throws ParseException {
        AjaxResult ar = new AjaxResult();
        // 债券新建新增快捷录入功能 add by renping 20191010 start
        FinBondTargetAssetDto dto = finBondTargetAssetService.getBondDto(
                finprodMarketId + tradeMarketId);
        if (StringUtil.isNotEmpty(dto)) {
            dto.setFinprodMarketId(finprodMarketId);
            ar.setData(dto);
        }
        return ar;
    }

    @ResponseBody
    @RequestMapping("/getBasicInfo")
    public FinBondTargetAssetDto getBasicInfo(@RequestBody FinBondTargetAssetDto finBondTargetAssetDto)
            throws Exception {
        FinBondTargetAssetDto resultDto = finBondTargetAssetService.getBasicInfo(finBondTargetAssetDto.getFinprodId());
        return resultDto;
    }

    /**
     * 获取新建界面的以及市场投标债券代码联想下拉框
     *
     * @author wangguiqi
     * @param
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getfinprodIdForAdd")
    public Page<SelectOptionDto> getfinprodIdForAdd()
            throws Exception {
        return finBondTargetAssetService.getfinprodIdForAdd(RequestUtil.getPageParam());
    }

    /**
     * 获取资产下拉框
     * @return
     */
    @ResponseBody
    @RequestMapping("getAssetIdOption")
    public Page<SelectOptionDto> getAssetIdOption(){
        Map<String,Object> param = RequestUtil.getPageParam();
        Page<SelectOptionDto> page = assetCommonService.getAssetIdOption(param);
        if (page != null && page.getRoot() != null && page.getRoot().size() > 0) {
            for (SelectOptionDto dto : page.getRoot()) {
                dto.setText(dto.getId());
            }
        }
        return page;
    }

    /**
     * 获取核算分类
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping("getFinanceProductClass")
    public FinBondTargetAssetDto getFinanceProductClass(@RequestBody FinBondTargetAssetDto dto){
        FinProductClass fdto = new FinProductClass();
        //金融产品类型
        fdto.setFinprodType(DictCodeListExt1.金融产品类型_债券.getValue());
        //资产类型
        fdto.setAssetType(dto.getAssetType());
        //交易场所
        fdto.setTradeMarket(dto.getTradeMarket());
        //境内外
        fdto.setBlnArea(dto.getBlnArea());
        dto.setAccountintType(assetCommonService.getFinanceProductClass(fdto));
        return dto;
    }

    /**
     * 获取税务分类
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping("getFinanceProductTaxClass")
    public FinBondTargetAssetDto getFinanceProductTaxClass(@RequestBody FinBondTargetAssetDto dto){
        FinProductClass fdto = new FinProductClass();
        //金融产品类型
        fdto.setFinprodType(DictCodeListExt1.金融产品类型_债券.getValue());
        //资产类型
        fdto.setAssetType(dto.getAssetType());
        dto.setTaxType(assetCommonService.getFinanceProductTaxClass(fdto));
        return dto;
    }
}
