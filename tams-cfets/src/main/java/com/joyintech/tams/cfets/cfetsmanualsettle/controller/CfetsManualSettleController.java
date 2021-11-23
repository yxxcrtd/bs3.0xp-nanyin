package com.joyintech.tams.cfets.cfetsmanualsettle.controller;


import com.joyintech.background.component.common.pack.common.util.StringUtil;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.tams.business.common.dto.CfetsExportRequestDto;
import com.joyintech.tams.business.common.pojo.Message;
import com.joyintech.tams.business.common.util.ExcelUitl;
import com.joyintech.tams.business.common.util.PositionUtil;
import com.joyintech.tams.cfets.cfetsmanualsettle.dto.*;
import com.joyintech.tams.cfets.cfetsmanualsettle.service.CfetsManualSettleService;
import com.joyintech.tams.cfets.etrdispatcher.dto.CfetsEtrDispatcherInfoRequest;
import com.joyintech.tams.cfets.etrdispatcher.dto.CfetsEtrDispatcherTradeListRequest;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.trade.manualsettle.dto.ManualSettleDto;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@PageId("CfetsManualSettle")
@RequestMapping("/cfets/manualsettle/ManualSettle")
@Api(tags = "银行间手工交收")
public class CfetsManualSettleController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(CfetsManualSettleController.class);

    @Autowired
    private CfetsManualSettleService cfetsManualSettleService;

    @Autowired
    private IPlatformDate platformDateImpl;

    @ApiOperation(value = "查询页面初始化参数", notes = "查询页面初始化参数")
    @PostMapping("/getPageParams")
    @ResponseBody
    public Message<Map<String, Object>> getPageParams(){
        Map<String, Object> initPageParams = new HashMap<>();
        //数据字典初始化数据
        //资产类型
        initPageParams.put("PINAN_ASSET_TYPE", this.getSelectDataFromDataDic(DictCode.平安资产类型, null));
        //业务类型
        initPageParams.put("FINPROD_TYPE_TG", this.getSelectDataFromDataDic(DictCode.金融产品类型_投管用, null));
        //交易方向
        initPageParams.put("PS", this.getSelectDataFromDataDic(DictCode.交易方向, null));
        //清算状态
        initPageParams.put("SETTLE_STATUS", this.getSelectDataFromDataDic(DictCode.清算状态, null));
        //结算指令状态
        initPageParams.put("CLEAN_STL", this.getSelectDataFromDataDic(DictCode.清算状态, null));
        //结算合同状态
        initPageParams.put("CLEAN_ARG", this.getSelectDataFromDataDic(DictCode.清算状态, null));

        // 平台日字符串格式
        initPageParams.put("SESSION_SYS_DATE", DateUtil.format(platformDateImpl.getSysDate()));

        initPageParams.put("pageId", "CfetsManualSettle");
        return new Message<Map<String, Object>>(initPageParams).success();
    }

    /**
     * 银行间手工交收分页查询
     *
     * @param dto
     * @return Page<CfetsManualSettleTradeListRespond>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ApiOperation(value = "银行间手工交收分页查询", notes = "银行间手工交收分页查询")
    @PostMapping("/findPage")
    @ResponseBody
    public Message<Page<CfetsManualSettleTradeListRespond>> findPage(@RequestBody CfetsManualSettleTradeListRequest dto)
            throws Exception {
        try {
            Page<CfetsManualSettleTradeListRespond> page = cfetsManualSettleService.findPage(dto);
            return new Message<Page<CfetsManualSettleTradeListRespond>>().success().setData(page);
        } catch (Exception e) {
            logger.error("查询指令管理分页数据异常", e);
            return new Message<Page<CfetsManualSettleTradeListRespond>>().fail().setMessage("查询银行间手工交收分页数据异常");
        }
    }

    @PostMapping(value = "/exportManualSettleTradeList")
    @ResponseBody
    @ApiOperation(value = "手工交收列表导出", notes = "手工交收列表导出")
    public void exportEtrDispatcherList(@RequestBody CfetsExportRequestDto req, HttpServletResponse response) {
        // 导出
        ExcelUitl.doExport(req, response, "手工交收", () -> {
            List dataList = cfetsManualSettleService.findPage(req.getDto(CfetsManualSettleTradeListRequest.class)).getRoot();
            return dataList;
        });
    }

    /**
     * 交收流水列表分页查询
     *
     * @param dto
     * @return Page<CfetsManualSettleFlowRespond>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ApiOperation(value = "交收流水列表分页查询", notes = "交收流水列表分页查询")
    @PostMapping("/findManualSettleFlow")
    @ResponseBody
    public Message<Page<CfetsManualSettleFlowRespond>> findManualSettleFlow(@RequestBody CfetsManualSettleCommonRequest dto)
            throws Exception {
        try {
            Page<CfetsManualSettleFlowRespond> page = cfetsManualSettleService.findManualSettleFlow(dto);
            return new Message<Page<CfetsManualSettleFlowRespond>>().success().setData(page);
        } catch (Exception e) {
            logger.error("查询交收流水列表数据异常", e);
            return new Message<Page<CfetsManualSettleFlowRespond>>().fail().setMessage("查询交收流水列表数据异常");
        }
    }

    @ApiOperation(value = "只读表单设置及数据查询_成交信息查询", notes = "只读表单设置及数据查询_成交信息查询")
    @PostMapping("/findManualSettleInfo/{pageCode}/{dtCode}/{spcdCode}")
    @ResponseBody
    public Message<Map<String,Object>> findManualSettleInfo(@PathVariable("pageCode") String pageCode,
                                                              @PathVariable("dtCode") String dtCode,
                                                              @PathVariable("spcdCode") String spcdCode,
                                                              @RequestBody CfetsManualSettleCommonRequest dto) throws Exception {
        try {
            Map<String,Object> resultData = cfetsManualSettleService.findManualSettleInfo(pageCode,dtCode,spcdCode,dto);
            return new Message<Map<String,Object>>().success().setData(resultData);
        } catch (Exception e) {
            logger.error("查询成交信息数据异常", e);
            return new Message<Map<String,Object>>().fail().setMessage("查询成交信息数据异常");
        }
    }

    /**
     * 手动交收
     * 前台传入整条数据
     * @param cfetsRequest br
     * @return Message<AjaxResult>
     * @author sjj
     */
    @ResponseBody
    @ApiOperation(value = "交收", notes = "交收")
    @PostMapping("doManualSettle")
    public Message<AjaxResult> doManualSettle(@RequestBody CfetsManualSettleRequest cfetsRequest, BindingResult br)
            throws Exception {
        //画面Check
        String returnMsg= cfetsManualSettleService.checkManualSettleSave(cfetsRequest, br);
        if (StringUtil.isNotBlank(returnMsg)) {
            return new Message<AjaxResult>().fail().setMessage(returnMsg);
        }
        if(StringUtil.equals(cfetsRequest.getButtonType(), DictCodeListExt3.交收操作类型_交收修改.getValue())){

        }
        //保存
        ProcessParameter<CfetsManualSettleRequest> processParameter = new ProcessParameter<CfetsManualSettleRequest>();
        WorkFlowParameter<CfetsManualSettleRequest> params = new WorkFlowParameter<CfetsManualSettleRequest>();
        params.setPageId("CfetsManualSettle");
        params.setElementId(cfetsRequest.getElementId());
        params.setBusinessCode(IDGenerator.generate());
        params.setBusinessType(DictCodeListExt3.共通业务类型_手工交收.getValue());
//        //交收
//        if(StringUtil.equals(cfetsRequest.getButtonType(),DictCodeListExt3.交收操作类型_交收.getValue())){
//        }//交收失败
//        else if(StringUtil.equals(cfetsRequest.getButtonType(),DictCodeListExt3.交收操作类型_交收失败.getValue())){
//
//        }//回溯
//        else if(StringUtil.equals(cfetsRequest.getButtonType(),DictCodeListExt3.交收操作类型_回溯.getValue())){
//
//        }//交收修改
//        else if(StringUtil.equals(cfetsRequest.getButtonType(),"05")){
//
//        }
        processParameter.setWorkFlowParameter(params);
        try{
            String msg = cfetsManualSettleService.start(processParameter,cfetsRequest);
//        String msg = cfetsManualSettleService.start(processParameter,
//                cfetsRequest.getSelectedItem().toArray(new CfetsManualSettleRequest[]{}));
            return new Message<AjaxResult>().success().setMessage("处理完成");
        }catch (Exception e) {
            logger.error("交收失败",e);
            return new Message<AjaxResult>().fail().setMessage("交收失败");
            //return PositionUtil.ajaxErrorReturn(e);
        }
    }


