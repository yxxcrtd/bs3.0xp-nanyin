package com.joyintech.tams.report.submitmaintenance.equityinfo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.apache.poi.ss.formula.functions.T;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.systemtools.constant.DataIndexSequenceType;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.report.investormerger.dao.ChbEquHoldCheckDtoMapper;
import com.joyintech.tams.report.investormerger.dto.ChbEquHoldCheckDto;
import com.joyintech.tams.report.submitmaintenance.equityinfo.dao.EquityInfo001Mapper;
import com.joyintech.tams.report.submitmaintenance.equityinfo.dao.EquityInfoDistinctMapper;
import com.joyintech.tams.report.submitmaintenance.equityinfo.dto.EquityInfo001Dto;
import com.joyintech.tams.report.submitmanagement.checkreport.dao.CheckReport001Mapper;
import com.joyintech.tams.report.submitmanagement.checkreport.dto.CheckReport001Dto;
import com.joyintech.tams.report.submitmanagement.equityinforegist.dao.EquityInfoRegist001Mapper;
import com.joyintech.tams.report.submitmanagement.equityinforegist.dto.EquityInfoRegist001Dto;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tools.collectors.JoyinCollectors;
@Service
public class EquityInfoDistinctService {
	/**
	 * logger
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CheckReport001Mapper chbReportCheckMapper;
	@Autowired
	private EquityInfoRegist001Mapper equityInfoRegist001Mapper;
	@Autowired
	private ChbEquHoldCheckDtoMapper chbEquHoldCheckDtoMapper;

	@Autowired
	private EquityInfoDistinctMapper equityInfoDistinctMapper;

	@Autowired
	private SqlSessionTemplate template;

	/**
	 * 长sql最大一次插入或者更新条数
	 */
	@Value("${application.reportSelectOrDeleteCountForShenFen}")
	public String reportSelectOrDeleteCountForShenFen;

	/**
	 * 跟已报送的数据进行对比,有重复则按照已报送的
	 * @param equityList  xml解析出来的身份信息
	 * @param intype 01 为进检查表 02 为不进检查表
	 */
	@SuppressWarnings("unchecked")
	public void checkSendedData(@SuppressWarnings("rawtypes") List equityList,List<String> keys,String intype,String flag){
		if(StringUtil.isEmptyList(equityList)){
			return;
		}

		//step1、根据三要素信息查询已报送数据
//		List<String> keys = getKeys(equityList);
		List<EquityInfoRegist001Dto> ybs = null;
		logger.info("getEquInfoSubListYbs开始");
		if("01".equals(flag)){
			List<Map<String, String>> equityMapList = new ArrayList<Map<String, String>>();
			for(Object dto : equityList){
				Map<String,String> equityMap = modelToMapByPrpperties((EquityInfo001Dto)dto);
				equityMapList.add(equityMap);
			}
			ybs = getEquInfoSubListYbs(equityMapList);
		}else{
			ybs = getEquInfoSubListYbs(equityList);
		}
		logger.info("getEquInfoSubListYbs结束");
		if(StringUtil.isEmptyList(ybs)){
			return;
		}

		String key = "";
		String key1 = "";
		Object tmpMap = null;

		Map<String,EquityInfoRegist001Dto> mapYbs = new HashMap<String,EquityInfoRegist001Dto>();
		if ( ybs!=null ) {
			for ( EquityInfoRegist001Dto v:ybs ) {
				String k = getKey(v);
				mapYbs.put(k, v);
			}
		}
		// 有时候ybs数据量会过大,而此时ybs已经没有作用，可以将其指向null，并建议JVM进行一次垃圾回收，释放堆内存空间
		ybs = null;
		System.gc();
		HashMap<String,EquityInfoRegist001Dto> existList = new HashMap<String,EquityInfoRegist001Dto>();
		//step2、跟已报送的数据进行对比,有重复则按照已报送的识别标识更新源数据的合并识别标识并入库
		logger.info("step2跟已报送的数据进行对比合并入库开始");
		List<String> keys1=new ArrayList<String>();
		String indentifyMark ="";
		for (Iterator<T> iteror = equityList.iterator();iteror.hasNext();) {
			tmpMap = iteror.next();
			//当前不校验【原识别标识】有值的数据,(后续【重要信息变更】会对这种场景check)
			if(tmpMap  instanceof Map) {
				if(StringUtil.isNotEmpty(((Map<String,String>)tmpMap).get("ORG_INDENTIFY_MARK"))){
					continue;
				}
				indentifyMark= ((Map<String,String>)tmpMap).get("INDENTIFY_MARK");
			}else if(tmpMap instanceof EquityInfo001Dto){
				if(StringUtil.isNotEmpty(((EquityInfo001Dto) tmpMap).getOrgIndentifyMark())){
					continue;
				}
				indentifyMark= ((EquityInfo001Dto) tmpMap).getIndentifyMark();
			}
			key = getKey(tmpMap);
			EquityInfoRegist001Dto  dto = mapYbs.get(key);
			if(dto!=null && !indentifyMark.equals(dto.getIndentifyMark()) ){
				key1 = getKey(dto);

				if(key!=null)
				{
					if(key.equals(key1) && !indentifyMark.equals(dto.getIndentifyMark()) ){
						//插入CHECK表
						EquityInfo001Dto tmp = new EquityInfo001Dto();
						if(tmpMap  instanceof Map){
							EquityInfoMaintenanceService.maptoDto((Map<String,String>)tmpMap, tmp);
						}else if(tmpMap  instanceof EquityInfo001Dto){
							tmp = (EquityInfo001Dto)tmpMap;
						}
						if("01".equals(intype)){
							ChbEquHoldCheckDto tempModel = transformModelToTemp(tmp);
							tempModel.setMergerindentifymark(dto.getIndentifyMark());
							tempModel.setOperatestatus(DictCodeListExt2.处理状态_中债_已合并.getValue());
							chbEquHoldCheckDtoMapper.deleteCheckDataByPrimarykey(tempModel);
							chbEquHoldCheckDtoMapper.insert(tempModel);
							if(!existList.containsKey(dto.getIndentifyMark())){
								if(null == chbEquHoldCheckDtoMapper.selectByPrimarykey(dto.getIndentifyMark())){
									existList.put(dto.getIndentifyMark(), dto);
									ChbEquHoldCheckDto regDto = transformRegModelToTemp(dto);
									regDto.setMergerindentifymark(dto.getIndentifyMark());
									regDto.setOperatestatus(DictCodeListExt2.处理状态_中债_已合并.getValue());
									chbEquHoldCheckDtoMapper.insert(regDto);
								}
							}
						}
						//此数据不入维护表
						keys1.add(key);
						iteror.remove();
						continue;
					}
				}
			}
		}
		keys.removeAll(keys1);
		logger.info("step2跟已报送的数据进行对比合并入库结束");
	}


