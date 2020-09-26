package com.joyintech.tams.svreport.interf.chb.investtrade.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.tams.common.base.entity.ChbMstCounterParty;
import com.joyintech.tams.report.interf.common.constant.InterfConstants;
import com.joyintech.tams.common.svreport.chb.constant.ChbInterfConfig;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.ChbInterfUtil;
import com.joyintech.tams.svreport.interf.chb.conf.DataSourceType;
import com.joyintech.tams.svreport.interf.chb.investtrade.dao.ChbMstCounterPartyInterfMapper;

/**
 * 功能:资产要素表接口
 * @since 1.6.0
 * @version 1.6.0
 * @作者：weiyuqiang
 * @创建时间：2019年2月18日
 * @修改记录：
 */
@SuppressWarnings("rawtypes")
@Service
@Transactional(rollbackFor = Exception.class)
public class ChbMstCounterPartyInterf  extends BaseService{
	
	/**
	 * 日志
	 */
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ChbMstCounterPartyInterfMapper chbMstCounterPartyInterfMapper;
	
	@Autowired
	private SqlSessionTemplate template;
	
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 功能:接入交易对手信息
     * @since 1.6.0
     * @version 1.6.0
     * @author：weiyuqiang 2019年2月18日
     * @param batchDate 跑批日
     * @prodList 产品代码
     * @param isSynchronizeTask 是否从轮训中进入
     * void
     */
    public Map<String,List<String>> generateCounterPartyInfoData(Date batchDate,List<String> prodList,boolean isSynchronizeTask) {
		logger.debug("上游交易对手信息入库:----开始------" +  df.format(new Date()));	
    	// 用于返回的数据
    	Map<String,List<String>> mapPara = new HashMap<String,List<String>>();    	
    	List<String> tradenoList = new ArrayList<String>(); 
    	// 切换数据源查询信息
		Date lastUpdatetime = ChbInterfUtil.queryUpdateTime(ChbInterfConfig.中债交易对手信息接口.getInterId());	
    	//查询信息
    	ChbMstCounterPartyInterf chbMstCounterPartyInterf =  SpringContextUtil.getBean(ChbMstCounterPartyInterf.class);
		logger.debug("上游交易对手信息查询:----开始------" +  df.format(new Date()));	
    	// 查询当日新增/更改交易对手信息
    	List<ChbMstCounterParty> listChbAssetInfo = chbMstCounterPartyInterf.queryCounterPartyByInterfdateBatch(lastUpdatetime,prodList);
		logger.debug("上游交易对手信息查询:----结束------" +  df.format(new Date()));	
//		// 登记更新列表
		List<ChbMstCounterParty> updateDataList = new ArrayList<ChbMstCounterParty>();
		// 登记插入列表
		List<ChbMstCounterParty> insertDataList = new ArrayList<ChbMstCounterParty>();
		//根据counter_id查询重复数据
		List<List<ChbMstCounterParty>> lists = ListUtils.partition(listChbAssetInfo, 100);
		List<String> counterId = new ArrayList<String>();
		for(List<ChbMstCounterParty> sublist : lists){
			counterId.addAll(chbMstCounterPartyInterfMapper.getExists(sublist));
		}
    	for(ChbMstCounterParty counterParty : listChbAssetInfo){
    		//新的最后更新时间
    		if (counterParty.getUpdateTime() != null 
    				&& lastUpdatetime.compareTo(counterParty.getUpdateTime()) < 0){
    			lastUpdatetime = counterParty.getUpdateTime();
    		}
    		// 填充默认值
//    		counterParty.setAssetUuid(IDGenerator.generate(36));
//    		counterParty.setStatus(DictCodeListExt1.维护数据状态_有效.getValue());
    		// 数据日期取原始数据日期
//    		counterParty.setStatusDate(batchDate);
//    		counterParty.setOrgCode(CommonUtil.getOrgCode());
//    		counterParty.setDeptCode(CommonUtil.getDeptCode());
//    		counterParty.setStatusDate(batchDate);
//	    		if (chbMstCounterPartyInterfMapper.selectIsExistAssetByAssetCode(counterParty.getAssetCode()) > 0){
    			if(counterId.contains(counterParty.getCounterId())){
	    			// 创建人默认system
//		    		counterParty.setUpdateUser(SecurityUtil.getLoginUser().getUid());
		//        	dto.setCreateUser(SecurityUtil.getLoginUser().getUid());
//		    		counterParty.setUpdateTime(DateUtil.now());
		    		updateDataList.add(counterParty);
	    		} else {
	    			// 创建人默认system
		    		counterParty.setCreateUser("system");
		//        	dto.setCreateUser(SecurityUtil.getLoginUser().getUid());
		    		counterParty.setCreateTime(DateUtil.now());
		    		counterParty.setUpdateTime(null);
		    		counterParty.setUpdateUser(null);
		    		counterParty.setUuid(UUID.randomUUID().toString());
		    		counterParty.setStatus("E");
		    		insertDataList.add(counterParty);
	    		}
				//是否中央交易对手方：若交易对手二级类型等于“非银行业金融机构”，则中央交易对手方默认为：01 是，否则为空
    			if(DictCodeListExt2.客户二级类型_非银行业金融机构.getValue().equals(counterParty.getCounterType2())) {
    				counterParty.setIsCentralCounter(DictCodeListExt2.是否_是.getValue());
    			}
    		
    	}
//		List<BaseEntity> allData = new ArrayList<BaseEntity>();
//		if (CollectionUtils.isNotEmpty(updateDataList)) {
//		allData.addAll(updateDataList);
//			// 批量更新
//			int  singalCount = reportSelectOrDeleteCount;
//			int totalCount = updateDataList.size();
//			if(totalCount>0){
//				if(totalCount<=singalCount){
//					chbMstCounterPartyInterfMapper.updateBatch(updateDataList);
//				}
//				else{
//					for(int i = 0; i < totalCount/singalCount ; i++){
//						chbMstCounterPartyInterfMapper.updateBatch(updateDataList.subList(0, singalCount));
//						updateDataList.removeAll(updateDataList.subList(0, singalCount));
//					}
//	                if(CollectionUtils.isNotEmpty(updateDataList)){
//	                	chbMstCounterPartyInterfMapper.updateBatch(updateDataList);
//	                }
//				}
//			}
//		}
//		if (CollectionUtils.isNotEmpty(insertDataList)) {
////			allData.addAll(insertDataList);
//			// 批量插入
//			int  singalCount = Integer.parseInt( null == SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT)?"500" : SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT));
//			int totalCount = insertDataList.size();
//			if(totalCount>0){
//				if(totalCount<=singalCount){
//			    	// 先删再插
//					chbMstCounterPartyInterfMapper.deleteByCounterId(insertDataList);
//					chbMstCounterPartyInterfMapper.insertBatch(insertDataList);
//				}
//				else{
//					for(int i = 0; i < totalCount/singalCount ; i++){
//						chbMstCounterPartyInterfMapper.deleteByCounterId(insertDataList.subList(0, singalCount));
//						chbMstCounterPartyInterfMapper.insertBatch(insertDataList.subList(0, singalCount));
//						insertDataList.removeAll(insertDataList.subList(0, singalCount));
//					}
//	                if(CollectionUtils.isNotEmpty(insertDataList)){
//						chbMstCounterPartyInterfMapper.deleteByCounterId(insertDataList);
//	                	chbMstCounterPartyInterfMapper.insertBatch(insertDataList);
//	                }
//				}
//			}
//		}
		logger.debug("上游交易对手信息插入:----开始------" +  df.format(new Date()));	
		dealResultCommitSingle(insertDataList);
		logger.debug("上游交易对手信息插入:----结束------" +  df.format(new Date()));	
		logger.debug("上游交易对手信息更新:----开始------" +  df.format(new Date()));	
		dealUpdateData(updateDataList);
		logger.debug("上游交易对手信息更新:----结束------" +  df.format(new Date()));	
    	if(lastUpdatetime!=null){
        	ChbInterfUtil.updateLastupdatetime(ChbInterfConfig.中债交易对手信息接口.getInterId(), lastUpdatetime);;
    	}
    	tradenoList.add(InterfConstants.SVR_TRADENOLIST_ALLDEAL);
    	mapPara.put(InterfConstants.SVR_TRADENOLIST, tradenoList);
		logger.debug("上游交易对手信息入库:----结束------" +  df.format(new Date()));	
    	return mapPara;
	}
    
