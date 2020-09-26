package com.joyintech.tams.report.submitmaintenance.proddeal.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.SAXException;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.common.ReadConfigurationUtil;
import com.joyintech.tams.report.common.TxtParseUtil;
import com.joyintech.tams.report.common.XmlParseUtil;
import com.joyintech.tams.common.base.entity.ChbPrdDeal;
import com.joyintech.tams.common.base.entity.ChbPrdFeeDeal;
import com.joyintech.tams.common.base.entity.ChbTypeMapping;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.report.framework.thread.JoyinThreadPoolExecutor;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.report.interf.common.constant.InterfMappingConstants;
import com.joyintech.tams.report.interf.common.imp.ImportGenerator;
import com.joyintech.tams.report.interf.common.imp.sqlldr.ImpColumn;
import com.joyintech.tams.report.interf.common.imp.sqlldr.ImpTable;
import com.joyintech.tams.report.submitmaintenance.equityinfo.util.EquityInfoCheckUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.svreport.interf.chb.assetinfo.dao.ChbTypeMappingMapper;
import com.joyintech.tams.svreport.interf.chb.proddeal.dao.ChbPrdDealInterfMapper;
import com.joyintech.tams.svreport.interf.chb.proddeal.service.ProdDealInterfService;
import com.joyintech.tools.collectors.JoyinCollectors;

@Service("prodDurationDeal001Service")
@Transactional(rollbackFor = Exception.class)
public class ProdDurationDeal001Service extends CommonExportService<ChbPrdDeal> {

	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * CHB_PRD_DEAL_NAME
	 */
	private static final String CHB_PRD_DEAL_NAME = "CHB_PRD_DEAL";

	/**
	 * CHB_PRD_FEE_DEAL_NAME
	 */
	private static final String CHB_PRD_FEE_DEAL_NAME = "CHB_PRD_FEE_DEAL";

	/**
	 * prodDurationDeal001Mapper
	 */
	@Autowired
	private ProdDealInterfService prodDealInterfService;
	

	@Autowired
	private ChbPrdDealInterfMapper chbPrdDealInterfMapper;
	
	@Autowired
	private ChbTypeMappingMapper chbTypeMappingMapper;
    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 不含有汉字验证
	 */
	public static final String PATTERN_IS_CHINA = "^[^\u4e00-\u9fa5]{0,}$";

	/**
	 * 数字30,2验证
	 */
	public static final String PATTERN_FIFTEEN_TWO = "([0-9]{0,28})(\\.(\\d){0,2})?$";

	/**
	 * 数字30,4验证
	 */
	public static final String PATTERN_FIFTEEN_FOUR = "([0-9]{0,26})(\\.(\\d){0,4})?$";

	/**
	 * 数字18.5
	 */
	public static final String PATTERN_EIGTEEN_FIFVE = "([0-9]{0,13})(\\.(\\d){0,5})?$";

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
	 * 上游接口文件编码格式
	 */
	@Value("${application.reportInterfFileEncoding}")
	public String reportInterfFileEncoding;
	
	 /**
     * sqlldr用数据库连接
     */
    @Value("${spring.datasource.reportSqlldrDatabaseName}")
    private String reportSqlldrDatabaseName;
    
	/**
	 * 上游接口解析分割符
	 */
	@Value("${application.reportInterfSplitCharacter}")
	private String reportInterfSplitCharacter;

	public String getEquityBusiDetailsByInterf(InputStream in, String type, String tmpPath, String suffix) {

		List<Map<String, String>> equityList = null;
		if ("xml".equals(suffix)) {
			try {
                equityList = XmlParseUtil.parse(in);
            } catch (ParserConfigurationException | SAXException | IOException e) {
                logger.error(e.getMessage(),e);
                return "fail";
            }
		} else if ("txt".equals(suffix)) {
			try {
                equityList = TxtParseUtil.parse(in, reportInterfFileEncoding, reportInterfSplitCharacter);
            } catch (ParserConfigurationException | SAXException | IOException e) {
                logger.error(e.getMessage(),e);
                return "fail";
            }
		}
		
		// 解析出内容日志打印
		// for (Map<String, String> map : equityList) {
		// System.out.println("解析的内容====================="+map.toString());
		// }
		// 从配置文件中读取需要覆盖的配置项 01覆盖02不覆盖
		String[] result = ReadConfigurationUtil.readPropertiesByGetResourceAsStream(CHB_PRD_DEAL_NAME)
				.split(GlobalConstant.COMMA);
		// 解析成功或失败的标识
		boolean success = true;
		if (equityList != null) {
			if (equityList.size() <= 1000) {
				success = excuteProdDealByStep(equityList, type, result, null, null, null);
			} else {
				// 创建表
				ImpTable table = new ImpTable(CHB_PRD_DEAL_NAME);
				table.setColumns(ImportGenerator.createImpTableColumns(CHB_PRD_DEAL_NAME));
				// 由于DTO字段和数据库字段名不匹配，修改字段名
//				for (ImpColumn column : table.getColumns()) {
//					if (StringUtil.equals(column.getName(), "EQUITY_CONF_DATE")) {
//						column.setValueField("eQuityConfDate");
//					}
//					if (StringUtil.equals(column.getName(), "SHARE_CONF_DATETIME")) {
//						column.setValueField("shareConfDatetime");
//					}
//				}
				// 创建一个线程池
				if (interfMaxCorePoolSize < 1) {
					interfMaxCorePoolSize = 20;
				}
				ExecutorService service = new JoyinThreadPoolExecutor(interfMaxCorePoolSize);
				List<Callable<Boolean>> tasks = new ArrayList<>();
				if (null != equityList) {
					int start = 0;
					int end = 0;
					int size = equityList.size();
					int max = (int) Math.ceil((double) size / 1000);
					for (int i = 0; i < max; i++) {
						start = end;
						end = Math.min(size, 1000 * (i + 1));
						List<Map<String, String>> subList = equityList.subList(start, end);
						tasks.add(new Callable<Boolean>() {

							@Override
							public Boolean call() {
								return excuteProdDealByStep(subList, type, result, tmpPath, table, null);
							}
						});
					}
				}

				// 为线程池添加任务
				List<Future<Boolean>> taskResult;
                try {
                    taskResult = service.invokeAll(tasks);
                } catch (InterruptedException e) {
                    logger.error(e.getMessage(),e);
                    Thread.currentThread().interrupt();
                    return "fail";
                }
				for (Future<Boolean> res : taskResult) {
					try {
                        success = success && res.get();
                    } catch (InterruptedException | ExecutionException e) {
                        logger.error(e.getMessage(),e);
                        Thread.currentThread().interrupt();
                        return "fail";
                    }
				}
				// 任务结束，停止线程池的所有线程
				service.shutdown();

				Collection<File> files = FileUtils.listFiles(new File(tmpPath), new String[] { "tmp" }, false);
				List<String> content = new ArrayList<>(equityList.size());
                List<String> subContent = new ArrayList<>(equityList.size());
                
				for (File f : files) {
                  if (f.getName().startsWith(CHB_PRD_FEE_DEAL_NAME)) {
	                  try {
                        subContent.addAll(FileUtils.readLines(f, Charset.forName("UTF-8")));
                    } catch (IOException e) {
                        logger.error(e.getMessage(),e);
                        return "fail";
                    }
	              }
                  
				  if (f.getName().startsWith(CHB_PRD_DEAL_NAME)) {
					try {
                        content.addAll(FileUtils.readLines(f, Charset.forName("UTF-8")));
                    } catch (IOException e) {
                        logger.error(e.getMessage(),e);
                        return "fail";
                    }
				  }
					f.delete();
				}
              
				// 系统是否使用的db2数据库
                boolean isDB2DataBase = CommonUtil.isDB2DataBase();
                if (isDB2DataBase) {
                    // db2数据库处理
                    if (CollectionUtils.isNotEmpty(content)) {
                        try {
                            success = success && ImportGenerator.importToDBByPart(table, tmpPath,
                                content, reportInterfOneTimeSqlLdrDataCount, reportInterfOneTimeSqlLdrCommitCount, "del", reportSqlldrDatabaseName);
                        } catch (IOException | InterruptedException e) {
                            logger.error(e.getMessage(),e);
                            Thread.currentThread().interrupt();
                            return "fail";
                        }
                    }
                    if (CollectionUtils.isNotEmpty(subContent)) {
                    	ImpTable subTable = new ImpTable(CHB_PRD_FEE_DEAL_NAME);
                        try {
                            success = success && ImportGenerator.importToDBByPart(subTable, tmpPath,
                                subContent, reportInterfOneTimeSqlLdrDataCount,
                                reportInterfOneTimeSqlLdrCommitCount, "del", reportSqlldrDatabaseName);
                        } catch (IOException | InterruptedException e) {
                            logger.error(e.getMessage(),e);
                            Thread.currentThread().interrupt();
                            return "fail";
                        }
                    }
                }
                else {
                	// oracle原逻辑
					if (CollectionUtils.isNotEmpty(content)) {
		               try {
                        success = success && ImportGenerator.importToDBByPart(table, tmpPath, content,
                                   reportInterfOneTimeSqlLdrDataCount, reportInterfOneTimeSqlLdrCommitCount,
                                   "txt", reportSqlldrDatabaseName);
                    } catch (IOException | InterruptedException e) {
                        logger.error(e.getMessage(),e);
                        Thread.currentThread().interrupt();
                        return "fail";
                    }
			        }
		          
					if (CollectionUtils.isNotEmpty(subContent)) {
						 ImpTable subTable = new ImpTable(CHB_PRD_FEE_DEAL_NAME);
		                 try {
                            success = success && ImportGenerator.importToDBByPart(subTable, tmpPath, subContent,
                                        reportInterfOneTimeSqlLdrDataCount, reportInterfOneTimeSqlLdrCommitCount,
                                        "txt", reportSqlldrDatabaseName);
                        } catch (IOException | InterruptedException e) {
                            logger.error(e.getMessage(),e);
                            Thread.currentThread().interrupt();
                            return "fail";
                        }
					}
                }
			}
		}
		// 数据解析完成，并导入数据库成功后，将ftp文件修改名称
		if (success) {
			return "success";
		}
		return "fail";
	}
	
