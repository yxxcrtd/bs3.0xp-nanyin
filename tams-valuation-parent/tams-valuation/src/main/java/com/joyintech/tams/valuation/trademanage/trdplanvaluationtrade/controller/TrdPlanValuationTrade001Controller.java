/*
 * 文件名：TrdPlanRateInvestTrade001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年11月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.trademanage.trdplanvaluationtrade.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.*;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;
import com.joyintech.tams.valuation.trademanage.trdplanvaluationtrade.dto.TrdPlanValuationTradeDto;
import com.joyintech.tams.valuation.trademanage.trdplanvaluationtrade.service.TrdPlanValuationTrade001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @Description:计划类资产投资交易查询
 * @auther: yuzj
 * @date: 2021/12/30
 * @param:
 * @return:
 */
@Controller
@PageId("TrdPlanValuationTrade001")
@RequestMapping("/valuation/trademanage/trdplanvaluationtrade/TrdPlanValuationTrade001")
public class TrdPlanValuationTrade001Controller extends BaseController {
    /**
     * 注入计划类（利率型）service1
     */
    @Autowired
    private TrdPlanValuationTrade001Service trdPlanValuationTrade001Service;

    /**
     * 流程组件
     */
    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;
    /**
     * 初始化方法
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.交易方向);
        model.addAttribute("TRADE_STATUS", DictCodeListExt1.交易流程状态_有效.getValue());
        model.addAttribute("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE")==null?"01": CacheUtil.getSysParam("IS_SHOW_TYPE"));
        return super.init(model);
    }


    /**
     * @Description:页面查询，表格分页
     * @auther: yuzj
     * @date: 2021/12/30
     * @param:
     * @return:
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<TrdPlanValuationTradeDto> findPage()
            throws Exception {
        Page<TrdPlanValuationTradeDto> page = trdPlanValuationTrade001Service.selectForPage(
                RequestUtil.getParameters(getRequest()));
        return page;
    }

    /**
     * 导出数据
     *
     * @return ResponseEntity<byte[]>
     * @throws Exception ExportUtil.export
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
            throws IOException {
        String datas = this.getParam("datas");
        List<TrdPlanValuationTradeDto> listdto;
        if (datas == null) {
            listdto = trdPlanValuationTrade001Service.findAll(RequestUtil.getPageParam());
        }
        else {
            listdto = JsonUtils.json2List(datas, TrdPlanValuationTradeDto.class);
        }
        File outFile = trdPlanValuationTrade001Service.makeFile(
                "/com/joyintech/tams/valuation/trademanage/trdplanvaluationtrade/conf/export_planvaluationtrade.xml",
                listdto);
        return ExportUtil.export(
                this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                        + DateUtil.format(new Date())
                        + SystemGlobalConstant.CLOSE_PARENTHESIS
                        + SystemGlobalConstant.POINT
                        + FileNameUtil.getFileExtName(outFile),
                outFile, true);
    }


}
