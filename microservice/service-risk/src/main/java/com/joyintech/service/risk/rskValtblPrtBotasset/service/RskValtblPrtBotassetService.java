/*
 * 文件名：RskValtblPrtBotassetService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年1月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.rskValtblPrtBotasset.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.risk.rskValtblPrtBotasset.dao.RskValtblPrtBotassetMapper;
import com.joyintech.service.risk.rskValtblPrtBotasset.dto.RskValtblPrtBotassetMapDto;
import com.joyintech.tams.common.base.dao.RskValtblPrtBotassetMapMapper;
import com.joyintech.tams.common.base.entity.RskValtblPrtBotassetMap;
import com.joyintech.tams.constant.DictCodeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 估值表底层资产映射
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年1月3日
 * @see RskValtblPrtBotassetService
 * @since 1.0
 */
@Service("backRskValtblPrtBotassetService")
public class RskValtblPrtBotassetService extends BaseService<RskValtblPrtBotassetMapDto>{

    @Autowired
    private RskValtblPrtBotassetMapper rskValtblPrtBotassetMapper;
    
    /**
     * 自动生成
     */
    @Autowired
    private RskValtblPrtBotassetMapMapper rskValtblPrtBotassetMapMapper;
    
    public Page<RskValtblPrtBotassetMapDto> selectForPage(int page, int length, Map<String, Object> pageParam)
    {
        return super.queryForPage(page, length, () -> rskValtblPrtBotassetMapper.queryForPage(pageParam));
    }
    
    /**
     * by组合代码查找底层资产映射
     * <一句话功能简述>
     * <功能详细描述>
     * @param ptlId
     * @return
     * @see
     * @since 1.0
     */
    public List<RskValtblPrtBotassetMapDto> selectBotAssetMap(String ptlId){
        return rskValtblPrtBotassetMapper.selectBotAssetMap(ptlId);
    }
    
