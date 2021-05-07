package com.joyintech.tams.svreport.interf.chb.prodinfo.service;

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

import com.joyintech.tams.common.FileImpService;
import com.joyintech.tams.common.InterfDefine;
import com.joyintech.tams.common.InterfFile;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.ChbPrdOpensch;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.report.framework.thread.JoyinThreadPoolExecutor;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.report.interf.common.imp.ImportGenerator;
import com.joyintech.tams.report.interf.common.imp.sqlldr.ImpTable;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.ChbInterfUtil;
import com.joyintech.tams.common.base.entity.ChbInterfConfigDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.svreport.interf.chb.prodinfo.dao.ChbPrdOpenschInterfMapper;

/**
 * @Description 开放计划文件接口
 * @author jiangxiao
 * @version 1.7 2019年5月10日
 * @since 1.7
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SvrProdOpenschFileImpService extends FileImpService {

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
	private ChbPrdOpenschInterfMapper chbPrdOpenschInterfMapper;
	
	@Override
	public String importDB(List<Map<String, String>> listData,InterfFile interfFile) {
		String result = "";
		logger.debug("开放计划数据检查----开始-----:" +  df.format(new Date()));
		checkData(listData, interfFile.getFilename(), InterfDefine.产品开放计划接口数据.getCode(), InterfDefine.产品开放计划接口数据.getCode(), null,"PROD_ID", "PLAN_TYPE", "OPEN_START", "OPEN_END","OPEN_VDATE");
		logger.debug("开放计划数据检查----结束-----:" +  df.format(new Date()));
		if(CollectionUtils.isEmpty(listData)){
			return "success";
		}
		ChbInterfConfigDto interfConfig = ChbInterfUtil.queryInterfConfigByInterfid(InterfDefine.产品开放计划接口数据.getCode());
        if (interfConfig == null){
            logger.info("CHB_INTERF_CONFIG表中无配置项！");
            return "fail";
        }
        String saveFlag = interfConfig.getContentType();
        logger.debug("开放计划数据入库----开始-----:" +  df.format(new Date()));
		result = saveData(listData,saveFlag,interfFile.getDataSource());
		logger.debug("开放计划数据入库----结束-----:" +  df.format(new Date()));
		// 更新系统配置时间
        ChbInterfUtil.updateInterfTimeBySysdate( InterfDefine.产品开放计划接口数据.getCode());
		return result;
	}
	
	@Override
	public String saveData(List<Map<String, String>> openSchList, String saveFlag,String dateSource) {
		//saveFlag: 0 全量, 1 增量
		// 解析成功或失败的标识
		boolean success = true;
        String tmpPath = SvrDataUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY, GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
		// 数据小于1000直接入库
//		if (openSchList.size() <= 1000) {
			success = excuteProdOpenSchByStep(dateSource,saveFlag,openSchList, null, null, null, null, null);
//		} else {// 大于1000使用sqlloader入库
//			// 创建表
//			ImpTable table = new ImpTable(InterfDefine.产品开放计划接口数据.getCode());
//			table.setColumns(ImportGenerator.createImpTableColumns(InterfDefine.产品开放计划接口数据.getCode()));
//			// 创建一个线程池
//			if (interfMaxCorePoolSize < 1) {
//				interfMaxCorePoolSize = 20;
//			}
//			ExecutorService service = new JoyinThreadPoolExecutor(interfMaxCorePoolSize);
//			List<Callable<Boolean>> tasks = new ArrayList<>();
//			if (null != openSchList) {
//				int start = 0;
//				int end = 0;
//				int size = openSchList.size();
//				int max = (int) Math.ceil((double) size / 1000);
//				for (int i = 0; i < max; i++) {
//					start = end;
//					end = Math.min(size, 1000 * (i + 1));
//					List<Map<String, String>> subList = openSchList.subList(start, end);
//					logger.debug("生成开放计划qlloader文件"+i);
//					tasks.add(new Callable<Boolean>() {
//						@Override
//						public Boolean call() {
//							logger.debug("sqlloader文件");
//							return excuteProdOpenSchByStep(dateSource,saveFlag,subList, null, null, tmpPath, table, null);
//						}
//					});
//				}
//			}
//			// 为线程池添加任务
//			List<Future<Boolean>> taskResult;
//			try {
//				taskResult = service.invokeAll(tasks);
//			} catch (InterruptedException e) {
//				logger.error(e.getMessage(), e);
//				return "fail";
//			}
//			for (Future<Boolean> res : taskResult) {
//				try {
//					success = success && res.get();
//				} catch (InterruptedException | ExecutionException e) {
//					logger.error(e.getMessage(), e);
//					return "fail";
//				}
//			}
//			// 任务结束，停止线程池的所有线程
//			service.shutdown();
//			Collection<File> files = FileUtils.listFiles(new File(tmpPath+"/CHB_PRD_OPENSCH/"), new String[] { "tmp" }, false);
//			logger.debug("获取开放计划sqlloder文件个数"+files.size());
//			List<String> content = new ArrayList<>(openSchList.size());
//			String result = "";
//			for (File f : files) {
//				if (f.getName().startsWith(InterfDefine.产品开放计划接口数据.getCode())) {
//					try {
//						content.addAll(FileUtils.readLines(f, Charset.forName("UTF-8")));
//						logger.debug("查找开放计划sqlloder文件"+f);
//					} catch (IOException e) {
//						logger.debug("查找开放计划sqlloder文件异常");
//						logger.error(e.getMessage(), e);
//						result = "fail";
//					} finally {
//						f.delete();
//					}
//				}
//			}
//			logger.debug("读取开放计划sqlloder文件共"+content.size()+"条");
//			if("fail".equals(result)){
//				return result;
//			}
//			// 系统是否使用的db2数据库
//			boolean isDB2DataBase = CommonUtil.isDB2DataBase();
//			if (isDB2DataBase) {
//				// db2数据库处理
//				if (CollectionUtils.isNotEmpty(content)) {
//					try {
//						success = success && ImportGenerator.importToDBByPart(table, tmpPath+"/CHB_PRD_OPENSCH/", content,
//								reportInterfOneTimeSqlLdrDataCount, reportInterfOneTimeSqlLdrCommitCount, "del",
//								reportSqlldrDatabaseName);
//					} catch (IOException | InterruptedException e) {
//						logger.error(e.getMessage(), e);
//						return "fail";
//					}
//				}
//			} else {
//				// oracle原逻辑
//				if (CollectionUtils.isNotEmpty(content)) {
//					try {
//						logger.debug("sqlloader入库开始");
//						success = success && ImportGenerator.importToDBByPart(table, tmpPath+"/CHB_PRD_OPENSCH/", content,
//								reportInterfOneTimeSqlLdrDataCount, reportInterfOneTimeSqlLdrCommitCount, "txt",
//								reportSqlldrDatabaseName);
//						logger.debug("sqlloader入库结束");
//					} catch (IOException | InterruptedException e) {
//						logger.debug("sqlloader入库报错");
//						logger.error(e.getMessage(), e);
//						return "fail";
//					}
//				}
//			}
//		}
		return "success";
	}
	
	private boolean excuteProdOpenSchByStep(String dateSource,String saveFlag,List<Map<String, String>> openSchList, String type, String[] result,
			String tmpPath, ImpTable table, ImpTable subTable) {
		// 定义新增数据list
		List<ChbPrdOpensch> listNE = new ArrayList<ChbPrdOpensch>();
		// 产品list(仅删除用的产品list)
		List<String> prodlistE = new ArrayList<String>();
		logger.debug("开放计划数据map转dto----开始-----:" +  df.format(new Date()));
		listNE = convertMapToEntity(openSchList,dateSource);
		logger.debug("开放计划数据map转dto----结束-----:" +  df.format(new Date()));
		for (ChbPrdOpensch openSch : listNE) {
			if (!prodlistE.contains(openSch.getProdId())) {
				prodlistE.add(openSch.getProdId());
			}
		}
		if (listNE != null && listNE.size() > 0) {
			logger.debug("开放计划数据删除----开始-----:" +  df.format(new Date()));
			deleteData(prodlistE);
			logger.debug("开放计划数据删除----结束-----:" +  df.format(new Date()));
			if (tmpPath == null) {
				// 插入开放计划
				insertData(listNE);
			} else {
				logger.debug("开放计划创建文件----开始-----:" +  df.format(new Date()));
				createDataFile(tmpPath + "/CHB_PRD_OPENSCH/CHB_PRD_OPENSCH", listNE, table.getColumns());
				logger.debug("开放计划创建文件----结束-----:" +  df.format(new Date()));
			}
		}
		return (CollectionUtils.isNotEmpty(listNE));
	}
	
	private void deleteByDate() {
		chbPrdOpenschInterfMapper.deleteAllData();
	}

	/**
	 * 根据产品删除开放计划
	 * @param prodidlist
	 */
	private void deleteData(List<String> prodidlist) {
		logger.debug("批量删除开放计划信息----开始-----:" + df.format(new Date()));
		// 批量删除
		int singalCount = Integer.parseInt(null == SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT)
				? "100" : SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT));
		int deleteCount = prodidlist.size();
		if (deleteCount > 0) {
			if (deleteCount <= singalCount) {
				chbPrdOpenschInterfMapper.deletePrdOpensch(prodidlist);
			} else {
				for (int i = 0; i < deleteCount / singalCount; i++) {
					chbPrdOpenschInterfMapper.deletePrdOpensch(prodidlist.subList(0, singalCount));
					prodidlist.removeAll(prodidlist.subList(0, singalCount));
				}
				if (CollectionUtils.isNotEmpty(prodidlist)) {
					chbPrdOpenschInterfMapper.deletePrdOpensch(prodidlist);
				}
			}
		}
		logger.debug("批量删除开放计划信息----结束-----:" + df.format(new Date()));
	}
	
	
	/**
	 * 开放计划入库
	 * @param openSchlist
	 */
	private void insertData(List<ChbPrdOpensch> openSchlist) {
		logger.debug("批量插入开放计划信息----开始-----:" + df.format(new Date()));
		SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
		ChbPrdOpenschInterfMapper chbPrdOpenschInterfMapper = session.getMapper(ChbPrdOpenschInterfMapper.class);
		try {
			int count = 0;
			for (ChbPrdOpensch sch : openSchlist) {
				chbPrdOpenschInterfMapper.insert(sch);
				count++;
				if (count % 1000 == 0) {
					session.commit();
					session.clearCache();
				}
			}
			session.commit();
			session.clearCache();
		} finally {
			session.close();
		}
		logger.debug("批量插入开放计划信息----结束-----:" + df.format(new Date()));
	}
	
    /** map转dto
	 * @param map
	 * @return
	 */
	private ChbPrdOpensch dataConversionEntity(Map<String, String> map) {
		ChbPrdOpensch openSch = new ChbPrdOpensch();
		openSch.setSeqno(IDGenerator.generate(36));//主键
		openSch.setProdId(map.get("PROD_ID"));//产品id
		openSch.setProdName(map.get("PROD_NAME"));//产品名称
		openSch.setIssuePartyId(map.get("ISSUE_PARTY_ID"));//发行机构代码
		openSch.setPlanType(map.get("PLAN_TYPE"));//计划类型 01:申购计划 02：赎回计划
		openSch.setOpenStart(DateUtil.parse(map.get("OPEN_START")));//开放周期起始日
		openSch.setOpenEnd(DateUtil.parse(map.get("OPEN_END")));//开放周期结束日
		openSch.setOpenVdate(DateUtil.parse(map.get("OPEN_VDATE")));//开放周期起息日
		openSch.setOrgCode(map.get("ORG_CODE"));//机构代码
		openSch.setDeptCode(map.get("DEPT_CODE"));//部门代码
		openSch.setDataSource(map.get("DATA_SOURCE"));//数据来源
		if(map.get("STATUS")==null){
			openSch.setStatus(DictCodeListExt2.数据状态_有效.getValue());
		}else{
			openSch.setStatus(map.get("STATUS"));//数据状态
		}
		openSch.setRevDate(DateUtil.parse(map.get("REV_DATE")));//撤销日
		openSch.setRemark(map.get("REMARK"));//备注
		return openSch;
	}
	
	/**
     * 将map转换为entity格式
     *
     * @param infoList 传入的listMap
     * @param entities 转换后的lis
     * @param listCloumn 表列名
     * @param interType 数据来源
     * @see
     * @since 1.0
     */
    private List<ChbPrdOpensch> convertMapToEntity(List<Map<String, String>> dataList,String dateSource) {
        // 获取产品表的列
    	List<ChbPrdOpensch> rs = new ArrayList<ChbPrdOpensch>();
        for (Map<String, String> map : dataList) {
        	ChbPrdOpensch t = new ChbPrdOpensch();
        	t.setStatus(DictCodeListExt1.维护数据状态_有效.getValue());
            for (String key : map.keySet()) {
            	if(key==null||key.equals("ERRMSG")){//待处理的数据
            		t.setStatus(DictCodeListExt1.维护数据状态_待处理.getValue());
            		continue;
            	}
                String value = map.get(key);
                Field field = ReflectionUtils.findField(ChbPrdOpensch.class,
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
                    } else if ("java.util.Date".equals(type.getTypeName())) {
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
            t.setSeqno(IDGenerator.generate(36));//主键
            // 创建人
            t.setCreateUser(CommonUtil.getUserName());
            t.setUpdateUser(CommonUtil.getUserName());
            // 创建部门
            t.setCreateDept(CommonUtil.getDeptCode());
            // 部门代码
            t.setDeptCode(CommonUtil.getDeptCode());
            // 机构代码
            t.setOrgCode(CommonUtil.getOrgCode());
            // 数据来源
            t.setDataSource(SysDataUtil.getNameFromDataDict(DictCode.数据来源, dateSource));
            rs.add(t);
        }
        return rs;
    }

}
