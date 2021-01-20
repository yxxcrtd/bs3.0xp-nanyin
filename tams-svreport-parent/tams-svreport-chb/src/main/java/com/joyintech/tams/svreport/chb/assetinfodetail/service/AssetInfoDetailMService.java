package com.joyintech.tams.svreport.chb.assetinfodetail.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.system.base.entity.SysDatadictListLink;
import com.joyintech.tams.common.base.entity.ChbAssetInfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.errormessage.dto.ErrorMessage001;
import com.joyintech.tams.svreport.chb.assetinfodetail.asset.DtoChangeUtil;
import com.joyintech.tams.svreport.chb.assetinfodetail.dao.AssetInfoDetailMapper;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.AssetInfoDetailDto;
import com.joyintech.tams.svreport.chb.assetinfodetail.util.ServerMenu;
import com.joyintech.tams.systemtools.util.CommonUtil;
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
 * @see AssetInfoDetailMService
 * @since 1.0
 */
@Service
public class AssetInfoDetailMService extends CommonExportService<AssetInfoDetailDto> {

	protected static final Logger logger = LoggerFactory.getLogger(AssetInfoDetailMService.class);

	@Autowired
	private AssetInfoDetailMapper assetInfoDetailMapper;

	static Map<String,Set<String>> map = null;
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
		//TODO  界面共同方法编写  error表和INFO表
		return assetInfoDetailMapper.getAssetInfoDetail(Assetuuid);
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
		dto.setStatus(DictCodeList.数据状态_修改.getValue());
		dto.setIssuePartyId(CommonUtil.selectBankCode());
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
}
