package com.joyintech.service.product.rddesgin.productSimulationTrial.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.product.rddesgin.productSimulationTrial.dao.ProductHistoricalMapper;
import com.joyintech.service.product.rddesgin.productSimulationTrial.dto.ProductHistoricalDto;
import com.joyintech.tams.busicommon.productRating.dto.CommonProductRatingDto;
import com.joyintech.tams.busicommon.productRating.service.impl.CommonProductRatingImplService;
import com.joyintech.tams.constant.DictCodeListExt3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.*;

/**
 * 历史数据查询service
 * 
 * @author jiaobo
 * @version 1.0 2019年11月5日
 * @see ProductHistoricalDto
 * @since 1.0
 */
@Service
public class ProductHistoricalService extends BaseService{

	@Autowired
	private ProductHistoricalMapper productHistoricalMapper;

	@Autowired
	private CommonProductRatingImplService commonProductRatingImplService;




	/**
	 * 查询历史记录信息
	 *
	 * @param page page
	 * @param length length
	 * @param pageParam 参数
	 * @return 模拟试算信息
	 * @throws Exception 异常
	 * @see
	 * @since 1.0
	 */
	public List<ProductHistoricalDto> findPage(Map<String, Object> pageParam)throws Exception {
		String assetTypeOne = (String)pageParam.get("assetTypeOne");
		String assetTypeThree = (String)pageParam.get("assetTypeThree");
		pageParam.put("assetTypeThree", StringUtil.splitToList(GlobalConstant.COMMA, assetTypeThree));
		String assetTypeFour = (String)pageParam.get("assetTypeFour");
		pageParam.put("assetTypeFour", StringUtil.splitToList(GlobalConstant.COMMA, assetTypeFour));
		String assetTypeFive = (String)pageParam.get("assetTypeFive");
		pageParam.put("assetTypeFive", StringUtil.splitToList(GlobalConstant.COMMA, assetTypeFive));
		if(StringUtil.isEmpty(assetTypeThree)) {
			List<String> allAssetTypeThree =  new ArrayList<String>();
			if(StringUtil.isNotEmpty(assetTypeOne)){
				if(assetTypeOne.equals("01")){
					allAssetTypeThree.add("17");
					allAssetTypeThree.add("18");
					allAssetTypeThree.add("19");
					allAssetTypeThree.add("20");
					allAssetTypeThree.add("21");
					allAssetTypeThree.add("22");
				}else if(assetTypeOne.equals("02")){
					allAssetTypeThree.add("01");
					allAssetTypeThree.add("02");
					allAssetTypeThree.add("08");
					allAssetTypeThree.add("09");
					allAssetTypeThree.add("10");
					allAssetTypeThree.add("11");
					allAssetTypeThree.add("12");
					allAssetTypeThree.add("13");
				}else if(assetTypeOne.equals("03")){
					allAssetTypeThree.add("03");
					allAssetTypeThree.add("04");
					allAssetTypeThree.add("05");
					allAssetTypeThree.add("06");
					allAssetTypeThree.add("07");
				}else{
					allAssetTypeThree.add("14");
					allAssetTypeThree.add("15");
					allAssetTypeThree.add("30");
					allAssetTypeThree.add("31");
					allAssetTypeThree.add("32");
					allAssetTypeThree.add("33");
					allAssetTypeThree.add("34");
					allAssetTypeThree.add("35");
					allAssetTypeThree.add("36");
					allAssetTypeThree.add("37");
					allAssetTypeThree.add("38");
					allAssetTypeThree.add("39");
					allAssetTypeThree.add("40");
					allAssetTypeThree.add("41");
					allAssetTypeThree.add("64");
					allAssetTypeThree.add("65");
				}
			}
			/*List<String> allAssetTypeThree = productHistoricalMapper.getCustomAssetTypeThree(pageParam);*/
			pageParam.put("allAssetTypeThree",allAssetTypeThree);
		}
		List<ProductHistoricalDto> productHistoricalDtoList =productHistoricalMapper.findPage(pageParam);
//		Page<ProductHistoricalDto> resultPage = this.queryForPage(page, length, () -> productHistoricalMapper.findPage(pageParam));
		if((StringUtil.equals(assetTypeOne, DictCodeListExt3.资产一级分类_债券类信用债.getValue()) ||
				StringUtil.equals(assetTypeOne, DictCodeListExt3.资产一级分类_债券类利率债.getValue()))
				&& !StringUtil.isEmptyList(productHistoricalDtoList)){
			List<ProductHistoricalDto> productHistoricalDtoList2 = new ArrayList<ProductHistoricalDto>();
			String debtRating = (String)pageParam.get("debtRating");
			String fcrGradeOut = (String)pageParam.get("fcrGradeOut");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("gradeDateStart",pageParam.get("startCheckDate"));
            map.put("gradeDateEnd ",pageParam.get("endCheckDate"));
            map.put("baseDataType","01");
            
            for (ProductHistoricalDto dto :productHistoricalDtoList){

                //主体评级
                map.put("oCode",dto.getIssuer());
                List<CommonProductRatingDto> list1 = commonProductRatingImplService.getProductOrgRating(map);

                //债项评级
                map.put("oCode",dto.getAssetId());
                List<CommonProductRatingDto> list2 = commonProductRatingImplService.getProductMarketRating(map);
                
                if(StringUtil.isNotEmpty(fcrGradeOut) && !StringUtil.isNotEmpty(debtRating)) {
                    if(!StringUtil.isEmptyList(list1)){
                        CommonProductRatingDto commonProductRatingDto = list1.get(0);
                        if(commonProductRatingDto!=null && StringUtil.isNotEmpty(commonProductRatingDto.getGradeResult())
                                &&new BigDecimal(commonProductRatingDto.getGradeResult()).compareTo(new BigDecimal(fcrGradeOut))<=0) {
                            dto.setFcrGradeOut(commonProductRatingDto.getGradeResult());
                            productHistoricalDtoList2.add(dto);
                        }
                    }
                } 
                else if(StringUtil.isNotEmpty(debtRating) && !StringUtil.isNotEmpty(fcrGradeOut)){
                    if(!StringUtil.isEmptyList(list2)){
                        CommonProductRatingDto commonProductRatingDto = list2.get(0);
                        if(commonProductRatingDto!=null && StringUtil.isNotEmpty(commonProductRatingDto.getGradeResult())
                                &&new BigDecimal(commonProductRatingDto.getGradeResult()).compareTo(new BigDecimal(debtRating))<=0) {
                            dto.setDebtRating(commonProductRatingDto.getGradeResult());
                            productHistoricalDtoList2.add(dto);
                        }
                    }
                } 
                else if(StringUtil.isNotEmpty(debtRating) && StringUtil.isNotEmpty(fcrGradeOut)){
                    if(!StringUtil.isEmptyList(list2) && !StringUtil.isEmptyList(list1)) {
                        CommonProductRatingDto fcrGradeOutDto = list1.get(0);
                        CommonProductRatingDto debtRatingDto = list2.get(0);
                        if(StringUtil.isNotEmpty(fcrGradeOutDto.getGradeResult()) && StringUtil.isNotEmpty(debtRatingDto.getGradeResult())
                            && new BigDecimal(fcrGradeOutDto.getGradeResult()).compareTo(new BigDecimal(fcrGradeOut))<=0
                            && new BigDecimal(debtRatingDto.getGradeResult()).compareTo(new BigDecimal(debtRating))<=0) {
                            dto.setFcrGradeOut(fcrGradeOutDto.getGradeResult());
                            dto.setDebtRating(debtRatingDto.getGradeResult());
                            productHistoricalDtoList2.add(dto);
                        } 
                    }
                }    
            }

            if(StringUtil.isNotEmpty(debtRating) || StringUtil.isNotEmpty(fcrGradeOut)) {
                return productHistoricalDtoList2;
            } else {
                return productHistoricalDtoList;
            }
            
		}

		return productHistoricalDtoList;
	}

