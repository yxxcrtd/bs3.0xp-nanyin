/**
 * 场内指令分发
 * @author msk
 * @version 1.0 2021年12月22日
 * @see FieldEtrDispatcherController
 * @since 1.0
 */

package com.joyintech.tams.exchange.entrust.fieldetrinfoquerymain.fieldetrdispatcher.controller;


import com.joyintech.background.component.common.pack.common.util.StringUtil;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.business.common.dto.FieldExportRequestDto;
import com.joyintech.tams.business.common.util.ExcelUitl;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.exchange.entrust.fieldetrinfoquerymain.fieldetrdispatcher.dto.*;
import com.joyintech.tams.exchange.entrust.fieldetrinfoquerymain.fieldetrdispatcher.service.FieldEtrDispatcherService;
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
    public Message<Page<FieldEtrDispatcherExeListInfoResponse>> getEtrDispatcherExeListInfo(@RequestBody FieldEtrDispatcherInfoRequest dto){
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
    public Message<Page<FieldEtrDispatcherDealListInfoResponse>> getEtrDispatcherDealListInfo(@RequestBody FieldEtrDispatcherInfoRequest dto){
        try {
            Page<FieldEtrDispatcherDealListInfoResponse> returnDto = fieldEtrDispatcherService.getEtrDispatcherDealListInfo(dto);
            return new Message<Page<FieldEtrDispatcherDealListInfoResponse>>().success().setData(returnDto);
        } catch (Exception e) {
            logger.error("查询指令成交异常", e);
            return new Message<Page<FieldEtrDispatcherDealListInfoResponse>>().fail().setMessage("查询指令成交异常");
        }
    }

    /**
     * 指令风险试算查询
     *
     * @param dto
     * @return Page<FieldEtrDispatcherRskInfoResponse>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ApiOperation(value = "风险试算查询", notes = "风险试算查询")
    @PostMapping("/getEtrDispatcherRskInfo")
    @ResponseBody
    public Message<Page<FieldEtrDispatcherRskInfoResponse>> getEtrDispatcherRskInfo(@RequestBody FieldEtrDispatcherInfoRequest dto){
        try {
            Page<FieldEtrDispatcherRskInfoResponse> page = fieldEtrDispatcherService.getEtrDispatcherRskInfo(dto);
            return new Message<Page<FieldEtrDispatcherRskInfoResponse>>().success().setData(page);
        } catch (Exception e) {
            logger.error("查询风险试算分页数据异常", e);
            return new Message<Page<FieldEtrDispatcherRskInfoResponse>>().fail().setMessage("查询风险试算异常");
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

    /**
     * 自动分发
     *
     * @param fieldDto br
     * @return Message<AjaxResult>
     * @author sjj
     */
    @ResponseBody
    @ApiOperation(value = "自动分发提交", notes = "自动分发提交")
    @PostMapping("submit")
    public Message<AjaxResult> submit(@RequestBody FieldEtrDispatcherAutoRequest fieldDto, BindingResult br){
        //提交check
        String returnMsg= fieldEtrDispatcherService.checkSave(fieldDto, br);
        if (StringUtil.isNotBlank(returnMsg)) {
            return new Message<AjaxResult>().fail().setMessage(returnMsg);
        }

        //设置提交
        fieldEtrDispatcherService.deleteEtrAutoTime();
        if (fieldDto.getVdate().equals("1900-01-01") && fieldDto.getMdate().equals("2099-12-31")) {

        } else {
            int count = fieldEtrDispatcherService.insertEtrAutoTime(fieldDto);
            if (count == 0) {
                return new Message<AjaxResult>().fail().setMessage("自动分发设置失败，请联系管理员！");
            }
        }
        return new Message<AjaxResult>().success().setMessage("自动分发设置完成");
    }

    /**
     * 自动分发查询
     *
     * @return Message<FieldEtrDispatcherAutoRequest>
     * @author sjj
     */
    @ResponseBody
    @ApiOperation(value = "自动分发查询", notes = "自动分发查询")
    @PostMapping("findAutoDetail")
    public Message<FieldEtrDispatcherAutoRequest> findAutoDetail(){
        try {
            FieldEtrDispatcherAutoRequest fieldEtrDispatcherAutoRequest = fieldEtrDispatcherService.findAutoDetail();
            return new Message<FieldEtrDispatcherAutoRequest>().success().setData(fieldEtrDispatcherAutoRequest);
        } catch (Exception e) {
            logger.error("自动分发查询异常", e);
            return new Message<FieldEtrDispatcherAutoRequest>().fail().setMessage("自动分发查询异常");
        }

    }

    /**
     * 点击分发按钮check
     *
     * @param fieldDto br
     * @return Message<AjaxResult>
     * @author sjj
     */
    @ResponseBody
    @ApiOperation(value = "分发check", notes = "分发check")
    @PostMapping("doDispatcherCheck")
    public Message<AjaxResult> doDispatcherCheck(@RequestBody FieldEtrDispatcherInfoRequest fieldDto, BindingResult br)
            throws Exception {
        //提交check
        Map<String, Object> pageParam = BeanUtil.object2Map(fieldDto);
        AjaxResult ajaxResult = fieldEtrDispatcherService.doDispatcherCheck(pageParam, br);
        if (!StringUtil.isEmptyList(ajaxResult.getErrorList())) {
            return new Message<AjaxResult>().fail().setMessage(ajaxResult.getMessage());
        } else {
            //选择交易员
            List<SelectOptionDto> userList = fieldEtrDispatcherService.getUsers(pageParam);
            ajaxResult.setData(userList);
            return new Message<AjaxResult>().success().setData(ajaxResult);
        }
    }

    /**
     * 点击分发确认
     *
     * @param fieldDto br
     * @return Message<AjaxResult>
     * @author sjj
     */
    @ResponseBody
    @ApiOperation(value = "分发", notes = "分发")
    @PostMapping("doDispatcher")
    public Message<AjaxResult> doDispatcher(@RequestBody FieldEtrDispatcherDealRequest fieldDto, BindingResult br){
        //提交check
        try {
            Map<String, Object> pageParam = BeanUtil.object2Map(fieldDto);
            AjaxResult ajaxResult = fieldEtrDispatcherService.doDispatcherDealCheck(pageParam, br);
            if (!StringUtil.isEmptyList(ajaxResult.getErrorList())) {
                return new Message<AjaxResult>().fail().setMessage(ajaxResult.getMessage());
            } else {
                //选择交易员
                fieldEtrDispatcherService.doDispatcherDeal(pageParam);
                return new Message<AjaxResult>().success().setMessage("分发成功");
            }
        } catch (Exception e) {
            logger.error("分发异常", e);
            return new Message<AjaxResult>().fail().setMessage("分发异常");
        }
    }

    /**
     * 点击退回
     *
     * @param fieldDto br
     * @return Message<AjaxResult>
     * @author sjj
     */
    @ResponseBody
    @ApiOperation(value = "退回", notes = "退回")
    @PostMapping("doRefuseEntrust")
    public Message<AjaxResult> doRefuseEntrust(@RequestBody FieldEtrDispatcherDealRequest fieldDto, BindingResult br){
        //退回check
        try {
            Map<String, Object> pageParam = BeanUtil.object2Map(fieldDto);
            AjaxResult ajaxResult = fieldEtrDispatcherService.doRefuseEntrustCheck(pageParam, br);
            if (!StringUtil.isEmptyList(ajaxResult.getErrorList())) {
                return new Message<AjaxResult>().fail().setMessage(ajaxResult.getMessage());
            }
            else{
                //退回指令
                ajaxResult = fieldEtrDispatcherService.refuseEntrust(pageParam);

                return new Message<AjaxResult>().success().setMessage("退回成功");
            }
        } catch (Exception e) {
            logger.error("退回异常", e);
            return new Message<AjaxResult>().fail().setMessage("退回异常");
        }
    }

    /**
     * 分发权限配置交易员查询
     *
     * @return Page<FieldEtrDispatcherTraderAuthListResponse>
     * @see
     * @since 1.0
     */
    @ApiOperation(value = "分发权限配置交易员查询", notes = "分发权限配置交易员查询")
    @PostMapping("/getTraderAuthList")
    @ResponseBody
    public Message<List<FieldEtrDispatcherTraderAuthListResponse>> getTraderAuthList(){
        try {
            List<FieldEtrDispatcherTraderAuthListResponse> list = fieldEtrDispatcherService.getTraderAuthList();
            return new Message<List<FieldEtrDispatcherTraderAuthListResponse>>().success().setData(list);
        }catch (Exception e) {
            logger.error("查询分发权限配置交易员数据异常",e);
            return new Message<List<FieldEtrDispatcherTraderAuthListResponse>>().fail().setMessage("查询指令列表分页数据异常");
        }
    }

    /**
     * 分发权限配置保存
     *
     * @return AjaxResult
     * @param  dto
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ApiOperation(value = "分发权限配置保存", notes = "分发权限配置保存")
    @PostMapping("/saveTraderAuthList")
    @ResponseBody
    public Message<AjaxResult> saveTraderAuthList(@RequestBody FieldEtrDispatcherTraderAuthListRequest dto)
            throws Exception {

        AjaxResult ajaxResult = new AjaxResult();
        List<FieldEtrDispatcherTraderAuthListResponse> dtoList = dto.getDtoList();
        //提交check，软控
        if(StringUtil.equals(dto.getIsConfirmFlag(), DictCodeList.是否_否.getValue())){
            ajaxResult = fieldEtrDispatcherService.checkSaveTraderAuthList(dtoList);
            if(StringUtil.isNotBlank(ajaxResult.getMessage())){
                return new Message<AjaxResult>().success().setData(ajaxResult);
            }
        }

        //保存
        ajaxResult = fieldEtrDispatcherService.doSaveTraderAuthList(dtoList);
        if (!StringUtil.isEmptyList(ajaxResult.getErrorList())) {
            return new Message<AjaxResult>().fail().setMessage("分发权限设置失败");
        }else{
            return new Message<AjaxResult>().success().setMessage("分发权限配置成功");
        }
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
                                                       @RequestBody FieldEtrDispatcherInfoRequest dto) {
        try{
            Map<String,Object> resultData = fieldEtrDispatcherService.getReadonlyFormEtrInfo(pageCode,dtCode,spcdCode,dto);
            return new Message<Map<String,Object>>().success().setData(resultData);
        } catch (Exception e) {
            logger.error("查询指令信息明细分页数据异常", e);
            return new Message<Map<String,Object>>().fail().setMessage("查询指令信息明细异常");
        }
    }
}
