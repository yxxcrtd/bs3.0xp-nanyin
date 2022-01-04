/*
 * 文件名：TrdDepositInvestTrade001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.trademanage.trddepositvaluationtrade.service;
import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;

import com.joyintech.service.valuation.trademanage.trddepositvaluationtrade.dao.TrdDepositValuationTrade001Mapper;
import com.joyintech.service.valuation.trademanage.trddepositvaluationtrade.dto.TrdDepositValuationTrade001Dto;
import com.joyintech.service.valuation.trademanage.trdplanratevaluationtrade.dto.TrdPlanRateValuationTradeDto;
import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.InterfSendOpics;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * 存款类投资交易Service
 * @author fanshuanghu
 * @version 1.0 2018年10月10日
 * @see TrdDepositValuationTrade001Service
 * @since 1.0
 */
@Service("backTrdDepositInvestTrade001Service")
@Transactional(rollbackFor=Exception.class)
public class TrdDepositValuationTrade001Service extends BaseService<TrdDepositValuationTrade001Dto> {
//    @Autowired(required = false)
//    private BaseTradeProductDealCommService baseTradeProductDealCommService;
//    @Autowired
//    private WflPaTrdProductDealMapper wflPaTrdProductDealMapper;
////    @Autowired
////    private FinProductMapper finProductMapper;
//    @Autowired
//    private WflStlPayOrdersMapper wflStlPayOrdersMapper;
//    @Autowired
//    private WflTrdProductDealMapper wflTrdProductDealMapper;
//    @Autowired
//    private WflTrdProductDealAddMapper wflTrdProductDealAddMapper;
//    /**
//     * 组合交易表处理Mapper
//     */
//    @Autowired
//    private PtlTradeMapper ptlTradeMapper;
//    /**
//     * 金融产品交易Mapper
//     */
//    @Autowired
//    private PaTrdProductDealMapper paTrdProductDealMapper;
//
//    /**
//     * 组合可用持仓和实际持仓Service
//     */
//    @Autowired(required = false)
//    private BasePtlSecPositionInfoService basePtlSecPositionInfoService;
//    /**
//     * 组合可用现金和实际现金Service
//     */
//    @Autowired(required = false)
//    private BasePtlCashPositionInfoService basePtlCashPositionInfoService;
//    /**
//     * 获取平台日组件
//     */
//    @Autowired(required = false)
//    private IPlatformDate iPlatformDate;
    /**
     * 注入存款类投资交易mapper
     */
    @Autowired
    private TrdDepositValuationTrade001Mapper trdDepositValuationTrade001Mapper;

    /**
     * 产品代码下拉框
     * @auther: Administrator
     * @date: 2021/12/29 16:47
     * @param:
     * @return:
     */
    public Page<SelectOptionDto> findPortfolioId(int page, int length,Map<String, Object> params) {
        return this.queryForPageForSelectReturn(page, length, ()->trdDepositValuationTrade001Mapper.findPortfolioId(params));
    }

    //资产代码下拉框
    public Page<SelectOptionDto> findFinprodId(int page, int length,Map<String, Object> params){
        return this.queryForPageForSelectReturn(page, length, ()->trdDepositValuationTrade001Mapper.findFinprodId(params));
    }

    //交易对手
    public Page<SelectOptionDto> getCounterId(int page, int length, Map<String, Object> param) {
        return queryForPageForSelectReturn(page, length, () -> trdDepositValuationTrade001Mapper.getCounterId(param));
    }

