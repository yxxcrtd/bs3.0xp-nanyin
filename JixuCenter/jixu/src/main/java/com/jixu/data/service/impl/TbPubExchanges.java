package com.jixu.data.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jixu.data.pojo.Exchanges;
import com.orm.obj.ExchangeCfg;

/**
 * @author Pan Wei
 * @Date 2017年10月30日 下午2:08:04
 * @Description []
 */

@Component("TbPubExchanges")
public class TbPubExchanges extends OutPublicDataOperation {
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public Class<?> getReturnClass() {
		return ExchangeCfg.class;
	}
	
	@Override
	public List<?> genResults(Object obj) {
		ExchangeCfg eg=(ExchangeCfg) obj;
		List<Exchanges> spList =new ArrayList<Exchanges>();
		Exchanges exchanges=new Exchanges();
		exchanges.setExgCode(eg.getCode());
		exchanges.setCountryID(3);
		exchanges.setExgName(eg.getName());
		spList.add(exchanges);
		return spList;
	}
	
	@Override
	protected List<?> genResults() {
		// TODO Auto-generated method stub
		return null;
	}
}
