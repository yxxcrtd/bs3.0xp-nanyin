package com.jixu.data.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jixu.dao.intf.CoaaDao;
import com.jixu.data.pojo.Coaa;

/**
 * @author Pan Wei
 * @Date 2017年10月30日 下午2:08:04
 * @Description []
 */

@Component("JixuTbCoaa")
public class TbCoaa extends OutDataOperation {
	
	@Autowired
	private CoaaDao coaaDao;
	
	@Override
	public List<?> genResults(String portNum, Date closeDate) {
		StringBuffer sb = new StringBuffer();
		sb.append("select ");
		sb.append(" ID as coaaID, ");
		sb.append(" ACCNTCATEGORY as accntCateGory, ");
		sb.append(" ACCNTNAME as accntName, ");
		sb.append(" ACCNTNUM as accntNum, ");
		sb.append(" CHARTNUMBER as chartNumber, ");
		sb.append(" acctlevel as acctLevel, ");
		sb.append(" acctisleaf as acctIsLeaf ");
		sb.append(" from coaa ");
		String[] params = {};
		Object[] values = {};
		List<Coaa> spList = coaaDao.executeSQLReturnBean(sb.toString(), params, values, Coaa.class);
		return spList;
	}
}
