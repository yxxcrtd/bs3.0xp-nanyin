package com.joyintech.tams.settle.batch.datadeal.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.constant.DictCodeListStl;
import com.joyintech.tams.settle.batch.datadeal.constant.StlDataDealProcessConstant;
import com.joyintech.tams.settle.batch.datadeal.dao.StlDownDataDealMapper;
import com.joyintech.tams.settle.batch.datadeal.dto.StlDownDataDealOperatorDto;
import com.joyintech.tams.settle.batch.datadeal.dto.StlDownDataDealWriteDto;
import com.joyintech.tams.settle.batch.datadeal.dto.step2.StlDownDataDealWriteBaseDto;
import com.joyintech.tams.settle.batch.datadeal.function.PersonalityFunction;
import com.joyintech.tams.settle.common.dto.FundInfoDto;
import com.joyintech.tams.settle.common.dto.StlDownDataDealAccountInfoDto;
import com.joyintech.tams.settle.common.service.SettleCommonService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 深中登SJSJG
 * 
 * @author 陈卓
 * @date 2020/7/11
 * @see StlDataDealSzdSjsjgProcessService
 * @since 1.0
 */
@Service
public class StlDataDealSzdSjsjgProcessService {

    public static final List<String> LIST_ZQLB_SHARES = Arrays.asList(
            StlDataDealProcessConstant.SJSMX_ZQLB_00,
            StlDataDealProcessConstant.SJSMX_ZQLB_03,
            StlDataDealProcessConstant.SJSMX_ZQLB_07,
            StlDataDealProcessConstant.SJSMX_ZQLB_30,
            StlDataDealProcessConstant.SJSMX_ZQLB_37
    );
    private static final List<String> LIST_ZQLB_BONDS = Arrays.asList(
            StlDataDealProcessConstant.SJSMX_ZQLB_10,
            StlDataDealProcessConstant.SJSMX_ZQLB_11,
            StlDataDealProcessConstant.SJSMX_ZQLB_12,
            StlDataDealProcessConstant.SJSMX_ZQLB_1A,
            StlDataDealProcessConstant.SJSMX_ZQLB_1C,
            StlDataDealProcessConstant.SJSMX_ZQLB_1E,
            StlDataDealProcessConstant.SJSMX_ZQLB_1F,
            StlDataDealProcessConstant.SJSMX_ZQLB_1H,
            StlDataDealProcessConstant.SJSMX_ZQLB_1J,
            StlDataDealProcessConstant.SJSMX_ZQLB_1R,
            StlDataDealProcessConstant.SJSMX_ZQLB_1S,
            StlDataDealProcessConstant.SJSMX_ZQLB_1X,
            StlDataDealProcessConstant.SJSMX_ZQLB_1Y,
            StlDataDealProcessConstant.SJSMX_ZQLB_1Z
    );
    private static final List<String> LIST_ZQLB_FUNDS = Arrays.asList(
            StlDataDealProcessConstant.SJSMX_ZQLB_15,
            StlDataDealProcessConstant.SJSMX_ZQLB_16,
            StlDataDealProcessConstant.SJSMX_ZQLB_18,
            StlDataDealProcessConstant.SJSMX_ZQLB_EF,
            StlDataDealProcessConstant.SJSJG_ZQLB_EJ
    );

    @Autowired
    private StlDataDealProcessCommonService stlDataDealProcessCommonService;

    @Autowired
    private SettleCommonService settleCommonService;

    @Autowired(required = false)
    private StlDownDataDealService stlDownDataDealService;
	
	@Autowired
    protected StlDownDataDealMapper stlDownDataDealMapper;

    // 生成数据清算批处理step2 processor的结果Dto(深中登SJSJG)
    public StlDownDataDealWriteDto generatorStlDownDataDealSzdSjsjgWriteDto(StlDownDataDealOperatorDto stlDownDataDealOperatorDto) throws ParseException {
        // 核算信息全量数据
        Map<String, List<StlDownDataDealAccountInfoDto>> map = settleCommonService.getAllStlDownDataDealAccountInfoDtoList(true);
        // 资产类型
        String finprodType = stlDownDataDealOperatorDto.getFinprodType();
        StlDownDataDealWriteDto stlDownDataDealWriteDto = new StlDownDataDealWriteDto();
        if (StringUtil.equals(DictCodeListExt1.证券品种_债券.getValue(), finprodType)) {
        	// 将SzdSjsjg清算数据List中，证券类别是PGQZ的数据去除，单独放在PGQZ的SJSJG清算数据List里面做后续处理。
    		List<SzdSjsjg> pgqzSzdSjsjgList = new ArrayList<SzdSjsjg>();
    		List<SzdSjsjg> notPgqzSzdSjsjgList = new ArrayList<SzdSjsjg>();
        	if (stlDownDataDealOperatorDto != null && stlDownDataDealOperatorDto.getStlDownDataDealOperatorBaseDto() != null &&
       			stlDownDataDealOperatorDto.getStlDownDataDealOperatorBaseDto().getSjsjgList() != null && 
       			stlDownDataDealOperatorDto.getStlDownDataDealOperatorBaseDto().getSjsjgList().size() > 0) {
        		for (SzdSjsjg szdSjsjg : stlDownDataDealOperatorDto.getStlDownDataDealOperatorBaseDto().getSjsjgList()) {
        			if (StringUtil.equals(szdSjsjg.getJgywlb(), StlDataDealProcessConstant.YWLB_PGQZ)) {
        				pgqzSzdSjsjgList.add(szdSjsjg);
        			}
        			else {
        				notPgqzSzdSjsjgList.add(szdSjsjg);
        			}
        		}
        		stlDownDataDealOperatorDto.getStlDownDataDealOperatorBaseDto().setSjsjgList(notPgqzSzdSjsjgList);
        	}
        	stlDownDataDealWriteDto = secTypeIsBondDeal(stlDownDataDealOperatorDto, map);
            // 佣金计算
         	stlDownDataDealWriteDto = stlDataDealProcessCommonService.calcCommsionAmt(stlDownDataDealOperatorDto, stlDownDataDealWriteDto);
            // PGQZ的SJSJG清算数据List数据单独处理
            if (pgqzSzdSjsjgList != null && pgqzSzdSjsjgList.size() > 0) {
            	List<FinProductQuto> finProductQutoList = generatorBondFinProductQutoList(pgqzSzdSjsjgList, map, stlDownDataDealOperatorDto);
            	stlDownDataDealWriteDto.setFinProductQutoList(finProductQutoList);
            }
        }
        else if (StringUtil.equals(DictCodeListExt1.证券品种_回购.getValue(), finprodType)) {
        	stlDownDataDealWriteDto = secTypeIsRepoDeal(stlDownDataDealOperatorDto, map);
            // 佣金计算
         	stlDownDataDealWriteDto = stlDataDealProcessCommonService.calcCommsionAmt(stlDownDataDealOperatorDto, stlDownDataDealWriteDto);
        }
        else if (StringUtil.equals(DictCodeListExt1.证券品种_基金.getValue(), finprodType)) {
        	stlDownDataDealWriteDto = secTypeIsFundDeal(stlDownDataDealOperatorDto, map);
            // 佣金计算
            stlDownDataDealWriteDto = stlDataDealProcessCommonService.calcCommsionAmt(stlDownDataDealOperatorDto, stlDownDataDealWriteDto);
        }
        else if (StringUtil.equals(DictCodeListExt1.证券品种_股票.getValue(), finprodType)) {
        	// 将SzdSjsjg清算数据List中，证券类别是PGQZ的数据去除，单独放在PGQZ的SJSJG清算数据List里面做后续处理。
    		List<SzdSjsjg> pgqzSzdSjsjgList = new ArrayList<SzdSjsjg>();
    		List<SzdSjsjg> notPgqzSzdSjsjgList = new ArrayList<SzdSjsjg>();
        	if (stlDownDataDealOperatorDto != null && stlDownDataDealOperatorDto.getStlDownDataDealOperatorBaseDto() != null &&
       			stlDownDataDealOperatorDto.getStlDownDataDealOperatorBaseDto().getSjsjgList() != null && 
       			stlDownDataDealOperatorDto.getStlDownDataDealOperatorBaseDto().getSjsjgList().size() > 0) {
        		for (SzdSjsjg szdSjsjg : stlDownDataDealOperatorDto.getStlDownDataDealOperatorBaseDto().getSjsjgList()) {
        			if (StringUtil.equals(szdSjsjg.getJgywlb(), StlDataDealProcessConstant.YWLB_PGQZ)) {
        				pgqzSzdSjsjgList.add(szdSjsjg);
        			}
        			else {
        				notPgqzSzdSjsjgList.add(szdSjsjg);
        			}
        		}
        		stlDownDataDealOperatorDto.getStlDownDataDealOperatorBaseDto().setSjsjgList(notPgqzSzdSjsjgList);
        	}
        	stlDownDataDealWriteDto = secTypeIsSharesDeal(stlDownDataDealOperatorDto, map);
            // 佣金计算
            stlDownDataDealWriteDto = stlDataDealProcessCommonService.calcCommsionAmt(stlDownDataDealOperatorDto, stlDownDataDealWriteDto);
            // PGQZ的SJSJG清算数据List数据单独处理
            if (pgqzSzdSjsjgList != null && pgqzSzdSjsjgList.size() > 0) {
            	List<FinProductQuto> finProductQutoList = generatorSharesFinProductQutoList(pgqzSzdSjsjgList, map, stlDownDataDealOperatorDto);
            	stlDownDataDealWriteDto.setFinProductQutoList(finProductQutoList);
            }
        }
     	
        return stlDownDataDealWriteDto;
    }

