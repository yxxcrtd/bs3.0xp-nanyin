/**
 * 
 */
package com.joyintech.service.product.productduration.expire.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.product.common.dto.FinProductCommonInfoDto;
import com.joyintech.service.product.productduration.expire.dao.FinProductExpireMapper;
import com.joyintech.service.product.productduration.expire.dto.FinProductExpire001Dto;
import com.joyintech.service.product.productduration.expire.dto.FinProductExpireBranchInfoDto;
import com.joyintech.service.product.productduration.expire.dto.FinProductExpireFeeInfoDto;
import com.joyintech.service.product.productduration.expire.dto.FinProductExpireMInfoDto;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.dto.product.addreduce.ReduceInfoDto;

/**
 * @author zhangliang
 */
@Service("backFinProductExpireService")
public class FinProductExpireService extends BaseService<FinProductExpire001Dto> {

    /**
     * {@linkplain}
     */
    @Autowired
    private FinProductExpireMapper finProductExpireMapper;

    /**
     * {@linkplain}
     */
    @Autowired
    private IPlatformDate iPlatformDate;

    /**
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     * 
     * @param page page
     * @param length length
     * @param map map
     * @return Page类 Page
     * @throws Exception 异常信息
     */
    public Page<FinProductExpire001Dto> selectForPage(int page, int length,
                                                      Map<String, Object> map)
        throws Exception {
        map.put("statusUndo", DictCodeListExt1.有效状态_待处理.getValue());
        map.put("statusEffect", DictCodeListExt1.有效状态_有效.getValue());
        map.put("statusdel", DictCodeListExt1.有效状态_撤销.getValue());
        map.put("statuswfl", DictCodeListExt1.有效状态_流程中.getValue());
        map.put("operationTypeClose", DictCodeList.开放类型_封闭式.getValue());
        map.put("dealModeNormal", DictCodeList.处理模式_普通.getValue());
        map.put("dealModeRound", BussinessDictCodeList.处理模式_滚动.getValue());
        map.put("dealModeNormalN", BussinessDictCodeList.处理模式_普通N天.getValue());
        map.put("dealModeRoundN", BussinessDictCodeList.处理模式_滚动N天.getValue());
        map.put("dealModeOpenC", BussinessDictCodeList.处理模式_开放阶梯.getValue());
        map.put("tradeStatus", DictCodeListExt1.交易状态_初始.getValue());
        map.put("delStatus", DictCodeListExt1.交易状态_撤销.getValue());
        map.put("noFlag", DictCodeList.是否_否.getValue());
        map.put("yes", DictCodeList.是否_是.getValue());
        map.put("psC5", DictCodeList.交易方向_产品到期.getValue());
        map.put("psC4", DictCodeList.交易方向_产品赎回.getValue());
        map.put("psC16", DictCodeList.交易方向_提前终止.getValue());
    //  map.put("psB", DictCodeList.交易方向_产品到期分红.getValue());
        map.put("psF", DictCodeList.交易方向_产品付费.getValue());
        map.put("psB", DictCodeList.交易方向_产品到期分红.getValue());
        map.put("cashType2Sale", DictCodeListExt1.现金流二级类型_销售费.getValue());
        map.put("cashType2Trust", DictCodeListExt1.现金流二级类型_托管费.getValue());
        map.put("hodingType", DictCodeList.持仓类型_实际.getValue());
        map.put("paBussTypeM", DictCodeListExt1.平安业务类型_产品到期.getValue());
        map.put("platDate", iPlatformDate.getSysDate());
        return super.queryForPage(page, length, () -> finProductExpireMapper.selectForPage(map));
    }

