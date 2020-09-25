package com.joyintech.tams.invest.product.riskLevelRating.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.product.riskLevelRating.dto.RiskLevelRating001Dto;
import com.joyintech.tams.invest.product.riskLevelRating.dto.RiskLevelRating002Dto;
import com.joyintech.tams.invest.product.riskLevelRating.service.RiskLevelRating001Service;
import com.joyintech.tams.invest.product.riskLevelRating.service.RiskLevelRating002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @program: RiskLevelRating003Controller
 * @description: 产品风险等级评定底层资产风险等级评定界面
 * @author: jyl
 * @create: 2020-01-02
 **/
@Controller
@PageId("RiskLevelRating003")
@RequestMapping("/invest/product/riskLevelRating/RiskLevelRating003")
public class RiskLevelRating003Controller extends BaseController {
    @Autowired
    RiskLevelRating002Service riskLevelRating002Service;

    public String init(Model model) {
        setSelectOptionToModel(model, DictCode.资产风险等级2, DictCode.资产二级分类, DictCode.资产三级分类
                , DictCode.资产四级分类, DictCode.资产五级分类,DictCode.平安资产类型);
        String finProductCode = this.getParam("finProductCode");
        String generateTime = this.getParam("generateTime");
        if(generateTime==""||generateTime=="null" ||generateTime==null){
            generateTime= DateUtil.getSysDate();
        }
        model.addAttribute("finProductCode",finProductCode);
        model.addAttribute("generateTime",generateTime);
        return super.init(model);
    }

    /**
     * 搜索查找产品风险等级评定信息
     *
     * @return Page
     */
    @RequestMapping("/findList")
    @ResponseBody
    public List<RiskLevelRating002Dto> findList() {
        Map<String, Object> map = RequestUtil.getPageParam();
        List<RiskLevelRating002Dto> findList=riskLevelRating002Service.findList(map).getRiskList();
        return findList;
    }

}
