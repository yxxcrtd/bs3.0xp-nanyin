/*
 * 文件名：FinDepositTarget002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.basicdata.asset.depo.controller;


import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.FinProductClass;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.basicdata.asset.common.service.ValAssetCommonService;
import com.joyintech.tams.valuation.basicdata.asset.depo.dto.FinDepositTargetDto;
import com.joyintech.tams.valuation.basicdata.asset.depo.service.FinDepositTargetService;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
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
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 存款类资产新建
 * @author zhangnian
 * @version 1.0 2018年9月5日
 * @see FinDepositTarget002Controller
 * @since 1.0
 */
@Controller
@PageId("FinDepositTarget002")
@RequestMapping("/valuation/basicdata/asset/depo/FinDepositTarget002")
public class FinDepositTarget002Controller extends BaseController {
    
    @Autowired(required = false)
    private FinDepositTargetService finDepositTargetService;

//    @Autowired
//    private FileAttachmentProcess fileAttachmentProcess;

//    @Autowired
//    private IPlatformDate iPlatformDate;

    @Autowired
    private ValAssetCommonService assetCommonService;

    @Autowired
    private CommonBusinessService commonBusinessService;

    /**
     * 初始化数据字典下拉框
     */
    private static final IDictCode[] DICT_CODES = new DictCode[] {DictCode.存款类资产类型_GZ,DictCode.存款类资产存款类型,DictCode.存款核算分类,DictCode.增值税资产分类,DictCode.拆借核算分类,
         DictCode.币种, DictCode.本金收益特征, DictCode.资产_负债二级类别, DictCode.资产来源,DictCode.机构核算分类,
        DictCode.是否, DictCode.境内外};

