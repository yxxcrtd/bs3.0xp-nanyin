package com.joyintech.service.disclosure.information.config.proelementadj.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.disclosure.information.config.proelementadj.dao.ProElementAdjMapper;
import com.joyintech.service.disclosure.information.config.proelementadj.dto.ProElementAdjDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProElementAdjService extends BaseService<ProElementAdjDto> {
    @Autowired
    private ProElementAdjMapper proElementAdjMapper;

    public Page<ProElementAdjDto> selectForPage(int page, int length, Map<String, Object> param) {
        String prodRegistNo = (String)param.get("prodRegistNo");
        if(StringUtil.isNotEmpty(prodRegistNo)){
            param.put("prodRegistNoList", StringUtil.splitToList(GlobalConstant.COMMA,prodRegistNo));
        }
        String prodId = (String)param.get("prodId");
        if(StringUtil.isNotEmpty(prodId)){
            param.put("prodIdList", StringUtil.splitToList(GlobalConstant.COMMA,prodId));
        }
        String adjField = (String)param.get("adjField");
        if(StringUtil.isNotEmpty(adjField)){
            param.put("adjFieldList", StringUtil.splitToList(GlobalConstant.COMMA,adjField));
        }
        return super.queryForPage(page, length, () -> proElementAdjMapper.select(param));
    }

    public ProElementAdjDto selectByPrimaryKey(String prodRegistNo) {
        return proElementAdjMapper.selectByPrimaryKey(prodRegistNo);
    }

    public void doComplete(ProElementAdjDto dto) {
        String type = dto.getOperateType();
        if (StringUtil.equals(type, "DELETE")) {
            proElementAdjMapper.deleteByKey(dto.getProdRegistNo());
        }
        else if (StringUtil.equals(type, "NEW")) {
            ProElementAdjDto proElementAdjDto = new ProElementAdjDto();
            BeanUtil.copyPropertiesIgnoreNull(proElementAdjDto,dto);
            proElementAdjMapper.insert(proElementAdjDto);
        }
        else {
            proElementAdjMapper.updateByKey(dto);
        }
    }

    public Page<SelectOptionDto> getRegistNo(int page, int length, Map<String, String> map) {
        return this.queryForPageForSelectReturn(page, length,()->proElementAdjMapper.getRegistNo(map));
    }

    public String selectByProdRegistNo(Map<String, Object> map) {
        return proElementAdjMapper.selectByProdRegistNo(map);
    }

    public Page<SelectOptionDto> getProdId(int page, int length, Map<String, String> map) {
        return this.queryForPageForSelectReturn(page, length,()->proElementAdjMapper.getProdId(map));
    }
}
