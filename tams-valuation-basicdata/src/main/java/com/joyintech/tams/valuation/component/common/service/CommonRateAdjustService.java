package com.joyintech.tams.valuation.component.common.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.component.common.calculator.RateResetCal;
import com.joyintech.tams.valuation.component.common.dto.AssetCommonInfoDto;
import com.joyintech.tams.valuation.component.common.dto.AssetCommonInvRateAdjustDto;
import com.joyintech.tams.valuation.component.common.dto.FinCashPlanDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * @ClassName CommonRateAdjustService
 * @Description: 利率报价共通
 * @Author yanya
 * @Date 2021/3/4 0004 
 * @Version V1.0
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class CommonRateAdjustService extends CommonExportService<AssetCommonInvRateAdjustDto> {

    /**
     * 处理资产报价信息
     * 用于计算支付计划
     * <功能详细描述>
     * @param scai
     * @param is
     * @param scheList
     * @param holidayList
     * @return
     * @see
     * @since 1.0
     */
    public List<AssetCommonInvRateAdjustDto> escapRateAdjust(AssetCommonInfoDto scai, FinCashPayRule is, List<FinCashPlanDto> scheList, List<String> holidayList){
        List<AssetCommonInvRateAdjustDto> rateList = new ArrayList<AssetCommonInvRateAdjustDto>();
        //固定利率
        // 一体化对应etliu start
//        if(StringUtil.equals(scai.getRateType(), DictCodeList.利率类型_固定利率.getValue())){
        if(StringUtil.equals(scai.getIntType(), DictCodeList.利率类型_固定利率.getValue())){
            // 一体化对应etliu end
            List<AssetCommonInvRateAdjustDto> findRateList = getRateAdjust(scai);
            rateList.addAll(findRateList);
            AssetCommonInvRateAdjustDto invRateQuo = new AssetCommonInvRateAdjustDto();
            invRateQuo.setEffDate(scai.getVdate());
            invRateQuo.setYield(scai.getRate());
            invRateQuo.setBasis(scai.getBasis());
            rateList.add(invRateQuo);
        }else{
            //浮动利率
            //取基准行情信息
            //利差(%),上限(%),下限(%)
            if(null!=scai.getFinRateInfo()){
                BigDecimal spreadRate = ArithmeticUtil.divide(scai.getFinRateInfo().getSpreadRate(), new BigDecimal(100), 14);
                // 一体化对应etliu
//                BigDecimal rateMax = ArithmeticUtil.divide(scai.getFinRateInfo().getRateMax(), new BigDecimal(100), 14);
//                BigDecimal rateMin = ArithmeticUtil.divide(scai.getFinRateInfo().getRateMin(), new BigDecimal(100), 14);
                //update by zhangnian 20191030 start
                //上限、下限为空时，不处理，否则后续计算报价信息会取0
                if(null!=scai.getFinRateInfo().getHighestRate()){
                    BigDecimal rateMax = ArithmeticUtil.divide(scai.getFinRateInfo().getHighestRate(), new BigDecimal(100), 14);
                    scai.getFinRateInfo().setHighestRate(rateMax);
                }
                if(null!=scai.getFinRateInfo().getLowestRate()){
                    BigDecimal rateMin = ArithmeticUtil.divide(scai.getFinRateInfo().getLowestRate(), new BigDecimal(100), 14);
                    scai.getFinRateInfo().setLowestRate(rateMin);
                }
                //update by zhangnian 20191030 end
                scai.getFinRateInfo().setSpreadRate(spreadRate);
                // 一体化对应etliu
//                scai.getFinRateInfo().setRateMax(rateMax);
//                scai.getFinRateInfo().setRateMin(rateMin);

            }
            // 一体化对应etliu
//            rateList = getMstRateInfo(scai.getFinRateInfo().getIndexId());
            rateList = getMstRateInfo(scai.getFinRateInfo().getBenchmarkId());
            rateList = RateResetCal.cal(scheList, is, scai.getFinRateInfo(), rateList,
                    scai, 0, holidayList, AssetCommonInvRateAdjustDto.class);
        }
        // 按生效日从小到大排序
        Collections.sort(rateList, new Comparator<AssetCommonInvRateAdjustDto>() {
            @Override
            public int compare(AssetCommonInvRateAdjustDto o1, AssetCommonInvRateAdjustDto o2) {
                return o1.getEffDate().compareTo(o2.getEffDate());
            }
        });
        return rateList;
    }

    /**
     * 获取利率调整信息
     * @return
     * @see
     * @since 1.0
     */
    public List<AssetCommonInvRateAdjustDto> getRateAdjust(AssetCommonInfoDto scai){
        return restTemplate.getForList(ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME
                        + "/FinAssetRateAdj/getFinAssetRateAdjByCashId?finprodId="
                        + scai.getFinprodId()+"&finprodType="+ DictCodeListExt1.现金流业务类型_金融产品.getValue()
                        +"&cashType="+ DictCodeListExt1.现金流二级类型_利息.getValue()
                        +"&isInitial="+DictCodeList.是否_否.getValue()
                        +"&cashId="+scai.getCashId(),
                AssetCommonInvRateAdjustDto.class);
    }


    /**
     * 获取利率行情信息
     * <功能详细描述>
     * @param indexId    基准编号
     * @return
     * @see
     * @since 1.0
     */
    public List<AssetCommonInvRateAdjustDto> getMstRateInfo(String indexId){
        return restTemplate.getForList(ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME
                        + "/FinAssetRateAdj/getMstRateInfo?indexId="
                        + indexId,
                AssetCommonInvRateAdjustDto.class);
    }


    /**
     * 获取资产息票品种
     * @param map
     * @return
     */
    public AssetCommonInfoDto getAssetInfoToRateAdj(Map<String,Object> map){
        return restTemplate.postForObject(ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME + "/FinAssetRateAdj/getAssetInfoToRateAdj",map,
                AssetCommonInfoDto.class);
    }

    /**
     * 获取资产到期日
     * yjm 20191014
     * @param finprodId
     * @return
     */
    public AssetCommonInfoDto getMdateByFinproId(String finprodId){
        return restTemplate.getForObject(ServiceApplication.VALUATION_BASICDATA_SERVICE_NAME +
                        "/FinAssetRateAdj/getMdateByFinproId?finprodId=" + finprodId, AssetCommonInfoDto.class);
    }

}