	/**
	 * 根据三要素(姓名+证件类型+证件号)来检查重复数据,如有重复则直接插入CHB_EQU_HOLD_CHECK
	 * @param equityList xml解析出来的身份信息
	 * @param intype 01 为进检查表 02 为不进检查表
	 */
	@SuppressWarnings("unchecked")
	public List<String> checkAllData(@SuppressWarnings("rawtypes") List equityList,List<String> keys,String intype){
		List<String> mergerkeys=new ArrayList<String>();
		if(StringUtil.isEmptyList(equityList)){
			return null;
		}

//		List<String> keys = getKeys(equityList);

		//step1、查询维护表以及CHB_EQU_HOLD_CHECK表中的含三要素的数据(去重)
		/**
		 * 场景：
		 *      上午接进一笔【张三+XXXXXXXX+001】的数据,此数据不重复未进入CHECK表;
		 *      下午又接进一笔【张三+XXXXXXXX+002】的数据,此时不查找维护表,无法判断是否重复;
		 */
		logger.info("查询库中未报送数据及check表数据开始");
		List<EquityInfo001Dto> infoList = getEquInfoList(keys,0);
		logger.info("查询库中未报送数据及check表数据结束");
		//step2、equityList、infoList去重合并
		logger.info("equityList、infoList去重合并开始");
		Map<String,List<EquityInfo001Dto>> allMap = getTotalMap(infoList,equityList);
		logger.info("equityList、infoList去重合并结束");
		String type="dto";
		if(equityList.get(0) instanceof Map){
			type="map";
		}
		logger.info("过滤识别标识变更数据开始");
		//原始别标识不为空的数据不能删除
		Object tmpMap = null;
		for (Iterator<T> iterator = equityList.iterator();iterator.hasNext();) {
			tmpMap = iterator.next();
			EquityInfo001Dto tmp = new EquityInfo001Dto();
			if(tmpMap instanceof Map){
				EquityInfoMaintenanceService.maptoDto((Map<String, String>)tmpMap, tmp);
				if(StringUtil.isNotEmpty(((Map<String,String>)tmpMap).get("ORG_INDENTIFY_MARK"))){
					continue;
				}
				iterator.remove();
			}else if(tmpMap instanceof  EquityInfo001Dto){
				tmp = (EquityInfo001Dto)tmpMap;
				if(StringUtil.isNotEmpty(((EquityInfo001Dto)tmp).getOrgIndentifyMark())){
					continue;
				}
				iterator.remove();
			}
		}
		logger.info("过滤识别标识变更数据结束");
		logger.info("数据比较入check表全删全插开始");
		for(Map.Entry<String, List<EquityInfo001Dto>> entry: allMap.entrySet()){
			if(entry.getValue().size() > 1){
				if("01".equals(intype)){
					//重复数据,CHECK表全删全插
					mergerkeys.add(entry.getKey());
					chbEquHoldCheckDtoMapper.delCheckDataBatch(entry.getKey());
					insertCheckData(entry.getValue());
				}
			}else{
				if(type.equals("map")){
					equityList.add(modelToMapByPrpperties(entry.getValue().get(0)));
				}else{
					equityList.add(entry.getValue().get(0));
				}
			}
		}
		logger.info("数据比较入check表全删全插结束");
		return mergerkeys;
	}

	/**
	 * 根据规则合并数据
	 * @param equityList
	 * @param keys
	 * @param type 调用方传参是map 还是dto
	 */
	@SuppressWarnings("unchecked")
	public void merger(@SuppressWarnings("rawtypes") List equityList,String type){
		//查询系统参数 是否自动处理 同身份信息多个有效识别标识
		String mergerFlag = SvrDataUtil.getSysParam("SVR_EQU_HOLD_MERGER");

//		List<String> keys = getKeys(equityList);
		List<ChbEquHoldCheckDto>  checkDataList = chbEquHoldCheckDtoMapper.mergerData(null);

		if("0".equals(mergerFlag)){
			manualMerger(checkDataList);
			return;
		}


		HashMap<String,ChbEquHoldCheckDto> lastMergerMap = new HashMap<String,ChbEquHoldCheckDto>();
		if("1".equals(mergerFlag)){
			//按照识别标识小的合并
			lastMergerMap = getMinMark(checkDataList);
		}else{
			//按照识别标识大的合并
			lastMergerMap = getMaxMark(checkDataList);
		}

		//CHECK表中合并识别标识按照规则刷新
		for(Map.Entry<String, ChbEquHoldCheckDto> entry: lastMergerMap.entrySet()){
			chbEquHoldCheckDtoMapper.updateMergerIndentifyMark(entry.getKey(),entry.getValue().getIndentifyMark());
			//返回规则合并后的数据给原逻辑处理
			if(type.equals("map")){
				equityList.add(modelToMapByPrpperties(transformModelToEquHold(entry.getValue())));
			}else{
				equityList.add(transformModelToEquHold(entry.getValue()));
			}
		}
	}

	/**
	 * 手动合并时，重复数据进入维护表，数据状态为待处理，且错误原因：识别标识不可用！该投资者存在多个识别标识
	 * @param equityList
	 */
	public void manualMerger(List<ChbEquHoldCheckDto>  checkDataList){
		List<EquityInfo001Dto> listN = new ArrayList<EquityInfo001Dto>();
		List<String> keys = new ArrayList<String>();
		for(ChbEquHoldCheckDto dto : checkDataList){
			EquityInfo001Dto info = transformModelToEquHold(dto);
			info.setStatus("A");
//			部门代码
//			info.setDeptCode(dto.getDeptCode());
//			机构代码
//			info.setOrgCode(dto.getOrgCode());
			info.setInterfCheckResult("识别标识不可用！该投资者存在多个识别标识");
			listN.add(info);
			keys.add(getKey(info));
		}
		deleteEquitInfo(keys);
		insertBtachInterfEquityInfoByPart(listN);
	}

	/**
	 * 分批次执行insertBtachInterfEquityInfo
	 *
	 * @param dataList 数据
	 * @see
	 * @since 1.0
	 */
	private void insertBtachInterfEquityInfoByPart(List<EquityInfo001Dto> dataList) {
		if (StringUtil.isEmptyList(dataList)) {
			return;
		}

		EquityInfo001Mapper equityInfo001Mapper = SpringContextUtil.getBean(EquityInfo001Mapper.class);
		int maxDataCountOneTime = parsToInt(reportSelectOrDeleteCountForShenFen,"1");
		int dataCount = dataList.size();
		if (dataCount <= maxDataCountOneTime) {
			equityInfo001Mapper.insertBtachInterfEquityInfo(dataList);
			return;
		}

		List<EquityInfo001Dto> newDataList = new ArrayList<EquityInfo001Dto>();
		newDataList.addAll(dataList);

		// 分批处理
		int part = dataCount / maxDataCountOneTime; // 分批数
		for (int i = 0; i < part; i++ ) {
			equityInfo001Mapper.insertBtachInterfEquityInfo(
					newDataList.subList(0, maxDataCountOneTime));
			newDataList.subList(0, maxDataCountOneTime).clear();
		}
		// 最后剩下的数据
		if (CollectionUtils.isNotEmpty(newDataList)) {
			equityInfo001Mapper.insertBtachInterfEquityInfo(newDataList);
		}
	}

	public int parsToInt(String count,String flag){
		if(StringUtil.isNotEmpty(count)&&Integer.parseInt(count)<=0){
			if("1".equals(flag)){
				return 20;
			}
			else{
				return 500;
			}
		}
		else{
			return Integer.parseInt(count);
		}
	}

	/**
	 * 三要素重复,根据合并规则取识别标识,则需要对之前的交易以及产品头寸的识别标识进行刷新
	 * @param equityList
	 * @param type 0:识别标识合并 1:识别标识变更
	 */
	public void refreshIndentifyMark(List<EquityInfo001Dto> list,int type){
		List<EquityInfo001Dto> markChangeList  = new ArrayList<EquityInfo001Dto>();
		if(1 == type){
			for(EquityInfo001Dto dto : list){
				if(StringUtil.isNotEmpty(dto.getOrgIndentifyMark())){
					markChangeList.add(dto);
				}
			}
			chbEquHoldCheckDtoMapper.refreshChangeIndentifyMark(markChangeList);
		}else{
//			chbEquHoldCheckDtoMapper.refreshMergerIndentifyMark(list);
		}
	}

