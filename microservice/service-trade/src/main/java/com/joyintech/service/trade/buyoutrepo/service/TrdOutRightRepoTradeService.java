/*
 * 文件名：TrdOutRightRepoTradeService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2019年01月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.trade.buyoutrepo.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.trade.buyoutrepo.dao.TrdOutRightRepoTradeMapper;
import com.joyintech.service.trade.buyoutrepo.dto.TrdOutRightRepoTradeDetailDto;
import com.joyintech.service.trade.buyoutrepo.dto.TrdOutRightRepoTradeDto;
import com.joyintech.tams.common.base.dao.PtlSecPositionFlowMapper;
import com.joyintech.tams.common.base.entity.EtrFinishStatus;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.PtlSecPosition;
import com.joyintech.tams.common.base.entity.PtlSecPositionFlow;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.component.base.entrust.BaseEtrInvestInfoService;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.common.dto.common.PledgeBondInfoDto;
import com.joyintech.tams.invest.common.trade.dao.BussRepoSecMapper;

@Service
public class TrdOutRightRepoTradeService extends BaseService<TrdOutRightRepoTradeDto> {

	/**
	 * {@linkplain}
	 */
	@Autowired
	private TrdOutRightRepoTradeMapper trdOutRightRepoTradeMapper;

	@Autowired
	private PtlSecPositionFlowMapper ptlSecPositionFlowMapper;

	/**
     * 投资委托基础组件
     */
    @Autowired(required = false)
    private BaseEtrInvestInfoService baseEtrInvestInfoService;

    @Autowired
    private IPlatformDate platformDateImpl;
    
    /**
     * TrdRepoTradeMapper
     */
    @Autowired
    private BussRepoSecMapper bussRepoSecMapper;

	/**
     * 分页查询
     *
     * @param page 页编号
     * @param length 页条数
     * @param pageParam 画面参数
     * @return 查询结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<TrdOutRightRepoTradeDto> selectForPage(int page, int length,
                                                     Map<String, Object> pageParam)
        throws Exception {
        pageParam.put("effective", DictCodeListExt1.交易流程状态_有效.getValue());
        pageParam.put("revoke", DictCodeListExt1.交易流程状态_撤销.getValue());
        pageParam.put("inflow", DictCodeListExt1.交易流程状态_流程中.getValue());
        pageParam.put("bugOutTypeForword", DictCodeListExt1.金融产品类型_债券买断式正回购.getValue());
        pageParam.put("bugOutTypeNegative", DictCodeListExt1.金融产品类型_债券买断式逆回购.getValue());
        pageParam.put("tradeStatusInit", DictCodeListExt1.交易状态_初始.getValue());
        pageParam.put("tradeStatusRevoke", DictCodeListExt1.交易状态_撤销.getValue());
        pageParam.put("noFlag", DictCodeList.是否_否.getValue());
        pageParam.put("yesFlag", DictCodeList.是否_是.getValue());
        pageParam.put("psPay", DictCodeList.交易方向_买入.getValue());

        return this.queryForPage(page, length,
            () -> trdOutRightRepoTradeMapper.queryForPage(pageParam));
    }
    
    /**
	 * 查询债券代码下拉框
	 * @param map 查询参数
	 * @return 债券代码下拉框
	 */
    public List<SelectOptionDto> querySecFinprodIdList(Map<String, Object> map){
    	map.put("FINPROD_TYPE_TG", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
    	return trdOutRightRepoTradeMapper.querySecFinprodIdList(map);
    }
    
    /** 根据交易编号查询买断式回购信息
	 * @param tradeId 交易编号
	 * @return 买断式回购信息
	 */
    public TrdOutRightRepoTradeDetailDto queryBuyOutRepoByTradeId(String tradeId){
    	return trdOutRightRepoTradeMapper.queryBuyOutRepoByTradeId(tradeId);
    }

    /**
     * 根据交易编号检索组合持仓流水（持仓类型为融入融出）
     *
     * @param bizCode 交易编号
     * @return List<PtlSecPositionFlowDto>
     * @see
     * @since 1.0
     */
    public List<PtlSecPositionFlow> selectPtlSecPositionFlow(String bizCode, String holdingType) {
        Map<String, Object> param = setParams(bizCode, holdingType);
        return this.trdOutRightRepoTradeMapper.selectPtlSecPositionFlow(param);
    }

    /**
     * 根据交易编号删除组合持仓流水（持仓类型为融入融出）
     *
     * @param bizCode 交易编号
     * @return List<PtlSecPositionFlowDto>
     * @see
     * @since 1.0
     */
    public long deletePtlSecPositionFlow(String bizCode, String holdingType) {
        Map<String, Object> param = setParams(bizCode, holdingType);
        updatePledgeBondForRevoke(bizCode, holdingType, true);
        return this.trdOutRightRepoTradeMapper.deletePtlSecPositionFlow(param);
    }

    private static Map<String, Object> setParams(String bizCode, String holdingType) {
        Map<String, Object> param = new HashMap<>();
        param.put("bizCode", bizCode);
        if (StringUtil.isNotEmpty(holdingType) && !"null".equals(holdingType)) {
            param.put("POSITION_TYPE_ROLLIN", holdingType);
            param.put("POSITION_TYPE_ROLLOUT", holdingType);
        }
        else {
            param.put("POSITION_TYPE_ROLLIN", DictCodeList.持仓类型_融入.getValue());
            param.put("POSITION_TYPE_ROLLOUT", DictCodeList.持仓类型_融出.getValue());
        }
        return param;
    }

    /**
     * 查询投资委托信息
    *
    * @param entrustId 委托代码
    * @return 投资委托信息
    * @see
    * @since 1.0
    */
   public BaseEtrInvestInfoDto getEntrustInfo(String entrustId) {
       if (baseEtrInvestInfoService == null) {
           return new BaseEtrInvestInfoDto();
       }
       BaseEtrInvestInfoDto resultDto = baseEtrInvestInfoService.queryActiveMandate(entrustId);
       if (resultDto != null && resultDto.getEtrEntrust() != null) {
           // 委托数量中扣除已完成额度
           if (!StringUtil.isEmptyList(resultDto.getEtrFinishStatusList())) {
               BigDecimal 已完成额度 = BigDecimal.ZERO;
               for (EtrFinishStatus etrFinishStatus : resultDto.getEtrFinishStatusList()) {
                   已完成额度 = ArithmeticUtil.doAdd(已完成额度,etrFinishStatus.getFinishAmt());
               }

               resultDto.getEtrEntrust().setEntrustNum(
                       ArithmeticUtil.subtract(resultDto.getEtrEntrust().getEntrustNum(), 已完成额度));
           }
       }
       if (resultDto != null && resultDto.getEtrEntrustAdd() != null) {
           // 清算速度
           if (resultDto.getEtrEntrustAdd().getSettleSpreed() == null
                   || resultDto.getEtrEntrustAdd().getSettleSpreed() == 0L) {
               resultDto.setSettleSpeedStr(DictCodeList.清算速度_T_0.getValue());
           } else {
               resultDto.setSettleSpeedStr(DictCodeList.清算速度_T_1.getValue());
           }
       }
       return resultDto;
   }
   
   /** 检查日期区间是否有 付息计划
    * @param param 参数
    * @return 是否有 付息计划
    */
   public int checkDateInPayPlan(Map<String, Object> param){
	   param.put("cashType2", DictCodeListExt1.现金流二级类型_利息.getValue());
	   return trdOutRightRepoTradeMapper.checkDateInPayPlan(param);
   }
   
   /** 查询委托的剩余额度
    * @param param 参数
    * @return 
    */
   public BigDecimal selectDoEntrustMoney(Map<String, Object> param){

       return trdOutRightRepoTradeMapper.selectDoEntrustMoney(param);
   }
   
   /** 根据交易编号删除质押品信息
    * @param tradeId 交易编号
    * @return Db
    */
   public int deleteFinDealPledgeByTradeId(String tradeId){
	   return trdOutRightRepoTradeMapper.deleteFinDealPledgeByTradeId(tradeId);
   }
   
   /** 根据交易编号删除交易类金融产品
    * @param tradeId 交易编号
    * @return Db
    */
   public int deleteFinTradeProductByTradeId(String tradeId){
	   return trdOutRightRepoTradeMapper.deleteFinTradeProductByTradeId(tradeId);
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
     * 更新实际并更新融入、融出或质押position持仓
     * @param bizCode
     * @param holdingType
     * @param 作废
     * @return
     */
    public long updatePledgeBondForRevoke(String bizCode, String holdingType, boolean 作废) {
        Map<String, Object> param = setParams(bizCode, DictCodeList.持仓类型_实际.getValue());
        List<PtlSecPositionFlow> ptlSecPositionFlows;
        if (!作废) {
            ptlSecPositionFlows = 更新持仓(param);
        }
        param = setParams(bizCode, holdingType);
        ptlSecPositionFlows = 更新持仓(param);
        return ptlSecPositionFlows.size();
    }

    private List<PtlSecPositionFlow> 更新持仓(Map<String, Object> param) {
        List<PtlSecPositionFlow> ptlSecPositionFlows = trdOutRightRepoTradeMapper.selectPtlSecPositionFlow(param);
        for (PtlSecPositionFlow flow : ptlSecPositionFlows) {
            PtlSecPosition position = new PtlSecPosition();
            BeanUtil.copyPropertiesIgnoreNull(position, flow);
            flow.setAmount(flow.getAmount().negate());
            flow.setSeqno(IDGenerator.generate());
            ptlSecPositionFlowMapper.insert(flow);
            trdOutRightRepoTradeMapper.updatePosition(position);
        }
        return ptlSecPositionFlows;
    }
    /**
     * 根据委托代码查询首次净价区间
     * @param entrustId
     * @return
     */
    public TrdOutRightRepoTradeDto getfCpriceBtw(String entrustId) {
        return trdOutRightRepoTradeMapper.getfCpriceBtw(entrustId);
    }
    /**
     * 根据参数删除组合持仓流水
     *
     * @param param 参数列表
     * @return Boolean
     * @see
     * @since 1.0
     */
    public Boolean deletePtlSecPositionFlowByParm(Map<String, Object> param) {
        return trdOutRightRepoTradeMapper.deletePtlSecPositionFlowByParm(param);
    }


    public List<FinCashPlan> getFinCashPlanForRepoRate(Map<String, Object> param){
        param.put("cashType2", DictCodeListExt1.现金流二级类型_利息.getValue());
        return trdOutRightRepoTradeMapper.getFinCashPlanForRepoRate(param);
    }
    
    public String findCeTradeIdByTradeId(String tradeId) {
        return trdOutRightRepoTradeMapper.findCeTradeIdByTradeId(tradeId);
    }
}
