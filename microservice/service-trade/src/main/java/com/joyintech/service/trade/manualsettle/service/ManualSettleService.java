package com.joyintech.service.trade.manualsettle.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.trade.entrustmsgbymq.dao.EntrustMsgbyMQMapper;
import com.joyintech.service.trade.manualsettle.dao.ManualSettleMapper;
import com.joyintech.service.trade.manualsettle.dto.ManualSettleDto;
import com.joyintech.service.trade.manualsettle.dto.ManualSettleFlowDto;
import com.joyintech.tams.common.base.dao.StlPayTempOrdersMapper;
import com.joyintech.tams.common.base.dao.TrdManualSettleFlowMapper;
import com.joyintech.tams.common.base.dao.TrdProductTempDealAddMapper;
import com.joyintech.tams.common.base.dao.TrdProductTempDealMapper;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.StlPayTempOrders;
import com.joyintech.tams.common.base.entity.TrdManualSettleFlow;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.common.base.entity.TrdProductTempDeal;
import com.joyintech.tams.common.base.entity.TrdProductTempDealAdd;
import com.joyintech.tams.component.business.trade.BaseTradeProductDealCommService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.component.base.entrust.BaseEtrInvestInfoService;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.dto.etr.EtrCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.etr.EtrPositionHoldDto;
import com.joyintech.tams.invest.common.dto.etr.EtrSecPositionFlowDto;
import com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto;
import com.joyintech.tams.invest.common.trade.service.impl.etrcash.EtrCommonCashPositionDealService;

/**
 * @author zhangnian
 * @ClassName ManualSettleService
 * @description TODO
 * @date 2020/10/22 0022 11:12
 **/
@Service("backManualSettleService")
public class ManualSettleService extends BaseService<ManualSettleDto> {

    private final static Logger logger = LoggerFactory.getLogger(ManualSettleService.class);


    @Autowired
    private ManualSettleMapper manualSettleMapper;

    @Autowired
    private TrdManualSettleFlowMapper trdManualSettleFlowMapper;
    @Autowired
    private BaseEtrInvestInfoService baseEtrInvestInfoService;

    @Autowired
    private TrdProductTempDealMapper trdProductTempDealMapper;

    @Autowired
    private TrdProductTempDealAddMapper trdProductTempDealAddMapper;

    @Autowired
    private StlPayTempOrdersMapper stlPayTempOrdersMapper;

    @Autowired
    private EntrustMsgbyMQMapper entrustMsgbyMQMapper;

    @Autowired
    private BaseTradeProductDealCommService baseTradeProductDealCommService;

    public Page<ManualSettleDto> selectForPage(int page, int length, Map<String, Object> pageParam)
            throws Exception {
        Page<ManualSettleDto> pageDto = super.queryForPage(page, length, () -> manualSettleMapper.queryForPage(pageParam));
        return pageDto;
    }


    public List<ManualSettleFlowDto>  queryForFlowPage(Map<String,Object> param){
        return manualSettleMapper.queryForFlowPage(param);
    }

