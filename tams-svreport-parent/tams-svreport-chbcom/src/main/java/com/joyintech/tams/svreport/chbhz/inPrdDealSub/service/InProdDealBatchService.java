package com.joyintech.tams.svreport.chbhz.inPrdDealSub.service;

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
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.chbreport.codecheck.service.CommonCodeCheckTmpBatchService;
import com.joyintech.tams.common.FileImpService;
import com.joyintech.tams.common.InterfFile;
import com.joyintech.tams.common.base.dao.BaseMapper;
import com.joyintech.tams.common.base.entity.ChbPrdDealSub;
import com.joyintech.tams.common.base.entity.ChbPrdDealSubTmp;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.util.ChbDeadlineUtil;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.svreport.chb.proddurationreg.dao.ChbPrdDurationRegMapper;
import com.joyintech.tams.svreport.chbhz.inPrdDealSub.dao.InprodDealSvrMapper;
import com.joyintech.tams.systemtools.constant.DataIndexSequenceType;
import com.joyintech.tams.systemtools.util.CommonUtil;

/**
 * 功能:产品存续期登记生成批处理主service
 * 
 * @since 1.7.0
 * @version 1.7.0
 * @作者：dongxiangyang @创建时间： @修改记录：
 */
@Service
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class InProdDealBatchService extends FileImpService {

	/**
	 * 产品存续期接口临时表名
	 */
	private String tableName = "CHB_PRD_DEAL_SUB_TMP";

	/**
	 * 重复性检查字段
	 */
	private static final String[] DUPLICATIED_LIST = { "PROD_REGIST_CODE", "MDATE" };

	/**
	 * 业务主键1
	 */
	private static final String BUSSINESSKEY = "PROD_ID";

	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());

	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Autowired
	private SvrMaptoEntityUtil svrMaptoEntityUtil;

	@Autowired
	private ChbPrdDurationRegMapper chbPrdDurationRegMapper;

	@Autowired
	private InprodDealSvrMapper inprodDealSvrMapper;

	@Autowired
	private CommonCodeCheckBatchService commonCodeCheckBatchService;

	@Autowired
	private SqlSessionTemplate template;
	/**
	 * 中债基础数据mapper
	 */
	@Autowired
	private BaseMapper baseMapper;

	/**
	 * @param
	 * @see
	 * @since 1.0
	 */
	public void generateDeclareInfoData(Date batchDate, List<String> prodIdList) {
		logger.debug("产品存续期登记生成批处理处理开始------" + df.format(new Date()));
		// 1、取得业务数据 新建list<产品存续期登记接口表DTO>
		List<ChbPrdDealSubTmp> listTmp = inprodDealSvrMapper.genChbPrdDealSubTmpList(DateUtil.format(batchDate),
				prodIdList);

		// 2 、校验、生成错误数据、正确数据的数据结构
		// 查询数据库表字段
		List<String> listCloumn = baseMapper.getTableColumnList(tableName);
		// 2.1调用共通 将list<接口表DTO>转换成List<Map<Key, Object>>格式
		List<Map<String, String>> listMap = svrMaptoEntityUtil.convertEntityToMap(listTmp, listCloumn);
		// 2.2调用FileImpService.checkData方法
		checkData(true, listMap, tableName, tableName, tableName, BUSSINESSKEY, DUPLICATIED_LIST);
		/** start 配置无法实现，代码实现的check add by dongxiangyang 20201009 */
		// commonCodeCheckTmpBatchService.codeCheckTmpCommon(listMap, tableName, BUSSINESSKEY);
		commonCodeCheckBatchService.codeCheckCommon(listMap, ChbCheckTableInfo.genChbCheckTableInfo(tableName), null);
		/** end 配置无法实现，代码实现的check add by dongxiangyang 20201009 */
		// 3、正确数据处理
		// 3.1 对[2]清理掉错误数据的List，调用以下共通，进行数据类型转化处理。将检查后的listMap 转为List<业务表登记表Dto>
		List<ChbPrdDealSub> chbPrdDealSubList = svrMaptoEntityUtil.convertMapToEntity(listMap, listCloumn,
				ChbPrdDealSub.class);

		// 拼接查询主keylist
		List<String> keylist = chbPrdDealSubList.stream().map(dto -> getBusinessKeyStr(dto)).collect(Collectors.toList());

		// 3.2.1查询已经在登记表中存在的数据
		// 定义已经在登记表存在的数据
		List<ChbPrdDealSub> chbPrdDealSubListin = new ArrayList<>();
		chbPrdDealSubListin = inprodDealSvrMapper.genChbPrdDealSubList(keylist);

		// 3.2.2 将已存在报送中的数据放入map中
		Map<String, ChbPrdDealSub> mapSub = chbPrdDealSubListin.stream()
				.collect(Collectors.toMap(dto -> getBusinessKeyStr(dto), chbPrdDealSub -> chbPrdDealSub, (v1, v2) -> v1));

		// 删除数据list
		List<ChbPrdDealSub> deleteList = new ArrayList<ChbPrdDealSub>();
		// 新建数据list
		List<ChbPrdDealSub> insertList = new ArrayList<ChbPrdDealSub>();

		// ]清理掉错误数据的List进行循环处理
		for (ChbPrdDealSub sub : chbPrdDealSubList) {
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
			sub.setIssuePartyId(SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_ISSUE_PARTY_ID));
			// 报送流水号
			sub.setSumitSeq(CommonUtil.getSequenceIndexData(DataIndexSequenceType.中债登记管理));
			// 业务表不存在数据：
			if (!mapSub.containsKey(getBusinessKeyStr(sub))) {
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
			} else if (mapSub.containsKey(getBusinessKeyStr(sub))) {
				// 临时表存在、业务表也存在 的情况下
				ChbPrdDealSub subYw = mapSub.get(getBusinessKeyStr(sub));
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
								if (!compareDealRegData(subYw, sub)) {
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
								if (!compareDealRegData(subYw, sub)) {
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
			// 细分类
			String typeDetail = "";
			// 从产品申报登记里取产品运作模式
			String operaMode = inprodDealSvrMapper.getOpertModeByPordId(sub.getProdId());
			if (DictCodeListExt2.产品运作模式_开放式非净值型.getValue().equals(operaMode)
					|| DictCodeListExt2.产品运作模式_封闭式非净值型.getValue().equals(operaMode)) {
				if (DictCodeListExt2.产品运作模式_开放式非净值型.getValue().equals(operaMode)) {
					typeDetail = DictCodeListExt2.细分类别_开放式非净值型.getValue();
				} else {
					typeDetail = DictCodeListExt2.细分类别_封闭式非净值型.getValue();
				}
			} else {
				if (DictCodeListExt2.产品运作模式_开放式净值型.getValue().equals(operaMode)) {
					typeDetail = DictCodeListExt2.细分类别_开放式净值型.getValue();
				} else {
					typeDetail = DictCodeListExt2.细分类别_封闭式净值型.getValue();
				}
			}
			// 报送截止日生成
			Map<String, List<Date>> dateMap = new HashMap<String, List<Date>>();
			HashMap<String, List<Date>> submitDateMap = new HashMap<String, List<Date>>();
			String key = DictCodeListExt2.报表名称_产品存续期登记.getValue() + typeDetail;
			List<Date> dateList = new ArrayList<Date>();
			if (submitDateMap.containsKey(key)) {
				dateList = submitDateMap.get(key);
			} else {
				dateList = ChbDeadlineUtil.getChbEndDate(DictCodeListExt2.报表名称_产品存续期登记.getValue(), typeDetail,
						DictCodeListExt2.报送数据状态_新建.getValue(), dateMap, DateUtil.parse(sub.getMdate()));
				submitDateMap.put(key, dateList);
			}

			if (StringUtil.isNotEmpty(dateList)) {
				sub.setChbSubmitDeadline(dateList.get(0));
				sub.setSubmitDeadline(dateList.get(1));
			}

		}
		logger.debug("产品存续期登记生成批处理处理结束------" + df.format(new Date()));
		// 进行入库处理
		logger.debug("产品存续期登记入库开始------" + df.format(new Date()));
		// dealResultIssueCommitSingle(deleteList, insertList);
		if (!StringUtil.isEmptyList(deleteList)) {
			deleteBatch(deleteList);
		}
		if (!StringUtil.isEmptyList(insertList)) {
			insertBatch(insertList);
		}
		logger.debug("产品存续期登记入库：" + insertList.size() + "条");
		logger.debug("产品存续期登记入库结束------" + df.format(new Date()));

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
	 * @Description 判断要素表中发行登记数据是否有变
	 * @param dto
	 * @param regdto
	 * @return boolean
	 * @version 1.7 2019年5月22日
	 */
	public boolean compareDealRegData(ChbPrdDealSub dto, ChbPrdDealSub regdto) {
		ChbPrdDealSub compare1 = genIssueDto(dto);
		ChbPrdDealSub compare2 = genIssueDto(regdto);
		if (JSON.toJSONString(compare1).equals(JSON.toJSONString(compare2))) {
			return true;
		} else {
			return false;
		}
	}

	private ChbPrdDealSub genIssueDto(ChbPrdDealSub dto) {
		ChbPrdDealSub gendto = new ChbPrdDealSub();
		// 产品登记编码
		gendto.setProdRegistCode(dto.getProdRegistCode());
		// 产品代码
		gendto.setProdId(dto.getProdId());
		// 发行机构代码
		gendto.setIssuePartyId(dto.getIssuePartyId());
		// 净值币种
		gendto.setValueCcy(dto.getValueCcy());
		// 银行实现收益(元)
		gendto.setBankImpInt(dto.getBankImpInt() == null ? BigDecimal.ZERO
				: dto.getBankImpInt().setScale(4, BigDecimal.ROUND_HALF_UP));
		// 业务起始日
		gendto.setVdate(dto.getVdate());
		// 业务结束日
		gendto.setMdate(dto.getMdate());
		// 产品余额
		gendto.setBalanceAmt(dto.getBalanceAmt() == null ? BigDecimal.ZERO
				: dto.getBalanceAmt().setScale(4, BigDecimal.ROUND_HALF_UP));
		// 币种及申购兑付金额
		gendto.setCcyPurchase(dto.getCcyPurchase());
		// 初始净值
		gendto.setOriginalValue(dto.getOriginalValue() == null ? BigDecimal.ZERO
				: dto.getOriginalValue().setScale(4, BigDecimal.ROUND_HALF_UP));
		// 产品净值
		gendto.setPrdValue(
				dto.getPrdValue() == null ? BigDecimal.ZERO : dto.getPrdValue().setScale(4, BigDecimal.ROUND_HALF_UP));
		// 累计净值
		gendto.setTotalValue(dto.getTotalValue() == null ? BigDecimal.ZERO
				: dto.getTotalValue().setScale(4, BigDecimal.ROUND_HALF_UP));
		// 人民币初始净值
		gendto.setCnyOrlValue(dto.getCnyOrlValue() == null ? BigDecimal.ZERO
				: dto.getCnyOrlValue().setScale(4, BigDecimal.ROUND_HALF_UP));
		// 折算人民币净值
		gendto.setCnyPrdValue(dto.getCnyPrdValue() == null ? BigDecimal.ZERO
				: dto.getCnyPrdValue().setScale(4, BigDecimal.ROUND_HALF_UP));
		// 人民币累计净值
		gendto.setCnyTalValue(dto.getCnyTalValue() == null ? BigDecimal.ZERO
				: dto.getCnyTalValue().setScale(4, BigDecimal.ROUND_HALF_UP));
		// 累计申购份额
		gendto.setPurchaseAmt(dto.getPurchaseAmt() == null ? BigDecimal.ZERO
				: dto.getPurchaseAmt().setScale(4, BigDecimal.ROUND_HALF_UP));
		// 累计赎回份额
		gendto.setRedeemQty(dto.getRedeemQty() == null ? BigDecimal.ZERO
				: dto.getRedeemQty().setScale(4, BigDecimal.ROUND_HALF_UP));
		// 产品份额
		gendto.setPrdQty(
				dto.getPrdQty() == null ? BigDecimal.ZERO : dto.getPrdQty().setScale(4, BigDecimal.ROUND_HALF_UP));
		// 实现收益率
		gendto.setReaYield(
				dto.getReaYield() == null ? BigDecimal.ZERO : dto.getReaYield().setScale(4, BigDecimal.ROUND_HALF_UP));
		// 最新预期收益率
		gendto.setNewExpYield(dto.getNewExpYield() == null ? BigDecimal.ZERO
				: dto.getNewExpYield().setScale(4, BigDecimal.ROUND_HALF_UP));
		// 每万份份额分红
		gendto.setShareAmtWan(dto.getShareAmtWan() == null ? BigDecimal.ZERO
				: dto.getShareAmtWan().setScale(4, BigDecimal.ROUND_HALF_UP));
		// 每万份现金分红
		gendto.setCashAmtWan(dto.getCashAmtWan() == null ? BigDecimal.ZERO
				: dto.getCashAmtWan().setScale(4, BigDecimal.ROUND_HALF_UP));
		gendto.setCreateUser(null);
		gendto.setCreateTime(null);
		gendto.setCreateDept(null);
		gendto.setUpdateUser(null);
		gendto.setUpdateTime(null);
		gendto.setStatus(null);
		return gendto;
	}

	private void deleteBatch(List<ChbPrdDealSub> dataList) {
		logger.debug("未报送登记信息批量删除:----开始------" + df.format(new Date()));
		int singalCount = Integer.parseInt(null == SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT)
				? "100" : SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT));
		List<List<ChbPrdDealSub>> bigList = createPrdDealSubList(dataList, singalCount);
		for (int i = 0; i < bigList.size(); i++) {
			chbPrdDurationRegMapper.deleteBatchByprodId(bigList.get(i));
		}
		logger.debug("未报送登记信息批量删除:----结束------" + df.format(new Date()));
	}

	private void insertBatch(List<ChbPrdDealSub> dataList) {
		logger.debug("登记信息入库:----开始------" + df.format(new Date()));
		SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
		// 批量插入mapper
		ChbPrdDurationRegMapper batchMapper = session.getMapper(ChbPrdDurationRegMapper.class);
		try {
			int count = 0;
			for (ChbPrdDealSub dl : dataList) {
				batchMapper.insertPrdDealSub(dl);
				count++;
				if (count % 1000 == 0) {
					session.commit();
					session.clearCache();
				}
			}
			session.commit();
			session.clearCache();
		}
		// catch (Exception e) {
		// logger.debug("批量登记信息出错:" + e.getMessage());
		// session.rollback();
		// }
		finally {
			session.close();
		}
		logger.debug("登记信息入库:----结束------" + df.format(new Date()));
	}

	/**
	 * 将集合均分成几个大小一致的子集合
	 * 
	 * @param 集合
	 * @param 子集合大小
	 * @return
	 */
	public static List<List<ChbPrdDealSub>> createPrdDealSubList(List<ChbPrdDealSub> targe, int size) {
		List<List<ChbPrdDealSub>> listArr = new ArrayList<List<ChbPrdDealSub>>();
		// 获取被拆分的数组个数
		int arrSize = targe.size() % size == 0 ? targe.size() / size : targe.size() / size + 1;
		for (int i = 0; i < arrSize; i++) {
			List<ChbPrdDealSub> sub = new ArrayList<ChbPrdDealSub>();
			// 把指定索引数据放入到list中
			for (int j = i * size; j <= size * (i + 1) - 1; j++) {
				if (j <= targe.size() - 1) {
					sub.add(targe.get(j));
				}
			}
			listArr.add(sub);
		}
		return listArr;
	}

	/**
	 * 获取业务主键拼接的字符串
	 * 
	 * @param sub
	 *            数据
	 * @return 业务主键
	 */
	private String getBusinessKeyStr(ChbPrdDealSub sub) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(sub.getProdId());
		buffer.append("|");
		buffer.append(sub.getMdate());
		return buffer.toString();
	}

}