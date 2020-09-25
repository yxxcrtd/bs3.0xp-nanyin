package com.joyintech.tams.invest.trade.bondtostock.service;

import java.util.Map;

import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.trade.bondtostock.dao.TrdBondToStockMapper;
import com.joyintech.tams.invest.trade.bondtostock.dto.TrdBondToStock001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 可转债转股
 *
 * @author 丁志鹏
 * @version 1.0 2019年11月14日
 * @see TrdBondToStock001Service 可转债转股管理画面001
 * @since 1.0
 */
@Service
public class TrdBondToStock001Service extends CommonExportService<TrdBondToStock001Dto> implements BaseProcessService<TrdBondToStock001Dto> {
    /**
     * 可转债转股Dao
     */
    @Autowired
    private TrdBondToStockMapper trdBondToStockMapper;

    @Override
    public void doComplete(TrdBondToStock001Dto entity, OperationType type) {

    }

    public static void  setParam(Map<String, Object> pageParam){
        pageParam.put("VALID_STATUS_YX", DictCodeListExt1.有效状态_有效.getValue());
        pageParam.put("VALID_STATUS_DCL", DictCodeListExt1.有效状态_待处理.getValue());
        pageParam.put("VALID_STATUS_CX", DictCodeListExt1.有效状态_撤销.getValue());
        pageParam.put("VALID_STATUS_LCZ", DictCodeListExt1.有效状态_流程中.getValue());
        pageParam.put("YES_NO_YES", DictCodeList.是否_是.getValue());
        pageParam.put("YES_NO_NO", DictCodeList.是否_否.getValue());
        pageParam.put("COMMON_BUSINESS_TYPE_ZZGJY", DictCodeListExt3.共通业务类型_可转债转股交易.getValue());
        pageParam.put("FINPROD_TYPE_TG_ZQ",DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        pageParam.put("TG_BUSI_TYPE_ZZG", DictCodeListExt3.平安业务类型_债转股.getValue());
//        pageParam.put("FINPROD_TYPE_TG_F01", DictCodeListExt1.金融产品类型_投管用_债转股.getValue());
        pageParam.put("TRADE_STATUS_CS", DictCodeListExt1.交易状态_初始.getValue());
        pageParam.put("TRADE_STATUS_CX", DictCodeListExt1.交易状态_撤销.getValue());
        String finprodMarketId = (String)pageParam.get("finprodMarketId");
        String finprodId = (String)pageParam.get("finprodId");
        String portfolioId = (String)pageParam.get("portfolioId");
        if(StringUtil.isNotEmpty(finprodMarketId)){
            pageParam.put("finprodMarketIdList",StringUtil.splitToList(GlobalConstant.COMMA,finprodMarketId));
        }
        if(StringUtil.isNotEmpty(finprodId)){
            pageParam.put("finprodIdList",StringUtil.splitToList(GlobalConstant.COMMA,finprodId));
        }
        if(StringUtil.isNotEmpty(portfolioId)){
            pageParam.put("portfolioIdList",StringUtil.splitToList(GlobalConstant.COMMA,portfolioId));
        }
        pageParam.put("loginUserName", LoginUserUtil.getUserName());
    }

    /**
     * 查询操作
     * @param pageParam 表单条件
     * @return 查询的结果
     */
    public Page<TrdBondToStock001Dto> findPage(Map<String, Object> pageParam) {
        setParam(pageParam);
        return queryForPage(()->{trdBondToStockMapper.findPage(pageParam);});
    }

    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/invest/trade/bondtostock/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<TrdBondToStock001Dto> selectPageData(Map<String, Object> selectParam) {
        return findPage(selectParam);
    }
}
