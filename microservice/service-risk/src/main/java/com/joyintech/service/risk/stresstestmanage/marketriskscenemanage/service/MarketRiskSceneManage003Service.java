/*
 * 文件名：MarketRiskSceneManage003Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：汪海
 * 修改时间：2019年9月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.stresstestmanage.marketriskscenemanage.service;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.collections4.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.risk.conf.DataSourceType;
import com.joyintech.service.risk.stresstestmanage.marketriskscenemanage.dao.MarketRiskSceneManage003Mapper;
import com.joyintech.service.risk.stresstestmanage.marketriskscenemanage.dto.MarketRiskSceneFactorTypeDto;
import com.joyintech.service.risk.stresstestmanage.marketriskscenemanage.dto.MarketRiskSceneManage002Dto;
import com.joyintech.service.risk.stresstestmanage.marketriskscenemanage.dto.MarketRiskSceneManage003Dto;
import com.joyintech.tams.common.base.dao.SttSceneFactorMapper;
import com.joyintech.tams.common.base.dao.SttSceneMapper;
import com.joyintech.tams.common.base.entity.SttScene;
import com.joyintech.tams.common.base.entity.SttSceneFactor;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListRsk;

/**
 * 情景详细
 * 〈功能详细描述〉
 * @author 汪海
 * @version 1.0 2019年9月21日
 * @see MarketRiskSceneManage003Service
 * @since 1.0
 */
@Service
public class MarketRiskSceneManage003Service extends BaseService<MarketRiskSceneManage003Dto> {

	//多线程
	/**
	 * 邮储压测最优线程数
	 */
	@Value("${application.riskTestMaxCorePoolSize:20}")
	private int MaxCorePoolSize;

	/**
	 * 市场情景新建时每个线程处理因子数(最多不要超过950)
	 */
	@Value("${application.marketNewMaxSubmitProductSize:20}")
	private int maxSubmitProductSize;

	/**
	 * logger
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private MarketRiskSceneManage003Mapper marketRiskSceneManage003Mapper;
	
	@Autowired
    private SttSceneMapper sttSceneMapper;
	
	@Autowired
    private SttSceneFactorMapper sttSceneFactorMapper;
	
	/**
	 * 取情景id序列值
	 * <功能详细描述>
	 * @return
	 * @see
	 * @since 1.0
	 */
	@Transactional
    @DataSource(DataSourceType.STRESSTEST)
	public String getNewSceneId(){
		return marketRiskSceneManage003Mapper.getNewSceneId();
	}
    
    /**
     * 根据因子二级分类查询出所有匹配的因子
     * <功能详细描述>
     * @param pageParam [{ftrType2Code:因子二级分类码}]
     * @return
     * @see
     * @since 1.0
     */
	@Transactional
    @DataSource(DataSourceType.STRESSTEST)
    public List<MarketRiskSceneFactorTypeDto> queryFactorList(Map<String, Object> pageParam){
        pageParam.put("ftrType", DictCode.风险因子管理_市场风险因子分类.getValue());
        pageParam.put("ftrType2", DictCode.因子二级分类.getValue());
        return marketRiskSceneManage003Mapper.queryFactorList(pageParam);
    }
    
    /**
     * 查询未准备的因子
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
	@Transactional
    @DataSource(DataSourceType.STRESSTEST)
    public List<MarketRiskSceneManage003Dto> queryUnreadyFactorList(Map<String, Object> pageParam){
    	return marketRiskSceneManage003Mapper.queryUnreadyFactorList(pageParam);
    }
	
    /**
     * 更新情景因子的变化值
     * <功能详细描述>
     * @param scFactorsList
     * @see
     * @since 1.0
     */
    public int updateSceneFactor(List<SttSceneFactor> scFactorsList){
    	int result = 0;
    	for(SttSceneFactor item : scFactorsList){
    		result = sttSceneFactorMapper.updateByPrimaryKeySelective(item);
    	}
    	return result;
    }
    
