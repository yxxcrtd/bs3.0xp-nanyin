/*
 * 文件名：PeriodCommonUtil.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：etliu 修改时间：2018年12月13日 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.autoperiod.basePtlRecePosition;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.web.PlatformDate;
import com.joyintech.tams.common.base.dao.FinTradeProductMapper;
import com.joyintech.tams.common.base.dao.PaTrdProductDealMapper;
import com.joyintech.tams.common.base.dao.PtlRecePositionFlowMapper;
import com.joyintech.tams.common.base.dao.PtlRecePositionMapper;
import com.joyintech.tams.common.base.dao.TrdProductDealAddMapper;
import com.joyintech.tams.common.base.dao.TrdProductDealMapper;
import com.joyintech.tams.common.base.dao.WflTrdProductDealOthMapper;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.PaTrdProductDeal;
import com.joyintech.tams.common.base.entity.PtlRecePosition;
import com.joyintech.tams.common.base.entity.PtlRecePositionFlow;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.CommonResult;
import com.joyintech.tams.invest.common.autoperiod.basePtlRecePosition.dao.PtlRecePositionCommonMapper;
import com.joyintech.tams.invest.common.autoperiod.basePtlRecePosition.dao.PtlRecePositionFlowCommonMapper;
import com.joyintech.tams.invest.common.autoperiod.basePtlRecePosition.dto.PtlRecePositionFlowDto;
import com.joyintech.tams.invest.common.autoperiod.basePtlRecePosition.dto.PtlRecePositionsFlowInputDto;
import com.joyintech.tams.invest.common.component.base.core.BaseGetBussinessId;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.component.base.trade.impl.dao.BaseTradeProductDealMapper;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealRevokeDto;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.service.MapperDelegateService;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dao.BaseAssetCashPositionCommMapper;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;
import com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto;

/**
 * 应收款持仓共通Service 批处理自动存续期和存续期画面共用逻辑
 *
 * @author etliu
 * @version 1.0 2018年12月13日
 * @see BasePtlRecePositionService
 * @since 1.0
 */
@Service
public class BasePtlRecePositionService extends BaseService {
    @Autowired
    private PtlRecePositionFlowCommonMapper ptlRecePositionFlowCommonMapper;
    @Autowired
    private PtlRecePositionCommonMapper ptlRecePositionCommonMapper;

    @Autowired
    private PtlRecePositionMapper ptlRecePositionMapper;

    @Autowired
    private MapperDelegateService mapperDelegateService;

    @Autowired
    private FinTradeProductMapper finTradeProductMapper;

    @Autowired
    private WflTrdProductDealOthMapper wflTrdProductDealOthMapper;


    @Autowired
    private BaseAssetCashPositionCommMapper baseAssetCashPositionCommMapper;

    /**
     * 业务编号取得共通
     */
    @Autowired
    private BaseGetBussinessId baseGetBussinessIdimpl;

    @Autowired
    private PtlRecePositionFlowMapper ptlRecePositionFlowMapper;
    @Autowired(required = false)
    private PlatformDate platformDate;
    /**
     * 平台日期
     */
    @Autowired
    private IPlatformDate platformDateImpl;
    /**
     * 金融产品交易
     */
    @Autowired
    private TrdProductDealMapper trdProductDealMapper;
    /**
     * 金融产品交易扩展
     */
    @Autowired
    private TrdProductDealAddMapper trdProductDealAddMapper;

    /**
     * 金融产品交易附属
     */
    @Autowired
    private PaTrdProductDealMapper paTrdProductDealMapper;

    /**
     * 金融产品交易共通
     */
    @Autowired
    private BaseTradeProductDealMapper baseTradeProductDealMapper;

    private static final List<String> 还本_到期_赎回_卖出 = Arrays.asList(DictCodeList.交易方向_还本.getValue(),
            DictCodeList.交易方向_到期.getValue(), DictCodeList.交易方向_赎回.getValue(), DictCodeList.交易方向_卖出.getValue(),
            DictCodeList.交易方向_应收款项转出.getValue());

    private static final List<String> 还本_卖出_到期 = Arrays.asList(DictCodeList.交易方向_还本.getValue(), DictCodeList.交易方向_到期.getValue(),
            DictCodeList.交易方向_卖出.getValue(),DictCodeList.交易方向_应收款项转出.getValue());

    /**
     *
     * 区间中还本不带息应收款可用持仓查询
     * <功能详细描述>
     * @param paramMap 参数交易日已格式化yyyyMMdd,交易日和金融产代码
     * @return
     * @see
     * @since 1.0
     */
    public List<PtlRecePositionFlow> findUnpayPtlRecePosition( Map<String, Object> paramMap){
        //获取参数
        getParams(paramMap);
        List<PtlRecePositionFlow> recePositionFlowlist=null;
        //根据交易日和金融产品代码查询付息区间
        String payDate = paramMap.get("payDate").toString();
        Long mDate=new Long(payDate+"02");
        FinCashPlan cashPlan =ptlRecePositionFlowCommonMapper.findPayInterestStdInfo(paramMap);
        if(cashPlan!=null){
            Long vDate=new Long(DateUtil.format(cashPlan.getVdate(),"yyyyMMdd")+"02");
            paramMap.put("vDate",vDate);
            paramMap.put("mDate",mDate);
            recePositionFlowlist=ptlRecePositionFlowCommonMapper.selectUnpayPtlRecePosition(paramMap);
        }
        return recePositionFlowlist;
    }