    public void settleCash(ManualSettleDto dto){
        //划款指令表
        StlPayTempOrders stlPayTempOrders = manualSettleMapper.selectStlPayOrders(dto.getTradeId());
        //交收、回溯处头寸逻辑
        if(StringUtil.equals(dto.getPageModel(),DictCodeListExt3.交收操作类型_交收.getValue())||
                StringUtil.equals(dto.getPageModel(),DictCodeListExt3.交收操作类型_回溯.getValue())){
            //临时交易表
            TrdProductDeal trdProductDeal = new TrdProductDeal();
            TrdProductTempDeal trdProductTempDeal = trdProductTempDealMapper.selectByPrimaryKey(dto.getTradeId());
            BeanUtil.copyPropertiesIgnoreNull(trdProductDeal, trdProductTempDeal);
            //临时交易附属表
            TrdProductDealAdd trdProductDealAdd = new TrdProductDealAdd();
            TrdProductTempDealAdd trdProductTempDealAdd = trdProductTempDealAddMapper.selectByPrimaryKey(dto.getTradeId());
            BeanUtil.copyPropertiesIgnoreNull(trdProductDealAdd, trdProductTempDealAdd);
            StlPayOrdersDto stlPayOrdersDto = new StlPayOrdersDto();
            if (null!=stlPayTempOrders) {
                BeanUtil.copyPropertiesIgnoreNull(stlPayOrdersDto, stlPayTempOrders);
            }
            FinProductTradeDto finProductTradeDto = new FinProductTradeDto();
            if(StringUtil.equals(dto.getPageModel(),DictCodeListExt3.交收操作类型_交收.getValue())){
                finProductTradeDto.setWorkflowStatus(DictCodeListExt1.交易流程状态_有效.getValue());
            }else if(StringUtil.equals(dto.getPageModel(),DictCodeListExt3.交收操作类型_回溯.getValue())){
                finProductTradeDto.setWorkflowStatus(DictCodeListExt1.交易流程状态_撤销.getValue());
            }
            BaseTradeProductDealDto baseTradeProductDealDto = new BaseTradeProductDealDto();
            baseTradeProductDealDto.setTrdProductDeal(trdProductDeal); // 金融产品交易
            baseTradeProductDealDto.setTrdProductDealAdd(trdProductDealAdd); // 金融产品附属
            // 是否来自场内交易下单成交标识
            baseTradeProductDealDto.setIsFromExchange(DictCodeList.是否_是.getValue());
            // 划款指令
            if (stlPayOrdersDto != null) {
                baseTradeProductDealDto.setStlPayOrdersList(Arrays.asList(stlPayOrdersDto));
            }
            finProductTradeDto.setBaseTradeProductDealDtos(Arrays.asList(baseTradeProductDealDto));
            finProductTradeDto.setNeedSum(true);
            String assetType2 = entrustMsgbyMQMapper.queryAssetType1(dto.getEntrustNo());
            finProductTradeDto.setAssetType2(assetType2);
            // 委托代码不为空处理委托代码
            finProductTradeDto.getBaseTradeProductDealDtos().forEach(item -> {
                String entrustId = dto.getEntrustNo();
                if (StringUtil.isEmpty(item.getEntrustId()) && StringUtil.isNotEmpty(entrustId)) {
                    item.setEntrustId(entrustId);
                }
            });
            // 调用业务组件.【金融产品交易共通.交易产品接口】,写入[实际]的组合持仓流水及现金流水
            finProductTradeDto.setNeedHandleFinishEntrust(true);
            baseTradeProductDealCommService.execute(finProductTradeDto);
            //针对回溯  删除交收及回溯的流水
            if(StringUtil.equals(dto.getPageModel(),DictCodeListExt3.交收操作类型_回溯.getValue())){
                if (stlPayOrdersDto != null&&StringUtil.isNotEmpty(stlPayOrdersDto.getOrderId())) {
                    manualSettleMapper.deletePtlCashFlow(stlPayOrdersDto.getOrderId());
                    manualSettleMapper.deleteEtrCashFlow(stlPayOrdersDto.getOrderId());
                }
                if(StringUtil.isNotEmpty(trdProductDeal.getTradeId())){
                    manualSettleMapper.deleteEtrSecFlow(trdProductDeal.getTradeId());
                    manualSettleMapper.deletePtlSecFlow(trdProductDeal.getTradeId());
                }
            }
        }
        else if(StringUtil.equals(dto.getPageModel(),DictCodeListExt3.交收操作类型_交收失败.getValue())){
            EtrPositionHoldDto etrPositionHoldDto = new EtrPositionHoldDto();
            //查询指令预占现金数据
            List<EtrCashPositionFlowDto> etrCashPositionFlows =manualSettleMapper.selectEtrCashPositionFlow(dto.getEntrustNo());
            //查询指令预占头寸数据
            List<EtrSecPositionFlowDto> etrSecPositionFlows = manualSettleMapper.selectEtrSecPositionFlow(dto.getEntrustNo());
            if(etrCashPositionFlows!=null&&!etrCashPositionFlows.isEmpty()){
                etrCashPositionFlows.forEach(item->{
                    item.setSeqno(IDGenerator.generate());
                    item.setCdate(DateUtil.platDateFromCache());
                    item.setOrderId(stlPayTempOrders.getOrderId());
                    item.setAmount(dto.getSettleAmt());
                });
            }
            if(etrSecPositionFlows!=null&&!etrSecPositionFlows.isEmpty()){
                etrSecPositionFlows.forEach(item->{
                    item.setSeqno(IDGenerator.generate());
                    item.setCdate(DateUtil.platDateFromCache());
                    item.setTradeId(dto.getTradeId());
                    item.setAmount(dto.getSettleNum());
                });
            }
            etrPositionHoldDto.setPtlCashPositionFlows(etrCashPositionFlows);
            etrPositionHoldDto.setPtlSecPositionFlows(etrSecPositionFlows);
            etrPositionHoldDto.setDealEtr(true);
            EtrCommonCashPositionDealService.持久化流水Dto(etrPositionHoldDto);
        }

    }