	/**
	 * 资产分类3多选下拉框加载
	 *
	 * @param pageParam 参数
	 * @return list
	 * @see
	 * @since 1.0
	 */
	public Page<SelectOptionDto> queryMulType3(int page, int length,
											   Map<String, Object> pageParam) {
		String initValue = (String)pageParam.get("initValue");
		pageParam.put("initValue", StringUtil.splitToList(GlobalConstant.COMMA, initValue));
		Page<SelectOptionDto> pages = super.queryForPage(page, length,
				() -> productHistoricalMapper.queryMulType3(pageParam));
		return pages;
	}

	/**
	 * 资产分类4多选下拉框加载
	 *
	 * @param pageParam 参数
	 * @return list
	 * @see
	 * @since 1.0
	 */
	public Page<SelectOptionDto> queryMulType4(int page, int length,
											   Map<String, Object> pageParam) {
		String assetTypeThree = (String)pageParam.get("assetTypeThree");
		pageParam.put("assetTypeThree", StringUtil.splitToList(GlobalConstant.COMMA, assetTypeThree));
		String initValue = (String)pageParam.get("initValue");
		pageParam.put("initValue", StringUtil.splitToList(GlobalConstant.COMMA, initValue));
		Page<SelectOptionDto> pages = super.queryForPage(page, length,
				() -> productHistoricalMapper.queryMulType4(pageParam));
		return pages;
	}

	/**
	 * 资产分类5多选下拉框加载
	 *
	 * @param pageParam 参数
	 * @return list
	 * @see
	 * @since 1.0
	 */
	public Page<SelectOptionDto> queryMulType5(int page, int length,
											   Map<String, Object> pageParam) {
		String assetTypeFour = (String)pageParam.get("assetTypeFour");
		pageParam.put("assetTypeFour", StringUtil.splitToList(GlobalConstant.COMMA, assetTypeFour));
		String initValue = (String)pageParam.get("initValue");
		pageParam.put("initValue", StringUtil.splitToList(GlobalConstant.COMMA, initValue));
		Page<SelectOptionDto> pages = super.queryForPage(page, length,
				() -> productHistoricalMapper.queryMulType5(pageParam));
		return pages;
	}

}
