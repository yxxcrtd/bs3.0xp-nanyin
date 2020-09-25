/*
 * 文件名：BussTrdCommonController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：刘二通
 * 修改时间：2018年11月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.trade.controller;

import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.component.business.trade.BussRepoSecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;

import java.util.Map;

/**
 * 交易共通Controller
 *
 * @author 刘二通
 * @version 1.0 2018年11月15日
 * @see BussTrdCommonController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/trade/BussTrdCommon")
public class BussTrdCommonController extends BaseController {

    @Autowired
    private BussRepoSecService bussRepoSecService;
    /**
     * 根据起息日和到期日自动计算期限
     *
     * @param vdate 起息日
     * @param mdate 到期日
     * @return 期限
     * @see
     * @since 1.0
     */
    @RequestMapping("calcTerm")
    @ResponseBody
    public String calcTerm(String vdate, String mdate) {
        if (StringUtil.isBlank(vdate) || StringUtil.isBlank(mdate)) {
            return null;
        }
        if (!DateUtil.isDateStr(vdate) || !DateUtil.isDateStr(mdate)) {
            return null;
        }
        if (DateUtil.compareDate(vdate, mdate) > 0) {
            return null;
        }
        return String.valueOf(DateUtil.getBetweenDayNumber(vdate, mdate));
    }

    /**
     * 根据起息日和期限自动计算到期日
     *
     * @param vdate 起息日
     * @param term  期限
     * @return 到期日
     * @see
     * @since 1.0
     */
    @RequestMapping("calcMdate")
    @ResponseBody
    public String calcMdate(String vdate, String term) {
        if (StringUtil.isBlank(vdate) || StringUtil.isBlank(term)) {
            return null;
        }

        if (!DateUtil.isDateStr(vdate)) {
            return null;
        }
        Integer termNum = null;
        try {
            termNum = Integer.valueOf(term);
        }
        catch (Exception e) {
            return null;
        }
        if (termNum < 0) {
            return null;
        }

        return DateUtil.addDay(vdate, termNum);
    }
}
