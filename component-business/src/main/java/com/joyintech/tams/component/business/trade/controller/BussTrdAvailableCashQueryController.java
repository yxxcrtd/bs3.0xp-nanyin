/*
 * 文件名：BussTrdAvailableCashQueryController.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：杨智
 * 修改时间：2018年10月11日 修改内容：新建 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.component.business.trade.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.common.service.BussCommonMapperDelegateService;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.component.business.trade.BussTrdAvailableCashQueryService;

@Controller
@RequestMapping("/component/trade/BussTrdAvailableCashQuery")
public class BussTrdAvailableCashQueryController extends BaseController {

    @Autowired(required = false)
    private BussTrdAvailableCashQueryService bussTrdAvailableCashQueryService;

    @Autowired(required = false)
    private DateCalService dateCalService;

    @Autowired(required = false)
    private BussCommonMapperDelegateService bussCommonMapperDelegateService;

    @Override
    public String init(Model model) {
        model.addAttribute("entityName",
                StringUtil.defaultString(this.getParam("entityName"), BussTrdAvailableCashQueryService.DEFAULT_ENTITY_NAME));

        model.addAttribute("ACCT_TYPE_A01", DictCodeListExt1.资金账户类型_产品托管户.getValue());
        model.addAttribute("ACCT_TYPE_A07", DictCodeListExt1.资金账户类型_券商资金账户.getValue());

        return "component/trade/BussTrdAvailableCashQuery";
    }

    /**
     * 查询组合T日可用现金
     * 查询组合T+1日可用现金
     *
     * @param param 参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("getAvailableCash")
    public Map<String, BigDecimal> getAvailableCash(@RequestBody Map<String, Object> param) {
        if (bussTrdAvailableCashQueryService != null) {
            String tradeDateStr = (String) param.get("tradeDate");
            Date tradeDate = DateUtil.parse(tradeDateStr);

            String dateId = (String) param.get("calendarId");
            String finprodId = (String) param.get("finprodId");
            if (StringUtil.isBlank(dateId) && StringUtil.isNotBlank(finprodId)) {
                dateId = bussCommonMapperDelegateService.selectBySinglePrimaryKey(FinProductMapper.class,
                        param.get("finprodId").toString(), FinProduct.class).getCalendarId();
            }
            List<String> holidayList = dateCalService.getDealCalendarHoliday(tradeDate, DateUtil.addDay(tradeDate, 15), dateId);
            param.put("tradeDateNext", DateUtil.format(dateCalService.calculationTradingDay(tradeDate, 1, holidayList, "1")));
            return bussTrdAvailableCashQueryService.getAvailableCash(param);
        }
        return null;
    }

    /**
     * 查询托管户剩余现金
     *
     * @param param 参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getSecManageAvailableCash")
    public BigDecimal getSecManageAvailableCash(@RequestBody Map<String, Object> param) {
        // TODO 需要调接口
        //需求确认托管剩余现金没有接口时就为空 yjm 20190730
        if (bussTrdAvailableCashQueryService != null && param.get("tradeDate") != null) {
            return bussTrdAvailableCashQueryService.getSecManageAvailableCash(param);
        }
        return BigDecimal.ZERO;
    }


    /**
     * 本方券可用持仓：实际 - 质押 +可用（流水表）-融出 +预占用 OWN_POSITION
     * 融入券可用持仓：融入-卖空-卖空可用（流水表） INTEGRATE_POSITION
     * 融入券卖空可用持仓：卖空 SHOWHANDS_POSITION
     */
    @RequestMapping("getShowHandNum")
    @ResponseBody
    public Map<String, Object> getShowHandNum(@RequestBody Map<String, Object> param) {
        return bussTrdAvailableCashQueryService.getShowHandNum(param);
    }
}
