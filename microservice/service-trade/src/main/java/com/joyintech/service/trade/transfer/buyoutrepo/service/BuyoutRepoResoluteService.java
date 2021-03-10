package com.joyintech.service.trade.transfer.buyoutrepo.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.trade.transfer.buyoutrepo.dao.BuyoutRepoResoluteMapper;
import com.joyintech.service.trade.transfer.buyoutrepo.dto.BuyoutRepoResolute001Dto;
import com.joyintech.tams.common.base.dao.PtlSecPositionFlowMapper;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.dto.common.PledgeBondInfoDto;
import com.joyintech.tams.invest.common.trade.dao.BussRepoSecMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 买断式回购交易拆分Service
 * @author liuwei
 * @version 1.0 2019年11月20日
 * @see BuyoutRepoResoluteService
 * @since 1.0
 */
@Service("backBuyoutRepoResoluteService")
@Transactional(rollbackFor=Exception.class)
public class BuyoutRepoResoluteService extends BaseService<BuyoutRepoResolute001Dto> {

    @Autowired
    private IPlatformDate platformDateImpl;

    /**
     * 注入存款类交易拆分mapper
     */
    @Autowired
    private BuyoutRepoResoluteMapper buyoutRepoResoluteMapper;

    /**
     * TrdRepoTradeMapper
     */
    @Autowired
    private BussRepoSecMapper bussRepoSecMapper;

    @Autowired
    private PtlSecPositionFlowMapper ptlSecPositionFlowMapper;

    /**
     * 由交易编号带出各种表单元素
     * @param map 交易编号
     * @return 资产信息等
     * @see
     * @since 1.
     */
    public BuyoutRepoResolute001Dto selectTradeInfo(Map<String,Object> map){
        map.put("YESORNO_Y",DictCodeList.是否_是.getValue());
        map.put("TRADE_STATUS_01",DictCodeListExt1.交易状态_初始.getValue());
        map.put("FEE_TYPE_B08", DictCodeListExt1.一次性费用类型_结算手续费.getValue());
        map.put("FEE_TYPE_B06", DictCodeListExt1.一次性费用类型_交易手续费.getValue());
        BuyoutRepoResolute001Dto dto = buyoutRepoResoluteMapper.selectTradeInfo(map);
//        dto.setTradeDate(DateUtil.parse(DateUtil.getSysDate()));
        return dto;
    }

    /**
     * 查询已拆分金额
     *
     * @return 拆分前交易编号
     * @see
     * @since 1.0
     */
    public BigDecimal getDistAmt(String tradeId){
        return buyoutRepoResoluteMapper.getDistAmt(tradeId);
    }

    /**
     * 获取拆分信息—组合代码
     * @return 联想文本框实体类集合
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getPortfolioId(Map<String,Object> map){
        return buyoutRepoResoluteMapper.getPortfolioId(map);
    }

    /**
     * 查询托管划款指令
     * <功能详细描述>
     * @param tradeId 查询条件
     * @see
     * @since 1.0
     */
    public StlPayOrders selectStlPayOrder(String tradeId){
        return buyoutRepoResoluteMapper.selectStlPayOrder(tradeId);
    }

    /**
     * 查询组合托管户
     * @return 组合托管户
     * @see
     * @since 1.0
     */
    public String getAccountInfo(Map<String,Object> map){
        map.put("ACCT_TYPE", DictCodeListExt2.资金账户类型_托管户.getValue());
        return buyoutRepoResoluteMapper.getAccountInfo(map);
    }

    /**
     * 根据交易编号删除质押品信息
     * @param tradeId 交易编号
     * @return Db
     */
    public int deleteFinDealPledgeByTradeId(String tradeId){
        return buyoutRepoResoluteMapper.deleteFinDealPledgeByTradeId(tradeId);
    }

    /**
     * 根据交易编号删除交易类金融产品
     * @param tradeId 交易编号
     * @return Db
     */
    public int deleteFinTradeProductByTradeId(String tradeId){
        return buyoutRepoResoluteMapper.deleteFinTradeProductByTradeId(tradeId);
    }

