package com.joyintech.tams.svreport.interf.chb.feebasis.service;

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
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.tams.common.base.dao.ChbPrdfeeBasisDataMapper;
import com.joyintech.tams.common.base.entity.ChbPrdfeeBasisData;
import com.joyintech.tams.common.svreport.chb.constant.ChbInterfConfig;
import com.joyintech.tams.common.svreport.chb.util.ChbInterfUtil;
import com.joyintech.tams.svreport.interf.chb.conf.DataSourceType;
import com.joyintech.tams.svreport.interf.chb.feebasis.dao.SvrChbPrdFeeBasisDBInterfMapper;


/**
 * 产品费用头寸信息数据接口
 * @author zhangzhao
 *
 */
@SuppressWarnings("rawtypes")
@Service
@Transactional(rollbackFor = Exception.class)
public class SvrChbPrdFeeBasisDBInterf extends BaseService{
	@Autowired
	private SvrChbPrdFeeBasisDBInterfMapper svrChbPrdFeeBasisDBInterfMapper;
	
	@Autowired
	private SqlSessionTemplate template;
	
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());
	
    public List<Date> generateData(Date batchTime) {
    	logger.info("产品费用头寸信息数据DB接口批处理开始");
    	long startTime=System.currentTimeMillis();
    	//定义批量插入mapper
    	SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
    	ChbPrdfeeBasisDataMapper batchMapper =  session.getMapper(ChbPrdfeeBasisDataMapper.class);
    	
    	//获取上一次更新时间
    	Date lastUpdatetime = ChbInterfUtil.queryUpdateTime(ChbInterfConfig.产品费用头寸接口.getInterId());
    	SvrChbPrdFeeBasisDBInterf svrChbPrdFeeBasisDBInterf =  SpringContextUtil.getBean(SvrChbPrdFeeBasisDBInterf.class);
    	//获取上游未接入数据的日期
    	List<Date> isUpdated = svrChbPrdFeeBasisDBInterf.isUpdated(lastUpdatetime);
    	try{
    	int count = 0;
    	for(Date date :isUpdated){
    		//根据日期去上游查询数据
    		List<ChbPrdfeeBasisData>  listChbPrdfeeBasisData=getDataFromInterf(DateUtil.format(date));
    		for(ChbPrdfeeBasisData  data:listChbPrdfeeBasisData){
    			//主键
    			data.setPosuuid(IDGenerator.generate(36));
    			data.setCreateUser("system");
        		data.setUpdateUser("system");
        		data.setStatus("E");
        		data.setDataSource("16");
        		
        		if(data.getUpdateTime().compareTo(lastUpdatetime)>0)
            		lastUpdatetime =data.getUpdateTime();
    		}
    		//先删后插
        	String dateStr = DateUtil.format(date);
        	logger.info("删除"+dateStr+"日的产品费用头寸数据，开始");
        	ChbPrdfeeBasisData param = new ChbPrdfeeBasisData();
        	param.setCdate(date);
        	svrChbPrdFeeBasisDBInterfMapper.deleteData(param);
        	logger.info("删除"+dateStr+"日的产品费用头寸数据，结束");
        	logger.info("插入"+dateStr+"日的产品费用头寸数据，开始");
        	
        	for(ChbPrdfeeBasisData  data:listChbPrdfeeBasisData){
        		batchMapper.insert(data);
        		count++;
        		if(count%1000==0){
        			logger.debug("已插入"+count+"条"+DateUtil.getCurrentDateTime());
            		session.commit();
            		session.clearCache();
        		}
        	}
        	logger.debug("插入"+dateStr+"日的资产基础指标数据，结束");
    	}
    	session.commit();
		session.clearCache();
	}finally {
		session.close();
	}
    	//新的最后更新时间
    	if(lastUpdatetime!=null){
    		ChbInterfUtil.updateLastupdatetime(ChbInterfConfig.产品费用头寸接口.getInterId(), lastUpdatetime);
    	}
    	logger.info("产品费用头寸接口导入批处理结束。用时："+(System.currentTimeMillis()-startTime)/1000+"s");
    	return isUpdated;
}
    
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.INTERF)
	public List<ChbPrdfeeBasisData> getDataFromInterf(String batchTime){
		List<ChbPrdfeeBasisData> rs = new ArrayList<ChbPrdfeeBasisData>();
		template.select("com.joyintech.tams.svreport.interf.chb.feebasis.dao.SvrChbPrdFeeBasisDBInterfMapper.selectByValuedate",batchTime,
                new ResultHandler() {
            @Override
            public void handleResult(ResultContext resultContext) {
            	ChbPrdfeeBasisData pos = (ChbPrdfeeBasisData) resultContext.getResultObject();
                rs.add(pos);
            }
        });
    	return rs;
    }
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.INTERF)
	public List<Date> isUpdated(Date batchTime){
    	return svrChbPrdFeeBasisDBInterfMapper.isUpdated(batchTime);
    }
	
}
