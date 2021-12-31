package com.joyintech.tams.svreport.chb.monthreportdetail.service;

import com.joyintech.background.component.common.pack.common.util.BeanUtil;
import com.joyintech.background.component.common.pack.common.util.IDGenerator;
import com.joyintech.background.component.common.pack.common.util.StringUtil;
import com.joyintech.tams.common.base.dao.ChbMonthPrdPositiondetailMapper;
import com.joyintech.tams.common.base.entity.ChbMonthPrdPositiondetail;
import com.joyintech.tams.common.base.entity.ChbPrdBaseinfo;
import com.joyintech.tams.common.svreport.chb.constant.ProdConstants;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.ChbCcyConvertUtil;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthFeePosition;
import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthPrdPosition;
import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthReportDetail;
import com.joyintech.tams.svreport.chb.monthreportdetail.dao.ChbMonthReportDetailAssetMapper;
import com.joyintech.tams.svreport.chb.monthreportdetail.dao.ChbMonthReportDetailProdMapper;
import com.joyintech.tams.svreport.chb.monthreportdetail.dto.ChbMonthReportDetailAsset;
import com.joyintech.tools.collectors.JoyinCollectors;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Transactional(rollbackFor = Exception.class)
@Service
public class ChbMonthReportDetailProdUtil {

    @Autowired
    private ChbMonthReportDetailProdMapper chbMonthReportDetailProdMapper;

    @Autowired
    private ChbMonthPrdPositiondetailMapper chbMonthPrdPositiondetailMapper;
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 数据批量一次性insert条数
     */
    @Value("${application.reportInsertCount}")
    public int reportInsertCount;

    /**
     * 数据赋值
     * @param prdPosition
     * @param info
     * @param flagCode
     * @return
     */
    public static ChbMonthPrdPositiondetail buildProdDetail(ChbMonthPrdPosition prdPosition, ChbPrdBaseinfo info, String termid){
        ChbMonthPrdPositiondetail chbMonthPrdPositiondetail = new ChbMonthPrdPositiondetail();
        //相同内容字段直接赋值
        BeanUtil.copyPropertiesIgnoreNull(chbMonthPrdPositiondetail,info);
        //先基本信息,再头寸信息,避免错误覆盖
        BeanUtil.copyPropertiesIgnoreNull(chbMonthPrdPositiondetail,prdPosition);
        //赋值所属类型
        chbMonthPrdPositiondetail.setTerm(termid);

        getTermDays(chbMonthPrdPositiondetail,info,prdPosition);

        return chbMonthPrdPositiondetail;
    }

    /**
     * 开放式净值型、开放式非净值型时取产品的开放天数，开放天数与产品基本信息中的【规律开放周期】的对应关系如下
     * @param openCircle
     * @param openCircleDays
     * @return
     */
    public static void getTermDays(ChbMonthPrdPositiondetail chbMonthPrdPositiondetail,ChbPrdBaseinfo info,ChbMonthPrdPosition prdPosition){
        BigDecimal days = null;
        String termType=null;
        if(DictCodeListExt2.产品运作模式_封闭式净值型.getValue().equals(info.getOperateMode())
            ||DictCodeListExt2.产品运作模式_封闭式非净值型.getValue().equals(info.getOperateMode())){
            days = prdPosition.getClosetermday();
            if(days.compareTo(new BigDecimal(90))<0){
                termType = DictCodeListExt3.中债月度明细_期限类型_90天以内.getValue();
            }else if(days.compareTo(new BigDecimal(90))>=0&&days.compareTo(new BigDecimal(183))<=0){
                termType = DictCodeListExt3.中债月度明细_期限类型_90天到6个月.getValue();
            }else if(days.compareTo(new BigDecimal(183))>0&&days.compareTo(new BigDecimal(365))<=0){
                termType = DictCodeListExt3.中债月度明细_期限类型_6到12个月.getValue();
            }else if(days.compareTo(new BigDecimal(365))>0&&days.compareTo(new BigDecimal(1095))<=0){
                termType = DictCodeListExt3.中债月度明细_期限类型_1到3年.getValue();
            }else if(days.compareTo(new BigDecimal(1095))>0){
                termType = DictCodeListExt3.中债月度明细_期限类型_3年以上.getValue();
            }
        }else{

                if(DictCodeListExt2.开放模式_中债_无规律开放.getValue().equals(info.getOpenMode())){
                    days =  BigDecimal.ONE;
                }else if(StringUtil.isNotEmpty(info.getOpenCircle())){
                    switch (info.getOpenCircle()){
                        case  "01": //DictCodeListExt2.规律开放周期_每天开放.getValue()
                            days = BigDecimal.ONE; break;
                        case "02" :
                            days = new BigDecimal("7"); break;
                        case "03" :
                            days = new BigDecimal("14"); break;
                        case "04" :
                            days = new BigDecimal("31"); break;
                        case "05" :
                            days = new BigDecimal("92"); break;
                        case "06" :
                            days = new BigDecimal("184"); break;
                        case "07" :
                            days = new BigDecimal("366"); break;
                        case "99" :
                            days = info.getOpenCircleDays(); break;
                    }
                }
            termType = info.getTermtype();
        }

        chbMonthPrdPositiondetail.setTermtype(termType);
        chbMonthPrdPositiondetail.setTermdays(days);
    }

