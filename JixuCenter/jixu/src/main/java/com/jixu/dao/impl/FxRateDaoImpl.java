package com.jixu.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.common.types.JixuDictCode;
import com.jixu.common.util.Pages;
import com.jixu.dao.intf.FxRateDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.jixu.mvc.control.model.MarketFxRateModel;
import com.orm.obj.FxRate;

/**
 * @ClassName 		FxRateDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("fxRateDao")
public class FxRateDaoImpl extends CommFuncDAOImpl implements FxRateDao{
	
	@Override
	public void saveFxRate(FxRate fxrate) {
		saveEntity(fxrate);
		
	}
	
	@Override
	public void updateFxRate(FxRate fxrate) {
		updateEntity(fxrate);
		
	}
	
	@Override
	public FxRate loadFxRate(FxRate fxrate) {
		String curID=fxrate.getCurrID();
		String baseCurID=fxrate.getBaseCurrID();
		Date rateDate=fxrate.getRateDate();
		int sourceID=fxrate.getSourceID().getSourceID();
		String hql="from FxRate where currID=:curID and baseCurrID=:baseCurID and rateDate=:rateDate and sourceID.sourceID=:sourceID";
		String[] params={"curID","baseCurID","rateDate","sourceID"};
		Object[] values={curID,baseCurID,rateDate,sourceID};
		List<FxRate> rateList=this.executeHQL(hql, params, values);
		if(rateList.size()>0){
			return rateList.get(0);
		}else{
			return null;
		}
	}
	
	@Override
	public Pages<MarketFxRateModel> lostMarketFxRate(Pages<MarketFxRateModel> pages, int sourceid, String cur,
			String baseCur, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		StringBuilder strBuilder=new StringBuilder();
		List<String> params=new ArrayList<String>();
		List<Object> values=new ArrayList<Object>();
		String hql="select new com.jixu.mvc.control.model.MarketFxRateModel(id, sourceID.sourceID,sourceID.sourceDesc,currID,"
				+ "baseCurrID,rateDate,rate) from FxRate fr"
				+ " where 1=1 ";
		
		strBuilder.append(hql);
		if(sourceid>0) {
			strBuilder.append(" and sourceID.sourceID=:sourceid ");
			params.add("sourceid");
			values.add(sourceid);
		}
		
		if(cur!=null && !cur.equals("") && !cur.equals("0")){
			strBuilder.append(" and currID=:cur");
			params.add("cur");
			values.add(cur);
		}
		if(baseCur!=null && baseCur.equals("") && !cur.equals("0")){
			strBuilder.append(" and baseCurrID=:baseCur");
			params.add("baseCur");
			values.add(baseCur);
		}
		if(startDate!=null){
			strBuilder.append(" and rateDate>=:startDate");
			params.add("startDate");
			values.add(startDate);
		}
		if(endDate!=null){
			strBuilder.append(" and rateDate<=:endDate");
			params.add("endDate");
			values.add(endDate);
		}
		strBuilder.append(" order by rateDate desc, id desc");
		
		String[] paramsArr=params.toArray(new String[params.size()]);
		Object[] valuesArr=values.toArray();
		
		return this.getPageHqlData(strBuilder.toString(), paramsArr, valuesArr, false, pages);
	}
	
	@Override
	public MarketFxRateModel loadMarketFxRateDetail(int rateid) {
		String hql="select new com.jixu.mvc.control.model.MarketFxRateModel(id, sourceID.sourceID,sourceID.sourceDesc,currID,baseCurrID,rateDate,rate) "
					+ " from FxRate where id=:rateid";
		String[] params={"rateid"};
		Object[] values={rateid};
		List<MarketFxRateModel> rateList=this.executeHQL(hql, params, values);
		if(rateList.size()>0){
			return rateList.get(0);
		}else{
			return null;
		}
	}
	
	@Override
	public double loadFxrate(String baseCurrency, String localCurrency, Date date, int sourceID) {
		String sql="select rate from JXM_FX_RATE where baseCurrID=:baseCurrency and CURR_ID=:localCurrency and RATE_DATE<=:date and (sourceID=:sourceID or sourceID=:mannualSource) order by RATE_DATE desc, sourceID desc";
		String[] params = {"baseCurrency","localCurrency", "date", "sourceID", "mannualSource"};
		Object[] values = { baseCurrency,localCurrency, date, sourceID, JixuDictCode.INTERFACE_SOURCE_MANNUAL};
		Object obj=this.executeSQLNo1Record(sql, params, values, null);
		if(obj!=null)
			return Double.valueOf(obj.toString());
		else
			return 0;
	}
}
