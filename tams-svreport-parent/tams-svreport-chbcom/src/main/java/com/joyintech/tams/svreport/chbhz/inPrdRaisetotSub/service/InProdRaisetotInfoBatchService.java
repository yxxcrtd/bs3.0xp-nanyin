package com.joyintech.tams.svreport.chbhz.inPrdRaisetotSub.service;

import com.alibaba.fastjson.JSON;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.FileImpService;
import com.joyintech.tams.common.InterfFile;
import com.joyintech.tams.common.base.dao.BaseMapper;
import com.joyintech.tams.common.base.entity.ChbPrdRaisetotSubTmp;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.common.svreport.datacheck.beforeinsert.dto.ChbCheckTableInfo;
import com.joyintech.tams.common.svreport.datacheck.beforeinsert.service.CommonCodeCheckBatchService;
import com.joyintech.tams.common.svreport.datacheck.beforeinsert.util.SvrMaptoEntityUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.util.ChbDeadlineUtil;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.svreport.chb.prodraisetotalregist.dao.RaiseTotalRegist001Mapper;
import com.joyintech.tams.svreport.chb.prodraisetotalregist.dto.RaiseTotalRegist001Dto;
import com.joyintech.tams.svreport.chbhz.inPrdRaisetotSub.dao.InprodRaisetotInfoSvrMapper;
import com.joyintech.tams.systemtools.constant.DataIndexSequenceType;
import com.joyintech.tams.systemtools.util.CommonUtil;
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

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 功能:产品募集总量登记生成批处理主service
 * 
 * @since 1.0.0
 * @version 1.0.0
 * @作者：zhaoyongsheng @创建时间： @修改记录：
 */
