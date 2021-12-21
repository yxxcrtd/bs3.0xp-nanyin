/*
 * 文件名：SysCoreService.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：刘二通 修改时间：2018年11月4日 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.svrcom.core.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.svrcom.core.dao.SvRSysCoreMapper;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 刘二通
 * @version 1.0 2018年11月4日
 * @see SvRSysCoreService
 * @since 1.0
 */
@SuppressWarnings("rawtypes")
@Service
public class SvRSysCoreService extends BaseService {

    /**
     * svRSysCoreMapper
     */
    @Autowired
    private SvRSysCoreMapper svRSysCoreMapper;

//    @Autowired
//    private BaseFinProductBaseInfoService baseFinProductBaseInfoService;

    @Autowired
    private IPlatformDate iPlatformDate;
    
//    @Autowired
//    private RiskRedisInitService<ImplCond> redisRriter;
    
    /**
     * 权益类资产中债报送(基础组件)
     */
//    @Autowired(required = false)
//    private BaseRepAssetEquityInfoService baseRepAssetEquityInfoService;
    
    /**
     * 利用币种与交易日查询汇率行情表中最新的收盘价
     *
     * @param ccy 币种
     * @param tradeDate 交易日
     * @return 汇率行情表中最新的收盘价
     * @see
     * @since 1.0
     */
    public BigDecimal selectClosePrice(String ccy, String tradeDate) {
        if (StringUtil.isBlank(ccy) || StringUtil.isBlank(tradeDate)) {
            return null;
        }

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("tradeDate", tradeDate);
        paramMap.put("ccy", ccy + "/CNY");

        BigDecimal closePrice = svRSysCoreMapper.selectClosePrice(paramMap);
        if (closePrice == null) {
            return new BigDecimal(1);
        }
        return closePrice;
    }

    /**
     * 资产是否存在交易
     *
     * @param finprodId 金融产品代码
     * @return 交易笔数
     * @see
     * @since 1.0
     */
    public int isTransInAsset(String finprodId) {
        return svRSysCoreMapper.isTransInAsset(finprodId);
    }

    /**
     * 获取最大交易日期 <功能详细描述>
     * 
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public Date getMaxTradeDate(String finprodId) {
        return svRSysCoreMapper.getMaxTradeDate(finprodId);
    }

    /**
     * 获取wind债券信息 <功能详细描述>
     * 
     * @return
     * @see
     * @since 1.0
     */
//    public List<InterfPayhsrcsecinfoDto> selectWindBondAsset(Map<String,Object> map) {
//        return svRSysCoreMapper.selectWindBondAsset(map);
//    }

    /**
     * 获取wind基金信息 <功能详细描述>
     * 
     * @return
     * @see
     * @since 1.0
     */
//    public List<InfChinamutualfunddescriptionDto> selectWindFundAsset(Map<String,Object> map) {
//        return svRSysCoreMapper.selectWindFundAsset(map);
//    }

    /**
     * 获取wind债券信息 <功能详细描述>
     * 
     * @param secid
     * @return
     * @see
     * @since 1.0
     */
//    public InterfPayhsrcsecinfoDto selectWindBondAssetBySecId(String secid) {
//        return svRSysCoreMapper.selectWindBondAssetBySecId(secid);
//    }

    /**
     * 获取wind基金信息 <功能详细描述>
     * 
     * @param fInfoWindcode
     * @return
     * @see
     * @since 1.0
     */
    public InterfCmfunddescrip selectWindFundAssetByfInfoWindcode(String fInfoWindcode) {
        return svRSysCoreMapper.selectWindFundAssetByfInfoWindcode(fInfoWindcode);
    }

    /**
     * 获取wind债券行权计划 <一句话功能简述> <功能详细描述>
     * 
     * @param secid 债券id
     * @return
     * @see
     * @since 1.0
     */
    public List<InterfPayhsrcsecoption> selectWindBondExercise(String secid) {
        return svRSysCoreMapper.selectWindBondExercise(secid);
    }

