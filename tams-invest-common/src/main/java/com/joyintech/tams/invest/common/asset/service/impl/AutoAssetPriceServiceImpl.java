package com.joyintech.tams.invest.common.asset.service.impl;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.FinProductValuationMapper;
import com.joyintech.tams.common.base.dao.MstBondMarketInfoMapper;
import com.joyintech.tams.common.base.dao.MstBondValuationInfoMapper;
import com.joyintech.tams.common.base.dao.MstFundMarketInfoMapper;
import com.joyintech.tams.common.base.dao.MstSharePriceMapper;
import com.joyintech.tams.common.base.entity.FinProductValuation;
import com.joyintech.tams.common.base.entity.MstBondMarketInfo;
import com.joyintech.tams.common.base.entity.MstBondValuationInfo;
import com.joyintech.tams.common.base.entity.MstFundMarketInfo;
import com.joyintech.tams.common.base.entity.MstSharePrice;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.asset.service.AutoAssetPriceService;
import com.joyintech.tams.invest.common.asset.service.PriceinfoService;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.redisinit.dto.ImplParaRds;
import com.joyintech.tams.invest.common.redisinit.service.RiskRefreshRedisService;
import com.joyintech.tams.invest.common.risktrialrds.util.RiskUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class AutoAssetPriceServiceImpl extends BaseService implements AutoAssetPriceService {
    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(AutoAssetPriceServiceImpl.class);
    /**
     * 处理天数
     */
    private int processDay = 10;

    public int getProcessDay() {
        return processDay;
    }

    public void setProcessDay(int processDay) {
        this.processDay = processDay;
    }

    @Autowired
    private PriceinfoService priceinfoService;

    @Autowired
    private IPlatformDate iPlatformDate;

    @Autowired
    private MstBondMarketInfoMapper mstBondMarketInfoMapper;

    @Autowired
    private MstBondValuationInfoMapper mstBondValuationInfoMapper;

    @Autowired
    private MstFundMarketInfoMapper mstFundMarketInfoMapper;

    @Autowired
    private MstSharePriceMapper mstSharePriceMapper;

    @Autowired
    private FinProductValuationMapper finProductValuationMapper;

    @Autowired
    private RiskRefreshRedisService riskRefreshRedisService;

    @Override
    public boolean checkIsNeedAutoPrice(Map<String, Object> map) {
        return priceinfoService.checkIsNeedAutoPrice(map);
    }

    @Override
    public void autoProcess(Map<String, Object> map) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("finprodid",finprodid);
//        map.put("assetType",assetType);
        String assetType = map.get("assetType")!=null?(String) map.get("assetType"):"";
        final String 平台日 = iPlatformDate.getSysDateStr();
        final String 起始日 = DateUtil.addDay(平台日, processDay*-1);
        map.put("endDate",平台日);
        map.put("startDate",起始日);
        String isNeedAutoPrice = CacheUtil.getSysParam("IS_NEED_AUTO_PRICE");
        if(StringUtil.equals(DictCodeList.是否_是.getValue(),isNeedAutoPrice)&&checkIsNeedAutoPrice(map)){
            String 共通业务类型 = null;
            // 债券
            if(StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_债券.getValue(),assetType)){
                process债券市场行情数据(map);
                process债券估值行情数据(map);
                共通业务类型 = DictCodeList.共通业务类型_债券估值行情信息.getValue()+","+ DictCodeListExt3.共通业务类型_债券每日行情.getValue();
            }
            // 基金
            if(StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_基金.getValue(),assetType)){
                map.put("FINPROD_TYPE_F06", DictCodeListExt1.金融产品类型_投管用_基金.getValue());
                process基金行情数据(map);
                process基金估值数据(map);
                共通业务类型 = DictCodeList.共通业务类型_基金类估值行情信息.getValue();
            }
            // 股票
            if(StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_股票.getValue(),assetType)){
                process股票行情数据(map);
                共通业务类型 = DictCodeListExt2.共通业务类型_股票行情信息.getValue();
            }
            if (CommonUtil.isType(CacheUtil.getSysParam(RiskUtil.PAGE_REDIS_FLAG), BussinessDictCodeListRds.是否_是)) {
                try {
                    logger.info("===========资产新建完成时更新资产redis开始=============");
                    ImplParaRds implParaRds=new ImplParaRds();
                    implParaRds.setCommonBusinessType(共通业务类型);
                    riskRefreshRedisService.refreshParamRedis(implParaRds);
                    logger.info("===========资产新建完成时更新资产redis结束=============");
                }catch (Exception e){
                    //异常无需抛出，在内部处理掉
                    logger.error("资产新建完成时更新资产redis异常",e);
                }

            }
        }
    }

    /**
     * 从接口表获取债券市场行情
     * @param map
     * @return
     */
    private int process债券市场行情数据(Map<String, Object> map){
        List<MstBondMarketInfo> list = priceinfoService.selectMstBondMarketInfoList(map);
        int count = 0;
        if (!StringUtil.isEmptyList(list)) {
            for (MstBondMarketInfo mstBondMarketInfo : list) {
                int i = mstBondMarketInfoMapper.insert(mstBondMarketInfo);
                count = count+i;
            }
        }
        return count;
    }

    /**
     * 从接口表获取债券估值行情
     * @param map
     * @return
     */
    private int process债券估值行情数据(Map<String, Object> map){
        List<MstBondValuationInfo> list = priceinfoService.selectMstBondValuationInfoList(map);
        int count = 0;
        if (!StringUtil.isEmptyList(list)) {
            for (MstBondValuationInfo mstBondValuationInfo : list) {
                int i = mstBondValuationInfoMapper.insert(mstBondValuationInfo);
                count = count+i;
            }
        }
        return count;
    }

    /**
     * 从接口表获取基金估值行情
     * @param map
     * @return
     */
    private int process基金行情数据(Map<String, Object> map){
        List<MstFundMarketInfo> list = priceinfoService.selectMstFundMarketInfoList(map);
        int count = 0;
        if (!StringUtil.isEmptyList(list)) {
            for (MstFundMarketInfo mstFundMarketInfo : list) {
                int i = mstFundMarketInfoMapper.insert(mstFundMarketInfo);
                count = count+i;
            }
        }
        return count;
    }

    /**
     * 从接口表获取基金估值数据
     * @param map
     * @return
     */
    private int process基金估值数据(Map<String, Object> map){
        List<FinProductValuation> list = priceinfoService.selectFinProductValuationList(map);
        int count = 0;
        if (!StringUtil.isEmptyList(list)) {
            for (FinProductValuation finProductValuation : list) {
                finProductValuation.setFinprodType(DictCodeListExt1.金融产品类型_投管用_基金.getValue());
                finProductValuation.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_基金.getValue());
                int i = finProductValuationMapper.insert(finProductValuation);
                count = count+i;
            }
        }
        return count;
    }

    /**
     * 从接口表获取股票估值行情
     * @param map
     * @return
     */
    private int process股票行情数据(Map<String, Object> map){
        List<MstSharePrice> list = priceinfoService.selectMstSharePriceList(map);
        int count = 0;
        if (!StringUtil.isEmptyList(list)) {
            for (MstSharePrice mstSharePrice : list) {
                int i = mstSharePriceMapper.insert(mstSharePrice);
                count = count+i;
            }
        }
        return count;
    }

}
