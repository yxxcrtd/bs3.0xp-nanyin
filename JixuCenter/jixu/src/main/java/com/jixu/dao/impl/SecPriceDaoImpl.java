package com.jixu.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.jixu.common.type.InterfaceSourceType;
import com.jixu.common.types.JixuDictCode;
import com.jixu.common.util.Pages;
import com.jixu.dao.intf.SecPriceDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.jixu.mvc.control.model.MarketPriceModel;
import com.orm.obj.SecPrice;

/**
 * @ClassName 		SecPriceDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("secPriceDao")
public class SecPriceDaoImpl extends CommFuncDAOImpl implements SecPriceDao{
	@Override
	public List<String> getCusipByPriceDate(List<String> cusipList, Date asOfDate) {
		StringBuilder strBuilder=new StringBuilder();
		strBuilder.append("select cusip.id from SecPrice where price_date=:asOfDate");
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		paramsList.add("asOfDate");
		valuesList.add(asOfDate);
		
		if(cusipList.size()>0){
			strBuilder.append(" and (");
			for(int i=0;i<cusipList.size();i++){
				strBuilder.append(" cusip.id=:cusip"+i);
				paramsList.add("cusip"+i);
				valuesList.add(cusipList.get(i));
				if(i<cusipList.size()-1){
					strBuilder.append(" or ");
				}
			}
			strBuilder.append(")");
		}
		String[] params=paramsList.toArray(new String[paramsList.size()]);
		Object[] values=valuesList.toArray();
		
		List<String> secList=this.executeHQL(strBuilder.toString(), params, values);
		return secList;
	}
	
	@Override
	public SecPrice loadSecPriceByParams(SecPrice secprice){
		String cusip=secprice.getCusip().getId();
		Date pricedate=secprice.getPrice_date();
		int sourceid=secprice.getSourceID().getSourceID();
		String hql="from SecPrice where cusip.id=:cusip and price_date=:pricedate and sourceID.sourceID=:sourceid";
		String[] params={"cusip","pricedate","sourceid"};
		Object values[]={cusip,pricedate,sourceid};
		List<SecPrice> priceList=this.executeHQL(hql, params, values);
		if(priceList.size()>0){
			return priceList.get(0);
		}else{
			return null;
		}
	}
	
	@Override
	public Pages<MarketPriceModel> loadMarketPriceModelList(Pages<MarketPriceModel> pages, int[] sources, String secid, Date startDate, Date endDate) {
		//"数据编号", "数据来源", "日期", "证券代码", "证券名称", "货币", "价格"
		
		StringBuilder strBuilder=new StringBuilder();
		List<String> params=new ArrayList<String>();
		List<Object> values=new ArrayList<Object>();
		//int priceid,int sourceid,String sourcename,String secid,String secname,String currency,double price, String priceDate,int priceto, double yield
		String hql="select new com.jixu.mvc.control.model.MarketPriceModel(sp.id, sp.sourceID.sourceID,sp.sourceID.sourceDesc, sp.cusip.securityid, sp.cusip.securityname,"
				+" sp.cusip.priCur,sp.price,sp.price_date,sp.priceto,sp.yield) from SecPrice sp ";
		strBuilder.append(hql);
		strBuilder.append(" where 1=1 ");
		
		if(sources.length>0){
			strBuilder.append(" and (");
			for(int i=0;i<sources.length;i++){
				int source=sources[i];
				strBuilder.append(" sp.sourceID.sourceID=:sourceid"+i);
				params.add("sourceid"+i);
				values.add(source);
				if(i<sources.length-1){
					strBuilder.append(" or ");
				}
			}
			strBuilder.append(")");
		}
		
		
		if(!secid.equals("")){
			strBuilder.append(" and sp.cusip.securityid=:secid");
			params.add("secid");
			values.add(secid);
		}
		if(startDate!=null){
			strBuilder.append(" and sp.price_date>=:startDate");
			params.add("startDate");
			values.add(startDate);
		}
		if(endDate!=null){
			strBuilder.append(" and sp.price_date<=:endDate");
			params.add("endDate");
			values.add(endDate);
		}
		strBuilder.append(" order by sp.price_date desc, id desc");
		
		String[] paramsArr=params.toArray(new String[params.size()]);
		Object[] valuesArr=values.toArray();
		
		return this.getPageHqlData(strBuilder.toString(), paramsArr, valuesArr, false, pages);
	}
	
	@Override
	public Page<MarketPriceModel> loadPageHelperMarketPriceModelList(Page<MarketPriceModel> page,int[] sources,String secid, Date startDate,Date endDate){
		return page;
	}
	
	@Override
	public MarketPriceModel loadMarketPriceDetail(int priceid) {
		String hql="select new com.jixu.mvc.control.model.MarketPriceModel(sp.id, sp.sourceID.sourceID,sp.sourceID.sourceDesc, sp.cusip.securityid, sp.cusip.securityname,"
				+" sp.cusip.priCur,sp.price,sp.price_date,sp.priceto,sp.yield) from SecPrice sp where sp.id=:priceid ";
		String[] params={"priceid"};
		Object[] values={priceid};
		List<MarketPriceModel> priceList=this.executeHQL(hql, params, values);
		if(priceList.size()>0){
			return priceList.get(0);
		}else{
			return null;
		}
	}
	
	public Date getMaxPriceDate(){
		String hql="select max(price_date) from SecPrice";
		List<Date> priceList=this.executeHQL(hql);
		return priceList.get(0);
	}
	
	@Override
	public double loadCusipPrice(String cusip, Date priceDate, int sourceID) {
		String sql="select sp.price from JXM_sec_price sp where sp.cusip=:cusip and sp.price_date<=:priceDate and (sp.sourceID=:sourceID or sp.sourceID=:mannualSource) order by sp.price_date desc, sp.sourceID desc";
		String[] params={"cusip", "priceDate", "sourceID","mannualSource"};
		Object[] values = {cusip, priceDate, sourceID, JixuDictCode.INTERFACE_SOURCE_MANNUAL};
		Object obj=this.executeSQLNo1Record(sql, params, values, null);
		if(obj!=null)
			return Double.valueOf(obj.toString());
		else
			return 0;
	}

	@Override
	public SecPrice loadSecPrice(String securityID, Date ledgerDate) {
		String hql="select sp from SecPrice sp,IssueInfo ii where ii.securityid=:securityID and ii.id=sp.cusip.id and sp.price_date=:ledgerDate";
		String[] params={"securityID","ledgerDate"};
		Object[] values= {securityID,ledgerDate};
		return (SecPrice) this.executeHQLNo1Record(hql, params, values);
	}
	
	@Override
	public double loadCusipYield(String portnum, String cusip, Date priceDate) {
		String sql="select sp.* " +
				" from JXS_PortfolioInterfaceMap pim, adpt_interfacesource iis, JXS_ISSUE_INFO ii, JXM_sec_price sp " +
				" where pim.interfacesourceid=iis.sourceid and ii.rpsectype=pim.rpsectype and pim.portfolio=:portnum  " +
				" and iis.sourcetype=:sourcetype and ii.cusip=:cusip and sp.cusip=ii.cusip and sp.sourceid=iis.sourceid and sp.price_date<=:priceDate  " +
				" order by sp.price_date desc";
		String[] params={"portnum", "sourcetype", "cusip","priceDate"};
		Object[] values = {portnum, InterfaceSourceType.price, cusip, priceDate };
		Object obj=this.executeSQLNo1Record(sql,params, values, SecPrice.class);
		if(obj!=null)
			return ((SecPrice)obj).getYield();
		else
			return 0;
	}
}
