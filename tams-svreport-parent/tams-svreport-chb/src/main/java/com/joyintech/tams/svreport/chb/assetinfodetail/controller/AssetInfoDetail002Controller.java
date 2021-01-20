/*
 * 文件名：ReportPageManage002Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：jiangsucheng
 * 修改时间：2018年11月6日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.svreport.chb.assetinfodetail.controller;

import com.alibaba.fastjson.JSONObject;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.ChbAssetInfo;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.AssetInfoDetailDto;
import com.joyintech.tams.svreport.chb.assetinfodetail.service.AssetInfoDetailMService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author jiangsucheng
 * @version 1.0 2018年11月6日
 * @see ReportPageManage002Controller
 * @since 1.0BussFinBondRepInfo
 */
@PageId("AssetInfoDetail002")
@Controller
@RequestMapping("/chb/assetinfodetail/AssetInfoDetail002")
public class AssetInfoDetail002Controller extends BaseController {

    /**
     * 资产要素service
     */
    @Autowired
    private AssetInfoDetailMService assetInfoDetailMService;

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public String init(Model model){
        super.setSelectOptionToModel(model, DictCode.中债对应上游资产一级类型,DictCode.资产_负债类别
                ,DictCode.中债对应上游资产二级类型, DictCode.资产_负债二级类别,DictCode.WIND二级分类_含债券二级分类等, DictCode.报送反馈状态,
                DictCode.交易流通场所_中债,DictCode.币种_中债,DictCode.行内资产二级分类,DictCode.行内资产三级分类,
                DictCode.行内资产四级分类, DictCode.行内资产五级分类);

        String assetUuid = this.getParam("assetUuid");
        String dataStatus = this.getParam("dataStatus");
        String issuepartyid= SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_ISSUE_PARTY_ID);

        //资产编号不为空则为修改.

        String jsonObject= JSONObject.toJSONString(assetInfoDetailMService.getDataListLink());
        model.addAttribute("AssetTypeListLink", jsonObject);
        AssetInfoDetailDto  dto=new   AssetInfoDetailDto();
        dto.setIssuePartyId(issuepartyid);
        model.addAttribute(GlobalConstant.ENTITY, dto);
        if(StringUtil.isNotEmpty(assetUuid)&&StringUtil.isNotEmpty(dataStatus)){
              dto = assetInfoDetailMService.getAssetInfoDetail(assetUuid,dataStatus);
            model.addAttribute(GlobalConstant.ENTITY, dto);
        }
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
            ValidateGroupEdit.class}) AssetInfoDetailDto dto, BindingResult result) {
        return commonCheck(result, "save");
    }
    @ResponseBody
    @RequestMapping(value = PAGE_SAVE, method = RequestMethod.POST)
    public AjaxResult doAdd(@RequestBody AssetInfoDetailDto dto) throws Exception {
        assetInfoDetailMService.doSave(dto);
        return new AjaxResult("保存成功");
    }
    @ResponseBody
    @RequestMapping("getValue")
    public BigDecimal getValue(@RequestBody Map<String, Object> param) {
        return assetInfoDetailMService.getTargetAmt(param);
    }
}
