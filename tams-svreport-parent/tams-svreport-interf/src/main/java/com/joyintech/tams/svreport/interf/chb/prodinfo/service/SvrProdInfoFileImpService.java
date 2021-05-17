package com.joyintech.tams.svreport.interf.chb.prodinfo.service;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.common.svreport.datacheck.beforeinsert.dto.ChbCheckTableInfo;
import com.joyintech.tams.constant.DictCode;
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

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.FileImpService;
import com.joyintech.tams.common.InterfDefine;
import com.joyintech.tams.common.InterfFile;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.ChbInterfUtil;
import com.joyintech.tams.common.base.dao.ChbPrdresistCodeMapper;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.report.framework.thread.JoyinThreadPoolExecutor;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.report.interf.common.imp.ImportGenerator;
import com.joyintech.tams.report.interf.common.imp.sqlldr.ImpTable;
import com.joyintech.tams.svreport.interf.chb.prodinfo.dao.ChbPrdBaseinfoInterfMapper;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.springframework.util.ReflectionUtils;

/**
 * 产品要素接口文件解析并入库
 * 
 * @author yuluan
 *
 */
@Service
public class SvrProdInfoFileImpService extends FileImpService {
	/**
	 * CHB_PRD_DEAL_NAME
	 */
	// private static final String CHB_PRD_BASEINFO = "CHB_PRD_BASEINFO";
	/**
	 * 业务主键
	 */
	private static final String BUSINESSKEY_CHB_PRD_BASEINFO = "PROD_ID";
	/**
	 * logger
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 数据批量一次性insert条数(单条数据字段较多)
	 */
	@Value("${application.reportInsertLongCount}")
	private int reportInsertLongCount;
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

	@Autowired
	private ChbPrdBaseinfoInterfMapper chbPrdBaseinfoInterfMapper;

    @Autowired
    private ChbPrdresistCodeMapper chbPrdresistCodeMapper;

	private boolean excuteProdBaseInfoByStep(List<Map<String, String>> baseinfoList, String tmpPath, ImpTable table,
			ImpTable subTable,String saveFlag, String dataSource) {

		// 定义新增数据list-验证不通过数据
		List<ChbPrdBaseinfo> listNA = new ArrayList<ChbPrdBaseinfo>();
		// 定义新增数据list-验证通过数据
		List<ChbPrdBaseinfo> listNE = new ArrayList<ChbPrdBaseinfo>();
		// 获取所有的产品登记编码
//		Map<String, String> prdRegistMap = new HashMap<String, String>();
//		prdRegistMap = chbPrdBaseinfoInterfMapper.getAllRegistProdCodeSet().stream()
//				.collect(JoyinCollectors.toMap(x -> x.get("PRODID"), x -> x.get("REG_CODE")));
//		for (Map<String, String> map : baseinfoList) {
//			ChbPrdBaseinfo info = dataConversionEntity(map);
//			if (map.get("ERRMSG") != null && !"".equals(map.get("ERRMSG"))) {
//				info.setStatus(DictCodeListExt1.维护数据状态_待处理.getValue());
//			}
//			info.setDataSource(dataSource);
//			listNE.add(info);
//		}
		listNE = dataConversionEntity(baseinfoList,dataSource);

		if (listNE != null && listNE.size() > 0) {
//			if (tmpPath == null) {
//				if(saveFlag.equals(com.joyintech.tams.report.interf.common.constant.InterfConstants.SAVEFLAG_ALL)){
//					chbPrdBaseinfoInterfMapper.deleteBySysDate();
//				}
				insertDataMerge(listNE);
//			} else {
//				if(saveFlag.equals(com.joyintech.tams.report.interf.common.constant.InterfConstants.SAVEFLAG_ADD)){
//					deleteByProdidBatch(listNE);
//				}else{
//					chbPrdBaseinfoInterfMapper.deleteBySysDate();
//				}
//				createDataFile(tmpPath + "/CHB_PRD_BASEINFO", listNE, table.getColumns());
//			}
			return (CollectionUtils.isNotEmpty(listNA) || CollectionUtils.isNotEmpty(listNE));
		}

		return false;

	}

