package com.joyintech.tams.disclosure.information.config.repprodinfo.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.disclosure.information.config.repprodinfo.service.ProdInfoCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wubingbing
 * @date 2022-05-09 11:14
 */
@Controller
@PageId("ProdInfoCommon")
@RequestMapping("/disclosure/information/config/repprodinfo/ProdInfoCommon")
public class ProdInfoCommonontroller extends BaseController {

    @Autowired
    private ProdInfoCommonService prodInfoCommonService;

    /**
     * 获取所有产品登记编码
     *
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("getRegistNoList")
    public Page<SelectOptionDto> getRegistNoList()
            throws Exception {
        Page<SelectOptionDto> temp = (Page<SelectOptionDto>) prodInfoCommonService.getRegistNo();
        return temp;
    }


    /**
     * 根据产品登记编码 获取产品主代码和产品名称,支持联动
     *
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("getProdIdAndNameByRegistNo")
    public Page<SelectOptionDto> getProdIdlist()
            throws Exception {
        Page<SelectOptionDto> temp = (Page<SelectOptionDto>) prodInfoCommonService.getProdIdAndNameByRegistNo();
        return temp;
    }


    /**
     * 查询母产品和普通产品的产品代码和产品名称
     *
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("getMontherAndCommonProdId")
    public Page<SelectOptionDto> getMontherAndCommonProdId()
            throws Exception {
        Page<SelectOptionDto> temp = (Page<SelectOptionDto>) prodInfoCommonService.getMontherAndCommonProdId();
        return temp;
    }


    /*    *//**
     * 根据产品登记编码 获取产品主代码和产品名称,支持联动
     * @return
     * @throws Exception
     *//*
    @ResponseBody
    @RequestMapping("getProdIdAndNameByRegistNo")
    public Page<SelectOptionDto> getProdIdlist()
            throws Exception {
        Page<SelectOptionDto> temp = (Page<SelectOptionDto>) prodInfoCommonService.getProdIdAndNameByRegistNo(RequestUtil.getPageParam());
        return temp;
    }*/


}
