package com.joyintech.tams.svreport.chb.businessdatamaintain.chbprdbaseinfo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.ChbPrdresistCodeMapper;
import com.joyintech.tams.common.base.entity.ChbPrdresistCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbprdbaseinfo.dao.ChbPrdBaseInfoDao;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbprdbaseinfo.dto.ChbPrdBaseInfoDto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author wubingbing
 * @date 2022-05-09 11:06
 */
@Service
public class ChbPrdBaseInfoService extends CommonExportService<ChbPrdBaseInfoDto> implements BaseProcessService<ChbPrdBaseInfoDto> {
    @Autowired
    private ChbPrdBaseInfoDao chbPrdBaseInfoDao;
    @Autowired
    private ChbPrdresistCodeMapper chbPrdresistCodeMapper;
    @Override
    @Transactional
    public void doComplete(ChbPrdBaseInfoDto entity, OperationType type) {
        if (OperationType.DELETE.equals(type)) {
            entity.setStatus(DictCodeListExt1.维护数据状态_删除.getValue());
            chbPrdBaseInfoDao.update(entity);

        }
        else if (OperationType.NEW.equals(type)) {
            entity.setDataSource(DictCodeListExt2.数据来源_新建导入.getValue());
            entity.setStatus(DictCodeListExt1.维护数据状态_有效.getValue());
            entity.setProduuid(IDGenerator.generate(32));
            chbPrdBaseInfoDao.insert(entity);
            ChbPrdresistCode code = new ChbPrdresistCode();
            code.setProdid(entity.getProdId());
            code.setRegCode(entity.getRegCode());
            code.setOrgCode(CommonUtil.getOrgCode());
            code.setDeptCode(CommonUtil.getDeptCode());
            code.setCreateTime(DateUtil.now());
            code.setUpdateTime(DateUtil.now());
            code.setCreateUser(CommonUtil.getUserName());
            code.setUpdateUser(CommonUtil.getUserName());
            chbPrdresistCodeMapper.insertSelective(code);
        }
        else if(OperationType.MODIFY.equals(type)){
            entity.setStatus(DictCodeListExt1.维护数据状态_有效.getValue());
            chbPrdBaseInfoDao.insert(entity);
        }
    }

    public List<SuggestDto> getProdIdAndProdName(Map<String, Object> pageParam) {
        return chbPrdBaseInfoDao.getProdIdAndProdName(pageParam);
    }

    public ChbPrdBaseInfoDto selectByKey(String prodId) {
        return chbPrdBaseInfoDao.selectByKey(prodId);
    }

    public Map<String, String> selectRegCodeByProdId(String prodId) {
        return chbPrdBaseInfoDao.selectRegCodeByProdId(prodId);
    }

    public Page<ChbPrdBaseInfoDto> selectForPage(Map<String, Object> pageParam) {
        pageParam.put("customerTypeList", StringUtil.isNotEmpty(pageParam.get("customerType"))?(pageParam.get("customerType").toString().split(",")):null);
        pageParam.put("issuingStatusList", StringUtil.isNotEmpty(pageParam.get("issuingStatus"))?(pageParam.get("issuingStatus").toString().split(",")):null);
        return queryForPage(Integer.parseInt((String) pageParam.get("page")), Integer.parseInt((String) pageParam.get("length")), () -> chbPrdBaseInfoDao.selectAll(pageParam));
    }

    public List<ChbPrdBaseInfoDto> selectAll(Map<String, Object> params) {
        return chbPrdBaseInfoDao.selectAll(params);
    }

    public Page<SelectOptionDto> queryCalendarInfoList(Map<String, Object> pageParam) {
        return this.queryForPageForSelectReturn(Integer.parseInt(StringUtil.isEmpty((String) pageParam.get("page"))?"1":(String) pageParam.get("page")), Integer.parseInt(StringUtil.isEmpty((String) pageParam.get("length"))?"20":(String) pageParam.get("length")), ()-> chbPrdBaseInfoDao.queryCalendarInfoList(pageParam));
    }

    public List<ChbPrdBaseInfoDto> queryForList(String prodIdStr) {
        return chbPrdBaseInfoDao.queryForList(prodIdStr.split(","));
    }

    public void updateIssuingStatus(ChbPrdBaseInfoDto dto) {
         chbPrdBaseInfoDao.updateIssuingStatus(dto);
    }
}
