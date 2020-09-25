package com.joyintech.service.period.trdirsduration.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.autoperiod.common.service.InvestPeriodCommonService;
import com.joyintech.service.period.trdirsduration.dao.TrdIRSDurationMapper;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.common.TrdCommonDeleteDto;
import com.joyintech.tams.invest.common.dto.period.common.BaseTradeInfoDto;
import com.joyintech.tams.invest.common.dto.period.trdirsduration.TrdIRSDurationDto;
import com.joyintech.tams.invest.common.risk.service.RiskCreditListCommonValidatorService;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;

/**
 * 利率互换存续期
 * 
 * @author : renping
 * @version V1.0
 * @Project: microservice
 * @Package com.joyintech.service.period.trdirsduration.service
 * @date Date : 2019年08月10日
 */
@Service
public class TrdIRSDurationService extends BaseService {

    @Autowired
    private TrdIRSDurationMapper trdIRSDurationMapper;

    @Autowired
    private IPlatformDate platformDateImpl;

    @Autowired
    private InvestPeriodCommonService investPeriodCommonService;
    
    @Autowired
    private RiskCreditListCommonValidatorService riskCreditListCommonValidatorService;

    /**
     * 汇总交易编号
     * 
     * @param page 页数
     * @param length 每页长度
     * @param param 参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getBusiCode(int page, int length, Map<String, Object> param) {
        param.put("FINPROD_TYPE_TGF14", DictCodeListExt1.金融产品类型_投管用_利率互换.getValue());
        param.put("TRADE_STATUS03", DictCodeListExt1.交易状态_撤销.getValue());
        return queryForPageForSelectReturn(page, length,
            () -> trdIRSDurationMapper.getBusiCode(param));
    }

    /**
     * 金融产品代码
     * 
     * @param page 页数
     * @param length 每页长度
     * @param param 参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getFinprodId(int page, int length, Map<String, Object> param) {
        param.put("FINPROD_TYPE_TGF14", DictCodeListExt1.金融产品类型_投管用_利率互换.getValue());
        return queryForPageForSelectReturn(page, length,
            () -> trdIRSDurationMapper.getFinprodId(param));
    }

    /**
     * 分页查询
     * 
     * @param page 页数
     * @param length 每页长度
     * @param param 参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public Page<TrdIRSDurationDto> findPage(int page, int length, Map<String, Object> param) {
        setParams(param);
        Page<TrdIRSDurationDto> result = queryForPageForSelectReturn(page, length,
            () -> trdIRSDurationMapper.findPage(param));
        return result;
    }

    /**
     * 查询有效、撤销或待处理的存续期交易数据（所有数据）
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    public List<TrdIRSDurationDto> findAllData(@RequestBody Map<String, Object> param) {
        setParams(param);
        return trdIRSDurationMapper.findPage(param);
    }

    private void setParams(Map<String, Object> param) {
        param.put("PAGE_DATE_STATUS01", DictCodeListExt1.画面数据状态_待处理.getValue());
        param.put("PAGE_DATE_STATUS03", DictCodeListExt1.画面数据状态_流程中.getValue());
        param.put("PAGE_DATE_STATUS05", DictCodeListExt1.画面数据状态_有效.getValue());
        param.put("PAGE_DATE_STATUS06", DictCodeListExt1.画面数据状态_撤销.getValue());
        // 数据字典缓存.交易状态.初始
        param.put("TRADE_STATUS01", DictCodeListExt1.交易状态_初始.getValue());
        param.put("TRADE_STATUS03", DictCodeListExt1.交易状态_撤销.getValue());
        param.put("YESNO_N", DictCodeList.是否_否.getValue());
        param.put("YESNO_Y", DictCodeList.是否_是.getValue());
        // 数据字典缓存.持仓类型.实际持仓
        param.put("POSITION_TYPE03", DictCodeList.持仓类型_实际.getValue());
        // 数据字典缓存.金融产品类型 投管分类.利率互换
        param.put("FINPROD_TYPE_TGF14", DictCodeListExt1.金融产品类型_投管用_利率互换.getValue());
        param.put("STATUS_N", DictCodeList.数据状态_新建.getValue());
        String plateDate = platformDateImpl.getSysDateStr();
        param.put("platDate", plateDate);

        param.put("psR", DictCodeList.交易方向_收取.getValue());
        param.put("psP", DictCodeList.交易方向_支付.getValue());
        param.put("irsType01", DictCodeListExt2.互换方式_收取固定.getValue());
        param.put("irsType02", DictCodeListExt2.互换方式_收取浮动.getValue());
        param.put("pridBussType01", DictCodeListExt2.利率互换存续期类型_中间收付息.getValue());
        param.put("pridBussType02", DictCodeListExt2.利率互换存续期类型_到期.getValue());
        param.put("pridBussType02", DictCodeListExt2.利率互换存续期类型_到期.getValue());
        param.put("irsPsIntType01", DictCodeListExt2.利率互换收付息类型_收取固定.getValue());
        param.put("irsPsIntType02", DictCodeListExt2.利率互换收付息类型_支付固定.getValue());
        param.put("irsPsIntType03", DictCodeListExt2.利率互换收付息类型_收取浮动.getValue());
        param.put("irsPsIntType04", DictCodeListExt2.利率互换收付息类型_支付浮动.getValue());
        param.put("irsPsIntType05", DictCodeListExt2.利率互换收付息类型_收取固定_支付浮动.getValue());
        param.put("irsPsIntType06", DictCodeListExt2.利率互换收付息类型_收取浮动_支付固定.getValue());
        param.put("cashBussType", DictCodeListExt1.现金流业务类型_金融产品.getValue());
        param.put("rateTypeFix", DictCodeList.利率类型_固定利率.getValue());
        param.put("rateTypeFloat", DictCodeList.利率类型_浮动利率.getValue());
        param.put("busiTable", DictCodeList.业务表_金融产品表.getValue());
        param.put("irsType01", DictCodeListExt2.互换方式_收取固定.getValue());
        param.put("TG_BUSI_TYPE_beforeEnd", DictCodeListExt2.平安业务类型_利率互换提前终止.getValue());
    }

    /**
     * 确认/查看数据展示查询
     * 
     * @param params
     * @return
     * @see
     * @since 1.0
     */
    public TrdIRSDurationDto findEffectiveData(Map<String, Object> params) {
        setParams(params);
        List<TrdIRSDurationDto> list = trdIRSDurationMapper.findPage(params);
        if (StringUtil.isEmptyList(list) || list.get(0) == null) {
            return new TrdIRSDurationDto();
        }
        return list.get(0);
    }