    /**
     * 查询需要处理的参数
     * @param params 参数
     * @return 处理好的查询参数
     */
    public Map<String,Object > getParams(Map<String, Object> params) {
        params.put("POSITION_TYPE_03", DictCodeList.持仓类型_实际.getValue());
        params.put("POSITION_TYPE_01", DictCodeList.持仓类型_可用.getValue());
        params.put("recType",DictCodeListExt3.应收款类型_还本未收利息.getValue());
        params.put("CASH_CLASS_TYPE_ZJSX",DictCodeList.现金流类型_中间收息.getValue());
        params.put("CASH_CLASS_TYPE_DQSX",DictCodeList.现金流类型_到期收息.getValue());

        return params;
    }

    /**
     * 插入现金流水 <功能详细描述>
     *
     * @param positionFlowDto
     * @see
     * @since 1.0
     */
    public  void dealCommandBalance(PtlRecePositionFlow positionFlowDto) {
        PtlRecePosition position = new PtlRecePosition();
        BeanUtil.copyPropertiesIgnoreNull(position, positionFlowDto);
        BigDecimal amount = ptlRecePositionCommonMapper.selectCashPosition(positionFlowDto);
        if (amount == null) {
            // 如果当天的持仓数据没有，取当天之前最近一天的数据
            // 查询最近的那一天
            Date maxCdate = ptlRecePositionCommonMapper.selectCashPositionMaxCdate(positionFlowDto);
            if (maxCdate == null) {
                ptlRecePositionMapper.insert(position);
            }
            else {
                PtlRecePositionFlow tempPositionFlowDto = new PtlRecePositionFlow();
                BeanUtil.copyPropertiesIgnoreNull(tempPositionFlowDto, positionFlowDto);
                tempPositionFlowDto.setCdate(maxCdate);

                BigDecimal tempAmount = ptlRecePositionCommonMapper.selectCashPosition(tempPositionFlowDto);
                position.setAmount(ArithmeticUtil.doAdd(position.getAmount(), tempAmount));
                ptlRecePositionMapper.insert(position);
            }
        }
        else {
            position.setAmount(ArithmeticUtil.doAdd(position.getAmount(), amount));
            ptlRecePositionMapper.updateByPrimaryKeySelective(position);
        }

        // 2.2.2 检索组合持仓的最大日期
        String maxCdate = ptlRecePositionCommonMapper.selectMaxCdate(positionFlowDto);
        String cDate = DateUtil.format(positionFlowDto.getCdate());
        // 2.2.3 上面检索出的最大日期 <> 输入参数.日期，将输入参数.日期到2.2.2检索出来的最大日期的数据全部更新
        if (maxCdate != null && !maxCdate.equals(cDate)) {
            String minDate = maxCdate.compareTo(cDate) > 0 ? DateUtil.addDay(cDate, 1) : maxCdate;
            String maxDate = maxCdate.compareTo(cDate) < 0 ? DateUtil.addDay(cDate, -1) : maxCdate;
            Map<String, Object> params = BeanUtil.object2Map(positionFlowDto);
            params.put("minDate", minDate);
            params.put("maxDate", maxDate);
            ptlRecePositionCommonMapper.updatePosition(params);
        }

    }

    /**
     *
     * 区间中还本不带息应收款可用持仓总金额查询
     * <功能详细描述>
     * @param paramMap 交易日和金融产代码
     * @return
     * @see
     * @since 1.0
     */
    public BigDecimal unpayTotalIntAmt( Map<String, Object> paramMap){
        //获取参数
        getParams(paramMap);
        //根据交易日和金融产品代码查询付息区间
        String payDate = paramMap.get("payDate").toString();
        BigDecimal amount=null;
        Long mDate=new Long(DateUtil.formatDate(payDate)+"02");
        FinCashPlan cashPlan =ptlRecePositionFlowCommonMapper.findPayInterestStdInfo(paramMap);
        if(cashPlan!=null){
            Long vDate=new Long(DateUtil.format(cashPlan.getVdate(),"yyyyMMdd")+"02");
            paramMap.put("vDate",vDate);
            paramMap.put("mDate",mDate);
            amount=ptlRecePositionFlowCommonMapper.unpayTotalIntAmt(paramMap);
        }
        return amount;
    }


    /**
     * {@inheritDoc}
     */
    public CommonResult execute(FinProductTradeDto finProductTradeDto) {
        if (finProductTradeDto == null) {
            return new CommonResult(CommonResult.FAILD, "没有数据需要处理");
        }

        if (DictCodeListExt1.交易流程状态_流程中.getValue().equals(finProductTradeDto.getWorkflowStatus())) {
            return deal流程中(finProductTradeDto);
        }
        else if (DictCodeListExt1.交易流程状态_有效.getValue().equals(finProductTradeDto.getWorkflowStatus())) {
            return deal有效(finProductTradeDto);
        }
        else if (DictCodeListExt1.交易流程状态_撤销.getValue().equals(finProductTradeDto.getWorkflowStatus())) {
            return deal撤销(finProductTradeDto);
        }
        return new CommonResult(CommonResult.SUCCED, "处理完成");
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
        if (!insertPosition(finProductTradeDto)) {
            return new CommonResult(CommonResult.FAILD, "处理失败");
        }

        return new CommonResult(CommonResult.SUCCED, "处理完成");
    }


    /**
     * @description: 2.写入类型为[实际]的应收款持仓流水与应收款现金流水
     * @param: [BaseTradeProductDealDtoList, 持仓类型]
     * @return: void
     * @date: 2018/9/29
     */
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

