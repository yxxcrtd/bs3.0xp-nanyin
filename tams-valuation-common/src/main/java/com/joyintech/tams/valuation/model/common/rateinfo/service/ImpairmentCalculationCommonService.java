package com.joyintech.tams.valuation.model.common.rateinfo.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.model.common.rateinfo.dto.ViewRatingInfoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * 减值模块视图查询
 */
@Service
public class ImpairmentCalculationCommonService extends BaseService{
	
	private static final Logger logger = LoggerFactory.getLogger(ImpairmentCalculationCommonService.class);

	/**
	 * 评级差(返回值 > 0 则，r1评级高；=0则评级相同，<0，则r2评级高)
	 * @param r1
	 * @param r2
	 * @return
	 */
	public int getPoorRating(String r1, String r2) {
		if (StringUtil.isEmpty(r1) || StringUtil.isEmpty(r2)) {
			return 0;
		}
		int ra = Integer.valueOf(r1.replaceAll("^(0+)", ""));
		int rs = Integer.valueOf(r2.replaceAll("^(0+)", ""));
		return rs - ra;
	}

	/**
	 * 评级结果比较返回最大值
	 * @param r1
	 * @param r2
	 * @param r3
	 * @return
	 */
	public String getMaxRating(String r1, String r2, String r3) {
		int ra = 0;
		int rb = 0;
		int rc = 0;
		if (StringUtil.isNotEmpty(r1)) {
			ra = Integer.valueOf(r1.replaceAll("^(0+)", ""));
		}
		if (StringUtil.isNotEmpty(r2)) {
			rb = Integer.valueOf(r2.replaceAll("^(0+)", ""));
		}
		if (StringUtil.isNotEmpty(r3)) {
			rb = Integer.valueOf(r3.replaceAll("^(0+)", ""));
		}
		int rst = 0;
		rst = ra;
		if (rb > rst) {
			rst = rb;
		}
		if (rc > rst) {
			rst = rc;
		}
		if (rst == ra) {
			return r1;
		} else if (rst == rb) {
			return r2;
		} else if (rst == rc) {
			return r3;
		}
		return null;
	}

	/**
	 * 评级结果比较返回最小值
	 * @param r1
	 * @param r2
	 * @param r3
	 * @return
	 */
	public String getMinRating(String r1, String r2, String r3) {
		int ra = 99;
		int rb = 99;
		int rc = 99;
		if (StringUtil.isNotEmpty(r1)) {
			ra = Integer.valueOf(r1.replaceAll("^(0+)", ""));
		}
		if (StringUtil.isNotEmpty(r2)) {
			rb = Integer.valueOf(r2.replaceAll("^(0+)", ""));
		}
		if (StringUtil.isNotEmpty(r3)) {
			rb = Integer.valueOf(r3.replaceAll("^(0+)", ""));
		}
		int rst = 99;
		rst = ra;
		if (rb < rst) {
			rst = rb;
		}
		if (rc < rst) {
			rst = rc;
		}
		if (rst == ra) {
			return r1;
		} else if (rst == rb) {
			return r2;
		} else if (rst == rc) {
			return r3;
		}
		return null;
	}

	/**
	 * 比较评级高低(返回值 > 0 则，r1评级高；=0则评级相同，<0，则r2评级高)
	 * @param r1
	 * @param r2
	 * @return
	 */
	public int comparePoorRating(String r1, String r2) {

		int calc = 0;
		// 若r1、r2同时为空，则返回 0
		if (StringUtil.isEmpty(r1) && StringUtil.isEmpty(r2)) {
			calc= 0;
		}
		// 若r1为空，则返回-1
		else if (StringUtil.isEmpty(r1)) {
			calc =  -1;
		}
		// 若r2为空，则返回1
		else if (StringUtil.isEmpty(r2)) {
			calc =  1;
		}
		int ra = Integer.valueOf(r1.replaceAll("^(0+)", ""));
		int rs = Integer.valueOf(r2.replaceAll("^(0+)", ""));
		calc = rs - ra;
		return calc;
	}
	/**
	 * 比较评级高低，返回较高的
	 * @param rating1
	 * @param rating2
	 * @return
	 */
	public ViewRatingInfoDto maxRating(ViewRatingInfoDto rating1,ViewRatingInfoDto rating2){
		if(rating1==null){
			if(rating2==null){
				return null;
			}else{
				return rating2;
			}
		}else{
			if(rating2==null){
				return rating1;
			}else{
				String r1 = rating1.getRating();
				String r2 = rating2.getRating();
				int ra = Integer.valueOf(r1.replaceAll("^(0+)", ""));
				int rs = Integer.valueOf(r2.replaceAll("^(0+)", ""));
				if(ra > rs){
					return rating1;
				}else{
					return rating2;
				}
			}
		}
	}

	/**
	 * 比较评级高低，返回较高的
	 * @param rating1
	 * @param rating2
	 * @return
	 */
	public ViewRatingInfoDto maxRating(ViewRatingInfoDto rating1,ViewRatingInfoDto rating2,ViewRatingInfoDto rating3){
		return maxRating(rating3,maxRating(rating1,rating2));
	}
}
