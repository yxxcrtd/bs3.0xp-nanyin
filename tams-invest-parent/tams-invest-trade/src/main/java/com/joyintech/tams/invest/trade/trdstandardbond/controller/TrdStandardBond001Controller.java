/*
 * 文件名：TrdStandardBond001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：陈志力
 * 修改时间：2019年1月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.trdstandardbond.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.trade.trdrepotrade.dto.TrdRepoTrade001Dto;
import com.joyintech.tams.invest.trade.trdstandardbond.dto.TrdStandardBond001Dto;
import com.joyintech.tams.invest.trade.trdstandardbond.service.TrdStandardBond001Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 标准券管理
 * 〈功能详细描述〉
 *
 * @author 陈志力
 * @version 1.0 2019年1月22日
 * @see TrdStandardBond001Controller
 * @since 1.0
 */
@PageId("TrdStandardBond001")
@RequestMapping("/invest/trade/trdstandardbond/TrdStandardBond001")
@Controller
public class TrdStandardBond001Controller extends BaseController {

    @Autowired(required = false)
    private TrdStandardBond001Service trdStandardBond001Service;

    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.交易场所);
        return super.init(model);
    }

    /**
     * 分页查询
     *
     * @return Page<TrdStandardBond001Dto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("selectPageData")
    @ResponseBody
    public Page<TrdStandardBond001Dto> selectPageData() throws Exception {
        return trdStandardBond001Service.selectPageData(RequestUtil.getPageParam());
    }
    @RequestMapping("selectListData")
    @ResponseBody
    public List<TrdStandardBond001Dto> selectListData(@RequestBody  Map<String,Object> param) throws Exception {
        Date sysDate = DateUtil.platDateFromCache();
        String formatDate = DateUtil.format(sysDate);
        param.put("searchDate",formatDate);
        Page<TrdStandardBond001Dto> trdStandardBond001DtoPage = trdStandardBond001Service.selectPageData(param);
        return trdStandardBond001DtoPage.getRoot();
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
        return trdStandardBond001Service.export(this.getPageId(), RequestUtil.getPageParam());
    }

    /**
     * 查询债券代码：下拉框
     *
     * @param params 画面参数
     * @return 债券代码：下拉框
     */
    @RequestMapping("getBondCode")
    @ResponseBody
    public List<SelectOptionDto> getBondCode(@RequestParam Map<String, Object> params) {
        return trdStandardBond001Service.getBondCode(params);
    }

    /**
     * 根据组合代码查询证券托管户：联想框文本框
     *
     * @param param 画面参数
     * @return 证券托管户：联想框文本框
     * @see
     * @since 1.0
     */
    @RequestMapping("getSecManageAcctId")
    @ResponseBody
    public List<SelectOptionDto> getSecManageAcctId(@RequestParam Map<String, Object> params) {
        return trdStandardBond001Service.getSecManageAcctId(params);
    }
}