    /**
     * 根据工作流代码获取交易编号
     * @param workflowId 工作流代码
     * @return Db
     */
    public List<WflTrdProductDeal> selectWflTrdProductDeal(String workflowId){
        return buyoutRepoResoluteMapper.selectWflTrdProductDeal(workflowId);
    }

    /**
     * 根据画面条件检索可质押券信息
     *
     * @param params 画面参数
     * @return List<PledgeBondInfoDto>
     */
    public List<PledgeBondInfoDto> selectAvaPledgeBondDetailList(Map<String, Object> params) {
        String ps = (String) params.get("ps");
        String bondType = (String) params.get("bondType");
        if(StringUtil.isNotEmpty(bondType)){
            params.put("bondTypeArr", StringUtil.split(bondType,","));
        }
        List<PledgeBondInfoDto> resultList = new ArrayList<>();
        if (DictCodeList.回购拆借方向_正回购.getValue().equals(ps)) {
            params.put("POSITION_TYPE_01", DictCodeList.持仓类型_可用.getValue());
            params.put("POSITION_TYPE_03", DictCodeList.持仓类型_实际.getValue());
            params.put("POSITION_TYPE_04", DictCodeList.持仓类型_质押.getValue());
            params.put("POSITION_TYPE_05", DictCodeList.持仓类型_融入.getValue());
            params.put("POSITION_TYPE_06", DictCodeList.持仓类型_融出.getValue());
            params.put("plateDate", platformDateImpl.getSysDateStr());
            params.put("repRegistOrg", StringUtil.splitToList(GlobalConstant.COMMA, (String) params.get("repRegistOrg")));
            params.put("FINPROD_TYPE_TG_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
            resultList = bussRepoSecMapper.selectAvaPledgeBondDetailListSellRepo(params);
        } else if (DictCodeList.回购拆借方向_逆回购.getValue().equals(ps)) {
            params.put("plateDate", platformDateImpl.getSysDateStr());
            params.put("repRegistOrg", StringUtil.splitToList(GlobalConstant.COMMA, (String) params.get("repRegistOrg")));
            params.put("FINPROD_TYPE_TG_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
            resultList = bussRepoSecMapper.selectAvaPledgeBondDetailListAntiRepo(params);
        }
        return resultList;
    }

    /**
     * 根据交易编号删除组合持仓流水（持仓类型为融入融出）
     *
     * @param tradeId 交易编号
     * @return List<PtlSecPositionFlowDto>
     * @see
     * @since 1.0
     */
    public long deletePtlSecPositionFlow(String tradeId) {
        Map<String, Object> param = new HashMap<>();
        param.put("tradeId", tradeId);
        param.put("POSITION_TYPE_ROLLIN", DictCodeList.持仓类型_融入.getValue());
        param.put("POSITION_TYPE_ROLLOUT", DictCodeList.持仓类型_融出.getValue());
        List<PtlSecPositionFlow> ptlSecPositionFlows = buyoutRepoResoluteMapper.selectPtlSecPositionFlow(param);
        for (PtlSecPositionFlow flow : ptlSecPositionFlows) {
            PtlSecPosition position = new PtlSecPosition();
            BeanUtil.copyPropertiesIgnoreNull(position, flow);
            flow.setAmount(flow.getAmount().negate());
            flow.setSeqno(IDGenerator.generate());
            ptlSecPositionFlowMapper.insert(flow);
            buyoutRepoResoluteMapper.updatePosition(position);
            //更新组合融入融出表明细
            PtlSecPositionDetail positionDetail = new PtlSecPositionDetail();
            BeanUtil.copyPropertiesIgnoreNull(positionDetail, position);
            positionDetail.setTradeId(flow.getTradeId());
            buyoutRepoResoluteMapper.updatePositionDetail(positionDetail);
        }
        return buyoutRepoResoluteMapper.deletePtlSecPositionFlow(param);
    }

}
