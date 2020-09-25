package com.jixu.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.types.JixuDictCode;
import com.jixu.dao.intf.ProvinceDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.Province;

/**
 * @ClassName 		ProvinceDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("provinceDao")
public class ProvinceDaoImpl extends CommFuncDAOImpl implements ProvinceDao{
	
	@Override
	public List<Province> getProvinceist() {
		if(JixuUserUtil.isJoyinTablesFlag()) {
			return defaultProvinces();
		}else {
			String hql="from Province order by proID asc";
			List<Province> provinceList=this.executeHQL(hql);
			return provinceList;
		}
	}
	
	private List<Province> defaultProvinces(){
		List<Province> provinces=new ArrayList<Province>();
		Province pv=new Province();
		pv.setProID(JixuDictCode.DEFAULT_PROVINCE_ID);
		pv.setCountry(JixuDictCode.DEFAULT_COUNTRY_CODE);
		pv.setProName("默认省份");
		provinces.add(pv);
		return provinces;
	}

}
