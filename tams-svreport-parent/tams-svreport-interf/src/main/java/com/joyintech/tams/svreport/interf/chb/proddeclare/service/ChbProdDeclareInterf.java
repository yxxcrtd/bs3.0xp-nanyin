package com.joyintech.tams.svreport.interf.chb.proddeclare.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.joyintech.tams.constant.DictCodeListExt2;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.tams.common.svreport.chb.constant.ChbInterfConfig;
import com.joyintech.tams.common.svreport.chb.util.ChbInterfUtil;
import com.joyintech.tams.svreport.interf.chb.conf.DataSourceType;
import com.joyintech.tams.svreport.interf.chb.proddeclare.dao.ChbPrdDeclareInfoMapper;
import com.joyintech.tams.svreport.interf.chb.proddeclare.dto.ChbPrdDeclareInfoDto;

/**
 * @Description 产品申报要素接入
 * @author yuluan
 * @version 1.0 2019年10月09日
 * @since 1.0
 */
@SuppressWarnings("rawtypes")
@Service
@Transactional(rollbackFor = Exception.class)
public class ChbProdDeclareInterf extends BaseService{

	private Logger logger = LoggerFactory.getLogger(getClass());

	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	private SqlSessionTemplate template;
//	@Autowired
//	private ChbPrdDeclareInfoMapper chbPrdDeclareInfoMapper;
	/**
	 * 数据批量一次性insert条数(单条数据字段较多)
	 */
	@Value("${application.reportInsertLongCount}")
	private int reportInsertLongCount;
	
	/**
	 * @Description  产品申报要素数据接入处理
	 * @param batchDate
	 * @return void
	 * @version 1.0 2019年10月09日
	 */
	public void generatePrdDeclareData(Date batchDate){
		//切换数据源查询信息
		ChbProdDeclareInterf chbPrdOpenschInterf2 =  SpringContextUtil.getBean(ChbProdDeclareInterf.class);
		logger.debug("读取申报要素信息----开始-----:" +  df.format(new Date()));
		Date lastUpdatetime = ChbInterfUtil.queryUpdateTime(ChbInterfConfig.产品申报信息接口.getInterId());
		//查询上游申报信息
		List<ChbPrdDeclareInfoDto> prodidlist = chbPrdOpenschInterf2.selectRepDeclare(lastUpdatetime,null);
		logger.debug("读取申报要素信息----结束-----:" +  df.format(new Date()));
		if(CollectionUtils.isNotEmpty(prodidlist)){
			for(ChbPrdDeclareInfoDto single:prodidlist){
				if(single.getUpdateTime().compareTo(lastUpdatetime) > 0){
					lastUpdatetime = single.getUpdateTime();
				}
				//DB接入 塞16
				single.setDatasource("16");
				// 中债对募集方式进行映射
				if("1".equals(single.getRaiseType())) {
					single.setRaiseType("01");
				}else if("2".equals(single.getRaiseType())) {
					single.setRaiseType("02");
				}
				if(StringUtils.isBlank(single.getStatus())) {
					single.setStatus(DictCodeListExt2.数据状态_有效.getValue());
				}

			}
			//新的最后更新时间
			if(lastUpdatetime!=null){
				ChbInterfUtil.updateLastupdatetime(ChbInterfConfig.产品申报信息接口.getInterId(), lastUpdatetime);;
			}
			insertDataMerge(prodidlist);
		}
		
	}

	private void insertDataMerge(List<ChbPrdDeclareInfoDto> assetInfoList) {
		SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
		try {
			int count = 0;
			logger.debug("产品要素插入开始--------------" + new Date());
			ChbPrdDeclareInfoMapper batchMapper = session.getMapper(ChbPrdDeclareInfoMapper.class);
			int batchCount = reportInsertLongCount == 0 ? 50 : reportInsertLongCount;// 每批commit的个数
			int batchLastIndex = batchCount;// 每批最后一个的下标
			for (int index = 0; index < assetInfoList.size();) {
				if (batchLastIndex > assetInfoList.size() - 1) {
					batchLastIndex = assetInfoList.size();
					batchMapper.mergeInfoList(assetInfoList.subList(index, batchLastIndex));
					break;// 数据插入完毕,退出循环
				} else {
					batchMapper.mergeInfoList(assetInfoList.subList(index, batchLastIndex));
					index = batchLastIndex;// 设置下一批下标
					batchLastIndex = index + batchCount;
				}
				count++;
				// 每20次提交
				if (count % 20 == 0) {
					session.commit();
					// 清理缓存，防止溢出
					session.clearCache();
				}
			}
			session.commit();
			// 清理缓存，防止溢出
			session.clearCache();
		} finally {
			session.close();
		}
		logger.debug("产品要素插入结束--------------" + new Date());
	}
	
	/**
	 * @Description 切换数据源，查询开放计划变更的所有产品代码
	 * @param batchDate
	 * @param plantype
	 * @return List<ChbPrdOpensch>
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.INTERF)
	public List<ChbPrdDeclareInfoDto> selectRepDeclare(Date batchDate,List<String> prodIdlist){
		List<ChbPrdDeclareInfoDto> prodList = new ArrayList<ChbPrdDeclareInfoDto>();
//		prodList =chbPrdDeclareInfoMapper.selectRepDeclare(batchDate);
		HashMap<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("updatetime", batchDate);
		template.select("com.joyintech.tams.svreport.interf.chb.proddeclare.dao.ChbPrdDeclareInfoMapper.selectRepDeclare",paraMap,
                new ResultHandler() {
            @Override
            public void handleResult(ResultContext resultContext) {
            	prodList.add((ChbPrdDeclareInfoDto) resultContext.getResultObject());
            }
        });
		return prodList;
	}
}