    /**
     * 情景详细页面-因子变化信息列表
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    @Transactional
    @DataSource(DataSourceType.STRESSTEST)
    public List<MarketRiskSceneManage003Dto> queryFactorInfoList(Map<String, Object> pageParam){
    	return marketRiskSceneManage003Mapper.queryFactorInfoList(pageParam);
    }

	/**
	 * 执行存储过程
	 * <功能详细描述>
	 * @param ftrIdStr
	 * @param startDat
	 * @param endDate
	 * @return
	 * @see
	 * @since 1.0
	 */
    @DataSource(DataSourceType.STRESSTEST)
	public List<MarketRiskSceneManage003Dto> executeProcedure(String ftrIdStr, String startDat, String endDate){
		//存储过程没有传出值结构，需要自己判断。
		try {
			String[] ftrArray = ftrIdStr.split(",");
			Map<String, List<String>> ftrMap= Arrays.asList(ftrArray).stream().collect(Collectors.groupingBy((item)->item.substring(0, 6)));
			ArrayList<MarketRiskSceneManage003Dto> cursorList = new ArrayList<MarketRiskSceneManage003Dto>();

			//创建线程池
			ExecutorService executor = Executors.newFixedThreadPool(MaxCorePoolSize);
			try {
				List<Callable<List<MarketRiskSceneManage003Dto>>> tasks = new ArrayList<>();

				ftrMap.values().stream().forEach(p ->{
					tasks.add(new Callable<List<MarketRiskSceneManage003Dto>>() {

						@Override
						public List<MarketRiskSceneManage003Dto> call() throws Exception {
							// TODO Auto-generated method stub
							String ids = StringUtil.join(p, ",");
							ArrayList<MarketRiskSceneManage003Dto> cursorList = null;
							if(StringUtil.isNotEmpty(ids)){
								Map<String,Object> map = new HashMap<>();
								map.put("p_factor_ids", ids);
								map.put("p_beginDate", DateUtil.parseDate(startDat, "yyyy-MM-dd"));
								map.put("p_endDate", DateUtil.parseDate(endDate, "yyyy-MM-dd"));
								marketRiskSceneManage003Mapper.callQueryFactorInfo(map);
								cursorList = (ArrayList<MarketRiskSceneManage003Dto>) map.get("p_factor_cur");
							}else{
								cursorList = new ArrayList<>();
							}

							return cursorList;
						}
					});
				});
				//阻塞，等待所有线程完成
				List<Future<List<MarketRiskSceneManage003Dto>>> futureList = executor.invokeAll(tasks);
				for(Future<List<MarketRiskSceneManage003Dto>> futrure: futureList){
					//按照一定顺序合并到一个list中
					List<MarketRiskSceneManage003Dto> list = futrure.get();
					if(list!=null && list.size()>0){
						String flagStr = list.get(0).getFtrId().substring(0, 5);
						if("IntRt".equals(flagStr)){
							cursorList.addAll(0, futrure.get());
						}else{
							cursorList.addAll(futrure.get());
						}
					}
				}
			} catch (InterruptedException e) {
				logger.error(e.getMessage());
			}finally {
				executor.shutdown();
			}

			if(cursorList != null){
				//遍历集合，导入map集合中，并将统计变更的因子分组
				Map<String, MarketRiskSceneManage003Dto> cursorMap = new HashMap<>();
				List<String> groupIdList = new ArrayList<>();
				for(MarketRiskSceneManage003Dto item : cursorList){
					if(item.getFtrId().indexOf(",")>0){
						groupIdList.add(item.getGroupId());
					}else{
						cursorMap.put(item.getFtrId(), item);
					}
				}
				
				//因子属性值补充和修改
				for(MarketRiskSceneManage003Dto item : cursorList){
					//将没有变更过的因子分组id置为空,
					if(!groupIdList.contains(item.getGroupId())){
						item.setGroupId("");
					}
					if(StringUtil.isEmpty(item.getChangeValue())){
//						item.setDays(null);
//						item.setStartDate(null);
//						item.setEndDate(null);
						item.setChangeValue("数据未准备");
					}
					//将变更过的因子缺失值补充完整，因子id分隔符改为-
					if(item.getFtrId().indexOf(",")>0){
						String[] ftrIdArr = item.getFtrId().split(",");
						item.setFtrName(cursorMap.get(ftrIdArr[0]).getFtrName()+"-"+cursorMap.get(ftrIdArr[1]).getFtrName());
						item.setMixFtr(true);
						item.setFtrValueStruct(cursorMap.get(ftrIdArr[0]).getFtrValueStruct());
						item.setFtrTypeCode(cursorMap.get(ftrIdArr[0]).getFtrTypeCode());
						item.setFtrType2Code(cursorMap.get(ftrIdArr[0]).getFtrType2Code());
						item.setFtrId(item.getFtrId().replace(',', '-'));
					}
				}
			}else{
				cursorList = new ArrayList<MarketRiskSceneManage003Dto>();
			}
			
			return cursorList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ArrayList<MarketRiskSceneManage003Dto>();
	}
	
	
}
