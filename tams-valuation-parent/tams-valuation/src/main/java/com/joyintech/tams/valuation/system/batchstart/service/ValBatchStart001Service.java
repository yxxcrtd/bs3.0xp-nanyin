package com.joyintech.tams.valuation.system.batchstart.service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.ISelect;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.dao.SysBatchGrpMapper;
import com.joyintech.framework.base.dao.SysBatchPrdGrpMapper;
import com.joyintech.framework.base.dao.SysBatchTaskMapper;
import com.joyintech.framework.base.dao.SysBatchgrpExecInfoMapper;
import com.joyintech.framework.base.dto.Product;
import com.joyintech.framework.base.entity.SysBatchGrp;
import com.joyintech.framework.base.entity.SysBatchGrpPrdlink;
import com.joyintech.framework.base.entity.SysBatchJob;
import com.joyintech.framework.base.entity.SysBatchPrdGrp;
import com.joyintech.framework.base.entity.SysBatchTask;
import com.joyintech.framework.base.entity.SysBatchgrpExecInfo;
import com.joyintech.framework.base.service.ProductService;
import com.joyintech.framework.constant.BatchDictCodeList;
import com.joyintech.framework.constant.BusinessMode;
import com.joyintech.framework.constant.DictCodeList;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DynamicDataSourceHolder;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.system.batchstart.dao.ValBatchStart001Mapper;
import com.joyintech.tams.valuation.system.batchstart.dto.BatchGrpPri;
import com.joyintech.tams.valuation.system.batchstart.dto.DimensionValue;
//import com.joyintech.tools.mq.MessageQueueManager;
import com.joyintech.tools.mq.constant.OperationType;
import com.joyintech.tools.mq.constant.SystemList;
//import com.joyintech.tools.mq.entity.PushMessageDto;

@Service
public class ValBatchStart001Service extends BaseService<SysBatchTask> {

	@Autowired
	private ValBatchStart001Mapper valBatchStart001Mapper;
	@Autowired
	private SysBatchTaskMapper sysBatchTaskMapper;

	@Autowired
	private SysBatchGrpMapper batchGroupMapper;

	@Autowired
	private SysBatchgrpExecInfoMapper batchExecuteDetailMapper;

	@Autowired
	private SysBatchPrdGrpMapper batchProductGroupMapper;

//	@Autowired
//	private MessageQueueManager messageQueueManager;

	@Autowired(required = false)
	private ProductService productService;

	@Autowired
	private ValBatchStart001MastService valBatchStart001MastService;

	public static final String BATCH_DISPATCH = "100";

	public static final String BATCH_UNISTART = "999999";

	public static final String BATCH_UNISTART_WITHOUT_DATECHANGE = "999998";
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(ValBatchStart001Service.class);

	/**
	 * 是否激活activemq
	 */
	@Value("${spring.activemq.active:true}")
	private Boolean isActivemqActive;

	public Page<SysBatchTask> findAllTask(Map<String, Object> pageParam) throws Exception {
		return super.queryForPage(new ISelect() {

			@Override
			public void doSelect() {
				DynamicDataSourceHolder.putDataSource(DataSourceType.OTHER);
				try {
					valBatchStart001Mapper.findAllTask(pageParam);
				} catch (Exception e) {
					logger.error("", e);
				} finally {
					DynamicDataSourceHolder.clearDataSource();
				}

			}
		});
	}

