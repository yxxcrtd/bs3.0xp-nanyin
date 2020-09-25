package com.jixu.data.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jixu.common.func.SystemSelect;
import com.jixu.dao.intf.NavDao;
import com.jixu.data.pojo.PortNav;

/**
 * @author Pan Wei
 * @Date 2017年10月30日 下午2:08:04
 * @Description []
 */

@Component("JixuTbPortNav")
public class TbPortNav extends OutDataOperation {
	
	@Autowired
	private NavDao navDao;
	
	@Override
	public List<?> genResults(String portNum, Date closeDate) {
		StringBuffer sb = new StringBuffer();
		List<PortNav> spList=new ArrayList<PortNav>();
		if(SystemSelect.getSystemDBFrameWork()==1){
			spList=navDao.genResults(portNum, closeDate);
		}else{
			sb.append("select ");
			sb.append(" portfolio as portnum, ");
			sb.append(" navdate as navDate, ");
			sb.append(" sum(assets) as asset, ");
			sb.append(" sum(capital) as capital, ");
			sb.append(" sum(liabilities) as liabilities, ");
			sb.append(" sum(CAPITALUNREAL) as capitalUnreal, ");
			sb.append(" sum(CAPITALRG) as capitalRG, ");
			sb.append(" sum(EXPENSES) as expenses, ");
			sb.append(" sum(INCOME) as income, ");
			sb.append(" sum(SHARESOUTSTANDING) as sharesOutStanding, ");
			sb.append(" sum(SHARESSETTLED) as sharesSettled, ");
			sb.append(" sum(NAVAMOUNT) as navAmount ");
			sb.append(" from JXV_nav where portfolio=:portNum and navdate=:navDate");
			sb.append(" group by portfolio,navdate");
			String[] params = { "portNum", "navDate" };
			Object[] values = { portNum, closeDate };

			spList = navDao.executeSQLReturnBean(sb.toString(), params, values, PortNav.class);
		}
		return spList;
	}
}
