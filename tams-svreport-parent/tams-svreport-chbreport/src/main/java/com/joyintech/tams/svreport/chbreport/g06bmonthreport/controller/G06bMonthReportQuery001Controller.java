package com.joyintech.tams.svreport.chbreport.g06bmonthreport.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.svreport.chbreport.common.dto.ChbreportExportDto;
import com.joyintech.tams.svreport.chbreport.common.dto.ChbreportPrdInfoDto;
import com.joyintech.tams.svreport.chbreport.g06bmonthreport.service.G06bMonthReportQueryMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * G06b风险资本计算表Controller
 *
 * @author WanShiWei
 * @version 1.0 2021年04月19日
 * @see G06bMonthReportQuery001Controller
 * @since 1.0
 */
@Controller
@PageId("SvrG06bMonthReportQuery001")
@RequestMapping("/chbreport/g06bmonthreport/G06bMonthReportQuery001")
public class G06bMonthReportQuery001Controller extends BaseController {

    @Autowired
    private G06bMonthReportQueryMService G06bMonthReportQueryMService;

    @Override
    public String init(Model model) {
        return super.init(model);
    }

    /**
     * 业务画面查询事件
     */
    @ResponseBody
    @RequestMapping(SVReportGlobalConstant.SEARCH_PAGE)
    public Page<ChbreportPrdInfoDto> selectAllData() throws Exception {
        return G06bMonthReportQueryMService.selectForPage(RequestUtil.getPageParam());
    }

    /**
     * 导出信息excel
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export() throws Exception {
        String datas = this.getParam("datas");
        List<ChbreportExportDto> list = JsonUtils.json2List(datas, ChbreportExportDto.class);
        return G06bMonthReportQueryMService.exportExcel(list);
    }
}