    @Override
    public String init(Model model){
        //标的金融产品代码
        String finprodId = this.getParam("finprodId");
        //String status = getParam("status");
        String workflowId = getParam("workflowId");

        String assetType=getParam("assetType");
        //初始化基本信息画面数据字典
        setSelectOptionToModel(model, DICT_CODES);
        model.addAttribute("workflowId", workflowId);
        model.addAttribute("YES", DictCodeList.是否_是.getValue());
        model.addAttribute("isTradeCalendar", DictCodeList.是否_是.getValue());
        model.addAttribute("counterType", DictCodeListExt1.交易对手类型_分行.getValue());
        model.addAttribute("FINPROD_TYPE_TG_F01", DictCodeListExt1.金融产品类型_债券.getValue());
        model.addAttribute("FINPROD_TYPE_TG_F07", DictCodeListExt1.金融产品类型_存款.getValue());
        model.addAttribute("FINPROD_TYPE_TG_F17", DictCodeListExt1.金融产品类型_计划类资产.getValue());
        model.addAttribute("FINPROD_TYPE_TG_F18", DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
        model.addAttribute("DEPO_ASSET_TYPE_01", DictCodeList.存款类资产类型_GZ_存放同业.getValue());
        model.addAttribute("DEPO_ASSET_TYPE_02", DictCodeList.存款类资产类型_GZ_活期类资产.getValue());
        model.addAttribute("DEPO_ASSET_TYPE_03", DictCodeListExt1.存款类资产类型_GZ_同业借款.getValue());
        model.addAttribute("CCY_INIT", DictCodeList.币种_人民币_CNY.getValue());
        model.addAttribute("COPY_FLAG", this.getParam("copyFlag"));//是否复制
        //付息频率
     // 一体化对应etliu start
//        model.addAttribute("INTPAY_CYCLE_01", DictCodeList.支付频率_付息频率_到期支付.getValue());
        model.addAttribute("INTPAY_CYCLE_01", DictCodeList.频率_到期一次.getValue());
     // 一体化对应etliu end

        //推算方式
     // 一体化对应etliu
//        model.addAttribute("CALC_METHOD_01", DictCodeList.推算方法_起息日向后.getValue());
        model.addAttribute("CALC_METHOD_01", DictCodeList.推算方式_起息日向后.getValue());

        //资产类型
        model.addAttribute("INT_ASSET_TYPE", DictCodeListExt1.金融产品类型_存款.getValue());
        //是否_是
        model.addAttribute("YES_OR_NO_Y", DictCodeList.是否_是.getValue());
        model.addAttribute("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE")==null?"01": CacheUtil.getSysParam("IS_SHOW_TYPE"));
        //增加快捷录入 add by zhangnian 2019/10/15 start
        String finprodMarketId=getParam("finprodMarketId");
        boolean isQuickInput = Boolean.parseBoolean(getParam("isQuickInput"));
        if (isQuickInput&& StringUtil.isNotEmpty(finprodMarketId)){
          //  FinDepositTargetDto dto = finDepositTargetService.getBasicInfo(StringUtil.EMPTY,finprodMarketId);
//            if(null!=dto){
//                finprodId = dto.getFinprodId();
//            }
        }
        //来自于代办
        if(!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)){
            if(DictCodeListExt1.存款类资产类型_GZ_同业借款.getValue().equals(assetType)){
                //资产类型
                model.addAttribute("INT_ASSET_TYPE", DictCodeListExt1.金融产品类型_同业借款.getValue());
            }else{
                //资产类型
                model.addAttribute("INT_ASSET_TYPE", DictCodeListExt1.金融产品类型_存款.getValue());
            }
            FinDepositTargetDto finDepositTargetDto = finDepositTargetService.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute("entity", finDepositTargetDto);
        }else if(WorkFlowUtil.isNotFromToDoList()&& StringUtil.isNotEmpty(finprodId)) {
            if(DictCodeListExt1.存款类资产类型_GZ_同业借款.getValue().equals(assetType)){
                //资产类型
                model.addAttribute("INT_ASSET_TYPE", DictCodeListExt1.金融产品类型_同业借款.getValue());
            }else{
                //资产类型
                model.addAttribute("INT_ASSET_TYPE", DictCodeListExt1.金融产品类型_存款.getValue());
            }
            model.addAttribute("isTransInAsset", commonBusinessService.isTransInAsset(finprodId)!=0?true:false);
            //根据主键获取业务实体--标的金融产品基本信息
           FinDepositTargetDto finDepositTargetDto = finDepositTargetService.getBasicInfo(
                finprodId, StringUtil.EMPTY,assetType);
            //封装画面需要的FinDepositTargetDto
            //finDepositTargetDto = finDepositTargetService.encapFinDepositTargetDto(finDepositTargetDto);
            model.addAttribute("entity", finDepositTargetDto);
        } else if(WorkFlowUtil.isNotFromToDoList() && !StringUtil.isNotEmpty(workflowId)){//新建
            FinDepositTargetDto finDepositTargetDto = new FinDepositTargetDto();
            finDepositTargetDto.setReferRate(BigDecimal.ZERO);
            model.addAttribute("entity", finDepositTargetDto);
            //操作类型标识
            model.addAttribute("optType","new");
        }
        // 平台日期
        model.addAttribute("iPlatformDate", DateUtil.getSysDate());
      //取系统参数
       // model.addAttribute("CE_MODEL", CacheUtil.getSysParam(BussinessConstant.CEMODEL));
        model.addAttribute("pageId",this.getPageId());
        //去系统参数   是否以借款利率计提
//        model.addAttribute("isCalLoadrate", CacheUtil.getSysParam("IS_CAL_LOADRATE"));
//        model.addAttribute("isQuickInput",isQuickInput);
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
        ValidateGroupEdit.class}) FinDepositTargetDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
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
        ValidateGroupEdit.class}) FinDepositTargetDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
            dto = finDepositTargetService.getFinprodId(dto);
        }
        //存放同业、活期存放 F07
        if(DictCodeList.存款类资产类型_GZ_存放同业.getValue().equals(dto.getAssetType()) || DictCodeList.存款类资产类型_GZ_活期类资产.getValue().equals(dto.getAssetType())){
            dto.setFinprodType(DictCodeListExt1.金融产品类型_存款.getValue());
            dto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_存款.getValue());
        }else {//同业借款  F21
            dto.setFinprodType(DictCodeListExt1.金融产品类型_同业借款.getValue());
            dto.setFinprodType2(DictCodeListExt1.金融产品类型_同业借款.getValue());
        }

        WorkFlowParameter<FinDepositTargetDto> params = new WorkFlowParameter<FinDepositTargetDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getFinprodMarketId());
        params.setBusinessType(DictCodeList.共通业务类型_存款类资产信息.getValue());
        // 设置流程业务说明
        params.setComment(entity ->
            WorkFlowUtil.concatComment(this.getMessage("FinDepositTarget002.finprodId")+ GlobalConstant.OPEN_PARENTHESIS+entity.getFinprodMarketId()+ GlobalConstant.CLOSE_PARENTHESIS,
                this.getMessage("FinDepositTarget002.finprodName")+ GlobalConstant.OPEN_PARENTHESIS+entity.getFinprodName()+ GlobalConstant.CLOSE_PARENTHESIS,
                this.getMessage("FinDepositTarget002.vdate")+ GlobalConstant.OPEN_PARENTHESIS+ DateUtil.format(entity.getVdate())+ GlobalConstant.CLOSE_PARENTHESIS,
                this.getMessage("FinDepositTarget002.mdate")+ GlobalConstant.OPEN_PARENTHESIS+ DateUtil.format(entity.getMdate())+ GlobalConstant.CLOSE_PARENTHESIS));
        // 给参数变成ProcessParameter而非WorkFlowParameter
        ProcessParameter<FinDepositTargetDto> parameter = new ProcessParameter<FinDepositTargetDto>();
        parameter.setWorkFlowParameter(params);
        // 系统页面表IS_NEED_WORKFLOW 是否走流程，01-是，02-否
        //封装标的类金融产品基础组件需要的DTO
        //dto = finDepositTargetService.encapBaseFinProductBaseInfoList(dto);
        String msg = finDepositTargetService.start(parameter, dto);
        return new AjaxResult(msg);
    }


    /**
     * 获取核算分类
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping("getFinanceProductClass")
    public FinProductClass getFinanceProductClass(@RequestBody FinDepositTargetDto dto){
        FinProductClass fdto = new FinProductClass();
        //资产类型
        fdto.setAssetType(dto.getAssetType());

        if(DictCodeListExt1.存款类资产类型_GZ_同业借款.getValue().equals(dto.getAssetType())){
            //金融产品类型
            fdto.setFinprodType(DictCodeListExt1.金融产品类型_同业借款.getValue());
        }else{
            //金融产品类型
            fdto.setFinprodType(DictCodeListExt1.金融产品类型_存款.getValue());
        }
        //交易场所
        fdto.setOrgClass(dto.getOrgClass());
        //境内外
        fdto.setBlnArea(dto.getBlnArea());

        return finDepositTargetService.getFinanceProductClass(fdto);
    }

    /**
     * 获取税务分类
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping("getFinanceProductTaxClass")
    public FinDepositTargetDto getFinanceProductTaxClass(@RequestBody FinDepositTargetDto dto){
        FinProductClass fdto = new FinProductClass();
        //资产类型
        fdto.setAssetType(dto.getAssetType());

        if(DictCodeListExt1.存款类资产类型_GZ_同业借款.getValue().equals(dto.getAssetType())){
            //金融产品类型
            fdto.setFinprodType(DictCodeListExt1.金融产品类型_同业借款.getValue());
        }else{
            //金融产品类型
            fdto.setFinprodType(DictCodeListExt1.金融产品类型_存款.getValue());
        }
        dto.setTaxType(assetCommonService.getFinanceProductTaxClass(fdto));
        return dto;
    }

    /**
     * 存款机构
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    /*存借款机构匹配存放行和融资人属性 modified by lishuping 20191018 start*/
    @ResponseBody
    @RequestMapping("getCustomerName")
    public List<SelectOptionDto> getCustomerName(){
        Map<String,Object> param = RequestUtil.getPageParam();
        return finDepositTargetService.getCustomerName(param);
    }
    /*存借款机构匹配存放行和融资人属性 modified by lishuping 20191018 end*/
}
