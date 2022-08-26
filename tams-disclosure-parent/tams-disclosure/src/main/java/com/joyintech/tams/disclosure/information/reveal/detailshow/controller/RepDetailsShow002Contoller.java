/*
 * 文件名：ValPublicDataImport001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年3月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
*/
package com.joyintech.tams.disclosure.information.reveal.detailshow.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.getprdinfoselect.service.GetPrdInfoSelectService;
import com.joyintech.tams.disclosure.information.reveal.detailshow.dto.RepDetailsShow001Dto;
import com.joyintech.tams.disclosure.information.reveal.detailshow.service.RepDetailsShow001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 模板示例<br>
 * 账套管理
 * @author 何阳阳
 * @version 1.0 2018年3月13日
 * @see RepDetailsShow002Contoller
 * @since 1.0
 */

@Controller
@PageId("RepDetailsShow002")
@RequestMapping("/disclosure/information/reveal/detailshow/RepDetailsShow002")
public class RepDetailsShow002Contoller extends BaseController {

    @Autowired
    private RepDetailsShow001Service repDetailsShow001Service;

    @Autowired
    private GetPrdInfoSelectService getPrdInfoSelectService;
	/**
     * 页面初始化，显示画面<br>
     * 
     * @param model Model
     * @return 初始化路径
     * @see
     * @since 1.0
     */
    
    public String init(Model model) {
        model.addAttribute(ReportGlobalConstant.REPORT_SERVER_URL, CacheUtil.getSysParam(ReportGlobalConstant.REPORT_SERVER_URL));
        model.addAttribute(ReportGlobalConstant.REPORT_USERNAME, CacheUtil.getSysParam(ReportGlobalConstant.REPORT_USERNAME));
        model.addAttribute(ReportGlobalConstant.REPORT_PASSWORD, CacheUtil.getSysParam(ReportGlobalConstant.REPORT_PASSWORD));
        model.addAttribute(ReportGlobalConstant.REPORT_SERVER_PATH, CacheUtil.getSysParam(ReportGlobalConstant.REPORT_SERVER_PATH));

        model.addAttribute("repId",getParam("repId"));
        model.addAttribute("repName",getParam("repName"));

        model.addAttribute("cureDate", DateUtil.getCurrentDate());
        model.addAttribute("uri", getParam("uri"));
        return super.init(model);
    }

    @RequestMapping("/getRepsearch")
    @ResponseBody
    public RepDetailsShow001Dto getRepsearch(String repId){
        RepDetailsShow001Dto dto = repDetailsShow001Service.getRepScreenList(repId);
        return dto;
    }

    @RequestMapping(value="/previewReport", method = RequestMethod.POST)
    public void previewReport(HttpServletRequest request, HttpServletResponse response) {
        repDetailsShow001Service.previewReport(RequestUtil.getPageParam(),response);
    }

    /**
     * 分页查询产品下拉框
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("getPProdIdList")
    @ResponseBody
    public Page<SelectOptionDto> getPProdIdList(){
        Map<String, Object> param=RequestUtil.getPageParam();
        if(param.get("repId")!=null){
            repDetailsShow001Service.getRandgeRule(param);
            //param.put("checkFor", "01");
            //CheckConfirmMessage msg=repCheckInfoService.check(param);
        }
        Page<SelectOptionDto> prdInfoSelect = getPrdInfoSelectService.getPProdIdList(param);
        return prdInfoSelect;
    }

    /**
     *
     * 获取系统枚举参数
     * <功能详细描述>
     * @param
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("getHalfYear")
    @ResponseBody
    public List<SelectOptionDto> getHalfYear(){
        SelectDto selectDto = this.getSelectDataFromDataDic(DictCode.半年, null);
        List<SelectOptionDto> list = selectDto.getOptions();
        return list;
    }


    /**
     *
     * 获取季度枚举值
     * <功能详细描述>
     * @param
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("getQuarter")
    @ResponseBody
    public List<SelectOptionDto> getQuarter(){
        SelectDto selectDto = this.getSelectDataFromDataDic(DictCode.季度, null);
        List<SelectOptionDto> list = selectDto.getOptions();
        return list;
    }

    /**
     *
     * 获取子产品代码下拉框
     * <功能详细描述>
     * @param
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("getProdIdList")
    @ResponseBody
    public Page<SelectOptionDto> getProdIdList(){
        Map<String, Object> param= RequestUtil.getPageParam();
        Page<SelectOptionDto> prdInfoSelect = getPrdInfoSelectService.getProdIdList(param);
        return prdInfoSelect;
    }

    /**
     *
     * 获取费率名称下拉框
     * <功能详细描述>
     * @param
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("getFeeNameList")
    @ResponseBody
    public Page<SelectOptionDto> getFeeNameList(){
        Map<String, Object> param=RequestUtil.getPageParam();
        Page<SelectOptionDto> feeNameList = getPrdInfoSelectService.getFeeNameList(param);
        return feeNameList;
    }

    /**
     *
     * 获取要素名称下拉框
     * <功能详细描述>
     * @param
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("getElementNameList")
    @ResponseBody
    public Page<SelectOptionDto> getElementNameList(){
        Map<String, Object> param=RequestUtil.getPageParam();
        Page<SelectOptionDto> elementNameList = getPrdInfoSelectService.getElementNameList(param);
        return elementNameList;
    }
    
}