package com.joyintech.tams.svreport.chbhz.inPrdDeclareSub.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.chbreport.codecheck.service.CommonCodeCheckTmpBatchService;
import com.joyintech.tams.common.FileImpService;
import com.joyintech.tams.common.InterfFile;
import com.joyintech.tams.common.base.dao.BaseMapper;
import com.joyintech.tams.common.base.entity.ChbPrdBaseinfo;
import com.joyintech.tams.common.base.entity.ChbPrdDeclareSubTmp;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.util.DeadlineUtil;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.svreport.chb.perprodeclarereg.dao.PerProDeclareReg001Mapper;
import com.joyintech.tams.svreport.chb.perprodeclarereg.dto.PerProDeclareRegDto;
import com.joyintech.tams.svreport.chbhz.inPrdDeclareSub.dao.InprodDeclareInfoSvrMapper;
import com.joyintech.tams.svreport.interf.chb.prodinfo.dao.ChbPrdBaseinfoInterfMapper;
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
public class InProdDeclareInfoBatchService extends FileImpService {

	/**
	 * 申报接口临时表名
	 */
	private String tableName = "CHB_PRD_DECLARE_SUB_TMP";

	/**
	 * 重复性检查字段
	 */
	private static final String[] DUPLICATIED_LIST = { "PROD_INS_ID" };

