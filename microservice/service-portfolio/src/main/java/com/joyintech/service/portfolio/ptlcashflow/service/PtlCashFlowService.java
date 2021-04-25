/*
 * 文件名：PtlCashFlowService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.portfolio.ptlcashflow.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.service.portfolio.ptlcashflow.dao.PtlCashFlowMapper;
import com.joyintech.service.portfolio.ptlcashflow.dto.PtlCashFlowDto;
import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.invest.common.component.base.core.impl.PlatformDateImpl;
import com.joyintech.tams.invest.common.component.base.group.BasePtlCashPositionInfoService;
import com.joyintech.tams.invest.common.component.base.trade.BaseTradeProductDealService;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.*;
import com.joyintech.tams.invest.common.dto.common.BussTrdTransferOrderBase;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.service.MapperDelegateService;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.service.BaseEtrCashPositionCommService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiangchengxi
 * @version 1.0 2018年10月26日
 * @see PtlCashFlowService
 * @since 1.0
 */
@Service("backPtlCashFlowService")
public class PtlCashFlowService extends BaseService<PtlCashFlowDto>{
    /**
     * ptlCashFlowMapper
     */
    @Autowired
    private PtlCashFlowMapper ptlCashFlowMapper;
    /**
     * 金融产品交易
     */
    @Autowired
    private BaseTradeProductDealService baseTradeProductDealService;
    /**
     * 组合交易
     */
    @Autowired
    private PtlTradeMapper ptlTradeMapper;
    /**
     * 组合可用现金和实际现金查询更新组件
     */
    @Autowired
    private BasePtlCashPositionInfoService basePtlCashPositionInfoService;
    /**
     * 金融产品交易扩展
     */
    @Autowired
    private PaTrdProductDealMapper paTrdProductDealMapper;
    /**
     * 金融产品交易业务流程表
     */
    @Autowired
    private WflTrdProductDealMapper wflTrdProductDealMapper;
    /**
     * 金融产品交易附属业务流程表
     */
    @Autowired
    private WflTrdProductDealAddMapper wflTrdProductDealAddMapper;
    /**
     * 金融产品交易扩展业务流程表
     */
    @Autowired
    private WflPaTrdProductDealMapper wflPaTrdProductDealMapper;
    /**
     * 划款指令业务流程表
     */
    @Autowired
    private WflStlPayOrdersMapper wflStlPayOrdersMapper;
    /**
     * 交易报送表
     */
    @Autowired
    private RepTradeMapper repTradeMapper;

    @Autowired
    private MapperDelegateService bussCommonMapperDelegateService;
    /**
     * 平台日期
     */
    @Autowired(required=false)
    private PlatformDateImpl platformDateImpl;
    /**
     * 委托指令共通
     */
    @Autowired(required = false)
    private BaseEtrCashPositionCommService baseEtrCashPositionCommService;

    /**
     * baseGetBusinessId
     */
    @Autowired(required = false)
    private BaseGetBusinessId baseGetBusinessId;

    /**
     * StlCapacctInfoMapper
     */
    @Autowired
    private StlCapacctInfoMapper stlCapacctInfoMapper;

