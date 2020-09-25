/*
 * 文件名：ChannelAsset002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年3月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.asset.channel.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.component.business.prod.dto.BussFinProductFeeInfoDto;
import com.joyintech.tams.invest.asset.channel.dto.ChannelAssetDto;
import com.joyintech.tams.invest.asset.channel.service.ChannelAssetService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 *  通道维护
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年3月19日
 * @see ChannelAsset002Controller
 * @since 1.0
 */
@Controller
@PageId("ChannelAsset002")
@RequestMapping("/invest/asset/channel/ChannelAsset002")
public class ChannelAsset002Controller extends BaseController{

    @Autowired
    private ChannelAssetService channelAssetService;
    
    @Autowired
    private CommonBusinessService commonBusinessService;
    
    private static final IDictCode[] DICT_CODES = new DictCode[] {DictCode.划款方式,DictCode.收取方式};
   
    @Override
    public String init(Model model){
      //初始化基本信息画面数据字典
        setSelectOptionToModel(model, DICT_CODES);
        String workflowId = this.getParam("workflowId");
        String finprodId = this.getParam("finprodId");
        model.addAttribute("FINPROD_TYPE_F28", DictCodeListExt1.金融产品类型_投管用_通道.getValue());
        model.addAttribute("workflowId", workflowId);
      //不来自于代办
        if(WorkFlowUtil.isNotFromToDoList()&& StringUtil.isNotEmpty(finprodId)) {
           model.addAttribute("isTransInAsset", commonBusinessService.isTransInAsset(finprodId)!=0?true:false);
          //根据主键获取业务实体--标的金融产品基本信息
           ChannelAssetDto channelAssetDto = channelAssetService.getBasicInfo(finprodId);
           if(null != channelAssetDto){
        	   channelAssetDto.setFjId(channelAssetDto.getFinprodId());
           }
            model.addAttribute("entity", channelAssetDto);
        }//来自于代办
        else if(StringUtil.isNotEmpty(workflowId)){
            //根据工作流与留痕处理提供的共通方法获取业务实体
            ChannelAssetDto channelAssetDto = channelAssetService.getBussinessEntityByWorkflowId(workflowId);
            //设置到model中
            model.addAttribute("entity", channelAssetDto);
        }else {
        	//add by yanya 20190903
        	ChannelAssetDto channelAssetDto = new ChannelAssetDto();
        	channelAssetDto.setFjId("fj" + System.currentTimeMillis());
        	model.addAttribute("entity", channelAssetDto);
        }
        model.addAttribute("finprodId", finprodId);
        return super.init(model);
    }
    
