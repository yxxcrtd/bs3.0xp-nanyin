package com.joyintech.service.product.nolimit.occupy.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.product.nolimit.common.dao.NolimitCommonMapper;
import com.joyintech.service.product.nolimit.common.dto.ProDesignAmtDto;
import com.joyintech.service.product.nolimit.occupy.dao.OccupyQueryMapper;
import com.joyintech.service.product.nolimit.occupy.dto.OccupyQueryDto;
import com.joyintech.service.product.nolimit.occupy.dto.ProdSeriesCurEndAmtDto;
import com.joyintech.service.product.productplanning.common.service.PlanningCommonService;
import com.joyintech.tams.common.base.entity.AssetClass;
import com.joyintech.tams.common.base.entity.AssetConfigMonDetail;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * yaozhenguo
 * 
 * @author Administrator 20191228 非标占比查询
 */
@Service
public class OccupyQueryService extends BaseService<OccupyQueryDto> {

	@Autowired
	private NolimitCommonMapper nolimitCommonMapper;
	@Autowired
	private OccupyQueryMapper occupyQueryMapper;
	@Autowired
	private PlanningCommonService planningCommonService;

	/**
	 * 查询口径为全部 非标
	 * 
	 * @param param
	 * @return
	 */
	public List<OccupyQueryDto> selectForPageByAll(Map<String, Object> param) {
		List<OccupyQueryDto> occupyQueryDtos = new ArrayList<>();
		OccupyQueryDto occupyQueryDto = new OccupyQueryDto();
		// 当前非标总资产
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("cdate", param.get("queryDay"));
		map1.put("isNonstandard", DictCodeListExt1.是否_是.getValue());
		BigDecimal nolimitTotalAssets = nolimitCommonMapper.getShareAmt(map1);
		occupyQueryDto.setNolimitTotalAssets(nolimitTotalAssets);
		// 当前非标总可用限额
		Map<String, Object> map2 = new HashMap<>();
		map2.put("effdate", DateUtil.parse((String) param.get("queryDay")));
		List<Map<String, Object>> list = nolimitCommonMapper.queryLimitAmt(map2);
		BigDecimal planNoLimitAmt = new BigDecimal(0);
		BigDecimal nonStandardLimit = BigDecimal.ZERO;
		if (!StringUtil.isEmptyList(list) && StringUtil.isNotEmpty(list.get(0))) {
			// 非标比例
			BigDecimal nonStandardRotio = new BigDecimal(String.valueOf(list.get(0).get("NON_STANDARD_ROTIO")));
			// 非标限额
			nonStandardLimit = new BigDecimal(String.valueOf(list.get(0).get("NON_STANDARD_LIMIT")));
			// 计划资产总量
			Map<String, Object> map3 = new HashMap<>();
			map3.put("queryDay", param.get("queryDay"));
			BigDecimal targetScale = occupyQueryMapper.getTargetScale(map3);
			// 计划非标总可用限额 = （计划资产总量*变量.非标比例）-当前非标总资产
			planNoLimitAmt = ArithmeticUtil.subtract(ArithmeticUtil.multiply(targetScale, 
					ArithmeticUtil.divide(nonStandardRotio,new BigDecimal("100"))),nolimitTotalAssets);
		}
		occupyQueryDto.setUpperLimitScale(planNoLimitAmt);
		// 当前总资产规模
		Map<String, Object> map4 = new HashMap<String, Object>();
		map4.put("cdate", param.get("queryDay"));
		BigDecimal totalAssetsScale = nolimitCommonMapper.getShareAmt(map4);
		occupyQueryDto.setTotalAssetsScale(totalAssetsScale);
		// 当前非标总可用限额
		BigDecimal availableTotalAssets = ArithmeticUtil.subtract(nonStandardLimit, nolimitTotalAssets);
		occupyQueryDto.setAvailableTotalAssets(availableTotalAssets);
		// 到期分布
		List<AssetClass> assetClasses = planningCommonService.queryAssetType();
		// 资产二级分类
		List<String> customAssetTypeTwo = new ArrayList<>();
		// 资产三级分类
		List<String> customAssetTypeThree = new ArrayList<>();
		// 资产四级分类
		List<String> customAssetTypeFour = new ArrayList<>();
		// 资产五级分类
		List<String> customAssetTypeFive = new ArrayList<>();
		if (!StringUtil.isEmptyList(assetClasses)) {
			for (AssetClass assetClass : assetClasses) {
				if (StringUtil.equals(assetClass.getIsNonstandard(), DictCodeListExt1.是否_是.getValue())) {
					customAssetTypeTwo.add(assetClass.getCustomAssetTypeTwo());
					customAssetTypeThree.add(assetClass.getCustomAssetTypeThree());
					customAssetTypeFour.add(assetClass.getCustomAssetTypeFour());
					customAssetTypeFive.add(assetClass.getCustomAssetTypeFive());
				}
			}
			Map<String, Object> map5 = new HashMap<>();
			map5.put("cdate", param.get("queryDay"));
			map5.put("benchDate", param.get("queryDay"));
			map5.put("benchDateEnd", DateUtil.format(getYearLastDay(DateUtil.parse((String) param.get("queryDay")))));
			map5.put("type9", customAssetTypeTwo);
			map5.put("type10", customAssetTypeThree);
			map5.put("type11", customAssetTypeFour);
			map5.put("type12", customAssetTypeFive);
			//按月份资产还本到期
			List<AssetConfigMonDetail> assetConfigMonDetails = planningCommonService.getCurEndAmt(map5);
			if (!StringUtil.isEmptyList(assetConfigMonDetails)) {
				for (AssetConfigMonDetail assetConfigMonDetail : assetConfigMonDetails) {
					String month = assetConfigMonDetail.getAssetMonth().substring(4, 6);
					switch (month) {
					case "01":
						occupyQueryDto.setDistribution1(assetConfigMonDetail.getCurEndAmt());
						break;
					case "02":
						occupyQueryDto.setDistribution2(assetConfigMonDetail.getCurEndAmt());
						break;
					case "03":
						occupyQueryDto.setDistribution3(assetConfigMonDetail.getCurEndAmt());
						break;
					case "04":
						occupyQueryDto.setDistribution4(assetConfigMonDetail.getCurEndAmt());
						break;
					case "05":
						occupyQueryDto.setDistribution5(assetConfigMonDetail.getCurEndAmt());
						break;
					case "06":
						occupyQueryDto.setDistribution6(assetConfigMonDetail.getCurEndAmt());
						break;
					case "07":
						occupyQueryDto.setDistribution7(assetConfigMonDetail.getCurEndAmt());
						break;
					case "08":
						occupyQueryDto.setDistribution8(assetConfigMonDetail.getCurEndAmt());
						break;
					case "09":
						occupyQueryDto.setDistribution9(assetConfigMonDetail.getCurEndAmt());
						break;
					case "10":
						occupyQueryDto.setDistribution10(assetConfigMonDetail.getCurEndAmt());
						break;
					case "11":
						occupyQueryDto.setDistribution11(assetConfigMonDetail.getCurEndAmt());
						break;
					case "12":
						occupyQueryDto.setDistribution12(assetConfigMonDetail.getCurEndAmt());
						break;
					default:
						break;
					}
				}
			}
		}
		occupyQueryDtos.add(occupyQueryDto);
		return occupyQueryDtos;

	}

