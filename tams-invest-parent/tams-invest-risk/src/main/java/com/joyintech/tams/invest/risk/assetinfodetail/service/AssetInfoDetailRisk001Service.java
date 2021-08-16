package com.joyintech.tams.invest.risk.assetinfodetail.service;

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
import com.joyintech.tams.common.base.entity.FinProductAdd;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.RskAssetInfo;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;
import com.joyintech.tams.invest.common.util.ExcelUtil;
import com.joyintech.tams.invest.common.util.ReportGlobalConstant;
import com.joyintech.tams.invest.risk.assetinfodetail.asset.DtoChangeUtil;
import com.joyintech.tams.invest.risk.assetinfodetail.dto.AssetInfoDetailRisk001Dto;
import com.joyintech.tams.invest.risk.assetinfodetail.dto.asset.SvrBussQDIISharesInfoRiskDto;
import com.joyintech.tams.invest.risk.assetinfodetail.dto.asset.SvrBussRepEquityInfoRiskDto;
import com.joyintech.tams.invest.risk.assetinfodetail.dto.asset.SvrBussRepFinanciallInfoRiskDto;
import com.joyintech.tams.invest.risk.assetinfodetail.dto.asset.SvrBussRepQDIIBondInfoRiskDto;
import com.joyintech.tams.invest.risk.assetinfodetail.util.AssetInfoDetailRiskUtil;
import com.joyintech.tams.invest.risk.assetinfodetail.util.ServerMenu;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

/**
 * 资产要素登记服务
 *
 * @author zhangzhongliang
 * @version 1.6.0 2019年04月24日
 * @see AssetInfoDetailRisk001Service
 * @since 1.0
 */
@Service
public class AssetInfoDetailRisk001Service extends CommonExportService<AssetInfoDetailRisk001Dto> implements BaseProcessService<AssetInfoDetailRisk001Dto> {

	protected static final Logger logger = LoggerFactory.getLogger(AssetInfoDetailRisk001Service.class);


	static Map<String,Set<String>> map = null;

	static Map<String,String> dict = new HashMap<>();
	
	@Autowired
	private AssetInfoDetailRiskUtil assetInfoDetailUtil;
	
	 @Autowired
	private CommonBusinessService commonBusinessService;
	
	/**
	 *
	 * 业务画面查询事件
	 * <功能详细描述>
	 * @return
	 * @see
	 * @since 1.0
	 */
	public Page<AssetInfoDetailRisk001Dto> searchForPage(Map<String, Object> param) {
		return restTemplate.postForPage(
	            ServiceApplication.RISK_SERVICE_NAME +
	            "/AssetInfoDetailRiskController/findPage",
	            param, AssetInfoDetailRisk001Dto.class);
	}