	/**
	 * 取最大识别标识的记录
	 * @param checkDataList
	 * @param equityList
	 * @return
	 */
	private HashMap<String,ChbEquHoldCheckDto> getMaxMark(List<ChbEquHoldCheckDto> checkDataList) {
		HashMap<String,ChbEquHoldCheckDto> resultMap = new HashMap<String,ChbEquHoldCheckDto>();

		Map<String, List<ChbEquHoldCheckDto>> infoMap = checkDataList.stream().collect(JoyinCollectors.groupingBy(
				x -> getKey(x)));

		String key = "";
		for(Entry<String, List<ChbEquHoldCheckDto>> map : infoMap.entrySet()){
			key = map.getKey();
			ChbEquHoldCheckDto max = Collections.max(map.getValue(), new Comparator<ChbEquHoldCheckDto>(){

				@Override
				public int compare(ChbEquHoldCheckDto arg0, ChbEquHoldCheckDto arg1) {
					String iKey = getKey(arg0);
					String jKey = getKey(arg1);
					if(iKey!=null && iKey.equals(jKey)){
						if(("01".equals(arg0.getIsBelongBank()) && "01".equals(arg1.getIsBelongBank()))
								|| (!"01".equals(arg0.getIsBelongBank()) && !"01".equals(arg1.getIsBelongBank()))){
							return arg0.getIndentifyMark().compareTo(arg1.getIndentifyMark());
						}else{
							if("01".equals(arg0.getIsBelongBank())){
								return -1;
							}else if("01".equals(arg1.getIsBelongBank())){
								return -1;
							}else{
								return 0;
							}
						}
					}
					return 0;
				}
			});
			resultMap.put(key, max);
		}


		return resultMap;
	}


	/**
	 * 取最小识别标识的记录
	 * @param checkDataList
	 * @param equityList
	 * @return
	 */
	private HashMap<String,ChbEquHoldCheckDto> getMinMark(List<ChbEquHoldCheckDto> checkDataList) {

		HashMap<String,ChbEquHoldCheckDto> resultMap = new HashMap<String,ChbEquHoldCheckDto>();

		Map<String, List<ChbEquHoldCheckDto>> infoMap = checkDataList.stream().collect(JoyinCollectors.groupingBy(
				x -> getKey(x)));

		String key = "";
		for(Entry<String, List<ChbEquHoldCheckDto>> map : infoMap.entrySet()){
			key = map.getKey();
			ChbEquHoldCheckDto min = Collections.min(map.getValue(), new Comparator<ChbEquHoldCheckDto>(){

				@Override
				public int compare(ChbEquHoldCheckDto arg0, ChbEquHoldCheckDto arg1) {
					String iKey = getKey(arg0);
					String jKey = getKey(arg1);
					if(iKey!=null && iKey.equals(jKey)){
						if(("01".equals(arg0.getIsBelongBank()) && "01".equals(arg1.getIsBelongBank()))
								|| (!"01".equals(arg0.getIsBelongBank()) && !"01".equals(arg1.getIsBelongBank()))){
							return arg0.getIndentifyMark().compareTo(arg1.getIndentifyMark());
						}else{
							if("01".equals(arg0.getIsBelongBank())){
								return 1;
							}else if("01".equals(arg1.getIsBelongBank())){
								return 1;
							}else{
								return 0;
							}
						}
					}
					return 0;
				}
			});
			resultMap.put(key, min);
		}

		return resultMap;
	}

	/**
	 * 删除此次三要素中未报送的身份信息以及未报送的登记信息
	 * @param equityList
	 */
	public void deleteEquitInfoAndSub(List<String> keys){
		if(StringUtil.isEmptyList(keys)){
			return;
		}
		String mergerFlag = SvrDataUtil.getSysParam("SVR_EQU_HOLD_MERGER");
		if(!"0".equals(mergerFlag)){
			deleteEquitInfo(keys);
			deleteEquitInfoSub(keys);
		}
	}

	/**
	 * 分批获取已报送成功数据
	 * @param equityList
	 * @param keys
	 */
//	@SuppressWarnings("rawtypes")
	private List<EquityInfoRegist001Dto> getEquInfoSubList(List<String> keys,int type){
		List<EquityInfoRegist001Dto> resultList = new ArrayList<EquityInfoRegist001Dto>();
//		int maxDataCountOneTime =(null == reportSelectOrDeleteCountForShenFen ? 100:Integer.parseInt(reportSelectOrDeleteCountForShenFen));
//        int dataCount = 0;
//        if(keys != null){
//        	dataCount = keys.size();
//        }
//        if(0 == type){
//        	for(String key:keys){
//        		template.select("com.joyintech.report.submitmaintenance.equityinfo.dao.EquityInfoDistinctMapper.queryAllEquityInfoConStrastYbsSingle",
//        				key,new ResultHandler() {
//        			@Override
//        			public void handleResult(ResultContext resultContext) {
//        				resultList.add((EquityInfoRegist001Dto) resultContext.getResultObject());
//        			}
//        		});
//        	}
//        }else{
//        	return equityInfoDistinctMapper.queryEquityInfoRegistByPkUnsubmit(keys);
//        }
//        if (dataCount <= maxDataCountOneTime) {
//        	if(0 == type){
//        		resultList = equityInfoDistinctMapper.queryAllEquityInfoConStrastYbs(keys);
//        	}else{
		resultList = equityInfoDistinctMapper.queryEquityInfoRegistByPkUnsubmit(keys);
//        	}
//            return resultList;
//        }
//
//        List<String> newDataList = new ArrayList<String>();
//        newDataList.addAll(keys);
//
//        // 分批处理
//        int part = dataCount / maxDataCountOneTime; // 分批数
//        for (int i = 0; i < part; i++ ) {
//        	if(0 == type){
//        		resultList.addAll(equityInfoDistinctMapper.queryAllEquityInfoConStrastYbs(newDataList.subList(0, maxDataCountOneTime)));
//        	}else{
//        		resultList.addAll(equityInfoDistinctMapper.queryEquityInfoRegistByPkUnsubmit(newDataList.subList(0, maxDataCountOneTime)));
//        	}
//        	newDataList.subList(0, maxDataCountOneTime).clear();
//        }
//        // 最后剩下的数据
//        if (CollectionUtils.isNotEmpty(newDataList)) {
//        	if(0 == type){
//        		resultList.addAll(equityInfoDistinctMapper.queryAllEquityInfoConStrastYbs(newDataList));
//        	}else{
//        		resultList.addAll(equityInfoDistinctMapper.queryEquityInfoRegistByPkUnsubmit(newDataList));
//        	}
//        }
		return resultList;
	}

