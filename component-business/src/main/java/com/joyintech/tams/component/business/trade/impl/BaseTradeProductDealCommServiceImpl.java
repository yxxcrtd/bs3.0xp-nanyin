package com.joyintech.tams.component.business.trade.impl;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.business.settle.BussSettleOrderCommService;
import com.joyintech.tams.component.business.trade.BaseTradeProductDealCommService;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.CommonResult;
import com.joyintech.tams.invest.common.component.base.group.BasePtlCashPositionInfoService;
import com.joyintech.tams.invest.common.component.base.group.BasePtlSecPositionInfoService;
import com.joyintech.tams.invest.common.component.business.common.BaseAssetCashPositionCommService;
import com.joyintech.tams.invest.common.component.business.common.dto.PortfolioCashPositionsFlowInputDto;
import com.joyintech.tams.invest.common.component.business.common.dto.PortfolioPositionsFlowInputDto;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.*;
import com.joyintech.tams.invest.common.dto.common.BussTrdTransferOrderBase;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.dto.trade.entrust.EtrFinishStatusDto;
import com.joyintech.tams.invest.common.service.MapperDelegateService;
import com.joyintech.tams.invest.common.trade.dao.BaseAssetCashPositionCommMapper;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;
import com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto;
import com.joyintech.tams.invest.common.trade.service.BaseEtrCashPositionCommService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author : 强成西
 * @version V1.0
 * @Project: tams-invest-parent
 * @Package com.joyintech.component.business.period.service.impl
 * @Description: 金融产品交易共通 实现类
 * @date Date : 2018年09月27日 15:31
 */
@Service
public class BaseTradeProductDealCommServiceImpl extends BaseService implements BaseTradeProductDealCommService {

    /**
     * 业务共通组件.资产头寸现金持仓共通组件
     */
    @Autowired
    private BaseAssetCashPositionCommService assetPositionsCashCommServiceImpl;

    @Autowired
    private BaseAssetCashPositionCommMapper baseAssetCashPositionCommMapper;

    @Autowired(required = false)
    private BasePtlSecPositionInfoService basePtlSecPositionInfoService;

    @Autowired(required = false)
    private BasePtlCashPositionInfoService basePtlCashPositionInfoService;
    /**
     * 持仓现金执行共通
     */
    @Autowired(required = false)
    private BaseEtrCashPositionCommService baseEtrCashPositionCommService;
    @Autowired
    private InterfTradSecdealMapper interfTradSecdealMapper;
    @Autowired
    private InterfOpicsTradRepoMapper interfOpicsTradRepoMapper;

    @Autowired
    private WflTrdFinProductDealTotalMapper wflTrdFinProductDealTotalMapper;

    @Autowired
    private WflPaTrdFinDealTotalMapper wflPaTrdFinDealTotalMapper;

    @Autowired
    private WflTrdProductDealMapper wflTrdProductDealMapper;

    @Autowired
    private WflTrdProductDealAddMapper wflTrdProductDealAddMapper;

    @Autowired
    private WflPaTrdProductDealMapper wflPaTrdProductDealMapper;

    @Autowired
    private WflTrdProductDealOthMapper wflTrdProductDealOthMapper;

    @Autowired
    private WflStlPayOrdersMapper wflStlPayOrdersMapper;

    @Autowired
    private TrdTradeFeeDetailMapper trdTradeFeeDetailMapper;

    @Autowired
    private EtrEntrustMapper etrEntrustMapper;
    @Autowired
    private EtrFinishStatusMapper etrFinishStatusMapper;

    /**
     * BussCommonMapperDelegateService
     */
    @Autowired
    private MapperDelegateService mapperDelegateService;
    @Autowired
    private FinTradeProductMapper finTradeProductMapper;
    @Autowired
    private TrdProductDealPreMapper trdProductDealPreMapper;
    @Autowired
    private TrdProductDealAddPreMapper trdProductDealAddPreMapper;
    /**
     * 清算指令共通Service
     */
    @Autowired(required = false)
    private BussSettleOrderCommService bussSettleOrderCommService;
    /**
     * 划款指令业务组件service
     */
    @Autowired(required = false)
    private BussTrdTransferOrderService bussTrdTransferOrderService;

    @Autowired
    private PtlTradeMapper ptlTradeMapper;

    private static final List<String> 认购确认_申购确认_赎回确认_除权登记_红利再投 = Arrays.asList(
            DictCodeList.交易方向_认购确认.getValue(), DictCodeList.交易方向_申购确认.getValue(),
            DictCodeList.交易方向_赎回确认.getValue(), DictCodeList.交易方向_除权登记.getValue(), DictCodeList.交易方向_红利再投.getValue());
    
    /**
     * {@inheritDoc}
     */
    @Override
    public CommonResult execute(FinProductTradeDto finProductTradeDto) {
        if (finProductTradeDto == null) {
            return new CommonResult(CommonResult.FAILD, "没有数据需要处理");
        }
        //处理CSTP下行数据
        if(finProductTradeDto.getBaseTradeProductDealDtos() != null && !finProductTradeDto.getBaseTradeProductDealDtos().isEmpty()
                && finProductTradeDto.getBaseTradeProductDealDtos().get(0).getTrdProductDeal() != null &&
                StringUtil.isNotBlank(finProductTradeDto.getBaseTradeProductDealDtos().get(0).getTrdProductDeal().getfTradeId())){
            if(StringUtil.equals(finProductTradeDto.getBaseTradeProductDealDtos().get(0).getTrdProductDeal().getBusiType(),DictCodeList.共通业务类型_债券类资产交易.getValue())){
                updateInterfForZhaiJuan(finProductTradeDto);
            }else if(StringUtil.equals(finProductTradeDto.getBaseTradeProductDealDtos().get(0).getTrdProductDeal().getBusiType(),DictCodeList.共通业务类型_质押式回购交易.getValue())){
                updateInterfForZhiYaShi(finProductTradeDto);
            }
        }
        String workflowStatus = finProductTradeDto.getWorkflowStatus();
        if (DictCodeListExt1.交易流程状态_流程中.getValue().equals(workflowStatus)) {
            return deal流程中(finProductTradeDto);
        }
        else if (DictCodeListExt1.交易流程状态_有效.getValue().equals(workflowStatus)) {
            return deal有效(finProductTradeDto);
        }
        else if (DictCodeListExt1.交易流程状态_撤销.getValue().equals(workflowStatus)) {
            return deal撤销(finProductTradeDto);
        }
        return new CommonResult(CommonResult.SUCCED, "处理完成");
    }

