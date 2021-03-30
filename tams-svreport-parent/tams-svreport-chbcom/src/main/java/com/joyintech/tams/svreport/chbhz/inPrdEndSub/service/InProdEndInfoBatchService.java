package com.joyintech.tams.svreport.chbhz.inPrdEndSub.service;

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
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.joyintech.tams.common.base.entity.ChbPrdEndSubTmp;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.util.ChbDeadlineUtil;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.svreport.chb.perprodeclarereg.dao.PerProDeclareReg001Mapper;
import com.joyintech.tams.svreport.chb.prodterminationregist.dao.ProdTerminationRegist001Mapper;
import com.joyintech.tams.svreport.chb.prodterminationregist.dto.ProdTerminationRegist001Dto;
import com.joyintech.tams.svreport.chbhz.inPrdEndSub.dao.InprodEndInfoSvrMapper;
import com.joyintech.tams.systemtools.constant.DataIndexSequenceType;
import com.joyintech.tams.systemtools.util.CommonUtil;

/**
 * 功能:产品终止信息登记生成批处理主service
 * 
 * @since
 * @version
 * @作者：dongxiangyang @创建时间： @修改记录：
 */
@Service
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class InProdEndInfoBatchService extends FileImpService {

	/**
	 * 终止接口临时表名
	 */
	private String tableName = "CHB_PRD_END_SUB_TMP";

	/**
	 * 重复性检查字段
	 */
	private static final String[] DUPLICATIED_LIST = { "PROD_ID" };

	/**
	 * 业务主键
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
	private PerProDeclareReg001Mapper perProDeclareReg001Mapper;

	@Autowired
	private CommonCodeCheckBatchService commonCodeCheckBatchService;

	@Autowired
	private InprodEndInfoSvrMapper InprodEndInfoSvrMapper;

	@Autowired
	private SqlSessionTemplate template;

	@Autowired
	private ProdTerminationRegist001Mapper prodTerminationRegist001Mapper;

	@Value("${application.reportInsertCount}")
	public int reportInsertCount;
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
		logger.debug("产品终止信息登记生成批处理处理开始------" + df.format(new Date()));
		// 1、取得业务数据 新建list<接口表DTO>
		List<ChbPrdEndSubTmp> listTmp = InprodEndInfoSvrMapper.genChbPrdEndSubTmpList(DateUtil.format(batchDate),
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
		List<ProdTerminationRegist001Dto> chbPrdEndSubList = svrMaptoEntityUtil.convertMapToEntity(listMap, listCloumn,
				ProdTerminationRegist001Dto.class);

		// 拼接查询主keylist
		List<String> keylist = chbPrdEndSubList.stream().map(ProdTerminationRegist001Dto::getProdId)
				.collect(Collectors.toList());

		// 3.2.1查询已经在登记表中存在的数据
		// 定义已经在登记表存在的数据
		List<ProdTerminationRegist001Dto> chbPrdEndSubListin = new ArrayList<>();
		chbPrdEndSubListin = InprodEndInfoSvrMapper.genChbPrdEndSubList(keylist);

		// 3.2.2 将已存在报送中的数据放入map中
		Map<String, ProdTerminationRegist001Dto> mapSub = chbPrdEndSubListin.stream().collect(Collectors.toMap(
				ProdTerminationRegist001Dto::getProdId, prodTerminationRegist001Dto -> prodTerminationRegist001Dto, (v1, v2) -> v1));

		// 删除数据list
		List<String> deleteList = new ArrayList<String>();
		// 新建数据list
		List<ProdTerminationRegist001Dto> insertList = new ArrayList<ProdTerminationRegist001Dto>();

		// ]清理掉错误数据的List进行循环处理
		for (ProdTerminationRegist001Dto sub : chbPrdEndSubList) {
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
			if (!mapSub.containsKey(sub.getProdId())) {
				deleteList.add(sub.getProdId());
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
			} else if (mapSub.containsKey(sub.getProdId())) {
				// 临时表存在、业务表也存在 的情况下
				ProdTerminationRegist001Dto subYw = mapSub.get(sub.getProdId());
				// （业务表中此条记录的.【报送状态】 == “02：接口报送”|| 业务表中此条记录的.【报送状态】 ==
				// “03：手工处理” ）
				if ((StringUtil.equals(DictCodeListExt2.报送状态_接口报送.getValue(), subYw.getSendStatus())
						|| StringUtil.equals(DictCodeListExt2.报送状态_手工处理.getValue(), subYw.getSendStatus()))) {
					// 当已经报送的数据状态为删除时
					if (StringUtil.equals(subYw.getStatus(), DictCodeListExt2.报送数据状态_删除.getValue())) {
						// tmp表数据为删除时
						if (StringUtil.equals(sub.getStatus(), DictCodeListExt2.数据状态_删除.getValue())) {
							// 删除数据List.add(此条记录）
							deleteList.add(sub.getProdId());
						} else {
							// 删除数据List.add(此条记录）
							deleteList.add(sub.getProdId());
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
								deleteList.add(sub.getProdId());
							}
							// tmp表数据不为删除时
							else {
								// 删除数据List.add(此条记录）删除的是未报送数据
								deleteList.add(sub.getProdId());
								// 内容不一致进行处理
								if (!compareRegData(subYw, sub)) {
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
								deleteList.add(sub.getProdId());
								// 新建 数据状态为删除数据状态，未报送数据
								sub.setStatus(DictCodeListExt2.报送数据状态_删除.getValue());
								// 报送状态
								sub.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
								insertList.add(sub);

							}
							// tmp表数据不为删除时
							else {
								// 删除数据List.add(此条记录）删除的是未报送数据
								deleteList.add(sub.getProdId());
								// 两条记录的所有申报字段比较，存在不一致的情况下，进行以下处理
								if (!compareRegData(subYw, sub)) {
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
					deleteList.add(sub.getProdId());
					// 新建数据List.add(此条记录）
					insertList.add(sub);
				}
			}
			// 报送截止日生成
			// 当前批次报送时点集合
			Map<String, List<Date>> dateMap = new HashMap<String, List<Date>>();
			List<Date> dateList = ChbDeadlineUtil.getChbEndDate(DictCodeListExt2.报表名称_产品终止登记.getValue(),
					DictCodeListExt2.报送数据状态_新建.getValue(), dateMap, batchDate);
			if (!StringUtil.isEmptyList(dateList)) {
				sub.setChbSubmitDeadline(dateList.get(0));
				sub.setSubmitDeadline(dateList.get(1));
			}
		}
		logger.debug("产品终止登记登记生成批处理处理结束------" + df.format(new Date()));
		// 进行入库处理
		int singalCount = Integer.parseInt(null == SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT)
				? "100" : SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT));
		int deleteCount = deleteList.size();
		int insertCount = insertList.size();
		logger.debug("终止登记数据删除--------开始---------");
		if (deleteCount > 0) {
			if (deleteCount <= singalCount) {
				prodTerminationRegist001Mapper.deleteBatch(deleteList);
			} else {
				for (int i = 0; i < deleteCount / singalCount; i++) {
					prodTerminationRegist001Mapper.deleteBatch(deleteList.subList(0, singalCount));
					deleteList.removeAll(deleteList.subList(0, singalCount));
				}
				if (!CollectionUtils.isEmpty(deleteList)) {
					prodTerminationRegist001Mapper.deleteBatch(deleteList);
				}
			}
		}
		logger.debug("终止登记数据删除--------结束---------");
		logger.debug("终止登记数据插入--------开始---------");
		if (insertCount > 0) {
			if (insertCount <= reportInsertCount) {
				prodTerminationRegist001Mapper.insertBatch(insertList);
			} else {
				for (int i = 0; i < insertCount / reportInsertCount; i++) {
					prodTerminationRegist001Mapper.insertBatch(insertList.subList(0, reportInsertCount));
					insertList.removeAll(insertList.subList(0, reportInsertCount));
				}
				if (!CollectionUtils.isEmpty(insertList)) {
					prodTerminationRegist001Mapper.insertBatch(insertList);
				}
			}
		}
		logger.debug("终止登记数据入库：" + insertList.size() + "条");
		logger.debug("终止登记数据插入--------结束---------");

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
	 * @Description 判断要素表中申报登记数据是否有变
	 * @param dto
	 * @param regdto
	 * @return boolean
	 * @version 1.7 2019年5月22日
	 */
	public boolean compareRegData(ProdTerminationRegist001Dto dto, ProdTerminationRegist001Dto regdto) {
		ProdTerminationRegist001Dto compare1 = genDeclareDto(dto);
		ProdTerminationRegist001Dto compare2 = genDeclareDto(regdto);
		if (JSON.toJSONString(compare1).equals(JSON.toJSONString(compare2))) {
			return true;
		} else {
			return false;
		}
	}

	private ProdTerminationRegist001Dto genDeclareDto(ProdTerminationRegist001Dto dto) {
		ProdTerminationRegist001Dto gendto = new ProdTerminationRegist001Dto();
		// 产品代码
		gendto.setProdId(dto.getProdId());
		// 产品名称
		gendto.setProdName(dto.getProdName());
		// 银行实际实现收入
		gendto.setProfit(dto.getProfit() == null ? BigDecimal.ZERO : dto.getProfit());
		// 兑付客户收益
		gendto.setCustomProfit(dto.getCustomProfit() == null ? BigDecimal.ZERO : dto.getCustomProfit());
		// 兑付客户收益(原币种)
		gendto.setCustomProfitOri(dto.getCustomProfitOri());
		// 兑付客户总金额(原币种)
		gendto.setCustomAmtOri(dto.getCustomAmtOri());
		// 兑付客户总金额
		gendto.setCustomAmt(dto.getCustomAmt() == null ? BigDecimal.ZERO : dto.getCustomAmt());
		// 客户实际年化收益率
		gendto.setCusYearYield(dto.getCusYearYield() == null ? BigDecimal.ZERO : dto.getCusYearYield());
		// 产品实际年化收益率
		gendto.setPrdYearYield(dto.getPrdYearYield() == null ? BigDecimal.ZERO : dto.getPrdYearYield());
		// 本机构托管费
		gendto.setCustFeeIn(dto.getCustFeeIn() == null ? BigDecimal.ZERO : dto.getCustFeeIn());
		// 本机构管理费
		gendto.setManFeeIn(dto.getManFeeIn() == null ? BigDecimal.ZERO : dto.getManFeeIn());
		// 本机构销售手续费
		gendto.setCouFeeIn(dto.getCouFeeIn() == null ? BigDecimal.ZERO : dto.getCouFeeIn());
		// 本机构其他产品费用
		gendto.setOtherFeeIn(dto.getOtherFeeIn() == null ? BigDecimal.ZERO : dto.getOtherFeeIn());
		// 其他机构托管费
		gendto.setCustFeeOut(dto.getCustFeeOut() == null ? BigDecimal.ZERO : dto.getCustFeeOut());
		// 其他机构管理费
		gendto.setManFeeOut(dto.getManFeeOut() == null ? BigDecimal.ZERO : dto.getManFeeOut());
		// 其他机构销售手续费
		gendto.setCouFeeOut(dto.getCouFeeOut() == null ? BigDecimal.ZERO : dto.getCouFeeOut());
		// 投资顾问费用
		gendto.setAdvFeeOut(dto.getAdvFeeOut() == null ? BigDecimal.ZERO : dto.getAdvFeeOut());
		// 其他机构其他产品费用
		gendto.setOtherFeeOut(dto.getOtherFeeOut() == null ? BigDecimal.ZERO : dto.getOtherFeeOut());
		// 兑付总份额
		gendto.setCustomQty(dto.getCustomQty() == null ? BigDecimal.ZERO : dto.getCustomQty());
		return gendto;
	}

}