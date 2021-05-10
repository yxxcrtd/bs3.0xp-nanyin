/*
 * 文件名：ValSubDevelopment001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：zwq
 * 修改时间：2020年12月31日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valaccountencryption.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.BokDetailId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.tams.valuation.component.common.constant.BussinessDictCode;
import com.joyintech.tams.valuation.component.common.constant.BussinessDictCodeList;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valaccountencryption.dto.ValAccountEncryption001Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@PageId("ValAccountEncryption001")
@RequestMapping("/valuation/dailyoperation/dailyaccountmake/valaccountencryption/ValAccountEncryption001")
public class ValAccountEncryption001Controller extends BaseController {

    @Autowired
    private InterfCommonService interfCommonService;
    /**
     * 重写init方法，加载页面
     */
    @Override
    public String init(Model model) {
//        this.setSelectOptionToModel(model, BussinessDictCode.金融产品类型);
        this.setSelectOptionToModel(model, BussinessDictCode.收益类型);
        this.setSelectOptionToModel(model, BussinessDictCode.投资目的);
        this.setSelectOptionToModel(model, BussinessDictCode.币种);
        
        SelectDto selectDto = this.getSelectDataFromDataDic(DictCode.金融产品类型, null);
        List<SelectOptionDto> options = selectDto.getOptions();
        if(options==null){
        	options = new ArrayList<SelectOptionDto>();
        }
        selectDto = this.getSelectDataFromDataDic(DictCode.资金账户类型, null);
        if(selectDto.getOptions()!=null && selectDto.getOptions().size()>0){
        	options.addAll(selectDto.getOptions());
        }
        model.addAttribute(DictCode.金融产品类型.getValue(), JsonUtils.objectArray2Json(options.toArray()));

        return super.init(model);
    }

    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody ValAccountEncryption001Dto dto){
        AjaxResult ajaxResult = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<>();

        if (!StringUtil.isNotEmpty(dto.getBusiDetailId())){
            errorList.add(new ValidMessage("业务明细代码不可为空！"));
        }
        if (!StringUtil.isNotEmpty(dto.getBusiDetailType())){
            errorList.add(new ValidMessage("业务明细类型不可为空！"));
        }
        if (StringUtil.equals(dto.getBusiDetailType(), BussinessDictCodeList.金融产品类型_债券.getValue()) && !StringUtil.isNotEmpty(dto.getInvAim())){
            errorList.add(new ValidMessage("业务明细类型为债券时，投资目的不可为空！"));
        }
        if (StringUtil.equals(dto.getBusiDetailType(),BussinessDictCodeList.金融产品类型_计划类资产.getValue()) && !StringUtil.isNotEmpty(dto.getProfitType())){
            errorList.add(new ValidMessage("业务明细类型为计划类资产时，收益类型不可为空！"));
        }
        if (StringUtil.equals(dto.getBusiDetailType(),BussinessDictCodeList.金融产品类型_计划类资产.getValue()) && StringUtil.equals(dto.getProfitType(),BussinessDictCodeList.收益类型_预期收益.getValue()) && !StringUtil.isNotEmpty(dto.getInvAim())){
            errorList.add(new ValidMessage("业务明细类型为计划类资产、收益类型为预期收益型时，投资目的不可为空！"));
        }
        if (StringUtil.equals(dto.getBusiDetailType(),BussinessDictCodeList.金融产品类型_理财子产品.getValue()) && dto.getVdate() == null){
            errorList.add(new ValidMessage("业务明细类型为理财子产品时，理财子产品起息日不可为空！"));
        }
        if (isBusiDetailType(dto) && !StringUtil.isNotEmpty(dto.getCcy())){
            errorList.add(new ValidMessage("业务明细类型为资金账户类型时，币种不可为空！"));
        }

        ajaxResult.setErrorList(errorList);
//        if (errorList.size()>0){
//            return ajaxResult;
//        }
        return ajaxResult;
    }

    @ResponseBody
    @RequestMapping("submit")
    public AjaxResult submit(@RequestBody ValAccountEncryption001Dto dto){
        AjaxResult ajaxResult = new AjaxResult();
        long branch = 0;
        if (StringUtil.equals(BussinessDictCodeList.金融产品类型_理财子产品.getValue(),dto.getBusiDetailType())){
            branch = Long.valueOf(new SimpleDateFormat("yyyyMMdd").format(dto.getVdate()));
        }

        //调用共通设计->【详细设计-接口共通方法设计.xlsx】->组装账务明细(资金帐户-现金类)SHEET
        BokDetailId bokDetailId = interfCommonService.getBokDetailId(dto.getBusiDetailId(), null, dto.getBusiDetailType(), dto.getInvAim(), branch, dto.getCcy());
        dto.setBokDetailId(bokDetailId.getBokDetailId());
        ajaxResult.setData(dto);
        ajaxResult.setMessage("生成成功！");
        return ajaxResult;
    }

    /**
     * 判断业务明细类型 是否为 (数据字典：资金账户类型)
     */
    private boolean isBusiDetailType(ValAccountEncryption001Dto dto){
        List<String> busiDetailTypeList = new ArrayList<>();
        busiDetailTypeList.add(BussinessDictCodeList.资金账户类型_托管账户.getValue());
        busiDetailTypeList.add(BussinessDictCodeList.资金账户类型_销售费账户.getValue());
        busiDetailTypeList.add(BussinessDictCodeList.资金账户类型_托管费账户.getValue());
        busiDetailTypeList.add(BussinessDictCodeList.资金账户类型_管理费账户.getValue());
        busiDetailTypeList.add(BussinessDictCodeList.资金账户类型_交易对手账户.getValue());
        busiDetailTypeList.add(BussinessDictCodeList.资金账户类型_通道方账户.getValue());
        busiDetailTypeList.add(BussinessDictCodeList.资金账户类型_交易所资金账户.getValue());
        busiDetailTypeList.add(BussinessDictCodeList.资金账户类型_甲类户.getValue());
        busiDetailTypeList.add(BussinessDictCodeList.资金账户类型_上清所DVP账户.getValue());
        busiDetailTypeList.add(BussinessDictCodeList.资金账户类型_中债登DVP账户.getValue());
        busiDetailTypeList.add(BussinessDictCodeList.资金账户类型_赎回过渡户.getValue());
        for (String type:busiDetailTypeList){
            if (StringUtil.equals(type,dto.getBusiDetailType())){
                return true;
            }
        }
        return false;
    }
}
