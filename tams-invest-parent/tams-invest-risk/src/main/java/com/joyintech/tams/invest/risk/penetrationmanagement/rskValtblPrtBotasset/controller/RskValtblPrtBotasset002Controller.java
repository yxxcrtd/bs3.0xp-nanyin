/*
 * 文件名：RskPortfolioasset002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年1月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.penetrationmanagement.rskValtblPrtBotasset.controller;
 
import java.util.Map; 
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil; 
import com.joyintech.tams.invest.risk.penetrationmanagement.rskValtblPrtBotasset.dto.RskValtblPrtBotassetMapDto;
import com.joyintech.tams.invest.risk.penetrationmanagement.rskValtblPrtBotasset.service.RskValtblPrtBotassetService;

/**
 *  底层资产映射配置
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年1月2日
 * @see RskValtblPrtBotasset002Controller
 * @since 1.0
 */
@Controller
@PageId("RskValtblPrtBotasset002")
@RequestMapping("/invest/risk/penetrationmanagement/rskValtblPrtBotasset/RskValtblPrtBotasset002")
public class RskValtblPrtBotasset002Controller extends BaseController{

    @Autowired
    private RskValtblPrtBotassetService rskValtblPrtBotassetService;
    @Override
    public String init(Model model) {
        model.addAttribute("ptlId", this.getParam("ptlId"));
        model.addAttribute("ptlName", this.getParam("ptlName"));
        model.addAttribute("subCode", this.getParam("subCode"));
        model.addAttribute("subName", this.getParam("subName"));
        model.addAttribute("subClass", this.getParam("subClass"));
        model.addAttribute("finprodId", this.getParam("finprodId"));
        model.addAttribute("tblMarketId", this.getParam("tblMarketId"));
        if(StringUtil.equals(this.getParam("subClass"), DictCodeListExt1.资产科目类型_债券.getValue())){
            model.addAttribute("finprodType2", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        }else if(StringUtil.equals(this.getParam("subClass"), DictCodeListExt1.资产科目类型_基金.getValue())){
            model.addAttribute("finprodType2", DictCodeListExt1.金融产品类型_投管用_基金.getValue());
        }
        else if(StringUtil.equals(this.getParam("subClass"), DictCodeListExt1.资产科目类型_股票.getValue())){
            model.addAttribute("finprodType2", DictCodeListExt1.金融产品类型_投管用_股票.getValue());
        }
        else if(StringUtil.equals(this.getParam("subClass"), DictCodeListExt1.资产科目类型_非标.getValue())){
            model.addAttribute("finprodType2", DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
        }else if(StringUtil.equals(this.getParam("subClass"), DictCodeListExt1.资产科目类型_存款类.getValue())){
            model.addAttribute("finprodType2", DictCodeListExt1.金融产品类型_投管用_存款.getValue());
        }else if(StringUtil.equals(this.getParam("subClass"), DictCodeListExt1.资产科目类型_项目类.getValue())){
            model.addAttribute("finprodType2", DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
        }
        model.addAttribute("finprodMarketId", this.getParam("finprodMarketId"));
        return super.init(model);
    }
    
    /**
     * 选择资产下拉框处理
     * <功能详细描述>
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("selectAllAsset")
    public Page<SelectOptionDto> selectAllAsset(){
        Map<String,Object> param = RequestUtil.getPageParam();
        return rskValtblPrtBotassetService.selectAllAsset(param);
    }
    
    @ResponseBody
    @RequestMapping("selectAssetInfoById")
    public RskValtblPrtBotassetMapDto selectAssetInfoById(@RequestBody RskValtblPrtBotassetMapDto dto){
        return rskValtblPrtBotassetService.selectAssetInfoById(dto);
    }
    
    /**
     * 更新(取消)映射关系
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(PAGE_SAVE)
    public AjaxResult save(@RequestBody RskValtblPrtBotassetMapDto dto) {
        rskValtblPrtBotassetService.update(dto);
        return new AjaxResult(getMessage("BUSS_COM_E_0033"));
    }
    
}
