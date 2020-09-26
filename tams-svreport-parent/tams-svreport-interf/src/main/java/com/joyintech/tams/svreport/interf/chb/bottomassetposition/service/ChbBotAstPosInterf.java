package com.joyintech.tams.svreport.interf.chb.bottomassetposition.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
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
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.tams.common.base.entity.ChbAssetsBasisData;
import com.joyintech.tams.common.base.entity.ChbBotAstPosition;
import com.joyintech.tams.common.svreport.chb.constant.ChbInterfConfig;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.ChbCcyConvertUtil;
import com.joyintech.tams.common.svreport.chb.util.ChbInterfUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.svreport.interf.chb.assetinfo.dao.ChbAssetInfoInterfMapper;
import com.joyintech.tams.svreport.interf.chb.bottomassetposition.dao.ChbBotAstPosInterfMapper;
import com.joyintech.tams.svreport.interf.chb.conf.DataSourceType;
import com.joyintech.tools.collectors.JoyinCollectors;


/**
 * 底层资产持仓表接口
 * @author Administrator
 *
 */
@SuppressWarnings("rawtypes")
@Service
@Transactional(rollbackFor = Exception.class)
public class ChbBotAstPosInterf extends BaseService{
	@Autowired
	private ChbBotAstPosInterfMapper chbBotAstPosInterfMapper;
	@Autowired
	private ChbAssetInfoInterfMapper chbAssetInfoInterfMapper;
	@Autowired
	private SqlSessionTemplate template;
	
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());
	
    public List<Date> generatePtlValutionData(Date batchTime) {	
    	logger.debug("底层资产持仓信息接口导入批处理开始");
    	long startTime=System.currentTimeMillis();
    	SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
    	String source = CacheUtil.getSysParam(SVReportGlobalConstant.SVR_EXCHANGE_SOURCE);
    	//批量插入mapper
    	ChbBotAstPosInterfMapper batchMapper =  session.getMapper(ChbBotAstPosInterfMapper.class);
    	//通道投资资产管理产品是否穿透报送
    	String isChlBtm = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_IS_CHL_BTM);
    	String isCalLoadRate =  CacheUtil.getSysParam("IS_CAL_LOADRATE");
    	ChbBotAstPosInterf chbBotAstPosInterf2 =  SpringContextUtil.getBean(ChbBotAstPosInterf.class);
    	Date lastUpdatetime = ChbInterfUtil.queryUpdateTime(ChbInterfConfig.底层持仓信息接口.getInterId());
    	List<Date> isUpdated = chbBotAstPosInterf2.isUpdated(lastUpdatetime);
    	List<Date> isUpdatedChl = new ArrayList<Date>();
    	if("02".equals(isChlBtm)){
    		isUpdatedChl = chbBotAstPosInterf2.isUpdatedChl(lastUpdatetime);
    	}
    	try{
    		int count = 0;
    		for(Date date :isUpdated){
	        	//查询信息
	        	List<ChbBotAstPosition> listChbBotAstPosition = chbBotAstPosInterf2.getDataFromInterf(date);
	        	if("02".equals(isChlBtm)){
	        		List<ChbBotAstPosition> listBotAstPosition = chbBotAstPosInterf2.getChlBtmDataFromInterf(date, source);
	        		List<ChbBotAstPosition> listBotAstPositionSp = chbBotAstPosInterf2.getChlBtmDataFromInterfSp(date, source,isCalLoadRate);
	        		Map<String,ChbBotAstPosition> spAssetsBasisDataMap=new HashMap<String,ChbBotAstPosition>(listBotAstPosition.size());
	        		spAssetsBasisDataMap=listBotAstPosition.stream().collect(
    		                JoyinCollectors.toMap(
    		                        x -> x.getPlanAssetId() + GlobalConstant.UNDER_LINE + x.getAssetCode(), x -> x));
	        		for(ChbBotAstPosition single:listBotAstPositionSp){
	        			String key=single.getPlanAssetId() + GlobalConstant.UNDER_LINE + single.getAssetCode();
	        			spAssetsBasisDataMap.put(key, single);
	        		}
	        		
	        		if(!spAssetsBasisDataMap.isEmpty()){
	        			for(String key:spAssetsBasisDataMap.keySet()){
	        				listChbBotAstPosition.add(spAssetsBasisDataMap.get(key));
	        			}
	        		}
	        	}
	        	List<String> assetTypeList = new ArrayList<String>();
	    		assetTypeList.add(DictCodeListExt2.资产_负债类别_资产管理产品.getValue());
	    		assetTypeList.add(DictCodeListExt2.资产_负债类别_私募基金.getValue());
	    		assetTypeList.add(DictCodeListExt2.资产_负债类别_委外投资_协议方式.getValue());
	    		// 生成日期为空防止要素（维护）变动更新日期
	    		List<String> assetPlanLList = chbAssetInfoInterfMapper.selectAssetCodeByAssetType(assetTypeList, null);
	    		Map<String, BigDecimal> rateMap = new HashMap<>();
	        	for(ChbBotAstPosition position : listChbBotAstPosition){
	        		position.setPosId(IDGenerator.generate(36));
	        		position.setIssuePartyId(SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_ISSUE_PARTY_ID));
	        		position.setCreateUser("system");
	        		position.setUpdateUser("system");
	        		position.setStatus("E");
	        		position.setIschl("02");
	        		position.setDatasource("16");
	        		//人民币发行金额 = 总发行数量*币种汇率
	    			if (position.getTotalNum() != null){
	    				position.setIssueAmt(ChbCcyConvertUtil.changeCnyMoney(position.getCcy(), position.getTotalNum(), position.getPosDate(), rateMap));
	    			}
	        		if(assetPlanLList.contains(position.getAssetCode())){
	        			position.setIsfinaplan("01");
	        		}else{
	        			position.setIsfinaplan("02");
	        		}
	            	if(position.getUpdateTime().compareTo(lastUpdatetime)>0)
	            		lastUpdatetime =position.getUpdateTime();
	        	}
	        	//先删后插
	        	String dateStr = DateUtil.format(date);
	        	logger.debug("删除"+dateStr+"日的底层资产持仓信息，开始");
	        	chbBotAstPosInterfMapper.deleteByPosdate(date);
	        	logger.debug("删除"+dateStr+"日的底层资产持仓信息，结束");
	        	logger.debug("插入"+dateStr+"日的底层资产持仓信息，开始");
	        	for(ChbBotAstPosition position : listChbBotAstPosition){
	        		batchMapper.insert(position);
	        		count++;
	        		if(count%1000==0){
	        			logger.debug("已插入"+count+"条"+DateUtil.getCurrentDateTime());
	            		session.commit();
	            		session.clearCache();
	        		}
	        			
	        	}	
	        	logger.debug("插入"+dateStr+"日的底层资产持仓信息，结束");
	        }
    		
			for (Date date : isUpdatedChl) {
				List<ChbBotAstPosition> listBotAstPosition = chbBotAstPosInterf2.getChlBtmDataFromInterf(date, source);
				List<ChbBotAstPosition> listBotAstPositionSp = chbBotAstPosInterf2.getChlBtmDataFromInterfSp(date, source,isCalLoadRate);
				Map<String,ChbBotAstPosition> spAssetsBasisDataMap=new HashMap<String,ChbBotAstPosition>(listBotAstPosition.size());
        		spAssetsBasisDataMap=listBotAstPosition.stream().collect(
		                JoyinCollectors.toMap(
		                        x -> x.getPlanAssetId() + GlobalConstant.UNDER_LINE + x.getAssetCode(), x -> x));
        		for(ChbBotAstPosition single:listBotAstPositionSp){
        			String key=single.getPlanAssetId() + GlobalConstant.UNDER_LINE + single.getAssetCode();
        			spAssetsBasisDataMap.put(key, single);
        		}
				if(!spAssetsBasisDataMap.isEmpty()){
					listBotAstPosition.clear();
					for(String key:spAssetsBasisDataMap.keySet()){
						listBotAstPosition.add(spAssetsBasisDataMap.get(key));
        			}
        		}
				List<String> assetTypeList = new ArrayList<String>();
				assetTypeList.add(DictCodeListExt2.资产_负债类别_资产管理产品.getValue());
				assetTypeList.add(DictCodeListExt2.资产_负债类别_私募基金.getValue());
				assetTypeList.add(DictCodeListExt2.资产_负债类别_委外投资_协议方式.getValue());
				// 生成日期为空防止要素（维护）变动更新日期
				List<String> assetPlanLList = chbAssetInfoInterfMapper.selectAssetCodeByAssetType(assetTypeList, null);
				for (ChbBotAstPosition position : listBotAstPosition) {
					position.setPosId(IDGenerator.generate(36));
					position.setIssuePartyId(SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_ISSUE_PARTY_ID));
					position.setCreateUser("system");
					position.setUpdateUser("system");
					position.setStatus("E");
					position.setDatasource("16");
					if (assetPlanLList.contains(position.getAssetCode())) {
						position.setIsfinaplan("01");
					} else {
						position.setIsfinaplan("02");
					}
					if (null != position.getUpdateTime() && position.getUpdateTime().compareTo(lastUpdatetime) > 0)
						lastUpdatetime = position.getUpdateTime();
				}
				// 先删后插
				String dateStr = DateUtil.format(date);
				logger.debug("删除" + dateStr + "日的底层资产持仓信息，开始");
				chbBotAstPosInterfMapper.deleteByPosdate(date);
				logger.debug("删除" + dateStr + "日的底层资产持仓信息，结束");
				logger.debug("插入" + dateStr + "日的底层资产持仓信息，开始");
				for (ChbBotAstPosition position : listBotAstPosition) {
					batchMapper.insert(position);
					count++;
					if (count % 1000 == 0) {
						logger.debug("已插入" + count + "条" + DateUtil.getCurrentDateTime());
						session.commit();
						session.clearCache();
					}

				}
				logger.debug("插入" + dateStr + "日的底层资产持仓信息，结束");
			}
