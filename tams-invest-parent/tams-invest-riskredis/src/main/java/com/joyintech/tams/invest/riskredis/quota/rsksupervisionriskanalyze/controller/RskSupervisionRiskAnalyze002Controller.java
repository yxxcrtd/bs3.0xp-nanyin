package com.joyintech.tams.invest.riskredis.quota.rsksupervisionriskanalyze.controller;


import java.util.Map;


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

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;

/**
 * @description: RskSupervisionRiskAnalyze002
 * @author: 裴家俊
 * @create: 2019-02-22
 **/
@Controller
@PageId("RskSupervisionRiskAnalyze002")
@RequestMapping("/invest/riskredis/quota/rsksupervisionriskanalyze/RskSupervisionRiskAnalyze002")
public class RskSupervisionRiskAnalyze002Controller extends BaseController  {

    /**
     * 监督值查询service
     */
    @Autowired
    private RskSupervisionRiskAnalyzeService rskSupervisionRiskAnalyzeService;
    
    private static final IDictCode[] DICT_CODES = new BussinessDictCodeRds[]{BussinessDictCodeRds.监督类型,BussinessDictCodeRds.监督状态,BussinessDictCodeRds.指标类型,BussinessDictCodeRds.单位,
            BussinessDictCodeRds.监督类型,BussinessDictCodeRds.连接符,BussinessDictCodeRds.运算符,BussinessDictCodeRds.监督时分,BussinessDictCodeRds.监督频率,BussinessDictCodeRds.金融产品类型,
            BussinessDictCodeRds.WIND一级分类_含债券一级分类等,BussinessDictCodeRds.基金类资产二级类型,BussinessDictCodeRds.资产评级,BussinessDictCodeRds.申万行业一级类型};
    /**
     * 监督值查询的初始化
     * @return 初始化结果
     * @see
     * @since 1.0
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        this.setSelectOptionToModel(model,DICT_CODES );
        model.addAttribute("normal", BussinessDictCodeListRds.监督状态_正常.getCodeName());
        model.addAttribute("warning", BussinessDictCodeListRds.监督状态_触发预警.getCodeName());
        model.addAttribute("approve",BussinessDictCodeListRds.监督状态_触发审批.getCodeName());
        model.addAttribute("violation",BussinessDictCodeListRds.监督状态_触发超限.getCodeName());
	    model.addAttribute("indId", this.getParam("indId"));
	    model.addAttribute("portfId", this.getParam("portfId"));
	    model.addAttribute("supDate", this.getParam("supDate"));
	    model.addAttribute("foulCond", this.getParam("foulCond"));
	    model.addAttribute("supFrequency",this.getParam("supFrequency"));
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
        Page<RskSupervisionRiskAnalyzeDto> page = rskSupervisionRiskAnalyzeService.selectForPageMx(map);
        return page;
    }

    /**
     * 导出信息excel
     * @return excel
     * @throws Exception 异常
     */
    @RequestMapping("exportMx")
    public ResponseEntity<byte[]> exportMx()
            throws Exception {
        return rskSupervisionRiskAnalyzeService.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));

    }
}