	/**
	 * 业务主键
	 */
	private static final String BUSSINESSKEY = "PROD_INS_ID";
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());

	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	private CommonCodeCheckTmpBatchService commonCodeCheckTmpBatchService;

	@Autowired
	private InprodDeclareInfoSvrMapper inprodDeclareInfoSvrMapper;

	@Autowired
	private SvrMaptoEntityUtil svrMaptoEntityUtil;

	@Autowired
	private PerProDeclareReg001Mapper perProDeclareReg001Mapper;

	@Autowired
	private SqlSessionTemplate template;
	/**
	 * 中债基础数据mapper
	 */
	@Autowired
	private BaseMapper baseMapper;
	
	/**
	 * 数据批量一次性insert条数(单条数据字段较多)
	 */
	@Value("${application.reportInsertLongCount}")
	private int reportInsertLongCount;

	/**
	 * @param
	 * @see
	 * @since 1.0
	 */
	public void generateDeclareInfoData(Date batchDate, List<String> prodIdList) {
		logger.debug("产品申报信息登记生成批处理处理开始------" + df.format(new Date()));
		// 1、取得业务数据 新建list<接口表DTO>
		List<ChbPrdDeclareSubTmp> listTmp = inprodDeclareInfoSvrMapper
				.genChbPrdDeclareSubTmpList(DateUtil.format(batchDate), prodIdList);

		// 2 、校验、生成错误数据、正确数据的数据结构
		// 查询数据库表字段
		List<String> listCloumn = baseMapper.getTableColumnList(tableName);
		// 2.1调用共通 将list<接口表DTO>转换成List<Map<Key, Object>>格式
		List<Map<String, String>> listMap = svrMaptoEntityUtil.convertEntityToMap(listTmp, listCloumn);
		// 2.2调用FileImpService.checkData方法
		checkData(true,listMap, tableName, tableName, tableName, BUSSINESSKEY, DUPLICATIED_LIST);
        
		/**start    配置无法实现，代码实现的check   add by  dongxiangyang 20201009*/
		commonCodeCheckTmpBatchService.codeCheckTmpCommon(listMap, tableName,BUSSINESSKEY);
		/**end       配置无法实现，代码实现的check   add by  dongxiangyang 20201009*/
		// 3、正确数据处理
		// 3.1 对[2]清理掉错误数据的List，调用以下共通，进行数据类型转化处理。将检查后的listMap 转为List<业务表登记表Dto>
		List<PerProDeclareRegDto> chbPrdDeclareSubList = svrMaptoEntityUtil.convertMapToEntity(listMap, listCloumn,
				PerProDeclareRegDto.class);

		// 拼接查询主keylist
		List<String> keylist = chbPrdDeclareSubList.stream().map(PerProDeclareRegDto::getProdInsId)
				.collect(Collectors.toList());

		// 3.2.1查询已经在登记表中存在的数据
		// 定义已经在登记表存在的数据
		List<PerProDeclareRegDto> chbPrdDeclareSubListin = new ArrayList<>();
		chbPrdDeclareSubListin = inprodDeclareInfoSvrMapper.genChbPrdDeclareSubList(keylist);

		// 3.2.2 将已存在报送中的数据放入map中
		Map<String, PerProDeclareRegDto> mapSub = chbPrdDeclareSubListin.stream().collect(
				Collectors.toMap(PerProDeclareRegDto::getProdInsId, perProDeclareRegDto -> perProDeclareRegDto, (v1, v2) -> v1));

		// 报送时点
		List<Date> declare = null;
		Map<String, List<Date>> declaredateMap = new HashMap<String, List<Date>>();
		Map<String, List<Date>> declaredateMapP = new HashMap<String, List<Date>>();

		// 删除数据list
		List<PerProDeclareRegDto> deleteList = new ArrayList<PerProDeclareRegDto>();
		// 新建数据list
		List<PerProDeclareRegDto> insertList = new ArrayList<PerProDeclareRegDto>();
		// 获取系统参数
		String svrSubmitOnChange = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_SUBMIT_ONCHANGE);

		// ]清理掉错误数据的List进行循环处理
		for (PerProDeclareRegDto sub : chbPrdDeclareSubList) {
			// 产品代码=行内表示码
			sub.setProdId(sub.getProdInsId());
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
            //报送流水号
			sub.setSumitSeq(CommonUtil.getSequenceIndexData(DataIndexSequenceType.中债登记管理));
			// 业务表不存在数据：
			if (!mapSub.containsKey(sub.getProdInsId())) {
				deleteList.add(sub);
				//如果当前数据状不等于删除
				if(!StringUtil.equals(DictCodeListExt2.数据状态_删除.getValue(),sub.getStatus())){
					// 报送状态
					sub.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
					// 数据状态
					sub.setStatus(DictCodeListExt2.报送数据状态_新建.getValue());
					// 新建数据List.add(此条记录）
					insertList.add(sub);
				}
				//数据状态等于删除，则不需要插入
			} else if (mapSub.containsKey(sub.getProdInsId())) {
				// 临时表存在、业务表也存在 的情况下
				PerProDeclareRegDto subYw = mapSub.get(sub.getProdInsId());
				// （业务表中此条记录的.【报送状态】 == “02：接口报送”|| 业务表中此条记录的.【报送状态】 ==
				// “03：手工处理” ）
				if ((StringUtil.equals(DictCodeListExt2.报送状态_接口报送.getValue(), subYw.getSendStatus())
						|| StringUtil.equals(DictCodeListExt2.报送状态_手工处理.getValue(), subYw.getSendStatus()))
						) {
					// 当已经报送的数据状态为删除时
					if (StringUtil.equals(subYw.getStatus(), DictCodeListExt2.报送数据状态_删除.getValue())) {
						//tmp表数据为删除时
						if(StringUtil.equals(sub.getStatus(), DictCodeListExt2.数据状态_删除.getValue())){
							// 删除数据List.add(此条记录）
							 deleteList.add(sub);
						}else{
						// 删除数据List.add(此条记录）
						deleteList.add(sub);
						sub.setStatus(DictCodeListExt2.报送数据状态_新建.getValue());
						// 报送状态
						sub.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
						insertList.add(sub);
						}
					}else{
					// 反馈状态为发送失败和中债退回时候
					if (StringUtil.equals(subYw.getSubmitFeedbackStatus(), DictCodeListExt2.报送反馈状态_中债退回.getValue())
							|| StringUtil.equals(subYw.getSubmitFeedbackStatus(),
									DictCodeListExt2.报送反馈状态_发送失败.getValue())) {
						//tmp表数据为删除时
						if(StringUtil.equals(sub.getStatus(), DictCodeListExt2.数据状态_删除.getValue())){
							//删除未报送
							 deleteList.add(sub);
						}
						//tmp表数据不为删除时
						else{
							// 删除数据List.add(此条记录）删除的是未报送数据
							deleteList.add(sub);
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
					else if (StringUtil.equals(subYw.getSubmitFeedbackStatus(), DictCodeListExt2.报送反馈状态_发送成功.getValue())
							|| StringUtil.equals(subYw.getSubmitFeedbackStatus(),
									DictCodeListExt2.报送反馈状态_中债通过.getValue())
							|| StringUtil.equals(subYw.getSubmitFeedbackStatus(),
									DictCodeListExt2.报送反馈状态_审核文件下载失败.getValue())) {
						//tmp表数据为删除时
						if(StringUtil.equals(sub.getStatus(), DictCodeListExt2.数据状态_删除.getValue())){
							//删除未报送
							deleteList.add(sub);
							if(StringUtil.equals(svrSubmitOnChange, DictCodeListExt1.是否_是.getValue())){
								// 新建 数据状态为删除数据状态，未报送数据
								sub.setStatus(DictCodeListExt2.报送数据状态_删除.getValue());
								// 报送状态
								sub.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
								insertList.add(sub);
							}
						}
						//tmp表数据不为删除时
						else{
							// 删除数据List.add(此条记录）删除的是未报送数据
							deleteList.add(sub);
							// 两条记录的所有申报字段比较，存在不一致的情况下，进行以下处理
							if (!compareRegData(subYw, sub)) {
								if(StringUtil.equals(svrSubmitOnChange, DictCodeListExt1.是否_是.getValue())){
									// 此条记录的【数据状态】= '02:修改'
									sub.setStatus(DictCodeListExt2.报送数据状态_修改.getValue());
									// 报送状态
									sub.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
									insertList.add(sub);
								}
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
				
			}
			// 募集起始日期（从）
			if (sub.getRaisePstrdate() != null) {
				if (DictCodeListExt2.募集方式_中债_公募.getValue().equals(sub.getRaiseType())) {
					declare = DeadlineUtil.getChbEndDate(DictCodeListExt2.报表名称_产品申报登记.getValue(),
							DictCodeListExt2.细分类别_公募.getValue(), sub.getStatus(), declaredateMap,
							sub.getRaisePstrdate(), SVReportGlobalConstant.SUBMIT_TIME);
				} else if (DictCodeListExt2.募集方式_中债_私募.getValue().equals(sub.getRaiseType())) {
					declare = DeadlineUtil.getChbEndDate(DictCodeListExt2.报表名称_产品申报登记.getValue(),
							DictCodeListExt2.细分类别_私募.getValue(), sub.getStatus(), declaredateMapP,
							sub.getRaisePstrdate(), SVReportGlobalConstant.SUBMIT_TIME);
				} else {
					logger.error("产品代码：" + sub.getProdId() + "的募集方式接入错误！");
				}
				if (CollectionUtils.isNotEmpty(declare)) {
					// 中债报送截止日
					sub.setChbSubmitDeadline(declare.get(0));
					// 行内报送截止日
					sub.setSubmitDeadline(declare.get(1));
				}
			}
		}
		logger.debug("产品申报信息登记生成批处理处理结束------" + df.format(new Date()));
		// 进行入库处理
		/**start   产品基本信息反写     add  by  dongxiangyang 20201014*/
		inChbprdBaseInfo(insertList);
		/**end   产品基本信息反写     add  by  dongxiangyang 20201014*/
		logger.debug("产品申报信息登记入库开始------" + df.format(new Date()));
		dealResultSubCommitSingle(deleteList, insertList);
		logger.debug("产品申报信息登记入库："+insertList.size()+"条");
		logger.debug("产品申报信息登记入库结束------" + df.format(new Date()));

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
	public boolean compareRegData(PerProDeclareRegDto dto, PerProDeclareRegDto regdto) {
		PerProDeclareRegDto compare1 = genDeclareDto(dto);
		PerProDeclareRegDto compare2 = genDeclareDto(regdto);
		if (JSON.toJSONString(compare1).equals(JSON.toJSONString(compare2))) {
			return true;
		} else {
			return false;
		}
	}

	private PerProDeclareRegDto genDeclareDto(PerProDeclareRegDto dto) {
		PerProDeclareRegDto gendto = new PerProDeclareRegDto();
		// 产品代码
		gendto.setProdId(dto.getProdId());
		// 产品名称
		gendto.setProdName(dto.getProdName());
		// 行内标识码
		gendto.setProdInsId(dto.getProdInsId());
		// 产品品牌
		gendto.setProdBrand(dto.getProdBrand());
		// 产品期次
		gendto.setProdStage((dto.getProdStage() == null ? BigDecimal.ZERO : dto.getProdStage()).setScale(4,
				BigDecimal.ROUND_HALF_UP));
		// 产品审批人身份证号
		gendto.setApproverId(dto.getApproverId());
		// 产品审批人姓名
		gendto.setApproverName(dto.getApproverName());
		// 产品设计人身份证号
		gendto.setDesignerId(dto.getDesignerId());
		// 产品设计人姓名
		gendto.setDesignerName(dto.getDesignerName());
		// 投资经理身份证号
		gendto.setManagerId(dto.getManagerId());
		// 投资经理姓名
		gendto.setManagerName(dto.getManagerName());
		// 业务联络人姓名
		gendto.setLiaisonName(dto.getLiaisonName());
		// 业务联络人座机
		gendto.setLiaisonTel(dto.getLiaisonTel());
		// 业务联络人手机
		gendto.setLiaisonMobile(dto.getLiaisonMobile());
		// 业务联络人邮箱
		gendto.setLiaisonEmail(dto.getLiaisonEmail());
		// 产品收益类型
		gendto.setProfitType(dto.getProfitType());
		// 产品期限
		gendto.setTermtype(dto.getTermtype());
		// 资金投向地区
		gendto.setInvestArae(dto.getInvestArae());
		// 产品投资国家或地区
		gendto.setInvestCountry(dto.getInvestCountry());
		// 理财业务服务模式
		gendto.setFinanSerModel(dto.getFinanSerModel());
		// 产品运作模式
		gendto.setOperateMode(dto.getOperateMode());
		// 产品资产配置方式
		gendto.setCmmode(dto.getCmmode());
		// 产品管理模式
		gendto.setPmmode(dto.getPmmode());
		// 实际管理人员名称
		gendto.setMname(dto.getMname());
		// 产品定价方式
		gendto.setPricedMode(dto.getPricedMode());
		// 合作模式
		gendto.setContMode(dto.getContMode());
		// 合作机构名称
		gendto.setCooperationName(dto.getCooperationName());
		// 投资资产种类及比例
		gendto.setInvTypeRat(dto.getInvTypeRat());
		// 投资者风险偏好
		gendto.setRiskType(dto.getRiskType());
		// 产品销售区域
		gendto.setSaleArea(dto.getSaleArea());
		// 募集币种
		gendto.setRaiseCcy(dto.getRaiseCcy());
		// 兑付本金币种
		gendto.setCashPrinCcy(dto.getCashPrinCcy());
		// 兑付收益币种
		gendto.setCashProfCcy(dto.getCashProfCcy());
		// 起点销售金额
		gendto.setSaleAmtStr((dto.getSaleAmtStr() == null ? BigDecimal.ZERO : dto.getSaleAmtStr()).setScale(4,
				BigDecimal.ROUND_HALF_UP));
		// 计划募集金额
		gendto.setRaiseAmtPlan((dto.getRaiseAmtPlan() == null ? BigDecimal.ZERO : dto.getRaiseAmtPlan()).setScale(4,
				BigDecimal.ROUND_HALF_UP));
		// 募集起始日期（从）
		gendto.setRaisePstrdate(dto.getRaisePstrdate());
		// 募集起始日期（到）
		gendto.setRaisePenddate(dto.getRaisePenddate());
		// 投资本金到账日
		gendto.setInvestPrinDate(dto.getInvestPrinDate());
		// 投资收益到账日
		gendto.setInvestProfDate(dto.getInvestProfDate());
		// 销售手续费
		gendto.setSaleFeeRate((dto.getSaleFeeRate() == null ? BigDecimal.ZERO : dto.getSaleFeeRate()).setScale(5,
				BigDecimal.ROUND_HALF_UP));
		// 境内托管机构名称
		gendto.setInDepName(dto.getInDepName());
		// 境内托管机构代码
		gendto.setInDepCode(dto.getInDepCode());
		// 境外托管机构国别
		gendto.setOutDepCtry(dto.getOutDepCtry());
		// 境外托管机构名称
		gendto.setOutDepName(dto.getOutDepName());
		// 托管费率
		gendto.setTruFeeRate((dto.getTruFeeRate() == null ? BigDecimal.ZERO : dto.getTruFeeRate()).setScale(5,
				BigDecimal.ROUND_HALF_UP));
		// 产品风险等级
		gendto.setRiskLevel(dto.getRiskLevel());
		// 发型机构提前终止权标识
		gendto.setTermRightFlag(dto.getTermRightFlag());
		// 客户赎回权标识
		gendto.setCusRedeemFlag(dto.getCusRedeemFlag());
		// 产品增信标识
		gendto.setCreditFlag(dto.getCreditFlag());
		// 产品增信机构类型
		gendto.setCreditType(dto.getCreditType());
		// 产品增信形式
		gendto.setCreditMode(dto.getCreditMode());
		// 备注
		gendto.setRemark(dto.getRemark());
		// 募集方式 01公募 02私募
		gendto.setRaiseType(dto.getRaiseType());
		// 投资性质 01固定收益类/02权益类/03混合类/04商品及金融衍生品类
		gendto.setIvstorientation(dto.getIvstorientation());
		// 是否金融同业专属
		gendto.setExcFinSector(dto.getExcFinSector());
		// 是否设置最短持有期限
		gendto.setIsShortTerm(dto.getIsShortTerm());
		// 最短持有期限(天)
		gendto.setShortTerm(dto.getShortTerm());
		// 最短持有期后是否自由赎回
		gendto.setIsFreeRedem(dto.getIsFreeRedem());
		// 是否现金管理类
		gendto.setCashManaCate(dto.getCashManaCate());
		// 业绩比较基准%
		gendto.setYield(
				(dto.getYield() == null ? BigDecimal.ZERO : dto.getYield()).setScale(5, BigDecimal.ROUND_HALF_UP));
		// 投资管理费率%
		gendto.setInvestFeeRate((dto.getInvestFeeRate() == null ? BigDecimal.ZERO : dto.getInvestFeeRate()).setScale(5,
				BigDecimal.ROUND_HALF_UP));
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
	private void dealResultSubCommitSingle(List<PerProDeclareRegDto> deleteDataList,
			List<PerProDeclareRegDto> insertDataList) {
		if (CollectionUtils.isNotEmpty(deleteDataList)) {
			int batchCount = Integer.parseInt(null == SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT)
					? "50" : SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT));// 每批commit的个数
			int batchLastIndex = batchCount;// 每批最后一个的下标
			for (int index = 0; index < deleteDataList.size();) {
				if (batchLastIndex > deleteDataList.size() - 1) {
					batchLastIndex = deleteDataList.size();
					perProDeclareReg001Mapper.deleteByProdid(deleteDataList.subList(index, batchLastIndex));
					break;// 数据插入完毕,退出循环
				} else {
					perProDeclareReg001Mapper.deleteByProdid(deleteDataList.subList(index, batchLastIndex));
					index = batchLastIndex;// 设置下一批下标
					batchLastIndex = index + batchCount;
				}
			}
		}
		if (CollectionUtils.isNotEmpty(insertDataList)) {
			int count = 0;
			SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
			try {
				PerProDeclareReg001Mapper batchMapper = session.getMapper(PerProDeclareReg001Mapper.class);
				for (PerProDeclareRegDto asset : insertDataList) {
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
//	/**start    配置无法实现，代码实现的check   add by  dongxiangyang 20201009*/
//	
//	/**
//	 * 代码实现的check
//	 * @param  list 被检查的数据   tableName  检查的表名
//	 */
//	public  void   codeCheck(List<Map<String, String>> list,String   tableName){
//        //错误信息集合
//        List<ChbInterfErrodataWithBLOBs> erroList=new ArrayList<ChbInterfErrodataWithBLOBs>();
//		//循环处理待check数据
//		for(int i=0;i<list.size();i++){
//			Map<String, String> map=list.get(i);
//			//错误检查方法
//			checkPrdoDeclare(map,list);
//		    String  msg=map.get("ERRMSG");
//			//如果代码错误校验有错误信息
//			if(StringUtil.isNotEmpty(msg)){
//				//拼装错误信息
//				ChbInterfErrodataWithBLOBs  errodata=new ChbInterfErrodataWithBLOBs();
//				//主键uuid
//				errodata.setDatauuid(IDGenerator.generate(36));
//				//文件名
//				errodata.setFireName(tableName);
//				//业务主键
//				errodata.setDataid(map.get(BUSSINESSKEY));
//				//接口类型
//				errodata.setInterfType(tableName);
//				//拼接错误字段信息
//				StringBuffer bf=new StringBuffer();
//				for(String key:map.keySet()){
//					 if(key==null){
//		            		continue;
//		            	}
//		                if("ERRMSG".equals(key)){
//		                	continue;
//		                }else{
//		                    bf.append(key+":"+map.get(key)+",");
//		                }
//				 }
//				errodata.setOrgData(bf.toString());
//				//错误提示信息
//				errodata.setErrMsg(msg);
//				//数据状态
//				errodata.setStatus("E");
//				errodata.setCreateUser("system");
//				errodata.setUpdateUser("system");
//				errodata.setCreateTime(new Date());
//				errodata.setUpdateTime(new Date());
//				erroList.add(errodata);
//				list.remove(i);
//			}
//			
//		}
//		//错误信息入库
//	     if(!erroList.isEmpty()){
//		  insertErroData(erroList);
//	     }
//	}
//	
//	/**
//	 * 代码检查产品申报登记
//	 * @param dataMap
//	 */
//	public  void  checkPrdoDeclare(Map<String, String> dataMap,List<Map<String, String>> list){
//		String errMsg = "";
//		//银行代码
//		String hzOrgCode = SvrDataUtil.getSysParam(SVReportGlobalConstant.HZ_ORGCODE);
//		//行内标识码
//		String   prodInsId=dataMap.get(BUSSINESSKEY);
//		//发行机构代码
//		String   issuePartyId=dataMap.get("ISSUE_PARTY_ID");
//		
//		//发行机构代码必须与银行代码相同。
//		if(!StringUtil.equals(hzOrgCode, issuePartyId)){
//			errMsg +="发行机构代码必须与银行代码相同[ISSUE_PARTY_ID="+issuePartyId+"];";
//		}
//		//不同产品之间，产品名称与产品期次不得同时重复
//		List<ChbPrdDeclareSubTmp>  listCheck1=inprodDeclareInfoSvrMapper.genChbPrdDeclareSubTmp1ByProdInsId(prodInsId);
//		if(listCheck1.size()>0){
//			errMsg +="产品期次和产品名称在报文内不可同时重复[PROD_NAME="+dataMap.get("PROD_NAME")+",PROD_STAGE="+dataMap.get("PROD_STAGE")+"];";
//		}
//		
//		//产品名称和产品期次不能同时重复！
//		List<ChbPrdDeclareSubTmp>  listCheck2=inprodDeclareInfoSvrMapper.genChbPrdDeclareSubTmp2ByProdInsId(prodInsId);
//		if(listCheck2.size()>0){
//		   errMsg +="产品名称和产品期次不能同时重复[PROD_NAME="+dataMap.get("PROD_NAME")+",PROD_STAGE="+dataMap.get("PROD_STAGE")+"];";
//		}
//		dataMap.put("ERRMSG", errMsg);
//	}
//	/**end    配置无法实现，代码实现的check   add by  dongxiangyang 20201009*/
	/**
	 * 反写产品基本信息表数据
	 * @param list
	 */
       public   void  inChbprdBaseInfo(List<PerProDeclareRegDto> insertDataList){
    	   //定义产品基本信息表
    	   List<ChbPrdBaseinfo>  insertInfo=new  ArrayList<ChbPrdBaseinfo>();
    	   if (CollectionUtils.isNotEmpty(insertDataList)) {
           for(PerProDeclareRegDto  dto:insertDataList){
        	   //产品基本信息
        	   ChbPrdBaseinfo info=new ChbPrdBaseinfo();
        	   //PRODUUID
        	   info.setProduuid(IDGenerator.generate(36));
        	   //产品代码
        	   info.setProdId(dto.getProdId());
        	   //行内标识码
        	   info.setProdInsId(dto.getProdInsId());
        	   //产品名称
        	   info.setProdName(dto.getProdName());
        	   //募集币种
        	   info.setRaiseCcy(dto.getRaiseCcy());
        	   //兑付本金币种
        	   info.setCashPrinCcy(dto.getCashPrinCcy());
        	   //兑付收益币种
        	   info.setCashProfCcy(dto.getCashProfCcy());
        	   //产品运作模式
        	   info.setOperateMode(dto.getOperateMode());
        	   //机构代码
        	   info.setOrgCode(dto.getOrgCode());
        	   //更新时间
        	   info.setUpdateTime(new Date());
        	   //创建时间
        	   info.setCreateTime(new Date());
        	   //更新人
        	   info.setUpdateUser("system");
               //创建人
        	   info.setCreateUser("system");
        	   //状态
        	   info.setStatus("E");
        	   System.out.println(info.getProduuid());
        	   System.out.println(info.getProdId());
        	   insertInfo.add(info);
           }
    	   }
    	   //产品基本信息集合入库
    	   insertDataMerge(insertInfo);
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
}