package com.joyintech.tams.invest.common.risktrial.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joyintech.tams.invest.common.risktrial.dao.SupervisionCalcDao;
import com.joyintech.tams.invest.common.risktrial.dto.CellEntry;
import com.joyintech.tams.invest.common.risktrial.dto.IsupLogDto;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.common.risktrial.dto.SystemParam;
import com.joyintech.tams.invest.common.risktrial.util.RiskUtil;

@Service
public class SupervisionCalcSvcWithMix {

	@Autowired
	private SupervisionCalcDao supervisionCalcDao;
	
	public Object getIndicatorParamValue(String sql, SystemParam sysParam,String detailJson) throws Exception{
		String sqlNew = sysParam.replaceForString(sql);
		List<Map<String, Object>> resultSet = supervisionCalcDao.selectOne(sqlNew);
		if(resultSet.size() > 0) {
			Map<String, Object> row0 = resultSet.get(0);
			if(row0==null){
				return null;
			}
			if(resultSet.size()>1){//针对多条记录下的
				ObjectMapper mapper = new ObjectMapper();
				Map<?, ?> detailMap = mapper.readValue(detailJson, Map.class);
				String type = (String)detailMap.get("type");
				List<CellEntry> sortList=new ArrayList<CellEntry>();
				if(type.indexOf(RiskUtil.LIMIT)!=-1){
					for(Map<String, Object> mapRes:resultSet){
						CellEntry comObj=getFirstOrNull(mapRes);
						if(comObj!=null){
							sortList.add(comObj);
						}
					}
					if(sortList.size()>0){
						return sortList;
					}
				}
				
			}
			Object retObj=getFirstOrNull(row0);
			return retObj;
		} else {
			return 0;//----update---裴家俊--------2018/01/29------没有记录返回0---------
		}
	}
   public void writeLog(IsupLogDto dto){
	   supervisionCalcDao.writeLog(dto);
   }
	
/*	public SupervisionParameterDto getIndicatorParam(String code, SupervisionGranularity granularity) {
		return supervisionCalcDao.getIndicatorParam(code, granularity.name());
	}*/
	
	public String getIndicatorParamType(String code) {
		return supervisionCalcDao.getIndicatorParamType(code);
	}
	
	public void addPointSupervisionValue(RiskTrialRetDto value) {
		supervisionCalcDao.addPointSupervisionValue(value);
	}
	
	public static CellEntry getFirstOrNull(Map<String, Object> map){
		int keycount=0;
		CellEntry cellEntry=new CellEntry();
		for(Entry<String,Object> entry:map.entrySet()){
			keycount++;
			if(entry.getValue()==null){
				cellEntry=null;
				break;
			}
			else{
				if(keycount==1){
					cellEntry.setUnique(entry.getValue().toString());
				}
				if(keycount==2){
					cellEntry.setValue(Double.parseDouble(entry.getValue().toString()));
				}
			}
		}
		return cellEntry;
	}
}
