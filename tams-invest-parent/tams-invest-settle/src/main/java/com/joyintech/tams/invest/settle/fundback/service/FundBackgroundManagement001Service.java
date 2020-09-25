package com.joyintech.tams.invest.settle.fundback.service;

import java.util.HashMap;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.settle.fundback.dto.FundBackPrintDataDto;
import com.joyintech.tams.invest.settle.fundback.dto.FundBackgroundManagement001Dto;

/**
 * @program: tams-invest-parent
 * @description: 资金后台管理用service
 * @author: 强成西
 * @create: 2018-11-15 14:37
 **/
@Service
public class FundBackgroundManagement001Service extends CommonExportService<FundBackgroundManagement001Dto> {

    public Page<SelectOptionDto> findPortfolioId() {
        return restTemplate.postForPage(ServiceApplication.SETTLE_SERVICE_NAME + "/FundBackgroundManagement/findPortfolioId",
            RequestUtil.getPageParam(), SelectOptionDto.class);
    }

    public Page<SelectOptionDto> findBizCode() {
        return restTemplate.postForPage(ServiceApplication.SETTLE_SERVICE_NAME + "/FundBackgroundManagement/findBizCode",
            RequestUtil.getPageParam(), SelectOptionDto.class);
    }

    public Page<FundBackgroundManagement001Dto> findPage(Map<String, Object> params) {
        params.put("userName", SecurityUtil.getLoginUser().getUserName());
        params.put("TRADE_OPERATOR", CacheUtil.getSysParam(BussinessConstant.交易经办));
        return restTemplate.postForPage(ServiceApplication.SETTLE_SERVICE_NAME + "/FundBackgroundManagement/findPage", params,
            FundBackgroundManagement001Dto.class);
    }

    public String printPayOrders(Model model) {
        String paBussType = RequestUtil.getParam("paBussType");
        Map<String, Object> params = RequestUtil.getPageParam();
        params.put(BussinessConstant.NOBRANCHNOVALUE, Long.valueOf(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
        params.put(BussinessConstant.交易经办, CacheUtil.getSysParam(BussinessConstant.交易经办));
        params.put(BussinessConstant.交易复核, CacheUtil.getSysParam(BussinessConstant.交易复核));
        params.put(BussinessConstant.总集经办, CacheUtil.getSysParam(BussinessConstant.总集经办));
        params.put(BussinessConstant.总集复核, CacheUtil.getSysParam(BussinessConstant.总集复核));
        FundBackPrintDataDto fundBackPrintDataDto = restTemplate.postForObject(
            ServiceApplication.SETTLE_SERVICE_NAME + "/FundBackgroundManagement/findPrintData", params,
            FundBackPrintDataDto.class);
        model.addAttribute(GlobalConstant.ENTITY, fundBackPrintDataDto);

        if (DictCodeListExt1.平安业务类型_存款类资产买入.getValue().equals(paBussType)) {
            model.addAttribute("YN_Y", DictCodeList.是否_是.getValue());
            model.addAttribute("YN_N", DictCodeList.是否_否.getValue());
            model.addAttribute("D02", DictCodeList.存款类资产类型_存放同业.getValue());
            model.addAttribute("D01", DictCodeList.存款类资产类型_同业借款.getValue());
            return "invest/settle/fundback/DepoPrint";
        }
        else if (DictCodeListExt1.平安业务类型_普通拆入.getValue().equals(paBussType)
                 || DictCodeListExt1.平安业务类型_普通拆出.getValue().equals(paBussType)
                 || DictCodeListExt1.平安业务类型_特拆拆入.getValue().equals(paBussType)
                 || DictCodeListExt1.平安业务类型_特拆拆入到期.getValue().equals(paBussType)
                 || DictCodeListExt1.平安业务类型_普通拆入到期.getValue().equals(paBussType)) {
            model.addAttribute("paBussType", paBussType);
            model.addAttribute("PA_BUSINESS_TYPE15", DictCodeListExt1.平安业务类型_特拆拆入.getValue());
            model.addAttribute("PA_BUSINESS_TYPE43", DictCodeListExt1.平安业务类型_特拆拆入到期.getValue());
            model.addAttribute("PA_BUSINESS_TYPE16", DictCodeListExt1.平安业务类型_普通拆出.getValue());
            model.addAttribute("PA_BUSINESS_TYPE42", DictCodeListExt1.平安业务类型_普通拆入到期.getValue());
            return "invest/settle/fundback/LendPrint";
        }
        else if (DictCodeListExt1.平安业务类型_项目类资产买入.getValue().equals(paBussType)) {
            model.addAttribute("paBussType", paBussType);
            model.addAttribute("P01", DictCodeList.项目类一级分类_非标债权.getValue());
            return "invest/settle/fundback/ProjPrint";
        }
        else if (DictCodeListExt1.平安业务类型_计划类资产_利率型_申购.getValue().equals(paBussType)) {
            model.addAttribute("paBussType", paBussType);
            return "invest/settle/fundback/PlanIntPrint";
        }
        else if (DictCodeListExt1.平安业务类型_计划类资产_净值型_申购申请.getValue().equals(paBussType)
                 || DictCodeListExt1.平安业务类型_非货币基金申购申请.getValue().equals(paBussType)
                 || DictCodeListExt1.平安业务类型_货币基金申购申请.getValue().equals(paBussType)) {
            model.addAttribute("paBussType", paBussType);
            model.addAttribute("PA_BUSINESS_TYPE25", DictCodeListExt1.平安业务类型_非货币基金申购申请.getValue());
            model.addAttribute("PA_BUSINESS_TYPE21", DictCodeListExt1.平安业务类型_货币基金申购申请.getValue());
            model.addAttribute("PA_BUSINESS_TYPE29", DictCodeListExt1.平安业务类型_计划类资产_净值型_申购申请.getValue());
            return "invest/settle/fundback/FundPrint";
        }
        else if (DictCodeListExt1.平安业务类型_自定义现金流支付.getValue().equals(paBussType)
                || DictCodeListExt1.平安业务类型_产品费用支付.getValue().equals(paBussType)) {
            return "invest/settle/fundback/CashAndFee";
        }
        else if (DictCodeListExt1.平安业务类型_产品成立.getValue().equals(paBussType)) {
            model.addAttribute("paBussType", paBussType);
            return "invest/settle/fundback/ProductPrint";
        }

        return "invest/settle/fundback/CashAndFee";
    }

    public Map<String, Object> findWorkflowInfo() {
        return restTemplate.postForObject(ServiceApplication.SETTLE_SERVICE_NAME + "/FundBackgroundManagement/findWorkflowInfo",
            RequestUtil.getPageParam(), HashMap.class);
    }

    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/invest/settle/fundback/config/export_conf.xml").dataSource(DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<FundBackgroundManagement001Dto> selectPageData(Map<String, Object> selectParam) {
        return findPage(selectParam);
    }
}
