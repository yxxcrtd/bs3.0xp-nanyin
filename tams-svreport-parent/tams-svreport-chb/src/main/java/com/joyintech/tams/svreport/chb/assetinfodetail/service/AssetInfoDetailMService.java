package com.joyintech.tams.svreport.chb.assetinfodetail.service;

import com.google.common.collect.Lists;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.JoyinMessage;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.system.base.entity.SysDatadictListLink;
import com.joyintech.tams.common.base.entity.ChbAssetInfo;
import com.joyintech.tams.common.base.entity.ChbInfoMgrResultConfig;
import com.joyintech.tams.common.base.entity.MstCustomerInfoAdd;
import com.joyintech.tams.common.svreport.base.service.BaseCommonCheckService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.dao.ChbInfoMgrResultConfigMapper;
import com.joyintech.tams.svreport.chb.assetinfodetail.asset.DtoChangeUtil;
import com.joyintech.tams.svreport.chb.assetinfodetail.dao.AssetInfoDetailMapper;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.AssetInfoDetailDto;
import com.joyintech.tams.svreport.chb.assetinfodetail.util.AssetInfoDetailUtil;
import com.joyintech.tams.svreport.chb.assetinfodetail.util.ServerMenu;
import com.joyintech.tams.svreport.chb.common.util.asset.RechbAassetInfoUtilService;
import com.joyintech.tams.svreport.interf.chb.assetinfo.service.ChbAssetInfoInterf;
import com.joyintech.tams.svreport.interf.chb.baseinfo.dao.SvrMstCustomerInfoAddImpMapper;
import com.joyintech.tams.svreport.interf.chb.baseinfo.dao.SvrMstCustomerInfoImpMapper;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.apache.commons.collections.CollectionUtils;
import org.aspectj.weaver.ast.Var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 资产要素登记服务
 *
 * @author zhangzhongliang
 * @version 1.6.0 2019年04月24日
 * @see AssetInfoDetailMService
 * @since 1.0
 */
@Service
public class AssetInfoDetailMService extends CommonExportService<AssetInfoDetailDto> implements BaseProcessService<AssetInfoDetailDto> {

	protected static final Logger logger = LoggerFactory.getLogger(AssetInfoDetailMService.class);

	@Autowired
	private AssetInfoDetailMapper assetInfoDetailMapper;

	@Autowired
	private RechbAassetInfoUtilService rechbAassetInfoUtilService;

	@Autowired
	private SvrMstCustomerInfoAddImpMapper svrMstCustomerInfoAddImpMapper;


	@Autowired
	private SvrMstCustomerInfoImpMapper svrMstCustomerInfoImpMapper;

	@Autowired
	private AssetInfoDetailUtil assetInfoDetailUtil;
	@Autowired
	private ChbInfoMgrResultConfigMapper chbInfoMgrResultConfigMapper;
	@Autowired
	private BaseCommonCheckService baseCommonCheckService;

	static Map<String,Set<String>> map = null;

	static Map<String,Set<String>> maptwo = null;
	/**
	 *
	 * 业务画面查询事件
	 * <功能详细描述>
	 * @return
	 * @see
	 * @since 1.0
	 */
	public Page<AssetInfoDetailDto> searchForPage(Map<String, Object> param) {

		//获取当前登录用户的机构编码
		param.put("orgCode", CommonUtil.getTreeInstCode());

		param.put("assetCodes", StringUtil.isNotEmpty(param.get("assetCode"))?(param.get("assetCode").toString().split(",")):null);
		param.put("assetTypes", StringUtil.isNotEmpty(param.get("assetType"))?(param.get("assetType").toString().split(",")):null);
		param.put("assetType2s", StringUtil.isNotEmpty(param.get("assetType2"))?(param.get("assetType2").toString().split(",")):null);
		param.put("orgAssetType1s", StringUtil.isNotEmpty(param.get("orgAssetType1"))?(param.get("orgAssetType1").toString().split(",")):null);
		param.put("orgAssetType2s", StringUtil.isNotEmpty(param.get("orgAssetType2"))?(param.get("orgAssetType2").toString().split(",")):null);

		return queryForPage(Integer.valueOf((String) param.get("page")), Integer.valueOf((String) param.get("length")),
				() -> assetInfoDetailMapper.selectChbInfoByParam(param));
	}

	/**
	 *
	 * 业务画面查询事件
	 * <功能详细描述>
	 * @return
	 * @see
	 * @since 1.0
	 */
	public AssetInfoDetailDto getAssetInfoDetail(String  Assetuuid,String dataStatus) {
		//界面共同方法编写  error表和INFO表
		AssetInfoDetailDto dto = new AssetInfoDetailDto();
		ChbAssetInfo  cinfo=rechbAassetInfoUtilService.BackChbAssetInfoMethod(dataStatus,Assetuuid);
		if(cinfo!=null){
			BeanUtil.copyPropertiesIgnoreNull(dto, cinfo);
		}
		//共同方法无dataStatus数据,需要赋值
		dto.setDataStatus(dataStatus);
		return dto;
	}