    /**
     * 债券类型SJSMX数据处理
     * @param stlDownDataDealOperatorDto StlDownDataDealWriteDto
     * @param map 核算信息
     * @return 结算数据
     * @throws ParseException
     */
    private StlDownDataDealWriteDto secTypeIsBondDeal(StlDownDataDealOperatorDto stlDownDataDealOperatorDto, Map<String, List<StlDownDataDealAccountInfoDto>> map) throws ParseException {
    	// 债转股的话，输入参数需要单独处理。根据股的数据重新设置清算本金和收付净额
    	List<SzdSjsjg> szdSjsjgList = stlDownDataDealOperatorDto.getStlDownDataDealOperatorBaseDto().getSjsjgList();
    	if (szdSjsjgList != null && szdSjsjgList.size() > 0 && StringUtil.equals(szdSjsjgList.get(0).getJgywlb(), StlDataDealProcessConstant.SJSJG_YWLB_ZQZG)) {
    		List<SzdSjsjg> szdSjsjgListNew = new ArrayList<SzdSjsjg>();
    		SzdSjsjg szdSjsjgZq = new SzdSjsjg();
    		SzdSjsjg szdSjsjgGp = new SzdSjsjg();
    		for (SzdSjsjg szdSjsjg : szdSjsjgList) {
    			if (StringUtil.equals(szdSjsjg.getJgzqlb(), StlDataDealProcessConstant.SJSMX_ZQLB_12) || StringUtil.equals(szdSjsjg.getJgzqlb(), StlDataDealProcessConstant.SJSMX_ZQLB_1E)) {
    				szdSjsjgZq = szdSjsjg;
    			}
    			else {
    				szdSjsjgGp = szdSjsjg;
    			}
    		}
    		szdSjsjgZq.setJgqsbj(szdSjsjgGp.getJgqsbj());
    		szdSjsjgZq.setJgsfje(szdSjsjgGp.getJgsfje());
    		szdSjsjgGp.setJgqsbj(new BigDecimal(0L));
    		szdSjsjgGp.setJgsfje(new BigDecimal(0L));
    		szdSjsjgListNew.add(szdSjsjgZq);
    		szdSjsjgListNew.add(szdSjsjgGp);
    		stlDownDataDealOperatorDto.getStlDownDataDealOperatorBaseDto().setSjsjgList(szdSjsjgListNew);
    	}
    	
        return secTypeCommonDeal(stlDownDataDealOperatorDto, map, ((szdSjsjg, settleTransData, settleTransDataAdd) -> {
            // 金融产品代码
            settleTransData.setFinprodId(DictCodeListExt1.金融产品类型_投管用_债券.getValue() + GlobalConstant.UNDER_LINE + szdSjsjg.getJgzqdm() + ".SZ");
            // 金融产品类型(投管分类)
            settleTransData.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_债券.getValue());
            // 金融产品类型(估值核算)
            settleTransData.setFinprodType(DictCodeListExt1.金融产品类型_债券.getValue());
            // 单位净价
            settleTransData.setUnitCprice(szdSjsjg.getJgcjjg());
            // 单位全价
            settleTransData.setUnitFprice(szdSjsjg.getJgqsjg());
            // 交易份额
            settleTransData.setShareAmt(abs(szdSjsjg.getJgqssl()));
            // 全价总额
            settleTransData.setFpriceAmt(abs(szdSjsjg.getJgqsbj()));
            // 业务类型
            settleTransData.setBusiType(DictCodeList.业务类型_银行间债券交易.getValue());
            // 交易方向、业务场景明细分类、交易份额等设置
            setBondDeal(szdSjsjg, settleTransData, settleTransDataAdd);

            return true;
        }));
    }

    /**
     * 回购类型SJSMX数据处理
     * @param stlDownDataDealOperatorDto StlDownDataDealWriteDto
     * @param map 核算信息
     * @return 结算数据
     * @throws ParseException
     */
    private StlDownDataDealWriteDto secTypeIsRepoDeal(StlDownDataDealOperatorDto stlDownDataDealOperatorDto, Map<String, List<StlDownDataDealAccountInfoDto>> map) throws ParseException {
        return secTypeCommonDeal(stlDownDataDealOperatorDto, map, ((szdSjsjg, settleTransData, settleTransDataAdd) -> {
            // 交易币种固定为人民币
            settleTransData.setCcy(DictCodeList.币种_人民币_CNY.getValue());
            // 利率
            settleTransData.setIntRate(abs(szdSjsjg.getJgcjjg()));
            // 计息基础
            settleTransData.setBasis(DictCodeList.计息基础_A_365.getValue());
            // 回购交易
            setRepoDeal(szdSjsjg, settleTransData);

            settleTransDataAdd.setTradeId(settleTransData.getTradeId());
            settleTransDataAdd.setRepoType(DictCodeList.回购类型_质押式协议回购.getValue());
            settleTransDataAdd.setSeRepDayType(CacheUtil.getSysParam(StlDataDealProcessConstant.STL_SE_REP_DAY_TYPE));
            return true;
        }));
    }

    /**
     * 基金
     * @param stlDownDataDealOperatorDto
     * @param map
     * @return
     * @throws ParseException
     */
    private StlDownDataDealWriteDto secTypeIsFundDeal(StlDownDataDealOperatorDto stlDownDataDealOperatorDto, Map<String, List<StlDownDataDealAccountInfoDto>> map) throws ParseException {
        // 全量基金信息获取
        Map<String, FundInfoDto> fundInfoDtoMap = settleCommonService.getAllFundInfoDto();
        return secTypeCommonDeal(stlDownDataDealOperatorDto, map, ((szdSjsjg, settleTransData, settleTransDataAdd) -> {
            // 金融产品代码
            settleTransData.setFinprodId(DictCodeListExt1.金融产品类型_投管用_基金.getValue() + GlobalConstant.UNDER_LINE + szdSjsjg.getJgzqdm() + ".SZ");
            // 金融产品类型(投管分类)
            settleTransData.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_基金.getValue());
            // 金融产品类型(估值核算)
            settleTransData.setFinprodType(DictCodeListExt1.金融产品类型_基金.getValue());
            FundInfoDto fundInfoDto = fundInfoDtoMap.get(settleTransData.getFinprodId());
            if(null == fundInfoDto) {
                return false;
            }
            // 业务类型
            if (StringUtil.equals(DictCodeList.基金类型_货币基金.getValue(), fundInfoDto.getType1())) {
                settleTransData.setBusiType(DictCodeList.业务类型_货币基金交易_货基_类货基资产交易.getValue());
            } else {
                settleTransData.setBusiType(DictCodeList.业务类型_非货币基金交易_非货基_非类货基资产交易.getValue());
            }
            // 单位净价
            settleTransData.setUnitCprice(szdSjsjg.getJgcjjg());
            // 交易本金
            settleTransData.setPrinAmt(szdSjsjg.getJgqsbj());
            // 利息总额
            settleTransData.setIntAmt(szdSjsjg.getJgqsbj());
            // 全价总额
            settleTransData.setFpriceAmt(szdSjsjg.getJgqsbj());
            setFundDeal(szdSjsjg,settleTransData,settleTransDataAdd);
            return true;
        }));
    }

    private StlDownDataDealWriteDto secTypeIsSharesDeal(StlDownDataDealOperatorDto stlDownDataDealOperatorDto, Map<String, List<StlDownDataDealAccountInfoDto>> map) throws ParseException {

        return secTypeCommonDeal(stlDownDataDealOperatorDto, map, ((szdSjsjg, settleTransData, settleTransDataAdd) -> {
        	// 金融产品代码
			settleTransData.setFinprodId(DictCodeListExt1.金融产品类型_投管用_股票.getValue() + GlobalConstant.UNDER_LINE + szdSjsjg.getJgzqdm() + ".SZ");
            // 金融产品类型(投管分类)
            settleTransData.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_股票.getValue());
            // 金融产品类型(估值核算)
            settleTransData.setFinprodType(DictCodeListExt1.金融产品类型_股票.getValue());
            // 业务类型
            settleTransData.setBusiType(DictCodeList.业务类型_股票交易.getValue());
            // 单位净价
            settleTransData.setUnitCprice(szdSjsjg.getJgcjjg());
            setSharesDeal(szdSjsjg,settleTransData,settleTransDataAdd);
            return true;
        }));
    }

    private void setSharesDeal(SzdSjsjg szdSjsjg, SettleTransData settleTransData, SettleTransDataAdd settleTransDataAdd) {
        String ywlb = szdSjsjg.getJgywlb();
        // 要约收购
        if(StringUtil.equals(ywlb, StlDataDealProcessConstant.SJSJG_YWLB_YYZJ)) {
            // TODO
        }
        // 配售
        else if((StringUtil.equals(ywlb, StlDataDealProcessConstant.SJSJG_YWLB_PGDZ)
                || StringUtil.equals(ywlb, StlDataDealProcessConstant.SJSJG_YWLB_PG90))
                && StringUtil.equals("01", szdSjsjg.getJgsjlx())) {
            settleTransData.setPs(DictCodeListExt2.交易方向_配股.getValue());
            settleTransData.setBusiSceneCalss(DictCodeListStl.业务场景分类明细_股票_配股认购_配股.getValue());
            if(StringUtil.equals(ywlb, StlDataDealProcessConstant.SJSJG_YWLB_PGDZ)) {
                settleTransData.setFinprodId(DictCodeListExt1.金融产品类型_投管用_股票.getValue() + GlobalConstant.UNDER_LINE + szdSjsjg.getJgzqdm() + ".SZ");
            }
            settleTransData.setShareAmt(abs(szdSjsjg.getJgjssl()));
        }
        //股息派发、红股及转增股
        else if(StringUtil.equals(ywlb, StlDataDealProcessConstant.SJSJG_YWLB_QPPX)
                || StringUtil.equals(ywlb, StlDataDealProcessConstant.SJSJG_YWLB_QPHG)
                || StringUtil.equals(ywlb, StlDataDealProcessConstant.SJSJG_YWLB_QOLS)) {
            settleTransData.setFpriceAmt(abs(szdSjsjg.getJgzjje()));
            settleTransData.setPs(DictCodeList.交易方向_现金分红.getValue());
            settleTransData.setBusiSceneCalss(DictCodeListStl.业务场景分类明细_股票_分红_现金分红.getValue());
        }
    }

    /**
     * 基金交易时设置交易方向和业务场景明细
     * @param szdSjsjg
     * @param settleTransData
     * @param settleTransDataAdd
     */
    private void setFundDeal(SzdSjsjg szdSjsjg, SettleTransData settleTransData, SettleTransDataAdd settleTransDataAdd) {
        String ywlb = szdSjsjg.getJgywlb();
        String zqlb = szdSjsjg.getJgzqlb();
        // 货币ETF收益
        if(StringUtil.equals(ywlb, StlDataDealProcessConstant.SJSJG_YWLB_EFSY)) {
            // 交易方向
            settleTransData.setPs(DictCodeList.交易方向_现金分红.getValue());
            // 交易份额
            settleTransData.setShareAmt(BigDecimal.ZERO);
            // 交易金额
            settleTransData.setTradeAmt(abs(szdSjsjg.getJgsfje()));
            // 业务场景分类明细
            settleTransData.setBusiSceneCalss(DictCodeListStl.业务场景分类明细_基金_ETF_分红_现金分红.getValue());
        }
        // 基金分红
        else if((StringUtil.equals(ywlb, StlDataDealProcessConstant.SJSJG_YWLB_QPPX)
                || StringUtil.equals(ywlb, StlDataDealProcessConstant.SJSJG_YWLB_QPZT))
                && LIST_ZQLB_FUNDS.contains(zqlb)){
            // 交易份额
            settleTransData.setShareAmt(szdSjsjg.getJgjssl());
            // 交易金额
            settleTransData.setTradeAmt(abs(szdSjsjg.getJgsfje()));
            if(StringUtil.equals(ywlb, StlDataDealProcessConstant.SJSJG_YWLB_QPPX)) {
                // 交易方向
                settleTransData.setPs(DictCodeList.交易方向_现金分红.getValue());
                // 业务场景分类明细
                if(StringUtil.equals(zqlb, StlDataDealProcessConstant.SJSMX_ZQLB_15)) {
                    settleTransData.setBusiSceneCalss(DictCodeListStl.业务场景分类明细_基金_LOF_分红_现金分红.getValue());
                }
                if(StringUtil.equals(zqlb, StlDataDealProcessConstant.SJSMX_ZQLB_16)) {
                    settleTransData.setBusiSceneCalss(DictCodeListStl.业务场景分类明细_基金_开放式基金_分红_现金分红.getValue());
                }
                if(StringUtil.equals(zqlb, StlDataDealProcessConstant.SJSMX_ZQLB_18)) {
                    settleTransData.setBusiSceneCalss(DictCodeListStl.业务场景分类明细_基金_封闭式基金_分红_现金分红.getValue());
                }
                if(StringUtil.equals(zqlb, StlDataDealProcessConstant.SJSMX_ZQLB_EF)) {
                    settleTransData.setBusiSceneCalss(DictCodeListStl.业务场景分类明细_基金_ETF_分红_现金分红.getValue());
                }
            }
            else if(StringUtil.equals(ywlb, StlDataDealProcessConstant.SJSJG_YWLB_QPZT)) {
                // 交易方向
                settleTransData.setPs(DictCodeList.交易方向_红利再投.getValue());
                // 业务场景分类明细
                if(StringUtil.equals(zqlb, StlDataDealProcessConstant.SJSMX_ZQLB_15)) {
                    settleTransData.setBusiSceneCalss(DictCodeListStl.业务场景分类明细_基金_LOF_分红_红利再投.getValue());
                }
                if(StringUtil.equals(zqlb, StlDataDealProcessConstant.SJSMX_ZQLB_16)) {
                    settleTransData.setBusiSceneCalss(DictCodeListStl.业务场景分类明细_基金_开放式基金_分红_红利再投.getValue());
                }
                if(StringUtil.equals(zqlb, StlDataDealProcessConstant.SJSMX_ZQLB_18)) {
                    settleTransData.setBusiSceneCalss(DictCodeListStl.业务场景分类明细_基金_封闭式基金_分红_红利再投.getValue());
                }
                if(StringUtil.equals(zqlb, StlDataDealProcessConstant.SJSMX_ZQLB_EF)) {
                    settleTransData.setBusiSceneCalss(DictCodeListStl.业务场景分类明细_基金_ETF_分红_红利再投.getValue());
                }
                // 交割日期需要设置成清算日期
                settleTransData.setSettleDate(settleTransData.getTradeDate());
            }
        }
    }

    /**
     * 债券交易时设置交易方向和业务场景明细
     * @param szdSjsjg
     * @param settleTransData
     */
    private void setBondDeal(SzdSjsjg szdSjsjg, SettleTransData settleTransData, SettleTransDataAdd settleTransDataAdd) throws ParseException {
        // 债券赎回
    	if (StringUtil.equals(StlDataDealProcessConstant.SJSJG_YWLB_ZQSH, szdSjsjg.getJgywlb())) {
    		settleTransData.setPs(DictCodeList.交易方向_赎回.getValue());
    		settleTransData.setIntAmt(abs(szdSjsjg.getJgzjje()));
    		settleTransData.setPrinAmt(abs(szdSjsjg.getJgzjje()));
    		settleTransData.setShareAmt(abs(szdSjsjg.getJgjssl()));
            settleTransData.setBusiSceneCalss(DictCodeListStl.业务场景分类明细_债券_赎回.getValue());
    	}
    	// 债券回售
    	if ((StringUtil.equals(StlDataDealProcessConstant.SJSJG_YWLB_ZQHS, szdSjsjg.getJgywlb()) || StringUtil.equals(StlDataDealProcessConstant.SJSJG_YWLB_ZQHZ, szdSjsjg.getJgywlb())) &&
    			StringUtil.equals(StlDataDealProcessConstant.SJSMX_GFXZ_00, szdSjsjg.getJggfxz())) {
    		settleTransData.setPs(DictCodeList.交易方向_行权_回售.getValue());
    		settleTransData.setIntAmt(abs(szdSjsjg.getJgzjje()));
    		settleTransData.setPrinAmt(abs(szdSjsjg.getJgzjje()));
    		settleTransData.setShareAmt(abs(szdSjsjg.getJgjssl()));
            settleTransData.setBusiSceneCalss(DictCodeListStl.业务场景分类明细_债券_回售_行权回售.getValue());
            // 交易日期
            settleTransData.setTradeDate(DateUtil.parseDate(szdSjsjg.getJgqsrq(), DateUtil.DEFAULT_OTHER_FORMAT));
    	}
    	// 可交换私募债换股-债券变动
    	if (StringUtil.equals(StlDataDealProcessConstant.SJSJG_YWLB_ZQHG, szdSjsjg.getJgywlb()) && LIST_ZQLB_BONDS.contains(szdSjsjg.getJgzqlb())) {
    		settleTransData.setPs(DictCodeListStl.交易方向_换股.getValue());
            settleTransData.setBusiSceneCalss(DictCodeListStl.业务场景分类明细_债券_换股.getValue());
    	}
    	// 可交换私募债换股-股票变动
    	if (StringUtil.equals(StlDataDealProcessConstant.SJSJG_YWLB_ZQHG, szdSjsjg.getJgywlb()) && LIST_ZQLB_SHARES.contains(szdSjsjg.getJgzqlb())) {
    		settleTransData.setPs(DictCodeListStl.交易方向_换股.getValue());
            settleTransData.setBusiSceneCalss(DictCodeListStl.业务场景分类明细_债券_换股.getValue());
            settleTransData.setFinprodId(DictCodeListExt1.金融产品类型_投管用_股票.getValue() + GlobalConstant.UNDER_LINE + szdSjsjg.getJgzqdm() + ".SZ");
            settleTransData.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_股票.getValue());
            settleTransData.setFinprodType(DictCodeListExt1.金融产品类型_股票.getValue());
    	}
    	// 债转股业务
    	if (StringUtil.equals(StlDataDealProcessConstant.SJSJG_YWLB_ZQZG, szdSjsjg.getJgywlb())) {
    		settleTransData.setPs(DictCodeListExt1.交易方向_债转股.getValue());
    		settleTransData.setBusiSceneCalss(DictCodeListStl.业务场景分类明细_债券_转股_债转股.getValue());
    		settleTransData.setShareAmt(abs(szdSjsjg.getJgjssl()));
    		settleTransData.setPrinAmt(abs(szdSjsjg.getJgqsbj()));
    		if (!StringUtil.equals(StlDataDealProcessConstant.SJSMX_ZQLB_12, szdSjsjg.getJgzqlb()) && !StringUtil.equals(StlDataDealProcessConstant.SJSMX_ZQLB_1E, szdSjsjg.getJgzqlb())) {
                settleTransDataAdd.setTradeId(szdSjsjg.getJgywlsh());
                settleTransDataAdd.setConPrice(szdSjsjg.getJgqsjg());
                settleTransDataAdd.setConAmt(szdSjsjg.getJgjssl());
                settleTransDataAdd.setConPrinAmt(abs(szdSjsjg.getJgqsbj()));
                settleTransDataAdd.setrFinprodId(szdSjsjg.getJgzqdm());
                settleTransData.setFinprodId(DictCodeListExt1.金融产品类型_投管用_股票.getValue() + GlobalConstant.UNDER_LINE + szdSjsjg.getJgzqdm() + ".SZ");
                settleTransData.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_股票.getValue());
                settleTransData.setFinprodType(DictCodeListExt1.金融产品类型_股票.getValue());
                settleTransData.setShareAmt(ArithmeticUtil.divide(abs(szdSjsjg.getJgjssl()), new BigDecimal(100), 2));
    		}
    	}
    	// 配股配债认购
    	if (StringUtil.equals(StlDataDealProcessConstant.SJSJG_YWLB_PGRG, szdSjsjg.getJgywlb()) && LIST_ZQLB_BONDS.contains(szdSjsjg.getJgzqlb())) {
    		settleTransData.setPs(DictCodeListExt3.交易方向_配债.getValue());
    		settleTransData.setBusiSceneCalss(DictCodeListStl.业务场景分类明细_债券_优先配售_申购.getValue());
    		settleTransData.setFinprodId(DictCodeListExt1.金融产品类型_投管用_债券.getValue() + GlobalConstant.UNDER_LINE + szdSjsjg.getJgzqdm2() + ".SZ");
    	}
