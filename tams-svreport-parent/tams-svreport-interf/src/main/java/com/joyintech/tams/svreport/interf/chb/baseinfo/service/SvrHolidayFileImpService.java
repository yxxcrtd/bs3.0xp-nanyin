package com.joyintech.tams.svreport.interf.chb.baseinfo.service;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.common.FileImpService;
import com.joyintech.tams.common.InterfDefine;
import com.joyintech.tams.common.InterfFile;
import com.joyintech.tams.common.base.entity.MstHoliday;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.ChbInterfUtil;
import com.joyintech.tams.common.base.entity.ChbInterfConfigDto;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.report.interf.common.imp.sqlldr.ImpTable;
import com.joyintech.tams.svreport.interf.chb.baseinfo.dao.SvrMstHolidayImpMapper;
import com.joyintech.tams.systemtools.util.CommonUtil;

/**
 * @Description 节假日信息文件接口
 * @author jiangxiao 
 * @version 1.7 2019年5月10日
 * @since 1.7
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SvrHolidayFileImpService extends FileImpService {

	 /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
	 * 上游接口文件编码格式
	 */
	@Value("${application.reportInterfFileEncoding}")
	public String reportInterfFileEncoding;
	/**
	 * 上游接口解析分割符
	 */
	@Value("${application.reportInterfSplitCharacter}")
	private String reportInterfSplitCharacter;
	
	/**
	 * 上游接口解析读取文件处理最大线程数
	 */
	@Value("${application.interfMaxCorePoolSize}")
	private int interfMaxCorePoolSize;
	 /**
     * 上游接口解析读取文件处理,单次sqlldr处理最大数据条数
     */
    @Value("${application.reportInterfOneTimeSqlLdrDataCount}")
    private int reportInterfOneTimeSqlLdrDataCount;

    /**
     * 上游接口解析读取文件处理,单次sqlldr处理最大数据条数
     */
    @Value("${application.reportInterfOneTimeSqlLdrCommitCount}")
    private int reportInterfOneTimeSqlLdrCommitCount;
    /**
     * sqlldr用数据库连接
     */
    @Value("${spring.datasource.reportSqlldrDatabaseName}")
    private String reportSqlldrDatabaseName;
    
    /**
     * 数据批量一次性insert条数(单条数据字段较多)
     */
    @Value("${application.reportInsertLongCount}")
    private int reportInsertLongCount;
    
	@Autowired
	private SqlSessionTemplate template;
    
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    @Autowired
    private SvrMstHolidayImpMapper svrHolidayImpMapper;
	
	@Override
	public String saveData(List<Map<String, String>> listData, String saveFlag, String dateSource) {
		boolean success = excuteHolidayByStep(saveFlag,listData, null, null, null, null);
		return success ? "success" : "fail";
	}

	@Override
	public String importDB(List<Map<String, String>> listData,InterfFile interfFile) {
		String result = "";
		logger.debug("节假日数据检查---开始-----:" +  df.format(new Date()));
		@SuppressWarnings("unused")
		List<Map<String, String>> listError = checkData(listData, interfFile.getFilename(), InterfDefine.节假日数据.getCode(), InterfDefine.节假日数据.getCode(), null,"MARKET_CODE","CDATE");
		logger.debug("节假日数据检查---结束-----:" +  df.format(new Date()));
		if(CollectionUtils.isEmpty(listData)){
			return "success";
		}
		ChbInterfConfigDto interfConfig = ChbInterfUtil.queryInterfConfigByInterfid(InterfDefine.节假日数据.getCode());
        if (interfConfig == null){
            logger.info("CHB_INTERF_CONFIG表中无配置项！");
            return "fail";
        }
        String saveFlag = interfConfig.getContentType();
        logger.debug("节假日数据入库---开始-----:" +  df.format(new Date()));
		result = saveData(listData,saveFlag,null);
		logger.debug("节假日数据入库---结束-----:" +  df.format(new Date()));
		// 更新系统配置时间
        ChbInterfUtil.updateInterfTimeBySysdate( InterfDefine.节假日数据.getCode());
		if("fail".equals(result)){
			return "fail";
		}else{
			return "success";
		}
	}

	private boolean excuteHolidayByStep(String saveFlag, List<Map<String, String>> holidayList, String[] result,
			String tmpPath, ImpTable table, ImpTable subTable) {
		boolean  isSucess=false;
		// 定义新增数据list-验证通过数据
		List<MstHoliday> listNE = new ArrayList<MstHoliday>();
		logger.debug("节假日数据转dto---开始-----:" +  df.format(new Date()));
		if(holidayList!=null&&holidayList.size()>0){
			for(Map<String, String> map : holidayList){
				try{
					MstHoliday dto = dataConversionEntity(map);
					listNE.add(dto);
				}catch(Exception  e){
					logger.error("读取数据出错:",e.getMessage(),e);
					isSucess=false;
				}
			}
		}
		logger.debug("节假日数据转dto---结束-----:" +  df.format(new Date()));
		if (listNE != null && listNE.size() > 0) {
			// 插入节假日（有就更新没有就插入）
			isSucess=mergeHoliday(listNE);
		}
		return isSucess;
	}
	
	private boolean mergeHoliday(List<MstHoliday> listNE) {
		boolean  isSuccess=true;
		SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        try {
            int count = 0;
            logger.debug("节假日插入开始--------------" + new Date());
            SvrMstHolidayImpMapper batchMapper = session.getMapper(SvrMstHolidayImpMapper.class);
            int batchCount = reportInsertLongCount==0?50:reportInsertLongCount;//每批commit的个数
            int batchLastIndex = batchCount;//每批最后一个的下标
            for(int index = 0; index < listNE.size();){
                if(batchLastIndex > listNE.size()-1){
                    batchLastIndex = listNE.size();
                    batchMapper.mergeHolidayData(listNE.subList(index, batchLastIndex));
                    break;//数据插入完毕,退出循环
                }else{
                    batchMapper.mergeHolidayData(listNE.subList(index, batchLastIndex));
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
            logger.debug("节假日插入结束--------------" + new Date());
            session.commit();
            // 清理缓存，防止溢出
            session.clearCache();
        }catch(Exception e){
            logger.error("数据插入出错:",e.getMessage(),e);
            isSuccess=false;
        }
        finally {
            session.close();
        }
        logger.debug("节假日插入结束--------------" + new Date());
        return  isSuccess;
	}

	@SuppressWarnings("unused")
	private void deleteHoliday(List<MstHoliday> holidayList){
		int batchCount = Integer.parseInt( null == SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT)?"100" : SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT));// 每批commit的个数
		int batchLastIndex = batchCount;// 每批最后一个的下标
		logger.debug("节假日删除开始--------------"+new Date());
		for (int index = 0; index < holidayList.size();) {
			if (batchLastIndex > holidayList.size() - 1) {
				batchLastIndex = holidayList.size();
				svrHolidayImpMapper.deleteHolidayBatch2(holidayList.subList(index, batchLastIndex));
				break;// 数据插入完毕,退出循环
			} else {
				svrHolidayImpMapper.deleteHolidayBatch2(holidayList.subList(index, batchLastIndex));
				index = batchLastIndex;// 设置下一批下标
				batchLastIndex = index + batchCount;
			}
		}
		logger.debug("节假日删除结束--------------"+new Date());
	}
	
	@SuppressWarnings("unused")
	private void insertHoliday(List<MstHoliday> holidayList) {
		SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
		try {
			int count = 0;
			logger.debug("节假日插入开始--------------"+new Date());
			SvrMstHolidayImpMapper batchMapper = session.getMapper(SvrMstHolidayImpMapper.class);
			for (MstHoliday holiday : holidayList) {
				batchMapper.insert(holiday);
				count++;
				if (count % 1000 == 0) {
					session.commit();
					// 清理缓存，防止溢出
					session.clearCache();
					logger.debug("节假日已插入条数-----------"+count+"---------"+df.format(new Date()));
				}
			}
			session.commit();
			// 清理缓存，防止溢出
			session.clearCache();
			logger.debug("节假日已插入条数-----------"+count+"---------"+df.format(new Date()));
		} finally {
			session.close();
		}
		logger.debug("节假日插入结束--------------"+df.format(new Date()));
	}
	
	private MstHoliday dataConversionEntity(Map<String, String> map) {
		MstHoliday dto = new MstHoliday();
		dto.setCalendarId(map.get("MARKET_CODE"));//日历编号
		dto.setCdate(DateUtil.parse(map.get("CDATE")));//日期
		dto.setCreateDept(map.get("CREATE_DEPT"));//创建部门
		dto.setCreateTime(DateUtil.parse(map.get("CREATE_TIME")));//创建时间
		dto.setCreateUser(map.get("CREATE_USER"));//创建人
		if(map.get("IS_HOLIDAY")!=null){
			dto.setIsHoliday(Long.parseLong(map.get("IS_HOLIDAY")));//是否节假日，0：工作日，1：节假日
		}
		dto.setUpdateTime(DateUtil.parse(map.get("UPDATE_TIME")));//更新时间
		dto.setUpdateUser(map.get("UPDATE_USER"));//更新人
		return dto;
	}
	
	@SuppressWarnings("unused")
	private void deleteAllData() {
		svrHolidayImpMapper.deleteTodayData();
	}
	
	/**
     * 将map转换为entity格式
     * @param infoList 传入的listMap
     * @param entities 转换后的lis
     * @param listCloumn 表列名
     * @param interType 数据来源
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unused")
	private List<MstHoliday> convertMapToEntity(List<Map<String, String>> dataList) {
        // 获取产品表的列
    	List<MstHoliday> rs = new ArrayList<MstHoliday>();
        for (Map<String, String> map : dataList) {
        	MstHoliday t = new MstHoliday();
        	t.setStatus(DictCodeListExt1.维护数据状态_有效.getValue());
            for (String key : map.keySet()) {
            	if(key==null||key.equals("ERRMSG")){//待处理的数据
            		t.setStatus(DictCodeListExt1.维护数据状态_待处理.getValue());
            		continue;
            	}
                String value = map.get(key);
                Field field = ReflectionUtils.findField(MstHoliday.class,
                			subBlank(key.toLowerCase()));
                if(field==null)
                	continue;
                field.setAccessible(true);
                Type type = field.getGenericType();
                if("java.lang.String".equals(type.getTypeName())){
                    try {
                        field.set(t, value);
                    } catch ( IllegalAccessException e) {
                        logger.error(e.getMessage(),e);
                    }
                }else if ("java.math.BigDecimal".equals(type.getTypeName())) {
                        try {
                            field.set(t,(value == null || "".equals(value)) ? BigDecimal.ZERO : new BigDecimal(value));
                        } catch ( IllegalAccessException e) {
                            logger.error(e.getMessage(),e);
                        }
                    }
                    else if ("java.lang.Integer".equals(type.getTypeName())) {
                        try {
                            field.set(t,(value == null || "".equals(value)) ? 0 : Integer.valueOf(value));
                        } catch (IllegalAccessException e) {
                            logger.error(e.getMessage(),e);
                        }
                    }
                    else if ("java.lang.Short".equals(type.getTypeName())) {
                        try {
                            field.set(t, (value == null || "".equals(value)) ? 0 : Short.valueOf(value));
                        } catch ( IllegalAccessException e) {
                            logger.error(e.getMessage(),e);
                        }
                    } 
                    else if ("java.lang.Long".equals(type.getTypeName())) {
                        try {
                            field.set(t, (value == null || "".equals(value)) ? 0 : Long.valueOf(value));
                        } catch ( IllegalAccessException e) {
                            logger.error(e.getMessage(),e);
                        }
                    }
                    else if ("java.util.Date".equals(type.getTypeName())) {
                        if (value != null&&""!=value) {
                            try {
                                field.set(t, DateUtil.parse(value));
                            } catch ( IllegalAccessException e) {
                                logger.error(e.getMessage(),e);
                            }
                        }
                    } else{
                	try {
                        field.set(t, value);
                    } catch ( IllegalAccessException e) {
                        logger.error(e.getMessage(),e);
                    }
                }
            }
            if(t.getCreateUser()==null){
            	t.setCreateUser("system");
            }
            // 创建人
            t.setCreateUser(CommonUtil.getUserName());
            // 创建部门
            t.setCreateDept(CommonUtil.getDeptCode());
            // 更新人
            t.setUpdateUser(CommonUtil.getUserName());
            rs.add(t);
        }
        return rs;
    }
}
