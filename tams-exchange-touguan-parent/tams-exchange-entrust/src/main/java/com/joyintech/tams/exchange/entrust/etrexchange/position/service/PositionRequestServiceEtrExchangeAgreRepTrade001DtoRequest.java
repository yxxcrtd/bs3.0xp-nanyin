package com.joyintech.tams.exchange.entrust.etrexchange.position.service;

import java.math.BigDecimal;
import java.util.*;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.tams.business.common.service.TamsBusinessCommonService;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.entrust.etragrereptrade.dto.EtrAgreRepTradeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.business.common.service.PositionHttpRequest;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.exchange.entrust.etrexchange.agreementrep.dto.EtrExchangeAgreRepTrade001Dto;
import com.joyintech.tams.exchange.entrust.etrexchange.agreementrep.dto.EtrExchangeAgreRepTrade003Dto;
import com.joyintech.tams.exchange.entrust.etrexchange.position.bean.PositionRequestBaseBean;
import com.joyintech.tams.exchange.entrust.etrexchange.position.constant.PositionConstant;
import com.joyintech.tams.invest.common.position.service.CommonPositionEngineService;

/**
 * 协议回购
 */
@Service
public class PositionRequestServiceEtrExchangeAgreRepTrade001DtoRequest extends BaseService {

    private static final Logger log = LoggerFactory.getLogger(PositionRequestServiceEtrExchangeAgreRepTrade001DtoRequest.class);

    @Autowired
    private CommonPositionEngineService commonPositionEngineService;

    @Autowired
    private TamsBusinessCommonService tamsBusinessCommonService;

    /**
     * EtrExchangeAgreRepTrade001Dto 请求对象使用
     *
     * @param entity
     */
    public void send(EtrExchangeAgreRepTrade001Dto entity, String decisionAct, String systemAct) {
        log.debug("send() called with parameters => 【entity = {}】, 【decisionAct = {}】, 【systemAct = {}】",JsonUtils.object2Json(entity), decisionAct, systemAct);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("decisionAct", decisionAct);
        resultMap.put("systemAct", systemAct);
        this.setCommonField(entity, resultMap);//设置公共字段，个性化的需要在具体接口中覆盖
        this.repo002(entity, resultMap);

        PositionHttpRequest.send(resultMap);
    }

    /**
     * doDelete时使用---作废指令-共通接口
     *
     * @param entity
     */
    public void sendDelete(EtrExchangeAgreRepTrade001Dto entity) {
        log.debug("sendDelete() called with parameters => 【entity = {}】", JsonUtils.object2Json(entity));
        String busiType = commonPositionEngineService.getPositionBusiType(entity.getFinprodType2(), entity.getPs(), entity.getAssetType2());
        PositionHttpRequest.doDeleteByWorkFlowId(entity.getWorkflowId(), PositionConstant.POSITION_TYPE_ETR, busiType, entity.getPs());
    }

    /**
     * 撤销时使用---撤销指令-共通接口
     *
     * @param entity
     */
    public void sendRevoke(EtrExchangeAgreRepTrade003Dto entity) {
        return;
//        String busiType = commonPositionEngineService.getPositionBusiType(entity.getFinprodType2(), entity.getPs(), null);
//        PositionHttpRequest.dorevoke(entity.getEntrustId(), PositionConstant.POSITION_TYPE_TRD, busiType, entity.getPs());
    }

    /**
     * 关闭时使用---关闭-共通接口
     *
     * @param entity
     */
    public void sendClose(EtrExchangeAgreRepTrade003Dto entity) {
        log.debug("sendClose() called with parameters => 【entity = {}】", JsonUtils.object2Json(entity));
        String busiType = commonPositionEngineService.getPositionBusiType(entity.getFinprodType2(), entity.getPs(), null);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("userId", SecurityUtil.getLoginUser().getUserName());
        resultMap.put("totalTradeId", entity.getWorkflowId());
        resultMap.put("modelType", PositionConstant.POSITION_TYPE_ETR);
        resultMap.put("busiType", busiType);
        resultMap.put("ps", entity.getPs());
        resultMap.put("busiAct", DictCodeListExt3.业务动作_指令手动关闭.getValue());
        resultMap.put("systemAct", DictCodeListExt3.系统动作_关闭.getValue());
        resultMap.put("closeId", entity.getEntrustId());
        PositionHttpRequest.batchClose(resultMap);
    }

