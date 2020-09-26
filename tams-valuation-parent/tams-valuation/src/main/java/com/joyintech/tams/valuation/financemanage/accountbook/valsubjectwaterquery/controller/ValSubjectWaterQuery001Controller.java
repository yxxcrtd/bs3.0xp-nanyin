/*
 * 文件名：ValSubjectWaterQuery001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年3月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
*/
package com.joyintech.tams.valuation.financemanage.accountbook.valsubjectwaterquery.controller;

import java.io.File;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.accounadjustmanage.assetbook.service.ValAssetBook001Service;
import com.joyintech.tams.valuation.financemanage.accountbook.valsubjectwaterquery.dto.ValSubjectWaterQuery001Dto;
import com.joyintech.tams.valuation.financemanage.accountbook.valsubjectwaterquery.dto.ValSubjectWaterQuery001SearchDto;
import com.joyintech.tams.valuation.financemanage.accountbook.valsubjectwaterquery.service.ValSubjectWaterQuery001Service;

/**
 * 科目流水查询
 * 
 * @author 何阳阳
 * @version 1.0 2018年3月13日
 * @see ValSubjectWaterQuery001Controller
 * @since 1.0
 */

@Controller
@PageId("ValSubjectWaterQuery001")
@RequestMapping("/valuation/financemanage/accountbook/valsubjectwaterquery/ValSubjectWaterQuery001")
public class ValSubjectWaterQuery001Controller extends BaseController {

    @Autowired
    private ValSubjectWaterQuery001Service valSubjectWaterQuery001Service;
    
    @Autowired
    private ValAssetBook001Service valAssetBook001Service;

    /**
     *  重写init方法，加载页面 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.币种);
        //this.setSelectOptionToModel(model, DictCode.业务类型);
        this.setSelectOptionToModel(model, DictCode.会计场景代码);
        //this.setSelectOptionToModel(model, DictCode.凭证类型);
        //List<SelectOptionDto> booksetIds = valSubjectWaterQuery001Service.findBooksets();
        // 所属账套控件初期化
        //model.addAttribute("booksetIds",JsonUtils.object2Json(booksetIds));

        return super.init(model);
    }
    
    /**
     * 科目号下拉框获取
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @RequestMapping("/getSubjectNo")
    @ResponseBody
    public Page<SelectOptionDto> getSubjectNo(HttpServletRequest request) {
        Map<String, Object> params =RequestUtil.getPageParam();
        return valSubjectWaterQuery001Service.getSubjectNo(params);
       }
    
    /**
     * 所属账套下拉框获取
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @RequestMapping("/findBooksetId")
    @ResponseBody
    public Page<SelectOptionDto> findBooksetId(){
        return valAssetBook001Service.findBooksetIdByPage(RequestUtil.getPageParam());
    }

//    /**
//     * 查询数据 <功能详细描述>
//     * 
//     * @return List<ValVouchMain>
//     * @throws ParseException
//     * @see
//     * @since 1.0
//     */
//    @RequestMapping("ValSubjectWaterQuery001search")
//    @ResponseBody
//    public List<ValSubjectWaterQuery001Dto> findPage() throws Exception {
//        Map<String, Object> params= this.getRequestMap();
//        ValSubjectWaterQuery001SearchDto dto = (ValSubjectWaterQuery001SearchDto)JsonUtils.map2entity(params, ValSubjectWaterQuery001SearchDto.class);
//        return valSubjectWaterQuery001Service.getSubjectWater(dto);
//
//    }
//    
    /**
     * 
     * 分页数据查询
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<ValSubjectWaterQuery001Dto> findPage() throws Exception{
        return valSubjectWaterQuery001Service.findPage(RequestUtil.getPageParam());
    }
    
    /**
     * 
     * 获取页面参数 页面中有时间参数，获取参数后将时间转换成Date类型
     * 
     * @return paraMap
     * @throws ParseException 
     * @see
     * @since 1.0
     */
    public Map<String, Object> getRequestMap() throws ParseException {
        Map<String, Object> paraMap = RequestUtil.getPageParam();
        // 入账日期区间
        String bookDateBegin = this.getParam("bookDateBegin");
        String bookDateEnd = this.getParam("bookDateEnd");
        if(StringUtil.isNotEmpty(bookDateBegin)){
            paraMap.put("bookDateBegin", DateUtil.parseDate(bookDateBegin, "yyyy-MM-dd"));
        }
        if(StringUtil.isNotEmpty(bookDateEnd)){
            paraMap.put("bookDateEnd", DateUtil.parseDate(bookDateEnd, "yyyy-MM-dd"));
        }
        return paraMap;
    }

//    /**
//     * 导出数据
//     * 
//     * @return ResponseEntity<byte[]>
//     * @throws Exception ExportUtil.export
//     * @see
//     * @since 1.0
//     */
//    public ResponseEntity<byte[]> export2()
//        throws Exception {
//        String datas = this.getParam("datas");
//        Map<String, Object> params = this.getRequestMap();
//        ValSubjectWaterQuery001SearchDto dto = (ValSubjectWaterQuery001SearchDto)JsonUtils.map2entity(
//            params, ValSubjectWaterQuery001SearchDto.class);
//        List<ValSubjectWaterQuery001Dto> listdto;
//        if (datas == null) {
//            listdto = valSubjectWaterQuery001Service.getSubjectWater(dto);
//        }
//        else {
//            listdto = JsonUtils.json2List(datas, ValSubjectWaterQuery001Dto.class);
//        }
//        File outFile = valSubjectWaterQuery001Service.makeFile(
//            "/com/joyintech/tams/valuation/financemanage/accountbook/valsubjectwaterquery/config/export_subjectwaterquery.xml",
//            listdto);
//        return ExportUtil.export(
//            this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
//                                 + DateUtil.format(new Date())
//                                 + SystemGlobalConstant.CLOSE_PARENTHESIS
//                                 + SystemGlobalConstant.POINT
//                                 + FileNameUtil.getFileExtName(outFile),
//            outFile, true);
//    }
//  
//    @RequestMapping(ACTION_EXPORT)
//    public ResponseEntity<byte[]> export()
//        throws Exception {
//        return valSubjectWaterQuery001Service.export(this.getPageId(), RequestUtil.getPageParam());
//
//    }
    
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
        throws Exception {
        String datas = this.getParam("datas");
        List<ValSubjectWaterQuery001Dto> listdto;
        if (datas == null) {
        	return valSubjectWaterQuery001Service.export(this.getPageId(), RequestUtil.getPageParam());
        }
        listdto = JsonUtils.json2List(datas, ValSubjectWaterQuery001Dto.class);
        File outFile = valSubjectWaterQuery001Service.makeFile(
                "/com/joyintech/tams/valuation/financemanage/accountbook/valsubjectwaterquery/config/export_subjectwaterquery.xml",
                listdto);
            return ExportUtil.export(
                this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                                     + DateUtil.format(new Date())
                                     + SystemGlobalConstant.CLOSE_PARENTHESIS
                                     + SystemGlobalConstant.POINT
                                     + FileNameUtil.getFileExtName(outFile),
                outFile, true);
    }
}