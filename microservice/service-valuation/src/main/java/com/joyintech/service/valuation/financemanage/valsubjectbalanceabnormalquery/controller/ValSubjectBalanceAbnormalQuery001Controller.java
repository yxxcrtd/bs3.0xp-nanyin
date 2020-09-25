/*
 * 文件名：ValSubjectBalanceAbnormalQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2019年1月28日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.financemanage.valsubjectbalanceabnormalquery.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.valuation.accounadjustmanage.assetbook.service.ValAssetBook001Service;
import com.joyintech.service.valuation.financemanage.valsubjectbalanceabnormalquery.dto.ValSubjectBalanceAbnormalQuery001Dto;
import com.joyintech.service.valuation.financemanage.valsubjectbalanceabnormalquery.service.ValSubjectBalanceAbnormalQuery001Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.common.service.BaseCommonService;

/**
 * 到期产品科目余额异常查询
 * 
 * @author niuqianyu
 * @version 1.0 2019年1月28日
 * @see ValSubjectBalanceAbnormalQuery001Controller
 * @since 1.0
 */
@RestController
@RequestMapping("/ValSubjectBalanceAbnormalQuery001")
public class ValSubjectBalanceAbnormalQuery001Controller extends BaseController {

    @Autowired
    private ValAssetBook001Service valAssetBook001Service;

    @Autowired
    private ValSubjectBalanceAbnormalQuery001Service valSubjectBalanceAbnormalQuery001Service;

    @Autowired
    private BaseCommonService baseCommonService;

    /**
     * 重写init方法，加载页面 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        String bookDate = baseCommonService.getSysDateString();
        if (bookDate == null || "".equals(bookDate)) {
            bookDate = DateUtil.format(new Date());
        }
        model.addAttribute("bookDate", DateUtil.addDay(bookDate, -1));
        this.setSelectOptionToModel(model, DictCode.是否);
        return super.init(model);
    }

   

    /**
     * 分页数据查询
     * 
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<ValSubjectBalanceAbnormalQuery001Dto> findPage(@RequestBody Map<String, Object> param,
			@PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return valSubjectBalanceAbnormalQuery001Service.queryPageList(page, length, param);
    }
    
    /**
     * 导出信息excel
     * 
     * @return excel
     * @throws Exception
     *             异常
     * @see
     * @since 1.0
     */
    @PostMapping("/getAllList")
    public List<ValSubjectBalanceAbnormalQuery001Dto> getAllList(@RequestBody Map<String, Object> params) {
		return valSubjectBalanceAbnormalQuery001Service.getAllList(params);
    }
}
