package com.joyintech.tams.svreport.chb.monthreportdetail.service;

import com.joyintech.background.component.common.pack.common.util.BeanUtil;
import com.joyintech.background.component.common.pack.common.util.IDGenerator;
import com.joyintech.tams.common.base.dao.ChbMonthAssetPositiondetailMapper;
import com.joyintech.tams.common.base.dao.ChbMonthPrdPositiondetailMapper;
import com.joyintech.tams.common.base.entity.ChbAssetInfo;
import com.joyintech.tams.common.base.entity.ChbMonthAssetPositiondetail;
import com.joyintech.tams.common.base.entity.ChbMonthPrdPositiondetail;
import com.joyintech.tams.common.base.entity.ChbPrdBaseinfo;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthAssetPosition;
import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthPrdPosition;
import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthReportDetail;
import com.joyintech.tams.svreport.chb.monthreportdetail.dao.ChbMonthReportDetailAssetMapper;
import com.joyintech.tams.svreport.chb.monthreportdetail.dao.ChbMonthReportDetailProdMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service
public class ChbMonthReportDetailAssetUtil {

    @Autowired
    private ChbMonthReportDetailAssetMapper chbMonthReportDetailAssetMapper;
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
    public static ChbMonthAssetPositiondetail buildAssetDetail(ChbMonthAssetPosition position, ChbPrdBaseinfo prodInfo, ChbAssetInfo chbAssetInfo, ChbMonthReportDetail reportDetail){
        ChbMonthAssetPositiondetail chbMonthAssetPositiondetail = new ChbMonthAssetPositiondetail();
        //相同内容字段直接赋值
        BeanUtil.copyPropertiesIgnoreNull(chbMonthAssetPositiondetail,prodInfo);

        if(null!=chbAssetInfo){
            BeanUtil.copyPropertiesIgnoreNull(chbMonthAssetPositiondetail,chbAssetInfo);
        }
        //先基本信息,再头寸信息,避免错误覆盖
        BeanUtil.copyPropertiesIgnoreNull(chbMonthAssetPositiondetail,position);
        BeanUtil.copyPropertiesIgnoreNull(chbMonthAssetPositiondetail,reportDetail);

//        BeanUtil.copyPropertiesIgnoreNull(chbMonthAssetPositiondetail,reportDetail);
        chbMonthAssetPositiondetail.setDetailno(reportDetail.getPropertycode());
        chbMonthAssetPositiondetail.setTerm(reportDetail.getTermid());
        //生成主键
        chbMonthAssetPositiondetail.setPosuuid(IDGenerator.generate(36));

        return chbMonthAssetPositiondetail;
    }

    /**
     * 批量入库方法
     * @param prdPositionList
     * @return
     */
    public int insertBatch(List<ChbMonthAssetPositiondetail> prdPositionList,String term){
        //一次性最大插入条数
        int maxDataCountOneTime = reportInsertCount;
        //同批次的详情数据删除
        chbMonthReportDetailAssetMapper.deleteDetail(term);


        //插入list大小
        int dataCount = prdPositionList.size();
        //若需要插入的数据比最大插入的数据少，则直接全部插入
        if (dataCount <= maxDataCountOneTime) {
            dataCount = chbMonthReportDetailAssetMapper.insertBatch(prdPositionList);
            return dataCount;
        }

        List<ChbMonthAssetPositiondetail> newDataList = new ArrayList<ChbMonthAssetPositiondetail>();
        newDataList.addAll(prdPositionList);

        // 分批存入
        int part = dataCount / maxDataCountOneTime; // 分批数
        int insertCount = 0;
        for (int i = 0; i < part; i++ ) {
            insertCount += chbMonthReportDetailAssetMapper.insertBatch(newDataList.subList(0, maxDataCountOneTime));
            newDataList.subList(0, maxDataCountOneTime).clear();
        }
        // 最后剩下的数据
        if (CollectionUtils.isNotEmpty(newDataList)) {
            insertCount += chbMonthReportDetailAssetMapper.insertBatch(newDataList);
        }
        return insertCount;
    }
}