    private CommonResult deal撤销(FinProductTradeDto finProductTradeDto) {
        if (finProductTradeDto.getBaseTradeProductDealDtos() == null || finProductTradeDto.getBaseTradeProductDealDtos().isEmpty()) {
            return new CommonResult(CommonResult.FAILD, "没有数据需要处理");
        }
        if (!finProductTradeDto.isNeedSum()) {
            // 2.调用基础组件.【金融产品交易组件.撤销交易金融产品交易信息】，撤销金融产品交易信息
            List<BaseTradeProductDealRevokeDto> baseTradeProductDealRevokeDtos = new ArrayList<>();
            for (BaseTradeProductDealDto baseTradeProductDealDto : finProductTradeDto.getBaseTradeProductDealDtos()) {
                BaseTradeProductDealRevokeDto dto = new BaseTradeProductDealRevokeDto();
                dto.setTrdProductDeal(baseTradeProductDealDto.getTrdProductDeal());
                dto.setTrdProductDealAdd(baseTradeProductDealDto.getTrdProductDealAdd());
                dto.setStlPayOrdersList(baseTradeProductDealDto.getStlPayOrdersList());
                dto.setTrdFinProductBarterList(baseTradeProductDealDto.getTrdFinProductBarterList());
                dto.setTrdTradeFeeDetailList(baseTradeProductDealDto.getTrdTradeFeeDetailList());
                dto.setMstFileInputParameter(baseTradeProductDealDto.getMstFileInputParameter());
                dto.setMstFileOutputParameter(baseTradeProductDealDto.getMstFileOutputParameter());
                dto.setCancelRemark(baseTradeProductDealDto.getTrdProductDeal().getCancelRemark());
                dto.setpTradeId(baseTradeProductDealDto.getTrdProductDeal().getTradeId());
                dto.setActPortfolioId(baseTradeProductDealDto.getActPortfolioId());
                baseTradeProductDealRevokeDtos.add(dto);
            }
            Boolean result = restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/common/trdcommonbase/revokeTradeInfo",
                baseTradeProductDealRevokeDtos, Boolean.class);
            if (!result) {
                return new CommonResult(CommonResult.FAILD, "处理失败");
            }
        }

        /*----------------------zhangliang----add------2019/02/23-------------start--------------------*/
        BaseTradeProductDealDto baseTradeDto = finProductTradeDto.getBaseTradeProductDealDtos().get(0);
        if (baseTradeDto.getTrdProductDeal() != null
        			&& StringUtil.equals(baseTradeDto.getTrdProductDeal().getBusiType(), DictCodeList.共通业务类型_买断式回购交易.getValue())){
        	if (baseTradeDto.getSecAmount() != null){
        		baseTradeDto.getTrdProductDeal().setShareAmt(baseTradeDto.getSecAmount());
        	}
        }
        /*----------------------zhangliang----add------2019/02/23-------------end--------------------*/
        /*----------------------TISHKBS-1003 资产配置分析：现金取值错误（原因：计划类净值型交易确认撤销时 PTL_CASH_POSITION 表金额未更新） add by hpp start--------------------*/
        Map<String, Object> vDateNato = baseAssetCashPositionCommMapper.selectVDateNato(baseTradeDto.getTrdProductDeal().getFinprodId());
        String profitType = vDateNato == null ? null : (String)vDateNato.get("PROFIT_TYPE");//获取收益类型
        List<StlPayOrdersDto> globalStlOrderList=new ArrayList<StlPayOrdersDto>();
        if(认购确认_申购确认_赎回确认_除权登记_红利再投.contains(baseTradeDto.getTrdProductDeal().getPs())
                && DictCodeListExt1.金融产品类型_投管用_基金.getValue().equals(baseTradeDto.getTrdProductDeal().getFinprodType2())||
                认购确认_申购确认_赎回确认_除权登记_红利再投.contains(baseTradeDto.getTrdProductDeal().getPs())&&
                        DictCodeList.收益类型_净值型.getValue().equals(profitType)&& DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue().equals(baseTradeDto.getTrdProductDeal().getFinprodType2())||
                认购确认_申购确认_赎回确认_除权登记_红利再投.contains(baseTradeDto.getTrdProductDeal().getPs())&&
                        DictCodeList.收益类型_货币型.getValue().equals(profitType)&& DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue().equals(baseTradeDto.getTrdProductDeal().getFinprodType2())){
            //查询划款指令详细信息
            /*----------------------TISHKBS-1583 计划类净值型存续期交易撤销报错,前置交易编号为空时取交易编号去掉R modify by msk start--------------------*/
            List<BussTrdTransferOrderBase> bttob;
            if(StringUtil.isNotEmpty(baseTradeDto.getTrdProductDeal().getbTradeId())){
                bttob = bussSettleOrderCommService.selectOrdersByBizCode(baseTradeDto.getTrdProductDeal().getbTradeId());
            }else{
                bttob = bussSettleOrderCommService.selectOrdersByBizCode(baseTradeDto.getTrdProductDeal().getTradeId().substring(0,(baseTradeDto.getTrdProductDeal().getTradeId().length()-1)));
            }
            //撤销基金除权登记时，根据前置交易编号查询不到划款指令特殊处理
            if(bttob == null || bttob.isEmpty()){
            	if(StringUtil.isNotEmpty(baseTradeDto.getTrdProductDeal().getbTradeId())){
            		bttob = bussSettleOrderCommService.selectOrdersByBizCodeByBTradeId(baseTradeDto.getTrdProductDeal().getbTradeId());
            	}
            }
            /*----------------------TISHKBS-1583 计划类净值型存续期交易撤销报错,前置交易编号为空时取交易编号去掉R modify by msk end--------------------*/
            List<BussTrdTransferOrderDto> reslist = new ArrayList<BussTrdTransferOrderDto>();
            if (bttob != null && bttob.size() > 0) {
                List<BussTrdTransferOrderDto> bttodList = new ArrayList<>();
                BussTrdTransferOrderDto bttod = new BussTrdTransferOrderDto();
                BeanUtil.copyPropertiesIgnoreNull(bttod, bttob.get(0));
                bttodList.add(bttod);
                reslist = bussTrdTransferOrderService.save(bttodList);
                reslist.get(0).setIsTotalTrade(DictCodeList.是否_否.getValue());

            }
            for (BussTrdTransferOrderDto bttod : reslist) {
                globalStlOrderList.add(bttod);
            }

            baseTradeDto.setStlPayOrdersList(globalStlOrderList);
        }
        /*----------------------计划类净值型交易确认撤销时 PTL_CASH_POSITION 表金额未更新 add by hpp end--------------------*/
        
