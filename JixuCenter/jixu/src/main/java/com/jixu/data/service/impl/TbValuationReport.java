package com.jixu.data.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jixu.common.func.GLACCT;
import com.jixu.common.type.AssetTypes;
import com.jixu.common.type.NetCategory;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.RoundUtil;
import com.jixu.dao.intf.GlCoaaMapDao;
import com.jixu.data.pojo.Balance;
import com.jixu.data.pojo.PortNav;
import com.jixu.data.pojo.SecPosition;
import com.jixu.data.service.OutDataInterface;
import com.jixu.mvc.control.service.ConfigService;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.ValuationReport;

/** 
 * @ClassName 		TbValuationReport
 * @author  		Pan Wei 
 * @Date 			2018年1月18日 上午9:44:03 	
 * @Description		[]  
 */

@Component("JixuTbValuationReport")
public class TbValuationReport extends OutDataOperation{
	
	@Resource(name="JixuTbSecPosition")
	private OutDataInterface secPosition;
	
	@Resource(name="JixuTbBalance")
	private OutDataInterface balance;
	
	@Resource(name="JixuTbPortNav")
	private OutDataInterface navInter;
	
	@Autowired
	private GlCoaaMapDao glCoaaMapDao;
	
	@Resource(name="configService")
	private ConfigService configService;
	
	private static final Logger logger = LoggerFactory.getLogger(TbValuationReport.class);
	
