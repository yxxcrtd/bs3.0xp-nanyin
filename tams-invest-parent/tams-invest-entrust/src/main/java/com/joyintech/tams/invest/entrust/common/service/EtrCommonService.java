package com.joyintech.tams.invest.entrust.common.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.system.base.dao.SysPageDictlistMapper;
import com.joyintech.system.base.entity.SysPageDictlist;
import com.joyintech.system.dict.service.SysDataDict001Service;
import com.joyintech.system.page.service.SysPageService;
import com.joyintech.tams.common.base.dao.FinProductConvertibleMapper;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dao.CreditLinesDealsMapper;
import com.joyintech.tams.invest.common.trade.dto.CreditLinesInputDto;
import com.joyintech.tams.invest.entrust.etragrereptrade.dto.EtrAgreRepTradeDto;
import com.joyintech.tams.invest.entrust.etrbondfirstlbidding.dto.EtrBondFirstLBiddingDto;
import com.joyintech.tams.invest.entrust.etrboutrepo.dto.EtrBoutRepoDto;
import com.joyintech.tams.invest.entrust.etrbrowdepoinfo.dto.EtrBrowDepoInfoDto;
import com.joyintech.tams.invest.entrust.etrconbond.dto.EtrConBondDto;
import com.joyintech.tams.invest.entrust.etrexchangetrade.dto.EtrExchangeTrade001Dto;
import com.joyintech.tams.invest.entrust.etrfund.dto.EtrFundDto;
import com.joyintech.tams.invest.entrust.etrinvestbondinfo.dto.EtrInvestBondInfoDto;
import com.joyintech.tams.invest.entrust.etrlendspecinfo.dto.EtrLendSpecInfoDto;
import com.joyintech.tams.invest.entrust.etrplanassetc.dto.EtrPlanAssetCDto;
import com.joyintech.tams.invest.entrust.etrplanassetn.dto.EtrPlanAssetNDto;
import com.joyintech.tams.invest.entrust.etrplanassetr.dto.EtrPlanAssetRDto;
import com.joyintech.tams.invest.entrust.etrproject.dto.EtrProjectDto;
//import com.joyintech.tams.invest.entrust.etrprojectnet.dto.EtrProjectNetDto;
import com.joyintech.tams.invest.entrust.etrprojectnet.dto.EtrProjectNetDto;
import com.joyintech.tams.invest.entrust.etrrepo.dto.EtrRepoDto;
import com.joyintech.tams.invest.entrust.etrseclend.dto.EtrSecLendDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.dao.FinProductAddMapper;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductAdd;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;
import com.joyintech.tams.invest.common.trade.dto.EtrSecPositionDto;
import com.joyintech.tams.invest.common.trade.service.BaseEtrCashPositionCommService;
import com.joyintech.tams.valuation.common.service.BaseCommonService;

/**
 * @program: 2.1.0
 * @description: 委托公用service
 * @author: 强成西
 * @create: 2019-10-15 09:06
 **/
