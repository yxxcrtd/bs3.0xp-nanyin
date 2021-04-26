package com.joyintech.tams.invest.portfolio.ptlsingleassetpositionanalysis.controller;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.invest.portfolio.ptlsingleassetpositionanalysis.dto.PtlSingleAssetPositionAnalysis001Dto;
import com.joyintech.tams.invest.portfolio.ptlsingleassetpositionanalysis.service.PtlSingleAssetPositionAnalysis001Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 单一资产持仓分析Controller
 * @author wanghb
 * @version 1.0 2019年1月23日
 * @see PtlSingleAssetPositionAnalysis001Controller
 * @since 1.0
 */
@Controller
@PageId("PtlSingleAssetPositionAnalysis001")
@RequestMapping("/invest/portfolio/ptlsingleassetpositionanalysis/PtlSingleAssetPositionAnalysis001")
public class PtlSingleAssetPositionAnalysis001Controller extends BaseController {
    @Autowired
    private PtlSingleAssetPositionAnalysis001Service ptlSingleAssetPositionAnalysis001Service;

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        Date sysDate = DateUtil.platDateFromCache();
        String morenDate = DateUtil.format(sysDate);
        model.addAttribute("sysDate", morenDate);
        model.addAttribute("AUTHORITY_TYPE", CacheUtil.getSysParam("AUTHORITY_TYPE"));
        return super.init(model);
    }

    /**
     * 画面查询方法
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<PtlSingleAssetPositionAnalysis001Dto> selectAll()
            throws Exception {
        Map<String, Object> map = RequestUtil.getPageParam();
        Page<PtlSingleAssetPositionAnalysis001Dto> page = ptlSingleAssetPositionAnalysis001Service.selectForPage(map);
        return page;
    }

    /**
     * 画面查询方法
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findList")
    public List<PtlSingleAssetPositionAnalysis001Dto> selectAllList()
            throws Exception {
        Map<String, Object> map = RequestUtil.getPageParam();
        List<PtlSingleAssetPositionAnalysis001Dto> list = ptlSingleAssetPositionAnalysis001Service.selectForList(map);
        return list;
    }

    /**
     * 资产代码的下拉框事件
     *
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getFinprodMarketId")
    public Page<SelectOptionDto> getFinprodMarketId() {
        Map<String, Object> map = RequestUtil.getPageParam();
        Page<SelectOptionDto> temp = ptlSingleAssetPositionAnalysis001Service.getFinprodMarketId(map);
        return temp;
    }
//    /**
//     * 导出信息excel
//     * @return excel
//     * @throws Exception 异常
//     * @see
//     * @since 1.0
//     */
//
//    @RequestMapping("export")
//    public ResponseEntity<byte[]> export()
//            throws Exception {
//
//        List<PtlSingleAssetPositionAnalysis001Dto> dtoList = new ArrayList<PtlSingleAssetPositionAnalysis001Dto>();
//        dtoList= ptlSingleAssetPositionAnalysis001Service.selectForList(RequestUtil.getParameters(getRequest()));
//        File outFile = ptlSingleAssetPositionAnalysis001Service.makeFile(
//            "/com/joyintech/tams/invest/portfolio/ptlsingleassetpositionanalysis/conf/export_conf.xml", dtoList);
//        return ExportUtil.export(
//            this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
//                                 + DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS
//                                 + SystemGlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
//            outFile, true);
//    }

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
        return ptlSingleAssetPositionAnalysis001Service.export(this.getPageId(),
                //TISHKBS-2036 【单一资产持仓分析】：当前导出excel的日期是以平台日为准，请优化为已查询日为准 wyn 2021/4/25
                RequestUtil.getParameters(getRequest()),"searchDate");
    }
}