    /**
     * 获取WIND_浮息债报价表(债券) <功能详细描述>
     * 
     * @param secid
     * @return
     * @see
     * @since 1.0
     */
    public List<InterfFloatcouprate> selectInterfFloatingcouponsrate(String secid) {
        return svRSysCoreMapper.selectInterfFloatingcouponsrate(secid);
    }

    /**
     * 获取中国债券基本资料(债券) <功能详细描述>
     * 
     * @param secid
     * @return
     * @see
     * @since 1.0
     */
    public InterfCbonddescription selectInterfCbonddescription(String secid) {
        return svRSysCoreMapper.selectInterfCbonddescription(secid);
    }

    /**
     * 获取wind债券付息计划 <功能详细描述>
     * 
     * @param secid
     * @return
     * @see
     * @since 1.0
     */
    public List<InterfPayhsrcsecschedule> selectWindBondSchedule(String secid) {
        return svRSysCoreMapper.selectWindBondSchedule(secid);
    }

    /**
     * 获取中国货币市场基金份额结转方式 <功能详细描述>
     * 
     * @param sInfoWindcode
     * @return
     * @see
     * @since 1.0
     */
    public InfCmoneymarketfscarryoverm selectWindFundMoneymarketfscarryoverm(String sInfoWindcode) {
        return svRSysCoreMapper.selectWindFundMoneymarketfscarryoverm(sInfoWindcode);
    }

    /**
     * 自动加券(债券) <功能详细描述>
     * 
     * @param dto
     * @see
     * @since 1.0
     */
//    public void autoBondCoupon(InterfPayhsrcsecinfoDto dto) {
//        if (null != dto.getBaseFinProductBaseInfoList() && !dto.getBaseFinProductBaseInfoList().isEmpty()) {
//            baseFinProductBaseInfoService.insert(dto.getBaseFinProductBaseInfoList());
//            //自动加券添加刷新风险redis-资产基本信息
//            if (CommonUtil.isType(CacheUtil.getSysParam("pageRedisFlag"), BussinessDictCodeListRds.是否_是)) {
//                ImplCond cond = new ImplCond();
//                cond.setDel(false);
//                if(iPlatformDate!=null&&redisRriter!=null) {
//                    for (BaseFinProductBaseInfoDto infoDto : dto.getBaseFinProductBaseInfoList()) {
//                    	if(infoDto.getFinProduct() != null&&StringUtil.isNotEmpty(infoDto.getFinProduct().getFinprodId())) {
//                        	cond.setAssetIdArray(infoDto.getFinProduct().getFinprodId());
//                        	cond.setPlateDate(iPlatformDate.getSysDate());
//                        	redisRriter.dealAssetData(cond);
//                    	}
//                    }
//                }
//            }
//        }
//    }

    /**
     * 自动加券(基金) <功能详细描述>
     * 
     * @param dto
     * @see
     * @since 1.0
     */
//    public void autoFundCoupon(InfChinamutualfunddescriptionDto dto) {
//        if (null != dto.getBaseFinProductBaseInfoList() && !dto.getBaseFinProductBaseInfoList().isEmpty()) {
//            baseFinProductBaseInfoService.insert(dto.getBaseFinProductBaseInfoList());
//            //自动加券添加刷新风险redis-资产基本信息
//            if (CommonUtil.isType(CacheUtil.getSysParam("pageRedisFlag"), BussinessDictCodeListRds.是否_是)) {
//                ImplCond cond = new ImplCond();
//                cond.setDel(false);
//                if(iPlatformDate!=null&&redisRriter!=null) {
//                    for (BaseFinProductBaseInfoDto infoDto : dto.getBaseFinProductBaseInfoList()) {
//                    	if(infoDto.getFinProduct() != null&&StringUtil.isNotEmpty(infoDto.getFinProduct().getFinprodId())) {
//                        	cond.setAssetIdArray(infoDto.getFinProduct().getFinprodId());
//                        	cond.setPlateDate(iPlatformDate.getSysDate());
//                        	redisRriter.dealAssetData(cond);
//                    	}
//                    }
//                }
//            }
//        }
//    }

    /**
     * 获取WIND_中国债券特殊条款(债券) <功能详细描述>
     * 
     * @param secid
     * @return
     * @see
     * @since 1.0
     */
    public InterfCbondspecialconditions selectInterfCbondspecialconditions(String secid) {
        return svRSysCoreMapper.selectInterfCbondspecialconditions(secid);
    }

