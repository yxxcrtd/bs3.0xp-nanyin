/*
 * 文件名：RskValtblPrtBotasset003Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年1月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.penetrationmanagement.rskValtblPrtBotasset.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.common.dto.BaseFinProductBaseInfoDto;
import com.joyintech.tams.component.business.common.dto.InterfPayhsrcsecinfoDto;
import com.joyintech.tams.component.business.common.service.CommonAutoCouponService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.risk.penetrationmanagement.rskValtblPrtBotasset.dto.RskValtblPrtBotassetMapDto;
import com.joyintech.tams.invest.risk.penetrationmanagement.rskValtblPrtBotasset.service.RskValtblPrtBotassetService;

/**
 *  底层资产映射应用
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年1月10日
 * @see RskValtblPrtBotasset003Controller
 * @since 1.0
 */
@Controller
@PageId("RskValtblPrtBotasset003")
@RequestMapping("/invest/risk/penetrationmanagement/rskValtblPrtBotasset/RskValtblPrtBotasset003")
public class RskValtblPrtBotasset003Controller extends BaseController{

    @Autowired
    private RskValtblPrtBotassetService rskValtblPrtBotassetService;
    
    @Autowired
    private CommonAutoCouponService commonAutoCouponService;
    @Override
    public String init(Model model) {
        model.addAttribute("ptlId", this.getParam("ptlId"));
        model.addAttribute("ptlName", this.getParam("ptlName"));
        model.addAttribute("subCode", this.getParam("subCode"));
        model.addAttribute("subName", this.getParam("subName"));
        model.addAttribute("subClass", this.getParam("subClass"));
        model.addAttribute("finprodId", this.getParam("finprodId"));
        model.addAttribute("finprodMarketId", this.getParam("finprodMarketId"));
        model.addAttribute("assetSource", this.getParam("assetSource"));
        model.addAttribute("tblMarketId", this.getParam("tblMarketId"));
        model.addAttribute("countForValUnmapped", getCountForValUnmapped(this.getParam("ptlId"),this.getParam("subCode")));
        model.addAttribute("countForValDiffmapped", getCountForValDiffmapped(this.getParam("ptlId"),this.getParam("subCode"),this.getParam("finprodId")));
        return super.init(model);
    }
    
    private Integer getCountForValUnmapped(String ptlId,String subCode){
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("ptlId", ptlId);
        paramMap.put("subCode", subCode);
        return rskValtblPrtBotassetService.getCountForValUnmapped(paramMap);
    }
    
    private Integer getCountForValDiffmapped(String ptlId,String subCode,String finprodId){
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("ptlId", ptlId);
        paramMap.put("subCode", subCode);
        paramMap.put("finprodId", finprodId);
        return rskValtblPrtBotassetService.getCountForValDiffmapped(paramMap);
    }
    
    /**
     * 应用未映射
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("applyMapForValUnmapped")
    public AjaxResult applyMapForValUnmapped(@RequestBody RskValtblPrtBotassetMapDto dto) {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("ptlId", dto.getPtlId());
        paramMap.put("subCode", dto.getSubCode());
        paramMap.put("tblMarketId", dto.getTblMarketId());
        //需要自动加券
        if(!StringUtil.equals(dto.getAssetSource(), DictCodeListExt1.估值资产来源_系统内.getValue())){
            InterfPayhsrcsecinfoDto interfPayhsrcsecinfoDto = callbackAutoBondCoupon(dto);
            if(null!=interfPayhsrcsecinfoDto.getBaseFinProductBaseInfoList()
                &&!interfPayhsrcsecinfoDto.getBaseFinProductBaseInfoList().isEmpty()){
                    for(BaseFinProductBaseInfoDto baseFinProductBaseInfoDto:interfPayhsrcsecinfoDto.getBaseFinProductBaseInfoList()){
                        paramMap.put("finprodId", baseFinProductBaseInfoDto.getFinProduct().getFinprodId());
                        //更新底层映射表----非系统内资产代码更新至系统内代码
                        dto.setFinprodId(baseFinProductBaseInfoDto.getFinProduct().getFinprodId());
                        dto.setAssetSource(DictCodeListExt1.估值资产来源_系统内.getValue());
                        rskValtblPrtBotassetService.update(dto);
                        break;
                    }
            }
        }else{
            paramMap.put("finprodId", dto.getFinprodId());
        }
        rskValtblPrtBotassetService.applyMapForValUnmapped(paramMap);
        return new AjaxResult(getMessage("BUSS_COM_E_0033"));
    }
    
    /**
     * 应用不同映射
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("applyMapForValDiffmapped")
    public AjaxResult applyMapForValDiffmapped(@RequestBody RskValtblPrtBotassetMapDto dto) {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("ptlId", dto.getPtlId());
        paramMap.put("subCode", dto.getSubCode());
        //需要自动加券
        if(!StringUtil.equals(dto.getAssetSource(), DictCodeListExt1.估值资产来源_系统内.getValue())){
            InterfPayhsrcsecinfoDto interfPayhsrcsecinfoDto = callbackAutoBondCoupon(dto);
            if(null!=interfPayhsrcsecinfoDto.getBaseFinProductBaseInfoList()
                &&!interfPayhsrcsecinfoDto.getBaseFinProductBaseInfoList().isEmpty()){
                    for(BaseFinProductBaseInfoDto baseFinProductBaseInfoDto:interfPayhsrcsecinfoDto.getBaseFinProductBaseInfoList()){
                        paramMap.put("finprodId", baseFinProductBaseInfoDto.getFinProduct().getFinprodId());
                        //更新底层映射表----非系统内资产代码更新至系统内代码
                        dto.setFinprodId(baseFinProductBaseInfoDto.getFinProduct().getFinprodId());
                        dto.setAssetSource(DictCodeListExt1.估值资产来源_系统内.getValue());
                        break;
                    }
            }
        }else{
            paramMap.put("finprodId", dto.getFinprodId());
        }
        rskValtblPrtBotassetService.applyMapForValDiffmapped(paramMap);
        return new AjaxResult(getMessage("BUSS_COM_E_0033"));
    }
    
    /**
     * 回调自动加券
     * <功能详细描述>
     * @see
     * @since 1.0
     */
    private InterfPayhsrcsecinfoDto callbackAutoBondCoupon(RskValtblPrtBotassetMapDto dto){
        InterfPayhsrcsecinfoDto interfPayhsrcsecinfoDto = new InterfPayhsrcsecinfoDto();
        List<String> isAutoCouponObjIdList = new ArrayList<String>();
        isAutoCouponObjIdList.add(dto.getFinprodId());
        interfPayhsrcsecinfoDto.setSecidList(isAutoCouponObjIdList);
        interfPayhsrcsecinfoDto = commonAutoCouponService.autoBondCoupon(interfPayhsrcsecinfoDto);
        //通过secId select wind objectid
        /*RskValtblPrtBotassetMapDto rskValtblPrtBotassetMapDto = rskValtblPrtBotassetService.selectWindBondAssetById(dto.getFinprodId());
        if(null!=rskValtblPrtBotassetMapDto){
            List<String> isAutoCouponObjIdList = new ArrayList<String>();
            isAutoCouponObjIdList.add(rskValtblPrtBotassetMapDto.getObjectId());
            interfPayhsrcsecinfoDto.setSecidList(isAutoCouponObjIdList);
            interfPayhsrcsecinfoDto = commonAutoCouponService.autoBondCoupon(interfPayhsrcsecinfoDto);
        }*/
        return interfPayhsrcsecinfoDto;
    }
    
}
