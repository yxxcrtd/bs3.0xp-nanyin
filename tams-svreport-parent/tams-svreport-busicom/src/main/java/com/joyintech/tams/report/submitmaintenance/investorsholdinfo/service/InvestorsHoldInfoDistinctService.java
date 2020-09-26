package com.joyintech.tams.report.submitmaintenance.investorsholdinfo.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.systemtools.constant.DataIndexSequenceType;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.report.investormerger.dao.ChbEquHoldCheckDtoMapper;
import com.joyintech.tams.report.investormerger.dao.ChbInvestholdInfoCheckDtoMapper;
import com.joyintech.tams.report.investormerger.dto.ChbEquHoldCheckDto;
import com.joyintech.tams.report.investormerger.dto.ChbInvestholdInfoCheckDto;
import com.joyintech.tams.report.submitmaintenance.investorsholdinfo.dao.InvestorsHoldInfo001Mapper;
//import com.joyintech.tams.report.submitmaintenance.investorsholdinfo.dao.InvestorsHoldInfo001Mapper;
//import com.joyintech.tams.report.submitmaintenance.equityinfo.dao.EquityInfoDistinctMapper;
import com.joyintech.tams.report.submitmaintenance.investorsholdinfo.dto.InvestorsHoldInfo001Dto;
import com.joyintech.tams.report.submitmanagement.checkreport.dao.CheckReport001Mapper;
import com.joyintech.tams.report.submitmanagement.checkreport.dto.CheckReport001Dto;
import com.joyintech.tams.report.submitmanagement.investorsholdinforegist.dao.InvestorsHoldInfoRegist001Mapper;
import com.joyintech.tams.report.submitmanagement.investorsholdinforegist.dto.InvestorsHoldInfoRegist001Dto;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tools.collectors.JoyinCollectors;
@Service
public class InvestorsHoldInfoDistinctService {
//	@Autowired
//	private InvestorsHoldInfo001Mapper investorsHoldInfo001Mapper;
	@Autowired
	private ChbEquHoldCheckDtoMapper chbEquHoldCheckDtoMapper;
	@Autowired
	private ChbInvestholdInfoCheckDtoMapper chbInvestholdInfoCheckDtoMapper;
	@Autowired
    private CheckReport001Mapper chbReportCheckMapper;
	@Autowired
    private InvestorsHoldInfoRegist001Mapper investorsHoldInfoRegist001Mapper;
//	@Autowired
//	private EquityInfoDistinctMapper equityInfoDistinctMapper;
	@Autowired
	private InvestorsHoldInfo001Mapper investorsHoldInfo001Mapper;
	
    /**
     * sql最大一次最大删除条数
     */
    @Value("${application.reportSelectOrDeleteCount}")
    public  String reportSelectOrDeleteCount;
    