        // 3.调用【组合可用持仓与实际持仓查询更新组件】，写入类型为[实际]的组合持仓流水
        if (!insertPosition(finProductTradeDto)) {
            return new CommonResult(CommonResult.FAILD, "处理失败");
        }
        // 调用基础组件.【投资委托组件.取消完成委托】，撤销委托完成状况信息(投资委托场合才执行此步)
        finishEntrust(finProductTradeDto.getBaseTradeProductDealDtos(), true);
        return new CommonResult(CommonResult.SUCCED, "处理完成");
    }

    private CommonResult deal有效(FinProductTradeDto finProductTradeDto) {
        if (finProductTradeDto.getBaseTradeProductDealDtos() == null || finProductTradeDto.getBaseTradeProductDealDtos().isEmpty()) {
            return new CommonResult(CommonResult.FAILD, "没有数据需要处理");
        }

        /*----------------------裴家俊-----add------2018/11/23-------------start--------------*/
        BaseTradeProductDealDto baseTradeDto=finProductTradeDto.getBaseTradeProductDealDtos().get(0);
        Map<String, Object> vDateNato = baseAssetCashPositionCommMapper.selectVDateNato(baseTradeDto.getTrdProductDeal().getFinprodId());
        String profitType = vDateNato == null ? null : (String)vDateNato.get("PROFIT_TYPE");//获取收益类型
        List<StlPayOrdersDto> globalStlOrderList=new ArrayList<StlPayOrdersDto>();
        if(认购确认_申购确认_赎回确认_除权登记_红利再投.contains(baseTradeDto.getTrdProductDeal().getPs())
        		&& DictCodeListExt1.金融产品类型_投管用_基金.getValue().equals(baseTradeDto.getTrdProductDeal().getFinprodType2())||
        		认购确认_申购确认_赎回确认_除权登记_红利再投.contains(baseTradeDto.getTrdProductDeal().getPs())&&
        		DictCodeList.收益类型_净值型.getValue().equals(profitType)&& DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue().equals(baseTradeDto.getTrdProductDeal().getFinprodType2())||
        		认购确认_申购确认_赎回确认_除权登记_红利再投.contains(baseTradeDto.getTrdProductDeal().getPs())&&
                DictCodeList.收益类型_货币型.getValue().equals(profitType)&& DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue().equals(baseTradeDto.getTrdProductDeal().getFinprodType2())){
        			globalStlOrderList=baseTradeDto.getStlPayOrdersList();
        			baseTradeDto.setStlPayOrdersList(new ArrayList<StlPayOrdersDto>());
        }
        /*-------------------裴家俊------add------2018/11/23-------------end--------------------*/
        if (!finProductTradeDto.isNeedSum()) {
        	//update by wangpeng  20191225 通道费改造 项目类，计划类利率 不需要入库通道信息
            String chlModelType = CacheUtil.getSysParam("CHL_MODEL_TYPE");
            String chlid = finProductTradeDto.getBaseTradeProductDealDtos().get(0).getTrdProductDeal().getChlId();
        	if((StringUtil.equals(finProductTradeDto.getBaseTradeProductDealDtos().get(0).getTrdProductDeal().getBusiType(), DictCodeList.共通业务类型_项目类资产交易.getValue())
        			||StringUtil.equals(finProductTradeDto.getBaseTradeProductDealDtos().get(0).getTrdProductDeal().getBusiType(), DictCodeList.共通业务类型_计划类资产_利率型_交易.getValue())
        			)&&StringUtil.isNotEmpty(finProductTradeDto.getBaseTradeProductDealDtos().get(0).getTrdProductDeal().getChlId())){
                if (StringUtil.isNotEmpty(chlModelType) && chlModelType.equals("01")) {
                    finProductTradeDto.getBaseTradeProductDealDtos().get(0).getTrdProductDeal().setChlId(null);
                }
        	}else if(StringUtil.equals(finProductTradeDto.getBaseTradeProductDealDtos().get(0).getTrdProductDeal().getBusiType(), DictCodeList.共通业务类型_存款类资产交易.getValue())){
        		//01 借款利率 不需要处理通道信息,  否则置空
            	if(!StringUtil.equals(CacheUtil.getSysParam(BussinessConstant.IS_CAL_LOADRATE), "01")){
            		finProductTradeDto.getBaseTradeProductDealDtos().get(0).getTrdProductDeal().setChlId(null);
            	}
        	}
            // 2.调用基础组件.【金融产品交易组件.新建金融产品交易信息】,写入金融产品交易信息
            Boolean result = restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/common/trdcommonbase/insertTradeInfo",
                    finProductTradeDto.getBaseTradeProductDealDtos(), Boolean.class);
            finProductTradeDto.getBaseTradeProductDealDtos().get(0).getTrdProductDeal().setChlId(chlid);
          //update by wangpeng  20191225 通道费改造 项目类，计划类净值 不需要入库通道信息
        }
        /*----------------------裴家俊----add------2018/11/23-------------start--------------------*/
        if(认购确认_申购确认_赎回确认_除权登记_红利再投.contains(baseTradeDto.getTrdProductDeal().getPs())
        		&& DictCodeListExt1.金融产品类型_投管用_基金.getValue().equals(baseTradeDto.getTrdProductDeal().getFinprodType2())||
        		认购确认_申购确认_赎回确认_除权登记_红利再投.contains(baseTradeDto.getTrdProductDeal().getPs())&&
        		DictCodeList.收益类型_净值型.getValue().equals(profitType)&& DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue().equals(baseTradeDto.getTrdProductDeal().getFinprodType2())||
        		认购确认_申购确认_赎回确认_除权登记_红利再投.contains(baseTradeDto.getTrdProductDeal().getPs())&&
                DictCodeList.收益类型_货币型.getValue().equals(profitType)&& DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue().equals(baseTradeDto.getTrdProductDeal().getFinprodType2())){
        		baseTradeDto.setStlPayOrdersList(globalStlOrderList);
        }
        /*----------------------裴家俊----add------2018/11/23-------------end--------------------*/
        
        // 3.写入类型为[实际]的组合持仓流水与组合现金流水
        insertPosition(finProductTradeDto);

        /*----------------------zhangliang----add------2019/02/23-------------start--------------------*/
        BigDecimal shareAmt = baseTradeDto.getTrdProductDeal().getShareAmt();
        if (baseTradeDto.getTrdProductDeal() != null
        		&& StringUtil.equals(baseTradeDto.getTrdProductDeal().getBusiType(), DictCodeList.共通业务类型_买断式回购交易.getValue())){
        	if (baseTradeDto.getSecAmount() != null){
        		baseTradeDto.getTrdProductDeal().setShareAmt(baseTradeDto.getSecAmount());
        	}
        }
        /*----------------------zhangliang----add------2019/02/23-------------end--------------------*/
        
        // 3.调用基础组件.【投资委托组件.完成委托】，写入委托完成状况信息(投资委托场合才执行此步)
        finishEntrust(finProductTradeDto.getBaseTradeProductDealDtos(), false);

        /*----------------------zhangliang----add------2019/02/23-------------start--------------------*/
        baseTradeDto.getTrdProductDeal().setShareAmt(shareAmt);
        /*----------------------zhangliang----add------2019/02/23-------------end--------------------*/
        return new CommonResult(CommonResult.SUCCED, "处理完成");
    }

    private Boolean finishEntrust(List<BaseTradeProductDealDto> BaseTradeProductDealDtoList, boolean isCancel) {
        List<EtrFinishStatusDto> baseEtrInvestInfoDtos = new ArrayList<>();
        for (BaseTradeProductDealDto baseTradeProductDealDto : BaseTradeProductDealDtoList) {
            if(StringUtil.isNotBlank(baseTradeProductDealDto.getPreTradeId())){
                TrdProductDealPreKey key = new TrdProductDealPreKey();
                key.setTradeId(baseTradeProductDealDto.getPreTradeId());
                key.setWorkflowStatus(DictCodeListExt1.交易流程状态_有效.getValue());
                TrdProductDealPre trdProductDealPre = trdProductDealPreMapper.selectByPrimaryKey(key);
                TrdProductDealAddPre trdProductDealAddPre = trdProductDealAddPreMapper.selectByPrimaryKey(baseTradeProductDealDto.getPreTradeId());
                EtrFinishStatusKey etrKey = new EtrFinishStatusKey();
                etrKey.setEntrustId(trdProductDealAddPre.getEntrustId());
                etrKey.setTradeId(baseTradeProductDealDto.getPreTradeId());
                EtrFinishStatus etrFinishStatus = etrFinishStatusMapper.selectByPrimaryKey(etrKey);
                if(trdProductDealPre!=null && StringUtil.isNotEmpty(trdProductDealAddPre.getEntrustId())){
                    if (StringUtil.equals(baseTradeProductDealDto.getTrdProductDeal().getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券.getValue())
                            || StringUtil.equals(baseTradeProductDealDto.getTrdProductDeal().getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue())
                            || StringUtil.equals(baseTradeProductDealDto.getTrdProductDeal().getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())) {
                        EtrFinishStatusDto status = new EtrFinishStatusDto();
                        status.setEntrustId(trdProductDealAddPre.getEntrustId());
                        status.setTradeId(baseTradeProductDealDto.getTrdProductDealAdd().getTradeId());
                        status.setFinishDate(baseTradeProductDealDto.getTrdProductDeal().getTradeDate());
                        status.setFinishAmt(baseTradeProductDealDto.getTrdProductDeal().getShareAmt());
                        status.setEntFinishAmt(etrFinishStatus != null ? etrFinishStatus.getEntFinishAmt() : BigDecimal.ZERO);
                        status.setTrader(baseTradeProductDealDto.getTrdProductDeal().getTrader());
                        if(StringUtil.isNotBlank(baseTradeProductDealDto.getfTradeId())){
                            status.setCstpTradeId(baseTradeProductDealDto.getfTradeId());
                        }
                        baseEtrInvestInfoDtos.add(status);
                        continue;
                    }
                }
            }
            if (StringUtil.isEmpty(baseTradeProductDealDto.getTrdProductDealAdd().getEntrustId())) {
                continue;
            }
            //可转债申购不需要更新委托完成度，service已经更新过委托状态
            if(StringUtil.equals(baseTradeProductDealDto.getTrdProductDeal().getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券.getValue())
                    &&baseTradeProductDealDto.getTrdFinProductDealTotal()!=null
            		&&(StringUtil.equals(DictCodeList.交易方向_申购.getValue(),baseTradeProductDealDto.getTrdFinProductDealTotal().getPs())||StringUtil.equals(DictCodeList.交易方向_申购确认.getValue(),baseTradeProductDealDto.getTrdFinProductDealTotal().getPs()))){
            	 continue;
            }
            // 外汇远期掉期到期时不需要更新委托完成度（到期copy的买入，所以委托代码也有值了） add by renping 20191125 start
            if ((StringUtil.equals(baseTradeProductDealDto.getTrdProductDeal().getFinprodType2(),
                DictCodeListExt1.金融产品类型_投管用_远期.getValue())
                 || StringUtil.equals(
                     baseTradeProductDealDto.getTrdProductDeal().getFinprodType2(),
                     DictCodeListExt1.金融产品类型_投管用_掉期.getValue()))
                && (StringUtil.equals(DictCodeList.交易方向_到期.getValue(),
                    baseTradeProductDealDto.getTrdProductDeal().getPs()))) {
                continue;
            }
            // 外汇远期掉期到期时不需要更新委托完成度（到期copy的买入，所以委托代码也有值了） add by renping 20191125 end

            EtrFinishStatusDto status = new EtrFinishStatusDto();
            status.setEntrustId(baseTradeProductDealDto.getTrdProductDealAdd().getEntrustId());
            status.setTradeId(baseTradeProductDealDto.getTrdProductDealAdd().getTradeId());
            status.setFinishDate(baseTradeProductDealDto.getTrdProductDeal().getTradeDate());
            if (StringUtil.equals(baseTradeProductDealDto.getTrdProductDeal().getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券.getValue())
            		|| StringUtil.equals(baseTradeProductDealDto.getTrdProductDeal().getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue())
            		|| StringUtil.equals(baseTradeProductDealDto.getTrdProductDeal().getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue())) {
//                status.setFinishAmt(new BigDecimal(baseTradeProductDealDto.getTrdProductDeal().getShareAmt().intValue() * 100));
                status.setFinishAmt(baseTradeProductDealDto.getTrdProductDeal().getShareAmt());
            }else if(StringUtil.equals(baseTradeProductDealDto.getTrdProductDeal().getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_股票.getValue())||
                     StringUtil.equals(baseTradeProductDealDto.getTrdProductDeal().getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue())||
                    StringUtil.equals(baseTradeProductDealDto.getTrdProductDeal().getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_基金.getValue())){
                    EtrEntrust etr =etrEntrustMapper.selectByPrimaryKey(baseTradeProductDealDto.getTrdProductDealAdd().getEntrustId());
            	//委托是金额 更新金额
            	if(StringUtil.isNotEmpty(etr)&&StringUtil.isNotEmpty(etr.getEntrustAmt())){
            		status.setFinishAmt(baseTradeProductDealDto.getTrdProductDeal().getTradeAmt());
            	}
            	//委托是数量 更新数量
            	else{
            		status.setFinishAmt(baseTradeProductDealDto.getTrdProductDeal().getShareAmt());
            	}
            }
            else {
                status.setFinishAmt(StringUtil.isNotEmpty(baseTradeProductDealDto.getTrdProductDeal().getPrinAmt())?baseTradeProductDealDto.getTrdProductDeal().getPrinAmt(): StringUtil.isNotEmpty(baseTradeProductDealDto.getTrdProductDeal().getShareAmt())?baseTradeProductDealDto.getTrdProductDeal().getShareAmt():baseTradeProductDealDto.getTrdProductDeal().getTradeAmt());
            }
            status.setTrader(baseTradeProductDealDto.getTrdProductDeal().getTrader());
            if(StringUtil.isNotBlank(baseTradeProductDealDto.getfTradeId())){
                status.setCstpTradeId(baseTradeProductDealDto.getfTradeId());
            }
            baseEtrInvestInfoDtos.add(status);
        }
        if (!baseEtrInvestInfoDtos.isEmpty()) {
            if (isCancel) {
                return restTemplate.postForObject(ServiceApplication.ENTRUST_SERVICE_NAME + "/common/entrustcommonbase/cancelEntrust", baseEtrInvestInfoDtos, Boolean.class);
            } else {
                return restTemplate.postForObject(ServiceApplication.ENTRUST_SERVICE_NAME + "/common/entrustcommonbase/finishEntrust", baseEtrInvestInfoDtos, Boolean.class);
            }
        }
        return Boolean.TRUE;
    }

    /**
     * 处理流程中状态
     *
     * @return CommonResult
     * @param finProductTradeDto
     */
    private CommonResult deal流程中(FinProductTradeDto finProductTradeDto) {
        if (finProductTradeDto.getBaseTradeProductDealDtos() == null || finProductTradeDto.getBaseTradeProductDealDtos().isEmpty()) {
            return new CommonResult(CommonResult.FAILD, "没有数据需要处理");
        }
        // 1.调用【组合可用持仓与实际持仓查询更新组件】与【组合可用现金与实际现金查询更新组件】执行持仓与现金的插入操作

        /*----------------------zhangliang----add------2019/02/23-------------start--------------------*/
        BaseTradeProductDealDto baseTradeDto = finProductTradeDto.getBaseTradeProductDealDtos().get(0);
        if (baseTradeDto.getTrdProductDeal() != null
        		&& StringUtil.equals(baseTradeDto.getTrdProductDeal().getBusiType(), DictCodeList.共通业务类型_买断式回购交易.getValue())){
        	if (baseTradeDto.getSecAmount() != null){
        		baseTradeDto.getTrdProductDeal().setShareAmt(baseTradeDto.getSecAmount());
        	}
        }
        /*----------------------zhangliang----add------2019/02/23-------------end--------------------*/

        if (!insertPosition(finProductTradeDto)) {
            return new CommonResult(CommonResult.FAILD, "处理失败");
        }

        return new CommonResult(CommonResult.SUCCED, "处理完成");
    }

    /**
     * 更新债券CSTP的交易
     */
    @DataSource(DataSourceType.OTHER)
    @DTXTransaction
    public void updateInterfForZhaiJuan(FinProductTradeDto finProductTradeDto){
        TrdProductDeal trdProductDeal = finProductTradeDto.getBaseTradeProductDealDtos().get(0).getTrdProductDeal();
        InterfTradSecdeal interfTradSecdeal = interfTradSecdealMapper.selectByPrimaryKey(trdProductDeal.getfTradeId());
        InterfTradSecdeal record = new InterfTradSecdeal();
        if (DictCodeListExt1.交易流程状态_撤销.getValue().equals(finProductTradeDto.getWorkflowStatus())) {
            if(interfTradSecdeal!=null){
                record.setDealno(trdProductDeal.getfTradeId());
                BigDecimal prinamt = interfTradSecdeal.getPrinamt();
                BigDecimal subtract = ArithmeticUtil.doAdd(prinamt, finProductTradeDto.getTotalFaceValue());
                if(subtract.compareTo(BigDecimal.ZERO)==-1||subtract.compareTo(BigDecimal.ZERO)==0){//小于0的话
                    record.setPrinamt(BigDecimal.ZERO);
                }else{
                    record.setPrinamt(subtract);
                }
                interfTradSecdealMapper.updateByPrimaryKeySelective(record);
            }
        }
        else if (DictCodeListExt1.交易流程状态_流程中.getValue().equals(finProductTradeDto.getWorkflowStatus())) {
            if(interfTradSecdeal!=null){
                record.setDealno(trdProductDeal.getfTradeId());
                BigDecimal prinamt = interfTradSecdeal.getPrinamt();
                BigDecimal subtract = ArithmeticUtil.subtract(prinamt, finProductTradeDto.getTotalFaceValue());
               if(subtract.compareTo(BigDecimal.ZERO)==-1||subtract.compareTo(BigDecimal.ZERO)==0){//小于0的话
                   record.setPrinamt(BigDecimal.ZERO);
               }else{
                   record.setPrinamt(subtract);
               }
               interfTradSecdealMapper.updateByPrimaryKeySelective(record);
            }
        }
        else if (DictCodeListExt1.交易流程状态_有效.getValue().equals(finProductTradeDto.getWorkflowStatus())) {

        }
    }

    /**
     * 更新CSTP质押式交易
     * @param finProductTradeDto
     */
    @DataSource(DataSourceType.OTHER)
    @DTXTransaction
    public void updateInterfForZhiYaShi(FinProductTradeDto finProductTradeDto){
        TrdProductDeal trdProductDeal = finProductTradeDto.getBaseTradeProductDealDtos().get(0).getTrdProductDeal();
        InterfOpicsTradRepo interfOpicsTradRepo = interfOpicsTradRepoMapper.selectByPrimaryKey(trdProductDeal.getfTradeId());
        InterfOpicsTradRepo record = new InterfOpicsTradRepo();
        if (DictCodeListExt1.交易流程状态_撤销.getValue().equals(finProductTradeDto.getWorkflowStatus())) {
            if(interfOpicsTradRepo!=null){
                record.setDealno(trdProductDeal.getfTradeId());
                record.setTradeid(trdProductDeal.getfTradeId());
                BigDecimal prinamt = interfOpicsTradRepo.getRepoamt();
                BigDecimal subtract = ArithmeticUtil.doAdd(prinamt, trdProductDeal.getPrinAmt());
                if(subtract.compareTo(BigDecimal.ZERO)==-1||subtract.compareTo(BigDecimal.ZERO)==0){//小于0的话
                    record.setRepoamt(BigDecimal.ZERO);
                }else{
                    record.setRepoamt(subtract);
                }
                interfOpicsTradRepoMapper.updateByPrimaryKeySelective(record);
            }
        }
        else if (DictCodeListExt1.交易流程状态_流程中.getValue().equals(finProductTradeDto.getWorkflowStatus())) {
            if(interfOpicsTradRepo!=null){
                record.setDealno(trdProductDeal.getfTradeId());
                record.setTradeid(trdProductDeal.getfTradeId());
                BigDecimal prinamt = interfOpicsTradRepo.getRepoamt();
                BigDecimal subtract = ArithmeticUtil.subtract(prinamt, trdProductDeal.getPrinAmt());
                if(subtract.compareTo(BigDecimal.ZERO)==-1||subtract.compareTo(BigDecimal.ZERO)==0){//小于0的话
                    record.setRepoamt(BigDecimal.ZERO);
                }else{
                    record.setRepoamt(subtract);
                }
                interfOpicsTradRepoMapper.updateByPrimaryKeySelective(record);
            }
        }
        else if (DictCodeListExt1.交易流程状态_有效.getValue().equals(finProductTradeDto.getWorkflowStatus())) {
        }
    }

    /**
     * @description: 2.写入类型为[实际]的组合持仓流水与组合现金流水
     * @param: [BaseTradeProductDealDtoList, 持仓类型]
     * @return: void
     * @date: 2018/9/29
     */
    @Override
    public Boolean insertPosition(FinProductTradeDto finProductTradeDto) {
        DictCodeList 持仓类型 = null;
        boolean needNegative = false;
        if (DictCodeListExt1.交易流程状态_撤销.getValue().equals(finProductTradeDto.getWorkflowStatus())) {
            持仓类型 = DictCodeList.持仓类型_实际;
            needNegative = true;
        }
        else if (DictCodeListExt1.交易流程状态_流程中.getValue().equals(finProductTradeDto.getWorkflowStatus())) {
            持仓类型 = DictCodeList.持仓类型_可用;
            needNegative = false;
        }
        else if (DictCodeListExt1.交易流程状态_有效.getValue().equals(finProductTradeDto.getWorkflowStatus())) {
            持仓类型 = DictCodeList.持仓类型_实际;
            needNegative = false;
        }
        if (持仓类型 == null) {
            return false;
        }

        List<PtlSecPositionFlowDto> ptlSecPositionFlows = new ArrayList<>();
        List<PtlCashPositionFlowDto> ptlCashPositionFlows = new ArrayList<>();
        for (BaseTradeProductDealDto baseTradeProductDealDto : finProductTradeDto.getBaseTradeProductDealDtos()) {
            PortfolioPositionsFlowInputDto inputDto = new PortfolioPositionsFlowInputDto();
            inputDto.setPositionType(持仓类型.getValue());
            inputDto.setTrdProductDeal(baseTradeProductDealDto.getTrdProductDeal());
//            增加附属表dto
            inputDto.setTrdFinProductBarterList(baseTradeProductDealDto.getTrdFinProductBarterList());
            inputDto.setPortfolioId(baseTradeProductDealDto.getTrdProductDealAdd().getPortfolioId());
            inputDto.setBonusMode(baseTradeProductDealDto.getTrdProductDealAdd().getPayType());
            inputDto.setAssetType2(finProductTradeDto.getAssetType2());
            List<PtlSecPositionFlowDto> ptlSecPositionFlowDtoss = assetPositionsCashCommServiceImpl.positionDeal(inputDto);
            //买断式回购持仓特殊处理
            for (PtlSecPositionFlowDto dto : ptlSecPositionFlowDtoss){
                if (DictCodeListExt1.交易场所_银行间.getValue().equals(baseTradeProductDealDto.getTrdProductDeal().getTradeMarket())
                        && DictCodeList.交易方向_买入.getValue().equals(baseTradeProductDealDto.getTrdProductDeal().getPs())
                            && (DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue().equals(baseTradeProductDealDto.getTrdProductDeal().getFinprodType2())
                            || DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue().equals(baseTradeProductDealDto.getTrdProductDeal().getFinprodType2()))){
                    dto.setAmount(baseTradeProductDealDto.getSecAmount());           
                }   
            }
            ptlSecPositionFlows.addAll(ptlSecPositionFlowDtoss);

            if (baseTradeProductDealDto.getStlPayOrdersList() != null && !baseTradeProductDealDto.getStlPayOrdersList().isEmpty()) {
                PortfolioCashPositionsFlowInputDto cashInputDto = new PortfolioCashPositionsFlowInputDto();
                cashInputDto.setPositionType(持仓类型.getValue());
                cashInputDto.setTrdProductDeal(baseTradeProductDealDto.getTrdProductDeal());
                cashInputDto.setPortfolioId(baseTradeProductDealDto.getTrdProductDealAdd().getPortfolioId());
                cashInputDto.setBonusMode(baseTradeProductDealDto.getTrdProductDealAdd().getPayType());
                cashInputDto.setAssetType2(finProductTradeDto.getAssetType2());
                cashInputDto.setStlPayOrders(baseTradeProductDealDto.getStlPayOrdersList());
                cashInputDto.setIeFlag(finProductTradeDto.getIeFlag());
                List<PtlCashPositionFlowDto> resultList = assetPositionsCashCommServiceImpl.cashDeal(cashInputDto);
                ptlCashPositionFlows.addAll(resultList);
            }
            //        现金与持仓执行共同
            if(baseEtrCashPositionCommService != null && !finProductTradeDto.isNeedExecuteEtrPosition()){
                //modify by mashukai 20210306 修复债券借贷融入交易后指令可用持仓未改变问题 start 
                if(finProductTradeDto.getBussRepoSec() != null){
                    baseTradeProductDealDto.setBussRepoSec(finProductTradeDto.getBussRepoSec());
                }
                //modify by mashukai 20210306 修复债券借贷融入交易后指令可用持仓未改变问题 end 
                baseTradeProductDealDto.setAssetType2(finProductTradeDto.getAssetType2());
                baseEtrCashPositionCommService.executeEtrCashPosition(baseTradeProductDealDto, 持仓类型, needNegative,finProductTradeDto.getIeFlag(),finProductTradeDto.getIsOverdueDeal());
            }
        }
        if (needNegative) {
            if (ptlCashPositionFlows != null && !ptlCashPositionFlows.isEmpty()) {
                for (PtlCashPositionFlowDto ptlCashPosition : ptlCashPositionFlows) {
                    if (ptlCashPosition.getAmount() != null) {
                        ptlCashPosition.setAmount(ptlCashPosition.getAmount().negate());
                    }
                    if (ptlCashPosition.getOrderId().lastIndexOf('R') != ptlCashPosition.getOrderId().length() - 1) {
                        ptlCashPosition.setOrderId(ptlCashPosition.getOrderId() + "R");
                    }
                }
            }
            if (ptlSecPositionFlows != null && !ptlSecPositionFlows.isEmpty()) {
                for (PtlSecPositionFlowDto ptlSecPosition : ptlSecPositionFlows) {
                    if (ptlSecPosition.getAmount() != null) {
                        ptlSecPosition.setAmount(ptlSecPosition.getAmount().negate());
                    }
                    if (ptlSecPosition.getTradeId().lastIndexOf('R') != ptlSecPosition.getTradeId().length() - 1) {
                        ptlSecPosition.setTradeId(ptlSecPosition.getTradeId() + "R");
                    }
                }
            }
        }
        PtlPositionHoldDto ptlPositionHoldDto = new PtlPositionHoldDto();
        ptlPositionHoldDto.setPtlSecPositionFlows(ptlSecPositionFlows);
        ptlPositionHoldDto.setPtlCashPositionFlows(ptlCashPositionFlows);
        //不是逾期交易，更新持仓流水
        if(!(StringUtil.isNotEmpty(finProductTradeDto.getIsOverdueDeal())&&StringUtil.equals(DictCodeList.是否_是.getValue(), finProductTradeDto.getIsOverdueDeal()))){
        	if (ptlPositionHoldDto.getPtlSecPositionFlows() != null && !ptlPositionHoldDto.getPtlSecPositionFlows().isEmpty()) {
                for (PtlSecPositionFlowDto ptlSecPositionFlow : ptlPositionHoldDto.getPtlSecPositionFlows()) {
                    basePtlSecPositionInfoService.inertSecPositionFlow(ptlSecPositionFlow);
                }
            }
        }
        if (ptlPositionHoldDto.getPtlCashPositionFlows() != null && !ptlPositionHoldDto.getPtlCashPositionFlows().isEmpty()) {
            for (PtlCashPositionFlowDto ptlCashPositionFlow : ptlPositionHoldDto.getPtlCashPositionFlows()) {
                basePtlCashPositionInfoService.insertCashPositionFlow(ptlCashPositionFlow);
            }
        }
        return Boolean.TRUE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommonResult delete(String workflowId) {
        // 根据工作流代码查询交易编号、组合代码
        WflTrdProductDealAdd wflTrdProductDealAdd = restTemplate.getForObject(
                ServiceApplication.TRADE_SERVICE_NAME
                        + "/common/trdcommonbase/selectWflTrdProductDealAdd/" + workflowId, WflTrdProductDealAdd.class);

        // 根据工作流代码查询指令编号
        List<WflStlPayOrders> orderList = restTemplate.getForList(
                ServiceApplication.TRADE_SERVICE_NAME
                        + "/common/trdcommonbase/selectOrderIdList/" + workflowId, WflStlPayOrders.class);
        // 调用授信共同 sjj start
        //根据工作流代码查询交易相关信息
        AllTradeCommonInputDto inputDto = restTemplate.getForObject(
                ServiceApplication.TRADE_SERVICE_NAME
                        + "/common/trdcommonbase/selectWflTrdProductDeal/" + workflowId, AllTradeCommonInputDto.class);
        // 所有交易生效后调用的共通方法
        if(inputDto !=null && wflTrdProductDealAdd !=null){
//        	AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
//            inputDto.setTrdProductDeal(trdProductDeal);
            //作废流程不做授信处理 20191029 add
            //  AllTradeCommonUtil.handleBeforeTradeComplete(inputDto,true);
        }
        // 调用授信共同 sjj end
        // 1.删除各业务流程信息

        // 1.删除各业务流程信息
        // 汇总表
        mapperDelegateService.deleteBySpecificKey("WFL_TRD_FIN_PRODUCT_DEAL_TOTAL", "WORKFLOW_ID",
                workflowId);
//        wflTrdFinProductDealTotalMapper.deleteByWorkflowId(workflowId);
        // 汇总扩展表

//        wflPaTrdFinDealTotalMapper.deleteByWorkflowId(workflowId);
        mapperDelegateService.deleteBySpecificKey("WFL_PA_TRD_FIN_DEAL_TOTAL", "WORKFLOW_ID",
                workflowId);
//        mapperDelegateService.deleteBySpecificKey("WFL_PA_TRD_FIN_DEAL_TOTAL", "WORKFLOW_ID",
//                workflowId);

        mapperDelegateService.deleteBySpecificKey("WFL_TRD_DEAL_TOTAL_ADD", "WORKFLOW_ID",
                workflowId);
        // 金融产品交易信息DTO.金融产品交易Entity -> 金融产品交易业务流程表
        mapperDelegateService.deleteBySpecificKey("WFL_TRD_PRODUCT_DEAL", "WORKFLOW_ID",
                workflowId);
//        wflTrdProductDealMapper.deleteByWorkflowId(workflowId);

        // 金融产品交易信息DTO.金融产品交易附属表Entity -> 金融产品交易附属业务流程表
        mapperDelegateService.deleteBySpecificKey("WFL_TRD_PRODUCT_DEAL_ADD", "WORKFLOW_ID",
                workflowId);
//        wflTrdProductDealAddMapper.deleteByWorkflowId(workflowId);
        // 金融产品交易扩展表Entity -> 金融产品交易扩展业务流程表
        mapperDelegateService.deleteBySpecificKey("WFL_PA_TRD_PRODUCT_DEAL", "WORKFLOW_ID",
                workflowId);
//        wflPaTrdProductDealMapper.deleteByWorkflowId(workflowId);
        // 金融产品交易业务流程其他信息Entity -> 金融产品交易业务流程其他信息表
        mapperDelegateService.deleteBySpecificKey("WFL_TRD_PRODUCT_DEAL_OTH", "WORKFLOW_ID",
                workflowId);
        wflTrdProductDealOthMapper.deleteByWorkflowId(workflowId);
        // 金融产品交易信息DTO.划款指令表Entity -> 划款指令业务流程表
        mapperDelegateService.deleteBySpecificKey("WFL_STL_PAY_ORDERS", "WORKFLOW_ID",
                workflowId);
//        wflStlPayOrdersMapper.deleteByWorkflowId(workflowId);

        if (wflTrdProductDealAdd == null) {
            return new CommonResult(CommonResult.FAILD, "没有数据需要处理");
        }
        else {
            // 费用明细表
            mapperDelegateService.deleteBySpecificKey("TRD_TRADE_FEE_DETAIL", "TRADE_ID",
                    wflTrdProductDealAdd.getTradeId());
            FinTradeProductKey key = new FinTradeProductKey();
            key.setFinprodId(wflTrdProductDealAdd.getTradeId());
            key.setBranch(new Long(0));
            FinTradeProduct finTradeProduct = finTradeProductMapper.selectByPrimaryKey(key);
            if(finTradeProduct!=null){
                mapperDelegateService.deleteBySpecificKey("FIN_TRADE_PRODUCT", "FINPROD_ID",
                        wflTrdProductDealAdd.getTradeId());
//                finTradeProductMapper.deleteByPrimaryKey(key);
            }
        }
        return new CommonResult(CommonResult.SUCCED, "处理完成");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommonResult deleteWflData(String workflowId) {
        // 1.删除各业务流程信息
        // 汇总表
        wflTrdFinProductDealTotalMapper.deleteByWorkflowId(workflowId);
        // 汇总扩展表
        wflPaTrdFinDealTotalMapper.deleteByWorkflowId(workflowId);
        // 金融产品交易信息DTO.金融产品交易Entity -> 金融产品交易业务流程表
        wflTrdProductDealMapper.deleteByWorkflowId(workflowId);

        // 金融产品交易信息DTO.金融产品交易附属表Entity -> 金融产品交易附属业务流程表
        wflTrdProductDealAddMapper.deleteByWorkflowId(workflowId);

        // 金融产品交易扩展表Entity -> 金融产品交易扩展业务流程表
        wflPaTrdProductDealMapper.deleteByWorkflowId(workflowId);

        // 金融产品交易业务流程其他信息Entity -> 金融产品交易业务流程其他信息表
        wflTrdProductDealOthMapper.deleteByWorkflowId(workflowId);

        // 金融产品交易信息DTO.划款指令表Entity -> 划款指令业务流程表
        wflStlPayOrdersMapper.deleteByWorkflowId(workflowId);

        return new CommonResult(CommonResult.SUCCED, "处理完成");
    }

    @Override
    public Integer insertPtlTrade(PtlTrade ptlTrade) {
        // 1.插入组合交易表
        return ptlTradeMapper.insert(ptlTrade);
    }
}