@Service
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class InProdRaisetotInfoBatchService extends FileImpService {

	/**
	 * 重复性检查字段
	 */
	private static final String[] DUPLICATIED_LIST = { "PROD_REGIST_CODE" };

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
	private InprodRaisetotInfoSvrMapper inprodRaisetotInfoSvrMapper;

	@Autowired
	private SvrMaptoEntityUtil svrMaptoEntityUtil;

	@Autowired
	private RaiseTotalRegist001Mapper raiseTotalRegist001Mapper;

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
	public void generateRaisetotInfoData(Date batchDate, List<String> prodIdList) {
		logger.debug("产品募集总量信息登记生成批处理处理开始------" + df.format(new Date()));
		// 1、取得业务数据 新建list<接口表DTO>
		List<ChbPrdRaisetotSubTmp> listTmp = inprodRaisetotInfoSvrMapper
				.genChbPrdRaisetotSubTmpList(DateUtil.format(batchDate), prodIdList);

		// 2 、校验、生成错误数据、正确数据的数据结构
		// 查询数据库表字段
		List<String> listCloumn = baseMapper.getTableColumnList(raisetotTableName);
		listCloumn.add("PROD_ID");
		// 2.1调用共通 将list<接口表DTO>转换成List<Map<Key, Object>>格式
		List<Map<String, String>> listMap = svrMaptoEntityUtil.convertEntityToMap(listTmp, listCloumn);

		// 2.2调用FileImpService.checkData方法
		checkData(true, listMap, raisetotTableName, raisetotTableName, raisetotTableName, BUSSINESSKEY,
				DUPLICATIED_LIST);

		/** start 配置无法实现，代码实现的check add by zhangliang 20201010 */
		// commonCodeCheckTmpBatchService.codeCheckTmpCommon(listMap, raisetotTableName, BUSSINESSKEY);
		commonCodeCheckBatchService.codeCheckCommon(listMap, ChbCheckTableInfo.genChbCheckTableInfo(raisetotTableName), null);
		/** end 配置无法实现，代码实现的check add by zhangliang 20201010 */

		// 3、正确数据处理
		// 3.1 对[2]清理掉错误数据的List，调用以下共通，进行数据类型转化处理。将检查后的listMap 转为List<业务表登记表Dto>
		List<RaiseTotalRegist001Dto> chbPrdRaisetotSubList = svrMaptoEntityUtil.convertMapToEntity(listMap, listCloumn,
				RaiseTotalRegist001Dto.class);

		// 拼接查询主keylist
		List<String> keylist = chbPrdRaisetotSubList.stream().map(RaiseTotalRegist001Dto::getProdRegistCode)
				.collect(Collectors.toList());

		// 3.2.1查询已经在登记表中存在的数据
		// 定义已经在登记表存在的数据
		List<RaiseTotalRegist001Dto> chbPrdRaisetotSubListin = new ArrayList<>();
		chbPrdRaisetotSubListin = inprodRaisetotInfoSvrMapper.genChbPrdRaisetotSubList(keylist);
		// 当前批次报送时点集合
		Map<String, List<Date>> dateMap = new HashMap<String, List<Date>>();

		// 3.2.2 将已存在报送中的数据放入map中
		Map<String, RaiseTotalRegist001Dto> mapSub = chbPrdRaisetotSubListin.stream().collect(Collectors
				.toMap(RaiseTotalRegist001Dto::getProdRegistCode, raiseTotalRegist001Dto -> raiseTotalRegist001Dto, (v1, v2) -> v1));

		// 删除数据list
		List<RaiseTotalRegist001Dto> deleteList = new ArrayList<RaiseTotalRegist001Dto>();
		// 新建数据list
		List<RaiseTotalRegist001Dto> insertList = new ArrayList<RaiseTotalRegist001Dto>();
		// 获取系统参数
		// String svrSubmitOnChange =
		// SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_SUBMIT_ONCHANGE);

		// ]清理掉错误数据的List进行循环处理
		for (RaiseTotalRegist001Dto sub : chbPrdRaisetotSubList) {
			// 产品代码=产品登记编码
			sub.setProdRegistCode(sub.getProdRegistCode());
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
			if (!mapSub.containsKey(sub.getProdRegistCode())) {
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
			} else if (mapSub.containsKey(sub.getProdRegistCode())) {
				// 临时表存在、业务表也存在 的情况下
				RaiseTotalRegist001Dto subYw = mapSub.get(sub.getProdRegistCode());
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
								if (!compareRaisetotRegData(subYw, sub)) {
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
								if (!compareRaisetotRegData(subYw, sub)) {
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
					deleteList.add(sub);
					// 新建数据List.add(此条记录）
					insertList.add(sub);
				}

				/*
				 * // 临时表存在、业务表也存在 的情况下 RaiseTotalRegist001Dto subYw =
				 * mapSub.get(sub.getProdRegistCode()); //（业务表中此条记录的.【报送状态】 ==
				 * “02：接口报送”|| 业务表中此条记录的.【报送状态】 == “03：手工处理” ）
				 * if((StringUtil.equals(DictCodeListExt2.报送状态_接口报送.getValue(),
				 * subYw.getSendStatus())||
				 * StringUtil.equals(DictCodeListExt2.报送状态_手工处理.getValue(),
				 * subYw.getSendStatus()))&&
				 * StringUtil.equals(svrSubmitOnChange,
				 * DictCodeListExt1.是否_是.getValue()) ){
				 * 
				 * //当已经报送的数据状态未删除时，新增已报送的新建数据
				 * if(StringUtil.equals(subYw.getStatus(),
				 * DictCodeListExt2.数据状态_删除.getValue())){ //删除数据List.add(此条记录）
				 * deleteList.add(sub);
				 * sub.setStatus(DictCodeListExt2.数据状态_新建.getValue()); //报送状态
				 * sub.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
				 * insertList.add(sub); } //两条记录的所有申报字段比较，存在不一致的情况下，进行以下处理 else
				 * if(!compareRaisetotRegData(subYw, sub)){ //删除数据List.add(此条记录）
				 * deleteList.add(sub); //此条记录的【数据状态】= '02:修改'
				 * sub.setStatus(DictCodeListExt2.数据状态_修改.getValue()); //报送状态
				 * sub.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
				 * insertList.add(sub); } // else{ // //此条记录的【数据状态】= '02:修改' //
				 * sub.setStatus(DictCodeListExt2.数据状态_新建.getValue()); // //报送状态
				 * // sub.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
				 * // insertList.add(sub); // } }else{ //此条记录的【数据状态】= '02:新建'
				 * sub.setStatus(DictCodeListExt2.数据状态_新建.getValue()); //报送状态
				 * sub.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
				 * //删除数据List.add(此条记录） deleteList.add(sub);
				 * //新建数据List.add(此条记录） insertList.add(sub); }
				 */
			}
			Date raiseEnddate = inprodRaisetotInfoSvrMapper.selectRaiseEnddateByRegistCode(sub.getProdRegistCode());
			if (raiseEnddate != null) {
				sub.setRaiseEnddate(raiseEnddate);
			} else {
				sub.setRaiseEnddate(batchDate);
			}

			if (sub.getRaiseEnddate() != null) {
				List<Date> dateList = ChbDeadlineUtil.getChbEndDate(DictCodeListExt2.报表名称_募集总量登记.getValue(),
						DictCodeListExt2.报送数据状态_新建.getValue(), dateMap, sub.getRaiseEnddate());
				if (!StringUtil.isEmptyList(dateList)) {
					sub.setChbSubmitDeadline(dateList.get(0));
					sub.setSubmitDeadline(dateList.get(1));
				}
			}

		}
		logger.debug("产品募集总量信息登记生成批处理处理结束------" + df.format(new Date()));
		// 进行入库处理
		logger.debug("产品募集总量信息登记入库开始------" + df.format(new Date()));
		dealResultSubCommitSingle(deleteList, insertList);
		logger.debug("产品募集总量信息登记入库：" + insertList.size() + "条");
		logger.debug("产品募集总量信息登记入库结束------" + df.format(new Date()));

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
	 * @Description 判断募集总量表中要素登记数据是否有变
	 * @param dto
	 * @param regdto
	 * @return boolean
	 * @version 1.7 2019年5月22日
	 */
	public boolean compareRaisetotRegData(RaiseTotalRegist001Dto dto, RaiseTotalRegist001Dto regdto) {
		RaiseTotalRegist001Dto compare1 = genRaisetotDto(dto);
		RaiseTotalRegist001Dto compare2 = genRaisetotDto(regdto);
		if (JSON.toJSONString(compare1).equals(JSON.toJSONString(compare2))) {
			return true;
		} else {
			return false;
		}
	}

	private RaiseTotalRegist001Dto genRaisetotDto(RaiseTotalRegist001Dto dto) {
		RaiseTotalRegist001Dto gendto = new RaiseTotalRegist001Dto();
//		// 产品代码
//		gendto.setProdId(dto.getProdId());
//		// 产品名称
//		gendto.setProdName(dto.getProdName());
//		// 中债报送截止日
//		gendto.setChbSubmitDeadline(dto.getChbSubmitDeadline());
//		// 代销总金额
//		gendto.setConsignAmt((dto.getConsignAmt() == null ? BigDecimal.ZERO : dto.getConsignAmt()).setScale(4,
//				BigDecimal.ROUND_HALF_UP));
//		gendto.setCreateDept(dto.getCreateDept());
//		gendto.setCreateTime(dto.getCreateTime());
//		gendto.setCreateUser(dto.getCreateUser());
//		gendto.setDataDateEnd(dto.getDataDateEnd());
//		gendto.setDataDateStart(dto.getDataDateStart());
//		gendto.setDeallist(dto.getDeallist());
//		gendto.setDeptCode(dto.getDeptCode());
//		gendto.setFprodId(dto.getFprodId());
//		gendto.setIsConsign(dto.getIsConsign());
//		gendto.setIssuePartyId(dto.getIssuePartyId());
//		gendto.setIssuingStatus(dto.getIssuingStatus());
//		gendto.setLegalTotal(dto.getLegalTotal() == null ? Integer.valueOf(0) : dto.getLegalTotal());
//		gendto.setOrgCode(dto.getOrgCode());
//		gendto.setPoslist(dto.getPoslist());
//		gendto.setProdRegCode(dto.getProdRegCode());
//		gendto.setProdRegistCode(dto.getProdRegistCode());
//		gendto.setRaiseEnddate(dto.getRaiseEnddate());
//		gendto.setRaiseQty((dto.getRaiseQty() == null ? BigDecimal.ZERO : dto.getRaiseQty()).setScale(4,
//				BigDecimal.ROUND_HALF_UP));
//		gendto.setRegCode(dto.getRegCode());
//		gendto.setRegistSeqno(dto.getRegistSeqno());
//		gendto.setRegseq(dto.getRegseq());
//		gendto.setRemark(dto.getRemark());
//		gendto.setSaleAreaAmt(dto.getSaleAreaAmt());
//		gendto.setSaletarget(dto.getSaletarget());
//		gendto.setSendStatus(dto.getSendStatus());
//		gendto.setSingleTotal(dto.getSingleTotal() == null ? Integer.valueOf(0) : dto.getSingleTotal());
//		gendto.setStatus(dto.getStatus());
//		gendto.setStatusDate(dto.getStatusDate());
//		gendto.setSubmitDateEnd(dto.getSubmitDateEnd());
//		gendto.setSubmitDateStart(dto.getSubmitDateStart());
//		gendto.setSubmitDeadline(dto.getSubmitDeadline());
//		gendto.setSubmitendDateEnd(dto.getSubmitendDateEnd());
//		gendto.setSubmitendDateStart(dto.getSubmitendDateStart());
//		gendto.setSubmitFeedbackStatus(dto.getSubmitFeedbackStatus());
//		gendto.setSubmitTime(dto.getSubmitTime());
//		gendto.setSubscribeCcyAmt(dto.getSubscribeCcyAmt());
//		gendto.setTotalAmt((dto.getTotalAmt() == null ? BigDecimal.ZERO : dto.getTotalAmt()).setScale(4,
//				BigDecimal.ROUND_HALF_UP));
//		gendto.setUnlegalTotal(dto.getUnlegalTotal() == null ? Integer.valueOf(0) : dto.getUnlegalTotal());
//		gendto.setUnRegseq(dto.getRegseq());
//		gendto.setUpdateTime(dto.getUpdateTime());
//		gendto.setUpdateUser(dto.getUpdateUser());
		
		gendto.setSingleTotal(dto.getSingleTotal());
		gendto.setLegalTotal(dto.getLegalTotal());
		gendto.setUnlegalTotal(dto.getUnlegalTotal());
		gendto.setSubscribeCcyAmt(dto.getSubscribeCcyAmt());
		gendto.setSaleAreaAmt(dto.getSaleAreaAmt());
		gendto.setTotalAmt((dto.getTotalAmt()==null?BigDecimal.ZERO:dto.getTotalAmt()).setScale(2, BigDecimal.ROUND_HALF_UP));
		gendto.setRaiseQty((dto.getRaiseQty()==null?BigDecimal.ZERO:dto.getRaiseQty()).setScale(2, BigDecimal.ROUND_HALF_UP));
		gendto.setIsConsign(dto.getIsConsign());
		gendto.setConsignAmt((dto.getConsignAmt()==null?BigDecimal.ZERO:dto.getConsignAmt()).setScale(2, BigDecimal.ROUND_HALF_UP));

		return gendto;
	}

	/**
	 * 功能:带提交的方式单条处理数据结果，入库
	 * 
	 * @since 1.6.0
	 * @version 1.6.0
	 * @author：weiyuqiang 2019年3月7日
	 * @param deleteDataList
	 * @param insertDataList
	 *            void
	 */
	private void dealResultSubCommitSingle(List<RaiseTotalRegist001Dto> deleteDataList,
			List<RaiseTotalRegist001Dto> insertDataList) {
		if (CollectionUtils.isNotEmpty(deleteDataList)) {
			int batchCount = Integer.parseInt(null == SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT)
					? "50" : SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT));// 每批commit的个数
			int batchLastIndex = batchCount;// 每批最后一个的下标
			for (int index = 0; index < deleteDataList.size();) {
				if (batchLastIndex > deleteDataList.size() - 1) {
					batchLastIndex = deleteDataList.size();
					raiseTotalRegist001Mapper.deleteByProdRegCode(deleteDataList.subList(index, batchLastIndex));
					break;// 数据插入完毕,退出循环
				} else {
					raiseTotalRegist001Mapper.deleteByProdRegCode(deleteDataList.subList(index, batchLastIndex));
					index = batchLastIndex;// 设置下一批下标
					batchLastIndex = index + batchCount;
				}
			}
		}
		if (CollectionUtils.isNotEmpty(insertDataList)) {
			int count = 0;
			SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
			try {
				RaiseTotalRegist001Mapper batchMapper = session.getMapper(RaiseTotalRegist001Mapper.class);
				for (RaiseTotalRegist001Dto asset : insertDataList) {
					batchMapper.insert(asset);
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
}
