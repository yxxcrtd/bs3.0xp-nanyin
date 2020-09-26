package com.joyintech.tams.svreport.interf.chb.prodinfo.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.ChbPrdPeriodYield;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.common.svreport.chb.constant.ChbInterfConfig;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.ChbInterfUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.svreport.interf.chb.conf.DataSourceType;
import com.joyintech.tams.svreport.interf.chb.prodinfo.dao.ChbPrdEndDayYieldInterfMapper;
import com.joyintech.tams.svreport.interf.chb.prodinfo.dao.ChbPrdPeriodYieldInterfMapper;

@SuppressWarnings("rawtypes")
@Service
@Transactional(rollbackFor = Exception.class)
public class ChbPrdEndDayYieldInterf extends BaseService{
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ChbPrdEndDayYieldInterfMapper chbPrdEndDayYieldInterfMapper;
	
	@Autowired
	private SqlSessionTemplate template;
	
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	
	/**
	 * @Description 每日日终接取收益信息
	 * @param batchDate
	 * @throws ParseException 
	 */
	public List<String> generatePrdPeriodyieldData(Date batchDate,List<String> prodidList,boolean isSynchronizeTask) throws ParseException{
		//获取数据进入CHB_PRD_PERIODYIELD
		//产品代码集合
		List<String> prodList = new ArrayList<String>();
		//取最大更新时间
		logger.debug("读取上游报价信息----开始-----:" +  df.format(new Date()));
//		Date lastUpdatetime = ChbInterfUtil.queryUpdateTime(ChbInterfConfig.产品报价变动信息接口.getInterId());
		List<ChbPrdPeriodYield> periodyieldlist = new ArrayList<ChbPrdPeriodYield>();
		//单位净值取值来源读系统参数 01:估值核算系统，02:信息批露系统，03:第三方
		// 从系统缓存中获取是否跑分层
		String isLayered = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_IS_LAYERED);
		if(StringUtils.isNotBlank(isLayered)) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					if(StringUtil.equals(DictCodeListExt1.是或否_否.getValue(), isLayered)) {
//					StringBuilder stringBuilder = new StringBuilder().append(sdf.format(batchDate).replace('-', '/')).append(" ").append(dateToWeek(sdf.format(batchDate)));
				ChbPrdEndDayYieldInterf chbPrdPeriodyieldInterf2 = SpringContextUtil.getBean(ChbPrdEndDayYieldInterf.class);
				periodyieldlist = chbPrdPeriodyieldInterf2.selectPrdPeriodyieldData(sdf.format(batchDate),prodidList);
			}
		}else{
			logger.debug("单位净值取值来源配置错误！");;
		}
		logger.debug("读取上游报价信息----结束-----:" +  df.format(new Date()));
		if(CollectionUtils.isNotEmpty(periodyieldlist)){
			for(ChbPrdPeriodYield dto : periodyieldlist){
				if(isSynchronizeTask){
					if(!prodList.contains(dto.getFprodId()==null?dto.getProdId():dto.getFprodId())){
						prodList.add(dto.getFprodId()==null?dto.getProdId():dto.getFprodId());
					}
				}
				//币种(默认成人民币)
				dto.setCcy(DictCodeListExt1.币种_中债_人民币.getValue());
				//部门代码
				dto.setDeptCode(CommonUtil.getDeptCode());
				//机构代码
				dto.setOrgCode(CommonUtil.getOrgCode());
				//交易编号
				dto.setSeqno(IDGenerator.generate(36));
				dto.setCreateUser(SecurityUtil.getLoginUser() == null ?"system" :SecurityUtil.getLoginUser().getUid());
				dto.setUpdateUser(SecurityUtil.getLoginUser() == null ?"system" :SecurityUtil.getLoginUser().getUid());
				//数据状态
				dto.setStatus(DictCodeListExt1.维护数据状态_有效.getValue());
				//数据来源
				dto.setDataSource("DB");
				dto.setUpdateTime(new Date());
//				if(dto.getUpdateTime().compareTo(lastUpdatetime) > 0){
//					lastUpdatetime = dto.getUpdateTime();
//				}
					
			}
		}
		//先删后插
