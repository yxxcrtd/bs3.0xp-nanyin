package com.joyintech.service.settle.fundback.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DynamicDataSourceHolder;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.settle.fundback.dao.FundBackgroundManagementMapper;
import com.joyintech.service.settle.fundback.dao.FundBackgroundPrintMapper;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.settle.fundback.FundBackPrintDataBaseDto;
import com.joyintech.tams.invest.common.dto.settle.fundback.FundBackgroundManagementDto;
import com.joyintech.tams.invest.common.util.SettleCommonUtil;

/**
 * @program: tams-invest-parent
 * @description: 资金后台管理微服务用service
 * @author: 强成西
 * @create: 2018-11-15 14:49
 **/
@Service
public class FundBackgroundManagementService extends BaseService<FundBackgroundManagementDto> {

    @Autowired
    private FundBackgroundManagementMapper fundBackgroundManagementMapper;

    @Autowired
    private FundBackgroundPrintMapper fundBackgroundPrintMapper;

    public Page<SelectOptionDto> findPortfolioId(int page, int length, Map<String, Object> param) {
        return queryForPageForSelectReturn(page, length, () -> fundBackgroundManagementMapper.findPortfolioId(param));
    }
    //二级法人改造  资产分开查询   modify bu caojin 2020/6/20
    public Page<SelectOptionDto> findBizCode(int page, int length, Map<String, Object> params) {
    	Page<SelectOptionDto> selectPage = new Page<SelectOptionDto>();
    	List<SelectOptionDto> all = new ArrayList<SelectOptionDto>();
        params.put("F24", DictCodeListExt1.金融产品类型_投管用_理财产品分层.getValue());
        //产品
        List<SelectOptionDto> A = fundBackgroundManagementMapper.findBizCodeA(params);
        all.addAll(A);
        //公共资产（债券、股票、基金）
        List<SelectOptionDto> B = fundBackgroundManagementMapper.findBizCodeB(params);
        all.addAll(B);
        //私有资产
        List<SelectOptionDto> C = fundBackgroundManagementMapper.findBizCodeC(params);
        all.addAll(C);
        //场外期权
        List<SelectOptionDto> D = fundBackgroundManagementMapper.findBizCodeD(params);
        all.addAll(D);
        
        selectPage.setRoot(all);
        return selectPage;
    }

    public Page<FundBackgroundManagementDto> findPage(int page, int length, Map<String, Object> params) {
        SettleCommonUtil.setParams(params);
        setParams(params);
        if (StringUtil.isNotEmpty(params.get("portfolioId"))) {
            params.put("portfolioId", StringUtil.splitToList(",", (String)params.get("portfolioId")));
        }
        if (StringUtil.isNotEmpty(params.get("bizCode"))) {
            params.put("bizCode", StringUtil.splitToList(",", (String)params.get("bizCode")));
        }
        if (StringUtil.isNotEmpty(params.get("bizType"))) {
            params.put("bizType", StringUtil.splitToList(",", (String)params.get("bizType")));
        }
        params.put("psFlag01", DictCodeListExt1.收支方向_收入.getValue());//add by zhangweiqi 2019-9-30 判断收支方向
        return queryForPageForSelectReturn(page, length, () -> fundBackgroundManagementMapper.findPage(params));
    }