//    	// 配股配债到账
//    	if (StringUtil.equals(StlDataDealProcessConstant.SJSJG_YWLB_PGDZ, szdSjsjg.getJgywlb()) && LIST_ZQLB_BONDS.contains(szdSjsjg.getJgzqlb())) {
//    		settleTransData.setPs(DictCodeList.交易方向_买入.getValue());
//    		settleTransData.setBusiSceneCalss(DictCodeListStl.业务场景分类明细_债券_优先配售_买入.getValue());
//			settleTransData.setShareAmt(abs(szdSjsjg.getJgcjsl()));
//    	}
    }

    /**
     * 回购交易设置
     * @param szdSjsjg
     * @param settleTransData
     */
    private void setRepoDeal(SzdSjsjg szdSjsjg, SettleTransData settleTransData) throws ParseException {
//        // 清算本金
        BigDecimal qsbj = szdSjsjg.getJgqsbj();
        // 业务类别
        String ywlb = szdSjsjg.getJgywlb();
        // 质押式回购协议：初始交易、购回交易、协议回购合约
        if(StringUtil.equals(ywlb, StlDataDealProcessConstant.SJSJG_YWLB_XYCS)
                || StringUtil.equals(ywlb, StlDataDealProcessConstant.SJSJG_YWLB_XYDQ)
                || StringUtil.equals(ywlb, StlDataDealProcessConstant.SJSJG_YWLB_XYHY)) {
            // 金融产品代码
            settleTransData.setFinprodId(szdSjsjg.getJgfjsm());
            if(StringUtil.equals(ywlb, StlDataDealProcessConstant.SJSJG_YWLB_XYCS)) {
                // 交易方向
                settleTransData.setPs(DictCodeList.交易方向_买入.getValue());
                // 交易份额
                settleTransData.setShareAmt(abs(szdSjsjg.getJgcjsl()));
                // 交易本金
                settleTransData.setPrinAmt(abs(qsbj));
                // 起息日
                settleTransData.setVdate(DateUtil.parseDate(szdSjsjg.getJgcjrq(), DateUtil.DEFAULT_OTHER_FORMAT));
                // 到期日
                settleTransData.setMdate(DateUtil.parseDate(szdSjsjg.getJgqtrq(), DateUtil.DEFAULT_OTHER_FORMAT));
                // 交易日
                settleTransData.setTradeDate(DateUtil.parseDate(szdSjsjg.getJgfsrq(), DateUtil.DEFAULT_OTHER_FORMAT));
                // 到期本金
                settleTransData.setmPrinAmt(settleTransData.getPrinAmt());
                // 首期交收日
                settleTransData.setVpayDate(DateUtil.parseDate(szdSjsjg.getJgjsrq(), DateUtil.DEFAULT_OTHER_FORMAT));
                // 到期交收日（其它日期+1个工作日）
                settleTransData.setMpayDate(nextWorkDay(DateUtil.parseDate(szdSjsjg.getJgqtrq(), DateUtil.DEFAULT_OTHER_FORMAT)));
                // 回购利息
                BigDecimal repayInt = ArithmeticUtil.multiply(
                        ArithmeticUtil.divide(ArithmeticUtil.multiply(abs(qsbj), abs(szdSjsjg.getJgcjjg())), 100, 2),
                        ArithmeticUtil.divide(new BigDecimal(DateUtil.getBetweenDayNumber(settleTransData.getMpayDate(), settleTransData.getVpayDate())), 365,2));
                // 到期金额
                settleTransData.setmTradeAmt(ArithmeticUtil.doAdd(settleTransData.getmPrinAmt(), repayInt));
            } else if (StringUtil.equals(ywlb, StlDataDealProcessConstant.SJSJG_YWLB_XYDQ)) {
                // 交易方向
                settleTransData.setPs(DictCodeList.交易方向_到期.getValue());
                // 交易份额
                settleTransData.setShareAmt(abs(szdSjsjg.getJgjssl()));
                // 交易本金
                settleTransData.setPrinAmt(BigDecimal.ZERO);
                // 到期日
                settleTransData.setMdate(DateUtil.parseDate(szdSjsjg.getJgcjrq(), DateUtil.DEFAULT_OTHER_FORMAT));
                // 交易日
                settleTransData.setTradeDate(DateUtil.parseDate(szdSjsjg.getJgfsrq(), DateUtil.DEFAULT_OTHER_FORMAT));
                // 到期交收日
                settleTransData.setMpayDate(DateUtil.parseDate(szdSjsjg.getJgjsrq(), DateUtil.DEFAULT_OTHER_FORMAT));
                // 到期金额
                settleTransData.setmTradeAmt(abs(qsbj));
            }
            else {
                // 交易方向
                settleTransData.setPs(DictCodeListStl.交易方向_质押券入库.getValue());
                // 交易份额
                settleTransData.setShareAmt(abs(szdSjsjg.getJgjssl()));
                // 交易本金
                settleTransData.setPrinAmt(abs(qsbj));
                // 起息日
                settleTransData.setVdate(DateUtil.parseDate(szdSjsjg.getJgcjrq(), DateUtil.DEFAULT_OTHER_FORMAT));
                // 到期日
                settleTransData.setMdate(DateUtil.parseDate(szdSjsjg.getJgcjrq(), DateUtil.DEFAULT_OTHER_FORMAT));
                // 交易日
                settleTransData.setTradeDate(DateUtil.parseDate(szdSjsjg.getJgfsrq(), DateUtil.DEFAULT_OTHER_FORMAT));
                // 首期交收日
                settleTransData.setVpayDate(DateUtil.parseDate(szdSjsjg.getJgfsrq(), DateUtil.DEFAULT_OTHER_FORMAT));
                // 到期交收日
                settleTransData.setMpayDate(nextWorkDay(DateUtil.parseDate(szdSjsjg.getJgqtrq(), DateUtil.DEFAULT_OTHER_FORMAT)));
            }
            // 根据清算本金设置金融产品类型
            setFinprodType(qsbj,settleTransData);
        }
        else if(StringUtil.equals(ywlb, StlDataDealProcessConstant.SJSJG_YWLB_XYXZ)) {
            // 根据清算本金设置金融产品类型
            setFinprodType(qsbj,settleTransData);
            // 金融产品代码
            settleTransData.setFinprodId(szdSjsjg.getJgfjsm());
            // 业务场景分类
            settleTransData.setBusiSceneCalss(DictCodeListStl.业务场景分类明细_债券协议逆回购_到期续作.getValue());
            // 交易日
            settleTransData.setTradeDate(DateUtil.parseDate(szdSjsjg.getJgfsrq(), DateUtil.DEFAULT_OTHER_FORMAT));
            // 交易方向
            settleTransData.setPs(DictCodeList.交易方向_到期续做.getValue());
            // 起息日
            settleTransData.setVdate(DateUtil.parseDate(szdSjsjg.getJgcjrq(), DateUtil.DEFAULT_OTHER_FORMAT));
            // 到期日
            settleTransData.setMdate(DateUtil.parseDate(szdSjsjg.getJgqtrq(), DateUtil.DEFAULT_OTHER_FORMAT));
            // 首期交收日
            settleTransData.setVpayDate(DateUtil.parseDate(szdSjsjg.getJgfsrq(), DateUtil.DEFAULT_OTHER_FORMAT));
            // 到期交收日
            settleTransData.setMpayDate(nextWorkDay(DateUtil.parseDate(szdSjsjg.getJgqtrq(), DateUtil.DEFAULT_OTHER_FORMAT)));
            // 交易本金
            settleTransData.setPrinAmt(abs(qsbj));
            // 回购利息
            BigDecimal repayInt = ArithmeticUtil.multiply(
                    ArithmeticUtil.divide(ArithmeticUtil.multiply(abs(qsbj), abs(szdSjsjg.getJgcjjg())), 100, 2),
                    ArithmeticUtil.divide(new BigDecimal(DateUtil.getBetweenDayNumber(settleTransData.getMpayDate(), settleTransData.getVpayDate())), 365,2));
            // 利息总额
            settleTransData.setIntAmt(abs(szdSjsjg.getJgzjje()));
            // 到期本金
            settleTransData.setmPrinAmt(settleTransData.getPrinAmt());
            // 到期金额
            settleTransData.setmTradeAmt(ArithmeticUtil.doAdd(settleTransData.getmPrinAmt(), repayInt));
        }
        // 报价回购
        else if(StringUtil.equals(ywlb, StlDataDealProcessConstant.SJSJG_YWLB_BJCS) || StringUtil.equals(ywlb, StlDataDealProcessConstant.SJSJG_YWLB_BJDQ)) {
            // 根据清算本金设置金融产品类型
            setFinprodType(qsbj,settleTransData);
            if(StringUtil.equals(ywlb, StlDataDealProcessConstant.SJSJG_YWLB_BJCS)) {
                // 交易方向
                settleTransData.setPs(DictCodeList.交易方向_买入.getValue());
                // 起息日
                settleTransData.setVdate(DateUtil.parseDate(szdSjsjg.getJgcjrq(), DateUtil.DEFAULT_OTHER_FORMAT));
                // 到期日
                settleTransData.setMdate(DateUtil.parseDate(szdSjsjg.getJgqtrq(), DateUtil.DEFAULT_OTHER_FORMAT));
                // 首期交收日
                settleTransData.setVpayDate(DateUtil.parseDate(szdSjsjg.getJgjsrq(), DateUtil.DEFAULT_OTHER_FORMAT));
                // 到期交收日
                settleTransData.setMpayDate(nextWorkDay(DateUtil.parseDate(szdSjsjg.getJgqtrq(), DateUtil.DEFAULT_OTHER_FORMAT)));
                if(BigDecimal.ZERO.compareTo(qsbj) < 0) {// 清算本金大于0
                    // 业务场景分类
                    settleTransData.setBusiSceneCalss(DictCodeListStl.业务场景分类明细_报价正回购_首期_买入.getValue());
                }
                if(BigDecimal.ZERO.compareTo(qsbj) > 0) {// 清算本金小于0
                    // 业务场景分类
                    settleTransData.setBusiSceneCalss(DictCodeListStl.业务场景分类明细_报价逆回购_首期_买入.getValue());
                }
                // 本金金额
                settleTransData.setPrinAmt(abs(qsbj));
                // 回购利息
                BigDecimal repayInt = ArithmeticUtil.multiply(
                        ArithmeticUtil.divide(ArithmeticUtil.multiply(abs(qsbj), abs(szdSjsjg.getJgcjjg())), 100, 2),
                        ArithmeticUtil.divide(new BigDecimal(DateUtil.getBetweenDayNumber(settleTransData.getMpayDate(), settleTransData.getVpayDate())), 365,2));
                // 到期本金
                settleTransData.setmPrinAmt(settleTransData.getPrinAmt());
                // 到期金额
                settleTransData.setmTradeAmt(ArithmeticUtil.doAdd(settleTransData.getmPrinAmt(), repayInt));
            }
            else {
                // 交易方向
                settleTransData.setPs(DictCodeList.交易方向_到期.getValue());
                // 起息日
                settleTransData.setVdate(DateUtil.parseDate(szdSjsjg.getJgqtrq(), DateUtil.DEFAULT_OTHER_FORMAT));
                // 到期日
                settleTransData.setMdate(DateUtil.parseDate(szdSjsjg.getJgcjrq(), DateUtil.DEFAULT_OTHER_FORMAT));
                // 首期交收日
                settleTransData.setVpayDate(DateUtil.parseDate(szdSjsjg.getJgqtrq(), DateUtil.DEFAULT_OTHER_FORMAT));
                // 到期交收日
                settleTransData.setMpayDate(nextWorkDay(DateUtil.parseDate(szdSjsjg.getJgjsrq(), DateUtil.DEFAULT_OTHER_FORMAT)));
                if(BigDecimal.ZERO.compareTo(qsbj) < 0) {// 清算本金大于0
                    // 业务场景分类
                    settleTransData.setBusiSceneCalss(DictCodeListStl.业务场景分类明细_报价正回购_到期.getValue());
                }
                if(BigDecimal.ZERO.compareTo(qsbj) > 0) {// 清算本金小于0
                    // 业务场景分类
                    settleTransData.setBusiSceneCalss(DictCodeListStl.业务场景分类明细_报价逆回购_到期.getValue());
                }
                // 到期金额
                settleTransData.setmTradeAmt(abs(qsbj));
            }
        }
        // 质押券入库
        else if(StringUtil.equals(ywlb, StlDataDealProcessConstant.SJSJG_YWLB_BJRK)) {
            // 成交编号
            settleTransData.setTradeId(szdSjsjg.getJgddbh());
            // 交易份额
            settleTransData.setShareAmt(abs(szdSjsjg.getJgjssl()));
            // 数据明细质押品信息DTO 在 secTypeCommonDeal 方法中设置
        }
        // 质押券出库
        else if(StringUtil.equals(ywlb, StlDataDealProcessConstant.SJSJG_YWLB_BJCK)) {
            // 成交编号
            settleTransData.setTradeId(szdSjsjg.getJgddbh());
            if(BigDecimal.ZERO.compareTo(szdSjsjg.getJgjssl()) == 0) { //清算数据Entity.交收数量 = 0
                // 交易方向： 数据字典：交易方向.派息派本
                settleTransData.setPs(DictCodeListStl.交易方向_派本派息.getValue());
                // 金融产品代码
                settleTransData.setFinprodId(DictCodeListExt1.金融产品类型_投管用_债券.getValue() + GlobalConstant.UNDER_LINE + szdSjsjg.getJgzqdm2() + ".SZ");
                // 金融产品类型(投管分类)
                settleTransData.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_债券.getValue());
                // 金融产品类型(估值核算)
                settleTransData.setFinprodType(DictCodeListExt1.金融产品类型_债券.getValue());
                // 利息总额
                settleTransData.setIntAmt(abs(szdSjsjg.getJgzjje()));
                // 交易本金
                settleTransData.setPrinAmt(abs(qsbj));
                // 业务类型
                settleTransData.setBusiType(DictCodeList.业务类型_银行间债券交易.getValue());
            }
            else {
                // 交易份额
                settleTransData.setShareAmt(abs(szdSjsjg.getJgjssl()));
                // 数据明细质押品信息DTO 在 secTypeCommonDeal 方法中设置
            }
        }
    }

    /**
     * SJSMX公共数据处理
     * @param stlDownDataDealOperatorDto StlDownDataDealWriteDto
     * @param map 核算信息
     * @return 清算处理结果
     */
    private StlDownDataDealWriteDto secTypeCommonDeal(StlDownDataDealOperatorDto stlDownDataDealOperatorDto, Map<String, List<StlDownDataDealAccountInfoDto>> map, PersonalityFunction<SzdSjsjg, SettleTransData, SettleTransDataAdd> funcion) throws ParseException {
        // 处理结果数据定义
        StlDownDataDealWriteDto stlDownDataDealWriteDto = new StlDownDataDealWriteDto();
        // 遍历 输入.SJSJG清算数据List 清算数据Entity
        for(SzdSjsjg szdSjsjg : stlDownDataDealOperatorDto.getStlDownDataDealOperatorBaseDto().getSjsjgList()) {
            // KEY = 证券账户号码 + 交易单元
            String key = szdSjsjg.getJgzqzh() + szdSjsjg.getJgjydy();
            // 组合代码不在输入的组合代码List里面不处理
            List<StlDownDataDealAccountInfoDto> accountInfoDtolist = map.get(key);

            if (CollectionUtils.isEmpty(accountInfoDtolist)) {
                continue;
            }
            // 组合代码不在输入的组合代码List里面不处理
            else if (stlDownDataDealOperatorDto.getPortfolioIdList().size() > 0 && !stlDownDataDealOperatorDto.getPortfolioIdList().contains(accountInfoDtolist.get(0).getPortfolioId())) {
                continue;
            }

            // 定义变量.成交数据明细DTO
            SettleTransData settleTransData = new SettleTransData();
            // 定义变量.成交数据明细附属表DTO
            SettleTransDataAdd settleTransDataAdd = new SettleTransDataAdd();
            
            // 交易平台
            settleTransData.setTradePlat(stlDataDealProcessCommonService.getTradePlat(szdSjsjg.getJgjyfs()));
            // 交收方式
            settleTransData.setSettleMode(stlDataDealProcessCommonService.getSettleMode(szdSjsjg.getJgjsfs()));
            // 交易币种
            settleTransData.setCcy(stlDataDealProcessCommonService.getCcy(szdSjsjg.getJghbdh()));
            // 成交编号
            settleTransData.setTradeId(szdSjsjg.getJgywlsh());
			// 执行日期
			settleTransData.setDealDate(stlDownDataDealOperatorDto.getDealDate());
			// 证券品种
			settleTransData.setSecType(stlDownDataDealOperatorDto.getFinprodType());
            // 申请编号
            settleTransData.setApplyTradeId(szdSjsjg.getJgddbh());
            // 分支序号
            settleTransData.setBranch(0L);
            // 交易日期
            settleTransData.setTradeDate(DateUtil.parseDate(szdSjsjg.getJgqsrq(), DateUtil.DEFAULT_OTHER_FORMAT));
            // 交割日期
            settleTransData.setSettleDate(DateUtil.parseDate(szdSjsjg.getJgjsrq(), DateUtil.DEFAULT_OTHER_FORMAT));
            // 清算速度
            settleTransData.setSettleSpeed(0L);
            if (DateUtil.compareDate(settleTransData.getTradeDate(), settleTransData.getSettleDate()) != 0){
                settleTransData.setSettleSpeed(1L);
            }
            // 印花税
            settleTransData.setStampFee(abs(szdSjsjg.getJgyhs()));
            // 经手费
            settleTransData.setJsfAmt(abs(szdSjsjg.getJgjyjsf()));
            // 过户费
            settleTransData.setGhfAmt(abs(szdSjsjg.getJgghf()));
            // 证管费
            settleTransData.setZgfAmt(abs(szdSjsjg.getJgjggf()));
            // 手续费
            settleTransData.setSxfAmt(abs(szdSjsjg.getJgsxf()));
            // 结算费
            settleTransData.setSettleFee(abs(szdSjsjg.getJgjsf()));
            // 交易金额
            settleTransData.setTradeAmt(abs(szdSjsjg.getJgsfje()));
            // 交易金额_原始
            settleTransData.setTradeAmtRaw(abs(szdSjsjg.getJgsfje()));
            // 印花税_原始
            settleTransData.setStampFeeRaw(abs(szdSjsjg.getJgyhs()));
            // 经手费_原始
            settleTransData.setJsfAmtRaw(abs(szdSjsjg.getJgjsf()));
            // 过户费_原始
            settleTransData.setGhfAmtRaw(abs(szdSjsjg.getJgghf()));
            // 证管费_原始
            settleTransData.setZgfAmtRaw(abs(szdSjsjg.getJgjggf()));
            // 手续费_原始
            settleTransData.setSxfAmtRaw(abs(szdSjsjg.getJgsxf()));
            // 结算费_原始
            settleTransData.setSettleFeeRaw(abs(szdSjsjg.getJgjsf()));
            // 交易场所
            settleTransData.setTradeMarket(DictCodeList.交易流通场所_深圳证券交易所.getValue());
            // 交易市场
            settleTransData.setTradeMarketMode(DictCodeListExt1.交易市场_二级市场.getValue());
            // 席位号
            settleTransData.setSeatNumber(szdSjsjg.getJgjydy());
            // 组合代码
            settleTransData.setPortfolioId(accountInfoDtolist.get(0).getPortfolioId());
            // 证券账户代码
            settleTransData.setSecAcctId(accountInfoDtolist.get(0).getSecAcctId());
            // 资金账户
            settleTransData.setAcctNo(accountInfoDtolist.get(0).getAcctNo());
			// 临时表流水号
			settleTransData.setSeqNo(szdSjsjg.getSeqno());
            
            // 日终过来的文件结算价格就是全价的，需要反算净价总额、应计利息总额
            stlDownDataDealService.calc(settleTransData);

            if(null != funcion) {
                if(!funcion.apply(szdSjsjg, settleTransData, settleTransDataAdd)) {
                    continue;
                }
            }

            StlDownDataDealWriteBaseDto stlDownDataDealWriteBaseDto = new StlDownDataDealWriteBaseDto();
            stlDownDataDealWriteBaseDto.setSettleTransData(settleTransData);
            if (settleTransDataAdd != null && StringUtil.isNotEmpty(settleTransDataAdd.getTradeId())) {
            	stlDownDataDealWriteBaseDto.setSettleTransDataAdd(settleTransDataAdd);
            }
            // 成交数据与投管交易关系
            SettleTransTgMapping settleTransTgMapping = new SettleTransTgMapping();
            settleTransTgMapping.setTradeId(settleTransData.getTradeId());// 取成交编号
            stlDownDataDealWriteBaseDto.setSettleTransTgMapping(settleTransTgMapping);

            // 成交数据明细质押品信息
            if(StringUtil.equals(szdSjsjg.getJgywlb(), StlDataDealProcessConstant.SJSJG_YWLB_BJRK) // 回购-质押券入库
                    || (StringUtil.equals(szdSjsjg.getJgywlb(), StlDataDealProcessConstant.SJSJG_YWLB_BJCK) && BigDecimal.ZERO.compareTo(szdSjsjg.getJgjssl()) != 0) // 回购-质押券出库
                ) {
                // 数据明细质押品信息DTO
                SettleTransDataPledge settleTransDataPledge = new SettleTransDataPledge();
                settleTransDataPledge.setTradeId(settleTransData.getTradeId());
                settleTransDataPledge.setBranch(0L);
                settleTransDataPledge.setPledgeFinprodId(DictCodeListExt1.金融产品类型_投管用_债券.getValue() + szdSjsjg.getJgzqdm2() + ".SZ");
                settleTransDataPledge.setPledgeNumber(abs(szdSjsjg.getJgjssl()));
                stlDownDataDealWriteBaseDto.getSettleTransDataPledgeList().add(settleTransDataPledge);
            }

            stlDownDataDealWriteDto.getStlDownDataDealWriteBaseDtoList().add(stlDownDataDealWriteBaseDto);
        }
        return stlDownDataDealWriteDto;
    }

    private BigDecimal abs(BigDecimal value) {
        return null != value ? value.abs() : null;
    }

    private void setFinprodType(BigDecimal qsbj, SettleTransData settleTransData) {
        if(BigDecimal.ZERO.compareTo(qsbj) < 0) {// 清算本金大于0
            // 金融产品类型(投管分类)
            settleTransData.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue());
            // 金融产品类型(估值核算)
            settleTransData.setFinprodType(DictCodeListExt1.金融产品类型_债券质押式正回购.getValue());
        }
        if(BigDecimal.ZERO.compareTo(qsbj) > 0) {// 清算本金小于0
            // 金融产品类型(投管分类)
            settleTransData.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue());
            // 金融产品类型(估值核算)
            settleTransData.setFinprodType(DictCodeListExt1.金融产品类型_债券质押式逆回购.getValue());
        }
    }

    /**
     * 取下一个工作日
     * @param parseDate
     * @return
     */
    private Date nextWorkDay(Date parseDate) {
        String calendarId = CacheUtil.getSysParam(StlDataDealProcessConstant.STL_DOWNDATA_DEAL_SZJYS_CALID);
        return stlDataDealProcessCommonService.getNextWorkDay(parseDate, calendarId);
    }
    
    private List<FinProductQuto> generatorBondFinProductQutoList(List<SzdSjsjg> pgqzSzdSjsjgList, 
    		Map<String, List<StlDownDataDealAccountInfoDto>> map,
    		StlDownDataDealOperatorDto stlDownDataDealOperatorDto) {
    	List<FinProductQuto> list = new ArrayList<FinProductQuto>();
    	for(SzdSjsjg szdSjsjg : pgqzSzdSjsjgList) {
            // KEY = 证券账户号码 + 交易单元
            String key = szdSjsjg.getJgzqzh() + szdSjsjg.getJgtgdy();
            // 组合代码不在输入的组合代码List里面不处理
            List<StlDownDataDealAccountInfoDto> accountInfoDtolist = map.get(key);

            if (CollectionUtils.isEmpty(accountInfoDtolist)) {
                continue;
            }
            // 组合代码不在输入的组合代码List里面不处理
            else if (stlDownDataDealOperatorDto.getPortfolioIdList().size() > 0 && !stlDownDataDealOperatorDto.getPortfolioIdList().contains(accountInfoDtolist.get(0).getPortfolioId())) {
                continue;
            }
            String approvalNo = szdSjsjg.getJgzqdm();
            List<String> finprodIdList = stlDownDataDealMapper.getFinproductIdByApprovalNo(DictCodeListExt3.金融产品其他代码类型_配债代码.getValue(), approvalNo);
			if (finprodIdList != null && finprodIdList.size() > 0) {
	            FinProductQuto finProductQuto = new FinProductQuto();
	            finProductQuto.setSeqNo(IDGenerator.generate());
	            finProductQuto.setPortfolioId(accountInfoDtolist.get(0).getPortfolioId());
				finProductQuto.setFinprodId(finprodIdList.get(0));
	            finProductQuto.setSecAcctId(accountInfoDtolist.get(0).getSecAcctId());
				finProductQuto.setOthType(DictCodeListExt3.金融产品其他代码类型_配债代码.getValue());
				finProductQuto.setCdate(DateUtil.parse(szdSjsjg.getJgcjrq()));
				finProductQuto.setQuto(abs(szdSjsjg.getJgjssl()));
	            list.add(finProductQuto);
			}
    	}
    	return list;
    }
    
    private List<FinProductQuto> generatorSharesFinProductQutoList(List<SzdSjsjg> pgqzSzdSjsjgList, 
    		Map<String, List<StlDownDataDealAccountInfoDto>> map,
    		StlDownDataDealOperatorDto stlDownDataDealOperatorDto) {
    	List<FinProductQuto> list = new ArrayList<FinProductQuto>();
    	for(SzdSjsjg szdSjsjg : pgqzSzdSjsjgList) {
            // KEY = 证券账户号码 + 交易单元
            String key = szdSjsjg.getJgzqzh() + szdSjsjg.getJgtgdy();
            // 组合代码不在输入的组合代码List里面不处理
            List<StlDownDataDealAccountInfoDto> accountInfoDtolist = map.get(key);

            if (CollectionUtils.isEmpty(accountInfoDtolist)) {
                continue;
            }
            // 组合代码不在输入的组合代码List里面不处理
            else if (stlDownDataDealOperatorDto.getPortfolioIdList().size() > 0 && !stlDownDataDealOperatorDto.getPortfolioIdList().contains(accountInfoDtolist.get(0).getPortfolioId())) {
                continue;
            }
            String approvalNo = szdSjsjg.getJgzqdm();
            List<String> finprodIdList = stlDownDataDealMapper.getFinproductIdByApprovalNo(DictCodeListExt3.金融产品其他代码类型_配股代码.getValue(), approvalNo);
			if (finprodIdList != null && finprodIdList.size() > 0) {
	            FinProductQuto finProductQuto = new FinProductQuto();
	            finProductQuto.setSeqNo(IDGenerator.generate());
	            finProductQuto.setPortfolioId(accountInfoDtolist.get(0).getPortfolioId());
	            finProductQuto.setSecAcctId(accountInfoDtolist.get(0).getSecAcctId());
				finProductQuto.setFinprodId(finprodIdList.get(0));
				finProductQuto.setOthType(DictCodeListExt3.金融产品其他代码类型_配股代码.getValue());
				finProductQuto.setCdate(DateUtil.parse(szdSjsjg.getJgcjrq()));
				finProductQuto.setQuto(abs(szdSjsjg.getJgjssl()));
	            list.add(finProductQuto);
			}
    	}
    	return list;
    }
}
