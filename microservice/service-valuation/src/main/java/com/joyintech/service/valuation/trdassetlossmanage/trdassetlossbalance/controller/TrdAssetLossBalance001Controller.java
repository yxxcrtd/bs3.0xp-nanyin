package com.joyintech.service.valuation.trdassetlossmanage.trdassetlossbalance.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.service.valuation.trdassetlossmanage.trdassetlossbalance.dto.TrdAssetLossBalance001Dto;
import com.joyintech.service.valuation.trdassetlossmanage.trdassetlossbalance.service.TrdAssetLossBalance001Service;
import com.joyintech.tams.constant.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController("TrdAssetLossBalance001Ctr_dtx")
@RequestMapping("/TrdAssetLossBalance001")
public class TrdAssetLossBalance001Controller extends BaseController {

    @Autowired
    private TrdAssetLossBalance001Service trdAssetLossBalance001Service;

    @Override
    public String init(Model model) {
        return super.init(model);
    }

    /**
     * check
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) TrdAssetLossBalance001Dto dto, BindingResult br) throws Exception{
        if (br != null && br.hasErrors()) {
            return new AjaxResult(br);
        }
        AjaxResult ajaxResult = new AjaxResult();
        return ajaxResult;
    }

    /**
     * 画面查询方法
     * @return 所有数据
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage/{page}/{length}")
    public Page<TrdAssetLossBalance001Dto> findPage(@RequestBody Map<String, Object> params,
            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        params.put("PROFITTYPE_VALUE_02", DictCodeListExt2.收益类型_预期收益.getValue());
        params.put("TYPE_TOUGUAN_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        params.put("TYPE_TOUGUAN_F07", DictCodeListExt1.金融产品类型_投管用_存款.getValue());
        params.put("TYPE_TOUGUAN_F17", DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
        params.put("TYPE_TOUGUAN_F18", DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
        Page<TrdAssetLossBalance001Dto> p= trdAssetLossBalance001Service.queryPageList(
                page,length,params);
        return p;
    }

    /**
     * 查询资产代码
     */
    @ResponseBody
    @RequestMapping("getFinprodId/{page}/{length}")
    public Page<SelectOptionDto> getFinprodId(@RequestBody Map<String, Object> params,
                                                @PathVariable("page") int page, @PathVariable("length") int length){
        params.put("PROFITTYPE_VALUE_02", DictCodeListExt2.收益类型_预期收益.getValue());
        params.put("TYPE_TOUGUAN_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        params.put("TYPE_TOUGUAN_F07", DictCodeListExt1.金融产品类型_投管用_存款.getValue());
        params.put("TYPE_TOUGUAN_F17", DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
        params.put("TYPE_TOUGUAN_F18", DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
        Page<SelectOptionDto> getFinprodId = trdAssetLossBalance001Service.getFinprodId(params,page, length);
        return getFinprodId;
    }

    /**
     * 查询账套代码
     */
    @ResponseBody
    @RequestMapping("getBooksetId/{page}/{length}")
    public Page<SelectOptionDto> getBooksetId(@RequestBody Map<String, Object> params,
                                              @PathVariable("page") int page, @PathVariable("length") int length){
        Page<SelectOptionDto> getBooksetId = trdAssetLossBalance001Service.getBooksetId(params,page, length);
        return getBooksetId;
    }

    /**
     * 导出数据查询
     */
    @ResponseBody
    @RequestMapping("getAllList")
    public List<TrdAssetLossBalance001Dto> getAllList(@RequestBody Map<String, Object> params){
        List<TrdAssetLossBalance001Dto> allList = trdAssetLossBalance001Service.getAllList(params);
        return allList;
    }

}

