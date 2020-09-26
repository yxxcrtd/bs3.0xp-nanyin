package com.joyintech.tams.svreport.interf.chb.cprdbalance.service;

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
import com.joyintech.tams.common.base.entity.ChbCprodBalanceDto;
import com.joyintech.tams.common.svreport.chb.constant.ChbInterfConfig;
import com.joyintech.tams.common.svreport.chb.util.ChbInterfUtil;
import com.joyintech.tams.svreport.interf.chb.conf.DataSourceType;
import com.joyintech.tams.svreport.interf.chb.cprdbalance.dao.ChbCprodBalanceDtoMapper;


/**
 * 周期型产品余额表接口
 * @author Administrator
 *
 */
@SuppressWarnings("rawtypes")
@Service
@Transactional(rollbackFor = Exception.class)
public class ChbCprodBalanceInterf extends BaseService{
	@Autowired
	private ChbCprodBalanceDtoMapper chbCprodBalanceDtoMapper;
	@Autowired
	private SqlSessionTemplate template;
	
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());
	
    public List<Date> generateData(Date batchTime) {	
    	logger.debug("周期型产品余额接口导入批处理开始");
    	long startTime=System.currentTimeMillis();
    	SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
//    	String source = CacheUtil.getSysParam(SVReportGlobalConstant.SVR_EXCHANGE_SOURCE);
    	//批量插入mapper
    	ChbCprodBalanceDtoMapper batchMapper =  session.getMapper(ChbCprodBalanceDtoMapper.class);
    	ChbCprodBalanceInterf chbBotAstPosInterf2 =  SpringContextUtil.getBean(ChbCprodBalanceInterf.class);
    	Date lastUpdatetime = ChbInterfUtil.queryUpdateTime(ChbInterfConfig.周期型产品余额接口.getInterId());
    	List<Date> isUpdated = chbBotAstPosInterf2.isUpdated(lastUpdatetime);
    	try{
    		int count = 0;
    		for(Date date :isUpdated){
	        	//查询信息
	        	List<ChbCprodBalanceDto> listChbCprodBalanceDto = chbBotAstPosInterf2.getDataFromInterf(date);
	    		// 生成日期为空防止要素（维护）变动更新日期
	        	for(ChbCprodBalanceDto position : listChbCprodBalanceDto){
	        		position.setBalId(IDGenerator.generate(36));
	        		//人民币发行金额 = 总发行数量*币种汇率
	            	if(position.getUpdateTime().compareTo(lastUpdatetime)>0)
	            		lastUpdatetime =position.getUpdateTime();
	        	}
	        	//先删后插
	        	String dateStr = DateUtil.format(date);
	        	logger.debug("删除"+dateStr+"日的周期型产品余额，开始");
	        	chbCprodBalanceDtoMapper.deleteByPosdate(date);
	        	logger.debug("删除"+dateStr+"日的周期型产品余额，结束");
	        	logger.debug("插入"+dateStr+"日的周期型产品余额，开始");
	        	for(ChbCprodBalanceDto position : listChbCprodBalanceDto){
	        		batchMapper.insert(position);
	        		count++;
	        		if(count%1000==0){
	        			logger.debug("已插入"+count+"条"+DateUtil.getCurrentDateTime());
	            		session.commit();
	            		session.clearCache();
	        		}
	        			
	        	}	
	        	logger.debug("插入"+dateStr+"日的周期型产品余额，结束");
	        }
    	
    		session.commit();
    		session.clearCache();
    	}finally {
    		session.close();
    	}
    		
    	
    	//新的最后更新时间
    	if(lastUpdatetime!=null){
    		ChbInterfUtil.updateLastupdatetime(ChbInterfConfig.周期型产品余额接口.getInterId(), lastUpdatetime);
    	}
    	logger.debug("周期型产品余额接口导入批处理结束。用时："+(System.currentTimeMillis()-startTime)/1000+"s");
    	return isUpdated;
	}


	@Transactional(propagation=Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.INTERF)
	public List<ChbCprodBalanceDto> getDataFromInterf(Date batchTime){
		List<ChbCprodBalanceDto> rs = new ArrayList<ChbCprodBalanceDto>();
		template.select("com.joyintech.tams.svreport.interf.chb.cprdbalance.dao.ChbCprodBalanceDtoMapper.selectByPosdate",batchTime,
                new ResultHandler() {
            @Override
            public void handleResult(ResultContext resultContext) {
            	ChbCprodBalanceDto pos = (ChbCprodBalanceDto) resultContext.getResultObject();
                rs.add(pos);
            }
        });
    	return rs;
    }
    
	@Transactional(propagation=Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.INTERF)
	public List<Date> isUpdated(Date batchTime){
    	return chbCprodBalanceDtoMapper.isUpdated(batchTime);
    }
	
}
