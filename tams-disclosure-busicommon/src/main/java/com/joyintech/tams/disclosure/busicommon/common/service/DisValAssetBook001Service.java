/*
 * 文件名：ValAssetBook001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：huangjian
 * 修改时间：2018年5月24日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.disclosure.busicommon.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.disclosure.busicommon.common.constants.DictCodeList;
import com.joyintech.tams.disclosure.busicommon.common.dao.BokBalance001Mapper;
import com.joyintech.tams.disclosure.busicommon.common.dao.BokBookset001Mapper;
import com.joyintech.tams.disclosure.busicommon.common.dao.DisValAssetBook001Mapper;
import com.joyintech.tams.disclosure.busicommon.common.dto.BokBalanceKey;
import com.joyintech.tams.disclosure.busicommon.common.dto.ValAssetbook001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;



/**
 * 账套管理
 * 
 * @author 黄健
 * @version 1.0 2018年5月10日
 * @see ValAssetBook001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DisValAssetBook001Service extends CommonExportService<ValAssetbook001Dto> implements BaseProcessService<ValAssetbook001Dto>{

    /**
     * ValAssetBook001Mapper
     */
    @Autowired
    private DisValAssetBook001Mapper mapper;
    
    @Autowired
    private BokBalance001Mapper bokBalanceMapper;
    
    @Autowired
    private BokBookset001Mapper booksetMapper;
    /**
     * 检索账套代码列表（只取理财账套）
     * 
     * @param param 过滤参数
     * @return 上级科目代码列表
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getBooksetId2(String param) {
        String booksetType = DictCodeList.账套类别_理财账套.getValue();
        return mapper.getBooksetId2(booksetType, param);
    }
    
    @Override
	public void doComplete(ValAssetbook001Dto dto, OperationType operationType) {
		// 删除科目余额
		BokBalanceKey condition = new BokBalanceKey();
		condition.setBooksetId(dto.getBooksetId());
		bokBalanceMapper.deleteByBooksetId(condition);
		// 删除账套
		booksetMapper.deleteByPrimaryKey(dto.getBooksetId());
	}  
    //多级法人改造start add by hxy 20200619
    /**
     * 银行模式时，返回机构
     * @return
     */
    public String selectBankModeOrgName(){
    	return mapper.selectBankModeOrgName();

    }
    /**
     * 联社机构时，选择账套时返回机构
     * @return
     */
    public String selectAssociationModeOrgName(String  booksetOrg){
    	return mapper.selectAssociationModeOrgName(booksetOrg);
    }
    /**
     * 联社机构时，选择账套组时返回机构
     * @return
     */
    public String selectAssociationModeOrgGrpName(String  booksetOrg){
    	return mapper.selectAssociationModeOrgGrpName(booksetOrg);
    }
    //多级法人改造end add by hxy 20200619
}