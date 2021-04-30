/*
 * 文件名：EntrustCommonPersonSelectService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.entrust.common.service;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.base.BaseService;
import com.joyintech.service.entrust.etrplanassetn.dto.EtrPlanAssetNDto;
import com.joyintech.service.entrust.etrplanassetr.dto.EtrPlanAssetRDto;
import com.joyintech.service.entrust.etrprojectnet.dto.EtrProjectNetDto;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.entrust.common.dao.EntrustCommonPersonSelectMapper;
import com.joyintech.service.entrust.etrinvestallinfo.dto.EtrInvestAllInfoDto;
import com.joyintech.service.entrust.etrinvestbondinfo.dto.EtrInvestBondInfoDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 投资委托人员信息下拉框查询
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2018年11月9日
 * @see EntrustCommonPersonSelectService
 * @since 1.0
 */
@Service
public class EntrustCommonPersonSelectService extends BaseService<ITableRow> {
    
    /**
     * 查询mapper
     */
    @Autowired
    private EntrustCommonPersonSelectMapper entrustCommonPersonSelectMapper;
    
    /**
     * 
     * 查询交易对手
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getCounterId(int page,int length,Map<String, Object> map){
        map.put("COUNTER_TYPE", DictCodeListExt1.交易对手类型_内部交易对手.getValue());
        map.put("YES_NO_Y", DictCodeList.是否_是.getValue());
        map.put("YES_NO_N", DictCodeList.是否_否.getValue());
        return this.queryForPageForSelectReturn(page, length,()->entrustCommonPersonSelectMapper.getCounterId(map));
    }

    public Page<SelectOptionDto> getCounterIdDepat(int page,int length,Map<String, Object> param) {
        return this.queryForPageForSelectReturn(page, length,() ->entrustCommonPersonSelectMapper.getCounterIdDepat(param));
    }
    /**
     * 查询本方交易员
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @DataSource(DataSourceType.OTHER)
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public Page<SelectOptionDto> getTrader(int page,int length,Map<String, Object> map) {
        String initValue =(String)map.get("initValue");
        if(StringUtil.isNotEmpty(initValue)){
            List<String> initValueList = StringUtil.splitToList(",", initValue);
            map.put("initValueList", initValueList);
        }
        map.put("TRADER", DictCodeList.角色岗位_交易员.getValue());
        map.put("TRADER_IN", DictCodeList.角色岗位_交易员_资本境内_岗.getValue());
        map.put("TRADER_OUT", DictCodeList.角色岗位_交易员_资本境外_岗.getValue());
        map.put("TRADER_MASTER", DictCodeList.角色岗位_交易处长.getValue());
        //wangyang1 二级法人改造 start
        if (MultilevelDataAuthorityUtil.isEnabled()) {
            map.put("institutionList", LoginUserUtil.getTreeInstCode());
        }
        //wangyang1 二级法人改造 end
        return this.queryForPageForSelectReturn(page, length,()->entrustCommonPersonSelectMapper.getTrader(map));
    }
    
    /**
     * 
     * 查询用户信息
     * <功能详细描述>
     * @param userName
     * @return
     * @see
     * @since 1.0
     */
    @DataSource(DataSourceType.OTHER)
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public Map<String, Object> getUser(String userName){
        return entrustCommonPersonSelectMapper.getUser(userName);
    }
    /**
     * 
     * 债券查询真实姓名
     * <功能详细描述>
     * @param userName
     * @return
     * @see
     * @since 1.0
     */
    @DataSource(DataSourceType.OTHER)
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public List<EtrInvestBondInfoDto> getRealName(List<EtrInvestBondInfoDto> dtoList){
        for(EtrInvestBondInfoDto dto:dtoList){
            String realName = entrustCommonPersonSelectMapper.getRealName(dto.getInvManagerId());
            dto.setInvManagerId(realName);
        }
        return dtoList;
    }
    /**
     * 
     * 所有投资委托查询真实姓名
     * <功能详细描述>
     * @param dtoList
     * @return
     * @see
     * @since 1.0
     */
    @DataSource(DataSourceType.OTHER)
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public List<EtrInvestAllInfoDto> getRealName2(List<EtrInvestAllInfoDto> dtoList){
        for(EtrInvestAllInfoDto dto:dtoList){
            String realName = entrustCommonPersonSelectMapper.getRealName(dto.getInvManagerId());
            dto.setInvManagerId(realName);
        }
        return dtoList;
    }
    /**
     *
     * 计划类净值委托查询查询投资经理真实姓名
     * @param dtoList
     * @return
     * @see
     * @since 1.0
     */
    @DataSource(DataSourceType.OTHER)
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public List<EtrPlanAssetNDto> getRealName3(List<EtrPlanAssetNDto> dtoList){
        //guyunfei 2019/09/03
        for(EtrPlanAssetNDto dto:dtoList){
            String realName = entrustCommonPersonSelectMapper.getRealName(dto.getInvManagerId());
            dto.setInvManagerId(realName);
        }
        return dtoList;
    }
    /**
     *
     * 计划类利率委托查询投资经理真实姓名
     * @param dtoList
     * @return
     * @see
     * @since 1.0
     */
    @DataSource(DataSourceType.OTHER)
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public List<EtrPlanAssetRDto> getRealName4(List<EtrPlanAssetRDto> dtoList){
        //guyunfei 2019/09/03
        for(EtrPlanAssetRDto dto:dtoList){
            String realName = entrustCommonPersonSelectMapper.getRealName(dto.getInvManagerId());
            dto.setInvManagerId(realName);
        }
        return dtoList;
    }

    public List<String> findSameNameCounterId(Map<String, Object> map) {
        return entrustCommonPersonSelectMapper.findSameNameCounterId(map);
    }

    // add by etliu 20210421 权益类资产相关代码合并 start
    @DataSource(DataSourceType.OTHER)
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public List<EtrProjectNetDto> getRealName5(List<EtrProjectNetDto> dtoList){
        for(EtrProjectNetDto dto:dtoList){
            String realName = entrustCommonPersonSelectMapper.getRealName(dto.getInvManagerId());
            dto.setInvManagerId(realName);
        }
        return dtoList;
    }
    // add by etliu 20210421 权益类资产相关代码合并 end
    
    /**
     * 查询本方交易员列表
     * 
     * @param param 入参
     * @return 交易员列表
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getCfetsTrader(int page, int length,
                                                       Map<String, Object> param) {
		String counterId = param.get("etrInvestPersonInfo.counterId") == null ? null
				: param.get("etrInvestPersonInfo.counterId").toString();
		if (StringUtil.isNotEmpty(counterId)) {
			param.remove("etrInvestPersonInfo.counterId");
			param.put("counterId", counterId);
		}
        return this.queryForPageForSelectReturn(page, length,
            () -> entrustCommonPersonSelectMapper.getCfetsTrader(param));
    }
    
    /**
     * 
     * 查询交易对手
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getCounterIdCfets(int page,int length,Map<String, Object> map){
        return this.queryForPageForSelectReturn(page, length,()->entrustCommonPersonSelectMapper.getCounterIdCfets(map));
    }
}
