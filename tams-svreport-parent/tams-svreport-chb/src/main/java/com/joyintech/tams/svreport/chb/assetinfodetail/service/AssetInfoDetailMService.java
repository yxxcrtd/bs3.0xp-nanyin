package com.joyintech.tams.svreport.chb.assetinfodetail.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.base.entity.ChbAssetInfo;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.svreport.chb.assetinfodetail.asset.BussAssetInfoService;
import com.joyintech.tams.svreport.chb.assetinfodetail.dao.AssetInfoDetailMapper;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.AssetInfoDetailDto;
import com.joyintech.tams.svreport.loaninfomanagement.dto.OperationalLoanInfo001Dto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.apache.fop.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
	private BussAssetInfoService assetInfoService;
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
	public AssetInfoDetailDto getAssetInfoDetail(String  Assetuuid) {
		return assetInfoDetailMapper.getAssetInfoDetail(Assetuuid);
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

	public void doSave(AssetInfoDetailDto dto){
		AssetInfoDetailDto infoDto = new AssetInfoDetailDto();
		infoDto = assetInfoService.setEntity(dto);

		infoDto.setCreateUser(LoginUserUtil.getUserName());
		infoDto.setUpdateUser(LoginUserUtil.getUserName());
		infoDto.setStatus(DictCodeList.数据状态_修改.getValue());
		restTemplate.postForObject(
				SvrServiceApplication.CHB_SERVICE_NAME + "/AssetInfoDetail/doSave", infoDto, Integer.class);
	}
}
