package com.joyintech.tams.valuation.lossmodel.impairmentprovision.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.lossmodel.impairmentprovision.dto.ImpairmentProvision001Dto;
import com.joyintech.tams.valuation.lossmodel.impairmentprovision.service.ImpairmentProvision001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 减值情况计提表
 * @author xutong
 * @createTime 2021-07-05
 */
@Controller
@PageId("ImpairmentProvision001")
@RequestMapping("/model/impairment/impairmentprovision/ImpairmentProvision001")
public class ImpairmentProvision001Controller extends BaseController {

    @Autowired
    private ImpairmentProvision001Service impairmentProvision001Service;

    @Override
    public String init(Model model) {
        super.setSelectOptionToModel(model, DictCode.行内资产二级分类);
        Date sysDate = DateUtil.platDateFromCache();
        String morenDate = DateUtil.format(sysDate);
        model.addAttribute("sysDate", morenDate);
        return super.init(model);
    }

    /**
     * 分页查询
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("findPage")
    public List<ImpairmentProvision001Dto> findPage() throws Exception {
        Map<String, Object> params= RequestUtil.getPageParam();
        return impairmentProvision001Service.selectForPage(params);

    }

    /**
     * 产品代码
     *
     * @param params 画面参数
     * @return 业务编号：下拉框
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getProdId")
    public List<SelectOptionDto> getProdId(@RequestParam Map<String, Object> params) throws Exception {
        return impairmentProvision001Service.getProdId(params);
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
    public ResponseEntity<byte[]> export() throws Exception {
        Map<String, Object> paraMap= RequestUtil.getPageParam();
        List<ImpairmentProvision001Dto> listdto = impairmentProvision001Service.selectForPage(paraMap);

        File outFile = impairmentProvision001Service.makeFile(
                "/com/joyintech/tams/valuation/lossmodel/impairmentprovision/conf/export_impairmentprovision.xml",
                listdto);
        return ExportUtil.export(DateUtil.format(new Date()) + "-减值情况计提表" + SystemGlobalConstant.POINT
                + FileNameUtil.getFileExtName(outFile), outFile, true);
    }

}
