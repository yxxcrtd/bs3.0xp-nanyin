package com.joyintech.tams.invest.risk.creditmanagement.cemonitor.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.risk.creditmanagement.cemonitor.dto.CreditLinesDealDto;
import com.joyintech.tams.invest.risk.creditmanagement.cemonitor.service.CeMonitorReportService;

/**
 * @description: CeMonitorReport001Controller
 * @author: 裴家俊
 * @create: 2019-01-28 
 **/
@Controller
@PageId("CeMonitorReport001")
@RequestMapping("/invest/risk/creditmanagement/cemonitor/CeMonitorReport001")
public class CeMonitorReport001Controller extends BaseController  {

    /**
     * 授信额度监测查询service
     */
    @Autowired
    private CeMonitorReportService ceMonitorReportService;

    /**
     * 监督值查询的初始化
     * @return 初始化结果
     * @see
     * @since 1.0
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        this.setSelectOptionToModel(model, DictCode.监督类型,DictCode.监督状态);
        return super.init(model);
    }

    /**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<CreditLinesDealDto> selectAll()
            throws Exception {
        Map<String,Object> map = RequestUtil.getParameters(getRequest());
        Page<CreditLinesDealDto> page = ceMonitorReportService.selectForPage(map);
        return page;
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
        return ceMonitorReportService.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));

    }
}