@Service
public class EtrCommonService extends BaseCommonService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired(required = false)
    private BaseEtrCashPositionCommService baseEtrCashPositionCommService;

    @Autowired(required = false)
    private FinProductAddMapper finProductAddMapper;
    
    @Autowired
    private FinProductMapper finProductMapper;
    @Autowired
    private CreditLinesDealsMapper creditLinesDealsMapper;

    @Autowired
    private CommonBusinessService commonBusinessService;

    /**
     * 日期计算
     */
    @Autowired
    private DateCalService dateCalService;

    @Autowired
    private SysPageDictlistMapper sysPageDictlistMapper;

    @Autowired
    private IPlatformDate iPlatformDate;
    @Autowired(required = false)
    private FinProductConvertibleMapper finProductConvertibleMapper;
    /**
     * 组合指令可用现金查询
     *
     * @param param
     * @return cashT 组合T日可用, cashT1 组合T+1日可用, cashT3 托管户可用, cashT2 通道可用， cashT4 交易所资金账户可用
     * @see
     * @since 1.0
     */
    public Map<String, Object> getProAvailableCash(Map<String, Object> param) {        
        PtlCashPositionFlowDto dto = new PtlCashPositionFlowDto();
        Date sysdate = DateUtil.parse(DateUtil.getSysDate());
        if (StringUtil.isNotEmpty(param.get("acctId"))){
            dto.setAcctId((String)param.get("acctId"));
        }
        if (StringUtil.isNotEmpty(param.get("finprodId"))){
            FinProduct finProduct = finProductMapper.selectByPrimaryKey((String)param.get("finprodId"));
            List<String> holidayList = dateCalService.getDealCalendarHoliday(sysdate,DateUtil.addDay(sysdate, 15),finProduct.getCalendarId());
            Date tradeDay = dateCalService.calculationTradingDay(DateUtil.parse(DateUtil.format(sysdate).toString()), 1, holidayList, "1");
            param.put("sysDateT1", DateUtil.format(tradeDay));
        }else {
            String calId = (String)param.get("calId");
            Date vdate = DateUtil.parse((String)param.get("vdate"));
            if(StringUtil.isNotEmpty(calId)&&vdate!=null){
                List<String> holidayList = dateCalService.getDealCalendarHoliday(DateUtil.parse(DateUtil.format(vdate)),DateUtil.addDay(DateUtil.parse(DateUtil.format(vdate)), 15),calId);
                Date tradeDay = dateCalService.calculationTradingDay(DateUtil.parse(DateUtil.format(vdate)), 1, holidayList, "1");
                param.put("sysDateT1",DateUtil.format(tradeDay));
            }
        }
        
        dto.setCdate(sysdate);
        List<String> acctTypeList = new ArrayList<>();
        acctTypeList.add(DictCodeListExt1.资金账户类型_产品托管户.getValue());
        dto.setPortfolioId((String)param.get("portfolioId"));
        dto.setCcy((String)param.get("ccy"));
        Map<String, Object> result = new HashMap<>();
        dto.setAcctType(acctTypeList);

        if (baseEtrCashPositionCommService != null) {
            BigDecimal cashT = BigDecimal.ZERO;
            //期货保证金单独处理
            if(param.containsKey("acctType")&&StringUtil.equals(param.get("acctType"),DictCodeListExt1.资金账户类型_期货资金账户.getValue())){
                acctTypeList.clear();
                acctTypeList.add(DictCodeListExt1.资金账户类型_期货资金账户.getValue());
                BigDecimal cashQH = baseEtrCashPositionCommService.qryCommandAvailableCash(dto);
                cashT = ArithmeticUtil.doAdd(cashT, cashQH == null ? BigDecimal.ZERO : cashQH);
            }else{
                BigDecimal cashT3 = baseEtrCashPositionCommService.qryCommandAvailableCash(dto);
                result.put("cashT3", cashT3); // 托管户可用
                cashT = ArithmeticUtil.doAdd(cashT, cashT3 == null ? BigDecimal.ZERO : cashT3);

                acctTypeList.clear();
                acctTypeList.add(DictCodeListExt1.资金账户类型_券商资金账户.getValue());
                BigDecimal cashT4 = baseEtrCashPositionCommService.qryCommandAvailableCash(dto);
                result.put("cashT4", cashT4);// 交易所资金账户可用
                cashT = ArithmeticUtil.doAdd(cashT, cashT4 == null ? BigDecimal.ZERO : cashT4);
            }

            if (StringUtil.isNotEmpty(param.get("chlFinprodId"))) {
                FinProductAdd finProductAdd = finProductAddMapper.selectByPrimaryKey((String)param.get("chlFinprodId"));
                result.put("payType", "02");
                if (finProductAdd != null) {
                    result.put("payType", finProductAdd.getPayType());
                }
                dto.setAcctType(null);
                dto.setChlFinprodId((String)param.get("chlFinprodId"));
                BigDecimal cashT2 = baseEtrCashPositionCommService.qryCommandAvailableCash(dto);
                result.put("cashT2", cashT2 == null ? BigDecimal.ZERO : cashT2);// 通道可用
                //组合T日可用现金不加通道可用现金
                //cashT = ArithmeticUtil.doAdd(cashT, cashT2);
            }
            result.put("cashT", cashT);// 组合T日可用

            if (StringUtil.isNotEmpty(param.get("sysDateT1"))) {
                PtlCashPositionFlowDto dto2 = new PtlCashPositionFlowDto();
                Date sysdateT1 = DateUtil.parse((String)param.get("sysDateT1"));
                dto2.setCdate(sysdateT1);
                acctTypeList.clear();
                acctTypeList.add(DictCodeListExt1.资金账户类型_券商资金账户.getValue());
                acctTypeList.add(DictCodeListExt1.资金账户类型_产品托管户.getValue());
                dto2.setAcctType(acctTypeList);
                dto2.setPortfolioId((String)param.get("portfolioId"));
                dto2.setCcy((String)param.get("ccy"));
                BigDecimal cashT1 = baseEtrCashPositionCommService.qryCommandAvailableCash(dto2);
                if (StringUtil.isNotEmpty(param.get("chlFinprodId"))) {
                    FinProductAdd finProductAdd = finProductAddMapper.selectByPrimaryKey((String)param.get("chlFinprodId"));
                    result.put("payType", "02");
                    if (finProductAdd != null) {
                        result.put("payType", finProductAdd.getPayType());
                    }
                    dto2.setAcctType(null);
                    //组合T日可用现金不加通道可用现金
                    /*dto2.setChlFinprodId((String)param.get("chlFinprodId"));
                    BigDecimal cashT2 = baseEtrCashPositionCommService.qryCommandAvailableCash(dto2);
                    result.put("cashT21", cashT2 == null ? BigDecimal.ZERO : cashT2);// 通道T+1可用
                    cashT1 = ArithmeticUtil.doAdd(cashT1, cashT2);*/
                }
                result.put("cashT1", cashT1 == null ? result.get("cashT") : cashT1);// 组合T+1日可用
            }
        }
        return result;
    }

    public BigDecimal getAssetAvailablePosition(EtrSecPositionDto dto) {
        if (baseEtrCashPositionCommService != null) {
            dto.setHodingType(DictCodeListExt1.持仓类型_委托指令可用.getValue());
            return baseEtrCashPositionCommService.qryAvailablePosition(dto);
        }
        return null;
    }

    /**
     * check多对多指令字段用,空判断
     * 
     * @param cellData cellData
     * @param labelName labelName
     * @param indexId indexId
     * @return 错误信息
     * @see
     * @since 1.0
     */
    public String checkIsBlank(String cellData, String labelName, String indexId) {
        // 判断是否是空
        if (StringUtil.isBlank(cellData)) {
            return getMessage("ETR_E_0015", getMessage(labelName), indexId);
        }
        return "";
    }

    public String checkMarketQx(String cellData1,String cellData2,String indexId){
        if (cellData1.equals ("09:OTC场外") && ( StringUtil.isNotEmpty(cellData2))){
                return "交易场所为OTC场外时，期限品种不可填(行号为"+indexId+")";
        }
        return "";
    }

    public String checkMarketQs(String cellData1,String cellData3,String indexId){
        if (cellData1.equals ("09:OTC场外") && cellData3.equals ("02:T+1")){
            return "交易场所为OTC场外时，清算速度只能选T+0 (行号为"+indexId+")";
        }
        return "";
    }


    public String checkQDQ(String cellData1,String cellData2,String cellData3,String indexId){
        Date cellDateResult1 = DateUtil.parse (cellData1);
        Date cellDateResult2 = DateUtil.parse (cellData2);
        if(cellDateResult1 != null && cellData2 != null){
            long days = DateUtil.getBetweenDayNumber(cellDateResult1,cellDateResult2);
            if(cellData3.equals (String.valueOf (days)) == false){
                return "起息日与到期日的间隔天数与期限不同 (行号为"+indexId+")";
            }
            if (DateUtil.format (DateUtil.parse (cellData2),"yyyy-MM-dd").equals (DateUtil.addDay(DateUtil.format (DateUtil.parse (cellData1),"yyyy-MM-dd"),Integer.parseInt (cellData3))) == false){
                return "起息日与期限相加不等于到期日 (行号为"+indexId+")";
            }
        }
        return "";
    }

    /**
     * check多对多指令字段用,日期格式
     * 
     * @param cellData cellData
     * @param labelName labelName
     * @param indexId indexId
     * @return 错误信息
     * @see
     * @since 1.0
     */
    public String checkIsDate(String cellData, String labelName, String indexId) {
        // 判断是否是正确的日期格式
        if (StringUtil.isNotBlank(cellData)) {
            try {
                Date result = DateUtil.parse(cellData);
                if (result == null) {
                    return getMessage("ETR_E_0016", getMessage(labelName), indexId);
                }
            }
            catch (RuntimeException e) {
                return getMessage("ETR_E_0016", getMessage(labelName), indexId);
            }
        }
        return "";
    }

    /**
     * check多对多指令字段用,组合代码是否存在
     * 
     * @param cellData cellData
     * @param labelName labelName
     * @param indexId indexId
     * @return 错误信息
     * @see
     * @since 1.0
     */
    public String checkPortfolioIdExist(String cellData, String labelName, String indexId) {
        // 判断是否是正确的组合代码
        if (StringUtil.isNotBlank(cellData)) {
            Map<String, Object> param = new HashMap<>();
            param.put("act", true);
            param.put("initValue", cellData);
            param.put("portType01", DictCodeListExt1.组合类型_投资类组合.getValue());
            param.put("loginUserName", LoginUserUtil.getUserName());
            param.put("page", 1);
            param.put("length", 20);
            Page<SelectOptionDto> selectOptionDtoPage = restTemplate.postForPage(
                ServiceApplication.COMMON_SERVICE_NAME + "/CommonSelect/selectPortfolioId", param,
                SelectOptionDto.class);
            if (selectOptionDtoPage.getRoot().isEmpty()) {
                return getMessage("ETR_E_0017", getMessage(labelName), indexId);
            }
        }
        return "";
    }
    
    /**
     * check多对多指令字段用,交易日历是否存在
     * 
     * @param cellData cellData
     * @param labelName labelName
     * @param indexId indexId
     * @return 错误信息
     * @see
     * @since 1.0
     */
    public String checkCalendarIdExist(String cellData, String labelName, String indexId) {
        // 判断是否是正确交易日历
        if (StringUtil.isNotBlank(cellData)) {
            Map<String, Object> param = new HashMap<>();
            param.put("initValue", cellData);
            param.put("page", 1);
            param.put("length", 20);
            Page<SelectOptionDto> selectOptionDtoPage = restTemplate.postForPage(
                ServiceApplication.COMMON_SERVICE_NAME + "/CommonSelect/selectCalendarId", param,
                SelectOptionDto.class);
            if (selectOptionDtoPage.getRoot().isEmpty()) {
                return getMessage("ETR_E_0017", getMessage(labelName), indexId);
            }
        }
        return "";
    }

    /**
     * check多对多指令字段用,正确的数值格式
     * 
     * @param cellData cellData
     * @param labelName labelName
     * @param indexId indexId
     * @return 错误信息
     * @see
     * @since 1.0
     */
    public String checkIsNumber(String cellData, String labelName, String indexId) {
        // 判断是否是正确的数值格式
        if (StringUtil.isNotBlank(cellData)) {
            try {
                Double.parseDouble(cellData.replace(",", ""));
            } catch (NumberFormatException e) {
                return getMessage("ETR_E_0016", getMessage(labelName), indexId);
            }
        }
        return "";
    }

    /**
     * check多对多指令字段用,数值大于0
     * 
     * @param cellData cellData
     * @param labelName labelName
     * @param indexId indexId
     * @return 错误信息
     * @see
     * @since 1.0
     */
    public String checkIsGtZero(String cellData, String labelName, String indexId) {
        // 判断是否数值大于0
        if (StringUtil.isNotBlank(cellData)){
            try {
                double num = Double.parseDouble(cellData.replace(",", ""));
                BigDecimal result = BigDecimal.valueOf(num);
                if (result.compareTo(BigDecimal.ZERO) <= 0) {
                    return getMessage("ETR_E_0019", getMessage(labelName), indexId);
                }
            } catch (NumberFormatException e) {
                return getMessage("ETR_E_0016", getMessage(labelName), indexId);
            }
        }
        return "";
    }

    /**
     * check多对多指令字段用,数值是否是整数
     *
     * @param cellData cellData
     * @param labelName labelName
     * @param indexId indexId
     * @return 错误信息
     * @see
     * @since 1.0
     */
    public String checkIsZNum(String cellData, String labelName, String indexId) {
        // 判断是否数值为整数
        if (StringUtil.isNotBlank(cellData)){
            try {
                double num = Double.parseDouble(cellData.replace(",", ""));
                BigDecimal result = BigDecimal.valueOf(num);
                if (result.compareTo(BigDecimal.ZERO) > 0) {
                    if(cellData.indexOf(".") > -1) {
                        return getMessage("ETR_E_0044", getMessage(labelName), indexId);
                    }
                }

            } catch (NumberFormatException e) {
                // 在校验大小时不要判断格式不正确，直接打印异常日志，正常返回即可
                logger.error(getMessage("ETR_E_0016", getMessage(labelName), indexId), e);
                return "";
            }
        }
        return "";
    }
    /**
     * check多对多指令字段用,数值是否大于0小于等于100
     *
     * @param cellData cellData
     * @param labelName labelName
     * @param indexId indexId
     * @return 错误信息
     * @see
     * @since 1.0
     */
    public String checkIsGtZeroLtHundred(String cellData, String labelName, String indexId) {
        // 判断是否数值大于0小于等于100
        if (StringUtil.isNotBlank(cellData)){
            try {
                double num = Double.parseDouble(cellData.replace(",", ""));
                BigDecimal result = BigDecimal.valueOf(num);
                if (result.compareTo(BigDecimal.ZERO) <= 0 && result.compareTo(BigDecimal.valueOf(100)) > 0){
                    return getMessage("ETR_E_0019", getMessage(labelName), indexId);
                }
            } catch (NumberFormatException e) {
                return getMessage("ETR_E_0016", getMessage(labelName), indexId);
            }
        }
        return "";
    }

    /**
     * check多对多指令字段用,数值是否小于等于100
     *
     * @param cellData cellData
     * @param labelName labelName
     * @param indexId indexId
     * @return 错误信息
     * @see
     * @since 1.0
     */
    public String checkLtHundred(String cellData, String labelName, String indexId) {
        // 判断是否数值小于等于100
        if (StringUtil.isNotBlank(cellData)){
            try {
                double num = Double.parseDouble(cellData.replace(",", ""));
                BigDecimal result = BigDecimal.valueOf(num);
                if (result.compareTo(BigDecimal.valueOf(100)) > 0){
                    return getMessage("ETR_E_0042", getMessage(labelName), indexId);
                }
            } catch (NumberFormatException e) {
                // 在校验大小时不要判断格式不正确，直接打印异常日志，正常返回即可
                logger.error(getMessage("ETR_E_0016", getMessage(labelName), indexId), e);
                return "";
            }
        }
        return "";
    }

    /**
     * check多对多指令字段用,错误消息拼接
     * 
     * @param errorMessage 原错误信息
     * @param addMessage 新增错误信息
     * @return 错误信息
     * @see
     * @since 1.0
     */
    public String StringAddByBr(String errorMessage, String addMessage) {
        // 错误信息拼接
        if (StringUtil.isNotBlank(errorMessage) && StringUtil.isNotBlank(addMessage)) {
            return errorMessage + "<br />" + addMessage;
        }
        else if (StringUtil.isNotBlank(errorMessage)) {
            return errorMessage;
        }
        else {
            return addMessage;
        }
    }
	
	    /***
     * 验证指令能否撤销(指令状态变更接口)
     * @param selectItem
     * @param entrustStatus  06 已关闭 07 已撤回
     * @return
     */
    public ValidMessage validEtrClose(List<? extends EtrEntrust> selectItem,String entrustStatus) {
        ValidMessage message = null;
        logger.info("调用交易系统能否撤销指令接口【{}】  接口URL："+ServiceApplication.EXCHANGE_SERVICE_NAME +
                "/joyin/instruction/cancel");
        for(EtrEntrust temp:selectItem) {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("user_id", LoginUserUtil.getUserName());
            paramMap.put("instruction_Id", temp.getEntrustId());
            paramMap.put("entrustStatus", entrustStatus);
            logger.info("请求报文【{}】","instruction_Id = "+ temp.getEntrustId());
            Map<String, Object> result = restTemplate.postForObject(ServiceApplication.EXCHANGE_SERVICE_NAME +
                            "/joyin/instruction/cancel?instruction_Id="+temp.getEntrustId()+"&entrustStatus="+entrustStatus,
                    paramMap, Map.class);
            logger.info("应答报文【{}】", "errorCode:"+(Integer)result.get("errorCode")+";errorMsg:"+(String)result.get("errorMsg"));
            if(result != null ) {
                Integer errorCode = (Integer)result.get("errorCode");
                if(errorCode == 4004 && StringUtil.equals(DictCodeList.委托状态_已撤回.getValue(),entrustStatus)) {//返回失败不可以撤销
                    message = new ValidMessage("指令有订单，不允许撤销！");
                    break;
                }
            }
        }
        return message;
    }

    // add by etliu 20210421 权益类资产相关代码合并 start

    /**
     * 检查投资指令开始日与投资指令结束日都必须为平台日，且投资指令开始日与结束日要相等
     * @param startDate 开始日
     * @param endDate 结束日
     * @param indexId 文件行号
     * @return 提示信息
     */
    public static String checkIsPlateDate(String startDate, String endDate, String indexId){

        // 估值日必须等于平台日
        String platedate = DateUtil.getSysDate();

        // modify by dubaojun 2020-07-06 缺陷SNSTZJY-124对应：输入的日期格式不合法时直接跳过  start
        try {
            if (DateUtil.compareDate(platedate,startDate) != 0
                    || DateUtil.compareDate(platedate,endDate) != 0){

                return "指令开始日与指令终止日应为【"+platedate+"】(行号为"+indexId+")";

            }else {

                if (DateUtil.compareDate(startDate,startDate) != 0){
                    return "指令开始日与指令终止日应为【"+platedate+"】(行号为"+indexId+")";
                }else {
                    return "";
                }

            }
        } catch (RuntimeException e) {
            return "";
        }
        // modify by dubaojun 2020-07-06 缺陷SNSTZJY-124对应：输入的日期格式不合法时直接跳过  end

    }


    public static String checkQ(String startDate, String endDate, String indexId){
        if (DateUtil.compareDate(endDate,startDate) != 0){
            return "起息日不等于指令开始日(行号为"+indexId+")";
        }
        return "";

    }

    public String checkDictItemVal(String pageId,String dictId,String dictItemVal,String labelName,String indexId) {
        SysPageDictlist param = new SysPageDictlist();
        param.setPageId(pageId);
        param.setSubPageId("999999");
        param.setDictId(dictId);
        param.setDictItemVal(dictItemVal);
        SysPageDictlist sysPageDictlist = this.sysPageDictlistMapper.selectByPrimaryKey(param);

        if(sysPageDictlist==null) {
            return getMessage("ETR_E_0017", getMessage(labelName), indexId);
        } else {
            return "";
        }
    }

    /**
     * check多对多指令字段用,数值大于0
     *
     * @param cellData cellData
     * @param labelName labelName
     * @param indexId indexId
     * @return 错误信息
     * @see
     * @since 1.0
     */
    public String checkIsGtZeroWithNum(String cellData, String labelName, String indexId) {
        // 判断是否数值大于0
        if (StringUtil.isNotBlank(cellData) && StringUtil.isBlank(this.checkIsNumber(cellData,labelName,indexId))){
            try {
                double num = Double.parseDouble(cellData.replace(",", ""));
                BigDecimal result = BigDecimal.valueOf(num);
                if (result.compareTo(BigDecimal.ZERO) <= 0) {
                    return getMessage("ETR_E_0019", getMessage(labelName), indexId);
                }
            } catch (NumberFormatException e) {
                return getMessage("ETR_E_0016", getMessage(labelName), indexId);
            }
        }
        return "";
    }

    public BigDecimal select券面面值(String finprodId, String sysDate) {
        Map<String, Object> params = new HashMap<>();
        params.put("finprodId", finprodId);
        params.put("sysDate", sysDate);
        return commonBusinessService.selectFaceValue(params);
    }

    public AjaxResult executeOccupyCreditLines(Object object, String status, String method) {
        AjaxResult ar = new AjaxResult();
//        if (StringUtil.equals(BussinessDictCodeList.监控授信开关_关.getValue(), CacheUtil.getSysParam(BussinessConstant.ISCREDIT))){
//            return ar;
//        }
//        List<CreditLinesInputDto> inputDtoList = new ArrayList<>();
//        if (object != null){
//            // 构建接口参数
//            generateInputDtoList(object,status,method,inputDtoList);
//            if (inputDtoList.size() > 0){
//                if ("check".equals(method)){
//                    ar = AllTradeCommonUtil.checkBeforeTradeComplete(inputDtoList);
//                }else {
//                    AllTradeCommonUtil.handleBeforeTradeComplete(inputDtoList,DictCodeListRsk.授信占用类别_预占.getValue());
//                }
//            }
//        }
        return ar;
    }

    /**
     * 生成调用接口list参数
     * @param object
     * @param status
     * @param method
     * @param inputDtoList
     */
    private void generateInputDtoList(Object object, String status, String method, List<CreditLinesInputDto> inputDtoList) {
        // 多对多指令 或者 多组合指令check
        if (object instanceof  List<?>){
            for (Object obj : (List<?>)object){
                generateInputDtoList(obj,status,method,inputDtoList);
            }
        }else if (object instanceof String && method.equals("close")){
            // 指令关闭
            CreditLinesInputDto creditLinesInputDto = new CreditLinesInputDto();
            creditLinesInputDto.setTradeId(String.valueOf(object));
            creditLinesInputDto.setTradeStatus(status);
            inputDtoList.add(creditLinesInputDto);
        }else if (object instanceof EtrEntrust && (method.equals("close") || method.equals("doBack"))){
            // 指令关闭
            CreditLinesInputDto creditLinesInputDto = new CreditLinesInputDto();
            creditLinesInputDto.setTradeId(((EtrEntrust) object).getEntrustId());
            creditLinesInputDto.setTradeStatus(status);
            inputDtoList.add(creditLinesInputDto);
        }else {
            CreditLinesInputDto creditLinesInputDto = generateInputDto(object,status,method);
            if (creditLinesInputDto != null){
                inputDtoList.add(creditLinesInputDto);
            }
        }
    }

    /**
     * 构建接口入参
     * @param object
     * @param status
     * @param method
     * @return
     */
    private CreditLinesInputDto generateInputDto(Object object, String status, String method){
        CreditLinesInputDto creditLinesInputDto = null;
        // 债券一级指令
        if (object instanceof EtrBondFirstLBiddingDto){
            EtrBondFirstLBiddingDto inputDto = (EtrBondFirstLBiddingDto) object;
            creditLinesInputDto = new CreditLinesInputDto();
            creditLinesInputDto.setCreditCallType(DictCodeListRsk.授信占用类别_预占.getValue());
            creditLinesInputDto.setTradeId(inputDto.getEntrustId());
            creditLinesInputDto.setFinprodId(inputDto.getFinprodId());
            creditLinesInputDto.setFinprodType2(DictCodeListExt2.金融产品类型_投管用_一级投标债券.getValue());
            creditLinesInputDto.setTradeDate(inputDto.getVdate());
            creditLinesInputDto.setTradeStatus(status);
            creditLinesInputDto.setPs(DictCodeList.交易方向_买入.getValue());
            if(StringUtil.isNotEmpty(inputDto.getEtrInvestPersonInfo())){
                creditLinesInputDto.setCounterId(inputDto.getEtrInvestPersonInfo().getCounterId());
            }
            creditLinesInputDto.setTradeAmt(inputDto.getEntrustNum());
            if (inputDto.getEntrustNum() != null){
                creditLinesInputDto.setShareAmt(inputDto.getEntrustNum().divide(new BigDecimal(100), RoundingMode.HALF_UP));
            }
        }
        // 债券二级指令
        if (object instanceof EtrInvestBondInfoDto){
            EtrInvestBondInfoDto inputDto = (EtrInvestBondInfoDto) object;
            if (StringUtil.equals(DictCodeList.交易方向_买入.getValue(),inputDto.getPs())){
                creditLinesInputDto = new CreditLinesInputDto();
                creditLinesInputDto.setCreditCallType(DictCodeListRsk.授信占用类别_预占.getValue());
                creditLinesInputDto.setTradeId(inputDto.getEntrustId());
                creditLinesInputDto.setFinprodId(inputDto.getFinprodId());
                creditLinesInputDto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_债券.getValue());
                creditLinesInputDto.setTradeDate(inputDto.getVdate());
                creditLinesInputDto.setTradeStatus(status);
                creditLinesInputDto.setPs(DictCodeList.交易方向_买入.getValue());
                if(StringUtil.isNotEmpty(inputDto.getEtrInvestPersonInfo())){
                    creditLinesInputDto.setCounterId(inputDto.getEtrInvestPersonInfo().getCounterId());
                }
                creditLinesInputDto.setTradeAmt(inputDto.getEntrustNum());
                if (inputDto.getEntrustNum() != null){
                    creditLinesInputDto.setShareAmt(inputDto.getEntrustNum().divide(new BigDecimal(100), RoundingMode.HALF_UP));
                }
                //最新面值
                BigDecimal newFaceValue = creditLinesDealsMapper.getFaceValue(creditLinesInputDto);
                if (newFaceValue == null){
                    newFaceValue = new BigDecimal(100);
                }
                if (creditLinesInputDto.getShareAmt() != null) {
                    creditLinesInputDto.setTradeAmt(newFaceValue.multiply(creditLinesInputDto.getShareAmt()));
                }else if (creditLinesInputDto.getTradeAmt() != null){
                    creditLinesInputDto.setTradeAmt(newFaceValue.multiply(ArithmeticUtil.divide(creditLinesInputDto.getTradeAmt(),100,2)));
                }
            }
        }
        // 可转债申购指令
        if (object instanceof EtrConBondDto){
            EtrConBondDto inputDto = (EtrConBondDto) object;
            if (StringUtil.equals(DictCodeList.交易方向_申购.getValue(),inputDto.getPs())){
                creditLinesInputDto = new CreditLinesInputDto();
                creditLinesInputDto.setCreditCallType(DictCodeListRsk.授信占用类别_预占.getValue());
                creditLinesInputDto.setTradeId(inputDto.getEntrustId());
                creditLinesInputDto.setFinprodId(inputDto.getFinprodId());
                creditLinesInputDto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_债券.getValue());
                creditLinesInputDto.setTradeDate(inputDto.getVdate());
                creditLinesInputDto.setTradeStatus(status);
                creditLinesInputDto.setPs(DictCodeList.交易方向_申购.getValue());
                if(StringUtil.isNotEmpty(inputDto.getEtrInvestPersonInfo())){
                    creditLinesInputDto.setCounterId(inputDto.getEtrInvestPersonInfo().getCounterId());
                }
                creditLinesInputDto.setTradeAmt(inputDto.getPurchBondTotal());
                if (inputDto.getPurchBondTotal() != null){
                    creditLinesInputDto.setShareAmt(inputDto.getPurchBondTotal().divide(new BigDecimal(100), RoundingMode.HALF_UP));
                }
                //最新面值
                BigDecimal newFaceValue = creditLinesDealsMapper.getFaceValue(creditLinesInputDto);
                if (newFaceValue == null){
                    newFaceValue = new BigDecimal(100);
                }
                if (creditLinesInputDto.getShareAmt() != null) {
                    creditLinesInputDto.setTradeAmt(newFaceValue.multiply(creditLinesInputDto.getShareAmt()));
                }else if (creditLinesInputDto.getTradeAmt() != null){
                    creditLinesInputDto.setTradeAmt(newFaceValue.multiply(ArithmeticUtil.divide(creditLinesInputDto.getTradeAmt(),100,2)));
                }
            }
        }
        //存款类资产
        if(object instanceof EtrBrowDepoInfoDto){
            EtrBrowDepoInfoDto inputDto = (EtrBrowDepoInfoDto) object;
            if (StringUtil.equals(DictCodeList.交易方向_买入.getValue(),inputDto.getPs())){
                creditLinesInputDto = new CreditLinesInputDto();
                creditLinesInputDto.setCreditCallType(DictCodeListRsk.授信占用类别_预占.getValue());
                creditLinesInputDto.setTradeId(inputDto.getEntrustId());
                creditLinesInputDto.setFinprodId(inputDto.getFinprodId());
                creditLinesInputDto.setTradeDate(inputDto.getVdate());
                creditLinesInputDto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_存款.getValue());
                creditLinesInputDto.setTradeStatus(status);
                creditLinesInputDto.setPs(DictCodeList.交易方向_买入.getValue());
                if(StringUtil.isNotEmpty(inputDto.getBussEtrInvestPersonInfoDto())){
                    creditLinesInputDto.setCounterId(inputDto.getBussEtrInvestPersonInfoDto().getCounterId());
                }
                creditLinesInputDto.setTradeAmt(inputDto.getEntrustAmt());
                creditLinesInputDto.setShareAmt(inputDto.getEntrustAmt());
            }
        }
        //项目类资产
        if(object instanceof EtrProjectDto){
            EtrProjectDto inputDto = (EtrProjectDto) object;
            if (StringUtil.equals(DictCodeList.交易方向_买入.getValue(),inputDto.getPs())){
                creditLinesInputDto = new CreditLinesInputDto();
                creditLinesInputDto.setCreditCallType(DictCodeListRsk.授信占用类别_预占.getValue());
                creditLinesInputDto.setTradeId(inputDto.getEntrustId());
                creditLinesInputDto.setFinprodId(inputDto.getFinprodId());
                creditLinesInputDto.setTradeDate(inputDto.getVdate());
                creditLinesInputDto.setTradeStatus(status);
                creditLinesInputDto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
                creditLinesInputDto.setPs(DictCodeList.交易方向_买入.getValue());
                if(StringUtil.isNotEmpty(inputDto.getEtrInvestPersonInfo())){
                    creditLinesInputDto.setCounterId(inputDto.getEtrInvestPersonInfo().getCounterId());
                }
                creditLinesInputDto.setTradeAmt(inputDto.getEntrustAmt());
                creditLinesInputDto.setShareAmt(inputDto.getEntrustAmt());
            }
        }
        // add by etliu 20210421 权益类资产相关代码合并 start
        //权益类资产
        if(object instanceof EtrProjectNetDto){
            EtrProjectNetDto inputDto = (EtrProjectNetDto) object;
            if (StringUtil.equals(DictCodeList.交易方向_买入.getValue(),inputDto.getPs())
                    ||StringUtil.equals(DictCodeList.交易方向_申购.getValue(),inputDto.getPs())){
                creditLinesInputDto = new CreditLinesInputDto();
                creditLinesInputDto.setCreditCallType(DictCodeListRsk.授信占用类别_预占.getValue());
                creditLinesInputDto.setTradeId(inputDto.getEntrustId());
                creditLinesInputDto.setFinprodId(inputDto.getFinprodId());
                creditLinesInputDto.setTradeDate(inputDto.getVdate());
                creditLinesInputDto.setTradeStatus(status);
                creditLinesInputDto.setPs(inputDto.getPs());
                creditLinesInputDto.setFinprodType2(DictCodeListExt3.金融产品类型_投管用_权益类资产.getValue());
                if(StringUtil.isNotEmpty(inputDto.getEtrInvestPersonInfo())){
                    creditLinesInputDto.setCounterId(inputDto.getEtrInvestPersonInfo().getCounterId());
                }
                //最新净值
                BigDecimal lastPrice = creditLinesDealsMapper.getLastPrice(inputDto.getFinprodId(),iPlatformDate.getSysDate());
                creditLinesInputDto.setTradeAmt(inputDto.getEntrustAmt());
                if (lastPrice == null){
                    lastPrice = BigDecimal.ONE;
                }
                creditLinesInputDto.setNetPrice(lastPrice);
                creditLinesInputDto.setShareAmt(ArithmeticUtil.divide(inputDto.getEntrustAmt(),lastPrice,2));
            }
        }
        // add by etliu 20210421 权益类资产相关代码合并 end
        //计划类货币型
        if(object instanceof EtrPlanAssetCDto){
            EtrPlanAssetCDto inputDto = (EtrPlanAssetCDto) object;
            if (StringUtil.equals(DictCodeList.交易方向_买入.getValue(),inputDto.getPs())
            ||StringUtil.equals(DictCodeList.交易方向_申购.getValue(),inputDto.getPs())){
                creditLinesInputDto = new CreditLinesInputDto();
                creditLinesInputDto.setCreditCallType(DictCodeListRsk.授信占用类别_预占.getValue());
                creditLinesInputDto.setTradeId(inputDto.getEntrustId());
                creditLinesInputDto.setFinprodId(inputDto.getFinprodId());
                creditLinesInputDto.setTradeDate(inputDto.getVdate());
                creditLinesInputDto.setTradeStatus(status);
                creditLinesInputDto.setPs(inputDto.getPs());
                creditLinesInputDto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
                if(StringUtil.isNotEmpty(inputDto.getEtrInvestPersonInfo())){
                    creditLinesInputDto.setCounterId(inputDto.getEtrInvestPersonInfo().getCounterId());
                }
                //最新净值
                BigDecimal lastPrice = creditLinesDealsMapper.getLastPrice(inputDto.getFinprodId(),iPlatformDate.getSysDate());
                creditLinesInputDto.setTradeAmt(inputDto.getEntrustAmt());
                if (lastPrice == null){
                    lastPrice = BigDecimal.ONE;
                }
                creditLinesInputDto.setNetPrice(lastPrice);
                creditLinesInputDto.setShareAmt(ArithmeticUtil.divide(inputDto.getEntrustAmt(),lastPrice,2));
            }
        }
        //计划类净值型
        if(object instanceof EtrPlanAssetNDto){
            EtrPlanAssetNDto inputDto = (EtrPlanAssetNDto) object;
            if (StringUtil.equals(DictCodeList.交易方向_买入.getValue(),inputDto.getPs())
                ||StringUtil.equals(DictCodeList.交易方向_申购.getValue(),inputDto.getPs())){
                creditLinesInputDto = new CreditLinesInputDto();
                creditLinesInputDto.setCreditCallType(DictCodeListRsk.授信占用类别_预占.getValue());
                creditLinesInputDto.setTradeId(inputDto.getEntrustId());
                creditLinesInputDto.setFinprodId(inputDto.getFinprodId());
                creditLinesInputDto.setTradeDate(inputDto.getVdate());
                creditLinesInputDto.setTradeStatus(status);
                creditLinesInputDto.setPs(inputDto.getPs());
                creditLinesInputDto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
                if(StringUtil.isNotEmpty(inputDto.getEtrInvestPersonInfo())){
                    creditLinesInputDto.setCounterId(inputDto.getEtrInvestPersonInfo().getCounterId());
                }
                //最新净值
                BigDecimal lastPrice = creditLinesDealsMapper.getLastPrice(inputDto.getFinprodId(),iPlatformDate.getSysDate());
                creditLinesInputDto.setTradeAmt(inputDto.getEntrustAmt());
                if (lastPrice == null){
                    lastPrice = BigDecimal.ONE;
                }
                creditLinesInputDto.setNetPrice(lastPrice);
                creditLinesInputDto.setShareAmt(ArithmeticUtil.divide(inputDto.getEntrustAmt(),lastPrice,2));
            }
        }
        //计划类利率型
        if(object instanceof EtrPlanAssetRDto){
            EtrPlanAssetRDto inputDto = (EtrPlanAssetRDto) object;
            if (StringUtil.equals(DictCodeList.交易方向_买入.getValue(),inputDto.getPs())){
                creditLinesInputDto = new CreditLinesInputDto();
                creditLinesInputDto.setCreditCallType(DictCodeListRsk.授信占用类别_预占.getValue());
                creditLinesInputDto.setTradeId(inputDto.getEntrustId());
                creditLinesInputDto.setFinprodId(inputDto.getFinprodId());
                creditLinesInputDto.setTradeDate(inputDto.getVdate());
                creditLinesInputDto.setTradeStatus(status);
                creditLinesInputDto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
                creditLinesInputDto.setPs(DictCodeList.交易方向_买入.getValue());
                if(StringUtil.isNotEmpty(inputDto.getEtrInvestPersonInfo())){
                    creditLinesInputDto.setCounterId(inputDto.getEtrInvestPersonInfo().getCounterId());
                }
                creditLinesInputDto.setTradeAmt(inputDto.getEntrustAmt());
                creditLinesInputDto.setShareAmt(inputDto.getEntrustAmt());
            }
        }
        //基金类资产
        if(object instanceof  EtrFundDto){
            EtrFundDto inputDto = (EtrFundDto) object;
            if (StringUtil.equals(DictCodeListExt2.交易方向_转换申请.getValue(),inputDto.getPs())
                || StringUtil.equals(DictCodeList.交易方向_申购.getValue(),inputDto.getPs())){
                creditLinesInputDto = new CreditLinesInputDto();
                creditLinesInputDto.setCreditCallType(DictCodeListRsk.授信占用类别_预占.getValue());
                creditLinesInputDto.setTradeId(inputDto.getEntrustId());
                creditLinesInputDto.setFinprodId(inputDto.getFinprodId());
                creditLinesInputDto.setTradeDate(inputDto.getVdate());
                creditLinesInputDto.setTradeStatus(status);
                creditLinesInputDto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_基金.getValue());
                creditLinesInputDto.setPs(DictCodeList.交易方向_买入.getValue());
                if(StringUtil.isNotEmpty(inputDto.getBussEtrInvestPersonInfoDto())){
                    creditLinesInputDto.setCounterId(inputDto.getBussEtrInvestPersonInfoDto().getCounterId());
                }
                creditLinesInputDto.setTradeAmt(inputDto.getEntrustAmt());
                creditLinesInputDto.setShareAmt(inputDto.getEntrustNum());

                //最新净值
                BigDecimal lastPrice = creditLinesDealsMapper.getLastPrice(inputDto.getFinprodId(),iPlatformDate.getSysDate());
                if (lastPrice == null){
                    lastPrice = BigDecimal.ONE;
                }
                creditLinesInputDto.setNetPrice(lastPrice);
                creditLinesInputDto.setShareAmt(ArithmeticUtil.divide(creditLinesInputDto.getTradeAmt(),lastPrice,2));

                if (StringUtil.equals(DictCodeListExt2.交易方向_转换申请.getValue(),inputDto.getPs())){
                    creditLinesInputDto.setFinprodId(DictCodeListExt1.金融产品类型_投管用_基金.getValue().concat("_"+inputDto.getChangeToFinprodMarketId()));
                    creditLinesInputDto.setNetPrice(lastPrice);
                    creditLinesInputDto.setTradeAmt(ArithmeticUtil.multiply(inputDto.getEntrustNum(),lastPrice));
                    creditLinesInputDto.setShareAmt(inputDto.getEntrustNum());
                }
            }
        }
        //债券借贷
        if(object instanceof EtrSecLendDto){
            EtrSecLendDto inputDto = (EtrSecLendDto) object;
            if (StringUtil.equals(DictCodeList.回购拆借方向_融出.getValue(),inputDto.getBusiPs())){
                creditLinesInputDto = new CreditLinesInputDto();
                creditLinesInputDto.setCreditCallType(DictCodeListRsk.授信占用类别_预占.getValue());
                creditLinesInputDto.setTradeId(inputDto.getEntrustId());
                creditLinesInputDto.setFinprodId("");
                creditLinesInputDto.setTradeDate(inputDto.getVdate());
                creditLinesInputDto.setTradeStatus(status);
                creditLinesInputDto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_债券借贷.getValue());
                creditLinesInputDto.setTgBusiType(DictCodeListExt2.平安资产类型_债券借贷.getValue());
                creditLinesInputDto.setPsPayment(inputDto.getBusiPs());
                creditLinesInputDto.setPs(DictCodeList.交易方向_买入.getValue());
                if(StringUtil.isNotEmpty(inputDto.getEtrInvestPersonInfo())){
                    creditLinesInputDto.setCounterId(inputDto.getEtrInvestPersonInfo().getCounterId());
                }
                creditLinesInputDto.setTradeAmt(ArithmeticUtil.multiply(inputDto.getTotalAmt(),new BigDecimal(10000)));
                if (inputDto.getTotalAmt() != null){
                    creditLinesInputDto.setShareAmt(inputDto.getTotalAmt().multiply(new BigDecimal(100)));
                }
                creditLinesInputDto.setShareAmt(ArithmeticUtil.multiply(inputDto.getTotalAmt(),new BigDecimal(10000)));
                //最新面值
                BigDecimal newFaceValue = creditLinesDealsMapper.getFaceValue(creditLinesInputDto);
                if (newFaceValue == null){
                    newFaceValue = new BigDecimal(100);
                }
                if (creditLinesInputDto.getShareAmt() != null) {
                    creditLinesInputDto.setTradeAmt(newFaceValue.multiply(ArithmeticUtil.divide(creditLinesInputDto.getShareAmt(),100,2)));
                }else if (creditLinesInputDto.getTradeAmt() != null){
                    creditLinesInputDto.setTradeAmt(newFaceValue.multiply(ArithmeticUtil.divide(creditLinesInputDto.getTradeAmt(),100,2)));
                }
            }
        }
        // 拆借
        if (object instanceof EtrLendSpecInfoDto){
            EtrLendSpecInfoDto inputDto = (EtrLendSpecInfoDto) object;
            if (StringUtil.equals(inputDto.getPs(), DictCodeList.回购拆借方向_拆出.getValue())){
                creditLinesInputDto = new CreditLinesInputDto();
                creditLinesInputDto.setTradeId(inputDto.getEntrustId());
                creditLinesInputDto.setFinprodId(inputDto.getFinprodId());
                creditLinesInputDto.setTradeDate(inputDto.getVdate());
                creditLinesInputDto.setTradeStatus(status);
                creditLinesInputDto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_拆出.getValue());
                creditLinesInputDto.setTgBusiType(DictCodeList.平安资产类型_拆借.getValue());
                creditLinesInputDto.setPsPayment(inputDto.getPs());
                creditLinesInputDto.setPs(DictCodeList.交易方向_买入.getValue());
                if(StringUtil.isNotEmpty(inputDto.getEtrInvestPersonInfo())){
                    creditLinesInputDto.setCounterId(inputDto.getEtrInvestPersonInfo().getCounterId());
                }
                creditLinesInputDto.setTradeAmt(inputDto.getEntrustAmt());
                creditLinesInputDto.setShareAmt(inputDto.getEntrustAmt());
            }
        }
        //质押式回购
        if(object instanceof EtrRepoDto){
            EtrRepoDto inputDto = (EtrRepoDto) object;
            if (StringUtil.equals(DictCodeList.回购拆借方向_逆回购.getValue(),inputDto.getPs())){
                creditLinesInputDto = new CreditLinesInputDto();
                creditLinesInputDto.setCreditCallType(DictCodeListRsk.授信占用类别_预占.getValue());
                creditLinesInputDto.setTradeId(inputDto.getEntrustId());
                creditLinesInputDto.setFinprodId(inputDto.getFinprodId());
                creditLinesInputDto.setTradeDate(inputDto.getVdate());
                creditLinesInputDto.setTradeStatus(status);
                creditLinesInputDto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue());
                creditLinesInputDto.setTgBusiType(DictCodeList.平安资产类型_质押式回购.getValue());
                creditLinesInputDto.setPsPayment(inputDto.getAssetType1());
                creditLinesInputDto.setSaleCode(inputDto.getPs());
                creditLinesInputDto.setPs(DictCodeList.交易方向_买入.getValue());
                if(StringUtil.isNotEmpty(inputDto.getEtrInvestPersonInfo())){
                    creditLinesInputDto.setCounterId(inputDto.getEtrInvestPersonInfo().getCounterId());
                }
                creditLinesInputDto.setTradeAmt(inputDto.getEntrustAmt());
                creditLinesInputDto.setShareAmt(inputDto.getEntrustAmt());
            }
        }
        // 买断式回购
        if(object instanceof EtrBoutRepoDto){
            EtrBoutRepoDto inputDto = (EtrBoutRepoDto) object;
            if (StringUtil.equals(DictCodeList.回购拆借方向_逆回购.getValue(),inputDto.getPs())){
                creditLinesInputDto = new CreditLinesInputDto();
                creditLinesInputDto.setCreditCallType(DictCodeListRsk.授信占用类别_预占.getValue());
                creditLinesInputDto.setTradeId(inputDto.getEntrustId());
                creditLinesInputDto.setFinprodId("");
                creditLinesInputDto.setTradeDate(inputDto.getVdate());
                creditLinesInputDto.setTradeStatus(status);
                creditLinesInputDto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue());
                creditLinesInputDto.setTgBusiType(DictCodeList.平安资产类型_买断式回购.getValue());
                creditLinesInputDto.setPsPayment(inputDto.getPs());
                creditLinesInputDto.setPs(DictCodeList.交易方向_买入.getValue());
                if(StringUtil.isNotEmpty(inputDto.getEtrInvestPersonInfo())){
                    creditLinesInputDto.setCounterId(inputDto.getEtrInvestPersonInfo().getCounterId());
                }
                creditLinesInputDto.setTradeAmt(inputDto.getEntrustAmt());
                if (inputDto.getEntrustAmt() != null){
                    creditLinesInputDto.setShareAmt(inputDto.getEntrustAmt().divide(new BigDecimal(100), RoundingMode.HALF_UP));
                }
                //最新面值
                BigDecimal newFaceValue = creditLinesDealsMapper.getFaceValue(creditLinesInputDto);
                if (newFaceValue == null){
                    newFaceValue = new BigDecimal(100);
                }
                if (creditLinesInputDto.getShareAmt() != null) {
                    creditLinesInputDto.setTradeAmt(newFaceValue.multiply(creditLinesInputDto.getShareAmt()));
                }else if (creditLinesInputDto.getTradeAmt() != null){
                    creditLinesInputDto.setTradeAmt(newFaceValue.multiply(ArithmeticUtil.divide(creditLinesInputDto.getTradeAmt(),100,2)));
                }
            }
        }
        // 交易所指令
        if (object instanceof EtrExchangeTrade001Dto){
            EtrExchangeTrade001Dto inputDto = (EtrExchangeTrade001Dto) object;
            // 债券指令
            if (inputDto.getFinprodType2().equals(DictCodeListExt1.金融产品类型_投管用_债券.getValue())
                && (StringUtil.equals(DictCodeList.交易方向_买入.getValue(),inputDto.getPs())
                || StringUtil.equals(DictCodeList.交易方向_申购.getValue(),inputDto.getPs())
                || StringUtil.equals(DictCodeListExt3.交易方向_配债.getValue(),inputDto.getPs()))){
                creditLinesInputDto = new CreditLinesInputDto();
                creditLinesInputDto.setCreditCallType(DictCodeListRsk.授信占用类别_预占.getValue());
                creditLinesInputDto.setTradeId(inputDto.getEntrustId());
                creditLinesInputDto.setFinprodId(inputDto.getFinprodId());
                creditLinesInputDto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_债券.getValue());
                creditLinesInputDto.setTradeDate(inputDto.getVdate());
                creditLinesInputDto.setTradeStatus(status);
                creditLinesInputDto.setPs(DictCodeList.交易方向_买入.getValue());
                creditLinesInputDto.setCounterId(inputDto.getCounterId());
                creditLinesInputDto.setShareAmt(inputDto.getEntrustNum());

                //最新面值
                BigDecimal newFaceValue = creditLinesDealsMapper.getFaceValue(creditLinesInputDto);
                if (newFaceValue == null){
                    newFaceValue = new BigDecimal(100);
                }
                if (creditLinesInputDto.getShareAmt() != null) {
                    creditLinesInputDto.setTradeAmt(newFaceValue.multiply(creditLinesInputDto.getShareAmt()));
                }else {
                    creditLinesInputDto.setTradeAmt(inputDto.getEntrustAmt());
                }

            }
            // 债券指令-债转股
            if (inputDto.getFinprodType2().equals(DictCodeListExt1.金融产品类型_投管用_债券.getValue())
                && StringUtil.equals(DictCodeListExt1.交易方向_债转股.getValue(),inputDto.getPs())){
                FinProductConvertible finProductConvertible = finProductConvertibleMapper.selectByPrimaryKey(inputDto.getFinprodId());
                if (finProductConvertible != null){
                    creditLinesInputDto = new CreditLinesInputDto();
                    creditLinesInputDto.setCreditCallType(DictCodeListRsk.授信占用类别_预占.getValue());
                    creditLinesInputDto.setTradeId(inputDto.getEntrustId());
                    creditLinesInputDto.setFinprodId(finProductConvertible.getConFinprodId());
                    creditLinesInputDto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_股票.getValue());
                    creditLinesInputDto.setTradeDate(inputDto.getVdate());
                    creditLinesInputDto.setTradeStatus(status);
                    creditLinesInputDto.setPs(DictCodeList.交易方向_买入.getValue());
                    creditLinesInputDto.setCounterId(inputDto.getCounterId());
                    creditLinesInputDto.setTradeAmt(inputDto.getEntrustAmt());
                    creditLinesInputDto.setShareAmt(inputDto.getEntrustNum());
                }
            }
            // 股票指令
            if (inputDto.getFinprodType2().equals(DictCodeListExt1.金融产品类型_投管用_股票.getValue())
                && (StringUtil.equals(DictCodeList.交易方向_买入.getValue(),inputDto.getPs())
                    || StringUtil.equals(DictCodeList.交易方向_申购.getValue(),inputDto.getPs())
                    || StringUtil.equals(DictCodeListExt2.交易方向_配股.getValue(),inputDto.getPs()))){
                creditLinesInputDto = new CreditLinesInputDto();
                creditLinesInputDto.setCreditCallType(DictCodeListRsk.授信占用类别_预占.getValue());
                creditLinesInputDto.setTradeId(inputDto.getEntrustId());
                creditLinesInputDto.setFinprodId(inputDto.getFinprodId());
                creditLinesInputDto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_股票.getValue());
                creditLinesInputDto.setTradeDate(inputDto.getVdate());
                creditLinesInputDto.setTradeStatus(status);
                creditLinesInputDto.setPs(DictCodeList.交易方向_买入.getValue());
                creditLinesInputDto.setCounterId(inputDto.getCounterId());
                creditLinesInputDto.setTradeAmt(inputDto.getEntrustAmt());
                creditLinesInputDto.setShareAmt(inputDto.getEntrustNum());
            }
            // 基金指令
            if (inputDto.getFinprodType2().equals(DictCodeListExt1.金融产品类型_投管用_基金.getValue())
                && (StringUtil.equals(DictCodeList.交易方向_买入.getValue(),inputDto.getPs())
                    || StringUtil.equals(DictCodeList.交易方向_申购.getValue(),inputDto.getPs()))){
                creditLinesInputDto = new CreditLinesInputDto();
                creditLinesInputDto.setCreditCallType(DictCodeListRsk.授信占用类别_预占.getValue());
                creditLinesInputDto.setTradeId(inputDto.getEntrustId());
                creditLinesInputDto.setFinprodId(inputDto.getFinprodId());
                creditLinesInputDto.setTradeDate(inputDto.getVdate());
                creditLinesInputDto.setTradeStatus(status);
                creditLinesInputDto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_基金.getValue());
                creditLinesInputDto.setPs(DictCodeList.交易方向_买入.getValue());
                creditLinesInputDto.setCounterId(inputDto.getCounterId());
                creditLinesInputDto.setTradeAmt(inputDto.getEntrustAmt());
                creditLinesInputDto.setShareAmt(inputDto.getEntrustNum());
                //最新净值
                BigDecimal lastPrice = creditLinesDealsMapper.getLastPrice(inputDto.getFinprodId(),iPlatformDate.getSysDate());
                if (lastPrice == null){
                    lastPrice = BigDecimal.ONE;
                }
                creditLinesInputDto.setNetPrice(lastPrice);
            }

        }
        // 交易所协议回购指令（逆回购、正向）
        if (object instanceof EtrAgreRepTradeDto){
            EtrAgreRepTradeDto inputDto = (EtrAgreRepTradeDto) object;
            // 质押式回购正回购不占授信
            if (StringUtil.equals(DictCodeList.回购拆借方向_正回购.getValue(),inputDto.getRepPs())){
                return null;
            }
            // 逆回购首期
            if (StringUtil.equals(inputDto.getPs(),DictCodeListExt3.交易方向_逆回购.getValue())){
                creditLinesInputDto = new CreditLinesInputDto();
                creditLinesInputDto.setCreditCallType(DictCodeListRsk.授信占用类别_预占.getValue());
                creditLinesInputDto.setTradeId(inputDto.getEntrustId());
                creditLinesInputDto.setTradeDate(DateUtil.parse(inputDto.getVdate()));
                creditLinesInputDto.setTradeStatus(status);
                creditLinesInputDto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue());
                creditLinesInputDto.setTgBusiType(DictCodeList.平安资产类型_质押式回购.getValue());
                creditLinesInputDto.setPsPayment(DictCodeList.回购类型_质押式协议回购.getValue());
                creditLinesInputDto.setSaleCode(inputDto.getPs());
                creditLinesInputDto.setPs(DictCodeList.交易方向_买入.getValue());
                creditLinesInputDto.setCounterId(inputDto.getCounterId());
                creditLinesInputDto.setTradeAmt(inputDto.getTradeAmt());
                creditLinesInputDto.setShareAmt(inputDto.getTradeAmt());
            }
            // 逆回购，到期续做 -> 新的首期
            if (StringUtil.equals(inputDto.getPs(),DictCodeListExt3.交易方向_到期续做.getValue())
                && StringUtil.equals(inputDto.getTradeMarket(),DictCodeListExt1.交易场所_上海证券交易所.getValue())){
                creditLinesInputDto = new CreditLinesInputDto();
                creditLinesInputDto.setCreditCallType(DictCodeListRsk.授信占用类别_预占.getValue());
                creditLinesInputDto.setTradeId(inputDto.getEntrustId());
                creditLinesInputDto.setTradeDate(DateUtil.parse(inputDto.getVdate()));
                creditLinesInputDto.setTradeStatus(status);
                creditLinesInputDto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue());
                creditLinesInputDto.setTgBusiType(DictCodeList.平安资产类型_质押式回购.getValue());
                creditLinesInputDto.setPsPayment(DictCodeList.回购类型_质押式协议回购.getValue());
                creditLinesInputDto.setSaleCode(inputDto.getRepPs());
                creditLinesInputDto.setPs(DictCodeList.交易方向_买入.getValue());
                creditLinesInputDto.setCounterId(inputDto.getCounterId());
                creditLinesInputDto.setTradeAmt(inputDto.getTradeAmt());
                creditLinesInputDto.setShareAmt(inputDto.getTradeAmt());
            }

        }
        // 校验是否需要调用接口
        boolean isCallCreditInterf = checkCallCreditInterf(creditLinesInputDto,status,method);
        if (isCallCreditInterf){
            return creditLinesInputDto;
        }
        return null;
    }

    private boolean checkCallCreditInterf(CreditLinesInputDto creditLinesInputDto, String status, String method) {
        if (creditLinesInputDto == null){
            return false;
        }
        // 买入、申购，提交到流程中，占用授信
        if ("doInsert".equals(method) || "doUpdate".equals(method)){
            if (DictCodeList.交易方向_买入.getValue().equals(creditLinesInputDto.getPs())
                    || DictCodeList.交易方向_申购.getValue().equals(creditLinesInputDto.getPs())
                    || DictCodeListExt2.交易方向_认购.getValue().equals(creditLinesInputDto.getPs())){
                return true;
            }
        }
        if ("doComplete".equals(method)){
            // 撤销生效时，下列交易方向调用
            if (DictCodeListExt1.交易状态_撤销.getValue().equals(status)){
                if (DictCodeList.交易方向_买入.getValue().equals(creditLinesInputDto.getPs())
                        || DictCodeList.交易方向_卖出.getValue().equals(creditLinesInputDto.getPs())
                        || DictCodeList.交易方向_申购.getValue().equals(creditLinesInputDto.getPs())
                        || DictCodeListExt2.交易方向_认购.getValue().equals(creditLinesInputDto.getPs())
                        || DictCodeList.交易方向_赎回确认.getValue().equals(creditLinesInputDto.getPs())){
                    return true;
                }
            }
        }
        // 买入、申购，退回时，释放授信
        if ("doBack".equals(method)){
            if (DictCodeList.交易方向_买入.getValue().equals(creditLinesInputDto.getPs())
                    || DictCodeList.交易方向_申购.getValue().equals(creditLinesInputDto.getPs())
                    || DictCodeListExt2.交易方向_认购.getValue().equals(creditLinesInputDto.getPs())){
                return true;
            }
        }
        if ("check".equals(method)){
            if (DictCodeList.交易方向_买入.getValue().equals(creditLinesInputDto.getPs())
                    || DictCodeList.交易方向_申购.getValue().equals(creditLinesInputDto.getPs())
                    || DictCodeListExt2.交易方向_认购.getValue().equals(creditLinesInputDto.getPs())){
                return true;
            }
        }
        return false;
    }
}
