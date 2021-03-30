package com.joyintech.tams.svreport.chbhz.inAssetInfoSub.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.joyintech.tams.common.svreport.datacheck.beforeinsert.dto.ChbCheckTableInfo;
import com.joyintech.tams.common.svreport.datacheck.beforeinsert.service.CommonCodeCheckBatchService;
import com.joyintech.tams.common.svreport.datacheck.beforeinsert.util.SvrMaptoEntityUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.chbreport.codecheck.service.CommonCodeCheckTmpBatchService;
import com.joyintech.tams.common.FileImpService;
import com.joyintech.tams.common.InterfFile;
import com.joyintech.tams.common.base.dao.BaseMapper;
import com.joyintech.tams.common.base.entity.ChbAssetInfoTmp;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.util.ChbDeadlineUtil;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.svreport.chb.assetinforeg.dao.AssetInfoRegist001Mapper;
import com.joyintech.tams.svreport.chb.assetinforeg.dto.ChbAssetInfoSub001Dto;
import com.joyintech.tams.svreport.chbhz.inAssetInfoSub.dao.InAssetInfoSvrMapper;
import com.joyintech.tams.systemtools.constant.DataIndexSequenceType;
import com.joyintech.tams.systemtools.util.CommonUtil;

/**
 * 功能:资产负债登记要素生成批处理主service
 * 
 * @since 1.7.0
 * @version 1.7.0
 * @作者：dongxiangyang @创建时间： @修改记录：
 */