    /**
     * 质押式协议回购-指令    repo002
     *
     * @param entity
     * @param resultMap
     */
    private void repo002(EtrExchangeAgreRepTrade001Dto entity, Map<String, Object> resultMap) {
        resultMap.put("interfaceType", PositionConstant.INTERF_TYPE_REPO002);
        // 业务动作
        resultMap.put("busiAct", DictCodeListExt3.业务动作_投资指令.getValue());
        // 回购方向
        String repPs = entity.getRepPs();
        // 交易方向
        resultMap.put("ps", repPs);
        // 清算速度
        resultMap.put("settleSpeed", null);
        // 交易平台
        resultMap.put("sharesTradePlatform", null);
        // 结算方式
        resultMap.put("deliveryType", null);

        PositionRequestBaseBean bean;

        // 首期组合现金数据集
        String ps = entity.getPs();
        if (DictCodeListExt3.交易方向_首期.getValue().equals(ps)
                || DictCodeListExt3.交易方向_提前购回.getValue().equals(ps)
                || DictCodeListExt3.交易方向_到期购回.getValue().equals(ps)
                || DictCodeListExt3.交易方向_解除质押.getValue().equals(ps)) {
            List<Map<String, Object>> list = new ArrayList<>(1);
            bean = new PositionRequestBaseBean();
            bean.setPortfolioId(entity.getPortfolioId());
            if (DictCodeListExt3.交易方向_解除质押.getValue().equals(ps)) {
                // 解除质押走线下划款，扣托管户现金
                bean.setCashAcctId(tamsBusinessCommonService.selectProdTghCapacctNo(entity.getPortfolioId()));
            } else {
                // 其他走券商资金账户
                bean.setCashAcctId(entity.getAcctNo());
            }
            bean.setTradeId(null);
            bean.setEtrId(entity.getEntrustId());
            // 交易金额
            bean.setCashAmount(entity.getTradeAmt());
            Map<String, Object> map = BeanUtil.object2Map(bean);
            list.add(map);
            resultMap.put("ptlCashList", list);
        }

        // 轧差组合现金数据集
        if (DictCodeListExt3.交易方向_到期续做.getValue().equals(ps)) {
            List<Map<String, Object>> list = new ArrayList<>(1);
            bean = new PositionRequestBaseBean();
            bean.setPortfolioId(entity.getPortfolioId());
            bean.setCashAcctId(entity.getAcctNo());
            bean.setTradeId(null);
            bean.setEtrId(entity.getEntrustId());

            BigDecimal firstTradeAmt = getFirstTradeAmt(entity);
            BigDecimal tradeAmt = entity.getTradeAmt();
            // IF 回购类型 = 正回购 THEN 指令.到期续作金额 - 指令.首期的到期结算金额
            //IF 回购类型 = 逆回购  and 成交编号找不到对应的首期 THEN 指令.到期续作金额
            //IF 回购类型 = 逆回购  and 成交编号找到对应的首期 THEN  指令.到期续作金额 - 指令.首期的到期结算金额
            if (Objects.nonNull(tradeAmt)) {
                bean.setCashAmount(tradeAmt.subtract(firstTradeAmt));
            }

            Map<String, Object> map = BeanUtil.object2Map(bean);
            list.add(map);
            resultMap.put("ptlOffsetCashList", list);
        }

        // 质押券数据集
        // IF 交易方向 = 首期、（中途换券换入） and 回购类型 = 正回购 THEN 传值 ELSE 空
        if (DictCodeListExt3.交易方向_正回购.getValue().equals(repPs)
            && (
                DictCodeListExt3.交易方向_首期.getValue().equals(ps)
                || DictCodeListExt3.交易方向_中途换券.getValue().equals(ps)
                )) {
            List<Map<String, Object>> list = new ArrayList<>(1);
            bean = new PositionRequestBaseBean();
            bean.setPortfolioId(entity.getPortfolioId());
            bean.setFinprodId(entity.getFinprodId());
            bean.setSecAcctId(entity.getSecAcctId());
            bean.setTradeId(null);
            bean.setEtrId(entity.getEntrustId());
            // 券面总额 指令.质押券数量*100
            bean.setSecAmount(entity.getRepSecNum().multiply(BigDecimal.valueOf(100L)));

            Map<String, Object> map = BeanUtil.object2Map(bean);
            //融入券券面总额
            map.put("integrateSecAmount", null);
            //本方券券面总额 指令.质押券数量*100
            map.put("ownSecAmount", bean.getSecAmount());

            list.add(map);
            resultMap.put("ptlSecList", list);
        }

        // 解除质押券数据集
        // IF 交易方向 = 解除质押、到期续作、中途换券换出 and 回购类型 = 正回购 THEN 传值 ELSE 空 END IF
        if ( DictCodeListExt3.交易方向_正回购.getValue().equals(repPs)
               && (DictCodeListExt3.交易方向_解除质押.getValue().equals(ps)
                || DictCodeListExt3.交易方向_到期续做.getValue().equals(ps)
                || DictCodeListExt3.交易方向_中途换券.getValue().equals(ps))) {
            List<Map<String, Object>> list = new ArrayList<>(1);
            bean = new PositionRequestBaseBean();
            bean.setPortfolioId(entity.getPortfolioId());
            bean.setFinprodId(entity.getFinprodId());
            bean.setSecAcctId(entity.getSecAcctId());
            bean.setTradeId(null);
            bean.setEtrId(entity.getEntrustId());
            // 券面总额 指令.质押券数量*100
            bean.setSecAmount(entity.getRepSecNum().multiply(BigDecimal.valueOf(100L)));

            Map<String, Object> map = BeanUtil.object2Map(bean);
            //融入券券面总额
            map.put("integrateSecAmount", null);
            //本方券券面总额 指令.质押券数量*100
            map.put("ownSecAmount", bean.getSecAmount());

            list.add(map);

            resultMap.put("ptlRelieveSecList", list);
        }
    }

