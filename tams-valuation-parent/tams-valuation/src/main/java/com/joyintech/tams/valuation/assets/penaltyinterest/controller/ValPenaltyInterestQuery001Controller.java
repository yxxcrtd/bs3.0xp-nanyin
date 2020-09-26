/*
 * 文件名：ValPenaltyInterestQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangqidong
 * 修改时间：2018年10月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.assets.penaltyinterest.controller;

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
import com.joyintech.tams.valuation.assets.penaltyinterest.dto.ValPenaltyInterestQuery001Dto;
import com.joyintech.tams.valuation.assets.penaltyinterest.service.ValPenaltyInterestQuery001Service;
import com.joyintech.tams.constant.DictCode;

/**
 * 逾期罚息类资产查询
 * 
 * @author zhangquan
 * @version 1.0 2019年12月24日
 * @see ValPenaltyInterestQuery001Controller
 * @since 1.0
 */
@Controller
@PageId("ValPenaltyInterestQuery001")
@RequestMapping("/valuation/assets/penaltyinterest/ValPenaltyInterestQuery001")
public class ValPenaltyInterestQuery001Controller extends BaseController {

    /**
     * valPenaltyInterestQuery001Service
     */
    @Autowired
    private ValPenaltyInterestQuery001Service valPenaltyInterestQuery001Service;

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
        return valPenaltyInterestQuery001Service.getAsset(param);
    }

    /**
     * 根据条件获取表格数据
     * 
     * @return Page<ValPenaltyInterestQuery001Dto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<ValPenaltyInterestQuery001Dto> findPage()
        throws Exception {
        return valPenaltyInterestQuery001Service.findPage(RequestUtil.getPageParam());
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
        List<ValPenaltyInterestQuery001Dto> listdto;
        if (datas == null) {
            listdto = (List<ValPenaltyInterestQuery001Dto>)valPenaltyInterestQuery001Service.findPageout(
                paraMap);
        }
        else {
            listdto = JsonUtils.json2List(datas, ValPenaltyInterestQuery001Dto.class);
        }
        File outFile = valPenaltyInterestQuery001Service.makeFile(
            "/com/joyintech/tams/valuation/assets/penaltyinterest/config/export_PenaltyInterestQuery001.xml",
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