	@SuppressWarnings({ "rawtypes" })
	public List<EquityInfoRegist001Dto> getEquInfoSubListYbs(List equityList){
		List<EquityInfoRegist001Dto> resultList = new ArrayList<EquityInfoRegist001Dto>();
//		int maxDataCountOneTime =(null == reportSelectOrDeleteCountForShenFen ? 100:Integer.parseInt(reportSelectOrDeleteCountForShenFen));
//        int dataCount = 0;
//     if(equityList!=null){
//    	 dataCount=equityList.size();
//     }
		Map<String,Object> params = new HashMap<>();
		List<String> keys = getKeys(equityList);
		params.put("keys", keys);
		template.select("com.joyintech.tams.report.submitmaintenance.equityinfo.dao.EquityInfoDistinctMapper.queryAllEquityInfo",params,
				new ResultHandler() {
					@Override
					public void handleResult(ResultContext resultContext) {
						resultList.add((EquityInfoRegist001Dto) resultContext.getResultObject());
					}
				});
//        template.select("com.joyintech.tams.report.submitmaintenance.equityinfo.dao.EquityInfoDistinctMapper.queryAllEquityInfo",keys,
//        		new ResultHandler() {
//        	@Override
//        	public void handleResult(ResultContext resultContext) {
//        		resultList.add((EquityInfoRegist001Dto) resultContext.getResultObject());
//        	}
//        });

//        	
//        if (dataCount <= maxDataCountOneTime) {
//        	
//        		resultList = equityInfoDistinctMapper.queryAllEquityInfoConStrastYbs(equityList);
//        	
//            return resultList;
//        }
//
//        List newDataList = new ArrayList();
//        newDataList.addAll(equityList);
//
//        // 分批处理
//        int part = dataCount / maxDataCountOneTime; // 分批数
//        for (int i = 0; i < part; i++ ) {
//        	
//        		resultList.addAll(equityInfoDistinctMapper.queryAllEquityInfoConStrastYbs(newDataList.subList(0, maxDataCountOneTime)));
//        	
//        	newDataList.subList(0, maxDataCountOneTime).clear();
//        }
//        // 最后剩下的数据
//        if (CollectionUtils.isNotEmpty(newDataList)) {
//        		resultList.addAll(equityInfoDistinctMapper.queryAllEquityInfoConStrastYbs(newDataList));
//        }

		return resultList;
	}
	/**
	 * 分批获取未报送数据
	 * @param equityList
	 * @param keys
	 */
	@SuppressWarnings("rawtypes")
	private List<EquityInfo001Dto> getEquInfoList(List<String> keys,int type){
		List<EquityInfo001Dto> resultList = new ArrayList<EquityInfo001Dto>();
//		int maxDataCountOneTime =(null == reportSelectOrDeleteCountForShenFen ? 100:Integer.parseInt(reportSelectOrDeleteCountForShenFen));
//		int dataCount=0;
//		if(keys != null){
//			dataCount = keys.size();
//        }
//        if (dataCount <= maxDataCountOneTime) {
		if(0 == type){
			List<EquityInfo001Dto>	resultListAll=new  ArrayList<EquityInfo001Dto>();
			logger.info("查询库中未报送数据及check表数据SQL流式查询开始");
//	       		 if(null!=keys&&keys.size()==1){
//	       			 resultListAll.addAll(equityInfoDistinctMapper.queryEquityInfoList(keys));
//	       		 }else{
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("keys", keys);
			template.select("com.joyintech.tams.report.submitmaintenance.equityinfo.dao.EquityInfoDistinctMapper.queryEquityInfoList",params,
					new ResultHandler() {
						@Override
						public void handleResult(ResultContext resultContext) {
							EquityInfo001Dto pos = (EquityInfo001Dto) resultContext.getResultObject();
							resultListAll.add(pos);
						}
					});
//        		 }
			logger.info("查询库中未报送数据及check表数据SQL流式查询结束");
//        		List<EquityInfo001Dto>	resultListAll = equityInfoDistinctMapper.queryEquityInfoList(keys);
			if(resultListAll==null||resultListAll.isEmpty()){
				return resultList;
			}
			logger.info("resultListAll数据转换成Map开始");
			Map<String, List<EquityInfo001Dto>> allListMap=resultListAll.stream().collect(
					JoyinCollectors.groupingBy(
							single ->
									(single.getBankCode()==null?"":single.getBankCode())+GlobalConstant.UNDER_LINE +(single.getIdentifyNumber()==null?"":single.getIdentifyNumber())
											+GlobalConstant.UNDER_LINE+(single.getHolderName()==null?"":single.getHolderName())+GlobalConstant.UNDER_LINE
											+(single.getPersonalIdentifyType()==null?"":single.getPersonalIdentifyType())+GlobalConstant.UNDER_LINE+(single.getInstitutionIdentifyType()==null?"":single.getInstitutionIdentifyType())
											+GlobalConstant.UNDER_LINE+(single.getOtherIdentifyName()==null?"":single.getOtherIdentifyName())));
			logger.info("resultListAll数据转换成Map结束");
			logger.info("循环keys取存在于kes中的未报送数据开始");
			for(String key:keys){
//        			String key= single.getBankCode()==null?"":single.getBankCode()+GlobalConstant.UNDER_LINE +single.getIdentifyNumber()==null?"":single.getIdentifyNumber()
//        			+GlobalConstant.UNDER_LINE+single.getHolderName()==null?"":single.getHolderName()+GlobalConstant.UNDER_LINE
//        			+single.getPersonalIdentifyType()==null?"":single.getPersonalIdentifyType()+GlobalConstant.UNDER_LINE+single.getInstitutionIdentifyType()==null?"":single.getInstitutionIdentifyType()
//        			+GlobalConstant.UNDER_LINE+single.getOtherIdentifyName()==null?"":single.getOtherIdentifyName();
				if(allListMap.containsKey(key)){
					resultList.addAll(allListMap.get(key));
				}
			}
			logger.info("循环keys取存在于kes中的未报送数据结束");
		}else{
			resultList = equityInfoDistinctMapper.queryRepeatEquityInfoUnSubmit(keys);
		}
		return resultList;
//        }
//
//        List<String> newDataList = new ArrayList<String>();
//        newDataList.addAll(keys);
//
//        // 分批处理
//        int part = dataCount / maxDataCountOneTime; // 分批数
//        for (int i = 0; i < part; i++ ) {
//        	if(0 == type){
//        		resultList.addAll(equityInfoDistinctMapper.queryEquityInfoList(newDataList.subList(0, maxDataCountOneTime)));
//        	}else{
//        		resultList.addAll(equityInfoDistinctMapper.queryRepeatEquityInfoUnSubmit(newDataList.subList(0, maxDataCountOneTime)));
//        	}
//        	newDataList.subList(0, maxDataCountOneTime).clear();
//        }
//        // 最后剩下的数据
//        if (CollectionUtils.isNotEmpty(newDataList)) {
//        	if(0 == type){
//        		resultList.addAll(equityInfoDistinctMapper.queryEquityInfoList(newDataList));
//        	}else{
//        		resultList.addAll(equityInfoDistinctMapper.queryRepeatEquityInfoUnSubmit(newDataList));
//        	}
//        }
//        return resultList;
	}

	/**
	 * 维护表删除
	 * @param equityList
	 * @param keys
	 */
	private void deleteEquitInfo(List<String> keys){
		int maxDataCountOneTime =(null == reportSelectOrDeleteCountForShenFen ? 100:Integer.parseInt(reportSelectOrDeleteCountForShenFen));
		int dataCount=0;
		if(CollectionUtils.isEmpty(keys)){
			return;
		}

		if(keys !=null){
			dataCount = keys.size();
		}
		if (CollectionUtils.isNotEmpty(keys)&&dataCount <= maxDataCountOneTime) {
			chbEquHoldCheckDtoMapper.deleteEquityInfoBatch(keys);
			return;
		}

		List<String> newDataList = new ArrayList<String>();
		newDataList.addAll(keys);

		// 分批处理
		int part = dataCount / maxDataCountOneTime; // 分批数
		for (int i = 0; i < part; i++ ) {
			chbEquHoldCheckDtoMapper.deleteEquityInfoBatch(newDataList.subList(0, maxDataCountOneTime));
			newDataList.subList(0, maxDataCountOneTime).clear();
		}
		// 最后剩下的数据
		if (CollectionUtils.isNotEmpty(newDataList)) {
			chbEquHoldCheckDtoMapper.deleteEquityInfoBatch(newDataList);
		}
	}

