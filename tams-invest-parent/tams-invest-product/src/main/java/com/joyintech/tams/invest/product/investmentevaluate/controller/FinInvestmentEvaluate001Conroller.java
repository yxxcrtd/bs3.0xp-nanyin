package com.joyintech.tams.invest.product.investmentevaluate.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.product.evaluationentrance.EvaluationEntrance;
import com.joyintech.tams.invest.product.investmentevaluate.dto.BokPositionPrdNetvalueDto;
import com.joyintech.tams.invest.product.investmentevaluate.dto.FinInvestmentEvaluate001Dto;
import com.joyintech.tams.invest.product.investmentevaluate.dto.FinInvestmentEvaluate003Dto;
import com.joyintech.tams.invest.product.investmentevaluate.service.FinInvestmentEvaluate001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Controller
@PageId("FinInvestmentEvaluate001")
@RequestMapping("/invest/product/investmentevaluate/FinInvestmentEvaluate001")
public class FinInvestmentEvaluate001Conroller extends BaseController {
    /*
     * 投资评价
     * 〈功能详细描述〉
     * @author jyl
     * @version 1.0 2019年10月10日
     * @see FinInvestmentEvaluate001Conroller
     * @since 1.0*/
    @Autowired
    FinInvestmentEvaluate001Service finInvestmentEvaluate001Service;
    //调用共通接口方法
    @Autowired
    EvaluationEntrance evaluationEntrance;

    @Override
    public String init(Model model) {
        model.addAttribute("sysdate", DateUtil.getSysDate());
        return super.init(model);
    }

    /**
     * 组合信息查询
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/findPage")
    public Page<FinInvestmentEvaluate003Dto> findPage() {
        return finInvestmentEvaluate001Service.findPage(RequestUtil.getPageParam());

    }

    @ResponseBody
    @RequestMapping("/findPortInfo")
    public List<FinInvestmentEvaluate003Dto> findPortInfo() {
        return finInvestmentEvaluate001Service.findPortInfo(RequestUtil.getPageParam());

    }

    /**
     * 评价日资产配置
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/findPage2")
    public Page<FinInvestmentEvaluate001Dto> findPage2() {
        return finInvestmentEvaluate001Service.findPage2(RequestUtil.getPageParam());

    }

    //净值表现，查产品起始日到结束日的估值信息
    @ResponseBody
    @RequestMapping("/findPage3")
    public Page<BokPositionPrdNetvalueDto> findPage3() throws ParseException {
        return finInvestmentEvaluate001Service.findPage3(RequestUtil.getPageParam());
    }

    @ResponseBody
    @RequestMapping("/findPage3e")
    public List<BokPositionPrdNetvalueDto> findPage3e(@RequestBody Map<String, Object> pageParam) throws ParseException {
        return finInvestmentEvaluate001Service.findPage3e(pageParam);
    }

    //收益指标
    @ResponseBody
    @RequestMapping("/findPage4")
    public List findPage4() throws ParseException {
//
        return finInvestmentEvaluate001Service.findPage4(RequestUtil.getPageParam());
    }

    //风险指标
    @ResponseBody
    @RequestMapping("/findPage5")
    public List findPage5() throws ParseException {

        return finInvestmentEvaluate001Service.findPage5(RequestUtil.getPageParam());
    }

    //风险调整收益指标
    @ResponseBody
    @RequestMapping("/findPage6")
    public List findPage6() throws ParseException {

        return finInvestmentEvaluate001Service.findPage6(RequestUtil.getPageParam());
    }

    //策略执行有效性
    @ResponseBody
    @RequestMapping("/findPage7")
    public List findPage7() throws ParseException {

        return finInvestmentEvaluate001Service.findPage7(RequestUtil.getPageParam());
    }

    //策略执收益来源
    @ResponseBody
    @RequestMapping("/findPage8")
    public List findPage8() throws ParseException {

        return finInvestmentEvaluate001Service.findPage8(RequestUtil.getPageParam());
    }

    //组合查询
    @ResponseBody
    @RequestMapping("/findPort")
    public List<SelectOptionDto> findPort() {

        return finInvestmentEvaluate001Service.findPort(RequestUtil.getPageParam());
    }
    //策略查询
    @ResponseBody
    @RequestMapping("/findStrategyId")
    public List<SelectOptionDto> findStrategyId() {

        return finInvestmentEvaluate001Service.findStrategyId(RequestUtil.getPageParam());
    }
}