        List<PtlRecePositionFlowDto> ptlRecePositionFlowDto = new ArrayList<>();
        if(finProductTradeDto.getBaseTradeProductDealDtos()!=null){
        for (BaseTradeProductDealDto baseTradeProductDealDto : finProductTradeDto.getBaseTradeProductDealDtos()) {
            PtlRecePositionsFlowInputDto inputDto = new PtlRecePositionsFlowInputDto();
            inputDto.setPositionType(持仓类型.getValue());
            inputDto.setRecType(baseTradeProductDealDto.getRecType());//应收款类型
            inputDto.setTrdProductDeal(baseTradeProductDealDto.getTrdProductDeal());
//            增加附属表dto
            inputDto.setTrdFinProductBarterList(baseTradeProductDealDto.getTrdFinProductBarterList());
            inputDto.setPortfolioId(baseTradeProductDealDto.getTrdProductDealAdd().getPortfolioId());
            inputDto.setBonusMode(baseTradeProductDealDto.getTrdProductDealAdd().getPayType());
            inputDto.setAssetType2(finProductTradeDto.getAssetType2());
            inputDto.setPayDate(baseTradeProductDealDto.getPayDate());//计划支付日
            inputDto.setRecType(baseTradeProductDealDto.getRecType());//应收款持仓类型
            if (StringUtil.isNotEmpty(baseTradeProductDealDto.getRecAmount())) {//实际应收款持仓更新不为空 存入该值
                inputDto.setAmount(baseTradeProductDealDto.getRecAmount());
            }
            List<PtlRecePositionFlowDto> ptlSecPositionFlowDtoss = positionDeal(inputDto);
            ptlRecePositionFlowDto.addAll(ptlSecPositionFlowDtoss);
        }
        }
        if (needNegative) {

            if (ptlRecePositionFlowDto != null && !ptlRecePositionFlowDto.isEmpty()) {
                for (PtlRecePositionFlowDto ptlSecPosition : ptlRecePositionFlowDto) {
                    if (ptlSecPosition.getAmount() != null) {
                        ptlSecPosition.setAmount(ptlSecPosition.getAmount().negate());
                    }
                    if (ptlSecPosition.getTradeId().lastIndexOf('R') != ptlSecPosition.getTradeId().length() - 1) {
                        ptlSecPosition.setTradeId(ptlSecPosition.getTradeId() + "R");
                    }
                }
            }
        }
        if (ptlRecePositionFlowDto != null && !ptlRecePositionFlowDto.isEmpty()) {
            for (PtlRecePositionFlowDto ptlRecePositionFlow : ptlRecePositionFlowDto) {
                inertRecePositionFlow(ptlRecePositionFlow);
            }
        }

