package com.joyintech.tams.cfets.cfetsmanualsettle.controller;


import com.joyintech.background.component.common.pack.common.util.StringUtil;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.tams.business.common.dto.CfetsExportRequestDto;
import com.joyintech.tams.business.common.pojo.Message;
import com.joyintech.tams.business.common.util.ExcelUitl;
import com.joyintech.tams.cfets.cfetsmanualsettle.dto.*;
import com.joyintech.tams.cfets.cfetsmanualsettle.service.CfetsManualSettleService;
import com.joyintech.tams.cfets.cfetsmanualsettle.service.CfetsManualSettleUpdateService;
import com.joyintech.tams.constant.DictCode;
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

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@PageId("CfetsManualSettleUpdate")
@RequestMapping("/cfets/manualsettle/ManualSettleUpdate")
@Api(tags = "银行间手工交收修改")
public class CfetsManualSettleUpdateController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(CfetsManualSettleUpdateController.class);

    @Autowired
    private CfetsManualSettleUpdateService cfetsManualSettleUpdateService;

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

        initPageParams.put("pageId", "CfetsManualSettleUpdate");
        return new Message<Map<String, Object>>(initPageParams).success();
    }

    /**
     * 手动交收修改
     * 前台传入整条数据
     * @param cfetsRequest br
     * @return Message<PortSettleinfoDto>
     * @author sjj
     */
    @ResponseBody
    @ApiOperation(value = "交收修改", notes = "交收修改")
    @PostMapping("doManualSettleUpdate")
    public Message<PortSettleinfoDto> doManualSettleUpdate(@RequestBody CfetsManualSettleUpdateRequest cfetsRequest, BindingResult br)
            throws Exception {
        //画面Check
        String returnMsg= cfetsManualSettleUpdateService.checkManualSettleUpdate(cfetsRequest, br);
        if (StringUtil.isNotBlank(returnMsg)) {
            return new Message<PortSettleinfoDto>().fail().setMessage(returnMsg);
        }
        //查询待修改记录
        PortSettleinfoDto portSettleinfoDto = cfetsManualSettleUpdateService.queryPortSettleinfo(cfetsRequest);

        return new Message<PortSettleinfoDto>().fail().setData(portSettleinfoDto);
    }

    /**
     * 手动交收修改提交
     * 前台传入整条数据
     * @param cfetsRequest br
     * @return Message<AjaxResult>
     * @author sjj
     */
    @ResponseBody
    @ApiOperation(value = "交收修改提交", notes = "交收修改提交")
    @PostMapping("doManualSettleUpdateSave")
    public Message<AjaxResult> doManualSettleUpdateSave(@RequestBody CfetsManualSettleUpdateRequest cfetsRequest, BindingResult br)
            throws Exception {
        //保存
        ProcessParameter<CfetsManualSettleUpdateRequest> processParameter = new ProcessParameter<CfetsManualSettleUpdateRequest>();
        WorkFlowParameter<CfetsManualSettleUpdateRequest> params = new WorkFlowParameter<CfetsManualSettleUpdateRequest>();
        params.setPageId("CfetsManualSettleUpdate");
        params.setElementId(cfetsRequest.getElementId());
        params.setBusinessCode(cfetsRequest.getSelectedItem().getTradeId());
        params.setBusinessType(DictCodeListExt3.共通业务类型_手工交收.getValue());
        processParameter.setWorkFlowParameter(params);
        try{
            String msg = cfetsManualSettleUpdateService.start(processParameter,cfetsRequest);
            return new Message<AjaxResult>().success().setMessage("处理完成");
        }catch (Exception e) {
            logger.error("交收修改失败",e);
            return new Message<AjaxResult>().fail().setMessage("交收修改失败");
        }
    }
}