	@Override
	public List<?> genResults(String portNum, Date closeDate) {
		List<ValuationReport> valList=new ArrayList<ValuationReport>();
		
		List<Balance> balList=(List<Balance>)balance.genResults(portNum, closeDate);
		List<PortNav> navList=(List<PortNav>)navInter.genResults(portNum, closeDate);
		
		if(navList.size()>0){
			PortNav nav=navList.get(0);
			PortfolioHdr portfolio=comService.loadPortfolio(portNum);
			HashMap<String, String> curIDCodeHM=configService.getCurrencyList();
			HashMap<String, String> curHM=new HashMap<String, String>();
			
			//key,value调换一下
			for (Map.Entry<String, String> entry : curIDCodeHM.entrySet()) {
				curHM.put(entry.getValue(), entry.getKey());
			}
			
			
			double netAsset=RoundUtil.roundMoney2Dec(nav.getAsset()-nav.getLiabilities());
			//损益平准金-已实现
			double sypzjysx=0;
			//损益平准金-未实现
			double sypzjwsx=0;
			
			double cash=0;
			
			String lastAccntNum="";
			for(Balance bal:balList){
				if(bal.getAccntNum().startsWith("1") || bal.getAccntNum().startsWith("2") || bal.getAccntNum().startsWith("3")){
					ValuationReport vr=new ValuationReport();
					vr.setAccntCategory(bal.getAccntCategory());
					vr.setAccntLevel(bal.getAccntLevel());
					vr.setAccntIsLeaf(bal.getAccntIsLeaf());
					vr.setAccntNum(bal.getAccntNum());
					vr.setAccntName(bal.getAccntName());
					vr.setAmount(0);
					if(bal.getAccntNum().startsWith("2")){
						vr.setCostLocal(RoundUtil.roundMoney2Dec(bal.getCrLocal()-bal.getDrLocal()));
						vr.setCostFunc(RoundUtil.roundMoney2Dec(bal.getCrFunc()-bal.getDrFunc()));
					}else{
						if(bal.getAccntNum().startsWith("11")){
							vr.setCostLocal(RoundUtil.roundMoney2Dec(bal.getDrLocal()));
							vr.setCostFunc(RoundUtil.roundMoney2Dec(bal.getDrFunc()));
						}else{
							vr.setCostLocal(RoundUtil.roundMoney2Dec(bal.getDrLocal()-bal.getCrLocal()));
							vr.setCostFunc(RoundUtil.roundMoney2Dec(bal.getDrFunc()-bal.getCrFunc()));
						}
					}
					vr.setCostPerUnitLocal(0);
					vr.setCostPerUnitFunc(0);
					vr.setCostPercent(RoundUtil.roundMoney(vr.getCostFunc()/netAsset,4));
					
					String curID="";
					if(bal.getLocalCur()!=null && !bal.getLocalCur().equals("") && !bal.getLocalCur().equals("0")){
						curID=curHM.get(bal.getLocalCur());
					}
					
					double fxrate=comService.loadFxrate(portNum, portfolio.getCurID(), curID, closeDate);
					vr.setCurrency(curID);
					vr.setCurrencyCode(bal.getLocalCur());
					vr.setFxRate(fxrate);
					
					vr.setGainLossFunc(0);
					vr.setGainLossLocal(0);
					vr.setMarketValueFunc(RoundUtil.roundMoney2Dec(bal.getDrFunc()-bal.getCrFunc()));
					vr.setMarketValueLocal(RoundUtil.roundMoney2Dec(bal.getDrLocal()-bal.getCrLocal()));
					vr.setMarketValuePercent(RoundUtil.roundMoney(vr.getMarketValueFunc()/netAsset,4));
					
					//设置上一级科目
					if(vr.getAccntNum().contains(lastAccntNum) && !vr.getAccntNum().equals(lastAccntNum)){
						vr.setLastLevelAccntNum(lastAccntNum);
					}
					
					if(vr.getAccntIsLeaf()==0){
						lastAccntNum=vr.getAccntNum();
					}
					
					valList.add(vr);
				}
				if(bal.getAccntNum().equals("1002")){
					cash=RoundUtil.roundMoney2Dec(bal.getDrFunc()-bal.getCrFunc());
				}else if(bal.getAccntNum().equals("401101")){
					sypzjysx=RoundUtil.roundMoney2Dec(bal.getDrFunc()-bal.getCrFunc());
				}else if(bal.getAccntNum().equals("401102")){
					sypzjwsx=RoundUtil.roundMoney2Dec(bal.getDrFunc()-bal.getCrFunc());
				}
				
			}
			
			//证券方面的头村
			List<ValuationReport> secValList=new ArrayList<ValuationReport>();
			List<SecPosition> spList=(List<SecPosition>)secPosition.genResults(portNum, closeDate);
			
			HashMap<String, Double> assetHM=new HashMap<String, Double>();
			
			for(SecPosition sp:spList){
				ValuationReport secVal=new ValuationReport();
				IssueInfo sec=issueInfoDao.loadIssueByCusip(sp.getSecMasterID());
				if(sec.getIssueType().equals(AssetTypes.BankZiGuan))
					continue;
				
				String accntnum=glCoaaMapDao.loadGLAcctnum(portfolio.getCoaahdr(), sec.getCoaaGroup(), GLACCT.BOOK_VALUE);
				if(accntnum==null){
					logger.error("总帐: " + portfolio.getCoaahdr() + " 总帐组: " + sec.getCoaaGroup() + " 总账栏: " + GLACCT.BOOK_VALUE);
					return null;
				}
				accntnum=accntnum.substring(0, accntnum.length()-2);
				
				//分级基金的情况下，需要标明每个头寸属于哪个投资组合
				PortfolioHdr innerPort=comService.loadPortfolio(sp.getPortnum());
				secVal.setPortnum(sp.getPortnum());
				secVal.setPortName(innerPort.getPortName());
				secVal.setPortCode(innerPort.getShortName());
				secVal.setAccntCategory(NetCategory.Assets);
				secVal.setAccntLevel(0);
				secVal.setAccntIsLeaf(1);
				secVal.setAccntNum(accntnum);
				secVal.setAccntName(sp.getSecurityname());
				secVal.setAmount(sp.getAmount());
				secVal.setAsOfDate(closeDate);
				secVal.setCostLocal(sp.getCost());
				secVal.setCostFunc(sp.getFuncCost());
				secVal.setCostPerUnitLocal(RoundUtil.roundMoney2Dec(sp.getCost()/sp.getAmount()));
				secVal.setCostPerUnitFunc(RoundUtil.roundMoney2Dec(sp.getFuncCost()/sp.getAmount()));
				secVal.setCostPercent(RoundUtil.roundMoney(sp.getFuncCost()/netAsset,4));
				
				String curID=sp.getFuncCurID();
				
				double fxrate=comService.loadFxrate(portNum, portfolio.getCurID(), curID, closeDate);
				secVal.setCurrency(curID);
				secVal.setCurrencyCode(curIDCodeHM.get(sec.getPriCur()));
				secVal.setFxRate(fxrate);
				
				secVal.setSecMasterID(sp.getSecMasterID());
				secVal.setSecurityID(sp.getSecurityid());
				secVal.setGainLossFunc(RoundUtil.roundMoney2Dec(sp.getFunMkValue()-sp.getFuncCost()));
				secVal.setGainLossLocal(RoundUtil.roundMoney2Dec(sp.getMkValue()-sp.getCost()));
				secVal.setMarketValueFunc(sp.getFunMkValue());
				secVal.setMarketValueLocal(sp.getMkValue());
				secVal.setMarketValuePercent(RoundUtil.roundMoney(sp.getFunMkValue()/netAsset,4));
				
				if(secVal.getCostFunc()==0 && secVal.getMarketValueFunc()==0 && secVal.getAmount()==0){
					continue;
				}
				
				if(assetHM.containsKey(sec.getIssueType())){
					assetHM.put(sec.getIssueType(), assetHM.get(sec.getIssueType())+secVal.getMarketValueFunc());
				}else{
					assetHM.put(sec.getIssueType(), secVal.getMarketValueFunc());
				}
				
				secValList.add(secVal);
				
				if(sp.getAmortValue()!=sp.getCost()){
					ValuationReport secVal2=new ValuationReport();
					
					String accntnum2=glCoaaMapDao.loadGLAcctnum(portfolio.getCoaahdr(), sec.getCoaaGroup(), GLACCT.BookValue_Dis);
					if(accntnum2==null){
						logger.error("总帐: " + portfolio.getCoaahdr() + " 总帐组: " + sec.getCoaaGroup() + " 总账栏: " + GLACCT.BookValue_Dis);
						return null;
					}
					accntnum2=accntnum2.substring(0, accntnum2.length()-2);
					//分级基金的情况下，需要标明每个头寸属于哪个投资组合
					secVal2.setPortnum(sp.getPortnum());
					secVal2.setPortName(innerPort.getPortName());
					secVal2.setPortCode(innerPort.getShortName());
					secVal2.setAccntCategory(NetCategory.Assets);
					secVal2.setAccntLevel(0);
					secVal2.setAccntIsLeaf(1);
					secVal2.setAccntNum(accntnum2);
					secVal2.setAccntName(sp.getSecurityid()+"-折溢价");
					secVal2.setAmount(sp.getAmount());
					secVal2.setAsOfDate(closeDate);
					secVal2.setCostLocal(RoundUtil.roundMoney2Dec(sp.getAmortValue()-sp.getCost()));
					secVal2.setCostFunc(RoundUtil.roundMoney2Dec(secVal2.getCostLocal()*fxrate));
					secVal2.setCostPerUnitLocal(0);
					secVal2.setCostPerUnitFunc(0);
					secVal2.setCostPercent(RoundUtil.roundMoney(sp.getFuncCost()/netAsset,4));
					
					secVal2.setCurrency(curID);
					secVal2.setCurrencyCode(curIDCodeHM.get(sec.getPriCur()));
					secVal2.setFxRate(fxrate);
					
					secVal2.setSecMasterID(sp.getSecMasterID());
					secVal2.setSecurityID(sp.getSecurityid());
					secVal2.setGainLossFunc(0);
					secVal2.setGainLossLocal(0);
					secVal2.setMarketValueFunc(0);
					secVal2.setMarketValueLocal(0);
					secVal2.setMarketValuePercent(RoundUtil.roundMoney(sp.getFuncCost()/netAsset,4));
					
					if(secVal.getCostFunc()==0 && secVal.getMarketValueFunc()==0 && secVal.getAmount()==0){
						continue;
					}
					
					if(assetHM.containsKey(sec.getIssueType())){
						assetHM.put(sec.getIssueType(), RoundUtil.roundMoney2Dec(assetHM.get(sec.getIssueType())+secVal.getCostFunc()+secVal2.getCostFunc()));
					}else{
						assetHM.put(sec.getIssueType(), RoundUtil.roundMoney2Dec(secVal.getCostFunc()+secVal2.getCostFunc()));
					}
					
					secValList.add(secVal2);
				}
				
				if(sp.getAccrued()!=0){
					ValuationReport secVal3=new ValuationReport();
					
					String accntnum3=glCoaaMapDao.loadGLAcctnum(portfolio.getCoaahdr(), sec.getCoaaGroup(), GLACCT.ACCRUED_INC);
					if(accntnum3==null){
						logger.error("总帐: " + portfolio.getCoaahdr() + " 总帐组: " + sec.getCoaaGroup() + " 总账栏: " + GLACCT.ACCRUED_INC);
						return null;
					}
					
					//分级基金的情况下，需要标明每个头寸属于哪个投资组合
					secVal3.setPortnum(sp.getPortnum());
					secVal3.setPortName(innerPort.getPortName());
					secVal3.setPortCode(innerPort.getShortName());
					secVal3.setAccntCategory(NetCategory.Assets);
					secVal3.setAccntLevel(0);
					secVal3.setAccntIsLeaf(1);
					secVal3.setAccntNum(accntnum3);
					secVal3.setAccntName("应收利息"+"-"+sec.getSecurityid());
					secVal3.setAmount(sp.getAmount());
					secVal3.setAsOfDate(closeDate);
					secVal3.setCostLocal(RoundUtil.roundMoney2Dec(sp.getAccrued()));
					secVal3.setCostFunc(RoundUtil.roundMoney2Dec(secVal3.getCostLocal()*fxrate));
					secVal3.setCostPerUnitLocal(0);
					secVal3.setCostPerUnitFunc(0);
					secVal3.setCostPercent(RoundUtil.roundMoney(sp.getFuncCost()/netAsset,4));
					
					secVal3.setCurrency(curID);
					secVal3.setCurrencyCode(curIDCodeHM.get(sec.getPriCur()));
					secVal3.setFxRate(fxrate);
					
					secVal3.setSecMasterID(sp.getSecMasterID());
					secVal3.setSecurityID(sp.getSecurityid());
					secVal3.setGainLossFunc(0);
					secVal3.setGainLossLocal(0);
					secVal3.setMarketValueFunc(secVal3.getCostFunc());
					secVal3.setMarketValueLocal(secVal3.getCostLocal());
					secVal3.setMarketValuePercent(RoundUtil.roundMoney(sp.getFuncCost()/netAsset,4));
					
					if(secVal.getCostFunc()==0 && secVal.getMarketValueFunc()==0 && secVal.getAmount()==0){
						continue;
					}
					secValList.add(secVal3);
				}
			}
			
			List<String> ingroreAcct=new ArrayList<String>();
			for(int i=0;i<valList.size();i++){
				ValuationReport vr=valList.get(i);
				for(ValuationReport vr2:secValList){
					//证券的科目号是余额表科目号的子科目
					if(vr2.getAccntNum().equals(vr.getAccntNum())){
						vr2.setLastLevelAccntNum(vr.getAccntNum());
						vr2.setAccntLevel(vr.getAccntLevel()+1);
						if(vr2.getAccntName().contains("折溢价")){
							vr2.setAccntNum("");
						}else{
							vr2.setAccntNum(vr.getAccntNum()+"-"+vr2.getSecurityID());	
						}
						valList.add(i+1, vr2);
						//消除估增科目
						i=i+1;
						if(!ingroreAcct.contains(vr.getAccntNum())){
							ingroreAcct.add(vr.getAccntNum());
						}
					}
				}
				
				for(String ignoreacct:ingroreAcct){
					if(!vr.getAccntNum().equals(ignoreacct) && vr.getAccntNum().startsWith(ignoreacct)){
						valList.remove(i);
						i=i-1;
						break;
					}
				}
			}
			
			assetHM.forEach((k,v)->{
				ValuationReport vrtmp=new ValuationReport();
				vrtmp.setAccntNum(AssetTypes.getPortAsset(false).get(k));
				vrtmp.setAmount(RoundUtil.roundMoney2Dec(v));
				valList.add(vrtmp);
			});
			
			/*ValuationReport vr1=new ValuationReport();
			vr1.setAccntNum("期初单位净值");
			valList.add(vr1);*/
			
			double todaynav=nav.getNavAmount();
			ValuationReport vr2=new ValuationReport();
			vr2.setAccntNum("今日单位净值");
			vr2.setAmount(todaynav);
			valList.add(vr2);
			
			List<PortNav> lastnavList=(List<PortNav>)navInter.genResults(portNum, DateUtil.getLastSqlDay(closeDate));
			
			double yesterdayNave=0;
			ValuationReport vr3=new ValuationReport();
			vr3.setAccntNum("昨日单位净值");
			if(lastnavList.size()>0){
				yesterdayNave=lastnavList.get(0).getNavAmount();
			}
			vr3.setAmount(yesterdayNave);
			valList.add(vr3);
			
			
			ValuationReport vr4=new ValuationReport();
			vr4.setAccntNum("日净值增长率");
			if(yesterdayNave!=0){
				vr4.setAmount(RoundUtil.roundMoney((todaynav-yesterdayNave)/yesterdayNave,4));
			}else{
				vr4.setAmount(0);
			}
			valList.add(vr4);
			
			double kfpsy=RoundUtil.roundMoney2Dec(nav.getCapital()+nav.getIncome()-nav.getExpenses());
			ValuationReport vr7=new ValuationReport();
			vr7.setAccntNum("可分配收益");
			vr7.setAmount(kfpsy);
			valList.add(vr7);
			
			ValuationReport vr8=new ValuationReport();
			vr8.setAccntNum("单位可分配收益");
			vr8.setAmount(RoundUtil.roundMoney(kfpsy/nav.getCapital(),4));
			valList.add(vr8);
			
			ValuationReport vr9=new ValuationReport();
			vr9.setAccntNum("现金类合计");
			vr9.setAmount(cash);
			valList.add(vr9);
			
			ValuationReport vr10=new ValuationReport();
			vr10.setAccntNum("实收资本");
			vr10.setAmount(nav.getCapital());
			valList.add(vr10);
			
			ValuationReport vr11=new ValuationReport();
			vr11.setAccntNum("损益平准金未实现");
			vr11.setAmount(sypzjysx);
			valList.add(vr11);
			
			ValuationReport vr12=new ValuationReport();
			vr12.setAccntNum("损益平准金已实现");
			vr12.setAmount(sypzjwsx);
			valList.add(vr12);
			
			ValuationReport vr13=new ValuationReport();
			vr13.setAccntNum("资产类合计");
			vr13.setAmount(nav.getAsset());
			valList.add(vr13);
			
			ValuationReport vr14=new ValuationReport();
			vr14.setAccntNum("负债类合计");
			vr14.setAmount(nav.getLiabilities());
			valList.add(vr14);
			
			ValuationReport vr15=new ValuationReport();
			vr15.setAccntNum("资产净值");
			vr15.setAmount(RoundUtil.roundMoney2Dec(nav.getAsset()-nav.getLiabilities()));
			valList.add(vr15);
			
			
			for(ValuationReport vr:valList){
				if(vr.getPortnum().equals("")){
					vr.setPortName(portfolio.getPortName());
					vr.setPortCode(portfolio.getShortCode());
				}
				vr.setPortnum(portfolio.getId());
				vr.setAsOfDate(closeDate);
			}
		}
		return valList;
	}
}