        return Boolean.TRUE;
    }


    public List<PtlRecePositionFlowDto> positionDeal(PtlRecePositionsFlowInputDto inputDto) {

        // 1、起息日、收益类型取得(计划类利率型/项目类资产)
        Map<String, Object> vDateNato = baseAssetCashPositionCommMapper.selectVDateNato(
                inputDto.getTrdProductDeal().getFinprodId());
        TrdProductDeal deal = inputDto.getTrdProductDeal();
        // 起息日
        Date vDate = vDateNato == null ? null : (Date)vDateNato.get("VDATE");
        /**
         * 收益类型
         */
        String profitType = vDateNato == null ? null : (String)vDateNato.get("PROFIT_TYPE");
        // 2、资产二级类型
        String assetType2 = StringUtil.isNotEmpty(
                inputDto.getAssetType2()) ? inputDto.getAssetType2() : baseAssetCashPositionCommMapper.selectFundType(
                inputDto.getTrdProductDeal().getFinprodId());

        List<PtlRecePositionFlowDto> result = new ArrayList<>();
        PtlRecePositionFlowDto resultDto = new PtlRecePositionFlowDto();
        resultDto.setPortfolioId(inputDto.getPortfolioId());
        String secAcctId = deal.getSecManageAcctId();
        if ((StringUtil.isEmpty(secAcctId) || "999999".equals(secAcctId)) && StringUtil.isNotEmpty(deal.getChlId())) {
            secAcctId = deal.getChlId();
        }
        resultDto.setSecAcctId(StringUtil.isEmpty(secAcctId) ? "999999" : secAcctId);
        resultDto.setFinprodId(deal.getFinprodId());
        resultDto.setBranch(deal.getBranch());
        resultDto.setInvAim(StringUtil.isEmpty(deal.getInvAim()) ? "999999" : deal.getInvAim());
        resultDto.setHodingType(inputDto.getPositionType());
        resultDto.setRecType(inputDto.getRecType());
        resultDto.setCcy(deal.getCcy());
        resultDto.setTradeId(deal.getTradeId());
        resultDto.setCreateUser(deal.getCreateUser());
        resultDto.setCreateDept(deal.getCreateDept());
        resultDto.setUpdateUser(deal.getCreateUser());
        resultDto.setPayDate(inputDto.getPayDate());//计划支付日
        
        if ( ( DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue().equals(deal.getFinprodType2())
        		||DictCodeListExt1.金融产品类型_投管用_债券.getValue().equals(deal.getFinprodType2())
        		||DictCodeListExt1.金融产品类型_投管用_存款.getValue().equals(deal.getFinprodType2())
        		||DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue().equals(deal.getFinprodType2()))
                && (StringUtil.equals(DictCodeList.交易方向_还本.getValue(), deal.getPs())
                		||StringUtil.equals(DictCodeList.交易方向_收息.getValue(), deal.getPs())
                		||StringUtil.equals(DictCodeList.交易方向_到期.getValue(), deal.getPs()))
        ) {
        	resultDto.setBranch(deal.getBranch());
            resultDto.setCdate(deal.getTradeDate());
            //实际更新应收款信息不为空  更新实际  
            if(StringUtil.isNotEmpty(inputDto.getAmount())){
            	resultDto.setAmount(ArithmeticUtil.multiply(inputDto.getAmount(), new BigDecimal(-1)));
            }else{
//            	resultDto.setAmount(ArithmeticUtil.multiply(deal.getTradeAmt(), new BigDecimal(-1)));
            	if(StringUtil.equals(DictCodeList.交易方向_收息.getValue(), deal.getPs())){
            		resultDto.setAmount(ArithmeticUtil.multiply(deal.getIntAmt(), new BigDecimal(-1)));
            	}else{
            		resultDto.setAmount(ArithmeticUtil.multiply(deal.getPrinAmt(), new BigDecimal(-1)));
            	}
            }
            resultDto.setRecType(inputDto.getRecType());//应收款类型
            resultDto.setPayDate(inputDto.getPayDate());//计划支付日
            result.add(resultDto);
            return result;
        }
        if ((DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue().equals(deal.getFinprodType2())
                && DictCodeList.收益类型_收益型.getValue().equals(profitType) && 还本_到期_赎回_卖出.contains(deal.getPs()))
                || (DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue().equals(deal.getFinprodType2())
                && 还本_卖出_到期.contains(deal.getPs()))) {

            resultDto.setCdate(deal.getTradeDate());
            resultDto.setAmount(ArithmeticUtil.multiply(deal.getShareAmt(), new BigDecimal(-1)));
            result.add(resultDto);
            return result;
        }
        if ((DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue().equals(deal.getFinprodType2())
                && DictCodeList.收益类型_收益型.getValue().equals(profitType) && StringUtil.equals(DictCodeList.交易方向_应收款项转入.getValue(),
                deal.getPs()))
                || (DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue().equals(deal.getFinprodType2())
                && StringUtil.equals(DictCodeList.交易方向_应收款项转入.getValue(), deal.getPs()))) {

            resultDto.setCdate(deal.getTradeDate());
            resultDto.setAmount(deal.getShareAmt());
            result.add(resultDto);
            return result;
        }


        if (DictCodeList.持仓类型_实际.getValue().equals(inputDto.getPositionType())) {
            /*
             * 1 输入金融产品交易Entity.金融产品类型（投管分类）= 数据字典缓存.金融产品类型（投管分类).计划类 and 金融产品交易Entity.交易方向 in （“申购确认”）
             *
             */
            if ( ( ((DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue().equals(deal.getFinprodType2()))
                  && DictCodeList.收益类型_收益型.getValue().equals(profitType) ) ||
                    DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue().equals(deal.getFinprodType2())
            )&& (DictCodeList.交易方向_应收款项转出.getValue().equals(deal.getPs()))
            ) {
                resultDto.setCdate(deal.getTradeDate());
                resultDto.setAmount( ArithmeticUtil.multiply(deal.getShareAmt(), new BigDecimal(-1)));
                result.add(resultDto);
                return result;
            }

            /*
             * 1 输入金融产品交易Entity.金融产品类型（投管分类）= 数据字典缓存.金融产品类型（投管分类).计划类 and 金融产品交易Entity.交易方向 in （“申购”）and 收益类型(共通逻辑获取) = “利率型”
             *
             * 2 输入金融产品交易Entity.金融产品类型（投管分类）= 数据字典缓存.金融产品类型（投管分类).项目类 and 金融产品交易Entity.交易方向 in （“买入”）
             *
             */
            else if (((DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue().equals(deal.getFinprodType2())
                    && DictCodeList.收益类型_收益型.getValue().equals(profitType))
                    && (DictCodeList.交易方向_应收款项转入.getValue().equals(deal.getPs())))

                    || (DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue().equals(deal.getFinprodType2())
                    && DictCodeList.交易方向_应收款项转入.getValue().equals(deal.getPs()))) {
                if (DateUtil.compareDate(vDate, deal.getTradeDate()) != -1) {
                    resultDto.setCdate(vDate);
                }
                else {
                    resultDto.setCdate(deal.getTradeDate());
                }
                resultDto.setAmount(deal.getShareAmt());
                result.add(resultDto);

                if (DateUtil.compareDate(vDate, deal.getTradeDate()) >= 1) {
                    PtlRecePositionFlowDto resultDtozt = new PtlRecePositionFlowDto();
                    BeanUtils.copyProperties(resultDto, resultDtozt);
                    resultDtozt.setCdate(deal.getTradeDate());
                    resultDtozt.setHodingType(DictCodeList.持仓类型_在途.getValue());
                    result.add(resultDtozt);

                    PtlRecePositionFlowDto resultDtozt1 = new PtlRecePositionFlowDto();
                    BeanUtils.copyProperties(resultDto, resultDtozt1);
                    resultDtozt1.setAmount(ArithmeticUtil.multiply(deal.getShareAmt(), new BigDecimal(-1)));
                    resultDtozt1.setHodingType(DictCodeList.持仓类型_在途.getValue());
                    resultDtozt1.setCdate(vDate);
                    result.add(resultDtozt1);
                }
                return result;
            }


        }
        return result;
    }
    /**
     * {@inheritDoc}
     */
    public boolean inertRecePositionFlow(PtlRecePositionFlowDto positionFlowDto) {
        Assert.notNull(positionFlowDto, "参数不能为null");
        Assert.isTrue(StringUtil.isNotEmpty(positionFlowDto.getPortfolioId()), "组合代码不能为空");
        Assert.isTrue(StringUtil.isNotEmpty(positionFlowDto.getFinprodId()), "金融产品代码不能为空");
        Assert.notNull(positionFlowDto.getBranch(), "分支序号不能为null");
        Assert.isTrue(StringUtil.isNotEmpty(positionFlowDto.getHodingType()), "持仓类型不能为空");
        Assert.notNull(positionFlowDto.getCdate(), "日期不能为null");
        Assert.isTrue(StringUtil.isNotEmpty(positionFlowDto.getCcy()), "币种不能为空");
        Assert.notNull(positionFlowDto.getAmount(), "数量不能为null");
        Assert.isTrue(StringUtil.isNotEmpty(positionFlowDto.getTradeId()), "交易编号不能为空");

        // 2.1.1 调用业务编号共通组件获取流水号
        BussinessCodeParameter parameter = new BussinessCodeParameter();
        parameter.setSeqNo("9999");
        String seqNo = baseGetBussinessIdimpl.buildBussinessCode(parameter);

        // 2.1.2 组合持仓流水数据插入
        positionFlowDto.setSeqno(seqNo);
        ptlRecePositionFlowMapper.insert(positionFlowDto);

        // 2.2 输入参数.持仓类型 IN (数据字典缓存.持仓类型.实际, 数据字典缓存.持仓类型.在途)
        if (DictCodeList.持仓类型_实际.getValue().equals(positionFlowDto.getHodingType())
                || DictCodeList.持仓类型_在途.getValue().equals(positionFlowDto.getHodingType())) {
            PtlRecePosition position = getPtlRecePosition(positionFlowDto);
            BigDecimal amount = ptlRecePositionCommonMapper.selectSecPosition(positionFlowDto);
            if (amount == null) {
                // 如果当天的持仓数据没有，取当天之前最近一天的数据
                // 查询最近的那一天
                Date maxCdate = ptlRecePositionCommonMapper.selectSecPositionMaxCdate(positionFlowDto);
                if (maxCdate == null) {
                    ptlRecePositionMapper.insertSelective(position);
                }
                else {
                    PtlRecePositionFlowDto tempPositionFlowDto = new PtlRecePositionFlowDto();
                    BeanUtils.copyProperties(positionFlowDto, tempPositionFlowDto);
                    tempPositionFlowDto.setCdate(maxCdate);

                    BigDecimal tempAmount = ptlRecePositionCommonMapper.selectSecPosition(tempPositionFlowDto);
                    position.setAmount(ArithmeticUtil.doAdd(position.getAmount(), tempAmount));
                    ptlRecePositionMapper.insertSelective(position);
                }
            }
            else {
                position.setAmount(ArithmeticUtil.doAdd(position.getAmount(), amount));
                position.setPayDate(null);
                ptlRecePositionMapper.updateByPrimaryKeySelective(position);
            }

            // 2.2.2 检索组合持仓的最大日期
            String maxCdate = ptlRecePositionCommonMapper.selectMaxCdate(positionFlowDto);
            String cDate = DateUtil.format(positionFlowDto.getCdate());
            // 2.2.3 上面检索出的最大日期 <> 输入参数.日期，将输入参数.日期到2.2.2检索出来的最大日期的数据全部更新
            if (maxCdate != null && !maxCdate.equals(cDate)) {
                String minDate = maxCdate.compareTo(cDate) > 0 ? DateUtil.addDay(cDate, 1) : maxCdate;
                String maxDate = maxCdate.compareTo(cDate) < 0 ? DateUtil.addDay(cDate, -1) : maxCdate;
                Map<String, Object> params = BeanUtil.object2Map(positionFlowDto);
                params.put("minDate", minDate);
                params.put("maxDate", maxDate);
                ptlRecePositionCommonMapper.updatePosition(params);
            }
            // 2.2.4 if 参数.持仓类型=数据字典缓存.持仓类型.实际
            if (DictCodeList.持仓类型_实际.getValue().equals(positionFlowDto.getHodingType())) {
                // 输入参数.前置交易编号不为null或者空的时候，输入参数.前置交易编号。否则，输入参数.交易编号
                positionFlowDto.setTradeId(StringUtil.isNotEmpty(
                        positionFlowDto.getbTradeId()) ? positionFlowDto.getbTradeId() : positionFlowDto.getTradeId());
                positionFlowDto.setHodingType(DictCodeList.持仓类型_可用.getValue());

                ptlRecePositionFlowCommonMapper.deletePtlSecPositionFlow(positionFlowDto);
            }

        }
        return true;
    }


    private PtlRecePosition getPtlRecePosition(PtlRecePositionFlowDto positionFlowDto) {
        PtlRecePosition position = new PtlRecePosition();
        position.setAmount(positionFlowDto.getAmount());
        position.setCcy(positionFlowDto.getCcy());
        position.setpFinprodId(positionFlowDto.getpFinprodId());
        position.setPortfolioId(positionFlowDto.getPortfolioId());
        position.setSecAcctId(positionFlowDto.getSecAcctId());
        position.setFinprodId(positionFlowDto.getFinprodId());
        position.setBranch(positionFlowDto.getBranch());
        position.setInvAim(positionFlowDto.getInvAim());
        position.setHodingType(positionFlowDto.getHodingType());
        position.setCdate(positionFlowDto.getCdate());
        position.setStatus(positionFlowDto.getStatus());
        position.setCreateTime(positionFlowDto.getCreateTime());
        position.setUpdateUser(positionFlowDto.getUpdateUser());
        position.setUpdateTime(positionFlowDto.getUpdateTime());
        position.setWorkflowId(positionFlowDto.getWorkflowId());
        position.setCreateUser(positionFlowDto.getCreateUser());
        position.setCreateDept(positionFlowDto.getCreateDept());
        position.setRecType(positionFlowDto.getRecType());
        position.setPayDate(positionFlowDto.getPayDate());
        return position;
    }


    private CommonResult deal有效(FinProductTradeDto finProductTradeDto) {
        if (finProductTradeDto.getBaseTradeProductDealDtos() == null || finProductTradeDto.getBaseTradeProductDealDtos().isEmpty()) {
            return new CommonResult(CommonResult.FAILD, "没有数据需要处理");
        }

        BaseTradeProductDealDto baseTradeDto=finProductTradeDto.getBaseTradeProductDealDtos().get(0);
        Map<String, Object> vDateNato = baseAssetCashPositionCommMapper.selectVDateNato(baseTradeDto.getTrdProductDeal().getFinprodId());
        String profitType = vDateNato == null ? null : (String)vDateNato.get("PROFIT_TYPE");//获取收益类型
        if (!finProductTradeDto.isNeedSum()) {
        	//update by wangpeng  20191225 通道费改造 项目类，计划类利率 不需要入库通道信息
        	String chlid = finProductTradeDto.getBaseTradeProductDealDtos().get(0).getTrdProductDeal().getChlId();
        	if((StringUtil.equals(finProductTradeDto.getBaseTradeProductDealDtos().get(0).getTrdProductDeal().getBusiType(), DictCodeList.共通业务类型_项目类资产交易.getValue())
        			||StringUtil.equals(finProductTradeDto.getBaseTradeProductDealDtos().get(0).getTrdProductDeal().getBusiType(), DictCodeList.共通业务类型_计划类资产_利率型_交易.getValue())
        			)&&StringUtil.isNotEmpty(finProductTradeDto.getBaseTradeProductDealDtos().get(0).getTrdProductDeal().getChlId())){
//        		finProductTradeDto.getBaseTradeProductDealDtos().get(0).getTrdProductDeal().setChlId(null);
        		for(BaseTradeProductDealDto baseTradeProductDealDto :finProductTradeDto.getBaseTradeProductDealDtos()){
        			baseTradeProductDealDto.getTrdProductDeal().setChlId(null);
        		}
        	}else if(StringUtil.equals(finProductTradeDto.getBaseTradeProductDealDtos().get(0).getTrdProductDeal().getBusiType(), DictCodeList.共通业务类型_存款类资产交易.getValue())){
        		//01 借款利率 不需要处理通道信息,  否则置空
            	if(!StringUtil.equals(CacheUtil.getSysParam(BussinessConstant.IS_CAL_LOADRATE), "01")){
//            		finProductTradeDto.getBaseTradeProductDealDtos().get(0).getTrdProductDeal().setChlId(null);
            		for(BaseTradeProductDealDto baseTradeProductDealDto :finProductTradeDto.getBaseTradeProductDealDtos()){
            			baseTradeProductDealDto.getTrdProductDeal().setChlId(null);
            		}
            	}
        	}
            // 2.调用基础组件.【金融产品交易组件.新建金融产品交易信息】,写入金融产品交易信息
            Boolean result = restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/common/trdcommonbase/insertTradeInfo",
                    finProductTradeDto.getBaseTradeProductDealDtos(), Boolean.class);
            for(BaseTradeProductDealDto baseTradeProductDealDto : finProductTradeDto.getBaseTradeProductDealDtos()){
                if(baseTradeProductDealDto.getPaTrdProductDeal()!=null){
                    paTrdProductDealMapper.insertSelective(baseTradeProductDealDto.getPaTrdProductDeal());
                }
                AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
                inputDto.setProdid(baseTradeProductDealDto.getTrdProductDealAdd().getPortfolioId());
                inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_投资组合.getValue());
                inputDto.setTrdProductDeal(baseTradeProductDealDto.getTrdProductDeal());
                inputDto.setTrdProductDealAdd(baseTradeProductDealDto.getTrdProductDealAdd());
                inputDto.setActPortfolioId(baseTradeProductDealDto.getActPortfolioId());
                inputDto.setPtlTradeList(baseTradeProductDealDto.getPtlTrade());
                AllTradeCommonUtil.handleAfterTradeComplete(inputDto);//调估值接口

            }