//		deleteBatch(periodyieldlist);
//		insertBatch(periodyieldlist);
		mergeIntoData(periodyieldlist);
		//批量插入(先删再插)
		//新的最后更新时间
//    	if(lastUpdatetime!=null){
//        	ChbInterfUtil.updateLastupdatetime(ChbInterfConfig.产品报价变动信息接口.getInterId(), lastUpdatetime);;
//    	}
		return prodList;
	}
	
	
	/**
	 * 将集合均分成几个大小一致的子集合
	 * @param 集合
	 * @param 子集合大小
	 * @return
	 */
	public static List<List<ChbPrdPeriodYield>>  createList(List<ChbPrdPeriodYield> targe,int size) {
		List<List<ChbPrdPeriodYield>> listArr = new ArrayList<List<ChbPrdPeriodYield>>();
		//获取被拆分的数组个数
		int arrSize = targe.size()%size==0?targe.size()/size:targe.size()/size+1;
		for(int i=0;i<arrSize;i++) {
			List<ChbPrdPeriodYield> sub = new ArrayList<ChbPrdPeriodYield>();
			//把指定索引数据放入到list中
			for(int j = i*size;j <= size*(i+1)-1;j++) {
				if(j <= targe.size()-1) {
					sub.add(targe.get(j));
				}
			}
			listArr.add(sub);
		}
		return listArr;
	}


	/**
	 * @Description 切换数据源，每日日终接取收益信息
	 * @param batchDate
	 * @param plantype
	 * @return List<ChbPrdOpensch>
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.INTERF)
	public List<ChbPrdPeriodYield> selectPrdPeriodyieldData(String batchDate,List<String> prodidList){
		List<ChbPrdPeriodYield> list = new ArrayList<ChbPrdPeriodYield>();
//		//1、判断当日是否有需要导入的变动数据
//		int isChange = chbPrdEndDayYieldInterfMapper.isChange(batchDate);
//		//2、如果数据变动为0.结束.不为0  则执行数据查询
//		if(isChange != 0) {
			HashMap<String,Object> paraMap = new HashMap<String,Object>();
			paraMap.put("updatetime", batchDate);
			template.select("com.joyintech.tams.svreport.interf.chb.prodinfo.dao.ChbPrdEndDayYieldInterfMapper.selectPrdPeriodYieldData",paraMap,
	                new ResultHandler() {
	            @Override
	            public void handleResult(ResultContext resultContext) {
	            	list.add((ChbPrdPeriodYield) resultContext.getResultObject());
	            }
	        });
//		}
		
		return list;
	}
	
	private void mergeIntoData(List<ChbPrdPeriodYield> periodyieldlist) {
		logger.debug("批量插入报价信息----开始-----:" +  df.format(new Date()));
		int singalCount = Integer.parseInt(null == SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT)?
                "100" : SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT));
		List<List<ChbPrdPeriodYield>> bigList = createList(periodyieldlist,singalCount);
		SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
    	//批量插入mapper
		ChbPrdPeriodYieldInterfMapper chbPrdPeriodYieldInterfMapper =  session.getMapper(ChbPrdPeriodYieldInterfMapper.class);
		try{
			int count = 0;
			for(List<ChbPrdPeriodYield> sch : bigList){
				chbPrdPeriodYieldInterfMapper.mergeChbPrdPeriodYieldData(sch);
        		count++;
        		if(count % 10==0){
            		session.commit();
            		session.clearCache();
        		}
        	}	
			session.commit();
    		session.clearCache();
		} finally {
    		session.close();
    	}
		logger.debug("批量插入报价信息----结束-----:" +  df.format(new Date()));		
	}
	
//	public static String dateToWeek(String datetime) {
//        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
//        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
//        Calendar cal = Calendar.getInstance(); // 获得一个日历
//        Date datet = null;
//        try {
//            datet = f.parse(datetime);
//            cal.setTime(datet);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
//        if (w < 0)
//            w = 0;
//        return weekDays[w];
//    }

}
