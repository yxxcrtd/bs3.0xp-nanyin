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
import com.joyintech.tams.cfets.etrdispatcher.dto.CfetsEtrDispatcherTradeListResponse;
import com.joyintech.tams.cfets.etrdispatcher.dto.CfetsEtrDispatcherTradeListRequest;
import com.joyintech.tams.cfets.etrdispatcher.service.CfetsEtrDispatcherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.joyintech.tams.business.common.pojo.Message;


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
}