//            finProductTradeDto.getBaseTradeProductDealDtos().get(0).getTrdProductDeal().setChlId(chlid);
            for(BaseTradeProductDealDto baseTradeProductDealDto :finProductTradeDto.getBaseTradeProductDealDtos()){
    			baseTradeProductDealDto.getTrdProductDeal().setChlId(chlid);
    		}
            //update by wangpeng  20191225 通道费改造 项目类，计划类净值 不需要入库通道信息
        }

        // 3.写入类型为[实际]的组合持仓流水与组合现金流水
        insertPosition(finProductTradeDto);

        BigDecimal shareAmt = baseTradeDto.getTrdProductDeal().getShareAmt();
        baseTradeDto.getTrdProductDeal().setShareAmt(shareAmt);
        return new CommonResult(CommonResult.SUCCED, "处理完成");
    }


    private CommonResult deal撤销(FinProductTradeDto finProductTradeDto) {
        if (finProductTradeDto.getBaseTradeProductDealDtos() == null || finProductTradeDto.getBaseTradeProductDealDtos().isEmpty()) {
            return new CommonResult(CommonResult.FAILD, "没有数据需要处理");
        }
        if (!finProductTradeDto.isNeedSum()) {
            // 2.调用基础组件.【金融产品交易组件.撤销交易金融产品交易信息】，撤销金融产品交易信息
            List<BaseTradeProductDealRevokeDto> baseTradeProductDealRevokeDtos = new ArrayList<>();
            //金融产品交易撤销信息-金融产品交易集合
            List<TrdProductDeal> trdProductDealList = new ArrayList<>();
            for (BaseTradeProductDealDto baseTradeProductDealDto : finProductTradeDto.getBaseTradeProductDealDtos()) {
                BaseTradeProductDealRevokeDto dto = new BaseTradeProductDealRevokeDto();
                TrdProductDeal productDeal=baseTradeProductDealDto.getTrdProductDeal();
                productDeal.setpTradeId(productDeal.getTradeId());
                productDeal.setTradeId(productDeal.getTradeId() + "R");
                if(productDeal.getTotleTradeId()!=null && StringUtils.isNotEmpty(productDeal.getTotleTradeId())){
                    productDeal.setTotleTradeId(productDeal.getTotleTradeId() +
                            "R");}
                productDeal.setCreateTime(new Date());
                productDeal.setUpdateTime(new Date());
                productDeal.setTradeStatus(DictCodeListExt1.交易状态_撤销.getValue());
                //金融产品交易
                dto.setTrdProductDealAdd(baseTradeProductDealDto.getTrdProductDealAdd());

                TrdProductDealAdd trdProductDealAdd=baseTradeProductDealDto.getTrdProductDealAdd();
                trdProductDealAdd.setTradeId(productDeal.getTradeId());
                trdProductDealAddMapper.insertSelective(trdProductDealAdd);
                
            	//update by wangpeng  20191225 通道费改造 项目类，计划类利率 不需要入库通道信息
            	String chlid = productDeal.getChlId();
            	if((StringUtil.equals(productDeal.getBusiType(), DictCodeList.共通业务类型_项目类资产交易.getValue())
            			||StringUtil.equals(productDeal.getBusiType(), DictCodeList.共通业务类型_计划类资产_利率型_交易.getValue())
            			)&&StringUtil.isNotEmpty(productDeal.getChlId())){
            		productDeal.setChlId(null);
            	}else if(StringUtil.equals(productDeal.getBusiType(), DictCodeList.共通业务类型_存款类资产交易.getValue())){
            		//01 借款利率 不需要处理通道信息,  否则置空
                	if(!StringUtil.equals(CacheUtil.getSysParam(BussinessConstant.IS_CAL_LOADRATE), "01")){
                		productDeal.setChlId(null);
                	}
            	}
            	TrdProductDeal trdProductDeal = new TrdProductDeal();
                BeanUtils.copyProperties(productDeal , trdProductDeal);
                //入库会改变busitype  copy对象入库
                trdProductDealMapper.insertSelective(trdProductDeal);
                productDeal.setChlId(chlid);
                
                trdProductDealList.add(productDeal);


                if(baseTradeProductDealDto.getPaTrdProductDeal()!=null){
                 PaTrdProductDeal paTrdProductDeal= baseTradeProductDealDto.getPaTrdProductDeal();
                 paTrdProductDeal.setTradeId(productDeal.getTradeId());
                 paTrdProductDeal.setCreateTime(new Date());
                 paTrdProductDeal.setUpdateTime(new Date());
                 paTrdProductDeal.setUpdateUser(paTrdProductDeal.getCreateUser());
                 paTrdProductDeal.setUpdateTime(paTrdProductDeal.getCreateTime());
                 paTrdProductDealMapper.insertSelective(paTrdProductDeal);
                    }
                AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
                inputDto.setProdid(trdProductDealAdd.getPortfolioId());
                inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_投资组合.getValue());
                inputDto.setTrdProductDeal(productDeal);
                inputDto.setTrdProductDealAdd(trdProductDealAdd);
                inputDto.setActPortfolioId(baseTradeProductDealDto.getActPortfolioId());
                inputDto.setPtlTradeList(baseTradeProductDealDto.getPtlTrade());
                AllTradeCommonUtil.handleAfterTradeComplete(inputDto);
            }

            //更新金融产品交易信息，是否已撤销 = 数据字典缓存.是否.是
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("isCancle", DictCodeList.是否_是.getValue());
            map.put("trdProductDealList", trdProductDealList);
            baseTradeProductDealMapper.updateTrdProDeal(map);
        }

        // 3.调用【组合可用持仓与实际持仓查询更新组件】，写入类型为[实际]的组合持仓流水
        if (!insertPosition(finProductTradeDto)) {
            return new CommonResult(CommonResult.FAILD, "处理失败");
        }
        return new CommonResult(CommonResult.SUCCED, "处理完成");
    }

    /**
     *
     * @param param []
     * @return
     */
    public List<PtlRecePosition>genPtlPeceAvailableIntPosition(Map<String,Object> param){
        param.put("POSITION_TYPE_SJ",DictCodeList.持仓类型_实际.getValue());
        param.put("POSITION_TYPE_KY",DictCodeList.持仓类型_可用.getValue());
        if(!param.containsKey("PLAT_DATE")&&platformDate!=null){
            param.put("PLAT_DATE",platformDate.getSysDate());
        }
        return ptlRecePositionCommonMapper.genPtlPeceAvailableIntPosition(param);
    }

    /**
     * 查询未支付利息map
     * @param lixiMap 查询参数map
     * @return 未支付branch
     */
    public List<String> genLixiBranchList(Map<String, Object> lixiMap) {
        return ptlRecePositionCommonMapper.genLixiBranchList(lixiMap);
    }

    public Date getPayDate(PtlRecePositionFlowDto ptlRecePositionFlowDto){
        return  ptlRecePositionFlowCommonMapper.getPayDate(ptlRecePositionFlowDto);
    }

    /**
     * 查询存续期的还本不带息支付日
     * @param ptlRecePositionFlowDto 查询参数ptlRecePositionFlowDto
     * @return
     */
    public Date getDurationPayDate(PtlRecePositionFlowDto ptlRecePositionFlowDto){
        return  ptlRecePositionFlowCommonMapper.getDurationPayDate(ptlRecePositionFlowDto);
    }


    /**
     *获取区间未付利息
     * @param param []
     * @return
     */
    public BigDecimal getRepayAmt(Map<String,Object> param){
        param.put("POSITION_TYPE_SJ",DictCodeList.持仓类型_实际.getValue());
        param.put("PLAT_DATE",platformDateImpl.getSysDate());
        param.put("INV_AIM","999999");
        return ptlRecePositionCommonMapper.getRepayAmt(param);
    }

   public  List<PtlRecePosition> getRepayAmtList(Map<String, Object> param){
       param.put("POSITION_TYPE_SJ",DictCodeList.持仓类型_实际.getValue());
       param.put("PLAT_DATE",platformDateImpl.getSysDate());
       param.put("INV_AIM","999999");
       param.put("REC_TYPE_03","03");//应收款类型
       return ptlRecePositionCommonMapper.getRepayAmtList(param);
   }

    public Date getPayDate(String findprodId,Long branch){
        //根据资产代码和branch查询支付日
        PtlRecePositionFlowDto flowDto =new PtlRecePositionFlowDto();
        flowDto.setFinprodId(findprodId);
        flowDto.setBranch(branch);
        return getPayDate(flowDto);//该资产还本计划的支付日
    }


    public Date getDurationPayDate(String findprodId,Long branch){
        //根据资产代码和branch查询支付日
        PtlRecePositionFlowDto flowDto =new PtlRecePositionFlowDto();
        flowDto.setFinprodId(findprodId);
        flowDto.setBranch(branch);
        return getDurationPayDate(flowDto);//该资产还本计划的支付日
    }

    public List<PtlRecePositionFlow> selectAllPosition(String tradeId) {
		return ptlRecePositionFlowCommonMapper.selectAllPosition(tradeId);
	}

    public List<PtlRecePositionFlow> selectAllPositionBytotelTradeId(String tradeId) {
        //根据汇总交易编号查出交易编号
        Map<String, Object> param = new HashMap<>();
        param.put("tradeId",tradeId);
        param.put("PS25",DictCodeList.交易方向_应收款项转入.getValue());
        param.put("PS26",DictCodeList.交易方向_应收款项转出.getValue());
        List<PtlRecePositionFlow> recePositionFlows=new ArrayList<>();
        List<String> trdproductdeallist=ptlRecePositionFlowCommonMapper.getTrdProductDealTradeIdByTotelTradeId(param);
        if(trdproductdeallist!=null && trdproductdeallist.size()>0){
            trdproductdeallist.forEach(tradeIds-> {
                List<PtlRecePositionFlow> recePosition = ptlRecePositionFlowCommonMapper.selectAllPosition(tradeIds);
                recePositionFlows.addAll(recePosition);
            });
        }
        return recePositionFlows;
    }


    public int deletePositionByTotelTradeId(String tradeId) {
        //根据汇总交易编号查出交易编号
        Map<String, Object> param = new HashMap<>();
        param.put("tradeId",tradeId);
        param.put("PS25",DictCodeList.交易方向_应收款项转入.getValue());
        param.put("PS26",DictCodeList.交易方向_应收款项转出.getValue());
        int i=0;
        List<String> trdproductdeallist=ptlRecePositionFlowCommonMapper.getTrdProductDealTradeIdByTotelTradeId(param);
        if(trdproductdeallist!=null && trdproductdeallist.size()>0) {
            trdproductdeallist.forEach(tradeIds-> {
               ptlRecePositionFlowCommonMapper.deletePositionByTradeId(tradeIds);
            });
        }
        return i;
    }

	public int deletePositionByTradeId(String tradeId) {
		return ptlRecePositionFlowCommonMapper.deletePositionByTradeId(tradeId);
	}


	public int updateSecPositionAmt(PtlRecePositionFlow ptlSecPositionFlow) {
		return ptlRecePositionFlowCommonMapper.updateRecePositionAmt(ptlSecPositionFlow);
	}


    /**
     *付息计划重新生成更新支付日；
     * @param finCashPlanList []
     * @return
     */
    public boolean updateRecePayDate(List<FinCashPlan> finCashPlanList) {
        //升序排序
        Collections.sort(finCashPlanList, new Comparator<FinCashPlan>() {

            public int compare(FinCashPlan o1, FinCashPlan o2) {
                return o1.getMdate().compareTo(o2.getMdate());
            }
        });
        Long vDate=null;
        Long mDate=null;
        Date payDate=null;
        Boolean updateStatue=false;
        Date sysDate=platformDateImpl.getSysDate();
        for(FinCashPlan finCashPlan:finCashPlanList){
            if(finCashPlan.getVdate().compareTo(sysDate)<0 && finCashPlan.getMdate().compareTo(sysDate)>=0  ) {
                vDate = new Long(DateUtil.format(finCashPlan.getVdate(), "yyyyMMdd") + "02");
                mDate = new Long(DateUtil.format(finCashPlan.getMdate(), "yyyyMMdd") + "02");
                payDate = finCashPlan.getPayDate();
                Map<String,Object> para=new HashMap<>();
                para.put("vDate",vDate);
                para.put("mDate",mDate);
                para.put("payDate",payDate);
                para.put("finprodId",finCashPlan.getFinprodId());
                ptlRecePositionFlowCommonMapper.updateReceFlowPayDate(para);
                ptlRecePositionCommonMapper.updateRecePositionPayDate(para);
                updateStatue=true;
            }
        }
        return updateStatue;
    }


    /**
     *获取存续期理论区间未付利息
     * @param param []
     * @return
     */
    public BigDecimal getRepaymentUnpayInterest(Map<String,Object> param) {
        param.put("REC_TYPE_03", DictCodeListExt3.应收款类型_还本未收利息.getValue());
        param.put("input_type", "04");
        return ptlRecePositionFlowCommonMapper.getRepaymentUnpayInterest(param);
    }

}