	/**
	 * 执行批处理
	 * <p>
	 * selectedBatchGroup
	 * 批处理组seqNo,batchGroupType为BATCH_EXECUTE_TYPE_SINGLE时是任务taskId
	 * selectedProductGroup
	 * 产品组seqNo,prodGroupType为BATCH_EXECUTE_TYPE_SINGLE时是产品prodId startDate
	 * 执行开始日 endDate 执行结束日 batchGroupType 任务组模式 prodGroupType 产品组模式
	 *
	 * @return 启动结果
	 */
	public Boolean executeBatch() {
		String selectedBatchGroup = RequestUtil.getParam("selectedBatchGroup");
		String selectedProductGroup = RequestUtil.getParam("selectedProductGroup");
		String startDate = RequestUtil.getParam("startDate");
		String endDate = RequestUtil.getParam("endDate");
		String batchGroupType = RequestUtil.getParam("batchGroupType");
		String prodGroupType = RequestUtil.getParam("prodGroupType");
		String batchGroupSeqNo = selectedBatchGroup;
		String nowString = null;
		List<String> tasks = null;
		String systemCode = null;
		if (GlobalConstant.BATCH_EXECUTE_TYPE_SINGLE.equals(batchGroupType)) {
			tasks = new ArrayList<>(Arrays.asList(selectedBatchGroup.split(",")));
			systemCode = getSystemCode(tasks.get(0));
			batchGroupSeqNo = IDGenerator.generate();
			SysBatchGrp batchGroup = new SysBatchGrp();
			// 自动创建标识
			batchGroup.setSystemCode(systemCode);
			nowString = DateUtil.getCurrentDateTime();
			batchGroup.setSeqNo(batchGroupSeqNo);
			batchGroup.setBatchName(SecurityUtil.getLoginUser().getRealName() + "创建于" + nowString);
			batchGroup.setDetail(batchGroup.getBatchName() + ";启动批处理时创建。");
			insertGroup(batchGroup);

		} else {
			String[] batchGroupSeqNos = StringUtil.split(selectedBatchGroup, ",");
			tasks = selectGroupBySeqNo(batchGroupSeqNos);
			systemCode = getSystemCode(tasks.get(0));
		}

		String prodGroupSeqNo = selectedProductGroup;

		List<String> prodList = null;
		if (StringUtil.isNotEmpty(prodGroupSeqNo) && GlobalConstant.BATCH_EXECUTE_TYPE_SINGLE.equals(prodGroupType)) {
			prodGroupSeqNo = IDGenerator.generate();
			SysBatchPrdGrp productGroup = new SysBatchPrdGrp();
			// 自动创建标识
			productGroup.setSystemCode(systemCode);
			productGroup.setSeqNo(prodGroupSeqNo);
			productGroup.setGroupName(SecurityUtil.getLoginUser().getRealName() + "创建于"
					+ (nowString == null ? DateUtil.getCurrentDateTime() : nowString));
			productGroup.setDetail(productGroup.getGroupName() + ";启动批处理时创建。");
			String[] prodGroupSeqNos = StringUtil.split(selectedProductGroup, ",");
			List<Product> productList = new ArrayList<Product>();
			if (productService != null) {
				productList = productService.findProductByIds(prodGroupSeqNos);
			}
			Map<String, Product> productMap = productList.parallelStream()
					.collect(Collectors.toMap(Product::getProdId, product -> product));
			List<SysBatchGrpPrdlink> products = new ArrayList<>();
			for (String prodId : prodGroupSeqNos) {
				SysBatchGrpPrdlink groupProducts = new SysBatchGrpPrdlink();
				groupProducts.setProductId(prodId);
				groupProducts.setGroupSeq(prodGroupSeqNo);
				groupProducts.setProductName(productMap.get(prodId).getProdName());
				products.add(groupProducts);
			}
			insertProdGroup(productGroup, products);

			prodList = Arrays.asList(selectedProductGroup.split(","));
		} else if (StringUtil.isNotEmpty(prodGroupSeqNo)
				&& GlobalConstant.BATCH_EXECUTE_TYPE_GROUP.equals(prodGroupType)) {
			String[] prodGroupSeqNos = StringUtil.split(selectedProductGroup, ",");
			prodList = findAllProdbyGroup(prodGroupSeqNos);

		}

		// BS-890 【 批处理启动_估值】执行产品，未选择产品，不应给其他机构批跑了 add by lizhi 20200712 start
		if (StringUtil.isEmpty(prodGroupSeqNo) && GlobalConstant.BATCH_EXECUTE_TYPE_SINGLE.equals(prodGroupType)
				&& DictCodeList.权限模式_联社模式.getValue().equals(CacheUtil.getSysParam(GlobalConstant.AUTHORITY_TYPE))
				&& StringUtil.equals(DictCodeList.是否_是.getValue(), CacheUtil.getSysParam(GlobalConstant.DATA_AUTHORITY))) {
			//当前用户所属机构代码
	        String createInst =  MultilevelDataAuthorityUtil.getService().queryOrganizationCode(LoginUserUtil.getUserName());
	        if(StringUtil.isNotEmpty(createInst)){
	        	// 查询登录用户所属机构的所有产品
				List<com.joyintech.tams.valuation.system.batchstart.dto.Product> productList =
						valBatchStart001Mapper.findAllProdByInstitutionCode(createInst);
				if(null != productList && productList.size() > 0){
					prodList = new ArrayList<String>();
					for(com.joyintech.tams.valuation.system.batchstart.dto.Product product : productList){
						prodList.add(product.getProdId());
					}
				}
	        }

			if(StringUtil.isNotEmpty(prodList)){
				selectedProductGroup = StringUtil.joinString(prodList, ",");
				prodGroupSeqNo = IDGenerator.generate();
				SysBatchPrdGrp productGroup = new SysBatchPrdGrp();
				// 自动创建标识
				productGroup.setSystemCode(systemCode);
				productGroup.setSeqNo(prodGroupSeqNo);
				productGroup.setGroupName(SecurityUtil.getLoginUser().getRealName() + "创建于"
						+ (nowString == null ? DateUtil.getCurrentDateTime() : nowString));
				productGroup.setDetail(productGroup.getGroupName() + ";启动批处理时创建。");
				String[] prodGroupSeqNos = StringUtil.split(selectedProductGroup, ",");
				List<Product> productList = new ArrayList<Product>();
				if (productService != null) {
					productList = productService.findProductByIds(prodGroupSeqNos);
				}
				Map<String, Product> productMap = productList.parallelStream()
						.collect(Collectors.toMap(Product::getProdId, product -> product));
				List<SysBatchGrpPrdlink> products = new ArrayList<>();
				for (String prodId : prodGroupSeqNos) {
					SysBatchGrpPrdlink groupProducts = new SysBatchGrpPrdlink();
					groupProducts.setProductId(prodId);
					groupProducts.setGroupSeq(prodGroupSeqNo);
					groupProducts.setProductName(productMap.get(prodId).getProdName());
					products.add(groupProducts);
				}
				insertProdGroup(productGroup, products);
			}
		}
		// BS-890 【 批处理启动_估值】执行产品，未选择产品，不应给其他机构批跑了 add by lizhi 20200712 end

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date start = null;
		Date end = null;
		try {
			start = StringUtils.isEmpty(startDate) ? null : format.parse(startDate);
			end = StringUtils.isEmpty(endDate) ? null : format.parse(endDate);
		} catch (ParseException e) {
			logger.error("", e);
		}
		Map<String, String> dateMap = new HashMap<>();
		dateMap.put("startDate", startDate);
		dateMap.put("endDate", endDate);
		// 是否走调度
		boolean doBatchDispatch = false;
		String doDateChange = com.joyintech.tams.constant.DictCodeList.是否_是.getValue();
		if (GlobalConstant.BATCH_EXECUTE_TYPE_SINGLE.equals(batchGroupType) && (
			BATCH_DISPATCH.equals(selectedBatchGroup) || BATCH_UNISTART.equals(selectedBatchGroup))) {
			// 如果选择单批【调度批处理】或【统一启动】则走调度逻辑
			doBatchDispatch = true;
		}
		else if (GlobalConstant.BATCH_EXECUTE_TYPE_SINGLE.equals(batchGroupType)
				 && BATCH_UNISTART_WITHOUT_DATECHANGE.equals(selectedBatchGroup)) {
			// 如果选择单批【统一启动_不日切】则走调度逻辑，且日切标识设为否
			doBatchDispatch = true;
			doDateChange = com.joyintech.tams.constant.DictCodeList.是否_否.getValue();;
		}
		else if (GlobalConstant.BATCH_EXECUTE_TYPE_GROUP.equals(batchGroupType) && tasks != null && tasks.size() == 1
				 && (BATCH_DISPATCH.equals(tasks.get(0)) || BATCH_UNISTART.equals(tasks.get(0)))) {
			// 如果任务组只包含【调度批处理】或【统一启动】则走调度逻辑
			doBatchDispatch = true;
		}
		// 如果走调度，则更新账套日期
		SysBatchgrpExecInfo executeDetail = new SysBatchgrpExecInfo();
		if (doBatchDispatch) {
			executeDetail = valBatchStart001MastService.getSysBatchgrpExecInfo(start, end, prodList, dateMap);
		}
		startDate = dateMap.get("startDate");
		endDate = dateMap.get("endDate");
		String detailSeqNo = IDGenerator.generate();
		// 任务实例
		List<SysBatchJob> insertJobInstList = new ArrayList<>();
		// 任务组优先级
		List<BatchGrpPri> insertGrpPriList = new ArrayList<>();
		// 维度值
		List<DimensionValue> insertDimValueList = new ArrayList<>();
		// 日期维度列表
		List<Date> dates = new ArrayList<>();
		// 如果走调度逻辑，则日期由调度处理，调度批只执行一次, 否则根据日期递增执行
		if (doBatchDispatch) {
			dates.add(start);
		} else {
			for (Date date = start; !date.after(end); date = DateUtil.addDay(date, 1)) {
				dates.add(date);
			}
		}
		for (Date exeDate : dates) {
			// 任务实例
			List<SysBatchJob> jobInstList = new ArrayList<>();
			String batchGroupSeqNoInst = IDGenerator.generate();
			for (String taskId : tasks) {
				SysBatchJob job = new SysBatchJob();
				job.setBatchSeqNo(batchGroupSeqNoInst);
				job.setTaskId(taskId);
				jobInstList.add(job);
			}
			insertJobInstList.addAll(jobInstList);
			// 任务组优先级
			BatchGrpPri batchGrpPri = new BatchGrpPri();
			batchGrpPri.setGroupSeqNo(batchGroupSeqNoInst);
			batchGrpPri.setDetailSeqNo(detailSeqNo);
			batchGrpPri.setPri(BigDecimal.ZERO);
			batchGrpPri.setExecuteDate(exeDate);
			insertGrpPriList.add(batchGrpPri);
			// 维度值
			DimensionValue dimensionValue = new DimensionValue();
			dimensionValue.setDimensionSeqNo("1");
			dimensionValue.setGroupInsSeqNo(batchGroupSeqNoInst);
			dimensionValue.setDimensionValue(DateUtil.format(exeDate));
			insertDimValueList.add(dimensionValue);
		}
		// 维度批处理必备参数
		insertDimensionTask(insertJobInstList, insertGrpPriList, insertDimValueList);

		executeDetail.setSystemCode(systemCode);
		executeDetail.setSeqNo(detailSeqNo);
		executeDetail.setBatchSeqNo(batchGroupSeqNo);
		executeDetail.setPrdGroupSeqNo(prodGroupSeqNo);
		executeDetail.setStartDate(DateUtil.parse(startDate));
		executeDetail.setEndDate(DateUtil.parse(endDate));
		executeDetail.setStatus(BatchDictCodeList.批处理执行状态_未执行.getValue());
		executeDetail.setCreateUser(SecurityUtil.getLoginUser().getUserName());
		executeDetail.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
		executeDetail.setDetailName(StringUtil.isBlank(RequestUtil.getParam("detailName"))
				? (SecurityUtil.getLoginUser().getRealName() + "创建于" + DateUtil.getCurrentDateTime())
				: RequestUtil.getParam("detailName"));
		// 是否日切
		executeDetail.setOtherParam1(doDateChange);
		// 估值分组
		executeDetail.setOtherParam3(executeDetail.getSeqNo());
		// 批处理任务组执行信息
		insertBatchExecuteDetail(executeDetail);

		// 添加是否激活activemq的判断 added by qiangchengxi 2018年5月10日(星期四)
		if (isActivemqActive == null || isActivemqActive) {
			return executeBatch(executeDetail.getSeqNo());
		} else {
			return true;
		}
	}
	private String getSystemCode(String taskId) {
		DynamicDataSourceHolder.putDataSource(DataSourceType.OTHER);
		SysBatchTask task = sysBatchTaskMapper.selectByPrimaryKey(taskId);
		return task.getSystemCode();
	}