//        	//批量插入
//    		int  singalCount = Integer.parseInt( null == SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT)?"500" : SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT));
//    		int totalCount = listChbBotAstPosition.size();
//
//    		if(totalCount>0){
//    			if(totalCount<=singalCount){
//    				chbBotAstPosInterfMapper.insertBatchList(listChbBotAstPosition);
//    			}
//    			else{
//    				for(int i = 0; i < totalCount/singalCount ; i++){
//    					chbBotAstPosInterfMapper.insertBatchList(listChbBotAstPosition.subList(0, singalCount));
//    					listChbBotAstPosition.removeAll(listChbBotAstPosition.subList(0, singalCount));
//    				}
//                    if(CollectionUtils.isNotEmpty(listChbBotAstPosition)){
//                    	chbBotAstPosInterfMapper.insertBatchList(listChbBotAstPosition);
//                    }
//    			}
//    		}
    	
    		session.commit();
    		session.clearCache();
    	}finally {
    		session.close();
    	}
    		
    	
    	//新的最后更新时间
    	if(lastUpdatetime!=null){
    		ChbInterfUtil.updateLastupdatetime(ChbInterfConfig.底层持仓信息接口.getInterId(), lastUpdatetime);
    	}
    	logger.debug("底层资产持仓信息接口导入批处理结束。用时："+(System.currentTimeMillis()-startTime)/1000+"s");
    	return isUpdated;
	}

    @Transactional(propagation=Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.INTERF)
	public List<Date> isUpdatedChl(Date lastUpdatetime) {
    	return chbBotAstPosInterfMapper.isUpdatedChl(lastUpdatetime);
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.INTERF)
	public List<ChbBotAstPosition> getDataFromInterf(Date batchTime){
		List<ChbBotAstPosition> rs = new ArrayList<ChbBotAstPosition>();
		template.select("com.joyintech.tams.svreport.interf.chb.bottomassetposition.dao.ChbBotAstPosInterfMapper.selectByPosdate",batchTime,
                new ResultHandler() {
            @Override
            public void handleResult(ResultContext resultContext) {
            	ChbBotAstPosition pos = (ChbBotAstPosition) resultContext.getResultObject();
                rs.add(pos);
            }
        });
    	return rs;
    }
    
	@Transactional(propagation=Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.INTERF)
	public List<Date> isUpdated(Date batchTime){
    	return chbBotAstPosInterfMapper.isUpdated(batchTime);
    }
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.INTERF)
	public List<ChbBotAstPosition> getChlBtmDataFromInterf(Date batchTime,String source){
		List<ChbBotAstPosition> rs = new ArrayList<ChbBotAstPosition>();
		Map<String,String> params = new HashMap<String,String>();
		params.put("batchTime", DateUtil.format(batchTime));
		params.put("status", source);
		template.select("com.joyintech.tams.svreport.interf.chb.bottomassetposition.dao.ChbBotAstPosInterfMapper.selectPtlChlBtmByPosdate",params,
                new ResultHandler() {
            @Override
            public void handleResult(ResultContext resultContext) {
            	ChbBotAstPosition pos = (ChbBotAstPosition) resultContext.getResultObject();
                rs.add(pos);
            }
        });
    	return rs;
    }
	@Transactional(propagation=Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.INTERF)
	public List<ChbBotAstPosition> getChlBtmDataFromInterfSp(Date batchTime,String source,String isCalLoadRate){
		List<ChbBotAstPosition> rs = new ArrayList<ChbBotAstPosition>();
		Map<String,String> params = new HashMap<String,String>();
		params.put("batchTime", DateUtil.format(batchTime));
		params.put("status", source);
		params.put("isCalLoadRate", isCalLoadRate);
		template.select("com.joyintech.tams.svreport.interf.chb.bottomassetposition.dao.ChbBotAstPosInterfMapper.selectPtlChlBtmByPosdateSp",params,
                new ResultHandler() {
            @Override
            public void handleResult(ResultContext resultContext) {
            	ChbBotAstPosition pos = (ChbBotAstPosition) resultContext.getResultObject();
                rs.add(pos);
            }
        });
    	return rs;
    }
}
