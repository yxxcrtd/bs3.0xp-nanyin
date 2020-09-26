/*
 * 文件名：ValSubjectBalanceAbnormalQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2019年1月28日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.financemanage.valsubjectbalanceabnormalquery.controller;

import java.io.File;
import java.util.Date;
import java.util.List;

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
import com.joyintech.tams.valuation.common.service.BaseCommonService;
import com.joyintech.tams.valuation.financemanage.valsubjectbalanceabnormalquery.dto.ValSubjectBalanceAbnormalQuery001Dto;
import com.joyintech.tams.valuation.financemanage.valsubjectbalanceabnormalquery.service.ValSubjectBalanceAbnormalQuery001Service;

/**
 * 到期产品科目余额异常查询
 * 
 * @author niuqianyu
 * @version 1.0 2019年1月28日
 * @see ValSubjectBalanceAbnormalQuery001Controller
 * @since 1.0
 */
@Controller
@PageId("ValSubjectBalanceAbnormalQuery001")
@RequestMapping("/valuation/financemanage/valsubjectbalanceabnormalquery/ValSubjectBalanceAbnormalQuery001")
public class ValSubjectBalanceAbnormalQuery001Controller extends BaseController {

    @Autowired
    private ValAssetBook001Service valAssetBook001Service;

    @Autowired
    private ValSubjectBalanceAbnormalQuery001Service valSubjectBalanceAbnormalQuery001Service;

    @Autowired
    private BaseCommonService baseCommonService;

    /**
     * 重写init方法，加载页面 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        String bookDate = baseCommonService.getSysDateString();
        if (bookDate == null || "".equals(bookDate)) {
            bookDate = DateUtil.format(new Date());
        }
        model.addAttribute("bookDate", DateUtil.addDay(bookDate, -1));
        this.setSelectOptionToModel(model, DictCode.是否);
        return super.init(model);
    }

    /**
     * 所属账套下拉框获取
     *
     * @return 返回的数据
     * @exception Exception 异常
     * @since 1.0
     */
    @RequestMapping("/findBooksetId")
    @ResponseBody
    public Page<SelectOptionDto> findBooksetId() {
        return valAssetBook001Service.findBooksetIdByPage(RequestUtil.getPageParam());
    }

    /**
     * 分页数据查询
     * 
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<ValSubjectBalanceAbnormalQuery001Dto> findPage()
        throws Exception {
        return valSubjectBalanceAbnormalQuery001Service.queryPageList(RequestUtil.getPageParam());
    }
    
    /**
     * 导出信息excel
     * 
     * @return excel
     * @throws Exception
     *             异常
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()throws Exception {
            String datas = this.getParam("datas");
            List<ValSubjectBalanceAbnormalQuery001Dto> listdto;
            if (datas == null) {
                listdto = valSubjectBalanceAbnormalQuery001Service.getAllList(RequestUtil.getPageParam());
            }
            else {
                listdto = JsonUtils.json2List(datas, ValSubjectBalanceAbnormalQuery001Dto.class);
            }
            File outFile = valSubjectBalanceAbnormalQuery001Service.makeFile(
                "/com/joyintech/tams/valuation/financemanage/valsubjectbalanceabnormalquery/config/export_ValSubjectBalanceAbnormalQuery001.xml", listdto);
            return ExportUtil.export(
                this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                                     + DateUtil.format(new Date())
                                     + SystemGlobalConstant.CLOSE_PARENTHESIS
                                     + SystemGlobalConstant.POINT
                                     + FileNameUtil.getFileExtName(outFile),
                outFile, true);
        }
}
