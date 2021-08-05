package com.joyintech.tams.invest.common.risktrialrds.reflect.duration.dao;

import com.joyintech.tams.invest.common.risktrialrds.reflect.duration.model.RiskManageCommonModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface RiskManageCommonMapper {

	/**
	 *
	 * @param cdate
	 * @param marketcode
	 * @return
	 */
	List<Date> queryLastFiveWorkdate(@Param("cdate") Date cdate, @Param("marketcode") String marketcode);

	/**
	 *
	 * <b>方法：</b>queryMDateByVDate<br>
	 * <b>描述：</b>根据债券secid和计算日得到含权债的下一行权日<br>
	 * @param map
	 * @return Date
	 *
	 */
	Date queryMDateByVDate(Map map);

	/**
	 *
	 * <b>方法：</b>queryRiskManageCommonModelByProdid<br>
	 * <b>描述：</b>根据产品prodid查询产品信息<br>
	 * @param prodid 产品代码
	 * @return RiskManageCommonModel
	 *
	 */
	RiskManageCommonModel queryRiskManageCommonModelByProdid(@Param("prodid") String prodid);

	/**
	 *
	 * <b>方法：</b>queryNextprdopenday<br>
	 * <b>描述：</b>根据计算日期和产品代码查询开放式产品下一个开放日<br>
	 * @param prodid 产品代码
	 * @param cDate 计算日
	 * @return 下一个开放日，如果没有下一个开放日则返回计算日
	 *
	 */
	Date queryNextprdopenday(@Param("prodid") String prodid, @Param("caldate") Date cDate);

	/**
	 *
	 * <b>方法：</b>queryPrdFixterm<br>
	 * <b>描述：</b>查询产品的固定期限日<br>
	 * @param prodid
	 * @return 固定期限日
	 *
	 */
	Integer queryPrdFixterm(String prodid);

	/**
	 *
	 * <b>方法：</b>queryListTTKFGDProd<br>
	 * <b>描述：</b>查询天天开放固定期限产品交易信息<br>
	 * @param map
	 * @return
	 *
	 */
	List<RiskManageCommonModel> queryListTTKFGDProd(Map<String, Object> map);

	Date queryMAXTTKFGDProd(Map<String, Object> map);

	/**
	 *
	 * <b>方法：</b>queryListTTKFKXProd<br>
	 * <b>描述：</b>查询天天开放可选期限产品交易信息<br>
	 * @param queryMap
	 * @return
	 *
	 */
	List<RiskManageCommonModel> queryListTTKFKXProd(Map<String, Object> queryMap);

	/**
	 *
	 * <b>方法：</b>queryMAXTTKFKXProd<br>
	 * <b>描述：</b>查询天天开放可选期限产品交易最大本金到期日<br>
	 * @param queryMap
	 * @return
	 *
	 */
	Date queryMAXTTKFKXProd(Map<String, Object> queryMap);

	/**
	 *
	 * <b>方法：</b>queryListPTHQOrHYFCProdInfo<br>
	 * <b>描述：</b>查询普通活期和收益分层产品的交易信息<br>
	 * @param queryMap
	 * @return
	 *
	 */
	List<RiskManageCommonModel> queryListPTHQOrHYFCProdInfo(Map<String, Object> queryMap);

	/**
	 *
	 * <b>方法：</b>querySecDurationBySecId<br>
	 * <b>描述：</b>查债券的估值久期<br>
	 * @param searchMap
	 * @return
	 *
	 */
	Map<String, BigDecimal> querySecDurationBySecId(Map<String, Object> searchMap);


	List<RiskManageCommonModel> getAssetListForDaily(@Param("prodid") String prodid, @Param("valDate") String valDate);

}
