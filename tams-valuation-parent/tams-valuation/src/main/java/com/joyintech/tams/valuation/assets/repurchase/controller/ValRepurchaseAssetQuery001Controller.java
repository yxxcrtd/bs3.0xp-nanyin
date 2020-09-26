/*
 * 文件名：ValRepurchaseAssetQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangqidong
 * 修改时间：2018年10月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.assets.repurchase.controller;

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
import com.joyintech.tams.valuation.assets.repurchase.dto.ValRepurchaseAssetQuery001Dto;
import com.joyintech.tams.valuation.assets.repurchase.service.ValRepurchaseAssetQuery001Service;
import com.joyintech.tams.constant.DictCode;

/**
 * 回购类资产查询
 * 
 * @author wangqidong
 * @version 1.0 2018年10月19日
 * @see ValRepurchaseAssetQuery001Controller
 * @since 1.0
 */
@Controller
@PageId("ValRepurchaseAssetQuery001")
@RequestMapping("/valuation/assets/repurchase/ValRepurchaseAssetQuery001")
public class ValRepurchaseAssetQuery001Controller extends BaseController {

    /**
     * valRepurchaseAssetQuery001Service
     */
    @Autowired
    private ValRepurchaseAssetQuery001Service valRepurchaseAssetQuery001Service;

    /**
     * 重写init方法，加载页面{@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.金融产品类型);
        return super.init(model);
    }

    /**
     * 资产下拉框初始化
     * 
     * @param request request
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    @RequestMapping("/getAsset")
    @ResponseBody
    public List<SelectOptionDto> getAsset(HttpServletRequest request) {
        Map<String, Object> param = RequestUtil.getPageParam();
        return valRepurchaseAssetQuery001Service.getAsset(param);
    }

    /**
     * 根据条件获取表格数据
     * 
     * @return Page<ValRepurchaseAssetQuery001Dto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<ValRepurchaseAssetQuery001Dto> findPage()
        throws Exception {
        return valRepurchaseAssetQuery001Service.findPage(RequestUtil.getPageParam());
    }

    /**
     * 导出功能 <功能详细描述>
     * 
     * @return ResponseEntity<byte[]>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
        throws Exception {
        String datas = this.getParam("datas");
        Map<String, Object> paraMap = RequestUtil.getPageParam();
        List<ValRepurchaseAssetQuery001Dto> listdto;
        if (datas == null) {
            listdto = (List<ValRepurchaseAssetQuery001Dto>)valRepurchaseAssetQuery001Service.findPageout(
                paraMap);
        }
        else {
            listdto = JsonUtils.json2List(datas, ValRepurchaseAssetQuery001Dto.class);
        }
        File outFile = valRepurchaseAssetQuery001Service.makeFile(
            "/com/joyintech/tams/valuation/assets/repurchase/config/export_RepurchaseAssetQuery001.xml",
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
