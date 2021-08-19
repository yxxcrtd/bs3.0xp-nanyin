/*
 * 文件名：EtrTradePlace001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chendijun
 * 修改时间：2020年7月1日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.entrust.etribrepo.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.JoyinMessage;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.trade.BussEtrInvestPersonInfoService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.entrust.etribrepo.dto.EtrFinProductDto;
import com.joyintech.tams.invest.entrust.etribrepo.dto.EtrIbRepo001Dto;
import com.joyintech.tams.invest.entrust.etribrepo.service.EtrIbRepo001Service;
import com.joyintech.tams.invest.entrust.etrstandardbond.dto.EtrStandardBond001Dto;

import com.joyintech.tams.invest.entrust.home.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 银行间债券指令
 * @author chengzhong
 * @version 1.0 2021年1月19日
 * @see EtrIbRepo001Controller
 * @since 1.0
 */
@Controller
@PageId("EtrIbRepo001")
@RequestMapping("/invest/entrust/etribrepo/EtrIbRepo001")
public class EtrIbRepo001Controller extends BaseController{

    /**
     * 市场行情接口地址
     */
/*    @Value("${MarketInfoInterf.BaseUrl}")
    private String MarketInfoInterf_BaseUrl;*/
    /**
     * 外汇交易中心行情接口地址
     */
    @Value("${MarketInfoInterf.cmdsUrl}")
    private String MarketInfoInterf_cmdsUrl;

    /**
     * 债券持仓查询
     */
    @Autowired
    private EtrIbRepo001Service etrIbRepo001Service;
    
    @Autowired(required=false)
    private BussEtrInvestPersonInfoService bussEtrInvestPersonInfoService;

    @Autowired
    private HomeService homeService;
    
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.委托状态);
        this.setSelectOptionToModel(model, DictCode.价格模式);
        this.setSelectOptionToModel(model, DictCode.回购拆借方向);
        this.setSelectOptionToModel(model, DictCode.委托状态);
        this.setSelectOptionToModel(model, DictCode.回购期限品种);
        this.setSelectOptionToModel(model, DictCode.价格模式);
        this.setSelectOptionToModel(model, DictCode.有效状态);
        model.addAttribute("YES_OR_NO_Y", DictCodeList.是否_是.getValue());
        model.addAttribute("repo_pledge_model",CacheUtil.getSysParam(BussinessConstant.REPO_PLEDGE_MODEL));

        // 定制化配置信息控制页面展示
        model.addAttribute("DYN_INFO", homeService.getChecked("EtrIbRepoMain"));
        return super.init(model);
    }
    
    /**
     * 查询持仓
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findZlglPage")
    public Page<EtrIbRepo001Dto> findZlglPage(){
        return etrIbRepo001Service.findZlglPage(RequestUtil.getPageParam());
    }

    /**
     * 查询债券质押持仓
     * 
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findZlglPositionPage")
    public Page<EtrIbRepo001Dto> findZlglPositionPage(){
        return etrIbRepo001Service.findZlglPositionPage(RequestUtil.getPageParam());
    }

    /**
     * 导出
     *
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
            throws Exception {
        Map<String, Object> searchParams = RequestUtil.getPageParam();
        String fileName = "银行间质押式回购指令（质押券管理）" + "(" + DateUtil.getCurrentDate() + ").xls";
        if (searchParams.get("isDvp") != null && Boolean.parseBoolean((String)searchParams.get("isDvp"))) {
        	fileName = "银行间质押式回购指令（指令管理）" + "(" + DateUtil.getCurrentDate() + ").xls";
        }
        return etrIbRepo001Service.export(fileName, RequestUtil.getParameters(getRequest()));
    }

    /***
     * 初始化行情
     * @return
     */
    @RequestMapping(value = "/initHq")
    public String initHq(Model model){
        model.addAttribute("MarketInfoInterf_BaseUrl", MarketInfoInterf_cmdsUrl);
        return "invest/entrust/etribrepo/EtrIbRepoinfoHq";
    }
    
    /**
     * 查询持仓信息
     *
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPositionInfo")
    public EtrFinProductDto findPositionInfo(@RequestBody Map<String, Object> param){
        return etrIbRepo001Service.findPositionInfo(param);
    }

    @ResponseBody
    @RequestMapping("getSecManageAcctId")
    public Page<SelectOptionDto> getSecManageAcctId() {
        Map<String, Object> param = RequestUtil.getPageParam();
        param.put("finprodType2", BussinessDictCodeList.金融产品类型_投管用_通道.getValue());
        return etrIbRepo001Service.getSecManageAcctId(param);
    }
    
    /**
     * 查询回购信息
     *
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findhginfo")
    public EtrFinProductDto findhginfo(@RequestBody Map<String, Object> param){
        return etrIbRepo001Service.findhginfo(param);
    }
    
    /**
     * 
     * 交易对手
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getCounterId")
    public Page<SelectOptionDto> getCounterId(){
        Map<String, Object> params = RequestUtil.getPageParam();
        if (StringUtil.isEmpty((String) params.get("innerTrade")) || "null".equals(params.get("innerTrade"))) {
            params.put("innerTrade", false);
        }
        else if (Boolean.FALSE.toString().equals(params.get("innerTrade"))) {
            params.put("innerTrade", false);
        }
        return bussEtrInvestPersonInfoService.getCounterId(params);
    }
    
    /**
     * 出库操作
     *
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("outSave")
    public AjaxResult outSave(@RequestBody EtrIbRepo001Dto dto) {
     // 校验操作的角色是否是投资经理
        Map<String,Object> map = new HashMap<>();
        map.put("role_pos", DictCodeListExt2.角色岗位_投资经理.getValue());
        Long count = etrIbRepo001Service.selectUserRolePosList(map) ;
        if(count > 0){
            Long outFlat = etrIbRepo001Service.outSave(dto);
            // 标记为大于0，出库成功
            if(outFlat > 0){
                return new AjaxResult(this.getMessage("EtrStandardBond001.outSave_002"));
            }else{
                return new AjaxResult(this.getMessage("EtrStandardBond001.outSave_003"));
            }
        }else{
            AjaxResult ar = new AjaxResult() ;
            List<JoyinMessage> joyinMessages = new ArrayList<>();
            joyinMessages.add(
                    new JoyinMessage(this.getMessage("EtrStandardBond003.role",
                      DictCodeListExt2.角色岗位_投资经理.getCodeName()), MessageType.警告消息));
            ar.setJoyinMessages(joyinMessages);

            return ar ;
        }
    }
    
    @RequestMapping(value="findQuotations")
    @ResponseBody
    public Map<String, Object> findQuotations() {
    	return etrIbRepo001Service.findQuotations(RequestUtil.getPageParam());
    }
}
