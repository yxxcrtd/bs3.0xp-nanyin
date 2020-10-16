package com.joyintech.service.risk.namelistmanage.assetnamelist.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.risk.namelistmanage.assetnamelist.dao.AssetNameManagementDetailMapper;
import com.joyintech.service.risk.namelistmanage.assetnamelist.dao.AssetNameManagementMapper;
import com.joyintech.service.risk.namelistmanage.assetnamelist.dto.RskAccessListDetailDto;
import com.joyintech.service.risk.namelistmanage.assetnamelist.dto.RskAccessListDto;
import com.joyintech.service.risk.namelistmanage.assetnamelist.dto.RskAccessListLogDto;
import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.RskAccessList;
import com.joyintech.tams.common.base.entity.RskAccessListDetail;
import com.joyintech.tams.common.base.entity.WflRskAccessListDetail;
import com.joyintech.tams.common.base.entity.WflRskAccessListLog;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 名单管理--资产名单管理
 * @author 张年
 * @version 1.0 2019年07月11日
 * @see AssetNameManagementService
 * @since 1.0
 */
@Service("backAssetNameManagementService")
public class AssetNameManagementService extends BaseService<RskAccessListDto> {

    @Autowired
    private AssetNameManagementMapper assetNameManagementMapper;

    @Autowired
    private AssetNameManagementDetailMapper assetNameManagementDetailMapper;

    @Autowired
    private IPlatformDate iPlatformDate;

    @Autowired
    private WflRskAccessListMapper wflRskAccessListMapper;

    @Autowired
    private WflRskAccessListDetailMapper wflRskAccessListDetailMapper;

    @Autowired
    private WflRskAccessListLogMapper wflRskAccessListLogMapper;

    @Autowired
    private RskAccessListDetailMapper rskAccessListDetailMapper;

    @Autowired
    private RskAccessListLogMapper rskAccessListLogMapper;

    @Autowired
    private RskAccessListMapper rskAccessListMapper;
    /**
     * 分页
     * @param page
     * @param length
     * @param pageParam
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<RskAccessListDto> selectForPage(int page, int length, Map<String, Object> pageParam)
        throws Exception {
        pageParam.put("sysDate",iPlatformDate.getSysDate());
        Page<RskAccessListDto> pageDto = super.queryForPage(page, length, () -> assetNameManagementMapper.queryForPage(pageParam));
        return pageDto;
    }

    /**
     * 插入业务表
     * @param dto
     */
    public void insert(RskAccessListDto dto){
        //删除名单明细
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("listId",dto.getListId());
        paramMap.put("sysDate",iPlatformDate.getSysDate());
        assetNameManagementDetailMapper.deleteRskAccessListLog(paramMap);
        //删除名单明细
        paramMap.put("rskAccessListDetailList",dto.getRskAccessListDetailList());
        assetNameManagementDetailMapper.deleteRskAccessListDetail(paramMap);
        //历史数据更新人变更
        paramMap.put("updateUser",dto.getUpdateUser());
        assetNameManagementDetailMapper.updateDetailUpdateUser(paramMap);
        //多级法人改造 add by songyawei 20200622  当前用户所属机构代码
        String createInst =  MultilevelDataAuthorityUtil.getService().queryOrganizationCode(LoginUserUtil.getUserName());
        //插入名单明细
        for(RskAccessListDetailDto rskAccessListDetailDto : dto.getRskAccessListDetailList()){
            if(!StringUtil.equals(DictCodeListExt2.名单操作方向_移出名单.getValue(),rskAccessListDetailDto.getOperatePs())){
                rskAccessListDetailDto.setListDate(iPlatformDate.getSysDate());
                /*rskAccessListDetailDto.setUpdateUser(dto.getUpdateUser());*/
        		rskAccessListDetailDto.setInsitituteCode(createInst);//多级法人改造 add by songyawei 20200622
                rskAccessListDetailMapper.insert(rskAccessListDetailDto);
            }
        }
        for(RskAccessListLogDto rskAccessListLogDto : dto.getRskAccessListLogDtoList()){
            /*rskAccessListLogDto.setUpdateUser(dto.getUpdateUser());*/
            rskAccessListLogDto.setUpdateTime(new Date());
    		rskAccessListLogDto.setInsitituteCode(createInst);//多级法人改造 add by songyawei 20200622
            rskAccessListLogMapper.insert(rskAccessListLogDto);
        }
    }

    /**
     * 插入业务流程表
     * @param dto
     */
    public void insertBussFlow(RskAccessListDto dto){
        //插入名单业务流程表
        wflRskAccessListMapper.insert(dto.getWflRskAccessList());
        //多级法人改造 add by songyawei 20200622  当前用户所属机构代码
        String createInst =  MultilevelDataAuthorityUtil.getService().queryOrganizationCode(LoginUserUtil.getUserName());
        //插入名单明细业务流程表
        for(WflRskAccessListDetail wflRskAccessListDetail : dto.getWflRskAccessListDetail()){
        	wflRskAccessListDetail.setInsitituteCode(createInst);//多级法人改造 add by songyawei 20200622
            wflRskAccessListDetailMapper.insert(wflRskAccessListDetail);
        }
        //插入名单履历业务流程表
        for(WflRskAccessListLog wflRskAccessListLog : dto.getWflRskAccessListLog()){
            //统一更新操作人
            /*wflRskAccessListLog.setUpdateUser(dto.getUpdateUser());*/
        	wflRskAccessListLog.setInsitituteCode(createInst);//多级法人改造 add by songyawei 20200622
            wflRskAccessListLogMapper.insert(wflRskAccessListLog);
        }
    }

    public RskAccessListDto selectRskAccessList(Map<String,Object> param){
        RskAccessListDto dto = new RskAccessListDto();
        RskAccessList rskAccessList =  rskAccessListMapper.selectByPrimaryKey(param.get("listId").toString());
        BeanUtil.copyPropertiesIgnoreNull(dto,rskAccessList);
        return dto;
    }
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public int getRskEntityByName(Map<String,Object> param) throws Exception {
        Date sysDate = iPlatformDate.getSysDate();
        param.put("sysDate", sysDate);
        return rskAccessListDetailMapper.selectByName(param);
    }
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public List<RskAccessListDetail> getEntityList(Map<String,Object> param) throws Exception {
        Date sysDate = iPlatformDate.getSysDate();
        param.put("sysDate", sysDate);
        return rskAccessListDetailMapper.selectEntityList(param);
    }

    /**
     * 删除名单
     * @param dto
     */
    public void delete(RskAccessListDto dto){
        Date sysDate = iPlatformDate.getSysDate();
        dto.setDeldate(sysDate);
        rskAccessListMapper.updateByPrimaryKeySelective(dto);
    }

    /**
     * 模糊下拉框 多选
     * @param param
     * @return
     */
    public List<SelectOptionDto> selectListId(Map<String, Object> param){
        return assetNameManagementMapper.selectListId(param);
    }
}
