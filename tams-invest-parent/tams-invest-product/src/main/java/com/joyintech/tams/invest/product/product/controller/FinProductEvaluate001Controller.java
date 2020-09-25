package com.joyintech.tams.invest.product.product.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.product.product.dto.*;
import com.joyintech.tams.invest.product.product.service.FinProductEvaluate001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 产品评价
 * @author guyunfei
 * @see
 * @since
 */
@Controller
@PageId("FinProductEvaluate001")
@RequestMapping("/invest/product/product/FinProductEvaluate001")
public class FinProductEvaluate001Controller extends BaseController {

    @Autowired
    private FinProductEvaluate001Service finProductEvaluate001Service;

    @Override
    public String init(Model model) {
        //系统平台日
        model.addAttribute("sysDate",DateUtil.getSysDate());
        model.addAttribute("proType",DictCodeList.收益类型_货币型.getValue());
        return super.init(model);
    }

    /**
     * 产品名称下拉框查询
     * @return
     */
    @ResponseBody
    @RequestMapping("/getProName")
    public List<SelectOptionDto> getProName(){
        return finProductEvaluate001Service.getProNames(RequestUtil.getPageParam());
    }

    /**
     * 产品发行前信息查询
     * @return
     */
    @ResponseBody
    @RequestMapping("/findPage")
    public Page<FinProductEvaluate001TabDto> findPage(){
        return finProductEvaluate001Service.selectForPage(RequestUtil.getPageParam());
    }

    /**
     * 评价日资产配置情况查询
     * @return
     */
    @ResponseBody
    @RequestMapping("/findPage2")
    public Page<FinProductEvaluate001Tab2Dto>findPage2(){
        return finProductEvaluate001Service.selectForPage2(RequestUtil.getPageParam());
    }

    /**
     * 产品净值表现查询
     * @return
     */
    @ResponseBody
    @RequestMapping("/findPage3")
    public List<FinProductEvaluate001Tab3Dto> findPage3(){
        return finProductEvaluate001Service.selectForPage3(RequestUtil.getPageParam());
    }

    /**
     * 收益指标查询
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/findPage4")
    public List<FinProductEvaluate001Tab4Dto> findPage4() throws Exception{
        return finProductEvaluate001Service.selectForPage4(RequestUtil.getPageParam());
    }

    /**
     * 收益结构指标
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/findPage5")
    public List<FinProductEvaluate001Tab5Dto> findPage5() throws Exception{
        return finProductEvaluate001Service.selectForPage5(RequestUtil.getPageParam());
    }

    /**
     * 风险指标
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/findPage6")
    public List<FinProductEvaluate001Tab6Dto> findPage6() throws Exception{
        return finProductEvaluate001Service.selectForPage6(RequestUtil.getPageParam());
    }

    /**
     * 风险调整收益指标
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/findPage7")
    public List<FinProductEvaluate001Tab7Dto> findPage7() throws Exception{
        return finProductEvaluate001Service.selectForPage7(RequestUtil.getPageParam());
    }


}