	boolean doCombineIdentifying(InvestorsHoldInfo001Dto action,List<InvestorsHoldInfo001Dto> listE,Map<String,List<ChbEquHoldCheckDto>> holdTmpMap){
//		String autoFlag=SvrDataUtil.getSysParam("SVR_EQU_HOLD_MERGER");
		String key=action.getIndentifyMark()+ GlobalConstant.UNDER_LINE +action.getRegistBankCode();
		//手动
//		if(autoFlag.equals("0")){
			//查询身份信息临时表
//			ChbEquHoldTmpDto qmodel=new ChbEquHoldTmpDto();
//			qmodel.setIndentifyMark(action.getIndentifyMark());
//			qmodel.setRegistBankCode(action.getRegistBankCode());
//			List<ChbEquHoldTmpDto> result=chbEquHoldTmpDtoMapper.selectBymodel(qmodel);
			//如果临时表有值为待处理 该数据插入临时表
//			if(holdTmpMap.containsKey(key)){
//				ChbInvestholdInfoTmpDto temp=transformModelToTemp(action);
//				chbInvestholdInfoTmpDtoMapper.insert(temp);
//				return false;
//			}
//		}else{
//			//查询身份信息正式表 
//			EquityInfoRegist001Dto qmodel=new EquityInfoRegist001Dto();
//			qmodel.setBankCode(action.getRegistBankCode());
//			qmodel.setIndentifyMark(action.getIndentifyMark());
//			EquityInfoRegist001Dto resultModel=equityInfoDistinctMapper.queryEquityInfoByIndentifymarkAndBankCode(qmodel);
//			//如果查不到
//			if(resultModel==null){
//				ChbInvestholdInfoTmpDto temp=transformModelToTemp(action);
//				chbInvestholdInfoTmpDtoMapper.insert(temp);
				//查询身份信息临时表 查出合并后的识别标识
//				ChbEquHoldTmpDto qtmodel=new ChbEquHoldTmpDto();
//				qtmodel.setIndentifyMark(action.getIndentifyMark());
//				qtmodel.setRegistBankCode(action.getRegistBankCode());
//				List<ChbEquHoldTmpDto> result=chbEquHoldTmpDtoMapper.selectBymodel(qtmodel);
				if(holdTmpMap.containsKey(key)){
					ChbEquHoldCheckDto value=holdTmpMap.get(key).get(0);
					if(DictCodeListExt2.处理状态_中债_未合并.getValue().equals(value.getOperatestatus())){
						ChbInvestholdInfoCheckDto temp=transformModelToTemp(action);
						chbInvestholdInfoCheckDtoMapper.insert(temp);
						return false;
					}else{
						ChbInvestholdInfoCheckDto temp=transformModelToTemp(action);
						chbInvestholdInfoCheckDtoMapper.insert(temp);
						String mergerindentifyMark=holdTmpMap.get(key).get(0).getMergerindentifymark();
						if(!mergerindentifyMark.equals(action.getIndentifyMark())){
							return false;
						}
						//查询listE中是否存在 合并后识别标识数据 进行合并
						for(InvestorsHoldInfo001Dto single:listE){
							if(single.getIndentifyMark().equals(action.getIndentifyMark())&&
									single.getCcy().equals(action.getCcy())&&single.getHoldDate().equals(action.getHoldDate())
									&&single.getProdid().equals(action.getProdid())&&single.getStatus().equals(action.getStatus())){
								continue;
							}
							if(!single.getIndentifyMark().equals(mergerindentifyMark)&&
									single.getCcy().equals(action.getCcy())&&single.getHoldDate().equals(action.getHoldDate())
									&&single.getProdid().equals(action.getProdid())&&single.getStatus().equals(action.getStatus())){
								action.setAmount(single.getAmount().add(action.getAmount()));
								action.setQty(single.getQty().add(action.getQty()));
								action.setCnyAmt(single.getCnyAmt().add(action.getCnyAmt()));
							}
						}
						return true;
					}
				}
//			}
//		}
		return false;
	}
	ChbInvestholdInfoCheckDto transformModelToTemp(InvestorsHoldInfo001Dto investorsHoldInfo001Dto){
		ChbInvestholdInfoCheckDto temp=new ChbInvestholdInfoCheckDto();
		BeanUtils.copyProperties(investorsHoldInfo001Dto,temp);
		return temp;
		
	}
	@SuppressWarnings("unchecked")
	/**
	 * 
	 * @param list
	 * @param type 检查类型 01报送前检查 02普通入库检查
	 */
	public void checkInvestorsHoldInfolist(@SuppressWarnings("rawtypes") List list,String type){
		//查询到持有信息检查表数据 并以身份标识和登记银行代码分组
		 List<ChbEquHoldCheckDto> allHoldTmpList = chbEquHoldCheckDtoMapper.selectAllData(null);
		 Map<String, List<ChbEquHoldCheckDto>> holdTmpMap=allHoldTmpList.stream().collect(
				 JoyinCollectors.groupingBy(
               x -> x.getRegistBankCode()+GlobalConstant.UNDER_LINE+x.getIndentifyMark()));
		//以合并后身份标识和登记银行代码分组
		 Map<String, List<ChbEquHoldCheckDto>> holdHbTmpMap=allHoldTmpList.stream().collect(
				 JoyinCollectors.groupingBy(
               x -> x.getRegistBankCode()+GlobalConstant.UNDER_LINE+x.getMergerindentifymark()));
		 List<ChbInvestholdInfoCheckDto> insertBatch=new ArrayList<ChbInvestholdInfoCheckDto>();
		 //循环本次入库数据
		 Object single=null;
		 for(Iterator<T> iterator = list.iterator();iterator.hasNext();){
			  single=iterator.next();
			  String key="";
			  if(single instanceof Map){
				   key=((Map<String,String>)single).get("REGIST_BANK_CODE")+
						   GlobalConstant.UNDER_LINE +((Map<String,String>) single).get("INDENTIFY_MARK");
			  }else if(single instanceof InvestorsHoldInfo001Dto){
				  key=((InvestorsHoldInfo001Dto)single).getRegistBankCode()+
						   GlobalConstant.UNDER_LINE +((InvestorsHoldInfo001Dto)single).getIndentifyMark();
			  }
			 //身份检查表中包含本次数据
			 if(holdTmpMap.containsKey(key)){
				 InvestorsHoldInfo001Dto qmodel=null;
				 if(single instanceof Map){
				  qmodel=mapToDto((Map<String,String>)single);
				 }else if(single instanceof InvestorsHoldInfo001Dto){
					 qmodel= (InvestorsHoldInfo001Dto)single;
				 }
				 //已报送剔除
				 if(chbInvestholdInfoCheckDtoMapper.checkInvestholdInfoSub(qmodel) > 0){
					 iterator.remove();
					 continue;
				 }
				 ChbEquHoldCheckDto infoTmp=holdTmpMap.get(key).get(0);
				 ChbInvestholdInfoCheckDto temp=transformModelToTemp(qmodel);
				 temp.setWorkflowId(infoTmp==null?null:infoTmp.getOperatestatus());
				 temp.setOperatestatus(DictCodeListExt2.处理状态_中债_未合并.getValue());
				 insertBatch.add(temp);
				 if(type.equals("01")){
					 continue;
				 }
//				 chbInvestholdInfoTmpDtoMapper.insert(temp);
				 List<ChbEquHoldCheckDto> infoTmpHbList=null;
				 if(infoTmp!=null)
				 {
					 infoTmpHbList=holdHbTmpMap.get(infoTmp.getRegistBankCode()+ GlobalConstant.UNDER_LINE +infoTmp.getMergerindentifymark());
				 }
				 //循环合并后标识一致的 所有数据
				 if(infoTmpHbList!=null)
				 for(ChbEquHoldCheckDto info:infoTmpHbList){
					 //排除本身
//					 if(single instanceof Map){
//						  if(info.getIndentifyMark().equals(((Map<String,String>)single).get("INDENTIFY_MARK"))){
//								 continue;
//							 }
//						 }else if(single instanceof InvestorsHoldInfo001Dto){
//							 qmodel= (InvestorsHoldInfo001Dto)single;
//							 if(info.getIndentifyMark().equals(((InvestorsHoldInfo001Dto)single).getIndentifyMark())){
//								 continue;
//							 }
//						 }
					
					 //根据识别标识查询持有检查表是否有数据
					 ChbInvestholdInfoCheckDto record=new ChbInvestholdInfoCheckDto();
					 record.setRegistBankCode(info.getRegistBankCode());
					 record.setIndentifyMark(info.getIndentifyMark());
					 record.setHoldDate(qmodel.getHoldDate());
					 List<ChbInvestholdInfoCheckDto> temList=chbInvestholdInfoCheckDtoMapper.selectBymodel(record);
					 if(temList==null||temList.isEmpty()){//持有信息中没有数据 再去维护表找是否有数据 (排除已报送)
						List<ChbInvestholdInfoCheckDto> infoList= chbInvestholdInfoCheckDtoMapper.selectBymodelFromFormer(record);
						if(infoList!=null&&!infoList.isEmpty()) {//维护表中有数据(排除已报送) 将维护表中数据 插入到检查表
							for(ChbInvestholdInfoCheckDto infoIntep:infoList){
								infoIntep.setMergerindentifymark(infoIntep.getIndentifyMark());
								ChbInvestholdInfoCheckDto dmodel=new ChbInvestholdInfoCheckDto();
								 dmodel.setRegistBankCode(infoIntep.getRegistBankCode());
								 dmodel.setIndentifyMark(infoIntep.getIndentifyMark());
								 dmodel.setHoldDate(infoIntep.getHoldDate());
								 dmodel.setCcy(infoIntep.getCcy());
//								 dmodel.setPrdRegistCode(infoIntep.getPrdRegistCode());
								 dmodel.setProdid(infoIntep.getProdid());
								 chbInvestholdInfoCheckDtoMapper.deleteBymodel(dmodel);
								 if(infoIntep.getStatus().equals("N")){
									 infoIntep.setStatus("E");
								 }
								chbInvestholdInfoCheckDtoMapper.insert(infoIntep);
							}
						}
					 }else{
						 for(ChbInvestholdInfoCheckDto tep:temList){
							 //二次合并将原检查表 已处理状态数据变更为未处理
								ChbInvestholdInfoCheckDto dmodel=new ChbInvestholdInfoCheckDto();
								 dmodel.setRegistBankCode(tep.getRegistBankCode());
								 dmodel.setIndentifyMark(tep.getIndentifyMark());
								 dmodel.setHoldDate(tep.getHoldDate());
								 dmodel.setCcy(tep.getCcy());
//								 dmodel.setPrdRegistCode(tep.getPrdRegistCode());
								 dmodel.setProdid(tep.getProdid());
//								 chbInvestholdInfoCheckDtoMapper.deleteBymodel(dmodel);
							     chbInvestholdInfoCheckDtoMapper.updateOperatestatusW(tep);
						 }
					 }
				 }
				 iterator.remove();
			 }
		 }
		 for(ChbInvestholdInfoCheckDto temp:insertBatch){
			 if(type.equals("01")){
				 ChbInvestholdInfoCheckDto qmodel=new ChbInvestholdInfoCheckDto();
				 qmodel.setRegistBankCode(temp.getRegistBankCode());
				 qmodel.setIndentifyMark(temp.getIndentifyMark());
				 qmodel.setHoldDate(temp.getHoldDate());
				 qmodel.setCcy(temp.getCcy());
				 qmodel.setPrdRegistCode(temp.getPrdRegistCode());
				 qmodel.setProdid(temp.getProdid());
				 chbInvestholdInfoCheckDtoMapper.updateMergerIdAndOperatestatus(qmodel);
			 }else{
				 //考虑重复导入 重复了 先删后插
				 ChbInvestholdInfoCheckDto qmodel=new ChbInvestholdInfoCheckDto();
				 qmodel.setRegistBankCode(temp.getRegistBankCode());
				 qmodel.setIndentifyMark(temp.getIndentifyMark());
				 qmodel.setHoldDate(temp.getHoldDate());
				 qmodel.setCcy(temp.getCcy());
				 qmodel.setPrdRegistCode(temp.getPrdRegistCode());
				 qmodel.setProdid(temp.getProdid());
				 chbInvestholdInfoCheckDtoMapper.deleteBymodel(qmodel);
				 if(temp.getStatus().equals("N")){
					 temp.setStatus("E");
				 }
				 if("01".equals(temp.getWorkflowId())){
					 temp.setWorkflowId(null);
					 temp.setStatus("A");
					 chbInvestholdInfoCheckDtoMapper.insertToChbInvestholdInfo(temp);
				 }
				 temp.setStatus("E");
				 chbInvestholdInfoCheckDtoMapper.insert(temp);
			 }
		 }
		
	}
	/**
	 * 合并
	 * @param list
	 * @param type 01 map类型  02dto 类型
	 */
	public void mergeInvestorsHoldInfolist(String type,String interType){
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		//查询出上次合并后识别标识
		List<String> lastHbhList=chbInvestholdInfoCheckDtoMapper.selectLastMergerIndentifyMarkAndBankcode();
		try {
			for(String lasthbh:lastHbhList){
				String[] ss=lasthbh.split(",");
				ChbInvestholdInfoCheckDto recode=new ChbInvestholdInfoCheckDto();
				recode.setRegistBankCode(ss[0]);
				recode.setIndentifyMark(ss[1]);
				 recode.setHoldDate(sdf.parse(ss[2]));
				chbInvestholdInfoCheckDtoMapper.deleteByBankcodeAndIndentifyMarkWH(recode);
				chbInvestholdInfoCheckDtoMapper.deleteByBankcodeAndIndentifyMarkDJ(recode);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//合并处理
		List<InvestorsHoldInfo001Dto> resultList=chbInvestholdInfoCheckDtoMapper.selectHbhChbInvestholdInfoUnmerged();
		for(Iterator<InvestorsHoldInfo001Dto> iterator = resultList.iterator();iterator.hasNext();){
			InvestorsHoldInfo001Dto single=iterator.next();
			if(chbInvestholdInfoCheckDtoMapper.checkInvestholdInfoSub(single) > 0){
			 iterator.remove();
			 continue;
		 }
		}
		List<String> keys = new ArrayList<>();
		List<Map<String, String>> newList=new ArrayList<Map<String, String>>();
		Map<String, String> nmap=null;
		for(InvestorsHoldInfo001Dto single:resultList){
			nmap=dtoToMap(single);
			newList.add(nmap);
			String key=single.getRegistBankCode()+GlobalConstant.UNDER_LINE +single.getIndentifyMark()
			+GlobalConstant.UNDER_LINE+single.getCcy()+GlobalConstant.UNDER_LINE+sdf.format(single.getHoldDate())+
			GlobalConstant.UNDER_LINE+single.getProdid();
			keys.add(key);
		}
		if(!keys.isEmpty()){
			chbInvestholdInfoCheckDtoMapper.deleteInvestholdInfoBatch(keys);
			chbInvestholdInfoCheckDtoMapper.deleteInvestholdInfoSubBatch(keys);
		}
//		if("01".equals(type)){
//			list.addAll(newList);
//		}else{
//			list.addAll(resultList);
//		}
		//更新持有检查表数据状态
		chbInvestholdInfoCheckDtoMapper.updateChbInvestholdCheckAftermerged();
		//插入维护及登记表
//		investorsHoldInfo001Mapper.insertChbHoldInfoData(resultList);
		InvestorsHoldInfo001Service investorsHoldInfo001Service = 
                SpringContextUtil.getBean(InvestorsHoldInfo001Service.class);
		// 获取所有的产品登记编码
		Map<String, String> prdRegistMap = new HashMap<String, String>();
		List<InvestorsHoldInfo001Dto> listA=new ArrayList<InvestorsHoldInfo001Dto>();
		List<InvestorsHoldInfo001Dto> listE=new ArrayList<InvestorsHoldInfo001Dto>();
		for(InvestorsHoldInfo001Dto single:resultList){
			if(single.getPrdRegistCode()==null||"".equals(single.getPrdRegistCode())){
				if (prdRegistMap.isEmpty()) {
					prdRegistMap = investorsHoldInfo001Mapper.getAllRegistProdCodeSet().stream()
							.collect(JoyinCollectors.toMap(x -> x.get("PRODID"), x -> x.get("REG_CODE")));
				} 
				if(single.getProdid()!=null){
					single.setPrdRegistCode(prdRegistMap.get(single.getProdid()));
				}
				if(single.getPrdRegistCode()==null||"".equals(single.getPrdRegistCode())){
					if(interType.equals(DictCodeListExt2.数据来源_资管系统.getValue()))
					listA.add(single);
				}else{
					listE.add(single);
				}
			}else{
				listE.add(single);
			}
		}
    	investorsHoldInfo001Service.afterCheckDeal(listE, listA, interType, null, null, null, null);
	}
	public void checkBeforeSubmit(){
		checkBeforeSubmit(null,"02");
	}
	/**
	 * @param type "01" 数据检查  02自动报送的检查
	 * @param registList
	 * @returnqueryRepeatEquityInfoHoldUnSubmit
	 */
	public List<InvestorsHoldInfoRegist001Dto> checkBeforeSubmit(List<InvestorsHoldInfoRegist001Dto> registList,String type){
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		//查询出存在于身份检查表中 且状态为未报送的持有维护信息
		List<String> keyst = new ArrayList<>();
		if(registList!=null){
			for(InvestorsHoldInfoRegist001Dto single:registList){
				String key=single.getRegistBankCode()+GlobalConstant.UNDER_LINE +single.getIndentifyMark()
				+GlobalConstant.UNDER_LINE+single.getCcy()+GlobalConstant.UNDER_LINE+sdf.format(single.getHoldDate())+
				GlobalConstant.UNDER_LINE+single.getProdid();
				keyst.add(key);
			}
		}
//		List<InvestorsHoldInfo001Dto> listR=chbInvestholdInfoCheckDtoMapper.queryRepeatEquityInfoHoldUnSubmit(keyst);
		List<InvestorsHoldInfo001Dto> listR=chbInvestholdInfoCheckDtoMapper.queryNotInHoldButIncheckInfoHold(keyst);
		
		if(listR!=null&&!listR.isEmpty()){
			String mergerFlag = SvrDataUtil.getSysParam("SVR_EQU_HOLD_MERGER");
//			if("0".equals(mergerFlag)){//如果是手动
//				//插入检查表
////				for(InvestorsHoldInfo001Dto single:listR){
////					ChbInvestholdInfoCheckDto temp=transformModelToTemp(single);
////					temp.setOperatestatus(DictCodeListExt2.处理状态_中债_未合并.getValue());
////					chbInvestholdInfoCheckDtoMapper.insert(temp);
////				}
//				//删除维护表和登记表
//				List<String> keys = new ArrayList<>();
//				for(InvestorsHoldInfo001Dto single:listR){
//					String key=single.getRegistBankCode()+GlobalConstant.UNDER_LINE +single.getIndentifyMark()
//					+GlobalConstant.UNDER_LINE+single.getCcy()+GlobalConstant.UNDER_LINE+sdf.format(single.getHoldDate())+
//					GlobalConstant.UNDER_LINE+single.getProdid();
//					keys.add(key);
//				}
////				chbEquHoldCheckDtoMapper.deleteEquityInfoBatch(keys);
////	        	chbEquHoldCheckDtoMapper.deleteEquitySubInfoBatch(keys);
//				checkInvestorsHoldInfolist(listR,"01");
//				//查询出对应的未报送登记信息
//				List<InvestorsHoldInfoRegist001Dto> djlist=chbInvestholdInfoCheckDtoMapper.queryInvestorsHoldInfoRegistByPkUnsubmit(keys);
//				//更新原检查表和登记表检查检查状态
//				dochecksafe(djlist,"02");
//				if("01".equals(type)){
//					return djlist;
//				}
//			}else
			if(!"0".equals(mergerFlag)){
				//插入检查表
//				for(InvestorsHoldInfo001Dto single:listR){
//					ChbInvestholdInfoCheckDto temp=transformModelToTemp(single);
//					temp.setOperatestatus(DictCodeListExt2.处理状态_中债_未合并.getValue());
//					chbInvestholdInfoCheckDtoMapper.insert(temp);
//				}
				//删除维护表和登记表
				//删除维护表和登记表
//				List<String> keys = new ArrayList<>();
//				for(InvestorsHoldInfo001Dto single:listR){
//					String key=single.getRegistBankCode()+GlobalConstant.UNDER_LINE +single.getIndentifyMark()
//					+GlobalConstant.UNDER_LINE+single.getCcy()+GlobalConstant.UNDER_LINE+sdf.format(single.getHoldDate())+
//					GlobalConstant.UNDER_LINE+single.getProdid();
//					keys.add(key);
//				}
//				chbInvestholdInfoCheckDtoMapper.deleteInvestholdInfoBatch(keys);
//				chbInvestholdInfoCheckDtoMapper.deleteInvestholdInfoSubBatch(keys);
//				checkInvestorsHoldInfolist(listR,"01");
				//调用合并处理
//	        	List<InvestorsHoldInfo001Dto> listN=new ArrayList<InvestorsHoldInfo001Dto>();
//	        	mergeInvestorsHoldInfolist("02",DictCodeListExt2.数据来源_EXCEL导入.getValue());
	        	
//	        	InvestorsHoldInfo001Service investorsHoldInfo001Service = 
//                SpringContextUtil.getBean(InvestorsHoldInfo001Service.class);
//	        	if(null==listN||listN.isEmpty()){
//	        		return null;
//	        	}
//	        	investorsHoldInfo001Service.afterCheckDeal(listN, null, DictCodeListExt2.数据来源_EXCEL导入.getValue(), null, null, null, null);
	        	
				List<String> keys1 = new ArrayList<>();
	        	for(InvestorsHoldInfo001Dto single:listR){
					String key=single.getRegistBankCode()+GlobalConstant.UNDER_LINE +single.getIndentifyMark()
					+GlobalConstant.UNDER_LINE +single.getCcy()+GlobalConstant.UNDER_LINE +sdf.format(single.getHoldDate())
					+GlobalConstant.UNDER_LINE +single.getProdid();
					keys1.add(key);
				}
//				chbEquHoldCheckDtoMapper.deleteEquityInfoBatch(keys);
//	        	chbEquHoldCheckDtoMapper.deleteEquitySubInfoBatch(keys);
				//查询出对应的未报送登记信息
	        	List<InvestorsHoldInfoRegist001Dto> djlist=chbInvestholdInfoCheckDtoMapper.queryInvestorsHoldInfoRegistByPkUnsubmit(keys1);
	        	//更新正确的识别标识
	        	//更新info表
	        	chbInvestholdInfoCheckDtoMapper.updateUnMergeInvestorsHoldInfo(keys1);
	        	//更新sub表
	        	chbInvestholdInfoCheckDtoMapper.updateUnMergeInvestorsHoldInfoSub(keys1);
	        	//更新check表
	        	chbInvestholdInfoCheckDtoMapper.updateUnMergeInvestorsHoldInfoCheck(keys1);
	        	//更新原检查表和登记表检查检查状态
				dochecksafe(djlist,"01");
				if("02".equals(type)){
					return djlist;
				}
			}
		}else{
			if("02".equals(type)){
			return registList;
			}
		}
		//增加检查 存在合并身份后 并没有接持有数据 导致持有 信息还是合并前识别标识 刷新该数据
//				chbInvestholdInfoCheckDtoMapper.updateUnMergeInvestorsHoldInfo();
//				chbInvestholdInfoCheckDtoMapper.updateUnMergeInvestorsHoldInfoSub();
		return null;
	}
	void dochecksafe(List<InvestorsHoldInfoRegist001Dto> equityInfoRegistRstList,String type){
		 List<InvestorsHoldInfoRegist001Dto> investRegistUpdateList = new ArrayList<>();
		  List<CheckReport001Dto> resultList = new ArrayList<>();
		  List<String> checkseqnos= new ArrayList<String>();
	        if(CollectionUtils.isNotEmpty(equityInfoRegistRstList)){
	            equityInfoRegistRstList.forEach(item->{
	            	if(item.getCheckSeqno()!=null&&!item.getCheckSeqno().equals("")&&!item.getCheckSeqno().equals("0")){
	            		checkseqnos.add(item.getCheckSeqno());
	            	}
	                //检查结果
//	                CheckReport001Dto chbReportCheck = new CheckReport001Dto();
//	                BeanUtil.copyPropertiesIgnoreNull(chbReportCheck, item);
//	                chbReportCheck.setCheckSeqno(CommonUtil.getSequenceIndexData(DataIndexSequenceType.检查序列号));
//	                chbReportCheck.setRepCode(DictCodeListExt2.报表名称_投资者持有信息登记.getValue());
//	                chbReportCheck.setRepName(DictCodeListExt2.报表名称_投资者持有信息登记.getCodeName());
//	                if(type.equals("01")){
//	                	chbReportCheck.setCheckResult("检查通过！");
//	                }else{
//	                	chbReportCheck.setCheckResult("检查不通过，该投资者存在多个识别标识！");
//	                }
//	                resultList.add(chbReportCheck);
//
//	                //更新检查序列号对应的数据
//	                InvestorsHoldInfoRegist001Dto dto = new InvestorsHoldInfoRegist001Dto();
//	                dto.setPrdRegistCode(item.getPrdRegistCode());
//	                dto.setIndentifyMark(item.getIndentifyMark());
//	                dto.setHoldDate(item.getHoldDate());
//	                dto.setCcy(item.getCcy());
//	                dto.setRegistBankCode(item.getRegistBankCode());
//	                dto.setCheckSeqno(chbReportCheck.getCheckSeqno().trim());
//	                investRegistUpdateList.add(dto);
	              CheckReport001Dto chbReportCheck = new CheckReport001Dto();
//	                BeanUtil.copyPropertiesIgnoreNull(chbReportCheck, item);
	              String validKey = "产品登记编码 "+item.getPrdRegistCode()+GlobalConstant.COMMA
	                      +"识别标识 "+item.getIndentifyMark()+GlobalConstant.COMMA
	                      +"持有日期 "+DateUtil.format(item.getHoldDate(),"yyyy-MM-dd")+GlobalConstant.COMMA
	                      +"币种  "+item.getCcy()+GlobalConstant.COMMA
	                      +"登记银行代码  "+item.getRegistBankCode();
	              	chbReportCheck.setValidKey(validKey);
	                chbReportCheck.setCheckSeqno(CommonUtil.getSequenceIndexData(DataIndexSequenceType.检查序列号));
	                chbReportCheck.setRepCode(DictCodeListExt2.报表名称_投资者持有信息登记.getValue());
	                chbReportCheck.setRepName(DictCodeListExt2.报表名称_投资者持有信息登记.getCodeName());
	                if(type.equals("01")){
	                	chbReportCheck.setCheckResult("01");
	                }else{
	                	chbReportCheck.setCheckResult("02");
	                	chbReportCheck.setErrorInfo("检查不通过，该投资者存在多个识别标识！");
	                }
	                resultList.add(chbReportCheck);

	                //更新检查序列号对应的数据
	                InvestorsHoldInfoRegist001Dto dto = new InvestorsHoldInfoRegist001Dto();
//	                List<String> keyList = StringUtil.splitToList(GlobalConstant.COMMA,item.getValidKey());
	                dto.setPrdRegistCode(item.getPrdRegistCode());
	                dto.setIndentifyMark(item.getIndentifyMark());
	                dto.setHoldDate(item.getHoldDate());
	                dto.setCcy(item.getCcy());
	                dto.setRegistBankCode(item.getRegistBankCode());
	                dto.setCheckSeqno(chbReportCheck.getCheckSeqno().trim());
	                investRegistUpdateList.add(dto);
	            });
	        }
	        //批量删除之前的检查数据
	        chbReportCheckMapper.delReportCheckDatas(checkseqnos);
	        if(CollectionUtils.isNotEmpty(resultList)){
	            //批量插入报送前检查表数据
	            chbReportCheckMapper.batchInsert(resultList);
	            //批量更新投资者身份信息检查序列号
	            if(CollectionUtils.isNotEmpty(investRegistUpdateList)){
	            	investorsHoldInfoRegist001Mapper.updateInvestRegistCheckSeqno(investRegistUpdateList);
	            }
	        }
	}
	/**
	 * 查询维护表中存在的相关数据 <功能详细描述>
	 *
	 * @param listE
	 *            数据集合
	 * @return 匹配的数据
	 * @see
	 * @since 1.0
	 */
	public Map<String, InvestorsHoldInfo001Dto> selectAllHoldInfoDataRegCodeKey(List<InvestorsHoldInfo001Dto> listE) {
		// 查询导入文件中的数据在数据库中已经存在的数据
		List<InvestorsHoldInfo001Dto> allDataList = new ArrayList<InvestorsHoldInfo001Dto>();

		int maxDataCountOneTime = this.parsToInt(reportSelectOrDeleteCount, "2");
//		if (maxDataCountOneTime < 1) {
//			maxDataCountOneTime = 1000;
//		}
		int dataCount = listE.size();
		if (dataCount <= maxDataCountOneTime) {
			allDataList.addAll(chbInvestholdInfoCheckDtoMapper.selectAllHoldInfoData(listE));
		} else {
			List<InvestorsHoldInfo001Dto> newKeyList = new ArrayList<InvestorsHoldInfo001Dto>();
			newKeyList.addAll(listE);

			// 分批处理
			int part = dataCount / maxDataCountOneTime; // 分批数
			for (int i = 0; i < part; i++) {
				allDataList.addAll(
						chbInvestholdInfoCheckDtoMapper.selectAllHoldInfoData(newKeyList.subList(0, maxDataCountOneTime)));
				newKeyList.subList(0, maxDataCountOneTime).clear();
			}
			// 最后剩下的数据
			if (CollectionUtils.isNotEmpty(newKeyList)) {
				allDataList.addAll(chbInvestholdInfoCheckDtoMapper.selectAllHoldInfoData(newKeyList));
			}
		}
		Map<String, InvestorsHoldInfo001Dto> allHoldInfoDataMap = StringUtil.isEmptyList(allDataList)
				? new HashMap<String, InvestorsHoldInfo001Dto>()
				: allDataList.stream().collect(JoyinCollectors.toMap(x -> x.getRegistBankCode()
						+ GlobalConstant.UNDER_LINE + x.getIndentifyMark() + GlobalConstant.UNDER_LINE
						// +x.getPrdRegistCode()+GlobalConstant.UNDER_LINE+x.getPrdRegistCode()
						+ x.getPrdRegistCode()  + GlobalConstant.UNDER_LINE + x.getCcy() + GlobalConstant.UNDER_LINE
						+ DateUtil.format(x.getHoldDate()), x -> x));
		return allHoldInfoDataMap;
	}
	
	
    /**
     * 配置项中非空处理
     * @param Count
     * @param flag
     * @return
     */
    public int parsToInt(String count,String flag){
   	 if(StringUtil.isNotEmpty(count)&&Integer.parseInt(count)<=0){
   		 if("1".equals(flag)){
   	         	return 20;
   		 }
   		 else if("1".equals(flag)){
	         	return 500;
   		 }
   		 else {
	         	return 100;
   		 }
        }
        else{
        	return Integer.parseInt(count);
        }
   }
    InvestorsHoldInfo001Dto mapToDto(Map<String,String> map){
    	InvestorsHoldInfo001Dto result=new InvestorsHoldInfo001Dto();
		if("001".equals(CacheUtil.getSysParam("CUSTOMER_BANK_CODE"))){
			result.setAgent(map.get("AGENT")== null ? "" : map.get("AGENT"));
		}else{
	    result.setAgent(map.get("AGENT"));
		}
    	result.setAmount(new BigDecimal(map.get("AMOUNT")));
    	result.setCcy(map.get("CCY"));
    	result.setCnyAmt(new BigDecimal(map.get("CNY_AMT")));
    	result.setDeptCode(map.get("DEPT_CODE"));
    	result.setHoldDate(map.get("HOLD_DATE")==null?null:DateUtil.parse(map.get("HOLD_DATE")));
		if("001".equals(CacheUtil.getSysParam("CUSTOMER_BANK_CODE"))){
			result.setOrgCode(map.get("ORG_CODE")==null ? "C10403":map.get("ORG_CODE"));
		}else{
	    result.setOrgCode(map.get("ORG_CODE"));
		}
    	result.setPrdRegistCode(map.get("REG_CODE"));
    	result.setProdid(map.get("ACCOUNT"));
    	result.setQty(new BigDecimal(map.get("QTY")));
    	result.setRegistBankCode(map.get("REGIST_BANK_CODE"));
    	result.setIndentifyMark(map.get("INDENTIFY_MARK"));
		return result;
		
	}
    Map<String,String> dtoToMap(InvestorsHoldInfo001Dto dto){
    	Map<String,String> map=new HashMap<String,String>();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    	map.put("AGENT",dto.getAgent());
    	map.put("AMOUNT",dto.getAmount()==null?"0":dto.getAmount().toString());
    	map.put("CCY",dto.getCcy());
    	map.put("CNY_AMT",dto.getCnyAmt()==null?"0":dto.getCnyAmt().toString());
    	map.put("DEPT_CODE",dto.getDeptCode());
    	map.put("HOLD_DATE",dto.getHoldDate()==null?"":sdf.format(dto.getHoldDate()));
    	map.put("ORG_CODE",dto.getOrgCode());
    	map.put("REG_CODE",dto.getPrdRegistCode());
    	map.put("ACCOUNT",dto.getProdid());
    	map.put("QTY",dto.getQty()==null?"0":dto.getQty().toString());
    	map.put("REGIST_BANK_CODE",dto.getRegistBankCode());
    	map.put("INDENTIFY_MARK",dto.getIndentifyMark());
		return map;
		
	}
}