	/**
	 *
	 * 业务画面查询事件
	 * <功能详细描述>
	 * @return
	 * @see
	 * @since 1.0
	 */
	public AssetInfoDetailRisk001Dto getAssetInfoDetail(String  assetuuid,String dataStatus) {
		AssetInfoDetailRisk001Dto dto = new AssetInfoDetailRisk001Dto();
		RskAssetInfo cinfo = restTemplate.getForObject(
	            ServiceApplication.RISK_SERVICE_NAME +
	            "/AssetInfoDetailRiskController/BackRskAssetInfoMethod?assetuuid=" + assetuuid, RskAssetInfo.class);
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
	public RskAssetInfo getChbAssetInfoByUUid(String uuid){
		return restTemplate.getForObject(
	            ServiceApplication.RISK_SERVICE_NAME +
	            "/AssetInfoDetailRiskController/getChbAssetInfoByUUid?uuid=" + uuid, RskAssetInfo.class);
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
		dtoList = restTemplate.postForList(
	            ServiceApplication.RISK_SERVICE_NAME +
	            "/AssetInfoDetailRiskController/selectAssetCodeList",map,
	            SelectOptionDto.class);
		return dtoList;
	}

	/**
	 * 设置流程业务说明
	 */
	 @Override
	 public String generateTitle(AssetInfoDetailRisk001Dto entity, OperationType type) {
		 return  "资产代码" + "(" + entity.getAssetCode() + ")" + "-" + "资产名称" + "("
				 + entity.getAssetName() + ")";
	 }
	/**
	 * 工作流（留痕操作结束的时候执行的方法）
	 */
	@Override
	public void doComplete(AssetInfoDetailRisk001Dto dto, OperationType type) {
		dto.setOperateType(type.toString());
		//分别调用各自的setEntity方法
		this.getDtoByPageMessgae(dto);
		//赋值相关数据
		dto.setIssuePartyId(CommonUtil.selectBankCode());
		if(StringUtil.isEmpty(dto.getAssetUuid())){
			dto.setAssetUuid(IDGenerator.generate(36));
		}
		//在金融产品附属表FinProductAdd，金融产品分类表FIN_PRODUCT_TYPE存值 start
		FinProductAdd finProductAdd = new FinProductAdd();
		BeanUtil.copyPropertiesIgnoreNull(finProductAdd, dto);
		finProductAdd.setFinprodId(dto.getAssetCode());
		FinProductType finProductType = new FinProductType();
		BeanUtil.copyPropertiesIgnoreNull(finProductType, dto);
		finProductType.setFinprodId(dto.getAssetCode());
		// 分支序号
		Long branch = StringUtil.isEmpty(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)) ? 0 : Long.parseLong(
				CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE));
		finProductType.setBranch(branch);
		if(StringUtil.equals(dto.getAssetType2(), DictCodeListExt2.资产_负债二级类别_股票_一级市场.getValue())
				||StringUtil.equals(dto.getAssetType2(), DictCodeListExt2.资产_负债二级类别_股票_二级市场.getValue())){
			finProductAdd.setInvestNature(DictCodeList.投资性质_权益类.getValue());
			finProductType.setType8(DictCodeListExt2.平安资产类型_股票.getValue());
			finProductType.setType9(dto.getOrgAssetType2());
			finProductType.setType10(dto.getOrgAssetType3());
			finProductType.setType11(dto.getOrgAssetType4());
			finProductType.setType12(dto.getOrgAssetType5());
		}else if(StringUtil.equals(dto.getAssetType2(), DictCodeListExt2.资产_负债二级类别_互换.getValue())){
			finProductAdd.setInvestNature(DictCodeList.投资性质_商品及金融衍生品类.getValue());
			finProductType.setType8(DictCodeListExt2.平安资产类型_利率互换.getValue());
			finProductType.setType9(dto.getOrgAssetType2());
			finProductType.setType10(dto.getOrgAssetType3());
			finProductType.setType11(dto.getOrgAssetType4());
			finProductType.setType12(dto.getOrgAssetType5());
		}else if(StringUtil.equals(dto.getAssetType2(), DictCodeListExt2.资产_负债二级类别_期货.getValue())){
			finProductAdd.setInvestNature(DictCodeList.投资性质_商品及金融衍生品类.getValue());
			finProductType.setType8(DictCodeListExt2.平安资产类型_期货.getValue());
			finProductType.setType9(dto.getOrgAssetType2());
			finProductType.setType10(dto.getOrgAssetType3());
			finProductType.setType11(dto.getOrgAssetType4());
			finProductType.setType12(dto.getOrgAssetType5());
		}else if(StringUtil.equals(dto.getAssetType2(), DictCodeListExt2.资产_负债二级类别_期权.getValue())){
			finProductAdd.setInvestNature(DictCodeList.投资性质_商品及金融衍生品类.getValue());
			finProductType.setType8(DictCodeListExt2.平安资产类型_期权.getValue());
			finProductType.setType9(dto.getOrgAssetType2());
			finProductType.setType10(dto.getOrgAssetType3());
			finProductType.setType11(dto.getOrgAssetType4());
			finProductType.setType12(dto.getOrgAssetType5());
		}else if(StringUtil.equals(dto.getAssetType2(), DictCodeListExt2.资产_负债二级类别_QDII债券.getValue())){
			finProductAdd.setInvestNature(DictCodeList.投资性质_固定收益类.getValue());
			finProductType.setType8(DictCodeList.平安资产类型_债券.getValue());finProductType.setType9(dto.getOrgAssetType2());
			finProductType.setType10(dto.getOrgAssetType3());
			finProductType.setType11(dto.getOrgAssetType4());
			finProductType.setType12(dto.getOrgAssetType5());
		}else if(StringUtil.equals(dto.getAssetType2(), DictCodeListExt2.资产_负债二级类别_QDII股票.getValue())){
			finProductAdd.setInvestNature(DictCodeList.投资性质_权益类.getValue());
			finProductType.setType8(DictCodeListExt2.平安资产类型_股票.getValue());
			finProductType.setType9(dto.getOrgAssetType2());
			finProductType.setType10(dto.getOrgAssetType3());
			finProductType.setType11(dto.getOrgAssetType4());
			finProductType.setType12(dto.getOrgAssetType5());
		}else{
			if(dto.getSvrBussQDIISharesInfoRiskDto()!=null) {
				finProductAdd.setInvestNature(dto.getSvrBussQDIISharesInfoRiskDto().getInvestNature());
			}
			finProductType.setType8(DictCodeList.平安资产类型_基金.getValue());
			finProductType.setType9(dto.getOrgAssetType2());
			finProductType.setType10(dto.getOrgAssetType3());
			finProductType.setType11(dto.getOrgAssetType4());
			finProductType.setType12(dto.getOrgAssetType5());
		}
		dto.setFinProductAdd(finProductAdd);
		dto.setFinProductType(finProductType);
		//在金融产品附属表FinProductAdd，金融产品分类表FIN_PRODUCT_TYPE存值 end
		/***当资产为资管计划或者委外时,需要保存资管计划开始结束日**/
		if(DictCodeListExt2.资产_负债类别_资产管理产品.getValue().equals(dto.getAssetType())
		||DictCodeListExt2.资产_负债类别_委外投资_协议方式.getValue().equals(dto.getAssetType())){
			dto.setPlanSdate(dto.getVdate());
			dto.setPlanEdate(dto.getMdate());
		}
		/***【资产基本信息维护】-新建或修改页面（债券类资产），“具体类别”字段未保存至CHB_ASSET_INFO表**/
		if(DictCodeListExt2.资产_负债类别_债券类资产_理财直接融资工具_同业存单.getValue().equals(dto.getAssetType())){
			dto.setChbSectype(dto.getSvrBussFinBondRepInfoRiskDto().getRepSectype());
		}