    public FundBackPrintDataBaseDto findPrintData(Map<String, Object> params) {
        String paBussType = (String)params.get("paBussType");
        setParams(params);
        if (DictCodeListExt1.平安业务类型_存款类资产买入.getValue().equals(paBussType)) {
            params.put("TRD04", DictCodeList.共通业务类型_存款类资产交易.getValue());
            FundBackPrintDataBaseDto fundBackPrintDataDto = fundBackgroundPrintMapper.findDepoPrintData(params);
            if (fundBackPrintDataDto != null) {
                findTradeOperators(params, fundBackPrintDataDto);
                if (DictCodeList.存款类资产类型_同业借款.getValue().equals(fundBackPrintDataDto.getType6())) {
                    fundBackPrintDataDto.setTextList(
                        fundBackgroundPrintMapper.findTextList(DictCodeList.存款类资产类型_同业借款.getValue()));
                }
            }
            return fundBackPrintDataDto;
        }
        else if (DictCodeListExt1.平安业务类型_普通拆出.getValue().equals(paBussType)) {
            params.put("TRD04", DictCodeList.共通业务类型_拆借投资交易.getValue());
            FundBackPrintDataBaseDto fundBackPrintDataDto = fundBackgroundPrintMapper.findLendOutPrintData(params);
            findTradeOperators(params, fundBackPrintDataDto);
            return fundBackPrintDataDto;
        }
        else if (DictCodeListExt1.平安业务类型_普通拆入到期.getValue().equals(paBussType)
                 || DictCodeListExt1.平安业务类型_特拆拆入到期.getValue().equals(paBussType)) {
            params.put("TRD04", DictCodeList.共通业务类型_拆借存续期交易.getValue());
            FundBackPrintDataBaseDto fundBackPrintDataDto = fundBackgroundPrintMapper.findLendInExpPrintData(params);
            findTradeOperators(params, fundBackPrintDataDto);
            return fundBackPrintDataDto;
        }
        else if (DictCodeListExt1.平安业务类型_特拆拆入.getValue().equals(paBussType)) {
            params.put("TRD04", DictCodeList.共通业务类型_拆借投资交易.getValue());
            FundBackPrintDataBaseDto fundBackPrintDataDto = fundBackgroundPrintMapper.findSpLendInPrintData(params);
            findTradeOperators(params, fundBackPrintDataDto);
            return fundBackPrintDataDto;
        }
        else if (DictCodeListExt1.平安业务类型_项目类资产买入.getValue().equals(paBussType)) {
            params.put("TRD04", DictCodeList.共通业务类型_项目类资产交易.getValue());
            FundBackPrintDataBaseDto fundBackPrintDataDto = fundBackgroundPrintMapper.findPurOfProjectAsset(params);
            findTradeOperators(params, fundBackPrintDataDto);
            return fundBackPrintDataDto;
        }
        else if (DictCodeListExt1.平安业务类型_计划类资产_利率型_申购.getValue().equals(paBussType)) {
            params.put("TRD04", DictCodeList.共通业务类型_计划类资产_利率型_交易.getValue());
            FundBackPrintDataBaseDto fundBackPrintDataDto = fundBackgroundPrintMapper.findPurOfProjectAsset(params);
            findTradeOperators(params, fundBackPrintDataDto);
            return fundBackPrintDataDto;
        }
        else if (DictCodeListExt1.平安业务类型_计划类资产_净值型_申购申请.getValue().equals(paBussType)
                 || DictCodeListExt1.平安业务类型_非货币基金申购申请.getValue().equals(paBussType)
                 || DictCodeListExt1.平安业务类型_货币基金申购申请.getValue().equals(paBussType)) {
            params.put("TRD04", DictCodeList.共通业务类型_自定义现金流.getValue());
            params.put("TRD05", DictCodeList.共通业务类型_产品费用支付管理.getValue());
            FundBackPrintDataBaseDto fundBackPrintDataDto = fundBackgroundPrintMapper.findDataForCashAndFee(params);
            findTradeOperators(params, fundBackPrintDataDto);
            return fundBackPrintDataDto;
        }
        else if (DictCodeListExt1.平安业务类型_自定义现金流支付.getValue().equals(paBussType)
                 || DictCodeListExt1.平安业务类型_产品费用支付.getValue().equals(paBussType)) {
            params.put("TRD04", DictCodeList.共通业务类型_计划类资产_净值型_交易.getValue());
            params.put("TRD05", DictCodeList.共通业务类型_基金类资产交易.getValue());
            FundBackPrintDataBaseDto fundBackPrintDataDto = fundBackgroundPrintMapper.findAppForPurchase(params);
            findTradeOperators(params, fundBackPrintDataDto);
            return fundBackPrintDataDto;
        }
        else if (DictCodeListExt1.平安业务类型_产品成立.getValue().equals(paBussType)) {
            params.put("TRD04", DictCodeList.共通业务类型_收益型产品认申购管理.getValue());
            params.put("CT01", DictCodeListExt1.现金流业务类型_金融产品.getValue());
            params.put("CT_ONE_03", DictCodeListExt1.现金流一级类型_费用.getValue());
            params.put("C02", DictCodeListExt1.现金流二级类型_利息.getValue());
            params.put("C06", DictCodeListExt1.现金流二级类型_销售费.getValue());
            params.put("C05", DictCodeListExt1.现金流二级类型_托管费.getValue());
            params.put("C03", DictCodeListExt1.现金流二级类型_固定管理费.getValue());
            params.put("NO", DictCodeList.是否_否.getValue());
            params.put("YES", DictCodeList.是否_是.getValue());
            FundBackPrintDataBaseDto fundBackPrintDataDto = fundBackgroundPrintMapper.findAppForProduct(params);
            findTradeOperators(params, fundBackPrintDataDto);
            return fundBackPrintDataDto;
        }
        return null;
    }