	private List<String> selectGroupBySeqNo(String[] batchGroupSeqNos) {
		DynamicDataSourceHolder.putDataSource(DataSourceType.OTHER);
		List<String> tasks = new ArrayList<>();
		try {
			tasks = valBatchStart001Mapper.findAllTaskbyGroup(batchGroupSeqNos);
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			DynamicDataSourceHolder.clearDataSource();
		}
		return tasks;
	}

	public Boolean executeBatch(String detailSeqNo) {
		boolean flag = true;
		DynamicDataSourceHolder.putDataSource(DataSourceType.OTHER);
		valBatchStart001Mapper.updateExeDetailToUnr(detailSeqNo, SecurityUtil.getLoginUser().getUserName());
		valBatchStart001Mapper.deleteAllBatchInfo(detailSeqNo);
//		if (isActivemqActive == null || isActivemqActive) {
//			PushMessageDto messageDto = new PushMessageDto();
//			messageDto.setMessageId(IDGenerator.generate());
//			messageDto.setDestSystem(SystemList.估值核算_批处理.getValue());
//			messageDto.setBusinessEntity(detailSeqNo);
//			messageDto.setBusinessMode(BusinessMode.批处理启动.getModeCode());
//			messageDto.setOperationType(OperationType.插入.getValue());
//			DynamicDataSourceHolder.clearDataSource();
//			flag = messageQueueManager.sendMessage(messageDto);
//		}
		return flag;
	}

