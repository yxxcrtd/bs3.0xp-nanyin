package com.test.jixu;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;

import com.jixu.common.util.DateUtil;
import com.jixu.data.service.OutDataInterface;
import com.jixu.data.service.OutPublicDataInterface;

/** 
 * @ClassName 		TestSynData
 * @author  		Pan Wei 
 * @Date 			2017年12月18日 下午2:51:53 	
 * @Description		[]  
 * @return  
 */

public class TestSynData extends BaseTest{
	
	@Resource(name="JixuTbPortfolio")
	private OutDataInterface portfolio;
	
	@Resource(name="JixuTbBalance")
	private OutDataInterface operBal;
	
	@Resource(name="JixuTbValuationReport")
	private OutDataInterface valuation;
	
	@Resource(name="JixuTbCoaa")
	private OutDataInterface coaa;
	
	@Resource(name="JixuTbTran")
	private OutDataInterface tran;
	
	@Resource(name="JixuTbVoucher")
	private OutDataInterface voucher;
	
	@Resource(name="JixuTbPortNav")
	private OutDataInterface nav;
	
	@Resource(name="TbPubExchanges")
	private OutPublicDataInterface exchanges;
	
	@Resource(name="JixuTbPubCountry")
	private OutPublicDataInterface country;
	
	@Resource(name="JixuTbPubHoliday")
	private OutPublicDataInterface holiday;
	
	@Resource(name="JixuTbPubIssuer")
	private OutPublicDataInterface issuer;
	
	@Resource(name="JixuTbPubSecurity")
	private OutPublicDataInterface security;
	
	
	@Test
	public void testSynPortfolio() {
		Date date=DateUtil.convertStr2Date("20170315");
		portfolio.sendResults("18",DateUtil.ConvertUtil2Sql(date));
	}
	
	@Test
	public void testCoaa() {
		Date date=DateUtil.convertStr2Date("20180107");
		coaa.sendResults("23",DateUtil.ConvertUtil2Sql(date));
	}
	
	@Test
	public void testSendBalance(){
		Date startDate=DateUtil.convertStr2Date("20170228");
		Date endDate=DateUtil.convertStr2Date("20170315");
		
		while(!startDate.after(endDate)){
			operBal.sendResults("18", DateUtil.ConvertUtil2Sql(startDate));
			startDate=DateUtil.getNextDay(startDate);
		}
	}
	
	@Test
	public void testSendValuation(){
		Date startDate=DateUtil.convertStr2Date("20160111");
		Date endDate=DateUtil.convertStr2Date("20160111");
		
		while(!startDate.after(endDate)){
			valuation.sendResults("1", DateUtil.ConvertUtil2Sql(startDate));
			startDate=DateUtil.getNextDay(startDate);
		}
	}
	
	@Test
	public void testSendNav(){
		Date startDate=DateUtil.convertStr2Date("20170301");
		Date endDate=DateUtil.convertStr2Date("20170315");
		
		while(!startDate.after(endDate)){
			nav.sendResults("18", DateUtil.ConvertUtil2Sql(startDate));
			startDate=DateUtil.getNextDay(startDate);
		}
	}
	
	@Test
	public void testSendTran(){
		Date startDate=DateUtil.convertStr2Date("20170102");
		Date endDate=DateUtil.convertStr2Date("20170707");
		
		while(!startDate.after(endDate)){
			tran.sendResults("23", DateUtil.ConvertUtil2Sql(startDate));
			startDate=DateUtil.getNextDay(startDate);
		}
	}
	
	@Test
	public void testSendVoucher(){
		Date startDate=DateUtil.convertStr2Date("20170103");
		Date endDate=DateUtil.convertStr2Date("20170707");
		
		while(!startDate.after(endDate)){
			voucher.sendResults("18", DateUtil.ConvertUtil2Sql(startDate));
			startDate=DateUtil.getNextDay(startDate);
		}
	}
	
	@Test
	public void testSynSecurity() {
		security.sendResults();
	}
	
	@Test
	public void testCountry() {
		country.sendResults();
	}
	
	
	@Test
	public void testIssuer() {
		issuer.sendResults();
	}
	
	@Test
	public void testSendHoliday(){
		holiday.sendResults();
	}
	
	@Test
	public void testSecuritys(){
		security.sendResults();
	}
}