    /**
     * 功能:查询接入日当天的资产要素
     * @since 1.6.0
     * @version 1.6.0
     * @author：weiyuqiang 2019年2月18日
     * @param interfDate 跑批日
     * @prodList 产品代码
     * @return 
     * List<ChbAssetInfo>
     */
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    // 切换数据源
    @DataSource(DataSourceType.INTERF)
    public List<ChbMstCounterParty> queryCounterPartyByInterfdate(Date interfDate,List<String> prodList){
    	return chbMstCounterPartyInterfMapper.selectCounterPartyByInterfdate(interfDate,prodList);
    }
    
    /**
     * 功能:查询接入日当天的资产要素
     * @since 1.6.0
     * @version 1.6.0
     * @author：weiyuqiang 2019年2月18日
     * @param interfDate 跑批日
     * @prodList 产品代码
     * @return 
     * List<ChbAssetInfo>
     */
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    // 切换数据源
    @DataSource(DataSourceType.INTERF)
    public List<ChbMstCounterParty> queryCounterPartyByInterfdateBatch(Date interfDate,List<String> prodList){
		List<ChbMstCounterParty> tradeList = new ArrayList<ChbMstCounterParty>();
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("interfDate", interfDate);
		param.put("prodList", prodList);
        template.select("com.joyintech.tams.svreport.interf.chb.investtrade.dao.ChbMstCounterPartyInterfMapper.selectCounterPartyByInterfdateBatch",
        		param,new ResultHandler() {
            @Override
            public void handleResult(ResultContext resultContext) {
            	tradeList.add((ChbMstCounterParty) resultContext.getResultObject());
            }
        });
    	return tradeList;
    }
    
