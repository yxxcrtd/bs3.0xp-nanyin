package com.joyintech.tams.svreport.chb.assetinfodetail.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.system.base.entity.SysDatadictListLink;
import com.joyintech.tams.common.base.entity.ChbAssetInfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.dto.SvrAjaxResult;
import com.joyintech.tams.report.common.util.ExcelUtil;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.report.submitmaintenance.businessdetailed.dto.EquiteBusiDetails001Dto;
import com.joyintech.tams.svreport.chb.assetinfodetail.asset.DtoChangeUtil;
import com.joyintech.tams.svreport.chb.assetinfodetail.dao.AssetInfoDetailMapper;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.AssetInfoDetailDto;
import com.joyintech.tams.svreport.chb.assetinfodetail.util.AssetExport;
import com.joyintech.tams.svreport.chb.assetinfodetail.util.AssetInfoDetailUtil;
import com.joyintech.tams.svreport.chb.assetinfodetail.util.ServerMenu;
import com.joyintech.tams.svreport.chb.common.util.asset.RechbAassetInfoUtilService;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.apache.commons.beanutils.BeanMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

/**
 * 资产要素登记服务
 *
 * @author zhangzhongliang
 * @version 1.6.0 2019年04月24日
 * @see AssetInfoDetailMService
 * @since 1.0
 */
@Service
public class AssetInfoDetailMService extends CommonExportService<AssetInfoDetailDto> {

	protected static final Logger logger = LoggerFactory.getLogger(AssetInfoDetailMService.class);

	@Autowired
	private AssetInfoDetailMapper assetInfoDetailMapper;

	@Autowired
	private RechbAassetInfoUtilService rechbAassetInfoUtilService;

	@Autowired
	private AssetInfoDetailUtil assetInfoDetailUtil;


	static Map<String,Set<String>> map = null;

	@Autowired
	private AssetExport assetExport;
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
        if (StringUtil.isNotEmpty(param.get("assetType")) && String.valueOf(param.get("assetType")).contains(DictCodeListExt2.资产_负债类别_私募基金_公募基金.getValue())){
        	param.put("assetType",String.valueOf(param.get("assetType")).replace(DictCodeListExt2.资产_负债类别_私募基金_公募基金.getValue(),DictCodeListExt2.资产_负债类别_公募基金.getValue()+","+DictCodeListExt2.资产_负债类别_私募基金.getValue()));
		}
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

	public void doSave(AssetInfoDetailDto dto) throws Exception {
		//分别调用各自的setEntity方法
		this.getDtoByPageMessgae(dto);
		//赋值相关数据
		dto.setCreateUser(LoginUserUtil.getUserName());
		dto.setUpdateUser(LoginUserUtil.getUserName());
		dto.setIssuePartyId(CommonUtil.selectBankCode());
		if(StringUtil.isEmpty(dto.getAssetUuid())){
			dto.setAssetUuid(IDGenerator.generate(36));
		}
		dto.setStatus(DictCodeListExt2.数据状态_有效.getValue());
		/***当资产为资管计划或者委外时,需要保存资管计划开始结束日**/
		if(DictCodeListExt2.资产_负债类别_资产管理产品.getValue().equals(dto.getAssetType())
		||DictCodeListExt2.资产_负债类别_委外投资_协议方式.getValue().equals(dto.getAssetType())){
			dto.setPlanSdate(dto.getVdate());
			dto.setPlanEdate(dto.getMdate());
		}
		/***【资产基本信息维护】-新建或修改页面（债券类资产），“具体类别”字段未保存至CHB_ASSET_INFO表**/
		if(DictCodeListExt2.资产_负债类别_债券类资产_理财直接融资工具_同业存单.getValue().equals(dto.getAssetType())){
			dto.setChbSectype(dto.getSvrBussFinBondRepInfoDto().getRepSectype());
		}

		restTemplate.postForObject(
				SvrServiceApplication.CHB_SERVICE_NAME + "/AssetInfoDetail/doSave", dto, Integer.class);


	}


	public void doDelete(AssetInfoDetailDto dto) throws Exception {

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
			return null;
		}
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