		restTemplate.postForObject(
				ServiceApplication.RISK_SERVICE_NAME +
	            "/AssetInfoDetailRiskController/doComplete", dto, Integer.class);


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
			List<SysDatadictListLink> listLink = restTemplate.postForList(
		            ServiceApplication.RISK_SERVICE_NAME +
		            "/AssetInfoDetailRiskController/getDataListLinkData",params,
		            SysDatadictListLink.class);
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
	public AssetInfoDetailRisk001Dto getDtoByPageMessgae(AssetInfoDetailRisk001Dto dto)  {
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
   	String  assetuuid=(String)map.get("assetuuid");
     return  restTemplate.getForObject(
	            ServiceApplication.RISK_SERVICE_NAME +
	            "/AssetInfoDetailRiskController/getTargetAmt?assetuuid=" + assetuuid, BigDecimal.class);
   }

	/**
	 * 根据界面传入数据获取二级资产类型
	 * @param resultMap
	 * @return
	 */
	public List<SelectOptionDto> getAssetTye2(Map<String,Object> resultMap){
		List<SelectOptionDto> resultOpt=restTemplate.postForList(
	            ServiceApplication.RISK_SERVICE_NAME +
	            "/AssetInfoDetailRiskController/getAssetTye2",resultMap,
	            SelectOptionDto.class);				
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
	public Page<AssetInfoDetailRisk001Dto> selectPageData(Map<String, Object> param) {
		return searchForPage(param);
	}

	public List<AssetInfoDetailRisk001Dto> setDataList(Map<String, Object> param) {
		List<AssetInfoDetailRisk001Dto> resultList = new ArrayList<>();
		String keyList = (String) param.get("keyList");
		if (StringUtil.isNotEmpty(keyList)) {
			try {
				resultList = JsonUtils.json2List(keyList, AssetInfoDetailRisk001Dto.class);
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

			resultList = restTemplate.postForList(
		            ServiceApplication.RISK_SERVICE_NAME +
		            "/AssetInfoDetailRiskController/selectChbInfoByParam",param,
		            AssetInfoDetailRisk001Dto.class);
		}
		return resultList;
	}
	
	/**
	 * 检查是否存在对应资产
	 * @param UUid
	 * @return
	 */
	public AssetInfoDetailRisk001Dto queryAssetInfo(String assetCode){
		return restTemplate.getForObject(
	            ServiceApplication.RISK_SERVICE_NAME +
	            "/AssetInfoDetailRiskController/queryAssetInfo?assetCode=" + assetCode, AssetInfoDetailRisk001Dto.class);
	}
	
	/**
     * 根据uuid和flag查询对应的资产要素实体类
     * @param flag  01-CHB_ASSET_INFO_ERRODATA    02-CHB_ASSET_INFO_
     * @param assetUUid
     * @return
     */
    public   RskAssetInfo BackChbAssetInfoMethod(String  flag,String uuid){
    	RskAssetInfo  infoDto=new  RskAssetInfo();
        infoDto = restTemplate.getForObject(
	            ServiceApplication.RISK_SERVICE_NAME +
	            "/AssetInfoDetailRiskController/getChbAssetInfoByUUid?uuid=" + uuid, RskAssetInfo.class);
         return  infoDto;
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
		List<AssetInfoDetailRisk001Dto> list = new ArrayList<>();
		List<ExcelValidDto> checkResult = assetInfoDetailUtil.convertToBean(list,data);
		if (StringUtil.isEmptyList(checkResult) && StringUtil.isNotEmpty(list)){
			int dataCount =restTemplate.postForObject(
		            ServiceApplication.RISK_SERVICE_NAME +
		            "/AssetInfoDetailRiskController/selectDataCount",list,
		            Integer.class);
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
		List<AssetInfoDetailRisk001Dto> list = new ArrayList<>();
		List<ExcelValidDto> resultList = assetInfoDetailUtil.convertToBean(list,data);
		list.forEach(dto -> {
			//在金融产品附属表FinProductAdd，金融产品分类表FIN_PRODUCT_TYPE存值 start
			FinProductAdd finProductAdd = new FinProductAdd();
			BeanUtil.copyPropertiesIgnoreNull(finProductAdd, dto);
			finProductAdd.setFinprodId(dto.getAssetCode());
			FinProductType finProductType = new FinProductType();
			BeanUtil.copyPropertiesIgnoreNull(finProductType, dto);
			finProductType.setFinprodId(dto.getAssetCode());
			// 分支序号
			Long branch = StringUtil.isEmpty(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)) ? 0 : Long.parseLong(
					CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE));
			finProductType.setBranch(branch);
			if(StringUtil.equals(dto.getAssetType2(), DictCodeListExt2.资产_负债二级类别_股票_一级市场.getValue())
					||StringUtil.equals(dto.getAssetType2(), DictCodeListExt2.资产_负债二级类别_股票_二级市场.getValue())){
				finProductAdd.setInvestNature(DictCodeList.投资性质_权益类.getValue());
				finProductType.setType8(DictCodeListExt2.平安资产类型_股票.getValue());
				finProductType.setType9(dto.getOrgAssetType2());
				finProductType.setType10(dto.getOrgAssetType3());
				finProductType.setType11(dto.getOrgAssetType4());
				finProductType.setType12(dto.getOrgAssetType5());
			}else if(StringUtil.equals(dto.getAssetType2(), DictCodeListExt2.资产_负债二级类别_互换.getValue())){
				finProductAdd.setInvestNature(DictCodeList.投资性质_商品及金融衍生品类.getValue());
				finProductType.setType8(DictCodeListExt2.平安资产类型_利率互换.getValue());
				finProductType.setType9(dto.getOrgAssetType2());
				finProductType.setType10(dto.getOrgAssetType3());
				finProductType.setType11(dto.getOrgAssetType4());
				finProductType.setType12(dto.getOrgAssetType5());
			}else if(StringUtil.equals(dto.getAssetType2(), DictCodeListExt2.资产_负债二级类别_期货.getValue())){
				finProductAdd.setInvestNature(DictCodeList.投资性质_商品及金融衍生品类.getValue());
				finProductType.setType8(DictCodeListExt2.平安资产类型_期货.getValue());
				finProductType.setType9(dto.getOrgAssetType2());
				finProductType.setType10(dto.getOrgAssetType3());
				finProductType.setType11(dto.getOrgAssetType4());
				finProductType.setType12(dto.getOrgAssetType5());
			}else if(StringUtil.equals(dto.getAssetType2(), DictCodeListExt2.资产_负债二级类别_期权.getValue())){
				finProductAdd.setInvestNature(DictCodeList.投资性质_商品及金融衍生品类.getValue());
				finProductType.setType8(DictCodeListExt2.平安资产类型_期权.getValue());
				finProductType.setType9(dto.getOrgAssetType2());
				finProductType.setType10(dto.getOrgAssetType3());
				finProductType.setType11(dto.getOrgAssetType4());
				finProductType.setType12(dto.getOrgAssetType5());
			}else if(StringUtil.equals(dto.getAssetType2(), DictCodeListExt2.资产_负债二级类别_QDII债券.getValue())){
				finProductAdd.setInvestNature(DictCodeList.投资性质_固定收益类.getValue());
				finProductType.setType8(DictCodeList.平安资产类型_债券.getValue());
				finProductType.setType9(dto.getOrgAssetType2());
				finProductType.setType10(dto.getOrgAssetType3());
				finProductType.setType11(dto.getOrgAssetType4());
				finProductType.setType12(dto.getOrgAssetType5());
			}else if(StringUtil.equals(dto.getAssetType2(), DictCodeListExt2.资产_负债二级类别_QDII股票.getValue())){
				finProductAdd.setInvestNature(DictCodeList.投资性质_权益类.getValue());
				finProductType.setType8(DictCodeListExt2.平安资产类型_股票.getValue());
				finProductType.setType9(dto.getOrgAssetType2());
				finProductType.setType10(dto.getOrgAssetType3());
				finProductType.setType11(dto.getOrgAssetType4());
				finProductType.setType12(dto.getOrgAssetType5());
			}else{
				if(dto.getSvrBussQDIISharesInfoRiskDto()!=null) {
					finProductAdd.setInvestNature(dto.getSvrBussQDIISharesInfoRiskDto().getInvestNature());
				}
				finProductType.setType8(DictCodeList.平安资产类型_基金.getValue());
				finProductType.setType9(dto.getOrgAssetType2());
				finProductType.setType10(dto.getOrgAssetType3());
				finProductType.setType11(dto.getOrgAssetType4());
				finProductType.setType12(dto.getOrgAssetType5());
			}
			dto.setFinProductAdd(finProductAdd);
			dto.setFinProductType(finProductType);
			//在金融产品附属表FinProductAdd，金融产品分类表FIN_PRODUCT_TYPE存值 end
		});
		if (StringUtil.isEmptyList(resultList)){
			return restTemplate.postForList(
					ServiceApplication.RISK_SERVICE_NAME + "/AssetInfoDetailRiskController/importData",
					list,
					ExcelValidDto.class);
		}
		return resultList;
	}
	
	
	public List<SysDatadictListLink> getDataListLink1(Map<String, Object> param){
			List<SysDatadictListLink> listLink = restTemplate.postForList(
		            ServiceApplication.RISK_SERVICE_NAME +
		            "/AssetInfoDetailRiskController/getDataListLinkData",param,
		            SysDatadictListLink.class);
		return listLink;
	}
}