    /**
     * 获取业务编号
     * <功能详细描述> 
     * @param param 页面参数
     * @return SuggestDto
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getBusiNo(String param) {
        if("null".equals(param)){
            param  =null;
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("busiNo", param);
        map.put("FINPROD_TYPE_TG_F23", DictCodeListExt1.金融产品类型_投管用_自定义现金流.getValue());
        return ptlCashFlowMapper.getBusiNo(map);
    }

    /**
     * 获取组合代码的联想文本框
     * <功能详细描述>
     * @param param param
     * @param page page
     * @param length length
     * @return SuggestDto
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getPortfolioId(int page, int length,Map<String, Object> param) {
        if("null".equals(param.get("param"))){
            param.put("param", param);
        }
        param.put("FINPROD_TYPE_TG_F23", DictCodeListExt1.金融产品类型_投管用_自定义现金流.getValue());
        return queryForPageForSelectReturn(page, length, () -> ptlCashFlowMapper.getPortfolioId(param));
    }
    /**
     * 获取组合代码的下拉框
     * <功能详细描述>
     * @param param param
     * @return SuggestDto
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getPortfolioIdToNew(Map<String, Object> param) {
        return ptlCashFlowMapper.getPortfolioIdToNew(param);
    }

    /**
     * 获得资产代码
     * <功能详细描述>
     * @param param param
     * @return SuggestDto
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getFinprodMarketId(String param) {
        if("null".equals(param)){
            param  =null;
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("param", param);
        map.put("FINPROD_TYPE_TG_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        map.put("FINPROD_TYPE_TG_F06",  DictCodeListExt1.金融产品类型_投管用_基金.getValue());
        map.put("FINPROD_TYPE_TG_F07",  DictCodeListExt1.金融产品类型_投管用_存款.getValue());
        map.put("FINPROD_TYPE_TG_F17",  DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
        map.put("FINPROD_TYPE_TG_F18",  DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
        // add by etliu 20210421 权益类资产相关代码合并 start
        map.put("FINPROD_TYPE_TG_F37",  DictCodeListExt3.金融产品类型_投管用_权益类资产.getValue());
        // add by etliu 20210421 权益类资产相关代码合并 end
        return ptlCashFlowMapper.getFinprodMarketId(map);
    }

    /**
     * 分页查询
     * <功能详细描述>
     * @param param param 
     * @param page page
     * @param length length
     * @return PtlCashFlowDto
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public Page<PtlCashFlowDto> selectForPage(int page, int length,Map<String, Object> param) throws Exception {
        param.put("FINPROD_TYPE_TG_F23", DictCodeListExt1.金融产品类型_投管用_自定义现金流.getValue());
        if(param.get("portfolioId") != null && !param.get("portfolioId").equals("")){
            List<String> portfolioId = new ArrayList<String>();
            String[] port = param.get("portfolioId").toString().split(",");
            for(int i = 0; i<port.length; i++){
                portfolioId.add(port[i]);
            }
            param.replace("portfolioId", portfolioId);
        }
        else{
            param.replace("portfolioId", null);
        }
        setParams(param);
        return super.queryForPage(page, length,
            () -> ptlCashFlowMapper.selectForPage(param));
    }

    private void setParams(Map<String, Object> param) {
        param.put("FINPROD_TYPE_TG_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        param.put("FINPROD_TYPE_TG_F06",  DictCodeListExt1.金融产品类型_投管用_基金.getValue());
        param.put("FINPROD_TYPE_TG_F07",  DictCodeListExt1.金融产品类型_投管用_存款.getValue());
        param.put("FINPROD_TYPE_TG_F17",  DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
        param.put("FINPROD_TYPE_TG_F18",  DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
        // add by etliu 20210421 权益类资产相关代码合并 start
        param.put("FINPROD_TYPE_TG_F37",  DictCodeListExt3.金融产品类型_投管用_权益类资产.getValue());
        param.put("PINAN_ASSET_TYPE_F19", DictCodeListExt3.平安资产类型_权益类资产.getValue());
        // add by etliu 20210421 权益类资产相关代码合并 end
        param.put("PINAN_ASSET_TYPE_F01", DictCodeList.平安资产类型_债券.getValue());
        param.put("PINAN_ASSET_TYPE_F02", DictCodeList.平安资产类型_基金.getValue());
        param.put("PINAN_ASSET_TYPE_F04", DictCodeList.平安资产类型_计划类资产_净值型.getValue());
        param.put("PINAN_ASSET_TYPE_F03", DictCodeList.平安资产类型_计划类资产_利率型.getValue());
        param.put("PINAN_ASSET_TYPE_F05", DictCodeList.平安资产类型_存款.getValue());
        param.put("PINAN_ASSET_TYPE_F06", DictCodeList.平安资产类型_项目类资产.getValue());
        param.put("PROFIT_TYPE_01", DictCodeList.收益类型_净值型.getValue());
        param.put("PROFIT_TYPE_02", DictCodeList.收益类型_收益型.getValue());
        param.put("YES_OR_NO_N", DictCodeList.是否_否.getValue());
        param.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param param param
     * @param page page
     * @param length length
     * @return SuggestDto
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getCounterId(int page, int length, Map<String, Object> param) {
        return queryForPageForSelectReturn(page, length, () -> ptlCashFlowMapper.getCounterId(param));
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param param param
     * @param page page
     * @param length length
     * @return SuggestDto
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getFinprodIdToNew(int page, int length,Map<String, Object> param) {
        setParams(param);
        return queryForPageForSelectReturn(page, length, () ->ptlCashFlowMapper.getFinprodIdToNew(param));
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map map
     * @return PtlCashFlowDto
     * @see
     * @since 1.0
     */
    public PtlCashFlowDto getEntity(Map<String, Object> map) {
        setParams(map);
        return ptlCashFlowMapper.getEntity(map);
    }

