package com.joyintech.tams.svreport.interf.chb.ptlvalution.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.tams.common.base.entity.ChbPtlValution;
import com.joyintech.tams.common.svreport.chb.constant.ChbInterfConfig;
import com.joyintech.tams.common.svreport.chb.util.ChbInterfUtil;
import com.joyintech.tams.svreport.interf.chb.conf.DataSourceType;
import com.joyintech.tams.svreport.interf.chb.ptlvalution.dao.ChbPtlValutionInterfMapper;


/**
 * 组合资产估值表接口
 * @author Administrator
 *
 */
@SuppressWarnings("rawtypes")
@Service
@Transactional(rollbackFor = Exception.class)
public class ChbPtlValutionInterf extends BaseService{
	@Autowired
	private ChbPtlValutionInterfMapper chbPtlValutionInterfMapper;
	@Autowired
	private SqlSessionTemplate template;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
    public List<Date> generatePtlValutionData(Date batchTime) {
    	logger.debug("组合资产估值信息接口导入，开始");
	   	//批量插入mapper
	   	SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
	   	ChbPtlValutionInterfMapper batchMapper =  session.getMapper(ChbPtlValutionInterfMapper.class);
	   	int count = 0;
	   	
	   	ChbPtlValutionInterf chbPtlValutionInterf2 =  SpringContextUtil.getBean(ChbPtlValutionInterf.class);
	   	Date lastUpdatetime = ChbInterfUtil.queryUpdateTime(ChbInterfConfig.组合资产估值信息接口.getInterId());
	   	List<Date> isUpdated = chbPtlValutionInterf2.isUpdated(lastUpdatetime);
	    try{
	    	for(Date date: isUpdated){
	    		String dateStr = DateUtil.format(date);
	    		//查询信息
	    		logger.debug("删除"+dateStr+"日的组合资产估值信息，开始");
	    		chbPtlValutionInterfMapper.deleteByCdate(date);//先删后插
	    		logger.debug("删除"+dateStr+"日的组合资产估值信息，结束");
	        	logger.debug("插入"+dateStr+"日的组合资产估值信息，开始");
	        	List<ChbPtlValution> listChbPtlValution = chbPtlValutionInterf2.getDataFromInterf(date);
	        	for(ChbPtlValution valution : listChbPtlValution){
	            	if(valution.getUpdateTime().compareTo(lastUpdatetime)>0)
	            		lastUpdatetime =valution.getUpdateTime();
	            	// 插入数据
	            	batchMapper.insert(valution);
	            	count++;
	            	if(count%1000==0){
	            		session.commit();
	            		session.clearCache();
	            		logger.debug("已插入"+count+"条组合资产估值信息："+DateUtil.getCurrentDateTime());
	        		}
	        	}
	        	logger.debug("插入"+dateStr+"日的组合资产估值信息，结束");
	//        	//先删后插
	//        	chbPtlValutionInterfMapper.deleteByCdate(date);
	        	//批量插入
	//    		int  singalCount = Integer.parseInt( null == SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT)?"500" : SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT));;
	//    		int totalCount = listChbPtlValution.size();
	//    		if(totalCount>0){
	//    			if(totalCount<=singalCount){
	//    				chbPtlValutionInterfMapper.insertBatchList(listChbPtlValution);
	//    			}
	//    			else{
	//    				for(int i = 0; i < totalCount/singalCount ; i++){
	//    					chbPtlValutionInterfMapper.insertBatchList(listChbPtlValution.subList(0, singalCount));
	//    					listChbPtlValution.removeAll(listChbPtlValution.subList(0, singalCount));
	//    				}
	//                    if(CollectionUtils.isNotEmpty(listChbPtlValution)){
	//                    	chbPtlValutionInterfMapper.insertBatchList(listChbPtlValution);
	//                    }
	//    			}
	//    		}
	    	}
	    	//新的最后更新时间
    		session.commit();
    		session.clearCache();
	    	if(lastUpdatetime!=null){
	        	ChbInterfUtil.updateLastupdatetime(ChbInterfConfig.组合资产估值信息接口.getInterId(), lastUpdatetime);;
	    	}
    	}finally {
    		session.close();
    	}
	    logger.debug("组合资产估值信息接口导入，结束");
    	return isUpdated;
	}
    
	@Transactional(propagation=Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.INTERF)
	public List<ChbPtlValution> getDataFromInterf(Date batchTime){
		List<ChbPtlValution> rs = new ArrayList<ChbPtlValution>();
		template.select("com.joyintech.tams.svreport.interf.chb.ptlvalution.dao.ChbPtlValutionInterfMapper.selectByCdate",batchTime,
                new ResultHandler() {
            @Override
            public void handleResult(ResultContext resultContext) {
            	ChbPtlValution value = (ChbPtlValution) resultContext.getResultObject();
                rs.add(value);
            }
        });
    	return rs;
    }

	@Transactional(propagation=Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.INTERF)
	public List<Date> isUpdated(Date batchTime){
    	return chbPtlValutionInterfMapper.isUpdated(batchTime);
    }
}