    /**
     * 期货标的是否存在交易
     *
     * @param finprodId 金融产品代码
     * @return 交易笔数
     * @see
     * @since 1.0
     */
    public int isExistTransInAsset(String finprodId) {
        return svRSysCoreMapper.isExistTransInAsset(finprodId);
    }

    /**
     * 获取Wind 债券分类板块(债券) <功能详细描述>
     * 
     * @param secid
     * @return
     * @see
     * @since 1.0
     */
    public InterfCbondindustrywind selectInterfCbondindustrywind(String secid) {
        return svRSysCoreMapper.selectInterfCbondindustrywind(secid);
    }

    /**
     * 自动加券(基金 获取wind代码) 根据前台代码获取wind代码
     * 
     * @param finprodMarketId
     * @see
     * @since 1.0
     */
    public List<FinProduct> getFwindCode(String finprodMarketId) {
        return svRSysCoreMapper.getFwindCode(finprodMarketId);
    }

    /**
     * 自动加券 投资委托根据资产代码查询入库表之后的金融产品代码信息
     * 
     * @param finprodMarketId
     * @return
     * @see
     * @since 1.0
     */
//    public AutoCouponDto selectAutoFinProd(String finprodMarketId) {
//        return svRSysCoreMapper.selectAutoFinProd(finprodMarketId);
//    }

    /**
     * 查询是否存在债券估值行情count
     *
     * @param finprodMarketId
     * @return
     */
    public List<Integer> selectMstBondValuationInfoCount(String finprodMarketId) {
        return svRSysCoreMapper.selectMstBondValuationInfoCount(finprodMarketId);
    }

    /**
     * 获取资讯_债券估值行情信息
     * 
     * @param finprodMarketId
     * @param sysDate
     * @param newDate
     * @return
     * @see
     * @since 1.0
     */
    public List<InterfPayhsrccbvlt> selectInterfPayhsrccbvlt(String finprodMarketId, String sysDate, String newDate) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("finprodMarketId", finprodMarketId);
        map.put("sysDate", sysDate);
        map.put("newDate", newDate);
        return svRSysCoreMapper.selectInterfPayhsrccbvlt(map);
    }

    /**
     * 查询资讯_市场行情数据INTERF_BOND_MARKET_INFO
     * 
     * @param finprodMarketId
     * @param sysDate
     * @param newDate
     * @return
     */
    public List<InterfBondMarketInfo> selectInterfBondMarketInfo(String finprodMarketId, String sysDate, String newDate) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("finprodMarketId", finprodMarketId);
        map.put("sysDate", sysDate);
        map.put("newDate", newDate);
        return svRSysCoreMapper.selectInterfBondMarketInfo(map);
    }

    /**
     * 判断当前日期是否存在回购标准券折算率 (FIN_STDSEC_CONVERSE_RADIO)
     * 
     * @param finprodMarketId
     * @return
     */
    public List<Integer> selectFinStdsecConverseRadioCount(String finprodMarketId, String sysDate) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("finprodMarketId", finprodMarketId);
        map.put("sysDate", sysDate);
        return svRSysCoreMapper.selectFinStdsecConverseRadioCount(map);
    }

    /**
     * 查询资讯_回购标准折算率(INTERF_STDSEC_CON_RADIO)
     * 
     * @param finprodMarketId
     * @return
     */
    public List<InterfStdsecConRadio> selectInterfStdsecConRadio(String finprodMarketId, String sysDate) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("finprodMarketId", finprodMarketId);
        map.put("sysDate", sysDate);
        return svRSysCoreMapper.selectInterfStdsecConRadio(map);
    }

    /**
     * 查询是否存在场外行情信息-基金加券
     * 
     * @param finprodMarketId
     * @return
     */
    public List<Integer> selectFinProductValuationCount(String finprodMarketId) {
        return svRSysCoreMapper.selectFinProductValuationCount(finprodMarketId);
    }

    /**
     * 查询资讯_基金净值list-基金加券
     * 
     * @param finprodMarketId
     * @param sysDate
     * @param newDate
     * @return
     */
    public List<InterfCmfundnav> selectInterfCmfundnav(String finprodMarketId, String sysDate, String newDate) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("finprodMarketId", finprodMarketId);
        map.put("sysDate", sysDate);
        map.put("newDate", newDate);
        return svRSysCoreMapper.selectInterfCmfundnav(map);
    }

    /**
     * 查询是否存在场外行情信息-基金加券MST_FUND_MARKET_INFO
     * 
     * @param finprodMarketId
     * @return
     */
    public List<Integer> selectMstFundMarketInfoCount(String finprodMarketId) {
        return svRSysCoreMapper.selectMstFundMarketInfoCount(finprodMarketId);
    }

    /**
     * 查询资讯_基金市场行情list-基金加券(INTERF_F_MARKET_INFO)
     * 
     * @param finprodMarketId
     * @param sysDate
     * @param newDate
     * @return
     */
    public List<InterfFMarketInfo> selectInterfFMarketInfo(String finprodMarketId, String sysDate, String newDate) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("finprodMarketId", finprodMarketId);
        map.put("sysDate", sysDate);
        map.put("newDate", newDate);
        return svRSysCoreMapper.selectInterfFMarketInfo(map);
    }

    /**
     * 债券估值行情保存(新增) <功能详细描述>
     * 
     * @param dto
     * @see
     * @since 1.0
     */