	/**
	 * 登记表删除
	 * @param equityList
	 * @param keys
	 */
	private void deleteEquitInfoSub(List<String> keys){

		int maxDataCountOneTime =(null == reportSelectOrDeleteCountForShenFen ? 100:Integer.parseInt(reportSelectOrDeleteCountForShenFen));
		int dataCount = 0;
		if(CollectionUtils.isEmpty(keys)){
			return;
		}
		if(keys != null){
			dataCount = keys.size();
		}
		if (CollectionUtils.isNotEmpty(keys)&&dataCount <= maxDataCountOneTime) {
			chbEquHoldCheckDtoMapper.deleteEquitySubInfoBatch(keys);
			return;
		}

		List<String> newDataList = new ArrayList<String>();
		newDataList.addAll(keys);

		// 分批处理
		int part = dataCount / maxDataCountOneTime; // 分批数
		for (int i = 0; i < part; i++ ) {
			chbEquHoldCheckDtoMapper.deleteEquitySubInfoBatch(newDataList.subList(0, maxDataCountOneTime));
			newDataList.subList(0, maxDataCountOneTime).clear();
		}
		// 最后剩下的数据
		if (CollectionUtils.isNotEmpty(newDataList)) {
			chbEquHoldCheckDtoMapper.deleteEquitySubInfoBatch(newDataList);
		}
	}

	/**
	 * 持久化数据库
	 * @param list
	 */
	private void insertCheckData(List<EquityInfo001Dto> list) {
		SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
		//批量插入mapper
		ChbEquHoldCheckDtoMapper mapper =  session.getMapper(ChbEquHoldCheckDtoMapper.class);
		try{
			int count = 0;
			ChbEquHoldCheckDto model = null;
			for(EquityInfo001Dto dto : list){
				model = transformModelToTemp(dto);
				model.setStatus(DictCodeListExt1.维护数据状态_有效.getValue());
				if(!DictCodeListExt2.处理状态_中债_已合并.getValue().equals(model.getOperatestatus())){
					model.setOperatestatus(DictCodeListExt2.处理状态_中债_未合并.getValue());
				}
				mapper.insert(model);
				count++;
				if(count % 1000==0){
					session.commit();
					session.clearCache();
				}
			}
			session.commit();
			session.clearCache();
		}
		finally {
			session.close();
		}

	}

	/**
	 * 返回数据类型：
	 *    key:三要素  value:身份信息List
	 * 比如：
	 *   key: 张三+身份证+340202021111
	 * value: 张三+身份证+340202021111+001的身份信息  
	 * 		     张三+身份证+340202021111+002的身份信息
	 * 		     张三+身份证+340202021111+003的身份信息
	 * @param infoList
	 * @param equityList
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Map<String,List<EquityInfo001Dto>> getTotalMap(List<EquityInfo001Dto> infoList,
														   @SuppressWarnings("rawtypes") List equityList){
		Map<String,EquityInfo001Dto> dictictMap = new HashMap<String,EquityInfo001Dto>();

		String key = "";
		for(EquityInfo001Dto dto : infoList){
			key = getKey(dto) + GlobalConstant.UNDER_LINE + dto.getIndentifyMark();

			if(!dictictMap.containsKey(key)){
				dictictMap.put(key, dto);
			}
		}

		for (Object map : equityList) {
			EquityInfo001Dto tmp = new EquityInfo001Dto();
			if(map instanceof Map){
				EquityInfoMaintenanceService.maptoDto((Map<String, String>)map, tmp);
				if(StringUtil.isNotEmpty(((Map<String,String>)map).get("ORG_INDENTIFY_MARK"))){
					continue;
				}
			}else if(map instanceof  EquityInfo001Dto){
				tmp = (EquityInfo001Dto)map;
				if(StringUtil.isNotEmpty(((EquityInfo001Dto)tmp).getOrgIndentifyMark())){
					continue;
				}
			}
			key = getKey(tmp) + GlobalConstant.UNDER_LINE + tmp.getIndentifyMark();
			//三要素+识别标识一致 则认为是同一条数据，覆盖原有数据,以文件中最新为准
			dictictMap.put(key, tmp);
		}

		List<EquityInfo001Dto> allList = new ArrayList<EquityInfo001Dto>();
		for(Map.Entry<String, EquityInfo001Dto> entry: dictictMap.entrySet()){
			allList.add(entry.getValue());
		}

		Map<String, List<EquityInfo001Dto>> infoMap = allList.stream().collect(JoyinCollectors.groupingBy(
				x -> getKey(x)));


//		//文件中的数据进行分组
//		Map<String, List<EquityInfo001Dto>> fileMap = tmpList.stream().collect(JoyinCollectors.groupingBy(
//											                x -> getKey(x)));// 其他证件名称

//		//【三要素+识别标识】相同的数据，以文件中的最新数据为准
//		if(!infoMap.isEmpty()){
//			resultMap.putAll(infoMap);
//		}
//		if(!fileMap.isEmpty()){
//			String key = "";
//			String key1 = "";
//			//对比文件中的数据，三要素相同且识别标识一致，则以文件中数据为准
//			List<EquityInfo001Dto> newList = null;
//			
//			for(Map.Entry<String, List<EquityInfo001Dto>> entry: fileMap.entrySet()){
//				
//				if(resultMap.containsKey(entry.getKey())){
//					newList = new ArrayList<EquityInfo001Dto>();
//					
//					List<EquityInfo001Dto> list = resultMap.get(entry.getKey());
//					
//					//找重复数据,以文件最新为准
//					label:
//					for(Iterator<EquityInfo001Dto> iterator = list.iterator();iterator.hasNext();){
//						EquityInfo001Dto dto = iterator.next();
//						key = getKey(dto);
//						
//						for(EquityInfo001Dto model : entry.getValue()){
//							key1 =  getKey(model);
//							if((key + GlobalConstant.UNDER_LINE + dto.getIndentifyMark())
//							   .equals(key1 + GlobalConstant.UNDER_LINE + model.getIndentifyMark())){
//								//以文件中最新的为准
//								newList.add(model);
//								continue label;
//							}else{
//								newList.add(model);
//							}
//						}
//						newList.add(dto);
//					}
//					resultMap.put(entry.getKey(), newList);
//				}else{
//					resultMap.put(entry.getKey(), entry.getValue());
//				}
//			}
//		}

		return infoMap;
	}

	/**
	 *
	 * @param dto
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private String getKey(Object dto) {
		if(dto instanceof EquityInfo001Dto){
			return  (null == ((EquityInfo001Dto) dto).getBankCode()?"":((EquityInfo001Dto) dto).getBankCode())//注册银行
					+ GlobalConstant.UNDER_LINE + (null == ((EquityInfo001Dto) dto).getIdentifyNumber()?"":((EquityInfo001Dto) dto).getIdentifyNumber())//证件号码
					+ GlobalConstant.UNDER_LINE + (null == ((EquityInfo001Dto) dto).getHolderName()?"":((EquityInfo001Dto) dto).getHolderName())//权益人名称
					+ GlobalConstant.UNDER_LINE + (null == ((EquityInfo001Dto) dto).getPersonalIdentifyType()?"":((EquityInfo001Dto) dto).getPersonalIdentifyType())//个人证件类别
					+ GlobalConstant.UNDER_LINE + (null == ((EquityInfo001Dto) dto).getInstitutionIdentifyType()?"":((EquityInfo001Dto) dto).getInstitutionIdentifyType())//机构证件类别
					+ GlobalConstant.UNDER_LINE + (null == ((EquityInfo001Dto) dto).getOtherIdentifyName()?"":((EquityInfo001Dto) dto).getOtherIdentifyName());// 其他证件名称

		}else if(dto instanceof EquityInfoRegist001Dto){
			return (null == ((EquityInfoRegist001Dto) dto).getBankCode()?"":((EquityInfoRegist001Dto) dto).getBankCode())//注册银行
					+ GlobalConstant.UNDER_LINE + (null == ((EquityInfoRegist001Dto) dto).getIdentifyNumber()?"":((EquityInfoRegist001Dto) dto).getIdentifyNumber())//证件号码
					+ GlobalConstant.UNDER_LINE + (null == ((EquityInfoRegist001Dto) dto).getHolderName()?"":((EquityInfoRegist001Dto) dto).getHolderName())//权益人名称
					+ GlobalConstant.UNDER_LINE + (null == ((EquityInfoRegist001Dto) dto).getPersonalIdentifyType()?"":((EquityInfoRegist001Dto) dto).getPersonalIdentifyType())//个人证件类别
					+ GlobalConstant.UNDER_LINE + (null == ((EquityInfoRegist001Dto) dto).getInstitutionIdentifyType()?"":((EquityInfoRegist001Dto) dto).getInstitutionIdentifyType())//机构证件类别
					+ GlobalConstant.UNDER_LINE + (null == ((EquityInfoRegist001Dto) dto).getOtherIdentifyName()?"":((EquityInfoRegist001Dto) dto).getOtherIdentifyName());// 其他证件名称
		}else if(dto instanceof ChbEquHoldCheckDto){
			return (null == ((ChbEquHoldCheckDto) dto).getRegistBankCode()?"":((ChbEquHoldCheckDto) dto).getRegistBankCode())//注册银行
					+ GlobalConstant.UNDER_LINE + (null == ((ChbEquHoldCheckDto) dto).getIdentifyNumber()?"":((ChbEquHoldCheckDto) dto).getIdentifyNumber())//证件号码
					+ GlobalConstant.UNDER_LINE + (null == ((ChbEquHoldCheckDto) dto).getHolderName()?"":((ChbEquHoldCheckDto) dto).getHolderName())//权益人名称
					+ GlobalConstant.UNDER_LINE + (null == ((ChbEquHoldCheckDto) dto).getPersonalIdentifyType()?"":((ChbEquHoldCheckDto) dto).getPersonalIdentifyType())//个人证件类别
					+ GlobalConstant.UNDER_LINE + (null == ((ChbEquHoldCheckDto) dto).getInstitutionIdentifyType()?"":((ChbEquHoldCheckDto) dto).getInstitutionIdentifyType())//机构证件类别
					+ GlobalConstant.UNDER_LINE + (null == ((ChbEquHoldCheckDto) dto).getOtherIdentifyName()?"":((ChbEquHoldCheckDto) dto).getOtherIdentifyName());// 其他证件名称
		}else if(dto instanceof Map){
			return (null == ((Map<String, String>)dto).get("REGIST_BANK_CODE") ? "" : ((Map<String, String>) dto).get("REGIST_BANK_CODE"))//注册银行
					+ GlobalConstant.UNDER_LINE + (null == ((Map<String, String>) dto).get("IDENTIFY_NUMBER") ? "" : ((Map<String, String>) dto).get("IDENTIFY_NUMBER"))//证件号码
					+ GlobalConstant.UNDER_LINE + (null == ((Map<String, String>) dto).get("HOLDER_NAME") ? "" : ((Map<String, String>) dto).get("HOLDER_NAME"))//权益人名称
					+ GlobalConstant.UNDER_LINE + (null == ((Map<String, String>) dto).get("PERSONAL_IDENTIFY_TYPE") ? "" : ((Map<String, String>) dto).get("PERSONAL_IDENTIFY_TYPE"))//个人证件类别
					+ GlobalConstant.UNDER_LINE + (null == ((Map<String, String>) dto).get("INSTITUTION_IDENTIFY_TYPE") ? "" : ((Map<String, String>) dto).get("INSTITUTION_IDENTIFY_TYPE"))//机构证件类别
					+ GlobalConstant.UNDER_LINE + (null == ((Map<String, String>) dto).get("OTHER_IDENTIFY_NAME") ? "" : ((Map<String, String>) dto).get("OTHER_IDENTIFY_NAME"));
		}else{

		}
		return null;
	}

	/**
	 *
	 * @param equityList
	 * @return
	 */
	public List<String> getKeys(@SuppressWarnings("rawtypes") List equityList) {
		List<String> keys = new ArrayList<String>();
		Set<String> skeys = new HashSet<String>();
		String key = "";
		for(Object map : equityList ){
			key = getKey(map);
			skeys.add(key);
		}
		for(String sk:skeys){
			keys.add(sk);
		}
		return keys;
	}