    /**
     * 根据金融产品代码查询到期信息
     * 
     * @param map 参数
     * @return 到期信息
     */
    public List<FinProductExpireMInfoDto> queryMInfoListByFinprodId(Map<String, Object> map) {
        map.put("cashTypeFinProd", DictCodeListExt1.现金流业务类型_金融产品.getValue());
        map.put("cashFirstTypeInt", DictCodeListExt1.现金流一级类型_利息.getValue());
        map.put("cashSecondTypeInt", DictCodeListExt1.现金流二级类型_利息.getValue());
        map.put("paBussTypeM", DictCodeListExt1.平安业务类型_产品到期.getValue());
        map.put("yesNoNo", DictCodeList.是否_否.getValue());
        map.put("yes", DictCodeList.是否_是.getValue());
        map.put("hodingType", DictCodeList.持仓类型_实际.getValue());
        map.put("platDate", iPlatformDate.getSysDate());

        List<FinProductExpireMInfoDto> list = finProductExpireMapper.queryMInfoListByFinprodId(map);
        if (StringUtil.isEmptyList(list)) {
            list = finProductExpireMapper.queryMInfoListByFinprodIdAdd(map);
        }

        if (!StringUtil.isEmptyList(list)) {
            for (FinProductExpireMInfoDto finProductExpireMInfoDto : list) {
                BigDecimal basis = new BigDecimal(365);
                if (StringUtil.equals(finProductExpireMInfoDto.getBasis(),
                    DictCodeList.计息基础_A_360.getValue())) {
                    basis = new BigDecimal(360);
                }
                // 系统自动生成没有利息
                if (!StringUtil.isNotEmpty(finProductExpireMInfoDto.getIntAmt())) {
                    // 应付利息 = 计息本金*应付利率*（计息结束日-计息开始日）/计息基础
                    finProductExpireMInfoDto.setIntAmt(ArithmeticUtil.divide(
                        ArithmeticUtil.multiply(finProductExpireMInfoDto.getTdyPrinAmt(),
                            finProductExpireMInfoDto.getYield(),
                            new BigDecimal(DateUtil.getBetweenDayNumber(
                                finProductExpireMInfoDto.getStartDate(),
                                finProductExpireMInfoDto.getEndDate()))),
                        basis, 2));
                }
                // 申赎接口没有收益率(利率 = 利息*计息基础/(本金*天数))
                if (!StringUtil.isNotEmpty(finProductExpireMInfoDto.getYield())) {
                    finProductExpireMInfoDto.setYield(ArithmeticUtil.divide(
                        ArithmeticUtil.multiply(finProductExpireMInfoDto.getIntAmt(), basis),
                        ArithmeticUtil.multiply(finProductExpireMInfoDto.getTdyPrinAmt(),
                            new BigDecimal(DateUtil.getBetweenDayNumber(
                                finProductExpireMInfoDto.getStartDate(),
                                finProductExpireMInfoDto.getEndDate()))),
                        2));
                }

                finProductExpireMInfoDto.setYield(ArithmeticUtil.multiply(
                    finProductExpireMInfoDto.getYield(), new BigDecimal(100)));
                finProductExpireMInfoDto.setActYield(finProductExpireMInfoDto.getYield());
                finProductExpireMInfoDto.setActIntAmt(finProductExpireMInfoDto.getIntAmt());
            }
        }

        return list;
    }

