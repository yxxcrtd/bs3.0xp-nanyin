
 
package com.joyintech.tams.invest.portfolio.ptlpositionanalysis.controller;

import com.google.common.collect.Lists;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.TargetDetail;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.portfolio.ptlpositionanalysis.dto.PtlPositionAnalysis003Dto;
import com.joyintech.tams.invest.portfolio.ptlpositionanalysis.service.PtlPositionAnalysis002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 组合持仓分析报表
 * @author nixiukuan
 * @version 1.0 2019年12月05日
 * @see PtlPositionAnalysis002Controller
 * @since 1.0
 */
@Controller
    @PageId("PtlPositionAnalysis002")
@RequestMapping("/invest/portfolio/ptlpositionanalysis/PtlPositionAnalysis002")
public class PtlPositionAnalysis002Controller extends BaseController {
    @Autowired
    private PtlPositionAnalysis002Service ptlPositionAnalysis002Service;
    /**
     * {@linkplain}
     */
    @Autowired
    private IPlatformDate iPlatformDate;
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.收益类型, DictCode.下钻层级, DictCode.展示方式);
        // 平台日期
        model.addAttribute("platDate", iPlatformDate.getSysDateStr());

        //默认的收益类型
        model.addAttribute("PROFIT_TYPE_defualt", DictCodeList.收益类型_净值型.getValue());
        // 默认的下钻层级
        model.addAttribute("DEEP_LEVEL_defualt", DictCodeListExt3.下钻层级_资产一级分类.getValue());
        // 默认的展示方式
        model.addAttribute("DISPLAY_TYPE_defualt", DictCodeListExt3.展示方式_多组合汇总.getValue());
        return super.init(model);
    }
    /**
     * 组合持仓明细查询
     * @return 查询结果集
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<PtlPositionAnalysis003Dto> selectAll(){
        Map<String,Object> map = RequestUtil.getParameters(getRequest());
        Page<PtlPositionAnalysis003Dto> list = ptlPositionAnalysis002Service.selectForPage(map);
        list.setRoot(formatFindPageResult(list.getRoot()));

        return list;
    }

    private List<PtlPositionAnalysis003Dto> formatFindPageResult(List<PtlPositionAnalysis003Dto> root) {
        //modify by jiangpeng on 20210528 ZGBSSE-173 资产的持仓市值为0时不显示
        List<PtlPositionAnalysis003Dto> ptlPositionAnalysis003Dtos = Lists.newArrayList();
        if (root.size() > 0) {
            root.forEach(item -> {
                // 票面利率 百分比化
                if (item.getRate() != null) {
                    item.setRate(ArithmeticUtil.round(item.getRate().multiply(BigDecimal.valueOf(100)), 2, RoundingMode.HALF_UP));
                }
                // 综合成本收益率 百分比化
                if (item.getCostRate() != null) {
                    item.setCostRate(ArithmeticUtil.round(item.getCostRate().multiply(BigDecimal.valueOf(100)), 4, RoundingMode.HALF_UP));
                }
                // 资产的持仓市值为0时不显示
                if (item.getPosValue() != null && BigDecimal.ZERO.compareTo(item.getPosValue()) != 0) {
                    ptlPositionAnalysis003Dtos.add(item);
                }
            });
        }
        //modify by jiangpeng on 20210528 ZGBSSE-173 资产的持仓市值为0时不显示
        return ptlPositionAnalysis003Dtos;
    }

    /**
     * 获取动态列结果
     * @return 查询结果集
     */
    @ResponseBody
    @RequestMapping("getTableCols")
    public String getTableCols() {
        Map<String,Object> map = RequestUtil.getParameters(getRequest());
      return StringUtil.trimHtmlTag(ptlPositionAnalysis002Service.getDataTableCols(map));

    }
    /**
     * 导出信息excel
     * @return excel
     * @see
     * @since 1.0
     */

    @RequestMapping("/export")
    public ResponseEntity<byte[]> export(){
        return ptlPositionAnalysis002Service.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()),"searchDate");
    }

}
