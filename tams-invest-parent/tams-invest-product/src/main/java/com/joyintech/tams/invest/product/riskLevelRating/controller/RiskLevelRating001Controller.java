package com.joyintech.tams.invest.product.riskLevelRating.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.product.riskLevelRating.dto.RiskLevelRating001Dto;
import com.joyintech.tams.invest.product.riskLevelRating.service.RiskLevelRating001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @program: RiskLevelRating001Controller
 * @description: 产品风险等级评定
 * @author: jyl
 * @create: 2020-01-02
 **/
@Controller
@PageId("RiskLevelRating001")
@RequestMapping("/invest/product/riskLevelRating/RiskLevelRating001")
public class RiskLevelRating001Controller extends BaseController {
    @Autowired
    RiskLevelRating001Service riskLevelRating001Service;

    public String init(Model model) {
        return super.init(model);
    }

    /**
     * 搜索查找产品风险等级评定信息
     *
     * @return Page
     */
    @RequestMapping("/findPage")
    @ResponseBody
    public Page<RiskLevelRating001Dto> findPage() {
        Map<String, Object> map = RequestUtil.getPageParam();
        return riskLevelRating001Service.findPage(map);
    }

    /**
     * 搜索查找产品风险等级评定信息
     *
     * @return Page
     */
    @RequestMapping("/findId")
    @ResponseBody
    public Page<SelectOptionDto> findId() {
        Map<String, Object> map = RequestUtil.getPageParam();
        return riskLevelRating001Service.findId(map);
    }

    /**
     * 导出信息excel
     *
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
            throws Exception {
        return riskLevelRating001Service.export(this.getPageId(), RequestUtil.getPageParam());
    }
}
