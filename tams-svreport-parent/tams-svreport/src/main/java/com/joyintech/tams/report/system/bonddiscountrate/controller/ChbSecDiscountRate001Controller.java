package com.joyintech.tams.report.system.bonddiscountrate.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.*;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.system.bonddiscountrate.dto.ChbSecDiscountRateDto;
import com.joyintech.tams.report.system.bonddiscountrate.service.ChbSecDiscountRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@PageId("ChbSecDiscountRate001")
@RequestMapping("/svreport/system/bonddiscountrate/ChbSecDiscountRate001")
public class ChbSecDiscountRate001Controller extends BaseController {

    @Autowired
    private ChbSecDiscountRateService chbSecDiscountRateService;

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        super.setSelectOptionToModel(model, DictCode.中债债券类型);
        return super.init(model);
    }

    /**
     * 查询列表
     *
     * @return DtPage
     */
    @ResponseBody
    @RequestMapping("searchPage")
    public Page<ChbSecDiscountRateDto> search() {
        Map<String, Object> param = RequestUtil.getPageParam();
        return chbSecDiscountRateService.searchForPage(param);
    }

}