    /**
     * 查询有效的存续期交易
     * 
     * @param params 参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public List<BaseTradeProductDealDto> findEffectiveDeal(Map<String, Object> params) {
        return trdIRSDurationMapper.findEffectiveDeal(params);
    }

    public String doComplete(TrdIRSDurationDto durationDto) {
        String updateUser = durationDto.getUpdateUser();
        Date updateTime = new Date();
        //撤销时查找授信交易编号
        String tradeid = trdIRSDurationMapper.findCeTradeIdByWorkflowId(durationDto.getWorkflowId());
        if (BussinessConstant.REVOKE.equals(durationDto.getOperateType())) {
            investPeriodCommonService.deleteByWorkflowId(durationDto.getWorkflowId());
            investPeriodCommonService.revoke汇总和交易相关表(durationDto.getBusiCode(), updateUser,
                durationDto.getRevokeReason(), null, null);
            // 删除其他表
            
            //删除授信占用
            if (null != tradeid && !"".equals(tradeid)){
                riskCreditListCommonValidatorService.deleteCredit(tradeid);
            }
            return Boolean.TRUE.toString();
        }
        investPeriodCommonService.doInsertAndDelete(durationDto.getBaseTotalTradeInfoDto(), updateUser,
            updateTime, durationDto.getWorkflowId());

        List<BaseTradeInfoDto> baseTradeInfoDtos = durationDto.getBaseTotalTradeInfoDto().getBaseTradeInfoDtoList();
        if (baseTradeInfoDtos != null && !baseTradeInfoDtos.isEmpty()) {
            baseTradeInfoDtos.forEach(baseTradeInfoDto -> {
                String portfolioId = baseTradeInfoDto.getTrdProductDealAdd() != null ? baseTradeInfoDto.getTrdProductDealAdd().getPortfolioId() : baseTradeInfoDto.getBaseTrade().getTrdProductDealAdd().getPortfolioId();

                AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
                inputDto.setProdid(portfolioId);
                inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_投资组合.getValue());
                if (baseTradeInfoDto.getTrdProductDeal() != null) {
                    String counterid = trdIRSDurationMapper.selectCounterIdByFinprodId(durationDto.getFinprodId());
                    baseTradeInfoDto.getTrdProductDeal().setCounterId(counterid);
                    inputDto.setTrdProductDeal(baseTradeInfoDto.getTrdProductDeal());
                    inputDto.setTrdProductDealAdd(baseTradeInfoDto.getTrdProductDealAdd());
                    inputDto.setPtlTradeList(baseTradeInfoDto.getPtlTrade());
                    inputDto.setStlPayOrders(baseTradeInfoDto.getStlPayOrders());
                    AllTradeCommonUtil.handleAfterTradeComplete(inputDto);
                }
                if (baseTradeInfoDto.getBaseTrade() != null
                    && baseTradeInfoDto.getBaseTrade().getTrdProductDeal() != null) {
                    inputDto.setTrdProductDeal(
                        baseTradeInfoDto.getBaseTrade().getTrdProductDeal());
                    inputDto.setTrdProductDealAdd(
                        baseTradeInfoDto.getBaseTrade().getTrdProductDealAdd());
                    inputDto.setPtlTradeList(baseTradeInfoDto.getBaseTrade().getPtlTrade());
                    inputDto.setStlPayOrders(baseTradeInfoDto.getStlPayOrders());
                    AllTradeCommonUtil.handleAfterTradeComplete(inputDto);
                }
            });
        }
        return Boolean.TRUE.toString();
    }

    public String doDelete(TrdCommonDeleteDto trdCommonDeleteDto) {
        investPeriodCommonService.deleteByWorkflowId(trdCommonDeleteDto.getWorkflowId());
        return Boolean.TRUE.toString();
    }

    public String doRevoke(TrdIRSDurationDto trdIRSDurationDto) {
        Map<String, Object> params = new HashMap<>();
        params.put("bizCode", trdIRSDurationDto.getBusiCode());
        params.put("workflowId", trdIRSDurationDto.getWorkflowId());
        params.put("workflowStatus", DictCodeList.数据状态_删除中.getValue());
        params.put("user", trdIRSDurationDto.getCreateUser());
        params.put("dept", StringUtil.isEmpty(trdIRSDurationDto.getCreateDept()) ? "dept" : trdIRSDurationDto.getCreateDept());
        params.put("time", trdIRSDurationDto.getCreateTime());
        params.put("revokeReason", trdIRSDurationDto.getRevokeReason());
        investPeriodCommonService.insertWflTable(params);
        return Boolean.TRUE.toString();
    }
}
