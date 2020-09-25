package com.joyintech.tams.disclosure.busicommon.infodisclosure.prodtrd.servie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.joyintech.framework.util.IDGenerator;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.prodtrd.dao.InfoDisProdtrdMapper;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.prodtrd.entity.InfoDisProdtrdEntity;

/**
 * 产品申赎信息detailService
 * 
 * @author ZhangYangFei
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class InfoDisProdtrdDetailService {
	/**
	 * logger
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private InfoDisProdtrdMapper infoDisProdtrdMapper;

    /**
     * 长sql最大一次插入或者更新条数
     */
    @Value("${application.reportInsertLongCount}")
    public int reportInsertLongCount;
    
	public void genDetailData(String executeDateStr) {
		logger.debug("执行日期：" + executeDateStr);
		HashMap<String, String> para = new HashMap<String, String>();
		// 跑批执行日期
		para.put("executeDateStr", executeDateStr);
		// 1.根据跑批日期，查询产品交易汇总信息
		List<InfoDisProdtrdEntity> chbPrdDealInfoList = infoDisProdtrdMapper.selectChbPrdDealInfoByUpdateTime(para);
		if (CollectionUtils.isEmpty(chbPrdDealInfoList)) {
			logger.debug("根据跑批日期，未查询产品交易信息。");
			return;
		}
		// 产品指标信息entity
		InfoDisProdtrdEntity prodIndexEntity = null;
		//2.遍历产品交易汇总信息
		for (InfoDisProdtrdEntity entity : chbPrdDealInfoList) {
			//3.查询产品指标，获取产品份额，并赋值给
			prodIndexEntity = infoDisProdtrdMapper.selectProdIndexByProdIdAndValueDate(entity);
			if(null != prodIndexEntity){
				entity.setCapital(prodIndexEntity.getCapital());
			}
			entity.setSeqNo(IDGenerator.generate(32));
		}
		//4.批量更新
		int count = mergeProdTrdList(chbPrdDealInfoList);
		logger.debug("更新表REP_PRODTRD，merge数据："+ count + "条");
	}

	/**
	 * 批量更新
	 * 
	 * @param dtoList
	 * @return
	 */
	private int mergeProdTrdList(List<InfoDisProdtrdEntity> dtoList) {
        //一次性最大插入条数
        int maxDataCountOneTime = reportInsertLongCount;
        //插入list大小
        int dataCount = dtoList.size();
        int mergeCount= 0;
        //若需要插入的数据比最大插入的数据少，则直接全部merge
        if (dataCount <= maxDataCountOneTime) {
            mergeCount = infoDisProdtrdMapper.mergeProdtrdList(dtoList);
            return mergeCount;
        }
        List<InfoDisProdtrdEntity> newDataList = new ArrayList<InfoDisProdtrdEntity>();
        newDataList.addAll(dtoList);
        
        // 分批处理
        int part = dataCount / maxDataCountOneTime; // 分批数
        for (int i = 0; i < part; i++ ) {
            mergeCount += infoDisProdtrdMapper.mergeProdtrdList(newDataList.subList(0, maxDataCountOneTime));
            newDataList.subList(0, maxDataCountOneTime).clear();
        }
        // 最后剩下的数据
        if (!CollectionUtils.isEmpty(newDataList)) {
            mergeCount += infoDisProdtrdMapper.mergeProdtrdList(newDataList);
        }
        return mergeCount;
	}
}