    private void setParams(Map<String, Object> params) {
        params.put("FIN_CASH_BUSINESS_TYPE_01", DictCodeListExt1.现金流业务类型_金融产品.getValue());
        params.put("NO", DictCodeList.是否_否.getValue());
        params.put("CASH_TYPE_2_C02", DictCodeListExt1.现金流二级类型_利息.getValue());
        params.put("IE_FLAG_01", DictCodeListExt1.收支方向_收入.getValue());
        params.put("IE_FLAG_02", DictCodeListExt1.收支方向_支出.getValue());
        params.put("TRADE_FLOW_STATUS_01", DictCodeListExt1.交易流程状态_流程中.getValue());
        params.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
        params.put("TRADE_STATUS_03", DictCodeListExt1.交易状态_撤销.getValue());
    }

    private void findTradeOperators(Map<String, Object> params, FundBackPrintDataBaseDto fundBackPrintDataDto) {
        if (fundBackPrintDataDto == null) {
            return;
        }
        try {
            DynamicDataSourceHolder.putDataSource(DataSourceType.OTHER);
            params.put("NEW", "new");
            String procInstId = findTraderProcInsId(params);
            if (StringUtil.isNotEmpty(procInstId)) {
                params.put("procInstId", procInstId);
                findTraders(params, fundBackPrintDataDto);
            }
        } finally {
            DynamicDataSourceHolder.clearDataSource();
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String findTraderProcInsId(Map<String, Object> params) {
        return fundBackgroundPrintMapper.findTraderProcInsId(params);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void findTraders(Map<String, Object> params, FundBackPrintDataBaseDto fundBackPrintDataDto) {
        List<Map<String, String>> traders = fundBackgroundPrintMapper.findTraders(params);
        traders.forEach(traderMap -> {
            if ("交易经办".equals(traderMap.get("CUR_NODE_ID"))) {
                fundBackPrintDataDto.setTradeOperator(fundBackgroundPrintMapper.findNameByUserName(traderMap.get("ASSIGNEE_")));
            }
            else if ("交易复核".equals(traderMap.get("CUR_NODE_ID"))) {
                fundBackPrintDataDto.setTradeChecker(fundBackgroundPrintMapper.findNameByUserName(traderMap.get("ASSIGNEE_")));
            }
            else if ("总集经办".equals(traderMap.get("CUR_NODE_ID"))) {
                fundBackPrintDataDto.setBackOperator(fundBackgroundPrintMapper.findNameByUserName(traderMap.get("ASSIGNEE_")));
                fundBackPrintDataDto.setBackOperateTime(traderMap.get("UPDATE_TIME"));
            }
            else if ("总集复核".equals(traderMap.get("CUR_NODE_ID"))) {
                fundBackPrintDataDto.setBackChecker(fundBackgroundPrintMapper.findNameByUserName(traderMap.get("ASSIGNEE_")));
                fundBackPrintDataDto.setBackCheckTime(traderMap.get("UPDATE_TIME"));
            }
        });
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Map<String, Object> findWorkflowInfo(Map<String, Object> params) {
        return fundBackgroundManagementMapper.findWorkflowInfo(params);
    }
}