	/**
	 *
	 * @param equityInfo001Dto
	 * @return
	 */
	private Map<String,String> modelToMapByPrpperties(EquityInfo001Dto equityInfo001Dto){
		Map<String,String> map = new HashMap<String,String>();
		if("001".equals(CacheUtil.getSysParam("CUSTOMER_BANK_CODE"))){
			map.put("INDENTIFY_MARK", equityInfo001Dto.getIndentifyMark()== null ? "" : equityInfo001Dto.getIndentifyMark());
			map.put("ORG_INDENTIFY_MARK",equityInfo001Dto.getOrgIndentifyMark()== null ? "" : equityInfo001Dto.getOrgIndentifyMark());
			map.put("REGIST_BANK_CODE",equityInfo001Dto.getBankCode()== null ? "" : equityInfo001Dto.getBankCode());
			map.put("IS_BELONG_BANK",equityInfo001Dto.getIsBelongBank()== null ? "" : equityInfo001Dto.getIsBelongBank());
			map.put("THE_BANK_NAME",equityInfo001Dto.getTheBankName()== null ? "" : equityInfo001Dto.getTheBankName());
			map.put("THE_BANK_CODE",equityInfo001Dto.getTheBankCode()== null ? "" : equityInfo001Dto.getTheBankCode());
			map.put("HOLDER_MARK",equityInfo001Dto.getHolderMark()== null ? "" : equityInfo001Dto.getHolderMark());
			map.put("HOLDER_REGION",equityInfo001Dto.getHolderRegion()== null ? "" : equityInfo001Dto.getHolderRegion());
			map.put("IDENTIFY_NUMBER",equityInfo001Dto.getIdentifyNumber()== null ? "" : equityInfo001Dto.getIdentifyNumber());
			map.put("HOLDER_TYPE",equityInfo001Dto.getHolderType()== null ? "" : equityInfo001Dto.getHolderType());
			map.put("PERSONAL_IDENTIFY_TYPE",equityInfo001Dto.getPersonalIdentifyType()== null ? "" : equityInfo001Dto.getPersonalIdentifyType());
			map.put("INSTITUTION_IDENTIFY_TYPE",equityInfo001Dto.getInstitutionIdentifyType()== null ? "" : equityInfo001Dto.getInstitutionIdentifyType());
			map.put("OTHER_IDENTIFY_NAME",equityInfo001Dto.getOtherIdentifyName()== null ? "" : equityInfo001Dto.getOtherIdentifyName());
			map.put("SPV_CAP_ACC_BANK",equityInfo001Dto.getSpvCapAccBank()== null ? "" :equityInfo001Dto.getSpvCapAccBank());
			map.put("OTHER_CAP_ACC_BANK",equityInfo001Dto.getOtherCapAccBank()== null ? "" : equityInfo001Dto.getOtherCapAccBank());
			map.put("SPV_REGIST_CODE",equityInfo001Dto.getSpvRegistCode()== null ? "" : equityInfo001Dto.getSpvRegistCode());
			map.put("HOLDER_DATE",null == equityInfo001Dto.getHolderDate() ? "" : equityInfo001Dto.getHolderDate().toString());
			map.put("HOLDER_NAME", equityInfo001Dto.getHolderName()== null ? "" : equityInfo001Dto.getHolderName());
			map.put("HOLDER_SEX",equityInfo001Dto.getHolderSex()== null ? "" : equityInfo001Dto.getHolderSex());
			map.put("HOLDER_RISK_PREFER",equityInfo001Dto.getHolderRiskPrefer()== null ? "" : equityInfo001Dto.getHolderRiskPrefer());
			map.put("HOLDER_CELLPHONE",equityInfo001Dto.getHolderCellphone()== null ? "" : equityInfo001Dto.getHolderCellphone());
			map.put("HOLDER_TELEPHONE",equityInfo001Dto.getHolderTelephone()== null ? "" : equityInfo001Dto.getHolderTelephone());
			map.put("HOLDER_EMAIL", equityInfo001Dto.getHolderEmail()== null ? "" : equityInfo001Dto.getHolderEmail());
			map.put("REMARK",equityInfo001Dto.getRemark()== null ? "" : equityInfo001Dto.getRemark());
			map.put("ORG_CODE",equityInfo001Dto.getOrgCode()== null ? "C0403" : equityInfo001Dto.getOrgCode() );
			map.put("AGENT",equityInfo001Dto.getAgent()== null ? "" : equityInfo001Dto.getAgent());
			map.put("DEPT_CODE",equityInfo001Dto.getDeptCode()== null ? "" : equityInfo001Dto.getDeptCode());
			map.put("DATATYPE",equityInfo001Dto.getDatatype()== null ? "01" : equityInfo001Dto.getDatatype());
		}else{
			map.put("INDENTIFY_MARK", equityInfo001Dto.getIndentifyMark());
			map.put("ORG_INDENTIFY_MARK",equityInfo001Dto.getOrgIndentifyMark());
			map.put("REGIST_BANK_CODE",equityInfo001Dto.getBankCode());
			map.put("IS_BELONG_BANK",equityInfo001Dto.getIsBelongBank());
			map.put("THE_BANK_NAME",equityInfo001Dto.getTheBankName());
			map.put("THE_BANK_CODE",equityInfo001Dto.getTheBankCode());
			map.put("HOLDER_MARK",equityInfo001Dto.getHolderMark());
			map.put("HOLDER_REGION",equityInfo001Dto.getHolderRegion());
			map.put("IDENTIFY_NUMBER",equityInfo001Dto.getIdentifyNumber());
			map.put("HOLDER_TYPE",equityInfo001Dto.getHolderType());
			map.put("PERSONAL_IDENTIFY_TYPE",equityInfo001Dto.getPersonalIdentifyType());
			map.put("INSTITUTION_IDENTIFY_TYPE",equityInfo001Dto.getInstitutionIdentifyType());
			map.put("OTHER_IDENTIFY_NAME",equityInfo001Dto.getOtherIdentifyName());
			map.put("SPV_CAP_ACC_BANK",equityInfo001Dto.getSpvCapAccBank());
			map.put("OTHER_CAP_ACC_BANK",equityInfo001Dto.getOtherCapAccBank());
			map.put("SPV_REGIST_CODE",equityInfo001Dto.getSpvRegistCode());
			map.put("HOLDER_DATE",null == equityInfo001Dto.getHolderDate() ? "" : equityInfo001Dto.getHolderDate().toString());
			map.put("HOLDER_NAME", equityInfo001Dto.getHolderName());
			map.put("HOLDER_SEX",equityInfo001Dto.getHolderSex());
			map.put("HOLDER_RISK_PREFER",equityInfo001Dto.getHolderRiskPrefer());
			map.put("HOLDER_CELLPHONE",equityInfo001Dto.getHolderCellphone());
			map.put("HOLDER_TELEPHONE",equityInfo001Dto.getHolderTelephone());
			map.put("HOLDER_EMAIL", equityInfo001Dto.getHolderEmail());
			map.put("REMARK",equityInfo001Dto.getRemark());
			map.put("ORG_CODE",equityInfo001Dto.getOrgCode());
			map.put("AGENT",equityInfo001Dto.getAgent());
			map.put("DEPT_CODE",equityInfo001Dto.getDeptCode());
		}
		return map;
	}

