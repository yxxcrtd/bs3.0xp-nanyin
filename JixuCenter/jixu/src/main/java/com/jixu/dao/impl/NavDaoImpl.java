package com.jixu.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.jixu.common.types.NavAssetObj;
import com.jixu.common.util.Pages;
import com.jixu.common.util.RoundUtil;
import com.jixu.dao.intf.NavDao;
import com.jixu.data.pojo.PortNav;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.Nav;

/**
 * @ClassName 		NavDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("navDao")
public class NavDaoImpl extends CommFuncDAOImpl implements NavDao{
	/**
	 * 查看关帐日当天，NAV是否已生成
	 * @param portnum
	 * @param cloDate
	 * @return
	 */
	@Override
	public boolean checkNavExist(String portnum, Date cloDate){
		String hql="from Nav where portfolio.id=:portnum and navDate=:cloDate";
		String[] params={"portnum","cloDate"};
		Object[] values={portnum,cloDate};
		List<Nav> navList=this.executeHQL(hql, params, values);
		if(navList.size()>0)
			return true;
		else
			return false;
	}
	
	@Override
	public void deleteNav(String portnum, Date navDate){
		String hql="delete from Nav where portfolio.id=:portnum and navDate>=:navDate";
		String[] params={"portnum","navDate"};
		Object[] values={portnum,navDate};
		this.deleteHql(hql, params, values);
	}
	
	@Override
	public Nav loadNAVByDate(String portnum, Date navDate){
		String hql="from Nav where portfolio.id=:portnum and assets=0 and liabilities=0 and navDate=:navDate order by navDate desc";
		String[] params={"portnum","navDate"};
		Object[] values={portnum, navDate};
		List<Nav> navList=this.executeHQL(hql, params, values);
		if(navList.size()==0)
			return null;
		else 
			return navList.get(0);
	}
	
	@Override
	public Pages<NavAssetObj> getNavList(Pages<NavAssetObj> pages,String portnum, Date navDate){
		StringBuilder strBuilder=new StringBuilder();
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		
		strBuilder.append("from Nav where portfolio.id=:portnum and assets=0 and liabilities=0 ");
		paramsList.add("portnum");
		valuesList.add(portnum);
		
		if(navDate != null){
			strBuilder.append(" and navDate<=:navDate ");
			paramsList.add("navDate");
			valuesList.add(navDate);
		}
		
		strBuilder.append("  order by navDate desc");
		
		String[] params=paramsList.toArray(new String[paramsList.size()]);
		Object[] values=valuesList.toArray();
		
		Pages<Nav> Navpages= this.getPageHqlData(strBuilder.toString(), params, values, false, pages);
		
		List<Nav> navList=Navpages.getResultList();
		
		List<NavAssetObj> navObjList=new ArrayList<NavAssetObj>();
		for(Nav nav:navList){
			navObjList.add(genNavObj(nav));
		}
		pages.setResultList(navObjList);
		pages.setTotalPages(Navpages.getTotalPages());
		return pages;
	}
	
	@Override
	public Page<NavAssetObj> getPageHelperNavList(Page<NavAssetObj> page,String portnum, Date navDate){
		return page;
	}
	
	@Override
	public List<Nav> getNavDetails(String portnum, Date navDate){
		String hql="from Nav where portfolio.id=:portnum and navDate=:navDate order by navid asc";
		String[] params={"portnum","navDate"};
		Object[] values={portnum,navDate};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public Nav loadLatestNAV(String portnum, Date navDate) {
		String hql="from Nav where portfolio.id=:portnum and assets=0 and liabilities=0 and navDate<=:navDate order by navDate desc";
		String[] params={"portnum","navDate"};
		Object[] values={portnum, navDate};
		List<Nav> navList=this.executeHQL(hql, params, values);
		if(navList.size()==0)
			return null;
		else 
			return navList.get(0);
	}
	
	@Override
	public NavAssetObj getLatestNAV(String portnum, Date curDate){
		Nav nav=loadLatestNAV(portnum, curDate);
		return genNavObj(nav);
	}
	
	public NavAssetObj genNavObj(Nav nav){
		NavAssetObj navobj=new NavAssetObj();
		if(nav==null){
			navobj.setNetAsset(0);
			navobj.setShares(0);
			navobj.setNavPerShare(0);
			navobj.setNavDate(null);
		}else{
			double capital=nav.getCapital();			//资本
			double capitalRG=nav.getCapitalRG();		//已实现收益
			double caoitalUnReal=nav.getCapitalUnreal();//未实现收益
			double income=nav.getIncome();				//收入
			double expenses=nav.getExpenses();			//开支
			
			double netAsset=RoundUtil.roundMoney2Dec(capital+capitalRG-caoitalUnReal+income-expenses);	//净资产
			double shares=nav.getSharesOutStanding();							//份额
			double perNav=nav.getNavAmount();
			
			
			navobj.setNetAsset(netAsset);
			navobj.setShares(shares);
			navobj.setNavPerShare(perNav);
			navobj.setNavDate(nav.getNavDate());
			navobj.setExpense(expenses);
			navobj.setIncomes(income);
		//累计净值
		//分红
		}
		return navobj;
	}
	
	@Override
	public NavAssetObj getNav(String portnum, Date curDate){
		Nav nav=loadNav(portnum, curDate);
		return genNavObj(nav);
	}
	
	@Override
	public Nav loadNav(String portnum, Date navDate) {
		String hql="from Nav where portfolio.id=:portnum and assets=0 and liabilities=0 and navDate=:navDate order by navDate desc";
		String[] params={"portnum","navDate"};
		Object[] values={portnum, navDate};
		List<Nav> navList=this.executeHQL(hql, params, values);
		if(navList.size()==0)
			return null;
		else 
			return navList.get(0);
	}

	@Override
	public List<PortNav> genResults(String portNum, Date closeDate) {
		return null;
	}
}