	public void deleteByProdidBatch(List<ChbPrdBaseinfo> listNE) {
		int batchCount = Integer.parseInt(null == SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT) ? "100"
				: SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT));// 每批commit的个数
		int batchLastIndex = batchCount;// 每批最后一个的下标
		logger.debug("产品要素删除开始--------------" + new Date());
		for (int index = 0; index < listNE.size();) {
			if (batchLastIndex > listNE.size() - 1) {
				batchLastIndex = listNE.size();
				chbPrdBaseinfoInterfMapper.deleteByProdid(listNE.subList(index, batchLastIndex));
				break;// 数据删除完毕,退出循环
			} else {
				chbPrdBaseinfoInterfMapper.deleteByProdid(listNE.subList(index, batchLastIndex));
				index = batchLastIndex;// 设置下一批下标
				batchLastIndex = index + batchCount;
			}
		}
		logger.debug("产品要素删除结束--------------" + new Date());
	}

	void insertInfoBatch(List<ChbPrdBaseinfo> listNE) {
		SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
		try {

			int count = 0;
			logger.debug("产品要素插入开始--------------" + new Date());
			ChbPrdBaseinfoInterfMapper batchMapper = session.getMapper(ChbPrdBaseinfoInterfMapper.class);
			for (ChbPrdBaseinfo prod : listNE) {
				batchMapper.insertBymodel(prod);
				count++;
				if (count % 1000 == 0) {
					session.commit();
					// 清理缓存，防止溢出
					session.clearCache();
					logger.debug("产品要素已插入条数-----------" + count + new Date());
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

	private void insertDataMerge(List<ChbPrdBaseinfo> assetInfoList) {
		SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
		try {
			int count = 0;
			logger.debug("产品要素插入开始--------------" + new Date());
			ChbPrdBaseinfoInterfMapper batchMapper = session.getMapper(ChbPrdBaseinfoInterfMapper.class);
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
	 * map转换成实体类
	 * 
	 * @param dataList
	 * @return
	 */
	List<ChbPrdBaseinfo> dataConversionEntity(List<Map<String, String>> dataList, String dateSource) {
		List<ChbPrdBaseinfo> rs = new ArrayList<ChbPrdBaseinfo>();
		for (Map<String, String> map : dataList) {
			ChbPrdBaseinfo t = new ChbPrdBaseinfo();
			for (String key : map.keySet()) {
				if(key==null||key.equals("ERRMSG")){//待处理的数据
					t.setStatus(DictCodeListExt1.维护数据状态_待处理.getValue());
					continue;
				}
				String value = map.get(key);
				Field field = ReflectionUtils.findField(ChbPrdBaseinfo.class,
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
						if(!StringUtil.isBlank(value)){
							field.set(t,new BigDecimal(value));
						}
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
			t.setProduuid(IDGenerator.generate(36));//主键
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
//		ChbPrdBaseinfo info = new ChbPrdBaseinfo();
//		info.setProduuid(IDGenerator.generate(36));// 主键
//		info.setFprodId(map.get("FPROD_ID"));// 父产品代码
//		info.setProdId(map.get("PROD_ID"));// 产品代码
//		info.setOrgCode(map.get("ORG_CODE"));// 机构代码
//		info.setProdName(map.get("PROD_NAME"));// 产品名称
//		//系统中存在的产品登记编码
//		info.setStatus(map.get("STATUS"));// 数据状态 A 新建/ E 有效/D删除
////		if(prdRegistMap.get(map.get("PROD_ID"))!=null){
////			info.setRegCode(prdRegistMap.get(map.get("PROD_ID")));
////		}else{
////			if(map.get("REG_CODE")!=null&&!"".equals(map.get("REG_CODE"))){
////				info.setRegCode(map.get("REG_CODE"));// 产品登记编码
//////				chbPrdBaseinfoInterfMapper.insertRegistProdCode();
////				ChbPrdresistCode record =new ChbPrdresistCode();
////				record.setProdid(map.get("PROD_ID"));
////				record.setRegCode(map.get("REG_CODE"));
////				chbPrdresistCodeMapper.insert(record);
////			}else{
////				info.setStatus(DictCodeListExt1.维护数据状态_待处理.getValue());
////			}
////		}
//		if (StringUtil.isEmpty(map.get("REG_CODE"))) {
//			// 根据产品代码去chb_prdresist_code表查询REG_CODE并赋值给产品基本信息
//			ChbPrdresistCodeKey key = new ChbPrdresistCodeKey();
//			key.setProdid(info.getProdId());
//			key.setFlag("2");
//			ChbPrdresistCode chbPrdresistCode = chbPrdresistCodeMapper.selectByPrimaryKey(key);
//			info.setRegCode(chbPrdresistCode == null ? "" : chbPrdresistCode.getRegCode());// 产品登记编码
//		} else {
//			info.setRegCode(map.get("REG_CODE"));// 产品登记编码
//		}
//		info.setLacks(map.get("LACKS"));// 信息缺失项 无缺失、缺失中债申报、缺失中债发行
//		info.setProdInsId(map.get("PROD_INS_ID"));// 行内标识码
//		info.setProfitType(map.get("PROFIT_TYPE"));// 产品收益类型 01 保证收益/02
//													// 保本浮动收益/03 非保本浮动收益
//		info.setInvestorType(map.get("INVESTOR_TYPE"));// 投资者类型
//		info.setIsIntact(map.get("IS_INTACT"));// 发行要素维护完整
//		info.setBasic(map.get("BASIC"));// 计息基础 A/360, A/365
//		info.setProdtype(map.get("PRODTYPE"));// 标识哪种产品，净值N，货币M，利率R，客户周期C
//		info.setIsagency(map.get("ISAGENCY"));// 是否代销，01/02
//		info.setSaletarget(map.get("SALETARGET"));// 目标客户类型,01 私人银行客户专属/02
//													// 机构客户专属/03 一般个人客户/04
//													// 金融同业专属/05 高资产净值客户
////		info.setDurationflag(map.get("DURATIONFLAG"));// 存续状态 存续01，终止02，未起息03
//		info.setDurationflag("03");
//		info.setMdate(DateUtil.parse(map.get("MDATE")));// 到期日
//		info.setVdate(DateUtil.parse(map.get("VDATE")));// 起息日
//		info.setTerminatedate(DateUtil.parse(map.get("TERMINATEDATE")));// 提前终止日
//		info.setIvstorientation(map.get("IVSTORIENTATION"));// 投资性质
//															// 01固定收益类/02权益类/03混合类/04商品及金融衍生品类
//		info.setOpenBussRemark(map.get("OPEN_BUSS_REMARK"));// 开放期业务说明
//		info.setRaisePstrdate(DateUtil.parse(map.get("RAISE_PSTRDATE")));// 募集起始日期（从）
//		info.setRaisePenddate(DateUtil.parse(map.get("RAISE_PENDDATE")));// 募集起始日期（到）
//		info.setDeclareStatus(map.get("DECLARE_STATUS"));// 报备状态
//		info.setIssuingStatus(map.get("ISSUING_STATUS"));// 发行状态
//															// 确认发行/发行成功/发行失败/不发行
//		info.setRiskLevel(map.get("RISK_LEVEL"));// 产品风险等级 01 一级（低）/02 二级（中低）/03
//													// 三级（中）/04 四级（中高）/05 五级（高）
//		info.setTermtype(map.get("TERMTYPE"));// 产品期限01 T+0产品/02 7天（含）以内/03
//												// 7天-1个月（含）/04 1-3个月（含）/05 3-6
//												// 25个月(含)/06 6-12个月（含）/07
//												// 1-3年（含）/08 3年以上
//		info.setRaiseCcy(map.get("RAISE_CCY"));// 募集币种
//		info.setRaiseVdate(DateUtil.parse(map.get("RAISE_VDATE")));// 募集起始日期
//		info.setRaiseEnddate(DateUtil.parse(map.get("RAISE_ENDDATE")));// 募集结束日期
//		info.setTemplateName(map.get("TEMPLATE_NAME"));// 模板名称
//		info.setProdBrand(map.get("PROD_BRAND"));// 产品品牌
//		if (StringUtil.isNotEmpty(map.get("PROD_STAGE"))) {
//			info.setProdStage(new BigDecimal(map.get("PROD_STAGE")));// 产品期次
//		}
//		info.setDesignerName(map.get("DESIGNER_NAME"));// 产品设计人姓名
//		info.setDesignerId(map.get("DESIGNER_ID"));// 产品设计人身份证号
//		info.setApproverName(map.get("APPROVER_NAME"));// 产品审批人姓名
//		info.setApproverId(map.get("APPROVER_ID"));// 产品审批人身份证号
//		info.setManagerName(map.get("MANAGER_NAME"));// 投资经理姓名
//		info.setManagerId(map.get("MANAGER_ID"));// 投资经理身份证号
//		info.setBookMode(map.get("BOOK_MODE"));// 会计核算方式 01 表内/02 表外
//		info.setLiaisonName(map.get("LIAISON_NAME"));// 业务联络人姓名
//		info.setLiaisonTel(map.get("LIAISON_TEL"));// 业务联络人座机
//		info.setLiaisonMobile(map.get("LIAISON_MOBILE"));// 业务联络人手机
//		info.setLiaisonEmail(map.get("LIAISON_EMAIL"));// 业务联络人邮箱
//		info.setInvestArae(map.get("INVEST_ARAE"));// 资金投向地区 01 境内/02 境外/03
//													// 境内和境外
//		info.setInvestCountry(map.get("INVEST_COUNTRY"));// 产品投资国家或地区参考《世界各国和地区名称代码》
//		info.setFinanSerModel(map.get("FINAN_SER_MODEL"));// 理财业务服务模式01
//															// 综合理财服务/02
//															// 理财顾问服务/99 其他
//		info.setOperateMode(map.get("OPERATE_MODE"));// 产品运作模式 01 封闭式净值型/02
//														// 封闭式非净值型/03 开放式净值型/04
//														// 开放式非净值型
//		info.setCmmode(map.get("CMMODE"));// 产品资产配置方式01 单一资产配置（一对一）/02
//											// 资产组合配置（一对多）/03 其他配置（多对多）
//		info.setPmmode(map.get("PMMODE"));// 产品管理模式01银行为实际管理人/02 其他机构为实际管理人/03
//											// 资产管理公司/04 特殊目的载体（SPV）
//		info.setMname(map.get("MNAME"));// 实际管理人名称
//		info.setInvAssetType(map.get("INV_ASSET_TYPE"));// 投资资产类型 01 结构性投资类/02
//														// 货币市场工具类/03 债券类/04
//														// 非标准化债权类/05 股票类/06
//														// 股权类/07 另类投资类/08
//														// 混合类/09代客境外投资类/10
//														// 银行存款类/11 理财直接融资工具类/12
//														// 新增可投资资产类/13 公募基金类/ 14
//														// 私募基金类/ 15 产业基金类 /16
//														// 衍生产品类 /17 商品类
//		info.setContMode(map.get("CONT_MODE"));// 合作模式 01 独立运作/02 银信/03 银保/04
//												// 银基/05 银证/07 混合类/99 其他
//		info.setCooperationName(map.get("COOPERATION_NAME"));// 合作机构
//		info.setConPrdTraget(map.get("CON_PRD_TRAGET"));
//		info.setIsYield(map.get("IS_YIELD"));
//		info.setIsYieldMeasBasis(map.get("IS_YIELD_MEAS_BASIS"));
//		info.setInvTypeRat(map.get("INV_TYPE_RAT"));
//		info.setRiskType(map.get("RISK_TYPE"));
////		info.setHighYield(new BigDecimal(map.get("HIGH_YIELD")));
////		info.setLowYield(new BigDecimal(map.get("LOW_YIELD")));
//		info.setSaleArea(map.get("SALE_AREA"));
//		if (StringUtil.isNotEmpty(map.get("SALE_AMT_STR"))) {
//			info.setSaleAmtStr(new BigDecimal(map.get("SALE_AMT_STR")));
//		}
//		info.setCashPrinCcy(map.get("CASH_PRIN_CCY"));
//		info.setCashProfCcy(map.get("CASH_PROF_CCY"));
//		if (StringUtil.isNotEmpty(map.get("RAISE_AMT_PLAN"))) {
//			info.setRaiseAmtPlan(new BigDecimal(map.get("RAISE_AMT_PLAN")));
//		}
//		if (map.get("SALE_FEE_RATE") != null && !"".equals(map.get("SALE_FEE_RATE"))) {
//			info.setSaleFeeRate(new BigDecimal(map.get("SALE_FEE_RATE")));
//		}
//		info.setInvestPrinDate(map.get("INVEST_PRIN_DATE"));
//		info.setInvestProfDate(map.get("INVEST_PROF_DATE"));
//		if (map.get("TRU_FEE_RATE") != null && !"".equals(map.get("TRU_FEE_RATE"))) {
//			info.setTruFeeRate(new BigDecimal(map.get("TRU_FEE_RATE")));
//		}
//		info.setInDepCode(map.get("IN_DEP_CODE"));
//		info.setInDepName(map.get("IN_DEP_NAME"));
//		info.setOutDepCtry(map.get("OUT_DEP_CTRY"));
//		info.setOutDepName(map.get("OUT_DEP_NAME"));
//		info.setTermRightFlag(map.get("TERM_RIGHT_FLAG"));
//		info.setCusRedeemFlag(map.get("CUS_REDEEM_FLAG"));
//		info.setCreditFlag(map.get("CREDIT_FLAG"));
//		info.setCreditType(map.get("CREDIT_TYPE"));
//		info.setPricedMode(map.get("PRICED_MODE"));
//		info.setPrdVdate(DateUtil.parse(map.get("VDATE")));
//		info.setPrdMdate(DateUtil.parse(map.get("MDATE")));
//		if (map.get("EXP_AVG_YIELD") != null && !"".equals(map.get("EXP_AVG_YIELD"))) {
//			info.setExpAvgYield(new BigDecimal(map.get("EXP_AVG_YIELD")));
//		}
//		info.setIsConstracte(map.get("IS_CONSTRACTE"));
//		info.setGradeType(map.get("GRADE_TYPE"));
//		info.setYieldComment(map.get("YIELD_COMMENT"));
//		info.setOpenMode(map.get("OPEN_MODE"));
//		info.setOpenCircle(map.get("OPEN_CIRCLE"));
//		if (map.get("OPEN_CIRCLE_DAYS") != null && !"".equals(map.get("OPEN_CIRCLE_DAYS"))) {
//			info.setOpenCircleDays(new BigDecimal(map.get("OPEN_CIRCLE_DAYS")));
//		}
//		info.setRuleComment(map.get("RULE_COMMENT"));
//		info.setFirstOpenDay(DateUtil.parse(map.get("FIRST_OPEN_DAY")));
//		info.setHolidayOnFlag(map.get("HOLIDAY_ON_FLAG"));
//		if (map.get("AVG_OPEN_COUNT") != null && !"".equals(map.get("AVG_OPEN_COUNT"))) {
//			info.setAvgOpenCount(new BigDecimal(map.get("AVG_OPEN_COUNT")));
//		}
//		info.setOpenBuss(map.get("OPEN_BUSS"));
//		if (map.get("HIGH_YIELD_ISSUE") != null && !"".equals(map.get("HIGH_YIELD_ISSUE"))) {
//			info.setHighYieldIssue(new BigDecimal(map.get("HIGH_YIELD_ISSUE")));
//		}
//		if (map.get("LOW_YIELD_ISSUE") != null && !"".equals(map.get("LOW_YIELD_ISSUE"))) {
//			info.setLowYieldIssue(new BigDecimal(map.get("LOW_YIELD_ISSUE")));
//		}
//		info.setCreditMode(map.get("CREDIT_MODE"));
//		info.setIssuePartyId(map.get("ISSUE_PARTY_ID"));
//		info.setOpentypep(map.get("OPENTYPEP"));
//		info.setOpendayp(map.get("OPENDAYP"));
//		info.setOpencyclep(map.get("OPENCYCLEP"));
//		info.setOpencycleunitp(map.get("OPENCYCLEUNITP"));
//		info.setWeekp(map.get("WEEKP"));
//		info.setDayp(map.get("DAYP"));
//		info.setWorkdayp(map.get("WORKDAYP"));
//		info.setRemark(map.get("REMARK"));
//		info.setDeptCode(map.get("DEPT_CODE"));
//		info.setWorkflowId(map.get("WORKFLOW_ID"));
//		info.setDataSource(map.get("DATA_SOURCE"));
//		info.setRaiseType(map.get("RAISE_TYPE"));
//		info.setManageType(map.get("MANAGE_TYPE"));
//		info.setUpdateUser(CommonUtil.getUserName());
//		// info.setUpdateTime(map.get("TERMTYPE"));
//		info.setCreateUser(CommonUtil.getUserName());
//		// info.setCreateTime(map.get("TERMTYPE"));
//		info.setAgent(map.get("AGENT"));
//		info.setFiduciaryDuty(map.get("FIDUCIARY_DUTY"));
//		info.setTransferType(map.get("TRANSFER_TYPE"));
//		info.setIsMarketFund(map.get("IS_MARKET_FUND"));
//		info.setExcFinSector(map.get("EXC_FIN_SECTOR"));
//		info.setIsShortTerm(map.get("IS_SHORT_TERM"));
//		if (map.get("SHORT_TERM") != null && !"".equals(map.get("SHORT_TERM"))) {
//			info.setShortTerm(new BigDecimal(map.get("SHORT_TERM")));
//		}
//		info.setIsFreeRedem(map.get("IS_FREE_REDEM"));
//		info.setCashManaCate(map.get("CASH_MANA_CATE"));
//		if (map.get("YIELD") != null && !"".equals(map.get("YIELD"))) {
//			info.setYield(new BigDecimal(map.get("YIELD")));
//		}
//		if (map.get("INVEST_FEE_RATE") != null && !"".equals(map.get("INVEST_FEE_RATE"))) {
//			info.setInvestFeeRate(new BigDecimal(map.get("INVEST_FEE_RATE")));
//		}
//		// 新增字段客户类型
//		info.setCustomerType(map.get("CUSTOMER_TYPE"));
//
//		//新增字段交易日历
//		info.setCalendarId(map.get("CALENDAR_ID"));
//		// 业绩比较基准下限
//		if (StringUtil.isNotEmpty(map.get("YIELD_LOW"))) {
//			info.setYieldLow(new BigDecimal(map.get("YIELD_LOW")));
//		}
//		// 业绩比较基准上限
//		if (StringUtil.isNotEmpty(map.get("YIELD_HIGH"))) {
//			info.setYieldHigh(new BigDecimal(map.get("YIELD_HIGH")));
//		}
//		// 业绩比较基准说明
//		info.setYieldRemark(map.get("YIELD_REMARK"));
//		// 资金托管账号
//		info.setBusiAcctNo(map.get("BUSI_ACCT_NO"));
//		// 资金托管账户
//		info.setAcctName(map.get("ACCT_NAME"));
//
//		/**start  by dongxiangyang  新增产品基本信息的三个字段  20210126*/
//        info.setIsTransfer(map.get("IS_TRANSFER"));
//        info.setIsFhxgcp(map.get("IS_FHXGCP"));
//        info.setIsYllccp(map.get("IS_YLLCCP"));
//		/**end  by dongxiangyang  新增产品基本信息的三个字段  20210126*/

	}

	@Override
	public String saveData(List<Map<String, String>> listData, String saveFlag, String dataSource) {
//		String tmpPath = SvrDataUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY,
//				GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
		boolean success = true;

//		if (listData.size() <= 1000) {
			success = excuteProdBaseInfoByStep(listData, null, null, null,saveFlag, dataSource);
//		} else {
//			// 创建表
//			ImpTable table = new ImpTable(InterfDefine.产品基本信息接口数据.getCode());
//			table.setColumns(ImportGenerator.createImpTableColumns(InterfDefine.产品基本信息接口数据.getCode()));
//			// 创建一个线程池
//			if (interfMaxCorePoolSize < 1) {
//				interfMaxCorePoolSize = 20;
//			}
//			ExecutorService service = new JoyinThreadPoolExecutor(interfMaxCorePoolSize);
//			List<Callable<Boolean>> tasks = new ArrayList<>();
//			if (null != listData) {
//				int start = 0;
//				int end = 0;
//				int size = listData.size();
//				int max = (int) Math.ceil((double) size / 1000);
//				for (int i = 0; i < max; i++) {
//					start = end;
//					end = Math.min(size, 1000 * (i + 1));
//					List<Map<String, String>> subList = listData.subList(start, end);
//					tasks.add(new Callable<Boolean>() {
//
//						@Override
//						public Boolean call() {
//							return excuteProdBaseInfoByStep(subList, tmpPath, table, null,saveFlag, dataSource);
//						}
//					});
//				}
//			}
//
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
//
//			Collection<File> files = FileUtils.listFiles(new File(tmpPath), new String[] { "tmp" }, false);
//			List<String> content = new ArrayList<>(listData.size());
//			String result = "";
//			for (File f : files) {
//				if (f.getName().startsWith(InterfDefine.产品基本信息接口数据.getCode())) {
//					try {
//						content.addAll(FileUtils.readLines(f, Charset.forName("UTF-8")));
//						logger.debug("查找sqlloder文件"+f);
//					} catch (IOException e) {
//						logger.debug("查找sqlloder文件异常");
//						logger.error(e.getMessage(), e);
//						result = "fail";
//					} finally {
//						f.delete();
//					}
//				}
//			}
//			if("fail".equals(result)){
//				return result;
//			}
//			// 系统是否使用的db2数据库
//			boolean isDB2DataBase = CommonUtil.isDB2DataBase();
//			if (isDB2DataBase) {
//				// db2数据库处理
//				if (CollectionUtils.isNotEmpty(content)) {
//					try {
//						success = success && ImportGenerator.importToDBByPart(table, tmpPath, content,
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
//						success = success && ImportGenerator.importToDBByPart(table, tmpPath, content,
//								reportInterfOneTimeSqlLdrDataCount, reportInterfOneTimeSqlLdrCommitCount, "txt",
//								reportSqlldrDatabaseName);
//					} catch (IOException | InterruptedException e) {
//						logger.error(e.getMessage(), e);
//						return "fail";
//					}
//				}
//
//			}
//		}
		if (success) {
			return "success";
		}
		return "fail";
	}

	// @Override
	public String importDB(List<Map<String, String>> listData, InterfFile interfFile) {
//		List<Map<String, String>> listA = 
		String result="success";
//		checkDataCodeCheck(listData, interfFile.getFilename(), ChbCheckTableInfo.个人发行登记表INFO);
//		checkData(listData, interfFile.getFilename(),
//		InterfDefine.产品基本信息接口数据.getCode(), InterfDefine.产品基本信息接口数据.getCode(),
//		BUSINESSKEY_CHB_PRD_BASEINFO, BUSINESSKEY_CHB_PRD_BASEINFO);
//		listData.addAll(listA);
		ChbInterfConfigDto interfConfig = ChbInterfUtil.queryInterfConfigByInterfid(InterfDefine.产品基本信息接口数据.getCode());
        if (interfConfig == null){
            logger.info("CHB_INTERF_CONFIG表中无配置项！");
            return "fail";
        }
        String saveFlag = interfConfig.getContentType();
        if(!listData.isEmpty()){
        	result=saveData(listData, saveFlag, interfFile.getDataSource());
        }
        // 更新系统配置时间
        ChbInterfUtil.updateInterfTimeBySysdate( InterfDefine.产品基本信息接口数据.getCode());
		return result;
	}

}