    /**
     * 为详情赋值费用数据
     * @param prdPositiondetail
     * @param prodId
     * @param bankprofit
     */
    public static void setFeeDetail(List<ChbMonthPrdPositiondetail> prdPositiondetail, ChbMonthFeePosition feePosition){
        Map<String, List<ChbMonthPrdPositiondetail>> entrustIdMap = prdPositiondetail.stream().collect(JoyinCollectors.groupingBy(ChbMonthPrdPositiondetail::getProdid));
        for(ChbMonthPrdPositiondetail detail : entrustIdMap.get(feePosition.getProdid())){
            //覆盖费用数据
            BeanUtil.copyPropertiesIgnoreNull(detail,feePosition);
        }
    }


    /**
     * 组装详情信息入库
     * @param posList
     */
    public void buildDatilList(List<ChbMonthPrdPositiondetail> prdPositionList,String term) {
        String date = DateUtil.getYesterDayDate(DateUtil.addMonthDateStrM(term+"01",1));
        String source = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_EXCHANGE_SOURCE_PBC);
        Map<String, BigDecimal> rateMap = new HashMap<String, BigDecimal>();


        //非保本不需要转换币种.prostion就转过了
//        for(ChbMonthPrdPositiondetail dto: prdPositionList){
//            if(!DictCodeListExt1.币种_中债_人民币.getValue().equals(dto.getCcy())&& ProdConstants.PROFITTYPE_FBBFDSY.equals(pos.getProfitType())){
//                if(null!=dto.getEndamtsumOth()){
//                    dto.setEndamtsumOth(ChbCcyConvertUtil.exchangeMoney(
//                            dto.getCcy(), DictCodeListExt1.币种_中债_人民币.getValue(),DateUtil.parse(date),dto.getEndamtsumOth(),rateMap,source));
//                }
//                if(null!=dto.getPayamtsum()){
//                    dto.setPayamtsum(ChbCcyConvertUtil.exchangeMoney(
//                            dto.getCcy(), DictCodeListExt1.币种_中债_人民币.getValue(),DateUtil.parse(date),dto.getPayamtsum(),rateMap,source));
//                }
//                if(null!=dto.getCustomeramt()){
//                    dto.setCustomeramt(ChbCcyConvertUtil.exchangeMoney(
//                            dto.getCcy(), DictCodeListExt1.币种_中债_人民币.getValue(),DateUtil.parse(date),dto.getCustomeramt(),rateMap,source));
//                }
//                if(null!=dto.getBankamt()){
//                    dto.setBankamt(ChbCcyConvertUtil.exchangeMoney(
//                            dto.getCcy(), DictCodeListExt1.币种_中债_人民币.getValue(),DateUtil.parse(date),dto.getBankamt(),rateMap,source));
//                }
//            }
//        }
    }

    /**
     * 批量入库方法
     * @param prdPositionList
     * @return
     */
    public int insertBatch(List<ChbMonthPrdPositiondetail> prdPositionList,String term){

        if(null!=prdPositionList&&prdPositionList.size()==0){
            return 0;
        }
        //一次性最大插入条数
        int maxDataCountOneTime = reportInsertCount;
        //同批次的详情数据删除
        chbMonthReportDetailProdMapper.deleteDetail(term);


        this.buildDatilList(prdPositionList,term);

        //插入list大小
        int dataCount = prdPositionList.size();
        //若需要插入的数据比最大插入的数据少，则直接全部插入
        if (dataCount <= maxDataCountOneTime) {
            dataCount = chbMonthReportDetailProdMapper.insertBatch(prdPositionList);
            return dataCount;
        }

        List<ChbMonthPrdPositiondetail> newDataList = new ArrayList<ChbMonthPrdPositiondetail>();
        newDataList.addAll(prdPositionList);

        // 分批存入
        int part = dataCount / maxDataCountOneTime; // 分批数
        int insertCount = 0;
        for (int i = 0; i < part; i++ ) {
            insertCount += chbMonthReportDetailProdMapper.insertBatch(newDataList.subList(0, maxDataCountOneTime));
            newDataList.subList(0, maxDataCountOneTime).clear();
        }
        // 最后剩下的数据
        if (CollectionUtils.isNotEmpty(newDataList)) {
            insertCount += chbMonthReportDetailProdMapper.insertBatch(newDataList);
        }
        return insertCount;
    }






}
