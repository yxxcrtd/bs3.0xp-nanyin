package com.joyintech.service.valuation.trdassetlossmanage.trdassetlossbalance.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.service.valuation.trdassetlossmanage.trdassetlossbalance.dto.TrdAssetLossBalance002Dto;
import com.joyintech.service.valuation.trdassetlossmanage.trdassetlossbalance.service.TrdAssetLossBalance002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController("TrdAssetLossBalance002Ctr_dtx")
@RequestMapping("/TrdAssetLossBalance002")
public class TrdAssetLossBalance002Controller extends BaseController {

    @Autowired
    private TrdAssetLossBalance002Service trdAssetLossBalance002Service;

    @Override
    public String init(Model model) {
        return super.init(model);
    }

    @ResponseBody
    @RequestMapping("findPage/{page}/{length}")
    public Page<TrdAssetLossBalance002Dto> findPage(@RequestBody Map<String, Object> params,
            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        Page<TrdAssetLossBalance002Dto> p= trdAssetLossBalance002Service.queryDetailList(
                page,length, params);
        return p;
    }

}
