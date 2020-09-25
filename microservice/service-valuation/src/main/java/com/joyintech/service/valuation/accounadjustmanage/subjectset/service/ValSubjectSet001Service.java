/*
 * 文件名：ValSubjectSet001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：杨智
 * 修改时间：2018年5月17日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.accounadjustmanage.subjectset.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.accounadjustmanage.subjectset.dao.ValSubjectSet001Mapper;
import com.joyintech.service.valuation.accounadjustmanage.subjectset.dto.ValBokSubjectDto;
import com.joyintech.tams.common.base.dao.BokSubjectMapper;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;

@Service(value="serviceValSubjectSet001Service")
@Transactional(rollbackFor = Exception.class)
public class ValSubjectSet001Service extends CommonExportService<ValBokSubjectDto> {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    @Autowired
    private ValSubjectSet001Mapper valSubjectSet001Mapper;
    
    private BokSubjectMapper valBokSubjectMapper;
    
//    /**
//     * 查询所有账套
//     * <br/> 用于所属账套控件初期化
//     * @return SelectOptionDto list
//     * @see
//     * @since 1.0
//     */
//    public List<SelectOptionDto> findBooksets(){
//        return valSubjectSet001Mapper.findBooksets();
//    }
    
    /**
     * 检索上级科目代码列表
     * 
     * @param param 过滤参数
     * @return 上级科目代码列表
     * @see 
     * @since 1.0
     */
    public List<SuggestDto> getFSubjectId(String param) {
        return queryForSuggest(valSubjectSet001Mapper::getFSubjectId, null, param);
    }
    /**
     * 获取科目文本输入框模糊查询
     * 
     * @param param 过滤参数
     * @return 上级科目代码列表
     * @see 
     * @since 1.0
     */
    public List<SuggestDto> getSubjectId(String param) {
        return queryForSuggest(valSubjectSet001Mapper::getSubjectId, null, param);
    }
    
//    /**
//     * 获取科目文本输入框模糊查询 和 联动
//     * 
//     * @param param 过滤参数
//     * @return 上级科目代码列表
//     * @see 
//     * @since 1.0
//     */
//    public List<SuggestDto> getSubjectId2(Map<String, Object> map) {
//        return queryForSuggest(()-> valSubjectSet001Mapper.getSubjectId2(map), (String)map.get("param"), null);
//    } 
    
    /**
     * 获取科目文本输入框模糊查询 和 联动
     * 
     * @param param 过滤参数
     * @return 上级科目代码列表
     * @throws Exception 
     * @see 
     * @since 1.0
     */
    public List<SelectOptionDto> getSubjectId2(Map<String, Object> map) throws Exception {
        List<SelectOptionDto> list = valSubjectSet001Mapper.getSubjectId2(map);
        if(CollectionUtils.isNotEmpty(list)) {
            list.forEach(item -> {
                item.setId(item.getId());
                item.setText(item.getId() + "（" + item.getText() + "）" );
            });
        }
        return list;
    }
    
    /**
     * 获取科目文本输入框模糊查询 和 联动
     * 
     * @param param 过滤参数
     * @return 上级科目代码列表
     * @throws Exception 
     * @see 
     * @since 1.0
     */
    public List<SelectOptionDto> getSubjectLevel(Map<String, Object> map) throws Exception {
        return valSubjectSet001Mapper.getSubjectLevel(map);
    }
    
    /**
     * 获取科目下拉框
     * @param hashMap
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> queryValBokSubject(Map<String, Object> map) {
        String subjectLevel = (String) map.get("subjectLevel");
        if(StringUtil.isNotEmpty(subjectLevel)){
            // 若是传的4级，则查询三级科目
            if(subjectLevel.equals("4")){
                subjectLevel = "3";
                map.put("subjectLevel", subjectLevel);
            }
            
        }
        return valSubjectSet001Mapper.queryValBokSubject(map);
    }
    
    /**
     * 检索科目列表
     * 
     * @param pageParam 过滤参数
     * @return 科目列表
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<ValBokSubjectDto> selectForPage(int page,int length, Map<String, Object> pageParam) throws Exception {
        return super.queryForPage(page,length,()-> valSubjectSet001Mapper.selectAll(pageParam));
    }
    /**
     * 根据主键检索科目
     * 
     * @param booksetId 账套代码
     * @param subjectNo 科目代码
     * @return 科目
     * @see
     * @since 1.0
     */
    public ValBokSubjectDto selectValBokSubject(String booksetId, String subjectNo) {
        return valSubjectSet001Mapper.selectValBokSubject(booksetId, subjectNo);
    }

    /**
     * 检索上级科目代码列表(支持入参)
     * 
     * @param param 过滤参数
     * @return 上级科目代码列表
     * @see 
     * @since 1.0
     */
    public List<SuggestDto> getFSubjectId2(String param) {
        Map<String, Object> map =  Maps.newHashMap();
        map.put("param", param);
        return valSubjectSet001Mapper.getFSubjectId2(map);
    }
    
    
    /**
     * 批量删除（删除当前科目及其子科目）
     * @param selectedItem
     * @see
     * @since 1.0
     */
    public void deleteByBatch(List<ValBokSubjectDto> selectedItem) {
        selectedItem.forEach(item -> {
            valSubjectSet001Mapper.deleteByFSubjectId(item);
        });
    }
    
    
    public void save(ValBokSubjectDto dto) {
        if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
            valBokSubjectMapper.updateByPrimaryKey(dto);
        }
        else{
            valBokSubjectMapper.insert(dto);
        }
    }
    
}
