/*
 * 文件名：PeriodCommonUtil.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：renping 修改时间：2019年08月10日 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.autoperiod.irs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.PaTrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.common.base.entity.WflTrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.WflTrdProductDeal;
import com.joyintech.tams.common.base.entity.WflTrdProductDealAdd;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.autoperiod.common.AutoPeriodCommonService;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.period.common.BaseTotalTradeInfoDto;
import com.joyintech.tams.invest.common.dto.period.common.BaseTradeInfoDto;
import com.joyintech.tams.invest.common.dto.period.trdirsduration.TrdIRSDurDbInertDto;
import com.joyintech.tams.invest.common.dto.period.trdirsduration.TrdIRSDurationDto;

/**
 * 利率互换存续期共通Service 批处理自动存续期和存续期画面共用逻辑
 *
 * @author renping
 * @version 1.0 2019年08月10日
 * @see AutoPeriodIRSCommonService
 * @since 1.0
 */
@Service
public class AutoPeriodIRSCommonService {

    /**
     * AutoPeriodCommonService
     */
    @Autowired
    private AutoPeriodCommonService autoPeriodCommonService;

    /**
     * 以组合维度生成dto，就是以利息明细维度组装新的dto，作为属性保存到流程entity中<br/>
     * 汇总 <br/>
     * 汇总扩展 <br/>
     * List<Dto> | 交易 <br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| 附属 <br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| 扩展 <br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| 组合交易 <br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| List<划款指令>
     */
    public BaseTotalTradeInfoDto genFinProductTradeDto(TrdIRSDurationDto entity) {
        BaseTotalTradeInfoDto result = new BaseTotalTradeInfoDto();
        // 金融产品交易信息DTO.金融产品交易汇总Entity -> 金融产品交易汇总业务流程表
        TrdFinProductDealTotal trdFinProductDealTotal = gen汇总交易(entity);
        result.setTrdFinProductDealTotal(trdFinProductDealTotal);
        PaTrdFinProductDealTotal paTrdFinProductDealTotal = gen汇总扩展(entity,
            trdFinProductDealTotal);
        result.setPaTrdFinProductDealTotal(paTrdFinProductDealTotal);
        List<BaseTradeInfoDto> baseTradeInfoDtoList = new ArrayList<>();
        result.setBaseTradeInfoDtoList(baseTradeInfoDtoList);

        // 根据利息明细处理汇总及交易dto
        List<TrdProductDeal> trdProductDeals = this.gen交易(entity);
        List<TrdProductDealAdd> trdProductDealAdds = this.gen交易附属表(trdProductDeals, entity);
        List<PtlTrade> ptlTrades = this.gen组合交易(entity, trdProductDeals);
        for (int i = 0; i < trdProductDeals.size(); i++ ) {
            BaseTradeInfoDto baseTradeInfoDto = new BaseTradeInfoDto();
            baseTradeInfoDtoList.add(baseTradeInfoDto);
            baseTradeInfoDto.setTrdProductDeal(trdProductDeals.get(i));
            baseTradeInfoDto.setTrdProductDealAdd(trdProductDealAdds.get(i));
            // 处理组合交易
            List<PtlTrade> list = new ArrayList<>();
            list.add(ptlTrades.get(i));
            baseTradeInfoDto.setPtlTrade(list);

        }
        return result;
    }