	/**
	 * 查询口径为按产品系列查询
	 * 
	 * @param page
	 * @param limit
	 * @param param
	 * @return
	 */
	public Page<OccupyQueryDto> selectForPageByProdSeries(int page, int limit, Map<String, Object> param) {
		Map<String, Object> map1 = new HashMap<>();
		map1.put("cdate", param.get("queryDay"));
		map1.put("isNonstandard", DictCodeListExt1.是否_是.getValue());
		// List<ProDesignAmtDto> list = nolimitCommonMapper.getProDesignAmt(map1);
		//产品系列可用额度查询
		Page<ProDesignAmtDto> pageList = queryForPageForSelectReturn(page, limit, () -> {
			nolimitCommonMapper.getProDesignAmt(map1);
		});

		// 到期分布
		//资产分类查询
		List<AssetClass> assetClasses = planningCommonService.queryAssetType();
		// 资产二级分类
		List<String> customAssetTypeTwo = new ArrayList<>();
		// 资产三级分类
		List<String> customAssetTypeThree = new ArrayList<>();
		// 资产四级分类
		List<String> customAssetTypeFour = new ArrayList<>();
		// 资产五级分类
		List<String> customAssetTypeFive = new ArrayList<>();
		if (!StringUtil.isEmptyList(assetClasses)) {
			for (AssetClass assetClass : assetClasses) {
				if (StringUtil.equals(assetClass.getIsNonstandard(), DictCodeListExt1.是否_是.getValue())) {
					customAssetTypeTwo.add(assetClass.getCustomAssetTypeTwo());
					customAssetTypeThree.add(assetClass.getCustomAssetTypeThree());
					customAssetTypeFour.add(assetClass.getCustomAssetTypeFour());
					customAssetTypeFive.add(assetClass.getCustomAssetTypeFive());
				}
			}
		}
		Map<String, Object> map2 = new HashMap<>();
		map2.put("cdate", param.get("queryDay"));
		map2.put("benchDate", param.get("queryDay"));
		map2.put("benchDateEnd", DateUtil.format(getYearLastDay(DateUtil.parse((String) param.get("queryDay")))));
		map2.put("type9", customAssetTypeTwo);
		map2.put("type10", customAssetTypeThree);
		map2.put("type11", customAssetTypeFour);
		map2.put("type12", customAssetTypeFive);
		List<ProdSeriesCurEndAmtDto> list2 = getProdSeriesCurEndAmt(map2);
		List<OccupyQueryDto> oqList = new ArrayList<>();
		if (!StringUtil.isEmptyList(pageList.getRoot())) {
			for (ProDesignAmtDto proDesignAmtDto : pageList.getRoot()) {
				OccupyQueryDto occupyQueryDto = new OccupyQueryDto();
				//查询产品系列名称
				occupyQueryDto.setProdSeries(proDesignAmtDto.getProDesignid());
				if (StringUtil.isNotEmpty(proDesignAmtDto.getProDesignid())) {
					Map<String, Object> map3 = new HashMap<>();
					map3.put("initValue", proDesignAmtDto.getProDesignid());
					List<SelectOptionDto> selectOptionDtos = queryPrdDesign(map3);
					if (!StringUtil.isEmptyList(selectOptionDtos)) {
						occupyQueryDto.setProdSeriesName(selectOptionDtos.get(0).getText());
					}
				}
				//当前非标总资产
				occupyQueryDto.setNolimitTotalAssets(proDesignAmtDto.getTotalAmt());
				//存量非标占比
				occupyQueryDto.setStockProportion(
						ArithmeticUtil.divide(proDesignAmtDto.getTotalAmt(), proDesignAmtDto.getTdyAssetValue(), 5));
				//按月份分布还本到期
				for (ProdSeriesCurEndAmtDto curEndAmt : list2) {
					if (StringUtil.equals(proDesignAmtDto.getProDesignid(), curEndAmt.getPrdDesignId())) {
						switch (curEndAmt.getAssetMonth()) {
						case "01":
							occupyQueryDto.setDistribution1(curEndAmt.getCurEndAmt());
							break;
						case "02":
							occupyQueryDto.setDistribution2(curEndAmt.getCurEndAmt());
							break;
						case "03":
							occupyQueryDto.setDistribution3(curEndAmt.getCurEndAmt());
							break;
						case "04":
							occupyQueryDto.setDistribution4(curEndAmt.getCurEndAmt());
							break;
						case "05":
							occupyQueryDto.setDistribution5(curEndAmt.getCurEndAmt());
							break;
						case "06":
							occupyQueryDto.setDistribution6(curEndAmt.getCurEndAmt());
							break;
						case "07":
							occupyQueryDto.setDistribution7(curEndAmt.getCurEndAmt());
							break;
						case "08":
							occupyQueryDto.setDistribution8(curEndAmt.getCurEndAmt());
							break;
						case "09":
							occupyQueryDto.setDistribution9(curEndAmt.getCurEndAmt());
							break;
						case "10":
							occupyQueryDto.setDistribution10(curEndAmt.getCurEndAmt());
							break;
						case "11":
							occupyQueryDto.setDistribution11(curEndAmt.getCurEndAmt());
							break;
						case "12":
							occupyQueryDto.setDistribution12(curEndAmt.getCurEndAmt());
							break;
						default:
							break;
						}
					}
				}
				oqList.add(occupyQueryDto);
			}
		}
		Page<OccupyQueryDto> oqPage = new Page<>();
		oqPage.setRecords(pageList.getRecords());
		oqPage.setPage(pageList.getPage());
		oqPage.setRoot(oqList);
		oqPage.setTotal(pageList.getTotal());
		return oqPage;
	}