    /**
     * 撤销
     * <功能详细描述>
     * @param dto dto
     * @return int
     * @see
     * @since 1.0
     */
    public int delete(PtlCashFlowDto dto) {
        List<BaseTradeProductDealRevokeDto> baseTradeProductDealRevokeList = new ArrayList<>();
        BaseTradeProductDealRevokeDto baseTradeProductDealRevokeDto = new BaseTradeProductDealRevokeDto();

        String oldTradeId = "";
        if(dto.getBusiNo().lastIndexOf("R") == dto.getBusiNo().length()-1){
            oldTradeId = StringUtil.substring(dto.getBusiNo(),0,dto.getBusiNo().length()-1);
        }else{
            oldTradeId = dto.getBusiNo();
        }

        setBaseTradeProductDealRevokeDto(baseTradeProductDealRevokeDto,dto);
        baseTradeProductDealRevokeList.add(baseTradeProductDealRevokeDto);
        if(baseTradeProductDealService != null){
            baseTradeProductDealService.revoke(baseTradeProductDealRevokeList);
        }
        //修改组合交易表
        PtlTradeKey key = new PtlTradeKey();
        key.setBusiNo(oldTradeId);
        key.setPortfolioId(dto.getPortfolioId());
        key.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
        PtlTrade ptlTrade = ptlTradeMapper.selectByPrimaryKey(key);
        setPtlTradeEntity(ptlTrade,dto);
        ptlTrade.setBusiNo((dto.getBusiNo()));
        ptlTradeMapper.insert(ptlTrade);
        List<BussTrdTransferOrderBase> list = dto.getBussTrdTransferOrder();
        List<PtlCashPositionFlowDto> ptlCash = new ArrayList<>();
        for(BussTrdTransferOrderBase e:list){
            PtlCashPositionFlowDto positionFlowDto = new PtlCashPositionFlowDto();
//            交易加入共通容器
            ptlCash.add(positionFlowDto);
            positionFlowDto.setPortfolioId(e.getPortfolioId());
            if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_收取.getValue())){
                positionFlowDto.setAcctId(e.getRecAcctNo());
            }
            if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_支付.getValue())){
                positionFlowDto.setAcctId(e.getPayAcctNo());
            }
            positionFlowDto.setHodingType(DictCodeList.持仓类型_实际.getValue());
            positionFlowDto.setCdate(e.getSettleDate());
            positionFlowDto.setCcy(e.getCcy());
            if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_收取.getValue())){
                positionFlowDto.setAmount(e.getSettleAmt().multiply(new BigDecimal(-1)));
            }
            if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_支付.getValue())
                    && e.getSettleAmt() != null){
                positionFlowDto.setAmount(e.getSettleAmt());
            }
            positionFlowDto.setOrderId(e.getOrderId());
            if(positionFlowDto.getPortfolioId() != null && positionFlowDto.getAcctId() != null
                    && positionFlowDto.getHodingType() != null && positionFlowDto.getCdate() != null
                    && positionFlowDto.getCcy() != null && positionFlowDto.getAmount() != null
                    && positionFlowDto.getOrderId() != null){
                basePtlCashPositionInfoService.insertCashPositionFlow(positionFlowDto);
            }
        }
        //增加委托指令流水逻辑
        if(baseEtrCashPositionCommService!=null){
            PtlPositionHoldDto ptlHoldDto = new PtlPositionHoldDto();
            ptlHoldDto.setPtlCashPositionFlows(ptlCash);
            baseEtrCashPositionCommService.portfolioEtrCashPosition(ptlHoldDto, true);
        }
        PaTrdProductDeal paTrdProductDeal = new PaTrdProductDeal();
        paTrdProductDeal.setTradeId((dto.getBusiNo()));
        if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_收取.getValue())){
            paTrdProductDeal.setPaBussType(DictCodeListExt1.平安业务类型_自定义现金流收取.getValue());
        }
        if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_支付.getValue())){
            paTrdProductDeal.setPaBussType(DictCodeListExt1.平安业务类型_自定义现金流支付.getValue());
        }
        paTrdProductDealMapper.insert(paTrdProductDeal);
        //删除交易报送信息表
        RepTrade repTrade = getRepTrade(dto);
        if(repTrade != null){
            repTradeMapper.deleteByPrimaryKey(repTrade);
        }
        return 1;
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param baseTradeProductDealRevokeDto baseTradeProductDealRevokeDto
     * @param dto dto
     * @see
     * @since 1.0
     */
    private void setBaseTradeProductDealRevokeDto(BaseTradeProductDealRevokeDto baseTradeProductDealRevokeDto,
             PtlCashFlowDto dto) {
        String oldTradeId = "";
        if(dto.getBusiNo().lastIndexOf("R") == dto.getBusiNo().length()-1){
            oldTradeId = StringUtil.substring(dto.getBusiNo(),0,dto.getBusiNo().length()-1);
        }else{
            oldTradeId = dto.getBusiNo();
        }

        //金融产品交易信息dto
        TrdProductDeal entity = new TrdProductDeal();
        entity.setFinprodId(dto.getFinprodId());
        entity.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_自定义现金流.getValue());
        entity.setBranch(dto.getBranch());
        entity.setTradeDate(dto.getSettleDate());
        entity.setSettleDate(dto.getSettleDate());
        entity.setCcy(dto.getCcy());
        entity.setTradeAmt(dto.getTradeAmt());
        entity.setPs(dto.getPs());
        entity.setIsClean(dto.getIsClean());
        entity.setCounterId(dto.getCounterId());
        entity.setfTradeId(dto.getfTradeId());
        entity.setRemark(dto.getRemark());
        entity.setTradeStatus(DictCodeListExt1.交易状态_撤销.getValue());
        entity.setIsCancel(DictCodeList.是否_是.getValue());
        entity.setpTradeId(oldTradeId);
        entity.setTradeId((dto.getBusiNo()));
        baseTradeProductDealRevokeDto.setTrdProductDeal(entity);
        //金融产品交易附属表
        TrdProductDealAdd entityAdd = new TrdProductDealAdd();
        entityAdd.setTradeId((dto.getBusiNo()));
        entityAdd.setPortfolioId(dto.getPortfolioId());
        //entityAdd.setProdId(dto.getFinprodId());
        entityAdd.setCustomCashType(dto.getCustomCashType());
        // 一体化对应etliu start
