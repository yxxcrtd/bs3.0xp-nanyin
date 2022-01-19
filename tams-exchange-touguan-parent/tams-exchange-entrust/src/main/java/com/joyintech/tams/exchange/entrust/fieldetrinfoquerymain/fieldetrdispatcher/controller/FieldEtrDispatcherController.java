/**
 * 场内指令分发
 * @author msk
 * @version 1.0 2021年12月22日
 * @see FieldEtrDispatcherController
 * @since 1.0
 */

package com.joyintech.tams.exchange.entrust.fieldetrinfoquerymain.fieldetrdispatcher.controller;


import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.business.common.dto.CommonSearchInfoRequest;
import com.joyintech.tams.business.common.dto.FieldExportRequestDto;
import com.joyintech.tams.business.common.util.ExcelUitl;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.exchange.entrust.fieldetrinfoquerymain.fieldetrdispatcher.dto.*;
import com.joyintech.tams.exchange.entrust.fieldetrinfoquerymain.fieldetrdispatcher.service.FieldEtrDispatcherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.joyintech.tams.business.common.pojo.Message;

import javax.servlet.http.HttpServletResponse;
import java.util.*;


@Controller
@PageId("FieldEtrDispatcher")
@RequestMapping("/exchange/entrust/fieldetrinfoquerymain/fieldetrdispatcher")
@Api(tags = "场内指令分发")
public class FieldEtrDispatcherController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(FieldEtrDispatcherController.class);

    @Autowired
    private FieldEtrDispatcherService fieldEtrDispatcherService;

    @ApiOperation(value = "查询页面初始化参数", notes = "查询页面初始化参数")
    @PostMapping("/getPageParams")
    @ResponseBody
    public Message<Map<String, Object>> getPageParams(){
        Map<String, Object> initPageParams = new HashMap<>();
        //数据字典初始化数据
        initPageParams.put("PINAN_ASSET_TYPE", this.getSelectDataFromDataDic(DictCode.平安资产类型, null));
        initPageParams.put("BUSI_TYPE_POSITION", this.getSelectDataFromDataDic(DictCode.头寸业务类型, null));
        initPageParams.put("TRADE_MARKET", this.getSelectDataFromDataDic(DictCode.交易场所, null));
        initPageParams.put("CCY", this.getSelectDataFromDataDic(DictCode.币种, null));
        initPageParams.put("ACCOUNT_SPEED", this.getSelectDataFromDataDic(DictCode.清算速度, null));
//        initPageParams.put("PS", this.getSelectDataFromDataDic(DictCode.交易方向, null));
        List<SelectOptionDto> psList = makeAllFloatRate();
        initPageParams.put("PS", JsonUtils.objectArray2Json(psList.toArray()));
        initPageParams.put("SHORTSELLINGSUPPORTED01", CacheUtil.getSysParam("SHORT_SELLING_SUPPORTED"));
        //自动刷新时间(ms)
        initPageParams.put("cfetsEtrDispatcher_tradeList_refresh_period", CacheUtil.getSysParam("cfetsEtrDispatcher_tradeList_refresh_period"));
        initPageParams.put("cfetsEtrDispatcher_dealersTable_refresh_period", CacheUtil.getSysParam("cfetsEtrDispatcher_dealersTable_refresh_period"));
        initPageParams.put("pageId", "FieldEtrDispatcher");
        return new Message<Map<String, Object>>(initPageParams).success();
    }

    /**
     *
     * 组交易方向下拉框值
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    private List<SelectOptionDto> makeAllFloatRate() {
        List<SelectOptionDto> listFloatRate = new ArrayList<SelectOptionDto>();

        SelectDto selectDto1 = this.getSelectDataFromDataDic(DictCode.交易方向, null);
        for (SelectOptionDto dto : selectDto1.getOptions()) {
            listFloatRate.add(dto);
        }
        SelectDto selectDto2 = this.getSelectDataFromDataDic(DictCode.回购拆借方向, null);
        for (SelectOptionDto dto : selectDto2.getOptions()) {
            listFloatRate.add(dto);
        }
        SelectDto selectDto3 = this.getSelectDataFromDataDic(DictCode.互换方式, null);
        for (SelectOptionDto dto : selectDto3.getOptions()) {
            listFloatRate.add(dto);
        }
        return listFloatRate;
    }

    /**
     * 指令列表分页查询
     *
     * @param dto
     * @return Page<FieldEtrDispatcherTradeListResponse>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ApiOperation(value = "指令列表分页查询", notes = "指令列表分页查询")
    @PostMapping("/findAllEtrDispatcherCommon")
    @ResponseBody
    public Message<Page<FieldEtrDispatcherTradeListResponse>> findAllEtrDispatcherCommon(@RequestBody FieldEtrDispatcherTradeListRequest dto)
            throws Exception {
        try {
            Page<FieldEtrDispatcherTradeListResponse> page = fieldEtrDispatcherService.findAllEtrDispatcherCommon(dto);
            return new Message<Page<FieldEtrDispatcherTradeListResponse>>().success().setData(page);
        } catch (Exception e) {
            logger.error("查询指令管理分页数据异常", e);
            return new Message<Page<FieldEtrDispatcherTradeListResponse>>().fail().setMessage("查询指令列表分页数据异常");
        }
    }

    /**
     * 指令执行查询
     *
     * @param dto
     * @return Page<FieldEtrDispatcherExeListInfoResponse>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ApiOperation(value = "指令执行查询", notes = "指令执行查询")
    @PostMapping("/getEtrDispatcherExeListInfo")
    @ResponseBody
    public Message<Page<FieldEtrDispatcherExeListInfoResponse>> getEtrDispatcherExeListInfo(@RequestBody CommonSearchInfoRequest dto){
        try {
            Page<FieldEtrDispatcherExeListInfoResponse> returnDto = fieldEtrDispatcherService.getEtrDispatcherExeListInfo(dto);
            return new Message<Page<FieldEtrDispatcherExeListInfoResponse>>().success().setData(returnDto);
        } catch (Exception e) {
            logger.error("查询指令管理分页数据异常", e);
            return new Message<Page<FieldEtrDispatcherExeListInfoResponse>>().fail().setMessage("查询指令成交异常");
        }
    }

    /**
     * 指令成交查询
     *
     * @param dto
     * @return Page<FieldEtrDispatcherDealListInfoResponse>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ApiOperation(value = "指令成交查询", notes = "指令成交查询")
    @PostMapping("/getEtrDispatcherDealListInfo")
    @ResponseBody
    public Message<Page<FieldEtrDispatcherDealListInfoResponse>> getEtrDispatcherDealListInfo(@RequestBody CommonSearchInfoRequest dto){
        try {
            Page<FieldEtrDispatcherDealListInfoResponse> returnDto = fieldEtrDispatcherService.getEtrDispatcherDealListInfo(dto);
            return new Message<Page<FieldEtrDispatcherDealListInfoResponse>>().success().setData(returnDto);
        } catch (Exception e) {
            logger.error("查询指令成交异常", e);
            return new Message<Page<FieldEtrDispatcherDealListInfoResponse>>().fail().setMessage("查询指令成交异常");
        }
    }

    @PostMapping(value = "/exportEtrDispatcherList")
    @ResponseBody
    @ApiOperation(value = "指令分发列表导出", notes = "指令分发列表导出")
    public void exportEtrDispatcherList(@RequestBody FieldExportRequestDto req, HttpServletResponse response) {
        // 导出
        ExcelUitl.doExport(req, response, "指令分发", () -> {
            List dataList = fieldEtrDispatcherService.findAllEtrDispatcherCommon(req.getDto(FieldEtrDispatcherTradeListRequest.class)).getRoot();
            return dataList;
        });
    }

    @GetMapping(value="/getEtrTraderList")
    @ResponseBody
    @ApiOperation(value = "交易员列表模糊查询", notes = "交易员列表模糊查询")
    public Message<List<FieldEtrDispatcherTraderListRequest>> getEtrTraderList() {
        Map<String,Object> param = RequestUtil.getParameters(getRequest());
        List<FieldEtrDispatcherTraderListRequest> list = fieldEtrDispatcherService.getEtrTraderList(param);
        return new Message<List<FieldEtrDispatcherTraderListRequest>>().success().setData(list);
    }

    @GetMapping(value = "/getEtrTraderAndWorkloadList")
    @ResponseBody
    @ApiOperation(value = "交易员与工作量列表查询", notes = "交易员与工作量列表查询")
    public Message<List<FieldEtrDispatcherTraderListRequest>> getEtrTraderAndWorkloadList() {
        Map<String,Object> param = RequestUtil.getParameters(getRequest());
        List<FieldEtrDispatcherTraderListRequest> list = fieldEtrDispatcherService.getEtrTraderAndWorkloadList(param);
        return new Message<List<FieldEtrDispatcherTraderListRequest>>().success().setData(list);
    }

    @ResponseBody
    @PostMapping("/getReadonlyFormEtrInfo/{pageCode}/{dtCode}/{spcdCode}")
    @ApiOperation(value = "只读表单设置及数据查询_指令信息", notes = "只读表单设置及数据查询_指令信息")
    public Message<Map<String,Object>> getReadonlyFormEtrInfo(@PathVariable("pageCode") String pageCode,
                                                       @PathVariable("dtCode") String dtCode,
                                                       @PathVariable("spcdCode") String spcdCode,
                                                       @RequestBody CommonSearchInfoRequest dto) {
        try{
            Map<String,Object> resultData = fieldEtrDispatcherService.getReadonlyFormEtrInfo(pageCode,dtCode,spcdCode,dto);
            return new Message<Map<String,Object>>().success().setData(resultData);
        } catch (Exception e) {
            logger.error("查询指令信息明细分页数据异常", e);
            return new Message<Map<String,Object>>().fail().setMessage("查询指令信息明细异常");
        }
    }

    @ResponseBody
    @PostMapping("/getEtrDispatcherInfo")
    @ApiOperation(value = "指令详情数据查询", notes = "指令详情数据查询")
    public Message<FieldEtrDispatcherInfoResponse> getEtrDispatcherInfo(CommonSearchInfoRequest dto) {
        try{
            FieldEtrDispatcherInfoResponse resultData =fieldEtrDispatcherService.getEtrDispatcherInfo(dto);
            return new Message<FieldEtrDispatcherInfoResponse>().success().setData(resultData);
        } catch (Exception e) {
            logger.error("指令详情数据查询数据异常", e);
            return new Message<FieldEtrDispatcherInfoResponse>().fail().setMessage("查询指令信息明细异常");
        }
    }
}