	private void insertGroup(SysBatchGrp batchGroup) {
		DynamicDataSourceHolder.putDataSource(DataSourceType.OTHER);
		try {
			batchGroupMapper.insert(batchGroup);
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			DynamicDataSourceHolder.clearDataSource();
		}
	}

	private void insertDimensionTask(List<SysBatchJob> jobInstList, List<BatchGrpPri> batchGrpPriList,
			List<DimensionValue> dimValueList) {
		DynamicDataSourceHolder.putDataSource(DataSourceType.OTHER);
		try {
			valBatchStart001Mapper.insertBatchJob(jobInstList);
			valBatchStart001Mapper.insertBatchGrpPri(batchGrpPriList);
			valBatchStart001Mapper.insertDimValue(dimValueList);
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			DynamicDataSourceHolder.clearDataSource();
		}
	}

	private void insertProdGroup(SysBatchPrdGrp productGroup, List<SysBatchGrpPrdlink> products) {
		DynamicDataSourceHolder.putDataSource(DataSourceType.OTHER);
		try {
			batchProductGroupMapper.insert(productGroup);
			valBatchStart001Mapper.batchInsertProdGroup(products);
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			DynamicDataSourceHolder.clearDataSource();
		}
	}

	private List<String> findAllProdbyGroup(String[] prodGroupSeqNos) {
		DynamicDataSourceHolder.putDataSource(DataSourceType.OTHER);
		List<String> list = new ArrayList<>();
		try {
			list = valBatchStart001Mapper.findAllProdbyGroup(prodGroupSeqNos);
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			DynamicDataSourceHolder.clearDataSource();
		}
		return list;
	}

	private void insertBatchExecuteDetail(SysBatchgrpExecInfo executeDetail) {
		DynamicDataSourceHolder.putDataSource(DataSourceType.OTHER);
		try {
			batchExecuteDetailMapper.insert(executeDetail);
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			DynamicDataSourceHolder.clearDataSource();
		}
	}

}