//        entityAdd.setGenType(DictCodeListExt1.录入方式_手工维护.getValue());
        entityAdd.setGenType(DictCodeListExt1.录入方式_手工录入.getValue());
        // 一体化对应etliu end
        baseTradeProductDealRevokeDto.setTrdProductDealAdd(entityAdd);
        //划款指令表entity
        List<StlPayOrdersDto> stlPayOrdersList = new ArrayList<StlPayOrdersDto>();
        List<BussTrdTransferOrderBase> list = dto.getBussTrdTransferOrder();
        if(list != null && list.size()>0){
            for( BussTrdTransferOrderBase d:list){
                d.setTradeId((dto.getBusiNo()));
                d.setpTradeId(oldTradeId);
                stlPayOrdersList.add(d);
            }
        }
        baseTradeProductDealRevokeDto.setStlPayOrdersList(stlPayOrdersList);
        //设置原交易编号
        baseTradeProductDealRevokeDto.setpTradeId(oldTradeId);
        
    }

    /**
     * 插入
     * <功能详细描述>
     * @param dto dto
     * @return int
     * @see
     * @since 1.0
     */
    public int insert(PtlCashFlowDto dto) {
        //金融产品交易信息实体
        BaseTradeProductDealDto baseTradeProductDealDto = new BaseTradeProductDealDto();
        List<BaseTradeProductDealDto> baseTradeProductDealList = new ArrayList<BaseTradeProductDealDto>();
        setBaseTradeProductDealDtoEntity(baseTradeProductDealDto,dto);
        baseTradeProductDealList.add(baseTradeProductDealDto);
        if(baseTradeProductDealService != null){
            baseTradeProductDealService.insert(baseTradeProductDealList);
        }
        //登组合交易表
        PtlTrade entity = new PtlTrade();
        setPtlTradeEntity(entity,dto);
        entity.setBusiNo(dto.getBusiNo());
        ptlTradeMapper.insert(entity);
        if (StringUtil.isNotEmpty(dto.getActPortfolioId()) && !dto.getActPortfolioId().equals(dto.getPortfolioId())) {
            entity.setPortfolioId(dto.getActPortfolioId());
            ptlTradeMapper.insert(entity);
        }
        List<PtlCashPositionFlowDto> ptlCash = new ArrayList<>();
        //插入现金流水和现金余额表
        List<BussTrdTransferOrderBase> list = dto.getBussTrdTransferOrder();
        for(BussTrdTransferOrderBase e:list){
            PtlCashPositionFlowDto positionFlowDto = new PtlCashPositionFlowDto();
            positionFlowDto.setPortfolioId(e.getPortfolioId());
            if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_收取.getValue())){
                positionFlowDto.setAcctId(e.getRecAcctNo());
            }
            if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_支付.getValue())){
                positionFlowDto.setAcctId(e.getPayAcctNo());
            }
            positionFlowDto.setHodingType(DictCodeList.持仓类型_实际.getValue());
            positionFlowDto.setCdate(e.getSettleDate());
            positionFlowDto.setCcy(e.getCcy());
            if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_收取.getValue())){
                positionFlowDto.setAmount(e.getSettleAmt());
            }
            if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_支付.getValue())
                && e.getSettleAmt() != null){
                positionFlowDto.setAmount(e.getSettleAmt().multiply(new BigDecimal(-1)));
            }
            positionFlowDto.setOrderId(e.getOrderId());
            if(positionFlowDto.getPortfolioId() != null && positionFlowDto.getAcctId() != null
                && positionFlowDto.getHodingType() != null && positionFlowDto.getCdate() != null
                && positionFlowDto.getCcy() != null && positionFlowDto.getAmount() != null
                && positionFlowDto.getOrderId() != null){
                //组合可用现金和实际现金查询更新组件
                basePtlCashPositionInfoService.insertCashPositionFlow(positionFlowDto);
//                加入持仓dto
                ptlCash.add(positionFlowDto);
                if (StringUtil.isNotEmpty(dto.getActPortfolioId()) && !dto.getActPortfolioId().equals(dto.getPortfolioId())) {
                    PtlCashPositionFlowDto positionFlowDto2 = new PtlCashPositionFlowDto();
//                    准备子交易
                    BeanUtil.copyPropertiesIgnoreNull(positionFlowDto2,positionFlowDto);
                    positionFlowDto2.setPortfolioId(dto.getActPortfolioId());
//                    上次调用在删除可用时会更新持仓类型
                    positionFlowDto2.setHodingType(DictCodeList.持仓类型_实际.getValue());
                    basePtlCashPositionInfoService.insertCashPositionFlow(positionFlowDto2);
                    ptlCash.add(positionFlowDto2);

                }
            }
        }
        //增加委托指令流水逻辑
        if(baseEtrCashPositionCommService!=null){
            PtlPositionHoldDto ptlHoldDto = new PtlPositionHoldDto();
            ptlHoldDto.setPtlCashPositionFlows(ptlCash);
            baseEtrCashPositionCommService.portfolioEtrCashPosition(ptlHoldDto, true);
        }
        PaTrdProductDeal paTrdProductDeal = new PaTrdProductDeal();
        paTrdProductDeal.setTradeId(dto.getBusiNo());
        if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_收取.getValue())){
            paTrdProductDeal.setPaBussType(DictCodeListExt1.平安业务类型_自定义现金流收取.getValue());
        }
        if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_支付.getValue())){
            paTrdProductDeal.setPaBussType(DictCodeListExt1.平安业务类型_自定义现金流支付.getValue());
        }
        paTrdProductDealMapper.insert(paTrdProductDeal);
        //插入交易报送信息表
        RepTrade repTrade = getRepTrade(dto);
        if(repTrade != null){
            repTradeMapper.insert(repTrade);
        }
        return 1;
    }
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto dto
     * @return RepTrade
     * @see
     * @since 1.0
     */
    private RepTrade getRepTrade(PtlCashFlowDto dto) {
      //根据组合代码查询产品登记编号
        String portfolioId = dto.getPortfolioId();
        String actPortfolio = AllTradeCommonUtil.exchangePortfolio(dto.getPortfolioId());
        if (StringUtil.isNotEmpty(actPortfolio) && !actPortfolio.equals(dto.getPortfolioId())) {
            portfolioId = actPortfolio;
        }
        String prodRegistCode = ptlCashFlowMapper.getProdRegistCode(portfolioId);
        if(StringUtil.isNotEmpty(prodRegistCode)){
          //若能查到则将这条数据存进交易报送信息表中
            RepTrade repTrade = new RepTrade();
            repTrade.setRepFinprodRegId(prodRegistCode);
            repTrade.setRepBankTradeCode(dto.getBusiNo());
            if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_收取.getValue())){
                repTrade.setRepMoneyFlowType(DictCodeListExt2.资金流动类型_其他收入.getValue());
                repTrade.setRepMoneyFlowTypeExp("收取");
                repTrade.setRepTradeComponent(dto.getFinprodMarketId());
            }
            if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_支付.getValue())){
                repTrade.setRepMoneyFlowType(DictCodeListExt2.资金流动类型_其他支出.getValue());
                repTrade.setRepMoneyFlowTypeExp("支付");
            }
            repTrade.setRepCount(dto.getTradeAmt());
            repTrade.setRepOccurMoney(dto.getTradeAmt());
            repTrade.setRepTradeDate(dto.getSettleDate());
            if(platformDateImpl != null){
                repTrade.setRepStatusDate(platformDateImpl.getSysDate());
            }
            return repTrade;
        }
        return null;
    }

    /**
     * 
     * 流程作废
     * <功能详细描述>
     * @param workflowId
     * @see
     * @since 1.0
     */
    public void doDelete(String workflowId) {
        wflTrdProductDealMapper.deleteByWorkflowId(workflowId);
        wflTrdProductDealAddMapper.deleteByWorkflowId(workflowId);
        wflPaTrdProductDealMapper.deleteByWorkflowId(workflowId);
        wflStlPayOrdersMapper.deleteByWorkflowId(workflowId);
    }
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param entity entity
     * @param dto dto
     * @see
     * @since 1.0
     */
    private void setPtlTradeEntity(PtlTrade entity, PtlCashFlowDto dto) {
        entity.setPortfolioId(dto.getPortfolioId());
        if (StringUtil.equals(dto.getOperateType(), "REVOKE")) {
            entity.setBusiNo((dto.getBusiNo()+"R"));
        }
        if(StringUtil.equals(dto.getOperateType(), "NEW")){
            entity.setBusiNo((dto.getBusiNo()));
        }
        entity.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
        entity.setBusiId(dto.getFinprodId());
        entity.setSettleDate(dto.getSettleDate());
    }

    /**
     * 组装数据
     * <功能详细描述>
     * @param baseTradeProductDealDto baseTradeProductDealDto
     * @param dto dto
     * @see
     * @since 1.0
     */
    private void setBaseTradeProductDealDtoEntity(BaseTradeProductDealDto baseTradeProductDealDto,PtlCashFlowDto dto) {
        //金融产品交易信息dto
        TrdProductDeal entity = new TrdProductDeal();
        entity.setTradeId(dto.getBusiNo());
        entity.setFinprodId(dto.getFinprodId());
        entity.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_自定义现金流.getValue());
        entity.setBranch(dto.getBranch());
        entity.setTradeDate(dto.getSettleDate());
        entity.setSettleDate(dto.getSettleDate());
        entity.setCcy(dto.getCcy());
        entity.setTradeAmt(dto.getTradeAmt());
        entity.setPs(dto.getPs());
        entity.setIsClean(dto.getIsClean());
        entity.setCounterId(dto.getCounterId());
        entity.setfTradeId(dto.getfTradeId());
        entity.setRemark(dto.getRemark());
        entity.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        entity.setIsCancel(DictCodeList.是否_否.getValue());
        entity.setpTradeId(null);
        entity.setTradeId(dto.getBusiNo());
        entity.setRemark(dto.getRemark());
        baseTradeProductDealDto.setTrdProductDeal(entity);
        //金融产品交易附属表
        TrdProductDealAdd entityAdd = new TrdProductDealAdd();
        entityAdd.setTradeId(dto.getBusiNo());
        entityAdd.setPortfolioId(dto.getPortfolioId());
        //entityAdd.setProdId(dto.getFinprodId());
        entityAdd.setCustomCashType(dto.getCustomCashType());
        
     // 一体化对应etliu start