    /**
     * 根据金融产品代码查询费用信息
     * 
     * @param map 参数
     * @return 费用信息
     */
    public List<FinProductExpireFeeInfoDto> queryFeeInfoListByFinprodId(Map<String, Object> map) {
        map.put("busiType", DictCodeList.共通业务类型_产品费用支付管理.getValue());
        map.put("ps", DictCodeList.交易方向_产品付费.getValue());
        map.put("yesFlag", DictCodeList.是否_是.getValue());
        map.put("tradeStatusInit", DictCodeListExt1.交易状态_初始.getValue());
        map.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
        map.put("cashType2", DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
        map.put("hodingType", DictCodeList.持仓类型_实际.getValue());
        map.put("platDate", iPlatformDate.getSysDate());
        List<FinProductExpireFeeInfoDto> list = finProductExpireMapper.queryFeeInfoListByFinprodId(
            map);
        if (!StringUtil.isEmptyList(list)) {
            for (FinProductExpireFeeInfoDto finProductExpireFeeInfoDto : list) {
                BigDecimal basis = new BigDecimal(365);
                if (StringUtil.equals(finProductExpireFeeInfoDto.getBasis(),
                    DictCodeList.计息基础_A_360.getValue())) {
                    basis = new BigDecimal(360);
                }
                finProductExpireFeeInfoDto.setFeeAmt(
                    ArithmeticUtil.divide(
                        ArithmeticUtil.multiply(finProductExpireFeeInfoDto.getPrinamt(),
                            finProductExpireFeeInfoDto.getFeeRate(),
                            new BigDecimal(DateUtil.getBetweenDayNumber(
                                finProductExpireFeeInfoDto.getStartDate(),
                                finProductExpireFeeInfoDto.getEndDate()))),
                        basis, 2));
                finProductExpireFeeInfoDto.setActFeeAmt(finProductExpireFeeInfoDto.getFeeAmt());
                finProductExpireFeeInfoDto.setActFeeRate(finProductExpireFeeInfoDto.getFeeRate());

            }
        }
        return list;
    }

    /** 根据金融产品代码查询分行信息
     * @param map 参数
     * @return 分行信息
     */
    public List<FinProductExpireBranchInfoDto> queryBranchInfoListByFinprodId(Map<String, Object> map) {
        map.put("cashTypeFinProd", DictCodeListExt1.现金流业务类型_金融产品.getValue());
        map.put("cashFirstTypeInt", DictCodeListExt1.现金流一级类型_利息.getValue());
        map.put("cashFirstTypeFee", DictCodeListExt1.现金流一级类型_费用.getValue());
        map.put("cashSecondTypeInt", DictCodeListExt1.现金流二级类型_利息.getValue());
        map.put("cashSecondTypeSale", DictCodeListExt1.现金流二级类型_销售费.getValue());
        map.put("cashSecondTypeOSale", DictCodeListExt1.现金流二级类型_代销费.getValue());
        map.put("yesNoNo", DictCodeList.是否_否.getValue());
        map.put("yes", DictCodeList.是否_是.getValue());
        map.put("platDate", iPlatformDate.getSysDate());
        List<FinProductExpireBranchInfoDto> list = finProductExpireMapper.queryBranchInfoListByFinprodId(
            map);
        if (!StringUtil.isEmptyList(list)) {
            for (FinProductExpireBranchInfoDto finProductExpireBranchInfoDto : list){
                //应付收益 = 本金金额*利率*（计息截止日-计息开始日）/计息基础
                BigDecimal prdBasis = new BigDecimal(365);
                if (StringUtil.equals(finProductExpireBranchInfoDto.getPrdBasis(),
                    DictCodeList.计息基础_A_360.getValue())) {
                    prdBasis = new BigDecimal(360);
                }
                finProductExpireBranchInfoDto.setProfitAmt(ArithmeticUtil.divide(
                    ArithmeticUtil.multiply(finProductExpireBranchInfoDto.getPrinAmt(),
                        finProductExpireBranchInfoDto.getPrdYield(),
                        new BigDecimal(DateUtil.getBetweenDayNumber(
                            finProductExpireBranchInfoDto.getStartDate(),
                            finProductExpireBranchInfoDto.getEndDate()))),
                    prdBasis, 2));
                finProductExpireBranchInfoDto.setActProfitAmt(finProductExpireBranchInfoDto.getProfitAmt());
                
                //应付销售费 = 本金金额*销售费率率*（计息截止日-计息开始日）/计息基础
                BigDecimal saleBasis = new BigDecimal(365);
                if (StringUtil.equals(finProductExpireBranchInfoDto.getSaleBasis(),
                    DictCodeList.计息基础_A_360.getValue())) {
                    saleBasis = new BigDecimal(360);
                }
                finProductExpireBranchInfoDto.setSaleFeeAmt(ArithmeticUtil.divide(
                    ArithmeticUtil.multiply(finProductExpireBranchInfoDto.getPrinAmt(),
                        finProductExpireBranchInfoDto.getSaleRate(),
                        new BigDecimal(DateUtil.getBetweenDayNumber(
                            finProductExpireBranchInfoDto.getStartDate(),
                            finProductExpireBranchInfoDto.getEndDate()))),
                    saleBasis, 2));
                finProductExpireBranchInfoDto.setActSaleFeeAmt(finProductExpireBranchInfoDto.getSaleFeeAmt());
                
                //应付代销费 = 本金金额*代销费率率*（计息截止日-计息开始日）/计息基础
                BigDecimal oSaleBasis = new BigDecimal(365);
                if (StringUtil.equals(finProductExpireBranchInfoDto.getoSaleBasis(),
                    DictCodeList.计息基础_A_360.getValue())) {
                    oSaleBasis = new BigDecimal(360);
                }
                finProductExpireBranchInfoDto.setoSaleFeeAmt(ArithmeticUtil.divide(
                    ArithmeticUtil.multiply(finProductExpireBranchInfoDto.getPrinAmt(),
                        finProductExpireBranchInfoDto.getoSaleRate(),
                        new BigDecimal(DateUtil.getBetweenDayNumber(
                            finProductExpireBranchInfoDto.getStartDate(),
                            finProductExpireBranchInfoDto.getEndDate()))),
                    oSaleBasis, 2));
                finProductExpireBranchInfoDto.setActOSaleFeeAmt(finProductExpireBranchInfoDto.getoSaleFeeAmt());
            }
        }
        
        return list;
    }

    /** 根据交易编号查询到期信息
     * @param tradeId 交易编号
     * @return 到期信息
     */
    public List<FinProductExpireMInfoDto> queryMInfoListByTradeId(String tradeId){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tradeId", tradeId);
        map.put("psSH", DictCodeList.交易方向_产品赎回.getValue());
        map.put("psDQ", DictCodeList.交易方向_产品到期.getValue());
        map.put("psInt", DictCodeList.交易方向_赎回收益确认.getValue());
        return finProductExpireMapper.queryMInfoListByTradeId(map);
    }

    /** 根据交易编号查询费用信息
     * @param tradeId 交易编号
     * @return 费用信息
     */
    public List<FinProductExpireFeeInfoDto> queryFeeInfoListByTradeId(String tradeId){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tradeId", tradeId);
        map.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
        map.put("cashType2", DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
        map.put("psFee", DictCodeList.交易方向_产品付费.getValue());
        return finProductExpireMapper.queryFeeInfoListByTradeId(map);
    }
    
    /** 根据交易编号查询分行信息
     * @param tradeId 交易编号
     * @return 分行信息
     */
    public List<FinProductExpireBranchInfoDto> queryBranchInfoListByTradeId(String tradeId){
        return finProductExpireMapper.queryBranchInfoListByTradeId(tradeId);
    }
    
    public FinProductCommonInfoDto queryProdInfoByMdate(Map<String, Object> map){
        map.put("cashType1", DictCodeListExt1.现金流一级类型_利息.getValue());
        map.put("cashType2", DictCodeListExt1.现金流二级类型_利息.getValue());
        FinProductCommonInfoDto dto=finProductExpireMapper.queryProdInfoByMdate(map);
        //List<BussFinProductFundAccountInfoDto> list= finProductExpireMapper.getFunInfo(map);
        //dto.setBussFinProductFundAccountInfoDto(list);
        return dto;
    }
    
    public List<ReduceInfoDto> queryInterest(Map<String, Object> map){
        map.put("psC15", DictCodeList.交易方向_赎回收益确认.getValue());
        map.put("psC7", DictCodeList.交易方向_中间付息.getValue());
        map.put("psC20", DictCodeList.交易方向_提前终止.getValue());
        //modify by wm 当收益型产品赎回、到期赎回利息为0不展示分层内容问题 start 2019-07-20
        map.put("psC4", DictCodeList.交易方向_产品赎回.getValue());
        map.put("psC5", DictCodeList.交易方向_产品到期.getValue());
        List<ReduceInfoDto> list = finProductExpireMapper.queryInterest(map);
        if(StringUtil.isEmptyList(list)){
            return finProductExpireMapper.queryPrinAmt(map);
        }
        return list;
//        return finProductExpireMapper.queryInterest(map);
      //modify by wm 当收益型产品赎回、到期赎回利息为0不展示分层内容问题 end 2019-07-20
    }
    
    /**
     * 根据到期汇总交易编号查询到期交易
     * <功能详细描述>
     * @param tradeId
     * @return
     * @see
     * @since 1.0
     */
    public FinProductExpire001Dto queryProdExpireByTotleTradeId(String tradeId){
        
        return finProductExpireMapper.queryProdExpireByTotleTradeId(tradeId ,DictCodeListExt1.金融产品类型_理财产品.getValue());
    }
    /**
     * 查询产品是否做到期交易
     * @param param
     * @return
     */
    public Integer isExpireTradeByFinprodId(Map<String,Object> param){
    	return finProductExpireMapper.isExpireTradeByFinprodId(param);
    }
}