	/**
	 * 根据uuid获取资产信息
	 * @param UUid
	 * @return
	 */
	public ChbAssetInfo getChbAssetInfoByUUid(String UUid){
		return assetInfoDetailMapper.getChbAssetInfoByUUid(UUid);
	}

	/**
	 * 资产代码下拉框
	 * @return 产品名称列表
	 */
	public List<SelectOptionDto> queryAssetCodeList(Map<String, Object> map) {
		String orgCode = (String)map.get("orgCode");
		List<String> orgCodeList = StringUtil.splitToList(GlobalConstant.COMMA, orgCode);
		if (StringUtil.isBlank(orgCode)) {
			orgCodeList = CommonUtil.getOrgCodeListForSearch();
		}
		map.put("orgCodeList", orgCodeList);
		List<SelectOptionDto> dtoList = new ArrayList<SelectOptionDto>();
		//在所选的机构list不为空或者是用户为管理员时
		dtoList = assetInfoDetailMapper.selectAssetCodeList(map);
		return dtoList;
	}

	/**
	 * 设置流程业务说明
	 */
	@Override
	public String generateTitle(AssetInfoDetailDto entity, OperationType type) {
		return  "资产代码" + "(" + entity.getAssetCode() + ")" + "-" + "资产名称" + "("
				+ entity.getAssetName() + ")";
	}
	/**
	 * 工作流（留痕操作结束的时候执行的方法）
	 */
	@Override
	public void doComplete(AssetInfoDetailDto dto, OperationType type) {
		dto.setOperateType(type.toString());
		//分别调用各自的setEntity方法
		this.getDtoByPageMessgae(dto);
		//获取市场机构规模数据

		//赋值相关数据
		dto.setIssuePartyId(CommonUtil.selectBankCode());
		if(StringUtil.isEmpty(dto.getAssetUuid())){
			dto.setAssetUuid(IDGenerator.generate(36));
		}

		/***当资产为资管计划或者委外时,需要保存资管计划开始结束日**/
		if(DictCodeListExt2.资产_负债类别_资产管理产品.getValue().equals(dto.getAssetType())
		||DictCodeListExt2.资产_负债类别_委外投资_协议方式.getValue().equals(dto.getAssetType())){
			dto.setPlanSdate(dto.getVdate());
			dto.setPlanEdate(dto.getMdate());
		}
		/***【资产基本信息维护】-新建或修改页面（债券类资产），“具体类别”字段未保存至CHB_ASSET_INFO表**/
		if(DictCodeListExt2.资产_负债类别_债券类资产_理财直接融资工具_同业存单.getValue().equals(dto.getAssetType())){
			if(dto.getSvrBussFinBondRepInfoDto()!=null) {
				dto.setChbSectype(dto.getSvrBussFinBondRepInfoDto().getRepSectype());
			}
		}

		List<MstCustomerInfoAdd> institList = svrMstCustomerInfoAddImpMapper.getAllMstCustomerInfoInstit();
		//获取市场机构规模数据
		Map<String,MstCustomerInfoAdd> orgListMap = institList.stream().collect(
				Collectors.toMap(MstCustomerInfoAdd::getCustomerId,(p)->p,(key1, key2)->key1));

		/*** 基金类资产数据接入会同步保存mngOrgName字段 **/
		if(DictCodeListExt2.资产_负债类别_私募基金.getValue().equals(dto.getAssetType())
			||DictCodeListExt2.资产_负债类别_公募基金.getValue().equals(dto.getAssetType())){
			if(dto.getMngOrgName()!=null) {
				dto.setMngOrgName(null!=orgListMap.get(dto.getChbManagerId())?
						orgListMap.get(dto.getChbManagerId()).getCompanyName():null);
			}
			if(dto.getTruOrgName()!=null) {
				dto.setTruOrgName(null!=orgListMap.get(dto.getChbFinancierId())?
						orgListMap.get(dto.getChbManagerId()).getCompanyName():null);
			}
		}


		//获取市场机构规模数据
		Map<String,String> institMap = institList.stream().collect(HashMap<String,String>::new,
				(map, item)->map.put(item.getCustomerId(), item.getPartyType1()), (m, n)->m.putAll(n));

		//2-5企业债券分行业、企业规模统计的列统计维度调整为根据市场机构管理中“机构类型（规模）”字段来取值。
		ChbAssetInfoInterf.convertInstitSize(dto,institMap);
		restTemplate.postForObject(
				SvrServiceApplication.CHB_SERVICE_NAME + "/AssetInfoDetail/doComplete", dto, Integer.class);
	}


	public void doDelete(AssetInfoDetailDto dto) {

		restTemplate.postForObject(
				SvrServiceApplication.CHB_SERVICE_NAME + "/AssetInfoDetail/doDelete", dto, Integer.class);


	}