	/**
	 *
	 * @param dto
	 * @return
	 */
	private EquityInfo001Dto transformModelToEquHold(ChbEquHoldCheckDto dto){
		EquityInfo001Dto temp = new EquityInfo001Dto();
		BeanUtils.copyProperties(dto,temp);
		temp.setBankCode(dto.getRegistBankCode());
		return temp;

	}

	/**
	 *
	 * @param equityInfo001Dto
	 * @return
	 */
	private ChbEquHoldCheckDto transformModelToTemp(EquityInfo001Dto equityInfo001Dto){
		ChbEquHoldCheckDto temp=new ChbEquHoldCheckDto();
		BeanUtils.copyProperties(equityInfo001Dto,temp);
		temp.setRegistBankCode(equityInfo001Dto.getBankCode());
		return temp;

	}

	/**
	 *
	 * @param equityInfo001Dto
	 * @return
	 */
	private ChbEquHoldCheckDto transformRegModelToTemp(EquityInfoRegist001Dto equityInfo001Dto){
		ChbEquHoldCheckDto temp=new ChbEquHoldCheckDto();
		BeanUtils.copyProperties(equityInfo001Dto,temp);
		temp.setRegistBankCode(equityInfo001Dto.getBankCode());
		return temp;
	}
	public List<EquityInfoRegist001Dto> checkBeforeSubmit(){
		return checkBeforeSubmit(null,"02");
	}
	/**
	 * 查询出未报送且在维护表中有重复的身份信息
	 * @param type 01 数据检查 02自动检查报送
	 * @return
	 */
	public List<EquityInfoRegist001Dto> checkBeforeSubmit(List<EquityInfoRegist001Dto> registList,String type){
		List<String> keyst = null;
		if(registList!=null){
			keyst = getKeys(registList);
		}
		List<EquityInfo001Dto> listR= getEquInfoList(keyst,1);
		if(listR!=null&&!listR.isEmpty()){

			String mergerFlag = SvrDataUtil.getSysParam("SVR_EQU_HOLD_MERGER");
			if("0".equals(mergerFlag)){//如果是手动
				//插入检查表
//				ChbEquHoldCheckDto model = null;
//				for(EquityInfo001Dto regDto: listR){
//					model = transformModelToTemp(regDto);
//					model.setOperatestatus(DictCodeListExt2.处理状态_中债_未合并.getValue());
//					 if(null == chbEquHoldCheckDtoMapper.selectByPrimarykey(model.getIndentifyMark())){
//						 chbEquHoldCheckDtoMapper.insert(model);
//					 }
//				}
				//删除维护表和登记表
				List<String> keys = new ArrayList<>();
				for(EquityInfo001Dto single:listR){
					String key=single.getBankCode()+GlobalConstant.UNDER_LINE +single.getIndentifyMark();
					keys.add(key);
				}
//				chbEquHoldCheckDtoMapper.deleteEquityInfoBatch(keys);
//	        	chbEquHoldCheckDtoMapper.deleteEquitySubInfoBatch(keys);
				//查询出对应的未报送登记信息
				List<EquityInfoRegist001Dto> djlist= getEquInfoSubList(keys,1);
				checkAllData(listR, getKeys(listR),"01");
				//更新原检查表和登记表检查检查状态
				dochecksafe(djlist,"02");
				if("01".equals(type)){
					for(EquityInfoRegist001Dto single:djlist){
						single.setRemark("检查不通过，该投资者存在多个识别标识！");
					}
					return djlist;
				}
			}else{
				//插入检查表
//				ChbEquHoldCheckDto model = null;
//				for(EquityInfo001Dto regDto: listR){
//					model = transformModelToTemp(regDto);
//					model.setOperatestatus(DictCodeListExt2.处理状态_中债_未合并.getValue());
//					 if(null == chbEquHoldCheckDtoMapper.selectByPrimarykey(model.getIndentifyMark())){
//						 chbEquHoldCheckDtoMapper.insert(model);
//					 }
//				}
				//删除维护表和登记表
				//删除维护表和登记表
				List<String> keys = getKeys(listR);
//				chbEquHoldCheckDtoMapper.deleteEquityInfoBatch(keys);
				deleteEquitInfo(keys);
//	        	chbEquHoldCheckDtoMapper.deleteEquitySubInfoBatch(keys);
				deleteEquitInfoSub(keys);
				checkAllData(listR, keys,"01");
				//调用合并处理
				List<EquityInfo001Dto> listN=new ArrayList<EquityInfo001Dto>();
				merger(listN,"dto");
				List<Map<String, String>> listType = new ArrayList<Map<String, String>>();
				for(EquityInfo001Dto single:listN){
					Map<String,String > map=new HashMap<String,String>();
					if (StringUtil.isNotEmpty(single.getOrgIndentifyMark())
							&& !single.getIndentifyMark().equals(
							single.getOrgIndentifyMark())) {
						map.put("type", "02");
					} else {
						map.put("type", "01");

					}
					listType.add(map);
				}
				//合并后的结果调用原来接口入库逻辑
				EquityInfo001Service equityInfoDistinctService =
						SpringContextUtil.getBean(EquityInfo001Service.class);
				equityInfoDistinctService.dealAfterCheck(listType, listN,DictCodeListExt2.数据来源_EXCEL导入.getValue(),null, null);
				List<String> keys1 = new ArrayList<>();
				for(EquityInfo001Dto single:listN){
					String key=single.getBankCode()+GlobalConstant.UNDER_LINE +single.getIndentifyMark();
					keys1.add(key);
				}
//				chbEquHoldCheckDtoMapper.deleteEquityInfoBatch(keys);
//	        	chbEquHoldCheckDtoMapper.deleteEquitySubInfoBatch(keys);
				//查询出对应的未报送登记信息
				List<EquityInfoRegist001Dto> djlist = getEquInfoSubList(keys1,1);
				//更新原检查表和登记表检查检查状态
				dochecksafe(djlist,"01");
				if("01".equals(type)){
					for(EquityInfoRegist001Dto single:djlist){
						single.setRemark("检查通过！");
					}
				}
				return djlist;
			}
		}else{
			if("02".equals(type))
				return registList;
		}
		return null;
	}
	void dochecksafe(List<EquityInfoRegist001Dto> equityInfoRegistRstList,String type){
		List<EquityInfoRegist001Dto> equityInfoRegistUpdateList = new ArrayList<>();
		List<CheckReport001Dto> resultList = new ArrayList<>();
		List<String> checkseqnos= new ArrayList<String>();
		if(CollectionUtils.isNotEmpty(equityInfoRegistRstList)){
			equityInfoRegistRstList.forEach(item->{
				if(item.getCheckSeqno()!=null&&!item.getCheckSeqno().equals("")&&!item.getCheckSeqno().equals("0")){
					checkseqnos.add(item.getCheckSeqno());
				}
				//检查结果
				CheckReport001Dto chbReportCheck = new CheckReport001Dto();
				BeanUtil.copyPropertiesIgnoreNull(chbReportCheck, item);
				chbReportCheck.setCheckSeqno(CommonUtil.getSequenceIndexData(DataIndexSequenceType.检查序列号));
				chbReportCheck.setRepCode(DictCodeListExt2.报表名称_投资者身份信息登记.getValue());
				chbReportCheck.setRepName(DictCodeListExt2.报表名称_投资者身份信息登记.getCodeName());
				if(type.equals("01")){
					chbReportCheck.setCheckResult("01");
				}else{
					chbReportCheck.setCheckResult("02");
					chbReportCheck.setErrorInfo("检查不通过，该投资者存在多个识别标识！");
				}
				resultList.add(chbReportCheck);

				//更新检查序列号对应的数据
				EquityInfoRegist001Dto dto = new EquityInfoRegist001Dto();
				dto.setIndentifyMark(item.getIndentifyMark());
				dto.setCheckSeqno(chbReportCheck.getCheckSeqno());
				equityInfoRegistUpdateList.add(dto);
			});
		}

		//批量删除之前的检查数据
		chbReportCheckMapper.delReportCheckDatas(checkseqnos);
		if(CollectionUtils.isNotEmpty(resultList)){
			//批量插入报送前检查表数据
			chbReportCheckMapper.batchInsert(resultList);
			//批量更新投资者身份信息检查序列号
			if(CollectionUtils.isNotEmpty(equityInfoRegistUpdateList)){
				equityInfoRegist001Mapper.updateEquityRegistCheckSeqno(equityInfoRegistUpdateList);
			}
		}
	}
	/**
	 * @Title: rejectUnqualifiedData
	 * @Description: (投资者类别、证件号码、投资者姓名、证件类型有一项为空，则改该数据进入维护表，且数据状态为待处理)
	 * @param @param equityList    上游文件解析出的结果
	 * @return void    返回类型
	 * @throws
	 */
	public List<Map<String, String>> rejectUnqualifiedData(List<Map<String, String>> equityList) {
		// 待处理数据集合
		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
		Iterator<Map<String, String>> it = equityList.iterator();
		while (it.hasNext()) {
			Map<String, String> mapStr = it.next();
			/**机构和部门代码 为空时，给默认值  add by 徽商个性化 chaowei 2019-12-03 begin**/
			if (StringUtil.isEmpty(mapStr.get("ORG_CODE"))) {
				mapStr.put("ORG_CODE", SvrDataUtil.getSysParam("CHB_ORG_CODE"));
			}
			if (StringUtil.isEmpty(mapStr.get("DEPT_CODE"))) {
				mapStr.put("DEPT_CODE", SvrDataUtil.getSysParam("CHB_DEPT_CODE"));
			}
			/**机构和部门代码 为空时，给默认值  add by 徽商个性化 chaowei 2019-12-03 end**/


			/**投资者属于境内时，所属国家和地区要为空  add by 徽商个性化  chaowei 2019-12-03 begin**/
			if (!StringUtil.isEmpty(mapStr.get("HOLDER_REGION")) && "CHN".equals(mapStr.get("HOLDER_REGION")) ) {
				mapStr.put("HOLDER_REGION", "");
			}
			/**投资者属于境内时，所属国家和地区要为空  add by 徽商个性化 chaowei 2019-12-03 end**/

			// 投资者类别
			if (StringUtil.isEmpty(mapStr.get("HOLDER_TYPE")) ||
					// 证件号码
					StringUtil.isEmpty(mapStr.get("IDENTIFY_NUMBER")) ||
					// 投资者姓名
					StringUtil.isEmpty(mapStr.get("HOLDER_NAME")) ) {
				// 存入待处理数据
				dataList.add(mapStr);
				// 移除错误数据
				it.remove();
			}else if(
				// 个人证件类型
					StringUtil.isEmpty(mapStr.get("PERSONAL_IDENTIFY_TYPE"))&&
							// 机构证件类型
							StringUtil.isEmpty(mapStr.get("INSTITUTION_IDENTIFY_TYPE"))){
				// 存入待处理数据
				dataList.add(mapStr);
				// 移除错误数据
				it.remove();
			}
		}
		// 待处理数据批量插入维护表
		return dataList;
	}
}
