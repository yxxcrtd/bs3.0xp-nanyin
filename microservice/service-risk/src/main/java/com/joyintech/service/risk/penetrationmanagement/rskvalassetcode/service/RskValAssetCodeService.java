/*
 * 文件名：RskValAssetCodeService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年12月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.penetrationmanagement.rskvalassetcode.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.RskValtblAssetSubMapDtlMapper;
import com.joyintech.tams.common.base.dao.RskValtblAssetSubMapMapper;
import com.joyintech.tams.common.base.entity.RskValtblAssetSubMap;
import com.joyintech.tams.common.base.entity.RskValtblAssetSubMapDtl;
import com.joyintech.tams.common.base.entity.RskValtblAssetSubMapDtlKey;
import com.joyintech.tams.common.base.entity.RskValtblSubject;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.risk.penetrationmanagement.rskvalassetcode.dao.RskValAssetCodeMapper;
import com.joyintech.service.risk.penetrationmanagement.rskvalassetcode.dto.RskValtblAssetSubMapDtlDto;
import com.joyintech.service.risk.penetrationmanagement.rskvalassetcode.dto.RskValtblAssetSubMapDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年12月25日
 * @see RskValAssetCodeService
 * @since 1.0
 */
@Service
public class RskValAssetCodeService extends BaseService<RskValtblAssetSubMapDtlDto>{

    @Autowired
    private RskValAssetCodeMapper rskValAssetCodeMapper;
    
    @Autowired
    private RskValtblAssetSubMapMapper   rskValtblAssetSubMapMapper;
    
    @Autowired
    private RskValtblAssetSubMapDtlMapper   rskValtblAssetSubMapDtlMapper;
    
    public List<RskValtblAssetSubMapDto> selectAll(Map<String,Object> map){
        return rskValAssetCodeMapper.selectForPage(map);
    }
    /**
     * 初始化查询
     * <功能详细描述>
     * @param page
     * @param length
     * @param pageParam
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    /*public Page<RskValtblAssetSubMapDto> selectForPage(int page, int length,
                                                  Map<String, Object> pageParam) throws Exception {
        return super.queryForPage(page, length, () -> {
            rskValAssetCodeMapper.selectForPage(pageParam);
        });
    }*/
    
    /**
     * 初始化映射明细
     * <功能详细描述>
     * @param id
     * @return
     * @see
     * @since 1.0
     */
    public Page<RskValtblAssetSubMapDtlDto> findPageDtl(int page, int length,
                                                       Map<String, Object> pageParam) throws Exception {
             return super.queryForPage(page, length, () -> {
                 rskValAssetCodeMapper.selectDtl(pageParam);
             });
         }
    
    public List<RskValtblAssetSubMapDtlDto> selectDtlList(String id){
        return rskValAssetCodeMapper.selectDtlList(id);
    }
    
    /**
     * 资产科目映射修改初始化
     * <功能详细描述>
     * @param id
     * @return
     * @see
     * @since 1.0
     */
    public RskValtblAssetSubMap selectAssetCodeByPrimaryKey(String id){
        return rskValtblAssetSubMapMapper.selectByPrimaryKey(id);
    }
    /**
     * 资产科目映射保存
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    public void saveAssetCode(RskValtblAssetSubMapDto dto){
        RskValtblAssetSubMap record = new RskValtblAssetSubMap();
        record.setId(dto.getId());
        record.setName(dto.getName());
        if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
            rskValtblAssetSubMapMapper.insert(record);
        }else{
            rskValtblAssetSubMapMapper.updateByPrimaryKey(record);
        }
    }
    
    /**
     * 删除
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    public void delete(RskValtblAssetSubMapDto dto){
        rskValtblAssetSubMapMapper.deleteByPrimaryKey(dto.getId());
        //删除明细
        rskValAssetCodeMapper.deleteById(dto.getId());
    }
    
    /**
     * 删除明细
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    public void deleteDtl(RskValtblAssetSubMapDtlDto dto){
        if(null!=dto.getSelectedItem()&&!dto.getSelectedItem().isEmpty()){
            for(RskValtblAssetSubMapDtlDto entity:dto.getSelectedItem()){
                RskValtblAssetSubMapDtlKey key = new RskValtblAssetSubMapDtlKey();
                key.setId(entity.getId());key.setSrcCode(entity.getSrcCode());
                rskValtblAssetSubMapDtlMapper.deleteByPrimaryKey(key);
            }
        }
    }
    /**
     * 资产科目映射明细保存
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    public void saveAssetCodeDtl(RskValtblAssetSubMapDtlDto dto){
        if(StringUtil.isNotEmpty(dto.getSrcCode())){
            List<String> srcCodeList = StringUtil.splitToList(null, dto.getSrcCode().toString());
            for(String srcCode:srcCodeList){
                RskValtblAssetSubMapDtl record = new RskValtblAssetSubMapDtl();
                record.setId(dto.getId());
                record.setTgtCode(dto.getTgtCode());
                record.setSrcCode(srcCode);
                record.setAllowSum(dto.getAllowSum());
                record.setMemo(dto.getMemo());
                rskValtblAssetSubMapDtlMapper.insert(record);
            }
        }
    }
    
    /**
     * 查询目标科目代码
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public List<RskValtblSubject> selecctRskValtblSubjectCodeList(String subType){
        return rskValAssetCodeMapper.selecctRskValtblSubjectCodeList(subType);
    }
    
    /**
     * 
     * 判断映射名称是否重复
     * <功能详细描述>
     * @param id
     * @param name
     * @return
     * @see
     * @since 1.0
     */
    public int selecctCountByName(String id,String name){
        return rskValAssetCodeMapper.selecctCountByName(id,name);
    }
    
    /**
     * 关联组合数
     * <功能详细描述>
     * @param assetSubMapId
     * @return
     * @see
     * @since 1.0
     */
    public int getCountByPtl(String assetSubMapId){
        return rskValAssetCodeMapper.getCountByPtl(assetSubMapId);
    }
    
}