    /**
     * 到期续作使用原始的回购金额
     */
    private BigDecimal getFirstTradeAmt(EtrExchangeAgreRepTrade001Dto entity){
        //获得初始回购金额和交易方向
        BigDecimal amount = BigDecimal.ZERO;
        String ps;
        Map<String, Object> map = new HashMap<>();
        map.put("tradeId", StringUtil.isEmpty(entity.getIntiTradeId()) ? entity.getTradeId() : entity.getIntiTradeId());
        List<EtrAgreRepTradeDto> result =  restTemplate.postForList(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrAgreRepTrade/getEntrustInfoByTradeId",
                map, EtrAgreRepTradeDto.class);
        log.debug("调用接口查询首期回购 url = [{}], request = [{}], response = [{}]",
                ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrAgreRepTrade/getEntrustInfoByTradeId",
                JsonUtils.object2Json(map), JsonUtils.object2Json(result));
        if(StringUtil.isEmptyList(result)) {
            ps = DictCodeListExt3.交易方向_逆回购.getValue();
        } else {
            ps = result.get(0).getPs();
            amount = result.get(0).getTradeAmt();
        }
        return amount;
    }

    /**
     * 设置公共字段
     *
     * @param entity    实例对象
     * @param resultMap 封装集合
     */
    private void setCommonField(EtrExchangeAgreRepTrade001Dto entity, Map<String, Object> resultMap) {
        String balanceCheck = "Y";
        if (StringUtil.isNotEmpty(entity.getSoftSkipMode()) && StringUtil.equals(entity.getSoftSkipMode(), "Y")) {
            balanceCheck = "N";
        }
        resultMap.put("userId", SecurityUtil.getLoginUser().getUserName());
        resultMap.put("totalTradeId", entity.getEntrustId());
        resultMap.put("balanceCheck", balanceCheck);
        resultMap.put("flowStatus", DictCodeListExt3.流程节点_流程提交.getValue());
        resultMap.put("modelType", DictCodeListExt1.子系统代码_委托.getValue());

        //设置头寸业务类型
        String busiType = commonPositionEngineService.getPositionBusiType(entity.getFinprodType2(), entity.getPs(), entity.getAssetType2());
        resultMap.put("busiType", busiType);

        // 结算方式	见数据字典：结算方式
        // 调用【共通:结算方式判断】 返回.结算方式
        String deliveryType = commonPositionEngineService.getDeliveryType(entity.getFinprodId());
        resultMap.put("deliveryType", deliveryType);
        // 交易方向
        resultMap.put("ps", entity.getPs());
        // 交易场所	见数据字典：交易场所
        resultMap.put("tradePlace", entity.getTradeMarket());
        // 交易平台	见数据字典：交易平台
        resultMap.put("sharesTradePlatform", null);
        // 流程编号	指令的工作流ID
        resultMap.put("workFlowId", entity.getWorkflowId());
        // 日期
        resultMap.put("cdate", entity.getVdate());
        // 币种
        resultMap.put("ccy", DictCodeList.币种_人民币_CNY.getValue());
        // 前次交易编号
        resultMap.put("bTotalTradeId", null);
    }
}
