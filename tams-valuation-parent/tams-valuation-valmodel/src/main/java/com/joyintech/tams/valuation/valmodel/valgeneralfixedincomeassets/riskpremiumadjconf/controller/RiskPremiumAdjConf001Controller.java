package com.joyintech.tams.valuation.valmodel.valgeneralfixedincomeassets.riskpremiumadjconf.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.valmodel.valgeneralfixedincomeassets.riskpremiumadjconf.dto.RiskPremiumAdjConf001Dto;
import com.joyintech.tams.valuation.valmodel.valgeneralfixedincomeassets.riskpremiumadjconf.service.RiskPremiumAdjConf001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 资产减值管理
 * @author xutong
 * @createTime 2021-06-24
 */
@Controller
@PageId("RiskPremiumAdjConf001")

@RequestMapping("/valmodel/valgeneralfixedincomeassets/riskpremiumadjconf/RiskPremiumAdjConf001")
public class RiskPremiumAdjConf001Controller extends BaseController {

    @Autowired
    private RiskPremiumAdjConf001Service riskPremiumAdjConf001Service;

    @Override
    public String init(Model model) {

        super.setSelectOptionToModel(model, DictCode.资产类型, DictCode.证券品种);
        return super.init(model);
    }

    /**
     * 分页查询
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<RiskPremiumAdjConf001Dto> findPage() throws Exception {
        Map<String, Object> params= RequestUtil.getPageParam();
        return  riskPremiumAdjConf001Service.selectForPage(params);

    }

    /**
     * 资产代码（剔除同时不含赎回权和利率调整权的永续债、优先股、可转债、可交债）
     *
     * @param param 画面参数
     * @return 业务编号：下拉框
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getFinprodIdForRiskPremium")
    public List<SelectOptionDto> getFinprodIdForRiskPremium(@RequestParam Map<String, Object> params) throws Exception {
        return riskPremiumAdjConf001Service.getFinprodIdForRiskPremium(params);
    }
}
