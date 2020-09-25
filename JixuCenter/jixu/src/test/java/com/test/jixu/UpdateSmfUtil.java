package com.test.jixu;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jixu.dao.intf.CashFlowCfgDao;
import com.jixu.mvc.control.service.ConfigService;
import com.orm.obj.CashFlowCfg;

public class UpdateSmfUtil extends BaseTest{
	
	@Autowired
	private CashFlowCfgDao cashFlowCfgDao;
	
	@Resource(name="configService")
	private ConfigService configService;
	
	//批量修改现金流利率
	@Test
	public void updateSMF() {
		String securityName="";
		double rate=0;
		String filePath="C:\\CE\\QH\\债券利息.csv";
		try {
			DataInputStream in=new DataInputStream(new FileInputStream(filePath));
			BufferedReader br=new BufferedReader(new InputStreamReader(in,"utf-8"));
			String line=null;
			while((line=br.readLine())!=null){
				String item[]=line.split(",");
				securityName=item[0];
				rate=Double.valueOf(item[1]);
				CashFlowCfg cfc=cashFlowCfgDao.loadCashFlowCfgByName(securityName);
				if(cfc!=null){
					cfc.setCoupon(rate);
					configService.updateCashFlowCfg(cfc);
					logger.info(String.valueOf(cfc.getCashFlowID()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