//        entityAdd.setGenType(DictCodeListExt1.录入方式_手工维护.getValue());
        entityAdd.setGenType(DictCodeListExt1.录入方式_手工录入.getValue());
     // 一体化对应etliu end
        
        baseTradeProductDealDto.setTrdProductDealAdd(entityAdd);
        //划款指令表entity
        List<StlPayOrdersDto> stlPayOrdersList = new ArrayList<StlPayOrdersDto>();
        List<BussTrdTransferOrderBase> list = dto.getBussTrdTransferOrder();
        if(list != null && list.size()>0){
            for( BussTrdTransferOrderBase d:list){
                d.setTradeId(dto.getBusiNo());
                d.setPortfolioId(dto.getPortfolioId());
                if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_收取.getValue())){
                    d.setMyAcctNo(d.getRecAcctNo());
                    d.setCounterAcctNo(d.getPayAcctNo());
                }
                if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_支付.getValue())){
                    d.setMyAcctNo(d.getPayAcctNo());
                    d.setCounterAcctNo(d.getRecAcctNo());
                }
                
                stlPayOrdersList.add(d);
            }
        }
        baseTradeProductDealDto.setStlPayOrdersList(stlPayOrdersList);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map map
     * @return SuggestDto
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getFTradeId(Map<String, Object> map) {
        map.put("FINPROD_TYPE_TG_F23", DictCodeListExt1.金融产品类型_投管用_自定义现金流.getValue());
        map.put("PINAN_BUSINESS_TYPE_35", DictCodeListExt1.平安业务类型_债券中间收息.getValue());
        map.put("PINAN_BUSINESS_TYPE_36", DictCodeListExt1.平安业务类型_债券中间还本.getValue());
        map.put("PINAN_BUSINESS_TYPE_37", DictCodeListExt1.平安业务类型_债券到期.getValue());
        map.put("PINAN_BUSINESS_TYPE_38", DictCodeListExt1.平安业务类型_存款中间还本.getValue());
        map.put("PINAN_BUSINESS_TYPE_39", DictCodeListExt1.平安业务类型_存款中间付息.getValue());
        map.put("PINAN_BUSINESS_TYPE_40", DictCodeListExt1.平安业务类型_存款到期.getValue());
        map.put("PINAN_BUSINESS_TYPE_45", DictCodeListExt1.平安业务类型_项目类资产中间付息.getValue());
        map.put("PINAN_BUSINESS_TYPE_46", DictCodeListExt1.平安业务类型_项目类资产中间还本.getValue());
        map.put("PINAN_BUSINESS_TYPE_47", DictCodeListExt1.平安业务类型_项目类资产到期.getValue());
        map.put("PINAN_BUSINESS_TYPE_48", DictCodeListExt1.平安业务类型_计划类资产_利率型_中间付息.getValue());
        map.put("PINAN_BUSINESS_TYPE_49", DictCodeListExt1.平安业务类型_计划类资产_利率型_中间还本.getValue());
        map.put("PINAN_BUSINESS_TYPE_50", DictCodeListExt1.平安业务类型_计划类资产_利率型_到期.getValue());
        map.put("PINAN_BUSINESS_TYPE_58", DictCodeListExt1.平安业务类型_计划类资产_净值型_现金分红.getValue());
        map.put("PINAN_BUSINESS_TYPE_59", DictCodeListExt1.平安业务类型_计划类资产_净值型_红利再投.getValue());
        map.put("PINAN_BUSINESS_TYPE_60", DictCodeListExt1.平安业务类型_计划类资产_净值型_分红除权.getValue());
        return ptlCashFlowMapper.getFTradeId(map);
    }

    public void deleteWflData(String workflowId) {
        bussCommonMapperDelegateService.deleteBySpecificKey("WFL_TRD_PRODUCT_DEAL", "WORKFLOW_ID",workflowId);
        bussCommonMapperDelegateService.deleteBySpecificKey("WFL_TRD_PRODUCT_DEAL_ADD", "WORKFLOW_ID",workflowId);
        bussCommonMapperDelegateService.deleteBySpecificKey("WFL_STL_PAY_ORDERS", "WORKFLOW_ID",workflowId);
        bussCommonMapperDelegateService.deleteBySpecificKey("WFL_PA_TRD_PRODUCT_DEAL", "WORKFLOW_ID",workflowId);
    }

    /**
     * 投管接口：其他现金流交易信息
     * 
     * @param dto 接口数据
     * @return 结果
     * @throws Exception
     *             异常
     */
    public int insertCashFlowInfo(PtlCashFlowDto dto) {
        // 交易用数据补齐
        BussinessCodeParameter parameter = new BussinessCodeParameter();
        parameter.setCommonBusinessType(DictCodeList.共通业务类型_自定义现金流);
        String code = baseGetBusinessId != null ? baseGetBusinessId.buildBussinessCode(
            parameter) : IDGenerator.generate();
        dto.setBusiNo(code);
        dto.setBranch(Long.parseLong(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
        dto.setActPortfolioId(dto.getPortfolioId());
        dto.setPortfolioId(AllTradeCommonUtil.exchangePortfolio(dto.getPortfolioId()));
        // 划款指令数据补齐
        List<BussTrdTransferOrderBase> orderlist = dto.getBussTrdTransferOrder();
        if (!StringUtil.isEmptyList(orderlist)) {
            for (BussTrdTransferOrderBase order : orderlist) {
                if (StringUtil.equals(order.getPs(), DictCodeList.收付方向_收入.getValue())) {
                    order.setMyAcctNo(order.getRecAcctNo());
                    order.setCounterAcctNo(order.getPayAcctNo());
                }
                else {
                    order.setMyAcctNo(order.getPayAcctNo());
                    order.setCounterAcctNo(order.getRecAcctNo());
                }
                // 指令编号
                if (baseGetBusinessId != null) {
                    BussinessCodeParameter bussinessCodeParameter = new BussinessCodeParameter();
                    bussinessCodeParameter.setSeqNo("9998");
                    order.setOrderId(baseGetBusinessId.buildBussinessCode(bussinessCodeParameter));
                }
                else {
                    order.setOrderId(IDGenerator.generate());
                }
                // 根据收款账户查询划款性质
                if (StringUtil.isNotEmpty(order.getRecAcctNo()) && StringUtil.isNotEmpty(order.getCcy())){
                    StlCapacctInfoKey stlCapacctInfoKey = new StlCapacctInfoKey();
                    stlCapacctInfoKey.setAcctNo(order.getRecAcctNo());
                    stlCapacctInfoKey.setCcy(order.getCcy());
                    StlCapacctInfo stlCapacctInfo = stlCapacctInfoMapper.selectByPrimaryKey(stlCapacctInfoKey);
                    if (stlCapacctInfo != null){
                        if (StringUtil.isNotEmpty(stlCapacctInfo.getInOutBank())){
                            order.setInOutBank(SysDataUtil.getNameFromDataDict(DictCode.资产来源, stlCapacctInfo.getInOutBank()));
                        }
                        order.setRecAcctPoint(stlCapacctInfo.getLargePayno());
                    }
                }
            }
        }
        this.insert(dto);
        return 1;
    }
}
