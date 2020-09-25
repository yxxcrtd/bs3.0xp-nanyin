package com.joyintech.tams.disclosure.information.config.dispoiquery.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.disclosure.information.config.dispoiquery.dao.DisPoiQuery001Mapper;
import com.joyintech.tams.disclosure.information.config.dispoiquery.dto.DisPoiQuery001Dto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * poi查询sql管理Service（微服务层）
 *
 * @author kx
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DisPoiQuery001Service extends CommonExportService<DisPoiQuery001Dto> {

    @Autowired
    private DisPoiQuery001Mapper disPoiQuery001Mapper;

    /**
     * 分页查询事件
     *
     * @param params
     * @return
     */
    public Page<DisPoiQuery001Dto> searchPage(Map<String, Object> params) {
        int page = Integer.parseInt(params.get(GlobalConstant.PARAM_PAGE).toString());
        int legth = Integer.parseInt(params.get(GlobalConstant.PARAM_LENGTH).toString());
        //关联编号
        String poiId = (String) params.get("poiId");
        if (!StringUtil.isBlank(poiId)) {
            params.put("poiIdList", StringUtil.splitToList(GlobalConstant.COMMA, poiId));
        }
        //关联类型
        String poiType = (String) params.get("poiType");
        if (!StringUtil.isBlank(poiType)) {
            params.put("poiTypeList", StringUtil.splitToList(GlobalConstant.COMMA, poiType));
        }
        Page<DisPoiQuery001Dto> pageList = queryForPage(
                page, legth, () -> disPoiQuery001Mapper.queryForPage(params));
        for (DisPoiQuery001Dto disPoiQuery001Dto : pageList.getRoot()) {
            disPoiQuery001Dto.setPoiTypeStr(SysDataUtil.getNameFromDataDict(DictCode.查询条件类型, disPoiQuery001Dto.getPoiType()));
        }
        return pageList;
    }


    /**
     * 获取关联编号下拉框初始化数据
     *
     * @param param
     * @return
     */
    public List<SelectOptionDto> queryPoiIdList(Map<String, Object> param) {
        param.put("statusEffective",DictCodeListExt1.维护数据状态_有效.getValue());
        List<SelectOptionDto> list = new ArrayList<SelectOptionDto>();
        if(StringUtils.isBlank(param.get("poiType").toString())){
            param.put("poiType","01");
            list = disPoiQuery001Mapper.queryPoiIdList(param);
            param.put("poiType","02");
            list.addAll(disPoiQuery001Mapper.queryPoiIdList(param));
        }else {
            list = disPoiQuery001Mapper.queryPoiIdList(param);
        }
        return list;
    }


    /**
     * 根据主键查询产品文件产品要素信息
     *
     * @param param
     * @return
     */
    public DisPoiQuery001Dto selectByPrimaryKey(Map<String, Object> param) {
        String id = param.get("id").toString();
        return disPoiQuery001Mapper.selectById(id);
    }

    /**
     * 根据主键删除数据
     *
     * @param dto
     */
    public void doDelete(DisPoiQuery001Dto dto) {
        List<DisPoiQuery001Dto> keyList = dto.getSelectedItem();
        // 批量删除poi查询sql管理数据
        disPoiQuery001Mapper.deleteByKeyList(keyList);
    }

    /**
     * 保存提交
     *
     * @param dto
     */
    public void doSave(DisPoiQuery001Dto dto) {
        // 有ID是新增，没ID是修改
        if (StringUtils.isNotBlank(dto.getId())) {
            dto.setUpdateUser(CommonUtil.getUserName());
            dto.setUpdateTime(DateUtil.now());
            disPoiQuery001Mapper.updateByPrimaryKeySelective(dto);
        } else {
            dto.setCreateDept(CommonUtil.getDeptCode());
            dto.setCreateUser(CommonUtil.getUserName());
            dto.setCreateTime(DateUtil.now());
            dto.setId(IDGenerator.generate());
            disPoiQuery001Mapper.insertSelective(dto);
        }
    }


}
