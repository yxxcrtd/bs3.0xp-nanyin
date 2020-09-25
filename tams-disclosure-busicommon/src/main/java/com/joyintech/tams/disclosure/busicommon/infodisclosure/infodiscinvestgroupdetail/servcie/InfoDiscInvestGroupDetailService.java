package com.joyintech.tams.disclosure.busicommon.infodisclosure.infodiscinvestgroupdetail.servcie;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodiscinvestgroupdetail.dao.InfoDiscInvestGroupDetailMapper;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodiscinvestgroupdetail.entity.InfoDiscInvestGroupDetailEntity;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCodeList;


/**
 * 投资组合详细批处理执行Service
 * 
 * @author ZhangYangFei
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class InfoDiscInvestGroupDetailService {
	/**
	 * logger
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private InfoDiscInvestGroupDetailMapper infoDiscInvestGroupDetailMapper;
	public void genDetailData(String executeDateStr) {
		logger.debug("执行日期：" + executeDateStr);
		HashMap<String, String> para = new HashMap<String, String>();
		para.put("executeDateStr", executeDateStr);
		
		// 1.查询各产品总投资估值
		List<InfoDiscInvestGroupDetailEntity> prodInvestList = infoDiscInvestGroupDetailMapper.selectProdInvestList(para);
		if(StringUtil.isEmptyList(prodInvestList)){
			logger.debug("未查询到各产品总投资估值数据..." );
			return;
		}

		// 2.查询产品各类资产的投资估值-资产分类ASSET_TYPE_DIS
		List<InfoDiscInvestGroupDetailEntity> asssetInvestListClassOne = infoDiscInvestGroupDetailMapper.selectAssetInvestListByAssetclassOne(para);
		if(StringUtil.isEmptyList(prodInvestList)){
			logger.debug("未查询到各产品资产一级分类投资估值数据..." );
			return;
		}
		// 3.计算资产投资占比、判断是否超限
		// 变量.产品总投资估值
		BigDecimal prodAssetsValueNet;
		for (InfoDiscInvestGroupDetailEntity investEntity : prodInvestList) {
			prodAssetsValueNet = investEntity.getAssetsValueNet();
			if (prodAssetsValueNet == null || BigDecimal.ZERO.compareTo(prodAssetsValueNet) == 0) {
				continue;
			}
			if (!StringUtil.isEmptyList(asssetInvestListClassOne)) {
				for (InfoDiscInvestGroupDetailEntity assetOne : asssetInvestListClassOne) {
					// 设置资产一级分类投资估值占比
					setAssetsValueNetScale(assetOne, prodAssetsValueNet);
					// 设置超限标识
					setIsScaleOut(assetOne,para);
				}
			}
		}
		
		//4.存入投资组合详细表
		List<InfoDiscInvestGroupDetailEntity> infoDiscInvestGroupDetail = new ArrayList<InfoDiscInvestGroupDetailEntity>();
		infoDiscInvestGroupDetail.addAll(asssetInvestListClassOne);
		
		if(infoDiscInvestGroupDetail != null && !infoDiscInvestGroupDetail.isEmpty()) {
			for(InfoDiscInvestGroupDetailEntity entity : infoDiscInvestGroupDetail){
				//设定主键
				entity.setSeqno(IDGenerator.generate());
				entity.setStatus("E");
			}
			infoDiscInvestGroupDetailMapper.deleteInfoDiscInvestGroupDetail(para);
			infoDiscInvestGroupDetailMapper.insertInfoDiscInvestGroupDetail(infoDiscInvestGroupDetail);
		}
	}

	/**
	 * 设置超限标识
	 * @param assetOne
	 * @param para
	 */
	private void setIsScaleOut(InfoDiscInvestGroupDetailEntity assetOne, HashMap<String, String> para) {
		// 查询该产品的资产种类在报告期的投资比例
		para.put("prodId", assetOne.getProdId());
		para.put("assetTypeCode",assetOne.getAssetTypeCode());
		InfoDiscInvestGroupDetailEntity adjRateEntity = infoDiscInvestGroupDetailMapper.selectProdInvestAdjRateList(para);
		if(null != adjRateEntity && StringUtil.isNotEmpty(adjRateEntity.getRate())){
			String rate = adjRateEntity.getRate().replace("%", "");//格式如 10%-20%
			String scaleLimit[] = rate.split("-");
			if(StringUtil.isNotEmpty(scaleLimit)) {
				// 最低限制
				BigDecimal scaleMin = new BigDecimal(scaleLimit[0].trim());
				scaleMin = scaleMin.divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
				// 最高限制
				BigDecimal scaleMax = new BigDecimal(scaleLimit[1].trim());
				scaleMax = scaleMax.divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
				// 比较
				if(scaleMin.compareTo(assetOne.getAssetsValueNetScale())>0 || scaleMax.compareTo(assetOne.getAssetsValueNetScale())<0) {
					// 超限
					assetOne.setIsScaleOut(DictCodeList.是否_是.getValue());
				}
			}
		}
	}

	/**
	 * 设置投资估值占比
	 * 
	 * @param entity
	 * @param prodAssetsValueNet
	 */
	private void setAssetsValueNetScale(InfoDiscInvestGroupDetailEntity assetOne, BigDecimal prodAssetsValueNet) {
		// 保留小数8位
		assetOne.setAssetsValueNetScale(ArithmeticUtil.divide(assetOne.getAssetsValueNet(), prodAssetsValueNet, 8));
	}
}
