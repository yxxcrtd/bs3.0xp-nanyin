/*
 * 文件名：PtlFutureCashPosi001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：renping
 * 修改时间：2019年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.portfolio.ptlfuturecashposi.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.portfolio.ptlfuturecashposi.dto.PtlFutureCashPosiDto;
import com.joyintech.tams.invest.portfolio.ptlfuturecashposi.service.PtlFutureCashPosiService;

/**
 * 组合未来头寸分析头寸查询
 * 
 * @author renping
 * @version 1.0 2019年11月9日
 * @see PtlFutureCashPosi001Controller
 * @since 1.0
 */
@Controller
@PageId("PtlFutureCashPosi001")
@RequestMapping("/invest/portfolio/ptlfuturecashposi/PtlFutureCashPosi001")
public class PtlFutureCashPosi001Controller extends BaseController {

    /**
     * PtlFutureCashPosiService
     */
    @Autowired
    private PtlFutureCashPosiService ptlFutureCashPosiService;

    /**
     * 界面初始化 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.收益类型, DictCode.展示维度);
        Date sysDate = DateUtil.platDateFromCache();
        String morenDate = ptlFutureCashPosiService.getNextWorkDay(sysDate);
        model.addAttribute("sysDateNext", morenDate);
        model.addAttribute("showDimensionShort", DictCodeListExt3.展示维度_短期.getValue());
        return super.init(model);
    }

    /**
     * 页面查询
     * 
     * @return Page
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public List<PtlFutureCashPosiDto> selectAllMstCaptialAccount()
        throws Exception {
        return ptlFutureCashPosiService.selectData(RequestUtil.getPageParam());
    }

    /**
     * 导出信息excel
     * 
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */

    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return ptlFutureCashPosiService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }

    /**
     * 查询导出按钮点击时的check
     * 
     * @param dto 验证的实体类
     * @param result 验证的结果
     * @return 错误信息
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@Validated @RequestBody PtlFutureCashPosiDto dto,
                            BindingResult result) {
        return new AjaxResult(result);
    }
}
