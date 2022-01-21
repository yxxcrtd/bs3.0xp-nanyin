/*
 * 文件名：TrdBondInvestTrade001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Zane.Y
 * 修改时间：2018年9月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.assistaccout.controller;


import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.valuation.accounadjustmanage.assistaccout.dto.ValAssistAccounting001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.assistaccout.dto.ValAssistHandDto;
import com.joyintech.tams.valuation.accounadjustmanage.assistaccout.service.ValAssistAccounting001Service;
import com.joyintech.tams.valuation.component.business.service.CommonBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author  殷亚
 * @description 辅助核算配置管理       
 * @date 2022-1-12 14:53
 */
@Controller
@PageId("ValAssistAccounting001")
@RequestMapping("/valuation/accounadjustmanage/assistaccount/ValAssistAccounting001")
public class ValAssistAccounting001Controller extends BaseController {

    @Autowired
    private ValAssistAccounting001Service valAssistAccounting001Service;


    @Autowired
    private CommonBusinessService commonBusinessService;

    @Override
    public String init(Model model) {
        List<SelectOptionDto> booksetIds = valAssistAccounting001Service.findBooksets();
        String booksetId = null;
        if (booksetIds != null && booksetIds.size() > 0) {
            booksetId = booksetIds.get(0).getId();
        }
        // 所属账套控件初期化
        model.addAttribute("booksetIds", JsonUtils.object2Json(booksetIds));
        model.addAttribute("booksetId", booksetId);
        return super.init(model);
    }
    /**
     * 分页查询
     *
     * @return Page<TrdBondInvestTradeDto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<ValAssistAccounting001Dto> findPage()
        throws Exception {
        Page<ValAssistAccounting001Dto> list=valAssistAccounting001Service.findPage(RequestUtil.getPageParam());
        return list;
    }

    /**
     * 所属账套下拉框获取
     *
     * @return 返回的数据
     * @exception Exception 异常
     * @since 1.0
     */
    @RequestMapping("/findBooksets")
    @ResponseBody
    public List<SelectOptionDto> findBooksets() {
        return valAssistAccounting001Service.findBooksets();
    }

   /**
    * @author  殷亚
    * @description 获取内部科目编码定义
    * @date 2022-1-12 15:09
    * @return java.util.List<com.joyintech.framework.select.SelectOptionDto>
    */
    @RequestMapping("/getSubjectInteral")
    @ResponseBody
    public List<SelectOptionDto> getSubjectInteral() {
        Map<String, Object> param = RequestUtil.getPageParam();
        return commonBusinessService.getSubjectInteral(param);
    }

    /**
     * @author  殷亚
     * @description 获取内部科目编码定义 模糊搜索
     * @date 2022-1-12 15:09
     * @return java.util.List<com.joyintech.framework.select.SelectOptionDto>
     */
    @RequestMapping("/findSubjectInteral")
    @ResponseBody
    public List<SuggestDto> findSubjectInteral() {
        Map<String, Object> param = RequestUtil.getPageParam();
        return commonBusinessService.findSubjectInteral(param);
    }

    /**
     * @author  殷亚
     * @description 根据条件获取辅助核算项
     * @date 2022-1-19 17:01
     * @return java.util.List<com.joyintech.tams.valuation.accounadjustmanage.assistaccout.dto.ValAssistHandDto>
     */
    @RequestMapping("findHandsByCondition")
    @ResponseBody
    public List<ValAssistHandDto> findHandsByCondition() {
        Map<String, Object> param = RequestUtil.getParameters(getRequest());
        return valAssistAccounting001Service.findHandsByCondition(param);
    }



}
