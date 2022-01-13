package com.joyintech.tams.exchange.entrust.etrexchange.fjyywzl.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.tams.business.common.pojo.Message;
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

import java.util.Map;

@Controller
@PageId("EtrExchangeTradeFjyywzl001")
@RequestMapping("/etrexchange/fjyywzl/etrExchangeTradeFjyywzl001")
@Api(tags = "非交易业务指令")
public class EtrExchangeTradeFjyywzl001Controller {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private EtrExchangeTradeFjyywzl001Service etrExchangeTradeFjyywzl001Service;

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
}
