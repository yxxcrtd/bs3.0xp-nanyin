package com.joyintech.tams.exchange.entrust.etrexchange.fjyywzl.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.business.common.pojo.Message;
import com.joyintech.tams.business.common.util.ExcelUitl;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.exchange.entrust.etrexchange.common.dto.EtrExchangeTradeCommonRequest;
import com.joyintech.tams.exchange.entrust.etrexchange.common.dto.EtrExchangeTradeExportCommonRequest;
import com.joyintech.tams.exchange.entrust.etrexchange.common.dto.EtrExchangeTradePositionsCommonRequest;
import com.joyintech.tams.exchange.entrust.etrexchange.common.dto.PsOptionsRequest;
import com.joyintech.tams.exchange.entrust.etrexchange.common.service.EtrExchangeTradeCommon001Service;
import com.joyintech.tams.exchange.entrust.etrexchange.fjyywzl.dto.EtrExchangeTradeFjyywzlZqzyDto;
import com.joyintech.tams.exchange.entrust.etrexchange.fjyywzl.dto.EtrExchangeTradeZqzyRequest;
import com.joyintech.tams.exchange.entrust.etrexchange.fjyywzl.service.EtrExchangeTradeFjyywzl001Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@PageId("EtrExchangeTradeFjyywzl001")
@RequestMapping("/etrexchange/fjyywzl/etrExchangeTradeFjyywzl001")
@Api(tags = "非交易业务指令")
public class EtrExchangeTradeFjyywzl001Controller extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private EtrExchangeTradeFjyywzl001Service etrExchangeTradeFjyywzl001Service;

    @Autowired
    private EtrExchangeTradeCommon001Service etrExchangeTradeCommon001Service;

    /**
     * 检索产品债券质押持仓分页数据。
     *
     * @return Page<EtrExchangeTrade002Dto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ApiOperation(value="查询债券质押数据",notes = "查询债券质押数据")
    @ResponseBody
    @PostMapping("/findZqzyPositionsPage")
    public Message<Page<EtrExchangeTradeFjyywzlZqzyDto>> findZqzyPositionsPage(@RequestBody EtrExchangeTradeZqzyRequest request){
        try {
            Map<String, Object> map = BeanUtil.object2Map(request);
            Page<EtrExchangeTradeFjyywzlZqzyDto> list = etrExchangeTradeFjyywzl001Service.findZqzyPositionsPage(map);
            return new Message<Page<EtrExchangeTradeFjyywzlZqzyDto>>().success().setData(list);
        }catch (Exception e) {
            logger.error("查询债券质押数据异常",e);
            return new Message<Page<EtrExchangeTradeFjyywzlZqzyDto>>().fail().setMessage("查询债券质押数据异常");
        }
    }

    /**
     * 查询上交所和深交所标准券数量
     *
     * @return 上交所和深交所标准券数量
     * @see
     * @since 1.0
     */
    @ApiOperation(value="查询上交所和深交所标准券数量",notes = "查询上交所和深交所标准券数量")
    @ResponseBody
    @PostMapping("/loadSecNum")
    public Message<Map<String, String>> loadSecNum(@RequestBody EtrExchangeTradeZqzyRequest request) {
        try {
            Map<String, Object> param = BeanUtil.object2Map(request);
            Map<String, String> map = etrExchangeTradeFjyywzl001Service.loadSecNum(param);
            return new Message<Map<String,String>>().success().setData(map);
        }catch (Exception e){
            logger.error("查询上交所和深交所标准券数量",e);
            return new Message<Map<String,String>>().fail().setMessage("查询上交所和深交所标准券数量");
        }
    }

    /**
     * 导出信息excel
     *
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("export")
    @ResponseBody
    @ApiOperation(value = "债券质押列表导出", notes = "债券质押列表导出")
    public void exportPosition(@RequestBody EtrExchangeTradeExportCommonRequest requestDto, HttpServletResponse response) {
        // 导出
        ExcelUitl.doExport(requestDto, response, "债券质押列表", () -> {
            EtrExchangeTradeZqzyRequest dto = requestDto.getDto(EtrExchangeTradeZqzyRequest.class);
            Map<String, Object> map = BeanUtil.object2Map(dto);
            List dataList = etrExchangeTradeFjyywzl001Service.findZqzyPositionsPage(map).getRoot();
            return dataList;
        });
    }

//    EtrExchangeTrade001/selectExtPs

    /**
     * 由于交易平台不同，设置不同交易平台对应的委托方向
     * @param psOptionsRequest psOptionsRequest
     */
    @ApiOperation(value = "获取交易方向", notes = "获取交易方向")
    @PostMapping("/getPsSelectOptions")
    @ResponseBody
    public Message<List<SelectOptionDto>> getPsSelectOptions(@RequestBody PsOptionsRequest psOptionsRequest) {
        try {
            SelectDto selectDto = this.getSelectDataFromDataDic(DictCode.交易方向, null);
            List<SelectOptionDto> tempList = etrExchangeTradeFjyywzl001Service.getPsSelectOptions(psOptionsRequest,selectDto);
            return new Message<List<SelectOptionDto>>().success().setData(tempList);
        } catch (Exception e) {
            logger.error("查询功能异常", e);
            return new Message<List<SelectOptionDto>>().fail().setMessage("查询异常");
        }
    }


    /**
     * 获取标准券持仓
     * @param request request
     */
    @ApiOperation(value = "获取标准券持仓", notes = "获取标准券持仓")
    @PostMapping("/queryStandandTicketCanUse")
    @ResponseBody
    public Message<String> queryStandandTicketCanUse(@RequestBody EtrExchangeTradeCommonRequest request) {
        try {
            Map<String,Object> param = BeanUtil.object2Map(request);
            Date sysDate = DateUtil.platDateFromCache();
            param.put("sysDate", DateUtil.format(sysDate));
            BigDecimal entrustNum = etrExchangeTradeCommon001Service.queryStandandTicketCanUse(param);
            if(entrustNum == null) {
                entrustNum= BigDecimal.ZERO;
            }
            String entrustNumStr=entrustNum.setScale(2).toString();
            return new Message<String>(entrustNumStr);
        }catch (Exception e) {
            logger.error("获取标准券持仓异常",e);
            return new Message<String>().fail().setMessage("获取标准券持仓异常");
        }
    }
}
