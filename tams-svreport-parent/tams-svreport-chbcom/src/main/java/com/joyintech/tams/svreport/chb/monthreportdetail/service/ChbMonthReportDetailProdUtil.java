package com.joyintech.tams.svreport.chb.monthreportdetail.service;

import com.joyintech.background.component.common.pack.common.util.BeanUtil;
import com.joyintech.background.component.common.pack.common.util.IDGenerator;
import com.joyintech.background.component.common.pack.common.util.StringUtil;
import com.joyintech.tams.common.base.dao.ChbMonthPrdPositiondetailMapper;
import com.joyintech.tams.common.base.entity.ChbMonthPrdPositiondetail;
import com.joyintech.tams.common.base.entity.ChbPrdBaseinfo;
import com.joyintech.tams.constant.DictCodeListExt2;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
//        chbMonthPrdPositiondetail.setDetailno(reportDetail.getPropertycode());
        chbMonthPrdPositiondetail.setTerm(termid);

        chbMonthPrdPositiondetail.setTermdays(getTermDays(info.getOperateMode(),info.getOpenCircle(),info.getOpenCircleDays(),prdPosition.getClosetermday()));

        return chbMonthPrdPositiondetail;
    }

    /**
     * 开放式净值型、开放式非净值型时取产品的开放天数，开放天数与产品基本信息中的【规律开放周期】的对应关系如下
     * @param openCircle
     * @param openCircleDays
     * @return
     */
    public static BigDecimal getTermDays(String operateMode,String openCircle,BigDecimal openCircleDays,BigDecimal closetermday){
        BigDecimal days = null;
        if(DictCodeListExt2.产品运作模式_封闭式净值型.getValue().equals(operateMode)
            ||DictCodeListExt2.产品运作模式_封闭式非净值型.getValue().equals(operateMode)){
            days = closetermday;
        }else{
            if(StringUtil.isNotEmpty(openCircle)){
                switch (openCircle){
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
                        days = openCircleDays; break;
                }
            }
        }

        return days;
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
     * 批量入库方法
     * @param prdPositionList
     * @return
     */
    public int insertBatch(List<ChbMonthPrdPositiondetail> prdPositionList,String term){
        //一次性最大插入条数
        int maxDataCountOneTime = reportInsertCount;
        //同批次的详情数据删除
        chbMonthReportDetailProdMapper.deleteDetail(term);

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