    @ResponseBody
    @RequestMapping("/getAcctNo")
    public Page<SelectOptionDto> getAcctNo(){
        Map<String,Object>map = RequestUtil.getPageParam();
        Page<SelectOptionDto> temp = channelAssetService.getAcctNo(map);
        return temp;
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
        ValidateGroupEdit.class}) ChannelAssetDto dto, BindingResult result) {
        //通道费用列表单独验证
        if(result.getErrorCount()<=0){
            AjaxResult ar = new AjaxResult(result);
            List<ValidMessage> validList = new ArrayList<ValidMessage>();
            List<BussFinProductFeeInfoDto> bussFinProductFeeInfoDtoList = dto.getBussFinProductFeeInfoDto().getBussFinProductFeeInfoDtoList();
            if (!StringUtil.isEmptyList(bussFinProductFeeInfoDtoList)) {
                for (int i = 0; i < bussFinProductFeeInfoDtoList.size(); i++ ) {
                    BussFinProductFeeInfoDto finProductFeeInfoDto = bussFinProductFeeInfoDtoList.get(
                        i);
                    if (!StringUtil.isNotEmpty(finProductFeeInfoDto.getFeeId())) {
                        validList.add(
                            new ValidMessage("bussFinProductFeeInfoDtoList[" + (i) + "].feeId",
                                getMessage("BUSS_COM_E_0038",
                                    getMessage("BussFinProductFeeInfo.feeName"))));
                    }
                    /*if (!StringUtil.isNotEmpty(finProductFeeInfoDto.getFeeType())) {
                        validList.add(
                            new ValidMessage("bussFinProductFeeInfoDtoList[" + (i) + "].feeType",
                                getMessage("BUSS_COM_E_0038",
                                    getMessage("BussFinProductFeeInfo.feeType"))));
                    }*/
                    if (!StringUtil.isNotEmpty(finProductFeeInfoDto.getFeeRate())) {
                        validList.add(
                            new ValidMessage("bussFinProductFeeInfoDtoList[" + (i) + "].feeRate",
                                getMessage("BUSS_COM_E_0038",
                                    getMessage("BussFinProductFeeInfo.feeRate"))));
                    }
                   /* if (StringUtil.isEmpty(finProductFeeInfoDto.getCollectType())) {
                        validList.add(
                            new ValidMessage("bussFinProductFeeInfoDtoList[" + (i) + "].collectType",
                                getMessage("BUSS_COM_E_0038",
                                    getMessage("BussFinProductFeeInfo.collectType"))));
                    }*/
                    /*if (!StringUtil.isNotEmpty(finProductFeeInfoDto.getBasicBill())) {
                        validList.add(
                            new ValidMessage("bussFinProductFeeInfoDtoList[" + (i) + "].basicBill",
                                getMessage("BUSS_COM_E_0038",
                                    getMessage("BussFinProductFeeInfo.basicBill"))));
                    }
                    if (!StringUtil.isNotEmpty(finProductFeeInfoDto.getBasis())) {
                        validList.add(new ValidMessage(
                            "bussFinProductFeeInfoDtoList[" + (i) + "].basis",
                            getMessage("BUSS_COM_E_0038", getMessage("BussFinProductFeeInfo.basis"))));
                    }*/
                    if (!StringUtil.isNotEmpty(finProductFeeInfoDto.getNetAssetValueDay())) {
                        validList.add(new ValidMessage(
                            "bussFinProductFeeInfoDtoList[" + (i) + "].netAssetValueDay",
                            getMessage("BUSS_COM_E_0038", getMessage("BussFinProductFeeInfo.netAssetValueDay"))));
                    }
                    if(null!=validList&&!validList.isEmpty()){
                        break;
                    }
                }
                // }
                // 判断费用类型是否重复
                Map<String, BussFinProductFeeInfoDto> goalInfoMap = bussFinProductFeeInfoDtoList.stream().collect(
                    Collectors.toMap(BussFinProductFeeInfoDto::getFeeType, a -> a, (k1, k2) -> k1));
                if (goalInfoMap.size() != bussFinProductFeeInfoDtoList.size()) {
                    validList.add(new ValidMessage("bussFinProductFeeInfoDtoList",
                        this.getMessage("FIN_PROD_E_0076")));
                }
                if(validList.size()>0){
                    ar.setErrorList(validList);
                    return ar;  
                }
            }
        }
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
        ValidateGroupEdit.class}) ChannelAssetDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        //生成金融产品代码
        if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
            dto = channelAssetService.getFinprodId(dto);
        }
        
        dto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_通道.getValue());
        WorkFlowParameter<ChannelAssetDto> params = new WorkFlowParameter<ChannelAssetDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getFinprodMarketId());
        params.setBusinessType(DictCodeList.共通业务类型_通道管理.getValue());
        // 设置流程业务说明
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment(getMessage("invest.channelId")+getMessage("ChannelAsset002.finprodIdBak")+
                GlobalConstant.OPEN_PARENTHESIS+entity.getFinprodMarketId(), GlobalConstant.CLOSE_PARENTHESIS+
                getMessage("invest.channelId")+getMessage("ChannelAsset002.finprodNameBak")+GlobalConstant.OPEN_PARENTHESIS+
                entity.getFinprodName()+GlobalConstant.CLOSE_PARENTHESIS);
        });
        // 给参数变成ProcessParameter而非WorkFlowParameter
        ProcessParameter<ChannelAssetDto> parameter = new ProcessParameter<ChannelAssetDto>();
        parameter.setWorkFlowParameter(params);
        // 系统页面表IS_NEED_WORKFLOW 是否走流程，01-是，02-否
        String msg = channelAssetService.start(parameter, dto);
        return new AjaxResult(msg);
    }
    
    
}
