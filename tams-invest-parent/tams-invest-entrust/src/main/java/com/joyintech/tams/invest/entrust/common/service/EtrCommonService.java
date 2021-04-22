package com.joyintech.tams.invest.entrust.common.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
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

    @Autowired(required = false)
    private BaseEtrCashPositionCommService baseEtrCashPositionCommService;

    @Autowired(required = false)
    private FinProductAddMapper finProductAddMapper;
    
    @Autowired
    private FinProductMapper finProductMapper;

    /**
     * 日期计算
     */
    @Autowired
    private DateCalService dateCalService;

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

            BigDecimal cashT3 = baseEtrCashPositionCommService.qryCommandAvailableCash(dto);
            result.put("cashT3", cashT3); // 托管户可用
            cashT = ArithmeticUtil.doAdd(cashT, cashT3 == null ? BigDecimal.ZERO : cashT3);

            acctTypeList.clear();
            acctTypeList.add(DictCodeListExt1.资金账户类型_券商资金账户.getValue());
            BigDecimal cashT4 = baseEtrCashPositionCommService.qryCommandAvailableCash(dto);
            result.put("cashT4", cashT4);// 交易所资金账户可用
            cashT = ArithmeticUtil.doAdd(cashT, cashT4 == null ? BigDecimal.ZERO : cashT4);

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
    // add by etliu 20210421 权益类资产相关代码合并 end
}