	public String getEquityBusiDetailsByInterfFx(InputStream in, String type, String tmpPath, String suffix) {

		List<Map<String, String>> equityList = null;
		// if ("xml".equals(suffix)) {
		// try {
		// equityList = XmlParseUtil.parse(in);
		// } catch (ParserConfigurationException | SAXException | IOException e)
		// {
		// logger.error(e.getMessage(),e);
		// return "fail";
		// }
		// } else if ("txt".equals(suffix)) {
		try {
			byte[] b = { 0x01, 0x01 };
			reportInterfSplitCharacter = new String(b);
			equityList = TxtParseUtil.parseTxtFx(in, reportInterfFileEncoding, reportInterfSplitCharacter);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			return "fail";
		}
		// }

		// 解析出内容日志打印
		// for (Map<String, String> map : equityList) {
		// System.out.println("解析的内容====================="+map.toString());
		// }
		// 从配置文件中读取需要覆盖的配置项 01覆盖02不覆盖
//		String[] result = ReadConfigurationUtil.readPropertiesByGetResourceAsStream(CHB_PRD_DEAL_NAME)
//				.split(GlobalConstant.COMMA);
		// 解析成功或失败的标识
		boolean success = true;
		 logger.info("销售系统产品交易文件解析成功");
		if (equityList != null) {
			if (equityList.size() <= 1000) {
				success = excuteProdDealByStepFx(equityList, type, null, null, null);
			} else {
				// 创建表
				ImpTable table = new ImpTable(CHB_PRD_DEAL_NAME);
				table.setColumns(ImportGenerator.createImpTableColumns(CHB_PRD_DEAL_NAME));
				// 由于DTO字段和数据库字段名不匹配，修改字段名
				// for (ImpColumn column : table.getColumns()) {
				// if (StringUtil.equals(column.getName(), "EQUITY_CONF_DATE"))
				// {
				// column.setValueField("eQuityConfDate");
				// }
				// if (StringUtil.equals(column.getName(),
				// "SHARE_CONF_DATETIME")) {
				// column.setValueField("shareConfDatetime");
				// }
				// }
				// 创建一个线程池
				if (interfMaxCorePoolSize < 1) {
					interfMaxCorePoolSize = 20;
				}
				ExecutorService service = new JoyinThreadPoolExecutor(interfMaxCorePoolSize);
				List<Callable<Boolean>> tasks = new ArrayList<>();
				if (null != equityList) {
					int start = 0;
					int end = 0;
					int size = equityList.size();
					int max = (int) Math.ceil((double) size / 1000);
					for (int i = 0; i < max; i++) {
						start = end;
						end = Math.min(size, 1000 * (i + 1));
						List<Map<String, String>> subList = equityList.subList(start, end);
						tasks.add(new Callable<Boolean>() {

							@Override
							public Boolean call() {
								return excuteProdDealByStepFx(subList, type,  tmpPath, table, null);
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

				Collection<File> files = FileUtils.listFiles(new File(tmpPath), new String[] { "tmp" }, false);
				List<String> content = new ArrayList<>(equityList.size());
				List<String> subContent = new ArrayList<>(equityList.size());

				for (File f : files) {
					if (f.getName().startsWith(CHB_PRD_FEE_DEAL_NAME)) {
						try {
							subContent.addAll(FileUtils.readLines(f, Charset.forName("UTF-8")));
						} catch (IOException e) {
							logger.error(e.getMessage(), e);
							return "fail";
						}
					}

					if (f.getName().startsWith(CHB_PRD_DEAL_NAME)) {
						try {
							content.addAll(FileUtils.readLines(f, Charset.forName("UTF-8")));
						} catch (IOException e) {
							logger.error(e.getMessage(), e);
							return "fail";
						}
					}
					f.delete();
				}

				// 系统是否使用的db2数据库
				boolean isDB2DataBase = CommonUtil.isDB2DataBase();
				if (isDB2DataBase) {
					// db2数据库处理
					if (CollectionUtils.isNotEmpty(content)) {
						try {
							success = success && ImportGenerator.importToDBByPart(table, tmpPath, content,
									reportInterfOneTimeSqlLdrDataCount, reportInterfOneTimeSqlLdrCommitCount, "del",
									reportSqlldrDatabaseName);
						} catch (IOException | InterruptedException e) {
							logger.error(e.getMessage(), e);
							Thread.currentThread().interrupt();
							return "fail";
						}
					}
					if (CollectionUtils.isNotEmpty(subContent)) {
						ImpTable subTable = new ImpTable(CHB_PRD_FEE_DEAL_NAME);
						try {
							success = success && ImportGenerator.importToDBByPart(subTable, tmpPath, subContent,
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
							success = success && ImportGenerator.importToDBByPart(table, tmpPath, content,
									reportInterfOneTimeSqlLdrDataCount, reportInterfOneTimeSqlLdrCommitCount, "txt",
									reportSqlldrDatabaseName);
						} catch (IOException | InterruptedException e) {
							logger.error(e.getMessage(), e);
							Thread.currentThread().interrupt();
							return "fail";
						}
					}

					if (CollectionUtils.isNotEmpty(subContent)) {
						ImpTable subTable = new ImpTable(CHB_PRD_FEE_DEAL_NAME);
						try {
							success = success && ImportGenerator.importToDBByPart(subTable, tmpPath, subContent,
									reportInterfOneTimeSqlLdrDataCount, reportInterfOneTimeSqlLdrCommitCount, "txt",
									reportSqlldrDatabaseName);
						} catch (IOException | InterruptedException e) {
							logger.error(e.getMessage(), e);
							Thread.currentThread().interrupt();
							return "fail";
						}
					}
				}
			}
		}
		// 数据解析完成，并导入数据库成功后，将ftp文件修改名称
		if (success) {
			return "success";
		}
		return "fail";
	}
	/**
	 * @param equityList
	 *            投资者列表
	 * @param type
	 *            数据来源
	 * @param result
	 *            参数
	 * @param tmpPath
	 *            生成的临时文件存放路径
	 * @return 此次是否成功插入
	 */
	private boolean excuteProdDealByStep(List<Map<String, String>> equityList, String type, String[] result,
			String tmpPath, ImpTable table, ImpTable subTable) {
		// 投资者信息接口信息不为null处理
		if (equityList != null && equityList.size() > 0) {
			// 定义循环投资者信息变量
			ChbPrdDeal deal = null;
			// 定义新增数据list-验证不通过数据
			List<ChbPrdDeal> listNA = new ArrayList<ChbPrdDeal>();
			// 定义新增数据list-验证通过数据
			List<ChbPrdDeal> listNE = new ArrayList<ChbPrdDeal>();
			// 定义新增数据list-验证通过数据
			List<ChbPrdFeeDeal> listFeeE = new ArrayList<ChbPrdFeeDeal>();
			// 定义已存在数据list-原数据“待处理”，新接入数据是“待处理”
			List<ChbPrdDeal> listAA = new ArrayList<ChbPrdDeal>();
			// 定义已存在数据list-原数据“待处理”，新接入数据是“有效”
			List<ChbPrdDeal> listAE = new ArrayList<ChbPrdDeal>();
			// 定义已存在数据list-原数据“有效”，新接入数据还是“待处理”
			List<ChbPrdDeal> listOA = new ArrayList<ChbPrdDeal>();
			// 定义已存在数据list-原数据“有效”，新接入数据还是“有效”
			List<ChbPrdDeal> listOE = new ArrayList<ChbPrdDeal>();
			HashMap<String,ChbPrdDeal> dlMap = new HashMap<String,ChbPrdDeal>(); 
			String key = "";
			for (Map<String, String> map : equityList) {
				if(StringUtil.isNotEmpty(map.get("STATUS")) && "A".equals(map.get("STATUS"))){
					continue;
				}
				
				if(StringUtil.isNotEmpty(map.get("STATUS")) 
						&& "D".equals(map.get("STATUS"))
						&& StringUtil.isEmpty(map.get("REV_DATE"))){
					continue;
				}
				
				if(StringUtil.isNotEmpty(map.get("REV_DATE")) && StringUtil.isNotEmpty(map.get("SETTDATE_DATE"))){
					//同一天既有效又撤销的数据不入库
					if(map.get("REV_DATE").equals(map.get("SETTDATE_DATE"))){
						continue;
					}
				}
				deal = dataConversionEntity(map);
				
				deal.setDataSource(type); // 12:销售系统
				deal.setSeqno(IDGenerator.generate(36));
				deal.setCreateUser(SecurityUtil.getLoginUser() == null ?"system" :SecurityUtil.getLoginUser().getUid());
				deal.setUpdateUser(SecurityUtil.getLoginUser() == null ?"system" :SecurityUtil.getLoginUser().getUid());
				
				if(null != deal.getRedemptionFee() && deal.getRedemptionFee().compareTo(BigDecimal.ZERO) > 0){
					//赎回费作为本行管理费插入费用交易表
					ChbPrdFeeDeal feeDeal = getFeeDealModel(deal);
					listFeeE.add(feeDeal);
				}
				//该费用存入管理费表
				deal.setRedemptionFee(BigDecimal.ZERO);
				
				listNE.add(deal);
				//认购数据需要更新要素表存续状态为01 存续，到期数据需要更新02 终止
				if(DictCodeListExt2.产品存续期登记交易类型_募集期认购.getValue().equals(deal.getTradeType())
						|| DictCodeListExt2.产品存续期登记交易类型_到期兑付.getValue().equals(deal.getTradeType())){
						key = (null == deal.getFprodId() ? deal.getProdId() : deal.getFprodId());
						if(dlMap.containsKey(key)){
							if(DictCodeListExt2.产品存续期登记交易类型_到期兑付.getValue().equals(deal.getTradeType())){
								dlMap.put(key, deal);
							}
						}else{
							dlMap.put(key, deal);
						}
					}
				//TODO 日后需处理 其他场景的数据
				
			}
			
			if (listNE != null && listNE.size() > 0) {
				//如果是增量文件则不需要删除
				prodDealInterfService.deletePrdDealBatch(listNE);
				if (tmpPath == null) {
                    // 插入维护信息表
					prodDealInterfService.insertPrdDealBatch(listNE);
                }
                else {
                    createDataFile(tmpPath + "/CHB_PRD_DEAL", listNE, table.getColumns());
                }
			}
				
			if (listFeeE != null && listFeeE.size() > 0) {
				//如果是增量文件则不需要删除
				prodDealInterfService.deletePrdFeeDealBatch(listFeeE);
				if (tmpPath == null) {
                    // 插入维护信息表
					prodDealInterfService.insertPrdFeeDealBatch(listFeeE);
                }
                else {
                	ImpTable feeTable = new ImpTable(CHB_PRD_FEE_DEAL_NAME);
    				table.setColumns(ImportGenerator.createImpTableColumns(CHB_PRD_FEE_DEAL_NAME));
                	createFeeDataFile(tmpPath + "/CHB_PRD_FEE_DEAL", listFeeE, feeTable.getColumns());
                }
			}
			logger.debug("反向更新要素信息:----开始-----" + df.format(new Date()));
			//反向更新要素信息
			ArrayList<ChbPrdDeal> updList = new ArrayList<ChbPrdDeal>();
			for(Map.Entry<String, ChbPrdDeal> entry: dlMap.entrySet()){
				ChbPrdDeal dl = entry.getValue();
				if(null != dl.getFprodId()){
					dl.setProdId(dl.getFprodId());
				}
				updList.add(dl);
	        }
			
			if(!StringUtil.isEmptyList(updList)){
				prodDealInterfService.updatePrdBaseInfo(updList);
			}
			logger.debug("反向更新要素信息:----结束-----" + df.format(new Date()));
			return (CollectionUtils.isNotEmpty(listNA) || CollectionUtils.isNotEmpty(listNE)
					|| CollectionUtils.isNotEmpty(listAA) || CollectionUtils.isNotEmpty(listAE)
					|| CollectionUtils.isNotEmpty(listOA) || CollectionUtils.isNotEmpty(listOE)
					|| CollectionUtils.isNotEmpty(listFeeE));
		}
		return false;
	}

	/**
	 * @param equityList
	 *            投资者列表
	 * @param type
	 *            数据来源
	 * @param result
	 *            参数
	 * @param tmpPath
	 *            生成的临时文件存放路径
	 * @return 此次是否成功插入
	 */
	private boolean excuteProdDealByStepFx(List<Map<String, String>> equityList, String type, 
			String tmpPath, ImpTable table, ImpTable subTable) {
		// 投资者信息接口信息不为null处理
		if (equityList != null && equityList.size() > 0) {
			 logger.info("销售系统产品交易文件处理开始");
			// 定义新增数据list-验证不通过数据
			List<ChbPrdDeal> listNA = new ArrayList<ChbPrdDeal>();
			// 定义新增数据list-验证通过数据
			List<ChbPrdDeal> listNE = new ArrayList<ChbPrdDeal>();
			// 定义新增数据list-验证通过数据
			List<ChbPrdFeeDeal> listFeeE = new ArrayList<ChbPrdFeeDeal>();
			// 定义已存在数据list-原数据“待处理”，新接入数据是“待处理”
			List<ChbPrdDeal> listAA = new ArrayList<ChbPrdDeal>();
			// 定义已存在数据list-原数据“待处理”，新接入数据是“有效”
			List<ChbPrdDeal> listAE = new ArrayList<ChbPrdDeal>();
			// 定义已存在数据list-原数据“有效”，新接入数据还是“待处理”
			List<ChbPrdDeal> listOA = new ArrayList<ChbPrdDeal>();
			// 定义已存在数据list-原数据“有效”，新接入数据还是“有效”
			List<ChbPrdDeal> listOE = new ArrayList<ChbPrdDeal>();
			// 客户类别映射
			List<ChbTypeMapping> customerType2 = chbTypeMappingMapper
					.selectMappingBySrcDataType(InterfMappingConstants.MAAPING_CUSTOMER_GROUP_ID);
			Map<String, ChbTypeMapping> customerMap = customerType2.stream()
					.collect(JoyinCollectors.toMap(x -> x.getSrcdatatypeid(), x -> x));
			// 币种映射
			List<ChbTypeMapping> ccyTypes = chbTypeMappingMapper
					.selectMappingBySrcDataType(InterfMappingConstants.MAAPING_CCY);
			Map<String, String> ccyMap = ccyTypes.stream()
					.collect(JoyinCollectors.toMap(x -> x.getSrcdatatypeid(), x -> x.getObjdatatypeid()));
			// 查询产品要素 插入发行机构代码
			List<ChbPrdDeal> infoList = chbPrdDealInterfMapper.getAllPrdIssuePartyFromBaseInfo();
			Map<String, String> infoMap = infoList.stream()
					.collect(JoyinCollectors.toMap(x -> x.getProdId(), x -> x.getIssuePartyId()));
			HashMap<String,ChbPrdDeal> dlMap = new HashMap<String,ChbPrdDeal>(); 
			String key = "";
			for (Map<String, String> map : equityList) {
				// if(StringUtil.isNotEmpty(map.get("STATUS")) &&
				// "A".equals(map.get("STATUS"))){
				// continue;
				// }
				//
				// if(StringUtil.isNotEmpty(map.get("STATUS"))
				// && "D".equals(map.get("STATUS"))
				// && StringUtil.isEmpty(map.get("REV_DATE"))){
				// continue;
				// }
				//
				// if(StringUtil.isNotEmpty(map.get("REV_DATE")) &&
				// StringUtil.isNotEmpty(map.get("SETTDATE_DATE"))){
				// //同一天既有效又撤销的数据不入库
				// if(map.get("REV_DATE").equals(map.get("SETTDATE_DATE"))){
				// continue;
				// }
				// }
				List<ChbPrdDeal> deals = dataConversionEntityFx(map, customerMap, infoMap, ccyMap);
				// // 定义循环投资者信息变量
				// ChbPrdDeal deal = null;
				for (ChbPrdDeal single : deals) {
					single.setDataSource(type); // 12:销售系统
					single.setSeqno(IDGenerator.generate(36));
					single.setCreateUser(
							SecurityUtil.getLoginUser() == null ? "system" : SecurityUtil.getLoginUser().getUid());
					single.setUpdateUser(
							SecurityUtil.getLoginUser() == null ? "system" : SecurityUtil.getLoginUser().getUid());

					if (null != single.getRedemptionFee() && single.getRedemptionFee().compareTo(BigDecimal.ZERO) > 0) {
						// 赎回费作为本行管理费插入费用交易表
						ChbPrdFeeDeal feeDeal = getFeeDealModel(single);
						listFeeE.add(feeDeal);
					}
					// 该费用存入管理费表
					single.setRedemptionFee(BigDecimal.ZERO);
					listNE.add(single);
					//认购数据需要更新要素表存续状态为01 存续，到期数据需要更新02 终止
					if(DictCodeListExt2.产品存续期登记交易类型_募集期认购.getValue().equals(single.getTradeType())
							|| DictCodeListExt2.产品存续期登记交易类型_到期兑付.getValue().equals(single.getTradeType())){
							key = (null == single.getFprodId() ? single.getProdId() : single.getFprodId());
							if(dlMap.containsKey(key)){
								if(DictCodeListExt2.产品存续期登记交易类型_到期兑付.getValue().equals(single.getTradeType())){
									dlMap.put(key, single);
								}
							}else{
								dlMap.put(key, single);
							}
						}
				}

			}

			if (listNE != null && listNE.size() > 0) {
				// 如果是增量文件则不需要删除
				prodDealInterfService.deletePrdDealBatch(listNE);
				if (tmpPath == null) {
					// 插入维护信息表
					prodDealInterfService.insertPrdDealBatch(listNE);
				} else {
					createDataFile(tmpPath + "/CHB_PRD_DEAL", listNE, table.getColumns());
				}
			}

			if (listFeeE != null && listFeeE.size() > 0) {
				// 如果是增量文件则不需要删除
				prodDealInterfService.deletePrdFeeDealBatch(listFeeE);
				if (tmpPath == null) {
					// 插入维护信息表
					prodDealInterfService.insertPrdFeeDealBatch(listFeeE);
				} else {
					ImpTable feeTable = new ImpTable(CHB_PRD_FEE_DEAL_NAME);
					table.setColumns(ImportGenerator.createImpTableColumns(CHB_PRD_FEE_DEAL_NAME));
					createFeeDataFile(tmpPath + "/CHB_PRD_FEE_DEAL", listFeeE, feeTable.getColumns());
				}
			}
			logger.debug("反向更新要素信息:----开始-----" + df.format(new Date()));
			//反向更新要素信息
			ArrayList<ChbPrdDeal> updList = new ArrayList<ChbPrdDeal>();
			for(Map.Entry<String, ChbPrdDeal> entry: dlMap.entrySet()){
				ChbPrdDeal dl = entry.getValue();
				if(null != dl.getFprodId()){
					dl.setProdId(dl.getFprodId());
				}
				updList.add(dl);
	        }
			
			if(!StringUtil.isEmptyList(updList)){
				prodDealInterfService.updatePrdBaseInfo(updList);
			}
			logger.debug("反向更新要素信息:----结束-----" + df.format(new Date()));
			logger.info("销售系统产品交易文件处理结束");
			logger.info("处理条数listNE："+listNE.size());
			
			return (CollectionUtils.isNotEmpty(listNA) || CollectionUtils.isNotEmpty(listNE)
					|| CollectionUtils.isNotEmpty(listAA) || CollectionUtils.isNotEmpty(listAE)
					|| CollectionUtils.isNotEmpty(listOA) || CollectionUtils.isNotEmpty(listOE)
					|| CollectionUtils.isNotEmpty(listFeeE));
		}
		return false;
	}
	/**
	 * 
	 * @param deal
	 * @return
	 */
	private ChbPrdFeeDeal getFeeDealModel(ChbPrdDeal deal) {
		ChbPrdFeeDeal feeDeal = new ChbPrdFeeDeal();
		feeDeal.setSeqno(IDGenerator.generate(36));
		feeDeal.setTradeId(deal.getTradeId());
		feeDeal.setProdId(deal.getProdId());
		feeDeal.setFprodId(deal.getFprodId());
		feeDeal.setFeeId("BJGGLF_JYSHF");
		feeDeal.setFeeName(DictCodeListExt2.费用二级类型_本机构管理费.getCodeName());
		feeDeal.setFeeType(DictCodeListExt2.费用类型_本机构费用.getValue());
		feeDeal.setFeeSectype(DictCodeListExt2.费用二级类型_本机构管理费.getValue());
		feeDeal.setCcy(deal.getCcy());
		feeDeal.setAmt(deal.getRedemptionFee());
		feeDeal.setPayDate(deal.getPayDate());
		feeDeal.setStatus(deal.getStatus());
		feeDeal.setDataSource(deal.getDataSource());
		feeDeal.setRevDate(deal.getRevDate());
		feeDeal.setCreateUser(SecurityUtil.getLoginUser() == null ?"system" :SecurityUtil.getLoginUser().getUid());
		feeDeal.setUpdateUser(SecurityUtil.getLoginUser() == null ?"system" :SecurityUtil.getLoginUser().getUid());
		feeDeal.setRemark("赎回费");
		
		return feeDeal;
	}

	/**
     * 创建数据文件
     *
     * @param path 文件路径
     * @param equityList 对象列表
     * @param columns 数据库表对应的列
     */
    private void createDataFile(String path, List<ChbPrdDeal> equityList,List<ImpColumn> columns) {
        // 生成txt文件
        List<String> lines = new ArrayList<>(equityList.size());
        equityList.forEach(item -> lines.add(ImportGenerator.buildTxt(item, columns)));
        try {
            FileUtils.writeLines(
                new File(path + GlobalConstant.UNDER_LINE + UUID.randomUUID().toString() + ".tmp"),
                "UTF-8", lines, true);
        }
        catch (IOException e) {
        	logger.error(e.getMessage(),e);
            e.getMessage();
        }
    }
	
    /**
     * 
     * @param path
     * @param listFeeE
     * @param columns
     */
	private void createFeeDataFile(String path, List<ChbPrdFeeDeal> listFeeE, List<ImpColumn> columns) {
		// 生成txt文件
        List<String> lines = new ArrayList<>(listFeeE.size());
        listFeeE.forEach(item -> lines.add(ImportGenerator.buildTxt(item, columns)));
        try {
            FileUtils.writeLines(
                new File(path + GlobalConstant.UNDER_LINE + UUID.randomUUID().toString() + ".tmp"),
                "UTF-8", lines, true);
        }
        catch (IOException e) {
            e.getMessage();
        	logger.error(e.getMessage(),e);
        }
	}

	/**
	 * 
	 * @param map
	 * @return
	 */
	private ChbPrdDeal dataConversionEntity(Map<String, String> map) {
		ChbPrdDeal ibd = new ChbPrdDeal();
		List<String> message = new ArrayList<String>();
		// 处理是的map
		// System.out.println("处理时的map========================"+map.toString());
		// 上游流水号
		if (StringUtil.isEmpty(map.get("TRADE_ID"))) {
			message.add(this.getMessage("CHB_EQUITYINFO_E_001", "流水号"));
		}
		// 机构代码
		if (StringUtil.isEmpty(map.get("ISSUE_PARTY_ID"))) {
			message.add(this.getMessage("CHB_EQUITYINFO_E_001", "发行机构代码"));
		}
		// 产品代码校验
		if (StringUtil.isEmpty(map.get("PROD_ID"))) {
			message.add(this.getMessage("CHB_EQUITYINFO_E_001", "产品代码"));
		} else if (EquityInfoCheckUtil.isExceedLength(map.get("PROD_ID"), 50)) {
			message.add(this.getMessage("CHB_EQUITYINFO_E_010", "产品代码最大50","50"));
			ibd.setProdId(map.get("PROD_ID").substring(0,50));
		} else{
			ibd.setProdId(map.get("PROD_ID"));
		}
		
		//父产品代码
		if (!StringUtil.isEmpty(map.get("FPROD_ID"))) {
			if (EquityInfoCheckUtil.isExceedLength(map.get("FPROD_ID"), 50)) {
				message.add(this.getMessage("CHB_EQUITYINFO_E_010", "父产品代码最大50","50"));
				ibd.setFprodId(map.get("FPROD_ID").substring(0,50));
			} 
		} else{
			ibd.setFprodId(map.get("FPROD_ID"));
		}
		
		// 识别标识
		// 获取投资者信息报送表中的数据
		Map<String, String> indentifyMarkMap = chbPrdDealInterfMapper.getAllRegistBankCodeSet().stream()
				.collect(JoyinCollectors.toMap(x -> x.get("INDENTIFY_MARK"), x -> x.get("ORG_CODE")));

		if (StringUtil.isEmpty(map.get("INDENTIFY_MARK"))) {
			message.add(this.getMessage("CHB_EQUITYINFO_E_001", "识别标识"));
		} else if (EquityInfoCheckUtil.isExceedLength(map.get("INDENTIFY_MARK"), 30)) {
			message.add(this.getMessage("CHB_EQUITYINFO_E_010", "识别标识最大30","30"));
			ibd.setIndentifyMark(map.get("INDENTIFY_MARK").substring(0,30));
		} else if (EquityInfoCheckUtil.findPatern(PATTERN_IS_CHINA, map.get("INDENTIFY_MARK"))) {
			message.add(this.getMessage("CHB_EQUITYINFO_E_012", "识别标识"));
			ibd.setIndentifyMark(map.get("INDENTIFY_MARK"));
		} else if (!indentifyMarkMap.containsKey(map.get("INDENTIFY_MARK"))) {
			message.add(this.getMessage("INVESTREGIST_001"));
			ibd.setIndentifyMark(map.get("INDENTIFY_MARK"));
		} else{
			ibd.setIndentifyMark(map.get("INDENTIFY_MARK"));
		}
		// 销售区域
		if (StringUtil.isEmpty(map.get("BUY_PLACE"))) {
			message.add(this.getMessage("CHB_EQUITYINFO_E_001", "销售区域"));
		}
		// 确认日
		if (StringUtil.isEmpty(map.get("SETTDATE_DATE"))) {
			message.add(this.getMessage("CHB_EQUITYINFO_E_001", "确认日"));
		} else if (EquityInfoCheckUtil.isExceedLength(map.get("SETTDATE_DATE"), 8)) {
			message.add(this.getMessage("CHB_EQUITYINFO_E_010", "确认日最大8","8"));
		} else{
			boolean flag = true;
			try{
                DateUtil.parse(map.get("SETTDATE_DATE"));
            }
            catch(Exception e){
            	flag = false;
                message.add(this.getMessage("CHB_EQUITYINFO_E_001", "确认日不符合格式"));
            }
			if(flag){
				ibd.setSettdateDate(DateUtil.parse(map.get("SETTDATE_DATE")));
			}
		}
		
		// 本金支付日
		if (StringUtil.isEmpty(map.get("PAY_DATE"))) {
			message.add(this.getMessage("CHB_EQUITYINFO_E_001", "本金支付日"));
		} else if (EquityInfoCheckUtil.isExceedLength(map.get("PAY_DATE"), 8)) {
			message.add(this.getMessage("CHB_EQUITYINFO_E_010", "本金支付日最大8","8"));
		} else{
			boolean flag = true;
			try{
                DateUtil.parse(map.get("PAY_DATE"));
            }
            catch(Exception e){
            	flag = false;
                message.add(this.getMessage("CHB_EQUITYINFO_E_001", "本金支付日不符合格式"));
            }
			if(flag){
				ibd.setPayDate(DateUtil.parse(map.get("PAY_DATE")));
			}
		}
		
		// 利息支付日
		if (StringUtil.isEmpty(map.get("INTEREST_DATE"))) {
			message.add(this.getMessage("CHB_EQUITYINFO_E_001", "利息支付日"));
		} else if (EquityInfoCheckUtil.isExceedLength(map.get("INTEREST_DATE"), 8)) {
			message.add(this.getMessage("CHB_EQUITYINFO_E_010", "利息支付日最大8","8"));
		} else{
			boolean flag = true;
			try{
                DateUtil.parse(map.get("INTEREST_DATE"));
            }
            catch(Exception e){
            	flag = false;
                message.add(this.getMessage("CHB_EQUITYINFO_E_001", "利息支付日不符合格式"));
            }
			if(flag){
				ibd.setInterestDate(DateUtil.parse(map.get("INTEREST_DATE")));
			}
		}
		
		//创建时间
		if (!StringUtil.isEmpty(map.get("CREATE_TIME"))) {
			if (EquityInfoCheckUtil.isExceedLength(map.get("CREATE_TIME"), 17)) {
				message.add(this.getMessage("CHB_EQUITYINFO_E_010", "创建时间最大17","17"));
			} 
		} 
		
		//更新时间
		if (!StringUtil.isEmpty(map.get("UPDATE_TIME"))) {
			if (EquityInfoCheckUtil.isExceedLength(map.get("UPDATE_TIME"), 17)) {
				message.add(this.getMessage("CHB_EQUITYINFO_E_010", "更新时间最大17","17"));
			} 
		} 
		
		// 业务种类
		if (StringUtil.isEmpty(map.get("TRADE_TYPE"))) {
			message.add(this.getMessage("CHB_EQUITYINFO_E_001", "业务种类"));
		} else if (EquityInfoCheckUtil.isDictCodeKeyExist(DictCode.产品存续期登记交易类型, map.get("TRADE_TYPE"))) {
			message.add(this.getMessage("CHB_EQUITYINFO_E_004", "业务种类"));
		}
		
		// 币种
		if (StringUtil.isEmpty(map.get("CCY"))) {
			message.add(this.getMessage("CHB_EQUITYINFO_E_001", "币种"));
		} else if (EquityInfoCheckUtil.isDictCodeKeyExist(DictCode.币种_中债, map.get("CCY"))) {
			message.add(this.getMessage("CHB_EQUITYINFO_E_004", "币种"));
		}
		
		// 交易本金
		if (StringUtil.isEmpty(map.get("TRADE_PRINCE"))) {
			message.add(this.getMessage("CHB_EQUITYINFO_E_001", "交易本金"));
		} else if (EquityInfoCheckUtil.findPatern(PATTERN_FIFTEEN_TWO, map.get("TRADE_PRINCE"))) {
			message.add(this.getMessage("CHB_EQUITYINFO_E_012", "金额"));
		} else if (new BigDecimal(map.get("TRADE_PRINCE")).compareTo(new BigDecimal(0)) < 0) {
			message.add(this.getMessage("REPORT_COMMON_E_0023", "金额", "0"));
		} else{
			ibd.setTradePrince(new BigDecimal(map.get("TRADE_PRINCE")));
		}
		// 交易份额
		if (StringUtil.isEmpty(map.get("TRADE_QTY"))) {
			message.add(this.getMessage("CHB_EQUITYINFO_E_001", "交易份额"));
		} else if (EquityInfoCheckUtil.findPatern(PATTERN_FIFTEEN_FOUR, map.get("TRADE_QTY"))) {
			message.add(this.getMessage("CHB_EQUITYINFO_E_012", "交易份额"));
		} else if (new BigDecimal(map.get("TRADE_QTY")).compareTo(new BigDecimal(0)) < 0) {
			message.add(this.getMessage("REPORT_COMMON_E_0023", "交易份额", "0"));
		} else{
			ibd.setTradeQty(new BigDecimal(map.get("TRADE_QTY")));
		}
		
		// 赎回费
		if(!StringUtil.isEmpty(map.get("REDEMPTION_FEE"))){
			if (new BigDecimal(map.get("REDEMPTION_FEE")).compareTo(new BigDecimal(0)) < 0) {
				message.add(this.getMessage("REPORT_COMMON_E_0023", "赎回费", "0"));
			}else{
				ibd.setRedemptionFee(new BigDecimal(map.get("REDEMPTION_FEE")));
				ibd.setTradePrince(ibd.getTradePrince().subtract(ibd.getRedemptionFee()));
			}
		}
		
		// 收益币种
		if (StringUtil.isEmpty(map.get("INTEREST_CCY"))) {
			message.add(this.getMessage("CHB_EQUITYINFO_E_001", "币种"));
		} else if (EquityInfoCheckUtil.isDictCodeKeyExist(DictCode.币种_中债, map.get("INTEREST_CCY"))) {
			message.add(this.getMessage("CHB_EQUITYINFO_E_004", "币种"));
		}
		
		// 数据状态
		if (StringUtil.isEmpty(map.get("STATUS"))) {
			message.add(this.getMessage("CHB_EQUITYINFO_E_001", "数据状态"));
		} else {
			// 撤销日 数据状态为D-删除时，必填
			if ("D".equals(map.get("STATUS"))){
				if (StringUtil.isEmpty(map.get("REV_DATE"))) {
					message.add(this.getMessage("CHB_EQUITYINFO_E_001", "撤销日"));
				}else{
					boolean flag = true;
					try{
		                DateUtil.parse(map.get("REV_DATE"));
		            }
		            catch(Exception e){
		            	flag = false;
		                message.add(this.getMessage("CHB_EQUITYINFO_E_001", "撤销日不符合格式"));
		            }
					if(flag){
						ibd.setRevDate(DateUtil.parse(map.get("REV_DATE")));
					}
				}
			}
		}
		// 客户一级类型
		if (StringUtil.isEmpty(map.get("CUSTOMER_TYPE1"))) {
			message.add(this.getMessage("CHB_EQUITYINFO_E_001", "客户一级类型"));
		}
		// 客户二级类型
		if (StringUtil.isEmpty(map.get("CUSTOMER_TYPE2"))) {
			message.add(this.getMessage("CHB_EQUITYINFO_E_001", "客户二级类型"));
		}
		if (message.isEmpty()) {
			if ("D".equals(map.get("STATUS"))){
				ibd.setStatus("D");
			}else{
				ibd.setStatus("E");
			}
		} else {
			ibd.setStatus("A");
		}
		
		ibd.setTradeId(map.get("TRADE_ID"));
		ibd.setIssuePartyId(map.get("ISSUE_PARTY_ID"));
		ibd.setBuyPlace(map.get("BUY_PLACE"));
		ibd.setTradeType(map.get("TRADE_TYPE"));	
		if(StringUtil.isNotEmpty(map.get("INT_AMT"))){
			ibd.setIntAmt(new BigDecimal(map.get("INT_AMT")));
		}
		ibd.setCcy(map.get("CCY"));
		ibd.setInterestCcy(map.get("INTEREST_CCY"));	
		ibd.setCustomerType1(map.get("CUSTOMER_TYPE1"));
		ibd.setCustomerType2(map.get("CUSTOMER_TYPE2"));
		ibd.setRemark(map.get("REMARK"));
		if (!message.isEmpty()) {
			ibd.setRemark(ibd.getRemark() + ";(数据校验结果:" + message + ")" );
		}
		
		

		return ibd;
	}
	/**
	 * 
	 * @param map
	 * @return
	 */
	private List<ChbPrdDeal> dataConversionEntityFx(Map<String, String> map, Map<String, ChbTypeMapping> customerMap,
			Map<String, String> infoMap, Map<String, String> ccyMap) {
		List<ChbPrdDeal> deals = new ArrayList<ChbPrdDeal>();
		ChbPrdDeal ibd = new ChbPrdDeal();
		List<String> message = new ArrayList<String>();
		// 处理是的map
		// System.out.println("处理时的map========================"+map.toString());
		// 上游流水号
		// if (StringUtil.isEmpty(map.get("TRADE_ID"))) {
		// message.add(this.getMessage("CHB_EQUITYINFO_E_001", "流水号"));
		// }

		// 产品代码校验
		if (StringUtil.isEmpty(map.get("PROD_ID"))) {
			message.add(this.getMessage("CHB_EQUITYINFO_E_001", "产品代码"));
		} else if (EquityInfoCheckUtil.isExceedLength(map.get("PROD_ID"), 50)) {
			message.add(this.getMessage("CHB_EQUITYINFO_E_010", "产品代码最大50", "50"));
			ibd.setProdId(map.get("PROD_ID").substring(0, 50));
		} else {
			ibd.setProdId(map.get("PROD_ID"));
		}
		// 机构代码
		ibd.setIssuePartyId(infoMap.get(map.get("PROD_ID")));
		// 父产品代码
		if (!StringUtil.isEmpty(map.get("FPROD_ID"))) {
			if (EquityInfoCheckUtil.isExceedLength(map.get("FPROD_ID"), 50)) {
				message.add(this.getMessage("CHB_EQUITYINFO_E_010", "父产品代码最大50", "50"));
				ibd.setFprodId(map.get("FPROD_ID").substring(0, 50));
			}
		} else {
			ibd.setFprodId(map.get("FPROD_ID"));
		}

		// 识别标识
		// 获取投资者信息报送表中的数据
		// Map<String, String> indentifyMarkMap =
		// chbPrdDealInterfMapper.getAllRegistBankCodeSet().stream()
		// .collect(JoyinCollectors.toMap(x -> x.get("INDENTIFY_MARK"), x ->
		// x.get("ORG_CODE")));

		// if (StringUtil.isEmpty(map.get("INDENTIFY_MARK"))) {
		// message.add(this.getMessage("CHB_EQUITYINFO_E_001", "识别标识"));
		// } else if
		// (EquityInfoCheckUtil.isExceedLength(map.get("INDENTIFY_MARK"), 30)) {
		// message.add(this.getMessage("CHB_EQUITYINFO_E_010",
		// "识别标识最大30","30"));
		// ibd.setIndentifyMark(map.get("INDENTIFY_MARK").substring(0,30));
		// } else if (EquityInfoCheckUtil.findPatern(PATTERN_IS_CHINA,
		// map.get("INDENTIFY_MARK"))) {
		// message.add(this.getMessage("CHB_EQUITYINFO_E_012", "识别标识"));
		// ibd.setIndentifyMark(map.get("INDENTIFY_MARK"));
		// } else if (!indentifyMarkMap.containsKey(map.get("INDENTIFY_MARK")))
		// {
		// message.add(this.getMessage("INVESTREGIST_001"));
		// ibd.setIndentifyMark(map.get("INDENTIFY_MARK"));
		// } else{
		// ibd.setIndentifyMark(map.get("INDENTIFY_MARK"));
		// }
		// 销售区域
		if (StringUtil.isEmpty(map.get("BUY_PLACE"))) {
			message.add(this.getMessage("CHB_EQUITYINFO_E_001", "销售区域"));
		}
		// 确认日
		if (StringUtil.isEmpty(map.get("PUB_DATE"))) {
			message.add(this.getMessage("CHB_EQUITYINFO_E_001", "下发日"));
		} else if (EquityInfoCheckUtil.isExceedLength(map.get("PUB_DATE"), 8)) {
			message.add(this.getMessage("CHB_EQUITYINFO_E_010", "下发日", "8"));
		} else {
			boolean flag = true;
			try {
				DateUtil.parse(map.get("PUB_DATE"));
			} catch (Exception e) {
				flag = false;
				message.add(this.getMessage("REPORT_COMMON_FILE_E_0013", "下发日不符合格式"));
			}
			if (flag) {
				ibd.setSettdateDate(DateUtil.parse(map.get("PUB_DATE")));
				ibd.setPayDate(DateUtil.parse(map.get("PUB_DATE")));
				ibd.setInterestDate(DateUtil.parse(map.get("PUB_DATE")));
			}
		}

		// 币种
		String ccy = map.get("CCY");
		if (ccy != null) {
			ccy = ccyMap.get(ccy);
		}
		if (StringUtil.isEmpty(ccy)) {
			message.add(this.getMessage("CHB_EQUITYINFO_E_001", "币种"));
		} else if (EquityInfoCheckUtil.isDictCodeKeyExist(DictCode.币种_中债, ccy)) {
			message.add(this.getMessage("CHB_EQUITYINFO_E_004", "币种"));
		}

		// 赎回费
		ibd.setTradePrince(BigDecimal.ZERO);

		// 数据状态
		// if (StringUtil.isEmpty(map.get("STATUS"))) {
		// message.add(this.getMessage("CHB_EQUITYINFO_E_001", "数据状态"));
		// } else {
		// // 撤销日 数据状态为D-删除时，必填
		// if ("D".equals(map.get("STATUS"))){
		// if (StringUtil.isEmpty(map.get("REV_DATE"))) {
		// message.add(this.getMessage("CHB_EQUITYINFO_E_001", "撤销日"));
		// }else{
		// boolean flag = true;
		// try{
		// DateUtil.parse(map.get("REV_DATE"));
		// }
		// catch(Exception e){
		// flag = false;
		// message.add(this.getMessage("CHB_EQUITYINFO_E_001", "撤销日不符合格式"));
		// }
		// if(flag){
		// ibd.setRevDate(DateUtil.parse(map.get("REV_DATE")));
		// }
		// }
		// }
		// }
		// if (message.isEmpty()) {
		// if ("D".equals(map.get("STATUS"))){
		// ibd.setStatus("D");
		// }else{
		// ibd.setStatus("E");
		// }
		// } else {
		// ibd.setStatus("A");
		// }
		ibd.setStatus("E");
		// 销售区域代码
		ibd.setBuyPlace(map.get("BUY_PLACE"));
		// 是否代销
		ibd.setIsagency("02");
		ibd.setCcy(ccy);
		ibd.setInterestCcy(ccy);
		String c2 = customerMap.get(map.get("CUSTOMER_GROUP_ID")).getObjdatatypeid();
		String c1 = null;
		if (c2 != null && c2.length() > 3) {
			c1 = c2.substring(0, 3);
		}
		String custCountsS=map.get("CUST_COUNTS");
		if (StringUtil.isEmpty(custCountsS)) {
			message.add(this.getMessage("CHB_EQUITYINFO_E_001", "保有量客户数"));
		}
		BigDecimal custCounts=BigDecimal.ZERO;
		try{
			custCounts=new BigDecimal(custCountsS);
		}catch(Exception e){
			message.add(this.getMessage("CHB_EQUITYINFO_E_003", "保有量客户数"));
		}
		ibd.setCustCounts(custCounts);
		ibd.setCustomerType1(c1);
		ibd.setCustomerType2(c2);
		ibd.setIndentifyMark(ibd.getProdId() + "=" + c1 + "=" + c2);

		ibd.setRemark(map.get("REMARK"));
		if (!message.isEmpty()) {
			ibd.setRemark(ibd.getRemark() + ";(数据校验结果:" + message + ")");
		}
		// 交易类型
		if (map.get("SUB_AMT") != null && new BigDecimal(map.get("SUB_AMT")).compareTo(BigDecimal.ZERO) != 0) {
			try {
				ChbPrdDeal ibdt = (ChbPrdDeal) ibd.clone();
				ibdt.setTradeType("01");
				ibdt.setTradePrince(new BigDecimal(map.get("SUB_AMT")));
				ibdt.setTradeQty(new BigDecimal(map.get("SUB_QTY")));
				ibdt.setTradeCost(new BigDecimal(map.get("SUB_AMT")));
				deals.add(ibdt);
			} catch (CloneNotSupportedException e) {
				logger.error(e.getMessage(), e);
				message.add(e.getMessage());
			}
		}
		if (map.get("PUR_AMT") != null && new BigDecimal(map.get("PUR_AMT")).compareTo(BigDecimal.ZERO) != 0) {
			try {
				ChbPrdDeal ibdt = (ChbPrdDeal) ibd.clone();
				ibdt.setTradeType("02");
				ibdt.setTradePrince(new BigDecimal(map.get("PUR_AMT")));
				ibdt.setTradeQty(new BigDecimal(map.get("PUR_QTY")));
				ibdt.setTradeCost(new BigDecimal(map.get("PUR_AMT")));
				deals.add(ibdt);
			} catch (CloneNotSupportedException e) {
				logger.error(e.getMessage(), e);
				message.add(e.getMessage());
			}
		}
		if (map.get("RED_AMT") != null && new BigDecimal(map.get("RED_AMT")).compareTo(BigDecimal.ZERO) != 0) {
			try {
				ChbPrdDeal ibdt = (ChbPrdDeal) ibd.clone();
				ibdt.setTradeType("03");
				ibdt.setTradePrince(new BigDecimal(map.get("RED_AMT")));
				ibdt.setTradeQty(new BigDecimal(map.get("RED_QTY")));
				ibdt.setIntAmt(new BigDecimal(map.get("RED_AMT")).subtract(new BigDecimal(map.get("RED_PRIN"))));
				ibdt.setTradeCost(new BigDecimal(map.get("RED_PRIN")));
				deals.add(ibdt);
			} catch (CloneNotSupportedException e) {
				logger.error(e.getMessage(), e);
				message.add(e.getMessage());
			}
		}
		if (map.get("MAT_AMT") != null && new BigDecimal(map.get("MAT_AMT")).compareTo(BigDecimal.ZERO) != 0) {
			try {
				ChbPrdDeal ibdt = (ChbPrdDeal) ibd.clone();
				ibdt.setTradeType("16");
				ibdt.setTradePrince(new BigDecimal(map.get("MAT_AMT")));
				ibdt.setTradeQty(new BigDecimal(map.get("MAT_QTY")));
				ibdt.setIntAmt(new BigDecimal(map.get("MAT_AMT")).subtract(new BigDecimal(map.get("MAT_PRIN"))));
				ibdt.setTradeCost(new BigDecimal(map.get("MAT_PRIN")));
				deals.add(ibdt);
			} catch (CloneNotSupportedException e) {
				logger.error(e.getMessage(), e);
				message.add(e.getMessage());
			}
		}
		if (map.get("PBON_AMT") != null && new BigDecimal(map.get("PBON_AMT")).compareTo(BigDecimal.ZERO) != 0) {
			try {
				ChbPrdDeal ibdt = (ChbPrdDeal) ibd.clone();
				ibdt.setTradeType("05");
				ibdt.setIntAmt(new BigDecimal(map.get("PBON_AMT")));
				ibdt.setTradePrince(new BigDecimal(map.get("PBON_AMT")));
				ibdt.setTradeQty(new BigDecimal(map.get("PBON_QTY")));
				ibdt.setTradeCost(new BigDecimal(map.get("PBON_AMT")));
				deals.add(ibdt);
			} catch (CloneNotSupportedException e) {
				logger.error(e.getMessage(), e);
				message.add(e.getMessage());
			}
		}
		if (map.get("CBON_AMT") != null && new BigDecimal(map.get("CBON_AMT")).compareTo(BigDecimal.ZERO) != 0) {
			try {
				ChbPrdDeal ibdt = (ChbPrdDeal) ibd.clone();
				ibdt.setTradeType("04");
				ibdt.setIntAmt(new BigDecimal(map.get("CBON_AMT")));
				ibdt.setTradePrince(new BigDecimal(map.get("CBON_AMT")));
				deals.add(ibdt);
			} catch (CloneNotSupportedException e) {
				logger.error(e.getMessage(), e);
				message.add(e.getMessage());
			}
		}
		if (map.get("SADD_AMT") != null && new BigDecimal(map.get("SADD_AMT")).compareTo(BigDecimal.ZERO) != 0
				|| map.get("SADD_QTY") != null && new BigDecimal(map.get("SADD_QTY")).compareTo(BigDecimal.ZERO) != 0) {
			try {
				ChbPrdDeal ibdt = (ChbPrdDeal) ibd.clone();
				ibdt.setTradeType("14");
				ibdt.setTradeCost(new BigDecimal(map.get("SADD_AMT")));
				ibdt.setTradeQty(new BigDecimal(map.get("SADD_QTY")));
				deals.add(ibdt);
			} catch (CloneNotSupportedException e) {
				logger.error(e.getMessage(), e);
				message.add(e.getMessage());
			}
		}
		if (map.get("SRED_PRIN") != null && new BigDecimal(map.get("SRED_PRIN")).compareTo(BigDecimal.ZERO) != 0
				|| map.get("SRED_QTY") != null && new BigDecimal(map.get("SRED_QTY")).compareTo(BigDecimal.ZERO) != 0) {
			try {
				ChbPrdDeal ibdt = (ChbPrdDeal) ibd.clone();
				ibdt.setTradeType("15");
				ibdt.setTradeCost(new BigDecimal(map.get("SRED_PRIN")));
				ibdt.setTradeQty(new BigDecimal(map.get("SRED_QTY")));
				deals.add(ibdt);
			} catch (CloneNotSupportedException e) {
				logger.error(e.getMessage(), e);
				message.add(e.getMessage());
			}
		}
		return deals;
	}
}