    public boolean recallDelegate(ManualSettleDto dto)
            throws IllegalAccessException, InvocationTargetException {
        BaseEtrInvestInfoDto entity = new BaseEtrInvestInfoDto();
        EtrEntrust etrEntrust = new EtrEntrust();
        BeanUtil.copyProperties(etrEntrust, dto);
        return baseEtrInvestInfoService.recallDelegate(entity);
    }

    public void settle(ManualSettleDto dto){
        //处理流水
        try{
            //DateFormat df = DateFormat.getTimeInstance();//只显示出时分秒
            //String hms = df.format(new Date());
            TrdManualSettleFlow flow = new TrdManualSettleFlow();
            flow.setSeqNo(UUID.randomUUID().toString().replace("-", ""));
            flow.setSettleSeqNo(dto.getSettleSeqNo());
            //时间拼接
            //String[] partsDate = DateUtil.format(dto.getSettleDate()).split("-");
            //String[] partsTime = hms.split(":");
            //GregorianCalendar gc = new GregorianCalendar(Integer.parseInt(partsDate[0]),Integer.parseInt(partsDate[1]),Integer.parseInt(partsDate[2]),Integer.parseInt(partsTime[0]),Integer.parseInt(partsTime[1]),Integer.parseInt(partsTime[2]));
            flow.setSettleTime(new Date());
            flow.setFinprodId(dto.getFinprodId());
            flow.setSettleNum(dto.getSettleNum());
            flow.setSettleAmt(dto.getSettleAmt());
            flow.setOperType(dto.getPageModel());
            flow.setUpdateUser(LoginUserUtil.getUserName());
            trdManualSettleFlowMapper.insert(flow);
        }catch(Exception e){
            logger.error(e.getMessage());
        }
        Map<String,Object> param = new HashMap<>();
        param.put("settleSeqNo",dto.getSettleSeqNo());
        if(StringUtil.equals(dto.getPageModel(),DictCodeListExt3.交收操作类型_交收.getValue())){
            param.put("settleStatus",DictCodeListExt3.交收状态_已交收.getValue());
        }else if(StringUtil.equals(dto.getPageModel(),DictCodeListExt3.交收操作类型_回溯.getValue())){
            param.put("settleStatus",DictCodeListExt3.交收状态_未交收.getValue());
        }else if(StringUtil.equals(dto.getPageModel(),DictCodeListExt3.交收操作类型_交收延期.getValue())){
            param.put("settleStatus",DictCodeListExt3.交收状态_交收延期.getValue());
        }else if(StringUtil.equals(dto.getPageModel(),DictCodeListExt3.交收操作类型_交收失败.getValue())){
            param.put("settleStatus",DictCodeListExt3.交收状态_交收失败.getValue());
        }
        manualSettleMapper.updateSettleInfo(param);
    }
} 

