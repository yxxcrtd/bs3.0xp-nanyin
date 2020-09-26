/*
 * 文件名：ValBondRealRate001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2019年1月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.assets.bondrate.controller;

import java.io.File;
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
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.valuation.assets.bondrate.dto.ValBondRealRate001Dto;
import com.joyintech.tams.valuation.assets.bondrate.service.ValBondRealRate001Service;
import com.joyintech.tams.constant.DictCode;

/**
 * 债券实际利率
 * 
 * @author HuangJian
 * @version 1.0 2019年1月19日
 * @see ValBondRealRate001Controller
 * @since 1.0
 */
@Controller
@PageId("ValBondRealRate001")
@RequestMapping("/valuation/assets/bondrate/ValBondRealRate001")
public class ValBondRealRate001Controller extends BaseController {

    /**
     * ValBondRealRate001Service
     */
    @Autowired
    private ValBondRealRate001Service valBondRealRate001Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.估值交易市场);
        return super.init(model);
    }

    /**
     * 表格查询
     * 
     * @return Page<ValBondRealRate001Dto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<ValBondRealRate001Dto> findPage()
        throws Exception {
        Map<String, Object> reqMap = RequestUtil.getPageParam();
        return valBondRealRate001Service.findPage(reqMap);
    }

    /**
     * 导出信息excel
     * 
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
        throws Exception {
        String datas = this.getParam("datas");
        Map<String, Object> paraMap = RequestUtil.getPageParam();
        List<ValBondRealRate001Dto> listdto;
        if (datas == null) {
            listdto = (List<ValBondRealRate001Dto>)valBondRealRate001Service.findAll(paraMap);
        }
        else {
            listdto = JsonUtils.json2List(datas, ValBondRealRate001Dto.class);
        }
        File outFile = valBondRealRate001Service.makeFile(
            "/com/joyintech/tams/valuation/assets/bondrate/conf/export_BondRealRate001.xml",
            listdto);
        return ExportUtil.export(
            this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                                 + DateUtil.format(new Date())
                                 + SystemGlobalConstant.CLOSE_PARENTHESIS
                                 + SystemGlobalConstant.POINT
                                 + FileNameUtil.getFileExtName(outFile),
            outFile, true);
    }

    /**
     * 账套获取
     * 
     * @param request HttpServletRequest
     * @return Page<SelectOptionDto>
     * @see
     * @since 1.0
     */
    @RequestMapping("/getBooksetId")
    @ResponseBody
    public Page<SelectOptionDto> getBooksetId(HttpServletRequest request) {
        Map<String, Object> param = RequestUtil.getPageParam();
        return valBondRealRate001Service.getBooksetId(param);
    }

    /**
     * 债券获取
     * 
     * @param request HttpServletRequest
     * @return Page<SelectOptionDto>
     * @see
     * @since 1.0
     */
    @RequestMapping("/getBondId")
    @ResponseBody
    public Page<SelectOptionDto> getBondId(HttpServletRequest request) {
        Map<String, Object> param = RequestUtil.getPageParam();
        return valBondRealRate001Service.getBondId(param);
    }

}
