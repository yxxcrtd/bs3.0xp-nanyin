package com.joyintech.tams.svreport.chbreport.prdownequitychan.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.svreport.chbreport.common.dto.ChbreportExportDto;
import com.joyintech.tams.svreport.chbreport.common.dto.ChbreportPrdInfoDto;
import com.joyintech.tams.svreport.chbreport.prdownequitychan.service.PrdOwnEquityChanQueryMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 产品所有者权益变动表Controller
 *
 * @author WanShiWei
 * @version 1.0 2021年04月19日
 * @see PrdOwnEquityChanQuery001Controller
 * @since 1.0
 */
@Controller
@PageId("SvrPrdOwnEquityChanQuery001")
@RequestMapping("/chbreport/prdownequitychan/PrdOwnEquityChanQuery001")
public class PrdOwnEquityChanQuery001Controller extends BaseController {

    @Autowired
    private PrdOwnEquityChanQueryMService PrdOwnEquityChanQueryMService;


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
        return PrdOwnEquityChanQueryMService.selectForPage(RequestUtil.getPageParam());
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
        List<ChbreportExportDto> list = JsonUtils.json2List(datas,
                ChbreportExportDto.class);
        return PrdOwnEquityChanQueryMService.exportExcel(list);
    }
}