//    /**
//     * 手动交收失败
//     * 前台传入整条数据
//     * @param cfetsRequest br
//     * @return Message<AjaxResult>
//     * @author sjj
//     */
//    @ResponseBody
//    @ApiOperation(value = "交收失败", notes = "交收失败")
//    @PostMapping("doManualSettleFaile")
//    public Message<AjaxResult> doManualSettleFaile(@RequestBody CfetsManualSettleRequest cfetsRequest, BindingResult br)
//            throws Exception {
//        //画面Check
//        String returnMsg= cfetsManualSettleService.checkManualSettleFaile(cfetsRequest, br);
//        if (StringUtil.isNotBlank(returnMsg)) {
//            return new Message<AjaxResult>().fail().setMessage(returnMsg);
//        }
//        //交收失败
//        ProcessParameter<CfetsManualSettleRequest> processParameter = new ProcessParameter<CfetsManualSettleRequest>();
//        WorkFlowParameter<CfetsManualSettleRequest> params = new WorkFlowParameter<CfetsManualSettleRequest>();
//        params.setPageId("CfetsManualSettle");
//        params.setElementId(cfetsRequest.getElementId());
//        params.setBusinessCode(IDGenerator.generate());
//        params.setBusinessType(DictCodeListExt3.共通业务类型_手工交收.getValue());
//        processParameter.setWorkFlowParameter(params);
//        try{
//            String msg = cfetsManualSettleService.start(processParameter,cfetsRequest);
//            return new Message<AjaxResult>().success().setMessage("处理完成");
//        }catch (Exception e) {
//            logger.error("交收失败",e);
//            return new Message<AjaxResult>().fail().setMessage("交收失败");
//            //return PositionUtil.ajaxErrorReturn(e);
//        }
//    }

}
