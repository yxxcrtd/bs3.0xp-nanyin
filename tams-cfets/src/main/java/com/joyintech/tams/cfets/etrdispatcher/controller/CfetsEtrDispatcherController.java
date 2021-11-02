/**
 * 场外指令分发
 * @author sjj
 * @version 1.0 2021年10月26日
 * @see CfetsEtrDispatcherController
 * @since 1.0
 */

package com.joyintech.tams.cfets.etrdispatcher.controller;


import com.joyintech.background.component.common.pack.common.util.StringUtil;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.business.common.dto.CfetsExportRequestDto;
import com.joyintech.tams.business.common.util.ExcelUitl;
import com.joyintech.tams.cfets.etrdispatcher.dto.*;
import com.joyintech.tams.cfets.etrdispatcher.service.CfetsEtrDispatcherService;
import com.joyintech.tams.common.base.dao.AutoDispenseRightMapper;
import com.joyintech.tams.common.base.entity.AutoDispenseRight;
import com.joyintech.tams.constant.DictCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.joyintech.tams.business.common.pojo.Message;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;


@Controller
@PageId("CfetsEtrDispatcher")
@RequestMapping("/cfets/etrdispatcher/CfetsEtrDispatcher")
@Api(tags = "银行间指令分发")
public class CfetsEtrDispatcherController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(CfetsEtrDispatcherController.class);

    @Autowired
    private CfetsEtrDispatcherService cfetsEtrDispatcherService;


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
            Page<CfetsEtrDispatcherTradeListResponse> page = cfetsEtrDispatcherService.findAllEtrDispatcherCommon(dto);
            return new Message<Page<CfetsEtrDispatcherTradeListResponse>>().success().setData(page);
        }catch (Exception e) {
            logger.error("查询指令管理分页数据异常",e);
            return new Message<Page<CfetsEtrDispatcherTradeListResponse>>().fail().setMessage("查询指令列表分页数据异常");
        }
    }

    /**
     * 指令信息明细查询
     *
     * @param dto
     * @return Page<CfetsEtrDispatcherInfoResponse>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ApiOperation(value = "指令信息明细查询", notes = "指令信息明细查询")
    @PostMapping("/getEtrDispatcherInfo")
    @ResponseBody
    public Message<CfetsEtrDispatcherInfoResponse> getEtrDispatcherInfo (@RequestBody CfetsEtrDispatcherInfoRequest dto)
            throws Exception {
        try {
            CfetsEtrDispatcherInfoResponse returnDto = cfetsEtrDispatcherService.getEtrDispatcherInfo(dto);
            return new Message<CfetsEtrDispatcherInfoResponse>().success().setData(returnDto);
        }catch (Exception e) {
            logger.error("查询指令信息明细分页数据异常",e);
            return new Message<CfetsEtrDispatcherInfoResponse>().fail().setMessage("查询指令信息明细异常");
        }
    }

//    /**
//     * 指令执行查询
//     *
//     * @param dto
//     * @return Page<CfetsEtrDispatcherInfoResponse>
//     * @throws Exception Exception
//     * @see
//     * @since 1.0
//     */
//    @ApiOperation(value = "指令执行查询", notes = "指令执行查询")
//    @PostMapping("/getEtrDispatcherExeInfo")
//    @ResponseBody
//    public Message<CfetsEtrDispatcherInfoResponse> getEtrDispatcherExeInfo (@RequestBody CfetsEtrDispatcherInfoRequest dto)
//            throws Exception {
//        try {
//            CfetsEtrDispatcherInfoResponse returnDto = cfetsEtrDispatcherService.getEtrDispatcherInfo(dto);
//            return new Message<CfetsEtrDispatcherInfoResponse>().success().setData(returnDto);
//        }catch (Exception e) {
//            logger.error("查询指令管理分页数据异常",e);
//            return new Message<CfetsEtrDispatcherInfoResponse>().fail().setMessage("查询指令执行异常");
//        }
//    }



    /**
     * 指令风险试算查询
     *
     * @param dto
     * @return Page<CfetsEtrDispatcherRskInfoResponse>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ApiOperation(value = "风险试算查询", notes = "风险试算查询")
    @PostMapping("/getEtrDispatcherRskInfo")
    @ResponseBody
    public Message<Page<CfetsEtrDispatcherRskInfoResponse>> getEtrDispatcherRskInfo (@RequestBody CfetsEtrDispatcherInfoRequest dto)
            throws Exception {
        try {
            Page<CfetsEtrDispatcherRskInfoResponse> page = cfetsEtrDispatcherService.getEtrDispatcherRskInfo(dto);
            return new Message<Page<CfetsEtrDispatcherRskInfoResponse>>().success().setData(page);
        }catch (Exception e) {
            logger.error("查询风险试算分页数据异常",e);
            return new Message<Page<CfetsEtrDispatcherRskInfoResponse>>().fail().setMessage("查询风险试算异常");
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

    /**
     * 自动分发规则、参数设置提交
     * @author sjj
     * @param cfetsDto br
     * @return Message<AjaxResult>
     */
    @ResponseBody
    @ApiOperation(value = "自动分发提交", notes = "自动分发提交")
    @PostMapping("submit")
    public Message<AjaxResult> submit(@RequestBody CfetsEtrDispatcherAutoRequest cfetsDto, BindingResult br)
            throws Exception {
        //提交check
        AjaxResult ajaxResult = cfetsEtrDispatcherService.checkSave(cfetsDto, br);
        if (!StringUtil.isEmptyList(ajaxResult.getErrorList())) {
            return new Message<AjaxResult>().fail().setData(ajaxResult);
        }

        //设置提交
        cfetsEtrDispatcherService.deleteEtrAutoTime();
        if(cfetsDto.getVdate().equals("1900-01-01") && cfetsDto.getMdate().equals("2099-12-31")){

        }
        else{
            int count = cfetsEtrDispatcherService.insertEtrAutoTime(cfetsDto);
            if(count == 0) {
                return new Message<AjaxResult>().fail().setMessage("自动分发设置失败，请联系管理员！");
            }
        }
        return new Message<AjaxResult>().success().setMessage("自动分发设置完成");
    }

    /**
     * 分发权限配置交易员查询
     *
     * @return Page<CfetsEtrDispatcherTraderAuthListResponse>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ApiOperation(value = "分发权限配置交易员查询", notes = "分发权限配置交易员查询")
    @PostMapping("/getTraderAuthList")
    @ResponseBody
    public Message<List<CfetsEtrDispatcherTraderAuthListResponse>> getTraderAuthList()
            throws Exception {
        try {
            List<CfetsEtrDispatcherTraderAuthListResponse> list = cfetsEtrDispatcherService.getTraderAuthList();
            return new Message<List<CfetsEtrDispatcherTraderAuthListResponse>>().success().setData(list);
        }catch (Exception e) {
            logger.error("查询分发权限配置交易员数据异常",e);
            return new Message<List<CfetsEtrDispatcherTraderAuthListResponse>>().fail().setMessage("查询指令列表分页数据异常");
        }
    }

    /**
     * 分发权限配置提交
     *
     * @return AjaxResult
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ApiOperation(value = "分发权限配置保存", notes = "分发权限配置保存")
    @PostMapping("/saveTraderAuthList")
    @ResponseBody
    public Message<AjaxResult> saveTraderAuthList(@RequestBody List<CfetsEtrDispatcherTraderAuthListResponse> dtoList, BindingResult br)
            throws Exception {
        //提交check
        AjaxResult ajaxResult = cfetsEtrDispatcherService.checkSaveTraderAuthList(dtoList, br);
        if (!StringUtil.isEmptyList(ajaxResult.getErrorList())) {
            return new Message<AjaxResult>().fail().setData(ajaxResult);
        }

        //保存
        ajaxResult = cfetsEtrDispatcherService.saveTraderAuthList(dtoList);
        if (!StringUtil.isEmptyList(ajaxResult.getErrorList())) {
            return new Message<AjaxResult>().fail().setData(ajaxResult);
        }else{
            ajaxResult.setMessage("分发权限配置成功");
            return new Message<AjaxResult>().success().setData(ajaxResult);
        }
    }

    @GetMapping(value="/getEtrTraderList")
    @ResponseBody
    @ApiOperation(value = "交易员列表模糊查询", notes = "交易员列表模糊查询")
    public Message<List<CfetsEtrDispatcherTraderListRequest>> getEtrTraderList() {
        Map<String,Object> param = RequestUtil.getParameters(getRequest());
        List<CfetsEtrDispatcherTraderListRequest> list = cfetsEtrDispatcherService.getEtrTraderList(param);
        return new Message<List<CfetsEtrDispatcherTraderListRequest>>().success().setData(list);
    }

    @GetMapping(value = "/getEtrTraderAndWorkloadList")
    @ResponseBody
    @ApiOperation(value = "交易员与工作量列表查询", notes = "交易员与工作量列表查询")
    public Message<List<CfetsEtrDispatcherTraderListRequest>> getEtrTraderAndWorkloadList() {
        Map<String,Object> param = RequestUtil.getParameters(getRequest());
        List<CfetsEtrDispatcherTraderListRequest> list = cfetsEtrDispatcherService.getEtrTraderAndWorkloadList(param);
        return new Message<List<CfetsEtrDispatcherTraderListRequest>>().success().setData(list);
    }
}
