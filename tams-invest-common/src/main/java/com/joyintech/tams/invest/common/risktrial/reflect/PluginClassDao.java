package com.joyintech.tams.invest.common.risktrial.reflect;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface PluginClassDao {

	@Select(
			"select SMV from PORTFOLIO_SUM_VALUATION "
			+" where sum_code=#{sumcode} "
			+" and portfolio_code in ${portfolioCode} "
			+" and val_date=#{valDate}"
	)
	Map<String, Object> getRecentPaiclUpCapital(@Param("portfolioCode")String portfolioCode,@Param("valDate")Date valDate,@Param("sumcode")String sumcode);
	
	@Select(
			"select" 
			+" AA.VAL_MODIFIED_DURATION as MODIFIEDDURATION,"
			+" BB.SMV"
			+" from STDDB.ASSET_BOND_VALUATION AA"
			+" inner join ( 	"
			+" select"
			+" ASSET_OBJECT_ID,"
			+" VAL_DATE,"
			+" case when smv  is null then security_cost else smv  end as smv"
			+" from"
			+" stddb.PORTFOLIO_ASSET_VALUATION val"
			+" join"
			+"	stddb.ASSET_CLASS_SUBJECT_CODE acsc"
			+" on"
			+"	acsc.ASSET_CLASS_CODE = 'ZQ'"
			+" where"
			+"	val.VAL_DATE = #{valDate}"
			+"	and val.SUBJECT_CODE like acsc.SUBJECT_CODE || '__01_%'"
			+"	and val.ASSET_OBJECT_ID is not null"
		    +" and VAL.PORTFOLIO_CODE in ${portfolioCode}) BB"
		    +" on AA.Val_Date=BB.VAL_DATE "
		    +" AND AA.Asset_Object_Id=BB.ASSET_OBJECT_ID "
	)
	List<Map<String, Object>> getRecentBondInfo(@Param("portfolioCode")String portfolioCode,@Param("valDate")Date valDate);
	
	@Select(
			" select SUM(SMV) AS SMV from"
			+" stddb.PORTFOLIO_ASSET_VALUATION val"
			+" where val.VAL_DATE = #{valDate} "
			+" and substr(val.SUBJECT_CODE, 7, 2) = '01' "
			+" and val.ASSET_OBJECT_ID is not null"
			+" and VAL.PORTFOLIO_CODE in ${portfolioCode}"
			+" group by VAL.SUBJECT_CODE HAVING count(VAL.SUBJECT_CODE)=#{size}"
	)
	Map<String, Object> getSameAssetSmv(@Param("portfolioCode")String portfolioCode,@Param("valDate")Date valDate,@Param("size")Integer size);
	
	@Select(
			"select  case when count(1)=0 then 0 else "
			+" max(case when bond.ISSUE_SCALE is null then null "
			+" else nvl(VAL.SECURITY_QUANTITY,0)*100/bond.ISSUE_SCALE end)  end as TOTALEQUITY "
			+" from "
			+" stddb.PORTFOLIO_ASSET_VALUATION val "
			+" join portfolio_info pi  "
			+" on pi.manager_code=#{managerCode}  "
			+" and val.portfolio_code=pi.code  "
			+" join "
			+" 		stddb.ASSET_CLASS_SUBJECT_CODE acsc "
			+" on "
			+" 		acsc.ASSET_CLASS_CODE = 'ZQ' "
		    +" join STDDB.ASSET_BOND_INFO bond "
		    +" on VAL.ASSET_OBJECT_ID=bond.ASSET_OBJECT_ID "
			+" where "
			+" 		val.VAL_DATE = #{valDate}"
			+" 		and val.SUBJECT_CODE like acsc.SUBJECT_CODE || '__01_%' "
			+" 		and val.ASSET_OBJECT_ID is not null "
	)
	Map<String, Object> getManagerBondScale(@Param("managerCode")String managerCode,@Param("valDate")Date valDate);
	
	@Select(
			"select  case when count(1)=0 then 0 else "
			+" max(case when stock.A_TOTAL_EQUITY is null then null "
			+" else VAL.SECURITY_QUANTITY/stock.A_TOTAL_EQUITY end)  end as TOTALEQUITY "
			+" from "
			+" stddb.PORTFOLIO_ASSET_VALUATION val "
			+" join portfolio_info pi  "
			+" on pi.manager_code=#{managerCode}  "
			+" and val.portfolio_code=pi.code  "
			+" join "
			+" 		stddb.ASSET_CLASS_SUBJECT_CODE acsc "
			+" on "
			+" 		acsc.ASSET_CLASS_CODE = 'GP' "
		    +" join STDDB.ASSET_STOCK_INFO stock "
		    +" on VAL.ASSET_OBJECT_ID=stock.ASSET_OBJECT_ID "
			+" where "
			+" 		val.VAL_DATE = #{valDate}"
			+" 		and val.SUBJECT_CODE like acsc.SUBJECT_CODE || '__01_%' "
			+" 		and val.ASSET_OBJECT_ID is not null "
	)
	Map<String, Object> getManagerStockScale(@Param("managerCode")String managerCode,@Param("valDate")Date valDate);
	
	@Select(
			"select  case when count(1)=0 then 0 else "
			+" max(case when fund.TOTAL_SHARE is null then null "
			+" else VAL.SMV/fund.TOTAL_SHARE end)  end as TOTALEQUITY "
			+" from "
			+" stddb.PORTFOLIO_ASSET_VALUATION val "
			+" join portfolio_info pi  "
			+" on pi.manager_code=#{managerCode}  "
			+" and val.portfolio_code=pi.code  "
			+" join "
			+" 		stddb.ASSET_CLASS_SUBJECT_CODE acsc "
			+" on "
			+" 		acsc.ASSET_CLASS_CODE = 'JJ' "
		    +" join STDDB.ASSET_FUND_INFO fund "
		    +" on VAL.ASSET_OBJECT_ID=fund.ASSET_OBJECT_ID "
			+" where "
			+" 		val.VAL_DATE = #{valDate}"
			+" 		and val.SUBJECT_CODE like acsc.SUBJECT_CODE || '__01_%' "
			+" 		and val.ASSET_OBJECT_ID is not null "
	)
	Map<String, Object> getManagerFundScale(@Param("managerCode")String managerCode,@Param("valDate")Date valDate);


	@Select(
			"	SELECT "
			+" 	NETPRICE"
			+" 	FROM"
			+" 	MST_BOND_VALUATION_INFO"
			+" 	WHERE"
			+" 	SEC_ID = (select FINPROD_MARKET_ID"
			+" 	from FIN_PRODUCT where FINPROD_ID=#{assetCode})"
			+" 	AND VALUE_SOURCE = ("
			+" 	select  case when REGIST_ORG='01' then 'CBC'"
			+" 	when REGIST_ORG='02' then 'SCH'"
			+" 	when REGIST_ORG='03' then 'CSD'"
			+" 	else  'CBC' end REGIST_ORG"
			+" 	from FIN_PRODUCT where FINPROD_ID=#{assetCode}"
			+" 	)"
			+" 	AND VALUE_DATE = ("
			+" 	SELECT MAX(VALUE_DATE) FROM MST_BOND_VALUATION_INFO"
			+" 	WHERE VALUE_DATE <(select CUR_DATE from SYS_DATE)"
			+" 	AND SEC_ID = (select FINPROD_MARKET_ID"
			+" 	from FIN_PRODUCT where FINPROD_ID=#{assetCode})"
			+" 	AND VALUE_SOURCE = ("
			+" 	select  case when REGIST_ORG='01' then 'CBC'"
			+" 	when REGIST_ORG='02' then 'SCH'"
			+" 	when REGIST_ORG='03' then 'CSD'"
			+" 	else  'CBC' end REGIST_ORG"
			+" 	from FIN_PRODUCT where FINPROD_ID=#{assetCode}))"
	)
	BigDecimal getRecentNetPrice(@Param("assetCode")String assetCode);

}
