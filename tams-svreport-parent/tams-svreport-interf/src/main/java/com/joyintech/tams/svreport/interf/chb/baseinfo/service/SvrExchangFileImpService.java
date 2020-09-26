package com.joyintech.tams.svreport.interf.chb.baseinfo.service;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
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

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.common.FileImpService;
import com.joyintech.tams.common.InterfDefine;
import com.joyintech.tams.common.InterfFile;
import com.joyintech.tams.common.base.entity.MstExchargeQuote;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.ChbInterfUtil;
import com.joyintech.tams.common.base.entity.ChbInterfConfigDto;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.report.framework.thread.JoyinThreadPoolExecutor;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.report.interf.common.imp.ImportGenerator;
import com.joyintech.tams.report.interf.common.imp.sqlldr.ImpTable;
import com.joyintech.tams.svreport.interf.chb.baseinfo.dao.SvrMstExchangeImpMapper;
import com.joyintech.tams.systemtools.util.CommonUtil;

/**
 * @Description 汇率信息文件接口
 * @author jiangxiao
 * @version 1.7 2019年5月10日
 * @since 1.7
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SvrExchangFileImpService extends FileImpService {
	
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
    
    @Autowired
	private SqlSessionTemplate template;
    
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	private SvrMstExchangeImpMapper chbExchangeImpMapper;
	
	@Override
	public String saveData(List<Map<String, String>> listData, String saveFlag, String dateSource) {
		// 解析成功或失败的标识
		boolean success = true;
        String tmpPath = SvrDataUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY, GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
		// 数据小于1000直接入库
		if (listData.size() <= 1000) {
			success = excuteProdExchangeByStep(saveFlag, listData, null, null, null, null, null);
		} else {// 大于1000使用sqlloader入库
			// 创建表
			ImpTable table = new ImpTable("MST_EXCHARGE_QUOTE");
			table.setColumns(ImportGenerator.createImpTableColumns("MST_EXCHARGE_QUOTE"));
			// 创建一个线程池
			if (interfMaxCorePoolSize < 1) {
				interfMaxCorePoolSize = 20;
			}
			ExecutorService service = new JoyinThreadPoolExecutor(interfMaxCorePoolSize);
			List<Callable<Boolean>> tasks = new ArrayList<>();
			if (null != listData) {
				int start = 0;
				int end = 0;
				int size = listData.size();
				int max = (int) Math.ceil((double) size / 1000);
				for (int i = 0; i < max; i++) {
					start = end;
					end = Math.min(size, 1000 * (i + 1));
					List<Map<String, String>> subList = listData.subList(start, end);
					tasks.add(new Callable<Boolean>() {
						@Override
						public Boolean call() {
							return excuteProdExchangeByStep(saveFlag, subList, null, null, tmpPath, table, null);
						}
					});
				}
			}
			// 为线程池添加任务
			List<Future<Boolean>> taskResult;
			try {
				taskResult = service.invokeAll(tasks);
			} catch (InterruptedException e) {
				logger.error(e.getMessage(), e);
				 Thread.currentThread().interrupt();
				return "fail";
			}
			for (Future<Boolean> res : taskResult) {
				try {
					success = success && res.get();
				} catch (InterruptedException | ExecutionException e) {
					logger.error(e.getMessage(), e);
					Thread.currentThread().interrupt();
					return "fail";
				}
			}
			// 任务结束，停止线程池的所有线程
			service.shutdown();
			Collection<File> files = FileUtils.listFiles(new File(tmpPath+"/MST_EXCHANGE_QUOTE/"), new String[] { "tmp" }, false);
			List<String> content = new ArrayList<>(listData.size());
			String result = "";
			for (File f : files) {
				if (f.getName().startsWith(InterfDefine.汇率信息数据.getCode())) {
					try {
						content.addAll(FileUtils.readLines(f, Charset.forName("UTF-8")));
						logger.debug("查找sqlloder文件" + f);
					} catch (IOException e) {
						logger.debug("查找sqlloder文件异常");
						logger.error(e.getMessage(), e);
						result = "fail";
					} finally {
						f.delete();
					}
				}
			}
			if ("fail".equals(result)) {
				return result;
			}
			// 系统是否使用的db2数据库
			boolean isDB2DataBase = CommonUtil.isDB2DataBase();
			if (isDB2DataBase) {
				// db2数据库处理
				if (CollectionUtils.isNotEmpty(content)) {
					try {
						success = success && ImportGenerator.importToDBByPart(table, tmpPath+"/MST_EXCHANGE_QUOTE/", content,
								reportInterfOneTimeSqlLdrDataCount, reportInterfOneTimeSqlLdrCommitCount, "del",
								reportSqlldrDatabaseName);
					} catch (IOException | InterruptedException e) {
						logger.error(e.getMessage(), e);
						Thread.currentThread().interrupt();
						return "fail";
					}
				}
			} else {
				// oracle原逻辑
				if (CollectionUtils.isNotEmpty(content)) {
					try {
						logger.debug("sqlloader入库开始");
						success = success && ImportGenerator.importToDBByPart(table, tmpPath+"/MST_EXCHANGE_QUOTE/", content,
								reportInterfOneTimeSqlLdrDataCount, reportInterfOneTimeSqlLdrCommitCount, "txt",
								reportSqlldrDatabaseName);
						logger.debug("sqlloader入库结束");
					} catch (IOException | InterruptedException e) {
						logger.debug("sqlloader入库报错");
						logger.error(e.getMessage(), e);
						Thread.currentThread().interrupt();
						return "fail";
					}
				}
			}
		}
		return "success";
	}

	@Override
	public String importDB(List<Map<String, String>> listData,InterfFile fileList) {
		String remoteDir = "";
		String result = "";
		
		logger.debug("汇率数据检查---开始-----:" +  df.format(new Date()));
		@SuppressWarnings("unused")
		List<Map<String, String>> listError = checkData(listData, fileList.getFilename(), InterfDefine.汇率信息数据.getCode(), InterfDefine.汇率信息数据.getCode(), null,"EXCHARGE_CODE", "TRADE_DATE");
		logger.debug("汇率数据检查---结束-----:" +  df.format(new Date()));
		ChbInterfConfigDto interfConfig = ChbInterfUtil.queryInterfConfigByInterfid(InterfDefine.汇率信息数据.getCode());
		if(CollectionUtils.isEmpty(listData)){
			return "success";
		}
		if (interfConfig == null){
            logger.info("CHB_INTERF_CONFIG表中无配置项！");
            return "fail";
        }
        String saveFlag = interfConfig.getContentType();
        logger.debug("汇率数据入库---开始-----:" +  df.format(new Date()));
		result = saveData(listData, saveFlag,null);
		logger.debug("汇率数据入库---结束-----:" +  df.format(new Date()));
		// 更新系统配置时间
        ChbInterfUtil.updateInterfTimeBySysdate( InterfDefine.汇率信息数据.getCode());
		if("fail".equals(result)||"fail".equals(remoteDir)){
			return "fail";
		}else{
			return "success";
		}
	}

	private boolean excuteProdExchangeByStep(String saveFlag, List<Map<String, String>> exchangeList, String type, String[] result,
			String tmpPath, ImpTable table, ImpTable subTable) {
		// 定义新增数据list-验证通过数据
		List<MstExchargeQuote> listNE = new ArrayList<MstExchargeQuote>();
		logger.debug("汇率数据转dto---开始-----:" +  df.format(new Date()));
		listNE = convertMapToEntity(exchangeList);
		logger.debug("汇率数据转dto---结束-----:" +  df.format(new Date()));
		if (listNE != null && listNE.size() > 0) {
			deleteAllData();
			if (tmpPath == null) {
				insertExchangeInfo(listNE);
			} else {
				createDataFile(tmpPath + "/MST_EXCHANGE_QUOTE/MST_EXCHANGE_QUOTE", listNE, table.getColumns());
			}
			return (CollectionUtils.isNotEmpty(listNE));
		}
		return false;
	}
	
	@SuppressWarnings("unused")
	private void mergeExchangeInfo(List<MstExchargeQuote> listNE) {
		chbExchangeImpMapper.mergeExchangeData(listNE);
	}
	
	private void deleteAllData() {
		chbExchangeImpMapper.deleteTodayData();
	}
	
	/** map转dto
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unused")
	private MstExchargeQuote dataConversionEntity(Map<String, String> map) {
		MstExchargeQuote dto = new MstExchargeQuote();
		dto.setExchargeCode(map.get("EXCHARGE_CODE"));//行情代码，一般为货币对
		dto.setTradeDate(DateUtil.parse(map.get("TRADE_DATE")));//交易日
		dto.setDatasourceType(map.get("DATASOURCE_TYPE"));//数据来源
		if(map.get("OPEN_PRICE") != null){
			dto.setOpenPrice(new BigDecimal(map.get("OPEN_PRICE")));//开盘价
		}
		if(map.get("CLOSE_PRICE") != null){
			dto.setClosePrice(new BigDecimal(map.get("CLOSE_PRICE")));//收盘价
		}
		if(map.get("HIGH_PRICE") != null){
			dto.setHighPrice(new BigDecimal(map.get("HIGH_PRICE")));//最高价
		}
		if(map.get("LOW_PRICE") != null){
			dto.setLowPrice(new BigDecimal(map.get("LOW_PRICE")));//最低价
		}
		if(map.get("MID_PRICE") != null){
			dto.setMidPrice(new BigDecimal(map.get("MID_PRICE")));//中间价
		}
		dto.setBasisCyy(map.get("BASIS_CYY"));//基础币种，比如USD/CNY中的USD
		dto.setQuotCyy(map.get("QUOT_CYY"));//报价币种，比如USD/CNY中的CNY
		if(map.get("QUOT_UNIT") != null){
			dto.setQuotUnit(Long.parseLong(map.get("QUOT_UNIT")));//报价单位
		}
		dto.setInputType(map.get("INPUT_TYPE"));//录入方式，接口、手工等
		return dto;
	}
	
	@SuppressWarnings("unused")
	private void deleteExchangeInfo(List<MstExchargeQuote> exchangeInfolist) {
		int batchCount = Integer.parseInt(null == SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT) ? "100"
				: SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT));// 每批commit的个数
		int batchLastIndex = batchCount;// 每批最后一个的下标
		logger.debug("汇率删除开始-------------" + df.format(new Date()));
		for (int index = 0; index < exchangeInfolist.size();) {
			if (batchLastIndex > exchangeInfolist.size() - 1) {
				batchLastIndex = exchangeInfolist.size();
				chbExchangeImpMapper.deleteExchangeBatch(exchangeInfolist.subList(index, batchLastIndex));
				break;// 数据插入完毕,退出循环
			} else {
				chbExchangeImpMapper.deleteExchangeBatch(exchangeInfolist.subList(index, batchLastIndex));
				index = batchLastIndex;// 设置下一批下标
				batchLastIndex = index + batchCount;
			}
		}
		logger.debug("汇率删除结束-------------" + df.format(new Date()));
	}
	
	private void insertExchangeInfo(List<MstExchargeQuote> exchangeInfolist) {
		SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
		try {
			int count = 0;
			SvrMstExchangeImpMapper batchMapper = session.getMapper(SvrMstExchangeImpMapper.class);
			logger.debug("汇率插入开始-------------" + df.format(new Date()));
			for (MstExchargeQuote exchangeInfo : exchangeInfolist) {
				batchMapper.insert(exchangeInfo);
				count++;
				if (count % 1000 == 0) {
					session.commit();
					// 清理缓存，防止溢出
					session.clearCache();
					logger.debug("汇率已插入条数--------" + count + "-----" + df.format(new Date()));
				}
			}
			session.commit();
			// 清理缓存，防止溢出
			session.clearCache();
			logger.debug("汇率已插入条数--------" + count + "-----" + df.format(new Date()));
		} finally {
			session.close();
		}
		logger.debug("汇率插入结束-------------" + df.format(new Date()));
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
    private List<MstExchargeQuote> convertMapToEntity(List<Map<String, String>> dataList) {
        // 获取产品表的列
    	List<MstExchargeQuote> rs = new ArrayList<MstExchargeQuote>();
        for (Map<String, String> map : dataList) {
        	MstExchargeQuote t = new MstExchargeQuote();
        	t.setStatus(DictCodeListExt1.维护数据状态_有效.getValue());
            for (String key : map.keySet()) {
            	if(key==null||key.equals("ERRMSG")){//待处理的数据
            		t.setStatus(DictCodeListExt1.维护数据状态_待处理.getValue());
            		continue;
            	}
                String value = map.get(key);
                Field field = ReflectionUtils.findField(MstExchargeQuote.class,
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
