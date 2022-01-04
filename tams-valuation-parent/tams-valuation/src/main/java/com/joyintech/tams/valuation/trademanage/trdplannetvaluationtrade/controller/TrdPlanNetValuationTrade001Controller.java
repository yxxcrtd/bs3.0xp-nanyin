package com.joyintech.tams.valuation.trademanage.trdplannetvaluationtrade.controller;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.*;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.service.MstWorkflowCommonService;
import com.joyintech.tams.valuation.trademanage.trdplannetvaluationtrade.dto.TrdPlanNetValuationTradeDto;
import com.joyintech.tams.valuation.trademanage.trdplannetvaluationtrade.service.TrdPlanNetValuationTrade001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:计划类资产(净值型)投资交易查询
 * @auther: yuzj
 * @date: 2021/12/30
 * @param:
 * @return:
 */
@Controller
@PageId("TrdPlanNetValuationTrade001")
@RequestMapping("/valuation/trademanage/trdplannetvaluationtrade/TrdPlanNetValuationTrade001")
public class TrdPlanNetValuationTrade001Controller extends BaseController {
    /**
     * 注入计划类（利率型）service1
     */
    @Autowired
    private TrdPlanNetValuationTrade001Service trdPlanNetValuationTrade001Service;

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
        this.setSelectOptionToModel(model, DictCode.交易方向, DictCode.交易流程状态);
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
    public Page<TrdPlanNetValuationTradeDto> findPage()
            throws Exception {
        Page<TrdPlanNetValuationTradeDto> page = trdPlanNetValuationTrade001Service.selectForPage(
                RequestUtil.getParameters(getRequest()));
        return page;
    }

    /**
     * 导出信息excel
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
            throws Exception {
        return trdPlanNetValuationTrade001Service.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));
    }
    

}