//    public void mstBondValuationInfoSave(InterfPayhsrcsecinfoDto dto) {
//        if (null != dto.getMstBondValuationInfoList() && !dto.getMstBondValuationInfoList().isEmpty()) {
//            baseFinProductBaseInfoService.insertMstBondValuationInfo(dto.getMstBondValuationInfoList());
//        }
//    }
//
//    /**
//     * 债券市场行情保存(新增) <功能详细描述>
//     *
//     * @param dto
//     * @see
//     * @since 1.0
//     */
//    public void mstBondMarketInfoSave(InterfPayhsrcsecinfoDto dto) {
//        if (null != dto.getMstBondMarketInfoList() && !dto.getMstBondMarketInfoList().isEmpty()) {
//            baseFinProductBaseInfoService.insertmstBondMarketInfo(dto.getMstBondMarketInfoList());
//        }
//    }
//
//    /**
//     * 回购标准折算率保存(新增) <功能详细描述>
//     *
//     * @param dto
//     * @see
//     * @since 1.0
//     */
//    public void finStdsecConverseRadioSave(InterfPayhsrcsecinfoDto dto) {
//        if (null != dto.getFinStdsecConverseRadioList() && !dto.getFinStdsecConverseRadioList().isEmpty()) {
//            baseFinProductBaseInfoService.insertFinStdsecConverseRadio(dto.getFinStdsecConverseRadioList());
//        }
//    }
//
//    /**
//     * (基金)场外金融产品保存(新增) <功能详细描述>
//     *
//     * @param dto
//     * @see
//     * @since 1.0
//     */
//    public void finProductValuationSave(InfChinamutualfunddescriptionDto dto) {
//        if (null != dto.getFinProductValuationList() && !dto.getFinProductValuationList().isEmpty()) {
//            baseFinProductBaseInfoService.insertFinProductValuation(dto.getFinProductValuationList());
//        }
//    }

    /**
     * 基金-基金市场行情保存(新增) <功能详细描述>
     * 
     * @param dto
     * @see
     * @since 1.0
     */