			resultList = assetInfoDetailMapper.selectReportInfoByParam(param);
		}
		return resultList;
	}

	/**
	 * 导入数据文件之前检查数据
	 *
	 * @param fileName 导入的文件名
	 * @return 处理结果
	 */
	public AjaxResult checkConfirmMsgForImportData(String fileName)  {
		AjaxResult webResult = new AjaxResult();
		List<List<String>> data = ExcelUtil.getDataList2(fileName);
		List<AssetInfoDetailDto> list = new ArrayList<>();
		List<ExcelValidDto> checkResult = assetInfoDetailUtil.convertToBean(list,data);
		if (!StringUtil.isEmptyList(checkResult) && StringUtil.isNotEmpty(list)){
			int dataCount = this.assetInfoDetailMapper.selectDataCount(list);
			if (dataCount > 0) {
				webResult.setMessage(this.getMessage("REPORT_ASSET_C_0003"), MessageType.确认消息);
			}
			else{
				webResult.setData(ReportGlobalConstant.NO_INFO_SHOWOUT);
			}
		}else {
			webResult.setData(ReportGlobalConstant.NO_INFO_SHOWOUT);
		}
		return webResult;
	}

	public List<ExcelValidDto> importData(String fileName){
		List<ExcelValidDto> tempCheckResult = assetInfoDetailUtil.checkExcelTemp(fileName);
		if (!StringUtil.isEmptyList(tempCheckResult)) {
			return tempCheckResult;
		}
		List<List<String>> data = ExcelUtil.getDataList2(fileName);
		List<AssetInfoDetailDto> list = new ArrayList<>();
		List<ExcelValidDto> resultList = assetInfoDetailUtil.convertToBean(list,data);
		if (StringUtil.isEmptyList(resultList)){
			return restTemplate.postForList(
					SvrServiceApplication.CHB3_SERVICE_NAME + "/AssetInfoDetail/importData",
					list,
					ExcelValidDto.class);
		}
		return resultList;
	}

	public ResponseEntity<byte[]> exportExcel(Map<String, Object> param) throws Exception {
		String datas = String.valueOf(param.get("keyList"));
		List<AssetInfoDetailDto> keyList = JsonUtils.json2List(datas, AssetInfoDetailDto.class);
		List<AssetInfoDetailDto> dtoList;
		if (StringUtil.isEmptyList(keyList)) {
			dtoList = setDataList(param);
		} else {
			dtoList = assetInfoDetailMapper.selectKeyListData(keyList);
		}
		List<File> fileList = createExcel(dtoList);
		File targetFile = null;
		// 如果只有一个excel文件，不需要生成压缩文件
		if (fileList.size() == 1) {
			return ExportUtil.export(
					fileList.get(0).getName(),
					fileList.get(0), true);
		}
		else {
			String exportFilePath = CacheUtil.getSysParam(
					GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
			String zipFileName = FileNameUtil.createFileName("zip");
			targetFile = new File(exportFilePath + File.separator + zipFileName);
			try {
				ZipFileUtil.compressZip(targetFile, true, fileList.toArray(new File[] {}));
			}
			catch (Exception e) {
				// 压缩文件失败后，删除所有临时文件
				for(File file : fileList) {
					if(file.exists()) {
						file.delete();
					}
				}
			}
		}
		return ExportUtil.export(
				"资产要素信息" + GlobalConstant.OPEN_PARENTHESIS
						+ DateUtil.format(new Date()) + GlobalConstant.CLOSE_PARENTHESIS
						+ GlobalConstant.POINT + FileNameUtil.getFileExtName(targetFile),
				targetFile, true);
	}

	public List<File> createExcel(List<AssetInfoDetailDto> ds){
		List<File> fileList = new ArrayList<>();
		List<Map<String,Object>> dtoList_01 = new ArrayList<>();
		List<Map<String,Object>> dtoList_02 = new ArrayList<>();
		List<Map<String,Object>> dtoList_03 = new ArrayList<>();
		List<Map<String,Object>> dtoList_04 = new ArrayList<>();
		List<Map<String,Object>> dtoList_05 = new ArrayList<>();
		List<Map<String,Object>> dtoList_06 = new ArrayList<>();
		List<Map<String,Object>> dtoList_07 = new ArrayList<>();
		List<Map<String,Object>> dtoList_08 = new ArrayList<>();
		List<Map<String,Object>> dtoList_12 = new ArrayList<>();
		List<Map<String,Object>> dtoList_14 = new ArrayList<>();
		List<Map<String,Object>> dtoList_L01 = new ArrayList<>();
		ds.forEach(assetInfoDetailDto -> {
			String type = checkType(assetInfoDetailDto.getAssetType())?assetInfoDetailDto.getAssetType():null;
			Map<String,Object> map = object2Map(assetInfoDetailDto,type);
			map.put("assetCode",assetInfoDetailDto.getAssetCode());
			map.put("issuePartyId",assetInfoDetailDto.getIssuePartyId());
			map.put("assetType2Str", SysDataUtil.getZZDictCodeAndName(DictCode.资产_负债二级类别, assetInfoDetailDto.getAssetType2()));
			map.put("tradeMarketStr",SysDataUtil.getZZDictCodeAndName(DictCode.交易流通场所_中债, assetInfoDetailDto.getTradeMarket()));
			map.put("ccyStr",SysDataUtil.getZZDictCodeAndName(DictCode.币种_中债, assetInfoDetailDto.getCcy()));
			map.put("pbcAssetType1Str",SysDataUtil.getZZDictCodeAndName(DictCode.人行资产一级分类, assetInfoDetailDto.getPbcAssetType1()));
			map.put("pbcAssetType2Str",SysDataUtil.getZZDictCodeAndName(DictCode.人行资产二级分类, assetInfoDetailDto.getPbcAssetType2()));
			map.put("pbcAssetType3Str",SysDataUtil.getZZDictCodeAndName(DictCode.人行资产三级分类, assetInfoDetailDto.getPbcAssetType3()));
			map.put("assetType2Str", SysDataUtil.getZZDictCodeAndName(DictCode.资产_负债二级类别, assetInfoDetailDto.getAssetType2()));
			map.put("tradeMarketStr",SysDataUtil.getZZDictCodeAndName(DictCode.交易流通场所_中债, assetInfoDetailDto.getTradeMarket()));
			map.put("ccyStr",SysDataUtil.getZZDictCodeAndName(DictCode.币种_中债, assetInfoDetailDto.getCcy()));
			map.put("pbcAssetType1Str",SysDataUtil.getZZDictCodeAndName(DictCode.人行资产一级分类, assetInfoDetailDto.getPbcAssetType1()));
			map.put("pbcAssetType2Str",SysDataUtil.getZZDictCodeAndName(DictCode.人行资产二级分类, assetInfoDetailDto.getPbcAssetType2()));
			map.put("pbcAssetType3Str",SysDataUtil.getZZDictCodeAndName(DictCode.人行资产三级分类, assetInfoDetailDto.getPbcAssetType3()));
			if (StringUtil.equals(assetInfoDetailDto.getAssetType(),DictCodeListExt2.资产_负债类别_本行_他行存款_大额存单.getValue())){
				map.put("basis", SysDataUtil.getZZDictCodeAndName(DictCode.计息基础_中债, assetInfoDetailDto.getBasis()));
				map.put("depoType", SysDataUtil.getZZDictCodeAndName(DictCode.存款类型, assetInfoDetailDto.getDepoType()));
				map.put("targetType", SysDataUtil.getZZDictCodeAndName(DictCode.标的类别, assetInfoDetailDto.getTargetType()));
				dtoList_01.add(map);
			}else if (StringUtil.equals(assetInfoDetailDto.getAssetType(),DictCodeListExt2.资产_负债类别_拆放同业_买入返售_资产_同业拆入_卖出回购_负债.getValue())){
				map.put("rivalPartyType",SysDataUtil.getZZDictCodeAndName(DictCode.对手方类型, assetInfoDetailDto.getRivalPartyType()));
				map.put("basis",SysDataUtil.getZZDictCodeAndName(DictCode.计息基础_中债, assetInfoDetailDto.getBasis()));
				map.put("targetType1",SysDataUtil.getZZDictCodeAndName(DictCode.回购标的类别, assetInfoDetailDto.getTargetType1()));
				dtoList_02.add(map);
			}else if (StringUtil.equals(assetInfoDetailDto.getAssetType(),DictCodeListExt2.资产_负债类别_债券类资产_理财直接融资工具_同业存单.getValue())){
				map.put("chbSectype",SysDataUtil.getZZDictCodeAndName(DictCode.具体类别, assetInfoDetailDto.getChbSectype()));
				map.put("issueMode",SysDataUtil.getZZDictCodeAndName(DictCode.发行方式, assetInfoDetailDto.getIssueMode()));
				map.put("secMainGrade",SysDataUtil.getZZDictCodeAndName(DictCode.主体评级_中债, assetInfoDetailDto.getSecMainGrade()));
				map.put("partyType1",SysDataUtil.getZZDictCodeAndName(DictCode.发行机构类型_按规模划分, assetInfoDetailDto.getPartyType1()));
				map.put("partyType3",SysDataUtil.getZZDictCodeAndName(DictCode.发行机构类型_按技术领域划分, assetInfoDetailDto.getPartyType3()));
				map.put("partyType2",SysDataUtil.getZZDictCodeAndName(DictCode.发行机构类型_按经济类型划分, assetInfoDetailDto.getPartyType2()));
				map.put("industry",SysDataUtil.getZZDictCodeAndName(DictCode.机构所属行业一级分类, assetInfoDetailDto.getIndustry()));
				map.put("registOrg",SysDataUtil.getZZDictCodeAndName(DictCode.登记托管机构_中债, assetInfoDetailDto.getRegistOrg()));
				map.put("registorgComment",SysDataUtil.getZZDictCodeAndName(DictCode.登记托管机构_监管报送, assetInfoDetailDto.getRegistorgComment()));
				map.put("bankSecZxzq",SysDataUtil.getZZDictCodeAndName(DictCode.是否_中债, assetInfoDetailDto.getBankSecZxzq()));
				map.put("bankCapital",SysDataUtil.getZZDictCodeAndName(DictCode.银行资本补充工具, assetInfoDetailDto.getBankCapital()));
				map.put("isFbzb",SysDataUtil.getZZDictCodeAndName(DictCode.是否_中债, assetInfoDetailDto.getIsFbzb()));
				dtoList_03.add(map);
			}else if (StringUtil.equals(assetInfoDetailDto.getAssetType(),DictCodeListExt2.资产_负债类别_非标准化债权类资产_信贷资产流转项目.getValue())){
				map.put("profitType",SysDataUtil.getZZDictCodeAndName(DictCode.收受益权类型, assetInfoDetailDto.getProfitType()));
				map.put("isResale",SysDataUtil.getZZDictCodeAndName(DictCode.是否_中债, assetInfoDetailDto.getIsResale()));
				map.put("rateFlag",SysDataUtil.getZZDictCodeAndName(DictCode.是否_中债, assetInfoDetailDto.getRateFlag()));
				map.put("couponSpecies",SysDataUtil.getZZDictCodeAndName(DictCode.计息类型, assetInfoDetailDto.getCouponSpecies()));
				map.put("interestRuleFlag",SysDataUtil.getZZDictCodeAndName(DictCode.是否_中债, assetInfoDetailDto.getInterestRuleFlag()));
				map.put("intpayRule",SysDataUtil.getZZDictCodeAndName(DictCode.利息分布方式, assetInfoDetailDto.getIntpayRule()));
				map.put("basis",SysDataUtil.getZZDictCodeAndName(DictCode.计息基础_中债, assetInfoDetailDto.getBasis()));
				map.put("basisType",SysDataUtil.getZZDictCodeAndName(DictCode.基准利率, assetInfoDetailDto.getBasisType()));
				map.put("floatFactorFlag",SysDataUtil.getZZDictCodeAndName(DictCode.是否_中债, assetInfoDetailDto.getFloatFactorFlag()));
				map.put("structGrade",SysDataUtil.getZZDictCodeAndName(DictCode.结构档次, assetInfoDetailDto.getStructGrade()));
				map.put("repaymentType",SysDataUtil.getZZDictCodeAndName(DictCode.还本方式, assetInfoDetailDto.getRepaymentType()));
				map.put("periodRepaymentFlag",SysDataUtil.getZZDictCodeAndName(DictCode.分期还本条款标识, assetInfoDetailDto.getPeriodRepaymentFlag()));
				map.put("financierType1",SysDataUtil.getZZDictCodeAndName(DictCode.发行机构类型_按规模划分, assetInfoDetailDto.getFinancierType1()));
				map.put("financierType2",SysDataUtil.getZZDictCodeAndName(DictCode.发行机构类型_按技术领域划分, assetInfoDetailDto.getFinancierType2()));
				map.put("financierType3",SysDataUtil.getZZDictCodeAndName(DictCode.发行机构类型_按经济类型划分, assetInfoDetailDto.getFinancierType3()));
				map.put("industry1",SysDataUtil.getZZDictCodeAndName(DictCode.机构所属行业一级分类, assetInfoDetailDto.getIndustry1()));
				map.put("proIndustry",SysDataUtil.getZZDictCodeAndName(DictCode.机构所属行业一级分类, assetInfoDetailDto.getProIndustry()));
				map.put("industryFlag",SysDataUtil.getZZDictCodeAndName(DictCode.是否_中债, assetInfoDetailDto.getIndustryFlag()));
				map.put("monitType",SysDataUtil.getZZDictCodeAndName(DictCode.重点监控行业和领域类别, assetInfoDetailDto.getMonitType()));
				map.put("guaType",SysDataUtil.getZZDictCodeAndName(DictCode.担保方式, assetInfoDetailDto.getGuaType()));
				map.put("pledgeType",SysDataUtil.getZZDictCodeAndName(DictCode.抵质押物类型, assetInfoDetailDto.getPledgeType()));
				map.put("natureGuarantee",SysDataUtil.getZZDictCodeAndName(DictCode.担保性质, assetInfoDetailDto.getNatureGuarantee()));
				map.put("relationFinan",SysDataUtil.getZZDictCodeAndName(DictCode.担保人与融资人关系, assetInfoDetailDto.getRelationFinan()));
				map.put("financierGrade",SysDataUtil.getZZDictCodeAndName(DictCode.资产评级, assetInfoDetailDto.getFinancierGrade()));
				map.put("outGrade",SysDataUtil.getZZDictCodeAndName(DictCode.资产评级, assetInfoDetailDto.getOutGrade()));
				map.put("containType",SysDataUtil.getZZDictCodeAndName(DictCode.含权类型, assetInfoDetailDto.getContainType()));
				map.put("exerciseType",SysDataUtil.getZZDictCodeAndName(DictCode.资产行权方式, assetInfoDetailDto.getExerciseType()));
				map.put("perpetualClauseType",SysDataUtil.getZZDictCodeAndName(DictCode.永续条款类型, assetInfoDetailDto.getPerpetualClauseType()));
				map.put("deferredInterestType",SysDataUtil.getZZDictCodeAndName(DictCode.利息递延条款类型, assetInfoDetailDto.getDeferredInterestType()));
				map.put("deferredInterestInt",SysDataUtil.getZZDictCodeAndName(DictCode.是否_中债, assetInfoDetailDto.getDeferredInterestInt()));
				map.put("partRedemFlag",SysDataUtil.getZZDictCodeAndName(DictCode.是否_中债, assetInfoDetailDto.getPartRedemFlag()));
				map.put("finArea",SysDataUtil.getZZDictCodeAndName(DictCode.行政区划_市, assetInfoDetailDto.getFinArea()));
				map.put("privateFlag",SysDataUtil.getZZDictCodeAndName(DictCode.是否_中债, assetInfoDetailDto.getPrivateFlag()));
				map.put("pppflag",SysDataUtil.getZZDictCodeAndName(DictCode.是否_中债, assetInfoDetailDto.getPppflag()));
				map.put("bondtostockFlag",SysDataUtil.getZZDictCodeAndName(DictCode.是否_中债, assetInfoDetailDto.getBondtostockFlag()));
				dtoList_04.add(map);
			}else if (StringUtil.equals(assetInfoDetailDto.getAssetType(),DictCodeListExt2.资产_负债类别_票据类资产_信用证类资产.getValue()) ||
					StringUtil.equals(assetInfoDetailDto.getAssetType(),DictCodeListExt2.资产_负债类别_商品类资产.getValue()) ||
					StringUtil.equals(assetInfoDetailDto.getAssetType(),DictCodeListExt2.资产_负债类别_另类资产.getValue()) ||
					StringUtil.equals(assetInfoDetailDto.getAssetType(),DictCodeListExt2.资产_负债类别_委外投资_协议方式.getValue()) ||
					StringUtil.equals(assetInfoDetailDto.getAssetType(),DictCodeListExt2.资产_负债类别_其他_负债_类资产.getValue())){
				if (StringUtil.equals(assetInfoDetailDto.getAssetType(),DictCodeListExt2.资产_负债类别_票据类资产_信用证类资产.getValue())){
					map.put("isRight_05",SysDataUtil.getZZDictCodeAndName(DictCode.是否_中债, assetInfoDetailDto.getIsRight()));
					map.put("isResale_05",SysDataUtil.getZZDictCodeAndName(DictCode.是否_中债, assetInfoDetailDto.getIsResale()));
					map.put("notstandSecType_05",SysDataUtil.getZZDictCodeAndName(DictCode.票据类信用证类资产类型, assetInfoDetailDto.getNotstandSecType()));
					map.put("industryShares_05",SysDataUtil.getZZDictCodeAndName(DictCode.机构所属行业一级分类, assetInfoDetailDto.getIndustryShares()));
					map.put("isBzhpj_05",SysDataUtil.getZZDictCodeAndName(DictCode.是否_中债, assetInfoDetailDto.getIsBzhpj()));
				}
				if (StringUtil.equals(assetInfoDetailDto.getAssetType(),DictCodeListExt2.资产_负债类别_商品类资产.getValue())){
					map.put("countryArea_13",SysDataUtil.getZZDictCodeAndName(DictCode.所属国家或地区, assetInfoDetailDto.getCountryArea()));
				}
				if (StringUtil.equals(assetInfoDetailDto.getAssetType(),DictCodeListExt2.资产_负债类别_另类资产.getValue())){
					map.put("countryArea_18",SysDataUtil.getZZDictCodeAndName(DictCode.所属国家或地区, assetInfoDetailDto.getCountryArea()));
				}
				if (StringUtil.equals(assetInfoDetailDto.getAssetType(),DictCodeListExt2.资产_负债类别_委外投资_协议方式.getValue())){
					map.put("truActType_16",SysDataUtil.getZZDictCodeAndName(DictCode.资金实际投向, assetInfoDetailDto.getTruActType()));
					map.put("capUseIndu_16",SysDataUtil.getZZDictCodeAndName(DictCode.机构所属行业一级分类, assetInfoDetailDto.getCapUseIndu()));
					map.put("proProperty_16",SysDataUtil.getZZDictCodeAndName(DictCode.管理方式_发行登记, assetInfoDetailDto.getProProperty()));
					map.put("rateFlag_16",SysDataUtil.getZZDictCodeAndName(DictCode.是否_中债, assetInfoDetailDto.getRateFlag()));
				}
				if (StringUtil.equals(assetInfoDetailDto.getAssetType(),DictCodeListExt2.资产_负债类别_其他_负债_类资产.getValue())){
					map.put("countryArea_17",SysDataUtil.getZZDictCodeAndName(DictCode.所属国家或地区, assetInfoDetailDto.getCountryArea()));
				}
				map.put("remark",assetInfoDetailDto.getRemark());
				dtoList_05.add(map);
			}else if (StringUtil.equals(assetInfoDetailDto.getAssetType(),DictCodeListExt2.资产_负债类别_权益类资产.getValue())){
				map.put("sharesType",SysDataUtil.getZZDictCodeAndName(DictCode.股票类型, assetInfoDetailDto.getSharesType()));
				map.put("industryShares",SysDataUtil.getZZDictCodeAndName(DictCode.机构所属行业一级分类, assetInfoDetailDto.getIndustryShares()));
				map.put("investStage",SysDataUtil.getZZDictCodeAndName(DictCode.投资阶段, assetInfoDetailDto.getInvestStage()));
				map.put("sharesType1",SysDataUtil.getZZDictCodeAndName(DictCode.发行机构类型_按规模划分, assetInfoDetailDto.getSharesType1()));
				map.put("sharesType3",SysDataUtil.getZZDictCodeAndName(DictCode.发行机构类型_按技术领域划分, assetInfoDetailDto.getSharesType3()));
				map.put("sharesType2",SysDataUtil.getZZDictCodeAndName(DictCode.发行机构类型_按经济类型划分, assetInfoDetailDto.getSharesType2()));
				map.put("isPledge",SysDataUtil.getZZDictCodeAndName(DictCode.是否_中债, assetInfoDetailDto.getIsPledge()));
				map.put("deptEquity",SysDataUtil.getZZDictCodeAndName(DictCode.是否_中债, assetInfoDetailDto.getDeptEquity()));
				map.put("bankSecPrefer",SysDataUtil.getZZDictCodeAndName(DictCode.是否_中债, assetInfoDetailDto.getBankSecPrefer()));
				map.put("isGovernFund",SysDataUtil.getZZDictCodeAndName(DictCode.是否_中债, assetInfoDetailDto.getIsGovernFund()));
				map.put("isunlisted",SysDataUtil.getZZDictCodeAndName(DictCode.是否_中债, assetInfoDetailDto.getIsunlisted()));
				dtoList_06.add(map);
			}else if (StringUtil.equals(assetInfoDetailDto.getAssetType(),DictCodeListExt2.资产_负债类别_金融衍生品.getValue())){
				map.put("targetType3",SysDataUtil.getZZDictCodeAndName(DictCode.标的类别, assetInfoDetailDto.getTargetType3()));
				map.put("holdPurpose",SysDataUtil.getZZDictCodeAndName(DictCode.持有目的, assetInfoDetailDto.getHoldPurpose()));
				dtoList_07.add(map);
			}else if (StringUtil.equals(assetInfoDetailDto.getAssetType(),DictCodeListExt2.资产_负债类别_QDII债券资产.getValue())
					|| StringUtil.equals(assetInfoDetailDto.getAssetType(),DictCodeListExt2.资产_负债类别_QDII拆出_逆回购资产.getValue()) ||
					StringUtil.equals(assetInfoDetailDto.getAssetType(),DictCodeListExt2.资产_负债类别_QDII股票_基金类资产.getValue()) ||
					StringUtil.equals(assetInfoDetailDto.getAssetType(),DictCodeListExt2.资产_负债类别_QDII结构性票据.getValue())){
				if (StringUtil.equals(assetInfoDetailDto.getAssetType(),DictCodeListExt2.资产_负债类别_QDII债券资产.getValue())){
					map.put("countryArea_08",SysDataUtil.getZZDictCodeAndName(DictCode.所属国家或地区, assetInfoDetailDto.getCountryArea()));
					map.put("industry_08",SysDataUtil.getZZDictCodeAndName(DictCode.机构所属行业一级分类, assetInfoDetailDto.getIndustry()));
				}
				if (StringUtil.equals(assetInfoDetailDto.getAssetType(),DictCodeListExt2.资产_负债类别_QDII拆出_逆回购资产.getValue())){
					map.put("countryArea_09",SysDataUtil.getZZDictCodeAndName(DictCode.所属国家或地区, assetInfoDetailDto.getCountryArea()));
					map.put("basis_09", SysDataUtil.getZZDictCodeAndName(DictCode.计息基础_中债, assetInfoDetailDto.getBasis()));
				}
				if (StringUtil.equals(assetInfoDetailDto.getAssetType(),DictCodeListExt2.资产_负债类别_QDII股票_基金类资产.getValue())){
					map.put("countryArea_10",SysDataUtil.getZZDictCodeAndName(DictCode.所属国家或地区, assetInfoDetailDto.getCountryArea()));
					map.put("industryShares_10",SysDataUtil.getZZDictCodeAndName(DictCode.机构所属行业一级分类, assetInfoDetailDto.getIndustry()));
				}
				if (StringUtil.equals(assetInfoDetailDto.getAssetType(),DictCodeListExt2.资产_负债类别_QDII结构性票据.getValue())){
					map.put("countryArea_11",SysDataUtil.getZZDictCodeAndName(DictCode.所属国家或地区, assetInfoDetailDto.getCountryArea()));
				}
				map.put("remark",assetInfoDetailDto.getRemark());
				dtoList_08.add(map);
			}else if (StringUtil.equals(assetInfoDetailDto.getAssetType(),DictCodeListExt2.资产_负债类别_资产管理产品.getValue())){
				map.put("financialComissue",SysDataUtil.getZZDictCodeAndName(DictCode.是否_中债, assetInfoDetailDto.getFinancialComissue()));
				map.put("truActType",SysDataUtil.getZZDictCodeAndName(DictCode.资金实际投向, assetInfoDetailDto.getTruActType()));
				map.put("capUseIndu",SysDataUtil.getZZDictCodeAndName(DictCode.机构所属行业一级分类, assetInfoDetailDto.getCapUseIndu()));
				map.put("proProperty",SysDataUtil.getZZDictCodeAndName(DictCode.资管计划属性, assetInfoDetailDto.getProProperty()));
				map.put("rateFlag",SysDataUtil.getZZDictCodeAndName(DictCode.是否_中债, assetInfoDetailDto.getRateFlag()));
				map.put("buyStruct",SysDataUtil.getZZDictCodeAndName(DictCode.购买结构, assetInfoDetailDto.getBuyStruct()));
				map.put("manageType",SysDataUtil.getZZDictCodeAndName(DictCode.管理方式, assetInfoDetailDto.getManageType()));
				map.put("privateprdFlag",SysDataUtil.getZZDictCodeAndName(DictCode.是否_中债, assetInfoDetailDto.getPrivateprdFlag()));
				dtoList_12.add(map);
			}else if (StringUtil.equals(assetInfoDetailDto.getAssetType(),DictCodeListExt2.资产_负债类别_各类资产的其他项.getValue())){
				map.put("inAssetType",SysDataUtil.getZZDictCodeAndName(DictCode.行内资产类别, assetInfoDetailDto.getInAssetType()));
				map.put("countryArea",SysDataUtil.getZZDictCodeAndName(DictCode.所属国家或地区, assetInfoDetailDto.getCountryArea()));
				map.put("rateFlag",SysDataUtil.getZZDictCodeAndName(DictCode.是否_中债, assetInfoDetailDto.getRateFlag()));
				map.put("financierType1",SysDataUtil.getZZDictCodeAndName(DictCode.发行机构类型_按规模划分, assetInfoDetailDto.getFinancierType1()));
				map.put("financierType2",SysDataUtil.getZZDictCodeAndName(DictCode.发行机构类型_按经济类型划分, assetInfoDetailDto.getFinancierType2()));
				map.put("financierType3",SysDataUtil.getZZDictCodeAndName(DictCode.发行机构类型_按技术领域划分, assetInfoDetailDto.getFinancierType3()));
				map.put("industry1",SysDataUtil.getZZDictCodeAndName(DictCode.机构所属行业一级分类, assetInfoDetailDto.getIndustry1()));
				map.put("proIndustry",SysDataUtil.getZZDictCodeAndName(DictCode.机构所属行业一级分类, assetInfoDetailDto.getProIndustry()));
				map.put("industryFlag",SysDataUtil.getZZDictCodeAndName(DictCode.是否_中债, assetInfoDetailDto.getIndustryFlag()));
				map.put("monitType",SysDataUtil.getZZDictCodeAndName(DictCode.重点监控行业和领域类别, assetInfoDetailDto.getMonitType()));
				map.put("outGrade",SysDataUtil.getZZDictCodeAndName(DictCode.资产评级, assetInfoDetailDto.getOutGrade()));
				map.put("guaType",SysDataUtil.getZZDictCodeAndName(DictCode.担保方式, assetInfoDetailDto.getGuaType()));
				map.put("pledgeType",SysDataUtil.getZZDictCodeAndName(DictCode.抵质押物类型, assetInfoDetailDto.getPledgeType()));
				map.put("natureGuarantee",SysDataUtil.getZZDictCodeAndName(DictCode.担保性质, assetInfoDetailDto.getNatureGuarantee()));
				map.put("relationFinan",SysDataUtil.getZZDictCodeAndName(DictCode.担保人与融资人关系, assetInfoDetailDto.getRelationFinan()));
				map.put("deptEquity",SysDataUtil.getZZDictCodeAndName(DictCode.是否_中债, assetInfoDetailDto.getDeptEquity()));
				dtoList_14.add(map);
			}else if (StringUtil.equals(assetInfoDetailDto.getAssetType(),DictCodeListExt2.资产_负债类别_私募基金_公募基金.getValue())
					|| StringUtil.equals(assetInfoDetailDto.getAssetType(),DictCodeListExt2.资产_负债类别_公募基金.getValue()) ||
					StringUtil.equals(assetInfoDetailDto.getAssetType(),DictCodeListExt2.资产_负债类别_私募基金.getValue())){
				map.put("financialComissue",SysDataUtil.getZZDictCodeAndName(DictCode.是否_中债, assetInfoDetailDto.getFinancialComissue()));
				map.put("industryShares",SysDataUtil.getZZDictCodeAndName(DictCode.机构所属行业一级分类, assetInfoDetailDto.getIndustryShares()));
				map.put("registOrg1",SysDataUtil.getZZDictCodeAndName(DictCode.登记备案机构, assetInfoDetailDto.getRegistOrg1()));
				map.put("isGovernFund",SysDataUtil.getZZDictCodeAndName(DictCode.是否_中债, assetInfoDetailDto.getIsGovernFund()));
				map.put("governInvestFund",SysDataUtil.getZZDictCodeAndName(DictCode.政府投资基金投向, assetInfoDetailDto.getGovernInvestFund()));
				map.put("investStage",SysDataUtil.getZZDictCodeAndName(DictCode.投资阶段, assetInfoDetailDto.getInvestStage()));
				map.put("sharesType1",SysDataUtil.getZZDictCodeAndName(DictCode.发行机构类型_按规模划分, assetInfoDetailDto.getSharesType1()));
				map.put("sharesType3",SysDataUtil.getZZDictCodeAndName(DictCode.发行机构类型_按技术领域划分, assetInfoDetailDto.getSharesType3()));
				map.put("sharesType2",SysDataUtil.getZZDictCodeAndName(DictCode.发行机构类型_按经济类型划分, assetInfoDetailDto.getSharesType2()));
				dtoList_L01.add(map);
			}
		});
		if (!StringUtil.isEmptyList(dtoList_01)){
			fileList.add(assetExport.makeFile("/com/joyintech/tams/svreport/chb/assetinfodetail/conf/asset_export_conf_01.xml",dtoList_01,getAssetFileName(DictCodeListExt2.资产_负债类别_本行_他行存款_大额存单.getValue())));

		}
		if (!StringUtil.isEmptyList(dtoList_02)){
			fileList.add(assetExport.makeFile("/com/joyintech/tams/svreport/chb/assetinfodetail/conf/asset_export_conf_02.xml",dtoList_02,getAssetFileName(DictCodeListExt2.资产_负债类别_拆放同业_买入返售_资产_同业拆入_卖出回购_负债.getValue())));

		}
		if (!StringUtil.isEmptyList(dtoList_03)){
			fileList.add(assetExport.makeFile("/com/joyintech/tams/svreport/chb/assetinfodetail/conf/asset_export_conf_03.xml",dtoList_03,getAssetFileName(DictCodeListExt2.资产_负债类别_债券类资产_理财直接融资工具_同业存单.getValue())));

		}
		if (!StringUtil.isEmptyList(dtoList_04)){
			fileList.add(assetExport.makeFile("/com/joyintech/tams/svreport/chb/assetinfodetail/conf/asset_export_conf_04.xml",dtoList_04,getAssetFileName(DictCodeListExt2.资产_负债类别_非标准化债权类资产_信贷资产流转项目.getValue())));

		}
		if (!StringUtil.isEmptyList(dtoList_05)){
			fileList.add(assetExport.makeFile("/com/joyintech/tams/svreport/chb/assetinfodetail/conf/asset_export_conf_05.xml",dtoList_05,getAssetFileName(DictCodeListExt2.资产_负债类别_票据类资产_信用证类资产.getValue())));

		}
		if (!StringUtil.isEmptyList(dtoList_06)){
			fileList.add(assetExport.makeFile("/com/joyintech/tams/svreport/chb/assetinfodetail/conf/asset_export_conf_06.xml",dtoList_06,getAssetFileName(DictCodeListExt2.资产_负债类别_权益类资产.getValue())));

		}
		if (!StringUtil.isEmptyList(dtoList_07)){
			fileList.add(assetExport.makeFile("/com/joyintech/tams/svreport/chb/assetinfodetail/conf/asset_export_conf_07.xml",dtoList_07,getAssetFileName(DictCodeListExt2.资产_负债类别_金融衍生品.getValue())));

		}
		if (!StringUtil.isEmptyList(dtoList_08)){
			fileList.add(assetExport.makeFile("/com/joyintech/tams/svreport/chb/assetinfodetail/conf/asset_export_conf_08.xml",dtoList_08,getAssetFileName(DictCodeListExt2.资产_负债类别_QDII债券资产.getValue())));

		}
		if (!StringUtil.isEmptyList(dtoList_12)){
			fileList.add(assetExport.makeFile("/com/joyintech/tams/svreport/chb/assetinfodetail/conf/asset_export_conf_12.xml",dtoList_12,getAssetFileName(DictCodeListExt2.资产_负债类别_资产管理产品.getValue())));

		}
		if (!StringUtil.isEmptyList(dtoList_14)){
			fileList.add(assetExport.makeFile("/com/joyintech/tams/svreport/chb/assetinfodetail/conf/asset_export_conf_14.xml",dtoList_14,getAssetFileName(DictCodeListExt2.资产_负债类别_各类资产的其他项.getValue())));

		}
		if (!StringUtil.isEmptyList(dtoList_L01)){
			fileList.add(assetExport.makeFile("/com/joyintech/tams/svreport/chb/assetinfodetail/conf/asset_export_conf_L01.xml",dtoList_L01,getAssetFileName(DictCodeListExt2.资产_负债类别_私募基金_公募基金.getValue())));

		}
		return fileList;
	}

	private boolean checkType(String type){
		List<String> typeList = Arrays.asList(DictCodeListExt2.资产_负债类别_票据类资产_信用证类资产.getValue(),
				DictCodeListExt2.资产_负债类别_商品类资产.getValue(),DictCodeListExt2.资产_负债类别_另类资产.getValue(),
				DictCodeListExt2.资产_负债类别_委外投资_协议方式.getValue(),DictCodeListExt2.资产_负债类别_其他_负债_类资产.getValue(),
				DictCodeListExt2.资产_负债类别_QDII债券资产.getValue(),DictCodeListExt2.资产_负债类别_QDII拆出_逆回购资产.getValue(),
				DictCodeListExt2.资产_负债类别_QDII股票_基金类资产.getValue(),DictCodeListExt2.资产_负债类别_QDII结构性票据.getValue());
		if (typeList.contains(type)){
			return true;
		}
		return false;
	}

	private String getAssetFileName(String assetType){
		String name = "";
		if (DictCodeListExt2.资产_负债类别_本行_他行存款_大额存单.getValue().equals(assetType)){
			name = "资产要素表模板-存款";
		}else if (DictCodeListExt2.资产_负债类别_拆放同业_买入返售_资产_同业拆入_卖出回购_负债.getValue().equals(assetType)){
			name = "资产要素表模板-回购";
		} else if (DictCodeListExt2.资产_负债类别_债券类资产_理财直接融资工具_同业存单.getValue().equals(assetType)){
			name = "资产要素表模板-债券";
		} else if (DictCodeListExt2.资产_负债类别_非标准化债权类资产_信贷资产流转项目.getValue().equals(assetType)){
			name = "资产要素表模板-非标";
		}else if (DictCodeListExt2.资产_负债类别_票据类资产_信用证类资产.getValue().equals(assetType)){
			name = "资产要素表模板-票据及其他";
		}else if (DictCodeListExt2.资产_负债类别_权益类资产.getValue().equals(assetType)){
			name = "资产要素表模板-权益类";
		}else if (DictCodeListExt2.资产_负债类别_金融衍生品.getValue().equals(assetType)){
			name = "资产要素表模板-金融衍生品";
		}else if (DictCodeListExt2.资产_负债类别_QDII债券资产.getValue().equals(assetType)){
			name = "资产要素表模板-QDII";
		}else if (DictCodeListExt2.资产_负债类别_资产管理产品.getValue().equals(assetType)){
			name = "资产要素表模板-资产管理产品";
		}else if (DictCodeListExt2.资产_负债类别_商品类资产.getValue().equals(assetType)){
			name = "资产要素表模板-票据及其他";
		}else if (DictCodeListExt2.资产_负债类别_各类资产的其他项.getValue().equals(assetType)){
			name = "资产要素表模板-各类资产其他项";
		}else if (DictCodeListExt2.资产_负债类别_私募基金_公募基金.getValue().equals(assetType)){
			name = "资产要素表模板-基金";
		}
		return name;
	}

	private Map<String, Object> object2Map(Object obj,String type) {
		Map<String, Object> result = new HashMap<>();
		if (obj == null) {
			return null;
		}

		BeanMap beanMap = new BeanMap(obj);
		beanMap.forEach((key, value) -> {
			if (!StringUtil.equals("class", key.toString())) {
				if (StringUtil.isNotEmpty(type)){
					result.put(key.toString() + GlobalConstant.UNDER_LINE + type, value);
				}else {
					result.put(key.toString() , value);
				}

			}
		});
		return result;
	}



}