@Service
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class InAssetInfoSubBatchService extends FileImpService {

	/**
	 * 资产负债登记
	 */
	private String tableName = "CHB_ASSET_INFO_TMP";

	/**
	 * 重复性检查字段
	 */
	private static final String[] DUPLICATIED_LIST = { "ASSET_CODE" };

	/**
	 * 业务主键
	 */
	private static final String BUSSINESSKEY = "ASSET_CODE";
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());

	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Autowired
	private AssetInfoRegist001Mapper assetInfoRegist001Mapper;

	@Autowired
	private SvrMaptoEntityUtil svrMaptoEntityUtil;

	@Autowired
	private InAssetInfoSvrMapper InAssetInfoSvrMapper;

	@Autowired
	private SqlSessionTemplate template;
	/**
	 * 中债基础数据mapper
	 */
	@Autowired
	private BaseMapper baseMapper;

	@Autowired
	private CommonCodeCheckBatchService commonCodeCheckBatchService;

	/**
	 * 数据批量一次查询或者删除条数
	 */
	@Value("${application.reportSelectOrDeleteCount}")
	private int reportSelectOrDeleteCount;

	/**
	 * @param
	 * @see
	 * @since 1.0
	 */
	public void generateDeclareInfoData(Date batchDate, List<String> prodIdList) {
		logger.debug("资产负债登记要素生成批处理处理开始------" + df.format(new Date()));
		// 1、取得业务数据 新建list<产品存续期登记接口表DTO>
		List<ChbAssetInfoTmp> listTmp = InAssetInfoSvrMapper.genChbAssetInfoTmpList(DateUtil.format(batchDate));

		// 2 、校验、生成错误数据、正确数据的数据结构
		// 查询数据库表字段
		List<String> listCloumn = baseMapper.getTableColumnList(tableName);
		// 2.1调用共通 将list<接口表DTO>转换成List<Map<Key, Object>>格式
		List<Map<String, String>> listMap = svrMaptoEntityUtil.convertEntityToMap(listTmp, listCloumn);
		// 2.2调用FileImpService.checkData方法
		checkData(true, listMap, tableName, tableName, tableName, BUSSINESSKEY, DUPLICATIED_LIST);

		/** start 配置无法实现，代码实现的check add by zhangliang 20201020 */
		// commonCodeCheckTmpBatchService.codeCheckTmpCommon(listMap, chbAsseTableName, BUSSINESSKEY);
		commonCodeCheckBatchService.codeCheckCommon(listMap, ChbCheckTableInfo.genChbCheckTableInfo(tableName), null);
		/** end 配置无法实现，代码实现的check add by zhangliang 20201020 */

		// 3、正确数据处理
		// 3.1 对[2]清理掉错误数据的List，调用以下共通，进行数据类型转化处理。将检查后的listMap 转为List<业务表登记表Dto>
		List<ChbAssetInfoSub001Dto> chbAssetInfoSubList = svrMaptoEntityUtil.convertMapToEntity(listMap, listCloumn,
				ChbAssetInfoSub001Dto.class);

		// 拼接查询主keylist
		List<String> keylist = chbAssetInfoSubList.stream().map(ChbAssetInfoSub001Dto::getAssetCode)
				.collect(Collectors.toList());

		// 3.2.1查询已经在登记表中存在的数据
		// 定义已经在登记表存在的数据
		List<ChbAssetInfoSub001Dto> ChbAssetInfoSubListin = new ArrayList<>();
		ChbAssetInfoSubListin = InAssetInfoSvrMapper.genChbAssetInfoSubList(keylist);

		// 3.2.2 将已存在报送中的数据放入map中
		Map<String, ChbAssetInfoSub001Dto> mapSub = ChbAssetInfoSubListin.stream().collect(
				Collectors.toMap(ChbAssetInfoSub001Dto::getAssetCode, chbAssetInfoSub001Dto -> chbAssetInfoSub001Dto, (v1, v2) -> v1));

		// 删除数据list
		List<ChbAssetInfoSub001Dto> deleteList = new ArrayList<ChbAssetInfoSub001Dto>();
		// 新建数据list
		List<ChbAssetInfoSub001Dto> insertList = new ArrayList<ChbAssetInfoSub001Dto>();

		// ]清理掉错误数据的List进行循环处理
		for (ChbAssetInfoSub001Dto sub : chbAssetInfoSubList) {
			// 登记序列
			sub.setRegistSeqno(IDGenerator.generate(36));
			// 数据日期
			sub.setStatusDate(batchDate);
			// 创建人
			sub.setCreateUser(CommonUtil.getUserName());
			// 更信任
			sub.setUpdateUser(CommonUtil.getUserName());
			// 创建部门
			sub.setCreateDept(CommonUtil.getDeptCode());
			// 创建机构
			sub.setOrgCode(CommonUtil.getOrgCode());
			// 发行机构代码取值
			sub.setIssuePartyId(CommonUtil.selectBankCode());
			// 报送流水号
			sub.setSumitSeq(CommonUtil.getSequenceIndexData(DataIndexSequenceType.中债登记管理));
			// 业务表不存在数据：
			if (!mapSub.containsKey(sub.getAssetCode())) {
				deleteList.add(sub);
				// 如果当前数据状不等于删除
				if (!StringUtil.equals(DictCodeListExt2.数据状态_删除.getValue(), sub.getStatus())) {
					// 报送状态
					sub.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
					// 数据状态
					sub.setStatus(DictCodeListExt2.报送数据状态_新建.getValue());
					// 新建数据List.add(此条记录）
					insertList.add(sub);
				}
				// 数据状态等于删除，则不需要插入
			} else if (mapSub.containsKey(sub.getAssetCode())) {
				// 临时表存在、业务表也存在 的情况下
				ChbAssetInfoSub001Dto subYw = mapSub.get(sub.getAssetCode());
				// （业务表中此条记录的.【报送状态】 == “02：接口报送”|| 业务表中此条记录的.【报送状态】 ==
				// “03：手工处理” ）
				if ((StringUtil.equals(DictCodeListExt2.报送状态_接口报送.getValue(), subYw.getSendStatus())
						|| StringUtil.equals(DictCodeListExt2.报送状态_手工处理.getValue(), subYw.getSendStatus()))) {
					// 当已经报送的数据状态为删除时
					if (StringUtil.equals(subYw.getStatus(), DictCodeListExt2.报送数据状态_删除.getValue())) {
						// tmp表数据为删除时
						if (StringUtil.equals(sub.getStatus(), DictCodeListExt2.数据状态_删除.getValue())) {
							// 删除数据List.add(此条记录）
							deleteList.add(sub);
						} else {
							// 删除数据List.add(此条记录）
							deleteList.add(sub);
							sub.setStatus(DictCodeListExt2.报送数据状态_新建.getValue());
							// 报送状态
							sub.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
							insertList.add(sub);
						}
					} else {
						// 反馈状态为发送失败和中债退回时候
						if (StringUtil.equals(subYw.getSubmitFeedbackStatus(), DictCodeListExt2.报送反馈状态_中债退回.getValue())
								|| StringUtil.equals(subYw.getSubmitFeedbackStatus(),
										DictCodeListExt2.报送反馈状态_发送失败.getValue())) {
							// tmp表数据为删除时
							if (StringUtil.equals(sub.getStatus(), DictCodeListExt2.数据状态_删除.getValue())) {
								// 删除未报送
								deleteList.add(sub);
							}
							// tmp表数据不为删除时
							else {
								// 删除数据List.add(此条记录）删除的是未报送数据
								deleteList.add(sub);
								// 内容不一致进行处理
								if (!compareAssetInfoReg(subYw, sub)) {
									// 此条记录的【数据状态】= 原来的【数据状态】
									sub.setStatus(subYw.getStatus());
									// 报送状态
									sub.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
									insertList.add(sub);
								}
							}
						} // 反馈状态为发送成功或中债通过或审核文件下载失败
						else if (StringUtil.equals(subYw.getSubmitFeedbackStatus(),
								DictCodeListExt2.报送反馈状态_发送成功.getValue())
								|| StringUtil.equals(subYw.getSubmitFeedbackStatus(),
										DictCodeListExt2.报送反馈状态_中债通过.getValue())
								|| StringUtil.equals(subYw.getSubmitFeedbackStatus(),
										DictCodeListExt2.报送反馈状态_审核文件下载失败.getValue())) {
							// tmp表数据为删除时
							if (StringUtil.equals(sub.getStatus(), DictCodeListExt2.数据状态_删除.getValue())) {
								// 删除未报送
								deleteList.add(sub);
								// 新建 数据状态为删除数据状态，未报送数据
								sub.setStatus(DictCodeListExt2.报送数据状态_删除.getValue());
								// 报送状态
								sub.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
								insertList.add(sub);

							}
							// tmp表数据不为删除时
							else {
								// 删除数据List.add(此条记录）删除的是未报送数据
								deleteList.add(sub);
								// 两条记录的所有申报字段比较，存在不一致的情况下，进行以下处理
								if (!compareAssetInfoReg(subYw, sub)) {
									// 此条记录的【数据状态】= '02:修改'
									sub.setStatus(DictCodeListExt2.报送数据状态_修改.getValue());
									// 报送状态
									sub.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
									insertList.add(sub);

								}
							}
						}
					}
				} else {
					// 此条记录的【数据状态】= '02:新建'
					sub.setStatus(DictCodeListExt2.报送数据状态_新建.getValue());
					// 报送状态
					sub.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
					// 删除数据List.add(此条记录）
					// 新建数据List.add(此条记录）
					deleteList.add(sub);
					insertList.add(sub);
				}
			}

			// 报送时点通用方法
			// 当前批次报送时点集合
			Map<String, List<Date>> dateMap = new HashMap<String, List<Date>>();
			List<Date> dateList = ChbDeadlineUtil.getChbEndDate(DictCodeListExt2.报表名称_资产要素信息登记.getValue(),
					DictCodeListExt2.报送数据状态_新建.getValue(), dateMap, batchDate);
			// 报送时点设置
			if (CollectionUtils.isNotEmpty(dateList)) {
				sub.setChbSubmitDeadline(dateList.get(0));
				sub.setSubmitDeadline(dateList.get(1));
			}
		}
		logger.debug("资产负债登记要素生成批处理处理结束------" + df.format(new Date()));
		// 进行入库处理
		logger.debug("资产负债登记要素入库开始------" + df.format(new Date()));
		insertDB(deleteList, insertList);
		logger.debug("资产负债登记要素入库：" + insertList.size() + "条");
		logger.debug("资产负债登记要素入库结束------" + df.format(new Date()));

	}

	@Override
	public String saveData(List<Map<String, String>> listData, String saveFlag, String dateSource) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String importDB(List<Map<String, String>> listData, InterfFile interfFile) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 功能:带提交的方式单条处理数据结果，入库
	 * 
	 * @since
	 * @version
	 * @author：dongxiangyang 2020年09月22日
	 * @param assetList
	 *            void
	 */
	private void insertDB(List<ChbAssetInfoSub001Dto> deleteDataList, List<ChbAssetInfoSub001Dto> insertDataList) {
		if (CollectionUtils.isNotEmpty(deleteDataList)) {
			// 插入前先删除未报送的登记数据
			logger.debug("资产要素信息登记删除未报送数据：删除开始----------");
			// 数据批量一次删除条数,如果参数设置过大则强制定位50
			int batchDeleteCount = reportSelectOrDeleteCount;
			// 每批最后一个的下标
			int batchDeleteLastIndex = batchDeleteCount;
			for (int index = 0; index < deleteDataList.size();) {
				if (batchDeleteLastIndex > deleteDataList.size() - 1) {
					batchDeleteLastIndex = deleteDataList.size();
					assetInfoRegist001Mapper
							.deleteByAssetCodeBatch(deleteDataList.subList(index, batchDeleteLastIndex));
					break;// 数据插入完毕,退出循环
				} else {
					assetInfoRegist001Mapper
							.deleteByAssetCodeBatch(deleteDataList.subList(index, batchDeleteLastIndex));
					index = batchDeleteLastIndex;// 设置下一批下标
					batchDeleteLastIndex = index + batchDeleteCount;
				}
			}
			logger.debug("资产要素信息登记删除未报送数据：删除结束----------");
			logger.debug("资产要素信息登记插入数据：插入开始----------");
			int count = 0;
			SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
			try {
				AssetInfoRegist001Mapper batchMapper = session.getMapper(AssetInfoRegist001Mapper.class);
				for (ChbAssetInfoSub001Dto asset : insertDataList) {
					batchMapper.insertBatch(asset);
					count++;
					if (count % 1000 == 0) {
						session.commit();
						// 清理缓存，防止溢出
						session.clearCache();
					}
				}
				session.commit();
				// 清理缓存，防止溢出
				session.clearCache();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				session.rollback();
				throw e;
			} finally {
				session.close();
			}
			logger.debug("资产要素信息登记插入数据：插入结束----------");
		}
	}

	/**
	 * 功能:用于比较是否生成修改数据
	 * 
	 * @since
	 * @version
	 * @author：dongxiangyang 2020年09月22日
	 * @param assetInfo1
	 * @param assetInfo2
	 * @return boolean
	 */
	private boolean compareAssetInfoReg(ChbAssetInfoSub001Dto assetInfo1, ChbAssetInfoSub001Dto assetInfo2) {
		ChbAssetInfoSub001Dto assetInfoCompare = getAssetInfoRegForCompare(assetInfo1);
		ChbAssetInfoSub001Dto assetInfoToCompare = getAssetInfoRegForCompare(assetInfo2);
		if (assetInfoCompare == null || assetInfoToCompare == null) {
			return false;
		} else {
			return JSON.toJSONString(assetInfoCompare).equals(JSON.toJSONString(assetInfoToCompare));
		}
	}

	/**
	 * 功能:获取用于比较的dto，去除某些属性
	 * 
	 * @since
	 * @version
	 * @author：dongxiangyang 2020年9月22日
	 * @param assetInfo
	 * @return ChbAssetInfoSub001Dto
	 */
	private ChbAssetInfoSub001Dto getAssetInfoRegForCompare(ChbAssetInfoSub001Dto assetInfo) {
		ChbAssetInfoSub001Dto result = null;
		try {
			result = (ChbAssetInfoSub001Dto) BeanUtil.cloneBean(assetInfo);
		} catch (IllegalAccessException e) {
			logger.error("获取不到指定类型，{}", e.getMessage(), e);
			return result;
		} catch (InstantiationException e) {
			logger.error("获取不到指定类型，{}", e.getMessage(), e);
			return result;
		} catch (InvocationTargetException e) {
			logger.error("获取不到指定类型，{}", e.getMessage(), e);
			return result;
		} catch (NoSuchMethodException e) {
			logger.error("获取不到指定方法，{}", e.getMessage(), e);
			return result;
		}
		result.setRegistSeqno(null);
		result.setSubmitDeadline(null);
		result.setChbSubmitDeadline(null);
		result.setSubmitTime(null);
		result.setStatusDate(null);
		result.setStatus(null);
		result.setCreateUser(null);
		result.setCreateTime(null);
		result.setUpdateUser(null);
		result.setUpdateTime(null);
		result.setOrgCode(null);
		result.setDeptCode(null);
		result.setSendStatus(null);
		// add by dubaojun 2020-10-31 字段对比时删除该字段 start
		result.setSumitSeq(null);
		// add by dubaojun 2020-10-31 字段对比时删除该字段 end
		result.setSubmitFeedbackStatus(null);
		result.setCreateDept(null);
		result.setDataSource(null);
		result.setDeleteRes(null);
		// 设置小数位
		setScale(result);
		return result;
	}

	/**
	 * 功能:设置小数位
	 * 
	 * @since
	 * @version
	 * @author：dongxiangyang 2020年9月22日
	 * @param dto
	 * @return ChbAssetInfoSub001Dto
	 */
	private void setScale(ChbAssetInfoSub001Dto dto) {
		// 设置小数位
		if (dto.getAssetYield() != null) {
			dto.setAssetYield(dto.getAssetYield().setScale(5, BigDecimal.ROUND_HALF_UP));
		}
		if (dto.getFloatFactor() != null) {
			dto.setFloatFactor(dto.getFloatFactor().setScale(5, BigDecimal.ROUND_HALF_UP));
		}
		if (dto.getTotalFeeRate() != null) {
			dto.setTotalFeeRate(dto.getTotalFeeRate().setScale(5, BigDecimal.ROUND_HALF_UP));
		}
		if (dto.getIncomeRatio() != null) {
			dto.setIncomeRatio(dto.getIncomeRatio().setScale(5, BigDecimal.ROUND_HALF_UP));
		}
		if (dto.getDerInstrRatio() != null) {
			dto.setDerInstrRatio(dto.getDerInstrRatio().setScale(5, BigDecimal.ROUND_HALF_UP));
		}
		if (dto.getMaxYield() != null) {
			dto.setMaxYield(dto.getMaxYield().setScale(5, BigDecimal.ROUND_HALF_UP));
		}
		if (dto.getMinYield() != null) {
			dto.setMinYield(dto.getMinYield().setScale(5, BigDecimal.ROUND_HALF_UP));
		}
		if (dto.getMaxRate() != null) {
			dto.setMaxRate(dto.getMaxRate().setScale(5, BigDecimal.ROUND_HALF_UP));
		}
		if (dto.getMinRate() != null) {
			dto.setMinRate(dto.getMinRate().setScale(5, BigDecimal.ROUND_HALF_UP));
		}
		if (dto.getTruFeeRate() != null) {
			dto.setTruFeeRate(dto.getTruFeeRate().setScale(5, BigDecimal.ROUND_HALF_UP));
		}
		if (dto.getTradeFeeRate() != null) {
			dto.setTradeFeeRate(dto.getTradeFeeRate().setScale(5, BigDecimal.ROUND_HALF_UP));
		}
		if (dto.getMgrFeeRate() != null) {
			dto.setMgrFeeRate(dto.getMgrFeeRate().setScale(5, BigDecimal.ROUND_HALF_UP));
		}
		if (dto.getAgencyFeeRate() != null) {
			dto.setAgencyFeeRate(dto.getAgencyFeeRate().setScale(5, BigDecimal.ROUND_HALF_UP));
		}
		if (dto.getOtherFeeRate() != null) {
			dto.setOtherFeeRate(dto.getOtherFeeRate().setScale(5, BigDecimal.ROUND_HALF_UP));
		}
		if (dto.getAmt() != null) {
			dto.setAmt(dto.getAmt().setScale(2, BigDecimal.ROUND_HALF_UP));
		}
		if (dto.getTargetAmt() != null) {
			dto.setTargetAmt(dto.getTargetAmt().setScale(2, BigDecimal.ROUND_HALF_UP));
		}
		if (dto.getRemainTerm() != null) {
			dto.setRemainTerm(dto.getRemainTerm().setScale(2, BigDecimal.ROUND_HALF_UP));
		}
	}
}