	/**
	 * 获取资产及二级类型的关系
	 * @return
	 */
	public Map<String, Set<String>> getDataListLink(){

		if(map==null||map.isEmpty()){
			map = new HashMap<>();
			//界面配置,根据SYS_DATADICT_LIST_LINK
			Map<String,Object> params = new HashMap<>();
			params.put("linkDictCode", DictCode.资产_负债类别.getValue());
//		params.put("linkItemCode", itemCode);
			List<SysDatadictListLink> listLink = assetInfoDetailMapper.getDataListLinkData(params);
			for (SysDatadictListLink link:listLink) {
				if(null!=map.get(link.getLinkedItemCode())){
					map.get(link.getLinkedItemCode()).add(link.getItemCode());
				}else{
					map.put(link.getLinkedItemCode(),new HashSet<>(Arrays.asList(link.getItemCode())));
				}
			}
		}
		return map;
	}


	/**
	 * 获取资产及二级类型的关系
	 * @return
	 */
	public Map<String, Set<String>> getDataListLink(String DictCode){

		if(maptwo==null||maptwo.isEmpty()){
			maptwo = new HashMap<>();
			//界面配置,根据SYS_DATADICT_LIST_LINK
			Map<String,Object> params = new HashMap<>();
			params.put("linkDictCode", DictCode);
//		params.put("linkItemCode", itemCode);
			List<SysDatadictListLink> listLink = assetInfoDetailMapper.getDataListLinkData(params);
			for (SysDatadictListLink link:listLink) {
				if(null!=maptwo.get(link.getLinkedItemCode())){
					maptwo.get(link.getLinkedItemCode()).add(link.getItemCode());
				}else{
					maptwo.put(link.getLinkedItemCode(),new HashSet<>(Arrays.asList(link.getItemCode())));
				}
			}
		}
		return maptwo;
	}

	/**
	 * 共同数据塞入方法
	 * @param dto 获取资产类型数据
	 * @return
	 */
	public AssetInfoDetailDto getDtoByPageMessgae(AssetInfoDetailDto dto)  {
		try {
			//遍历资产类型数据
			for (ServerMenu menu : ServerMenu.getAllAsset()) {
				//判断数据字典中是否有当前资产类型的实体类
				if (getDataListLink().get(menu.getAssetType()).contains(dto.getAssetType2())) {
					//获取实体类中对应名称的资产方法
					PropertyDescriptor pd = new PropertyDescriptor(menu.getServerId(),
							dto.getClass());
					Method getMethod =pd.getReadMethod();
					//获取对应的实体类字段
					Object obj1 = getMethod.invoke(dto);
					//执行数据转换方法
					DtoChangeUtil.SetChbToRep(obj1, dto, menu.getFlagStr(), true);
					//资产类型赋值
					dto.setAssetType(menu.getAssetType());
				}
			}
			return dto;
		}catch (Exception e){
			logger.info("实体转换错误,请检查数据类型",e);
		}finally {
//			return null;
		}
		return null;
	}

	/**
	 * 查询回购交易的金额
	 */
   public BigDecimal  getTargetAmt(Map<String, Object> map){
   	String  assetUUid=(String)map.get("assetuuid");
     return assetInfoDetailMapper.getTargetAmt(assetUUid);
   }

	/**
	 * 根据界面传入数据获取二级资产类型
	 * @param resultMap
	 * @return
	 */
	public List<SelectOptionDto> getAssetTye2(Map<String,Object> resultMap){
		List<SelectOptionDto> resultOpt= assetInfoDetailMapper.getAssetTye2(resultMap);
		return  resultOpt;
	}
	/**
	 * 获取交易对手列表
	 * @param resultMap
	 * @return
	 */
	public List<SelectOptionDto> selectCounterId(Map<String,Object> resultMap){
		List<SelectOptionDto> resultOpt= assetInfoDetailMapper.selectCounterId(resultMap);
		return  resultOpt;
	}



	/**
	 * 设置导出文件参数
	 */
	@Override
	protected void buildExportParam(ExportParam exportParam) {
		exportParam.config(
				"/com/joyintech/tams/svreport/chb/assetinfodetail/conf/export_conf.xml")
				.dataSource(DataSourceType.MASERT);
	}
	/**
	 * 查询要导出的数据
	 */
	@Override
	public Page<AssetInfoDetailDto> selectPageData(Map<String, Object> param) {
		return searchForPage(param);
	}

