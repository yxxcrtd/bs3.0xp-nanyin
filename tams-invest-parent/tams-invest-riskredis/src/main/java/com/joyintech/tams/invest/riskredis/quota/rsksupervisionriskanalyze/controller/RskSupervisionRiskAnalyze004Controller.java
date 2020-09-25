package com.joyintech.tams.invest.riskredis.quota.rsksupervisionriskanalyze.controller;


import java.util.Map;

import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;
import com.joyintech.tams.invest.riskredis.quota.rsksupervisionriskanalyze.dto.RskSupervisionRiskAnalyzeDto;
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
import com.joyintech.tams.invest.riskredis.quota.rsksupervisionriskanalyze.service.RskSupervisionRiskAnalyzeService;

/**
 * @description: RskSupervisionRiskAnalyze004
 * @author: 郑俊平 
 * @create: 2019-08-28
 **/
@Controller
@PageId("RskSupervisionRiskAnalyze004")
@RequestMapping("/invest/riskredis/quota/rsksupervisionriskanalyze/RskSupervisionRiskAnalyze004")
public class RskSupervisionRiskAnalyze004Controller extends BaseController  {

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
        model.addAttribute("portfolioId", this.getParam("portfolioId"));
	    model.addAttribute("indName", this.getParam("indName"));
	    model.addAttribute("assetName", this.getParam("assetName"));
	    model.addAttribute("assetCode", this.getParam("assetCode"));
	    model.addAttribute("supDate", this.getParam("supDate"));
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
        Page<RskSupervisionRiskAnalyzeDto> page = rskSupervisionRiskAnalyzeService.selectForPageNx(map);
        return page;
    }

    /**
     * 导出信息excel
     * @return excel
     * @throws Exception 异常
     */
    @RequestMapping("exportNx")
    public ResponseEntity<byte[]> exportNx()
            throws Exception {
        return rskSupervisionRiskAnalyzeService.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));

    }
}