	/**
	 * 功能:带提交的方式单条处理数据结果，入库
	 * @since 1.6.0
	 * @version 1.6.0
	 * @author：weiyuqiang 2019年3月7日
	 * @param assetList 
	 * void
	 */
	private void dealResultCommitSingle(List<ChbMstCounterParty> investList){
		if (CollectionUtils.isNotEmpty(investList)) {
//			logger.debug("上游交易对手先删后插-删除:----开始------" +  df.format(new Date()));
//			int batchCount = Integer.parseInt( null == SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT)?"50" : SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT));//每批commit的个数
//	        int batchLastIndex = batchCount;//每批最后一个的下标
	        /*for(int index = 0; index < investList.size();){
	            if(batchLastIndex > investList.size()-1){
	                batchLastIndex = investList.size();
	                chbMstCounterPartyInterfMapper.deleteByCounterId(investList.subList(index, batchLastIndex));
//	                System.out.println("index:"+index+"     batchLastIndex:"+batchLastIndex+"list"+prodinfos.subList(index, batchLastIndex).size());
	                break;//数据插入完毕,退出循环
	            }else{
	            	chbMstCounterPartyInterfMapper.deleteByCounterId(investList.subList(index, batchLastIndex));
//	                System.out.println("index:"+index+"     batchLastIndex:"+batchLastIndex +"list"+prodinfos.subList(index, batchLastIndex).size());
	                index = batchLastIndex;//设置下一批下标
	                batchLastIndex = index + batchCount;                     
	            }
	        }*/
//			logger.debug("上游交易对手先删后插-删除:----结束------" +  df.format(new Date()));
			logger.debug("上游交易对手先删后插-插入:----开始------" +  df.format(new Date()));
			int count = 0;
			SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
	    	try{
	    		ChbMstCounterPartyInterfMapper batchMapper =  session.getMapper(ChbMstCounterPartyInterfMapper.class);
	    		for(ChbMstCounterParty asset : investList){
	    			batchMapper.insert(asset);
	    			count++;
	    			if(count%1000==0){
	    				session.commit();
	    	            // 清理缓存，防止溢出
	    	            session.clearCache();
	    				logger.debug("上游交易对手先删后插-插入1000条第"+ count/1000 +"次:----结束------" +  df.format(new Date()));	
	    			}
	    		}
	    		session.commit();
	            // 清理缓存，防止溢出
	            session.clearCache();
	    	}
//	    	catch(Exception e){
//	    		session.rollback();
//	    		throw e;
//	    	}
	    	finally {
	    		session.close();
	    	}
			logger.debug("上游交易对手先删后插-插入:----结束------" +  df.format(new Date()));
		}
	}
	
	/**
	 * 交易对手数据更新
	 * @since 1.6.0
	 * @version 1.6.0
	 * @author：panbin 2019-05-23
	 * @param updateList 
	 * String
	 */
	private String dealUpdateData(List<ChbMstCounterParty> updateList){
		SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
   	 try {
            int count = 0;
            logger.debug("交易对手更新开始--------------" + new Date());
            ChbMstCounterPartyInterfMapper batchMapper = session.getMapper(ChbMstCounterPartyInterfMapper.class);
            int batchCount = Integer.parseInt( null == SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT)?"50" : SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT));//每批commit的个数
            int batchLastIndex = batchCount;//每批最后一个的下标
            for(int index = 0; index < updateList.size();){
                if(batchLastIndex > updateList.size()-1){
                    batchLastIndex = updateList.size();
                    batchMapper.updateBachByStatus(updateList.subList(index, batchLastIndex));
                    break;//数据插入完毕,退出循环
                }else{
                    batchMapper.updateBachByStatus(updateList.subList(index, batchLastIndex));
                    index = batchLastIndex;//设置下一批下标
                    batchLastIndex = index + batchCount;                     
                }
                count ++;
                // 每20次提交
                if (count % 20 == 0) {
                    session.commit();
                    // 清理缓存，防止溢出
                    session.clearCache();
                }
            }
            logger.debug("交易对手更新结束--------------" + new Date());
            session.commit();
            // 清理缓存，防止溢出
            session.clearCache();
        }catch(Exception e){
            logger.error("数据更新出错:",e.getMessage(),e);
            return "fail";
        }
        finally {
            session.close();
        }
        logger.debug("交易对手更新结束--------------" + new Date());
        return "success";
		
	}
}