    /**
     *
     * 获取分页查询结果（sharesId  dealDateString）
     * <功能详细描述>
     * @param pageParam Map<String, Object>
     * @return Page<MstSharePriceDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    public Page<TrdDepositValuationTrade001Dto> selectForPage(int page, int length, Map<String, Object> pageParam) throws Exception {
//        return super.queryForPage(page,length,()-> trdDepositValuationTrade001Mapper.select(pageParam));
        Page<TrdDepositValuationTrade001Dto> pagelist = new Page<>();
        List<TrdDepositValuationTrade001Dto> planList = new ArrayList<>();
        pagelist.setRoot(planList);
        return pagelist;
    }

//    /**
//     * 分页查询
//     * @param map 查询参数
//     * @return 存款类投资交易实体类
//     * @throws Exception
//     * @see
//     * @since 1.0
//     */
//    public Page<TrdDepositInvestTrade001Dto> selectForPage(int page, int length, Map<String,Object> map) throws Exception{
//        return super.queryForPage(page, length, () -> trdDepositInvestTrade001Mapper.selectForPage(map));
//    }
//
//    /**
//     * 获取委托代码联想框集合
//     * @param entrustId 委托代码入参
//     * @return 联想文本框实体类
//     * @see
//     * @since 1.0
//     */
//    public List<SuggestDto> getEntrustId(String entrustId){
//        return trdDepositInvestTrade001Mapper.getEntrustId(entrustId);
//    }
//    /**
//     * 获取委组合代码联想框集合
//     * @param map 组合代码入参
//     * @param page
//     * @param length
//     * @return 联想文本框实体类
//     * @see
//     * @since 1.0
//     */
//    public Page<SelectOptionDto> getPortfolioId(int page, int length, Map<String, Object> map){
//        map.put("portType", DictCodeListExt1.组合类型_投资类组合.getValue());
//        map.put("portType1", DictCodeListExt1.组合类型_子组合.getValue());
//        return super.queryForPageForSelectReturn(page, length, () -> trdDepositInvestTrade001Mapper.getPortfolioId(map));
//    }
//    /**
//     * 获取资产代码联想框集合
//     * @param finprodMarketId 资产代码入参
//     * @return 联想文本框实体类
//     * @see
//     * @since 1.0
//     */
//    public List<SelectOptionDto> getFinprodMarketId2(Map<String,Object> map){
//        map.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_存款.getValue());
//        return trdDepositInvestTrade001Mapper.getFinprodMarketId2(map);
//    }
//    /**
//     * 获取资产代码联想框集合
//     * @param finprodMarketId 资产代码入参
//     * @return 联想文本框实体类
//     * @see
//     * @since 1.0
//     */
//    public List<SuggestDto> getFinprodMarketId(String finprodMarketId){
//        Map<String,Object> map = new HashMap<>();
//        map.put("finprodMarketId", finprodMarketId);
//        map.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_存款.getValue());
//        return trdDepositInvestTrade001Mapper.getFinprodMarketId(map);
//    }
//    /**
//     * 获取资产提供行下拉框列表
//     * @return 下拉框列表
//     * @see
//     * @since 1.0
//     */
//    public List<SelectOptionDto> getAssetProvider(Map<String,Object> map){
//        return trdDepositInvestTrade001Mapper.getAssetProvider(map);
//    }
//    /**
//     * 由资产代码带出各种表单元素
//     * @param finprodId 资产代码
//     * @return 资产类型、计息基础等
//     * @see
//     * @since 1.0
//     */
//    public TrdDepositInvestTrade001Dto getFinInfo(String finprodId){
//        Map<String,Object> map = new HashMap<String,Object>();
//        map.put("finprodId", finprodId);
//        map.put("cashType1", DictCodeListExt1.现金流一级类型_利息.getValue());
//        map.put("cashType2", DictCodeListExt1.现金流二级类型_利息.getValue());
//        TrdDepositInvestTrade001Dto dto = trdDepositInvestTrade001Mapper.getFinInfo(map);
//        dto.setTradeDate(DateUtil.parse(DateUtil.getSysDate()));
//        return dto;
//    }
//    /**
//     * 是否保本
//     * @param portfolioId 组合代码
//     * @return 是否
//     * @see
//     * @since 1.0
//     */
//    public String getIsGuarantee(String portfolioId){
//        return trdDepositInvestTrade001Mapper.getIsGuarantee(portfolioId);
//    }
//    /**
//     * 查询组合可用持仓和可用现金
//     * @param map 查询参数
//     * @return 数值集合
//     * @see
//     * @since 1.0
//     */
//    public List<SuggestDto> available(Map<String,Object> map){
//        List<SuggestDto> list = new ArrayList<SuggestDto>();
//        if(map.get("ps").equals(DictCodeList.交易方向_买入.getValue())){//判断是买入还是卖出然后获取流水信息
//            List<String> acctTypeList = new ArrayList<>();
//            acctTypeList.add(DictCodeListExt1.资金账户类型_产品托管户.getValue());
//            PtlCashPositionFlowDto dto = new PtlCashPositionFlowDto();
//            dto.setPortfolioId(map.get("portfolioId").toString());
//            dto.setFinprodId(map.get("finprodId").toString());
//            dto.setCdate(DateUtil.parse(map.get("tradeDate").toString()));
//            dto.setAcctType(acctTypeList);
////            dto.setHodingType(DictCodeList.持仓类型_可用.getValue());
//            dto.setCcy(map.get("ccy").toString());
//            BigDecimal s = basePtlCashPositionInfoService.queryUsableCashPosition(dto);
////            dto.setCdate(DateUtil.addDay(DateUtil.parse(map.get("tradeDate").toString()), day));
//            SuggestDto sDto = new SuggestDto();
//
//            final Date tradeDay = DateUtil.parse(map.get("tradeDay").toString());
//            dto.setCdate(tradeDay);
//            BigDecimal s1 = basePtlCashPositionInfoService.queryUsableCashPosition(dto);
//
//            acctTypeList.clear();
//            acctTypeList.add(DictCodeListExt1.资金账户类型_券商资金账户.getValue());
//            BigDecimal s4 = basePtlCashPositionInfoService.queryUsableCashPosition(dto);
//            sDto = new SuggestDto();
//            sDto.setCode("T1");
//            sDto.setName(s1.toString());
//            list.add(sDto);
//            sDto = new SuggestDto();
//            sDto.setCode("T2");
//            sDto.setName(s.toString());
//            list.add(sDto);
//
//            // 通道交易 新增 强成西 2019/03/26 start
//            if (StringUtil.isNotEmpty(map.get("chlId"))) {
//                dto.setCdate(tradeDay);
//                dto.setAcctType(null);
//                dto.setChlFinprodId((String) map.get("chlId"));
//                BigDecimal s2 = basePtlCashPositionInfoService.queryUsableCashPosition(dto);
//                s = ArithmeticUtil.doAdd(s, s2 == null ? BigDecimal.ZERO : s2);
//                sDto = new SuggestDto();
//                sDto.setCode("T3");
//                sDto.setName(s2.toString());
//                list.add(sDto);
//            }
//            else {
//                sDto = new SuggestDto();
//                sDto.setCode("T3");
//                sDto.setName("");
//                list.add(sDto);
//            }
//            // 通道交易 新增 强成西 2019/03/26 end
//
//            sDto = new SuggestDto();
//            sDto.setCode("T4");
//            sDto.setName(s4.toString());
//            list.add(sDto);
//            s = s = ArithmeticUtil.doAdd(s, s4 == null ? BigDecimal.ZERO : s4);
//            sDto.setCode("T");
//            sDto.setName(s.toString());
//            list.add(0, sDto);
//
////            String acctNo = getAcctNo(map);//等待持仓流水接口完成
//        }else{
//            PtlSecPositionFlowDto dto = new PtlSecPositionFlowDto();
//            dto.setPortfolioId(map.get("portfolioId").toString());
//            dto.setFinprodId(map.get("finprodId").toString());
//            dto.setCdate(DateUtil.parse(map.get("tradeDate").toString()));
//            if (StringUtil.isNotEmpty((String) map.get("chlId"))) {
//                dto.setChannel(true);
//                dto.setSecAcctId((String) map.get("chlId"));
//            } else {
//                dto.setSecAcctId((String) map.get("secAcctId"));
//            }
//            BigDecimal o =basePtlSecPositionInfoService.queryUsableSecPosition(dto);
//            SuggestDto sDto = new SuggestDto();
//            sDto.setCode("T");
//            sDto.setName(o.toString());
//            list.add(sDto);
//        }
//        return list;
//    }
//    /**
//     * 查询交易员
//     * @param map 委托代码，接受状态
//     * @return 交易员
//     * @see
//     * @since 1.0
//     */
//    public String getTrader(Map<String,Object> map){
//        return trdDepositInvestTrade001Mapper.getTrader(map);
//    }
//    /**
//     * 获取账号代码
//     * @return 账号
//     * @see
//     * @since 1.0
//     */
//    public String getAcctNo(Map<String,Object> map){
//        map.put("acctType", DictCodeListExt1.资金账户类型_产品托管户.getValue());
//        return trdDepositInvestTrade001Mapper.getAcctNo(map);
//    }
//    /**
//     * 新建交易
//     * @param dto 存款类投资交易实体类
//     * @return 是否成功
//     * @see
//     * @since 1.0
//     */
//    public int insert(TrdDepositInvestTrade001Dto dto){
//        if (dto.getPtlTrade() != null && !dto.getPtlTrade().isEmpty()) {
//            dto.getPtlTrade().forEach(ptlTrade -> ptlTradeMapper.insert(ptlTrade));
//        }
//        paTrdProductDealMapper.insert(dto.getPaTrdProductDeal());
//        return 1;
//    }
//    /**
//     * 撤销组合交易表和金融产品扩展表数据
//     * @param dto 存款类投资交易实体类
//     * @return 是否成功
//     * @see
//     * @since 1.0
//     */
//    public int undo(TrdDepositInvestTrade001Dto dto){
////        trdDepositInvestTrade001Mapper.updatePtlTrade(dto.getPtlTrade());
////        trdDepositInvestTrade001Mapper.updateTrdProduct(dto.getPaTrdProductDeal());
//        return 1;
//    }
//    /**
//     * 作废清空流程表数据
//     * @param workflowId 工作流ID
//     * @see
//     * @since 1.0
//     */
//    public void doDelete(String workflowId){
//        wflPaTrdProductDealMapper.deleteByWorkflowId(workflowId);
//        wflStlPayOrdersMapper.deleteByWorkflowId(workflowId);
//        wflTrdProductDealMapper.deleteByWorkflowId(workflowId);
//        wflTrdProductDealAddMapper.deleteByWorkflowId(workflowId);
////        trdDepositInvestTrade001Mapper.deleteWflPaTrdProductDeal(workflowId);
////        trdDepositInvestTrade001Mapper.deleteWflStlPayOrders(workflowId);
////        trdDepositInvestTrade001Mapper.deleteWflTrdProductDeal(workflowId);
////        trdDepositInvestTrade001Mapper.deleteWflTrdProductDealAdd(workflowId);
//    }
//    /**
//     * 获取划款指令实体类
//     * @param tradeId 交易编号
//     * @return 还款指令实体类
//     * @see
//     * @since 1.0
//     */
//    public List<StlPayOrders> stlInfo(String tradeId){
//        return trdDepositInvestTrade001Mapper.stlInfo(tradeId);
//    }
//    /**
//     * 获取登记编号
//     * @param map 查询参数
//     * @return 登记编号
//     * @see
//     * @since 1.0
//     */
//    public String getProdRegistCode(Map<String,Object> map){
//        return trdDepositInvestTrade001Mapper.getProdRegistCode(map);
//    }
//    /**
//     * 获取汇率
//     * @param map 币种、日期
//     * @return 汇率
//     * @see
//     * @since 1.0
//     */
//    public BigDecimal getClosePrice(Map<String,Object> map){
//        return trdDepositInvestTrade001Mapper.getClosePrice(map);
//    }
//    /**
//     * 获取opics数据
//     * @param tradeId 交易代码
//     * @return opics实体类
//     * @see
//     * @since 1.0
//     */
//    public List<InterfSendOpics> getOpics(String tradeId){
//        return trdDepositInvestTrade001Mapper.getOpics(tradeId);
//    }
//
//    /**
//     * 获取组合代码
//     * @return 联想文本框
//     * @see
//     * @since 1.0
//     */
//    public List<SuggestDto> getPortfolioIdSug(Map<String,Object> map){
//        map.put("portType", DictCodeListExt1.组合类型_投资类组合.getValue());
//        map.put("portType1", DictCodeListExt1.组合类型_子组合.getValue());
//        return trdDepositInvestTrade001Mapper.getPortfolioIdSug(map);
//    }
//
//    /**
//     * 获取opics下拉框
//     * @param map 请求类型
//     * @return 下拉框
//     * @see
//     * @since 1.0
//     */
//    public List<SelectOptionDto> getSelectOptions(Map<String,Object> map){
//        return trdDepositInvestTrade001Mapper.getSelectOptions(map);
//    }
}
