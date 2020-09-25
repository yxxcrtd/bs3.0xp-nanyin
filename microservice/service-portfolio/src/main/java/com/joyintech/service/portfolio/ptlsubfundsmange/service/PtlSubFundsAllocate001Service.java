/*
 * 文件名：PtlSubFundsAllocate001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：niuqianyu
 * 修改时间：2019年8月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.portfolio.ptlsubfundsmange.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.BeanUtil;
import com.joyintech.service.portfolio.ptlsubfundsmange.dto.PtlSubFundsAllocateDataDto;
import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.dto.portfolio.dto.FinSubFundsDealDbDataDto;
import com.joyintech.tams.invest.common.service.MapperDelegateService;
import com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.portfolio.ptlsubfundsmange.dao.PtlSubFundsAllocate001Mapper;
import com.joyintech.service.portfolio.ptlsubfundsmange.dto.PtlSubFundsAllocateDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 子组合间资金调配
 *
 * @author niuqianyu
 * @version 1.0 2019年8月15日
 * @see PtlSubFundsAllocate001Service
 * @since 1.0
 */
@Service
public class PtlSubFundsAllocate001Service extends BaseService<PtlSubFundsAllocateDto> {

    @Autowired
    private PtlSubFundsAllocate001Mapper ptlSubFundsAllocate001Mapper;

    @Autowired
    private MapperDelegateService bussCommonMapperDelegateService;

    /**
     * 子组合间资金调配-组合代码联想框
     *
     * @param pageParam
     * @return
     */
    public Page<SelectOptionDto> getPortfolioId(Map<String, Object> pageParam, int page, int length) {
        pageParam.put("loginUserName", LoginUserUtil.getUserName());
        pageParam.put("portfolioType", DictCodeListExt1.组合类型_投资类组合.getValue());
        pageParam.put("investYesOrNo", DictCodeList.是否_否.getValue());
        return super.queryForPageForSelectReturn(page, length,
                () -> ptlSubFundsAllocate001Mapper.getPortfolioId(pageParam));
    }

    /**
     * 子组合间资金调配-子组合代码联想框
     *
     * @param pageParam
     * @return
     */
    public Page<SelectOptionDto> getSubPortfolioId(Map<String, Object> pageParam, int page, int length) {
        pageParam.put("portfolioType", DictCodeListExt1.组合类型_子组合.getValue());
        return super.queryForPageForSelectReturn(page, length,
                () -> ptlSubFundsAllocate001Mapper.getSubPortfolioId(pageParam));
    }

