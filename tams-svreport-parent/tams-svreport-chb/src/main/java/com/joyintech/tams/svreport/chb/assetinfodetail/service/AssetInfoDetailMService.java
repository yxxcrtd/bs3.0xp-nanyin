package com.joyintech.tams.svreport.chb.assetinfodetail.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.intf.DataDict;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.system.base.entity.SysDatadictListLink;
import com.joyintech.tams.common.base.entity.ChbAssetInfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.svreport.chb.assetinfodetail.asset.factoryserver.AssetInfoFactoryService;
import com.joyintech.tams.svreport.chb.assetinfodetail.dao.AssetInfoDetailMapper;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.AssetInfoDetailDto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class AssetInfoDetailMService extends BaseService<AssetInfoDetailDto>{

	@Autowired
	private AssetInfoDetailMapper assetInfoDetailMapper;

	@Autowired
	private AssetInfoFactoryService asetInfoFactoryService;


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
		return queryForPage(AssetInfoDetailMapper.class,"selectChbInfoByParam",param);
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
		return restTemplate.postForList(
				SvrServiceApplication.CHB_SERVICE_NAME + "/AssetInfoDetail/queryAssetCodeList",map,
				SelectOptionDto.class);
	}

	public void doSave(AssetInfoDetailDto dto) throws Exception {
		AssetInfoDetailDto infoDto = new AssetInfoDetailDto();
		//分别调用各自的setEntity方法
		infoDto = asetInfoFactoryService.getAssetInfo(dto.getAssetType2()).setEntity(dto);

		infoDto.setCreateUser(LoginUserUtil.getUserName());
		infoDto.setUpdateUser(LoginUserUtil.getUserName());
		infoDto.setStatus(DictCodeList.数据状态_修改.getValue());
		infoDto.setIssuePartyId(CommonUtil.selectBankCode());
		restTemplate.postForObject(
				SvrServiceApplication.CHB_SERVICE_NAME + "/AssetInfoDetail/doSave", infoDto, Integer.class);
	}



	/**
	 * 获取资产及二级类型的关系
	 * @param flag
	 * @param itemCode
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
}