	public List<AssetInfoDetailDto> setDataList(Map<String, Object> param) {
		List<AssetInfoDetailDto> resultList = new ArrayList<>();
		String keyList = (String) param.get("keyList");
		if (StringUtil.isNotEmpty(keyList)) {
			try {
				resultList = JsonUtils.json2List(keyList, AssetInfoDetailDto.class);
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
				return null;
			}
		} else {
			param.put("orgCode", CommonUtil.getTreeInstCode());
			param.put("assetCodes", StringUtil.isNotEmpty(param.get("assetCode"))?(param.get("assetCode").toString().split(",")):null);
			param.put("assetTypes", StringUtil.isNotEmpty(param.get("assetType"))?(param.get("assetType").toString().split(",")):null);
			param.put("assetType2s", StringUtil.isNotEmpty(param.get("assetType2"))?(param.get("assetType2").toString().split(",")):null);
			param.put("orgAssetType1s", StringUtil.isNotEmpty(param.get("orgAssetType1"))?(param.get("orgAssetType1").toString().split(",")):null);
			param.put("orgAssetType2s", StringUtil.isNotEmpty(param.get("orgAssetType2"))?(param.get("orgAssetType2").toString().split(",")):null);

			resultList = assetInfoDetailMapper.selectChbInfoByParam(param);
		}
		return resultList;
	}
	
	/**
	 * 导入数据文件之前检查数据
	 *
	 * @param fileName 导入的文件名
	 * @return 处理结果
	 */
	public AjaxResult checkConfirmMsgForImportData(String fileName,String tableName) throws Exception {
		AjaxResult webResult = new AjaxResult();
		if (fileName.indexOf("资产要素") == -1){
			webResult.setMessage(this.getMessage("ImportFileName"),MessageType.错误消息);
			return webResult;
		}
		List<Map<String, String>> mapList = dataMapAnalysis(fileName,tableName);
		int size = mapList.size();
		List<com.joyintech.tams.systemtools.dto.ExcelValidDto> checkResult = baseCommonCheckService.checkDataAll("D", mapList, tableName, fileName, tableName, tableName, null, null);

		long count = mapList.stream().filter(ele ->null != ele.get("STATUS")&&ele.get("STATUS").equals("A")).count();
		webResult.setMessage(this.getMessage("ChbImportMessage",mapList.size()-count,size-mapList.size(),count),MessageType.确认消息);
		int duplicate = 0;
		if(!CollectionUtils.isEmpty(mapList)){
			duplicate = assetInfoDetailMapper.selectValidRecords(mapList);
		}
		//查数据库记录
		List<JoyinMessage> joyinMessages = new ArrayList<>();
		JoyinMessage ms = new JoyinMessage(this.getMessage("ChbImportDuplicateMessage",duplicate), MessageType.确认消息);
		joyinMessages.add(ms);
		webResult.setJoyinMessages(joyinMessages);
		return webResult;
	}

	public List<com.joyintech.tams.systemtools.dto.ExcelValidDto> importData(String fileName,String tableName) throws Exception{
		List<Map<String, String>> list = dataMapAnalysis(fileName,tableName);
		List<com.joyintech.tams.systemtools.dto.ExcelValidDto> checkResult = baseCommonCheckService.checkDataAll("D", list, tableName, fileName, tableName, tableName, null, null);
		if(!CollectionUtils.isEmpty(list)){
			List<AssetInfoDetailDto> AssetInfoDetailDtos= converToBean(list);
			AssetInfoDetailDtos.forEach(e-> assetInfoDetailMapper.batchInsert(Lists.newArrayList(e)));
		}
		List<ExcelValidDto> resultList = checkResult.stream().filter(ele -> null != ele.getValidKey() && ele.getValidKey().equals("YW")).collect(Collectors.toList());
		resultList.forEach(e->e.setRowNum(String.valueOf(Integer.valueOf(e.getRowNum())+1)));
		return resultList;

	}

