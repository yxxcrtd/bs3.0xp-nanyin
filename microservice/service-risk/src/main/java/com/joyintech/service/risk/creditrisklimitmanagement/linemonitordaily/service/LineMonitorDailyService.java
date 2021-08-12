package com.joyintech.service.risk.creditrisklimitmanagement.linemonitordaily.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.risk.creditrisklimitmanagement.linemonitordaily.dao.LineMonitorDailyMapper;
import com.joyintech.service.risk.creditrisklimitmanagement.linemonitordaily.dto.AbsLineMonitorDailyDto;
import com.joyintech.service.risk.creditrisklimitmanagement.linemonitordaily.dto.BusLineMonitorDailyDto;
import com.joyintech.service.risk.creditrisklimitmanagement.linemonitordaily.dto.EbCbLineMonitorDailyDto;
import com.joyintech.service.risk.creditrisklimitmanagement.linemonitordaily.dto.LineMonitorDailyDto;
import com.joyintech.service.risk.creditrisklimitmanagement.linemonitordaily.dto.ObjLineMonitorDailyDto;
import com.joyintech.service.risk.creditrisklimitmanagement.linemonitordaily.dto.TotalLineMonitorDailyDto;
import com.joyintech.service.risk.creditrisklimitmanagement.linemonitordaily.dto.TyLineMonitorDailyDto;

@Service
public class LineMonitorDailyService  extends BaseService<LineMonitorDailyDto> {
	
	@Autowired
	private LineMonitorDailyMapper lineMonitorDailyMapper;

	public Map<String,Object> abslineMonitorDaily(Date cdate){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		List<AbsLineMonitorDailyDto> list = lineMonitorDailyMapper.queryAbsLineMonitorDaily(cdate);
		resultMap.put("list", list);
		return resultMap;
	}
	
	public List<AbsLineMonitorDailyDto> queryAbsLineMonitorDailyList(Date cdate){
		return lineMonitorDailyMapper.queryAbsLineMonitorDaily(cdate);
	}
	
	public List<EbCbLineMonitorDailyDto> queryEbCbLineMonitorDailyList(Date cdate){
		return lineMonitorDailyMapper.queryEbCbLineMonitorDaily(cdate);
	}
	
	
	public List<ObjLineMonitorDailyDto> queryObjLineMonitorDailyList(Date cdate){
		return lineMonitorDailyMapper.queryObjLineMonitorDaily(cdate);
	}
	
	public List<BusLineMonitorDailyDto> queryBusLineMonitorDailyList(Date cdate){
		List<BusLineMonitorDailyDto> resultlist = new ArrayList<BusLineMonitorDailyDto>();
		
		List<BusLineMonitorDailyDto> list =  lineMonitorDailyMapper.queryBusLineMonitorDaily(cdate);
		
		BusLineMonitorDailyDto totalDto = new BusLineMonitorDailyDto();
		totalDto.setCreditTypeName("总额度");
		//核定额度
		BigDecimal creditAmt = BigDecimal.ZERO;
		//已用额度
		BigDecimal hapCreditAmt = BigDecimal.ZERO;
		//剩余额度
		BigDecimal remainderAmt = BigDecimal.ZERO;
		
		for(BusLineMonitorDailyDto dto : list){
			//TODO 去掉质押式回购
			creditAmt = creditAmt.add(dto.getCreditAmt());
			hapCreditAmt = hapCreditAmt.add(hapCreditAmt);
		}
		
		//计算剩余额度
		remainderAmt = creditAmt.subtract(hapCreditAmt);
		
		totalDto.setCreditAmt(creditAmt);
		totalDto.setHapCreditAmt(hapCreditAmt);
		totalDto.setRemainderAmt(remainderAmt);
		//调用一下计算比例
		totalDto.getRatio();
		
		if(!StringUtil.isEmptyList(list)){
			resultlist.add(totalDto);
			resultlist.addAll(list);
		}
		
		return resultlist;
	}
	