    /**
     * 更新(取消)映射关系
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    public void update(RskValtblPrtBotassetMapDto dto){
        RskValtblPrtBotassetMap record = new RskValtblPrtBotassetMap();
        record.setPtlId(dto.getPtlId());
        record.setSubCode(dto.getSubCode());
        record.setFinprodId(dto.getFinprodId());
        record.setAssetSource(dto.getAssetSource());
        record.setSubClass(dto.getSubClass());
        record.setSubName(dto.getSubName());
        record.setTblMarketId(dto.getTblMarketId());
        rskValtblPrtBotassetMapMapper.updateByPrimaryKey(record);
        //取消映射  置空估值表FINPROD_ID
        //add by zhangnian 20191012  start
        if(StringUtil.equals(dto.getOperateType(),"cancle")){
            rskValtblPrtBotassetMapper.cancleValmapped(dto.getPtlId(),dto.getSubCode());
        }
        //add by zhangnian 20191012  end
    }
    /**
     * 保存
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    public void writebotAssetMap(RskValtblPrtBotassetMapDto dto){
        if(null!=dto.getSelectedItem()&&!dto.getSelectedItem().isEmpty()){
            Map<String, Object> map = new HashMap<String, Object>();
            /*for(RskValtblPrtBotassetMapDto entity:dto.getSelectedItem()){
                map.put("ptlId", entity.getPtlId());
                rskValtblPrtBotassetMapper.deletePrtBotassetMap(map);
            }*/
            for(RskValtblPrtBotassetMapDto entity:dto.getSelectedItem()){
                RskValtblPrtBotassetMap record = new RskValtblPrtBotassetMap();
                record.setPtlId(entity.getPtlId());
                record.setSubCode(entity.getSubCode());
                record.setSubName(entity.getSubName());
                record.setAssetSource(entity.getAssetSource());
                record.setSubClass(entity.getSubClass());
                record.setFinprodId(entity.getFinprodId());
                record.setTblMarketId(entity.getTblMarketId());
                if(StringUtil.equals(entity.getIsAdd(), DictCodeList.是否_是.getValue())){
                    rskValtblPrtBotassetMapMapper.insert(record);
                }else{
                    if(StringUtil.isNotEmpty(entity.getFinprodId())){
                        rskValtblPrtBotassetMapMapper.updateByPrimaryKey(record);
                    }
                }
            }
        }
    }
    
    public RskValtblPrtBotassetMapDto selectAssetById(String finprodId){
        return rskValtblPrtBotassetMapper.selectAssetById(finprodId);
    }
    
    public RskValtblPrtBotassetMapDto selectWindBondAssetById(String finprodId){
        return rskValtblPrtBotassetMapper.selectWindBondAssetById(finprodId);
    }
    
    /**
     * 获取未映射个数
     * <功能详细描述>
     * @param paramMap
     * @return
     * @see
     * @since 1.0
     */
    public Integer getCountForValUnmapped(Map<String,Object> paramMap){
        return rskValtblPrtBotassetMapper.getCountForValUnmapped(paramMap);
    }
    
    /**
     * 获取不同映射个数
     * <功能详细描述>
     * @param paramMap
     * @return
     * @see
     * @since 1.0
     */
    public Integer getCountForValDiffmapped(Map<String,Object> paramMap){
        return rskValtblPrtBotassetMapper.getCountForValDiffmapped(paramMap);
    }
    
    /**
     * 应用未映射
     * <功能详细描述>
     * @param paramMap
     * @see
     * @since 1.0
     */
    public void applyMapForValUnmapped(Map<String,Object> paramMap){
         rskValtblPrtBotassetMapper.applyMapForValUnmapped(paramMap);
    }
    
    /**
     * 应用不同映射
     * <功能详细描述>
     * @param paramMap
     * @see
     * @since 1.0
     */
    public void applyMapForValDiffmapped(Map<String,Object> paramMap){
        rskValtblPrtBotassetMapper.applyMapForValDiffmapped(paramMap);
   }
    /**
     * 获取无标的信息下拉框数据
     * @return
     */
    public List<SelectOptionDto> selectUnTargetAsset(Map<String,Object> paramMap){
        return rskValtblPrtBotassetMapper.selectUnTargetAsset(paramMap);
    }
    public List<RskValtblPrtBotassetMapDto> selectUnMapSubject(Map<String, Object> paramMap) {
        return rskValtblPrtBotassetMapper.selectUnMapSubject(paramMap);
    }

    public Page<RskValtblPrtBotassetMapDto> selectMapSubjectInfoForPage(Map<String, Object> paramMap, int page, int length) {
        return super.queryForPage(page, length, () -> rskValtblPrtBotassetMapper.selectMapSubjectInfoForPage(paramMap));
    }
    /**
     * @Description: 映射&应用
     * @Param:
     * @return:
     * @Author: liupengfei
     * @Date: 2021/3/24
     */
    @Transactional
    public void applyMapForSubject(Map<String, Object> paramMap) {
        String subCode = (String)paramMap.get("subCode");
        String ptlId = (String)paramMap.get("ptlId");
        String finprodId = (String)paramMap.get("finprodId");
        if(StringUtil.isNotEmpty(subCode)){
            //资产科目
            String[] subCodeArr = StringUtil.split(subCode, GlobalConstant.COMMA);
            for(int i = 0;i<subCodeArr.length;i++){
                //更新：估值表组合底层资产映射表
                RskValtblPrtBotassetMapDto dto = new RskValtblPrtBotassetMapDto();
                dto.setPtlId(ptlId);
                dto.setSubCode(subCodeArr[i]);
                dto.setFinprodId(finprodId);
                rskValtblPrtBotassetMapMapper.updateByPrimaryKeySelective(dto);

                //更新：估值表资产科目表RSK_VALTBL_ASSET_SUB
                rskValtblPrtBotassetMapper.applyMapForSubject(dto);

                //处理期货类科目成本科目和冲销成本科目做特殊处理，以及根据成本自动更新利息科目做特殊处理
                String befSubCode =StringUtil.substring(subCodeArr[i],0,8);//科目代码　
                String aftSubCode =StringUtil.substring(subCodeArr[i],8);//资产代码
                List<String> intSubject = rskValtblPrtBotassetMapper.selectIntSubjectByCostSubject(befSubCode);
                if(!StringUtil.isEmptyList(intSubject) ){
                    for(int j=0;j<intSubject.size();j++){
                        RskValtblPrtBotassetMapDto dto1 = new RskValtblPrtBotassetMapDto();
                        BeanUtil.copyPropertiesIgnoreNull(dto1,dto);
                        dto1.setSubCode(intSubject.get(j)+aftSubCode);
                        //更新：估值表组合底层资产映射表
                        rskValtblPrtBotassetMapMapper.updateByPrimaryKeySelective(dto1);

                        //更新：估值表资产科目表RSK_VALTBL_ASSET_SUB
                        rskValtblPrtBotassetMapper.applyMapForSubject(dto1);
                    }

                }
            }
            //修改RSK_OUT_VALTBL_IMPORT_HIS表得状态 让其自动跑估值穿透start
           // updateRskOutValtblImportHis(ptlId);
            //修改RSK_OUT_VALTBL_IMPORT_HIS表得状态 让其自动跑估值穿透end
        }
    }

    /*private void updateRskOutValtblImportHis(String ptlId) {
        rskValtblPrtBotassetMapper.updateRskOutValtblImportHis(ptlId);
    }*/

    /**
     * @Description: 取消映射&应用
     * @Param:
     * @return:
     * @Author:
     * @Date:
     */
    @Transactional
    public void cancelApplyMapForSubject(Map<String, Object> paramMap) {
        String subCode = (String)paramMap.get("subCode");
        String ptlId = (String)paramMap.get("ptlId");
        String finprodId = (String)paramMap.get("finprodId");
        if(StringUtil.isNotEmpty(subCode)){
            //资产科目
            //更新：估值表组合底层资产映射表
            RskValtblPrtBotassetMapDto dto = new RskValtblPrtBotassetMapDto();
            dto.setPtlId(ptlId);
            dto.setSubCode(subCode);
            dto.setFinprodId(finprodId);
            rskValtblPrtBotassetMapper.cancelApplyMapForSubject(dto);
            //更新：估值表资产科目表RSK_VALTBL_ASSET_SUB
            rskValtblPrtBotassetMapper.cancleValmapped(dto.getPtlId(),dto.getSubCode());

            //处理期货类科目成本科目和冲销成本科目做特殊处理，以及根据成本自动更新利息科目做特殊处理
            String befSubCode = subCode.substring(0,8);//科目代码
            String aftSubCode = subCode.substring(8);//资产代码
            List<String> intSubject = rskValtblPrtBotassetMapper.selectIntSubjectByCostSubject(befSubCode);
            if(!StringUtil.isEmptyList(intSubject) && intSubject.size()>0){
                for(int j=0;j<intSubject.size();j++){
                    RskValtblPrtBotassetMapDto dto1 = new RskValtblPrtBotassetMapDto();
                    BeanUtil.copyPropertiesIgnoreNull(dto1,dto);
                    dto1.setSubCode(intSubject.get(j)+aftSubCode);
                    //更新：估值表组合底层资产映射表
                    rskValtblPrtBotassetMapper.cancelApplyMapForSubject(dto1);
                    //更新：估值表资产科目表RSK_VALTBL_ASSET_SUB
                    rskValtblPrtBotassetMapper.cancleValmapped(dto1.getPtlId(),dto1.getSubCode());
                }
            }


        }
        //add by  修改RSK_OUT_VALTBL_IMPORT_HIS表得状态 让其自动跑估值穿透start
        //updateRskOutValtblImportHis(ptlId);
        //add by  修改RSK_OUT_VALTBL_IMPORT_HIS表得状态 让其自动跑估值穿透end
    }

    /**
     * 获取资产维护界面维护的资产
     * @param paramMap
     * @return
     */
    public Page<SelectOptionDto> selectRskAsset(int page, int length, Map<String, Object> paramMap){
        return super.queryForPageForSelectReturn(page,length,() -> rskValtblPrtBotassetMapper.selectRskAsset(paramMap));
    }

    /**
     * 获取底层资产要素信息下拉框数据
     * @return
     */
   /* public List<SelectOptionDto> selectRepAssetDept(Map<String,Object> paramMap){
        return rskValtblPrtBotassetMapper.selectRepAssetDept(paramMap);
    }*/
}
