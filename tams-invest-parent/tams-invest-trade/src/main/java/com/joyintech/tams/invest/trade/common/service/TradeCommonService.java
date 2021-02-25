package com.joyintech.tams.invest.trade.common.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.FinCashPlanMapper;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.trade.dao.TrdCommonBaseMapper;
import com.joyintech.tams.invest.interf.dto.CusdepOrderDto;
import com.joyintech.tams.invest.interf.service.InterfComponentService;
import com.joyintech.tams.invest.trade.common.dto.EtrInvestInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: tams-invest-parent
 * @description: 交易类共通Service
 * @author: 强成西
 * @create: 2018-12-04 11:54
 **/
@Service
public class TradeCommonService extends BaseService {
    
    public static final String 金融产品代码 = "FINPROD_ID";
	/**
     * mstWorkflowCommonService工作流组件
     */
    @Autowired(required=false)
    private MstWorkflowCommonService mstWorkflowCommonService;
	
    @Autowired
    private TrdCommonBaseMapper trdCommonBaseMapper;

    /**
     * 向其它系统发送划款报文
     */
    @Autowired
    private InterfComponentService interfComponentService;

    /**
     * DateCalService
     */
    @Autowired(required = false)
    private DateCalService dateCalService;

    @Autowired
    private FinCashPlanMapper finCashPlanMapper;
    
    /**
     * 查询生效的委托
     * @param entrustId
     * @return
     */
    public EtrInvestInfoDto findEtrInfo(String entrustId) {
        return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/common/trdcommonbase/findEtrInfo", entrustId,
                EtrInvestInfoDto.class);
    }

    /**
     * 查询交易员
     * @param entrustId 委托代码
     * @return 交易员
     * @see
     * @since 1.0
     */
    public String getTrader(String entrustId){
        Map<String,Object> map = new HashMap<>();
        map.put("entrustId", entrustId);
        map.put("entrustStatus", DictCodeList.委托状态_已接收.getValue());
        return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME
                + "/TrdPlanRateInvestTrade/getTrader",map,String.class);
    }
    /**
     * 划款信息向其它系统发送报文信息
     * @param entity 划款组建对应的实体类
     * @see
     * @since 1.0
     */
    public void sendCusdepOrder(List<BussTrdTransferOrderDto> entity){
        if (StringUtil.equals(CacheUtil.getSysParam("interfFlag_send_orders"), "01") && entity != null) {
            entity.forEach(item -> {
                if(StringUtil.equals(DictCodeListExt1.收支方向_支出.getValue(), item.getPs())){
                    CusdepOrderDto cusdepOrderDto = interfComponentService.sendCusdepRequest(item.getOrderId(), StringUtil.equals(item.getPayType(), 
                                                    DictCodeListExt1.平安划款类型_DVP划款.getValue()), "1");
                    if(cusdepOrderDto == null || cusdepOrderDto.getErrMsg() != null 
                        || StringUtil.equals(cusdepOrderDto.isResult(), false)){
                        throw new RuntimeException("请求发送异常...");
                    }
                }
            });
        }
    }
    
    /**
     * huwenbin 
     * 指令接收交易录单验证数据已存在流程中
     * @param entrustId
     * @param pageId
     * @return
     */
    public String check(String entrustId, String pageId){
    	WflValidInputParameter parameter = new WflValidInputParameter();
        parameter.setBusinessDataPk3(entrustId);
        parameter.setPageId(pageId);
        if(null != mstWorkflowCommonService && 
            mstWorkflowCommonService.isInWflProcess(parameter)){
        	return "已存在流程,不能操作！";
        }
        else{
        	return null;
        }
    }
    /**
     * wangguiqi
     * 指令接收交易录单根据委托代码查询委托金额
     * @param entrustId
     * @return BigDecimal
     */
    public BigDecimal getEntrustAmtByEntrustId(String entrustId) {
    	BigDecimal entrustNum = BigDecimal.ZERO;
    	entrustNum = restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/common/trdcommonbase/getEntrustAmtByEntrustId", entrustId,
    			BigDecimal.class);
		return entrustNum;
    }
    /**
     * wangguiqi
     * 指令接收交易录单根据委托代码查询流程中已录入金额
     * @param entrustId
     * @return BigDecimal
     */
    public BigDecimal getWflPrinAmt(String entrustId, String finProductType, String invAim) {
    	BigDecimal wflPrinAmt = BigDecimal.ZERO;
        Map<String,Object> map = new HashMap<>();
        map.put("entrustId", entrustId);
        map.put("type", finProductType);
        map.put("invAim", invAim);
    	wflPrinAmt = restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/common/trdcommonbase/getWflPrinAmt", map,
    			BigDecimal.class);
		return wflPrinAmt;
    }

    /**
     * 获取下一个工作日
     *
     * @param parameters 画面参数
     * @return 下一个工作日
     * @see
     * @since 1.0
     */
    public String getNextWorkDay(Map<String, Object> parameters) {
        String tradeDateStr = (String)parameters.get("tradeDate");
        String calendarIdStr = (String)parameters.get("calendarId");
        if (StringUtil.isBlank(tradeDateStr) || StringUtil.isBlank(calendarIdStr)) {
            return null;
        }

        if (dateCalService != null) {
            Date tradeDate = DateUtil.parse((String)parameters.get("tradeDate"));
            List<String> holiday = dateCalService.getDealCalendarHoliday(tradeDate,
                    DateUtil.addDays(tradeDate, 31), (String)parameters.get("calendarId"));
            String settleSpreed = (String)parameters.get("settleSpreed");
            Date tradingDay = dateCalService.calculationTradingDay(tradeDate, Integer.parseInt(StringUtil.isEmpty(settleSpreed)?"0":settleSpreed), holiday, "1");
            return DateUtil.format(tradingDay);
        }

        return null;
    }

    /**
     * check是否流程中，目前只有金融产品代码在汇总表是否存在
     * @param findKey
     * @param key类型
     * @param 是否汇总表
     * @return
     */
    public List<String> checkWflData(String findKey, String key类型, boolean 是否汇总表) {
        if (金融产品代码.equals(key类型)) {
            if (是否汇总表) {
                return trdCommonBaseMapper.checkTotleWflData(findKey);
            }
        }
        return null;
    }

    public List<BigDecimal> selectProAvailableCash(String entrustId){
        Map<String,Object> map = new HashMap<>();
        map.put("entrustId",entrustId);
        return trdCommonBaseMapper.selectProAvailableCash(map);
    }

    /**
     * 查询当天是否为资产收息日或到期日
     * @param trdProductDeal
     * @return
     */
    public int getPayTrad(Map<String, Object> map) {
        return finCashPlanMapper.getPayTrad(map);
    }
    /**
     * 查询转仓日前一天(其他应收款-利息)科目余额是否为0
     * @param trdProductDeal
     * @return
     */
    public Map<String, Object> getYSKLXBalance(Map<String, Object> map) {
        return trdCommonBaseMapper.getYSKLXBalance(map);
    }

    /**
     * 查询理论结息日有没有待处理和流程中的资产存续交易
     * @param params
     * @return
     */
    public int selectTrdOnPayDate(Map<String, Object> params) {
        return trdCommonBaseMapper.selectTrdOnPayDate(params);
    }
}
