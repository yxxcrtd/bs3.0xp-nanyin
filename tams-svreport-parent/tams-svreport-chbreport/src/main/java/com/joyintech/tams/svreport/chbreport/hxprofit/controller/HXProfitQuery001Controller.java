package com.joyintech.tams.svreport.chbreport.hxprofit.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.svreport.chbreport.common.dto.ChbreportExportDto;
import com.joyintech.tams.svreport.chbreport.common.dto.ChbreportPrdInfoDto;
import com.joyintech.tams.svreport.chbreport.hxprofit.service.HXProfitQueryMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 华夏利润表Controller
 *
 * @author WanShiWei
 * @version 1.0 2021年04月19日
 * @see HXProfitQuery001Controller
 * @since 1.0
 */
@Controller
@PageId("SvrHXProfitQuery001")
@RequestMapping("/chbreport/hxprofit/HXProfitQuery001")
public class HXProfitQuery001Controller extends BaseController {

    @Autowired
    private HXProfitQueryMService hxProfitQueryMService;

    @Override
    public String init(Model model) {
        return super.init(model);
    }

    /**
     * 业务画面查询事件
     *
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(SVReportGlobalConstant.SEARCH_PAGE)
    public Page<ChbreportPrdInfoDto> selectAllData() throws Exception {
        return hxProfitQueryMService.selectForPage(RequestUtil.getPageParam());
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
    public ResponseEntity<byte[]> export() throws Exception {
        String datas = this.getParam("datas");
        List<ChbreportExportDto> list = JsonUtils.json2List(datas, ChbreportExportDto.class);
        return hxProfitQueryMService.exportExcel(list);
    }
}
