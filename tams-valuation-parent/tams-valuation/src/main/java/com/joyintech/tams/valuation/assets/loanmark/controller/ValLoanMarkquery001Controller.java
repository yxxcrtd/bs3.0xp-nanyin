/*
 * 文件名：ValVoucherQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.assets.loanmark.controller;

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
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.valuation.assets.loanmark.dto.ValLoanMarkquery001Dto;
import com.joyintech.tams.valuation.assets.loanmark.service.ValLoanMarkquery001Service;
import com.joyintech.tams.constant.DictCode;

/**
 * 拆借标的查询
 * 
 * @author huwei
 * @version 2.0 2018年10月19日
 * @see ValLoanMarkquery001Controller
 * @since 2.0
 */
@Controller
@PageId("ValLoanMarkquery001")
@RequestMapping("/valuation/assets/loanmark/ValLoanMarkquery001")
public class ValLoanMarkquery001Controller extends BaseController {

    /**
     * ValLoanMarkquery001Service
     */
    @Autowired
    private ValLoanMarkquery001Service valLoanMarkquery001Service;

    /**
     * 重写init方法，加载页面
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.金融产品类型);
        return super.init(model);
    }

    /**
     * 债券类资产数据取得
     *
     * @return Page<ValLoanMarkquery001Dto>
     * @throws Exception 异常
     * @see
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<ValLoanMarkquery001Dto> findPage()
        throws Exception {
        return valLoanMarkquery001Service.findPage(RequestUtil.getPageParam());
    }

    /**
     * 导出信息excel
     * @return ResponseEntity
     * @throws Exception Exception
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
        throws Exception {
        String datas = this.getParam("datas");
        List<ValLoanMarkquery001Dto> listdto;
        if (datas == null) {
            listdto = (List<ValLoanMarkquery001Dto>)valLoanMarkquery001Service.findAll(
                RequestUtil.getPageParam());
        }
        else {
            listdto = JsonUtils.json2List(datas, ValLoanMarkquery001Dto.class);
        }
        File outFile = valLoanMarkquery001Service.makeFile(
            "/com/joyintech/tams/valuation/assets/loanmark/config/export_loanmarkquery001.xml",
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