//    public void mstFundMarketInfoSave(InfChinamutualfunddescriptionDto dto) {
//        if (null != dto.getMstFundMarketInfoList() && !dto.getMstFundMarketInfoList().isEmpty()) {
//            baseFinProductBaseInfoService.insertMstFundMarketInfo(dto.getMstFundMarketInfoList());
//        }
//    }

    /**
     * 查询客户信息附属表list-债券中债
     * 
     * @param customerid
     * @return
     */
    public List<MstCustomerInfoAdd> selectMstCustomerInfoAdd(String customerid) {
        return svRSysCoreMapper.selectMstCustomerInfoAdd(customerid);
    }

    /**
     * 查询资讯_债券评级信息表
     * 
     * @param secid
     * @return
     */
    public List<InterfPayhsrcsecrating> selectInterfPayhsrcsecrating(String secid) {
        return svRSysCoreMapper.selectInterfPayhsrcsecrating(secid);
    }

    /**
     * 查询资讯_债券评级中主体评级结果
     * 
     * @param secid
     * @param date
     * @return
     */
    public List<InterfPayhsrcsecrating> selectRating(String secid, String date) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("secid", secid);
        map.put("date", date);
        return svRSysCoreMapper.selectRating(map);
    }

    // 资产五级分类联动检索 add by renping 2019-07-11 start
    /**
     * 资产五级分类联动检索
     *
     * @param param 参数
     * @return 结果
     * @see
     * @since 1.0
     */
    public FinProductTypeConfig getAssetFiveType(FinProductTypeConfig param) {
        // 配置类型未传参时，默认投管分类
        if (StringUtil.isEmpty(param.getConfigType())) {
            param.setConfigType(DictCodeListExt2.配置类型_投管分类.getValue());
        }
        List<FinProductTypeConfig> list = svRSysCoreMapper.getAssetFiveType(param);
        if (StringUtil.isEmptyList(list) || list.size() > 1 || list.get(0) == null) {
            return new FinProductTypeConfig();
        }
        return list.get(0);
    }

    // 资产五级分类联动检索 add by renping 2019-07-11 end
    /**
     * 判断是走正常流程新建的交易，还是未走流程直接入库的交易
     * 
     * @param parameter
     * @return
     */
//    public int isProcessData(WflValidInputParameter parameter) {
//        DynamicDataSourceHolder.putDataSource(DataSourceType.OTHER);
//        int i = svRSysCoreMapper.isProcessData(parameter);
//        DynamicDataSourceHolder.clear();
//        return i;
//    }


    /**
     * 资产是否存在交易
     *
     * @param finprodId 金融产品代码
     * @return 交易笔数
     * @see
     * @since 1.0
     */
    public int isBondExerciseAsset(String finprodId) {
        return svRSysCoreMapper.isBondExerciseAsset(finprodId);
    }
    
//	public InterfStockInfoDto selectWindStockAssetByStockCode(String stockCode) {
//		return svRSysCoreMapper.selectWindStockAssetByStockCode(stockCode);
//	}
//
//	public List<InterfStockInfoDto> selectWindStockAsset(Map<String, Object> map) {
//		return svRSysCoreMapper.selectWindStockAsset(map);
//	}

	/**
     * 自动加券(股票) <功能详细描述>
     * 
     * @param dto
     * @see
     * @since 1.0
     */
//	public void autoStockCouponSave(InterfStockInfoDto dto) {
//		if (null != dto.getBaseFinProductBaseInfoList() && !dto.getBaseFinProductBaseInfoList().isEmpty()) {
//            baseFinProductBaseInfoService.insert(dto.getBaseFinProductBaseInfoList());
//            for(BaseFinProductBaseInfoDto baseFinProductBaseInfoDto:dto.getBaseFinProductBaseInfoList()){
//            	//权益类资产中债报送
//            	if(null!=baseRepAssetEquityInfoService){
//            		if(null!=baseFinProductBaseInfoDto.getBaseRepAssetEquityInfoDto().getRepAssetEquityList()){
//            			baseRepAssetEquityInfoService.insert(baseFinProductBaseInfoDto.getBaseRepAssetEquityInfoDto().getRepAssetEquityList());
//            		}
//            	}
//            	//JXS_ISSUE_INFO
//            	if(null!=baseFinProductBaseInfoDto.getJxsIssueInfo()){
//            		svRSysCoreMapper.insertJxInfo(baseFinProductBaseInfoDto.getJxsIssueInfo());
//            	}
//            }
//        }
//	}

    /**
     * 资产是否存在交易(存在撤销交易也算交易)
     *
     * @param finprodId 金融产品代码
     * @return 交易笔数
     * @see
     * @since 1.0
     */
    public int isTransInAssetTwo(String finprodId) {
        return svRSysCoreMapper.isTransInAssetTwo(finprodId);
    }
}
