/**
 * 场外指令分发
 * @author sjj
 * @version 1.0 2021年10月26日
 * @see CfetsEtrDispatcherController
 * @since 1.0
 */

package com.joyintech.tams.cfets.etrdispatcher.controller;


import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.business.common.dto.CfetsExportRequestDto;
import com.joyintech.tams.business.common.util.ExcelUitl;
import com.joyintech.tams.cfets.etrdispatcher.dto.CfetsEtrDispatcherTradeListResponse;
import com.joyintech.tams.cfets.etrdispatcher.dto.CfetsEtrDispatcherTradeListRequest;
import com.joyintech.tams.cfets.etrdispatcher.dto.CfetsEtrDispatcherTraderListRequest;
import com.joyintech.tams.cfets.etrdispatcher.service.CfetsEtrDispatcherService;
import com.joyintech.tams.cfets.etrib.dto.CfetsEtrIbBondinfo001Dto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.joyintech.tams.business.common.pojo.Message;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@PageId("CfetsEtrDispatcher")
@RequestMapping("/cfets/etrdispatcher/CfetsEtrDispatcher")
@Api(tags = "银行间指令分发")
public class CfetsEtrDispatcherController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(CfetsEtrDispatcherController.class);

    @Autowired
    private CfetsEtrDispatcherService cfetsEtrDispatcherService;

    /**
     * 多组合指令新建画面
     * @param model
     * @return
     */
    @Override
    public String init(Model model) {
        return super.init(model);
    }

    /**
     * 指令列表分页查询
     *
     * @param dto
     * @return Page<CfetsEtrDispatcherTradeListResponse>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ApiOperation(value = "指令列表分页查询", notes = "指令列表分页查询")
    @PostMapping("/findAllEtrDispatcherCommon")
    @ResponseBody
    public Message<Page<CfetsEtrDispatcherTradeListResponse>> findAllEtrDispatcherCommon(@RequestBody CfetsEtrDispatcherTradeListRequest dto)
            throws Exception {
        try {
            Page<CfetsEtrDispatcherTradeListResponse> list = cfetsEtrDispatcherService.findAllEtrDispatcherCommon(dto);
            return new Message<Page<CfetsEtrDispatcherTradeListResponse>>().success().setData(list);
        }catch (Exception e) {
            logger.error("查询指令管理分页数据异常",e);
            return new Message<Page<CfetsEtrDispatcherTradeListResponse>>().fail().setMessage("查询指令列表分页数据异常");
        }
    }

    @PostMapping(value="/exportEtrDispatcherList")
    @ResponseBody
    @ApiOperation(value = "指令分发列表导出", notes = "指令分发列表导出")
    public void exportEtrDispatcherList(@RequestBody CfetsExportRequestDto req, HttpServletResponse response) {
        // 导出
        ExcelUitl.doExport(req, response, "指令分发", () -> {
            List dataList = cfetsEtrDispatcherService.findAllEtrDispatcherCommon(req.getDto(CfetsEtrDispatcherTradeListRequest.class)).getRoot();
            return dataList;
        });
    }

    @PostMapping(value="/getEtrTraderList")
    @ResponseBody
    @ApiOperation(value = "交易员列表模糊查询", notes = "交易员列表模糊查询")
    public Message<List<CfetsEtrDispatcherTraderListRequest>> getEtrTraderList() {
        Map<String,Object> param = RequestUtil.getParameters(getRequest());
        List<CfetsEtrDispatcherTraderListRequest> list = cfetsEtrDispatcherService.getEtrTraderList(param);
        return new Message<List<CfetsEtrDispatcherTraderListRequest>>().success().setData(list);
    }
}