	public List<TotalLineMonitorDailyDto> queryTotalLineMonitorDailyList(Date cdate){
		List<TotalLineMonitorDailyDto> resultlist = new ArrayList<TotalLineMonitorDailyDto>();
		
		List<TotalLineMonitorDailyDto> list =  lineMonitorDailyMapper.queryTotalLineMonitorDaily(cdate);
		
		//同业总和
		TotalLineMonitorDailyDto totalTYDto = new TotalLineMonitorDailyDto();
		totalTYDto.setCreditTypeName("总额度");
		//核定额度
		BigDecimal creditTyAmt = BigDecimal.ZERO;
		//已用额度
		BigDecimal hapCreditTyAmt = BigDecimal.ZERO;
		//剩余额度
		BigDecimal remainderTyAmt = BigDecimal.ZERO;
		
		//企业总和
		TotalLineMonitorDailyDto totalQYDto = new TotalLineMonitorDailyDto();
		totalQYDto.setCreditTypeName("总额度");
		//核定额度
		BigDecimal creditQyAmt = BigDecimal.ZERO;
		//已用额度
		BigDecimal hapCreditQyAmt = BigDecimal.ZERO;
		//剩余额度
		BigDecimal remainderQyAmt = BigDecimal.ZERO;
		
		boolean tyflag = false;
		
		boolean qyflag = false;
		
		for(TotalLineMonitorDailyDto dto : list){
			//TODO 去掉质押式回购
			if("同业".equals(dto.getCustomerType())){
				creditTyAmt = creditTyAmt.add(dto.getCreditAmt());
				hapCreditTyAmt = hapCreditTyAmt.add(hapCreditTyAmt);
				tyflag = true;
			}else {
				creditQyAmt = creditTyAmt.add(dto.getCreditAmt());
				hapCreditQyAmt = hapCreditTyAmt.add(hapCreditTyAmt);
				qyflag = true;
			}

		}
		
		//同业计算
		//计算剩余额度
		remainderTyAmt = remainderTyAmt.subtract(hapCreditTyAmt);
		
		totalTYDto.setCreditAmt(creditTyAmt);
		totalTYDto.setHapCreditAmt(hapCreditTyAmt);
		totalTYDto.setRemainderAmt(remainderTyAmt);
		//调用一下计算比例
		totalTYDto.getRatio();
		
		//企业计算
		remainderQyAmt = remainderQyAmt.subtract(hapCreditQyAmt);
		
		totalQYDto.setCreditAmt(creditQyAmt);
		totalQYDto.setHapCreditAmt(hapCreditQyAmt);
		totalQYDto.setRemainderAmt(remainderQyAmt);
		//调用一下计算比例
		totalQYDto.getRatio();
		
		if(tyflag){
			resultlist.add(totalTYDto);
		}
		if(qyflag){
			resultlist.add(totalQYDto);
		}
		if(!StringUtil.isEmptyList(list)){
			resultlist.addAll(list);
		}
		
		
		return resultlist;
	}
	

	public List<TyLineMonitorDailyDto> queryTyLineMonitorDailyList(Map<String, Object> param){
		List<TyLineMonitorDailyDto> rowList=new ArrayList<TyLineMonitorDailyDto>();
		Date cdate = DateUtil.parse((String)param.get("cdate"));
		String[] titleArr =  StringUtil.split((String) param.get("title"),","); //包含开头，下一步去掉开头

		//每个dto相当于最小的数据单位 （主体名称，失效日，期限，总额，已用，剩余），并不代表一行的数据
		
		
		
		//不考虑总额度，
		for(int i = 1 ; i < titleArr.length ; i ++ ){
			
			
			//只对特殊资产做特殊处理
			
			List<TyLineMonitorDailyDto> confList=new ArrayList<TyLineMonitorDailyDto>();
			
			
			
			
		}
		
		
/*		for(int i=0;i<6;i++){
		     Map m= new HashMap<String,String>();
		     m.put("u_login_id","登录名"+i); m.put("u_name","张三"+i);
		 m.put("u_role","角色"+i); m.put("u_dep",BigDecimal.ZERO);
		 m.put("u_type","用户类型"+i);
		     rowList.add(m);
		 }*/
		return rowList;
	}
	
	
}
