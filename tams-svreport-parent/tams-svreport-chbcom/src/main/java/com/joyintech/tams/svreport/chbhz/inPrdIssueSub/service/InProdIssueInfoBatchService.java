package com.joyintech.tams.svreport.chbhz.inPrdIssueSub.service;

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
import com.joyintech.tams.common.base.entity.ChbPrdIssueSubTmp;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.util.ChbDeadlineUtil;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.svreport.chb.prodpersonissue.dao.ProdPersonIssue001Mapper;
import com.joyintech.tams.svreport.chb.prodpersonissue.dto.ProdPersonIssue001Dto;
import com.joyintech.tams.svreport.chbhz.inPrdIssueSub.dao.InprodIssueInfoSvrMapper;
import com.joyintech.tams.systemtools.constant.DataIndexSequenceType;
import com.joyintech.tams.systemtools.util.CommonUtil;

/**
 * 功能:产品申报信息登记生成批处理主service
 * 
 * @since 1.7.0
 * @version 1.7.0
 * @作者：dongxiangyang @创建时间： @修改记录：
 */
@Service
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class InProdIssueInfoBatchService extends FileImpService {

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
	private ProdPersonIssue001Mapper prodPersonIssue001Mapper;

	@Autowired
	private InprodIssueInfoSvrMapper inprodIssueInfoSvrMapper;

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
	 * @param
	 * @see
	 * @since 1.0
	 */
	public void generateDeclareInfoData(Date batchDate, List<String> prodIdList) {
		logger.debug("产品发行信息登记生成批处理处理开始------" + df.format(new Date()));
		// 1、取得业务数据 新建list<产品发行登记接口表DTO>
		List<ChbPrdIssueSubTmp> listTmp = inprodIssueInfoSvrMapper.genChbPrdIssueSubTmpList(DateUtil.format(batchDate),
				prodIdList);

		// 2 、校验、生成错误数据、正确数据的数据结构
		// 查询数据库表字段
		List<String> listCloumn = baseMapper.getTableColumnList(issueTableName);
		// 2.1调用共通 将list<接口表DTO>转换成List<Map<Key, Object>>格式
		List<Map<String, String>> listMap = svrMaptoEntityUtil.convertEntityToMap(listTmp, listCloumn);
		// 2.2调用FileImpService.checkData方法
		checkData(true, listMap, issueTableName, issueTableName, issueTableName, BUSSINESSKEY, DUPLICATIED_LIST);

		/** start 配置无法实现，代码实现的check add by zhangliang 20201010 */
		// commonCodeCheckTmpBatchService.codeCheckTmpCommon(listMap, issueTableName, BUSSINESSKEY);
		commonCodeCheckBatchService.codeCheckCommon(listMap, ChbCheckTableInfo.genChbCheckTableInfo(issueTableName), null);
		/** end 配置无法实现，代码实现的check add by zhangliang 20201010 */

		// 3、正确数据处理
		// 3.1 对[2]清理掉错误数据的List，调用以下共通，进行数据类型转化处理。将检查后的listMap 转为List<业务表登记表Dto>
		List<ProdPersonIssue001Dto> chbPrdIssueSubList = svrMaptoEntityUtil.convertMapToEntity(listMap, listCloumn,
				ProdPersonIssue001Dto.class);

		// 拼接查询主keylist
		List<String> keylist = chbPrdIssueSubList.stream().map(ProdPersonIssue001Dto::getProdId)
				.collect(Collectors.toList());

		// 3.2.1查询已经在登记表中存在的数据
		// 定义已经在登记表存在的数据
		List<ProdPersonIssue001Dto> chbPrdIssueSubListin = new ArrayList<>();
		chbPrdIssueSubListin = inprodIssueInfoSvrMapper.genChbPrdIssueSubList(keylist);

		// 3.2.2 将已存在报送中的数据放入map中
		Map<String, ProdPersonIssue001Dto> mapSub = chbPrdIssueSubListin.stream().collect(
				Collectors.toMap(ProdPersonIssue001Dto::getProdId, prodPersonIssue001Dto -> prodPersonIssue001Dto, (v1, v2) -> v1));

		// 报送时点
		Map<String, List<Date>> dateMap = new HashMap<String, List<Date>>();

		// 删除数据list
		List<ProdPersonIssue001Dto> deleteList = new ArrayList<ProdPersonIssue001Dto>();
		// 新建数据list
		List<ProdPersonIssue001Dto> insertList = new ArrayList<ProdPersonIssue001Dto>();

		// ]清理掉错误数据的List进行循环处理
		for (ProdPersonIssue001Dto sub : chbPrdIssueSubList) {
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
			sub.setIssuePartyId(SvrDataUtil.getSysParam(CommonUtil.selectBankCode()));
			// 报送流水号
			sub.setSumitSeq(CommonUtil.getSequenceIndexData(DataIndexSequenceType.中债登记管理));
			// 业务表不存在数据：
			if (!mapSub.containsKey(sub.getProdId())) {
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
			} else if (mapSub.containsKey(sub.getProdId())) {
				// 临时表存在、业务表也存在 的情况下
				ProdPersonIssue001Dto subYw = mapSub.get(sub.getProdId());
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
								if (!compareIssueRegData(subYw, sub)) {
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
								if (!compareIssueRegData(subYw, sub)) {
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
			// 产品发行登记报送时点:募集起始日期
			if (sub.getRaiseVdate() != null) {
				List<Date> issueDateList = ChbDeadlineUtil.getChbEndDate(DictCodeListExt2.报表名称_产品发行登记.getValue(),
						DictCodeListExt2.报送数据状态_新建.getValue(), dateMap, sub.getRaiseVdate());
				if (CollectionUtils.isNotEmpty(issueDateList)) {
					sub.setChbSubmitDeadline(issueDateList.get(0));
					sub.setSubmitDeadline(issueDateList.get(1));
				}
			}

		}
		logger.debug("产品发行信息登记生成批处理处理结束------" + df.format(new Date()));
		// 进行入库处理
		/**反写产品基本信息的起息日和到期日 */
		updateChbProBaseInfo(insertList);
		/**反写产品基本信息的起息日和到期日*/
		logger.debug("产品发行信息登记入库开始------" + df.format(new Date()));
		dealResultIssueCommitSingle(deleteList, insertList);
		logger.debug("产品发行信息登记入库：" + insertList.size() + "条");
		logger.debug("产品发行信息登记入库结束------" + df.format(new Date()));

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
	public boolean compareIssueRegData(ProdPersonIssue001Dto dto, ProdPersonIssue001Dto regdto) {
		ProdPersonIssue001Dto compare1 = genIssueDto(dto);
		ProdPersonIssue001Dto compare2 = genIssueDto(regdto);
		if (JSON.toJSONString(compare1).equals(JSON.toJSONString(compare2))) {
			return true;
		} else {
			return false;
		}
	}

	private ProdPersonIssue001Dto genIssueDto(ProdPersonIssue001Dto dto) {
		ProdPersonIssue001Dto gendto = new ProdPersonIssue001Dto();
		// 产品登记编码
		gendto.setProdRegistCode(dto.getProdRegistCode());
		// 产品代码
		gendto.setProdId(dto.getProdId());
		// 募集起始日期
		gendto.setRaiseVdate(dto.getRaiseVdate());
		// 募集结束日期
		gendto.setRaiseEnddate(dto.getRaiseEnddate());
		// 产品起始日期
		gendto.setPrdVdate(dto.getPrdVdate());
		// 产品终止日期
		gendto.setPrdMdate(dto.getPrdMdate());
		// 是否为结构化
		gendto.setIsConstracte(dto.getIsConstracte());
		// 层级类别
		gendto.setGradeType(dto.getGradeType());
		// 预计客户最低年收益率 %
		gendto.setExpMinYield((dto.getExpMinYield() == null ? BigDecimal.ZERO : dto.getExpMinYield()).setScale(5,
				BigDecimal.ROUND_HALF_UP));
		// 预计客户最高年收益率 %
		gendto.setExpMaxYield((dto.getExpMaxYield() == null ? BigDecimal.ZERO : dto.getExpMaxYield()).setScale(5,
				BigDecimal.ROUND_HALF_UP));
		// 平均预计客户年收益率%
		gendto.setExpAvgYield((dto.getExpAvgYield() == null ? BigDecimal.ZERO : dto.getExpAvgYield()).setScale(5,
				BigDecimal.ROUND_HALF_UP));
		// 收益率分档情况说明
		gendto.setYieldComment(dto.getYieldComment());
		// 开放模式 01 有规律开放/02 无规律开放
		gendto.setOpenMode(dto.getOpenMode());
		// 规律开放周期
		gendto.setOpenCircle(dto.getOpenCircle());
		// 其他规律开放周期 (天)
		gendto.setOpenCircleDays((dto.getOpenCircleDays() == null ? BigDecimal.ZERO : dto.getOpenCircleDays())
				.setScale(4, BigDecimal.ROUND_HALF_UP));
		// 无规律开放说明
		gendto.setRuleComment(dto.getRuleComment());
		// 首次开放周期起始日
		gendto.setFirstOpenDay(dto.getFirstOpenDay());
		// 节假日是否开放 01 是/02 否
		gendto.setHolidayOnFlag(dto.getHolidayOnFlag());
		// 平均开放次数（年化）
		gendto.setAvgOpenCount((dto.getAvgOpenCount() == null ? BigDecimal.ZERO : dto.getAvgOpenCount()).setScale(4,
				BigDecimal.ROUND_HALF_UP));
		// 开放期业务
		gendto.setOpenBuss(dto.getOpenBuss());
		// 开放期业务说明
		gendto.setOpenBussRemark(dto.getOpenBussRemark());
		// add by dubaojun 2020-11-05 五月升级字段比较 start
		// 管理方式
		gendto.setManageType(dto.getManageType());
		// 业绩比较基准下限
        gendto.setYieldLow(dto.getYieldLow());
		// 业绩比较基准上限
        gendto.setYieldHigh(dto.getYieldHigh());
		// 业绩比较基准说明
        gendto.setYieldRemark(dto.getYieldRemark());
		// 资金托管账号
        gendto.setBusiAcctNo(dto.getBusiAcctNo());
		// 资金托管账户
        gendto.setAcctName(dto.getAcctName());
        // add by dubaojun 2020-11-05 五月升级字段比较 end
		return gendto;
	}

	/**
	 * 功能:带提交的方式单条处理数据结果，入库
	 * 
	 * @since 1.6.0
	 * @version 1.6.0
	 * @author：weiyuqiang 2019年3月7日
	 * @param assetList
	 *            void
	 */
	private void dealResultIssueCommitSingle(List<ProdPersonIssue001Dto> deleteDataList,
			List<ProdPersonIssue001Dto> insertDataList) {
		if (CollectionUtils.isNotEmpty(deleteDataList)) {
			int batchCount = Integer.parseInt(null == SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT)
					? "50" : SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT));// 每批commit的个数
			int batchLastIndex = batchCount;// 每批最后一个的下标
			for (int index = 0; index < deleteDataList.size();) {
				if (batchLastIndex > deleteDataList.size() - 1) {
					batchLastIndex = deleteDataList.size();
					prodPersonIssue001Mapper.deleteByProdid(deleteDataList.subList(index, batchLastIndex));
					break;// 数据插入完毕,退出循环
				} else {
					prodPersonIssue001Mapper.deleteByProdid(deleteDataList.subList(index, batchLastIndex));
					index = batchLastIndex;// 设置下一批下标
					batchLastIndex = index + batchCount;
				}
			}
		}
		if (CollectionUtils.isNotEmpty(insertDataList)) {
			int count = 0;
			SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
			try {
				ProdPersonIssue001Mapper batchMapper = session.getMapper(ProdPersonIssue001Mapper.class);
				for (ProdPersonIssue001Dto asset : insertDataList) {
					batchMapper.insertSub(asset);
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
				session.rollback();
				logger.error(e.getMessage(), e);
			} finally {
				session.close();
			}
		}
	}
	
	/**
	 * 根据发行反写产品基本信息的起息日和到期日
	 * @param insertList
	 */
	public   void   updateChbProBaseInfo(List<ProdPersonIssue001Dto> insertList){
		     //循环list，进行更新
		    for(ProdPersonIssue001Dto dto : insertList){
		    	inprodIssueInfoSvrMapper.updateChbprdBaseInfo(dto);
		    }
	}

}