    /**
     * 子组合间资金调配-分页查询
     *
     * @param page
     * @param length
     * @param pageParam
     * @return
     */
    public Page<PtlSubFundsAllocateDto> findFundsAllocatePage(int page, int length, Map<String, Object> pageParam) {
        //pageParam.put("busiType", DictCodeList.业务类型_净值型TA交易.getValue());// 数据字典缓存.业务类型.净值型TA交易
        pageParam.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());// 数据字典缓存.金融产品类型_投管用_理财产品
        pageParam.put("paBussType", DictCodeListExt1.平安业务类型_子组合资金调配.getValue());// 数据字典缓存.平安业务类型.产品交易资金分配
        pageParam.put("userId", LoginUserUtil.getUserName());
        pageParam.put("isCancle", DictCodeList.是否_是.getValue());
        return super.queryForPage(page, length, () -> ptlSubFundsAllocate001Mapper.findFundsAllocatePage(pageParam));
    }

    /**
     * 子组合间资金调配-导出
     *
     * @param pageParam
     * @return
     */
    public List<PtlSubFundsAllocateDto> selectFundsAllocateList(Map<String, Object> pageParam) {
        pageParam.put("busiType", DictCodeList.业务类型_净值型TA交易.getValue());// 数据字典缓存.业务类型.净值型TA交易
        pageParam.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());// 数据字典缓存.金融产品类型_投管用_理财产品
        pageParam.put("paBussType", DictCodeListExt1.平安业务类型_子组合资金调配.getValue());// 数据字典缓存.平安业务类型.子组合资金调配
        pageParam.put("userId", LoginUserUtil.getUserName());
        pageParam.put("isCancle", DictCodeList.是否_是.getValue());
        return ptlSubFundsAllocate001Mapper.findFundsAllocatePage(pageParam);
    }

    /**
     * 子组合间资金调配-组合代码联想框
     *
     * @param param
     * @return
     */
    public List<SelectOptionDto> getPortfolioIds(Map<String, Object> param) {
        param.put("loginUserName", LoginUserUtil.getUserName());
        param.put("portfolioType", DictCodeListExt1.组合类型_投资类组合.getValue());
        param.put("investYesOrNo", DictCodeList.是否_否.getValue());
        return ptlSubFundsAllocate001Mapper.getPortfolioIds(param);
    }

    public Page<PtlSubFundsAllocateDto> getSubFundsAllocate(int page, int length, Map<String, Object> pageParam)
            throws Exception {
        pageParam.put("portfolioId", pageParam.get("portfolioId2"));
        //update by leipeng 20190910  按设计修改添加查询逻辑
        pageParam.put("acctType", DictCodeListExt1.资金账户类型_产品托管户.getValue());
        Page<PtlSubFundsAllocateDto> resultPage = new Page<PtlSubFundsAllocateDto>();
        resultPage = super.queryForPage(page, length, () -> ptlSubFundsAllocate001Mapper.getSubFundsAllocate(pageParam));

        this.processSubFundsAllocate(pageParam, resultPage);
        return resultPage;
    }

    public Page<PtlSubFundsAllocateDto> selectOther(int page, int length, Map<String, Object> pageParam)
            throws Exception {
        pageParam.put("hodingType", DictCodeList.持仓类型_实际.getValue());
        //update by leipeng 20190910  按设计修改添加查询逻辑
        pageParam.put("acctType", DictCodeListExt1.资金账户类型_产品托管户.getValue());
        Page<PtlSubFundsAllocateDto> resultPage = super.queryForPage(page, length, () -> ptlSubFundsAllocate001Mapper.selectOther(pageParam));
        this.processSubFundsAllocate(pageParam, resultPage);
        return resultPage;
    }

    public Page<PtlSubFundsAllocateDto> processSubFundsAllocate(Map<String, Object> pageParam, Page<PtlSubFundsAllocateDto> resultPage)
            throws Exception {
        if (!StringUtil.isEmptyList(resultPage.getRoot())) {
            BigDecimal actualAmt = BigDecimal.ZERO;
            BigDecimal avaAmt = BigDecimal.ZERO;
            BigDecimal ajustActualAmt = BigDecimal.ZERO;
            BigDecimal ajustAvaAmt = BigDecimal.ZERO;
            BigDecimal tradeAmt = BigDecimal.ZERO;
            for (PtlSubFundsAllocateDto resultDto : resultPage.getRoot()) {
                pageParam.put("hodingType", DictCodeList.持仓类型_实际.getValue());
                pageParam.put("subPortfolioId", resultDto.getSubPortfolioId());
                pageParam.put("acctType", DictCodeListExt1.资金账户类型_产品托管户.getValue());
            	/*
                List<PtlSubFundsAllocateDto> dtoList = SumCashFlowActualAmt(pageParam);
                if(dtoList.size()>0) {
                	if(resultDto.getActualAmt() != null)
                		actualAmt = resultDto.getActualAmt();
                	avaAmt = getSumCashFlowAvaAmt(pageParam,dtoList.get(0));
                    avaAmt = avaAmt.add(actualAmt);
            	}*/
                if (resultDto.getActualAmt() != null)
                    actualAmt = resultDto.getActualAmt();
                avaAmt = getSumCashFlowAvaAmt(pageParam, resultDto);
                avaAmt = avaAmt.add(actualAmt);
                if (resultDto.getTradeAmt() != null)
                    tradeAmt = resultDto.getTradeAmt();
                if (StringUtil.isNotEmpty(resultDto.getTotleTradeId())) {
                    ajustActualAmt = actualAmt;
                    ajustAvaAmt = avaAmt;
                } else {
                    ajustActualAmt = actualAmt.add(tradeAmt);
                    ajustAvaAmt = avaAmt.add(tradeAmt);
                }
                resultDto.setActualAmt(actualAmt);
                resultDto.setAvaAmt(avaAmt);
                resultDto.setTradeAmt(tradeAmt);
                resultDto.setAjustActualAmt(ajustActualAmt);
                resultDto.setAjustAvaAmt(ajustAvaAmt);
            }
        }
        return resultPage;
    }

    public BigDecimal getCashFlowActualAmt(Map<String, Object> param) {
        param.put("hodingType", DictCodeList.持仓类型_实际.getValue());
        BigDecimal actualAmt = BigDecimal.ZERO;
        List<PtlSubFundsAllocateDto> dtoList = SumCashFlowActualAmt(param);
        if (dtoList.size() > 0 && dtoList.get(0).getActualAmt() != null)
            actualAmt = dtoList.get(0).getActualAmt();
        return actualAmt;
    }

    public BigDecimal getSumCashFlowAvaAmt(Map<String, Object> param, PtlSubFundsAllocateDto dto) {
        BigDecimal avaAmt = BigDecimal.ZERO;
        param.put("hodingType", DictCodeList.持仓类型_可用.getValue());
        param.put("subPortfolioId", dto.getSubPortfolioId());
        param.put("ccy", dto.getCcy());
        List<PtlSubFundsAllocateDto> dtoList = SumCashFlowAvaAmt(param);
        if (dtoList.size() > 0 && dtoList.get(0).getAvaAmt() != null)
            avaAmt = dtoList.get(0).getAvaAmt();
        return avaAmt;
    }


    public BigDecimal getSumCashFlowAvaAmt1(Map<String, Object> param, PtlSubFundsAllocateDto dto) {
        BigDecimal avaAmt = BigDecimal.ZERO;
        param.put("subPortfolioId", dto.getSubPortfolioId());
        param.put("ccy", dto.getCcy());
        //update by leipeng 20190910  按设计修改添加查询逻辑
        param.put("acctType", DictCodeListExt1.资金账户类型_产品托管户.getValue());
        List<PtlSubFundsAllocateDto> dtoList = SumCashFlowAvaAmt(param);
        if (dtoList.size() > 0 && dtoList.get(0).getAvaAmt() != null)
            avaAmt = dtoList.get(0).getAvaAmt();
        return avaAmt;
    }

    public List<PtlSubFundsAllocateDto> SumCashFlowActualAmt(Map<String, Object> map) {
        return ptlSubFundsAllocate001Mapper.SumCashFlowActualAmt(map);
    }

    public List<PtlSubFundsAllocateDto> SumCashFlowAvaAmt(Map<String, Object> map) {
        return ptlSubFundsAllocate001Mapper.SumCashFlowAvaAmt(map);
    }

    public String insertBatch(FinProductTradeDto finProductTradeDto) {
        boolean isAdd = false;
        for (BaseTradeProductDealDto tradeDto : finProductTradeDto.getBaseTradeProductDealDtos()) {
            // 金融产品交易表
            TrdProductDeal trdProductDeal = new TrdProductDeal();
            BeanUtil.copyPropertiesIgnoreNull(trdProductDeal, tradeDto.getTrdProductDeal());
            trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());

            // 金融产品交易扩展表
            PaTrdProductDeal paTrdProductDeal = new PaTrdProductDeal();
            BeanUtil.copyPropertiesIgnoreNull(paTrdProductDeal, tradeDto.getPaTrdProductDeal());

            // 金融产品交易附属表
            TrdProductDealAdd trdProductDealAdd = new TrdProductDealAdd();
            BeanUtil.copyPropertiesIgnoreNull(trdProductDealAdd, tradeDto.getTrdProductDealAdd());

            // 组合交易表Entity
            PtlTrade ptlTrade = new PtlTrade();
            BeanUtil.copyPropertiesIgnoreNull(ptlTrade, tradeDto.getPtlTrade().get(0));


            // 批量插入
            bussCommonMapperDelegateService.insertBatch(TrdProductDealMapper.class,
                    trdProductDeal, PaTrdProductDealMapper.class, paTrdProductDeal,
                    TrdProductDealAddMapper.class, trdProductDealAdd, PtlTradeMapper.class, ptlTrade);

            List<StlPayOrdersDto> stlPayOrdersList = tradeDto.getStlPayOrdersList();
            if (stlPayOrdersList != null && stlPayOrdersList.size() > 0) {
                bussCommonMapperDelegateService.insertAutoClassifyBatch(stlPayOrdersList);
            }

            if (!isAdd) {
                TrdFinProductDealTotal trdFinProductDealTotal = new TrdFinProductDealTotal();
                BeanUtil.copyPropertiesIgnoreNull(trdFinProductDealTotal, tradeDto.getTrdFinProductDealTotal());
                trdFinProductDealTotal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
                PaTrdFinProductDealTotal paTrdFinProductDealTotal = new PaTrdFinProductDealTotal();
                BeanUtil.copyPropertiesIgnoreNull(paTrdFinProductDealTotal, tradeDto.getPaTrdFinProductDealTotal());
                bussCommonMapperDelegateService.insertBatch(TrdFinProductDealTotalMapper.class,
                        trdFinProductDealTotal, PaTrdFinProductDealTotalMapper.class, paTrdFinProductDealTotal);
                isAdd = true;
            }
        }
        return "Success";
    }
    
    @Autowired
    private WflTrdProductDealMapper wflTrdProductDealMapper;

    @Autowired
    private WflTrdProductDealAddMapper wflTrdProductDealAddMapper;

    @Autowired
    private WflPaTrdProductDealMapper wflPaTrdProductDealMapper;

    @Autowired
    private WflTrdFinProductDealTotalMapper wflTrdFinProductDealTotalMapper;

    @Autowired
    private WflPaTrdFinDealTotalMapper wflPaTrdFinDealTotalMapper;

    @Autowired
    private WflStlPayOrdersMapper wflStlPayOrdersMapper;
    public String deleteWflData(String workflowId) {
        // 金融产品交易信息DTO.金融产品交易Entity -> 金融产品交易业务流程表
    	  wflTrdProductDealMapper.deleteByWorkflowId(workflowId);

          // 金融产品交易信息DTO.金融产品交易附属表Entity -> 金融产品交易附属业务流程表
          wflTrdProductDealAddMapper.deleteByWorkflowId(workflowId);

          // 金融产品交易扩展表Entity -> 金融产品交易扩展业务流程表
          wflPaTrdProductDealMapper.deleteByWorkflowId(workflowId);

          // 金融产品交易汇总业务流程表
          wflTrdFinProductDealTotalMapper.deleteByWorkflowId(workflowId);

          // 金融产品交易汇总扩展业务流程表
          wflPaTrdFinDealTotalMapper.deleteByWorkflowId(workflowId);

          wflStlPayOrdersMapper.deleteByWorkflowId(workflowId);
          return "Success";
      }

    public String handleBussFlowData(PtlSubFundsAllocateDataDto entity) {
        if(StringUtil.isNotEmpty(entity.getWorkflowId())){
            deleteWflData(entity.getWorkflowId());
        }
    	boolean isAdd = false;
        for (FinSubFundsDealDbDataDto tradeDto : entity.getSubFundsDealDbDataList()) {
            if (!isAdd) {
                WflTrdFinProductDealTotal wflTrdFinProductDealTotal = new WflTrdFinProductDealTotal();
                WflPaTrdFinDealTotal wflPaTrdFinDealTotal = new WflPaTrdFinDealTotal();
                BeanUtil.copyPropertiesIgnoreNull(wflTrdFinProductDealTotal, tradeDto.getTrdFinProductDealTotal());
                wflTrdFinProductDealTotal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
                wflTrdFinProductDealTotal.setWorkflowId(entity.getWorkflowId());
                wflTrdFinProductDealTotal.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
                wflTrdFinProductDealTotalMapper.insertSelective(wflTrdFinProductDealTotal);

                BeanUtil.copyPropertiesIgnoreNull(wflPaTrdFinDealTotal, tradeDto.getPaTrdFinProductDealTotal());
                wflPaTrdFinDealTotal.setWorkflowId(entity.getWorkflowId());
                wflPaTrdFinDealTotal.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
                wflPaTrdFinDealTotalMapper.insertSelective(wflPaTrdFinDealTotal);
                isAdd = true;
            }

            WflTrdProductDealAdd wflTrdProductDealAdd = new WflTrdProductDealAdd();
            BeanUtil.copyPropertiesIgnoreNull(wflTrdProductDealAdd, tradeDto.getTrdProductDealAdd());
            wflTrdProductDealAdd.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
            wflTrdProductDealAdd.setWorkflowId(entity.getWorkflowId());
            wflTrdProductDealAddMapper.insertSelective(wflTrdProductDealAdd);

            WflTrdProductDeal wflTrdProductDeal = new WflTrdProductDeal();
            BeanUtil.copyPropertiesIgnoreNull(wflTrdProductDeal, tradeDto.getTrdProductDeal());
            wflTrdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
            wflTrdProductDeal.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
            wflTrdProductDeal.setWorkflowId(entity.getWorkflowId());
            wflTrdProductDealMapper.insertSelective(wflTrdProductDeal);

            WflPaTrdProductDeal wflPaTrdProductDeal = new WflPaTrdProductDeal();
            BeanUtil.copyPropertiesIgnoreNull(wflPaTrdProductDeal, tradeDto.getPaTrdProductDeal());
            wflPaTrdProductDeal.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
            wflPaTrdProductDeal.setWorkflowId(entity.getWorkflowId());
            wflPaTrdProductDealMapper.insertSelective(wflPaTrdProductDeal);

            WflStlPayOrders wflStlPayOrders = new WflStlPayOrders();
            if (tradeDto.getBussTrdTransferOrderDto() != null) {
                BeanUtil.copyPropertiesIgnoreNull(wflStlPayOrders, tradeDto.getBussTrdTransferOrderDto());
                wflStlPayOrders.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
                wflStlPayOrders.setWorkflowId(entity.getWorkflowId());
                // wflStlPayOrdersMapper.insert(wflStlPayOrders);
                if (wflStlPayOrders.getOrderId() != null) {
                    wflStlPayOrdersMapper.insertSelective(wflStlPayOrders);
                }
            }
        }
        return "Success";
    }
    
    public String getProfitTypeById(Map<String, Object> map) {
    	List<String> list = ptlSubFundsAllocate001Mapper.getProfitTypeById(map);
    	if(list != null && list.size()>0)
    		return list.get(0);
    	else 
    		return null;
    }
}
