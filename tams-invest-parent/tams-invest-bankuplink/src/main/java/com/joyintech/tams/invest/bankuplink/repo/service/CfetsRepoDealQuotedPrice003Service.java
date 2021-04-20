/*
 * 文件名：CfetsRepoDealQuotedPriceService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yufei
 * 修改时间：2020年5月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.bankuplink.repo.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.*;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.invest.bankuplink.repo.dto.CfetsRepoDealQuotedPrice003Dto;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.common.PtlAllocationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author zhangnian
 * @version 1.0 2021年02月26日
 * @see CfetsRepoDealQuotedPrice003Service
 * @since 1.0
 */
@Service
public class CfetsRepoDealQuotedPrice003Service extends CommonExportService<CfetsRepoDealQuotedPrice003Dto> {

    /**
     * {@linkplain}
     */
    @Autowired
    private IPlatformDate iPlatformDate;

    /**
     * DateCalService
     */
    @Autowired(required = false)
    private DateCalService dateCalService;

    /**
     * 查询可质押列表
     * 
     * @param param 查询参数
     * @return 可质押基本信息
     * @see
     * @since 1.0
     */
    public Page<CfetsRepoDealQuotedPrice003Dto> queryPledgeBondList(Map<String, Object> param) {
        param.put("platDate", iPlatformDate.getSysDateStr());
        param.put("platDateBefore", getPreviousWorkDay());
        param.put("loginUserId", LoginUserUtil.getUserName());
        String bondCodesStr = (String)param.get("bondCodes");
        if (StringUtil.isNotEmpty(bondCodesStr)) {
            try {
                String[] bondCodes = JsonUtils.json2Array(bondCodesStr, String[].class);
                param.put("bondCodes", bondCodes);
            }
            catch (IOException e) {
                param.put("bondCodes", new String[0]);
            }
        }
        Page<CfetsRepoDealQuotedPrice003Dto> list = restTemplate.postForPage(
            ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsRepoDealQuotedPrice001/queryPledgeBondList",
            param, CfetsRepoDealQuotedPrice003Dto.class);
        if (list != null && !StringUtil.isEmptyList(list.getRoot())) {
            for (CfetsRepoDealQuotedPrice003Dto dto : list.getRoot()) {
                // 计算可质押券面
                if (dto.getAvaPrinDayBefore() != null && dto.getPrinSysDate() != null
                    && dto.getPrinDayBefore() != null && dto.getPledgeNumber() != null) {
                    // 当日的有效卖出(T+0/+1)委托指令券面:prinSysDate
                    // 昨日的债券持仓券面:AvaPrinDayBefore
                    // 昨日的T+1卖出券面:prinDayBefore
                    // 今日已质押面额:pledgeNumber
                    // 可质押券面总额 =
                    // (昨日的债券持仓券面-昨日已质押券面)-昨日的T+1卖出券面-当日的有效卖出(T+0/+1)委托指令券面-当日已报价的正回购该债券的质押券面
                    //dto.setAvaPledgePrin(ArithmeticUtil.doAdd(dto.getAvaPrinDayBefore(), dto.getPrinSysDate(), dto.getPrinDayBefore()));
                    dto.setAvaPledgeNumber(ArithmeticUtil.subtract(dto.getAvaPledgePrin(), dto.getPledgeNumber()));
                    
                    // modify by zhangliang 2020027 单位万元处理
                    dto.setPrin(ArithmeticUtil.divide(dto.getPrin(), new BigDecimal(10000), 6));
                    dto.setAvaPledgePrin(ArithmeticUtil.divide(dto.getAvaPledgePrin(), new BigDecimal(1), 6));
                    dto.setPledgeNumber(ArithmeticUtil.divide(dto.getPledgeNumber(), new BigDecimal(10000), 6));
                    dto.setAvaPledgeNumber(ArithmeticUtil.divide(dto.getAvaPledgeNumber(), new BigDecimal(10000), 6));
                }
            }
        }
        return list;
    }
    
    /**
     * 查询质押券明细列表
     * 
     * @param param 查询参数
     * @return 质押券明细列表
     * @see
     * @since 1.0
     */
    public Page<CfetsRepoDealQuotedPrice003Dto> getPledgeBondList(Map<String, Object> param) {
    	param.put("plateDate", iPlatformDate.getSysDateStr());
        Page<CfetsRepoDealQuotedPrice003Dto> list = restTemplate.postForPage(
            ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsRepoDealQuotedPrice001/getPledgeBondList",
            param, CfetsRepoDealQuotedPrice003Dto.class);
        return list;
    }

    /**
     * 获取下一个工作日
     *
     * @param parameters 画面参数
     * @return 下一个工作日
     * @see
     * @since 1.0
     */
    private String getPreviousWorkDay() {
        if (dateCalService != null) {
            Date tradeDate = iPlatformDate.getSysDate();
            Calendar cd = Calendar.getInstance();
            cd.setTime(tradeDate);
            cd.add(Calendar.DATE, -31);
            List<String> holiday = dateCalService.getDealCalendarHoliday(cd.getTime(),
                DateUtil.addDays(tradeDate, 31), CacheUtil.getSysParam("ibuVar_calendarId"));
            String tradingDay = null;
			try {
				tradingDay = dateCalService.calculationTradingDayForBefore(
				    iPlatformDate.getSysDateStr(), 1, holiday, "1");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            return tradingDay;
        }

        return null;
    }


    /**
     * 组合分配列表
     * @param map
     * @return
     */
    public List<PtlAllocationDto> selectPortfolioInit(Map<String,Object> map) {
        return restTemplate.postForList(
                ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsRepoDealQuotedPrice001/selectPortfolioInit",
                map, PtlAllocationDto.class);
    }

    /**
     * 组合分配列表
     * @param map
     * @return
     */
    public List<CfetsRepoDealQuotedPrice003Dto> selectQuoteCrepPledgeForDzh(Map<String,Object> map) {
        return restTemplate.postForList(
                ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsRepoDealQuotedPrice001/selectQuoteCrepPledgeForDzh",
                map, CfetsRepoDealQuotedPrice003Dto.class);
    }


    /**
     * 对手方质押券信息
     * @param map
     * @return
     */
    public List<CfetsRepoDealQuotedPrice003Dto> selectQuoteCrepPledgeForDsf(Map<String,Object> map) {
        return restTemplate.postForList(
                ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsRepoDealQuotedPrice001/selectQuoteCrepPledgeForDsf",
                map, CfetsRepoDealQuotedPrice003Dto.class);
    }

    public String getIsShowDsfPledge(Map<String,Object> map) {
        map.put("quoteRoleType", BussinessDictCodeList.银行间上行本方角色_回复方.getValue());
        map.put("ps",BussinessDictCodeList.回购拆借方向_正回购.getValue());
        return restTemplate.postForObject(
                ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsRepoDealQuotedPrice001/getIsShowDsfPledge",
                map, String.class);
    }
}
