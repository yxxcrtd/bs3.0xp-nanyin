package com.joyintech.tams.exchange.service.trade.controller;

import com.github.pagehelper.PageInfo;
import com.joyintech.tams.business.common.util.ExcelUitl;
import com.joyintech.tams.exchange.common.ResponseResultBody;
import com.joyintech.tams.exchange.common.bean.*;
import com.joyintech.tams.exchange.common.constant.EntrustBsProp;
import com.joyintech.tams.exchange.common.db.domain.EntrustInterface;
import com.joyintech.tams.exchange.common.db.domain.Instruction;
import com.joyintech.tams.exchange.common.db.domain.IntegratedEntrust;
import com.joyintech.tams.exchange.common.domain.Message;
import com.joyintech.tams.exchange.common.request.EntrustExportRequest;
import com.joyintech.tams.exchange.common.request.EntrustQueryRequest;
import com.joyintech.tams.exchange.common.request.PageEntrustInfoRequest;
import com.joyintech.tams.exchange.datacommon.db.service.InstructionService;
import com.joyintech.tams.exchange.service.trade.management.service.EntrustManagement;
import com.joyintech.tams.exchange.util.EntrustPropUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/trade/joyin")
@ResponseResultBody
@Api(tags = "场内交易-委托操作接口")
public class EntrustController {

    @Autowired
    private EntrustManagement entrustManagement;
    @Autowired
    private InstructionService instructionService;

    @ApiOperation(value = "查询委托信息", notes = "查询委托信息")
    @RequestMapping(value = {"/entrust"}, method = RequestMethod.POST)
    public Message<OrderAndEntrustQryResult> queryAll(@RequestBody @Validated EntrustQueryRequest entrustQueryRequest) {
        return new Message<OrderAndEntrustQryResult>(entrustManagement.queryEntrust(entrustQueryRequest.getUserId(), entrustQueryRequest.getInstructionId(), entrustQueryRequest.getAccountId(), entrustQueryRequest.getOrderId()));
    }

    @ApiOperation(value = "查询综合业务信息", notes = "查询综合业务信息")
    @RequestMapping(value = {"/entrust/integrated"}, method = RequestMethod.POST)
    public Message<List<IntegratedEntrust>> queryIntegratedEntrustAll(@RequestBody @Validated EntrustQueryRequest request) {
        return new Message<List<IntegratedEntrust>>(entrustManagement.queryIntegratedEntrust(request.getUserId(), request.getInstructionId(), request.getAccountId(), request.getOrderId()));
    }

    /**
     * 撤销user下对应entrust
     *
     * @param userId    用户Id
     * @param entrustId 委托单号
     * @return Message
     */
    @ApiOperation(value = "撤销委托", notes = "撤销委托")
    @RequestMapping(value = "/entrust/cancel", method = RequestMethod.POST)
    public Message<Object> cancelEntrust(@RequestParam String userId,
                                         @RequestParam String entrustId) {
        return new Message<Object>(entrustManagement.cancelEntrust(userId, entrustId));
    }

    /**
     * 强制撤销对应entrust
     *
     * @param userId    用户Id
     * @param userName  用户名称
     * @param entrustId 委托单号
     * @return Message
     */
    @ApiOperation(value = "撤销异常委托", notes = "撤销异常委托")
    @RequestMapping(value = "/entrust/exception/cancel", method = RequestMethod.POST)
    public Message<Object> cancelExceptionEntrust(@RequestParam String userId,
                                                  @RequestParam String userName,
                                                  @RequestParam String entrustId) {
        return new Message<Object>(entrustManagement.cancelExceptionEntrust(userId, userName, entrustId));
    }

