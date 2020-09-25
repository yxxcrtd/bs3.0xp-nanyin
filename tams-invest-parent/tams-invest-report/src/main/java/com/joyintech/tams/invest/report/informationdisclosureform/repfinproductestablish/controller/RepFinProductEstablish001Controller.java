/*
 * 文件名：ProductStartAndEndAnno001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年7月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.report.informationdisclosureform.repfinproductestablish.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.report.informationdisclosureform.repfinproductestablish.dto.RepFinProductEstablishDto;
import com.joyintech.tams.invest.report.informationdisclosureform.repfinproductestablish.service.RepFinProductEstablishService;

/**
 * 产品到期公告
 * 〈功能详细描述〉
 * @author 胡文彬
 * @version 1.0 2018年7月30日
 * @see RepFinProductEstablish001Controller
 * @since 1.0
 */
@Controller
@PageId("RepFinProductEstablish001")
@RequestMapping("/invest/report/informationdisclosureform/repfinproductestablish/RepFinProductEstablish001")
public class RepFinProductEstablish001Controller extends BaseController {
    /**
     * 
     */
    @Autowired
    private RepFinProductEstablishService repFinProductEstablishService;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        // TODO Auto-generated method stub
        List<SelectOptionDto> list = new ArrayList<SelectOptionDto>();
        SelectOptionDto dto = new SelectOptionDto();
        dto.setId(DictCodeList.文件类型_投管_产品到期公告_横表.getValue());
        dto.setText(DictCodeList.文件类型_投管_产品到期公告_横表.getCodeName());
        SelectOptionDto dto1 = new SelectOptionDto();
        dto1.setId(DictCodeList.文件类型_投管_产品到期公告_竖表.getValue());
        dto1.setText(DictCodeList.文件类型_投管_产品到期公告_竖表.getCodeName());
        list.add(dto);
        list.add(dto1);
        model.addAttribute("FILE_TYPE_FG1", JsonUtils.objectArray2Json(list.toArray()));
        List<SelectOptionDto> list1 = new ArrayList<SelectOptionDto>();
        SelectOptionDto dto3 = new SelectOptionDto();
        dto3.setId(DictCodeList.文件类型_投管_产品成立公告_横表.getValue());
        dto3.setText(DictCodeList.文件类型_投管_产品成立公告_横表.getCodeName());
        SelectOptionDto dto4 = new SelectOptionDto();
        dto4.setId(DictCodeList.文件类型_投管_产品成立公告_竖表.getValue());
        dto4.setText(DictCodeList.文件类型_投管_产品成立公告_竖表.getCodeName());
        list1.add(dto3);
        list1.add(dto4);
        model.addAttribute("FILE_TYPE_FG", JsonUtils.objectArray2Json(list1.toArray()));
        this.setSelectOptionToModel(model,DictCode.销售部门);
        return super.init(model);
    }
    /**
     * 
     * 页面查询
     * <功能详细描述>
     * @return Page<RepFinProductEstablishDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<RepFinProductEstablishDto> selectAll()
        throws Exception {
        Page<RepFinProductEstablishDto> page = repFinProductEstablishService.select(
            RequestUtil.getParameters(getRequest()));
        return page;
    }
    /**
     * 
     * 页面下拉联想框
     * <功能详细描述>
     * @return List<SelectOptionDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getPortfolioId")
    public List<SelectOptionDto> getPortfolioId()
        throws Exception {
        
        return repFinProductEstablishService.getSelectOption(RequestUtil.getParameters(getRequest()));
    }
    
    
    /**
     * 生成公告时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupDelete.class}) RepFinProductEstablishDto dto, BindingResult result) {
        return commonCheck(result, "");
    }
    
    /**
     * 
     * 生成公告
     * <功能详细描述>
     * @throws IOException  IOException
     * @see
     * @return ResponseEntity<byte[]>
     * @since 1.0
     */
    @RequestMapping("/generateBulletin")
    public ResponseEntity<byte[]> generateBulletin() throws IOException {
        Map<String,Object> param = RequestUtil.getPageParam();
        RepFinProductEstablishDto temp = JsonUtils.json2Object((String)param.get("dtoStr"),RepFinProductEstablishDto.class);
        return repFinProductEstablishService.generateBulletin(temp);
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
        return repFinProductEstablishService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));

    }

}
