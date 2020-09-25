package com.joyintech.tams.invest.riskredis.quota.rsksupervisionriskanalyze.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.riskredis.quota.rsksupervisionriskanalyze.dto.RskSupervisionRiskAnalyzeDto;
import com.joyintech.tams.invest.riskredis.quota.rsksupervisionriskanalyze.service.RskSupervisionRiskAnalyzeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

/**
 * @program: tams-invest-parent
 * @description: RskSupervisionRiskAnalyze001
 * @author: liuwei
 * @create: 2018-11-01 14:29
 **/
@Controller
@PageId("RskSupervisionRiskAnalyze001")
@RequestMapping("/invest/riskredis/quota/rsksupervisionriskanalyze/RskSupervisionRiskAnalyze001")
public class RskSupervisionRiskAnalyze001Controller extends BaseController  {

    /**
     * 监督值查询service
     */
    @Autowired
    private RskSupervisionRiskAnalyzeService rskSupervisionRiskAnalyzeService;

    /**
     * 监督值查询的初始化
     * @return 初始化结果
     * @see
     * @since 1.0
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
         this.setSelectOptionToModel(model, BussinessDictCodeRds.监督类型,BussinessDictCodeRds.单位,BussinessDictCodeRds.监督状态);
        model.addAttribute("normal", BussinessDictCodeListRds.监督状态_正常.getCodeName());
        model.addAttribute("warning", BussinessDictCodeListRds.监督状态_触发预警.getCodeName());
        model.addAttribute("approve",BussinessDictCodeListRds.监督状态_触发审批.getCodeName());
        model.addAttribute("violation",BussinessDictCodeListRds.监督状态_触发超限.getCodeName());
        model.addAttribute("error",BussinessDictCodeListRds.监督状态_异常.getCodeName());
        model.addAttribute("NONE", BussinessDictCodeListRds.单位_无.getValue());
        return super.init(model);
    }

    /**
     * 从资管跳转监督值查询的初始化
     * @return 初始化结果
     * @see
     * @since 1.0
     */
    @RequestMapping("outInit")
    public String outInit(Model model, HttpServletRequest request){
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()){
            String key=paramNames.nextElement();
            if("valDate".equals(key)&& StringUtil.isNotEmpty(request.getParameter(key))){
                model.addAttribute(key,DateUtil.format(DateUtil.parse(request.getParameter(key)),DateUtil.DEFAULT_DATE_FORMAT_EN)+" - "+DateUtil.format(DateUtil.parse(request.getParameter(key)),DateUtil.DEFAULT_DATE_FORMAT_EN));
            }else {
                model.addAttribute(key, request.getParameter(key));
            }
        }
        this.setSelectOptionToModel(model, BussinessDictCodeRds.监督类型,BussinessDictCodeRds.单位,BussinessDictCodeRds.监督状态);
        model.addAttribute("normal", BussinessDictCodeListRds.监督状态_正常.getCodeName());
        model.addAttribute("warning", BussinessDictCodeListRds.监督状态_触发预警.getCodeName());
        model.addAttribute("approve",BussinessDictCodeListRds.监督状态_触发审批.getCodeName());
        model.addAttribute("violation",BussinessDictCodeListRds.监督状态_触发超限.getCodeName());
        return super.init(model);
    }

    /**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<RskSupervisionRiskAnalyzeDto> selectAll()
            throws Exception {
        Map<String,Object> map = RequestUtil.getParameters(getRequest());
        Page<RskSupervisionRiskAnalyzeDto> page = rskSupervisionRiskAnalyzeService.selectForPage(map);
        return page;
    }
    
    /**
     * 进入界面初始化查询
     * @return 查询结果集
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPageInit")
    public Page<RskSupervisionRiskAnalyzeDto> selectAllInit()
            throws Exception {
        Map<String,Object> map = RequestUtil.getParameters(getRequest());
        Page<RskSupervisionRiskAnalyzeDto> page = rskSupervisionRiskAnalyzeService.selectForPageInit(map);
        return page;
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
        return rskSupervisionRiskAnalyzeService.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));

    }

    @ResponseBody
    @RequestMapping("getUserName")
    public Page<SelectOptionDto> getUserName() {
        return rskSupervisionRiskAnalyzeService.getUserName(RequestUtil.getPageParam());
    }
}