    /**
     * 撤销user下对应entrust  //撤销审批，对应revokecontroller,指令中有是否需要复核参数，决定是否走这个revoke
     *
     * @param userId 用户Id
     * @param dto    委托单号
     * @return ResultBean
     */
    @RequestMapping(value = "/entrust/cancel/revoke", method = RequestMethod.POST)
    public ResultBean cancelEntrustWithRevoke(String userId, @RequestBody RevokeDto dto) {
        return entrustManagement.cancelEntrustWithRevoke(userId, dto);
    }

//    /**
//     * 最大交易数量查询
//     *
//     * @param userId        用户Id
//     * @param instructionId 指令编号
//     * @param stockCode     市场.证券代码
//     * @param entrustProp   交易类型
//     * @param entrustBs     交易方向
//     * @param entrustPrice  委托价格
//     * @param accountId     账户Id
//     * @return ResultBean
//     */
//    @RequestMapping(value = "/entrust/enable", method = RequestMethod.GET)
//    public ResultBean queryEnable(@RequestParam(value = "user_id") String userId,
//                                  @RequestParam(value = "stock_code") String stockCode,
//                                  @RequestParam(value = "entrust_prop") String entrustProp,
//                                  @RequestParam(value = "entrust_bs") String entrustBs,
//                                  @RequestParam(value = "entrust_price") double entrustPrice,
//                                  @RequestParam(value = "account_id") String accountId) {
//        return entrustManagement.queryEnable(userId, stockCode, entrustProp, entrustBs, entrustPrice, accountId);
//    }

    /**
     * 最大交易数量查询
     *
     * @param enableQryBean 查询参数
     * @return ResultBean
     */
    @RequestMapping(value = "/entrust/enable", method = RequestMethod.POST)
    public ResultBean queryEnable(@RequestBody @Validated EnableQryBean enableQryBean) {
        Instruction instruction = instructionService.findById(enableQryBean.getInstructionId());
        enableQryBean.setEntrustProp(EntrustPropUtils.getEntrustProp(instruction, enableQryBean.getEntrustProp()));
        enableQryBean.setEntrustBs(EntrustBsProp.getEntrustBs(instruction.getPs()));
        if (StringUtils.isNotEmpty(enableQryBean.getStockCode())) {
            enableQryBean.setStockCode(instruction.getFinprodMarketId());
        }
        if (StringUtils.isNotEmpty(enableQryBean.getAccountId())) {
            enableQryBean.setAccountId(instruction.getAcctNo());
        }
        return entrustManagement.queryEnable(enableQryBean);
    }

    /*** 
     * @Description: 申报代码获取
     * @Param: [finprodId, othCodeType]
     * @return: com.joyintech.tams.exchange.common.bean.ResultBean
     * @Author: MAXX
     * @Date: 2020/6/5 
     */
    @RequestMapping(value = "/entrust/getOthCode", method = RequestMethod.POST)
    public ResultBean getOthCode(@RequestParam(value = "finprod_id", required = false) String finprodId,
                                 @RequestParam(value = "oth_code_type", required = false) String othCodeType) {

        return entrustManagement.getOthCode(finprodId, othCodeType);
    }

    @ApiOperation(value = "查询分页委托信息", notes = "查询分页委托信息")
    @PostMapping(value = "/entrust/page")
    public Message<PageInfo<EntrustQueryBean>> queryAllEntrustForPage(@RequestBody @Validated PageEntrustInfoRequest request) {
        return entrustManagement.queryAllEntrustForPage(request);
    }

    @ApiOperation(value = "查询分页综合业务委托信息", notes = "查询分页综合业务委托信息")
    @PostMapping(value = "/entrust/integrated/page")
    public Message<PageInfo<EntrustQueryBean>> queryIntegratedEntrustForPage(@RequestBody @Validated PageEntrustInfoRequest request) {
        return entrustManagement.queryIntegratedEntrustForPage(request);
    }

    @PostMapping(value = "/entrust/export")
    @ResponseBody
    @ApiOperation(value = "导出委托信息列表", notes = "导出委托信息列表")
    public void exportPosition(@RequestBody EntrustExportRequest request, HttpServletResponse response) {
        ExcelUitl.doExport(request, response, "委托列表", () -> entrustManagement.queryAllEntrustForPage(request.getDto(PageEntrustInfoRequest.class)).getData().getList());
    }

    @ApiOperation(value = "查询异常委托", notes = "查询异常委托")
    @RequestMapping(value = "/entrust/exception", method = RequestMethod.POST)
    public Message<List<EntrustInterface>> queryExceptionEntrust(@RequestParam String userId,
                                                                 @RequestParam(required = false) String entrustStatus) {
        if (StringUtils.isEmpty(entrustStatus)) {
            entrustStatus = "1,3,4";
        }
        return new Message<List<EntrustInterface>>(entrustManagement.queryEntrustByStatus(userId, entrustStatus));
    }
}