	/**
	 * 获取传入日期所在年的最后一天
	 * 
	 * @param todayDate
	 * @return
	 */
	public Date getYearLastDay(Date todayDate) {
		Calendar calendar = Calendar.getInstance();
		// 把平台日期赋值给Calendar
		calendar.setTime(todayDate);
		int last = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
		calendar.set(Calendar.DAY_OF_YEAR, last);
		return calendar.getTime();
	}

	public int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		// 把平台日期赋值给Calendar
		calendar.setTime(date);
		return calendar.get(calendar.YEAR);
	}

	/**
	 * 产品系列资产还本到期金额
	 * 
	 * @param param
	 *            cdate：基准日 benchDate：查询开始日 benchDateEnd：查询结束日
	 * @return
	 */
	public List<ProdSeriesCurEndAmtDto> getProdSeriesCurEndAmt(Map<String, Object> param) {
		param.put("holdingType", DictCodeList.持仓类型_实际.getValue());
		param.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
		param.put("finprodType_bond", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
		return occupyQueryMapper.getProdSeriesCurEndAmt(param);
	}

	/**
	 * 产品设计系列查询
	 * 
	 * @param param
	 * @return
	 */
	public List<SelectOptionDto> queryPrdDesign(Map<String, Object> param) {
		if (!StringUtil.isNotEmpty(param.get("initValue"))) {
			return null;
		}
		param.put("dataStatus_effective", DictCodeListExt1.有效状态_有效.getValue());
		return occupyQueryMapper.queryPrdDesign(param);
	}
}
