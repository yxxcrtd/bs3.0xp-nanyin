/*
 * 文件名：EtrTradePlace001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chendijun
 * 修改时间：2020年7月1日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.exchange.entrust.etrexchange.hqjj.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.business.common.util.ExcelUitl;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.exchange.entrust.etrexchange.hqjj.dto.*;
import com.joyintech.tams.exchange.entrust.etrexchange.hqjj.service.EtrExchangeTradeJj001Service;
import com.joyintech.tams.exchange.entrust.etrexchange.hqjj.service.EtrExchangeTradeJj002Service;
import com.joyintech.tams.exchange.entrust.etrexchange.hqjj.service.EtrExchangeTradeJj003Service;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.entrust.etrexchangetrade.dto.EtrExchangeTrade002Dto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.joyintech.tams.business.common.pojo.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 交易所指令交易（债券股票回购基金场内指令交易）
 * @author meifaxin
 * @version 1.0 2021年12月20日
 * @see EtrExchangeTradeJj002Controller
 * @since 1.0
 */
@Controller
@PageId("JjEtrExchangeTrade002")
@RequestMapping("/etrexchange/hqjj/jjetrExchangeTrade002")
@Api(tags = "交易所持仓")
public class EtrExchangeTradeJj002Controller extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(EtrExchangeTradeJj002Controller.class);

    /**
     * 可转债委托处理详细
     */
    @Autowired
    private EtrExchangeTradeJj002Service jjEtrExchangeTrade002Service;

    @Autowired
    private EtrExchangeTradeJj001Service jjEtrExchangeTrade001Service;

    @Autowired
    private EtrExchangeTradeJj003Service jjEtrExchangeTrade003Service;


    @ApiOperation(value="查询页面初始化参数", notes = "查询页面初始化参数")
    @PostMapping("/init")
    @ResponseBody
    public Message<Map<String,Object>> init() {
        Map<String, Object> initPageParams = new HashMap<>();
        initPageParams.put("TRADE_PLACE", this.getSelectDataFromDataDic(BussinessDictCode.交易场所, null));
        initPageParams.put("FINPROD_TYPE_TG", this.getSelectDataFromDataDic(DictCode.金融产品类型_投管用, null));
        initPageParams.put("PS", this.getSelectDataFromDataDic(DictCode.交易方向, null));

        EtrExchangeTrade002Dto exchangeTradeDto002 = new EtrExchangeTrade002Dto();
        initPageParams.put("entity",exchangeTradeDto002);
        initPageParams.put("FINPROD_TYPE_TG_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        initPageParams.put("DECLA_TYPE_PRM05", DictCodeListExt3.申报类型_不指定.getValue());
        initPageParams.put("DECLA_TYPE_PRM14", DictCodeListExt3.申报类型_指定对手方成交申报.getValue());
        initPageParams.put("ENTRUST_BUSS_TYPE_15", DictCodeListExt3.上农商_指令业务类型_债券质押式回购竞价指令.getValue());
        initPageParams.put("PS_T02", DictCodeList.交易方向_卖出.getValue());
        initPageParams.put("PS_T69", DictCodeListExt3.交易方向_质押券出库.getValue());
        initPageParams.put("TARGET_CLASS_01", DictCodeListExt3.目标类型_数量.getValue());
        initPageParams.put("PRICE_MODEL_PRM01", DictCodeListExt2.价格模式_指定价格.getValue());
        
        // 场内交易指令画面持仓列表自动刷新时间（毫秒）
        long etrExchange_posTable_refresh_period = 20000;
        String tempSysParaVal = CacheUtil.getSysParam(
            BussinessConstant.ETREXCHANGE_POSTABLE_REFRESH_PERIOD);
        if (StringUtil.isNotBlank(tempSysParaVal)
            && !StringUtil.equals(BussinessConstant.ETREXCHANGE_POSTABLE_REFRESH_PERIOD,
                tempSysParaVal)) {
            try {
                etrExchange_posTable_refresh_period = Long.parseLong(tempSysParaVal);
            }
            catch (Exception e) {
            }
        }
        initPageParams.put("etrExchange_posTable_refresh_period", etrExchange_posTable_refresh_period);
        /*add by chenjiaxing 更改交易方向枚举值 start*/
        SelectDto selectDto1 = this.getSelectDataFromDataDic(DictCode.交易方向, null);
        for(int i = 0 ; i < selectDto1.getOptions().size() ; i++){
            if(StringUtil.equals(selectDto1.getOptions().get(i).getId(),"T03")){
                selectDto1.getOptions().get(i).setText("债回售");
            }
        }
        List<SelectOptionDto> optionsGradeResult = selectDto1.getOptions();
        initPageParams.put("PS_ETR", JsonUtils.objectArray2Json(optionsGradeResult.toArray()));
        /*add by chenjiaxing 更改交易方向枚举值 end*/
        return new Message<Map<String, Object>>(initPageParams).success();
    }
    
    /**
     * 查询持仓
     * @return
     * @see
     * @since 1.0
     */
    @ApiOperation(value="查询持仓分页数据",notes="查询持仓分页数据")
    @PostMapping("findPositionsPage")
    @ResponseBody
    public Message<Page<EtrExchangeTradePositionsJjResponse>> findPositionsPage(EtrExchangeTradePositionsJjRequest request){
        Page<EtrExchangeTradePositionsJjResponse> page = jjEtrExchangeTrade002Service.findPositionsPage(request);
        return new Message<Page<EtrExchangeTradePositionsJjResponse>>().success().setData(page);
    }

    /**
     * 查询债券质押持仓
     * 
     * @return
     * @see
     * @since 1.0
     */
    @ApiOperation(value="查询债券质押持仓分页数据",notes="查询债券质押持仓分页数据")
    @RequestMapping("findZqzyPositionsPage")
    @ResponseBody
    public Message<Page<EtrExchangeTradePositionsJjResponse>> findZqzyPositionsPage(EtrExchangeTradePositionsJjRequest request){
        Page<EtrExchangeTradePositionsJjResponse> page = jjEtrExchangeTrade002Service.findZqzyPositionsPage(request);
        return new Message<Page<EtrExchangeTradePositionsJjResponse>>().success().setData(page);
    }

    /**
     * 查询证券代码联想框文本框
     *
     * @param request 画面参数
     * @return 证券代码联想框文本框
     * @see
     * @since 1.0
     */
    @ApiOperation(value = " 证券代码：联想文本框",notes = "证券代码：联想文本框")
    @RequestMapping("getSecAcctId")
    public Message<Page<SelectOptionDto>> getSecAcctId(@RequestBody SelectOptionRequestDto request) {
        Page<SelectOptionDto> tempList = jjEtrExchangeTrade002Service.getSecAcctId(request);
        return new Message<Page<SelectOptionDto>>().success().setData(tempList);
    }

    /**
     * 查询上交所和深交所标准券数量
     *
     * @return 上交所和深交所标准券数量
     * @see
     * @since 1.0
     */
    @ApiOperation(value = " 查询上交所和深交所标准券数量",notes = "查询上交所和深交所标准券数量")
    @RequestMapping("loadSecNum")
    @ResponseBody
    public Message<Map<String, String>> loadSecNum(@RequestBody SelectOptionRequestDto request) {
        Map<String, String> map = jjEtrExchangeTrade002Service.loadSecNum(request);
        return new Message<Map<String, String>>().success().setData(map);
    }

    /**
     * 指令列表
     * @return
     */
    @ApiOperation(value = " 查询指令列表分页数据",notes = "查询指令列表分页数据")
    @RequestMapping("selectEtrExchange")
    @ResponseBody
    public Message<Page<EtrExchangeTradeEtrExchangeJjResponse>> selectEtrExchange(EtrExchangeTradeEtrExchangeJjRequest request){
        Page<EtrExchangeTradeEtrExchangeJjResponse> result = jjEtrExchangeTrade002Service.selectEtrExchange(request);
        return new Message<Page<EtrExchangeTradeEtrExchangeJjResponse>>().success().setData(result);
    }

    /**
     * 导出信息excel
     *
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("exportPosition")
    @ResponseBody
    @ApiOperation(value = "持仓列表导出", notes = "持仓列表导出")
    public void exportPosition(@RequestBody EtrExchangeTradeExportJjRequest requestDto, HttpServletResponse response) {
//        Map<String, Object> param = RequestUtil.getPageParam();
//        /*指令执行导出*/
//        String datas = this.getParam("datas");
//        List<EtrExchangeTrade003Dto> param1;
//        if (datas == null) {
//            param1 = jjEtrExchangeTrade001Service.selectEtrExchange2(RequestUtil.getPageParam());
//        } else {
//            param1 = JsonUtils.json2List(datas, EtrExchangeTrade003Dto.class);
//        }
//        File outFile = jjEtrExchangeTrade003Service.makeFile(
//                "/com/joyintech/tams/invest/entrust/etrexchangetrade/conf/export_conf1.xml",
//                param1);
//        return ExportUtil.export(
//                "场内交易指令管理" + SystemGlobalConstant.OPEN_PARENTHESIS
//                        + DateUtil.format(DateUtil.platDateFromCache())
//                        + SystemGlobalConstant.CLOSE_PARENTHESIS
//                        + SystemGlobalConstant.POINT
//                        + FileNameUtil.getFileExtName(outFile),
//                outFile, true);
        // 导出
        ExcelUitl.doExport(requestDto, response, "持仓列表", () -> {
            EtrExchangeTradePositionsJjRequest dto = requestDto.getDto(EtrExchangeTradePositionsJjRequest.class);
            List dataList = jjEtrExchangeTrade002Service.findPositionsPage(dto).getRoot();
            return dataList;
        });
    }

    /**
     * 导出信息excel
     *
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("exportEtrExchange")
    @ResponseBody
    @ApiOperation(value = "指令列表导出", notes = "指令列表导出")
    public void exportEtrExchange(@RequestBody EtrExchangeTradeExportJjRequest requestDto, HttpServletResponse response) {
        // 导出
        ExcelUitl.doExport(requestDto, response, "持仓列表", () -> {
            EtrExchangeTradeEtrExchangeJjRequest dto = requestDto.getDto(EtrExchangeTradeEtrExchangeJjRequest.class);
            List dataList = jjEtrExchangeTrade002Service.selectEtrExchange(dto).getRoot();
            return dataList;
        });
    }


}
