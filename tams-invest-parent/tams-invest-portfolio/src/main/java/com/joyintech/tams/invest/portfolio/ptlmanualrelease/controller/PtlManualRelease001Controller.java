/*
 * 文件名：PtlCashFlow001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.portfolio.ptlmanualrelease.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.portfolio.ptlmanualrelease.dto.PtlManualRelease001Dto;
import com.joyintech.tams.invest.portfolio.ptlmanualrelease.dto.PtlManualRelease003Dto;
import com.joyintech.tams.invest.portfolio.ptlmanualrelease.service.PtlManualRelease001Service;

/**
 * 手动释放头寸
 * 〈功能详细描述〉
 * @author chenlei1
 * @version 1.0 2019年12月09日
 * @see PtlManualRelease001Controller
 * @since 1.0
 */
@Controller
@PageId("PtlManualRelease001")
@RequestMapping("/invest/portfolio/ptlmanualrelease/PtlManualRelease001")
public class PtlManualRelease001Controller extends BaseController{
    /**
     * ptlCashFlowService
     */
    @Autowired
    private PtlManualRelease001Service ptlManualRelease001Service;
  
    /**
     * 界面初始化
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        this.setSelectOptionToModel(model, DictCode.释放状态, DictCode.交易场所,DictCode.登记托管机构);
        model.addAttribute("FINPROD_TYPE_TG_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        return super.init(model);
    }
    
    /**
     * 
     * 获得组合代码
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */

    /**
     * 组合代码联想文本框service层实现
     *
     * @return SuggestDto
     */
    @ResponseBody
    @RequestMapping("/getPortfolioId")
    public List<SuggestDto> getPortfolioId() {
        String portfolioId = super.getRequest().getParameter("param");
        return ptlManualRelease001Service.getPortfolioId(portfolioId);
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
        return ptlManualRelease001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));

    }
    
    
    /**
     * 证券账户账号联想文本框 <功能详细描述>
     * @param param String
     * @return List
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getSecAcctId")
    public List<SuggestDto> getSecAcctId(String param)
        throws Exception {
        List<SuggestDto> temp = ptlManualRelease001Service.getSecAcctId(param);
        return temp;
    }
    
    /**
     * 
     * 获得交易对手的联想文本框
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */

    @ResponseBody
    @RequestMapping("/getCounterId")
    public List<SuggestDto> getCounterId(String param)
        throws Exception {
        List<SuggestDto> temp = ptlManualRelease001Service.getCounterId(param);
        return temp;
    }
    
    
    /**
     * 质押式正回购分页查询
     * @return Page<PtlManualRelease001Dto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/findPageRs")
    public Page<PtlManualRelease001Dto> findPageRs()
        throws Exception {
        return ptlManualRelease001Service.selectForPageRs(RequestUtil.getParameters(getRequest()));
    }
    /**
     * 质押券查询
     * @return Page<PtlManualRelease001Dto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/findPageRepInfo")
    public Page<PtlManualRelease001Dto> findPageRepInfo()
        throws Exception {
        return ptlManualRelease001Service.findPageRepInfo(RequestUtil.getParameters(getRequest()));
    }
    /**
     * 质押式逆回购分页查询
     * @return Page<PtlManualRelease001Dto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/findPageRb")
    public Page<PtlManualRelease001Dto> findPageRb()
        throws Exception {
        return ptlManualRelease001Service.selectForPageRb(RequestUtil.getParameters(getRequest()));
    }
    
    
 
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Valid PtlManualRelease001Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    
    
    @ResponseBody
    @RequestMapping("checkB")
    public AjaxResult checkB(@RequestBody @Valid PtlManualRelease003Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * @param dto 业务实体
     * @return 画面地址
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = "revokeRs", method = RequestMethod.POST)
    public AjaxResult revokeRs(@RequestBody PtlManualRelease001Dto dto, BindingResult br) {
    	 AjaxResult ar = new AjaxResult() ;
        //dto.getSelectedItem().toArray(new PtlManualRelease001Dto[dto.getSelectedItem().size()]);
 		ptlManualRelease001Service.revokeRs(dto);
        return ar;
 
    }
    @ResponseBody
    @RequestMapping(value = "revokeRb", method = RequestMethod.POST)
    public AjaxResult revokeRb(@RequestBody PtlManualRelease001Dto dto, BindingResult br) {
    	 AjaxResult ar = new AjaxResult() ;
        //dto.getSelectedItem().toArray(new PtlManualRelease001Dto[dto.getSelectedItem().size()]);
		ptlManualRelease001Service.revokeRb(dto);
		return ar;

    }    
    
    
}
