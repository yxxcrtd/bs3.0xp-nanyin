/*
 * 文件名：MstBankTranFeeManage001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2019年3月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.basicdata.dayendaccount.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.basicdata.dayendaccount.dto.DayEndAccountDto;
import com.joyintech.tams.invest.basicdata.dayendaccount.service.DayEndAccount001Service;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 日终清算数据核对
 * 〈功能详细描述〉
 * @author chengzhong
 * @version 1.0 2021年4月20日
 * @see DayEndAccount001Controller
 * @since 1.0
 */
@Controller
@PageId("DayEndAccount001")
@RequestMapping("/invest/basicdata/dayendaccount/DayEndAccount001")
public class DayEndAccount001Controller extends BaseController {
    /**
     * mstBankTranFeeManageService
     */
    @Autowired
    private DayEndAccount001Service dayEndAccount001Service;
    /**
     * 入口
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        // 下拉框初始化
        this.setSelectOptionToModel(model, BussinessDictCode.交易场所);
        this.setSelectOptionToModel(model, DictCode.对账状态结果);
        String accountStatus = (String)model.asMap().get("ACCOUNT_STATUS");
        String ACCOUNT_STATUS1 = accountStatus.replace(",{\"id\":\"2\",\"text\":\"待处理\",\"col\":\"ACCOUNT_STATUS\"},{\"id\":\"3\",\"text\":\"手工完成\",\"col\":\"ACCOUNT_STATUS\"}","");
        model.addAttribute("ACCOUNT_STATUS1",ACCOUNT_STATUS1);
        model.addAttribute("settleDateTotal", DateUtil.platDateFromCache());
        return super.init(model);
    }
    /**
     * 画面查询方法
     * @return 所有数据
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<DayEndAccountDto> selectAll()
        throws Exception {
        return dayEndAccount001Service.selectForPage(
            RequestUtil.getParameters(getRequest()));
    }

    @ResponseBody
    @RequestMapping("queryAssetCode")
    public Page<SelectOptionDto> queryAssetCode() {
        Map<String, Object> param = RequestUtil.getPageParam();
        return dayEndAccount001Service.queryAssetCode(param);
    }

    /**
     * 画面查询方法
     * @return 所有数据
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("qryAccount")
    public AjaxResult qryAccount() {
        Map<String, Object> param = RequestUtil.getPageParam();
        return dayEndAccount001Service.qryAccount(param);
    }

    @RequestMapping("selectCustomerId")
    @ResponseBody
    public Page<SelectOptionDto> selectCustomerId() {
        Map<String, Object> param = RequestUtil.getPageParam();
        return dayEndAccount001Service.selectCustomerId(param);
    }

    @RequestMapping("queryBusiAcctNo")
    @ResponseBody
    public Page<SelectOptionDto> queryBusiAcctNo() {
        Map<String, Object> param = RequestUtil.getPageParam();
        return dayEndAccount001Service.queryBusiAcctNo(param);
    }

    @ResponseBody
    @RequestMapping("selectPage")
    public Page<DayEndAccountDto> selectPage()
            throws Exception {
        return dayEndAccount001Service.selectPage(RequestUtil.getParameters(getRequest()));
    }
}