    /**
     * 2.0改造生成汇总交易entity
     * 
     * @param entity 源entity
     * @return 目标entity
     * @see
     * @since 1.0
     */
    public TrdFinProductDealTotal gen汇总交易(TrdIRSDurationDto entity) {
        TrdFinProductDealTotal trdFinProductDealTotal = new TrdFinProductDealTotal();
        trdFinProductDealTotal.setTotleTradeId(entity.getBusiCode());
        if ((entity.getFixMdate() != null && entity.getMdate().equals(entity.getFixMdate()))
            || (entity.getFloatMdate() != null
                && entity.getMdate().equals(entity.getFloatMdate()))) {
            trdFinProductDealTotal.setTgBusiType(DictCodeListExt2.平安业务类型_利率互换到期.getValue());
        }
        else {
            trdFinProductDealTotal.setTgBusiType(DictCodeListExt2.平安业务类型_利率互换中间收付息.getValue());
        }
        trdFinProductDealTotal.setFinprodId(entity.getFinprodId());
        trdFinProductDealTotal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_利率互换.getValue());
        trdFinProductDealTotal.setBranch(
            Long.parseLong((String)CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
        if (entity.getFixActPayDate() == null) {
            trdFinProductDealTotal.setTradeDate(entity.getFloatActPayDate());
            trdFinProductDealTotal.setSettleDate(entity.getFloatActPayDate());
        }
        else {
            trdFinProductDealTotal.setTradeDate(entity.getFixActPayDate());
            trdFinProductDealTotal.setSettleDate(entity.getFixActPayDate());
        }
        trdFinProductDealTotal.setCcy(entity.getCcy());
        trdFinProductDealTotal.setPs(entity.getIrsPsIntType());

        trdFinProductDealTotal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        trdFinProductDealTotal.setIsCancel(DictCodeList.是否_否.getValue());
        trdFinProductDealTotal.setStatus(DictCodeList.数据状态_新建.getValue());
        trdFinProductDealTotal.setRemark(entity.getRemark());
        trdFinProductDealTotal.setRemainPrin(entity.getRestPrinAfter());
        trdFinProductDealTotal.setWorkflowId(entity.getWorkflowId());
        trdFinProductDealTotal.setBusiType(DictCodeListExt2.共通业务类型_利率互换存续期交易.getValue());
        trdFinProductDealTotal.setPortfolioId(entity.getPortfolioId());
        return trdFinProductDealTotal;
    }

    /**
     * 2.0改造生成汇总扩展交易entity
     * 
     * @param entity 业务entity
     * @param trdFinProductDealTotal 汇总entity
     * @return 目标entity
     * @see
     * @since 1.0
     */
    private PaTrdFinProductDealTotal gen汇总扩展(TrdIRSDurationDto entity,
                                             TrdFinProductDealTotal trdFinProductDealTotal) {
        PaTrdFinProductDealTotal paTrdFinDealTotal = new PaTrdFinProductDealTotal();
        paTrdFinDealTotal.setTotleTradeId(trdFinProductDealTotal.getTotleTradeId());
        paTrdFinDealTotal.setPaBussType(getPaBussType(entity));
        if (entity.getFixPayDate() != null) {
            paTrdFinDealTotal.setTheoryPayDate(entity.getFixPayDate());
        }
        else {
            paTrdFinDealTotal.setTheoryPayDate(entity.getFloatPayDate());
        }
        paTrdFinDealTotal.setWorkflowId(entity.getWorkflowId());
        return paTrdFinDealTotal;
    }

    /**
     * 利率互换存续期交易 构造将要登录的业务流程表数据
     *
     * @param entity 参数dto
     * @see
     * @since 1.0
     */
    public void genIRSDataForInsertDto(TrdIRSDurationDto entity) {
        // 金融产品交易信息DTO.金融产品交易汇总Entity -> 金融产品交易汇总业务流程表
        WflTrdFinProductDealTotal trdFinProductDealTotal = gen汇总流程表(entity);

        List<WflTrdProductDeal> wflTrdProductDeals = new ArrayList<>();
        List<WflTrdProductDealAdd> wflTrdProductDealAdds = new ArrayList<>();
        List<PtlTrade> ptlTrades = new ArrayList<>();
        List<BaseTradeInfoDto> baseTradeList = entity.getBaseTotalTradeInfoDto().getBaseTradeInfoDtoList();
        baseTradeList.forEach(baseTradeInfoDto -> {
            // 交易流程表
            WflTrdProductDeal wflTrdProductDeal = new WflTrdProductDeal();
            BeanUtil.copyPropertiesIgnoreNull(wflTrdProductDeal,
                baseTradeInfoDto.getTrdProductDeal());
            wflTrdProductDeal.setWorkflowStatus(DictCodeList.数据状态_新建.getValue());
            wflTrdProductDeals.add(wflTrdProductDeal);
            // 交易附加流程表
            WflTrdProductDealAdd wflTrdProductDealAdd = new WflTrdProductDealAdd();
            BeanUtil.copyPropertiesIgnoreNull(wflTrdProductDealAdd,
                baseTradeInfoDto.getTrdProductDealAdd());
            wflTrdProductDealAdd.setWorkflowStatus(DictCodeList.数据状态_新建.getValue());
            wflTrdProductDealAdds.add(wflTrdProductDealAdd);
            // 组合交易表
            PtlTrade ptlTrade = new PtlTrade();
            BeanUtil.copyPropertiesIgnoreNull(ptlTrade, baseTradeInfoDto.getPtlTrade());
            ptlTrades.add(ptlTrade);
        });
        TrdIRSDurDbInertDto trdIRSDurDbInertDto = new TrdIRSDurDbInertDto();
        trdIRSDurDbInertDto.setWflTrdFinProductDealTotal(trdFinProductDealTotal);
        trdIRSDurDbInertDto.setWflTrdProductDealList(wflTrdProductDeals);
        trdIRSDurDbInertDto.setWflTrdProductDealAdds(wflTrdProductDealAdds);
        trdIRSDurDbInertDto.setPtlTrades(ptlTrades);
        entity.setTrdIRSDurDbInertDto(trdIRSDurDbInertDto);
    }

    /**
     * 汇总流程中数据作成
     * 
     * @param entity 参数
     * @return 汇总流程中数据
     * @see
     * @since 1.0
     */
    private WflTrdFinProductDealTotal gen汇总流程表(TrdIRSDurationDto entity) {
        WflTrdFinProductDealTotal wflTrdFinProductDealTotal = new WflTrdFinProductDealTotal();
        BeanUtil.copyPropertiesIgnoreNull(wflTrdFinProductDealTotal,
            entity.getBaseTotalTradeInfoDto().getTrdFinProductDealTotal());
        wflTrdFinProductDealTotal.setWorkflowStatus(DictCodeList.数据状态_新建.getValue());
        return wflTrdFinProductDealTotal;
    }

    /**
     * 交易数据修改
     * 
     * @param entity TrdIRSDurationDto
     * @return 交易数据List
     * @see
     * @since 1.0
     */
    private List<TrdProductDeal> gen交易(TrdIRSDurationDto entity) {
        List<TrdProductDeal> trdProductDeals = new ArrayList<>();
        // 固定端交易数据
        TrdProductDeal trdProductDeal = new TrdProductDeal();
        BussinessCodeParameter parameter = new BussinessCodeParameter();
        parameter.setCommonBusinessType(DictCodeList.共通业务类型_资产存续期交易);
        if (entity.getFixMdate() != null) {
            trdProductDeal.setTradeId(autoPeriodCommonService.buildBussinessCode(parameter));
            trdProductDeal.setFinprodId(entity.getFinprodId());
            trdProductDeal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_利率互换.getValue());
            trdProductDeal.setRemark(entity.getRemark());
            trdProductDeal.setBranch(new Long(
                DateUtil.format(entity.getFixMdate(), DateUtil.DEFAULT_OTHER_FORMAT) + "02"));
            trdProductDeal.setCcy(entity.getCcy());
            if (StringUtil.equals(DictCodeListExt2.利率互换收付息类型_收取固定.getValue(),
                entity.getIrsPsIntType())
                || StringUtil.equals(DictCodeListExt2.利率互换收付息类型_收取固定_支付浮动.getValue(),
                    entity.getIrsPsIntType())) {
                trdProductDeal.setPs(DictCodeList.交易方向_收取.getValue());
            }
            else if (StringUtil.equals(DictCodeListExt2.利率互换收付息类型_支付固定.getValue(),
                entity.getIrsPsIntType())
                     || StringUtil.equals(DictCodeListExt2.利率互换收付息类型_收取浮动_支付固定.getValue(),
                         entity.getIrsPsIntType())) {
                trdProductDeal.setPs(DictCodeList.交易方向_支付.getValue());
            }
            trdProductDeal.setTradeDate(entity.getFixActPayDate());
            trdProductDeal.setVdate(entity.getFixVdate());
            trdProductDeal.setMdate(entity.getFixMdate());
            trdProductDeal.setSettleDate(entity.getFixActPayDate());
            trdProductDeal.setIntAmt(entity.getFixIntAmt());
            trdProductDeal.setTradeAmt(entity.getFixIntAmt());
            trdProductDeal.setTotleTradeId(entity.getBusiCode());
            trdProductDeal.setIsClean(DictCodeList.是否_是.getValue());
            trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
            trdProductDeal.setStatus(DictCodeList.数据状态_新建.getValue());
            trdProductDeal.setCashId(entity.getFixCashId());
            trdProductDeal.setWorkflowId(entity.getWorkflowId());
            trdProductDeal.setIsCancel(DictCodeList.是否_否.getValue());
            trdProductDeals.add(trdProductDeal);
        }
        // 浮动端交易数据
        if (entity.getFloatMdate() != null) {
            trdProductDeal = new TrdProductDeal();
            trdProductDeal.setTradeId(autoPeriodCommonService.buildBussinessCode(parameter));
            trdProductDeal.setFinprodId(entity.getFinprodId());
            trdProductDeal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_利率互换.getValue());
            trdProductDeal.setRemark(entity.getRemark());
            trdProductDeal.setBranch(new Long(
                DateUtil.format(entity.getFloatMdate(), DateUtil.DEFAULT_OTHER_FORMAT) + "02"));
            trdProductDeal.setCcy(entity.getCcy());
            if (StringUtil.equals(DictCodeListExt2.利率互换收付息类型_收取浮动.getValue(),
                entity.getIrsPsIntType())
                || StringUtil.equals(DictCodeListExt2.利率互换收付息类型_收取浮动_支付固定.getValue(),
                    entity.getIrsPsIntType())) {
                trdProductDeal.setPs(DictCodeList.交易方向_收取.getValue());
            }
            else if (StringUtil.equals(DictCodeListExt2.利率互换收付息类型_支付浮动.getValue(),
                entity.getIrsPsIntType())
                     || StringUtil.equals(DictCodeListExt2.利率互换收付息类型_收取固定_支付浮动.getValue(),
                         entity.getIrsPsIntType())) {
                trdProductDeal.setPs(DictCodeList.交易方向_支付.getValue());
            }
            trdProductDeal.setTradeDate(entity.getFloatActPayDate());
            trdProductDeal.setVdate(entity.getFloatVdate());
            trdProductDeal.setMdate(entity.getFloatMdate());
            trdProductDeal.setSettleDate(entity.getFloatActPayDate());
            trdProductDeal.setIntAmt(entity.getFloatIntAmt());
            trdProductDeal.setTradeAmt(entity.getFloatIntAmt());
            trdProductDeal.setTotleTradeId(entity.getBusiCode());
            trdProductDeal.setIsClean(DictCodeList.是否_是.getValue());
            trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
            trdProductDeal.setStatus(DictCodeList.数据状态_新建.getValue());
            trdProductDeal.setCashId(entity.getFloatCashId());
            trdProductDeal.setWorkflowId(entity.getWorkflowId());
            trdProductDeal.setIsCancel(DictCodeList.是否_否.getValue());
            trdProductDeals.add(trdProductDeal);
        }
        // 到期交易
        if (entity.getMdate().equals(entity.getFixMdate())) {
            trdProductDeal = new TrdProductDeal();
            trdProductDeal.setTradeId(autoPeriodCommonService.buildBussinessCode(parameter));
            trdProductDeal.setFinprodId(entity.getFinprodId());
            trdProductDeal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_利率互换.getValue());
            trdProductDeal.setRemark(entity.getRemark());
            trdProductDeal.setBranch(
                Long.valueOf(CacheUtil.getSysParam("noBranchNoValue"))/* Long.valueOf(0) */);
            trdProductDeal.setCcy(entity.getCcy());
            trdProductDeal.setPs(DictCodeList.交易方向_到期.getValue());
            trdProductDeal.setTradeDate(entity.getFixActPayDate());
            trdProductDeal.setSettleDate(entity.getFixActPayDate());
            trdProductDeal.setShareAmt(entity.getRestPrinAfter());
            trdProductDeal.setTotleTradeId(entity.getBusiCode());
            trdProductDeal.setIsClean(DictCodeList.是否_是.getValue());
            trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
            trdProductDeal.setStatus(DictCodeList.数据状态_新建.getValue());
            trdProductDeal.setCashId(entity.getFixCashId());
            trdProductDeal.setWorkflowId(entity.getWorkflowId());
            trdProductDeal.setIsCancel(DictCodeList.是否_否.getValue());
            trdProductDeals.add(trdProductDeal);
        }
        return trdProductDeals;
    }

    /**
     * 金融产品交易附属表Entity作成
     * 
     * @param trdProductDeals 交易
     * @param entity 参数
     * @return 金融产品交易附属表Entity
     * @see
     * @since 1.0
     */
    private List<TrdProductDealAdd> gen交易附属表(List<TrdProductDeal> trdProductDeals,
                                             TrdIRSDurationDto entity) {
        List<TrdProductDealAdd> result = new ArrayList<>();
        trdProductDeals.forEach(trdProductDeal -> {
            TrdProductDealAdd add = new TrdProductDealAdd();
            add.setPortfolioId(entity.getPortfolioId());
            add.setGenType(DictCodeListExt1.生成方式_画面录入.getValue());
            add.setWorkflowId(entity.getWorkflowId());
            add.setTradeId(trdProductDeal.getTradeId());
            add.setStatus(DictCodeList.数据状态_新建.getValue());
            result.add(add);
        });
        return result;
    }

    /**
     * 组合交易作成
     * 
     * @param entity 画面DTO
     * @param trdProductDeals 交易信息
     * @return 组合交易
     * @see
     * @since 1.0
     */
    private List<PtlTrade> gen组合交易(TrdIRSDurationDto entity,
                                   List<TrdProductDeal> trdProductDeals) {
        List<PtlTrade> result = new ArrayList<>();
        trdProductDeals.forEach(trdProductDeal -> {
            PtlTrade ptlTrade2 = new PtlTrade();
            ptlTrade2.setPortfolioId(entity.getPortfolioId());
            ptlTrade2.setBusiNo(trdProductDeal.getTradeId());
            ptlTrade2.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
            ptlTrade2.setBusiId(entity.getFinprodId());
            ptlTrade2.setSettleDate(trdProductDeal.getTradeDate());
            result.add(ptlTrade2);
        });
        return result;
    }

    /**
     * 以组合维度生成dto，就是以利息明细维度组装新的dto，作为属性保存到流程entity中(提前终止用)<br/>
     * 汇总 <br/>
     * 汇总扩展 <br/>
     * List<Dto> | 交易 <br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| 附属 <br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| 扩展 <br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| 组合交易 <br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| List<划款指令>
     */
    public BaseTotalTradeInfoDto genFinProductTradeDtoBeforeEnd(TrdIRSDurationDto entity) {
        BaseTotalTradeInfoDto result = new BaseTotalTradeInfoDto();
        // 金融产品交易信息DTO.金融产品交易汇总Entity -> 金融产品交易汇总业务流程表
        TrdFinProductDealTotal trdFinProductDealTotal = gen汇总交易_提前终止(entity);
        result.setTrdFinProductDealTotal(trdFinProductDealTotal);
        PaTrdFinProductDealTotal paTrdFinProductDealTotal = gen汇总扩展_提前终止(entity,
            trdFinProductDealTotal);
        result.setPaTrdFinProductDealTotal(paTrdFinProductDealTotal);
        List<BaseTradeInfoDto> baseTradeInfoDtoList = new ArrayList<>();
        result.setBaseTradeInfoDtoList(baseTradeInfoDtoList);

        // 根据利息明细处理汇总及交易dto
        List<TrdProductDeal> trdProductDeals = this.gen交易_提前终止(entity);
        List<TrdProductDealAdd> trdProductDealAdds = this.gen交易附属表(trdProductDeals, entity);
        List<PtlTrade> ptlTrades = this.gen组合交易(entity, trdProductDeals);
        for (int i = 0; i < trdProductDeals.size(); i++ ) {
            BaseTradeInfoDto baseTradeInfoDto = new BaseTradeInfoDto();
            baseTradeInfoDtoList.add(baseTradeInfoDto);
            baseTradeInfoDto.setTrdProductDeal(trdProductDeals.get(i));
            baseTradeInfoDto.setTrdProductDealAdd(trdProductDealAdds.get(i));
            // 处理组合交易
            List<PtlTrade> list = new ArrayList<>();
            list.add(ptlTrades.get(i));
            baseTradeInfoDto.setPtlTrade(list);

        }
        return result;
    }

    /**
     * 提前终止生成汇总交易entity
     * 
     * @param entity 源entity
     * @return 目标entity
     * @see
     * @since 1.0
     */
    public TrdFinProductDealTotal gen汇总交易_提前终止(TrdIRSDurationDto entity) {
        TrdFinProductDealTotal trdFinProductDealTotal = new TrdFinProductDealTotal();
        trdFinProductDealTotal.setTotleTradeId(entity.getBusiCode());
        trdFinProductDealTotal.setTgBusiType(DictCodeListExt2.平安业务类型_利率互换提前终止.getValue());
        trdFinProductDealTotal.setFinprodId(entity.getFinprodId());
        trdFinProductDealTotal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_利率互换.getValue());
        trdFinProductDealTotal.setBranch(
            Long.parseLong((String)CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
        trdFinProductDealTotal.setTradeDate(entity.getBeforeEndDate());
        trdFinProductDealTotal.setSettleDate(entity.getBeforeEndPayDate());
        trdFinProductDealTotal.setCcy(entity.getCcy());
        trdFinProductDealTotal.setShareAmt(entity.getBeforeEndPrinAfter());
        trdFinProductDealTotal.setTradeAmt(entity.getBeforeEndAmt());
        trdFinProductDealTotal.setPs(entity.getBeforeEndPs());

        trdFinProductDealTotal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        trdFinProductDealTotal.setIsCancel(DictCodeList.是否_否.getValue());
        trdFinProductDealTotal.setStatus(DictCodeList.数据状态_新建.getValue());
        trdFinProductDealTotal.setRemark(entity.getRemark());
        trdFinProductDealTotal.setRemainPrin(entity.getRestPrinAfter());
        trdFinProductDealTotal.setWorkflowId(entity.getWorkflowId());
        trdFinProductDealTotal.setBusiType(DictCodeListExt2.共通业务类型_利率互换存续期交易.getValue());
        trdFinProductDealTotal.setPortfolioId(entity.getPortfolioId());
        return trdFinProductDealTotal;
    }

    /**
     * 提前终止生成汇总扩展交易entity
     * 
     * @param entity 业务entity
     * @param trdFinProductDealTotal 汇总entity
     * @return 目标entity
     * @see
     * @since 1.0
     */
    private PaTrdFinProductDealTotal gen汇总扩展_提前终止(TrdIRSDurationDto entity,
                                                  TrdFinProductDealTotal trdFinProductDealTotal) {
        PaTrdFinProductDealTotal paTrdFinDealTotal = new PaTrdFinProductDealTotal();
        paTrdFinDealTotal.setTotleTradeId(trdFinProductDealTotal.getTotleTradeId());
        paTrdFinDealTotal.setPaBussType(getPaBussType(entity));
        paTrdFinDealTotal.setTheoryPayDate(entity.getBeforeEndPayDate());
        paTrdFinDealTotal.setWorkflowId(entity.getWorkflowId());
        return paTrdFinDealTotal;
    }

    /**
     * 提前终止交易数据修改
     * 
     * @param entity TrdIRSDurationDto
     * @return 交易数据List
     * @see
     * @since 1.0
     */
    private List<TrdProductDeal> gen交易_提前终止(TrdIRSDurationDto entity) {
        List<TrdProductDeal> trdProductDeals = new ArrayList<>();
        // 提前终止交易数据
        TrdProductDeal trdProductDeal = new TrdProductDeal();
        BussinessCodeParameter parameter = new BussinessCodeParameter();
        parameter.setCommonBusinessType(DictCodeList.共通业务类型_资产存续期交易);
        trdProductDeal.setTradeId(autoPeriodCommonService.buildBussinessCode(parameter));
        trdProductDeal.setFinprodId(entity.getFinprodId());
        trdProductDeal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_利率互换.getValue());
        trdProductDeal.setRemark(entity.getRemark());
        trdProductDeal.setBranch(
            Long.parseLong((String)CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
        trdProductDeal.setCcy(entity.getCcy());
        trdProductDeal.setPs(entity.getBeforeEndPs());
        trdProductDeal.setTradeDate(entity.getBeforeEndDate());
        trdProductDeal.setSettleDate(entity.getBeforeEndPayDate());
        trdProductDeal.setShareAmt(entity.getBeforeEndPrinAfter());
        trdProductDeal.setTradeAmt(entity.getBeforeEndAmt());
        trdProductDeal.setTotleTradeId(entity.getBusiCode());
        trdProductDeal.setIsClean(DictCodeList.是否_是.getValue());
        trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        trdProductDeal.setStatus(DictCodeList.数据状态_新建.getValue());
        trdProductDeal.setWorkflowId(entity.getWorkflowId());
        trdProductDeal.setIsCancel(DictCodeList.是否_否.getValue());
        // jixu报错接口 add by renping 20191031 start 
        trdProductDeal.setMdate(entity.getBeforeEndDate());
        // jixu报错接口 add by renping 20191031 end 
        trdProductDeals.add(trdProductDeal);
        return trdProductDeals;
    }

    /**
     * 获取 平安业务类型
     *
     * @return 平安业务类型
     */
    private String getPaBussType(TrdIRSDurationDto entity) {
        if (StringUtil.isNotEmpty(entity.getBeforeEndPs())) {
            return DictCodeListExt2.平安业务类型_利率互换提前终止.getValue();
        }
        else if (entity.getMdate() != null
                 && ((entity.getFixMdate() != null
                      && entity.getMdate().equals(entity.getFixMdate()))
                     || (entity.getFloatMdate() != null
                         && entity.getMdate().equals(entity.getFloatMdate())))) {
            return DictCodeListExt2.平安业务类型_利率互换到期.getValue();
        }
        else if (entity.getMdate() != null
                 && (entity.getFixMdate() != null || entity.getFloatMdate() != null)) {
            return DictCodeListExt2.平安业务类型_利率互换中间收付息.getValue();
        }
        return null;
    }
}
