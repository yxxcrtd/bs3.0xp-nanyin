package com.joyintech.tams.cfets.cfetsmanualsettle.controller;


import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.tams.business.common.pojo.Message;
import com.joyintech.tams.cfets.cfetsmanualsettle.dto.*;
import com.joyintech.tams.cfets.cfetsmanualsettle.service.CfetsManualSettleService;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 手动交收check
     * 前台传入整条数据
     * @param cfetsRequest br
     * @return Message<AjaxResult>
     * @author sjj
     */
    @ResponseBody
    @ApiOperation(value = "交收", notes = "交收")
    @PostMapping("doManualSettleCheck")
    public Message<AjaxResult> doManualSettleCheck(@RequestBody CfetsManualSettleRequest cfetsRequest, BindingResult br)
            throws Exception {
        //画面Check
//        if (StringUtil.equals(cfetsRequest.getButtonType(), DictCodeListExt3.交收操作类型_交收.getValue())) {
        AjaxResult ajaxResult = cfetsManualSettleService.checkManualSettleSave(cfetsRequest, br);
        return new Message<AjaxResult>().success().setData(ajaxResult);
//        }else{
//            String returnMsg = cfetsManualSettleService.checkManualSettleSave(cfetsRequest, br);
//            if (StringUtil.isNotBlank(returnMsg)) {
//                return new Message<AjaxResult>().fail().setMessage(returnMsg);
//            }else{
//                return new Message<AjaxResult>().success().setMessage(returnMsg);
//            }
//        }
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
        //保存
        ProcessParameter<CfetsManualSettleRequest> processParameter = new ProcessParameter<CfetsManualSettleRequest>();
        WorkFlowParameter<CfetsManualSettleRequest> params = new WorkFlowParameter<CfetsManualSettleRequest>();
        params.setPageId("CfetsManualSettle");
        params.setElementId(cfetsRequest.getElementId());
        params.setBusinessCode(IDGenerator.generate());
        params.setBusinessType(DictCodeListExt3.共通业务类型_手工交收.getValue());
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