	/**
	 * excel导入数据整理
	 *
	 * @param flieName 文件名称
	 * @return 检验list
	 * @throws Exception 异常
	 * @see
	 * @since 1.0
	 */
	public List<Map<String,String>> dataMapAnalysis(String flieName,String tableName)
			throws Exception {
		String prefix = CacheUtil.getSysParam(GlobalConstant.COM_PROP_UPLOAD_PATH);
		if(!StringUtil.startsWith(flieName, prefix)
				&& !StringUtil.startsWith(flieName, prefix.replace("/", File.separator))) {
			flieName = prefix + File.separator + flieName;
		}
		com.joyintech.framework.util.ExcelUtil excelUtil = com.joyintech.framework.util.ExcelUtil.build(flieName, null);

		List<List<String>> dataList = excelUtil.readToList(0);
		List<String> headList = dataList.get(1);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("tableId", tableName);
		param.put("flag", false);
		List<ChbInfoMgrResultConfig> chbInfoMgrResultConfigList = chbInfoMgrResultConfigMapper.queryAllchbMgrTableInfo(param);
		Map<String,String> map = new HashMap<>();
		chbInfoMgrResultConfigList.forEach(ele->map.put(ele.getColumnName(),ele.getRealColumnId()));


		List<String> columnList = new ArrayList<>();
		for(int i=0;i<headList.size();++i){
			String columnName = headList.get(i);
			columnName= map.get(columnName);
			columnList.add(i,columnName);

		}
		List<Map<String,String>> list = new ArrayList<>();
		for(int i=2;i<dataList.size();++i){
			Map<String,String> subMap = new HashMap<>();
			List<String> subList = dataList.get(i);
			for(int j=0;j<subList.size();++j){
				if(StringUtil.isEmpty(subMap.get(columnList.get(j)))){
					subMap.put(columnList.get(j),subList.get(j));
				}
			}
			subMap.put("ASSET_UUID",IDGenerator.generate(36));
			subMap.put("TRADE_MARKET",StringUtil.isEmpty(subMap.get("TRADE_MARKET"))?null:subMap.get("TRADE_MARKET").substring(0,2));
			subMap.put("BASIS",StringUtil.isEmpty(subMap.get("BASIS"))?null:subMap.get("BASIS").substring(0,2));
			subMap.put("DEPO_TYPE",StringUtil.isEmpty(subMap.get("DEPO_TYPE"))?null:subMap.get("DEPO_TYPE").substring(0,2));
			subMap.put("ISSUE_MODE",StringUtil.isEmpty(subMap.get("ISSUE_MODE"))?null:subMap.get("ISSUE_MODE").substring(0,2));
			subMap.put("SEC_MAIN_GRADE",StringUtil.isEmpty(subMap.get("SEC_MAIN_GRADE"))?null:subMap.get("SEC_MAIN_GRADE").substring(0,2));
			subMap.put("PARTY_TYPE1",StringUtil.isEmpty(subMap.get("PARTY_TYPE1"))?null:subMap.get("PARTY_TYPE1").substring(0,2));
			subMap.put("PARTY_TYPE3",StringUtil.isEmpty(subMap.get("PARTY_TYPE3"))?null:subMap.get("PARTY_TYPE3").substring(0,2));
			subMap.put("PARTY_TYPE2",StringUtil.isEmpty(subMap.get("PARTY_TYPE2"))?null:subMap.get("PARTY_TYPE2").substring(0,2));

			subMap.put("REGIST_ORG",StringUtil.isEmpty(subMap.get("REGIST_ORG"))?null:subMap.get("REGIST_ORG").substring(0,2));
			subMap.put("RIVAL_PARTY_TYPE",StringUtil.isEmpty(subMap.get("RIVAL_PARTY_TYPE"))?null:subMap.get("RIVAL_PARTY_TYPE").substring(0,2));
			subMap.put("TARGET_TYPE1",StringUtil.isEmpty(subMap.get("TARGET_TYPE1"))?null:subMap.get("TARGET_TYPE1").substring(0,2));
			subMap.put("PROFIT_TYPE",StringUtil.isEmpty(subMap.get("PROFIT_TYPE"))?null:subMap.get("PROFIT_TYPE").substring(0,2));
			subMap.put("IS_RESALE",StringUtil.isEmpty(subMap.get("IS_RESALE"))?null:subMap.get("IS_RESALE").substring(0,2));

			subMap.put("RATE_FLAG",StringUtil.isEmpty(subMap.get("RATE_FLAG"))?null:subMap.get("RATE_FLAG").substring(0,2));
			subMap.put("COUPON_SPECIES",StringUtil.isEmpty(subMap.get("COUPON_SPECIES"))?null:subMap.get("COUPON_SPECIES").substring(0,2));
			subMap.put("INTEREST_RULE_FLAG",StringUtil.isEmpty(subMap.get("INTEREST_RULE_FLAG"))?null:subMap.get("INTEREST_RULE_FLAG").substring(0,2));


			subMap.put("INTPAY_RULE",StringUtil.isEmpty(subMap.get("INTPAY_RULE"))?null:subMap.get("INTPAY_RULE").substring(0,2));
			subMap.put("FLOAT_FACTOR_FLAG",StringUtil.isEmpty(subMap.get("FLOAT_FACTOR_FLAG"))?null:subMap.get("FLOAT_FACTOR_FLAG").substring(0,2));
			subMap.put("STRUCT_GRADE",StringUtil.isEmpty(subMap.get("STRUCT_GRADE"))?null:subMap.get("STRUCT_GRADE").substring(0,2));
			subMap.put("REPAYMENT_TYPE",StringUtil.isEmpty(subMap.get("REPAYMENT_TYPE"))?null:subMap.get("REPAYMENT_TYPE").substring(0,2));

			subMap.put("PERIOD_REPAYMENT_FLAG",StringUtil.isEmpty(subMap.get("PERIOD_REPAYMENT_FLAG"))?null:subMap.get("PERIOD_REPAYMENT_FLAG").substring(0,2));
			subMap.put("FINANCIER_TYPE1",StringUtil.isEmpty(subMap.get("FINANCIER_TYPE1"))?null:subMap.get("FINANCIER_TYPE1").substring(0,2));
			subMap.put("FINANCIER_TYPE2",StringUtil.isEmpty(subMap.get("FINANCIER_TYPE2"))?null:subMap.get("FINANCIER_TYPE2").substring(0,2));
			subMap.put("FINANCIER_TYPE3",StringUtil.isEmpty(subMap.get("FINANCIER_TYPE3"))?null:subMap.get("FINANCIER_TYPE3").substring(0,2));

			subMap.put("INDUSTRY_FLAG",StringUtil.isEmpty(subMap.get("INDUSTRY_FLAG"))?null:subMap.get("INDUSTRY_FLAG").substring(0,2));
			subMap.put("GUA_TYPE",StringUtil.isEmpty(subMap.get("GUA_TYPE"))?null:subMap.get("GUA_TYPE").substring(0,2));
			subMap.put("PLEDGE_TYPE",StringUtil.isEmpty(subMap.get("PLEDGE_TYPE"))?null:subMap.get("PLEDGE_TYPE").substring(0,2));

			subMap.put("NATURE_GUARANTEE",StringUtil.isEmpty(subMap.get("NATURE_GUARANTEE"))?null:subMap.get("NATURE_GUARANTEE").substring(0,2));
			subMap.put("RELATION_FINAN",StringUtil.isEmpty(subMap.get("RELATION_FINAN"))?null:subMap.get("RELATION_FINAN").substring(0,2));
			subMap.put("FINANCIER_GRADE",StringUtil.isEmpty(subMap.get("FINANCIER_GRADE"))?null:subMap.get("FINANCIER_GRADE").substring(0,2));

			subMap.put("OUT_GRADE",StringUtil.isEmpty(subMap.get("OUT_GRADE"))?null:subMap.get("OUT_GRADE").substring(0,2));
			subMap.put("CONTAIN_TYPE",StringUtil.isEmpty(subMap.get("CONTAIN_TYPE"))?null:subMap.get("CONTAIN_TYPE").substring(0,2));
			subMap.put("EXERCISE_TYPE",StringUtil.isEmpty(subMap.get("EXERCISE_TYPE"))?null:subMap.get("EXERCISE_TYPE").substring(0,2));
			subMap.put("PERPETUAL_CLAUSE_TYPE",StringUtil.isEmpty(subMap.get("PERPETUAL_CLAUSE_TYPE"))?null:subMap.get("PERPETUAL_CLAUSE_TYPE").substring(0,2));

			subMap.put("DEFERRED_INTEREST_TYPE",StringUtil.isEmpty(subMap.get("DEFERRED_INTEREST_TYPE"))?null:subMap.get("DEFERRED_INTEREST_TYPE").substring(0,2));
			subMap.put("DEFERRED_INTEREST_INT",StringUtil.isEmpty(subMap.get("DEFERRED_INTEREST_INT"))?null:subMap.get("DEFERRED_INTEREST_INT").substring(0,2));
			subMap.put("PART_REDEM_FLAG",StringUtil.isEmpty(subMap.get("PART_REDEM_FLAG"))?null:subMap.get("PART_REDEM_FLAG").substring(0,2));
			subMap.put("IS_RIGHT",StringUtil.isEmpty(subMap.get("IS_RIGHT"))?null:subMap.get("IS_RIGHT").substring(0,2));

			subMap.put("IS_RESALE",StringUtil.isEmpty(subMap.get("IS_RESALE"))?null:subMap.get("IS_RESALE").substring(0,2));
			subMap.put("NOTSTAND_SEC_TYPE",StringUtil.isEmpty(subMap.get("NOTSTAND_SEC_TYPE"))?null:subMap.get("NOTSTAND_SEC_TYPE").substring(0,2));
			subMap.put("IS_BZHPJ",StringUtil.isEmpty(subMap.get("IS_BZHPJ"))?null:subMap.get("IS_BZHPJ").substring(0,2));
			subMap.put("SHARES_TYPE",StringUtil.isEmpty(subMap.get("SHARES_TYPE"))?null:subMap.get("SHARES_TYPE").substring(0,2));

			subMap.put("INVEST_STAGE",StringUtil.isEmpty(subMap.get("INVEST_STAGE"))?null:subMap.get("INVEST_STAGE").substring(0,2));
			subMap.put("SHARES_TYPE1",StringUtil.isEmpty(subMap.get("SHARES_TYPE1"))?null:subMap.get("SHARES_TYPE1").substring(0,2));
			subMap.put("SHARES_TYPE3",StringUtil.isEmpty(subMap.get("SHARES_TYPE3"))?null:subMap.get("SHARES_TYPE3").substring(0,2));
			subMap.put("SHARES_TYPE2",StringUtil.isEmpty(subMap.get("SHARES_TYPE2"))?null:subMap.get("SHARES_TYPE2").substring(0,2));

			subMap.put("IS_PLEDGE",StringUtil.isEmpty(subMap.get("IS_PLEDGE"))?null:subMap.get("IS_PLEDGE").substring(0,2));
			subMap.put("DEPT_EQUITY",StringUtil.isEmpty(subMap.get("DEPT_EQUITY"))?null:subMap.get("DEPT_EQUITY").substring(0,2));
			subMap.put("HOLD_PURPOSE",StringUtil.isEmpty(subMap.get("HOLD_PURPOSE"))?null:subMap.get("HOLD_PURPOSE").substring(0,2));
			subMap.put("FINANCIAL_PRODUCT_FLAG",StringUtil.isEmpty(subMap.get("FINANCIAL_PRODUCT_FLAG"))?null:subMap.get("FINANCIAL_PRODUCT_FLAG").substring(0,2));

			subMap.put("FINANCIAL_COMISSUE",StringUtil.isEmpty(subMap.get("FINANCIAL_COMISSUE"))?null:subMap.get("FINANCIAL_COMISSUE").substring(0,2));
			subMap.put("TRU_ACT_TYPE",StringUtil.isEmpty(subMap.get("TRU_ACT_TYPE"))?null:subMap.get("TRU_ACT_TYPE").substring(0,2));
			subMap.put("PRO_PROPERTY",StringUtil.isEmpty(subMap.get("PRO_PROPERTY"))?null:subMap.get("PRO_PROPERTY").substring(0,2));

			subMap.put("BUY_STRUCT",StringUtil.isEmpty(subMap.get("BUY_STRUCT"))?null:subMap.get("BUY_STRUCT").substring(0,2));
			subMap.put("MANAGE_TYPE",StringUtil.isEmpty(subMap.get("MANAGE_TYPE"))?null:subMap.get("MANAGE_TYPE").substring(0,2));
			subMap.put("IN_ASSET_TYPE",StringUtil.isEmpty(subMap.get("IN_ASSET_TYPE"))?null:subMap.get("IN_ASSET_TYPE").substring(0,2));
			subMap.put("MONIT_TYPE",StringUtil.isEmpty(subMap.get("MONIT_TYPE"))?null:subMap.get("MONIT_TYPE").substring(0,2));

			subMap.put("REGIST_ORG1",StringUtil.isEmpty(subMap.get("REGIST_ORG1"))?null:subMap.get("REGIST_ORG1").substring(0,2));
			subMap.put("PROFIT_FLAG",StringUtil.isEmpty(subMap.get("PROFIT_FLAG"))?null:subMap.get("PROFIT_FLAG").substring(0,2));
			subMap.put("IS_GOVERN_FUND",StringUtil.isEmpty(subMap.get("IS_GOVERN_FUND"))?null:subMap.get("IS_GOVERN_FUND").substring(0,2));
			subMap.put("GOVERN_INVEST_FUND",StringUtil.isEmpty(subMap.get("GOVERN_INVEST_FUND"))?null:subMap.get("GOVERN_INVEST_FUND").substring(0,2));

			subMap.put("IS_DEPOSIT_FLAG",StringUtil.isEmpty(subMap.get("IS_DEPOSIT_FLAG"))?null:subMap.get("IS_DEPOSIT_FLAG").substring(0,2));
			subMap.put("BANK_SEC_ZXZQ",StringUtil.isEmpty(subMap.get("BANK_SEC_ZXZQ"))?null:subMap.get("BANK_SEC_ZXZQ").substring(0,2));
			subMap.put("BONDTOSTOCK_FLAG",StringUtil.isEmpty(subMap.get("BONDTOSTOCK_FLAG"))?null:subMap.get("BONDTOSTOCK_FLAG").substring(0,2));
			subMap.put("PRIVATEFUND_FLAG",StringUtil.isEmpty(subMap.get("PRIVATEFUND_FLAG"))?null:subMap.get("PRIVATEFUND_FLAG").substring(0,2));

			subMap.put("ISUNLISTED",StringUtil.isEmpty(subMap.get("ISUNLISTED"))?null:subMap.get("ISUNLISTED").substring(0,2));
			subMap.put("PRIVATEPRD_FLAG",StringUtil.isEmpty(subMap.get("PRIVATEPRD_FLAG"))?null:subMap.get("PRIVATEPRD_FLAG").substring(0,2));
			subMap.put("CONSTRACTE_BUSI_TYPE",StringUtil.isEmpty(subMap.get("CONSTRACTE_BUSI_TYPE"))?null:subMap.get("CONSTRACTE_BUSI_TYPE").substring(0,2));
			subMap.put("CONSTRACTE_GRADE",StringUtil.isEmpty(subMap.get("CONSTRACTE_GRADE"))?null:subMap.get("CONSTRACTE_GRADE").substring(0,2));
			subMap.put("PRIVATE_FLAG",StringUtil.isEmpty(subMap.get("PRIVATE_FLAG"))?null:subMap.get("PRIVATE_FLAG").substring(0,2));
			subMap.put("IS_BANK_CAP",StringUtil.isEmpty(subMap.get("IS_BANK_CAP"))?null:subMap.get("IS_BANK_CAP").substring(0,2));
			subMap.put("IS_CONSTRACTE_BUSI",StringUtil.isEmpty(subMap.get("IS_CONSTRACTE_BUSI"))?null:subMap.get("IS_CONSTRACTE_BUSI").substring(0,2));
			subMap.put("ASSET_TYPE2",!StringUtil.isEmpty(subMap.get("ASSET_TYPE2"))&&subMap.get("ASSET_TYPE2").length()>3?subMap.get("ASSET_TYPE2").substring(0,4):null);

			list.add(subMap);
		}


		return list;
	}
	public List<AssetInfoDetailDto> converToBean(List<Map<String,String>> dataList)
			throws Exception {
		// 返回的list定义
		List<AssetInfoDetailDto> assetInfoDetailDtoList = new ArrayList<AssetInfoDetailDto>();
		Class<AssetInfoDetailDto> clazz = AssetInfoDetailDto.class;
		Map<String, Method> toMap = methodToMap(clazz);
		Map<String, String> filedToMap = filedToMap(clazz);
		Object var = null;
		for (Map<String, String> map : dataList) {
			Map<String,String> temp = new HashMap<>();
			Set<String> keys = map.keySet();
			for(String key :keys){
				if(null !=key){
					temp.put(key.toLowerCase().replace("_", ""),map.get(key));
				}
			}

			AssetInfoDetailDto assetInfoDetailDto = clazz.newInstance();
				for(Map.Entry e:temp.entrySet()){
					for(Map.Entry el:toMap.entrySet()){
						if(null !=e.getKey()){
							if(((String)el.getKey()).substring(3).equalsIgnoreCase((String) e.getKey())){
								if(null !=filedToMap.get(e.getKey())&&filedToMap.get(e.getKey()).equals(String.class.getName())){
									var =StringUtil.isEmpty((String)e.getValue())?null:(String)e.getValue();
								}else if(null !=filedToMap.get(e.getKey())&&filedToMap.get(e.getKey()).equals(Date.class.getName())){
									var = StringUtil.isEmpty((String)e.getValue())?null:DateUtil.parse((String) e.getValue());
								}else if(null !=filedToMap.get(e.getKey())&&filedToMap.get(e.getKey()).equals(Long.class.getName())){
									var = StringUtil.isEmpty((String)e.getValue())?null:Long.valueOf((String) e.getValue()) ;
								}else if(null !=filedToMap.get(e.getKey())&&filedToMap.get(e.getKey()).equals(Integer.class.getName())){
									var = StringUtil.isEmpty((String)e.getValue())?null:Integer.valueOf((String) e.getValue()) ;
								}else if(null !=filedToMap.get(e.getKey())&&filedToMap.get(e.getKey()).equals(BigDecimal.class.getName())){
									var = StringUtil.isEmpty((String)e.getValue())?null:new BigDecimal((String) e.getValue());
								}
								((Method)el.getValue()).invoke(assetInfoDetailDto,var);
								break;
							}
						}
					}
				}
			assetInfoDetailDto.setErrMsg(null!=map.get("ERRMSG")?map.get("ERRMSG"):"");
			assetInfoDetailDto.setCreateUser(CommonUtil.getUserName());
			assetInfoDetailDto.setCreateDept(CommonUtil.getDeptCode()==null?"":CommonUtil.getDeptCode());
			assetInfoDetailDto.setCreateTime(DateUtil.now());
			assetInfoDetailDto.setUpdateUser(CommonUtil.getUserName());
			assetInfoDetailDto.setUpdateTime(DateUtil.now());
			assetInfoDetailDto.setDataSource(DictCodeListExt2.数据来源_新建导入.getValue());
			assetInfoDetailDto.setStatus(null!=map.get("STATUS") && map.get("STATUS").equals("A")? DictCodeListExt1.维护数据状态_待处理.getValue():DictCodeListExt1.维护数据状态_有效.getValue());
			assetInfoDetailDtoList.add(assetInfoDetailDto);
		}
		return  assetInfoDetailDtoList;
	}
	// 获取类的set方法
	public Map<String,Method> methodToMap(Class clazz){
		return Arrays.stream(clazz.getMethods()).filter(m -> m.getName().startsWith("set")).collect(Collectors.toMap(m -> m.getName(), m -> m));
	}
	// 获取类的属性类型
	public Map<String,String> filedToMap(Class clazz){
		Map<String, String> var1 = Arrays.stream(clazz.getDeclaredFields()).collect(Collectors.toMap(f -> f.getName().toLowerCase(), f -> f.getGenericType().getTypeName()));
		Map<String, String> var2 = Arrays.stream(clazz.getSuperclass().getDeclaredFields()).collect(Collectors.toMap(f -> f.getName().toLowerCase(), f -> f.getGenericType().getTypeName()));
		var2.putAll(var1);
		return var2;
	}
}
